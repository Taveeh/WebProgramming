function deleteComment(commentId) {
    if (confirm("Are you sure you want to delete your comment?")) {
        console.log(commentId)
        $.ajax({
            url: 'delete',
            method: 'post',
            data: {
                'commentId': commentId
            },
            success: function () {
                location.reload()
            }
        })
    }
}


function addComment() {
    console.log(document.getElementById('commentInput').value)
    $.ajax({
        url: 'comment',
        method: 'post',
        data: {
            'commentText': document.getElementById('commentInput').value
        },
        success: function () {
            location.reload()
        }
    })
}
