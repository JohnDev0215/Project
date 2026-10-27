package com.android.bluetooth.avrcp;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class Metadata implements Cloneable {
    public String album;
    public String artist;
    public String duration;
    public String genre;
    public String mediaId;
    public String numTracks;
    public String title;
    public String trackNum;

    Metadata() {
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Metadata m9clone() {
        Metadata data = new Metadata();
        data.mediaId = this.mediaId;
        data.title = this.title;
        data.artist = this.artist;
        data.album = this.album;
        data.trackNum = this.trackNum;
        data.numTracks = this.numTracks;
        data.genre = this.genre;
        data.duration = this.duration;
        return data;
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof Metadata)) {
            return false;
        }
        Metadata m = (Metadata) o;
        if (!Objects.equals(this.title, m.title) || !Objects.equals(this.artist, m.artist) || !Objects.equals(this.album, m.album)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "{ mediaId=\"" + this.mediaId + "\" title=\"" + this.title + "\" artist=\"" + this.artist + "\" album=\"" + this.album + "\" duration=" + this.duration + " trackPosition=" + this.trackNum + "/" + this.numTracks + " }";
    }
}
