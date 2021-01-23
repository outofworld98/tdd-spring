package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DeviceControllerTests {
    private MockMvc mockMvc;
    private SpyDeviceService spyDeviceService;

    @BeforeEach
    void setUp() {
        spyDeviceService = new SpyDeviceService();
        DeviceController DeviceController = new DeviceController(spyDeviceService);
        mockMvc = MockMvcBuilders.standaloneSetup(DeviceController).build();
    }

    @Test
    public void getDevice_returnOkStatus() throws Exception {
        mockMvc.perform(get("/Devices/deviceA"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getDevice_callsDeviceService_toFindDevice() throws Exception {
        mockMvc.perform(get("/Devices/deviceA"));

        assertThat(spyDeviceService.findDeviceById_argument_deviceId, equalTo("deviceA"));
    }

    @Test
    public void getDevice_returnDeviceInfo() throws Exception {
        Device device = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();

        spyDeviceService.findDeviceById_returnValue = device;

        mockMvc.perform(get("/Devices/deviceA"))
                .andExpect(jsonPath("$.id", equalTo("deviceA")))
                .andExpect(jsonPath("$.name", equalTo("deviceA name")));
    }

    @Test
    public void getDevice_returnsNotFoundStatus() throws Exception {
        spyDeviceService.findDeviceById_throwNotFoundException = true;
        mockMvc.perform(get("/Devices/noExistdevice"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getAllDevices_returnOkStatus() throws Exception {
        mockMvc.perform(get("/Devices"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllDevices_callsDeviceService_toFindAllDevices() throws Exception {
        mockMvc.perform(get("/Devices"));

        assertThat(spyDeviceService.findAllDevices_wasCalled, equalTo(true));
    }

    @Test
    public void getAllDevices_returnDeviceInfoList() throws Exception {
        List<Device> deviceList = Collections.singletonList(
                Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build()
        );

        spyDeviceService.findAllDevices_returnValue = deviceList;

        mockMvc.perform(get("/Devices"))
                .andExpect(jsonPath("$[0].id", equalTo("deviceA")))
                .andExpect(jsonPath("$[0].name", equalTo("deviceA name")));
    }

    @Test
    public void createDevice_returnCreatedStatus() throws Exception {
        String createDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(post("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createDevice_returnsConflictStatus_ifDeviceExists() throws Exception {
        spyDeviceService.createDevice_triggerAlreadyExistException = true;

        String createDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(post("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void createDevice_sendsDeviceInfo_toDeviceService() throws Exception {
        String createDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(post("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Device expectedDevice = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();
        assertThat(spyDeviceService.createDevice_argument_Device, equalTo(expectedDevice));
    }

    @Test
    public void updateDevice_returnsNoContentStatus_whenSuccessful() throws Exception {
        String updateDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(put("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void updateDevice_returnNotFoundStatus_whenUpdateFails() throws Exception {
        spyDeviceService.updateDevice_throwNotFoundException = true;

        String updateDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(put("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateDevice_sendsDeviceInfo_toDeviceService() throws Exception {
        String createDeviceRequest = "{\n" +
                "    \"id\": \"deviceA\",\n" +
                "    \"name\": \"deviceA name\"\n" +
                "}";

        mockMvc.perform(put("/Devices")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createDeviceRequest))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Device expectedDevice = Device.builder()
                .id("deviceA")
                .name("deviceA name")
                .build();
        assertThat(spyDeviceService.updateDevice_argument_Device, equalTo(expectedDevice));
    }

    @Test
    public void deleteDevice_returnsNoContentStatus() throws Exception {
        mockMvc.perform(delete("/Devices/deviceA"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deleteDevice_callsDeviceService_toDeleteDevice() throws Exception {
        mockMvc.perform(delete("/Devices/deviceA"));

        assertThat(spyDeviceService.deleteDevice_argument_deviceId, equalTo("deviceA"));
    }

    @Test
    public void deleteDevice_returnsNotFoundStatus() throws Exception {
        spyDeviceService.deleteDeviceById_throwNotFoundException = true;
        mockMvc.perform(delete("/Devices/noExistdevice"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
