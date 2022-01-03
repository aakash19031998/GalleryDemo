package com.example.myapplication;

public class GalleryModel  {

    String thumb;
    String regular;

    public GalleryModel() {
    }

    public GalleryModel(String thumb, String regular) {
        this.thumb = thumb;
        this.regular = regular;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
