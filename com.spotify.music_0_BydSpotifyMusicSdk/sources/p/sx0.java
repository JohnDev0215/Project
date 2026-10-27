package p;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class sx0 implements Parcelable {
    public static final Parcelable.Creator<sx0> CREATOR = new tk(4);
    public final String c;
    public final String e;
    public final boolean k;
    public final int l;
    public final int m;
    public final String n;
    public final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f183p;
    public final boolean q;
    public final boolean r;
    public final int s;
    public final String t;
    public final int u;
    public final boolean v;

    public sx0(ww0 ww0Var) {
        this.c = ww0Var.getClass().getName();
        this.e = ww0Var.m;
        this.k = ww0Var.u;
        this.l = ww0Var.D;
        this.m = ww0Var.E;
        this.n = ww0Var.F;
        this.o = ww0Var.I;
        this.f183p = ww0Var.t;
        this.q = ww0Var.H;
        this.r = ww0Var.G;
        this.s = ww0Var.T.ordinal();
        this.t = ww0Var.f205p;
        this.u = ww0Var.q;
        this.v = ww0Var.O;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.c);
        sb.append(" (");
        sb.append(this.e);
        sb.append(")}:");
        if (this.k) {
            sb.append(" fromLayout");
        }
        int i = this.m;
        if (i != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i));
        }
        String str = this.n;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(str);
        }
        if (this.o) {
            sb.append(" retainInstance");
        }
        if (this.f183p) {
            sb.append(" removing");
        }
        if (this.q) {
            sb.append(" detached");
        }
        if (this.r) {
            sb.append(" hidden");
        }
        String str2 = this.t;
        if (str2 != null) {
            sb.append(" targetWho=");
            sb.append(str2);
            sb.append(" targetRequestCode=");
            sb.append(this.u);
        }
        if (this.v) {
            sb.append(" userVisibleHint");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeString(this.n);
        parcel.writeInt(this.o ? 1 : 0);
        parcel.writeInt(this.f183p ? 1 : 0);
        parcel.writeInt(this.q ? 1 : 0);
        parcel.writeInt(this.r ? 1 : 0);
        parcel.writeInt(this.s);
        parcel.writeString(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v ? 1 : 0);
    }

    public sx0(Parcel parcel) {
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.k = parcel.readInt() != 0;
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readString();
        this.o = parcel.readInt() != 0;
        this.f183p = parcel.readInt() != 0;
        this.q = parcel.readInt() != 0;
        this.r = parcel.readInt() != 0;
        this.s = parcel.readInt();
        this.t = parcel.readString();
        this.u = parcel.readInt();
        this.v = parcel.readInt() != 0;
    }
}
