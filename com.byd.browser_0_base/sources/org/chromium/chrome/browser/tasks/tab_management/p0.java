package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.t32;
import defpackage.xl2;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class p0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ p0(Object obj, int i, boolean z) {
        this.e = i;
        this.g = obj;
        this.f = z;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        t32 t32Var;
        int i = this.e;
        boolean z = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                TabListRecyclerView tabListRecyclerView = (TabListRecyclerView) obj2;
                tabListRecyclerView.c1 = false;
                tabListRecyclerView.p0(z);
                break;
            default:
                Callback callback = (Callback) obj2;
                xl2 xl2Var = (xl2) obj;
                if (xl2Var != null) {
                    if (z && (t32Var = xl2Var.p) != null) {
                        t32Var.a(System.currentTimeMillis() - CriticalPersistedTabData.m(xl2Var.e).r, "EnterTabSwitcher");
                    }
                    callback.onResult(xl2Var.q());
                } else {
                    callback.onResult(null);
                }
                break;
        }
    }
}
