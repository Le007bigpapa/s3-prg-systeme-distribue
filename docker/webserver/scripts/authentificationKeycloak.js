usager = new Usager();

keycloak = new Keycloak({
        "realm": "usager",
        "auth-server-url": "http://localhost:8180/",
        "ssl-required": "external",
        "clientId": "frontend",
        "public-client": true,
        "confidential-port": 0
    });

keycloak.init({onLoad: 'login-required'}).then(function (authenticated) {
    console.log("Authenticated:", authenticated);
    if(authenticated){
        // console.log("TokenParsed:", keycloak.tokenParsed);
        window.keycloakToken = keycloak.token;
        usager.setCip(keycloak.tokenParsed.preferred_username);
        // console.log("Preferred Username:", usager.cip);
    }
    document.dispatchEvent(new CustomEvent('keycloakAuthenticated'));
}).catch(function () {
    alert('failed to initialize');
});