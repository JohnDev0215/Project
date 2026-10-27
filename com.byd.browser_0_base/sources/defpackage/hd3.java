package defpackage;

import android.view.View;

/* JADX INFO: compiled from: ToolbarUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class hd3 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ xe3 e;
    public final /* synthetic */ View f;
    public final /* synthetic */ View g;

    public hd3(xe3 xe3Var, View view, View view2) {
        this.e = xe3Var;
        this.f = view;
        this.g = view2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        xe3 xe3Var = this.e;
        View view2 = this.f;
        View view3 = this.g;
        jd3.a(xe3Var, view2, view3);
        view3.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
