package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ArrayList<FragmentState> f1479b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<String> f1480c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    BackStackState[] f1481d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f1482e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f1483f;

    static class a implements Parcelable.Creator<FragmentManagerState> {
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
        this.f1482e = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1479b);
        parcel.writeStringList(this.f1480c);
        parcel.writeTypedArray(this.f1481d, i);
        parcel.writeString(this.f1482e);
        parcel.writeInt(this.f1483f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f1482e = null;
        this.f1479b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1480c = parcel.createStringArrayList();
        this.f1481d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f1482e = parcel.readString();
        this.f1483f = parcel.readInt();
    }
}
