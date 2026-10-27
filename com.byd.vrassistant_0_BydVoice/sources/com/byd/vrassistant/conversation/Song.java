package com.byd.vrassistant.conversation;

/* JADX INFO: loaded from: classes.dex */
public class Song {
    public String data;
    public int duration;
    public String name;
    public String singer;
    public long size;

    public String getSinger() {
        return this.singer;
    }

    public void setSinger(String str) {
        this.singer = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String toString() {
        return "Song{singer='" + this.singer + "', name='" + this.name + "', data='" + this.data + "', duration=" + this.duration + ", size=" + this.size + '}';
    }
}
