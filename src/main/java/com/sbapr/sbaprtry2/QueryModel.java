package com.sbapr.sbaprtry2;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;

import java.util.Objects;

public class QueryModel {

    Model model;

    public QueryModel(String format) {
        String outputRDF = Objects.requireNonNull(QueryModel.class.getResource("output.rdf")).getPath();

        if (format.equalsIgnoreCase("ese")) {
            this.model = RDFDataMgr.loadModel(outputRDF);
        } else {
            // if format == "axmpr"
            String dictionaries = Objects.requireNonNull(QueryModel.class.getResource("axmpr/dictionaries_schema.rdf")).getPath();
            InfModel dictionariesModel = ModelFactory.createRDFSModel(RDFDataMgr.loadModel(dictionaries));
            this.model = ModelFactory.createRDFSModel(dictionariesModel, RDFDataMgr.loadModel(outputRDF));
        }
    }

    public Model getModel() {
        return model;
    }
}
