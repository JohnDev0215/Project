package android.support.v4.media;

import android.annotation.SuppressLint;
import android.media.MediaMetadata;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import p.bg1;
import p.bi1;
import p.nb;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    public static final nb k;
    public final Bundle c;
    public MediaMetadata e;

    static {
        nb nbVar = new nb();
        k = nbVar;
        nbVar.put("android.media.metadata.TITLE", 1);
        nbVar.put("android.media.metadata.ARTIST", 1);
        nbVar.put("android.media.metadata.DURATION", 0);
        nbVar.put("android.media.metadata.ALBUM", 1);
        nbVar.put("android.media.metadata.AUTHOR", 1);
        nbVar.put("android.media.metadata.WRITER", 1);
        nbVar.put("android.media.metadata.COMPOSER", 1);
        nbVar.put("android.media.metadata.COMPILATION", 1);
        nbVar.put("android.media.metadata.DATE", 1);
        nbVar.put("android.media.metadata.YEAR", 0);
        nbVar.put("android.media.metadata.GENRE", 1);
        nbVar.put("android.media.metadata.TRACK_NUMBER", 0);
        nbVar.put("android.media.metadata.NUM_TRACKS", 0);
        nbVar.put("android.media.metadata.DISC_NUMBER", 0);
        nbVar.put("android.media.metadata.ALBUM_ARTIST", 1);
        nbVar.put("android.media.metadata.ART", 2);
        nbVar.put("android.media.metadata.ART_URI", 1);
        nbVar.put("android.media.metadata.ALBUM_ART", 2);
        nbVar.put("android.media.metadata.ALBUM_ART_URI", 1);
        nbVar.put("android.media.metadata.USER_RATING", 3);
        nbVar.put("android.media.metadata.RATING", 3);
        nbVar.put("android.media.metadata.DISPLAY_TITLE", 1);
        nbVar.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        nbVar.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        nbVar.put("android.media.metadata.DISPLAY_ICON", 2);
        nbVar.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        nbVar.put("android.media.metadata.MEDIA_ID", 1);
        nbVar.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        nbVar.put("android.media.metadata.MEDIA_URI", 1);
        nbVar.put("android.media.metadata.ADVERTISEMENT", 0);
        nbVar.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        CREATOR = new bg1(2);
    }

    public MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.c = bundle2;
        bi1.a(bundle2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.c);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.c = parcel.readBundle(bi1.class.getClassLoader());
    }
}
