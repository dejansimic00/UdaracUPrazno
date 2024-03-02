package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class Main {

    private static Random RANDOM = new Random();

    public static void main(String[] args) {
        // Establishing a connection
        try (Connection connection = DriverManager.getConnection(Utils.JDBCURL, "root", "Tesl499")) {
            if (connection != null) {
                System.out.println("Connected to the database!");
                /////////////////////
                //dodajPoslove(connection); // Call the method to add jobs
                //dodajSmjene(connection);
                //dodajOsobe(connection);
                //dodajSefove(connection);
                //dodajNaloge(connection);
                dodajProstorije(connection);

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    private static void dodajPoslove(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            // Insert four jobs into the "posao" table
            statement.executeUpdate("INSERT INTO POSAO (naziv, koeficijent) VALUES ('CA', 1)");
            statement.executeUpdate("INSERT INTO POSAO (naziv, koeficijent) VALUES ('HR', 2)");
            statement.executeUpdate("INSERT INTO POSAO (naziv, koeficijent) VALUES ('G', 3)");
            statement.executeUpdate("INSERT INTO POSAO (naziv, koeficijent) VALUES ('K', 4)");
            System.out.println("Jobs added successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to add jobs to the database!");
            e.printStackTrace();
        }
    }

    private static void dodajSmjene(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            // Insert shifts into the "smjena" table
            statement.executeUpdate("INSERT INTO SMJENA (naziv, pocetak, kraj) VALUES ('Prva', '08:00:00', '16:00:00')");
            statement.executeUpdate("INSERT INTO SMJENA (naziv, pocetak, kraj) VALUES ('Druga', '16:00:00', '00:00:00')");
            statement.executeUpdate("INSERT INTO SMJENA (naziv, pocetak, kraj) VALUES ('Treca', '00:00:00', '08:00:00')");
            System.out.println("Shifts added successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to add shifts to the database!");
            e.printStackTrace();
        }
    }

    private static void dodajOsobe(Connection connection) {
        // Insert shifts into the "smjena" table
        long idOsoba;
        String ime;
        String prezime;
        String jmb;
        String slika;
        boolean sef = false;


        for (int i = 0; i < 200; i++) {
            idOsoba = RANDOM.nextInt((int) Math.pow(2, 32));
            ime = Utils.getIme();
            prezime = Utils.getPrezime();
            slika = "slikeRadnika/" + idOsoba + ".jpg";
            jmb = String.valueOf(RANDOM.nextLong((long) Math.pow(10, 13)));
            sef = i % 40 == 0; // Assign true or false based on the condition

            String query = "INSERT INTO OSOBA (id_osoba, ime, prezime, jmbg, slika, sef) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, idOsoba);
                statement.setString(2, ime);
                statement.setString(3, prezime);
                statement.setString(4, jmb);
                statement.setString(5, slika);
                statement.setBoolean(6, sef);

                statement.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Failed to insert data into OSOBA table!");
                e.printStackTrace();
                i--;
            }
        }


        System.out.println("Shifts added successfully!");
    }


    private static void dodajSefove(Connection connection) {

        int glavniSefID = 111111111;

        try (Statement statement = connection.createStatement()) {
            // Insert main supervisor into OSOBA table
            String queryInsertMainSupervisor = "INSERT INTO OSOBA (id_osoba, ime, prezime, jmbg, slika, sef) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertMainSupervisorStatement = connection.prepareStatement(queryInsertMainSupervisor);
            insertMainSupervisorStatement.setInt(1, glavniSefID);
            insertMainSupervisorStatement.setString(2, "Marko");
            insertMainSupervisorStatement.setString(3, "Markovic");
            insertMainSupervisorStatement.setString(4, "1111111111111");
            insertMainSupervisorStatement.setString(5, "slikeRadnika/1111111111111");
            insertMainSupervisorStatement.setBoolean(6, true);
            insertMainSupervisorStatement.executeUpdate();

            // Retrieve all supervisors
            String querySelectSupervisors = "SELECT * FROM OSOBA WHERE sef = 1";
            ResultSet supervisorsResultSet = statement.executeQuery(querySelectSupervisors);

            // Insert main supervisor into SEF table

            ArrayList<Integer> idSefofva = new ArrayList<>();
            while (supervisorsResultSet.next()) {
                int supervisorId = supervisorsResultSet.getInt("id_osoba");
                String queryInsertIntoSEF = "INSERT INTO SEF (id_osoba, id_sef) VALUES (?, ?)";
                PreparedStatement insertIntoSEFStatement = connection.prepareStatement(queryInsertIntoSEF);
                insertIntoSEFStatement.setInt(1, supervisorId);
                insertIntoSEFStatement.setInt(2, glavniSefID);
                insertIntoSEFStatement.executeUpdate();
                idSefofva.add(supervisorId);
            }

            String query = "Select * from OSOBA";
            ResultSet osobe = statement.executeQuery(query);

            while (osobe.next()) {

                int idSef = idSefofva.get(RANDOM.nextInt(idSefofva.size()));

                int idOsoba = osobe.getInt("id_osoba");
                String queryInsertIntoSEF = "INSERT INTO SEF (id_osoba, id_sef) VALUES (?, ?)";
                PreparedStatement insertIntoSEFStatement = connection.prepareStatement(queryInsertIntoSEF);
                insertIntoSEFStatement.setInt(1, idOsoba);
                insertIntoSEFStatement.setInt(2, idSef);
                insertIntoSEFStatement.executeUpdate();

            }


        } catch (SQLException e) {
            System.err.println("Failed to execute SQL queries!");
            e.printStackTrace();
        }

    }

    private static void dodajNaloge(Connection connection) {

        int glavniSefID = 111111111;

        int idOsoba = 0;
        String korIme = "";
        String loz = "";
        int uloga = 0;

        try (Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM OSOBA ";
            ResultSet osobe = statement.executeQuery(query);


            // za sistemskog administratora
            osobe.next();

            idOsoba = osobe.getInt("id_osoba");
            //korIme=osobe.getString("ime").toLowerCase();
            //loz=osobe.getString("ime").toLowerCase();
            korIme = "admin";
            loz = "admin";
            uloga = 1;

            query = "INSERT INTO NALOG (id_osoba, korisnicko_ime, lozinka, uloga) VALUES  (?,?,?,?)";
            PreparedStatement insertToOsoba = connection.prepareStatement(query);
            insertToOsoba.setInt(1, idOsoba);
            insertToOsoba.setString(2, korIme);
            insertToOsoba.setString(3, loz);
            insertToOsoba.setInt(4, uloga);
            insertToOsoba.executeUpdate();


            //za HR
            osobe.next();

            idOsoba = osobe.getInt("id_osoba");
            //korIme=osobe.getString("ime").toLowerCase();
            //loz=osobe.getString("ime").toLowerCase();
            korIme = "hr";
            loz = "hr";
            uloga = 2;

            query = "INSERT INTO NALOG (id_osoba, korisnicko_ime, lozinka, uloga) VALUES  (?,?,?,?)";
            insertToOsoba = connection.prepareStatement(query);
            insertToOsoba.setInt(1, idOsoba);
            insertToOsoba.setString(2, korIme);
            insertToOsoba.setString(3, loz);
            insertToOsoba.setInt(4, uloga);
            insertToOsoba.executeUpdate();


            //za nadredjenog


            String jmb = "";

            query = "select * from OSOBA where sef = 1";
            ResultSet sefovi = statement.executeQuery(query);
            uloga = 3;

            while (sefovi.next()) {
                jmb = sefovi.getString("jmbg");
                idOsoba = sefovi.getInt("id_osoba");
                korIme = sefovi.getString("ime").toLowerCase() + jmb.substring(0, 3);
                loz = sefovi.getString("ime").toLowerCase();

                query = "INSERT INTO NALOG (id_osoba, korisnicko_ime, lozinka, uloga) VALUES  (?,?,?,?)";
                insertToOsoba = connection.prepareStatement(query);

                insertToOsoba = connection.prepareStatement(query);
                insertToOsoba.setInt(1, idOsoba);
                insertToOsoba.setString(2, korIme);
                insertToOsoba.setString(3, loz);
                insertToOsoba.setInt(4, uloga);
                insertToOsoba.executeUpdate();
            }

            // za zaposlene

            jmb = "";

            query = "select * from OSOBA where sef = 0";
            ResultSet radnici = statement.executeQuery(query);
            uloga = 4;

            while (radnici.next()) {
                try {


                    jmb = radnici.getString("jmbg");
                    idOsoba = radnici.getInt("id_osoba");
                    korIme = radnici.getString("ime").toLowerCase() + jmb.substring(0, 3);
                    loz = radnici.getString("ime").toLowerCase();

                    query = "INSERT INTO NALOG (id_osoba, korisnicko_ime, lozinka, uloga) VALUES  (?,?,?,?)";
                    insertToOsoba = connection.prepareStatement(query);

                    insertToOsoba = connection.prepareStatement(query);
                    insertToOsoba.setInt(1, idOsoba);
                    insertToOsoba.setString(2, korIme);
                    insertToOsoba.setString(3, loz);
                    insertToOsoba.setInt(4, uloga);
                    insertToOsoba.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("duplikat");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void dodajProstorije(Connection connection) {

        try (Statement statement = connection.createStatement()) {

            String insertQuery = "INSERT INTO PROSTORIJA (port, kamera, naziv) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Generate 7 random rooms
            for (int i = 0; i < 7; i++) {
                String port = Utils.getPort();
                String kamera = Utils.getPort();
                String naziv = Utils.getProstorija();

                // Set values for the placeholders
                preparedStatement.setString(1, port);
                preparedStatement.setString(2, kamera);
                preparedStatement.setString(3, naziv);

                // Execute the prepared statement
                preparedStatement.executeUpdate();
            }

            // Close the prepared statement and connection
            preparedStatement.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}


//# id_smjena, naziv, pocetak, kraj