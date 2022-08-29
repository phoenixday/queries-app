import React, {useState} from 'react';
import {FileUploadForm} from "./components/FileUploadForm";
import {DataGraph} from "./components/DataGraph";
import {SparqlEditor} from "./components/SparqlEditor";

function App() {
    const [loading, setLoading] = useState(false);
    const [uploaded, setUploaded] = useState(false)

    const onLoad = (loading: boolean, uploaded: boolean) => {
        setLoading(loading);
        setUploaded(uploaded);
    }

    return (
        <div className="container">
            <FileUploadForm onLoad={onLoad}/>
            {uploaded && <>
                    <DataGraph/>
                    <SparqlEditor/>
                </>}
            {loading && <div className="center-page">
                <span className="spinner primary"></span>
                <p>Loading...</p>
            </div>}
        </div>
    );
}

export default App;
