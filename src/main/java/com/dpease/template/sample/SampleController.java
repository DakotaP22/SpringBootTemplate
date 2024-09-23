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

import com.dpease.template.sample.dto.SampleDTO;


@RestController
@RequestMapping("/samples")
public class SampleController {
    
    @Autowired
    private SampleService sampleSvc;

    @PostMapping
    public ResponseEntity<Sample> create(@RequestBody SampleDTO dto) {
        Sample sample = sampleSvc.create(dto);
        return ResponseEntity.ok().body(sample);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Sample> read(@PathVariable Long id) {
        Sample sample = sampleSvc.read(id);
        return ResponseEntity.ok().body(sample);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sample> update(@PathVariable Long id, @RequestBody SampleDTO dto) {
        Sample sample = sampleSvc.update(id, dto);
        return ResponseEntity.ok().body(sample);
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
