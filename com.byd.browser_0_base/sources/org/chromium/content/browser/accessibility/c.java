package org.chromium.content.browser.accessibility;

import defpackage.i22;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* JADX INFO: compiled from: AutoDisableAccessibilityHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c {
    public final a a;
    public final i22 b = new i22(10, this);
    public boolean c;

    /* JADX INFO: compiled from: AutoDisableAccessibilityHandler.java */
    public interface a {
    }

    public c(WebContentsAccessibilityImpl.b bVar) {
        this.a = bVar;
    }

    public final void a() {
        if (this.c) {
            WebContentsAccessibilityImpl.this.n.removeCallbacks(this.b);
            this.c = false;
        }
    }
}
