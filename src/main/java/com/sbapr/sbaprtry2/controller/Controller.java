package com.sbapr.sbaprtry2.controller;

import com.sbapr.sbaprtry2.model.transformers.Transformer;
import com.sbapr.sbaprtry2.model.transformers.XSLTTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/")
public class Controller {
    String inputXML = "src/main/java/com/sbapr/sbaprtry2/controller/putMeSomewhereElse/input.xml";
    String outputRDF = "src/main/java/com/sbapr/sbaprtry2/controller/putMeSomewhereElse/output.rdf";
    String dictionaries = "src/main/java/com/sbapr/sbaprtry2/model/assets/axmpr/dictionaries_schema.rdf";
    String format;

    @PostMapping("/upload")
    public HttpStatus upload(@RequestParam("format") String format, @RequestParam("file") MultipartFile file) {
        this.format = format;
        File dest = new File(inputXML);
        try {
            file.transferTo(dest.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            //TODO error handling
        }
        return HttpStatus.OK;
    }

    @PostMapping("/query")
    public String askQuery(@RequestParam("query") String queryStr) {
        Transformer transformer = new XSLTTransformer(format, inputXML, outputRDF);
        transformer.transformXMLToRDF();
        Model model = createModel();
        Query query = QueryFactory.create(queryStr);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ResultSetFormatter.outputAsJSON(outputStream, qexec.execSelect());
            return outputStream.toString();
        }
    }

    private Model createModel() {
        if (format.equalsIgnoreCase("ese")) {
            return RDFDataMgr.loadModel(outputRDF);
        }
        // if format == "axmpr"
        InfModel dictionariesModel = ModelFactory.createRDFSModel(RDFDataMgr.loadModel(dictionaries));
        return ModelFactory.createRDFSModel(dictionariesModel, RDFDataMgr.loadModel(outputRDF));
    }
}

