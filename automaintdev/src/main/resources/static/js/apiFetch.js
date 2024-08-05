document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('vinForm').addEventListener('submit', function(event) {
        event.preventDefault();
        fetchVinSpecs();
    });
});

function fetchVinSpecs() {
    const vin = document.getElementById('vin').value;
    fetch(`/api/cars/vin/${vin}`)
        .then(response => response.json())
        .then(data => {
            document.getElementById('vinSpecs').innerHTML = `
                <h2>Specifications for VIN: ${vin}</h2>
                <ul>
                    <li>Year: ${data.year || 'N/A'}</li>
                    <li>Make: ${data.make || 'N/A'}</li>
                    <li>Model: ${data.model || 'N/A'}</li>
                    <li>Trim: ${data.trim || 'N/A'}</li>
                </ul>
            `;
        })
        .catch(error => {
            console.error('Error fetching VIN specs:', error);
            document.getElementById('vinSpecs').innerHTML = '<p>Error fetching VIN specs. Please try again.</p>';
        });
}
