package a.l.d;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class w implements Parcelable {
    public static final Parcelable.Creator<w> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1051b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1052c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1053d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1054e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1055f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final Bundle j;
    public final boolean k;
    public final int l;
    public Bundle m;

    public static class a implements Parcelable.Creator<w> {
        @Override // android.os.Parcelable.Creator
        public w createFromParcel(Parcel parcel) {
            return new w(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public w[] newArray(int i) {
            return new w[i];
        }
    }

    public w(Parcel parcel) {
        this.f1050a = parcel.readString();
        this.f1051b = parcel.readString();
        this.f1052c = parcel.readInt() != 0;
        this.f1053d = parcel.readInt();
        this.f1054e = parcel.readInt();
        this.f1055f = parcel.readString();
        this.g = parcel.readInt() != 0;
        this.h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0;
        this.j = parcel.readBundle();
        this.k = parcel.readInt() != 0;
        this.m = parcel.readBundle();
        this.l = parcel.readInt();
    }

    public w(Fragment fragment) {
        this.f1050a = fragment.getClass().getName();
        this.f1051b = fragment.f1557e;
        this.f1052c = fragment.m;
        this.f1053d = fragment.v;
        this.f1054e = fragment.w;
        this.f1055f = fragment.x;
        this.g = fragment.A;
        this.h = fragment.l;
        this.i = fragment.z;
        this.j = fragment.f1558f;
        this.k = fragment.y;
        this.l = fragment.O.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1050a);
        sb.append(" (");
        sb.append(this.f1051b);
        sb.append(")}:");
        if (this.f1052c) {
            sb.append(" fromLayout");
        }
        if (this.f1054e != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1054e));
        }
        String str = this.f1055f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1055f);
        }
        if (this.g) {
            sb.append(" retainInstance");
        }
        if (this.h) {
            sb.append(" removing");
        }
        if (this.i) {
            sb.append(" detached");
        }
        if (this.k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1050a);
        parcel.writeString(this.f1051b);
        parcel.writeInt(this.f1052c ? 1 : 0);
        parcel.writeInt(this.f1053d);
        parcel.writeInt(this.f1054e);
        parcel.writeString(this.f1055f);
        parcel.writeInt(this.g ? 1 : 0);
        parcel.writeInt(this.h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeBundle(this.j);
        parcel.writeInt(this.k ? 1 : 0);
        parcel.writeBundle(this.m);
        parcel.writeInt(this.l);
    }
}
