<?php
require('DbConnect.php');

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    
    if (isset($_GET['korisnicko_ime']) && isset($_GET['lozinka'])) {
        
        if($_GET['korisnicko_ime'] == "")
            echo false;
        else if($_GET['lozinka'] == "")
            echo false;
        $korisnicko_ime = $_GET['korisnicko_ime'];
        $lozinka = $_GET['lozinka'];
        
        $db = new DbConnect();
        $conn = $db->connect();
        $stmt = $conn->prepare("SELECT * FROM NALOG WHERE korisnicko_ime = :korisnicko_ime AND lozinka = :lozinka");
        $stmt->bindParam(":korisnicko_ime", $korisnicko_ime);
        $stmt->bindParam(":lozinka", $lozinka);
        $stmt->execute();
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        if ($user) {
            http_response_code(200);
            echo json_encode(array("id_osoba" => $user['id_osoba'], "uloga" => $user['uloga']));
        } else {
            http_response_code(400);
            echo 0;
        }
    } else {
        http_response_code(400);
        echo "Required fields are missing.";
    }
} else {
    http_response_code(400);
    echo "Method not allowed.";
}
?>
