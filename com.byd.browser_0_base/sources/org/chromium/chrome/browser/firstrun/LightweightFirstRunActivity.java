package org.chromium.chrome.browser.firstrun;

import J.N;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import defpackage.a8;
import defpackage.by1;
import defpackage.dm0;
import defpackage.dn;
import defpackage.e71;
import defpackage.gn0;
import defpackage.ie0;
import defpackage.iq2;
import defpackage.ko0;
import defpackage.o22;
import defpackage.oj2;
import defpackage.q11;
import defpackage.ql0;
import defpackage.qo;
import defpackage.rs1;
import defpackage.un1;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.firstrun.a;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.policy.PolicyService;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes.dex */
public class LightweightFirstRunActivity extends FirstRunActivityBase implements LoadingView.c {
    public static final /* synthetic */ int y0 = 0;
    public final org.chromium.chrome.browser.firstrun.a l0;
    public TextView m0;
    public Button n0;
    public LoadingView o0;
    public View p0;
    public View q0;
    public View r0;
    public boolean s0;
    public boolean t0;
    public boolean u0;
    public long v0;
    public Handler w0;
    public ql0 x0;

    public class a extends gn0 {
        public a(Activity activity, dn dnVar) {
            super(activity, dnVar);
        }

        @Override // defpackage.gn0
        public final void e(Bundle bundle) {
            boolean z = bundle.getBoolean("IsChildAccount", false);
            int i = LightweightFirstRunActivity.y0;
            LightweightFirstRunActivity lightweightFirstRunActivity = LightweightFirstRunActivity.this;
            lightweightFirstRunActivity.getClass();
            lightweightFirstRunActivity.setContentView(LayoutInflater.from(lightweightFirstRunActivity).inflate(R$layout.lightweight_fre_tos, (ViewGroup) null));
            un1 un1Var = new un1(lightweightFirstRunActivity, new ko0(3, lightweightFirstRunActivity));
            un1 un1Var2 = new un1(lightweightFirstRunActivity, new dm0(4, lightweightFirstRunActivity));
            un1 un1Var3 = new un1(lightweightFirstRunActivity, new e71(lightweightFirstRunActivity, 1));
            String strR = q11.r(lightweightFirstRunActivity.getIntent(), "org.chromium.chrome.browser.firstrun.AssociatedAppName");
            if (strR == null) {
                strR = "";
            }
            SpannableString spannableStringA = z ? iq2.a(lightweightFirstRunActivity.getString(R$string.lightweight_fre_associated_app_tos_and_privacy_child_account, strR), new iq2.a(un1Var, "<LINK1>", "</LINK1>"), new iq2.a(un1Var2, "<LINK2>", "</LINK2>"), new iq2.a(un1Var3, "<LINK3>", "</LINK3>")) : iq2.a(lightweightFirstRunActivity.getString(R$string.lightweight_fre_associated_app_tos, strR), new iq2.a(un1Var, "<LINK1>", "</LINK1>"), new iq2.a(un1Var2, "<LINK2>", "</LINK2>"));
            TextView textView = (TextView) lightweightFirstRunActivity.findViewById(R$id.lightweight_fre_tos_and_privacy);
            lightweightFirstRunActivity.m0 = textView;
            textView.setText(spannableStringA);
            lightweightFirstRunActivity.m0.setMovementMethod(LinkMovementMethod.getInstance());
            lightweightFirstRunActivity.q0 = lightweightFirstRunActivity.findViewById(R$id.lightweight_fre_buttons);
            Button button = (Button) lightweightFirstRunActivity.findViewById(R$id.button_primary);
            lightweightFirstRunActivity.n0 = button;
            button.setOnClickListener(new ie0(8, lightweightFirstRunActivity));
            ((Button) lightweightFirstRunActivity.findViewById(R$id.button_secondary)).setOnClickListener(new by1(10, lightweightFirstRunActivity));
            lightweightFirstRunActivity.o0 = (LoadingView) lightweightFirstRunActivity.findViewById(R$id.loading_view);
            lightweightFirstRunActivity.p0 = lightweightFirstRunActivity.findViewById(R$id.loading_view_container);
            lightweightFirstRunActivity.r0 = lightweightFirstRunActivity.findViewById(R$id.privacy_disclaimer);
            lightweightFirstRunActivity.s0 = true;
            lightweightFirstRunActivity.v0 = SystemClock.elapsedRealtime();
            org.chromium.chrome.browser.firstrun.a aVar = lightweightFirstRunActivity.l0;
            if (aVar != null) {
                rs1<Boolean> rs1Var = aVar.f;
                if (rs1Var.get() == null) {
                    lightweightFirstRunActivity.o0.a(lightweightFirstRunActivity);
                    lightweightFirstRunActivity.o0.e();
                    lightweightFirstRunActivity.k2(false);
                } else if (rs1Var.get().booleanValue()) {
                    lightweightFirstRunActivity.k2(false);
                    lightweightFirstRunActivity.l2();
                }
            }
        }
    }

