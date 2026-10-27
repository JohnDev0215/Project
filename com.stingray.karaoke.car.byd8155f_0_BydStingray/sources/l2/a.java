package L2;

import I1.g;
import android.os.Parcel;
import android.os.Parcelable;
import e2.J;

/* JADX INFO: loaded from: classes.dex */
public final class a implements J {
    public static final Parcelable.Creator<a> CREATOR = new g(2);
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f3998m;

    public a(int i7, String str) {
        this.l = i7;
        this.f3998m = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Ait(controlCode=");
        sb.append(this.l);
        sb.append(",url=");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, this.f3998m, ")");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f3998m);
        parcel.writeInt(this.l);
    }
}
