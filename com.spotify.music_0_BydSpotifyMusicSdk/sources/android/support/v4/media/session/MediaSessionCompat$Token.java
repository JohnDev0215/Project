package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import p.at2;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new e(2);
    public final Object e;
    public b k;
    public final Object c = new Object();
    public at2 l = null;

    public MediaSessionCompat$Token(Object obj, b bVar) {
        this.e = obj;
        this.k = bVar;
    }

    public final b a() {
        b bVar;
        synchronized (this.c) {
            bVar = this.k;
        }
        return bVar;
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
        Object obj2 = this.e;
        if (obj2 == null) {
            return mediaSessionCompat$Token.e == null;
        }
        Object obj3 = mediaSessionCompat$Token.e;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        Object obj = this.e;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable((Parcelable) this.e, i);
    }
}
