package android.support.v4.media;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new a(2);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f11247m;

    public RatingCompat(float f10, int i7) {
        this.l = i7;
        this.f11247m = f10;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.l;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.l);
        sb.append(" rating=");
        float f10 = this.f11247m;
        sb.append(f10 < 0.0f ? "unrated" : String.valueOf(f10));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeInt(this.l);
        parcel.writeFloat(this.f11247m);
    }
}
