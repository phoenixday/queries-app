package com.sbapr.queriesapp;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;

import java.util.Objects;

public class QueryModel {

    Model model;

    public QueryModel(String format) {
        String outputRDF = findUri("output.rdf");

        if (format.equalsIgnoreCase("ese")) {
            // if format == "ese"
            String dictionaries = findUri("ese/dublin_core_terms.rdf");
            Model schema = RDFDataMgr.loadModel(dictionaries);
            Model data = RDFDataMgr.loadModel(outputRDF);
            this.model = ModelFactory.createRDFSModel(schema, data);
        } else {
            // if format == "axmpr"
            String dictionaries = findUri("axmpr/dictionaries-schema.rdf");
            InfModel dictionariesModel = ModelFactory.createRDFSModel(RDFDataMgr.loadModel(dictionaries));
            this.model = ModelFactory.createRDFSModel(dictionariesModel, RDFDataMgr.loadModel(outputRDF));
        }
    }

    public Model getModel() {
        return model;
    }

    private String findUri(String path) {
        return Objects.requireNonNull(QueryModel.class.getResource(path)).getPath();
    }
}
