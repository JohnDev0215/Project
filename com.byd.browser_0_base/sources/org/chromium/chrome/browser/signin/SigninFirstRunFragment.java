package org.chromium.chrome.browser.signin;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import defpackage.bi1;
import defpackage.ci1;
import defpackage.hm2;
import defpackage.hn0;
import defpackage.km2;
import defpackage.lm2;
import defpackage.o22;
import defpackage.on0;
import defpackage.v42;
import defpackage.y70;
import java.util.Locale;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.firstrun.LightweightFirstRunActivity;
import org.chromium.chrome.browser.firstrun.a;
import org.chromium.chrome.browser.ui.signin.fre.SigninFirstRunView;
import org.chromium.components.policy.PolicyService;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class SigninFirstRunFragment extends Fragment implements hn0, hm2.a {
    public static final /* synthetic */ int f0 = 0;
    public FrameLayout Z;
    public bi1 a0;
    public a b0;
    public hm2 c0;
    public boolean d0;
    public y70 e0;

    public final void H0() {
        b().E();
    }

    public final View I0(LayoutInflater layoutInflater, Configuration configuration) {
        SigninFirstRunView signinFirstRunView = (SigninFirstRunView) layoutInflater.inflate(b().r() && configuration.orientation == 2 && configuration.screenWidthDp >= 600 ? R$layout.signin_first_run_landscape_view : R$layout.signin_first_run_portrait_view, (ViewGroup) null, false);
        this.c0.a(signinFirstRunView);
        TextView textView = (TextView) signinFirstRunView.findViewById(R$id.message);
        if (Locale.getDefault().getLanguage().equals("iw")) {
            textView.setText(textView.getContext().getString(R$string.statement_message).replace("BYD Auto Industry", "\u2066BYD\u2069 \u2066Auto\u2069 \u2066Industry\u2069"));
        }
        return signinFirstRunView;
    }

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        String stringExtra;
        if (i != 1 || i2 != -1 || intent == null || (stringExtra = intent.getStringExtra("authAccount")) == null) {
            return;
        }
        this.c0.a.l(stringExtra);
    }

    @Override // androidx.fragment.app.Fragment
    public final void a0(Context context) {
        super.a0(context);
        this.a0 = ((ci1) D()).P0();
        this.c0 = new hm2(x0(), this.a0, this, v42.g());
        if (b().T0()) {
            a aVar = new a(b().e0(), EnterpriseInfo.b(), (LightweightFirstRunActivity.b) null);
            this.b0 = aVar;
            aVar.h(new on0(13, this));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(D());
        this.Z = frameLayout;
        frameLayout.setLayoutDirection(3);
        this.Z.addView(I0(layoutInflater, N().getConfiguration()));
        return this.Z;
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        y70 y70Var;
        FrameLayout frameLayout = this.Z;
        if (frameLayout != null && (y70Var = this.e0) != null) {
            frameLayout.removeCallbacks(y70Var);
        }
        this.H = true;
    }

    @Override // defpackage.hn0
    public final void h() {
        View view = this.f12J;
        if (view == null) {
            return;
        }
        view.findViewById(R$id.title).sendAccessibilityEvent(8);
    }

    @Override // androidx.fragment.app.Fragment
    public final void i0() {
        this.H = true;
        this.Z = null;
        a aVar = this.b0;
        if (aVar != null) {
            aVar.e.a();
            o22 o22Var = aVar.i;
            if (o22Var != null) {
                o22Var.e.a();
                if (o22Var.h != null) {
                    PolicyService policyService = o22Var.g.get();
                    PolicyService.a aVar2 = o22Var.h;
                    b<PolicyService.a> bVar = policyService.b;
                    bVar.c(aVar2);
                    if (bVar.isEmpty()) {
                        N.MU0pXsSP(policyService.a, policyService);
                    }
                    o22Var.h = null;
                }
                aVar.i = null;
            }
            this.b0 = null;
        }
        hm2 hm2Var = this.c0;
        hm2Var.a(null);
        km2 km2Var = hm2Var.a;
        km2Var.k.e(km2Var);
        km2Var.g.j(km2Var);
        km2Var.l = true;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        FrameLayout frameLayout = this.Z;
        if (frameLayout != null) {
            y70 y70Var = this.e0;
            if (y70Var != null) {
                frameLayout.removeCallbacks(y70Var);
            } else {
                this.e0 = new y70(this, 14, configuration);
            }
            this.Z.post(this.e0);
        }
    }

    @Override // defpackage.hn0
    public final void reset() {
        hm2 hm2Var = this.c0;
        PropertyModel.n nVar = lm2.f;
        PropertyModel propertyModel = hm2Var.a.j;
        propertyModel.l(nVar, false);
        propertyModel.l(lm2.g, false);
    }
}
