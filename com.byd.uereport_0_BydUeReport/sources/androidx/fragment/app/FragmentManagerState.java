package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<FragmentState> f1400d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<String> f1401e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public BackStackState[] f1402f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f1403g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1404h;

    public static class a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState[] newArray(int i8) {
            return new FragmentManagerState[i8];
        }
    }

    public FragmentManagerState() {
        this.f1403g = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeTypedList(this.f1400d);
        parcel.writeStringList(this.f1401e);
        parcel.writeTypedArray(this.f1402f, i8);
        parcel.writeString(this.f1403g);
        parcel.writeInt(this.f1404h);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f1403g = null;
        this.f1400d = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1401e = parcel.createStringArrayList();
        this.f1402f = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f1403g = parcel.readString();
        this.f1404h = parcel.readInt();
    }
}
