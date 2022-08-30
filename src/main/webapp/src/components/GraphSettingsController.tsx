import {useSigma} from "@react-sigma/core";
import {useEffect} from "react";
import {EdgeDisplayData, NodeDisplayData} from "sigma/types";

interface GraphSettingsControllerProps {
    hoveredNode: string | null,
    hoveredNeighbours: Set<string> | null
}

export const GraphSettingsController = ({hoveredNode, hoveredNeighbours}: GraphSettingsControllerProps) => {
    const sigma = useSigma();
    const graph = sigma.getGraph();

    useEffect(() => {
        sigma.setSetting(
            "nodeReducer",
            (node, data) => {
                const res: Partial<NodeDisplayData> = {...data};
                if (hoveredNeighbours) {
                    if (hoveredNeighbours.has(node) && hoveredNode !== node) {
                        res.label = "";
                    } else if (hoveredNode !== node){
                        res.label = "";
                        res.color = "#dad7d7";
                    }
                }
                return res;
            }
        );
        sigma.setSetting(
            "edgeReducer",
            (edge, data) => {
                const res: Partial<EdgeDisplayData> = {...data};
                if (hoveredNode && !graph.hasExtremity(edge, hoveredNode)) {
                    res.hidden = true;
                }
                return res;
            }
        );
    }, [hoveredNode, hoveredNeighbours, graph, sigma]);

    return null;
};
