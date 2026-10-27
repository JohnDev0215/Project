package org.chromium.content.browser.accessibility;

import J.N;
import defpackage.i9;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* JADX INFO: compiled from: WebContentsAccessibilityImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e extends i9<Void> {
    public final /* synthetic */ WebContentsAccessibilityImpl.b h;

    public e(WebContentsAccessibilityImpl.b bVar) {
        this.h = bVar;
    }

    @Override // defpackage.i9
    public final Void b() {
        WebContentsAccessibilityImpl.b bVar = this.h;
        N.MOP_btw0(WebContentsAccessibilityImpl.this.j);
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = WebContentsAccessibilityImpl.this;
        webContentsAccessibilityImpl.I.a();
        webContentsAccessibilityImpl.H.clear();
        return null;
    }

    @Override // defpackage.i9
    public final void l(Void r1) {
        WebContentsAccessibilityImpl.this.N = true;
    }
}
