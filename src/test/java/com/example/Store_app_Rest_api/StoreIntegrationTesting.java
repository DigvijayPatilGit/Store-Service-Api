package com.example.Store_app_Rest_api;

import com.example.Store_app_Rest_api.controller.StoreController;
import com.example.Store_app_Rest_api.entity.Store;
import com.example.Store_app_Rest_api.service.StoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(StoreController.class)
public class StoreIntegrationTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateStoreRecord_WhenRecordIsSuccessFullySaved() throws Exception {
        int storeId = 1;
        Store store = new Store(0,"Raman","Pune",234455.76);
        String storeData = objectMapper.writeValueAsString(store);

        Mockito.when(service.addItem(Mockito.any(Store.class))).thenReturn(storeId);

        this.mockMvc.perform(post("/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeData))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("{" +
                        "\"message\":\"Data Store Successfully!\"," +
                        "\"code\":201," +
                        "\"httpStatus\":\"CREATED\"" +
                        "}"));

    }

    @Test
    void shouldNotCreateStoreRecord_WhenRecordNotSuccessFullySaved() throws Exception {
        int storeIdNotSaveIndicator = 0;
        Store store = new Store(0,"Raman","Pune",234455.76);
        String storeData = objectMapper.writeValueAsString(store);

        Mockito.when(service.addItem(Mockito.any(Store.class))).thenReturn(storeIdNotSaveIndicator);

        this.mockMvc.perform(post("/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeData))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{" +
                        "\"message\":\"Data Not Store!\"," +
                        "\"code\":400," +
                        "\"httpStatus\":\"BAD_REQUEST\"" +
                        "}"));

    }

    @Test
    public void shouldHandledEntityNotFoundException() throws Exception {
        Store store = new Store(0,"Raman","Pune",234455.76);
        String storeData = objectMapper.writeValueAsString(store);

        when(service.addItem(any(Store.class))).thenThrow(new EntityNotFoundException("test message"));

        this.mockMvc.perform(post("/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(storeData))

                .andDo(print()).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus", org.hamcrest.Matchers.is("NOT_FOUND")))
                .andExpect(jsonPath("$.message", org.hamcrest.Matchers.is("test message")));
    }

}
