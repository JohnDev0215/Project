package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    ArrayList<FragmentState> f2218e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    ArrayList<String> f2219g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    BackStackState[] f2220h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    int f2221i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    String f2222j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    ArrayList<String> f2223k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    ArrayList<Bundle> f2224l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    ArrayList<FragmentManager.LaunchedFragmentInfo> f2225m;

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
        public FragmentManagerState[] newArray(int i7) {
            return new FragmentManagerState[i7];
        }
    }

    public FragmentManagerState() {
        this.f2222j = null;
        this.f2223k = new ArrayList<>();
        this.f2224l = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeTypedList(this.f2218e);
        parcel.writeStringList(this.f2219g);
        parcel.writeTypedArray(this.f2220h, i7);
        parcel.writeInt(this.f2221i);
        parcel.writeString(this.f2222j);
        parcel.writeStringList(this.f2223k);
        parcel.writeTypedList(this.f2224l);
        parcel.writeTypedList(this.f2225m);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f2222j = null;
        this.f2223k = new ArrayList<>();
        this.f2224l = new ArrayList<>();
        this.f2218e = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f2219g = parcel.createStringArrayList();
        this.f2220h = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f2221i = parcel.readInt();
        this.f2222j = parcel.readString();
        this.f2223k = parcel.createStringArrayList();
        this.f2224l = parcel.createTypedArrayList(Bundle.CREATOR);
        this.f2225m = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
