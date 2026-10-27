package org.chromium.components.download.internal;

import android.content.Intent;
import android.content.IntentFilter;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public final class BatteryStatusListenerAndroid {
    @CalledByNative
    public static int getBatteryPercentage() {
        int intExtra;
        Intent intentG = xz.g(xz.a, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (intentG == null || (intExtra = intentG.getIntExtra("scale", -1)) == 0) {
            return 0;
        }
        return (int) Math.round((((double) intentG.getIntExtra("level", -1)) * 100.0d) / ((double) intExtra));
    }
}
