package com.byd.music.beans;

import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;

/* JADX INFO: loaded from: classes5.dex */
public class ArtistBean extends MusicSet {
    public static String[] QUERY_PROJECTION = {"_id", "artist", "number_of_tracks"};
    public static Uri QUERY_URI = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;

    public static ArtistBean from(LocalMusicBean localMusicBean, int i) {
        String artist = localMusicBean.getArtist();
        if (artist == null) {
            return null;
        }
        return new ArtistBean(localMusicBean.getArtistId(), 1, artist);
    }

    public ArtistBean(long j, int i, String str) {
        super(j, i, str);
    }

    public String toString() {
        return "ArtistBean{id=" + this.id + ", name=" + this.name + DateFormat.QUOTE + ", musicCount=" + this.musicCount + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((ArtistBean) obj).getId();
    }
}
