package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class DeviceServiceTests {
    DeviceService DeviceService;
    SpyDeviceRepository spyDeviceRepository;

    @BeforeEach
    void setUp() {
        spyDeviceRepository = new SpyDeviceRepository();
        DeviceService = new DefaultDeviceServiceImpl(spyDeviceRepository);
    }

    @Test
    public void findDeviceById_callsDeviceRepository_toFindById() {
        spyDeviceRepository.findById_returnValue = Optional.of(Device.builder().build());
        DeviceService.findDeviceById("deviceA");

        assertThat(spyDeviceRepository.findById_argument_id, equalTo("deviceA"));
    }

    @Test
    public void findDeviceById_returnsDeviceEntity() {
        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        spyDeviceRepository.findById_returnValue = Optional.of(device);
        Device returnedDevice = DeviceService.findDeviceById("deviceA");

        assertThat(returnedDevice, equalTo(device));
    }

    @Test
    public void findAllDevices_callsDeviceRepository() {
        DeviceService.findAllDevices();

        assertThat(spyDeviceRepository.findAll_wasCalled, equalTo(true));
    }

    @Test
    public void findAllDevices_returnsDeviceEntities() {
        List<Device> DeviceEntities = Collections.singletonList(Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build()
        );

        spyDeviceRepository.findAll_returnValue = DeviceEntities;
        List<Device> returnedDevice = DeviceService.findAllDevices();

        assertThat(returnedDevice, equalTo(DeviceEntities));
    }

    @Test
    public void createDevice_passDeviceId_toDeviceRepository_toFindById() {
        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        DeviceService.createDevice(device);

        assertThat(spyDeviceRepository.findById_argument_id, equalTo("deviceA"));
    }

    @Test
    public void createDevice_savesDeviceToDeviceRepository() {
        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        DeviceService.createDevice(device);

        assertThat(spyDeviceRepository.save_argument_entity, equalTo(device));
    }

    @Test
    public void updateDevice_passDeviceId_toDeviceRepository_toFindById() {
        spyDeviceRepository.findById_returnValue = Optional.of(Device.builder().build());

        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        DeviceService.updateDevice(device);

        assertThat(spyDeviceRepository.findById_argument_id, equalTo("deviceA"));
    }

    @Test
    public void updateDevice_savesDeviceToDeviceRepository() {
        spyDeviceRepository.findById_returnValue = Optional.of(Device.builder().build());

        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        DeviceService.updateDevice(device);

        assertThat(spyDeviceRepository.save_argument_entity, equalTo(device));
    }

    @Test
    public void deleteDevice_retrievesExistingDevice() {
        spyDeviceRepository.findById_returnValue = Optional.of(Device.builder().build());

        DeviceService.deleteDevice("deviceA");

        assertThat(spyDeviceRepository.findById_argument_id, equalTo("deviceA"));
    }

    @Test
    public void deleteDevice_deletesDevice_fromDeviceRepository() {
        Device device = Device.builder().build();
        spyDeviceRepository.findById_returnValue = Optional.of(device);

        DeviceService.deleteDevice("deviceA");

        assertThat(spyDeviceRepository.deleteById_argument_id, equalTo("deviceA"));
    }
}
