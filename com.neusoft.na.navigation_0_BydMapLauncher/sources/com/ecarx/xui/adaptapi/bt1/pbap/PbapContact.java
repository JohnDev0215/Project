package com.ecarx.xui.adaptapi.bt1.pbap;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PbapContact implements Parcelable {
    public static final int ADDRESS_TYPE_DOM = 1;
    public static final int ADDRESS_TYPE_HOME = 6;
    public static final int ADDRESS_TYPE_INTL = 2;
    public static final int ADDRESS_TYPE_NULL = 0;
    public static final int ADDRESS_TYPE_PARCEL = 4;
    public static final int ADDRESS_TYPE_POSTAL = 3;
    public static final int ADDRESS_TYPE_WORK = 5;
    public static final Parcelable.Creator<PbapContact> CREATOR = new Parcelable.Creator<PbapContact>() { // from class: com.ecarx.xui.adaptapi.bt1.pbap.PbapContact.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PbapContact createFromParcel(Parcel parcel) {
            return new PbapContact(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.createIntArray(), parcel.createStringArray(), parcel.readInt(), parcel.createByteArray(), parcel.createIntArray(), parcel.createStringArray(), parcel.createIntArray(), parcel.createStringArray());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PbapContact[] newArray(int i) {
            return new PbapContact[i];
        }
    };
    public static final int EMAIL_TYPE_HOME = 5;
    public static final int EMAIL_TYPE_INTERNET = 2;
    public static final int EMAIL_TYPE_NULL = 0;
    public static final int EMAIL_TYPE_PREF = 1;
    public static final int EMAIL_TYPE_WORK = 4;
    public static final int EMAIL_TYPE_X400 = 3;
    public static final int NUMBER_TYPE_CELL = 7;
    public static final int NUMBER_TYPE_FAX = 5;
    public static final int NUMBER_TYPE_HOME = 3;
    public static final int NUMBER_TYPE_MSG = 6;
    public static final int NUMBER_TYPE_NULL = 0;
    public static final int NUMBER_TYPE_PAGER = 8;
    public static final int NUMBER_TYPE_PREF = 1;
    public static final int NUMBER_TYPE_VOICE = 4;
    public static final int NUMBER_TYPE_WORK = 2;
    public static final int STORAGE_TYPE_CALL_LOGS = 8;
    public static final int STORAGE_TYPE_DIALED_CALLS = 7;
    public static final int STORAGE_TYPE_FAVORITE = 4;
    public static final int STORAGE_TYPE_MISSED_CALLS = 5;
    public static final int STORAGE_TYPE_PHONE_MEMORY = 2;
    public static final int STORAGE_TYPE_RECEIVED_CALLS = 6;
    public static final int STORAGE_TYPE_SIM = 1;
    public static final int STORAGE_TYPE_SPEEDDIAL = 3;
    private final String[] mAddress;
    private final int[] mAddressType;
    private final String[] mEmail;
    private final int[] mEmailType;
    private final String mFirstName;
    private final String mLastName;
    private final String mMiddleName;
    private final String[] mNumber;
    private final int[] mPhoneType;
    private final byte[] mPhoto;
    private final int mPhotoType;
    private final String mRemoteAddress;
    private final int mStorageType;

    private String getAddressTypeString(int i) {
        switch (i) {
            case 0:
                return "Null";
            case 1:
                return "Pref";
            case 2:
                return "International";
            case 3:
                return "Postal";
            case 4:
                return "Parcel";
            case 5:
                return "Work";
            case 6:
                return "Home";
            default:
                return "Unknown";
        }
    }

    private String getEmailTypeString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "Unknown" : "Home" : "Work" : "X400" : "Internet" : "Pref" : "Null";
    }

    private String getNumberTypeString(int i) {
        switch (i) {
            case 0:
                return "Null";
            case 1:
                return "Pref";
            case 2:
                return "Work";
            case 3:
                return "Home";
            case 4:
                return "Voice";
            case 5:
                return "Fax";
            case 6:
                return "Msg";
            case 7:
                return "Cell";
            case 8:
                return "Pager";
            default:
                return "Unknown";
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PbapContact(String str, int i, String str2, String str3, String str4, int[] iArr, String[] strArr, int i2, byte[] bArr, int[] iArr2, String[] strArr2, int[] iArr3, String[] strArr3) {
        this.mRemoteAddress = str;
        this.mStorageType = i;
        this.mFirstName = str2;
        this.mMiddleName = str3;
        this.mLastName = str4;
        this.mPhoneType = iArr;
        this.mNumber = strArr;
        this.mPhotoType = i2;
        this.mPhoto = (bArr == null || bArr.length <= 0) ? null : bArr;
        this.mEmailType = iArr2;
        this.mEmail = strArr2;
        this.mAddressType = iArr3;
        this.mAddress = strArr3;
    }

    public String getRemoteAddress() {
        return this.mRemoteAddress;
    }

    public int getStorageType() {
        return this.mStorageType;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getMiddleName() {
        return this.mMiddleName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public int[] getPhoneTypeArray() {
        return this.mPhoneType;
    }

    public String[] getNumberArray() {
        return this.mNumber;
    }

    public int getPhotoType() {
        return this.mPhotoType;
    }

    public byte[] getPhotoByteArray() {
        return this.mPhoto;
    }

    public int[] getEmailTypeArray() {
        return this.mEmailType;
    }

    public String[] getEmailArray() {
        return this.mEmail;
    }

    public int[] getAddressType() {
        return this.mAddressType;
    }

    public String[] getAddressArray() {
        return this.mAddress;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRemoteAddress);
        parcel.writeInt(this.mStorageType);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mMiddleName);
        parcel.writeString(this.mLastName);
        parcel.writeIntArray(this.mPhoneType);
        parcel.writeStringArray(this.mNumber);
        parcel.writeInt(this.mPhotoType);
        byte[] bArr = this.mPhoto;
        if (bArr == null) {
            bArr = new byte[0];
        }
        parcel.writeByteArray(bArr);
        parcel.writeIntArray(this.mEmailType);
        parcel.writeStringArray(this.mEmail);
        parcel.writeIntArray(this.mAddressType);
        parcel.writeStringArray(this.mAddress);
    }
}
