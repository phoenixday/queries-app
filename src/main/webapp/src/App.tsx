import React from 'react';
import {FileUploadForm} from "./components/FileUploadForm";
import {DataGraph} from "./components/DataGraph";
import {SparqlEditor} from "./components/SparqlEditor";

function App() {
    return (
        <div className="container">
            <FileUploadForm/>
            <DataGraph/>
            <SparqlEditor/>
        </div>
    );
}

export default App;
