package defpackage;

import J.N;
import android.app.Activity;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: CustomTabDownloadObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class q30 extends nh0 {
    public final Activity e;
    public final o23 f;

    public q30(Activity activity, o23 o23Var) {
        this.e = activity;
        this.f = o23Var;
        o23Var.g.add(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if ((navigationHandle.d & (-939524096)) == 0) {
            this.f.g.remove(this);
            return;
        }
        sk skVar = bq.a;
        if (N.M09VlOh_("CCTNewDownloadTab") && navigationHandle.h) {
            y70 y70Var = new y70(this, 5, tabImpl);
            Objects.requireNonNull(tabImpl);
            boolean z = false;
            p30 p30Var = new p30(0, tabImpl);
            String strJ = tabImpl.v().j();
            WindowAndroid windowAndroid = tabImpl.e;
            dc0 dc0Var = new dc0(p30Var, strJ, a8.z(), xp2.a(windowAndroid), new z8(tabImpl, 8, y70Var));
            g80.b().a(y70Var);
            mk3 mk3Var = tabImpl.B;
            zl1 zl1Var = (zl1) mk3Var.b(zl1.class);
            if (zl1Var == null) {
                zl1Var = (zl1) mk3Var.d(zl1.class, new zl1(tabImpl, dc0Var, this.e));
            }
            Tab tab = zl1Var.e;
            tab.x(zl1Var);
            if (((dc0) zl1Var.g).e.a != null && tab.o().b(zl1Var)) {
                z = true;
            }
            if (z) {
                return;
            }
            zl1Var.e.o().a(zl1Var);
        }
    }
}
