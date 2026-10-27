package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: ViewDrawBlocker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rm3 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ nw2 e;
    public final /* synthetic */ View f;

    public rm3(View view, nw2 nw2Var) {
        this.e = nw2Var;
        this.f = view;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        if (!((Boolean) this.e.get()).booleanValue()) {
            return false;
        }
        this.f.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }
}
