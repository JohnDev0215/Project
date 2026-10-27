package defpackage;

import android.view.View;

/* JADX INFO: compiled from: NavigationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class vk1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ wk1 e;

    public vk1(wk1 wk1Var) {
        this.e = wk1Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.e.e();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }
}
