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
    ArrayList<FragmentState> f1512b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<String> f1513c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    BackStackState[] f1514d;
    String e;
    int f;

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
        this.e = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f1512b);
        parcel.writeStringList(this.f1513c);
        parcel.writeTypedArray(this.f1514d, i);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.e = null;
        this.f1512b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1513c = parcel.createStringArrayList();
        this.f1514d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }
}
