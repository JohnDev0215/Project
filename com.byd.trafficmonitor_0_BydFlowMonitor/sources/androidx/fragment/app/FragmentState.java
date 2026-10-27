package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f966c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f967d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f969f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f970g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f971h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f972i;
    public final boolean j;
    public final Bundle k;
    public final boolean l;
    public final int m;
    public Bundle n;

    public class a implements Parcelable.Creator<FragmentState> {
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
        this.f965b = fragment.getClass().getName();
        this.f966c = fragment.mWho;
        this.f967d = fragment.mFromLayout;
        this.f968e = fragment.mFragmentId;
        this.f969f = fragment.mContainerId;
        this.f970g = fragment.mTag;
        this.f971h = fragment.mRetainInstance;
        this.f972i = fragment.mRemoving;
        this.j = fragment.mDetached;
        this.k = fragment.mArguments;
        this.l = fragment.mHidden;
        this.m = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f965b);
        sb.append(" (");
        sb.append(this.f966c);
        sb.append(")}:");
        if (this.f967d) {
            sb.append(" fromLayout");
        }
        if (this.f969f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f969f));
        }
        String str = this.f970g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f970g);
        }
        if (this.f971h) {
            sb.append(" retainInstance");
        }
        if (this.f972i) {
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
        parcel.writeString(this.f965b);
        parcel.writeString(this.f966c);
        parcel.writeInt(this.f967d ? 1 : 0);
        parcel.writeInt(this.f968e);
        parcel.writeInt(this.f969f);
        parcel.writeString(this.f970g);
        parcel.writeInt(this.f971h ? 1 : 0);
        parcel.writeInt(this.f972i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeBundle(this.n);
        parcel.writeInt(this.m);
    }

    public FragmentState(Parcel parcel) {
        this.f965b = parcel.readString();
        this.f966c = parcel.readString();
        this.f967d = parcel.readInt() != 0;
        this.f968e = parcel.readInt();
        this.f969f = parcel.readInt();
        this.f970g = parcel.readString();
        this.f971h = parcel.readInt() != 0;
        this.f972i = parcel.readInt() != 0;
        this.j = parcel.readInt() != 0;
        this.k = parcel.readBundle();
        this.l = parcel.readInt() != 0;
        this.n = parcel.readBundle();
        this.m = parcel.readInt();
    }
}
