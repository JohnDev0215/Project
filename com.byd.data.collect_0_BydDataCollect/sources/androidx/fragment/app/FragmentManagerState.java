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
    ArrayList<FragmentState> f1387b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<String> f1388c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    BackStackState[] f1389d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1390e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    String f1391f;

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
        public FragmentManagerState[] newArray(int i2) {
            return new FragmentManagerState[i2];
        }
    }

    public FragmentManagerState() {
        this.f1391f = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.f1387b);
        parcel.writeStringList(this.f1388c);
        parcel.writeTypedArray(this.f1389d, i2);
        parcel.writeInt(this.f1390e);
        parcel.writeString(this.f1391f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f1391f = null;
        this.f1387b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1388c = parcel.createStringArrayList();
        this.f1389d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f1390e = parcel.readInt();
        this.f1391f = parcel.readString();
    }
}
