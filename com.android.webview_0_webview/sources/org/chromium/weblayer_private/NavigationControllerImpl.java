package org.chromium.weblayer_private;

import J.N;
import defpackage.AbstractC1054kS;
import defpackage.JR;
import defpackage.KR;
import defpackage.NR;
import org.chromium.weblayer_private.interfaces.NavigateParams;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class NavigationControllerImpl extends JR {
    public long a;
    public NR b;

    public NavigationControllerImpl(TabImpl tabImpl, NR nr) {
        this.b = nr;
        long jMuaFsixb = N.MuaFsixb(tabImpl.a);
        this.a = jMuaFsixb;
        N.MpejyhKI(jMuaFsixb, this);
    }

    private NavigationImpl createNavigation(long j) {
        return new NavigationImpl(this.b, j);
    }

    private void loadProgressChanged(double d) {
        ((KR) this.b).d(d);
    }

    private void loadStateChanged(boolean z, boolean z2) {
        ((KR) this.b).f(z, z2);
    }

    private void navigationCompleted(NavigationImpl navigationImpl) {
        ((KR) this.b).i(navigationImpl.a);
    }

    private void navigationFailed(NavigationImpl navigationImpl) {
        ((KR) this.b).u(navigationImpl.a);
    }

    private void navigationRedirected(NavigationImpl navigationImpl) {
        ((KR) this.b).v(navigationImpl.a);
    }

    private void navigationStarted(NavigationImpl navigationImpl) {
        ((KR) this.b).w(navigationImpl.a);
    }

    private void onFirstContentfulPaint() {
        ((KR) this.b).x();
    }

    private void readyToCommitNavigation(NavigationImpl navigationImpl) {
        ((KR) this.b).y(navigationImpl.a);
    }

    public void c(String str, NavigateParams navigateParams) {
        AbstractC1054kS.a();
        WebLayerFactoryImpl.c();
        if (navigateParams == null) {
            N.MdNsGAMH(this.a, this, str);
        } else {
            N.MRMbFqUg(this.a, this, str, navigateParams.s);
        }
    }
}
