package model;

public class NewsChoose {
    private String _title;
    private int   _image;
    private  boolean _star;
    public String get_title() {
        return _title;
    }

    public boolean is_star() {
        return _star;
    }

    public void set_star(boolean _star) {
        this._star = _star;
    }

    public void set_title(String _title) {
        this._title = _title;
    }


    public int get_image() {
        return _image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public NewsChoose() {

    }

    public NewsChoose(String _title, boolean _star, int _image) {

        this._title = _title;
        this._star = _star;
        this._image = _image;
    }


}
