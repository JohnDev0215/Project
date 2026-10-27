package org.chromium.components.media_router;

import defpackage.vd1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class TestMediaRouterClient extends vd1 {
    @CalledByNative
    public static void initialize() {
        if (vd1.e != null) {
            return;
        }
        vd1.e = new TestMediaRouterClient();
    }

    @Override // defpackage.vd1
    public final int h(WebContents webContents) {
        return 1;
    }
}
