package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import java.util.List;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.chrome.browser.infobar.InfoBarContainerView;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PageViewObserver.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"NewApi"})
public final class zu1 extends nh0 {
    public final Activity e;
    public final gi0 f;
    public final ob3 g;
    public final zw2 h;
    public final nw2<TabContentManager> i;
    public Tab j;
    public String k;

    public zu1(Activity activity, zp1 zp1Var, gi0 gi0Var, ob3 ob3Var, zw2 zw2Var, aq1 aq1Var) {
        this.e = activity;
        this.f = gi0Var;
        this.g = ob3Var;
        this.h = zw2Var;
        this.i = aq1Var;
        i20 i20Var = new i20(zp1Var, this, new jb1(27, this));
        i20Var.c.onResult((Tab) zp1Var.get());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        z1(null);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        z1(null);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Z(TabImpl tabImpl) {
        z1(tabImpl.getUrl());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void k(GURL gurl) {
        String strE = GURL.l(gurl) ? "" : gurl.e();
        s62<List<String>> s62Var = this.h.c;
        y1(strE, (s62Var == null || !s62Var.d()) ? false : s62Var.b.contains(strE));
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        if (tab.k() || tab.r()) {
            return;
        }
        z1(tab.getUrl());
    }

    public final boolean y1(String str, boolean z) {
        Tab tab = this.j;
        boolean z2 = false;
        if (tab == null) {
            return false;
        }
        ww2 ww2VarZ1 = ww2.z1(tab, this.i);
        if (z && str.equals(ww2VarZ1.h)) {
            return false;
        }
        if (!z) {
            if (!(ww2VarZ1.h != null)) {
                return false;
            }
        }
        if (!z) {
            ww2VarZ1.e.o().c(ww2VarZ1);
            ww2VarZ1.g = null;
            WebContents webContentsC = ww2VarZ1.e.c();
            if (webContentsC != null) {
                webContentsC.Y();
                webContentsC.A(false);
            }
            ww2VarZ1.g = null;
            ww2VarZ1.h = null;
            if (!this.j.k() && !hd2.z1(this.j)) {
                this.j.a();
            }
            return false;
        }
        ww2VarZ1.h = str;
        Tab tab2 = ww2VarZ1.e;
        tab2.x(ww2VarZ1);
        tab2.y();
        WebContents webContentsC2 = tab2.c();
        if (webContentsC2 != null) {
            webContentsC2.e();
            webContentsC2.f0();
            webContentsC2.A(true);
            if (N.MybJWOXK(webContentsC2) || N.MKIWbnaU(webContentsC2) || N.MDk3$bjp(webContentsC2)) {
                N.M3xnlzVW(webContentsC2);
            }
        }
        InfoBarContainer infoBarContainerD = InfoBarContainer.d(tab2);
        if (infoBarContainerD != null) {
            infoBarContainerD.o = true;
            InfoBarContainerView infoBarContainerView = infoBarContainerD.q;
            if (infoBarContainerView != null) {
                infoBarContainerView.setHidden(true);
            }
        }
        if (ww2VarZ1.g != null && tab2.o().b(ww2VarZ1)) {
            z2 = true;
        }
        if (z2) {
            ww2VarZ1.A1();
        } else {
            ww2VarZ1.y1();
        }
        TabContentManager tabContentManager = ww2VarZ1.f.get();
        if (tabContentManager != null) {
            ww2VarZ1.g.post(new z8(ww2VarZ1, 27, tabContentManager));
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z1(org.chromium.url.GURL r17) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zu1.z1(org.chromium.url.GURL):void");
    }
}
