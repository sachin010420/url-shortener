async function shortenUrl() {
    const url = document.getElementById('urlInput').value;
    if (!url) {
        alert("Enter a URL!");
        return;
    }

    try {
        const response = await fetch(`/api/shorten?url=${encodeURIComponent(url)}`, {
            method: 'POST'
        });

        if (!response.ok) {
            const errorData = await response.json();
            document.getElementById('result').innerText = `Error: ${errorData.error}`;
            return;
        }

        const shortCode = await response.text();
        const shortUrl = `${window.location.origin}/${shortCode}`;
        document.getElementById('result').innerHTML = `Short URL: <a href="${shortUrl}" target="_blank">${shortUrl}</a>`;

    } catch (err) {
        console.error(err);
        document.getElementById('result').innerText = 'Something went wrong!';
    }
}
