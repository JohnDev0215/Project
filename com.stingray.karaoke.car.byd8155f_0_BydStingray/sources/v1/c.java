package V1;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class c implements InputFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f9315a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public V0.f f9316b;

    public c(TextView textView) {
        this.f9315a = textView;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i7, int i10, Spanned spanned, int i11, int i12) {
        TextView textView = this.f9315a;
        if (textView.isInEditMode()) {
            return charSequence;
        }
        int iB = T1.i.a().b();
        if (iB != 0) {
            if (iB == 1) {
                if ((i12 == 0 && i11 == 0 && spanned.length() == 0 && charSequence == textView.getText()) || charSequence == null) {
                    return charSequence;
                }
                if (i7 != 0 || i10 != charSequence.length()) {
                    charSequence = charSequence.subSequence(i7, i10);
                }
                return T1.i.a().f(0, charSequence.length(), 0, charSequence);
            }
            if (iB != 3) {
                return charSequence;
            }
        }
        T1.i iVarA = T1.i.a();
        if (this.f9316b == null) {
            this.f9316b = new V0.f(textView, this);
        }
        iVarA.g(this.f9316b);
        return charSequence;
    }
}
