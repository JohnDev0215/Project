package com.android.net.module.util;

import android.os.Parcel;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes4.dex */
public class InetAddressUtils {
    public static void parcelInetAddress(Parcel parcel, InetAddress inetAddress, int i) {
        parcel.writeByteArray(inetAddress != null ? inetAddress.getAddress() : null);
    }

    public static InetAddress unparcelInetAddress(Parcel parcel) {
        byte[] bArrCreateByteArray = parcel.createByteArray();
        if (bArrCreateByteArray == null) {
            return null;
        }
        try {
            return InetAddress.getByAddress(bArrCreateByteArray);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private InetAddressUtils() {
    }
}
