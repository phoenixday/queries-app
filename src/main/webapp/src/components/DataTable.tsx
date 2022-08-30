import {Binding, QueryResults} from "../interfaces";
import {useEffect, useState} from "react";

const BINDINGS_PER_PAGE = 5

interface DataTableProps {
    data: QueryResults
}

export const DataTable = ({data}: DataTableProps) => {
    const {head, results} = data;
    const [currentPage, setCurrentPage] = useState(1);
    const [bindings, setBindings] = useState<Binding[]>([])

    const handlePreviousPage = () => {
        setCurrentPage((page) => page - 1);
    }

    const handleNextPage = () => {
        setCurrentPage((page) => page + 1);
    }

    useEffect(() => setCurrentPage(1), [data]);

    useEffect(() => {
        if (currentPage === 1) {
            setBindings(results.bindings.slice(0, BINDINGS_PER_PAGE));
        } else {
            setBindings((bindings) =>
                results.bindings.slice(BINDINGS_PER_PAGE * (currentPage - 1),
                    BINDINGS_PER_PAGE * currentPage));
        }
    }, [currentPage, results.bindings]);

    return (
        <>
            <div className="row">
                <div className="col-sm-12">
                    { bindings.length > 0 ?
                    <table className="striped" style={{height: "100%"}}>
                        {/*<caption>Output</caption>*/}
                        <thead>
                        <tr>
                            {head.vars.map((varName) => <th key={varName}>{varName}</th>)}
                        </tr>
                        </thead>
                        <tbody>
                        {bindings.map((binding, index) => (
                            <tr key={index}>
                                {Object.entries(binding).map(
                                    ([varName, {type, value}]) => (
                                        <td data-label={varName} key={index + "_" + varName}>{value}</td>
                                    ))}
                            </tr>
                        ))}
                        </tbody>
                    </table>
                        : <pre>No data found.</pre>}
                </div>
            </div>
            <div className="row">
                <div className="col-sm-12">
                    {currentPage !== 1 && <div className="button default" onClick={handlePreviousPage}>Previous page</div>}
                    {BINDINGS_PER_PAGE * currentPage < results.bindings.length &&
                        <div className="button default" onClick={handleNextPage}>Next page</div>}
                </div>
            </div>
        </>
    );
}