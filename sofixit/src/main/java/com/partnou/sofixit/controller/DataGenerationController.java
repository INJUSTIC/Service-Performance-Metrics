package com.partnou.sofixit.controller;

import com.partnou.sofixit.model.PersonData;
import com.partnou.sofixit.service.DataGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DataGenerationController {
    private final DataGenerationService dataGenerationService;

    @Autowired
    public DataGenerationController(DataGenerationService dataGenerationService) {
        this.dataGenerationService = dataGenerationService;
    }

    @GetMapping("/generate/json/{size}")
    public List<PersonData> generateJson(@PathVariable int size) {
        if (size < 0) throw new IllegalArgumentException("size is negative");
        return dataGenerationService.generateRandomPersonDataList(size);
    }

}
