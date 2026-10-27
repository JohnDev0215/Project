package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;

/* JADX INFO: compiled from: AutofillPaymentMethodsFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class bb implements ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback {
    public final /* synthetic */ Preference a;
    public final /* synthetic */ AutofillPaymentMethodsFragment b;

    public bb(AutofillPaymentMethodsFragment autofillPaymentMethodsFragment, Preference preference) {
        this.b = autofillPaymentMethodsFragment;
        this.a = preference;
    }

    @Override // org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge.HasServiceWorkerPaymentAppsCallback
    public final void a(boolean z) {
        int i = AutofillPaymentMethodsFragment.h0;
        this.b.getClass();
        Preference preference = this.a;
        if (z) {
            preference.R(null);
            preference.J(true);
        } else {
            preference.Q(R$string.payment_no_apps_summary);
            preference.J(false);
        }
    }
}
