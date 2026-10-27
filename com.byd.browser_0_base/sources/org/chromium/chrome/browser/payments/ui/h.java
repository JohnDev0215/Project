package org.chromium.chrome.browser.payments.ui;

import defpackage.e23;
import defpackage.yq;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: PaymentUiService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h implements e23 {
    public final /* synthetic */ j e;

    public h(j jVar) {
        this.e = jVar;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        yq yqVar = (yq) this.e.o;
        yqVar.e.a(0);
        yqVar.b("Tab switch dismissed Payment Request UI.");
    }
}
