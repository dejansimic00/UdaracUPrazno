<?php
require('DbConnect.php');

if (isset($_GET['user_id'])) {
    $user_id = $_GET['user_id'];
    $sql = "SELECT * from OSOBA join NALOG on NALOG.id_osoba = OSOBA.id_osoba join SEF on SEF.id_osoba = OSOBA.id_osoba where SEF.id_sef = :user_id";
    try{
        $db = new DbConnect();
        $dbConn = $db->connect();
        $stmt = $dbConn->prepare($sql);
        $stmt->bindParam(':user_id', $user_id);
    
        if ($stmt->execute()) {
            $coins = $stmt->fetchAll(PDO::FETCH_ASSOC);
    
            if ($coins) {
                header('Content-Type: application/json');
                http_response_code(200);
                echo json_encode($coins);
            } else {
                http_response_code(200);
                echo json_encode($coins);
            }
        } else {
            http_response_code(500);
            echo "Error fetching coins.";
        }
    } catch (Error $ex) {

        echo $ex->getMessage() . "\n";
        http_response_code(500);
        echo json_encode(array("success" => false, "message" => "Failed to connect to database!"));
    }
} else {
    http_response_code(400);
    echo "user_id parameter is missing.";
}
