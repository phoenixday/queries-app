package com.sbapr.sbaprtry2.model.queries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.rdf.model.Model;

import java.util.List;

//TODO: store model somewhere?

/**
 * A common class for demonstration queries for generated ontologies.
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class Queries {

    /**
     * An Apache Jena model, that stores all triples
     */
    Model model;

    /**
     * By default, list of queries should contain at least selectAll().
     */
    abstract public List<QueryResults> computeQueries();

    /**
     * A method that returns all triples.
     *
     * @return all triples made from RDF
     */
    QueryResults selectAll() {
        String queryString = "" + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + "PREFIX : <http://www.openarchives.org/OAI/2.0/#>" + "SELECT * WHERE {" + "   ?subject ?predicate ?object" + "}";
        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, getModel())) {
            return new QueryResults("Select all triples", qexec.execSelect());
        }
    }
}
