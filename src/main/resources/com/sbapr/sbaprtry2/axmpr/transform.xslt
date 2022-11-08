<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:oai="http://www.openarchives.org/OAI/2.0/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:axmpr="https://api.museion.cz/schema/axmpr"
                xmlns:example="https://api.museion.cz/schema/axmpr/PublikacePredmetu">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:for-each select="oai:OAI-PMH/oai:ListRecords/oai:record/oai:metadata/axmpr:axmpr/PublikacePredmetu">
                <rdf:Description>
                    <example:id><xsl:value-of select="id"/></example:id>
                    <example:inventarniCislo><xsl:value-of select="inventarniCislo"/></example:inventarniCislo>
                    <example:nazev><xsl:value-of select="nazev"/></example:nazev>
                    <example:popis><xsl:value-of select="popis"/></example:popis>
                    <example:rozmer><xsl:value-of select="rozmer"/></example:rozmer>
                    <example:konvDatace><xsl:value-of select="konvDatace"/></example:konvDatace>
                    <example:konvMaterial><xsl:value-of select="konvMaterial"/></example:konvMaterial>
                    <example:poznamka><xsl:value-of select="poznamka"/></example:poznamka>
                    <example:autorskaPrava><xsl:value-of select="autorskaPrava"/></example:autorskaPrava>
                    <example:datumPublikace><xsl:value-of select="datumPublikace"/></example:datumPublikace>
                    <example:datumModifikace><xsl:value-of select="rozmer"/></example:datumModifikace>
                    <example:dataceNazvemPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://myera.org/'" />
                            <xsl:value-of select="Datace/dataceNazvemPublic/@id" />
                        </xsl:attribute>
                    </example:dataceNazvemPublic>
                    <example:typSbirkyPublic><xsl:value-of select="typSbirkyPublic/@label" /></example:typSbirkyPublic>
                    <example:materialPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://mymat.org/'" />
                            <xsl:value-of select="materialPublic/@id" />
                        </xsl:attribute>
                    </example:materialPublic>
                    <example:lokalitaPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://myloc.org/'" />
                            <xsl:value-of select="lokalitaPublic/@id" />
                        </xsl:attribute>
                    </example:lokalitaPublic>
                    <example:typPredmetu><xsl:value-of select="typPredmetu"/></example:typPredmetu>
                    <example:sbirka><xsl:value-of select="sbirka/@label" /></example:sbirka>
                    <example:spravceSbirky><xsl:value-of select="spravceSbirky/@label" /></example:spravceSbirky>
                    <example:podsbirka><xsl:value-of select="podsbirka/@label" /></example:podsbirka>
                    <example:fond><xsl:value-of select="fond/@label" /></example:fond>
                    <example:skupina><xsl:value-of select="skupina/@label" /></example:skupina>
                </rdf:Description>
            </xsl:for-each>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>

