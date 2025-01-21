Site web de gestion du temps
===================

### Description de l'application

L’application, un gestionnaire de temps spécialement conçu avec les intérêts d'un étudiant en tête, a pour objectif de faciliter la gestion efficace des périodes de travail sur différentes tâches et divers projets. En permettant de suivre et d'organiser de manière détaillée les sessions de travail, cette application offre une solution adaptée aux besoins des étudiants qui jonglent souvent avec des échéances multiples et des charges de travail variées. Elle permet aux utilisateurs de structurer leur temps de manière optimale, de faciliter la gestion des heures travaillées et d’améliorer la précision des statistiques d’équipe. Bien que cette application soit axée sur les besoins des étudiants, elle peut également être bénéfique pour toute personne souhaitant une gestion précise et efficace de son temps de travail. À noter que le produit final est bien à l’image de l’idée initiale, et en dehors de l’aspect visuel de l’application, l’équipe n’a pas jugé nécessaire de modifier les différentes fonctionnalités offertes par le gestionnaire de temps.

### Architecture du systèmes
L’application est distribuée en quatre emplacements : une interface web dans le navigateur client, un serveur contenant l’API, et deux conteneurs Docker pour la base de données et l’authentification.


Le logiciel est organisé en couches, l’interface communique avec le serveur, qui communique avec la base de données, et l’interface et le serveur communiquent avec le serveur d’authentification.
Les principaux processus de traitement au niveau de la base de données sont :
-	Vérification des autorisations
-	Cohérence des sous-tâches et des projets
-	Sauvegarde des projets, tâches et sessions
Les principaux processus de traitement au niveau du serveur d’application sont :
-	Authentification avec Keycloak
-	Structuration des requêtes vers la base de données
-	CRUD pour les projets et les tâches
-	Démarrage et fermeture d’une session de travail
Les principaux processus de traitement au niveau des interfaces usagers sont :
-	Présentation des données


### Comment l'exécuter
- Tester un service dans un browser : localhost

Utiliser l'application
- Tester le serveur web dans un browser : localhost/usager
