package com.jupiter.tools.spring.test.mongo.junit5.meta.annotation;

import com.jupiter.tools.spring.test.mongo.junit5.EnableMongoDbTestContainers;
import com.jupiter.tools.spring.test.mongo.junit5.MongoDbExtension;
import com.jupiter.tools.spring.test.mongo.annotation.MongoDataSet;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Runs MongoDb testcontainers and JUnit5 extension - {@link MongoDbExtension},
 * to execute tests with the {@link MongoDataSet} annotation.
 * <p>
 * Using this annotation will enable full auto-configuration
 * and will start an entire application context.
 * <p>
 * @author Korovin Anatoliy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ExtendWith({SpringExtension.class, MongoDbExtension.class})
@EnableMongoDbTestContainers
public @interface MongoDbIntegrationTest {
}
