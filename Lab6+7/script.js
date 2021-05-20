
$('#loginButton').click(() => {
    let username = $('#username').val();
    let password = $('#password').val();
    $.ajax({
        type: 'GET',
        url: "http://localhost/Lab6/Controller.php",
        data: {user: username, pass: password ,action: 'checkValidPassword'},
        success: function (data) {
            let result = JSON.parse(data);
            console.log(result);
            if (result === false) {
                alert("Wrong combination of username and password");
            } else {
                location.href = "userPage.php?user=" + username;
            }
        }
    })
})