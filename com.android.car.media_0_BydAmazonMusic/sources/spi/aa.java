package spi;

import android.view.View;

/* JADX INFO: compiled from: SearchWidescreenController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aa implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View.OnApplyWindowInsetsListener a;
    public final /* synthetic */ com.android.car.ui.toolbar.f b;

    public aa(com.android.car.ui.toolbar.f fVar, View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.b = fVar;
        this.a = onApplyWindowInsetsListener;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.b.d.getRootView().setOnApplyWindowInsetsListener(this.a);
        this.b.d.removeOnAttachStateChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
