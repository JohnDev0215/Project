package com.airbnb.lottie.w;

import android.view.Choreographer;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
public class e extends a implements Choreographer.FrameCallback {

    @Nullable
    private com.airbnb.lottie.d j;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f968c = 1.0f;
    private boolean d = false;
    private long e = 0;
    private float f = 0.0f;
    private int g = 0;
    private float h = -2.1474836E9f;
    private float i = 2.1474836E9f;

    @VisibleForTesting
    protected boolean k = false;

    private void A() {
        if (this.j == null) {
            return;
        }
        float f = this.f;
        if (f < this.h || f > this.i) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.h), Float.valueOf(this.i), Float.valueOf(this.f)));
        }
    }

    private float i() {
        com.airbnb.lottie.d dVar = this.j;
        if (dVar == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / dVar.h()) / Math.abs(this.f968c);
    }

    private boolean m() {
        return l() < 0.0f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    @MainThread
    public void cancel() {
        a();
        q();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        p();
        if (this.j == null || !isRunning()) {
            return;
        }
        com.airbnb.lottie.c.a("LottieValueAnimator#doFrame");
        float fI = (this.e != 0 ? j - r1 : 0L) / i();
        float f = this.f;
        if (m()) {
            fI = -fI;
        }
        float f2 = f + fI;
        this.f = f2;
        boolean z = !g.d(f2, k(), j());
        this.f = g.b(this.f, k(), j());
        this.e = j;
        e();
        if (z) {
            if (getRepeatCount() == -1 || this.g < getRepeatCount()) {
                c();
                this.g++;
                if (getRepeatMode() == 2) {
                    this.d = !this.d;
                    t();
                } else {
                    this.f = m() ? j() : k();
                }
                this.e = j;
            } else {
                this.f = this.f968c < 0.0f ? k() : j();
                q();
                b(m());
            }
        }
        A();
        com.airbnb.lottie.c.b("LottieValueAnimator#doFrame");
    }

    @MainThread
    public void endAnimation() {
        q();
        b(m());
    }

    public void f() {
        this.j = null;
        this.h = -2.1474836E9f;
        this.i = 2.1474836E9f;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float g() {
        com.airbnb.lottie.d dVar = this.j;
        if (dVar == null) {
            return 0.0f;
        }
        return (this.f - dVar.o()) / (this.j.f() - this.j.o());
    }

    @Override // android.animation.ValueAnimator
    @FloatRange(from = 0.0d, to = 1.0d)
    public float getAnimatedFraction() {
        float fJ;
        float fK;
        if (this.j == null) {
            return 0.0f;
        }
        if (m()) {
            fJ = j();
            fK = this.f;
        } else {
            fJ = this.f;
            fK = k();
        }
        return (fJ - fK) / (j() - k());
    }

    @Override // android.animation.ValueAnimator
    public Object getAnimatedValue() {
        return Float.valueOf(g());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public long getDuration() {
        com.airbnb.lottie.d dVar = this.j;
        if (dVar == null) {
            return 0L;
        }
        return (long) dVar.d();
    }

    public float h() {
        return this.f;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public boolean isRunning() {
        return this.k;
    }

    public float j() {
        com.airbnb.lottie.d dVar = this.j;
        if (dVar == null) {
            return 0.0f;
        }
        float f = this.i;
        return f == 2.1474836E9f ? dVar.f() : f;
    }

    public float k() {
        com.airbnb.lottie.d dVar = this.j;
        if (dVar == null) {
            return 0.0f;
        }
        float f = this.h;
        return f == -2.1474836E9f ? dVar.o() : f;
    }

    public float l() {
        return this.f968c;
    }

    @MainThread
    public void n() {
        q();
    }

    @MainThread
    public void o() {
        this.k = true;
        d(m());
        v((int) (m() ? j() : k()));
        this.e = 0L;
        this.g = 0;
        p();
    }

    protected void p() {
        if (isRunning()) {
            r(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    @MainThread
    protected void q() {
        r(true);
    }

    @MainThread
    protected void r(boolean z) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z) {
            this.k = false;
        }
    }

    @MainThread
    public void s() {
        float fK;
        this.k = true;
        p();
        this.e = 0L;
        if (m() && h() == k()) {
            fK = j();
        } else if (m() || h() != j()) {
            return;
        } else {
            fK = k();
        }
        this.f = fK;
    }

    @Override // android.animation.ValueAnimator
    public void setRepeatMode(int i) {
        super.setRepeatMode(i);
        if (i == 2 || !this.d) {
            return;
        }
        this.d = false;
        t();
    }

    public void t() {
        z(-l());
    }

    public void u(com.airbnb.lottie.d dVar) {
        float fO;
        float f;
        boolean z = this.j == null;
        this.j = dVar;
        if (z) {
            fO = (int) Math.max(this.h, dVar.o());
            f = Math.min(this.i, dVar.f());
        } else {
            fO = (int) dVar.o();
            f = dVar.f();
        }
        x(fO, (int) f);
        float f2 = this.f;
        this.f = 0.0f;
        v((int) f2);
    }

    public void v(float f) {
        if (this.f == f) {
            return;
        }
        this.f = g.b(f, k(), j());
        this.e = 0L;
        e();
    }

    public void w(float f) {
        x(this.h, f);
    }

    public void x(float f, float f2) {
        if (f > f2) {
            throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", Float.valueOf(f), Float.valueOf(f2)));
        }
        com.airbnb.lottie.d dVar = this.j;
        float fO = dVar == null ? -3.4028235E38f : dVar.o();
        com.airbnb.lottie.d dVar2 = this.j;
        float f3 = dVar2 == null ? Float.MAX_VALUE : dVar2.f();
        this.h = g.b(f, fO, f3);
        this.i = g.b(f2, fO, f3);
        v((int) g.b(this.f, f, f2));
    }

    public void y(int i) {
        x(i, (int) this.i);
    }

    public void z(float f) {
        this.f968c = f;
    }
}
