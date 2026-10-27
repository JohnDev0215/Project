package com.android.server.util;

import com.android.networkstack.util.Inet4AddressUtils;
import java.net.Inet4Address;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkStackConstants {
    public static final Inet4Address IPV4_ADDR_ALL = Inet4AddressUtils.intToInet4AddressHTH(-1);
    public static final Inet4Address IPV4_ADDR_ANY = Inet4AddressUtils.intToInet4AddressHTH(0);
}
