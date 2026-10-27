package defpackage;

import J.N;
import android.text.TextUtils;
import defpackage.g83;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.share.a;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: LinkToTextCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s71 extends nh0 {
    public rq e;
    public Tab f;
    public jr g;
    public long h;
    public String i;
    public f83 j;
    public String k;
    public a l;
    public a m;
    public int n;

    public s71(Tab tab, rq rqVar, jr jrVar, long j, String str, String str2) {
        this.f = tab;
        this.e = rqVar;
        this.g = jrVar;
        this.h = j;
        this.i = str;
        this.k = str2;
        tab.x(this);
        this.n = 0;
    }

    public final void A1(String str) {
        String str2;
        a aVar;
        a aVar2;
        if (str.isEmpty()) {
            aVar = null;
        } else {
            WindowAndroid windowAndroidD = this.f.D();
            String title = this.f.getTitle();
            String strB = y71.b(this.i, str);
            String str3 = this.k;
            if (str3.length() <= 35) {
                str2 = this.k;
            } else {
                str2 = this.k.substring(0, 32) + "...";
            }
            String str4 = str2;
            Boolean bool = Boolean.TRUE;
            if (!TextUtils.isEmpty(strB) && !TextUtils.isEmpty(strB)) {
                strB = ((GURL) N.M1WDPiaY(strB)).j();
            }
            aVar = new a(windowAndroidD, title, str3, "\"%s\"\n", strB, null, null, null, null, null, null, bool, str4, "\"%s\"\n");
        }
        this.l = aVar;
        WindowAndroid windowAndroidD2 = this.f.D();
        String title2 = this.f.getTitle();
        String str5 = this.k;
        Boolean boolValueOf = Boolean.valueOf(!str.isEmpty());
        String strJ = "";
        if (!TextUtils.isEmpty("") && !TextUtils.isEmpty("")) {
            strJ = ((GURL) N.M1WDPiaY("")).j();
        }
        this.m = new a(windowAndroidD2, title2, str5, null, strJ, null, null, null, null, null, null, boolValueOf, null, null);
        rq rqVar = this.e;
        if (str.isEmpty() || (aVar2 = this.l) == null) {
            aVar2 = this.m;
        }
        rqVar.d(aVar2, this.g, this.h);
    }

    public final void B1() {
        RenderFrameHost renderFrameHost = this.g.i;
        g83.a aVar = g83.a;
        if (renderFrameHost != null && renderFrameHost.a()) {
            this.j = (f83) this.g.i.e(aVar);
        } else {
            if (this.f.c() == null || this.f.c().y() == null) {
                return;
            }
            this.j = (f83) this.f.c().y().e(aVar);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        if (this.g.g) {
            z1(4);
        } else {
            y1(8);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        if (this.g.g) {
            z1(2);
        } else {
            y1(6);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void k(GURL gurl) {
        if (this.g.g) {
            z1(3);
        } else {
            y1(7);
        }
    }

    public final void y1(int i) {
        f83 f83Var;
        N.MsDwTpRd(this.f.c(), i);
        if (i != 6 && i != 7 && i != 8) {
            A1("");
        }
        if (this.n == 1) {
            this.n = 3;
            if (!this.g.g && (f83Var = this.j) != null) {
                f83Var.cancel();
            }
        }
        f83 f83Var2 = this.j;
        if (f83Var2 != null) {
            f83Var2.close();
        }
        this.f.t(this);
    }

    public final void z1(int i) {
        f83 f83Var;
        N.MwdUorUl(i);
        if (i != 2 && i != 3 && i != 4) {
            A1("");
        }
        if (this.n == 1) {
            this.n = 3;
            if (!this.g.g && (f83Var = this.j) != null) {
                f83Var.cancel();
            }
        }
        f83 f83Var2 = this.j;
        if (f83Var2 != null) {
            f83Var2.close();
        }
        this.f.t(this);
    }
}
