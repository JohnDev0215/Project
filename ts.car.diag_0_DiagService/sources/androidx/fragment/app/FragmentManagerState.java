package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<FragmentState> f643c;
    ArrayList<String> d;
    BackStackState[] e;
    int f;
    String g;
    ArrayList<String> h;
    ArrayList<Bundle> i;
    ArrayList<FragmentManager.LaunchedFragmentInfo> j;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<FragmentManagerState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    }

    public FragmentManagerState() {
        this.g = null;
        this.h = new ArrayList<>();
        this.i = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f643c);
        parcel.writeStringList(this.d);
        parcel.writeTypedArray(this.e, i);
        parcel.writeInt(this.f);
        parcel.writeString(this.g);
        parcel.writeStringList(this.h);
        parcel.writeTypedList(this.i);
        parcel.writeTypedList(this.j);
    }

    public FragmentManagerState(Parcel parcel) {
        this.g = null;
        this.h = new ArrayList<>();
        this.i = new ArrayList<>();
        this.f643c = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.d = parcel.createStringArrayList();
        this.e = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f = parcel.readInt();
        this.g = parcel.readString();
        this.h = parcel.createStringArrayList();
        this.i = parcel.createTypedArrayList(Bundle.CREATOR);
        this.j = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
