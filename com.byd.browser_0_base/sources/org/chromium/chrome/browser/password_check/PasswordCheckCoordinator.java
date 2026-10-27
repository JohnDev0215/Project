package org.chromium.chrome.browser.password_check;

import J.N;
import android.content.Context;
import android.view.MenuItem;
import defpackage.C0175b;
import defpackage.a71;
import defpackage.cg;
import defpackage.fu0;
import defpackage.hu0;
import defpackage.i83;
import defpackage.n3;
import defpackage.oj2;
import defpackage.p81;
import defpackage.pd;
import defpackage.ww1;
import defpackage.xa1;
import defpackage.xw1;
import defpackage.yw1;
import defpackage.z7;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.device_reauth.ReauthenticatorBridge;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.R$dimen;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class PasswordCheckCoordinator implements b, a71 {
    public final fu0 e;
    public final PasswordCheckFragmentView f;
    public final SettingsLauncher g;
    public final ww1 h;
    public final f i;
    public PropertyModel j;

    public interface a {
        void a(CompromisedCredential compromisedCredential, Context context);

        void d(CompromisedCredential compromisedCredential);

        void g(CompromisedCredential compromisedCredential);

        void h(CompromisedCredential compromisedCredential);
    }

    public PasswordCheckCoordinator(PasswordCheckFragmentView passwordCheckFragmentView, fu0 fu0Var, n3 n3Var, pd pdVar, i83 i83Var) {
        this.e = fu0Var;
        this.f = passwordCheckFragmentView;
        this.g = n3Var;
        passwordCheckFragmentView.h0 = this;
        ww1 ww1Var = new ww1(passwordCheckFragmentView.D(), passwordCheckFragmentView.M());
        this.h = ww1Var;
        new ReauthenticatorBridge(5);
        this.i = new f(new xw1(passwordCheckFragmentView.D(), n3Var, pdVar, i83Var), ww1Var, n3Var, new yw1(new LargeIconBridge(Profile.d()), passwordCheckFragmentView.N().getDimensionPixelSize(R$dimen.default_favicon_size)));
    }

    @Override // org.chromium.chrome.browser.password_check.b
    public final boolean b(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        PasswordCheckFragmentView passwordCheckFragmentView = this.f;
        ((hu0) this.e).c(passwordCheckFragmentView.D(), passwordCheckFragmentView.D().getString(R$string.help_context_check_passwords), null, Profile.d());
        return true;
    }

    @Override // org.chromium.chrome.browser.password_check.b
    public final void c() {
        e eVar = n3.x;
        PropertyModel.l<p81<xa1>> lVar = h.a;
        f fVar = this.i;
        if (eVar == null) {
            fVar.getClass();
        } else {
            boolean z = false;
            if (((p81) fVar.c.j(lVar)).get(0) != null && ((xa1) ((p81) fVar.c.j(lVar)).get(0)).b.h(h.b.b) == 1) {
                z = true;
            }
            if (z) {
                z7.b0(2);
            }
            N.MbiHHiCX(eVar.a.a);
        }
        PasswordCheckFragmentView passwordCheckFragmentView = this.f;
        if (passwordCheckFragmentView.D() == null || passwordCheckFragmentView.D().isFinishing()) {
            if (!fVar.j) {
                p81 p81Var = (p81) fVar.c.j(lVar);
                for (int i = 1; i < p81Var.size(); i++) {
                    oj2.U(4, 5, "PasswordManager.AutomaticChange.AcceptanceWithoutAutoButton");
                }
            }
            ((e) fVar.j()).b.c(fVar);
            this.j = null;
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [T, p81] */
    @Override // org.chromium.chrome.browser.password_check.b
    public final void d() {
        if (this.j == null) {
            HashMap mapE = PropertyModel.e(h.f);
            PropertyModel.l<p81<xa1>> lVar = h.a;
            ?? p81Var = new p81();
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = p81Var;
            PropertyModel propertyModelP = C0175b.p(mapE, lVar, gVar, mapE);
            this.j = propertyModelP;
            cg cgVar = new cg(14);
            PasswordCheckFragmentView passwordCheckFragmentView = this.f;
            j.b(propertyModelP, passwordCheckFragmentView, cgVar);
            PropertyModel propertyModel = this.j;
            org.chromium.chrome.browser.password_check.a aVarI0 = n3.i0(this.g);
            int i2 = passwordCheckFragmentView.i0;
            Runnable runnable = new Runnable() { // from class: org.chromium.chrome.browser.password_check.d
                @Override // java.lang.Runnable
                public final void run() {
                    PasswordCheckCoordinator passwordCheckCoordinator = this.e;
                    a aVarI02 = n3.i0(passwordCheckCoordinator.g);
                    N.M2leB6Ho(((e) aVarI02).a.a, passwordCheckCoordinator.f.D());
                }
            };
            f fVar = this.i;
            fVar.c = propertyModel;
            fVar.d = aVarI0;
            fVar.e = runnable;
            fVar.j = false;
            oj2.U(i2, 4, "PasswordManager.BulkCheck.PasswordCheckReferrerAndroid2");
            boolean z = i2 != 1;
            fVar.b(z ? 1 : ((e) fVar.j()).f);
            ((e) fVar.j()).a(fVar, true);
            if (z) {
                z7.b0(0);
                N.MqdzTSiP(((e) fVar.j()).a.a);
            }
        }
    }

    @Override // org.chromium.chrome.browser.password_check.b
    public final void destroy() {
        n3.E();
    }

    @Override // org.chromium.chrome.browser.password_check.b
    public final void e() {
        this.i.j = false;
        ww1 ww1Var = this.h;
        Callback<Boolean> callback = ww1Var.c;
        if (callback != null) {
            callback.onResult(Boolean.valueOf(n3.o(0)));
            ww1Var.c = null;
        }
    }
}
