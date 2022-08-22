import React, {FormEvent, useCallback, useState} from "react";
import {StreamLanguage} from "@codemirror/language";
import {sparql} from "@codemirror/legacy-modes/mode/sparql";
import CodeMirror from '@uiw/react-codemirror';
import {DataTable} from "./DataTable";

export const SparqlEditor = () => {
    const [query, setQuery] = useState<string>("hello\nworld");

    const onChange = useCallback((value: string, viewUpdate: any) => {
        setQuery(value);
    }, []);

    function handleSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();
        fetch(`api/query/${query}`,)
            .then((response) => {
                console.log(`Success... ${response.status} ... ${response.statusText}`);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <CodeMirror
                    value={query}
                    height="200px"
                    theme='dark'
                    extensions={[StreamLanguage.define(sparql)]}
                    onChange={onChange}/>
                <button type="submit">Ask query</button>
            </form>
            <DataTable/>
        </>
    );
}