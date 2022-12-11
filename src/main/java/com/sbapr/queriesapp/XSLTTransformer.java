package com.sbapr.queriesapp;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Objects;

public class XSLTTransformer{

    String format;
    String XSLT_PATH = "transform.xslt";
    String XML_PATH = "input.xml";
    String RDF_PATH = "output.rdf";

    public XSLTTransformer(String format) {
        this.format = format.toLowerCase();
    }

    public void transformXMLToRDF() {
        try {
            TransformerFactory
                    .newInstance()
                    .newTransformer(createStreamSource(format + "/" + XSLT_PATH))
                    .transform(createStreamSource(XML_PATH), createStreamResult(RDF_PATH));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private StreamSource createStreamSource(String path) {
        return new StreamSource(new File(Objects.requireNonNull(XSLTTransformer.class.getResource(path)).getPath()));
    }

    private StreamResult createStreamResult(String path) {
        return new StreamResult(new File(Objects.requireNonNull(XSLTTransformer.class.getResource(path)).getPath()));
    }
}
