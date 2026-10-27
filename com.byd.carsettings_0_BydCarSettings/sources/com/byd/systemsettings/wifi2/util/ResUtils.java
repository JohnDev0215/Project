package com.byd.systemsettings.wifi2.util;

import android.content.Context;
import com.byd.carsettings.R;
import com.byd.systemsettings.wifi2.model.CipherType;

/* JADX INFO: loaded from: classes.dex */
public final class ResUtils {
    public static int getRssiImgId(CipherType cipherType, int i) {
        if (cipherType == null) {
            return R.drawable.icon_public_wifilock_2_48;
        }
        if (cipherType.isSecurity()) {
            if (i == 0 || i == 1) {
                return R.drawable.icon_public_wifilock_1_48;
            }
            if (i == 2) {
                return R.drawable.icon_public_wifilock_2_48;
            }
            if (i == 3) {
                return R.drawable.icon_public_wifilock_3_48;
            }
            if (i == 4) {
                return R.drawable.icon_public_wifilock_4_48;
            }
        }
        return (i == 0 || i == 1) ? R.drawable.icon_public_wifi_1_48 : i != 2 ? i != 3 ? i != 4 ? R.drawable.icon_public_wifilock_2_48 : R.drawable.icon_public_wifi_4_48 : R.drawable.icon_public_wifi_3_48 : R.drawable.icon_public_wifi_2_48;
    }

    public static String getRssiText(Context context, int i) {
        return context == null ? "" : (i == 0 || i == 1) ? context.getString(R.string.rssi_level_1) : i != 2 ? i != 3 ? i != 4 ? context.getString(R.string.rssi_level_0) : context.getString(R.string.rssi_level_4) : context.getString(R.string.rssi_level_3) : context.getString(R.string.rssi_level_2);
    }
}
