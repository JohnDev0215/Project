package defpackage;

import android.view.View;
import defpackage.om3;

/* JADX INFO: compiled from: ViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class nm3 extends om3.b<Boolean> {
    public nm3(int i) {
        super(i, Boolean.class, 0, 28);
    }

    @Override // om3.b
    public final Boolean b(View view) {
        return Boolean.valueOf(om3.m.b(view));
    }

    @Override // om3.b
    public final void c(View view, Boolean bool) {
        om3.m.d(view, bool.booleanValue());
    }

    @Override // om3.b
    public final boolean f(Boolean bool, Boolean bool2) {
        return !om3.b.a(bool, bool2);
    }
}
