package F0;

import T.C0767t0;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class i1 implements View.OnAttachStateChangeListener {
    public final /* synthetic */ View l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0767t0 f2213m;

    public i1(View view, C0767t0 c0767t0) {
        this.l = view;
        this.f2213m = c0767t0;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) throws IllegalAccessException, InvocationTargetException {
        this.l.removeOnAttachStateChangeListener(this);
        this.f2213m.s();
    }
}
