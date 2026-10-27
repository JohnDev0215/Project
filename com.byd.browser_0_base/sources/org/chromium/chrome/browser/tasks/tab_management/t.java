package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.view.ViewGroup;
import defpackage.gz2;
import defpackage.nw2;
import defpackage.y13;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tasks.tab_management.m;

/* JADX INFO: compiled from: TabGroupUiCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t implements nw2<m.b> {
    public final /* synthetic */ u e;

    public t(u uVar) {
        this.e = uVar;
    }

    @Override // defpackage.nw2
    public final boolean f() {
        return this.e.w != null;
    }

    @Override // defpackage.nw2
    public final m.b get() {
        u uVar = this.e;
        if (uVar.w == null) {
            y13 y13Var = uVar.q;
            TabContentManager tabContentManager = uVar.u;
            gz2 gz2Var = uVar.s;
            int i = R$id.coordinator;
            Activity activity = uVar.e;
            uVar.w = new j(activity, y13Var, tabContentManager, gz2Var, (ViewGroup) activity.findViewById(i), null, null, null, uVar.k, uVar.y.e.E, uVar.p);
        }
        j jVar = uVar.w;
        jVar.getClass();
        return jVar;
    }
}
