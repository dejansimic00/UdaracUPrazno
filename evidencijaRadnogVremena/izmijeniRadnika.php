<?php
require('DbConnect.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['id_osoba'])) {
        $id_osoba = $_POST['id_osoba'];
        $ime = isset($_POST['ime']) ? $_POST['ime'] : null;
        $prezime = isset($_POST['prezime']) ? $_POST['prezime'] : null;
        $jmbg = isset($_POST['jmbg']) ? $_POST['jmbg'] : null;
        $sef = isset($_POST['sef']) ? $_POST['sef'] : null;
        $uloga = isset($_POST['uloga']) ? $_POST['uloga'] : null;
        $korisnicko_ime = isset($_POST['korisnicko_ime']) ? $_POST['korisnicko_ime'] : null;
        $lozinka = isset($_POST['lozinka']) ? $_POST['lozinka'] : null;
        $nadredjeni = isset($_POST['nadredjeni']) ? $_POST['nadredjeni'] : null;

        try {
            $db = new DbConnect();
            $dbConn = $db->connect();
            $sql = "UPDATE OSOBA SET";
            $params = [];
            if ($ime !== null) {
                $sql .= " ime = :ime,";
                $params[':ime'] = $ime;
            }
            if ($prezime !== null) {
                $sql .= " prezime = :prezime,";
                $params[':prezime'] = $prezime;
            }
            if ($jmbg !== null) {
                $sql .= " jmbg = :jmbg,";
                $params[':jmbg'] = $jmbg;
            }
            if ($sef !== null) {
                $sql .= " sef = :sef,";
                $params[':sef'] = $sef;
            }
            $sql = rtrim($sql, ',');
            $sql .= " WHERE id_osoba = :id_osoba";
            $params[':id_osoba'] = $id_osoba;

            $stmt = $dbConn->prepare($sql);
            foreach ($params as $key => &$value) {
                $stmt->bindParam($key, $value);
            }

            try {
                $stmt->execute();

                if ($korisnicko_ime !== null || $lozinka !== null) {
                    $sql = "UPDATE NALOG SET";
                    $params = [];
                    if ($korisnicko_ime !== null) {
                        $sql .= " korisnicko_ime = :korisnicko_ime,";
                        $params[':korisnicko_ime'] = $korisnicko_ime;
                    }
                    if ($lozinka !== null) {
                        $sql .= " lozinka = :lozinka,";
                        $params[':lozinka'] = $lozinka;
                    }
                    $sql = rtrim($sql, ',');
                    $sql .= " WHERE id_osoba = :id_osoba";
                    $params[':id_osoba'] = $id_osoba;

                    $stmt = $dbConn->prepare($sql);
                    foreach ($params as $key => &$value) {
                        $stmt->bindParam($key, $value);
                    }

                    $stmt->execute();
                }

                if ($nadredjeni !== null) {
                    $sql = "UPDATE SEF SET id_sef = :id_sef WHERE id_osoba = :id_osoba";
                    $stmt = $dbConn->prepare($sql);
                    $stmt->bindParam(':id_osoba', $id_osoba);
                    $stmt->bindParam(':id_sef', $nadredjeni);
                    $stmt->execute();
                }

                http_response_code(200);
                echo json_encode(array("success" => true));
            } catch (Exception $ex) {
                echo $ex->getMessage();
                http_response_code(400);
                echo json_encode(array("success" => false, "message" => "Failed to update user!"));
            }

        } catch (Error $ex) {
            echo $ex->getMessage() . "\n";
            http_response_code(500);
            echo json_encode(array("success" => false, "message" => "Failed to connect to database!"));
        }
    } else {
        http_response_code(400);
        echo json_encode(array("success" => false, "message" => "User ID is missing"));
    }
} else {
    http_response_code(400);
    echo json_encode(array("success" => false, "message" => "Invalid request method"));
}
?>
