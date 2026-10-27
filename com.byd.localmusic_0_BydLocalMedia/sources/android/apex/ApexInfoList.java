package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ApexInfoList implements Parcelable {
    public static final Parcelable.Creator<ApexInfoList> CREATOR = new Parcelable.Creator<ApexInfoList>() { // from class: android.apex.ApexInfoList.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexInfoList createFromParcel(Parcel parcel) {
            ApexInfoList apexInfoList = new ApexInfoList();
            apexInfoList.readFromParcel(parcel);
            return apexInfoList;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexInfoList[] newArray(int i) {
            return new ApexInfoList[i];
        }
    };
    public ApexInfo[] apexInfos;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeTypedArray(this.apexInfos, 0);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.apexInfos = (ApexInfo[]) parcel.createTypedArray(ApexInfo.CREATOR);
            if (parcel.dataPosition() - iDataPosition >= i) {
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
