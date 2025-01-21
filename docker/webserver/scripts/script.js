document.addEventListener("DOMContentLoaded", function () {
    function loadContent(url, elementId) {
        fetch(url)
            .then(response => response.text())
            .then(data => {
                document.getElementById(elementId).innerHTML = data;
            })
            .catch(error => {
                console.error('Error loading content from ' + url + ':', error);
            });
    }

    // Déterminer le chemin de la page actuelle
    const currentPath = window.location.pathname;

    loadContent('ressource/header.html', 'headerContent');
    loadContent('ressource/footer.html', 'footerContent');

    if (currentPath.includes('index')) {
        loadContent('ressource/formulaire/addWidget.html', 'addWidgetContent');
    }
});