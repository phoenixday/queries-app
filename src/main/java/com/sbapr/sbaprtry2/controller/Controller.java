package com.sbapr.sbaprtry2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/")
public class Controller {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("format") String format, @RequestParam("file") MultipartFile file) {
        log.info("Format... " + format);
        log.info("filename... " + file);
        return ResponseEntity.ok("");
    }

    @GetMapping("/query/{query}")
    public ResponseEntity<?> askQuery(@PathVariable String query) {
        log.info("Query... " + query);
        return ResponseEntity.ok("");
    }
}
