package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class SpyDeviceService implements DeviceService {
    public String findDeviceById_argument_deviceId;
    public Device findDeviceById_returnValue;
    public boolean findDeviceById_throwNotFoundException = false;
    public Boolean findAllDevices_wasCalled = false;
    public List<Device> findAllDevices_returnValue;
    public Device createDevice_argument_Device;
    public boolean createDevice_triggerAlreadyExistException = false;
    public Device updateDevice_argument_Device;
    public boolean updateDevice_throwNotFoundException = false;
    public String deleteDevice_argument_deviceId;
    public boolean deleteDeviceById_throwNotFoundException = false;
    @Override
    public Device findDeviceById(String deviceId) {
        if (findDeviceById_throwNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        findDeviceById_argument_deviceId = deviceId;
        return findDeviceById_returnValue;
    }

    @Override
    public List<Device> findAllDevices() {
        findAllDevices_wasCalled = true;
        return findAllDevices_returnValue;
    }

    @Override
    public void createDevice(Device Device) {
        if (createDevice_triggerAlreadyExistException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        createDevice_argument_Device = Device;
    }

    @Override
    public void updateDevice(Device Device) {
        if (updateDevice_throwNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        updateDevice_argument_Device = Device;
    }

    @Override
    public void deleteDevice(String deviceId) {
        if (deleteDeviceById_throwNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        deleteDevice_argument_deviceId = deviceId;
    }
}
