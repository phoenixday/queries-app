package com.sbapr.sbaprtry2.model.queries;

import java.util.Optional;

public class QueriesFactory {

    public static Optional<Queries> retrieveQueries(String XMLFormat, String inputXML, String outputRDF) {
        //TODO: throw an exception if XMLFormat is null?
        if (XMLFormat == null) {
            return Optional.empty();
        }
        if (XMLFormat.equalsIgnoreCase("ese")) {
            return Optional.of(new ESEQueries(inputXML, outputRDF));
        }
        if (XMLFormat.equalsIgnoreCase("axmpr")) {
            return Optional.of(new AXMPRQueries(inputXML, outputRDF));
        }
        //TODO: throw an exception if XMLFormat is not a valid format?
        return Optional.empty();
    }
}
