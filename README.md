## Local deployment

### Run the backend REST API

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

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX europeana:` <<http://www.europeana.eu/schemas/ese/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
SELECT ?title ?material WHERE {
?subject europeana:dataProvider "Národní muzeum - Historické muzeum" .
?subject dc:title ?title .
?subject dcterms:medium ?material
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
PREFIX pubpred:` <<https://api.museion.cz/schema/axmpr/PublikacePredmetu>>`
SELECT ?nazev ?regiontitle WHERE {
?country dc:title "Česká republika" .
?region dcterms:isPartOf* ?country .
?predmet pubpred:lokalitaPublic ?region .
?predmet pubpred:nazev ?nazev .
?region dc:title ?regiontitle
}`

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX :` <<http://mymat.org/>>`
PREFIX pubpred:` <<https://api.museion.cz/schema/axmpr/PublikacePredmetu>>`
SELECT ?nazev ?materialtitle WHERE {   
?group dc:title "Živočišný původ" .
?material dcterms:isPartOf* ?group .
?predmet pubpred:materialPublic ?material .
?predmet pubpred:nazev ?nazev .
?material dc:title ?materialtitle
}`

`PREFIX dc:` <<http://purl.org/dc/elements/1.1/>>`
PREFIX dcterms:` <<http://purl.org/dc/terms/>>`
PREFIX :` <<http://mymat.org/>>`
PREFIX pubpred:` <<https://api.museion.cz/schema/axmpr/PublikacePredmetu>>`
SELECT ?nazev ?materialtitle WHERE {   
?group dc:title "Nerost" .
?material dcterms:isPartOf* ?group .
?predmet pubpred:materialPublic ?material .
?predmet pubpred:nazev ?nazev .
?material dc:title ?materialtitle
}`