package defpackage;

import J.N;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ak;
import defpackage.ck;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.b;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: AdaptiveToolbarButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m3 implements ck, ck.a, hk1, SharedPreferencesManager.b, bx {
    public ck f;
    public ak.a i;
    public boolean j;
    public final q2 k;
    public final p3 l;
    public final SharedPreferencesManager m;
    public xq1 n;
    public final n62 o;
    public final j3 p;
    public int q;
    public i20 s;
    public final b<ck.a> e = new b<>();
    public final HashMap g = new HashMap();
    public final bk h = new bk();
    public int r = 0;

    public m3(AppCompatActivity appCompatActivity, n3 n3Var, q2 q2Var, j3 j3Var, g3 g3Var, SharedPreferencesManager sharedPreferencesManager) {
        this.o = new n62(n3Var, 18, appCompatActivity);
        this.k = q2Var;
        q2Var.f(this);
        this.l = new p3(g3Var);
        this.p = j3Var;
        this.m = sharedPreferencesManager;
        sharedPreferencesManager.a(this);
        this.q = appCompatActivity.getResources().getConfiguration().screenWidthDp;
    }

    @Override // defpackage.hk1
    public final void G0() {
        if (n3.H0()) {
            this.l.b(new dr(15, this));
            int i = 5;
            N.MNlIGBvD(Profile.d(), new wy2(new ad0(new xo(8), i), i));
            if (this.n != null) {
                return;
            }
            xq1 xq1VarG = g();
            this.n = xq1VarG;
            if (xq1VarG == null) {
                return;
            }
            this.i = null;
            m(this.h.a);
        }
    }

    @Override // ck.a
    public final void a(boolean z) {
        m(z);
    }

    @Override // defpackage.ck
    public final void b(ck.a aVar) {
        this.e.c(aVar);
    }

    @Override // defpackage.ck
    public final bk c(Tab tab) {
        ck ckVar = this.f;
        bk bkVarC = ckVar == null ? null : ckVar.c(tab);
        if (bkVarC == null) {
            this.i = null;
            return null;
        }
        if (!this.j && bkVarC.a && bkVarC.b) {
            ak.a aVar = bkVarC.c;
            if (!aVar.h) {
                this.j = true;
                oj2.U(aVar.g, 8, "Android.AdaptiveToolbarButton.SessionVariant");
            }
        }
        boolean z = bkVarC.a && k();
        bk bkVar = this.h;
        bkVar.a = z;
        bkVar.b = bkVarC.b;
        ak.a aVar2 = bkVarC.c;
        if (aVar2 != this.i) {
            if (this.n == null) {
                this.n = g();
            }
            this.i = aVar2;
            Drawable drawable = aVar2.a;
            final int i = aVar2.g;
            final View.OnClickListener onClickListener = aVar2.b;
            bkVar.c = new ak.a(drawable, new View.OnClickListener() { // from class: k3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    oj2.U(i, 8, "Android.AdaptiveToolbarButton.Clicked");
                    onClickListener.onClick(view);
                }
            }, aVar2.h ? null : this.n, aVar2.d, aVar2.e, aVar2.f, i, aVar2.i);
        }
        return bkVar;
    }

    @Override // defpackage.ck
    public final void d(ck.a aVar) {
        this.e.a(aVar);
    }

    @Override // defpackage.ck
    public final void destroy() {
        n(0);
        this.e.clear();
        this.m.k(this);
        this.k.e(this);
        Iterator it = this.g.entrySet().iterator();
        while (it.hasNext()) {
            ((ck) ((Map.Entry) it.next()).getValue()).destroy();
            it.remove();
        }
    }

    public final void e(int i, ad adVar) {
        this.g.put(Integer.valueOf(i), adVar);
    }

    public final xq1 g() {
        if (!a8.P()) {
            return null;
        }
        j3 j3Var = this.p;
        j3Var.getClass();
        if (n3.H0()) {
            return new xq1(j3Var, 1, this.o);
        }
        return null;
    }

    @Override // org.chromium.chrome.browser.preferences.SharedPreferencesManager.b
    public final void i(String str) {
        if ("Chrome.AdaptiveToolbarCustomization.Settings".equals(str) || "Chrome.AdaptiveToolbarCustomization.Enabled".equals(str)) {
            this.l.b(new jb1(13, this));
        }
    }

    public final boolean k() {
        int i = this.q;
        sk skVar = bq.a;
        return i >= N.M37SqSAy("AdaptiveButtonInTopToolbarCustomizationV2", "minimum_width_dp", 360);
    }

    public final void m(boolean z) {
        Iterator<ck.a> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ck.a) aVar.next()).a(z);
            }
        }
    }

    public final void n(int i) {
        ck ckVar = (ck) this.g.get(Integer.valueOf(i));
        ck ckVar2 = this.f;
        if (ckVar2 != null) {
            ckVar2.b(this);
        }
        this.f = ckVar;
        if (ckVar != null) {
            ckVar.d(this);
        }
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        if (!this.k.h() || this.q == configuration.screenWidthDp) {
            return;
        }
        boolean zK = k();
        this.q = configuration.screenWidthDp;
        if (zK != k()) {
            m(this.h.a);
        }
    }
}
