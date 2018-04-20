package model;

public class News {
    String title, description;
    int image;
    boolean status;

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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public News(String title, String description, int image, boolean status) {

        this.title = title;
        this.description = description;
        this.image = image;
        this.status = status;
    }
}
