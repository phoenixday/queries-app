package com.sbapr.sbaprtry2.model.transformers;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.Map;

//TODO: add reasoning
public class XSLTTransformer implements Transformer {

    Map<String, String> xsltMap = Map.of(
            "ESE", "src/main/java/com/sbapr/sbaprtry2/model/assets/ese/transform.xslt",
            "AXMPR", "src/main/java/com/sbapr/sbaprtry2/model/assets/axmpr/transform.xslt");

    String format;
    String inputXML;
    String outputRDF;

    public XSLTTransformer(String format, String inputXML, String outputRDF) {
        this.format = format.toUpperCase();
        this.inputXML = inputXML;
        this.outputRDF = outputRDF;
    }

    /**
     * A method to convert XML to RDF/XML. Uses XSLT.
     */
    @Override
    public void transformXMLToRDF() {
        javax.xml.transform.TransformerFactory factory = javax.xml.transform.TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(xsltMap.get(format)));
        javax.xml.transform.Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }

        Source text = new StreamSource(new File(inputXML));
        try {
            transformer.transform(text, new StreamResult(new File(outputRDF)));
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
