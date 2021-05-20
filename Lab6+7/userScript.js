let currentUser = 'taveeh';
let currentPage = 0;
let field = 'basic';
let severityField = 'Error';
let typeField;
const insertData = (newBody, data) => {
    console.log(data);
    let result = JSON.parse(data);
    console.log(result);
    for (let log of result) {
        let newRow = newBody.insertRow();
        if (result.indexOf(log) >= 4 * currentPage) {
            for (let index of ['ID', 'Type', 'Severity', 'Date', 'Username', 'Log']) {
                let newCol = newRow.insertCell();
                let newText = document.createTextNode(log[index]);
                newCol.appendChild(newText);
            }
            newBody.append(newRow);
        }
        if (result.indexOf(log) >= 4 * currentPage + 3) {
            break;
        }
    }
}
const showLogReports = () => {
    let body = $('.logTable tbody').eq(0);
    let newBody = document.createElement('tbody');
    $.ajax({
        type: 'GET',
        url: "http://localhost/Lab6/Controller.php",
        data: {action: 'getAllLogs'},
        success: (data) => {
            insertData(newBody, data)
        }
    })
    body.replaceWith(newBody);
}
//, user: currentUser
const showLogsByUser = () => {
    let body = $('.logTable tbody').eq(0);
    let newBody = document.createElement('tbody');
    $.ajax({
        type: 'GET',
        url: "http://localhost/Lab6/Controller.php",
        data: {action: 'getLogsByUser'},
        success: (data) => {
            insertData(newBody, data);
        }
    })
    body.replaceWith(newBody);
}

const showLogsBySeverity = (severity) => {
    let body = $('.logTable tbody').eq(0);
    let newBody = document.createElement('tbody');
    $.ajax({
        type: 'GET',
        url: "http://localhost/Lab6/Controller.php",
        data: {action: 'getLogsBySeverity', severity: severity},
        success: (data) => {
            insertData(newBody, data);
        }
    })
    body.replaceWith(newBody);
}

const showLogsByType = (type) => {
    let body = $('.logTable tbody').eq(0);
    let newBody = document.createElement('tbody');
    $.ajax({
        type: 'GET',
        url: "http://localhost/Lab6/Controller.php",
        data: {action: 'getLogsByType', type: type},
        success: (data) => {
            insertData(newBody, data);
        }
    })
    body.replaceWith(newBody);
}
const getCorrectLogs = () => {
    switch (field) {
        case 'basic':
            showLogReports();
            break;
        case 'user':
            showLogsByUser();
            break;
        case 'severity':
            showLogsBySeverity(severityField);
            break;
        case 'type':
            showLogsByType(typeField);
            break;
    }
}
$(document).ready(() => {
    // let index = document.URL.indexOf('?');
    // currentUser = document.URL.substring(index + 'user='.length + 1).replaceAll(' ', '');
    showLogReports();

    $('#filterBySeverityButton').click(() => {
        currentPage = 0;
        field = 'severity';
        severityField = $('#severityInputFilter').val();
        getCorrectLogs();
    });

    $('#filterByUser').click(() => {
        currentPage = 0;
        field = 'user';
        getCorrectLogs();
    })

    $('#allLogsButton').click(() => {
        currentPage = 0;
        field = 'basic';
        getCorrectLogs();
    })

    $('#nextButton').click(() => {
        currentPage++;
        getCorrectLogs();
    })

    $('#previousButton').click(() => {
        if (currentPage > 0) {
            currentPage--;
        }
        getCorrectLogs();
    })

    $('#filterByTypeButton').click(() => {
       currentPage = 0;
       field = 'type';
       typeField = $('#typeInputFilter').val();
       getCorrectLogs();
    });

    $('#insertLogButton').click(() => {
        let type = $('#typeField').val();
        let severity = $('#severityField').val();
        let date = $('#dateField').val();
        let log = $('#logField').val();
        console.log([type, severity, currentUser, date, log]);
        $.ajax({
            type: "GET",
            url: "http://localhost/Lab6/Controller.php",
            data: {
                action: 'insertLog',
                logType: type,
                severity: severity,
                user: currentUser,
                date: date,
                log: log
            },
            success: (data) => {
                let res = JSON.parse(data);
                if (res === 0) {
                    alert("Log could not be added");
                } else {
                    getCorrectLogs();
                }
            }
        })
    });

    $('#removeLogButton').click(() => {
        let id = Number($('#idField').val());
        $.ajax({
            type: "GET",
            url: "http://localhost/Lab6/Controller.php",
            data: {
                action: 'deleteLog',
                id: id,
                user: currentUser
            },
            success: (data) => {
                let res = JSON.parse(data);
                console.log("Remove: " + res);
                if (res === 0) {
                    alert("The Log you want to remove does not exits or it is not yours");
                } else {
                    getCorrectLogs();
                }
            }
        })
    })
});
