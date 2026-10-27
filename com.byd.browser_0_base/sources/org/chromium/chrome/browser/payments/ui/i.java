package org.chromium.chrome.browser.payments.ui;

import defpackage.t13;
import defpackage.yq;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: PaymentUiService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements t13 {
    public final /* synthetic */ j e;

    public i(j jVar) {
        this.e = jVar;
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        if (tab.getId() != i2) {
            yq yqVar = (yq) this.e.o;
            yqVar.e.a(0);
            yqVar.b("Tab switch dismissed Payment Request UI.");
        }
    }
}
