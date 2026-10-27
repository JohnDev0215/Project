package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C0802g;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C0802g();
    public final int s;
    public final float t;

    public RatingCompat(int i, float f) {
        this.s = i;
        this.t = f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.s;
    }

    public String toString() {
        StringBuilder sbF = Z7.f("Rating:style=");
        sbF.append(this.s);
        sbF.append(" rating=");
        float f = this.t;
        sbF.append(f < 0.0f ? "unrated" : String.valueOf(f));
        return sbF.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.s);
        parcel.writeFloat(this.t);
    }
}
