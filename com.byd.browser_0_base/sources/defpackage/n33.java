package defpackage;

import J.N;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabStateAttributes.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n33 extends nh0 {
    public final /* synthetic */ p33 e;

    public n33(p33 p33Var) {
        this.e = p33Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void D0(Tab tab, boolean z) {
        sk skVar = bq.a;
        if (!N.M09VlOh_("TabStateV1Optimizations") || z) {
            return;
        }
        p33 p33Var = this.e;
        if (p33Var.j == 1) {
            p33Var.f(2);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void K0(Tab tab) {
        this.e.f(2);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void L0(TabImpl tabImpl, boolean z) {
        p33 p33Var = this.e;
        if (p33Var.j != 1) {
            return;
        }
        sk skVar = bq.a;
        if (!N.M09VlOh_("TabStateV1Optimizations") || z) {
            p33Var.f(2);
        } else {
            if (p33Var.l) {
                return;
            }
            p33Var.l = true;
            PostTask.b(rh3.a, new f52(12, this), 30000L);
        }
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid == null) {
            return;
        }
        this.e.f(1);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        sk skVar = bq.a;
        boolean zM09VlOh_ = N.M09VlOh_("TabStateV1Optimizations");
        p33 p33Var = this.e;
        if (!zM09VlOh_) {
            if (p33Var.j == 1) {
                p33Var.f(2);
            }
        } else {
            if (p33Var.g.n() || p33Var.j != 1) {
                return;
            }
            p33Var.f(2);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        this.e.f(1);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void r1(Tab tab) {
        this.e.f(1);
    }
}
