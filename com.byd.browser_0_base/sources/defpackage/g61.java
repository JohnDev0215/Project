package defpackage;

import android.content.Context;
import android.graphics.RectF;

/* JADX INFO: compiled from: LayoutManagerHost.java */
/* JADX INFO: loaded from: classes.dex */
public interface g61 {
    void b(boolean z, boolean z2);

    void d();

    void e(RectF rectF);

    void g(RectF rectF);

    int getBottomControlsHeightPixels();

    fj getBrowserControlsManager();

    Context getContext();

    rq0 getFullscreenManager();

    int getHeight();

    float getHeightMinusBrowserControls();

    j61 getLayoutRenderHost();

    int getTopControlsHeightPixels();

    int getWidth();

    void h(RectF rectF);

    default void f(Runnable runnable) {
    }
}
