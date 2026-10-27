package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteContactListInfo implements Parcelable {
    public static final Parcelable.Creator<FavoriteContactListInfo> CREATOR = new Parcelable.Creator<FavoriteContactListInfo>() { // from class: com.ts.carplay.common.data.FavoriteContactListInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoriteContactListInfo createFromParcel(Parcel in) {
            return new FavoriteContactListInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FavoriteContactListInfo[] newArray(int size) {
            return new FavoriteContactListInfo[size];
        }
    };
    public String mAddressBookId;
    public String mDisplayName;
    public int mIndex;
    public String mLabel;
    public String mRemoteId;
    public int mService;

    public FavoriteContactListInfo() {
        this.mIndex = -1;
        this.mRemoteId = null;
        this.mDisplayName = null;
        this.mLabel = null;
        this.mAddressBookId = null;
        this.mService = -1;
    }

    public FavoriteContactListInfo(Parcel in) {
        this.mIndex = in.readInt();
        this.mRemoteId = in.readString();
        this.mDisplayName = in.readString();
        this.mLabel = in.readString();
        this.mAddressBookId = in.readString();
        this.mService = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mIndex);
        dest.writeString(this.mRemoteId);
        dest.writeString(this.mDisplayName);
        dest.writeString(this.mLabel);
        dest.writeString(this.mAddressBookId);
        dest.writeInt(this.mService);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FavoriteContactListInfo{");
        stringBuffer.append("mIndex = ");
        stringBuffer.append(this.mIndex);
        stringBuffer.append(",");
        stringBuffer.append("mRemoteId = ");
        stringBuffer.append(this.mRemoteId);
        stringBuffer.append(",");
        stringBuffer.append("mDisplayName = ");
        stringBuffer.append(this.mDisplayName);
        stringBuffer.append(",");
        stringBuffer.append("mLabel = ");
        stringBuffer.append(this.mLabel);
        stringBuffer.append(",");
        stringBuffer.append("mAddressBookId = ");
        stringBuffer.append(this.mAddressBookId);
        stringBuffer.append(",");
        stringBuffer.append("mService = ");
        stringBuffer.append(this.mService);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
