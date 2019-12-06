package com.yanbo.videodlnascreen;

/**
 * NeverMore
 * 2019/12/6
 **/
public class EntityVideo {
    private String thumbPath;
    private String path;
    private long duration;
    private String name;
    private String id;
    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
