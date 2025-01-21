// Function to open the popup
function openPopup() {
    var popup = document.getElementById('addWidget');
    popup.style.display = 'flex';
}

// Function to close the popup
function closePopup() {
    var id = document.getElementsByClassName('selected')[0].id;
    var grid = GridStack.init();

    switch (id) {
        case 'projectList':
            axios.get("http://localhost:8888/api/printtabletache", {
                headers: {
                    'Authorization': 'Bearer ' + keycloak.token
                }
            }).then(function (response) {
                var tabledata = response.data;

                grid.addWidget('<div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="4" data-gs-height="2">' +
                    '<div id="table_projet" class="grid-stack-item-content"></div></div></div>');

                var table = new Tabulator("#table_projet", {
                    data:tabledata, //assign data to table
                    columns:[
                        {title:"Titre", field:"titre"},
                        {title:"Description", field:"description"},
                        {title:"Date de création", field:"date_creation"},
                        {title:"Dernière modification", field:"date_modifier"},
                        {title:"Projet", field:"id_projet"},
                        {title:"Tâche parent", field:"id_tache_parent"},
                    ],
                });
            });
            break;

        case 'taskList':
            var lien = `http://localhost:8888/api/printtabletache`;
            axios.get(lien, {
                headers: {
                    'Authorization': 'Bearer ' + keycloak.token
                }
            }).then(function (response) {
                var tabledata = response.data;
                console.log(tabledata);
                grid.addWidget('<div class="grid-stack-item" data-gs-x="0" data-gs-y="0" data-gs-width="4" data-gs-height="2">' +
                    '<div id="table_tache" class="grid-stack-item-content"></div></div></div>', { w:5, h:2 });

                var table = new Tabulator("#table_tache", {
                    data: tabledata,
                    columns:[
                        {title:"id", field:"idTache"},
                        {title:"Titre", field:"titre"},
                        {title:"Description", field:"description"},
                        {title:"Projet", field:"idProjet"},
                        {title:"Propriétaire", field:"cipCreateur"},
                        {title:"Tâche parent", field:"idTacheParent"},
                    ],
                    responsiveLayout: true,
                    layout:"fitColumns",
                    rowClick: function (e, row) {

                        window.location.href = `singleTask.html?taskId=${row.getData().idTache}&taskTitle=${row.getData().title}`; // Redirection vers une URL avec l'ID de la ligne
                    }
                });

                table.on("rowClick", function(e, row){
                    console.log(row.getData());
                    window.location.href='singleTask.html?taskId='+row.getData().idTache+'&taskTitle='+row.getData().titre;
                });
            });

            break;
    }

    document.getElementsByClassName('selected')[0].classList.remove('selected');

    var popup = document.getElementById('addWidget');
    popup.style.display = 'none';
}

let selectedCell = null;

function toggleSelection(cell) {
    // Si une cellule est déjà sélectionnée, la désélectionner
    if (selectedCell) {
        selectedCell.classList.remove('selected');
    }
    // Sélectionner la nouvelle cellule
    cell.classList.add('selected');
    selectedCell = cell;
}