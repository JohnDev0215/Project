package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1392b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f1393c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1394d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f1395e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f1396f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final String f1397g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f1398h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final boolean f1399i;
    final boolean j;
    final Bundle k;
    final boolean l;
    final int m;
    Bundle n;

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
        public FragmentState[] newArray(int i2) {
            return new FragmentState[i2];
        }
    }

    FragmentState(Fragment fragment) {
        this.f1392b = fragment.getClass().getName();
        this.f1393c = fragment.f1366g;
        this.f1394d = fragment.o;
        this.f1395e = fragment.x;
        this.f1396f = fragment.y;
        this.f1397g = fragment.z;
        this.f1398h = fragment.C;
        this.f1399i = fragment.n;
        this.j = fragment.B;
        this.k = fragment.f1367h;
        this.l = fragment.A;
        this.m = fragment.S.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1392b);
        sb.append(" (");
        sb.append(this.f1393c);
        sb.append(")}:");
        if (this.f1394d) {
            sb.append(" fromLayout");
        }
        if (this.f1396f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1396f));
        }
        String str = this.f1397g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1397g);
        }
        if (this.f1398h) {
            sb.append(" retainInstance");
        }
        if (this.f1399i) {
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
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1392b);
        parcel.writeString(this.f1393c);
        parcel.writeInt(this.f1394d ? 1 : 0);
        parcel.writeInt(this.f1395e);
        parcel.writeInt(this.f1396f);
        parcel.writeString(this.f1397g);
        parcel.writeInt(this.f1398h ? 1 : 0);
        parcel.writeInt(this.f1399i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeBundle(this.n);
        parcel.writeInt(this.m);
    }

    FragmentState(Parcel parcel) {
        this.f1392b = parcel.readString();
        this.f1393c = parcel.readString();
        this.f1394d = parcel.readInt() != 0;
        this.f1395e = parcel.readInt();
        this.f1396f = parcel.readInt();
        this.f1397g = parcel.readString();
        this.f1398h = parcel.readInt() != 0;
        this.f1399i = parcel.readInt() != 0;
        this.j = parcel.readInt() != 0;
        this.k = parcel.readBundle();
        this.l = parcel.readInt() != 0;
        this.n = parcel.readBundle();
        this.m = parcel.readInt();
    }
}
