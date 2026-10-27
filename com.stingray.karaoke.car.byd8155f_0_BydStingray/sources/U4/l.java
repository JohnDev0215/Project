package U4;

import F1.AbstractC0265y;
import F1.H;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class l implements View.OnAttachStateChangeListener {
    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        view.removeOnAttachStateChangeListener(this);
        int[] iArr = H.f2303a;
        AbstractC0265y.b(view);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
