<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:oai="http://www.openarchives.org/OAI/2.0/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:dc="http://purl.org/dc/elements/1.1/"
                xmlns:dcterms="http://purl.org/dc/terms/"
                xmlns:europeana="http://www.europeana.eu/schemas/ese/">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:for-each select="oai:OAI-PMH/oai:ListRecords/oai:record/oai:metadata/europeana:record">
                <rdf:Description>
                    <dc:title><xsl:value-of select="dc:title"/></dc:title>
                    <dc:description><xsl:value-of select="dc:description"/></dc:description>
                    <!-- пространственное или временное покрытие -->
                    <dc:coverage><xsl:value-of select="dc:coverage"/></dc:coverage>
                    <!-- пространственное покрытие -->
                    <dcterms:spatial><xsl:value-of select="dcterms:spatial"/></dcterms:spatial>
                    <!-- A related resource (sbírky) -->
                    <dc:relation><xsl:value-of select="dc:relation"/></dc:relation>
                    <!-- An unambiguous reference to the resource within a given context. -->
                    <dc:identifier><xsl:value-of select="dc:identifier"/></dc:identifier>
                    <xsl:for-each select="dc:date">
                        <dc:date><xsl:value-of select="./text()"/></dc:date>
                    </xsl:for-each>
                    <!-- временное покрытие -->
                    <dcterms:temporal><xsl:value-of select="./dcterms:temporal"/></dcterms:temporal>
                    <dcterms:created><xsl:value-of select="./dcterms:created"/></dcterms:created>
                    <dc:rights><xsl:value-of select="./dc:rights"/></dc:rights>
                    <!-- The size or duration of the resource. (обычно размер) -->
                    <dcterms:extent><xsl:value-of select="./dcterms:extent"/></dcterms:extent>
                    <!-- The material or physical carrier(носитель) of the resource. -->
                    <xsl:for-each select="./dcterms:medium">
                        <dcterms:medium><xsl:value-of select="./text()"/></dcterms:medium>
                    </xsl:for-each>
                    <!-- на интернете (eSbírky) -->
                    <europeana:provider><xsl:value-of select="europeana:provider"/></europeana:provider>
                    <!-- в реальности (музей) -->
                    <europeana:dataProvider><xsl:value-of select="europeana:dataProvider"/></europeana:dataProvider>
                    <europeana:isShownBy><xsl:value-of select="europeana:isShownBy"/></europeana:isShownBy>
                    <europeana:isShownAt><xsl:value-of select="europeana:isShownAt"/></europeana:isShownAt>
                    <europeana:rights><xsl:value-of select="europeana:rights"/></europeana:rights>
                    <europeana:type><xsl:value-of select="europeana:type"/></europeana:type>
                </rdf:Description>
            </xsl:for-each>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>

