package com.example.datasetapp.service;

import com.example.datasetapp.model.DatasetRecord;
import com.example.datasetapp.repository.DatasetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatasetService {

    private final DatasetRepository repository;
    private final ObjectMapper objectMapper;

    public DatasetRecord insertRecord(String datasetName, Map<String, Object> jsonData) throws Exception {
        try {
        DatasetRecord record = new DatasetRecord();
        record.setDatasetName(datasetName);
        record.setJsonData(objectMapper.writeValueAsString(jsonData));
        System.out.println("Inserting: " + record);
        return repository.save(record);
    }catch (Exception e) {
        e.printStackTrace();  // Print detailed error in console
        throw new Exception("Error inserting record: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    public Map<String, List<Map<String, Object>>> groupBy(String datasetName, String field) throws Exception {
        List<DatasetRecord> records = repository.findByDatasetName(datasetName);
        return records.stream()
                .map(record -> {
                    try {
                        return objectMapper.readValue(record.getJsonData(), Map.class);
                    } catch (Exception e) {
                        return Collections.emptyMap();
                    }
                })
                .filter(record -> record.containsKey(field))  // Prevent null field issues
                .collect(Collectors.groupingBy(
    record -> String.valueOf(record.get(field)),
    LinkedHashMap::new,
    Collectors.mapping(record -> (Map<String, Object>) record, Collectors.toList())
));

    }

    public List<Map<String, Object>> sortBy(String datasetName, String field, String order) throws Exception {
        List<DatasetRecord> records = repository.findByDatasetName(datasetName);
        List<Map<String, Object>> jsonDataList = new ArrayList<>();
        for (DatasetRecord record : records) {
            jsonDataList.add(objectMapper.readValue(record.getJsonData(), Map.class));
        }

        Comparator<Map<String, Object>> comparator = Comparator.comparing(record -> (Comparable) record.get(field));
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        return jsonDataList.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
