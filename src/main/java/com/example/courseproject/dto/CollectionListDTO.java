package com.example.courseproject.dto;

import com.example.courseproject.entity.ItemCollection;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionListDTO {

    private List<ItemCollection> collections;

}
