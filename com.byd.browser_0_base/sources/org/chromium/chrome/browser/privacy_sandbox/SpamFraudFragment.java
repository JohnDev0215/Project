package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.os.Bundle;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;

/* JADX INFO: loaded from: classes2.dex */
public class SpamFraudFragment extends PrivacySandboxSettingsBaseFragment {
    @Override // org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment, androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D0();
        D().setTitle(R$string.privacy_sandbox_spam_fraud_title);
        qj2.a(this, R$xml.spam_fraud_preference);
        f("spam_fraud_description").Q(N.MhaiireD() ? R$string.privacy_sandbox_spam_fraud_description_trials_on : R$string.privacy_sandbox_spam_fraud_description_trials_off);
    }
}
