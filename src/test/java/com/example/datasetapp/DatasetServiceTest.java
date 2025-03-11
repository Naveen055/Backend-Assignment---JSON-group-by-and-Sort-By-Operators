package com.example.datasetapp;

import com.example.datasetapp.service.DatasetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatasetServiceTest {
    @SuppressWarnings("unused")
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final DatasetService datasetService = mock(DatasetService.class);

    @Test
    public void testGroupBy() throws Exception {
        Map<String, List<Map<String, Object>>> expected = Map.of(
                "Engineering", List.of(Map.of("id", 1, "name", "John", "age", 30, "department", "Engineering"))
        );

        when(datasetService.groupBy("employee_dataset", "department")).thenReturn(expected);
        assertEquals(expected, datasetService.groupBy("employee_dataset", "department"));
    }
}
