package org.chromium.content.browser.accessibility.captioning;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public class CaptioningBridgeFactory {
    public static SystemCaptioningBridge getSystemCaptioningBridge(Context context) {
        return KitKatCaptioningBridge.getInstance(context);
    }
}
