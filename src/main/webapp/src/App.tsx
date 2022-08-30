import React, {useState} from 'react';
import {FileUploadForm} from "./components/FileUploadForm";
import {DataGraph} from "./components/DataGraph";
import {SparqlEditor} from "./components/SparqlEditor";

function App() {
    const [loading, setLoading] = useState<boolean | undefined>();
    const [format, setFormat] = useState("ese");

    const onLoad = (loading: boolean, format: string) => {
        setLoading(loading);
        setFormat(format);
    }

    return (
        <div className="container">
            <FileUploadForm onLoad={onLoad}/>
            {!loading && loading !== undefined && <>
                <DataGraph format={format}/>
                <SparqlEditor format={format}/>
            </>}
            {loading && <div className="center-page">
                <span className="spinner primary"></span>
                <p>Loading...</p>
            </div>}
        </div>
    );
}

export default App;
