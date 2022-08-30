import {ControlsContainer, FullScreenControl, SearchControl, SigmaContainer, ZoomControl} from "@react-sigma/core";
import "@react-sigma/core/lib/react-sigma.min.css";
import {LayoutForceAtlas2Control} from "@react-sigma/layout-forceatlas2";
import {BsArrowsFullscreen, BsFullscreenExit, BsZoomIn, BsZoomOut} from "react-icons/bs";
import {BiRadioCircleMarked} from "react-icons/bi";
import {useEffect, useState} from "react";
import {QueryResults} from "../interfaces";
import {LoadGraph} from "./LoadGraph";
import {MultiDirectedGraph} from "graphology";
import {GraphEventsController} from "./GraphEventsController";
import {GraphSettingsController} from "./GraphSettingsController";

interface DataGraphProps {
    format: string
}

export const DataGraph = ({format}: DataGraphProps) => {
    const [hoveredNode, setHoveredNode] = useState<string | null>(null);
    const [hoveredNeighbours, setHoveredNeighbours] = useState<Set<string> | null>(null);
    const [data, setData] = useState<QueryResults | undefined>();
    const query =
        "SELECT * WHERE {\n" +
        "\t?subject ?predicate ?object\n" +
        "}";

    useEffect(() => {
        const formData = new FormData();
        formData.append('format', format);
        formData.append('query', query);
        fetch(`api/query`, {method: 'POST', body: formData})
            .then((response) => response.json())
            .then((data) => {
                setData(data);
            })
            .catch((error) => {
                alert(`Error: ${error}`);
                console.error('Error:', error);
            });
    }, [query, format])

    return (<div className="center-page">
        <SigmaContainer style={{border: "1px solid #ddd", height: "500px", width: "1000px"}}
                        graph={MultiDirectedGraph}
                        initialSettings={{
                            renderEdgeLabels: true,
                            labelDensity: 0.07,
                            labelGridCellSize: 60,
                            labelFont: "Lato, sans-serif",
                            zIndex: true,
                            hideEdgesOnMove: true,
                            hideLabelsOnMove: true,
                        }}>
            {data && <>
                <LoadGraph data={data}/>
                <GraphSettingsController hoveredNode={hoveredNode} hoveredNeighbours={hoveredNeighbours}/>
                <GraphEventsController setHoveredNode={setHoveredNode} setHoveredNeighbours={setHoveredNeighbours}/>
                <ControlsContainer position={"top-right"}
                    // style={{visibility: "hidden"}}
                >
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
            </>}
        </SigmaContainer>
    </div>);
};