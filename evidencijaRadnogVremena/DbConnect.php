<?php 

	class DbConnect {
		private $server = '192.168.20.249:3306';
		private $dbname = 'evidencija_radnog_vremena';
		private $user = 'hack';
		private $pass = 'hack';

		public function connect() {
			try {
				$conn = new PDO('mysql:host=' .$this->server .';dbname=' . $this->dbname, $this->user, $this->pass);
				$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
				return $conn;
			} catch (Exception $e) {
                echo $e->getMessage();
				return $e;
			}
		}
	}
 ?>