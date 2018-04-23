package model;

public class NewsChoose {
    private String _title;
    private  byte[]  _image;
    private  int _star;
    public String get_title() {
        return _title;
    }

    public byte[] get_image() {
        return _image;
    }

    public void set_image(byte[] _image) {
        this._image = _image;
    }

    public int get_star() {
        return _star;
    }

    public void set_star(int _star) {
        this._star = _star;
    }

    public void set_title(String _title) {
        this._title = _title;
    }



    public NewsChoose() {

    }

    public NewsChoose(String _title, byte[] _image, int _star) {
        this._title = _title;
        this._image = _image;
        this._star = _star;
    }
}
