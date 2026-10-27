package defpackage;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import defpackage.j13;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: StripLayoutHelperManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class ev2 extends j23 {
    public final /* synthetic */ zu2 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ev2(zu2 zu2Var, y13 y13Var) {
        super(y13Var);
        this.i = zu2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        this.i.k(tabImpl.isIncognito()).B(tabImpl.getId());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i, Tab tab) {
        this.i.k(tab.isIncognito()).B(tab.getId());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void K(TabImpl tabImpl, boolean z) {
        fv2 fv2VarF = this.i.k(tabImpl.isIncognito()).f(tabImpl.getId());
        if (fv2VarF != null) {
            j13 j13Var = fv2VarF.c;
            if (!j13Var.c) {
                j13Var.c = true;
                ((h61) vu2.this.a).T(null);
            }
            j13Var.a.removeCallbacks(j13Var.e);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void L0(TabImpl tabImpl, boolean z) {
        fv2 fv2VarF = this.i.k(tabImpl.isIncognito()).f(tabImpl.getId());
        if (fv2VarF != null) {
            j13 j13Var = fv2VarF.c;
            if (j13Var.c) {
                Handler handler = j13Var.a;
                j13.a aVar = j13Var.e;
                handler.removeCallbacks(aVar);
                handler.postDelayed(aVar, 100L);
            }
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void X(Tab tab, Bitmap bitmap, GURL gurl) {
        zu2.d(this.i, tab);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        this.i.k(tab.isIncognito()).B(tab.getId());
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
        int i2 = loadUrlParams.d;
        if (i2 == 67108864 || (i2 & 33554432) == 33554432) {
            this.i.k(tab.isIncognito()).a(SystemClock.uptimeMillis(), true);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        fv2 fv2VarF = this.i.k(tab.isIncognito()).f(tab.getId());
        if (fv2VarF != null) {
            j13 j13Var = fv2VarF.c;
            if (!j13Var.d) {
                j13Var.d = true;
                ((h61) vu2.this.a).T(null);
            }
            j13Var.a.removeCallbacks(j13Var.f);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void r1(Tab tab) {
        zu2.d(this.i, tab);
    }
}
