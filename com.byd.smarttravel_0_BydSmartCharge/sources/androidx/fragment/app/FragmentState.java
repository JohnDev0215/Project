package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import b.k.a.e;
import b.k.a.h;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f386b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f387c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f388d;
    public final int e;
    public final int f;
    public final String g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final Bundle k;
    public final boolean l;
    public final int m;
    public Bundle n;
    public Fragment o;

    public static class a implements Parcelable.Creator<FragmentState> {
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

    public FragmentState(Fragment fragment) {
        this.f386b = fragment.getClass().getName();
        this.f387c = fragment.mWho;
        this.f388d = fragment.mFromLayout;
        this.e = fragment.mFragmentId;
        this.f = fragment.mContainerId;
        this.g = fragment.mTag;
        this.h = fragment.mRetainInstance;
        this.i = fragment.mRemoving;
        this.j = fragment.mDetached;
        this.k = fragment.mArguments;
        this.l = fragment.mHidden;
        this.m = fragment.mMaxState.ordinal();
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
            Fragment fragmentA = eVar.a(classLoader, this.f386b);
            this.o = fragmentA;
            fragmentA.setArguments(this.k);
            Bundle bundle2 = this.n;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.o.mSavedFragmentState = this.n;
            } else {
                this.o.mSavedFragmentState = new Bundle();
            }
            Fragment fragment = this.o;
            fragment.mWho = this.f387c;
            fragment.mFromLayout = this.f388d;
            fragment.mRestored = true;
            fragment.mFragmentId = this.e;
            fragment.mContainerId = this.f;
            fragment.mTag = this.g;
            fragment.mRetainInstance = this.h;
            fragment.mRemoving = this.i;
            fragment.mDetached = this.j;
            fragment.mHidden = this.l;
            fragment.mMaxState = Lifecycle.State.values()[this.m];
            if (h.f1223d) {
                Log.v("FragmentManager", "Instantiated fragment " + this.o);
            }
        }
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f386b);
        sb.append(" (");
        sb.append(this.f387c);
        sb.append(")}:");
        if (this.f388d) {
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
        parcel.writeString(this.f386b);
        parcel.writeString(this.f387c);
        parcel.writeInt(this.f388d ? 1 : 0);
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

    public FragmentState(Parcel parcel) {
        this.f386b = parcel.readString();
        this.f387c = parcel.readString();
        this.f388d = parcel.readInt() != 0;
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
