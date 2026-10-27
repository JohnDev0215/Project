package com.byd.music.beans;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class AlbumBean extends MusicSet {
    public static String[] QUERY_PROJECTION = {"_id", "album", "artist", "numsongs"};
    public static Uri QUERY_URI = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    private Bitmap albumBitmap;
    private String artist;
    public String uri;

    public AlbumBean(long j, int i, String str, String str2) {
        super(j, i, str);
        this.uri = "";
        this.artist = str2;
    }

    public static AlbumBean from(LocalMusicBean localMusicBean, int i) {
        String album = localMusicBean.getAlbum();
        if (album == null) {
            return null;
        }
        AlbumBean albumBean = new AlbumBean(localMusicBean.getAlbumId(), 1, album, localMusicBean.getArtist());
        albumBean.uri = localMusicBean.getUri();
        return albumBean;
    }

    public static AlbumBean from(Cursor cursor) {
        String string = cursor.getString(cursor.getColumnIndex("album"));
        if (string == null) {
            return null;
        }
        return new AlbumBean(cursor.getInt(cursor.getColumnIndex("_id")), cursor.getInt(cursor.getColumnIndex("numsongs")), string, cursor.getString(cursor.getColumnIndex("artist")));
    }

    public String getArtist() {
        return this.artist;
    }

    public Bitmap getAlbumBitmap() {
        return this.albumBitmap;
    }

    public void setAlbumBitmap(Bitmap bitmap) {
        this.albumBitmap = bitmap;
    }

    public String toString() {
        return "AblumBean{id=" + this.id + ", name=" + this.name + DateFormat.QUOTE + ", musicCount=" + this.musicCount + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((AlbumBean) obj).id;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id));
    }
}
