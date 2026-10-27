package defpackage;

import J.N;
import android.text.Editable;
import android.text.TextWatcher;

/* JADX INFO: compiled from: PhoneNumberUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class v02 implements TextWatcher {
    public boolean e;
    public String f;

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (this.e) {
            return;
        }
        String strMDrtwxb3 = N.MDrtwxb3(editable.toString(), this.f);
        this.e = true;
        editable.replace(0, editable.length(), strMDrtwxb3, 0, strMDrtwxb3.length());
        this.e = false;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
