package com.sbapr.queriesapp;

import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/")
public class Controller {

    @PostMapping("/upload")
    public HttpStatus upload(@RequestParam("file") MultipartFile file) {
        File dest = new File(Objects.requireNonNull(Controller.class.getResource("input.xml")).getPath());
        try {
            file.transferTo(dest.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    @PostMapping("/query")
    public String askQuery(@RequestParam("format") String format, @RequestParam("query") String queryStr) {
        XSLTTransformer transformer = new XSLTTransformer(format);
        transformer.transformXMLToRDF();
        QueryModel queryModel = new QueryModel(format);
        Query query = QueryFactory.create(queryStr);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, queryModel.getModel())) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(outputStream, qexec.execSelect());
            return outputStream.toString();
        }
    }
}

