package com.sbapr.sbaprtry2.model.queries;

import com.sbapr.sbaprtry2.model.transformers.Transformer;
import com.sbapr.sbaprtry2.model.transformers.XSLTTransformer;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.riot.RDFDataMgr;

import java.util.List;

public class ESEQueries extends Queries {

    public ESEQueries(String inputXML, String outputRDF) {
        super();
        Transformer transformer = new XSLTTransformer("ESE", inputXML, outputRDF);
        transformer.transformXMLToRDF();
        //setModel(ModelFactory.createRDFSModel(RDFDataMgr.loadModel(outputRDF)));
        setModel(RDFDataMgr.loadModel(outputRDF));
    }

    @Override
    public List<QueryResults> computeQueries() {
        return List.of(selectAll(), selectAllMaterials(), selectAllDurations(), selectAllCollections(), selectAllPlaces(), selectAllMuseums(), selectAllCoverages());
    }

    QueryResults selectAllMaterials() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "SELECT distinct ?material WHERE {" + "   ?subject dcterms:medium ?material" + "}" + "ORDER BY ?material";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all materials", qexec.execSelect());
        }
    }

    QueryResults selectAllDurations() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "SELECT distinct ?duration WHERE {" + "   ?subject dcterms:temporal ?duration" + "}" + "ORDER BY ?duration";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all durations", qexec.execSelect());
        }
    }

    QueryResults selectAllCoverages() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "SELECT distinct ?duration WHERE {" + "   ?subject dc:coverage ?duration" + "}" + "ORDER BY ?duration";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all coverages", qexec.execSelect());
        }
    }

    QueryResults selectAllCollections() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "SELECT distinct ?collection WHERE {" + "   ?subject dc:relation ?collection" + "}" + "ORDER BY ?collection";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all collections", qexec.execSelect());
        }
    }

    QueryResults selectAllPlaces() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "SELECT distinct ?place WHERE {" + "   ?subject dcterms:spatial ?place" + "}" + "ORDER BY ?place";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all places", qexec.execSelect());
        }
    }

    QueryResults selectAllMuseums() {
        String queryString = "" + "PREFIX europeana: <http://www.europeana.eu/schemas/ese/>" + "SELECT distinct ?museum WHERE {" + "   ?subject europeana:dataProvider ?museum" + "}" + "ORDER BY ?museum";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all museums", qexec.execSelect());
        }
    }
}
