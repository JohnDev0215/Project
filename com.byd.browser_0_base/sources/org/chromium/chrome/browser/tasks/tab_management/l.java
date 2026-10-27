package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.e23;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabGridDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l implements e23 {
    public final /* synthetic */ m e;

    public l(m mVar) {
        this.e = mVar;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        m mVar = this.e;
        mVar.k(mVar.e, tabModel.isIncognito());
    }
}
