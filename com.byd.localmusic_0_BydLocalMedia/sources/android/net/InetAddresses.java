package android.net;

import java.net.InetAddress;
import libcore.net.InetAddressUtils;

/* JADX INFO: loaded from: classes2.dex */
public class InetAddresses {
    private InetAddresses() {
    }

    public static boolean isNumericAddress(String str) {
        return InetAddressUtils.isNumericAddress(str);
    }

    public static InetAddress parseNumericAddress(String str) {
        return InetAddressUtils.parseNumericAddress(str);
    }
}
