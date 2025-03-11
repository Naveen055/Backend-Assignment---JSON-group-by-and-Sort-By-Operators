package com.example.datasetapp.controller;

import com.example.datasetapp.model.DatasetRecord;
import com.example.datasetapp.service.DatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dataset")
@RequiredArgsConstructor
public class DatasetController {

    private final DatasetService datasetService;

    @PostMapping("/{datasetName}/record")
    public ResponseEntity<?> insertRecord(@PathVariable String datasetName, @RequestBody Map<String, Object> jsonData) {
        try {
            DatasetRecord record = datasetService.insertRecord(datasetName, jsonData);
            return ResponseEntity.ok(Map.of(
                    "message", "Record added successfully",
                    "dataset", datasetName,
                    "recordId", record.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error inserting record");
        }
    }

    @GetMapping("/{datasetName}/query")
    public ResponseEntity<?> queryRecords(@PathVariable String datasetName,
                                          @RequestParam(required = false) String groupBy,
                                          @RequestParam(required = false) String sortBy,
                                          @RequestParam(required = false, defaultValue = "asc") String order) {
        try {
            if (groupBy != null) {
                return ResponseEntity.ok(Map.of("groupedRecords", datasetService.groupBy(datasetName, groupBy)));
            } else if (sortBy != null) {
                return ResponseEntity.ok(Map.of("sortedRecords", datasetService.sortBy(datasetName, sortBy, order)));
            } else {
                return ResponseEntity.badRequest().body("Provide either groupBy or sortBy parameter");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error processing request");
        }
    }
}
