package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import v0.e;
import v0.h;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1405d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1406e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1407f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1408g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1409h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f1410i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1411j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f1412k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f1413l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Bundle f1414m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f1415n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f1416o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Bundle f1417p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Fragment f1418q;

    public static class a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i8) {
            return new FragmentState[i8];
        }
    }

    public FragmentState(Fragment fragment) {
        this.f1405d = fragment.getClass().getName();
        this.f1406e = fragment.mWho;
        this.f1407f = fragment.mFromLayout;
        this.f1408g = fragment.mFragmentId;
        this.f1409h = fragment.mContainerId;
        this.f1410i = fragment.mTag;
        this.f1411j = fragment.mRetainInstance;
        this.f1412k = fragment.mRemoving;
        this.f1413l = fragment.mDetached;
        this.f1414m = fragment.mArguments;
        this.f1415n = fragment.mHidden;
        this.f1416o = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Fragment f(ClassLoader classLoader, e eVar) {
        if (this.f1418q == null) {
            Bundle bundle = this.f1414m;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentA = eVar.a(classLoader, this.f1405d);
            this.f1418q = fragmentA;
            fragmentA.setArguments(this.f1414m);
            Bundle bundle2 = this.f1417p;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.f1418q.mSavedFragmentState = this.f1417p;
            } else {
                this.f1418q.mSavedFragmentState = new Bundle();
            }
            Fragment fragment = this.f1418q;
            fragment.mWho = this.f1406e;
            fragment.mFromLayout = this.f1407f;
            fragment.mRestored = true;
            fragment.mFragmentId = this.f1408g;
            fragment.mContainerId = this.f1409h;
            fragment.mTag = this.f1410i;
            fragment.mRetainInstance = this.f1411j;
            fragment.mRemoving = this.f1412k;
            fragment.mDetached = this.f1413l;
            fragment.mHidden = this.f1415n;
            fragment.mMaxState = Lifecycle.State.values()[this.f1416o];
            if (h.f8366f) {
                Log.v("FragmentManager", "Instantiated fragment " + this.f1418q);
            }
        }
        return this.f1418q;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1405d);
        sb.append(" (");
        sb.append(this.f1406e);
        sb.append(")}:");
        if (this.f1407f) {
            sb.append(" fromLayout");
        }
        if (this.f1409h != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1409h));
        }
        String str = this.f1410i;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1410i);
        }
        if (this.f1411j) {
            sb.append(" retainInstance");
        }
        if (this.f1412k) {
            sb.append(" removing");
        }
        if (this.f1413l) {
            sb.append(" detached");
        }
        if (this.f1415n) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f1405d);
        parcel.writeString(this.f1406e);
        parcel.writeInt(this.f1407f ? 1 : 0);
        parcel.writeInt(this.f1408g);
        parcel.writeInt(this.f1409h);
        parcel.writeString(this.f1410i);
        parcel.writeInt(this.f1411j ? 1 : 0);
        parcel.writeInt(this.f1412k ? 1 : 0);
        parcel.writeInt(this.f1413l ? 1 : 0);
        parcel.writeBundle(this.f1414m);
        parcel.writeInt(this.f1415n ? 1 : 0);
        parcel.writeBundle(this.f1417p);
        parcel.writeInt(this.f1416o);
    }

    public FragmentState(Parcel parcel) {
        this.f1405d = parcel.readString();
        this.f1406e = parcel.readString();
        this.f1407f = parcel.readInt() != 0;
        this.f1408g = parcel.readInt();
        this.f1409h = parcel.readInt();
        this.f1410i = parcel.readString();
        this.f1411j = parcel.readInt() != 0;
        this.f1412k = parcel.readInt() != 0;
        this.f1413l = parcel.readInt() != 0;
        this.f1414m = parcel.readBundle();
        this.f1415n = parcel.readInt() != 0;
        this.f1417p = parcel.readBundle();
        this.f1416o = parcel.readInt();
    }
}
