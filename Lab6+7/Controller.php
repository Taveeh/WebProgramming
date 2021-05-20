<?php
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 01 Jan 1996 00:00:00 GMT');

header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Credentials');
header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

require_once 'LogReport.php';
require_once 'Repository.php';
class Controller {

    private Repository $repository;
    private function show($value) {
        echo json_encode($value);
    }


    public function __construct() {
        $this->repository = new Repository();
    }

    public function run() {
        if (isset($_GET['action']) && !empty($_GET['action'])) {
            switch ($_GET['action']) {
                case 'checkValidPassword':
                    session_start();
                    $user = $_GET['user'];
                    $pass = $_GET['pass'];
                    $this->checkValidPassword($user, $pass);
                    break;
                case 'getAllLogs':
                    session_start();
                    $this->getAllLogs();
                    break;
                case 'getLogsByUser':
                    session_id($_GET['sessionId']);
                    session_start();
                    $user = $_SESSION['username'];
                    $this->getLogsByUser($user);
                    break;
                case 'getLogsBySeverity':
                    session_start();
                    $severity = $_GET['severity'];
                    $this->getLogsBySeverity($severity);
                    break;
                case 'insertLog':
                    session_id($_GET['sessionId']);
                    session_start();

                    $type = $_GET['logType'];
                    $severity = $_GET['severity'];
                    $user = $_SESSION['username'];
                    $date = $_GET['date'];
                    $log = $_GET['log'];
                    $this->insertLog($type, $severity, $date, $user, $log);
                    break;
                case 'deleteLog':
                    session_id($_GET['sessionId']);
                    session_start();
                    $id = $_GET["id"];
                    $user = $_SESSION['username'];
                    $this->deleteLog($id, $user);
                    break;
                case 'getLogsByType':
                    session_start();
                    $type = $_GET["type"];
                    $this->getLogsByType($type);
                    break;
                case 'login':
                    $this->createUserSession();
                    break;
                case 'checkUserSet':
                    session_id($_GET['sessionId']);
                    session_start();
                    $this->checkUserSet();
                    break;
            }
        }
    }
    public function checkValidPassword(string $username, string $password): bool {
        $result = $this->repository->getUser($username);
        if (count($result) == 0) {
            return false;
        } else {
            if ($result[0]["Password"] === $password) {
                return true;
            } else {
                return false;
            }
        }
    }

    private function getAllLogs() {
        $result = $this->repository->getAllLogs();
        $this->show($result);
    }

    private function getLogsByUser(string $username) {
        $this->show($this->repository->getLogsByUser($username));
    }

    private function getLogsBySeverity(string $severity) {
        $this->show($this->repository->getLogsBySeverity($severity));
    }
    private function insertLog(string $type, string $severity, string $date, string $user, string $log) {
        $this->show($this->repository->insertLog(new LogReport($type, $severity, $date, $user, $log)));
    }

    private function deleteLog(int $id, string $user) {
        $this->show($this->repository->deleteLog($id, $user));
    }
    private function getLogsByType(string $type) {
        $this->show($this->repository->getLogsByType($type));
    }

    private function createUserSession() {
        session_start();
        $errors = 0;
        $fields = array("username", "password");
        if (isset($_GET['loginButton'])) {
            foreach ($fields as $key => $field) {
                if (!isset($_GET[$field]) && empty($_GET[$field])) {
                    echo "please enter username and password\n";
                    $errors++;
                }
            }
        }

        if ($errors <= 0) {
            $password = $_GET['password'];
            $username = $_GET['username'];
            $controller = new Controller();
            if ($controller->checkValidPassword($username, $password)) {
                $_SESSION['username'] = $username;
                $this->show(session_id());
            }else {
                $this->show(false);
            }
        } else {
            $this->show("You have ".$errors);
        }
    }

    private function checkUserSet() {
        $this->show(isset($_SESSION['username']));
    }
}
$controller = new Controller();
$controller->run();
