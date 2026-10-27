package org.chromium.chrome.browser.toolbar.adaptive;

import android.util.Pair;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class AdaptiveToolbarBridge {
    @CalledByNative
    public static Object createResult(boolean z, int i) {
        return new Pair(Boolean.valueOf(z), Integer.valueOf(i));
    }
}
