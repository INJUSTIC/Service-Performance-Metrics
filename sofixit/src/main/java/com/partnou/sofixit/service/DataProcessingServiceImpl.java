package com.partnou.sofixit.service;

import com.partnou.sofixit.model.PersonData;
import com.partnou.sofixit.operationPerformer.OperationPerformer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DataProcessingServiceImpl implements DataProcessingService{

    private final RestTemplate restTemplate;


    public DataProcessingServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public String performOperations(List<String> operations, int size) {
        List<PersonData> personDataList = getPersonDataListFromFirstService(size);
        return OperationPerformer.performOperations(personDataList, operations);
    }

    @Override
    public String convertToCSVWithGivenColumns(List<String> columns, int size) {
        List<PersonData> personDataList = getPersonDataListFromFirstService(size);
        StringBuilder csvData = new StringBuilder();

        // Nagłówki
        String headers = String.join(",", columns) + "<br>";
        csvData.append(headers);

        for (PersonData personData : personDataList) {
            StringBuilder rowData = new StringBuilder();

            //szukamy pola które mają taką samą nazwę jak kolumne
            for (String column : columns) {
                Object colValue = getColValue(personData, column);
                rowData.append(colValue).append(";");
            }

            // Usuwanie ostatniego przecinku w wierszu
            rowData.deleteCharAt(rowData.length() - 1);
            csvData.append(rowData).append("<br>");
        }

        return csvData.toString();
    }

    @Override
    public String convertToCSV(int size) {
        List<PersonData> personDataList = getPersonDataListFromFirstService(size);
        String headers = "Type, _id, Name, Type, Latitude, Longitude <br>";

        // Tworzenie danych w formacie CSV
        StringBuilder csvData = new StringBuilder();
        for (PersonData data : personDataList) {
            csvData.append(String.format("%s; %d; %s; %s; %f; %f <br>",
                    data.get_type(), data.get_id(), data.getName(), data.getType(),
                    data.getGeoPosition().getLatitude(), data.getGeoPosition().getLongitude()));
        }

        return headers + csvData;
    }

    private Object getColValue(PersonData personData, String column) {
        return switch(column) {
            case "_id" -> personData.get_id();
            case "_type" -> personData.get_type();
            case "key" -> personData.getKey();
            case "name" -> personData.getName();
            case "fullName" -> personData.getFullName();
            case "location_id" -> personData.getLocation_id();
            case "iata_airport_code" -> personData.getIata_airport_code();
            case "type" -> personData.getType();
            case "coreCountry" -> personData.getCountryData();
            case "distance" -> personData.getDistanceInKm();
            case "geo_position" -> personData.getGeoPosition();
            case "latitude" -> personData.getGeoPosition().getLatitude();
            case "longitude" -> personData.getGeoPosition().getLongitude();
            case "country" -> personData.getCountryData().getCountry();
            case "inEurope" -> personData.getCountryData().isInEurope();
            case "countryCode" -> personData.getCountryData().getCountryCode();
            //w przypadku niepoprawnej kolumny
            default -> throw new IllegalArgumentException("Incorrect column: " + column);
        };
    }

    @Override
    public List<PersonData> getPersonDataListFromFirstService(int size) {
        String url = "http://localhost:8080/generate/json/" + size;

        PersonData[] personDataArray = restTemplate.getForObject(url, PersonData[].class);

        //Jak nie dostaliśmy żadnych danych, zwrócamy null
        if (personDataArray == null) return null;

        return Arrays.asList(personDataArray);
    }
}
