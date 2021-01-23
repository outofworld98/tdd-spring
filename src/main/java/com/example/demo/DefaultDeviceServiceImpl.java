package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultDeviceServiceImpl implements DeviceService {
    DeviceRepository DeviceRepository;

    public DefaultDeviceServiceImpl(DeviceRepository DeviceRepository) {
        this.DeviceRepository = DeviceRepository;
    }

    @Override
    public Device findDeviceById(String deviceId) {
        Optional<Device> maybeDeviceEntity = DeviceRepository.findById(deviceId);
        if (maybeDeviceEntity.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return maybeDeviceEntity.get();
    }

    @Override
    public List<Device> findAllDevices() {
        return DeviceRepository.findAll();
    }

    @Override
    public void createDevice(Device Device) {
        Optional<Device> maybeDeviceEntity = DeviceRepository.findById(Device.getId());
        if (maybeDeviceEntity.isPresent()) {
            throw  new ResponseStatusException(HttpStatus.CONFLICT);
        }

        DeviceRepository.save(Device);
    }

    @Override
    public void updateDevice(Device Device) {
        Optional<Device> maybeDeviceEntity = DeviceRepository.findById(Device.getId());

        if (maybeDeviceEntity.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DeviceRepository.save(Device);
    }

    @Override
    public void deleteDevice(String deviceId) {
        Optional<Device> maybeDeviceEntity = DeviceRepository.findById(deviceId);

        if (maybeDeviceEntity.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        DeviceRepository.deleteById(deviceId);
    }
}
