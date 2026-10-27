package com.android.car.broadcastradio.support.platform;

import android.hardware.radio.RadioMetadata;

/* JADX INFO: loaded from: classes4.dex */
public class RadioMetadataExt {
    private static int sModuleId;

    public static void setModuleId(int i) {
        sModuleId = i;
    }

    public static long getGlobalBitmapId(RadioMetadata radioMetadata, String str) {
        int bitmapId = radioMetadata.getBitmapId(str);
        if (bitmapId == 0) {
            return 0L;
        }
        return ((long) bitmapId) | (((long) sModuleId) << 32);
    }
}
