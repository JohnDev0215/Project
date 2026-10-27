package org.chromium.chrome.browser.about_settings;

import android.os.Bundle;
import androidx.preference.d;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;

/* JADX INFO: loaded from: classes.dex */
public class LegalInformationSettings extends d {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        qj2.a(this, R$xml.legal_information_preferences);
        D().setTitle(R$string.legal_information_title);
    }
}
