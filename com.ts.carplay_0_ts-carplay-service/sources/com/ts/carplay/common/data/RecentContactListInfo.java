package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class RecentContactListInfo implements Parcelable {
    public static final Parcelable.Creator<RecentContactListInfo> CREATOR = new Parcelable.Creator<RecentContactListInfo>() { // from class: com.ts.carplay.common.data.RecentContactListInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentContactListInfo createFromParcel(Parcel in) {
            return new RecentContactListInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentContactListInfo[] newArray(int size) {
            return new RecentContactListInfo[size];
        }
    };
    public String mAddressBookId;
    public String mDisplayName;
    public int mDuration;
    public int mIndex;
    public String mLabel;
    public int mOccurrences;
    public String mRemoteId;
    public int mService;
    public int mType;
    public int mUnixTimestamp;

    public RecentContactListInfo() {
        this.mIndex = -1;
        this.mRemoteId = null;
        this.mDisplayName = null;
        this.mLabel = null;
        this.mAddressBookId = null;
        this.mService = -1;
        this.mType = -1;
        this.mUnixTimestamp = -1;
        this.mDuration = -1;
        this.mOccurrences = -1;
    }

    public RecentContactListInfo(Parcel in) {
        this.mIndex = in.readInt();
        this.mRemoteId = in.readString();
        this.mDisplayName = in.readString();
        this.mLabel = in.readString();
        this.mAddressBookId = in.readString();
        this.mService = in.readInt();
        this.mType = in.readInt();
        this.mUnixTimestamp = in.readInt();
        this.mDuration = in.readInt();
        this.mOccurrences = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mIndex);
        dest.writeString(this.mRemoteId);
        dest.writeString(this.mDisplayName);
        dest.writeString(this.mLabel);
        dest.writeString(this.mAddressBookId);
        dest.writeInt(this.mService);
        dest.writeInt(this.mType);
        dest.writeInt(this.mUnixTimestamp);
        dest.writeInt(this.mDuration);
        dest.writeInt(this.mOccurrences);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("RecentContactListInfo{");
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
        stringBuffer.append(",");
        stringBuffer.append("mType = ");
        stringBuffer.append(this.mType);
        stringBuffer.append(",");
        stringBuffer.append("mUnixTimestamp = ");
        stringBuffer.append(this.mUnixTimestamp);
        stringBuffer.append(",");
        stringBuffer.append("mDuration = ");
        stringBuffer.append(this.mDuration);
        stringBuffer.append(",");
        stringBuffer.append("mOccurrences = ");
        stringBuffer.append(this.mOccurrences);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
