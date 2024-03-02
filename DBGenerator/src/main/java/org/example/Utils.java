package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Utils {

    private static Random RANDOM = new Random();


    public static ArrayList<String> IMENA ;
    public static ArrayList<String> PREZIMENA;
    public static ArrayList<Long> LISTAJMB;
    public static ArrayList<String> PORTOVI;
    public static ArrayList<String> PROSTORIJE;



    static{
        String[] imena = {
                "Stefan", "Marko", "Nikola", "Aleksandar", "Ivan",
                "Filip", "Vladimir", "Dusan", "Milos", "Dragan",
                "Ana", "Jovana", "Marija", "Milica", "Sofija",
                "Natalija", "Maja", "Jelena", "Ivana", "Katarina"
        };
        IMENA = new ArrayList<>(Arrays.asList(imena));


        String[] prezimena = {
                "Petrović", "Jovanović", "Nikolić", "Ivanović", "Kovačević",
                "Stojanović", "Marković", "Đorđević", "Stanković", "Pavlović",
                "Milosavljević", "Stefanović", "Đorđević", "Simić", "Janković",
                "Milić", "Vuković", "Lukić", "Krstić", "Tošić"
        };
        PREZIMENA = new ArrayList<>(Arrays.asList(prezimena));

        String[] portNames = {
                "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "COM10"
        };
        PORTOVI = new ArrayList<>(Arrays.asList(portNames));

        // Adding room names
        String[] roomNames = {
                "Radna soba 1", "Radna soba 2", "Kuhinja", "Toalet", "Kancelarija", "Garaža", "Podrum","Bife"
        };
        PROSTORIJE = new ArrayList<>(Arrays.asList(roomNames));

    }

    public static String JDBCURL = "jdbc:mysql://192.168.20.249:3306/evidencija_radnog_vremena";


    public static String getIme(){

        return IMENA.get( RANDOM.nextInt(IMENA.size() ));
    }

    public static String getPrezime(){

        return PREZIMENA.get( RANDOM.nextInt(PREZIMENA.size() ));
    }

    public static String getPort(){

        return PORTOVI.get( RANDOM.nextInt(PORTOVI.size() ));
    }

    public static String getProstorija(){

        return PROSTORIJE.get( RANDOM.nextInt(PROSTORIJE.size() ));
    }


}
