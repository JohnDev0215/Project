package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f1516c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1517d;
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

    static class a implements Parcelable.Creator<FragmentState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i) {
            return new FragmentState[i];
        }
    }

    FragmentState(Fragment fragment) {
        this.f1515b = fragment.getClass().getName();
        this.f1516c = fragment.g;
        this.f1517d = fragment.o;
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

    public Fragment f(ClassLoader classLoader, e eVar) {
        if (this.o == null) {
            Bundle bundle = this.k;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentA = eVar.a(classLoader, this.f1515b);
            this.o = fragmentA;
            fragmentA.f1(this.k);
            Bundle bundle2 = this.n;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.o.f1502d = this.n;
            } else {
                this.o.f1502d = new Bundle();
            }
            Fragment fragment = this.o;
            fragment.g = this.f1516c;
            fragment.o = this.f1517d;
            fragment.q = true;
            fragment.x = this.e;
            fragment.y = this.f;
            fragment.z = this.g;
            fragment.C = this.h;
            fragment.n = this.i;
            fragment.B = this.j;
            fragment.A = this.l;
            fragment.T = Lifecycle.State.values()[this.m];
            if (h.f1533d) {
                Log.v("FragmentManager", "Instantiated fragment " + this.o);
            }
        }
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1515b);
        sb.append(" (");
        sb.append(this.f1516c);
        sb.append(")}:");
        if (this.f1517d) {
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1515b);
        parcel.writeString(this.f1516c);
        parcel.writeInt(this.f1517d ? 1 : 0);
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

    FragmentState(Parcel parcel) {
        this.f1515b = parcel.readString();
        this.f1516c = parcel.readString();
        this.f1517d = parcel.readInt() != 0;
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
}
