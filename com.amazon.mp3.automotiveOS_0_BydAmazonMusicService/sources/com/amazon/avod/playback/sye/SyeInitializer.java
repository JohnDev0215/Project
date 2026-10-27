package com.amazon.avod.playback.sye;

import android.content.Context;
import com.netinsight.sye.syeClient.SyeAPI;
import com.netinsight.sye.syeClient.SyeAPIConfig;

/* JADX INFO: loaded from: classes2.dex */
class SyeInitializer {
    private static boolean mHasInitialized = false;

    SyeInitializer() {
    }

    static synchronized void setup(Context context, SyeAPIConfig syeAPIConfig) {
        if (!mHasInitialized) {
            SyeAPI.setup(context, syeAPIConfig);
            mHasInitialized = true;
        }
    }
}
