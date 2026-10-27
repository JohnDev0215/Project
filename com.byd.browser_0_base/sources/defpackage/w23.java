package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w23 implements t13 {
    public final /* synthetic */ z23 e;

    public w23(z23 z23Var) {
        this.e = z23Var;
    }

    @Override // defpackage.t13
    public final void C(Tab tab) {
        this.e.s();
    }

    @Override // defpackage.t13
    public final void X0(boolean z) {
        z23 z23Var = this.e;
        if (z) {
            z23Var.p = true;
        } else {
            z23Var.o = true;
        }
    }
}
