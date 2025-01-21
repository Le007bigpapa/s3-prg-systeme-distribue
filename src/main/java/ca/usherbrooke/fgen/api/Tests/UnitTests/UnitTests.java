package ca.usherbrooke.fgen.api.Tests.UnitTests;


import ca.usherbrooke.fgen.api.Service.SessionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

import ca.usherbrooke.fgen.api.Tests.Tests;


//import personnel


public class UnitTests
{


    private static final String URL = "jdbc:postgresql://localhost:5444/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    int u_id_tache = 1;
    String u_titre_Tache = "Tache_Test";
    String u_description_Tache = "Description_Test";
    int u_id_projet = 1;
    String u_cip_createur_tache = "test1234";
    String u_cip = "test1234";
    String u_email = "test1234@usherbrooke.ca";
    String u_titre_projet = "Projet_Test";
    String u_description_projet = "Description_Projet_Test";
    String u_cip_createur_projet = "test1234";
    String u_cip_proprietaire_projet = "test1234";

    boolean s_last_id_checked = false;
    boolean t_last_id_checked = false;
    boolean p_last_id_checked = false;




    ColoredConsole c;
    Scripts_UnitTests scripts_u = new Scripts_UnitTests();



    int s_last_idPrimaryKey;
    int t_last_idPrimaryKey;
    int p_last_idPrimaryKey;


