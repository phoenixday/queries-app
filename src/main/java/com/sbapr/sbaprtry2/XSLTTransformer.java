package com.sbapr.sbaprtry2;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Objects;

public class XSLTTransformer{

    String format;

    public XSLTTransformer(String format) {
        this.format = format.toUpperCase();
    }

    public void transformXMLToRDF() {
        TransformerFactory factory = TransformerFactory.newInstance();
        String path = format.equalsIgnoreCase("ese") ? "ese/transform.xslt" : "axmpr/transform.xslt";
        Source xslt = new StreamSource(new File(Objects.requireNonNull(XSLTTransformer.class.getResource(path)).getPath()));
        Transformer transformer;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        Source text = new StreamSource(new File(Objects.requireNonNull(XSLTTransformer.class.getResource("input.xml")).getPath()));
        try {
            transformer.transform(text, new StreamResult(new File(Objects.requireNonNull(XSLTTransformer.class.getResource("output.rdf")).getPath())));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
