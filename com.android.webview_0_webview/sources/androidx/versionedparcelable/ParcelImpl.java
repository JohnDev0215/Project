package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.V7;
import defpackage.X7;
import defpackage.Y7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new V7();
    public final Y7 s;

    public ParcelImpl(Parcel parcel) {
        this.s = new X7(parcel, parcel.dataPosition(), parcel.dataSize(), "").g();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        new X7(parcel, parcel.dataPosition(), parcel.dataSize(), "").k(this.s);
    }
}
