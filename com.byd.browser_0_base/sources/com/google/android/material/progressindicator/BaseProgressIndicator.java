package com.google.android.material.progressindicator;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.c90;
import defpackage.l5;
import defpackage.oj2;
import defpackage.oz0;
import defpackage.r83;
import defpackage.td;
import defpackage.uc1;
import defpackage.vd0;
import defpackage.w5;
import defpackage.xd0;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseProgressIndicator<S extends td> extends ProgressBar {
    public static final int q = R$style.Widget_MaterialComponents_ProgressIndicator;
    public final S e;
    public int f;
    public boolean g;
    public final boolean h;
    public final int i;
    public w5 j;
    public boolean k;
    public int l;
    public final a m;
    public final b n;
    public final c o;
    public final d p;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = BaseProgressIndicator.q;
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            if (baseProgressIndicator.i > 0) {
                SystemClock.uptimeMillis();
            }
            baseProgressIndicator.setVisibility(0);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = BaseProgressIndicator.q;
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            boolean z = false;
            ((vd0) baseProgressIndicator.getCurrentDrawable()).f(false, false, true);
            if ((baseProgressIndicator.getProgressDrawable() == null || !baseProgressIndicator.getProgressDrawable().isVisible()) && (baseProgressIndicator.getIndeterminateDrawable() == null || !baseProgressIndicator.getIndeterminateDrawable().isVisible())) {
                z = true;
            }
            if (z) {
                baseProgressIndicator.setVisibility(4);
            }
            baseProgressIndicator.getClass();
        }
    }

    public class c extends l5.a {
        public c() {
        }

        @Override // l5.a
        public final void b(Drawable drawable) {
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            baseProgressIndicator.setIndeterminate(false);
            baseProgressIndicator.b(baseProgressIndicator.f, baseProgressIndicator.g);
        }
    }

    public class d extends l5.a {
        public d() {
        }

        @Override // l5.a
        public final void b(Drawable drawable) {
            BaseProgressIndicator baseProgressIndicator = BaseProgressIndicator.this;
            if (baseProgressIndicator.k) {
                return;
            }
            baseProgressIndicator.setVisibility(baseProgressIndicator.l);
        }
    }

    public BaseProgressIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(uc1.a(context, attributeSet, i, q), attributeSet, i);
        this.k = false;
        this.l = 4;
        this.m = new a();
        this.n = new b();
        this.o = new c();
        this.p = new d();
        Context context2 = getContext();
        this.e = (S) a(context2, attributeSet);
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.BaseProgressIndicator, i, i2, new int[0]);
        typedArrayD.getInt(R$styleable.BaseProgressIndicator_showDelay, -1);
        this.i = Math.min(typedArrayD.getInt(R$styleable.BaseProgressIndicator_minHideDelay, -1), 1000);
        typedArrayD.recycle();
        this.j = new w5();
        this.h = true;
    }

    private xd0<S> getCurrentDrawingDelegate() {
        if (isIndeterminate()) {
            if (getIndeterminateDrawable() == null) {
                return null;
            }
            return getIndeterminateDrawable().p;
        }
        if (getProgressDrawable() == null) {
            return null;
        }
        return getProgressDrawable().p;
    }

    public abstract S a(Context context, AttributeSet attributeSet);

    public void b(int i, boolean z) {
        if (!isIndeterminate()) {
            super.setProgress(i);
            if (getProgressDrawable() == null || z) {
                return;
            }
            getProgressDrawable().jumpToCurrentState();
            return;
        }
        if (getProgressDrawable() != null) {
            this.f = i;
            this.g = z;
            this.k = true;
            if (getIndeterminateDrawable().isVisible()) {
                w5 w5Var = this.j;
                ContentResolver contentResolver = getContext().getContentResolver();
                w5Var.getClass();
                if (Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f) != 0.0f) {
                    getIndeterminateDrawable().q.d();
                    return;
                }
            }
            this.o.b(getIndeterminateDrawable());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c() {
        /*
            r4 = this;
            java.util.WeakHashMap<android.view.View, in3> r0 = defpackage.om3.a
            boolean r0 = om3.g.b(r4)
            r1 = 0
            if (r0 == 0) goto L34
            int r0 = r4.getWindowVisibility()
            if (r0 != 0) goto L34
            r0 = r4
        L10:
            int r2 = r0.getVisibility()
            r3 = 1
            if (r2 == 0) goto L18
            goto L26
        L18:
            android.view.ViewParent r0 = r0.getParent()
            if (r0 != 0) goto L28
            int r4 = r4.getWindowVisibility()
            if (r4 != 0) goto L26
        L24:
            r4 = r3
            goto L2d
        L26:
            r4 = r1
            goto L2d
        L28:
            boolean r2 = r0 instanceof android.view.View
            if (r2 != 0) goto L31
            goto L24
        L2d:
            if (r4 == 0) goto L34
            r1 = r3
            goto L34
        L31:
            android.view.View r0 = (android.view.View) r0
            goto L10
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.progressindicator.BaseProgressIndicator.c():boolean");
    }

    @Override // android.widget.ProgressBar
    public Drawable getCurrentDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public int getHideAnimationBehavior() {
        return this.e.f;
    }

    public int[] getIndicatorColor() {
        return this.e.c;
    }

    public int getShowAnimationBehavior() {
        return this.e.e;
    }

    public int getTrackColor() {
        return this.e.d;
    }

    public int getTrackCornerRadius() {
        return this.e.b;
    }

    public int getTrackThickness() {
        return this.e.a;
    }

    @Override // android.view.View
    public final void invalidate() {
        super.invalidate();
        if (getCurrentDrawable() != null) {
            getCurrentDrawable().invalidateSelf();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getProgressDrawable() != null && getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().q.c(this.o);
        }
        c90<S> progressDrawable = getProgressDrawable();
        d dVar = this.p;
        if (progressDrawable != null) {
            getProgressDrawable().a(dVar);
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().a(dVar);
        }
        if (c()) {
            if (this.i > 0) {
                SystemClock.uptimeMillis();
            }
            setVisibility(0);
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final void onDetachedFromWindow() {
        removeCallbacks(this.n);
        removeCallbacks(this.m);
        ((vd0) getCurrentDrawable()).f(false, false, false);
        oz0<S> indeterminateDrawable = getIndeterminateDrawable();
        d dVar = this.p;
        if (indeterminateDrawable != null) {
            getIndeterminateDrawable().h(dVar);
            getIndeterminateDrawable().q.f();
        }
        if (getProgressDrawable() != null) {
            getProgressDrawable().h(dVar);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        int iSave = canvas.save();
        if (getPaddingLeft() != 0 || getPaddingTop() != 0) {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        if (getPaddingRight() != 0 || getPaddingBottom() != 0) {
            canvas.clipRect(0, 0, getWidth() - (getPaddingLeft() + getPaddingRight()), getHeight() - (getPaddingTop() + getPaddingBottom()));
        }
        getCurrentDrawable().draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        xd0<S> currentDrawingDelegate = getCurrentDrawingDelegate();
        if (currentDrawingDelegate == null) {
            return;
        }
        int iE = currentDrawingDelegate.e();
        int iD = currentDrawingDelegate.d();
        setMeasuredDimension(iE < 0 ? getMeasuredWidth() : iE + getPaddingLeft() + getPaddingRight(), iD < 0 ? getMeasuredHeight() : iD + getPaddingTop() + getPaddingBottom());
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (this.h) {
            ((vd0) getCurrentDrawable()).f(c(), false, z);
        }
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.h) {
            ((vd0) getCurrentDrawable()).f(c(), false, false);
        }
    }

    public void setAnimatorDurationScaleProvider(w5 w5Var) {
        this.j = w5Var;
        if (getProgressDrawable() != null) {
            getProgressDrawable().g = w5Var;
        }
        if (getIndeterminateDrawable() != null) {
            getIndeterminateDrawable().g = w5Var;
        }
    }

    public void setHideAnimationBehavior(int i) {
        this.e.f = i;
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setIndeterminate(boolean z) {
        if (z == isIndeterminate()) {
            return;
        }
        vd0 vd0Var = (vd0) getCurrentDrawable();
        if (vd0Var != null) {
            vd0Var.f(false, false, false);
        }
        super.setIndeterminate(z);
        vd0 vd0Var2 = (vd0) getCurrentDrawable();
        if (vd0Var2 != null) {
            vd0Var2.f(c(), false, false);
        }
        if ((vd0Var2 instanceof oz0) && c()) {
            ((oz0) vd0Var2).q.e();
        }
        this.k = false;
    }

    @Override // android.widget.ProgressBar
    public void setIndeterminateDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setIndeterminateDrawable(null);
        } else {
            if (!(drawable instanceof oz0)) {
                throw new IllegalArgumentException("Cannot set framework drawable as indeterminate drawable.");
            }
            ((vd0) drawable).f(false, false, false);
            super.setIndeterminateDrawable(drawable);
        }
    }

    public void setIndicatorColor(int... iArr) {
        if (iArr.length == 0) {
            iArr = new int[]{oj2.o(getContext(), R$attr.colorPrimary, -1)};
        }
        if (Arrays.equals(getIndicatorColor(), iArr)) {
            return;
        }
        this.e.c = iArr;
        getIndeterminateDrawable().q.b();
        invalidate();
    }

    @Override // android.widget.ProgressBar
    public synchronized void setProgress(int i) {
        if (isIndeterminate()) {
            return;
        }
        b(i, false);
    }

    @Override // android.widget.ProgressBar
    public void setProgressDrawable(Drawable drawable) {
        if (drawable == null) {
            super.setProgressDrawable(null);
        } else {
            if (!(drawable instanceof c90)) {
                throw new IllegalArgumentException("Cannot set framework drawable as progress drawable.");
            }
            c90 c90Var = (c90) drawable;
            c90Var.f(false, false, false);
            super.setProgressDrawable(c90Var);
            c90Var.setLevel((int) ((getProgress() / getMax()) * 10000.0f));
        }
    }

    public void setShowAnimationBehavior(int i) {
        this.e.e = i;
        invalidate();
    }

    public void setTrackColor(int i) {
        S s = this.e;
        if (s.d != i) {
            s.d = i;
            invalidate();
        }
    }

    public void setTrackCornerRadius(int i) {
        S s = this.e;
        if (s.b != i) {
            s.b = Math.min(i, s.a / 2);
        }
    }

    public void setTrackThickness(int i) {
        S s = this.e;
        if (s.a != i) {
            s.a = i;
            requestLayout();
        }
    }

    public void setVisibilityAfterHide(int i) {
        if (i != 0 && i != 4 && i != 8) {
            throw new IllegalArgumentException("The component's visibility must be one of VISIBLE, INVISIBLE, and GONE defined in View.");
        }
        this.l = i;
    }

    @Override // android.widget.ProgressBar
    public oz0<S> getIndeterminateDrawable() {
        return (oz0) super.getIndeterminateDrawable();
    }

    @Override // android.widget.ProgressBar
    public c90<S> getProgressDrawable() {
        return (c90) super.getProgressDrawable();
    }
}
