package com.example.courseproject.service.impl;

import com.example.courseproject.entity.ItemCollection;
import com.example.courseproject.entity.User;
import com.example.courseproject.dto.CollectionCreationDTO;
import com.example.courseproject.repository.ItemCollectionRepository;
//import com.example.courseproject.repository_elastic.CollectionIndexRepository;
import com.example.courseproject.service.ItemCollectionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemCollectionServiceImpl implements ItemCollectionService {

    private final ItemCollectionRepository itemCollectionRepository;
    private final UserServiceImpl userService;
//    private final CollectionIndexRepository collectionIndexRepository;
    private final ModelMapper modelMapper;

    public ItemCollectionServiceImpl(ItemCollectionRepository itemCollectionRepository,
                                     UserServiceImpl userService,
//                                     CollectionIndexRepository collectionIndexRepository,
                                     ModelMapper modelMapper) {
        this.itemCollectionRepository = itemCollectionRepository;
        this.userService = userService;
//        this.collectionIndexRepository = collectionIndexRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemCollection createNewCollection(CollectionCreationDTO dto, MultipartFile file) throws IOException {

        ItemCollection collection = new ItemCollection();

        modelMapper.map(dto, collection);

        collection.setAuthor(userService.findByUsername(dto.getUsername()).get());
        collection.setImage(file.getBytes());

        collection = itemCollectionRepository.save(collection);
//        collectionIndexRepository.save(collection);
        return collection;
    }

    @Override
    public ItemCollection updateCollection(ItemCollection request, MultipartFile file) throws IOException {
        ItemCollection dbCollection = itemCollectionRepository.findById(request.getItemCollectionId()).get();
        modelMapper.map(dbCollection, request);
        dbCollection.setImage(file == null ? null : file.getBytes());
        dbCollection = itemCollectionRepository.save(dbCollection);

//        ItemCollection indexedCollection = collectionIndexRepository.findById(dbCollection.getItemCollectionId()).get();
//        modelMapper.map(indexedCollection, request);
//        collectionIndexRepository.save(indexedCollection);
        return dbCollection;
    }

    @Override
    public void removeCollection(Long id) {
        itemCollectionRepository.deleteById(id);
//        collectionIndexRepository.deleteById(id);
    }

    @Override
    public List<ItemCollection> getAllCollectionsByUsername(String username) throws Exception {
        User author = userService.findByUsername(username).get();
        if (!userService.findByUsername(username).isPresent()) {
            throw new Exception();
        }
        return itemCollectionRepository.findAllByAuthor(author);
    }

    @Override
    public Optional<ItemCollection> getCollectionDetailsByItemId(Long itemId) {
        return itemCollectionRepository.findCollectionByItemId(itemId);
    }

    @Override
    public List<ItemCollection> getLargestCollections(Long num) {
        return itemCollectionRepository.findLargestCollections(num);
    }

}
