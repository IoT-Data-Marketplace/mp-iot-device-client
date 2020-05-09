package com.iotdatamp.mpiotdeviceclient.service.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/*
 *
 * credits go to: https://stackoverflow.com/a/45376510/7269889
 *
 * */

@Slf4j
@Service
public class BashExecutor {

    public String executeBashCommand(String command) {
        String result = "";
        log.info("Executing BASH command:\n   " + command);
        Runtime r = Runtime.getRuntime();
        String[] commands = {"bash", "-c", command};
        try {
            Process p = r.exec(commands);

            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";

            while ((line = b.readLine()) != null) {
                System.out.println(line);
                result = line;
            }

            b.close();
        } catch (Exception e) {
            log.error("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
        return result;
    }

}
