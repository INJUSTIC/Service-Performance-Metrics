package com.partnou.sofixit.service;

import com.partnou.sofixit.model.PersonData;
import java.util.List;

public interface DataProcessingService {
    String performOperations(List<String> operations, int size);

    String convertToCSVWithGivenColumns(List<String> columns, int size);

    String convertToCSV(int size);
    List<PersonData> getPersonDataListFromFirstService(int size);
}
