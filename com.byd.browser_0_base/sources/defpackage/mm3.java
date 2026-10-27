package defpackage;

import android.text.TextUtils;
import android.view.View;
import defpackage.om3;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class mm3 extends om3.b<CharSequence> {
    public mm3(int i) {
        super(i, CharSequence.class, 64, 30);
    }

    @Override // om3.b
    public final CharSequence b(View view) {
        return om3.o.a(view);
    }

    @Override // om3.b
    public final void c(View view, CharSequence charSequence) {
        om3.o.b(view, charSequence);
    }

    @Override // om3.b
    public final boolean f(CharSequence charSequence, CharSequence charSequence2) {
        return !TextUtils.equals(charSequence, charSequence2);
    }
}
