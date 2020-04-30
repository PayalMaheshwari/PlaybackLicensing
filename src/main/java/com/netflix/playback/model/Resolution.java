package com.netflix.playback.model;

public class Resolution implements Comparable<Resolution> {

    private Integer length;
    private Integer width;

    public Resolution(Integer length, Integer width) {
        this.length = length;
        this.width = width;
    }

    public Resolution() {
    }

    @Override
    public int compareTo(Resolution compareWith) {
        int result =  this.length.compareTo(compareWith.length);
        if(result != 0){
            return result;
        }
        return this.width.compareTo(compareWith.width);
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
