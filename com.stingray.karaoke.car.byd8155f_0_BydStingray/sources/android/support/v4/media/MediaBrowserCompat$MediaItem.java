package android.support.v4.media;

import I1.g;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = new g(29);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MediaDescriptionCompat f11239m;

    public MediaBrowserCompat$MediaItem(MediaDescriptionCompat mediaDescriptionCompat, int i7) {
        if (TextUtils.isEmpty(mediaDescriptionCompat.l)) {
            throw new IllegalArgumentException("description must have a non-empty media id");
        }
        this.l = i7;
        this.f11239m = mediaDescriptionCompat;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaItem{mFlags=" + this.l + ", mDescription=" + this.f11239m + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        this.f11239m.writeToParcel(parcel, i7);
    }

    public MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.l = parcel.readInt();
        this.f11239m = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }
}
