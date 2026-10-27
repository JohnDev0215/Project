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
    final String f1484b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f1485c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1486d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f1487e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f1488f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final String f1489g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f1490h;
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
        this.f1484b = fragment.getClass().getName();
        this.f1485c = fragment.mWho;
        this.f1486d = fragment.mFromLayout;
        this.f1487e = fragment.mFragmentId;
        this.f1488f = fragment.mContainerId;
        this.f1489g = fragment.mTag;
        this.f1490h = fragment.mRetainInstance;
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

    public Fragment e(ClassLoader classLoader, e eVar) {
        if (this.o == null) {
            Bundle bundle = this.k;
            if (bundle != null) {
                bundle.setClassLoader(classLoader);
            }
            Fragment fragmentA = eVar.a(classLoader, this.f1484b);
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
            fragment.mWho = this.f1485c;
            fragment.mFromLayout = this.f1486d;
            fragment.mRestored = true;
            fragment.mFragmentId = this.f1487e;
            fragment.mContainerId = this.f1488f;
            fragment.mTag = this.f1489g;
            fragment.mRetainInstance = this.f1490h;
            fragment.mRemoving = this.i;
            fragment.mDetached = this.j;
            fragment.mHidden = this.l;
            fragment.mMaxState = Lifecycle.State.values()[this.m];
            if (h.f1512d) {
                Log.v("FragmentManager", "Instantiated fragment " + this.o);
            }
        }
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f1484b);
        sb.append(" (");
        sb.append(this.f1485c);
        sb.append(")}:");
        if (this.f1486d) {
            sb.append(" fromLayout");
        }
        if (this.f1488f != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.f1488f));
        }
        String str = this.f1489g;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(this.f1489g);
        }
        if (this.f1490h) {
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
        parcel.writeString(this.f1484b);
        parcel.writeString(this.f1485c);
        parcel.writeInt(this.f1486d ? 1 : 0);
        parcel.writeInt(this.f1487e);
        parcel.writeInt(this.f1488f);
        parcel.writeString(this.f1489g);
        parcel.writeInt(this.f1490h ? 1 : 0);
        parcel.writeInt(this.i ? 1 : 0);
        parcel.writeInt(this.j ? 1 : 0);
        parcel.writeBundle(this.k);
        parcel.writeInt(this.l ? 1 : 0);
        parcel.writeBundle(this.n);
        parcel.writeInt(this.m);
    }

    FragmentState(Parcel parcel) {
        this.f1484b = parcel.readString();
        this.f1485c = parcel.readString();
        this.f1486d = parcel.readInt() != 0;
        this.f1487e = parcel.readInt();
        this.f1488f = parcel.readInt();
        this.f1489g = parcel.readString();
        this.f1490h = parcel.readInt() != 0;
        this.i = parcel.readInt() != 0;
        this.j = parcel.readInt() != 0;
        this.k = parcel.readBundle();
        this.l = parcel.readInt() != 0;
        this.n = parcel.readBundle();
        this.m = parcel.readInt();
    }
}
