package ca.usherbrooke.fgen.api.Tests.UnitTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Hashtable;

public class Scripts_UnitTests {



    //Dictionnaire Test Session
    List<Hashtable<String, String>> s_verifyDatas = new ArrayList();

    //Dictionnaire Test Tache
    List<Hashtable<String, String>> t_verifyDatas = new ArrayList();

    //Dictionnaire Test Projet
    List<Hashtable<String, String>> p_verifyDatas = new ArrayList();

    //Dictionnaire de base avec l'entièreté des clés
    Hashtable base_verifyDatas = new Hashtable();

    //@Path("addtache/{titre}/{description}/{idProjet}/{cipCreateur}/{idTacheParent}/{cip}")






    // Constructeur
    public Scripts_UnitTests() {

        //Dictionnaire de base. il faut qu'il y aille l'entièreté des clés possibles
        base_verifyDatas.put("titre_fonction", "NONE");
        base_verifyDatas.put("titre", "NONE");
        base_verifyDatas.put("description", "NONE");
        base_verifyDatas.put("id_projet", "NONE");
        base_verifyDatas.put("id_tacheParent", "NONE");
        base_verifyDatas.put("id_session", "NONE");
        base_verifyDatas.put("id_tache", "NONE");
        base_verifyDatas.put("cip_createur", "NONE");
        base_verifyDatas.put("cip_proprietaire", "NONE");
        base_verifyDatas.put("cip_derniere_modif", "NONE");
        base_verifyDatas.put("cip_usager", "NONE");
        base_verifyDatas.put("date_debut", "NONE");
        base_verifyDatas.put("date_fin", "NONE");
        base_verifyDatas.put("date_creation", "NONE");
        base_verifyDatas.put("date_modifier", "NONE");
        base_verifyDatas.put("commencer_a", "NONE");
        base_verifyDatas.put("fini_a", "NONE");
        base_verifyDatas.put("response_statusCode", "NONE");
        base_verifyDatas.put("SQL_response", "NONE");
        base_verifyDatas.put("SQL_request", "NONE");



        //_________________TEST_SESSION_________________
        Hashtable<String, String> s_verifyDatas1 = new Hashtable<>();
        s_verifyDatas1.put("titre_fonction", "chrono");
        s_verifyDatas1.put("id_session", "1");
        s_verifyDatas1.put("commencer_a", "date");
        s_verifyDatas1.put("fini_a", "null");
        s_verifyDatas1.put("id_tache", "1");
        s_verifyDatas1.put("cip_usager", "test1234");
        s_verifyDatas1.put("nom_test", "CHRONO : Normal_StartChrono");
        s_verifyDatas1.put("response_statusCode", "200");
        s_verifyDatas1.put("SQL_response", "notnull");

        Hashtable<String, String> s_verifyDatas2 = new Hashtable<>();
        s_verifyDatas2.put("titre_fonction", "chrono");
        s_verifyDatas2.put("id_session", "1");
        s_verifyDatas2.put("commencer_a", "date");
        s_verifyDatas2.put("fini_a", "date");
        s_verifyDatas2.put("id_tache", "1");
        s_verifyDatas2.put("cip_usager", "test1234");
        s_verifyDatas2.put("nom_test", "CHRONO : Normal_StopChrono");
        s_verifyDatas2.put("response_statusCode", "200");
        s_verifyDatas2.put("SQL_response", "notnull");

        Hashtable<String, String> s_verifyDatas3 = new Hashtable<>();
        s_verifyDatas3.put("titre_fonction", "chrono");
        s_verifyDatas3.put("id_session", "2");
        s_verifyDatas3.put("commencer_a", "date");
        s_verifyDatas3.put("fini_a", "null");
        s_verifyDatas3.put("id_tache", "1");
        s_verifyDatas3.put("cip_usager", "test1234");
        s_verifyDatas3.put("nom_test", "CHRONO : Redemarrer un chronometre arrete");
        s_verifyDatas3.put("response_statusCode", "200");
        s_verifyDatas3.put("SQL_response", "notnull");

        s_verifyDatas.add(s_verifyDatas1);
        s_verifyDatas.add(s_verifyDatas2);
        s_verifyDatas.add(s_verifyDatas3);



        //_________________TEST_TACHE_________________
        Hashtable<String, String> t_verifyDatas1 = new Hashtable<>();
        t_verifyDatas1.put("titre_fonction", "addtache");
        t_verifyDatas1.put("titre", "Test_Tache");
        t_verifyDatas1.put("id_tache", "1"); //ici c'est + 0 du dernier num et non l'id zéro
        t_verifyDatas1.put("nom_test", "TACHE : Ajout d'une tache dans un projet");
        t_verifyDatas1.put("description", "Description_Test1");
        t_verifyDatas1.put("id_projet", "1");
        t_verifyDatas1.put("cip_createur", "test1234");
        t_verifyDatas1.put("id_tache_parent", "null");
        t_verifyDatas1.put("date_creation", "date");
        t_verifyDatas1.put("date_modifier", "date");
        t_verifyDatas1.put("cip", "test1234");
        t_verifyDatas1.put("response_statusCode", "200");
        t_verifyDatas1.put("SQL_response", "notnull");


        Hashtable<String, String> t_verifyDatas2 = new Hashtable<>();
        t_verifyDatas2.put("titre_fonction", "addtache");
        t_verifyDatas2.put("titre", "Test_Tache");
        t_verifyDatas2.put("id_tache", "2"); //ici c'est + 0 du dernier num et non l'id zéro
        t_verifyDatas2.put("nom_test", "TACHE : Ajout d'une tache dans un projet avec tache parent");
        t_verifyDatas2.put("description", "Sous_Tache_de_Tache_1");
        t_verifyDatas2.put("id_projet", "1");
        t_verifyDatas2.put("cip_createur", "test1234");
        t_verifyDatas2.put("id_tache_parent", "1");
        t_verifyDatas2.put("date_creation", "date");
        t_verifyDatas2.put("date_modifier", "date");
        t_verifyDatas2.put("cip", "test1234");
        t_verifyDatas2.put("response_statusCode", "200");
        t_verifyDatas2.put("SQL_response", "notnull");


        Hashtable<String, String> t_verifyDatas3 = new Hashtable<>();
        t_verifyDatas3.put("titre_fonction", "removetache");
        t_verifyDatas3.put("id_tache", "2"); //ici c'est + dernier num et non l'id zéro
        t_verifyDatas3.put("nom_test", "TACHE : Suppression d'une tache");
        t_verifyDatas3.put("response_statusCode", "200");
        t_verifyDatas3.put("SQL_response", "null");




        //modifytache/{idTache}/{titre}/{description}
        Hashtable<String, String> t_verifyDatas4 = new Hashtable<>();
        t_verifyDatas4.put("titre_fonction", "modifytache");
        t_verifyDatas4.put("titre", "Test_Tache");
        t_verifyDatas4.put("id_tache", "1"); //ici c'est + 0 du dernier num et non l'id zéro
        t_verifyDatas4.put("nom_test", "TACHE : modification d'une tache existante");
        t_verifyDatas4.put("description", "Description_Test1_mofifie");
        t_verifyDatas4.put("id_projet", "1");
        t_verifyDatas4.put("cip_createur", "test1234");
        t_verifyDatas4.put("id_tache_parent", "null");
        t_verifyDatas4.put("date_creation", "date");
        t_verifyDatas4.put("date_modifier", "date");
        t_verifyDatas4.put("cip", "test1234");
        t_verifyDatas4.put("response_statusCode", "204");
        t_verifyDatas4.put("SQL_response", "notnull");

        Hashtable<String, String> t_verifyDatas5 = new Hashtable<>();
        t_verifyDatas5.put("titre_fonction", "addtache");
        t_verifyDatas5.put("titre", "Test_Tache");
        t_verifyDatas5.put("id_tache", "3"); //ici c'est + 0 du dernier num et non l'id zéro
        t_verifyDatas5.put("nom_test", "TACHE : Ajout d'une deuxième sous-tache");
        t_verifyDatas5.put("description", "Sous_Tache_de_Tache_1");
        t_verifyDatas5.put("id_projet", "1");
        t_verifyDatas5.put("cip_createur", "test1234");
        t_verifyDatas5.put("id_tache_parent", "1");
        t_verifyDatas5.put("date_creation", "date");
        t_verifyDatas5.put("date_modifier", "date");
        t_verifyDatas5.put("cip", "test1234");
        t_verifyDatas5.put("response_statusCode", "200");
        t_verifyDatas5.put("SQL_response", "notnull");


        //A faire plus tard
        /*Hashtable<String, String> t_verifyDatas6 = new Hashtable<>();
        t_verifyDatas6.put("titre_fonction", "removetache");
        t_verifyDatas6.put("id_tache", "2"); //ici c'est + dernier num et non l'id zéro
        t_verifyDatas6.put("nom_test", "TACHE : Suppression d'une tache parent");
        t_verifyDatas6.put("response_statusCode", "200");
        t_verifyDatas6.put("SQL_response", "null");
        t_verifyDatas6.put("SQL_request", "null");*/




        
        t_verifyDatas.add(t_verifyDatas1);
        t_verifyDatas.add(t_verifyDatas2);
        t_verifyDatas.add(t_verifyDatas3);
        t_verifyDatas.add(t_verifyDatas4);
        t_verifyDatas.add(t_verifyDatas5);



        //_________________TEST_PROJET_________________
        Hashtable<String, String> p_verifyDatas1 = new Hashtable<>();
        p_verifyDatas1.put("titre_fonction", "newProjet");
        p_verifyDatas1.put("nom_test", "PROJET : ajouter un nouveau projet normal");
        p_verifyDatas1.put("id_projet", "1");
        p_verifyDatas1.put("titre", "Projet_Test");
        p_verifyDatas1.put("description", "Description_Projet_Test");
        p_verifyDatas1.put("date_creation", "date");
        p_verifyDatas1.put("date_modifier", "date");
        p_verifyDatas1.put("cip_createur", "test1234");
        p_verifyDatas1.put("cip_proprietaire", "test1234");
        p_verifyDatas1.put("cip_derniere_modif", "test1234");
        p_verifyDatas1.put("response_statusCode", "204");
        p_verifyDatas1.put("SQL_response", "notnull");

        Hashtable<String, String> p_verifyDatas2 = new Hashtable<>();
        p_verifyDatas2.put("titre_fonction", "editProjet");
        p_verifyDatas2.put("nom_test", "PROJET : modification d'un projet normal");
        p_verifyDatas2.put("id_projet", "1");
        p_verifyDatas2.put("titre", "Projet_Test");
        p_verifyDatas2.put("description", "Description_Projet_Test_modifie");
        p_verifyDatas2.put("date_creation", "date");
        p_verifyDatas2.put("date_modifier", "date");
        p_verifyDatas2.put("cip_createur", "test1234");
        p_verifyDatas2.put("cip_proprietaire", "test1234");
        p_verifyDatas2.put("cip_derniere_modif", "test1234");
        p_verifyDatas2.put("response_statusCode", "204");
        p_verifyDatas2.put("SQL_response", "notnull");

        Hashtable<String, String> p_verifyDatas3 = new Hashtable<>();
        p_verifyDatas3.put("titre_fonction", "newProjet");
        p_verifyDatas3.put("nom_test", "PROJET : ajouter un deuxieme projet");
        p_verifyDatas3.put("id_projet", "2");
        p_verifyDatas3.put("titre", "Projet_Test");
        p_verifyDatas3.put("description", "Description_Projet_Test2");
        p_verifyDatas3.put("date_creation", "date");
        p_verifyDatas3.put("date_modifier", "date");
        p_verifyDatas3.put("cip_createur", "test1234");
        p_verifyDatas3.put("cip_proprietaire", "test1234");
        p_verifyDatas3.put("cip_derniere_modif", "test1234");
        p_verifyDatas3.put("response_statusCode", "204");
        p_verifyDatas3.put("SQL_response", "notnull");

        Hashtable<String, String> p_verifyDatas4 = new Hashtable<>();
        p_verifyDatas4.put("titre_fonction", "deleteProjet");
        p_verifyDatas4.put("nom_test", "PROJET : suppression d'un projet");
        p_verifyDatas4.put("id_projet", "2");
        p_verifyDatas4.put("response_statusCode", "204");
        p_verifyDatas4.put("SQL_response", "null");

        p_verifyDatas.add(p_verifyDatas1);
        p_verifyDatas.add(p_verifyDatas2);
        p_verifyDatas.add(p_verifyDatas3);
        p_verifyDatas.add(p_verifyDatas4);



        
    }

    // Autres méthodes de la classe

    public static void main(String[] args) {
        // Exemple d'utilisation
        Scripts_UnitTests unitTests = new Scripts_UnitTests();
        // Vous pouvez ensuite utiliser unitTests.verifyDatas dans votre programme
    }
}
