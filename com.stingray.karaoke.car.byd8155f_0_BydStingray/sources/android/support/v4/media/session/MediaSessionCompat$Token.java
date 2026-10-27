package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new a(2);
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Parcelable f11250m;

    public MediaSessionCompat$Token(Parcelable parcelable) {
        this.f11250m = parcelable;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Parcelable parcelable = this.f11250m;
        if (parcelable == null) {
            return mediaSessionCompat$Token.f11250m == null;
        }
        Parcelable parcelable2 = mediaSessionCompat$Token.f11250m;
        if (parcelable2 == null) {
            return false;
        }
        return parcelable.equals(parcelable2);
    }

    public final int hashCode() {
        Parcelable parcelable = this.f11250m;
        if (parcelable == null) {
            return 0;
        }
        return parcelable.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeParcelable(this.f11250m, i7);
    }
}
