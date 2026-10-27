package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class UiccCardInfo implements Parcelable {
    public static final Parcelable.Creator<UiccCardInfo> CREATOR = new Parcelable.Creator<UiccCardInfo>() { // from class: android.telephony.UiccCardInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UiccCardInfo createFromParcel(Parcel parcel) {
            return new UiccCardInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UiccCardInfo[] newArray(int i) {
            return new UiccCardInfo[i];
        }
    };
    private final int mCardId;
    private final String mEid;
    private final String mIccId;
    private final boolean mIsEuicc;
    private final boolean mIsRemovable;
    private final int mSlotIndex;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private UiccCardInfo(Parcel parcel) {
        this.mIsEuicc = parcel.readByte() != 0;
        this.mCardId = parcel.readInt();
        this.mEid = parcel.readString();
        this.mIccId = parcel.readString();
        this.mSlotIndex = parcel.readInt();
        this.mIsRemovable = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsEuicc ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mCardId);
        parcel.writeString(this.mEid);
        parcel.writeString(this.mIccId);
        parcel.writeInt(this.mSlotIndex);
        parcel.writeByte(this.mIsRemovable ? (byte) 1 : (byte) 0);
    }

    public UiccCardInfo(boolean z, int i, String str, String str2, int i2, boolean z2) {
        this.mIsEuicc = z;
        this.mCardId = i;
        this.mEid = str;
        this.mIccId = str2;
        this.mSlotIndex = i2;
        this.mIsRemovable = z2;
    }

    public boolean isEuicc() {
        return this.mIsEuicc;
    }

    public int getCardId() {
        return this.mCardId;
    }

    public String getEid() {
        if (this.mIsEuicc) {
            return this.mEid;
        }
        return null;
    }

    public String getIccId() {
        return this.mIccId;
    }

    public int getSlotIndex() {
        return this.mSlotIndex;
    }

    public UiccCardInfo getUnprivileged() {
        return new UiccCardInfo(this.mIsEuicc, this.mCardId, null, null, this.mSlotIndex, this.mIsRemovable);
    }

    public boolean isRemovable() {
        return this.mIsRemovable;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UiccCardInfo uiccCardInfo = (UiccCardInfo) obj;
        return this.mIsEuicc == uiccCardInfo.mIsEuicc && this.mCardId == uiccCardInfo.mCardId && Objects.equals(this.mEid, uiccCardInfo.mEid) && Objects.equals(this.mIccId, uiccCardInfo.mIccId) && this.mSlotIndex == uiccCardInfo.mSlotIndex && this.mIsRemovable == uiccCardInfo.mIsRemovable;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.mIsEuicc), Integer.valueOf(this.mCardId), this.mEid, this.mIccId, Integer.valueOf(this.mSlotIndex), Boolean.valueOf(this.mIsRemovable));
    }

    public String toString() {
        return "UiccCardInfo (mIsEuicc=" + this.mIsEuicc + ", mCardId=" + this.mCardId + ", mEid=" + this.mEid + ", mIccId=" + this.mIccId + ", mSlotIndex=" + this.mSlotIndex + ", mIsRemovable=" + this.mIsRemovable + ")";
    }
}
