package com.example.Store_app_Rest_api.service;

import com.example.Store_app_Rest_api.entity.Store;
import com.example.Store_app_Rest_api.exception.IdNotFoundException;
import com.example.Store_app_Rest_api.repository.Store_Repository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private Store_Repository repository;

    public Integer addItem(Store store){
        int storeId = 0;
        repository.save(store);
        storeId = store.getStoreId();
        return storeId;
    }

    public List<Store> getAllStoreInfo(){
        return repository.findAll();
    }

    public Optional<Store> findById(int id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id);
        }
        else{
            throw new IdNotFoundException("Sorry, given ID Not present in record");
        }
    }

    public String deleteById(int id){
        Optional<Store> op = repository.findById(id);
        if(op.isEmpty()){
            return "No Id Present";
        }
        else{
            repository.deleteById(id);
            return "Record deleted Successfully";
        }
    }

    public Store updateStoreRecord(int id, Store store){
        Store store1 = repository.findById(id).get();

        store1.setOwnerName(store.getOwnerName());
        store1.setLocation(store.getLocation());
        store1.setRevenue(store.getRevenue());
        repository.save(store1);
        return store1;
    }

}
