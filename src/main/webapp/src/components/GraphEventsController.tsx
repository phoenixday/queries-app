import {useRegisterEvents, useSigma} from "@react-sigma/core";
import React, {useEffect} from "react";

interface GraphEventsControllerProps {
    setHoveredNode: (node: string | null) => void,
    setHoveredNeighbours: (neighbours: Set<string> | null) => void;
}

export const GraphEventsController = ({setHoveredNode, setHoveredNeighbours}: GraphEventsControllerProps) => {
    const registerEvents = useRegisterEvents();
    const sigma = useSigma();
    const graph = sigma.getGraph();

    useEffect(() => {
        registerEvents({
            enterNode({node}) {
                setHoveredNode(node);
                setHoveredNeighbours(new Set(graph.neighbors(node)));
            },
            leaveNode() {
                setHoveredNode(null);
                setHoveredNeighbours(null);
            },
        });
    }, []);

    return null;
};
