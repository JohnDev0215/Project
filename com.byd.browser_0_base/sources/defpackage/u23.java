package defpackage;

import defpackage.p33;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class u23 implements p33.b {
    public final /* synthetic */ z23 a;

    public u23(z23 z23Var) {
        this.a = z23Var;
    }

    @Override // p33.b
    public final void a(int i, Tab tab) {
        if (i != 2 || tab.isDestroyed()) {
            return;
        }
        z23 z23Var = this.a;
        z23Var.b(tab);
        z23Var.r();
    }
}
