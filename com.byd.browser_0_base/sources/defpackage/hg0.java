package defpackage;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import org.chromium.components.autofill.prefeditor.EditorTextField;

/* JADX INFO: compiled from: EditorTextField.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hg0 implements View.OnFocusChangeListener {
    public final /* synthetic */ boolean e = false;
    public final /* synthetic */ EditorTextField f;

    public hg0(EditorTextField editorTextField) {
        this.f = editorTextField;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        EditorTextField editorTextField = this.f;
        if (z) {
            if (this.e && !editorTextField.i) {
                AutoCompleteTextView autoCompleteTextView = editorTextField.g;
                autoCompleteTextView.setSelection(autoCompleteTextView.getText().length());
                ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 0);
            }
            editorTextField.i = true;
        } else if (editorTextField.i) {
            editorTextField.c(!editorTextField.e.d());
        }
        if (editorTextField.e.r != 0) {
            editorTextField.f.setCounterEnabled(z);
        }
    }
}
