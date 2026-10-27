package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.view.View;
import androidx.preference.PreferenceManager;
import androidx.preference.d;
import defpackage.g5;
import defpackage.rh3;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes.dex */
public class AndroidPaymentAppsFragment extends d {
    public static final /* synthetic */ int h0 = 0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.payment_apps_title);
        PreferenceManager preferenceManager = this.a0;
        L0(preferenceManager.a(preferenceManager.a));
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        I0().c0();
        I0().V = true;
        g5 g5Var = new g5(this);
        Object obj = ThreadUtils.a;
        if (N.M1X7xdZV("ServiceWorkerPaymentApps")) {
            N.MFeChwbo(g5Var);
        } else {
            PostTask.c(rh3.a, new org.chromium.chrome.browser.payments.b(g5Var));
        }
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        super.q0(view, bundle);
        this.b0.setItemAnimator(null);
    }
}
