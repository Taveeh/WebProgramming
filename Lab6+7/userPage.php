<?php
    session_start();
    if (!isset($_SESSION['username'])) {
        header('Location: index.html');
    }
    $txt = sprintf("The user is: %s", $_SESSION['username']);
    echo $txt;
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log Reports</title>
    <link rel="stylesheet" href="style.css">
    <script src="jquery-2.0.3.js"></script>
    <script src="userScript.js" type="text/javascript"></script>
</head>

<body>
<div id="showLogs">
    <table class="logTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Severity</th>
            <th>Date</th>
            <th>Username</th>
            <th>Log</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<div id="buttons">
    <div id="previousButton">Previous</div>
    <div id="filterByUser">Show my logs</div>
    <div id="filterBySeverity">
        <label for="severityInputFilter">Severity: </label><input type="text" id="severityInputFilter">
        <div id="filterBySeverityButton">Show by severity</div>
    </div>
    <div id="filterByType">
        <label for="typeInputFilter">Type: </label><input type="text" id="typeInputFilter">
        <div id="filterByTypeButton">Show by type</div>
    </div>

    <div id="allLogsButton">Show All</div>
    <div id="nextButton">Next</div>
</div>

<div id="form">
    <div id="addForm">
        <div><label for="typeField">Type: </label><input type="text" id="typeField"></div>
        <div><label for="severityField">Severity: </label><input type="text" id="severityField"></div>
        <div><label for="dateField">Date: </label><input type="date" id="dateField"></div>
        <div><label for="logField">Log: </label><input type="text" id="logField"></div>
        <div id="insertLogButton">Add Log</div>
    </div>
    <div id="removeForm">
        <div><label for="idField">Log id: </label><input type="number" id="idField"></div>
        <div id="removeLogButton">Remove Log</div>
    </div>
</div>


</body>
</html>