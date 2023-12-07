package com.partnou.sofixit.service;

import com.partnou.sofixit.model.PersonData;

import java.util.List;

public interface DataGenerationService {
    List<PersonData> generateRandomPersonDataList(int size);
}
