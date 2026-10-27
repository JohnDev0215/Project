package org.chromium.chrome.browser.ui.autofill;

import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.browser.ui.autofill.e;

/* JADX INFO: compiled from: OtpVerificationDialogView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f implements TextWatcher {
    public final /* synthetic */ e.a e;

    public f(e.a aVar) {
        this.e = aVar;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.e.a(charSequence);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
