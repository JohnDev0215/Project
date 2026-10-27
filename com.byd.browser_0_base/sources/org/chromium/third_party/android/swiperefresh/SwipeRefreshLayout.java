package org.chromium.third_party.android.swiperefresh;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import defpackage.fx2;
import defpackage.gx2;
import defpackage.ix2;
import defpackage.lx2;
import defpackage.pq2;
import defpackage.qc1;
import defpackage.rh3;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes3.dex */
public class SwipeRefreshLayout extends ViewGroup {
    public static final int[] H = {R.attr.enabled};
    public int A;
    public int B;
    public boolean C;
    public final a D;
    public float E;
    public final c F;
    public final d G;
    public e e;
    public f f;
    public boolean g;
    public float h;
    public final int i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public final DecelerateInterpolator n;
    public CircleImageView o;
    public int p;
    public int q;
    public float r;
    public int s;
    public qc1 t;
    public gx2 u;
    public ix2 v;
    public lx2 w;
    public b x;
    public float y;
    public boolean z;

    public class c extends Animation {
        public c() {
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            float fAbs = !swipeRefreshLayout.C ? swipeRefreshLayout.y - Math.abs(swipeRefreshLayout.s) : swipeRefreshLayout.y;
            swipeRefreshLayout.g((swipeRefreshLayout.q + ((int) ((((int) fAbs) - r0) * f))) - swipeRefreshLayout.o.getTop());
            float f2 = 1.0f - f;
            qc1.b bVar = swipeRefreshLayout.t.f;
            if (f2 != bVar.q) {
                bVar.q = f2;
                bVar.a();
            }
        }
    }

    public class d extends Animation {
        public d() {
        }

        @Override // android.view.animation.Animation
        public final void applyTransformation(float f, Transformation transformation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            swipeRefreshLayout.g((swipeRefreshLayout.q + ((int) ((swipeRefreshLayout.s - r3) * f))) - swipeRefreshLayout.o.getTop());
        }
    }

    public interface e {
        void c();
    }

    public interface f {
    }

