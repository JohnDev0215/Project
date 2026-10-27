package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class m implements Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1328b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f1329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1330d;
    final int e;
    final int f;
    final String g;
    final boolean h;
    final boolean i;
    final boolean j;
    final Bundle k;
    final boolean l;
    final int m;
    Bundle n;
    Fragment o;

    static class a implements Parcelable.Creator<m> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return new m(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i) {
            return new m[i];
        }
    }

    m(Parcel parcel) {
        this.f1328b = parcel.readString();
        this.f1329c = parcel.readString();
        this.f1330d = parcel.readInt() != 0;
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0;
        this.j = parcel.readInt() != 0;
        this.k = parcel.readBundle();
        this.l = parcel.readInt() != 0;
        this.n = parcel.readBundle();
        this.m = parcel.readInt();
    }

    m(Fragment fragment) {
        this.f1328b = fragment.getClass().getName();
        this.f1329c = fragment.g;
        this.f1330d = fragment.o;
        this.e = fragment.x;
        this.f = fragment.y;
        this.g = fragment.z;
        this.h = fragment.C;
        this.i = fragment.n;
        this.j = fragment.B;
        this.k = fragment.h;
        this.l = fragment.A;
        this.m = fragment.T.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1328b);
        sb.append(" (");
        sb.append(this.f1329c);
        sb.append(")}:");
        if (this.f1330d) {
            sb.append(" fromLayout");
        }
        if (this.f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f));
        }
        String str = this.g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.g);
        }
        if (this.h) {
            sb.append(" retainInstance");
        }
        if (this.i) {
            sb.append(" removing");
        }
        if (this.j) {
            sb.append(" detached");
        }
        if (this.l) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    public Fragment u(ClassLoader classLoader, g gVar) {
        Fragment fragment;
        Bundle bundle;
        if (this.o == null) {
            Bundle bundle2 = this.k;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
            }
            Fragment fragmentA = gVar.a(classLoader, this.f1328b);
            this.o = fragmentA;
            fragmentA.q1(this.k);
            Bundle bundle3 = this.n;
            if (bundle3 != null) {
                bundle3.setClassLoader(classLoader);
                fragment = this.o;
                bundle = this.n;
            } else {
                fragment = this.o;
                bundle = new Bundle();
            }
            fragment.f1273d = bundle;
            Fragment fragment2 = this.o;
            fragment2.g = this.f1329c;
            fragment2.o = this.f1330d;
            fragment2.q = true;
            fragment2.x = this.e;
            fragment2.y = this.f;
            fragment2.z = this.g;
            fragment2.C = this.h;
            fragment2.n = this.i;
            fragment2.B = this.j;
            fragment2.A = this.l;
            fragment2.T = d.b.values()[this.m];
            if (j.f1292d) {
                Log.v("FragmentManager", "Instantiated fragment " + this.o);
            }
        }
        return this.o;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1328b);
        parcel.writeString(this.f1329c);
        parcel.writeInt(this.f1330d ? 1 : 0);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeBundle(this.n);
        parcel.writeInt(this.m);
    }
}
