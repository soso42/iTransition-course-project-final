package com.example.courseproject.service;


import com.example.courseproject.entity.ItemCollection;
import com.example.courseproject.dto.CollectionCreationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemCollectionService {
    ItemCollection createNewCollection(CollectionCreationDTO request, MultipartFile file) throws IOException;
    ItemCollection updateCollection(ItemCollection collection, MultipartFile file) throws IOException;
    void removeCollection(Long id);
    List<ItemCollection> getAllCollectionsByUsername(String username) throws Exception;
    Optional<ItemCollection> getCollectionDetailsByItemId(Long itemId);
    List<ItemCollection> getLargestCollections(Long num);
}
