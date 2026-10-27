package org.chromium.chrome.browser.firstrun;

import J.N;
import android.R;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.activity.OnBackPressedDispatcher;
import defpackage.aq1;
import defpackage.bq;
import defpackage.dn;
import defpackage.en0;
import defpackage.fn0;
import defpackage.n3;
import defpackage.o22;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.q11;
import defpackage.qs1;
import defpackage.rs1;
import defpackage.vb;
import defpackage.xb;
import defpackage.xr;
import defpackage.z7;
import defpackage.zb;
import defpackage.zp1;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.components.policy.PolicyService;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class FirstRunActivityBase extends AsyncInitializationActivity implements vb {
    public static final /* synthetic */ int k0 = 0;
    public boolean c0;
    public final fn0 d0;
    public final rs1<PolicyService> e0;
    public final a f0 = new a();
    public final o22 g0;
    public final long h0;
    public long i0;
    public dn j0;

    public class a extends aq1<Boolean> {
        public a() {
            r(Boolean.TRUE);
        }
    }

    public class b implements PendingIntent.OnFinished {
        public b() {
        }

        @Override // android.app.PendingIntent.OnFinished
        public final void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str, Bundle bundle) {
            FirstRunActivityBase.h2(FirstRunActivityBase.this.getIntent());
        }
    }

    public FirstRunActivityBase() {
        Object obj = ThreadUtils.a;
        fn0 fn0Var = fn0.g;
        if (fn0Var == null) {
            fn0Var = new fn0();
        } else {
            fn0.g = null;
        }
        this.d0 = fn0Var;
        rs1<PolicyService> rs1Var = new rs1<>();
        this.e0 = rs1Var;
        o22 o22Var = new o22(fn0Var, rs1Var);
        this.g0 = o22Var;
        this.h0 = SystemClock.elapsedRealtime();
        o22Var.f.h(o22Var.e.b(new xr(19, this)));
    }

    public static void h2(Intent intent) {
        if (q11.i(intent, "Extra.FreChromeLaunchIntentIsCct", false)) {
            q11.j(intent, "Extra.FreChromeLaunchIntentExtras");
            CustomTabsConnection.e().getClass();
        }
    }

    public final qs1<Boolean> I() {
        return this.j0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public void K() {
        super.K();
        this.c0 = true;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        this.i0 = jElapsedRealtime;
        oj2.b0(jElapsedRealtime - this.h0, "MobileFre.NativeInitialized");
        this.e0.a((PolicyService) N.MXHPjU6q());
    }

    @Override // defpackage.nj
    public final boolean O() {
        return true;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void Y1() {
        SparseIntArray sparseIntArray = zb.k;
        boolean zA = bq.b.a();
        OnBackPressedDispatcher onBackPressedDispatcher = this.k;
        if (zA) {
            xb.a(this, onBackPressedDispatcher, this);
        } else {
            xb.b(this, onBackPressedDispatcher, new pq2(14, this));
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean c2(Intent intent) {
        return false;
    }

    public final qs1<Boolean> e0() {
        return this.g0;
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public void g2() {
        this.j0 = new dn(AccountManagerFacadeProvider.getInstance(), this.d0);
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.f0;
    }

    public void i2(boolean z) {
        if (this.c0) {
            oj2.b0(SystemClock.elapsedRealtime() - this.i0, z ? "MobileFre.PolicyServiceInitDelayAfterNative.WithPolicy2" : "MobileFre.PolicyServiceInitDelayAfterNative.WithoutPolicy2");
        }
    }

    public final boolean j2() {
        b bVar;
        PendingIntent pendingIntent = (PendingIntent) q11.n(getIntent(), "Extra.FreChromeLaunchIntent");
        boolean zI = q11.i(getIntent(), "Extra.FreChromeLaunchIntentIsCct", false);
        if (pendingIntent == null) {
            return false;
        }
        if (zI) {
            try {
                bVar = new b();
            } catch (PendingIntent.CanceledException e) {
                Log.e("cr_FirstRunActivity", "Unable to send PendingIntent.", e);
                return false;
            }
        } else {
            bVar = null;
        }
        pendingIntent.send(-1, bVar, (Handler) null);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        return true;
    }

    public abstract int o();

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        o22 o22Var = this.g0;
        o22Var.e.a();
        if (o22Var.h != null) {
            PolicyService policyService = o22Var.g.get();
            PolicyService.a aVar = o22Var.h;
            org.chromium.base.b<PolicyService.a> bVar = policyService.b;
            bVar.c(aVar);
            if (bVar.isEmpty()) {
                N.MU0pXsSP(policyService.a, policyService);
            }
            o22Var.h = null;
        }
        fn0 fn0Var = this.d0;
        en0 en0Var = fn0Var.f;
        if (en0Var != null) {
            en0Var.a(true);
        }
        fn0Var.d.clear();
        fn0Var.e.clear();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        super.onPause();
        UmaUtils.d = SystemClock.uptimeMillis();
        if (this.c0) {
            z7.i();
        }
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        n3.t = true;
        super.onResume();
        UmaUtils.d();
    }
}
