<?php

require_once 'DatabaseUtils.php';

class Repository {
    private DatabaseUtils $database;

    public function __construct() {
        $this->database = new DatabaseUtils();
    }

    public function getAllLogs(): array {
        return $this->database->selectAllLogs();
    }

    public function getLogsBySeverity($severity): array {
        return $this->database->selectLogsBySeverity($severity);
    }

    public function getLogsByUser($username): array {
        return $this->database->selectLogsByUser($username);
    }

    public function insertLog(LogReport $log) {
        return $this->database->insertLog($log->getType(), $log->getSeverity(), $log->getDate(), $log->getUser(), $log->getLog());
    }

    public function deleteLog(int $id, string $user) {
        return $this->database->deleteLog($id, $user);
    }

    public function getUser(string $user): array {
        return $this->database->getUserByName($user);
    }

    public function getLogsByType(string $type): array {
        return $this->database->getLogsByType($type);
    }

}