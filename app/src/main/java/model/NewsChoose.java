package model;

public class NewsChoose {
    private String _title;

    private  int _star;
    public String get_title() {
        return _title;
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

    public NewsChoose(String _title, int _star) {
        this._title = _title;

        this._star = _star;
    }
}
