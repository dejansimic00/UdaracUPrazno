<?php
require('DbConnect.php');

if ($_SERVER["REQUEST_METHOD"] == "GET") {

    if (isset($_GET['id_osoba']) && isset($_GET['id_prostorija'])) {

        if ($_GET['id_osoba'] == "")
            echo false;
        else if ($_GET['id_prostorija'] == "")
            echo false;
        $id_osoba = $_GET['id_osoba'];
        $id_prostorija = $_GET['id_prostorija'];
        $db = new DbConnect();
        $conn = $db->connect();
        $stmt = $conn->prepare("SELECT * FROM OSOBA_PROSTORIJA WHERE id_osoba = :id_osoba AND id_prostorija = :id_prostorija");
        $stmt->bindParam(":id_osoba", $id_osoba);
        $stmt->bindParam(":id_prostorija", $id_prostorija);
        $stmt->execute();
        $user = $stmt->fetch(PDO::FETCH_ASSOC);
        if ($user) {
            $conn = $db->connect();
            $stmt = $conn->prepare("SELECT slika FROM OSOBA WHERE id_osoba = :id_osoba");
            $stmt->bindParam(":id_osoba", $id_osoba);
            $stmt->execute();
            $user = $stmt->fetch(PDO::FETCH_ASSOC);
            if ($user) {
                if (file_exists($user['slika'])) {
                    header('Content-Type: image/jpeg');
                    header('Content-Length: ' . filesize($user['slika']));
                    http_response_code(200);
                    readfile($user['slika']);
                }
            }
        } else {
            http_response_code(400);
            echo false;
        }
    } else {
        http_response_code(400);
        echo "Required fields are missing.";
    }
} else {
    http_response_code(400);
    echo "Method not allowed.";
}