    public SwipeRefreshLayout(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimationProgress(float f2) {
        this.o.setScaleX(f2);
        this.o.setScaleY(f2);
    }

    private void setColorViewAlpha(int i) {
        this.o.getBackground().setAlpha(i);
        this.t.setAlpha(i);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void bringChildToFront(View view) {
        if (indexOfChild(view) == getChildCount() - 1) {
            return;
        }
        super.bringChildToFront(view);
    }

    public final void c(float f2) {
        if (isEnabled() && this.l) {
            float f3 = this.h / 3;
            float fMax = this.E + Math.max(-f3, Math.min(f3, f2 * 0.5f));
            this.E = fMax;
            qc1.b bVar = this.t.f;
            if (!bVar.o) {
                bVar.o = true;
                bVar.a();
            }
            float f4 = fMax / this.h;
            if (f4 < 0.0f) {
                return;
            }
            float fMin = Math.min(1.0f, Math.abs(f4));
            float fMax2 = (((float) Math.max(((double) fMin) - 0.4d, 0.0d)) * 5.0f) / 3.0f;
            float fAbs = Math.abs(fMax) - this.h;
            float f5 = this.C ? this.y - this.s : this.y;
            double dMax = Math.max(0.0f, Math.min(fAbs, f5 * 2.0f) / f5) / 4.0f;
            float fPow = ((float) (dMax - Math.pow(dMax, 2.0d))) * 2.0f;
            int i = this.s + ((int) ((f5 * fMin) + (f5 * fPow * 2.0f)));
            if (this.o.getVisibility() != 0) {
                this.o.setVisibility(0);
            }
            if (!this.m) {
                this.o.setScaleX(1.0f);
                this.o.setScaleY(1.0f);
            }
            float f6 = this.h;
            if (fMax < f6 && this.m) {
                setAnimationProgress(fMax / f6);
            }
            qc1 qc1Var = this.t;
            float fMin2 = Math.min(0.8f, fMax2 * 0.8f);
            qc1.b bVar2 = qc1Var.f;
            bVar2.e = 0.0f;
            bVar2.a();
            bVar2.f = fMin2;
            bVar2.a();
            qc1 qc1Var2 = this.t;
            float fMin3 = Math.min(1.0f, fMax2);
            qc1.b bVar3 = qc1Var2.f;
            if (fMin3 != bVar3.q) {
                bVar3.q = fMin3;
                bVar3.a();
            }
            this.t.setAlpha(((int) (Math.max(0.0f, Math.min(1.0f, (fMin - 0.9f) / 0.1f)) * 179.0f)) + 76);
            qc1.b bVar4 = this.t.f;
            bVar4.g = ((fPow * 2.0f) + ((fMax2 * 0.4f) - 0.25f)) * 0.5f;
            bVar4.a();
            g(i - this.j);
        }
    }

    public final void d(boolean z) {
        b bVar;
        if (this.l) {
            this.l = false;
            float f2 = this.E;
            if (isEnabled() && z && f2 > this.h) {
                f(true, true);
                return;
            }
            this.g = false;
            qc1.b bVar2 = this.t.f;
            bVar2.e = 0.0f;
            bVar2.a();
            bVar2.f = 0.0f;
            bVar2.a();
            boolean z2 = this.m;
            if (z2) {
                bVar = null;
            } else {
                if (this.x == null) {
                    this.x = new b();
                }
                bVar = this.x;
            }
            int i = this.j;
            if (z2) {
                this.q = i;
                this.r = this.o.getScaleX();
                if (this.w == null) {
                    lx2 lx2Var = new lx2(this);
                    this.w = lx2Var;
                    lx2Var.setDuration(150L);
                }
                if (bVar != null) {
                    this.o.setAnimationListener(bVar);
                }
                this.o.clearAnimation();
                this.o.startAnimation(this.w);
            } else {
                this.q = i;
                d dVar = this.G;
                dVar.reset();
                dVar.setDuration(200L);
                dVar.setInterpolator(this.n);
                if (bVar != null) {
                    this.o.setAnimationListener(bVar);
                }
                this.o.clearAnimation();
                this.o.startAnimation(dVar);
            }
            qc1.b bVar3 = this.t.f;
            if (bVar3.o) {
                bVar3.o = false;
                bVar3.a();
            }
        }
    }

    public final void e() {
        this.l = false;
        f(false, false);
        this.t.stop();
        this.o.setVisibility(8);
        setColorViewAlpha(255);
        if (this.m) {
            setAnimationProgress(0.0f);
        } else {
            g(this.s - this.j);
        }
        this.j = this.o.getTop();
        f fVar = this.f;
        if (fVar != null) {
            fx2 fx2Var = (fx2) ((pq2) fVar).f;
            if (fx2Var.k != null) {
                return;
            }
            androidx.appcompat.widget.a aVar = new androidx.appcompat.widget.a(9, fx2Var);
            fx2Var.k = aVar;
            PostTask.c(rh3.a, aVar);
        }
    }

    public final void f(boolean z, boolean z2) {
        if (this.g != z) {
            this.z = z2;
            this.g = z;
            a aVar = this.D;
            if (!z) {
                i(aVar);
                return;
            }
            this.q = this.j;
            c cVar = this.F;
            cVar.reset();
            cVar.setDuration(200L);
            cVar.setInterpolator(this.n);
            if (aVar != null) {
                this.o.setAnimationListener(aVar);
            }
            this.o.clearAnimation();
            this.o.startAnimation(cVar);
        }
    }

    public final void g(int i) {
        this.o.bringToFront();
        this.o.offsetTopAndBottom(i);
        this.j = this.o.getTop();
    }

    @Override // android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        int i3 = this.p;
        return i3 < 0 ? i2 : i2 == i + (-1) ? i3 : i2 >= i3 ? i2 + 1 : i2;
    }

    public final boolean h() {
        if (!isEnabled() || this.g) {
            return false;
        }
        this.o.clearAnimation();
        this.t.stop();
        g(this.s - this.o.getTop());
        this.E = 0.0f;
        this.l = true;
        this.t.setAlpha(76);
        return true;
    }

    public final void i(a aVar) {
        if (this.v == null) {
            ix2 ix2Var = new ix2(this);
            this.v = ix2Var;
            ix2Var.setDuration(150L);
        }
        this.o.setAnimationListener(aVar);
        this.o.clearAnimation();
        this.o.startAnimation(this.v);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredWidth = getMeasuredWidth();
        if (getChildCount() == 0) {
            return;
        }
        int measuredWidth2 = this.o.getMeasuredWidth();
        int measuredHeight = this.o.getMeasuredHeight();
        int i5 = measuredWidth / 2;
        int i6 = measuredWidth2 / 2;
        int i7 = this.j;
        this.o.layout(i5 - i6, i7, i5 + i6, measuredHeight + i7);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.o.measure(View.MeasureSpec.makeMeasureSpec(this.A, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.B, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        if (!this.C && !this.k) {
            this.k = true;
            int i3 = (int) ((-this.o.getMeasuredHeight()) * 1.05f);
            this.s = i3;
            this.j = i3;
        }
        this.p = -1;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) == this.o) {
                this.p = i4;
                return;
            }
        }
    }

