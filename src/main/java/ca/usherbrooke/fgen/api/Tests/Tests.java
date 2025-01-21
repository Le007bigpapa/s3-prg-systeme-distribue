package ca.usherbrooke.fgen.api.Tests;

import ca.usherbrooke.fgen.api.Tests.UnitTests.UnitTests;


public class Tests
{

    public static boolean __CLEAN__ = false; //Nettoye la base de donnée après les tests
    public static boolean __SESSION__ = true; //Test Session
    public static boolean __TACHE__ = true; //Test Tache
    public static boolean __PROJECT__ = true; //Test Projet
    public static boolean __USAGER__ = false; //Test Usager
    public static boolean __DEBUG__ = false; //Affiche les variables utiles pour le  Debug




    public static void main(String[] args) {


        UnitTests u_tests = new UnitTests();
    }
}
