package org.example;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;


public class Simulation {


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(Utils.JDBCURL, "hack", "hack")) {
            if (connection != null) {
                System.out.println("Connected to the database!");
                //////////////////////////////
                //generateData(connection);
                //dolazakPrvaSmjena(connection);
                //odlazakNaPauzu(connection );
                //dolazakSaPauze(connection);
                odlazakSaPosla(connection, 2, 5);

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    private static void odlazakSaPosla(Connection connection, int people, long timeAdvance) {
        int idRadnogVremena=0;
        long trenutnoVrijeme = 0;

        String query = "SELECT * FROM RADNO_VRIJEME where kraj is null limit " +people + ";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {


            ResultSet vrijeme = statement.executeQuery(query);

            while (vrijeme.next()){
                idRadnogVremena = vrijeme.getInt("id");
                query = "update RADNO_VRIJEME set kraj =  ?, aktivnost = 'odjava' where id = ?";
                trenutnoVrijeme = System.currentTimeMillis();

                try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                    long odlazakSaPosla = System.currentTimeMillis() + timeAdvance*1_000_000;
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(odlazakSaPosla);
                    statement2.setTimestamp(1, timestamp);
                    statement2.setInt(2, idRadnogVremena);


                    statement2.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Failed to insert data into OSOBA table!");
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            System.err.println("Failed to insert data into OSOBA table!");
            e.printStackTrace();

        }
    }

    private static void dolazakSaPauze(Connection connection,int people, int timeAdvance) {
        int idRadnogVremena=0;
        long trenutnoVrijeme = 0;

        String query = "SELECT * FROM RADNO_VRIJEME where aktivnost = 'na pauzi' limit " + people +";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){

                    query = "insert into RADNO_VRIJEME (id_osoba, aktivnost, pocetak) VALUES (?,?,?)";
                    trenutnoVrijeme = System.currentTimeMillis();
                    int idOsoba = resultSet.getInt("id_osoba");
                    try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                        statement2.setLong(1, idOsoba);
                        statement2.setString(2, "radi");
                        long currentTimeMillis = System.currentTimeMillis() + timeAdvance*1_000_000;


                        // Convert milliseconds to a Timestamp
                        java.sql.Timestamp timestamp = new java.sql.Timestamp(currentTimeMillis);

                        // Set the Timestamp as the third parameter in the PreparedStatement
                        statement2.setTimestamp(3, timestamp);

                        statement2.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Failed to insert data into OSOBA table!");
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            System.err.println("Failed to insert data into OSOBA table!");
            e.printStackTrace();

        }
    }

    private static void odlazakNaPauzu(Connection connection, int people, int timeAdvance){
        int idRadnogVremena=0;
        long trenutnoVrijeme = 0;

        String query = "SELECT * FROM RADNO_VRIJEME where kraj is null limit "+ people+";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet vrijeme = statement.executeQuery(query);

            while (vrijeme.next()){
                idRadnogVremena = vrijeme.getInt("id");
                query = "update RADNO_VRIJEME set kraj =  ?, aktivnost = 'na pauzi' where id = ?";
                trenutnoVrijeme = System.currentTimeMillis();

                try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                    trenutnoVrijeme = System.currentTimeMillis() + 1_000_000;
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(trenutnoVrijeme);
                    statement2.setTimestamp(1, timestamp);
                    statement2.setInt(2, idRadnogVremena);


                    statement2.executeUpdate();
                } catch (SQLException e) {
                    System.err.println("Failed to insert data into OSOBA table!");
                    e.printStackTrace();
                }

            }
        } catch (SQLException e) {
            System.err.println("Failed to insert data into OSOBA table!");
            e.printStackTrace();

        }
    }



    private static void dolazakPrvaSmjena(Connection connection, int people) {
        long idOsoba = 0;
        int aktivnost = 1;
        long trenutnoVrijeme = 0;

        String query = "Select * from OSOBA limit "+people+";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet osobe = statement.executeQuery(query);

           while (osobe.next()){

               query = "insert into RADNO_VRIJEME (id_osoba, aktivnost, pocetak) VALUES (?,?,?)";
               trenutnoVrijeme = System.currentTimeMillis();
               idOsoba = osobe.getInt("id_osoba");
               try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                   statement2.setLong(1, idOsoba);
                   statement2.setInt(2, aktivnost);
                   long currentTimeMillis = System.currentTimeMillis();

                   // Convert milliseconds to a Timestamp
                   java.sql.Timestamp timestamp = new java.sql.Timestamp(currentTimeMillis);

                   // Set the Timestamp as the third parameter in the PreparedStatement
                   statement2.setTimestamp(3, timestamp);

                   statement2.executeUpdate();
               } catch (SQLException e) {
                   System.err.println("Failed to insert data into OSOBA table!");
                   e.printStackTrace();
               }

            }
        } catch (SQLException e) {
            System.err.println("Failed to insert data into OSOBA table!");
            e.printStackTrace();

        }
    }
}
