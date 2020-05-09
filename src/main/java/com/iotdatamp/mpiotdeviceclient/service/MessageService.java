package com.iotdatamp.mpiotdeviceclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iotdatamp.mpiotdeviceclient.config.PropertiesBean;
import com.iotdatamp.mpiotdeviceclient.dto.NewMessagesDTO;
import com.iotdatamp.mpiotdeviceclient.service.datasource.BashExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final PropertiesBean properties;
    private final BashExecutor bashExecutor;
    ObjectMapper mapper = new ObjectMapper();

    public void pushMessages() {
        while (true) {
            try {
                // todo this is just for testing
                String bashCommand = "vcgencmd measure_temp";

                Date date = new Date();
                String temperature = bashExecutor.executeBashCommand(bashCommand).replace("temp=", "").replace("'C", "");

                NewMessagesDTO.Record record = NewMessagesDTO.Record.builder()
                        .key(new Timestamp(date.getTime()).toString())
                        .value(temperature)
                        .build();
                NewMessagesDTO newMessagesDTO = NewMessagesDTO.builder().records(Arrays.asList(record)).build();
                String graphQlQuery = createGraphQlQuery(newMessagesDTO);
                log.info("Submitting the request, graphQlQuery: \n".concat(graphQlQuery));
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("application/json");
                String jsonQuery = createJsonQuery(graphQlQuery, mapper.createObjectNode());
                RequestBody body = RequestBody.create(mediaType, jsonQuery);
                Request request = new Request.Builder()
                        .url(properties.getPlatformEndpoint())
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Response response = client.newCall(request).execute();
                log.debug(response.body().string());
                Thread.sleep(100);
            } catch (Exception e) {
                log.error(e.getMessage());
                continue;
            }
        }
    }

    private String createJsonQuery(String graphql, ObjectNode variables)
            throws JsonProcessingException {

        ObjectNode wrapper = mapper.createObjectNode();
        wrapper.put("query", graphql);
        wrapper.set("variables", variables);
        return mapper.writeValueAsString(wrapper);
    }

    //    It does the job for now...
    private String createGraphQlQuery(NewMessagesDTO newMessagesDTO) {
        StringBuilder query = new StringBuilder();
        query.append("mutation{\n");
        query.append("sendMessages(sensorContractAddress:\"" + properties.getSensorID() + "\",\n");
        query.append("newMessagesDTO:{\n");
        query.append("records:[\n");
        newMessagesDTO.getRecords().forEach(record -> {
            query.append("{\n".concat("key:").concat("\"").concat(record.getKey()).concat("\"").concat("\nvalue:").concat("\"").concat(record.getValue()).concat("\"").concat("\n},"));
        });
        query.append("]\n");
        query.append("\n}");
        query.append(") {\n");
        query.append("statusCode\nresponseBody\n");
        query.append("}\n}");
        return query.toString();
    }

}
