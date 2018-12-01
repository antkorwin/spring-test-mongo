package com.antkorwin.springtestmongo.junit5;

import com.antkorwin.springtestmongo.Bar;
import com.antkorwin.springtestmongo.MongoPopulator;
import com.antkorwin.springtestmongo.annotation.MongoDataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 01.12.2018.
 *
 * @author Korovin Anatoliy
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith({MongoExtensionCleanBeforeIT.TestExtension.class, MongoExtension.class})
class MongoExtensionCleanBeforeIT {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void name() {
        Bar simpleDoc = mongoTemplate.findById("55f3ed00b1375a48e61830bf", Bar.class);
        assertThat(simpleDoc).isNotNull();
    }

    @Test
    @MongoDataSet(cleanBefore = true)
    void cleanBefore() {

        Bar simpleDoc = mongoTemplate.findById("55f3ed00b1375a48e61830bf", Bar.class);
        assertThat(simpleDoc).isNull();

        assertThat(mongoTemplate.getCollectionNames().size()).isEqualTo(0);
    }

    public static class TestExtension implements Extension, BeforeEachCallback {

        @Override
        public void beforeEach(ExtensionContext context) throws Exception {

            MongoTemplate mongoTemplate = SpringExtension.getApplicationContext(context)
                                                         .getBean(MongoTemplate.class);

            MongoPopulator.populate(mongoTemplate, "/dataset/simple_dataset.json");
        }
    }
}
