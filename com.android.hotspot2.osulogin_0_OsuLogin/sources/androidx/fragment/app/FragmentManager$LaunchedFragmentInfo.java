package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
class FragmentManager$LaunchedFragmentInfo implements Parcelable {
    public static final Parcelable.Creator<FragmentManager$LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<FragmentManager$LaunchedFragmentInfo>() { // from class: androidx.fragment.app.FragmentManager$LaunchedFragmentInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManager$LaunchedFragmentInfo createFromParcel(Parcel parcel) {
            return new FragmentManager$LaunchedFragmentInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManager$LaunchedFragmentInfo[] newArray(int i) {
            return new FragmentManager$LaunchedFragmentInfo[i];
        }
    };
    int mRequestCode;
    String mWho;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    FragmentManager$LaunchedFragmentInfo(Parcel parcel) {
        this.mWho = parcel.readString();
        this.mRequestCode = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mWho);
        parcel.writeInt(this.mRequestCode);
    }
}
