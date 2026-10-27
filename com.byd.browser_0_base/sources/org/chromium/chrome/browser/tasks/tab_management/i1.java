package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.v33;
import defpackage.w33;
import defpackage.y13;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.z0;

/* JADX INFO: compiled from: TabSuggestionMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i1 extends w0 {
    public final /* synthetic */ v33 c;
    public final /* synthetic */ Callback d;
    public final /* synthetic */ m1 e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i1(m1 m1Var, z0.d dVar, int i, v33 v33Var, Callback callback) {
        super(dVar, i);
        this.e = m1Var;
        this.c = v33Var;
        this.d = callback;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.w0
    public final void a(ArrayList arrayList, y13 y13Var) {
        y13Var.z().getCount();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(Integer.valueOf(((Tab) arrayList.get(i)).getId()));
        }
        this.e.getClass();
        this.d.onResult(new w33(this.c, 2, arrayList2));
        super.a(arrayList, y13Var);
    }
}
