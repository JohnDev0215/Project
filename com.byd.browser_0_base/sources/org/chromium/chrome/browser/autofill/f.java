package org.chromium.chrome.browser.autofill;

import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: CreditCardScanner.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public final a a;
    public final WebContents b;

    /* JADX INFO: compiled from: CreditCardScanner.java */
    public interface a {
    }

    public f(WebContents webContents, a aVar) {
        this.b = webContents;
        this.a = aVar;
    }
}
