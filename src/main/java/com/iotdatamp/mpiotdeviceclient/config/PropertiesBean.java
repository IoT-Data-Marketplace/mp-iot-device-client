package com.iotdatamp.mpiotdeviceclient.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class PropertiesBean {

    @Value("${services.platform.endpoint}")
    private String platformEndpoint;

    @Value("${services.sensor.id}")
    private String sensorID;

}
