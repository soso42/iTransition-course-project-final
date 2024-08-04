package com.example.courseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionCreationDTO {

    private String username;
    private String name;
    private String description;
    private String topic;

    private MultipartFile image;

    private String customString1;
    private String customString2;
    private String customString3;

    private String customInteger1;
    private String customInteger2;
    private String customInteger3;

    private String customBoolean1;
    private String customBoolean2;
    private String customBoolean3;

    private String customMultilineText1;
    private String customMultilineText2;
    private String customMultilineText3;

    private String customDate1;
    private String customDate2;
    private String customDate3;
}
