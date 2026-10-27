package com.byd.systemsettings.wifi2.util;

import android.content.Context;
import com.byd.systemsettings.R;
import com.byd.systemsettings.wifi2.model.CipherType;

/* JADX INFO: loaded from: classes2.dex */
public final class ResUtils {
    public static int getRssiImgId(CipherType cipherType, int i8) {
        if (cipherType == null) {
            return R.drawable.icon_public_wifilock_2_48;
        }
        if (cipherType.isSecurity()) {
            if (i8 == 0 || i8 == 1) {
                return R.drawable.icon_public_wifilock_1_48;
            }
            if (i8 == 2) {
                return R.drawable.icon_public_wifilock_2_48;
            }
            if (i8 == 3) {
                return R.drawable.icon_public_wifilock_3_48;
            }
            if (i8 == 4) {
                return R.drawable.icon_public_wifilock_4_48;
            }
        }
        return (i8 == 0 || i8 == 1) ? R.drawable.icon_public_wifi_1_48 : i8 != 2 ? i8 != 3 ? i8 != 4 ? R.drawable.icon_public_wifilock_2_48 : R.drawable.icon_public_wifi_4_48 : R.drawable.icon_public_wifi_3_48 : R.drawable.icon_public_wifi_2_48;
    }

    public static String getRssiText(Context context, int i8) {
        return context == null ? "" : (i8 == 0 || i8 == 1) ? context.getString(R.string.rssi_level_1) : i8 != 2 ? i8 != 3 ? i8 != 4 ? context.getString(R.string.rssi_level_0) : context.getString(R.string.rssi_level_4) : context.getString(R.string.rssi_level_3) : context.getString(R.string.rssi_level_2);
    }
}
