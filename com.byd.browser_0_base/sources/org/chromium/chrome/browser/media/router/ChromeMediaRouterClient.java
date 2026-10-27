package org.chromium.chrome.browser.media.router;

import J.N;
import defpackage.vd1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeMediaRouterClient extends vd1 {
    @CalledByNative
    public static void initialize() {
        if (vd1.e != null) {
            return;
        }
        vd1.e = new ChromeMediaRouterClient();
    }

    @Override // defpackage.vd1
    public final int h(WebContents webContents) {
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(webContents);
        if (tabImpl == null) {
            return -1;
        }
        return tabImpl.getId();
    }
}
