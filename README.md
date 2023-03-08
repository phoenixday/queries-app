# Museum ontology

An application for creating a simple ontology over museum objects. One can upload an XML with objects data. The app will create a triplestore, transform data to <a href="https://en.wikipedia.org/wiki/Resource_Description_Framework">RDF</a> format and display the graph of objects, their properties and relations between them. The user then can ask the SPARQL queries over the triplestore. 

This is a proof-of-concept for <a href="https://www.axiell.com/cz/reseni/produkty/museion/">Museion</a> company. Its functionality and algorithm should be enhanced in many ways.

The app is written on Java, using <a href="https://jena.apache.org/">Apache Jena</a> for triplestore. The frontend is written in React; graphs are visualised with <a href="https://www.sigmajs.org/">Sigma.js</a> library; SPARQL queries are highlighted 

## Local deployment

### Run the backend REST API

Open terminal in the folder queriesapp and run
`mvn clean install -DskipTests && mvn spring-boot:run`

### Run the frontend react server 

Open another terminal and run: 
`cd src/main/webapp/ && npm install && npm start`

### Data for importing

... is in the folder data-for-importing, for AXMPR and ESE accordingly

### Example queries

#### ESE

`PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
SELECT distinct ?material WHERE {
?subject dcterms:medium ?material
}
ORDER BY ?material`

`PREFIX dcterms:` <<http://purl.org/dc/terms/>`
SELECT distinct ?duration WHERE {
?subject dcterms:temporal ?duration}
ORDER BY ?duration`

`PREFIX europeana:` <<http://www.europeana.eu/schemas/ese/>>`
PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
SELECT ?title ?material WHERE {
?subject europeana:dataProvider "Národní muzeum - Historické muzeum" .
?subject dc:title ?title .
?subject dcterms:medium ?material
}`

`PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
SELECT ?title ?predicate ?object WHERE {
?subject ?predicate ?object .
?subject dcterms:coverage ?object .
?subject dc:title ?title
}`

#### AXMPR

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX :` <<http://myloc.org/>>`
SELECT ?title WHERE {
?continent dc:title "Evropa" .
?region dcterms:isPartOf* ?continent .
?region a :region .
?region dc:title ?title
}`

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX :` <<http://myloc.org/>>`
PREFIX example:` <<https://api.museion.cz/schema/axmpr/PublikacePredmetu>>`
SELECT ?nazev ?regiontitle WHERE {
?country dc:title "Česká republika" .
?region dcterms:isPartOf* ?country .
?predmet example:lokalitaPublic ?region .
?predmet example:nazev ?nazev .
?region dc:title ?regiontitle
}`

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX :` <<http://mymat.org/>>`
PREFIX example:` <<https://api.museion.cz/schema/axmpr/PublikacePredmetu>>`
SELECT ?nazev ?materialtitle WHERE {   
?group dc:title "Živočišný původ" .
?material dcterms:isPartOf* ?group .
?predmet example:materialPublic ?material .
?predmet example:nazev ?nazev .
?material dc:title ?materialtitle
}`

