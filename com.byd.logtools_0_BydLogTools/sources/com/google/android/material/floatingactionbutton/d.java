package com.google.android.material.floatingactionbutton;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import b.g.k.t;
import d.b.a.a.w.m;
import d.b.a.a.w.p;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final TimeInterpolator f3643a = d.b.a.a.m.a.f4762c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f3644b = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f3645c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int[] f3646d = {R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final int[] f3647e = {R.attr.state_hovered, R.attr.state_enabled};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final int[] f3648f = {R.attr.state_enabled};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    static final int[] f3649g = new int[0];
    private int A;
    private ArrayList<Animator.AnimatorListener> C;
    private ArrayList<Animator.AnimatorListener> D;
    private ArrayList<i> E;
    final FloatingActionButton F;
    final d.b.a.a.v.b G;
    private ViewTreeObserver.OnPreDrawListener L;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    m f3650h;
    d.b.a.a.w.h i;
    Drawable j;
    com.google.android.material.floatingactionbutton.c k;
    Drawable l;
    boolean m;
    float o;
    float p;
    float q;
    int r;
    private final com.google.android.material.internal.h s;
    private d.b.a.a.m.h t;
    private d.b.a.a.m.h u;
    private Animator v;
    private d.b.a.a.m.h w;
    private d.b.a.a.m.h x;
    private float y;
    boolean n = true;
    private float z = 1.0f;
    private int B = 0;
    private final Rect H = new Rect();
    private final RectF I = new RectF();
    private final RectF J = new RectF();
    private final Matrix K = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3651a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f3652b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ j f3653c;

        a(boolean z, j jVar) {
            this.f3652b = z;
            this.f3653c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3651a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.v = null;
            if (this.f3651a) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.F;
            boolean z = this.f3652b;
            floatingActionButton.b(z ? 8 : 4, z);
            j jVar = this.f3653c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f3652b);
            d.this.B = 1;
            d.this.v = animator;
            this.f3651a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f3655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ j f3656b;

        b(boolean z, j jVar) {
            this.f3655a = z;
            this.f3656b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.v = null;
            j jVar = this.f3656b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f3655a);
            d.this.B = 2;
            d.this.v = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class c extends d.b.a.a.m.g {
        c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
            d.this.z = f2;
            return super.evaluate(f2, matrix, matrix2);
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.floatingactionbutton.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class C0067d implements TypeEvaluator<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        FloatEvaluator f3659a = new FloatEvaluator();

        C0067d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f2, Float f3, Float f4) {
            float fFloatValue = this.f3659a.evaluate(f2, (Number) f3, (Number) f4).floatValue();
            if (fFloatValue < 0.1f) {
                fFloatValue = 0.0f;
            }
            return Float.valueOf(fFloatValue);
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class e implements ViewTreeObserver.OnPreDrawListener {
        e() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            d.this.H();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class f extends l {
        f() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class g extends l {
        g() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            d dVar = d.this;
            return dVar.o + dVar.p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class h extends l {
        h() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            d dVar = d.this;
            return dVar.o + dVar.q;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    interface i {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    interface j {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class k extends l {
        k() {
            super(d.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.d.l
        protected float a() {
            return d.this.o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f3667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f3668c;

        private l() {
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.g0((int) this.f3668c);
            this.f3666a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f3666a) {
                d.b.a.a.w.h hVar = d.this.i;
                this.f3667b = hVar == null ? 0.0f : hVar.w();
                this.f3668c = a();
                this.f3666a = true;
            }
            d dVar = d.this;
            float f2 = this.f3667b;
            dVar.g0((int) (f2 + ((this.f3668c - f2) * valueAnimator.getAnimatedFraction())));
        }

        /* synthetic */ l(d dVar, a aVar) {
            this();
        }
    }

    d(FloatingActionButton floatingActionButton, d.b.a.a.v.b bVar) {
        this.F = floatingActionButton;
        this.G = bVar;
        com.google.android.material.internal.h hVar = new com.google.android.material.internal.h();
        this.s = hVar;
        hVar.a(f3644b, i(new h()));
        hVar.a(f3645c, i(new g()));
        hVar.a(f3646d, i(new g()));
        hVar.a(f3647e, i(new g()));
        hVar.a(f3648f, i(new k()));
        hVar.a(f3649g, i(new f()));
        this.y = floatingActionButton.getRotation();
    }

    private boolean a0() {
        return t.P(this.F) && !this.F.isInEditMode();
    }

    private void g(float f2, Matrix matrix) {
        matrix.reset();
        if (this.F.getDrawable() == null || this.A == 0) {
            return;
        }
        RectF rectF = this.I;
        RectF rectF2 = this.J;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.A;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.A;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    private AnimatorSet h(d.b.a.a.m.h hVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        hVar.h("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        hVar.h("scale").a(objectAnimatorOfFloat2);
        h0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        hVar.h("scale").a(objectAnimatorOfFloat3);
        h0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f4, this.K);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new d.b.a.a.m.f(), new c(), new Matrix(this.K));
        hVar.h("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        d.b.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    private void h0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new C0067d());
    }

    private ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f3643a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private d.b.a.a.m.h l() {
        if (this.u == null) {
            this.u = d.b.a.a.m.h.d(this.F.getContext(), d.b.a.a.a.f4680a);
        }
        return (d.b.a.a.m.h) b.g.j.h.c(this.u);
    }

    private d.b.a.a.m.h m() {
        if (this.t == null) {
            this.t = d.b.a.a.m.h.d(this.F.getContext(), d.b.a.a.a.f4681b);
        }
        return (d.b.a.a.m.h) b.g.j.h.c(this.t);
    }

    private ViewTreeObserver.OnPreDrawListener r() {
        if (this.L == null) {
            this.L = new e();
        }
        return this.L;
    }

    void A() {
        this.s.c();
    }

    void B() {
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            d.b.a.a.w.i.f(this.F, hVar);
        }
        if (K()) {
            this.F.getViewTreeObserver().addOnPreDrawListener(r());
        }
    }

    void C() {
    }

    void D() {
        ViewTreeObserver viewTreeObserver = this.F.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.L;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.L = null;
        }
    }

    void E(int[] iArr) {
        this.s.d(iArr);
    }

    void F(float f2, float f3, float f4) {
        f0();
        g0(f2);
    }

    void G(Rect rect) {
        b.g.j.h.d(this.l, "Didn't initialize content background");
        if (!Z()) {
            this.G.c(this.l);
        } else {
            this.G.c(new InsetDrawable(this.l, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    void H() {
        float rotation = this.F.getRotation();
        if (this.y != rotation) {
            this.y = rotation;
            d0();
        }
    }

    void I() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    void J() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    boolean K() {
        return true;
    }

    void L(ColorStateList colorStateList) {
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            hVar.setTintList(colorStateList);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.k;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    void M(PorterDuff.Mode mode) {
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            hVar.setTintMode(mode);
        }
    }

    final void N(float f2) {
        if (this.o != f2) {
            this.o = f2;
            F(f2, this.p, this.q);
        }
    }

    void O(boolean z) {
        this.m = z;
    }

    final void P(d.b.a.a.m.h hVar) {
        this.x = hVar;
    }

    final void Q(float f2) {
        if (this.p != f2) {
            this.p = f2;
            F(this.o, f2, this.q);
        }
    }

    final void R(float f2) {
        this.z = f2;
        Matrix matrix = this.K;
        g(f2, matrix);
        this.F.setImageMatrix(matrix);
    }

    final void S(int i2) {
        if (this.A != i2) {
            this.A = i2;
            e0();
        }
    }

    void T(int i2) {
        this.r = i2;
    }

    final void U(float f2) {
        if (this.q != f2) {
            this.q = f2;
            F(this.o, this.p, f2);
        }
    }

    void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, d.b.a.a.u.b.d(colorStateList));
        }
    }

    void W(boolean z) {
        this.n = z;
        f0();
    }

    final void X(m mVar) {
        this.f3650h = mVar;
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        Object obj = this.j;
        if (obj instanceof p) {
            ((p) obj).setShapeAppearanceModel(mVar);
        }
        com.google.android.material.floatingactionbutton.c cVar = this.k;
        if (cVar != null) {
            cVar.f(mVar);
        }
    }

    final void Y(d.b.a.a.m.h hVar) {
        this.w = hVar;
    }

    boolean Z() {
        return true;
    }

    final boolean b0() {
        return !this.m || this.F.getSizeDimension() >= this.r;
    }

    void c0(j jVar, boolean z) {
        if (z()) {
            return;
        }
        Animator animator = this.v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(0, z);
            this.F.setAlpha(1.0f);
            this.F.setScaleY(1.0f);
            this.F.setScaleX(1.0f);
            R(1.0f);
            if (jVar != null) {
                jVar.a();
                return;
            }
            return;
        }
        if (this.F.getVisibility() != 0) {
            this.F.setAlpha(0.0f);
            this.F.setScaleY(0.0f);
            this.F.setScaleX(0.0f);
            R(0.0f);
        }
        d.b.a.a.m.h hVarM = this.w;
        if (hVarM == null) {
            hVarM = m();
        }
        AnimatorSet animatorSetH = h(hVarM, 1.0f, 1.0f, 1.0f);
        animatorSetH.addListener(new b(z, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.C;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    public void d(Animator.AnimatorListener animatorListener) {
        if (this.D == null) {
            this.D = new ArrayList<>();
        }
        this.D.add(animatorListener);
    }

    void d0() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.y % 90.0f != 0.0f) {
                if (this.F.getLayerType() != 1) {
                    this.F.setLayerType(1, null);
                }
            } else if (this.F.getLayerType() != 0) {
                this.F.setLayerType(0, null);
            }
        }
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            hVar.e0((int) this.y);
        }
    }

    void e(Animator.AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    final void e0() {
        R(this.z);
    }

    void f(i iVar) {
        if (this.E == null) {
            this.E = new ArrayList<>();
        }
        this.E.add(iVar);
    }

    final void f0() {
        Rect rect = this.H;
        s(rect);
        G(rect);
        this.G.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    void g0(float f2) {
        d.b.a.a.w.h hVar = this.i;
        if (hVar != null) {
            hVar.W(f2);
        }
    }

    d.b.a.a.w.h j() {
        return new d.b.a.a.w.h((m) b.g.j.h.c(this.f3650h));
    }

    final Drawable k() {
        return this.l;
    }

    float n() {
        return this.o;
    }

    boolean o() {
        return this.m;
    }

    final d.b.a.a.m.h p() {
        return this.x;
    }

    float q() {
        return this.p;
    }

    void s(Rect rect) {
        int sizeDimension = this.m ? (this.r - this.F.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.n ? n() + this.q : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    float t() {
        return this.q;
    }

    final m u() {
        return this.f3650h;
    }

    final d.b.a.a.m.h v() {
        return this.w;
    }

    void w(j jVar, boolean z) {
        if (y()) {
            return;
        }
        Animator animator = this.v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(z ? 8 : 4, z);
            if (jVar != null) {
                jVar.b();
                return;
            }
            return;
        }
        d.b.a.a.m.h hVarL = this.x;
        if (hVarL == null) {
            hVarL = l();
        }
        AnimatorSet animatorSetH = h(hVarL, 0.0f, 0.0f, 0.0f);
        animatorSetH.addListener(new a(z, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.D;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        d.b.a.a.w.h hVarJ = j();
        this.i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.i.setTintMode(mode);
        }
        this.i.d0(-12303292);
        this.i.N(this.F.getContext());
        d.b.a.a.u.a aVar = new d.b.a.a.u.a(this.i.D());
        aVar.setTintList(d.b.a.a.u.b.d(colorStateList2));
        this.j = aVar;
        this.l = new LayerDrawable(new Drawable[]{(Drawable) b.g.j.h.c(this.i), aVar});
    }

    boolean y() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    boolean z() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }
}
