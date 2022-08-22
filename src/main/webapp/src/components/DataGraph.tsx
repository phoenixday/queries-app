import Graph from "graphology";
import {
    ControlsContainer,
    FullScreenControl,
    SearchControl,
    SigmaContainer,
    useLoadGraph,
    ZoomControl
} from "@react-sigma/core";
import "@react-sigma/core/lib/react-sigma.min.css";
import {LayoutForceAtlas2Control} from "@react-sigma/layout-forceatlas2";
import {BsArrowsFullscreen, BsFullscreenExit, BsZoomIn, BsZoomOut} from "react-icons/bs";
import {BiRadioCircleMarked} from "react-icons/bi";
import {useEffect} from "react";

export const LoadGraph = () => {
    const loadGraph = useLoadGraph();

    useEffect(() => {
        const RED = "#FA4F40";
        const BLUE = "#727EE0";
        const GREEN = "#5DB346";
        const graph = new Graph();
        graph.addNode("John", {size: 15, label: "John", color: RED});
        graph.addNode("Mary", {size: 15, label: "Mary", color: RED});
        graph.addNode("Suzan", {size: 15, label: "Suzan", color: RED});
        graph.addNode("Nantes", {size: 15, label: "Nantes", color: BLUE});
        graph.addNode("New-York", {size: 15, label: "New-York", color: BLUE});
        graph.addNode("Sushis", {size: 7, label: "Sushis", color: GREEN});
        graph.addNode("Falafels", {size: 7, label: "Falafels", color: GREEN});
        graph.addNode("Kouign Amann", {size: 7, label: "Kouign Amann", color: GREEN});

        graph.addEdge("John", "Mary", {type: "arrow", label: "works with", size: 5});
        graph.addEdge("Mary", "Suzan", {type: "arrow", label: "works with", size: 5});
        graph.addEdge("Mary", "Nantes", {type: "arrow", label: "lives in", size: 5});
        graph.addEdge("John", "New-York", {type: "arrow", label: "lives in", size: 5});
        graph.addEdge("Suzan", "New-York", {type: "arrow", label: "lives in", size: 5});
        graph.addEdge("John", "Falafels", {type: "arrow", label: "eats", size: 5});
        graph.addEdge("Mary", "Sushis", {type: "arrow", label: "eats", size: 5});
        graph.addEdge("Suzan", "Kouign Amann", {type: "arrow", label: "eats", size: 5});

        graph.nodes().forEach((node, i) => {
            const angle = (i * 2 * Math.PI) / graph.order;
            graph.setNodeAttribute(node, "x", 100 * Math.cos(angle));
            graph.setNodeAttribute(node, "y", 100 * Math.sin(angle));
        });

        loadGraph(graph);
    }, [loadGraph]);

    return null;
};

export const DataGraph = () => {
    return (<SigmaContainer style={{height: "500px", width: "1000px"}}
                            initialSettings={{
                                renderEdgeLabels: true, defaultEdgeColor: "#f6f1f1", labelFont: "Lato, sans-serif",
                            }}>
        <LoadGraph/>
        <ControlsContainer position={"top-right"} style={{visibility: "hidden"}}>
            <LayoutForceAtlas2Control autoRunFor={2000}/>
        </ControlsContainer>
        <ControlsContainer position={"top-left"}>
            <FullScreenControl className="ico">
                <BsArrowsFullscreen/>
                <BsFullscreenExit/>
            </FullScreenControl>
            <ZoomControl className="ico">
                <BsZoomIn/>
                <BsZoomOut/>
                <BiRadioCircleMarked/>
            </ZoomControl>
        </ControlsContainer>
        <ControlsContainer position={"bottom-left"}>
            <SearchControl/>
        </ControlsContainer>
    </SigmaContainer>);
};