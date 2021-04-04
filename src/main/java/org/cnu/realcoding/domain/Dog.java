package org.cnu.realcoding.domain;

import lombok.Data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dog {
    private String name;
    private String kine;
    private String ownerName;
    private String ownerPhoneNumber;
    private List<String> medicalRecords;
}
