package com.amazon.avod.connectivity;

import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum MobileNetworkClass implements Parcelable {
    TWO_G("2G"),
    THREE_G("3G"),
    FOUR_G("4G"),
    UNKNOWN("Unknown"),
    NONE("None");

    public static final Parcelable.Creator<NetworkType> CREATOR = new Parcelable.Creator<NetworkType>() { // from class: com.amazon.avod.connectivity.MobileNetworkClass.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkType createFromParcel(Parcel parcel) {
            return NetworkType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkType[] newArray(int i) {
            return new NetworkType[i];
        }
    };
    private final String shortName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    MobileNetworkClass(String str) {
        this.shortName = str;
    }

    @Nonnull
    public String getShortName() {
        return this.shortName;
    }

    @Nonnull
    public static MobileNetworkClass getClassByTelephonyManager(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return UNKNOWN;
        }
        try {
            return getClassByNetworkType(telephonyManager.getDataNetworkType());
        } catch (SecurityException unused) {
            return UNKNOWN;
        }
    }

    @Nonnull
    public static MobileNetworkClass getClassByNetworkInfo(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return UNKNOWN;
        }
        return getClassByNetworkType(networkInfo.getSubtype());
    }

    @Nonnull
    public static MobileNetworkClass getClassByNetworkType(int i) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return TWO_G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return THREE_G;
            case 13:
                return FOUR_G;
            default:
                return UNKNOWN;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
