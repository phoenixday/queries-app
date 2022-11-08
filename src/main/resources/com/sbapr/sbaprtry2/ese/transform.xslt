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
                    <dcterms:title><xsl:value-of select="dc:title"/></dcterms:title>
                    <dcterms:description><xsl:value-of select="dc:description"/></dcterms:description>
                    <!-- пространственное или временное покрытие -->
                    <dcterms:coverage><xsl:value-of select="dc:coverage"/></dcterms:coverage>
                    <!-- пространственное покрытие -->
                    <dcterms:spatial><xsl:value-of select="dcterms:spatial"/></dcterms:spatial>
                    <!-- A related resource (sbírky) -->
                    <dcterms:relation><xsl:value-of select="dc:relation"/></dcterms:relation>
                    <!-- An unambiguous reference to the resource within a given context. -->
                    <dcterms:identifier><xsl:value-of select="dc:identifier"/></dcterms:identifier>
                    <xsl:for-each select="dc:date">
                        <dcterms:date><xsl:value-of select="./text()"/></dcterms:date>
                    </xsl:for-each>
                    <!-- временное покрытие -->
                    <dcterms:temporal><xsl:value-of select="./dcterms:temporal"/></dcterms:temporal>
                    <dcterms:created><xsl:value-of select="./dcterms:created"/></dcterms:created>
                    <dcterms:rights><xsl:value-of select="./dc:rights"/></dcterms:rights>
                    <!-- The size or duration of the resource. (обычно размер) -->
                    <dcterms:extent><xsl:value-of select="./dcterms:extent"/></dcterms:extent>
                    <!-- The material or physical carrier(носитель) of the resource. -->
                    <xsl:for-each select="./dcterms:medium">
                        <dcterms:medium><xsl:value-of select="./text()"/></dcterms:medium>
                    </xsl:for-each>
<!--                    &lt;!&ndash; на интернете (eSbírky) &ndash;&gt;-->
<!--                    <europeana:provider><xsl:value-of select="europeana:provider"/></europeana:provider>-->
<!--                    &lt;!&ndash; в реальности (музей) &ndash;&gt;-->
<!--                    <europeana:dataProvider><xsl:value-of select="europeana:dataProvider"/></europeana:dataProvider>-->
<!--                    <europeana:isShownBy><xsl:value-of select="europeana:isShownBy"/></europeana:isShownBy>-->
<!--                    <europeana:isShownAt><xsl:value-of select="europeana:isShownAt"/></europeana:isShownAt>-->
<!--                    <europeana:rights><xsl:value-of select="europeana:rights"/></europeana:rights>-->
<!--                    <europeana:type><xsl:value-of select="europeana:type"/></europeana:type>-->
                </rdf:Description>
            </xsl:for-each>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>

