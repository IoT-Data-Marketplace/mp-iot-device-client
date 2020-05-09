package com.iotdatamp.mpiotdeviceclient.service;

import com.pi4j.io.w1.W1Master;
import com.pi4j.system.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class TemperatureService {


    public void getTemperature() throws IOException, InterruptedException {

        W1Master w1Master = new W1Master();


        log.info(String.valueOf(SystemInfo.getClockFrequencyCore()));

    }


}
