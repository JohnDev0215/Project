package defpackage;

import J.N;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.theme.ThemeColorProvider;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TopUiThemeColorProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xd3 extends ThemeColorProvider {
    public final i20 j;
    public final nw2<Integer> k;
    public final boolean l;
    public final Context m;
    public final boolean n;
    public final boolean o;
    public boolean p;

    public xd3(AppCompatActivity appCompatActivity, zp1 zp1Var, nw2 nw2Var, boolean z, boolean z2, boolean z3) {
        super(appCompatActivity);
        this.m = appCompatActivity;
        this.j = new i20(zp1Var, new wd3(this), new dr(12, this));
        this.k = nw2Var;
        this.l = z;
        this.n = z2;
        this.o = z3;
    }

    public static int e(Tab tab) {
        if (tab.isNativePage()) {
            return tab.u().r();
        }
        WebContents webContentsC = tab.c();
        RenderWidgetHostViewImpl renderWidgetHostViewImplB0 = webContentsC == null ? null : webContentsC.b0();
        int iMRWsmoin = renderWidgetHostViewImplB0 != null ? N.MRWsmoin(renderWidgetHostViewImplB0.a, renderWidgetHostViewImplB0) : 0;
        return iMRWsmoin != 0 ? iMRWsmoin : yp.b(tab.getContext(), false);
    }

    public final int c(int i, Tab tab) {
        int iIntValue;
        if (!(h(tab) && i != 0 && (this.o || !z7.S(i)))) {
            i = yp.a(this.m, tab.isIncognito());
            if (h(tab) && (iIntValue = this.k.get().intValue()) != 0) {
                i = iIntValue;
            }
        }
        return (-14737633) | i;
    }

    public final void d() {
        this.h.clear();
        this.i.clear();
        this.j.a();
    }

    public final int f(Tab tab) {
        jk1 jk1VarU = tab.u();
        int iC = c(tab.j(), tab);
        return jk1VarU != null ? jk1VarU.d(iC) : iC;
    }

    public final float g(Tab tab) {
        float f = z7.i0(c(tab.j(), tab)) ? 1.0f : 0.2f;
        jk1 jk1VarU = tab.u();
        return jk1VarU != null ? jk1VarU.f(f) : f;
    }

    public final boolean h(Tab tab) {
        return (!tab.F() || this.l || (!this.n && z7.I(tab.getContext())) || tab.isNativePage() || tab.isIncognito()) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [org.chromium.chrome.browser.theme.ThemeColorProvider, xd3] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [int] */
    /* JADX WARN: Type inference failed for: r3v8 */
    public final void j(int i, Tab tab, boolean z) {
        a(c(i, tab), z);
        boolean z2 = true;
        if ((h(tab) && i != 0 && (this.o || !z7.S(i))) || (h(tab) && this.k.get().intValue() != 0)) {
            z2 = false;
        }
        this.p = z2;
        b(tab.isIncognito() ? 2 : this.p ? 3 : z7.h0(this.e), n3.y0(this.m));
    }
}
