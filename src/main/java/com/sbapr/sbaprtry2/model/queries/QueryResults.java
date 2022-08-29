package com.sbapr.sbaprtry2.model.queries;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * A class for results of a query. Contains a description and results.
 */
@Getter
@Setter
@NoArgsConstructor
public class QueryResults {

    String description;
    List<String> varNames;
    List<QuerySolution> results;

    public QueryResults(String description, ResultSet results) {
        this.description = description;
        this.varNames = results.getResultVars();
        this.results = ResultSetFormatter.toList(results);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);
        String json = outputStream.toString();
    }
}
