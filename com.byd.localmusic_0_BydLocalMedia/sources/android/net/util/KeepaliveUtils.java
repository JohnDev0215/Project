package android.net.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkCapabilities;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import com.android.internal.R;

/* JADX INFO: loaded from: classes2.dex */
public final class KeepaliveUtils {
    public static final String TAG = "KeepaliveUtils";

    public static class KeepaliveDeviceConfigurationException extends AndroidRuntimeException {
        public KeepaliveDeviceConfigurationException(String str) {
            super(str);
        }
    }

    public static int[] getSupportedKeepalives(Context context) {
        String[] stringArray;
        try {
            stringArray = context.getResources().getStringArray(R.array.config_networkSupportedKeepaliveCount);
        } catch (Resources.NotFoundException unused) {
            stringArray = null;
        }
        if (stringArray == null) {
            throw new KeepaliveDeviceConfigurationException("invalid resource");
        }
        int[] iArr = new int[8];
        for (String str : stringArray) {
            if (TextUtils.isEmpty(str)) {
                throw new KeepaliveDeviceConfigurationException("Empty string");
            }
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length != 2) {
                throw new KeepaliveDeviceConfigurationException("Invalid parameter length");
            }
            try {
                int i = Integer.parseInt(strArrSplit[0]);
                int i2 = Integer.parseInt(strArrSplit[1]);
                if (!NetworkCapabilities.isValidTransport(i)) {
                    throw new KeepaliveDeviceConfigurationException("Invalid transport " + i);
                }
                if (i2 < 0) {
                    throw new KeepaliveDeviceConfigurationException("Invalid supported count " + i2 + " for " + NetworkCapabilities.transportNameOf(i));
                }
                iArr[i] = i2;
            } catch (NumberFormatException unused2) {
                throw new KeepaliveDeviceConfigurationException("Invalid number format");
            }
        }
        return iArr;
    }

    public static int getSupportedKeepalivesForNetworkCapabilities(int[] iArr, NetworkCapabilities networkCapabilities) {
        int[] transportTypes = networkCapabilities.getTransportTypes();
        if (transportTypes.length == 0) {
            return 0;
        }
        int i = iArr[transportTypes[0]];
        for (int i2 : transportTypes) {
            if (i > iArr[i2]) {
                i = iArr[i2];
            }
        }
        return i;
    }
}
