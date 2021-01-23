package com.example.demo;

import java.util.List;

public interface DeviceService {
    Device findDeviceById(String deviceId);

    List<Device> findAllDevices();

    void createDevice(Device Device);

    void updateDevice(Device Device);

    void deleteDevice(String deviceId);
}
