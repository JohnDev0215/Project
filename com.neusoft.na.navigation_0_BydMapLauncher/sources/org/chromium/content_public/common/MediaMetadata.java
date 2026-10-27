package org.chromium.content_public.common;

import android.text.TextUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class MediaMetadata {
    private String mAlbum;
    private String mArtist;
    private String mTitle;

    public String getTitle() {
        return this.mTitle;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    private static MediaMetadata create(String str, String str2, String str3) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        return new MediaMetadata(str, str2, str3);
    }

    public MediaMetadata(String str, String str2, String str3) {
        this.mTitle = str;
        this.mArtist = str2;
        this.mAlbum = str3;
    }

    public MediaMetadata(MediaMetadata mediaMetadata) {
        this(mediaMetadata.mTitle, mediaMetadata.mArtist, mediaMetadata.mAlbum);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return TextUtils.equals(this.mTitle, mediaMetadata.mTitle) && TextUtils.equals(this.mArtist, mediaMetadata.mArtist) && TextUtils.equals(this.mAlbum, mediaMetadata.mAlbum);
    }

    public int hashCode() {
        return (((this.mTitle.hashCode() * 31) + this.mArtist.hashCode()) * 31) + this.mAlbum.hashCode();
    }
}
