package com.sbapr.sbaprtry2.model.queries;

import com.sbapr.sbaprtry2.model.transformers.Transformer;
import com.sbapr.sbaprtry2.model.transformers.XSLTTransformer;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;

import java.util.List;

public class AXMPRQueries extends Queries {

    String dictionaries = "src/main/java/com/example/demo/model/assets/axmpr/dictionaries_schema.rdf";

    public AXMPRQueries(String inputXML, String outputRDF) {
        super();
        Transformer transformer = new XSLTTransformer("AXMPR", inputXML, outputRDF);
        transformer.transformXMLToRDF();
        InfModel dictionariesModel = ModelFactory.createRDFSModel(RDFDataMgr.loadModel(dictionaries));
        setModel(ModelFactory.createRDFSModel(dictionariesModel, RDFDataMgr.loadModel(outputRDF)));
    }

    @Override
    public List<QueryResults> computeQueries() {
        return List.of(selectAll(), selectAllRegionsFromEU());
    }

//    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
//    PREFIX dc: <http://purl.org/dc/elements/1.1/>
//    PREFIX dcterms: <http://purl.org/dc/terms/>
//    PREFIX myloc: <http://myloc.org/>
//    SELECT ?region ?title WHERE {
//                ?continent dc:title "Evropa".
//                ?region dcterms:isPartOf* ?continent .
//                ?region a myloc:region .
//                ?region dc:title ?title
//    }


    QueryResults selectAllRegionsFromEU() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX dc: <http://purl.org/dc/elements/1.1/>" + "PREFIX dcterms: <http://purl.org/dc/terms/>" + "PREFIX : <http://myloc.org/>" + "SELECT * WHERE {" +
                //"   ?subject ?predicate ?lokalita ." +
                "   ?region dcterms:isPartOf* :17143 ." + "   ?region dc:title ?title" + "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all regions from Europe", qexec.execSelect());
        }
    }
}
