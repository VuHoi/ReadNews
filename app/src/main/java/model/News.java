package model;

public class News {
    String title, description;
    String image;
    String link;
    boolean status;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public News(String title, String description, String image, String link, boolean status) {

        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.status = status;
    }

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
