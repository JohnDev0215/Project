package org.chromium.chrome.browser.tasks.tab_management;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import defpackage.hy1;
import defpackage.ny0;
import defpackage.q2;
import defpackage.ry0;
import defpackage.sy0;
import defpackage.t43;
import defpackage.wp2;
import defpackage.wy0;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.a;
import org.chromium.chrome.browser.tasks.tab_management.g;

/* JADX INFO: compiled from: IncognitoReauthPromoMessageService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends g implements hy1 {
    public final int g;
    public final ny0 h;
    public final Profile i;
    public final Context j;
    public final SharedPreferencesManager k;
    public final wp2 l;
    public final q2 m;
    public boolean n;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.tasks.tab_management.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IncognitoReauthPromoMessageService.java */
    public class C0114a implements g.a {
        public final MessageCardView.c a;
        public final MessageCardView.a b;

        public C0114a(ry0 ry0Var, sy0 sy0Var) {
            this.a = ry0Var;
            this.b = sy0Var;
        }
    }

    public a(Profile profile, Activity activity, SharedPreferencesManager sharedPreferencesManager, ny0 ny0Var, wp2 wp2Var, t43 t43Var, q2 q2Var) {
        super(4);
        this.i = profile;
        this.j = activity;
        this.k = sharedPreferencesManager;
        this.h = ny0Var;
        this.l = wp2Var;
        this.g = (((Boolean) t43Var.get()).booleanValue() ? 2 : 1) * 10;
        this.m = q2Var;
        q2Var.f(this);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.g
    public final void c(d dVar) {
        super.c(dVar);
        m();
    }

    @Override // defpackage.hy1
    public final void f() {
        n();
    }

    public final void i() {
        g();
        this.k.l("Chrome.IncognitoReauth.PromoCardEnabled", false);
        this.m.e(this);
    }

    public final boolean k(Profile profile) {
        if (!ny0.a(profile) && ny0.b() && Build.VERSION.SDK_INT >= 30 && wy0.a()) {
            return this.k.readBoolean("Chrome.IncognitoReauth.PromoCardEnabled", true);
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [ry0] */
    public final void m() {
        if (k(this.i)) {
            if (this.k.d(0, "Chrome.IncognitoReauth.PromoShowCount") >= this.g) {
                i();
            } else {
                e(new C0114a(new MessageCardView.c() { // from class: ry0
                    @Override // org.chromium.chrome.browser.tasks.tab_management.MessageCardView.c
                    public final void a() {
                        a aVar = this.a;
                        if (!aVar.k(aVar.i)) {
                            aVar.n();
                        } else {
                            aVar.h.c(new ty0(aVar));
                        }
                    }
                }, new sy0(0, this)));
            }
        }
    }

    public final void n() {
        Profile profile = this.i;
        if (k(profile)) {
            if (this.n) {
                m();
            }
        } else if (ny0.a(profile)) {
            i();
        } else {
            g();
            this.n = true;
        }
    }

    @Override // defpackage.hy1
    public final void h() {
    }
}
