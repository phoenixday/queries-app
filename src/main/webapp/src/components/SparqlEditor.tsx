import React, {FormEvent, useCallback, useState} from "react";
import {StreamLanguage} from "@codemirror/language";
import {sparql} from "@codemirror/legacy-modes/mode/sparql";
import CodeMirror from '@uiw/react-codemirror';
import {QueryResults} from "../interfaces";
import {DataTable} from "./DataTable";

interface SparqlEditorProps {
    format: string
}

export const SparqlEditor = ({format}: SparqlEditorProps) => {
    const [query, setQuery] = useState<string>(
        "SELECT * WHERE {\n" +
        "\t?subject ?predicate ?object\n" +
        "}");
    const [data, setData] = useState<QueryResults | undefined>();

    const onChange = useCallback((value: string, viewUpdate: any) => {
        setQuery(value);
    }, []);

    function handleSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();
        const formData = new FormData();
        formData.append('format', format);
        formData.append('query', query);
        fetch(
            `api/query`,
            {
                method: 'POST',
                body: formData
            }
        )
            .then((response) => response.json())
            .then((data) => {
                setData(data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    return (
        <>
            <div className="row">
                <div className="col-sm-12">
                    <form onSubmit={handleSubmit}>
                        <CodeMirror
                            value={query}
                            height="200px"
                            theme='dark'
                            extensions={[StreamLanguage.define(sparql)]}
                            onChange={onChange}/>
                        <button type="submit">Ask query</button>
                    </form>
                </div>
            </div>
            {data && <DataTable data={data}/>}
        </>
    );
}