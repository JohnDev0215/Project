package org.chromium.chrome.browser.logo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.FloatProperty;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.nd;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class LogoView extends FrameLayout implements View.OnClickListener {
    public Bitmap e;
    public Bitmap f;
    public Bitmap g;
    public nd h;
    public ObjectAnimator i;
    public final Paint j;
    public Matrix k;
    public Matrix l;
    public Matrix m;
    public boolean n;
    public boolean o;
    public boolean p;
    public final LoadingView q;
    public float r;
    public c s;
    public final a t;

    public class a extends FloatProperty<LogoView> {
        public a() {
            super("");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((LogoView) obj).r);
        }

        @Override // android.util.FloatProperty
        public final void setValue(LogoView logoView, float f) {
            LogoView logoView2 = logoView;
            if (logoView2.r != f) {
                logoView2.r = f;
                LogoView.this.invalidate();
            }
        }
    }

    @FunctionalInterface
    public interface c {
        void a(boolean z);
    }

    public LogoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.p = true;
        this.t = new a();
        this.k = new Matrix();
        this.n = true;
        Paint paint = new Paint();
        this.j = paint;
        paint.setFilterBitmap(true);
        setOnClickListener(this);
        setClickable(false);
        setWillNotDraw(false);
        LoadingView loadingView = new LoadingView(getContext());
        this.q = loadingView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        loadingView.setLayoutParams(layoutParams);
        loadingView.setVisibility(8);
        addView(loadingView);
    }

    public final void a(int i, int i2, Matrix matrix, boolean z) {
        float width = getWidth();
        float f = i;
        float height = getHeight();
        float f2 = i2;
        float fMin = Math.min(width / f, height / f2);
        if (z) {
            fMin = Math.min(1.0f, fMin);
        }
        int iRound = Math.round((width - (f * fMin)) * 0.5f);
        int iRound2 = Math.round((height - (f2 * fMin)) * 0.5f);
        matrix.setScale(fMin, fMin);
        matrix.postTranslate(iRound, iRound2);
    }

    public final void b(Bitmap bitmap, String str, boolean z, boolean z2) {
        ObjectAnimator objectAnimator = this.i;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        this.q.c();
        if (this.e == bitmap) {
            return;
        }
        this.f = bitmap;
        this.l = new Matrix();
        this.o = z;
        a(this.f.getWidth(), this.f.getHeight(), this.l, this.o);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.t, 0.0f, 1.0f);
        this.i = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(this.p ? 400L : 0L);
        this.i.addListener(new b(str, z2));
        this.i.start();
    }

    public boolean getAnimationEnabledForTesting() {
        return this.p;
    }

    public c getClickHandlerForTesting() {
        return this.s;
    }

    public Bitmap getDefaultGoogleLogoForTesting() {
        return this.g;
    }

    public ObjectAnimator getFadeAnimationForTesting() {
        return this.i;
    }

    public int getLoadingViewVisibilityForTesting() {
        return this.q.getVisibility();
    }

    public Bitmap getLogoForTesting() {
        return this.e;
    }

    public Bitmap getNewLogoForTesting() {
        return this.f;
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        if (drawable == this.h) {
            invalidate();
        } else {
            super.invalidateDrawable(drawable);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        c cVar;
        if (view != this || (cVar = this.s) == null) {
            return;
        }
        if (this.r != 0.0f) {
            return;
        }
        cVar.a(this.h != null);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (this.h != null) {
            ObjectAnimator objectAnimator = this.i;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            this.e = null;
            this.f = null;
            canvas.save();
            canvas.concat(this.m);
            this.h.draw(canvas);
            canvas.restore();
            return;
        }
        Bitmap bitmap = this.e;
        Paint paint = this.j;
        if (bitmap != null) {
            float f = this.r;
            if (f < 0.5f) {
                paint.setAlpha((int) ((0.5f - f) * 510.0f));
                canvas.save();
                canvas.concat(this.k);
                canvas.drawBitmap(this.e, 0.0f, 0.0f, paint);
                canvas.restore();
            }
        }
        if (this.f != null) {
            float f2 = this.r;
            if (f2 > 0.5f) {
                paint.setAlpha((int) ((f2 - 0.5f) * 510.0f));
                canvas.save();
                canvas.concat(this.l);
                canvas.drawBitmap(this.f, 0.0f, 0.0f, paint);
                canvas.restore();
            }
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 == i4) {
            return;
        }
        nd ndVar = this.h;
        if (ndVar != null) {
            a(ndVar.h, ndVar.i, this.m, false);
        }
        Bitmap bitmap = this.e;
        if (bitmap != null) {
            a(bitmap.getWidth(), this.e.getHeight(), this.k, this.n);
        }
        Bitmap bitmap2 = this.f;
        if (bitmap2 != null) {
            a(bitmap2.getWidth(), this.f.getHeight(), this.l, this.o);
        }
    }

    public void setAnimationEnabled(boolean z) {
        this.p = z;
    }

    public void setClickHandler(c cVar) {
        this.s = cVar;
    }

    public void setDefaultGoogleLogo(Bitmap bitmap) {
        this.g = bitmap;
    }

    public void setLoadingViewVisibilityForTesting(int i) {
        this.q.setVisibility(i);
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return drawable == this.h || super.verifyDrawable(drawable);
    }

    public class b implements Animator.AnimatorListener {
        public final /* synthetic */ String e;
        public final /* synthetic */ boolean f;

        public b(String str, boolean z) {
            this.e = str;
            this.f = z;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            onAnimationEnd(animator);
            LogoView.this.invalidate();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            LogoView logoView = LogoView.this;
            logoView.e = logoView.f;
            logoView.k = logoView.l;
            logoView.n = logoView.o;
            logoView.f = null;
            logoView.l = null;
            logoView.r = 0.0f;
            logoView.i = null;
            String str = this.e;
            logoView.setContentDescription(str);
            boolean z = this.f;
            logoView.setClickable(z);
            logoView.setFocusable(z || !TextUtils.isEmpty(str));
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }
}
