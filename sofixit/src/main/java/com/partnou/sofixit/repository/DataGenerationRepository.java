package com.partnou.sofixit.repository;

import com.partnou.sofixit.model.PersonData;

import java.util.List;

public interface DataGenerationRepository {
    List<PersonData> generateRandomPersonDataList(int size);
}
