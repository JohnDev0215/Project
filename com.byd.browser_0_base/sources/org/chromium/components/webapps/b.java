package org.chromium.components.webapps;

import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: compiled from: AddToHomescreenDialogView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements TextWatcher {
    public final /* synthetic */ c e;

    public b(c cVar) {
        this.e = cVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.e.a();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
