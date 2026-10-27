package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.settings.AutofillLocalCardEditor;

/* JADX INFO: compiled from: AutofillLocalCardEditor.java */
/* JADX INFO: loaded from: classes.dex */
public final class ya implements TextWatcher {
    public final /* synthetic */ AutofillLocalCardEditor e;

    public ya(AutofillLocalCardEditor autofillLocalCardEditor) {
        this.e = autofillLocalCardEditor;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        boolean z = !editable.toString().matches(".*\\d.*");
        AutofillLocalCardEditor autofillLocalCardEditor = this.e;
        autofillLocalCardEditor.o0 = z;
        autofillLocalCardEditor.i0.setError(autofillLocalCardEditor.o0 ? "" : autofillLocalCardEditor.b0.getResources().getString(R$string.autofill_credit_card_editor_invalid_nickname));
        autofillLocalCardEditor.M0();
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
