package androidx.versionedparcelable;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.a;
import x3.C2675c;
import x3.InterfaceC2676d;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new a(28);
    public final InterfaceC2676d l;

    public ParcelImpl(InterfaceC2676d interfaceC2676d) {
        this.l = interfaceC2676d;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        new C2675c(parcel).l(this.l);
    }

    public ParcelImpl(Parcel parcel) {
        this.l = new C2675c(parcel).h();
    }
}
