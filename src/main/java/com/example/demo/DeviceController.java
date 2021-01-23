package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Devices")
public class DeviceController {
    DeviceService DeviceService;

    public DeviceController(DeviceService DeviceService) {
        this.DeviceService = DeviceService;
    }

    @GetMapping("/{deviceId}")
    public Device getDevice(@PathVariable String deviceId) {
        return DeviceService.findDeviceById(deviceId);
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return DeviceService.findAllDevices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDevice(@RequestBody Device Device) {
        DeviceService.createDevice(Device);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDevice(@RequestBody Device Device) {
        DeviceService.updateDevice(Device);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDevice(@PathVariable String deviceId) {
        DeviceService.deleteDevice(deviceId);
    }
}
