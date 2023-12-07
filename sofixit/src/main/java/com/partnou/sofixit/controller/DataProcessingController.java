package com.partnou.sofixit.controller;
import com.partnou.sofixit.service.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;

    @Autowired
    public DataProcessingController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    //zwraca dane w formacie csv z kolumnami ‘type, _id, name, type, latitude, longitude’
    @GetMapping("/data/csv/{size}")
    public String convertToCSV(@PathVariable int size) {
        return dataProcessingService.convertToCSV(size);
    }

    //zwraca dane w formacie csv z kolumnami podanymi kolumnami
    @GetMapping("/data/customCsv/{size}")
    public String convertToCSVWithGivenColumns(@PathVariable int size, @RequestParam String columns) {
        //tworzy listę kolumn podzielając ciąg znaków przecinkiem oraz ignorując wszystkie spacje
        List<String> columnList = List.of(columns.split("\\s*,\\s*"));
        return dataProcessingService.convertToCSVWithGivenColumns(columnList, size);
    }

    //%2B dla + oraz %5E dla ^
    @GetMapping("/calculate/{size}")
    public String performOperations(@PathVariable int size, @RequestParam String operations) {
        operations = operations.replaceAll("\\s", "");
        List<String> operationList = List.of(operations.split(","));
        return dataProcessingService.performOperations(operationList, size);
    }

}