    public UnitTests()
    {
        //System.out.println(c.YELLOW + "UnitTests" + c.RESET);
        cleanAfterUnitTests();
        Tests __TESTS__ = new Tests();


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);)
        {
            CallableStatement callableStatement_Usager = connection.prepareCall("INSERT INTO app.usager(cip, email) VALUES (?, ?)");
            callableStatement_Usager.setString(1, u_cip);
            callableStatement_Usager.setString(2, u_email);
            callableStatement_Usager.execute();

            if(!__TESTS__.__PROJECT__)
            {
                String userQuery = "INSERT INTO app.projet(" +
                        "id_projet," +
                        "titre," +
                        "description," +
                        "date_creation," +
                        "date_modifier," +
                        "cip_createur," +
                        "cip_proprietaire," +
                        "cip_derniere_modif)" +
                        " VALUES (?, ?, ?, now(), null, ?, ?, null)";

                PreparedStatement callableStatement_Projet = connection.prepareStatement(userQuery);
                p_last_idPrimaryKey = get_lasIdProjet();
                callableStatement_Projet.setInt(1, p_last_idPrimaryKey);
                callableStatement_Projet.setString(2, u_titre_projet);
                callableStatement_Projet.setString(3, u_description_projet);
                callableStatement_Projet.setString(4, u_cip_createur_projet);
                callableStatement_Projet.setString(5, u_cip_proprietaire_projet);

                callableStatement_Projet.executeUpdate();

            }
            else
            {
                p_last_idPrimaryKey = get_lasIdProjet();
                TEST_Projet(__TESTS__);
            }



            if(!__TESTS__.__TACHE__)
            {
                CallableStatement callableStatement_Task = connection.prepareCall("INSERT INTO app.tache(" +
                        "id_tache," +
                        " titre," +
                        " description," +
                        " date_creation," +
                        " date_modifier," +
                        " id_projet," +
                        " cip_createur," +
                        " id_tache_parent)" +
                        " VALUES (?, ?, ?, now(), null, ?, ?, null)");
                t_last_idPrimaryKey = get_lasIdTache();
                callableStatement_Task.setInt(1, t_last_idPrimaryKey);
                callableStatement_Task.setString(2, u_titre_Tache);
                callableStatement_Task.setString(3, u_description_Tache);
                callableStatement_Task.setInt(4, p_last_idPrimaryKey);
                callableStatement_Task.setString(5, u_cip_createur_tache);



                callableStatement_Task.execute();
            }
            else
            {
                t_last_idPrimaryKey = get_lasIdTache();
                TEST_Tache(__TESTS__);
            }


        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(c.RED + "l'initialisation des variables de UnitTests n'on pas été initialisé" + c.RESET);
        }
















        if(__TESTS__.__SESSION__)
        {
            s_last_idPrimaryKey = get_lasIdSession();
            TEST_Session(__TESTS__);
        }



        if(__TESTS__.__CLEAN__){cleanAfterUnitTests();}



    }

    /**
     *
     * @param __TESTS__
     * @return
     */
    public boolean TEST_Session(Tests __TESTS__) {
        //public boolean startSession(Integer id_session, Integer id_tache, String cip_usager)
        //System.out.println(c.YELLOW + "TEST_Session" + c.RESET);


        verifyTest(scripts_u.s_verifyDatas);



        return true;
    }

    /**
     *
     * @param __TESTS__
     * @return
     */
    //========================= TEST TACHE =========================//
    public boolean TEST_Tache(Tests __TESTS__)
    {
        //public boolean addTache(String titre, String description, Integer idProjet, String cipCreateur, Integer idTacheParent, String cip)
        //System.out.println(c.YELLOW + "TEST_Tache" + c.RESET);

        verifyTest(scripts_u.t_verifyDatas);


        return true;
    }

    public boolean TEST_Projet(Tests __TESTS__)
    {
        //public boolean addTache(String titre, String description, Integer idProjet, String cipCreateur, Integer idTacheParent, String cip)
        //System.out.println(c.YELLOW + "TEST_Tache" + c.RESET);

        verifyTest(scripts_u.p_verifyDatas);


        return true;
    }




    //========================= TEST SESSION =========================//


    public int get_lasIdSession()
    {
        //System.out.println(c.YELLOW + "get_lasIdSession()" + c.RESET);
        int lastIdSession = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Préparer la requête SQL pour récupérer la dernière ligne
            if(!this.s_last_id_checked)
            {

                /*if(!t_last_id_checked)
                {
                    t_last_id_checked = true;
                    String query = "INSERT INTO app.session(commencer_a, fini_a, id_tache, cip_usager)  VALUES (now(), NULL, " + get_lasIdTache() + ", 'test1234')";
                    t_last_id_checked = false;
                }
                else
                {
                    String query = "INSERT INTO app.session(commencer_a, fini_a, id_tache, cip_usager)  VALUES (now(), NULL, " + get_lasIdTache() + ", 'test1234')";
                }
                t_last_id_checked = true;*/
                String query = "INSERT INTO app.session(commencer_a, fini_a, id_tache, cip_usager)  VALUES (now(), NULL, " + get_lasIdTache() + ", 'test1234')";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }


            String query1 = "SELECT id_session FROM app.session ORDER BY id_session DESC LIMIT 1";
            try (PreparedStatement stmt1 = connection.prepareStatement(query1)) {
                // Exécuter la requête et obtenir le résultat
                try (ResultSet rs = stmt1.executeQuery()) {
                    // Vérifier s'il y a un résultat
                    if (rs.next()) {
                        // Récupérer la valeur de id_session de la dernière ligne
                        lastIdSession = rs.getInt("id_session");
                    } else {
                        // Aucun résultat trouvé
                        System.out.println("Aucune ligne trouvee dans la table session");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs de requête SQL
            }

            if(!this.s_last_id_checked)
            {
                String query2 = "DELETE FROM app.session WHERE id_session = " + lastIdSession + " AND cip_usager = 'test1234'";
                try (PreparedStatement stmt = connection.prepareStatement(query2)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }

            this.s_last_id_checked = true;


        }
        catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion à la base de données
        }

        return lastIdSession;
    }

    public int get_lasIdTache()
    {
        //System.out.println(c.YELLOW + "get_lasIdTache()" + c.RESET);
        int lastIdTache = 0;


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Préparer la requête SQL pour récupérer la dernière ligne
            if(!this.t_last_id_checked)
            {
                String query = "INSERT INTO app.tache(titre, description, date_creation, date_modifier, id_projet, cip_createur, id_tache_parent) " +
                        "VALUES ('Get_Last_Id', 'no_des', now(), NULL, "+ get_lasIdProjet() +", 'test1234', NULL)";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }


            String query1 = "SELECT id_tache FROM app.tache ORDER BY id_tache DESC LIMIT 1";
            try (PreparedStatement stmt1 = connection.prepareStatement(query1)) {
                // Exécuter la requête et obtenir le résultat
                try (ResultSet rs = stmt1.executeQuery()) {
                    // Vérifier s'il y a un résultat
                    if (rs.next()) {
                        // Récupérer la valeur de id_session de la dernière ligne
                        lastIdTache = rs.getInt("id_tache");
                    } else {
                        // Aucun résultat trouvé
                        System.out.println("Aucune ligne trouvee dans la table tache");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs de requête SQL
            }

            if(!this.t_last_id_checked)
            {
                String query2 = "DELETE FROM app.tache WHERE id_tache = " + lastIdTache + " AND cip_createur = 'test1234'";
                try (PreparedStatement stmt = connection.prepareStatement(query2)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }

            this.t_last_id_checked = true;

        }
        catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion à la base de données
        }

        return lastIdTache;
    }

    public int get_lasIdProjet()
    {
        //System.out.println(c.YELLOW + "get_lasIdTache()" + c.RESET);
        int lastIdProjet = 0;


        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Préparer la requête SQL pour récupérer la dernière ligne
            if(!this.p_last_id_checked)
            {
                String query = "INSERT INTO app.projet(titre, description, date_creation, date_modifier, cip_createur, cip_proprietaire, cip_derniere_modif) " +
                        "VALUES ('Get_Last_Id', 'no_des', now(), now(), 'test1234', 'test1234', 'test1234')";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }


            String query1 = "SELECT id_projet FROM app.projet ORDER BY id_projet DESC LIMIT 1";
            try (PreparedStatement stmt1 = connection.prepareStatement(query1)) {
                // Exécuter la requête et obtenir le résultat
                try (ResultSet rs = stmt1.executeQuery()) {
                    // Vérifier s'il y a un résultat
                    if (rs.next()) {
                        // Récupérer la valeur de id_session de la dernière ligne
                        lastIdProjet = rs.getInt("id_projet");
                    } else {
                        // Aucun résultat trouvé
                        System.out.println("Aucune ligne trouvée dans la table projet");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Gérer les erreurs de requête SQL
            }

            if(!this.p_last_id_checked)
            {
                String query2 = "DELETE FROM app.projet WHERE id_projet = " + lastIdProjet + " AND cip_createur = 'test1234'";
                try (PreparedStatement stmt = connection.prepareStatement(query2)) {
                    stmt.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Gérer les erreurs de requête SQL
                }
            }

            this.p_last_id_checked = true;

        }
        catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs de connexion à la base de données
        }

        return lastIdProjet;
    }




    /**
     *
     * @param vd
     */
    public void verifyTest(List<Hashtable<String, String>> vd) {
        //System.out.println(c.YELLOW + "verifyTest(List<Hashtable<String, String>> vd)" + c.RESET);
        HttpClient client = HttpClient.newHttpClient();
        Tests __TESTS__ = new Tests();

        if (scripts_u.base_verifyDatas == null) {
            scripts_u.base_verifyDatas = new Hashtable<>();
        }

        Hashtable<String, String> t_vd = new Hashtable<>(scripts_u.base_verifyDatas);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (int i = 0; i < vd.size(); i++)
            {
                //System.out.println("vd = " + vd.get(i));
                String titre_fonction = new String(vd.get(i).get("titre_fonction"));
                Set<String> keys = vd.get(i).keySet();
                for (String key : keys) {
                    if (key.equals("nom_fonction")) {
                        continue;
                    }
                    String value = vd.get(i).get(key);

                    if (key != null && value != null) {
                        t_vd.put(key, value);
                    } else {
                        System.out.println("Clé ou valeur null détectée : clé = " + key + ", valeur = " + value);
                    }
                }

                int id_primaryKey = 0;
                String request_url;
                String id_name;
                String table;
                switch (titre_fonction) {
                    case "addtache":

                        id_primaryKey = Integer.parseInt(t_vd.get("id_tache")) + t_last_idPrimaryKey;
                        t_vd.put("id_tache", String.valueOf(id_primaryKey));
                        if(!t_vd.get("id_tache_parent").equals("null"))
                        {
                            t_vd.put("id_tache_parent", String.valueOf(t_last_idPrimaryKey + Integer.valueOf(t_vd.get("id_tache_parent"))));
                        }
                        t_vd.put("id_projet", String.valueOf(p_last_idPrimaryKey + Integer.valueOf(t_vd.get("id_projet"))));
                        //System.out.println("id_tache = " + t_vd.get("id_tache"));
                        request_url = "http://localhost:8888/api/addtache" +
                                "/" + t_vd.get("titre") +
                                "/" + t_vd.get("description") +
                                "/" + t_vd.get("id_projet") +
                                "/" + t_vd.get("cip_createur") +
                                "/" + t_vd.get("id_tache_parent") +
                                "/" + t_vd.get("cip");
                        id_name = "id_tache";
                        table = "app.tache";
                        break;
                    case "chrono":

                        id_primaryKey = Integer.parseInt(t_vd.get("id_session")) + s_last_idPrimaryKey;
                        t_vd.put("id_session", String.valueOf(id_primaryKey));

                        t_vd.put("id_tache", String.valueOf(t_last_idPrimaryKey + Integer.valueOf(t_vd.get("id_tache"))));
                        //System.out.println("id_session = " + t_vd.get("id_session"));
                        //System.out.println("id_tache = " + t_vd.get("id_tache"));
                        request_url = "http://localhost:8888/api/chrono/" +
                                t_vd.get("cip_usager") +
                                "/" + t_vd.get("id_tache");
                        id_name = "id_session";
                        table = "app.session";
                        break;

                    case "removetache":
                        id_primaryKey = Integer.parseInt(t_vd.get("id_tache")) + t_last_idPrimaryKey;
                        t_vd.put("id_tache", String.valueOf(id_primaryKey));
                        request_url = "http://localhost:8888/api/removetache/" + t_vd.get("id_tache");
                        id_name = "id_tache";
                        table = "app.tache";
                        break;

                    //modifytache/{idTache}/{titre}/{description}
                    case "modifytache":
                        id_primaryKey = Integer.parseInt(t_vd.get("id_tache")) + t_last_idPrimaryKey;
                        t_vd.put("id_tache", String.valueOf(id_primaryKey));
                        if(!t_vd.get("id_tache_parent").equals("null"))
                        {
                            t_vd.put("id_tache_parent", String.valueOf(t_last_idPrimaryKey + Integer.valueOf(t_vd.get("id_tache_parent"))));
                        }
                        t_vd.put("id_projet", String.valueOf(p_last_idPrimaryKey + Integer.valueOf(t_vd.get("id_projet"))));
                        //System.out.println("id_tache = " + t_vd.get("id_tache"));
                        request_url = "http://localhost:8888/api/modifytache" +
                                "/" + t_vd.get("id_tache") +
                                "/" + t_vd.get("titre") +
                                "/" + t_vd.get("description");
                        id_name = "id_tache";
                        table = "app.tache";
                        break;

                    //"newProject/{cipCreateur}/{titre}/{description}"
                    case "newProjet":
                        id_primaryKey = Integer.parseInt(t_vd.get("id_projet")) + p_last_idPrimaryKey;
                        t_vd.put("id_projet", String.valueOf(id_primaryKey));
                        request_url = "http://localhost:8888/api/newProjet" +
                                "/" + t_vd.get("cip_createur") +
                                "/" + t_vd.get("titre") +
                                "/" + t_vd.get("description");
                        id_name = "id_projet";
                        table = "app.projet";
                        break;

                    //"editProjet/{idProjet}/{titre}/{description}/{cipDerniereModif}"
                    case "editProjet":
                        id_primaryKey = Integer.parseInt(t_vd.get("id_projet")) + p_last_idPrimaryKey;
                        t_vd.put("id_projet", String.valueOf(id_primaryKey));
                        request_url = "http://localhost:8888/api/editProjet" +
                                "/" + t_vd.get("id_projet") +
                                "/" + t_vd.get("titre") +
                                "/" + t_vd.get("description") +
                                "/" + t_vd.get("cip_derniere_modif") +
                                "/" + t_vd.get("cip_proprietaire");
                        id_name = "id_projet";
                        table = "app.projet";
                        break;


                    case "deleteProjet":
                        id_primaryKey = Integer.parseInt(t_vd.get("id_projet")) + p_last_idPrimaryKey;
                        t_vd.put("id_projet", String.valueOf(id_primaryKey));
                        request_url = "http://localhost:8888/api/deleteProjet" +
                                "/" + t_vd.get("id_projet");
                        id_name = "id_projet";
                        table = "app.projet";
                        break;



                    default:
                        request_url = "http://localhost:8888/api/test";
                        table = "app.tache";
                        id_name = "id_tache";
                }



                System.out.print(c.SPACE1 + c.BLUE + "TEST " + t_vd.get("nom_test") + c.RESET + " : ");
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(request_url))
                        .build();

                try {
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    if(response.statusCode() != Integer.valueOf(t_vd.get("response_statusCode")))
                    {
                        System.out.println(c.ECHEC);
                        System.out.println(c.SPACE2 + c.RED +  "response.statusCode() != " + t_vd.get("response_statusCode") + " response.statusCode() == " + response.statusCode() + c.RESET);
                        System.out.println(c.SPACE2 + c.RED + "la requete est mauvaise : " + request_url + c.RESET);
                        continue;
                    }

                    response.body();
                } catch (IOException | InterruptedException e) {
                    System.out.println(c.SPACE2 + c.BLUE + "request_url : " + request_url);
                    e.printStackTrace();
                }


                String sql_request = "SELECT * FROM " + table + " WHERE "+ id_name + " = "+ id_primaryKey;
                if(!Objects.equals(t_vd.get("SQL_request"), "NONE"))
                {
                    sql_request = t_vd.get("SQL_request");
                }
                //System.out.println("sql_request = " + sql_request);
                try (CallableStatement callableStatement = connection.prepareCall(sql_request)) {


                    boolean rs1 = callableStatement.execute();

                    if (rs1) {
                        try (ResultSet rs = callableStatement.getResultSet()) {
                            ResultSetMetaData metaData = rs.getMetaData();
                            int columnCount = metaData.getColumnCount();

                            boolean echec = false;
                            String SQL_response = "null";
                            Hashtable<String, Object> row = new Hashtable<>();
                            while (rs.next()) {
                                SQL_response = "notnull";
                                row = new Hashtable<>(); // Réinitialisation de row à chaque itération

                                for (int z = 1; z <= columnCount; z++) {
                                    String columnName = metaData.getColumnName(z);
                                    Object value = rs.getObject(z);
                                    if (columnName != null) {
                                        if(value == null){row.put(columnName, "null"); continue;}
                                        row.put(columnName, value.toString());
                                    }
                                }


                                for (String key : row.keySet()) {
                                    Object rowValue = row.get(key);
                                    String t_vdValue = t_vd.get(key);
                                    if (!row.containsKey(key) || !row.get(key).toString().equals(t_vd.get(key))) {

                                        if ("date".equals(t_vdValue)) {
                                            if (rowValue != "null") {
                                                continue;
                                            }
                                        }

                                        if (!echec) {
                                            System.out.println(c.ECHEC);
                                            echec = true;
                                        }

                                        System.out.println(c.SPACE2 + c.BLUE + key + " != " + c.RESET + t_vd.get(key) + c.BLUE + " key = " + c.RED + row.get(key) + c.RESET);
                                    }
                                }

                            }

                            if(SQL_response.equals(t_vd.get("SQL_response")) && !echec)
                            {
                                System.out.println(c.REUSSI);
                            }
                            else if(!echec)
                            {
                                System.out.println(c.ECHEC);
                                if(!SQL_response.equals(t_vd.get("SQL_response")))
                                {
                                    System.out.println(c.SPACE2 + c.RED + "SQL_response != " + t_vd.get("SQL_response") + " SQL_response == " + SQL_response);
                                }

                            }
                            System.out.println(c.SPACE2 + c.BLUE + "request : " + c.RESET + request_url);

                            if(__TESTS__.__DEBUG__){debug(row, t_vd, vd.get(i));}
                        }
                    }
                    else {
                        System.out.println(c.ECHEC);
                        System.out.println(c.SPACE2 + c.BLUE + "request : " + c.RESET + request_url);
                        System.out.println(c.SPACE2 + c.RED + "Pas de resultats trouves pour le test " + i + c.RESET);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void debug(Hashtable<String, Object> row, Hashtable<String, String> t_vd, Hashtable<String, String> dicti)
    {
        ColoredConsole c = new ColoredConsole();
        System.out.println("\n" + c.SPACE2 + c.GREY + "Valeur attendu :");
        for (String key : row.keySet())
        {
            System.out.println(c.SPACE3 + key + " : " + row.get(key));
        }

        System.out.println(c.SPACE2 + c.GREY + "Valeur des colonnes comparées du dictionnaire :");
        for (String key : row.keySet())
        {
            System.out.println(c.SPACE3 + key + " : " + t_vd.get(key));
        }

        System.out.println(c.SPACE2 + c.GREY + "Dictionnaire :");
        for (String key : dicti.keySet())
        {
            System.out.println(c.SPACE3 + key + " : " + t_vd.get(key));
        }
        System.out.println(c.RESET);

    }




    public static void cleanAfterUnitTests()
    {
        //System.out.println("cleanAfterUnitTests");
        cleanSessionUnitTests();
        cleanTacheUnitTests();
        cleanProjetUnitTests();
        cleanUsagerUnitTests();
    }

    public static void cleanSessionUnitTests()
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Préparer et exécuter la première requête DELETE
            CallableStatement deleteSessionStatement = connection.prepareCall("DELETE FROM app.session WHERE cip_usager = 'test1234'");
            deleteSessionStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanTacheUnitTests()
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM app.tache WHERE cip_createur = 'test1234';")) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanProjetUnitTests()
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM app.projet WHERE cip_createur = 'test1234';")) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cleanUsagerUnitTests()
    {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Préparer et exécuter la deuxième requête DELETE
            try (CallableStatement deleteUsagerStatement = connection.prepareCall("DELETE FROM app.usager WHERE cip = 'test1234'")) {
                deleteUsagerStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}









