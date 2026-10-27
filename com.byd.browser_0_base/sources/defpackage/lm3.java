package defpackage;

import android.text.TextUtils;
import android.view.View;
import defpackage.om3;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class lm3 extends om3.b<CharSequence> {
    public lm3(int i) {
        super(i, CharSequence.class, 8, 28);
    }

    @Override // om3.b
    public final CharSequence b(View view) {
        return om3.m.a(view);
    }

    @Override // om3.b
    public final void c(View view, CharSequence charSequence) {
        om3.m.e(view, charSequence);
    }

    @Override // om3.b
    public final boolean f(CharSequence charSequence, CharSequence charSequence2) {
        return !TextUtils.equals(charSequence, charSequence2);
    }
}
