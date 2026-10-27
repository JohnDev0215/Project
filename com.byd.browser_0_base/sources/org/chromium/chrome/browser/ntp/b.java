package org.chromium.chrome.browser.ntp;

import org.chromium.chrome.browser.ntp.a;

/* JADX INFO: compiled from: IncognitoNewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements a.InterfaceC0096a {
    public final /* synthetic */ c a;

    public b(c cVar) {
        this.a = cVar;
    }

    @Override // org.chromium.chrome.browser.ntp.a.InterfaceC0096a
    public final void a(int i, boolean z) {
        c cVar = this.a;
        cVar.a.m.setIncognitoCookieControlsToggleEnforcement(i);
        cVar.a.m.setIncognitoCookieControlsToggleChecked(z);
    }
}
