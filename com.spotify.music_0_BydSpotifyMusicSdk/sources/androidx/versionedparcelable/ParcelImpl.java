package androidx.versionedparcelable;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import p.at2;
import p.bg1;
import p.zs2;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new bg1(11);
    public final at2 c;

    public ParcelImpl(at2 at2Var) {
        this.c = at2Var;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        new zs2(parcel).l(this.c);
    }

    public ParcelImpl(Parcel parcel) {
        this.c = new zs2(parcel).h();
    }
}
