package org.example;

import javax.xml.transform.Result;
import java.sql.*;


public class Simulation {


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(Utils.JDBCURL, "root", "Tesl499")) {
            if (connection != null) {
                System.out.println("Connected to the database!");
                //////////////////////////////
                //generateData(connection);
                dolazakPrvaSmjena(connection);


            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    private static void dolazakPrvaSmjena(Connection connection) {
        long idOsoba = 0;
        int id_posao = 1;
        long trenutnoVrijeme = 0;

        String query = "Select * from OSOBA limit 10";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet osobe = statement.executeQuery(query);

           while (osobe.next()){

               query = "insert into RADNO_VRIJEME (id_osoba, id_posao, pocetak) VALUES (?,?,?)";
               trenutnoVrijeme = System.currentTimeMillis();
               idOsoba = osobe.getInt("id_osoba");
               try (PreparedStatement statement2 = connection.prepareStatement(query)) {
                   statement2.setLong(1, idOsoba);
                   statement2.setInt(2, id_posao);
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
