package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList<FragmentState> f957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<String> f958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BackStackState[] f959d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f961f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList<String> f962g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList<Bundle> f963h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList<FragmentManager.LaunchedFragmentInfo> f964i;

    public class a implements Parcelable.Creator<FragmentManagerState> {
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
        this.f961f = null;
        this.f962g = new ArrayList<>();
        this.f963h = new ArrayList<>();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.f957b);
        parcel.writeStringList(this.f958c);
        parcel.writeTypedArray(this.f959d, i2);
        parcel.writeInt(this.f960e);
        parcel.writeString(this.f961f);
        parcel.writeStringList(this.f962g);
        parcel.writeTypedList(this.f963h);
        parcel.writeTypedList(this.f964i);
    }

    public FragmentManagerState(Parcel parcel) {
        this.f961f = null;
        this.f962g = new ArrayList<>();
        this.f963h = new ArrayList<>();
        this.f957b = parcel.createTypedArrayList(FragmentState.CREATOR);
        this.f958c = parcel.createStringArrayList();
        this.f959d = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.f960e = parcel.readInt();
        this.f961f = parcel.readString();
        this.f962g = parcel.createStringArrayList();
        this.f963h = parcel.createTypedArrayList(Bundle.CREATOR);
        this.f964i = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
