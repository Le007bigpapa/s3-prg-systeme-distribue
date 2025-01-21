document.addEventListener('keycloakAuthenticated', function() {
    console.log(`usager.cip ->${usager.cip}`);
    // Envoyer les données des colonnes à l'API
    document.getElementById('submitEdit').onclick = function () {
        const editID = document.getElementById('editID').value;
        const editTitre = document.getElementById('editTitre').value;
        const editDescription = document.getElementById('editDescription').value;
        const editProprietaire = document.getElementById('editProprietaire').value;

        // Remplacer par l'URL de votre API
        const apiUrl = `http://localhost:8888/api/editProjet/${editID}/${editTitre}/${editDescription}/${usager.cip}/${editProprietaire}`;

        fetch(apiUrl, {
            method: 'GET',  // Assurez-vous que la méthode est GET
            headers: {
                'Content-Type': 'application/json'  // Vérifiez si ce header est nécessaire
            },
        })
            .then(response => {
                if (response.ok) {
                    return response.text();  // Lire la réponse en tant que texte brut
                } else {
                    throw new Error('La requête a échoué avec le code : ' + response.status);
                }
            })
            .then(text => {
                console.log('Succès :', text);
                alert('La modification a été un succès!');
                location.reload();
                // Réinitialiser le formulaire après l'envoi
                document.querySelector('#editID').value = '';
                document.querySelector('#editTitre').value = '';
                document.querySelector('#editDescription').value = '';
                document.querySelector('#editProprietaire').value = '';
            })
            .catch(error => {
                console.error('Erreur :', error);
                alert('Une erreur est survenue lors de l\'envoi de la modification du projet');
                alert(apiUrl);
            });
    }


    document.getElementById('submitNew').onclick = function () {
        const newTitre = document.getElementById('newTitre').value;
        const newDescription = document.getElementById('newDescription').value;

        // Remplacer par l'URL de votre API
        const apiUrl2 = `http://localhost:8888/api/newProjet/${usager.cip}/${newTitre}/${newDescription}`;


        fetch(apiUrl2, {
            method: 'GET',  // Assurez-vous que la méthode est GET
            headers: {
                'Content-Type': 'application/json'  // Vérifiez si ce header est nécessaire
            },
        })
            .then(response => {
                if (response.ok) {
                    return response.text();  // Lire la réponse en tant que texte brut
                } else {
                    throw new Error('La requête a échoué avec le code : ' + response.status);
                }
            })
            .then(text => {
                console.log('Succès :', text);
                alert("L'ajout du nouveau projet a été un succès!");
                // Réinitialiser le formulaire après l'envoi
                location.reload();
                document.querySelector('#newTitre').value = '';
                document.querySelector('#newDescription').value = '';
            })
            .catch(error => {
                console.error('Erreur :', error);
                alert('Une erreur est survenue lors de la création du projet');
                alert(apiUrl2);
            });
    }

    document.getElementById('rightSubmit').onclick = function () {
        const deleteProjetID = document.getElementById('deleteProjetID').value;

        // Remplacer par l'URL de votre API
        const apiUrl3 = `http://localhost:8888/api/deleteProjet/${deleteProjetID}`;

        fetch(apiUrl3, {
            method: 'GET',  // Assurez-vous que la méthode est GET
            headers: {
                'Content-Type': 'application/json'  // Vérifiez si ce header est nécessaire
            },
        })
            .then(response => {
                if (response.ok) {
                    return response.text();  // Lire la réponse en tant que texte brut
                } else {
                    throw new Error('La requête a échoué avec le code : ' + response.status);
                }
            })
            .then(text => {
                console.log('Succès :', text);
                alert("La supression projet a été un succès!");
                // Réinitialiser le formulaire après l'envoi
                location.reload();
                document.querySelector('#deleteProjetID').value = '';
            })
            .catch(error => {
                console.error('Erreur :', error);
                alert('Une erreur est survenue lors de la supression du projet');
                alert(apiUrl3);
            });
    }
})
