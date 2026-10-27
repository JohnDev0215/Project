package defpackage;

import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabModelSelectorTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public class j23 extends nh0 implements z10 {
    public final k23 e;
    public final boolean f;
    public final ArrayList g = new ArrayList();
    public boolean h;

    public j23(y13 y13Var) {
        k23 k23Var = new k23(y13Var);
        this.e = k23Var;
        this.f = true;
        k23Var.b(new i23(this));
        this.f = false;
        ThreadUtils.c().postAtFrontOfQueue(new eu1(23, this));
    }

    public void destroy() {
        this.h = true;
        k23 k23Var = this.e;
        k23Var.a.a();
        for (Tab tab : (Tab[]) k23Var.d.toArray(new Tab[0])) {
            k23Var.c(tab);
        }
    }

    public void y1(Tab tab) {
    }

    public void z1(Tab tab) {
    }
}
