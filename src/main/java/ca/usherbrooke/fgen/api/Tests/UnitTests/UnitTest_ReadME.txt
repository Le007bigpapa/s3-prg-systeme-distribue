


________CHOISIR TEST À EFFECTUER________
Pour choisir les tests à effectuer changer dans le fichier Tests.java les valeurs boolean:

    public static boolean __CLEAN__ = false;
    //quand __CLEAN__ = true -> efface les données ajoutées dans la base de données
    //Peut importe la valeur de __CLEAN__, UnitTests efface les données appartenant au cip "fora1234" dans la base de donnée

    public static boolean __SESSION__ = true;
    //quand __SESSION__ = true -> fait les tests unitaires associés à la table session

    public static boolean __TACHE__ = true;
    //quand __TACHE__ = true -> fait les tests unitaires associés à la table tache


________COMMENT LE CODE FONCTIONNE________

    __PARAMÈTRE__
    __fichier Scripts_unitTests.java__
    Un dictionnaire est créé avec l'entièreté des clés des colonnes de la base de donnée avec une valeur associée "NONE":

                base_verifyDatas.put("titre_fonction", "NONE");
                base_verifyDatas.put("titre", "NONE");
                base_verifyDatas.put("description", "NONE");
                base_verifyDatas.put("id_projet", "NONE");
                base_verifyDatas.put("cip_createur", "NONE");
                base_verifyDatas.put("id_tacheParent", "NONE");
                base_verifyDatas.put("date_debut", "NONE");
                base_verifyDatas.put("date_fin", "NONE");
                base_verifyDatas.put("id_session", "NONE");
                base_verifyDatas.put("commencer_a", "NONE");
                base_verifyDatas.put("fini_a", "NONE");
                base_verifyDatas.put("id_tache", "NONE");
                base_verifyDatas.put("cip_usager", "NONE");
                base_verifyDatas.put("response_statusCode", "NONE");
                base_verifyDatas.put("SQL_response", "NONE");

    Si un nouveau test utilise d'autres noms de colonne, elles doivent être ajoutées dans ce dictionnaire

    Un dictionnaire du test voulu est ensuite créé avec:
        -les valeurs la clé de chaque colonne ainsi que la valeur attendue, la valeur attendue sera cell
         passée dans la requête
        -le code retournée pas la requête Http
        -une donnée avec la clé "SQL_response" avec la valeur "null" ou "notnull" qui défini si la requête SQL
         devrait trouver quelque chose ou non
        -une donnée avec la clé "titre_fonction" et sa valeur sera le titre que vous voulez donner à votre test

         ex de dictionnaire:

                 Hashtable<String, String> s_verifyDatas1 = new Hashtable<>();
                 s_verifyDatas1.put("titre_fonction", "chrono");
                 s_verifyDatas1.put("id_session", "1"); #ici c'est 1+dernier id_session
                 s_verifyDatas1.put("commencer_a", "date");#"date" ou "null" si pas de date
                 s_verifyDatas1.put("fini_a", "null");#"date" ou "null" si pas de date
                 s_verifyDatas1.put("id_tache", "1");#ici c'est 1+dernier id_session
                 s_verifyDatas1.put("cip_usager", "test1234");
                 s_verifyDatas1.put("nom_test", "Normal_StartChrono");
                 s_verifyDatas1.put("response_statusCode", "200");
                 s_verifyDatas1.put("SQL_response", "notnull");

    Ce dictionnaire sera ensuite mis dans une liste

        s_verifyDatas.add(s_verifyDatas1);
        //s_verifyDatas est la liste des dicionnaires de tests pour Session
        //t_verifyDatas est la liste des dicionnaires de tests pour Tache

    __fichier UnitTests.java__
    __FONCTION DE TESTS__

    la liste de dictionnaire va être passée dans la fonction public void verifyTest(List<Hashtable<String, String>> vd)
    Cette fonction va utiliser les valeurs initiées dans le constructeur comme le dernier l'id_session ou l'id_tache
    des tables.

    Une boucle for va par la suite passer chaque dictionnaire dans les tests.

    Le test va d'abord remplacer les valeurs du dictionnaire dans une copie du dictionnaire de base; t_vd.
        ex de t_vd:

            base_verifyDatas.put("titre_fonction", "chrono");
            base_verifyDatas.put("titre", "Normal_StartChrono");
            base_verifyDatas.put("description", "NONE");
            base_verifyDatas.put("id_projet", "NONE");
            base_verifyDatas.put("cip_createur", "NONE");
            base_verifyDatas.put("id_tacheParent", "NONE");
            base_verifyDatas.put("date_debut", "date");
            base_verifyDatas.put("date_fin", "null");
            base_verifyDatas.put("id_session", "1");
            base_verifyDatas.put("commencer_a", "NONE");
            base_verifyDatas.put("fini_a", "NONE");
            base_verifyDatas.put("id_tache", "1");
            base_verifyDatas.put("cip_usager", "NONE");
            base_verifyDatas.put("response_statusCode", "200");
            base_verifyDatas.put("SQL_response", "notnull");

    Une requête http sera ensuite envoyé basé sur les paramètres du dictionnaire

    Une requête SQL sera ensuite fait sur la base de donnée et vérifira si chaque clé de colonne a la valeur
    qui était définie dans le dictionnaire

    Il vérifie aussi pour les fonctions comme démarrer un chrono sur une tache qui n'existe pas et donc qui
    crée une réponse http de 500




