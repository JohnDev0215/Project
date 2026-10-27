package org.chromium.chrome.browser.tracing.settings;

import android.os.Bundle;
import androidx.preference.d;
import defpackage.qj2;
import org.chromium.chrome.R$xml;

/* JADX INFO: loaded from: classes2.dex */
public class DeveloperSettings extends d {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle("Developer options");
        qj2.a(this, R$xml.developer_preferences);
        I0().d0(f("beta_stable_hint"));
    }
}
