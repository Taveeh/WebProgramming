package domain;

import java.util.List;

public class Post {
    private int id;
    private String postContent;
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postContent='" + postContent + '\'' +
                ", comments=" + comments +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post(int id, String postContent, List<Comment> comments) {
        this.id = id;
        this.postContent = postContent;
        this.comments = comments;
    }
}
