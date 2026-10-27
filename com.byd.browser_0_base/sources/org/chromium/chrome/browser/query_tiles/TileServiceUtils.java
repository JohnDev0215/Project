package org.chromium.chrome.browser.query_tiles;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.AppHooks;

/* JADX INFO: loaded from: classes2.dex */
public class TileServiceUtils {
    @CalledByNative
    public static String getDefaultServerUrl() {
        AppHooks.get().getClass();
        return "";
    }
}
