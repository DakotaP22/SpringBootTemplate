package com.dpease.template.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/samples")
public class SampleController {
    
    @Autowired
    private SampleService sampleSvc;

    @PostMapping
    public ResponseEntity<Sample> create(@RequestBody Sample sample) {
        Sample createdSample = sampleSvc.create(sample);
        return ResponseEntity.ok().body(createdSample);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Sample> read(@PathVariable Long id) {
        Sample sample = sampleSvc.read(id);
        return ResponseEntity.ok().body(sample);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sample> update(@PathVariable Long id, @RequestBody Sample sample) {
        Sample updatedSample = sampleSvc.update(id, sample);
        return ResponseEntity.ok().body(updatedSample);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        sampleSvc.delete(id);
        return ResponseEntity.ok().body("Sample " + id + " successfully deleted.");
    }

    @GetMapping
    public ResponseEntity<List<Sample>> getSamples() {
        List<Sample> samples = sampleSvc.getAllSamples();
        return ResponseEntity.ok().body(samples);
    }
    

}
