package defpackage;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.emoji2.text.d;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: EmojiTextWatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class rg0 implements TextWatcher {
    public final EditText e;
    public a g;
    public final boolean f = false;
    public boolean h = true;

    /* JADX INFO: compiled from: EmojiTextWatcher.java */
    public static class a extends d.f {
        public final WeakReference a;

        public a(EditText editText) {
            this.a = new WeakReference(editText);
        }

        @Override // androidx.emoji2.text.d.f
        public final void b() {
            rg0.a((EditText) this.a.get(), 1);
        }
    }

    public rg0(EditText editText) {
        this.e = editText;
    }

    public static void a(EditText editText, int i) {
        int length;
        if (i == 1 && editText != null && editText.isAttachedToWindow()) {
            Editable editableText = editText.getEditableText();
            int selectionStart = Selection.getSelectionStart(editableText);
            int selectionEnd = Selection.getSelectionEnd(editableText);
            d dVarA = d.a();
            if (editableText == null) {
                length = 0;
            } else {
                dVarA.getClass();
                length = editableText.length();
            }
            dVarA.f(0, length, editableText);
            if (selectionStart >= 0 && selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionStart, selectionEnd);
            } else if (selectionStart >= 0) {
                Selection.setSelection(editableText, selectionStart);
            } else if (selectionEnd >= 0) {
                Selection.setSelection(editableText, selectionEnd);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001b  */
    @Override // android.text.TextWatcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onTextChanged(java.lang.CharSequence r5, int r6, int r7, int r8) {
        /*
            r4 = this;
            android.widget.EditText r0 = r4.e
            boolean r1 = r0.isInEditMode()
            if (r1 != 0) goto L54
            boolean r1 = r4.h
            r2 = 1
            if (r1 == 0) goto L1b
            boolean r1 = r4.f
            r3 = 0
            if (r1 != 0) goto L1c
            androidx.emoji2.text.d r1 = androidx.emoji2.text.d.k
            if (r1 == 0) goto L18
            r1 = r2
            goto L19
        L18:
            r1 = r3
        L19:
            if (r1 != 0) goto L1c
        L1b:
            r3 = r2
        L1c:
            if (r3 == 0) goto L1f
            goto L54
        L1f:
            if (r7 > r8) goto L54
            boolean r7 = r5 instanceof android.text.Spannable
            if (r7 == 0) goto L54
            androidx.emoji2.text.d r7 = androidx.emoji2.text.d.a()
            int r7 = r7.b()
            if (r7 == 0) goto L40
            if (r7 == r2) goto L35
            r5 = 3
            if (r7 == r5) goto L40
            goto L54
        L35:
            android.text.Spannable r5 = (android.text.Spannable) r5
            androidx.emoji2.text.d r4 = androidx.emoji2.text.d.a()
            int r8 = r8 + r6
            r4.f(r6, r8, r5)
            goto L54
        L40:
            androidx.emoji2.text.d r5 = androidx.emoji2.text.d.a()
            rg0$a r6 = r4.g
            if (r6 != 0) goto L4f
            rg0$a r6 = new rg0$a
            r6.<init>(r0)
            r4.g = r6
        L4f:
            rg0$a r4 = r4.g
            r5.g(r4)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rg0.onTextChanged(java.lang.CharSequence, int, int, int):void");
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
