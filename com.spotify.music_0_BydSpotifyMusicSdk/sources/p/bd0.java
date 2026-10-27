package p;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class bd0 implements TextWatcher {
    public final EditText c;
    public ad0 k;
    public final boolean e = false;
    public boolean l = true;

    public bd0(EditText editText) {
        this.c = editText;
    }

    public static void a(EditText editText, int i) {
        int length;
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            jc0 jc0VarA = jc0.a();
            if (editableText == null) {
                length = 0;
            } else {
                jc0VarA.getClass();
                length = editableText.length();
            }
            jc0VarA.f(0, length, editableText);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        EditText editText = this.c;
        if (editText.isInEditMode() || !this.l) {
            return;
        }
        if ((this.e || jc0.j != null) && i2 <= i3 && (charSequence instanceof Spannable)) {
            int iB = jc0.a().b();
            if (iB != 0) {
                if (iB == 1) {
                    jc0.a().f(i, i3 + i, (Spannable) charSequence);
                    return;
                } else if (iB != 3) {
                    return;
                }
            }
            jc0 jc0VarA = jc0.a();
            if (this.k == null) {
                this.k = new ad0(editText);
            }
            jc0VarA.g(this.k);
        }
    }
}
