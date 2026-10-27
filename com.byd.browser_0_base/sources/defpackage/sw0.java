package defpackage;

import J.N;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ck;
import defpackage.h62;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* JADX INFO: compiled from: IdentityDiscController.java */
/* JADX INFO: loaded from: classes.dex */
public final class sw0 implements hk1, h62.b, IdentityManager.a, ck {
    public final Context e;
    public q2 f;
    public final zp1<Profile> g;
    public IdentityManager i;
    public h62 j;
    public boolean k;
    public final bk l;
    public boolean n;
    public final c3 h = new c3(29, this);
    public final b<ck.a> m = new b<>();

    public sw0(AppCompatActivity appCompatActivity, q2 q2Var, zp1 zp1Var) {
        this.e = appCompatActivity;
        this.f = q2Var;
        this.g = zp1Var;
        q2Var.f(this);
        this.l = new bk(null, new ie0(10, this), appCompatActivity.getString(R$string.accessibility_toolbar_btn_identity_disc), 0, false, new hw0(appCompatActivity.getResources(), "IPH_IdentityDisc", R$string.iph_identity_disc_text, R$string.iph_identity_disc_accessibility_text), 0);
    }

    @Override // defpackage.hk1
    public final void G0() {
        this.f.e(this);
        this.f = null;
        this.n = true;
        this.g.p(this.h);
    }

    public final void a() {
        if (this.n) {
            IdentityManager identityManager = this.i;
            String strB = CoreAccountInfo.b(identityManager != null ? identityManager.a(0) : null);
            sk skVar = bq.a;
            if (N.M09VlOh_("IdentityStatusConsistency")) {
                this.k = true;
            } else {
                this.k = strB != null;
            }
            if (this.k && this.j == null) {
                int i = R$dimen.toolbar_identity_disc_size;
                Context context = this.e;
                h62 h62Var = new h62(context, context.getResources().getDimensionPixelSize(i), null);
                this.j = h62Var;
                h62Var.b(this);
            }
            this.l.a = false;
        }
    }

    @Override // defpackage.ck
    public final void b(ck.a aVar) {
        this.m.c(aVar);
    }

    @Override // defpackage.ck
    public final bk c(Tab tab) {
        boolean z = tab != null && (tab.u() instanceof h);
        bk bkVar = this.l;
        if (z) {
            a();
            return bkVar;
        }
        bkVar.a = false;
        return bkVar;
    }

    @Override // defpackage.ck
    public final void d(ck.a aVar) {
        this.m.a(aVar);
    }

    @Override // defpackage.ck
    public final void destroy() {
        q2 q2Var = this.f;
        if (q2Var != null) {
            q2Var.e(this);
            this.f = null;
        }
        h62 h62Var = this.j;
        if (h62Var != null) {
            h62Var.e(this);
            this.j = null;
        }
        IdentityManager identityManager = this.i;
        if (identityManager != null) {
            identityManager.c(this);
            this.i = null;
        }
        if (this.n) {
            this.g.d(this.h);
        }
    }

    public final void e(boolean z) {
        Iterator<ck.a> it = this.m.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((ck.a) aVar.next()).a(z);
            }
        }
    }

    @Override // org.chromium.components.signin.identitymanager.IdentityManager.a
    public final void m(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        int i = primaryAccountChangeEvent.a;
        if (i == 1) {
            e(true);
            return;
        }
        if (i != 2) {
            return;
        }
        h62 h62Var = this.j;
        if (h62Var != null) {
            h62Var.e(this);
            this.j = null;
        }
        e(false);
    }

    @Override // h62.b
    public final void n(String str) {
        if (this.k) {
            IdentityManager identityManager = this.i;
            if (str.equals(CoreAccountInfo.b(identityManager != null ? identityManager.a(0) : null))) {
                e(false);
                e(true);
            }
        }
    }
}
