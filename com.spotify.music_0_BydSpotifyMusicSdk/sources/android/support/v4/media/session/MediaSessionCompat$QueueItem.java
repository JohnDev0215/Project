package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.MediaSession;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new e(1);
    public final MediaDescriptionCompat c;
    public final long e;
    public MediaSession.QueueItem k;

    public MediaSessionCompat$QueueItem(MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (j == -1) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
        this.c = mediaDescriptionCompat;
        this.e = j;
        this.k = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaSession.QueueItem {Description=" + this.c + ", Id=" + this.e + " }";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        this.c.writeToParcel(parcel, i);
        parcel.writeLong(this.e);
    }

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.c = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.e = parcel.readLong();
    }
}