    @Deprecated
    public void setColorScheme(int... iArr) {
        setColorSchemeResources(iArr);
    }

    public void setColorSchemeColors(int... iArr) {
        qc1.b bVar = this.t.f;
        bVar.j = iArr;
        bVar.b(0);
        bVar.b(0);
    }

    public void setColorSchemeResources(int... iArr) {
        Resources resources = getResources();
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = resources.getColor(iArr[i]);
        }
        setColorSchemeColors(iArr2);
    }

    public void setDistanceToTriggerSync(int i) {
        this.h = i;
    }

    public void setOnRefreshListener(e eVar) {
        this.e = eVar;
    }

    public void setOnResetListener(f fVar) {
        this.f = fVar;
    }

    @Deprecated
    public void setProgressBackgroundColor(int i) {
        setProgressBackgroundColorSchemeResource(i);
    }

    public void setProgressBackgroundColorSchemeColor(int i) {
        this.o.setBackgroundColor(i);
        this.t.f.w = i;
    }

    public void setProgressBackgroundColorSchemeResource(int i) {
        setProgressBackgroundColorSchemeColor(getResources().getColor(i));
    }

    public void setRefreshing(boolean z) {
        if (!z || this.g == z) {
            f(z, false);
            return;
        }
        this.g = z;
        g(((int) (!this.C ? this.y + this.s : this.y)) - this.j);
        this.z = false;
        this.o.setVisibility(0);
        this.t.setAlpha(255);
        if (this.u == null) {
            gx2 gx2Var = new gx2(this);
            this.u = gx2Var;
            gx2Var.setDuration(this.i);
        }
        a aVar = this.D;
        if (aVar != null) {
            this.o.setAnimationListener(aVar);
        }
        this.o.clearAnimation();
        this.o.startAnimation(this.u);
    }

    public void setSize(int i) {
        if (i == 0 || i == 1) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            if (i == 0) {
                int i2 = (int) (displayMetrics.density * 56.0f);
                this.A = i2;
                this.B = i2;
            } else {
                int i3 = (int) (displayMetrics.density * 40.0f);
                this.A = i3;
                this.B = i3;
            }
            this.o.setImageDrawable(null);
            this.t.d(i);
            this.o.setImageDrawable(this.t);
        }
    }

    public SwipeRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.h = -1.0f;
        this.k = false;
        this.p = -1;
        this.D = new a();
        this.F = new c();
        this.G = new d();
        this.i = getResources().getInteger(R.integer.config_mediumAnimTime);
        setWillNotDraw(false);
        this.n = new DecelerateInterpolator(2.0f);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, H);
        setEnabled(typedArrayObtainStyledAttributes.getBoolean(0, true));
        typedArrayObtainStyledAttributes.recycle();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = (int) (displayMetrics.density * 40.0f);
        this.A = i;
        this.B = i;
        this.o = new CircleImageView(getContext());
        qc1 qc1Var = new qc1(getContext(), this);
        this.t = qc1Var;
        qc1Var.f.w = -328966;
        this.o.setImageDrawable(qc1Var);
        this.o.setVisibility(8);
        addView(this.o);
        setChildrenDrawingOrderEnabled(true);
        float f2 = displayMetrics.density * 64.0f;
        this.y = f2;
        this.h = f2;
    }

    public class a implements Animation.AnimationListener {
        public a() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            e eVar;
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.g) {
                swipeRefreshLayout.t.setAlpha(255);
                swipeRefreshLayout.t.start();
                if (swipeRefreshLayout.z && (eVar = swipeRefreshLayout.e) != null) {
                    eVar.c();
                }
            } else {
                swipeRefreshLayout.e();
            }
            swipeRefreshLayout.j = swipeRefreshLayout.o.getTop();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }

    public class b implements Animation.AnimationListener {
        public b() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationEnd(Animation animation) {
            SwipeRefreshLayout swipeRefreshLayout = SwipeRefreshLayout.this;
            if (swipeRefreshLayout.m) {
                return;
            }
            swipeRefreshLayout.i(swipeRefreshLayout.D);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public final void onAnimationStart(Animation animation) {
        }
    }
}
