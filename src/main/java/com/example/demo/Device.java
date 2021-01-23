package com.example.demo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Device")
class Device {
    private String id;
    private String name;
}



