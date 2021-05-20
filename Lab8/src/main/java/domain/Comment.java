package domain;

public class Comment {
    private int id;
    private String comment;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }

    public Comment(int id, String comment, User user) {
        this.id = id;
        this.comment = comment;
        this.user = user;
    }
}
