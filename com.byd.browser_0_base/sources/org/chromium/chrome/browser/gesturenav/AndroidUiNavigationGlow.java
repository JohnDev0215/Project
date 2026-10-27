package org.chromium.chrome.browser.gesturenav;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import defpackage.l10;
import defpackage.sn0;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidUiNavigationGlow extends sn0 {
    public final l10 f;
    public final GlowView g;
    public float h;

    public static class GlowView extends View {
        public final EdgeEffect e;

        public GlowView(Context context) {
            super(context);
            EdgeEffect edgeEffect = new EdgeEffect(context);
            this.e = edgeEffect;
            edgeEffect.setColor(-16777216);
        }

        @Override // android.view.View
        public final void onDraw(Canvas canvas) {
            boolean zDraw;
            super.onDraw(canvas);
            EdgeEffect edgeEffect = this.e;
            if (edgeEffect.isFinished()) {
                zDraw = false;
            } else {
                int iSave = canvas.save();
                canvas.translate(getWidth(), 0.0f);
                canvas.rotate(90.0f, 0.0f, 0.0f);
                zDraw = edgeEffect.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (zDraw) {
                postInvalidateOnAnimation();
            }
        }

        @Override // android.view.View
        public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            this.e.setSize(getHeight(), getWidth());
        }
    }

    public AndroidUiNavigationGlow(ViewGroup viewGroup) {
        super(viewGroup);
        GlowView glowView = new GlowView(viewGroup.getContext());
        this.g = glowView;
        glowView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.f = new l10(15, this);
    }

    @Override // defpackage.sn0
    public final void e(float f) {
        float f2 = -(f - this.h);
        this.h = f;
        this.g.e.onPull(f2 / ((ViewGroup) this.e).getWidth());
    }

    @Override // defpackage.sn0
    public final void f(float f, float f2) {
        ViewGroup viewGroup = (ViewGroup) this.e;
        viewGroup.removeCallbacks(this.f);
        GlowView glowView = this.g;
        if (glowView.getParent() == null) {
            viewGroup.addView(glowView);
        }
    }

    @Override // defpackage.sn0
    public final void h() {
        GlowView glowView = this.g;
        glowView.e.onRelease();
        glowView.postInvalidateOnAnimation();
        if (glowView.getParent() != null) {
            ((ViewGroup) this.e).postDelayed(this.f, 500L);
        }
        this.h = 0.0f;
    }

    @Override // defpackage.sn0
    public final void j() {
        h();
    }
}
