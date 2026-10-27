package com.android.networkstack.tethering.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public class Inet4AddressUtils {
    public static Inet4Address intToInet4AddressHTH(int i) {
        try {
            return (Inet4Address) InetAddress.getByAddress(new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)});
        } catch (UnknownHostException unused) {
            throw new AssertionError();
        }
    }

    public static int inet4AddressToIntHTH(Inet4Address inet4Address) throws IllegalArgumentException {
        byte[] address = inet4Address.getAddress();
        return (address[3] & 255) | ((address[0] & 255) << 24) | ((address[1] & 255) << 16) | ((address[2] & 255) << 8);
    }
}
