package com.google.android.material.floatingactionbutton;

import a.g.k.t;
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
import android.os.Build;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import b.a.a.a.b0.n;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final TimeInterpolator f3103a = b.a.a.a.m.a.f1734c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f3104b = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f3105c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final int[] f3106d = {R.attr.state_focused, R.attr.state_enabled};
    static final int[] e = {R.attr.state_hovered, R.attr.state_enabled};
    static final int[] f = {R.attr.state_enabled};
    static final int[] g = new int[0];
    private int A;
    private ArrayList<Animator.AnimatorListener> C;
    private ArrayList<Animator.AnimatorListener> D;
    private ArrayList<i> E;
    final FloatingActionButton F;
    final b.a.a.a.a0.b G;
    private ViewTreeObserver.OnPreDrawListener L;
    b.a.a.a.b0.k h;
    b.a.a.a.b0.g i;
    Drawable j;
    com.google.android.material.floatingactionbutton.a k;
    Drawable l;
    boolean m;
    float o;
    float p;
    float q;
    int r;
    private final com.google.android.material.internal.f s;
    private b.a.a.a.m.h t;
    private b.a.a.a.m.h u;
    private Animator v;
    private b.a.a.a.m.h w;
    private b.a.a.a.m.h x;
    private float y;
    boolean n = true;
    private float z = 1.0f;
    private int B = 0;
    private final Rect H = new Rect();
    private final RectF I = new RectF();
    private final RectF J = new RectF();
    private final Matrix K = new Matrix();

    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3107a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f3108b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ j f3109c;

        a(boolean z, j jVar) {
            this.f3108b = z;
            this.f3109c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3107a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.B = 0;
            b.this.v = null;
            if (this.f3107a) {
                return;
            }
            FloatingActionButton floatingActionButton = b.this.F;
            boolean z = this.f3108b;
            floatingActionButton.b(z ? 8 : 4, z);
            j jVar = this.f3109c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.F.b(0, this.f3108b);
            b.this.B = 1;
            b.this.v = animator;
            this.f3107a = false;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.floatingactionbutton.b$b, reason: collision with other inner class name */
    class C0112b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f3111a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ j f3112b;

        C0112b(boolean z, j jVar) {
            this.f3111a = z;
            this.f3112b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.B = 0;
            b.this.v = null;
            j jVar = this.f3112b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            b.this.F.b(0, this.f3111a);
            b.this.B = 2;
            b.this.v = animator;
        }
    }

    class c extends b.a.a.a.m.g {
        c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            b.this.z = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    class d implements TypeEvaluator<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        FloatEvaluator f3115a = new FloatEvaluator();

        d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f, Float f2, Float f3) {
            float fFloatValue = this.f3115a.evaluate(f, (Number) f2, (Number) f3).floatValue();
            if (fFloatValue < 0.1f) {
                fFloatValue = 0.0f;
            }
            return Float.valueOf(fFloatValue);
        }
    }

    class e implements ViewTreeObserver.OnPreDrawListener {
        e() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            b.this.F();
            return true;
        }
    }

    private class f extends l {
        f() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.l
        protected float a() {
            return 0.0f;
        }
    }

    private class g extends l {
        g() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.l
        protected float a() {
            b bVar = b.this;
            return bVar.o + bVar.p;
        }
    }

    private class h extends l {
        h() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.l
        protected float a() {
            b bVar = b.this;
            return bVar.o + bVar.q;
        }
    }

    interface i {
        void a();

        void b();
    }

    interface j {
        void a();

        void b();
    }

    private class k extends l {
        k() {
            super(b.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.b.l
        protected float a() {
            return b.this.o;
        }
    }

    private abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3118a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f3119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f3120c;

        private l() {
        }

        /* synthetic */ l(b bVar, a aVar) {
            this();
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b.this.c0((int) this.f3120c);
            this.f3118a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f3118a) {
                b.a.a.a.b0.g gVar = b.this.i;
                this.f3119b = gVar == null ? 0.0f : gVar.w();
                this.f3120c = a();
                this.f3118a = true;
            }
            b bVar = b.this;
            float f = this.f3119b;
            bVar.c0((int) (f + ((this.f3120c - f) * valueAnimator.getAnimatedFraction())));
        }
    }

    b(FloatingActionButton floatingActionButton, b.a.a.a.a0.b bVar) {
        this.F = floatingActionButton;
        this.G = bVar;
        com.google.android.material.internal.f fVar = new com.google.android.material.internal.f();
        this.s = fVar;
        fVar.a(f3104b, i(new h()));
        fVar.a(f3105c, i(new g()));
        fVar.a(f3106d, i(new g()));
        fVar.a(e, i(new g()));
        fVar.a(f, i(new k()));
        fVar.a(g, i(new f()));
        this.y = floatingActionButton.getRotation();
    }

    private boolean W() {
        return t.N(this.F) && !this.F.isInEditMode();
    }

    private void d0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new d());
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

    private AnimatorSet h(b.a.a.a.m.h hVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        hVar.e("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        hVar.e("scale").a(objectAnimatorOfFloat2);
        d0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        hVar.e("scale").a(objectAnimatorOfFloat3);
        d0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f4, this.K);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new b.a.a.a.m.f(), new c(), new Matrix(this.K));
        hVar.e("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        b.a.a.a.m.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    private ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f3103a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private b.a.a.a.m.h k() {
        if (this.u == null) {
            this.u = b.a.a.a.m.h.c(this.F.getContext(), b.a.a.a.a.f1634a);
        }
        return (b.a.a.a.m.h) a.g.j.h.c(this.u);
    }

    private b.a.a.a.m.h l() {
        if (this.t == null) {
            this.t = b.a.a.a.m.h.c(this.F.getContext(), b.a.a.a.a.f1635b);
        }
        return (b.a.a.a.m.h) a.g.j.h.c(this.t);
    }

    private ViewTreeObserver.OnPreDrawListener q() {
        if (this.L == null) {
            this.L = new e();
        }
        return this.L;
    }

    void A() {
    }

    void B() {
        ViewTreeObserver viewTreeObserver = this.F.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.L;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.L = null;
        }
    }

    void C(int[] iArr) {
        this.s.d(iArr);
    }

    void D(float f2, float f3, float f4) {
        b0();
        c0(f2);
    }

    void E(Rect rect) {
        b.a.a.a.a0.b bVar;
        Drawable insetDrawable;
        a.g.j.h.d(this.l, "Didn't initialize content background");
        if (V()) {
            insetDrawable = new InsetDrawable(this.l, rect.left, rect.top, rect.right, rect.bottom);
            bVar = this.G;
        } else {
            bVar = this.G;
            insetDrawable = this.l;
        }
        bVar.c(insetDrawable);
    }

    void F() {
        float rotation = this.F.getRotation();
        if (this.y != rotation) {
            this.y = rotation;
            Z();
        }
    }

    void G() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    void H() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    boolean I() {
        return true;
    }

    void J(ColorStateList colorStateList) {
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            gVar.setTintList(colorStateList);
        }
        if (this.k != null) {
            throw null;
        }
    }

    void K(PorterDuff.Mode mode) {
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            gVar.setTintMode(mode);
        }
    }

    final void L(float f2) {
        if (this.o != f2) {
            this.o = f2;
            D(f2, this.p, this.q);
        }
    }

    void M(boolean z) {
        this.m = z;
    }

    final void N(b.a.a.a.m.h hVar) {
        this.x = hVar;
    }

    final void O(float f2) {
        if (this.p != f2) {
            this.p = f2;
            D(this.o, f2, this.q);
        }
    }

    final void P(float f2) {
        this.z = f2;
        Matrix matrix = this.K;
        g(f2, matrix);
        this.F.setImageMatrix(matrix);
    }

    final void Q(float f2) {
        if (this.q != f2) {
            this.q = f2;
            D(this.o, this.p, f2);
        }
    }

    void R(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, b.a.a.a.z.b.d(colorStateList));
        }
    }

    void S(boolean z) {
        this.n = z;
        b0();
    }

    final void T(b.a.a.a.b0.k kVar) {
        this.h = kVar;
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            gVar.setShapeAppearanceModel(kVar);
        }
        Object obj = this.j;
        if (obj instanceof n) {
            ((n) obj).setShapeAppearanceModel(kVar);
        }
        if (this.k != null) {
            throw null;
        }
    }

    final void U(b.a.a.a.m.h hVar) {
        this.w = hVar;
    }

    boolean V() {
        return true;
    }

    final boolean X() {
        return !this.m || this.F.getSizeDimension() >= this.r;
    }

    void Y(j jVar, boolean z) {
        if (x()) {
            return;
        }
        Animator animator = this.v;
        if (animator != null) {
            animator.cancel();
        }
        if (!W()) {
            this.F.b(0, z);
            this.F.setAlpha(1.0f);
            this.F.setScaleY(1.0f);
            this.F.setScaleX(1.0f);
            P(1.0f);
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
            P(0.0f);
        }
        b.a.a.a.m.h hVarL = this.w;
        if (hVarL == null) {
            hVarL = l();
        }
        AnimatorSet animatorSetH = h(hVarL, 1.0f, 1.0f, 1.0f);
        animatorSetH.addListener(new C0112b(z, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.C;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    void Z() {
        FloatingActionButton floatingActionButton;
        int i2;
        if (Build.VERSION.SDK_INT == 19) {
            if (this.y % 90.0f != 0.0f) {
                i2 = 1;
                if (this.F.getLayerType() != 1) {
                    floatingActionButton = this.F;
                    floatingActionButton.setLayerType(i2, null);
                }
            } else if (this.F.getLayerType() != 0) {
                floatingActionButton = this.F;
                i2 = 0;
                floatingActionButton.setLayerType(i2, null);
            }
        }
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            gVar.a0((int) this.y);
        }
    }

    final void a0() {
        P(this.z);
    }

    final void b0() {
        Rect rect = this.H;
        r(rect);
        E(rect);
        this.G.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    void c0(float f2) {
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            gVar.V(f2);
        }
    }

    public void d(Animator.AnimatorListener animatorListener) {
        if (this.D == null) {
            this.D = new ArrayList<>();
        }
        this.D.add(animatorListener);
    }

    void e(Animator.AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    void f(i iVar) {
        if (this.E == null) {
            this.E = new ArrayList<>();
        }
        this.E.add(iVar);
    }

    final Drawable j() {
        return this.l;
    }

    float m() {
        return this.o;
    }

    boolean n() {
        return this.m;
    }

    final b.a.a.a.m.h o() {
        return this.x;
    }

    float p() {
        return this.p;
    }

    void r(Rect rect) {
        int sizeDimension = this.m ? (this.r - this.F.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.n ? m() + this.q : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    float s() {
        return this.q;
    }

    final b.a.a.a.b0.k t() {
        return this.h;
    }

    final b.a.a.a.m.h u() {
        return this.w;
    }

    void v(j jVar, boolean z) {
        if (w()) {
            return;
        }
        Animator animator = this.v;
        if (animator != null) {
            animator.cancel();
        }
        if (!W()) {
            this.F.b(z ? 8 : 4, z);
            if (jVar != null) {
                jVar.b();
                return;
            }
            return;
        }
        b.a.a.a.m.h hVarK = this.x;
        if (hVarK == null) {
            hVarK = k();
        }
        AnimatorSet animatorSetH = h(hVarK, 0.0f, 0.0f, 0.0f);
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

    boolean w() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    boolean x() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }

    void y() {
        this.s.c();
    }

    void z() {
        b.a.a.a.b0.g gVar = this.i;
        if (gVar != null) {
            b.a.a.a.b0.h.f(this.F, gVar);
        }
        if (I()) {
            this.F.getViewTreeObserver().addOnPreDrawListener(q());
        }
    }
}
