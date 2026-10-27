package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.banners.SwipableOverlayView;

/* JADX INFO: compiled from: SwipableOverlayView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bx2 implements View.OnLayoutChangeListener {
    public final /* synthetic */ SwipableOverlayView e;

    public bx2(SwipableOverlayView swipableOverlayView) {
        this.e = swipableOverlayView;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        SwipableOverlayView swipableOverlayView = this.e;
        swipableOverlayView.removeOnLayoutChangeListener(swipableOverlayView.f);
        swipableOverlayView.setTranslationY(swipableOverlayView.l);
        swipableOverlayView.m = true;
        swipableOverlayView.g(true);
    }
}
