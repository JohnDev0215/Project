package org.chromium.chrome.browser.password_entry_edit;

import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;

/* JADX INFO: compiled from: CredentialEditFragmentView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements TextWatcher {
    public final /* synthetic */ CredentialEntryFragmentViewBase.b e;

    public c(CredentialEntryFragmentViewBase.b bVar) {
        this.e = bVar;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.e.g(charSequence.toString());
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
