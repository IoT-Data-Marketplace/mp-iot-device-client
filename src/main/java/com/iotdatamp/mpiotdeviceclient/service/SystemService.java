package com.iotdatamp.mpiotdeviceclient.service;

import com.pi4j.platform.PlatformManager;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;


/*
 *
 * credits go to: https://github.com/Pi4J/pi4j/blob/master/pi4j-example/src/main/java/SystemInfoExample.java
 *
 * */

@Slf4j
@Service
public class SystemService {

    public void getSystemInfo() throws InterruptedException, IOException {

        // display a few of the available system information properties
        log.info("----------------------------------------------------");
        log.info("PLATFORM INFO");
        log.info("----------------------------------------------------");
        try {
            log.info("Platform Name     :  " + PlatformManager.getPlatform().getLabel());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Platform ID       :  " + PlatformManager.getPlatform().getId());
        } catch (UnsupportedOperationException ex) {
        }
        log.info("----------------------------------------------------");
        log.info("HARDWARE INFO");
        log.info("----------------------------------------------------");
        try {
            log.info("Serial Number     :  " + SystemInfo.getSerial());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Revision      :  " + SystemInfo.getCpuRevision());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Architecture  :  " + SystemInfo.getCpuArchitecture());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Part          :  " + SystemInfo.getCpuPart());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Temperature   :  " + SystemInfo.getCpuTemperature());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Core Voltage  :  " + SystemInfo.getCpuVoltage());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CPU Model Name    :  " + SystemInfo.getModelName());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Processor         :  " + SystemInfo.getProcessor());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Hardware          :  " + SystemInfo.getHardware());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Hardware Revision :  " + SystemInfo.getRevision());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Is Hard Float ABI :  " + SystemInfo.isHardFloatAbi());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Board Type        :  " + SystemInfo.getBoardType().name());
        } catch (UnsupportedOperationException ex) {
        }

        log.info("----------------------------------------------------");
        log.info("MEMORY INFO");
        log.info("----------------------------------------------------");
        try {
            log.info("Total Memory      :  " + SystemInfo.getMemoryTotal());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Used Memory       :  " + SystemInfo.getMemoryUsed());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Free Memory       :  " + SystemInfo.getMemoryFree());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Shared Memory     :  " + SystemInfo.getMemoryShared());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Memory Buffers    :  " + SystemInfo.getMemoryBuffers());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Cached Memory     :  " + SystemInfo.getMemoryCached());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("SDRAM_C Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_C());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("SDRAM_I Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_I());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("SDRAM_P Voltage   :  " + SystemInfo.getMemoryVoltageSDRam_P());
        } catch (UnsupportedOperationException ex) {
        }

        log.info("----------------------------------------------------");
        log.info("OPERATING SYSTEM INFO");
        log.info("----------------------------------------------------");
        try {
            log.info("OS Name           :  " + SystemInfo.getOsName());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("OS Version        :  " + SystemInfo.getOsVersion());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("OS Architecture   :  " + SystemInfo.getOsArch());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("OS Firmware Build :  " + SystemInfo.getOsFirmwareBuild());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("OS Firmware Date  :  " + SystemInfo.getOsFirmwareDate());
        } catch (UnsupportedOperationException | ParseException ex) {
        }

        log.info("----------------------------------------------------");
        log.info("JAVA ENVIRONMENT INFO");
        log.info("----------------------------------------------------");
        log.info("Java Vendor       :  " + SystemInfo.getJavaVendor());
        log.info("Java Vendor URL   :  " + SystemInfo.getJavaVendorUrl());
        log.info("Java Version      :  " + SystemInfo.getJavaVersion());
        log.info("Java VM           :  " + SystemInfo.getJavaVirtualMachine());
        log.info("Java Runtime      :  " + SystemInfo.getJavaRuntime());

        log.info("----------------------------------------------------");
        log.info("NETWORK INFO");
        log.info("----------------------------------------------------");

        // display some of the network information
        log.info("Hostname          :  " + NetworkInfo.getHostname());
        for (String ipAddress : NetworkInfo.getIPAddresses())
            log.info("IP Addresses      :  " + ipAddress);
        for (String fqdn : NetworkInfo.getFQDNs())
            log.info("FQDN              :  " + fqdn);
        for (String nameserver : NetworkInfo.getNameservers())
            log.info("Nameserver        :  " + nameserver);

        log.info("----------------------------------------------------");
        log.info("CLOCK INFO");
        log.info("----------------------------------------------------");
        try {
            log.info("ARM Frequency     :  " + SystemInfo.getClockFrequencyArm());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("CORE Frequency    :  " + SystemInfo.getClockFrequencyCore());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("H264 Frequency    :  " + SystemInfo.getClockFrequencyH264());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("ISP Frequency     :  " + SystemInfo.getClockFrequencyISP());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("V3D Frequency     :  " + SystemInfo.getClockFrequencyV3D());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("UART Frequency    :  " + SystemInfo.getClockFrequencyUART());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("PWM Frequency     :  " + SystemInfo.getClockFrequencyPWM());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("EMMC Frequency    :  " + SystemInfo.getClockFrequencyEMMC());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("Pixel Frequency   :  " + SystemInfo.getClockFrequencyPixel());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("VEC Frequency     :  " + SystemInfo.getClockFrequencyVEC());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("HDMI Frequency    :  " + SystemInfo.getClockFrequencyHDMI());
        } catch (UnsupportedOperationException ex) {
        }
        try {
            log.info("DPI Frequency     :  " + SystemInfo.getClockFrequencyDPI());
        } catch (UnsupportedOperationException ex) {
        }

        log.info("Exiting SystemInfoExample");
    }


}
