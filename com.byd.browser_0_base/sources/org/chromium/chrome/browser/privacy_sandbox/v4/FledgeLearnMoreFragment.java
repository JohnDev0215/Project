package org.chromium.chrome.browser.privacy_sandbox.v4;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.preference.d;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;

/* JADX INFO: loaded from: classes2.dex */
public class FledgeLearnMoreFragment extends d {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.settings_fledge_page_title);
        qj2.a(this, R$xml.fledge_learn_more_preference);
        D0();
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
    }
}
