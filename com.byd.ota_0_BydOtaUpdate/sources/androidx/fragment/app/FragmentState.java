package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.j.a.d;
import b.j.a.g;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();
    public final String E3;
    public final boolean F3;
    public final boolean G3;
    public final boolean H3;
    public final Bundle I3;
    public final boolean J3;
    public final int K3;
    public Bundle L3;
    public Fragment M3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f511c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f512d;
    public final boolean s;
    public final int x;
    public final int y;

    public static class a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    }

    public FragmentState(Fragment fragment) {
        this.f511c = fragment.getClass().getName();
        this.f512d = fragment.E3;
        this.s = fragment.M3;
        this.x = fragment.V3;
        this.y = fragment.W3;
        this.E3 = fragment.X3;
        this.F3 = fragment.a4;
        this.G3 = fragment.L3;
        this.H3 = fragment.Z3;
        this.I3 = fragment.F3;
        this.J3 = fragment.Y3;
        this.K3 = fragment.r4.ordinal();
    }

    public Fragment a(ClassLoader classLoader, d dVar) {
        if (this.M3 == null) {
            Bundle bundle = this.I3;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentA = dVar.a(classLoader, this.f511c);
            this.M3 = fragmentA;
            fragmentA.d1(this.I3);
            Bundle bundle2 = this.L3;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.M3.s = this.L3;
            } else {
                this.M3.s = new Bundle();
            }
            Fragment fragment = this.M3;
            fragment.E3 = this.f512d;
            fragment.M3 = this.s;
            fragment.O3 = true;
            fragment.V3 = this.x;
            fragment.W3 = this.y;
            fragment.X3 = this.E3;
            fragment.a4 = this.F3;
            fragment.L3 = this.G3;
            fragment.Z3 = this.H3;
            fragment.Y3 = this.J3;
            fragment.r4 = Lifecycle.State.values()[this.K3];
            if (g.s) {
                Log.v("FragmentManager", "Instantiated fragment " + this.M3);
            }
        }
        return this.M3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f511c);
        sb.append(" (");
        sb.append(this.f512d);
        sb.append(")}:");
        if (this.s) {
            sb.append(" fromLayout");
        }
        if (this.y != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.y));
        }
        String str = this.E3;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.E3);
        }
        if (this.F3) {
            sb.append(" retainInstance");
        }
        if (this.G3) {
            sb.append(" removing");
        }
        if (this.H3) {
            sb.append(" detached");
        }
        if (this.J3) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f511c);
        parcel.writeString(this.f512d);
        parcel.writeInt(this.s ? 1 : 0);
        parcel.writeInt(this.x);
        parcel.writeInt(this.y);
        parcel.writeString(this.E3);
        parcel.writeInt(this.F3 ? 1 : 0);
        parcel.writeInt(this.G3 ? 1 : 0);
        parcel.writeInt(this.H3 ? 1 : 0);
        parcel.writeBundle(this.I3);
        parcel.writeInt(this.J3 ? 1 : 0);
        parcel.writeBundle(this.L3);
        parcel.writeInt(this.K3);
    }

    public FragmentState(Parcel parcel) {
        this.f511c = parcel.readString();
        this.f512d = parcel.readString();
        this.s = parcel.readInt() != 0;
        this.x = parcel.readInt();
        this.y = parcel.readInt();
        this.E3 = parcel.readString();
        this.F3 = parcel.readInt() != 0;
        this.G3 = parcel.readInt() != 0;
        this.H3 = parcel.readInt() != 0;
        this.I3 = parcel.readBundle();
        this.J3 = parcel.readInt() != 0;
        this.L3 = parcel.readBundle();
        this.K3 = parcel.readInt();
    }
}
