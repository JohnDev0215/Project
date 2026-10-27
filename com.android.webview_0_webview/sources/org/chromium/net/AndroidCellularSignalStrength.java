package org.chromium.net;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import defpackage.RunnableC1432rK;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidCellularSignalStrength {
    public static final AndroidCellularSignalStrength b = new AndroidCellularSignalStrength();
    public volatile int a = Integer.MIN_VALUE;

    public AndroidCellularSignalStrength() {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("AndroidCellularSignalStrength");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new RunnableC1432rK(this));
    }

    public static int getSignalStrengthLevel() {
        return b.a;
    }
}
