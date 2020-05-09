package com.iotdatamp.mpiotdeviceclient.bootstrap;

import com.iotdatamp.mpiotdeviceclient.service.MessageService;
import com.iotdatamp.mpiotdeviceclient.service.SystemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class ClientInit implements CommandLineRunner {

    private final SystemService systemService;
    private final MessageService messageService;

    @Override
    public void run(String... args) throws Exception {
        systemService.getSystemInfo();
        messageService.pushMessages();
    }


}
