package defpackage;

import android.content.Context;
import androidx.appcompat.widget.a;
import defpackage.h62;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountManagerFacade;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* JADX INFO: compiled from: BookmarkPromoHeader.java */
/* JADX INFO: loaded from: classes.dex */
public final class pg implements SigninManager.b, h62.b, z1 {
    public final Context e;
    public final SigninManager f;
    public final AccountManagerFacade g;
    public final Runnable h;
    public final h62 i;
    public final xx2 j;
    public int k = 0;

    public pg(Context context, Profile profile, a aVar) {
        this.e = context;
        this.h = aVar;
        z7.m().getClass();
        SigninManager signinManagerD = z7.D(profile);
        this.f = signinManagerD;
        signinManagerD.i(this);
        AccountManagerFacade accountManagerFacadeProvider = AccountManagerFacadeProvider.getInstance();
        this.g = accountManagerFacadeProvider;
        if (xx2.a()) {
            h62 h62VarC = h62.c(context);
            this.i = h62VarC;
            h62VarC.b(this);
            this.j = new xx2(9, a8.y());
            accountManagerFacadeProvider.a(this);
        } else {
            this.i = null;
            this.j = null;
        }
        b();
    }

    @Override // defpackage.z1
    public final void F() {
        px0 px0Var;
        xx2 xx2Var = this.j;
        if (xx2Var != null && (px0Var = xx2Var.b) != null) {
            px0Var.a(null);
            xx2Var.b = null;
        }
        this.h.run();
    }

    public final int a() {
        SigninManager signinManager = this.f;
        if (signinManager.l().b(1)) {
            SharedPreferencesManager.getInstance().d(0, "enhanced_bookmark_signin_promo_show_count");
            return 0;
        }
        signinManager.j();
        return 0;
    }

    public final void b() {
        a();
        int i = this.k;
        if (i == 0) {
            return;
        }
        if (i != 0) {
        }
        xx2 xx2Var = this.j;
        this.k = 0;
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        px0 px0Var;
        b();
        xx2 xx2Var = this.j;
        if (xx2Var != null && (px0Var = xx2Var.b) != null) {
            px0Var.a(null);
            xx2Var.b = null;
        }
        this.h.run();
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void j() {
        px0 px0Var;
        b();
        xx2 xx2Var = this.j;
        if (xx2Var != null && (px0Var = xx2Var.b) != null) {
            px0Var.a(null);
            xx2Var.b = null;
        }
        this.h.run();
    }

    @Override // h62.b
    public final void n(String str) {
        px0 px0Var;
        xx2 xx2Var = this.j;
        if (xx2Var != null && (px0Var = xx2Var.b) != null) {
            px0Var.a(null);
            xx2Var.b = null;
        }
        this.h.run();
    }
}
