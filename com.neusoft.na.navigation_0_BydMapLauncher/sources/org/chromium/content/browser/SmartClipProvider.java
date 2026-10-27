package org.chromium.content.browser;

import android.os.Handler;

/* JADX INFO: loaded from: classes3.dex */
public interface SmartClipProvider {
    void extractSmartClipData(int i, int i2, int i3, int i4);

    void setSmartClipResultHandler(Handler handler);
}
