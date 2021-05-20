<?php


use Cassandra\Date;

class LogReport implements JsonSerializable {
    private string $type;
    private string $severity;
    private string $date;
    private string $user;
    private string $log;
    /**
     * LogReport constructor.
     * @param $type
     * @param $severity
     * @param $date
     * @param $user
     * @param $log
     */
    public function __construct($type, $severity, $date, $user, $log) {
        $this->type = $type;
        $this->severity = $severity;
        $this->date = $date;
        $this->user = $user;
        $this->log = $log;
    }

    /**
     * @return string
     */
    public function getType(): string {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType(string $type): void {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getSeverity(): string {
        return $this->severity;
    }

    /**
     * @param string $severity
     */
    public function setSeverity(string $severity): void {
        $this->severity = $severity;
    }

    /**
     * @return string
     */
    public function getDate(): string {
        return $this->date;
    }

    /**
     * @param string $date
     */
    public function setDate(string $date): void {
        $this->date = $date;
    }

    /**
     * @return string
     */
    public function getUser(): string {
        return $this->user;
    }

    /**
     * @param string $user
     */
    public function setUser(string $user): void {
        $this->user = $user;
    }

    /**
     * @return string
     */
    public function getLog(): string {
        return $this->log;
    }

    /**
     * @param string $log
     */
    public function setLog(string $log): void {
        $this->log = $log;
    }


    public function jsonSerialize() {
        return get_object_vars($this);
    }
}