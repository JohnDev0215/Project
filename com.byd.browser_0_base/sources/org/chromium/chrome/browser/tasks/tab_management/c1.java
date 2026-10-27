package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.e23;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: TabSelectionEditorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c1 implements e23 {
    public final /* synthetic */ a1 e;

    public c1(a1 a1Var) {
        this.e = a1Var;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        this.e.h(tabModel.isIncognito());
    }
}
