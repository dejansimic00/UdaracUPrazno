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
                odlazakNaPauzu(connection );

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    private static void odlazakNaPauzu(Connection connection, ArrayList<Integer> ids){
            ids.stream().forEach(id->{


                    String query = "SELECT * FROM evidencija_radnog_vremena.RADNO_VRIJEME "+
                    " where id_osoba=?"+
                    " order by id desc " +
                    "LIMIT 1;";

                    int resultId;
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setLong(1, id);
                    ResultSet result = statement.executeQuery();
                    result.getInt(1)

                } catch (SQLException e) {
                    e.printStackTrace();
                    // i--;
                }



                    String query = "update RADNO_VRIJEME " +
                            "set kraj=now() " +
                            "where id_osoba= ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setLong(1, id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    // i--;
                }
            });
    }



    private static void dolazakPrvaSmjena(Connection connection) {
        long idOsoba = 0;
        int id_posao = 1;
        long trenutnoVrijeme = 0;

        String query = "Select * from OSOBA limit 10";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet osobe = statement.executeQuery(query);

           while (osobe.next()){

               idOsoba = osobe.getInt("id_osoba");
                try {
                    query = "insert into RADNO_VRIJEME (id_osoba, id_posao, pocetak) VALUES (?,?,?)";
                    statement.setLong(1, idOsoba);
                    statement.setInt(2, id_posao);
                    statement.setLong(3, trenutnoVrijeme);


                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                   // i--;
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to insert data into OSOBA table!");
            e.printStackTrace();

        }
    }
}
