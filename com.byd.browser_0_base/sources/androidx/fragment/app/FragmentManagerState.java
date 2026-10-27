package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();
    public ArrayList<String> e;
    public ArrayList<String> f;
    public BackStackRecordState[] g;
    public int h;
    public String i;
    public final ArrayList<String> j;
    public final ArrayList<BackStackState> k;
    public ArrayList<FragmentManager.LaunchedFragmentInfo> l;

    public class a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        public final FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final FragmentManagerState[] newArray(int i) {
            return new FragmentManagerState[i];
        }
    }

    public FragmentManagerState() {
        this.i = null;
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.e);
        parcel.writeStringList(this.f);
        parcel.writeTypedArray(this.g, i);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeStringList(this.j);
        parcel.writeTypedList(this.k);
        parcel.writeTypedList(this.l);
    }

    public FragmentManagerState(Parcel parcel) {
        this.i = null;
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        this.e = parcel.createStringArrayList();
        this.f = parcel.createStringArrayList();
        this.g = (BackStackRecordState[]) parcel.createTypedArray(BackStackRecordState.CREATOR);
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.createStringArrayList();
        this.k = parcel.createTypedArrayList(BackStackState.CREATOR);
        this.l = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
