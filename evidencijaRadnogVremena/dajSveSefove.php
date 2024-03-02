<?php
require('DbConnect.php');

$sql = "SELECT * from OSOBA join NALOG on NALOG.id_osoba = OSOBA.id_osoba join SEF on SEF.id_sef = OSOBA.id_osoba";

$db = new DbConnect();
$dbConn = $db->connect();
$stmt = $dbConn->prepare($sql);

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
