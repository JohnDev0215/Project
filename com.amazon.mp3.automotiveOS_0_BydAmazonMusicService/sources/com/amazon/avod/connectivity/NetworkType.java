package com.amazon.avod.connectivity;

import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum NetworkType implements Parcelable {
    NO_CONNECTION,
    WIFI,
    WAN,
    ETHERNET,
    OTHER;

    public static final Parcelable.Creator<NetworkType> CREATOR;
    private static final Map<Integer, NetworkType> NETWORK_TYPE_MAP;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    static {
        NetworkType networkType = WIFI;
        NetworkType networkType2 = WAN;
        NetworkType networkType3 = ETHERNET;
        HashMap map = new HashMap();
        NETWORK_TYPE_MAP = map;
        map.put(1, networkType);
        map.put(0, networkType2);
        map.put(9, networkType3);
        CREATOR = new Parcelable.Creator<NetworkType>() { // from class: com.amazon.avod.connectivity.NetworkType.1
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
    }

    public static NetworkType getTypeByNetworkInfo(@Nonnull NetworkInfo networkInfo) {
        int type = networkInfo.getType();
        Map<Integer, NetworkType> map = NETWORK_TYPE_MAP;
        if (!map.containsKey(Integer.valueOf(type))) {
            return OTHER;
        }
        return map.get(Integer.valueOf(type));
    }

    public boolean isNoConnectionType() {
        return this == NO_CONNECTION;
    }

    public boolean isWifiType() {
        return this == WIFI;
    }

    public boolean isWanType() {
        return this == WAN;
    }

    public boolean isEthernetType() {
        return this == ETHERNET;
    }

    public boolean isOtherType() {
        return this == OTHER;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
