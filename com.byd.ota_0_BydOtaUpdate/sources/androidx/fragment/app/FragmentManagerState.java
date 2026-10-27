package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<FragmentState> f509c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<String> f510d;
    public BackStackState[] s;
    public String x;
    public int y;

    public static class a implements Parcelable.Creator<FragmentManagerState> {
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
        this.x = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.f509c);
        parcel.writeStringList(this.f510d);
        parcel.writeTypedArray(this.s, i2);
        parcel.writeString(this.x);
        parcel.writeInt(this.y);
    }

    public FragmentManagerState(Parcel parcel) {
        this.x = null;
        this.f509c = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f510d = parcel.createStringArrayList();
        this.s = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.x = parcel.readString();
        this.y = parcel.readInt();
    }
}
