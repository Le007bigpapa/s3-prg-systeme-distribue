function requestStudent() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/student", {
        headers: {
            'Authorization': 'Bearer ' + window.keycloak.token
        }
    })
    .then(function (response) {
        console.log("Response: ", response.status);
        span.innerHTML = '<br> <strong>' + response.data.cip + '</strong> </br>' +
            '<br> <strong>' + response.data.last_name + '</strong> </br>' +
            '<br> <strong>' + response.data.first_name + '</strong> </br>' +
            '<br> <strong>' + response.data.email + '</strong> </br>' +
            '<br> <strong>' + response.data.roles + '</strong> </br>';
    })
    .catch(function (error) {
        console.log('refreshing');
        keycloak.updateToken(5).then(function () {
            console.log('Token refreshed');
        }).catch(function () {
            console.log('Failed to refresh token');
            // Affichez le message seulement si le rafraîchissement du token échoue
            span.innerHTML = '<br> <strong>' + "Vous n'avez pas le role d'étudiant" + '</strong> </br>';
        });
    });
}