    public class b implements a.InterfaceC0086a {
        public b() {
        }
    }

    public LightweightFirstRunActivity() {
        org.chromium.chrome.browser.firstrun.a aVar = new org.chromium.chrome.browser.firstrun.a(this.g0, EnterpriseInfo.b(), new b());
        this.l0 = aVar;
        aVar.h(new e71(this, 0));
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void K() {
        super.K();
        this.t0 = true;
        if (this.u0) {
            a8.e(false);
            SharedPreferencesManager.getInstance().l("lightweight_first_run_flow", true);
            finish();
            j2();
        }
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void M() {
        oj2.b0(SystemClock.elapsedRealtime() - this.v0, "MobileFre.Lightweight.LoadingDuration");
        if (this.l0.f.get().booleanValue()) {
            l2();
            return;
        }
        boolean zIsAccessibilityFocused = this.p0.isAccessibilityFocused();
        this.p0.setVisibility(8);
        k2(true);
        if (zIsAccessibilityFocused) {
            this.m0.sendAccessibilityEvent(8);
        }
    }

    @Override // org.chromium.ui.widget.LoadingView.c
    public final void V0() {
        this.p0.setVisibility(0);
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final void g2() {
        super.g2();
        setFinishOnTouchOutside(true);
        AccountManagerFacadeProvider.getInstance().b().g(new dm0(3, new a(this, this.j0)));
        W1();
    }

    public final void k2(boolean z) {
        int i = z ? 0 : 8;
        this.m0.setVisibility(i);
        this.q0.setVisibility(i);
    }

    public final void l2() {
        this.p0.setVisibility(8);
        this.r0.setVisibility(0);
        this.r0.sendAccessibilityEvent(8);
        this.x0 = new ql0(4, this);
        Handler handler = new Handler(ThreadUtils.d());
        this.w0 = handler;
        handler.postDelayed(this.x0, qo.f().d() ? 2000 : 1000);
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, defpackage.vb
    public final int o() {
        finish();
        FirstRunActivityBase.h2(getIntent());
        return 0;
    }

    @Override // org.chromium.chrome.browser.firstrun.FirstRunActivityBase, org.chromium.chrome.browser.init.AsyncInitializationActivity, org.chromium.chrome.browser.ChromeBaseAppCompatActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ql0 ql0Var;
        super.onDestroy();
        this.o0.b();
        org.chromium.chrome.browser.firstrun.a aVar = this.l0;
        if (aVar != null) {
            aVar.e.a();
            o22 o22Var = aVar.i;
            if (o22Var != null) {
                o22Var.e.a();
                if (o22Var.h != null) {
                    PolicyService policyService = o22Var.g.get();
                    PolicyService.a aVar2 = o22Var.h;
                    org.chromium.base.b<PolicyService.a> bVar = policyService.b;
                    bVar.c(aVar2);
                    if (bVar.isEmpty()) {
                        N.MU0pXsSP(policyService.a, policyService);
                    }
                    o22Var.h = null;
                }
                aVar.i = null;
            }
        }
        Handler handler = this.w0;
        if (handler == null || (ql0Var = this.x0) == null) {
            return;
        }
        handler.removeCallbacks(ql0Var);
    }
}
