package V1;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public final class h implements TextWatcher {
    public final EditText l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g f9324m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9325n = true;

    public h(EditText editText) {
        this.l = editText;
    }

    public static void a(EditText editText, int i7) {
        int length;
        if (i7 == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            T1.i iVarA = T1.i.a();
            if (editableText == null) {
                length = 0;
            } else {
                iVarA.getClass();
                length = editableText.length();
            }
            iVarA.f(0, length, 0, editableText);
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
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        EditText editText = this.l;
        if (!editText.isInEditMode() && this.f9325n && T1.i.c() && i10 <= i11 && (charSequence instanceof Spannable)) {
            int iB = T1.i.a().b();
            if (iB != 0) {
                if (iB == 1) {
                    T1.i.a().f(i7, i11 + i7, 0, (Spannable) charSequence);
                    return;
                } else if (iB != 3) {
                    return;
                }
            }
            T1.i iVarA = T1.i.a();
            if (this.f9324m == null) {
                this.f9324m = new g(editText);
            }
            iVarA.g(this.f9324m);
        }
    }
}
