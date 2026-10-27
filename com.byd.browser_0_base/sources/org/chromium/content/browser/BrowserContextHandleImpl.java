package org.chromium.content.browser;

import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: loaded from: classes3.dex */
public class BrowserContextHandleImpl {
    @CalledByNative
    public static long getNativeBrowserContextPointer(BrowserContextHandle browserContextHandle) {
        return browserContextHandle.a();
    }
}
