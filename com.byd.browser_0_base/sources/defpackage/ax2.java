package defpackage;

import org.chromium.components.browser_ui.banners.SwipableOverlayView;

/* JADX INFO: compiled from: SwipableOverlayView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ax2 extends os0 {
    public int a;
    public float b;
    public float c;
    public final /* synthetic */ SwipableOverlayView d;

    public ax2(SwipableOverlayView swipableOverlayView) {
        this.d = swipableOverlayView;
    }

    @Override // defpackage.os0
    public final void a(int i, int i2) {
        SwipableOverlayView swipableOverlayView = this.d;
        if (swipableOverlayView.h != 2) {
            return;
        }
        swipableOverlayView.h = 0;
        i(i, i2);
        boolean z = i > this.a;
        boolean z2 = this.b < ((float) swipableOverlayView.l);
        float translationY = swipableOverlayView.getTranslationY();
        float f = swipableOverlayView.l;
        swipableOverlayView.g((!z && (((1.0f - (translationY / f)) > (z2 ? 0.9f : 0.2f) ? 1 : ((1.0f - (translationY / f)) == (z2 ? 0.9f : 0.2f) ? 0 : -1)) > 0)) || ((((float) i) > (f * 0.5f) ? 1 : (((float) i) == (f * 0.5f) ? 0 : -1)) < 0));
    }

    @Override // defpackage.os0
    public final void b(int i, int i2) {
        SwipableOverlayView swipableOverlayView = this.d;
        if (swipableOverlayView.d() && swipableOverlayView.a()) {
            h(i, i2);
            swipableOverlayView.h = 2;
        }
    }

    @Override // defpackage.os0
    public final void c(int i, int i2) {
        SwipableOverlayView swipableOverlayView = this.d;
        if (swipableOverlayView.h != 1) {
            return;
        }
        swipableOverlayView.h = 0;
        i(i, i2);
        swipableOverlayView.g(swipableOverlayView.i(i));
    }

    @Override // defpackage.os0
    public final void d(int i, int i2) {
        this.a = i;
        SwipableOverlayView swipableOverlayView = this.d;
        if (!swipableOverlayView.h(i)) {
            h(i, i2);
        } else {
            if (swipableOverlayView.h == 0 || !swipableOverlayView.a() || swipableOverlayView.e()) {
                return;
            }
            i(i, i2);
        }
    }

    @Override // defpackage.os0
    public final void e(int i, int i2, boolean z) {
        SwipableOverlayView swipableOverlayView = this.d;
        if (swipableOverlayView.d() && swipableOverlayView.a()) {
            h(i, i2);
            this.a = i;
            swipableOverlayView.h = 1;
        }
    }

    public final void h(int i, int i2) {
        SwipableOverlayView swipableOverlayView = this.d;
        swipableOverlayView.k = i;
        this.c = i2;
        this.b = swipableOverlayView.getTranslationY();
    }

    public final void i(int i, int i2) {
        SwipableOverlayView swipableOverlayView = this.d;
        float fO = a8.o(this.b + (i2 - this.c) + (i - swipableOverlayView.k), swipableOverlayView.l, 0.0f);
        if (fO <= 0.0f) {
            h(i, i2);
        }
        swipableOverlayView.setTranslationY(fO);
    }
}
