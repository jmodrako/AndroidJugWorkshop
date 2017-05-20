package pl.jug.bydgoszcz.androidjugworkshop;

public class JugPostModel {
    // will be set later
    private JugUserModel user;

    private long userId;
    private long id;
    private String title;
    private String body;

    // TODO For tests.
    public String imageUrl() {
        return "http://www.dfhtechnologies.com/images/user.png";
    }

    public long getUserId() {
        return userId;
    }

    public void setUser(JugUserModel user) {
        this.user = user;
    }

    public JugUserModel getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "user=" + user +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
