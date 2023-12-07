package com.partnou.sofixit.service;

import com.partnou.sofixit.model.PersonData;
import com.partnou.sofixit.repository.DataGenerationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataGenerationServiceImpl implements DataGenerationService {
    DataGenerationRepository dataGenerationRepository;

    @Autowired
    public DataGenerationServiceImpl(DataGenerationRepository dataGenerationRepository) {
        this.dataGenerationRepository = dataGenerationRepository;
    }

    @Override
    public List<PersonData> generateRandomPersonDataList(int size) {
        return dataGenerationRepository.generateRandomPersonDataList(size);
    }
}
