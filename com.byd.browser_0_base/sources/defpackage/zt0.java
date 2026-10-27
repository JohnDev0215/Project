package defpackage;

import android.view.View;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: HardwareDraw.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zt0 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ du0 e;

    public zt0(du0 du0Var) {
        this.e = du0Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        TraceEvent.R("HardwareDraw::ViewAttachedToWindow");
        view.removeOnAttachStateChangeListener(this);
        this.e.c = false;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        TraceEvent.R("HardwareDraw::ViewDetachedFromWindow");
    }
}
