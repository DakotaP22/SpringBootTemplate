package com.dpease.template.sample;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;



@DataJpaTest(properties = "spring.test.database.replace=NONE")
@Testcontainers
@ActiveProfiles("test")
public class SampleRepositoryTest {
    
    @Container
    @ServiceConnection
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.2");

    @Autowired
    private SampleRepository sampleRepository;

    @BeforeEach
    public void setup() {
        sampleRepository.deleteAll(); // give each test a clean DB
    }


    /*
     * 
     * Avoid writing unit tests for basic JPA operations. JPA is battle tested and works very well for CRUD operations.
     * Focus Integration Testing for the DB layer on custom queries, transactions, and other complex queries/operations.
     * The below tests serve mostly as an example of how to write tests for JPA repositories for completeness of this sample.
     */


    @Test
    public void create() {
        Sample sample = new Sample();
        sample.setDetails("Some details");

        sampleRepository.save(sample);

        Assertions.assertEquals(sampleRepository.count(), 1);
    }

    @Test
    public void read() {
        String detailsTestString = "Some details: " + UUID.randomUUID().toString();

        Sample sample = new Sample();
        sample.setDetails(detailsTestString);

        Sample saved = sampleRepository.save(sample);

        Sample found = sampleRepository.findById(saved.getId()).get();
        Assertions.assertEquals(found.getDetails(), detailsTestString);
    }

    @Test
    public void update() {
        String updatedDetailsTestString = "Updated details: " + UUID.randomUUID().toString();

        Sample sample = new Sample();
        sample.setDetails("Some details");

        Sample saved = sampleRepository.save(sample);

        saved.setDetails(updatedDetailsTestString);        
        Sample updated = sampleRepository.save(saved);


        Sample found = sampleRepository.findById(updated.getId()).get();

        Assertions.assertEquals(found.getDetails(), updatedDetailsTestString);
    }

    @Test
    public void delete() {
        Sample sample = new Sample();
        sample.setDetails("Some details");

        Sample saved = sampleRepository.save(sample);

        sampleRepository.delete(saved);

        Assertions.assertEquals(sampleRepository.count(), 0);
    }


}
