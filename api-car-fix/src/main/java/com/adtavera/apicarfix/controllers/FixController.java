package com.adtavera.apicarfix.controllers;

import com.adtavera.apicarfix.dtos.FixDto;
import com.adtavera.apicarfix.models.Fix;
import com.adtavera.apicarfix.services.FixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixes")
public class FixController {

    @Autowired
    private FixService fixService;

    @GetMapping
    public ResponseEntity<List<Fix>> getFixes() {
        List<Fix> fixes = this.fixService.findAll();
        return ResponseEntity.ok(fixes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fix> getFix(
            @PathVariable("id") Long id
    ) {
        Fix fix = this.fixService.findById(id);
        return ResponseEntity.ok(fix);
    }

    @PostMapping
    public ResponseEntity<Fix> create(@Valid @RequestBody FixDto fixDto) {
        return ResponseEntity.ok(this.fixService.create(fixDto));
    }
}
