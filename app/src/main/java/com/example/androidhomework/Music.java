package com.example.androidhomework;

public class Music {
    private String musicTitle;
    private String singerName;

    public String getMusicTitle() {

        return musicTitle;
    }

    public String getSingerName() {

        return singerName;
    }

    public Music(String musicTitle, String singerName) {
        this.musicTitle = musicTitle;
        this.singerName = singerName;
    }
}
