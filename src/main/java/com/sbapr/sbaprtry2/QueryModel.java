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
            // if format == "ese"
            String dictionaries = Objects.requireNonNull(QueryModel.class.getResource("ese/dublin_core_terms.rdf")).getPath();
            Model schema = RDFDataMgr.loadModel(dictionaries);
            Model data = RDFDataMgr.loadModel(outputRDF);
            this.model = ModelFactory.createRDFSModel(schema, data);
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
