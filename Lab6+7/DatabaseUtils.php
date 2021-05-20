<?php
class DatabaseUtils {
    private string $host = '127.0.0.1';
    private string $database = 'LogReports';
    private string $user = 'root';
    private string $password = '';
    private string $charset = 'utf8';

    private PDO $pdo;
    private string $error;

    public function __construct() {
        $dsn = "mysql:host=$this->host;dbname=$this->database;charset=$this->charset";
        $opt = array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES => false);
        try {
            $this->pdo = new PDO($dsn, $this->user, $this->password, $opt);
        }
        catch (PDOException $e) {
            $this->error = $e->getMessage();
            echo "Error connecting to DB: " . $this->error;
        }
    }

    public function selectAllLogs(): array {
        $statement = $this->pdo->query("SELECT * FROM Logs");
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }

    public function selectLogsBySeverity($severity): array {
        $statement = $this->pdo->query("SELECT * FROM Logs L WHERE L.Severity = '$severity'");
        return $statement->fetchAll(PDO::FETCH_ASSOC);

    }

    public function selectLogsByUser($username): array {
        $statement = $this->pdo->query("SELECT * FROM Logs L WHERE L.Username = '$username'");
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }

    public function insertLog($type, $severity, $date, $username, $log) {
        return $this->pdo->exec("INSERT INTO Logs(Type, Severity, Date, Username, Log) VALUES ('$type', '$severity', '$date', '$username', '$log')");
    }

    public function deleteLog($id, $user) {
        return $this->pdo->exec("DELETE FROM Logs WHERE ID = ".$id. " AND Username = '$user'");
    }

    public function getUserByName($name): array {
        $statement = $this->pdo->query("SELECT * FROM Users U WHERE U.UserName = '$name'");
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }

    public function getLogsByType($type): array {
        $statement = $this->pdo->query("SELECT * FROM Logs L WHERE L.Type = '$type'");
        return $statement->fetchAll(PDO::FETCH_ASSOC);
    }
}
