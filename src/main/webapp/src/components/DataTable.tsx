export const DataTable = () => {
    return (
        <table className="stripped">
            <caption>People</caption>
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Alias</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td data-label="Name">Chad</td>
                <td data-label="Surname">Wilberts</td>
                <td data-label="Alias">MrOne</td>
            </tr>
            <tr>
                <td data-label="Name">Adam</td>
                <td data-label="Surname">Smith</td>
                <td data-label="Alias">TheSmith</td>
            </tr>
            <tr>
                <td data-label="Name">Sophia</td>
                <td data-label="Surname">Canderson</td>
                <td data-label="Alias">Candee</td>
            </tr>
            </tbody>
        </table>
    );
}