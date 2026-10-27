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
    public ArrayList<FragmentState> f1315b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<String> f1316c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BackStackState[] f1317d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f1318e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1319f;

    public static class a implements Parcelable.Creator<FragmentManagerState> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState createFromParcel(Parcel parcel) {
            return new FragmentManagerState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public FragmentManagerState[] newArray(int i3) {
            return new FragmentManagerState[i3];
        }
    }

    public FragmentManagerState() {
        this.f1318e = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeTypedList(this.f1315b);
        parcel.writeStringList(this.f1316c);
        parcel.writeTypedArray(this.f1317d, i3);
        parcel.writeString(this.f1318e);
        parcel.writeInt(this.f1319f);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f1318e = null;
        this.f1315b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f1316c = parcel.createStringArrayList();
        this.f1317d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f1318e = parcel.readString();
        this.f1319f = parcel.readInt();
    }
}
