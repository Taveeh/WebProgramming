
function goToPost(value) {
    if (value !== '') {
        document.cookie = `postId=${value}`;
        $(location).attr("href", "post");
    } else {
        alert("Invalid post");
    }
}

function addPost() {
    let value = document.getElementById("topic").value
    $.ajax({
        url: 'addPost',
        method: 'post',
        data: {
            postContent: value
        },
        success: function () {
            location.reload();
        }
    })
}

function logout() {
    if (confirm("Want to log out?")) {
        $.ajax({
            url: 'logout',
            method: 'get',
            success: function () {
                $(location).attr("href", "login")
            }
        })
    }
}
