package org.chromium.chrome.browser.ui.signin;

import android.os.Handler;
import android.text.TextUtils;
import defpackage.d92;
import defpackage.ke0;
import defpackage.x10;
import defpackage.z7;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.chrome.browser.ui.signin.b;
import org.chromium.chrome.browser.ui.signin.d;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.chrome.browser.ui.signin.k;

/* JADX INFO: compiled from: ConfirmSyncDataStateMachine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements b.a, d.a {
    public final a b;
    public final String c;
    public final String d;
    public final h e;
    public boolean g;
    public Boolean h;
    public d92 i;
    public int a = 0;
    public final Handler f = new Handler();

    /* JADX INFO: compiled from: ConfirmSyncDataStateMachine.java */
    public interface a {
    }

    public f(h hVar, String str, String str2, k kVar) {
        Object obj = ThreadUtils.a;
        this.e = hVar;
        this.c = str;
        this.d = str2;
        this.b = kVar;
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).a(str2, new x10(14, this));
        c();
    }

    public final void a(boolean z) {
        Object obj = ThreadUtils.a;
        d92 d92Var = this.i;
        if (d92Var != null) {
            this.f.removeCallbacks(d92Var);
            this.i = null;
        }
        this.a = 4;
        if (z) {
            return;
        }
        SyncConsentFragmentBase syncConsentFragmentBase = ((k) this.b).b;
        syncConsentFragmentBase.p0 = null;
        syncConsentFragmentBase.h0 = false;
        this.e.a();
    }

    public final void b() {
        boolean zBooleanValue = this.h.booleanValue();
        h hVar = this.e;
        if (!zBooleanValue) {
            hVar.a();
            c();
            return;
        }
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        String strP = z7.D(profileD).p(this.d);
        hVar.a();
        hVar.f = new d(hVar.b, hVar.a, this, strP);
    }

    public final void c() {
        int i = this.a;
        h hVar = this.e;
        if (i == 0) {
            this.a = 1;
            String str = this.c;
            if (TextUtils.isEmpty(str) || this.d.equals(str)) {
                c();
                return;
            }
            String str2 = this.c;
            String str3 = this.d;
            hVar.a();
            hVar.e = new b(hVar.b, hVar.a, this, str2, str3);
            return;
        }
        if (i == 1) {
            this.a = 2;
            if (this.h != null) {
                b();
                return;
            }
            ke0 ke0Var = new ke0(27, this);
            hVar.a();
            hVar.c = new h.a(hVar.b, hVar.a, ke0Var);
            if (this.i == null) {
                this.i = new d92(18, this);
            }
            this.f.postDelayed(this.i, 30000L);
            return;
        }
        if (i != 2) {
            if (i == 4) {
                throw new IllegalStateException("Can't progress from DONE state!");
            }
            return;
        }
        this.a = 4;
        final boolean z = this.g;
        final k kVar = (k) this.b;
        SyncConsentFragmentBase syncConsentFragmentBase = kVar.b;
        syncConsentFragmentBase.p0 = null;
        if (syncConsentFragmentBase.D().isDestroyed()) {
            return;
        }
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        final SigninManager signinManagerD = z7.D(profileD);
        final boolean z2 = kVar.a;
        signinManagerD.q(new Runnable() { // from class: wx2
            @Override // java.lang.Runnable
            public final void run() {
                k kVar2 = kVar;
                boolean z3 = z;
                boolean z4 = z2;
                if (z3) {
                    kVar2.getClass();
                    signinManagerD.g(new o40(kVar2, 3, z4));
                } else {
                    SyncConsentFragmentBase syncConsentFragmentBase2 = kVar2.b;
                    syncConsentFragmentBase2.L0(new zx1(26, kVar2), syncConsentFragmentBase2.f0, z4);
                }
            }
        });
    }
}
