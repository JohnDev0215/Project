package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class TrackRecordBean {
    private long album_id;
    private String album_title;
    private int content_type;
    private double duration;
    private long ended_at;
    private long id;
    private String imgUrl;
    private String play_source;
    private int play_type;
    private double played_secs;
    private String rec_src;
    private String rec_track;
    private long started_at;
    private int totalDuration;
    private long track_id;
    private String track_title;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getTrack_id() {
        return this.track_id;
    }

    public void setTrack_id(long j) {
        this.track_id = j;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double d) {
        this.duration = d;
    }

    public double getPlayed_secs() {
        return this.played_secs;
    }

    public void setPlayed_secs(double d) {
        this.played_secs = d;
    }

    public long getStarted_at() {
        return this.started_at;
    }

    public void setStarted_at(long j) {
        this.started_at = j;
    }

    public long getEnded_at() {
        return this.ended_at;
    }

    public void setEnded_at(long j) {
        this.ended_at = j;
    }

    public int getPlay_type() {
        return this.play_type;
    }

    public void setPlay_type(int i) {
        this.play_type = i;
    }

    public String getRec_src() {
        return this.rec_src;
    }

    public void setRec_src(String str) {
        this.rec_src = str;
    }

    public String getRec_track() {
        return this.rec_track;
    }

    public void setRec_track(String str) {
        this.rec_track = str;
    }

    public String getPlay_source() {
        return this.play_source;
    }

    public void setPlay_source(String str) {
        this.play_source = str;
    }

    public String getAlbum_title() {
        return this.album_title;
    }

    public void setAlbum_title(String str) {
        this.album_title = str;
    }

    public String getTrack_title() {
        return this.track_title;
    }

    public void setTrack_title(String str) {
        this.track_title = str;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public long getAlbum_id() {
        return this.album_id;
    }

    public void setAlbum_id(long j) {
        this.album_id = j;
    }

    public int getContent_type() {
        return this.content_type;
    }

    public void setContent_type(int i) {
        this.content_type = i;
    }

    public int getTotalDuration() {
        return this.totalDuration;
    }

    public void setTotalDuration(int i) {
        this.totalDuration = i;
    }
}
