package defpackage;

import android.view.View;
import defpackage.qh3;

/* JADX INFO: compiled from: DisplayStyleObserverAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eb0 implements db0, View.OnAttachStateChangeListener {
    public final db0 e;
    public qh3.b f;
    public boolean g;
    public final qh3 h;

    public eb0(View view, qh3 qh3Var, db0 db0Var) {
        this.h = qh3Var;
        this.e = db0Var;
        this.g = view.isAttachedToWindow();
        view.addOnAttachStateChangeListener(this);
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
        this.f = bVar;
        if (this.g) {
            this.e.d(bVar);
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.g = true;
        d(this.f);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.g = false;
    }
}
