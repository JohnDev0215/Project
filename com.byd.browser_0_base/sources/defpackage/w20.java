package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.a30;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: compiled from: CustomTabActivityNavigationController.java */
/* JADX INFO: loaded from: classes.dex */
public final class w20 implements kr2 {
    public final x20 e;
    public final a30 f;
    public final oj g;
    public final p61<b40> h;
    public final lu i;
    public final sp j;
    public final Activity k;
    public final b l;
    public g m;
    public c n;
    public boolean o;
    public boolean p;
    public int q;
    public final a r;

    /* JADX INFO: compiled from: CustomTabActivityNavigationController.java */
    public class a extends a30.a {
        public a() {
        }

        @Override // a30.a
        public final void a() {
            w20 w20Var = w20.this;
            w20Var.a(w20Var.p ? 0 : 2);
        }
    }

    /* JADX INFO: compiled from: CustomTabActivityNavigationController.java */
    public interface b {
        String a();
    }

    /* JADX INFO: compiled from: CustomTabActivityNavigationController.java */
    public interface c {
    }

    public w20(x20 x20Var, a30 a30Var, oj ojVar, p61 p61Var, lu luVar, sp spVar, Activity activity, q2 q2Var, b bVar) {
        a aVar = new a();
        this.r = aVar;
        this.e = x20Var;
        this.f = a30Var;
        this.g = ojVar;
        this.h = p61Var;
        this.i = luVar;
        this.j = spVar;
        this.k = activity;
        this.l = bVar;
        q2Var.f(this);
        a30Var.a.a(aVar);
    }

    public final void a(int i) {
        jy2 jy2Var;
        if (this.o) {
            return;
        }
        int i2 = 1;
        this.o = true;
        this.q = i;
        if (i != 1) {
            PostTask.b(rh3.a, new qe3(7), 500L);
        }
        c cVar = this.n;
        if (cVar != null) {
            z70 z70Var = (z70) cVar;
            BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) z70Var.f;
            u20 u20Var = (u20) z70Var.g;
            int i3 = BaseCustomTabActivity.o1;
            if (i == 0) {
                baseCustomTabActivity.getClass();
                Tab tab = u20Var.g.b;
                String str = null;
                if (tab != null && (jy2Var = (jy2) tab.B().b(jy2.class)) != null) {
                    str = jy2Var.f;
                }
                if (str != null) {
                    if (str.equals(u20Var.e.d(u20Var.f.z()))) {
                        i2 = u20Var.h ? 3 : 2;
                    } else if (!u20Var.h) {
                        i2 = 0;
                    }
                    bs0 bs0Var = bs0.c;
                    if ("com.google.android.googlequicksearchbox".equals(str)) {
                        oj2.U(i2, 4, "CustomTabs.ConnectionStatusOnReturn.GSA");
                    } else {
                        oj2.U(i2, 4, "CustomTabs.ConnectionStatusOnReturn.NonGSA");
                    }
                }
            }
            baseCustomTabActivity.g3();
        }
    }

    public final void b(long j, LoadUrlParams loadUrlParams) {
        Tab tab = this.f.b;
        if (tab == null || tab.isDestroyed()) {
            return;
        }
        oj ojVar = this.g;
        int i = 1;
        if (ojVar.M() == null) {
            b40 b40Var = this.h.get();
            b40Var.k = j;
            if (tab.k()) {
                b40Var.l = -1L;
                b40Var.n = 2;
            } else {
                b40Var.n = 1;
            }
        }
        Intent intentT = ojVar.t();
        String strI = org.chromium.chrome.browser.b.i(intentT);
        if (strI != null) {
            int iL = q11.l(intentT, "android.support.browser.extra.referrer_policy", 1);
            if (iL >= 0 && iL < 8) {
                i = iL;
            }
            loadUrlParams.e = new on2(strI, i);
        }
        String strG = org.chromium.chrome.browser.b.g(intentT);
        if (strG != null) {
            loadUrlParams.g = strG;
        }
        loadUrlParams.d = org.chromium.chrome.browser.b.m(ojVar.t(), (ojVar.S() || ojVar.U()) ? 134217734 : 134217728);
        sk skVar = bq.a;
        if (N.M09VlOh_("OpaqueOriginForIncomingIntents")) {
            loadUrlParams.b = (Origin) N.MWkeKQbk();
        }
        tab.b(loadUrlParams);
    }

    public final boolean c() {
        a30 a30Var = this.f;
        Tab tab = a30Var.b;
        if (tab == null) {
            return false;
        }
        GURL url = tab.getUrl();
        if (a8.M(url)) {
            url = a8.D(url);
        }
        String strJ = url.j();
        boolean zIsEmpty = TextUtils.isEmpty(strJ);
        oj ojVar = this.g;
        if (zIsEmpty) {
            strJ = ojVar.K();
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(strJ));
        intent.setFlags(268435456);
        intent.putExtra("com.android.chrome.from_open_in_browser", true);
        String strA = this.l.a();
        Activity activity = this.k;
        if (strA != null) {
            intent.setPackage(strA);
            if (intent.resolveActivity(activity.getPackageManager()) == null) {
                intent.setPackage(null);
            }
        }
        boolean z = ojVar.N() || (ojVar instanceof xx0);
        boolean z2 = (ojVar.S() || ojVar.U()) ? false : true;
        boolean zG = z | cj0.g(intent, true);
        Bundle bundle = ActivityOptions.makeCustomAnimation(activity, R$anim.abc_fade_in, R$anim.abc_fade_out).toBundle();
        if (z2 && zG) {
            a30Var.a.c(this.r);
            l10 l10Var = new l10(3, this);
            x20 x20Var = this.e;
            a30 a30Var2 = x20Var.r;
            Tab tab2 = a30Var2.b;
            if (tab2 != null) {
                if (x20Var.m.c().z().getCount() <= 1) {
                    a30Var2.a();
                }
                x20Var.s.getClass();
                bb2.b(tab2).c(intent, l10Var);
                x20Var.f.startActivity(intent, bundle);
            }
        } else {
            if (ojVar.J() == 2) {
                org.chromium.chrome.browser.b.A(null, intent, ChromeLauncherActivity.class.getName());
            } else {
                activity.startActivity(intent, bundle);
            }
        }
        return true;
    }

    @Override // defpackage.kr2
    public final void j() {
        this.o = false;
    }

    @Override // defpackage.kr2
    public final void l() {
        boolean z = this.o;
        x20 x20Var = this.e;
        if (!z) {
            z20 z20Var = x20Var.m;
            if (z20Var.k == null) {
                z20Var.k = new k50();
            }
            z20Var.k.f();
            return;
        }
        z20 z20Var2 = x20Var.m;
        if (z20Var2.c().z().getCount() > 0) {
            x20Var.e(x20Var.r.b);
        }
        z20Var2.c().e(true);
        h40 h40Var = x20Var.l;
        h40Var.d.b(new l10(1, h40Var));
    }
}
