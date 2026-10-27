package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final String f2226e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final String f2227g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f2228h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int f2229i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    final int f2230j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    final String f2231k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final boolean f2232l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    final boolean f2233m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final boolean f2234n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final Bundle f2235o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    final boolean f2236p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    final int f2237q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    Bundle f2238r;

    class a implements Parcelable.Creator<FragmentState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i7) {
            return new FragmentState[i7];
        }
    }

    FragmentState(Fragment fragment) {
        this.f2226e = fragment.getClass().getName();
        this.f2227g = fragment.f2115k;
        this.f2228h = fragment.f2123s;
        this.f2229i = fragment.B;
        this.f2230j = fragment.C;
        this.f2231k = fragment.D;
        this.f2232l = fragment.G;
        this.f2233m = fragment.f2122r;
        this.f2234n = fragment.F;
        this.f2235o = fragment.f2116l;
        this.f2236p = fragment.E;
        this.f2237q = fragment.W.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f2226e);
        sb.append(" (");
        sb.append(this.f2227g);
        sb.append(")}:");
        if (this.f2228h) {
            sb.append(" fromLayout");
        }
        if (this.f2230j != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f2230j));
        }
        String str = this.f2231k;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f2231k);
        }
        if (this.f2232l) {
            sb.append(" retainInstance");
        }
        if (this.f2233m) {
            sb.append(" removing");
        }
        if (this.f2234n) {
            sb.append(" detached");
        }
        if (this.f2236p) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f2226e);
        parcel.writeString(this.f2227g);
        parcel.writeInt(this.f2228h ? 1 : 0);
        parcel.writeInt(this.f2229i);
        parcel.writeInt(this.f2230j);
        parcel.writeString(this.f2231k);
        parcel.writeInt(this.f2232l ? 1 : 0);
        parcel.writeInt(this.f2233m ? 1 : 0);
        parcel.writeInt(this.f2234n ? 1 : 0);
        parcel.writeBundle(this.f2235o);
        parcel.writeInt(this.f2236p ? 1 : 0);
        parcel.writeBundle(this.f2238r);
        parcel.writeInt(this.f2237q);
    }

    FragmentState(Parcel parcel) {
        this.f2226e = parcel.readString();
        this.f2227g = parcel.readString();
        this.f2228h = parcel.readInt() != 0;
        this.f2229i = parcel.readInt();
        this.f2230j = parcel.readInt();
        this.f2231k = parcel.readString();
        this.f2232l = parcel.readInt() != 0;
        this.f2233m = parcel.readInt() != 0;
        this.f2234n = parcel.readInt() != 0;
        this.f2235o = parcel.readBundle();
        this.f2236p = parcel.readInt() != 0;
        this.f2238r = parcel.readBundle();
        this.f2237q = parcel.readInt();
    }
}
