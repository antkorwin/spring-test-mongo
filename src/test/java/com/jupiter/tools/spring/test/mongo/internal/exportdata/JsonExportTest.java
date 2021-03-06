package com.jupiter.tools.spring.test.mongo.internal.exportdata;

import com.antkorwin.commonutils.exceptions.InternalException;
import com.jupiter.tools.spring.test.mongo.Bar;
import com.jupiter.tools.spring.test.mongo.internal.DataSet;
import com.jupiter.tools.spring.test.mongo.internal.importdata.ImportFile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 06.12.2018.
 *
 * @author Korovin Anatoliy
 */
class JsonExportTest {

    @Test
    void testExport() {
        // Arrange
        String expectedJson = getExpectedJson();
        DataSet dataSet = getDataSet();
        // Act
        String text = new JsonExport(dataSet).read();
        // Asserts
        assertThat(text).isNotNull()
                        .isEqualTo(expectedJson);
    }

    @Test
    void wrongDataSet() {
        Assertions.assertThrows(InternalException.class,
                                () -> new JsonExport(null).read());
    }

    private DataSet getDataSet() {

        ObjectMapper mapper = new ObjectMapper();

        Bar bar1 = new Bar("111100001", "data-1");
        Bar bar2 = new Bar("111100002", "data-2");

        Map<String, List<Map<String, Object>>> map =
                ImmutableMap.of(Bar.class.getCanonicalName(),
                                Arrays.asList(mapper.convertValue(bar1, Map.class),
                                              mapper.convertValue(bar2, Map.class)));
        return () -> map;
    }

    private String getExpectedJson() {
        return new ImportFile("/dataset/internal/json_expected.json").read();
    }
}