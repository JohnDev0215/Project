package defpackage;

import defpackage.k23;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: TabModelSelectorTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i23 implements k23.b {
    public final /* synthetic */ j23 a;

    public i23(j23 j23Var) {
        this.a = j23Var;
    }

    @Override // k23.b
    public final void a(Tab tab) {
        j23 j23Var = this.a;
        if (j23Var.f) {
            j23Var.g.remove(tab);
            return;
        }
        if (!j23Var.h) {
            PostTask.c(rh3.a, new z8(this, 25, tab));
        } else if (!tab.isDestroyed()) {
            tab.t(j23Var);
            CriticalPersistedTabData.m(tab).x.c(j23Var);
        }
        j23Var.z1(tab);
    }

    @Override // k23.b
    public final void b(Tab tab) {
        if (tab.isDestroyed()) {
            return;
        }
        j23 j23Var = this.a;
        tab.x(j23Var);
        CriticalPersistedTabData.m(tab).x.a(j23Var);
        if (j23Var.f) {
            j23Var.g.add(tab);
        } else {
            j23Var.y1(tab);
        }
    }
}
