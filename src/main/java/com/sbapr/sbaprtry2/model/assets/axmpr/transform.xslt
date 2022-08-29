<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:oai="http://www.openarchives.org/OAI/2.0/"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:axmpr="https://api.museion.cz/schema/axmpr"
                xmlns:pubpred="https://api.museion.cz/schema/axmpr/PublikacePredmetu">
    <xsl:template match="/">
        <rdf:RDF>
            <xsl:for-each select="oai:OAI-PMH/oai:ListRecords/oai:record/oai:metadata/axmpr:axmpr/PublikacePredmetu">
                <rdf:Description>
                    <pubpred:id><xsl:value-of select="id"/></pubpred:id>
                    <pubpred:inventarniCislo><xsl:value-of select="inventarniCislo"/></pubpred:inventarniCislo>
                    <pubpred:nazev><xsl:value-of select="nazev"/></pubpred:nazev>
                    <pubpred:popis><xsl:value-of select="popis"/></pubpred:popis>
                    <pubpred:rozmer><xsl:value-of select="rozmer"/></pubpred:rozmer>
                    <pubpred:konvDatace><xsl:value-of select="konvDatace"/></pubpred:konvDatace>
                    <pubpred:konvMaterial><xsl:value-of select="konvMaterial"/></pubpred:konvMaterial>
                    <pubpred:poznamka><xsl:value-of select="poznamka"/></pubpred:poznamka>
                    <pubpred:autorskaPrava><xsl:value-of select="autorskaPrava"/></pubpred:autorskaPrava>
                    <pubpred:datumPublikace><xsl:value-of select="datumPublikace"/></pubpred:datumPublikace>
                    <pubpred:datumModifikace><xsl:value-of select="rozmer"/></pubpred:datumModifikace>
                    <pubpred:dataceNazvemPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://myera.org/'" />
                            <xsl:value-of select="Datace/dataceNazvemPublic/@id" />
                        </xsl:attribute>
                    </pubpred:dataceNazvemPublic>
                    <pubpred:typSbirkyPublic><xsl:value-of select="typSbirkyPublic/@label" /></pubpred:typSbirkyPublic>
                    <pubpred:materialPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://mymat.org/'" />
                            <xsl:value-of select="materialPublic/@id" />
                        </xsl:attribute>
                    </pubpred:materialPublic>
                    <pubpred:lokalitaPublic>
                        <xsl:attribute name="rdf:resource">
                            <xsl:value-of select="'http://myloc.org/'" />
                            <xsl:value-of select="lokalitaPublic/@id" />
                        </xsl:attribute>
                    </pubpred:lokalitaPublic>
                    <pubpred:typPredmetu><xsl:value-of select="typPredmetu"/></pubpred:typPredmetu>
                    <pubpred:sbirka><xsl:value-of select="sbirka/@label" /></pubpred:sbirka>
                    <pubpred:spravceSbirky><xsl:value-of select="spravceSbirky/@label" /></pubpred:spravceSbirky>
                    <pubpred:podsbirka><xsl:value-of select="podsbirka/@label" /></pubpred:podsbirka>
                    <pubpred:fond><xsl:value-of select="fond/@label" /></pubpred:fond>
                    <pubpred:skupina><xsl:value-of select="skupina/@label" /></pubpred:skupina>
                </rdf:Description>
            </xsl:for-each>
        </rdf:RDF>
    </xsl:template>
</xsl:stylesheet>

