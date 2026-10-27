package org.chromium.chrome.browser.logo;

import defpackage.oj2;
import org.chromium.chrome.browser.logo.LogoBridge;
import org.chromium.chrome.browser.logo.b;

/* JADX INFO: compiled from: LogoMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements LogoBridge.LogoObserver {
    public final /* synthetic */ long a;
    public final /* synthetic */ LogoBridge.LogoObserver b;
    public final /* synthetic */ b c;

    public c(b bVar, long j, b.a aVar) {
        this.c = bVar;
        this.a = j;
        this.b = aVar;
    }

    @Override // org.chromium.chrome.browser.logo.LogoBridge.LogoObserver
    public final void onCachedLogoRevalidated() {
        this.b.onCachedLogoRevalidated();
    }

    @Override // org.chromium.chrome.browser.logo.LogoBridge.LogoObserver
    public final void onLogoAvailable(LogoBridge.Logo logo, boolean z) {
        b bVar = this.c;
        if (bVar.h == null) {
            return;
        }
        if (logo != null) {
            int i = logo.d == null ? 0 : 1;
            oj2.U(i, 2, "NewTabPage.LogoShown");
            if (z) {
                oj2.U(i, 2, "NewTabPage.LogoShown.FromCache");
            } else {
                oj2.U(i, 2, "NewTabPage.LogoShown.Fresh");
            }
            if (bVar.v) {
                oj2.Y(System.currentTimeMillis() - this.a, "NewTabPage.LogoShownTime2");
                bVar.v = false;
            }
        } else if (!z) {
            bVar.v = false;
        }
        bVar.t = logo != null ? logo.b : null;
        bVar.u = logo != null ? logo.d : null;
        this.b.onLogoAvailable(logo, z);
    }
}
