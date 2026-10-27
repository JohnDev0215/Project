package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class AvailableNetworkInfo implements Parcelable {
    public static final Parcelable.Creator<AvailableNetworkInfo> CREATOR = new Parcelable.Creator<AvailableNetworkInfo>() { // from class: android.telephony.AvailableNetworkInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvailableNetworkInfo createFromParcel(Parcel parcel) {
            return new AvailableNetworkInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvailableNetworkInfo[] newArray(int i) {
            return new AvailableNetworkInfo[i];
        }
    };
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = 3;
    public static final int PRIORITY_MED = 2;
    private ArrayList<Integer> mBands;
    private ArrayList<String> mMccMncs;
    private int mPriority;
    private int mSubId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public List<String> getMccMncs() {
        return (List) this.mMccMncs.clone();
    }

    public List<Integer> getBands() {
        return (List) this.mBands.clone();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSubId);
        parcel.writeInt(this.mPriority);
        parcel.writeStringList(this.mMccMncs);
        parcel.writeList(this.mBands);
    }

    private AvailableNetworkInfo(Parcel parcel) {
        this.mSubId = parcel.readInt();
        this.mPriority = parcel.readInt();
        ArrayList<String> arrayList = new ArrayList<>();
        this.mMccMncs = arrayList;
        parcel.readStringList(arrayList);
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        this.mBands = arrayList2;
        parcel.readList(arrayList2, Integer.class.getClassLoader());
    }

    public AvailableNetworkInfo(int i, int i2, List<String> list, List<Integer> list2) {
        this.mSubId = i;
        this.mPriority = i2;
        this.mMccMncs = new ArrayList<>(list);
        this.mBands = new ArrayList<>(list2);
    }

    public boolean equals(Object obj) {
        ArrayList<String> arrayList;
        try {
            AvailableNetworkInfo availableNetworkInfo = (AvailableNetworkInfo) obj;
            return obj != null && this.mSubId == availableNetworkInfo.mSubId && this.mPriority == availableNetworkInfo.mPriority && (arrayList = this.mMccMncs) != null && arrayList.equals(availableNetworkInfo.mMccMncs) && this.mBands.equals(availableNetworkInfo.mBands);
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSubId), Integer.valueOf(this.mPriority), this.mMccMncs, this.mBands);
    }

    public String toString() {
        return "AvailableNetworkInfo: mSubId: " + this.mSubId + " mPriority: " + this.mPriority + " mMccMncs: " + Arrays.toString(this.mMccMncs.toArray()) + " mBands: " + Arrays.toString(this.mBands.toArray());
    }
}
