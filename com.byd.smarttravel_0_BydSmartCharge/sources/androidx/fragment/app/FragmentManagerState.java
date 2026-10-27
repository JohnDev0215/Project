package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<FragmentState> f383b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<String> f384c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BackStackState[] f385d;
    public String e;
    public int f;

    public static class a implements Parcelable.Creator<FragmentManagerState> {
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
        parcel.writeTypedList(this.f383b);
        parcel.writeStringList(this.f384c);
        parcel.writeTypedArray(this.f385d, i);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.e = null;
        this.f383b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f384c = parcel.createStringArrayList();
        this.f385d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.e = parcel.readString();
        this.f = parcel.readInt();
    }
}
