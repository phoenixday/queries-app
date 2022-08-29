import {useLoadGraph} from "@react-sigma/core";
import {useEffect} from "react";
import Graph from "graphology";
import {QueryResults} from "../interfaces";

interface LoadGraphProps {
    data: QueryResults;
}

export const LoadGraph = ({data}: LoadGraphProps) => {
    const loadGraph = useLoadGraph();

    useEffect(() => {
        const graph = new Graph({type: "directed", multi: true});
        const {results} = data;

        results.bindings.forEach((binding) => {
            [binding['subject'], binding['object']].forEach((node) => {
                if (!graph.hasNode(node.value) && node.value !== "") {
                    let size: number, color;
                    if (node.type === "bnode") {size = 20; color = "#b21407"}
                    else if (node.type === "uri") {size = 13; color = "#965a5a";}
                    else {size = 7; color = "#4a814a";}
                    graph.addNode(node.value, {
                        size: size, color: color, label: node.value, x: Math.random(), y: Math.random()
                    });
                }
            })
        });

        results.bindings.forEach((binding, i) => {
            const subject = binding['subject'];
            const predicate = binding['predicate'];
            const object = binding['object'];

            if (subject.value !== "" && predicate.value !== "" && object.value !== "") {
                graph.addDirectedEdgeWithKey(
                    `${predicate.value}_${i}`,
                    subject.value,
                    object.value,
                    {
                        type: "arrow", label: predicate.value, size: 3, color: "#c4bfbf"
                    })
            }
        });

        // graph.nodes().forEach((node, i) => {
        //     const angle = (i * 2 * Math.PI) / graph.order;
        //     graph.setNodeAttribute(node, "x", 100 * Math.cos(angle));
        //     graph.setNodeAttribute(node, "y", 100 * Math.sin(angle));
        // });

        loadGraph(graph);
    }, [loadGraph, data]);

    return null;
};