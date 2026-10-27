package android.net.shared;

import android.net.NetworkCapabilities;

/* JADX INFO: loaded from: classes.dex */
public class NetworkMonitorUtils {
    public static boolean isPrivateDnsValidationRequired(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return false;
        }
        if (networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(13) && networkCapabilities.hasCapability(14)) {
            return true;
        }
        return networkCapabilities.hasTransport(7) && networkCapabilities.hasCapability(13) && (networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(2) || networkCapabilities.hasTransport(3));
    }

    public static boolean isValidationRequired(NetworkCapabilities networkCapabilities) {
        return isPrivateDnsValidationRequired(networkCapabilities) && networkCapabilities.hasCapability(15);
    }
}
