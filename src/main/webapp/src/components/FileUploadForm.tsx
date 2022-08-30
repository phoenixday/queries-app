import {ChangeEvent, FormEvent, useEffect, useState} from "react";

interface FileUploadFormProps {
    onLoad: (loading: boolean, format: string) => void;
}

export const FileUploadForm = ({onLoad}: FileUploadFormProps) => {
    const [format, setFormat] = useState("ese");
    const [file, setFile] = useState<File | undefined>();
    const [error, setError] = useState<string | null>(null);

    function handleSubmit(e: FormEvent<HTMLFormElement>) {
        e.preventDefault();
        onLoad(true, format);
        if (file === undefined || error) return;
        const formData = new FormData();
        formData.append('file', file);
        fetch(
            'api/upload',
            {
                method: 'POST',
                body: formData,
            }
        )
            .then((response) => {
                console.log(`Success... ${response.status} ... ${response.statusText}`);
                onLoad(false, format);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }

    function handleChange(event: ChangeEvent<HTMLInputElement>) {
        if (event.target.files) setFile(event.target.files[0]);
    }

    useEffect(() => {
        setError(null);
        if (file === undefined)
            setError("Please upload a valid XML file.")
        else if (file.type !== "text/xml")
            setError("It's not a valid extension, you need to upload an XML file.")
    }, [file]);

    return (
        <div className="row">
            <div className="col-sm-12">
                <form onSubmit={handleSubmit}>
                    <label htmlFor="format">Select a format:</label>
                    <select
                        name="format"
                        id="format"
                        value={format}
                        onChange={(e) => setFormat(e.target.value)}>
                        <option value="ese">ESE</option>
                        <option value="axmpr">AXMPR</option>
                    </select>

                    <label htmlFor="file" className="button">Upload file</label>
                    <input
                        type="file"
                        id="file"
                        name="file"
                        style={{display: "none"}}
                        accept=".xml"
                        onChange={handleChange}/>
                    <br/>

                    {file && <p>Filename: {file.name}</p>}
                    {error && <p>{error}</p>}

                    <button type="submit">Submit</button>
                </form>
            </div>
        </div>
    );
}