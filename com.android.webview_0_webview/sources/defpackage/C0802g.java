package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;

/* JADX INFO: renamed from: g, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0802g implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new RatingCompat[i];
    }
}
