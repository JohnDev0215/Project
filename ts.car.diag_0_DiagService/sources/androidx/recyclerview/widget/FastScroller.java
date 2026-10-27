package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
class FastScroller extends RecyclerView.l implements RecyclerView.q {
    private static final int[] x = {R.attr.state_pressed};
    private static final int[] y = new int[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f1068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f1069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final StateListDrawable f1070c;
    final Drawable d;
    private final int e;
    private final int f;
    private final StateListDrawable g;
    private final Drawable h;
    private final int i;
    private final int j;
    private RecyclerView m;

    @VisibleForTesting
    float mHorizontalDragX;

    @VisibleForTesting
    int mHorizontalThumbCenterX;

    @VisibleForTesting
    int mHorizontalThumbWidth;

    @VisibleForTesting
    float mVerticalDragY;

    @VisibleForTesting
    int mVerticalThumbCenterY;

    @VisibleForTesting
    int mVerticalThumbHeight;
    private int k = 0;
    private int l = 0;
    private boolean n = false;
    private boolean o = false;
    private int p = 0;
    private int q = 0;
    private final int[] r = new int[2];
    private final int[] s = new int[2];
    final ValueAnimator t = ValueAnimator.ofFloat(0.0f, 1.0f);
    int u = 0;
    private final Runnable v = new a();
    private final RecyclerView.r w = new b();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FastScroller.this.hide(500);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b extends RecyclerView.r {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public void b(RecyclerView recyclerView, int i, int i2) {
            FastScroller.this.y(recyclerView.computeHorizontalScrollOffset(), recyclerView.computeVerticalScrollOffset());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f1073a = false;

        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1073a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1073a) {
                this.f1073a = false;
                return;
            }
            if (((Float) FastScroller.this.t.getAnimatedValue()).floatValue() == 0.0f) {
                FastScroller fastScroller = FastScroller.this;
                fastScroller.u = 0;
                fastScroller.v(0);
            } else {
                FastScroller fastScroller2 = FastScroller.this;
                fastScroller2.u = 2;
                fastScroller2.s();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d implements ValueAnimator.AnimatorUpdateListener {
        d() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
            FastScroller.this.f1070c.setAlpha(iFloatValue);
            FastScroller.this.d.setAlpha(iFloatValue);
            FastScroller.this.s();
        }
    }

    FastScroller(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.f1070c = stateListDrawable;
        this.d = drawable;
        this.g = stateListDrawable2;
        this.h = drawable2;
        this.e = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f = Math.max(i, drawable.getIntrinsicWidth());
        this.i = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.j = Math.max(i, drawable2.getIntrinsicWidth());
        this.f1068a = i2;
        this.f1069b = i3;
        this.f1070c.setAlpha(MediaControl.ACTION_NONE);
        this.d.setAlpha(MediaControl.ACTION_NONE);
        this.t.addListener(new c());
        this.t.addUpdateListener(new d());
        j(recyclerView);
    }

    private void k() {
        this.m.removeCallbacks(this.v);
    }

    private void l() {
        this.m.Z0(this);
        this.m.a1(this);
        this.m.b1(this.w);
        k();
    }

    private void m(Canvas canvas) {
        int i = this.l;
        int i2 = this.i;
        int i3 = this.mHorizontalThumbCenterX;
        int i4 = this.mHorizontalThumbWidth;
        this.g.setBounds(0, 0, i4, i2);
        this.h.setBounds(0, 0, this.k, this.j);
        canvas.translate(0.0f, i - i2);
        this.h.draw(canvas);
        canvas.translate(i3 - (i4 / 2), 0.0f);
        this.g.draw(canvas);
        canvas.translate(-r2, -r0);
    }

    private void n(Canvas canvas) {
        int i = this.k;
        int i2 = this.e;
        int i3 = i - i2;
        int i4 = this.mVerticalThumbCenterY;
        int i5 = this.mVerticalThumbHeight;
        int i6 = i4 - (i5 / 2);
        this.f1070c.setBounds(0, 0, i2, i5);
        this.d.setBounds(0, 0, this.f, this.l);
        if (!r()) {
            canvas.translate(i3, 0.0f);
            this.d.draw(canvas);
            canvas.translate(0.0f, i6);
            this.f1070c.draw(canvas);
            canvas.translate(-i3, -i6);
            return;
        }
        this.d.draw(canvas);
        canvas.translate(this.e, i6);
        canvas.scale(-1.0f, 1.0f);
        this.f1070c.draw(canvas);
        canvas.scale(-1.0f, 1.0f);
        canvas.translate(-this.e, -i6);
    }

    private int[] o() {
        int[] iArr = this.s;
        int i = this.f1069b;
        iArr[0] = i;
        iArr[1] = this.k - i;
        return iArr;
    }

    private int[] p() {
        int[] iArr = this.r;
        int i = this.f1069b;
        iArr[0] = i;
        iArr[1] = this.l - i;
        return iArr;
    }

    private void q(float f) {
        int[] iArrO = o();
        float fMax = Math.max(iArrO[0], Math.min(iArrO[1], f));
        if (Math.abs(this.mHorizontalThumbCenterX - fMax) < 2.0f) {
            return;
        }
        int iU = u(this.mHorizontalDragX, fMax, iArrO, this.m.computeHorizontalScrollRange(), this.m.computeHorizontalScrollOffset(), this.k);
        if (iU != 0) {
            this.m.scrollBy(iU, 0);
        }
        this.mHorizontalDragX = fMax;
    }

    private boolean r() {
        return ViewCompat.q(this.m) == 1;
    }

    private void t(int i) {
        k();
        this.m.postDelayed(this.v, i);
    }

    private int u(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 == 0) {
            return 0;
        }
        int i5 = i - i3;
        int i6 = (int) (((f2 - f) / i4) * i5);
        int i7 = i2 + i6;
        if (i7 >= i5 || i7 < 0) {
            return 0;
        }
        return i6;
    }

    private void w() {
        this.m.g(this);
        this.m.i(this);
        this.m.j(this.w);
    }

    private void z(float f) {
        int[] iArrP = p();
        float fMax = Math.max(iArrP[0], Math.min(iArrP[1], f));
        if (Math.abs(this.mVerticalThumbCenterY - fMax) < 2.0f) {
            return;
        }
        int iU = u(this.mVerticalDragY, fMax, iArrP, this.m.computeVerticalScrollRange(), this.m.computeVerticalScrollOffset(), this.l);
        if (iU != 0) {
            this.m.scrollBy(0, iU);
        }
        this.mVerticalDragY = fMax;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public boolean a(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        int i = this.p;
        if (i == 1) {
            boolean zIsPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean zIsPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (!zIsPointInsideVerticalThumb && !zIsPointInsideHorizontalThumb) {
                return false;
            }
            if (zIsPointInsideHorizontalThumb) {
                this.q = 1;
                this.mHorizontalDragX = (int) motionEvent.getX();
            } else if (zIsPointInsideVerticalThumb) {
                this.q = 2;
                this.mVerticalDragY = (int) motionEvent.getY();
            }
            v(2);
        } else if (i != 2) {
            return false;
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void b(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        if (this.p == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean zIsPointInsideVerticalThumb = isPointInsideVerticalThumb(motionEvent.getX(), motionEvent.getY());
            boolean zIsPointInsideHorizontalThumb = isPointInsideHorizontalThumb(motionEvent.getX(), motionEvent.getY());
            if (zIsPointInsideVerticalThumb || zIsPointInsideHorizontalThumb) {
                if (zIsPointInsideHorizontalThumb) {
                    this.q = 1;
                    this.mHorizontalDragX = (int) motionEvent.getX();
                } else if (zIsPointInsideVerticalThumb) {
                    this.q = 2;
                    this.mVerticalDragY = (int) motionEvent.getY();
                }
                v(2);
                return;
            }
            return;
        }
        if (motionEvent.getAction() == 1 && this.p == 2) {
            this.mVerticalDragY = 0.0f;
            this.mHorizontalDragX = 0.0f;
            v(1);
            this.q = 0;
            return;
        }
        if (motionEvent.getAction() == 2 && this.p == 2) {
            x();
            if (this.q == 1) {
                q(motionEvent.getX());
            }
            if (this.q == 2) {
                z(motionEvent.getY());
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public void c(boolean z) {
    }

    @VisibleForTesting
    Drawable getHorizontalThumbDrawable() {
        return this.g;
    }

    @VisibleForTesting
    Drawable getHorizontalTrackDrawable() {
        return this.h;
    }

    @VisibleForTesting
    Drawable getVerticalThumbDrawable() {
        return this.f1070c;
    }

    @VisibleForTesting
    Drawable getVerticalTrackDrawable() {
        return this.d;
    }

    @VisibleForTesting
    void hide(int i) {
        int i2 = this.u;
        if (i2 == 1) {
            this.t.cancel();
        } else if (i2 != 2) {
            return;
        }
        this.u = 3;
        ValueAnimator valueAnimator = this.t;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
        this.t.setDuration(i);
        this.t.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.x xVar) {
        if (this.k != this.m.getWidth() || this.l != this.m.getHeight()) {
            this.k = this.m.getWidth();
            this.l = this.m.getHeight();
            v(0);
        } else if (this.u != 0) {
            if (this.n) {
                n(canvas);
            }
            if (this.o) {
                m(canvas);
            }
        }
    }

    @VisibleForTesting
    boolean isPointInsideHorizontalThumb(float f, float f2) {
        if (f2 >= this.l - this.i) {
            int i = this.mHorizontalThumbCenterX;
            int i2 = this.mHorizontalThumbWidth;
            if (f >= i - (i2 / 2) && f <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean isPointInsideVerticalThumb(float f, float f2) {
        if (!r() ? f >= this.k - this.e : f <= this.e) {
            int i = this.mVerticalThumbCenterY;
            int i2 = this.mVerticalThumbHeight;
            if (f2 >= i - (i2 / 2) && f2 <= i + (i2 / 2)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    boolean isVisible() {
        return this.p == 1;
    }

    public void j(@Nullable RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.m;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            l();
        }
        this.m = recyclerView;
        if (recyclerView != null) {
            w();
        }
    }

    void s() {
        this.m.invalidate();
    }

    void v(int i) {
        if (i == 2 && this.p != 2) {
            this.f1070c.setState(x);
            k();
        }
        if (i == 0) {
            s();
        } else {
            x();
        }
        if (this.p == 2 && i != 2) {
            this.f1070c.setState(y);
            t(1200);
        } else if (i == 1) {
            t(1500);
        }
        this.p = i;
    }

    public void x() {
        int i = this.u;
        if (i != 0) {
            if (i != 3) {
                return;
            } else {
                this.t.cancel();
            }
        }
        this.u = 1;
        ValueAnimator valueAnimator = this.t;
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f);
        this.t.setDuration(500L);
        this.t.setStartDelay(0L);
        this.t.start();
    }

    void y(int i, int i2) {
        int iComputeVerticalScrollRange = this.m.computeVerticalScrollRange();
        int i3 = this.l;
        this.n = iComputeVerticalScrollRange - i3 > 0 && i3 >= this.f1068a;
        int iComputeHorizontalScrollRange = this.m.computeHorizontalScrollRange();
        int i4 = this.k;
        boolean z = iComputeHorizontalScrollRange - i4 > 0 && i4 >= this.f1068a;
        this.o = z;
        if (!this.n && !z) {
            if (this.p != 0) {
                v(0);
                return;
            }
            return;
        }
        if (this.n) {
            float f = i3;
            this.mVerticalThumbCenterY = (int) ((f * (i2 + (f / 2.0f))) / iComputeVerticalScrollRange);
            this.mVerticalThumbHeight = Math.min(i3, (i3 * i3) / iComputeVerticalScrollRange);
        }
        if (this.o) {
            float f2 = i4;
            this.mHorizontalThumbCenterX = (int) ((f2 * (i + (f2 / 2.0f))) / iComputeHorizontalScrollRange);
            this.mHorizontalThumbWidth = Math.min(i4, (i4 * i4) / iComputeHorizontalScrollRange);
        }
        int i5 = this.p;
        if (i5 == 0 || i5 == 1) {
            v(1);
        }
    }
}
