package com.dpease.template.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;


@Service
public class SampleService {
    
    @Autowired
    private SampleRepository sampleRepository;


    public Sample create(Sample sample) {
        Sample saved = sampleRepository.save(sample);
        return saved;
    }

    public Sample read(Long id) {
        Sample sample = sampleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Sample entities with ID=" + id));
        return sample;
    }

    public Sample update(Long id, Sample updatedSample) {
        Sample saved = sampleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Sample entities with ID=" + id));
        saved.setDetails(updatedSample.getDetails());
        Sample updated = sampleRepository.save(saved);
        return updated;
    }

    public void delete(Long id) {
        this.sampleRepository.deleteById(id);
    }

    public List<Sample> getAllSamples() {
        List<Sample> samples = this.sampleRepository.findAll();
        return samples;
    }

}
