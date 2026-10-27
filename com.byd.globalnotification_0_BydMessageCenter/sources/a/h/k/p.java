package a.h.k;

import a.h.k.q;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class p extends q.a<Boolean> {
    public p(int i, Class cls, int i2) {
        super(i, cls, i2);
    }

    @Override // a.h.k.q.a
    public Boolean b(View view) {
        return Boolean.valueOf(view.isAccessibilityHeading());
    }

    @Override // a.h.k.q.a
    public void c(View view, Boolean bool) {
        view.setAccessibilityHeading(bool.booleanValue());
    }

    @Override // a.h.k.q.a
    public boolean e(Boolean bool, Boolean bool2) {
        return !a(bool, bool2);
    }
}
