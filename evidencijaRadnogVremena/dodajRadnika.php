<?php
require('DbConnect.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST['ime'])) {

        $id_osoba = rand(100000000, 999999999);
        $ime = $_POST['ime'];
        $prezime = $_POST['prezime'];
        $jmbg = $_POST['jmbg'];
        if($_POST['uloga'] == 2)
            $sef = 1;
        else
            $sef = 1;
        $uloga = $_POST['uloga'];
        $korisnicko_ime = $_POST['korisnicko_ime'];
        $lozinka = $_POST['lozinka'];
        if(isset($_POST['nadredjeni']))
            $nadredjeni = $_POST['nadredjeni'];
        else
            $nadredjeni = "";

        try {
            $db = new DbConnect();
            $dbConn = $db->connect();
            $sql = "INSERT INTO OSOBA (id_osoba, ime, prezime, jmbg, slika, sef) VALUES(:id_osoba, :ime, :prezime, :jmbg, :slika, :sef)";
            $stmt = $dbConn->prepare($sql);
            $stmt->bindParam(':id_osoba', $id_osoba);
            $stmt->bindParam(':ime', $ime);
            $stmt->bindParam(':prezime', $prezime);
            $stmt->bindParam(':jmbg', $jmbg);
            $s = "slikeRadnika/" . $id_osoba . ".jpg";
            $stmt->bindParam(':slika', $s);
            $stmt->bindParam(':sef', $sef);

            try {
                $stmt->execute();
                try {
                    $uploadDir = 'slikeRadnika/';
                    if (!file_exists($uploadDir)) {
                        mkdir($uploadDir, 0777, true);
                    }
                    $imagePaths = [];
                    foreach ($_FILES as $key => $file) {
                        $targetFile = $uploadDir . basename($id_osoba.".jpg");
                        if (move_uploaded_file($file["tmp_name"], $targetFile)) {
                            $sql = "INSERT INTO NALOG (id_osoba, korisnicko_ime, lozinka, uloga) VALUES(:id_osoba, :korisnicko_ime, :lozinka, :uloga)";
                            $stmt = $dbConn->prepare($sql);
                            $stmt->bindParam(':id_osoba', $id_osoba);
                            $stmt->bindParam(':korisnicko_ime', $korisnicko_ime);
                            $stmt->bindParam(':lozinka', $lozinka);
                            $stmt->bindParam(':uloga', $uloga);
                            try {
                                $stmt->execute();
                                if($nadredjeni != ""){
                                    $sql = "INSERT INTO SEF (id_osoba, id_sef) VALUES(:id_osoba, :id_sef)";
                                    $stmt = $dbConn->prepare($sql);
                                    $stmt->bindParam(':id_osoba', $id_osoba);
                                    $stmt->bindParam(':id_sef', $nadredjeni);
                                    try {
                                        $stmt->execute();
                                        http_response_code(200);
                                        echo json_encode(array("success" => true, "id_osoba" => $id_osoba));
                                    } catch (Exception $ex) {
                                        echo $ex->getMessage();
                                        http_response_code(400);
                                        echo json_encode(array("success" => false, "message" => "This email is already in use!59"));
                                    }
                                }
                                else{
                                    http_response_code(200);
                                    echo json_encode(array("success" => true, "id_osoba" => $id_osoba));
                                }
                            } catch (Exception $ex) {
                                echo $ex->getMessage();
                                http_response_code(400);
                                echo json_encode(array("success" => false, "message" => "This email is already in use!64"));
                            }
                        } else {
                            http_response_code(500);
                            echo "Failed to upload file.";
                        }
                    }
                } catch (Exception $ex) {
                    http_response_code(400);
                    echo json_encode(array("success" => false, "message" => "This email is already in use!73"));
                }
            } catch (Exception $ex) {
                echo $ex->getMessage();
                http_response_code(400);
                echo json_encode(array("success" => false, "message" => "This email is already in use!77"));
            }
        } catch (Error $ex) {

            echo $ex->getMessage() . "\n";
            http_response_code(500);
            echo json_encode(array("success" => false, "message" => "Failed to connect to database!"));
        }
    } else {
        http_response_code(500);
        echo json_encode(array("success" => false, "message" => "Failed to add new user"));
    }
} else {
    http_response_code(400);
    echo json_encode(array("success" => false, "message" => "Parameter(s) is missing"));
}
