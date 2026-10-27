package org.chromium.content.browser.accessibility.captioning;

import J.N;
import defpackage.C1477sA;
import defpackage.InterfaceC1532tA;
import defpackage.InterfaceC1587uA;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CaptioningController implements InterfaceC1532tA {
    public InterfaceC1587uA a;
    public long b;

    public CaptioningController(WebContents webContents) {
        if (C1477sA.d == null) {
            C1477sA.d = new C1477sA();
        }
        this.a = C1477sA.d;
        this.b = N.MX95jWaj(this, webContents);
    }

    private void onDestroy() {
        this.b = 0L;
    }

    private void onRenderProcessChange() {
        this.a.b(this);
    }
}
