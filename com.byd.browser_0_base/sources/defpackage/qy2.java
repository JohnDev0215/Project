package defpackage;

import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabBrowserControlsOffsetHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qy2 extends nh0 implements lk3 {
    public TabImpl e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;

    public qy2(Tab tab) {
        this.e = (TabImpl) tab;
        tab.x(this);
    }

    public static qy2 y1(Tab tab) {
        qy2 qy2Var = (qy2) tab.B().b(qy2.class);
        if (qy2Var != null) {
            return qy2Var;
        }
        qy2 qy2Var2 = new qy2(tab);
        tab.B().d(qy2.class, qy2Var2);
        return qy2Var2;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.k = false;
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
        this.e = null;
    }

    public final void z1() {
        this.k = true;
        b.a aVarN = this.e.N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).D(this.e, this.f, this.g, this.h, this.i, this.j);
        }
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
