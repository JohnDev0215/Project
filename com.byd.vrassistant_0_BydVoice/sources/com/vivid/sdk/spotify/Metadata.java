package com.vivid.sdk.spotify;

/* JADX INFO: loaded from: classes3.dex */
public class Metadata {
    private String coverUrl;
    private long duration;
    private String subTitle;
    private String title;

    public Metadata(String str, String str2, String str3, long j) {
        this.title = str;
        this.subTitle = str2;
        this.coverUrl = str3;
        this.duration = j;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public void setCoverUrl(String str) {
        this.coverUrl = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }
}
