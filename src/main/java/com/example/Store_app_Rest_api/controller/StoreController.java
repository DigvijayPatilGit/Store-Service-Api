package com.example.Store_app_Rest_api.controller;

import com.example.Store_app_Rest_api.dto.ResponseStore;
import com.example.Store_app_Rest_api.entity.Store;
import com.example.Store_app_Rest_api.service.StoreService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class StoreController {

    //Logger logger = (Logger) LoggerFactory.getLogger(StoreController.class.getName());

    @Autowired
    private StoreService service;

    @PostMapping("/addItem")
    public ResponseEntity<?> addItemToStore(@RequestBody Store store){
        int id = service.addItem(store);

//        if(id > 0){
//            ResponseStore responseStore = new ResponseStore(HttpStatus.CREATED,"Data Store Successfully!");
//            logger.info(responseStore.getMessage()+ ". code :"+responseStore.getCode());
//            return new ResponseEntity<>(responseStore,HttpStatus.CREATED);
//        }
//        else{
//            ResponseStore responseStore = new ResponseStore(HttpStatus.BAD_REQUEST,"Data Not Store!");
//            logger.info(responseStore.getMessage()+ ". code :"+responseStore.getCode());
//            return new ResponseEntity<>(responseStore,HttpStatus.BAD_REQUEST);
//        }
        ResponseStore responseStore = (id > 0) ?
                new ResponseStore(HttpStatus.CREATED, "Data Store Successfully!") :
                new ResponseStore(HttpStatus.BAD_REQUEST, "Data Not Store!");

        HttpStatus status = (id > 0) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

       // logger.info(responseStore.getMessage() + ". code: " + responseStore.getCode());
        return ResponseEntity.status(status).body(responseStore);
    }

    @GetMapping("/getItem")
    public List<Store> getAllStoreItems(){
        return service.getAllStoreInfo();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStoreInfo(@PathVariable int id, @RequestBody Store updateStore){
        Store store = service.updateStoreRecord(id,updateStore);

        ResponseStore responseStore = (store != null) ?
                new ResponseStore(HttpStatus.OK, "Data Updated Successfully!") :
                new ResponseStore(HttpStatus.BAD_REQUEST, "Unable to update!");

        HttpStatus status = (store != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

       // logger.info(responseStore.getMessage() + ". code: " + responseStore.getCode());
        return ResponseEntity.status(status).body(responseStore);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStoreRecord(@PathVariable int id){
        String resultMessage = service.deleteById(id);

        ResponseStore responseStore =  (resultMessage.equals("No Id Present")) ?
                new ResponseStore(HttpStatus.NOT_FOUND,"Item not found"):
                new ResponseStore(HttpStatus.OK,"Item Deleted successfully");

        HttpStatus status = (resultMessage.equals("No Id Present")) ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        //logger.info(responseStore.getMessage() + ". code: " + responseStore.getCode());
        return ResponseEntity.status(status).body(responseStore);
    }


}
