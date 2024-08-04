package com.example.courseproject.controller;

import com.example.courseproject.entity.ItemCollection;
import com.example.courseproject.dto.CollectionListDTO;
import com.example.courseproject.dto.CollectionCreationDTO;
import com.example.courseproject.service.impl.ItemCollectionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ItemCollectionController {

    private final ItemCollectionServiceImpl collectionService;

    public ItemCollectionController(ItemCollectionServiceImpl collectionService) {
        this.collectionService = collectionService;
    }

    @RequestMapping(value = "/collection/new", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> createNewCollection(
                        @RequestPart("properties") CollectionCreationDTO collectionCreationDTO,
                        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        ItemCollection itemCollection = collectionService.createNewCollection(collectionCreationDTO, file);
        return new ResponseEntity<>(itemCollection, HttpStatus.OK);
    }

    @RequestMapping(value = "/collection/update", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateCollection(
                        @RequestPart("properties") ItemCollection collection,
                        @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        ItemCollection updatedCollection = collectionService.updateCollection(collection, file);
        return new ResponseEntity<>(updatedCollection, HttpStatus.OK);
    }

    @DeleteMapping("/collection/remove/{id}")
    public ResponseEntity<?> removeCollection(@PathVariable Long id) {
        collectionService.removeCollection(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/collection/user={username}")
    public ResponseEntity<?> getAllByUsername(@PathVariable String username) {
        CollectionListDTO collections = new CollectionListDTO();
        try {
            collections.setCollections(collectionService.getAllCollectionsByUsername(username));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @GetMapping("/collection/byItemId={itemId}")
    public ResponseEntity<?> getCollectionDetailsByItemId(@PathVariable Long itemId) {
        Optional<ItemCollection> collection = collectionService.getCollectionDetailsByItemId(itemId);
        if (collection.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(collection.get(), HttpStatus.OK);
    }

    @GetMapping("/collection/top/{num}")
    public ResponseEntity<?> getLargestCollections(@PathVariable Long num) {
        CollectionListDTO response = new CollectionListDTO();
        response.setCollections(collectionService.getLargestCollections(num));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
