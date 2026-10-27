package defpackage;

import android.text.InputFilter;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.widget.TextView;
import androidx.emoji2.text.d;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: EmojiInputFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class ng0 implements InputFilter {
    public final TextView a;
    public a b;

    /* JADX INFO: compiled from: EmojiInputFilter.java */
    public static class a extends d.f {
        public final WeakReference a;
        public final WeakReference b;

        public a(TextView textView, ng0 ng0Var) {
            this.a = new WeakReference(textView);
            this.b = new WeakReference(ng0Var);
        }

        @Override // androidx.emoji2.text.d.f
        public final void b() {
            boolean z;
            int length;
            InputFilter[] filters;
            TextView textView = (TextView) this.a.get();
            InputFilter inputFilter = (InputFilter) this.b.get();
            if (inputFilter == null || textView == null || (filters = textView.getFilters()) == null) {
                z = false;
            } else {
                for (InputFilter inputFilter2 : filters) {
                    if (inputFilter2 == inputFilter) {
                        z = true;
                        break;
                    }
                }
                z = false;
            }
            if (z && textView.isAttachedToWindow()) {
                CharSequence text = textView.getText();
                d dVarA = d.a();
                if (text == null) {
                    length = 0;
                } else {
                    dVarA.getClass();
                    length = text.length();
                }
                CharSequence charSequenceF = dVarA.f(0, length, text);
                if (text == charSequenceF) {
                    return;
                }
                int selectionStart = Selection.getSelectionStart(charSequenceF);
                int selectionEnd = Selection.getSelectionEnd(charSequenceF);
                textView.setText(charSequenceF);
                if (charSequenceF instanceof Spannable) {
                    Spannable spannable = (Spannable) charSequenceF;
                    if (selectionStart >= 0 && selectionEnd >= 0) {
                        Selection.setSelection(spannable, selectionStart, selectionEnd);
                    } else if (selectionStart >= 0) {
                        Selection.setSelection(spannable, selectionStart);
                    } else if (selectionEnd >= 0) {
                        Selection.setSelection(spannable, selectionEnd);
                    }
                }
            }
        }
    }

    public ng0(TextView textView) {
        this.a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        TextView textView = this.a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int iB = d.a().b();
        if (iB != 0) {
            boolean z = true;
            if (iB == 1) {
                if (i4 == 0 && i3 == 0 && spanned.length() == 0 && charSequence == textView.getText()) {
                    z = false;
                }
                if (!z || charSequence == null) {
                    return charSequence;
                }
                if (i != 0 || i2 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i, i2);
                }
                return d.a().f(0, charSequence.length(), charSequence);
            }
            if (iB != 3) {
                return charSequence;
            }
        }
        d dVarA = d.a();
        if (this.b == null) {
            this.b = new a(textView, this);
        }
        dVarA.g(this.b);
        return charSequence;
    }
}
