package defpackage;

import android.text.Editable;
import android.text.TextWatcher;
import defpackage.xf0;
import org.chromium.components.autofill.prefeditor.EditorTextField;

/* JADX INFO: compiled from: EditorTextField.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ig0 implements TextWatcher {
    public final /* synthetic */ xf0 e;
    public final /* synthetic */ EditorTextField f;

    public ig0(EditorTextField editorTextField, xf0 xf0Var) {
        this.f = editorTextField;
        this.e = xf0Var;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.e.n = editable.toString();
        EditorTextField editorTextField = this.f;
        editorTextField.c(false);
        int i = EditorTextField.j;
        xf0.b bVar = editorTextField.e.h;
        if (bVar == null) {
            return;
        }
        bVar.a();
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f.g.hasFocus()) {
            this.e.k = null;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
