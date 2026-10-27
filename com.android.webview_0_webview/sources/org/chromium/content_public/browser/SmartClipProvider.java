package org.chromium.content_public.browser;

import android.os.Handler;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface SmartClipProvider {
    void extractSmartClipData(int i, int i2, int i3, int i4);

    void setSmartClipResultHandler(Handler handler);
}
