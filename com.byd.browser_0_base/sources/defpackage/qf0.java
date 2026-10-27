package defpackage;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: EditorDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class qf0 implements InputFilter {
    public final /* synthetic */ Pattern a;

    public qf0(Pattern pattern) {
        this.a = pattern;
    }

    @Override // android.text.InputFilter
    public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (i == i2 || this.a.matcher(charSequence.subSequence(i, i2)).matches()) {
            return null;
        }
        return "";
    }
}
