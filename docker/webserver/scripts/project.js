document.addEventListener('DOMContentLoaded', function() {
    // Données de test
    document.addEventListener('keycloakAuthenticated', function() {
        let apiUrl = 'http://localhost:8888/api/printListProjet/';

        apiUrl= apiUrl+usager.cip;


        // Initialiser Tabulator
        var table = new Tabulator('#data-table', {
            ajaxURL: apiUrl,
            ajaxConfig: 'GET',
            ajaxResponse: function (url, params, response) {
                return response;
            },
            columns: [
                {title: 'ID', field: 'idProjet', hozAlign: 'left'},
                {title: 'Titre', field: 'titre', hozAlign: 'left'},
                {title: 'Description', field: 'description', hozAlign: 'left'},
                {title: 'Date de Création', field: 'dateCreation', hozAlign: 'left'},
                {title: 'CIP Propriétaire', field: 'cipProprietaire', hozAlign: 'left'},
                {title: 'CIP Créateur', field: 'cipCreateur', hozAlign: 'left'}
            ],
            layout: 'fitColumns',
            pagination: 'local',
            paginationSize: 10,
            movableColumns: true,
            resizableColumns: true,
            initialSort: [{column: 'dateCreation', dir: 'desc'}]
        });
        // Définir l'événement rowClick
        table.on("rowClick", function(e, row) {
            // Ouvrir la nouvelle page
            window.location.href="tasks.html?idProjet="+ row.getData().idProjet; // "_blank" pour ouvrir dans un nouvel onglet
        });
    });
});
