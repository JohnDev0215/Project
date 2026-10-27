package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import r0.e;
import r0.h;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1320b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1321c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1322d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1323e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1324f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f1325g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f1326h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f1327i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f1328j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Bundle f1329k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f1330l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1331m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Bundle f1332n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Fragment f1333o;

    public static class a implements Parcelable.Creator<FragmentState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentState createFromParcel(Parcel parcel) {
            return new FragmentState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentState[] newArray(int i3) {
            return new FragmentState[i3];
        }
    }

    public FragmentState(Fragment fragment) {
        this.f1320b = fragment.getClass().getName();
        this.f1321c = fragment.mWho;
        this.f1322d = fragment.mFromLayout;
        this.f1323e = fragment.mFragmentId;
        this.f1324f = fragment.mContainerId;
        this.f1325g = fragment.mTag;
        this.f1326h = fragment.mRetainInstance;
        this.f1327i = fragment.mRemoving;
        this.f1328j = fragment.mDetached;
        this.f1329k = fragment.mArguments;
        this.f1330l = fragment.mHidden;
        this.f1331m = fragment.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Fragment f(ClassLoader classLoader, e eVar) {
        if (this.f1333o == null) {
            Bundle bundle = this.f1329k;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentA = eVar.a(classLoader, this.f1320b);
            this.f1333o = fragmentA;
            fragmentA.setArguments(this.f1329k);
            Bundle bundle2 = this.f1332n;
            if (bundle2 != null) {
                bundle2.setClassLoader(classLoader);
                this.f1333o.mSavedFragmentState = this.f1332n;
            } else {
                this.f1333o.mSavedFragmentState = new Bundle();
            }
            Fragment fragment = this.f1333o;
            fragment.mWho = this.f1321c;
            fragment.mFromLayout = this.f1322d;
            fragment.mRestored = true;
            fragment.mFragmentId = this.f1323e;
            fragment.mContainerId = this.f1324f;
            fragment.mTag = this.f1325g;
            fragment.mRetainInstance = this.f1326h;
            fragment.mRemoving = this.f1327i;
            fragment.mDetached = this.f1328j;
            fragment.mHidden = this.f1330l;
            fragment.mMaxState = Lifecycle.State.values()[this.f1331m];
            if (h.f4686d) {
                Log.v("FragmentManager", "Instantiated fragment " + this.f1333o);
            }
        }
        return this.f1333o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1320b);
        sb.append(" (");
        sb.append(this.f1321c);
        sb.append(")}:");
        if (this.f1322d) {
            sb.append(" fromLayout");
        }
        if (this.f1324f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1324f));
        }
        String str = this.f1325g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1325g);
        }
        if (this.f1326h) {
            sb.append(" retainInstance");
        }
        if (this.f1327i) {
            sb.append(" removing");
        }
        if (this.f1328j) {
            sb.append(" detached");
        }
        if (this.f1330l) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.f1320b);
        parcel.writeString(this.f1321c);
        parcel.writeInt(this.f1322d ? 1 : 0);
        parcel.writeInt(this.f1323e);
        parcel.writeInt(this.f1324f);
        parcel.writeString(this.f1325g);
        parcel.writeInt(this.f1326h ? 1 : 0);
        parcel.writeInt(this.f1327i ? 1 : 0);
        parcel.writeInt(this.f1328j ? 1 : 0);
        parcel.writeBundle(this.f1329k);
        parcel.writeInt(this.f1330l ? 1 : 0);
        parcel.writeBundle(this.f1332n);
        parcel.writeInt(this.f1331m);
    }

    public FragmentState(Parcel parcel) {
        this.f1320b = parcel.readString();
        this.f1321c = parcel.readString();
        this.f1322d = parcel.readInt() != 0;
        this.f1323e = parcel.readInt();
        this.f1324f = parcel.readInt();
        this.f1325g = parcel.readString();
        this.f1326h = parcel.readInt() != 0;
        this.f1327i = parcel.readInt() != 0;
        this.f1328j = parcel.readInt() != 0;
        this.f1329k = parcel.readBundle();
        this.f1330l = parcel.readInt() != 0;
        this.f1332n = parcel.readBundle();
        this.f1331m = parcel.readInt();
    }
}
