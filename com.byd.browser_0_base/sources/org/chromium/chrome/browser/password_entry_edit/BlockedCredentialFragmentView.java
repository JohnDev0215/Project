package org.chromium.chrome.browser.password_entry_edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class BlockedCredentialFragmentView extends CredentialEntryFragmentViewBase {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.section_saved_passwords_exceptions);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        D0();
        return layoutInflater.inflate(R$layout.blocked_credential_view, viewGroup, false);
    }
}
