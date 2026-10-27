package a4;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.Choreographer;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: renamed from: a4.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ChoreographerFrameCallbackC0924e extends ValueAnimator implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public O3.a f11106w;
    public final CopyOnWriteArraySet l = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CopyOnWriteArraySet f11097m = new CopyOnWriteArraySet();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CopyOnWriteArraySet f11098n = new CopyOnWriteArraySet();
    public float o = 1.0f;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f11099p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f11100q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f11101r = 0.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f11102s = 0.0f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f11103t = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f11104u = -2.1474836E9f;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f11105v = 2.1474836E9f;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f11107x = false;

    public final float a() {
        O3.a aVar = this.f11106w;
        if (aVar == null) {
            return 0.0f;
        }
        float f10 = this.f11102s;
        float f11 = aVar.l;
        return (f10 - f11) / (aVar.f5078m - f11);
    }

    @Override // android.animation.Animator
    public final void addListener(Animator.AnimatorListener animatorListener) {
        this.f11097m.add(animatorListener);
    }

    @Override // android.animation.Animator
    public final void addPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.f11098n.add(animatorPauseListener);
    }

    @Override // android.animation.ValueAnimator
    public final void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.l.add(animatorUpdateListener);
    }

    public final float b() {
        O3.a aVar = this.f11106w;
        if (aVar == null) {
            return 0.0f;
        }
        float f10 = this.f11105v;
        return f10 == 2.1474836E9f ? aVar.f5078m : f10;
    }

    public final float c() {
        O3.a aVar = this.f11106w;
        if (aVar == null) {
            return 0.0f;
        }
        float f10 = this.f11104u;
        return f10 == -2.1474836E9f ? aVar.l : f10;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void cancel() {
        Iterator it = this.f11097m.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
        }
        e(d());
        g(true);
    }

    public final boolean d() {
        return this.o < 0.0f;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j3) {
        boolean z6 = false;
        if (this.f11107x) {
            g(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
        O3.a aVar = this.f11106w;
        if (aVar == null || !this.f11107x) {
            return;
        }
        float fAbs = (this.f11100q != 0 ? j3 - r2 : 0L) / ((1.0E9f / aVar.f5079n) / Math.abs(this.o));
        float f10 = this.f11101r;
        if (d()) {
            fAbs = -fAbs;
        }
        float f11 = f10 + fAbs;
        float fC = c();
        float fB = b();
        PointF pointF = AbstractC0925f.f11108a;
        if (f11 >= fC && f11 <= fB) {
            z6 = true;
        }
        float fB2 = AbstractC0925f.b(f11, c(), b());
        this.f11101r = fB2;
        this.f11102s = fB2;
        this.f11100q = j3;
        if (z6) {
            f();
        } else if (getRepeatCount() == -1 || this.f11103t < getRepeatCount()) {
            if (getRepeatMode() == 2) {
                this.f11099p = !this.f11099p;
                this.o = -this.o;
            } else {
                float fB3 = d() ? b() : c();
                this.f11101r = fB3;
                this.f11102s = fB3;
            }
            this.f11100q = j3;
            f();
            Iterator it = this.f11097m.iterator();
            while (it.hasNext()) {
                ((Animator.AnimatorListener) it.next()).onAnimationRepeat(this);
            }
            this.f11103t++;
        } else {
            float fC2 = this.o < 0.0f ? c() : b();
            this.f11101r = fC2;
            this.f11102s = fC2;
            g(true);
            f();
            e(d());
        }
        if (this.f11106w == null) {
            return;
        }
        float f12 = this.f11102s;
        if (f12 < this.f11104u || f12 > this.f11105v) {
            throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", Float.valueOf(this.f11104u), Float.valueOf(this.f11105v), Float.valueOf(this.f11102s)));
        }
    }

    public final void e(boolean z6) {
        Iterator it = this.f11097m.iterator();
        while (it.hasNext()) {
            ((Animator.AnimatorListener) it.next()).onAnimationEnd(this, z6);
        }
    }

    public final void f() {
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((ValueAnimator.AnimatorUpdateListener) it.next()).onAnimationUpdate(this);
        }
    }

    public final void g(boolean z6) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (z6) {
            this.f11107x = false;
        }
    }

    @Override // android.animation.ValueAnimator
    public final float getAnimatedFraction() {
        float fC;
        float fB;
        float fC2;
        if (this.f11106w == null) {
            return 0.0f;
        }
        if (d()) {
            fC = b() - this.f11102s;
            fB = b();
            fC2 = c();
        } else {
            fC = this.f11102s - c();
            fB = b();
            fC2 = c();
        }
        return fC / (fB - fC2);
    }

    @Override // android.animation.ValueAnimator
    public final Object getAnimatedValue() {
        return Float.valueOf(a());
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final long getDuration() {
        O3.a aVar = this.f11106w;
        if (aVar == null) {
            return 0L;
        }
        return (long) aVar.b();
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    public final void h(float f10) {
        if (this.f11101r == f10) {
            return;
        }
        float fB = AbstractC0925f.b(f10, c(), b());
        this.f11101r = fB;
        this.f11102s = fB;
        this.f11100q = 0L;
        f();
    }

    public final void i(float f10, float f11) {
        if (f10 > f11) {
            throw new IllegalArgumentException("minFrame (" + f10 + ") must be <= maxFrame (" + f11 + ")");
        }
        O3.a aVar = this.f11106w;
        float f12 = aVar == null ? -3.4028235E38f : aVar.l;
        float f13 = aVar == null ? Float.MAX_VALUE : aVar.f5078m;
        float fB = AbstractC0925f.b(f10, f12, f13);
        float fB2 = AbstractC0925f.b(f11, f12, f13);
        if (fB == this.f11104u && fB2 == this.f11105v) {
            return;
        }
        this.f11104u = fB;
        this.f11105v = fB2;
        h((int) AbstractC0925f.b(this.f11102s, fB, fB2));
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final boolean isRunning() {
        return this.f11107x;
    }

    @Override // android.animation.Animator
    public final void removeAllListeners() {
        this.f11097m.clear();
    }

    @Override // android.animation.ValueAnimator
    public final void removeAllUpdateListeners() {
        this.l.clear();
    }

    @Override // android.animation.Animator
    public final void removeListener(Animator.AnimatorListener animatorListener) {
        this.f11097m.remove(animatorListener);
    }

    @Override // android.animation.Animator
    public final void removePauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.f11098n.remove(animatorPauseListener);
    }

    @Override // android.animation.ValueAnimator
    public final void removeUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.l.remove(animatorUpdateListener);
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final /* bridge */ /* synthetic */ Animator setDuration(long j3) {
        setDuration(j3);
        throw null;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void setInterpolator(TimeInterpolator timeInterpolator) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    @Override // android.animation.ValueAnimator
    public final void setRepeatMode(int i7) {
        super.setRepeatMode(i7);
        if (i7 == 2 || !this.f11099p) {
            return;
        }
        this.f11099p = false;
        this.o = -this.o;
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final void setStartDelay(long j3) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    @Override // android.animation.ValueAnimator, android.animation.Animator
    public final ValueAnimator setDuration(long j3) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }
}
