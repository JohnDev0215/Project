package defpackage;

import J.N;
import android.text.Editable;
import android.text.TextWatcher;
import org.chromium.chrome.browser.omnibox.AutocompleteEditText;

/* JADX INFO: compiled from: AutocompleteEditText.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ha implements TextWatcher {
    public final /* synthetic */ AutocompleteEditText e;

    public ha(AutocompleteEditText autocompleteEditText) {
        this.e = autocompleteEditText;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        AutocompleteEditText autocompleteEditText = this.e;
        if (!autocompleteEditText.n || autocompleteEditText.m) {
            return;
        }
        autocompleteEditText.o = true;
        String string = editable.toString();
        String strM14CHwRm = autocompleteEditText.p ? N.M14CHwRm(string) : string;
        if (!string.equals(strM14CHwRm)) {
            editable.replace(0, editable.length(), strM14CHwRm, 0, strM14CHwRm.length());
        }
        autocompleteEditText.o = false;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
