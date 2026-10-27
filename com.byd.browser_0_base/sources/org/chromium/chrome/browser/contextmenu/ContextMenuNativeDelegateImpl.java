package org.chromium.chrome.browser.contextmenu;

import J.N;
import defpackage.uz;
import defpackage.wy2;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class ContextMenuNativeDelegateImpl implements uz {
    public final RenderFrameHost a;
    public long b;

    public static class ImageCallbackResult {
        public final byte[] a;
        public final String b;

        public ImageCallbackResult(String str, byte[] bArr) {
            this.a = bArr;
            this.b = str;
        }
    }

    public ContextMenuNativeDelegateImpl(WebContents webContents, RenderFrameHost renderFrameHost, ContextMenuParams contextMenuParams) {
        this.a = renderFrameHost;
        this.b = N.Mz9Ykykf(webContents, contextMenuParams);
    }

    @CalledByNative
    public static ImageCallbackResult createImageCallbackResult(byte[] bArr, String str) {
        return new ImageCallbackResult(str, bArr);
    }

    public final void a(Callback callback, int i) {
        long j = this.b;
        if (j == 0) {
            return;
        }
        N.M4wUt4Cl(j, this, this.a, new wy2(callback, 1), 2048, 2048, i);
    }
}
