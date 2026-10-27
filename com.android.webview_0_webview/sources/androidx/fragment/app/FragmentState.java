package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import defpackage.C1142m4;
import defpackage.K3;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C1142m4();
    public final Bundle A;
    public final boolean B;
    public Bundle C;
    public K3 D;
    public final String s;
    public final int t;
    public final boolean u;
    public final int v;
    public final int w;
    public final String x;
    public final boolean y;
    public final boolean z;

    public FragmentState(K3 k3) {
        this.s = k3.getClass().getName();
        this.t = k3.v;
        this.u = k3.D;
        this.v = k3.O;
        this.w = k3.P;
        this.x = k3.Q;
        this.y = k3.T;
        this.z = k3.S;
        this.A = k3.x;
        this.B = k3.R;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.s);
        parcel.writeInt(this.t);
        parcel.writeInt(this.u ? 1 : 0);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeString(this.x);
        parcel.writeInt(this.y ? 1 : 0);
        parcel.writeInt(this.z ? 1 : 0);
        parcel.writeBundle(this.A);
        parcel.writeInt(this.B ? 1 : 0);
        parcel.writeBundle(this.C);
    }

    public FragmentState(Parcel parcel) {
        this.s = parcel.readString();
        this.t = parcel.readInt();
        this.u = parcel.readInt() != 0;
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readString();
        this.y = parcel.readInt() != 0;
        this.z = parcel.readInt() != 0;
        this.A = parcel.readBundle();
        this.B = parcel.readInt() != 0;
        this.C = parcel.readBundle();
    }
}
