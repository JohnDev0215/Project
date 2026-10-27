package com.google.android.material.floatingactionbutton;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import e3.n;
import i0.u;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
class a {
    static final TimeInterpolator E = p2.a.f8822c;
    static final int[] F = {R.attr.state_pressed, R.attr.state_enabled};
    static final int[] G = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    static final int[] H = {R.attr.state_focused, R.attr.state_enabled};
    static final int[] I = {R.attr.state_hovered, R.attr.state_enabled};
    static final int[] J = {R.attr.state_enabled};
    static final int[] K = new int[0];
    private ViewTreeObserver.OnPreDrawListener D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    e3.k f4535a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    e3.g f4536b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Drawable f4537c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Drawable f4538d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4539e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    float f4541g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f4542h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f4543i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    int f4544j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final com.google.android.material.internal.f f4545k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private p2.h f4546l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private p2.h f4547m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private Animator f4548n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private p2.h f4549o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private p2.h f4550p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f4551q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4553s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private ArrayList<Animator.AnimatorListener> f4555u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private ArrayList<Animator.AnimatorListener> f4556v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private ArrayList<h> f4557w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    final FloatingActionButton f4558x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    final d3.b f4559y;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    boolean f4540f = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private float f4552r = 1.0f;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f4554t = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final Rect f4560z = new Rect();
    private final RectF A = new RectF();
    private final RectF B = new RectF();
    private final Matrix C = new Matrix();

    /* JADX INFO: renamed from: com.google.android.material.floatingactionbutton.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class C0049a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f4561a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f4562b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ i f4563c;

        C0049a(boolean z6, i iVar) {
            this.f4562b = z6;
            this.f4563c = iVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f4561a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f4554t = 0;
            a.this.f4548n = null;
            if (this.f4561a) {
                return;
            }
            FloatingActionButton floatingActionButton = a.this.f4558x;
            boolean z6 = this.f4562b;
            floatingActionButton.b(z6 ? 8 : 4, z6);
            i iVar = this.f4563c;
            if (iVar != null) {
                iVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f4558x.b(0, this.f4562b);
            a.this.f4554t = 1;
            a.this.f4548n = animator;
            this.f4561a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f4565a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ i f4566b;

        b(boolean z6, i iVar) {
            this.f4565a = z6;
            this.f4566b = iVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.f4554t = 0;
            a.this.f4548n = null;
            i iVar = this.f4566b;
            if (iVar != null) {
                iVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            a.this.f4558x.b(0, this.f4565a);
            a.this.f4554t = 2;
            a.this.f4548n = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class c extends p2.g {
        c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f7, Matrix matrix, Matrix matrix2) {
            a.this.f4552r = f7;
            return super.evaluate(f7, matrix, matrix2);
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    class d implements ViewTreeObserver.OnPreDrawListener {
        d() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a.this.F();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class e extends k {
        e() {
            super(a.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.a.k
        protected float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class f extends k {
        f() {
            super(a.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.a.k
        protected float a() {
            a aVar = a.this;
            return aVar.f4541g + aVar.f4542h;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class g extends k {
        g() {
            super(a.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.a.k
        protected float a() {
            a aVar = a.this;
            return aVar.f4541g + aVar.f4543i;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    interface h {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    interface i {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private class j extends k {
        j() {
            super(a.this, null);
        }

        @Override // com.google.android.material.floatingactionbutton.a.k
        protected float a() {
            return a.this.f4541g;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    private abstract class k extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f4574a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f4575b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f4576c;

        private k() {
        }

        protected abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            a.this.c0((int) this.f4576c);
            this.f4574a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f4574a) {
                e3.g gVar = a.this.f4536b;
                this.f4575b = gVar == null ? 0.0f : gVar.w();
                this.f4576c = a();
                this.f4574a = true;
            }
            a aVar = a.this;
            float f7 = this.f4575b;
            aVar.c0((int) (f7 + ((this.f4576c - f7) * valueAnimator.getAnimatedFraction())));
        }

        /* synthetic */ k(a aVar, C0049a c0049a) {
            this();
        }
    }

    a(FloatingActionButton floatingActionButton, d3.b bVar) {
        this.f4558x = floatingActionButton;
        this.f4559y = bVar;
        com.google.android.material.internal.f fVar = new com.google.android.material.internal.f();
        this.f4545k = fVar;
        fVar.a(F, i(new g()));
        fVar.a(G, i(new f()));
        fVar.a(H, i(new f()));
        fVar.a(I, i(new f()));
        fVar.a(J, i(new j()));
        fVar.a(K, i(new e()));
        this.f4551q = floatingActionButton.getRotation();
    }

    private boolean W() {
        return u.P(this.f4558x) && !this.f4558x.isInEditMode();
    }

    private void d0(ObjectAnimator objectAnimator) {
    }

    private void g(float f7, Matrix matrix) {
        matrix.reset();
        if (this.f4558x.getDrawable() == null || this.f4553s == 0) {
            return;
        }
        RectF rectF = this.A;
        RectF rectF2 = this.B;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i7 = this.f4553s;
        rectF2.set(0.0f, 0.0f, i7, i7);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i8 = this.f4553s;
        matrix.postScale(f7, f7, i8 / 2.0f, i8 / 2.0f);
    }

    private AnimatorSet h(p2.h hVar, float f7, float f8, float f9) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.f4558x, (Property<FloatingActionButton, Float>) View.ALPHA, f7);
        hVar.e("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.f4558x, (Property<FloatingActionButton, Float>) View.SCALE_X, f8);
        hVar.e("scale").a(objectAnimatorOfFloat2);
        d0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.f4558x, (Property<FloatingActionButton, Float>) View.SCALE_Y, f8);
        hVar.e("scale").a(objectAnimatorOfFloat3);
        d0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f9, this.C);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.f4558x, new p2.f(), new c(), new Matrix(this.C));
        hVar.e("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        p2.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    private ValueAnimator i(k kVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(E);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(kVar);
        valueAnimator.addUpdateListener(kVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    private p2.h k() {
        if (this.f4547m == null) {
            this.f4547m = p2.h.c(this.f4558x.getContext(), o2.a.design_fab_hide_motion_spec);
        }
        return (p2.h) h0.h.f(this.f4547m);
    }

    private p2.h l() {
        if (this.f4546l == null) {
            this.f4546l = p2.h.c(this.f4558x.getContext(), o2.a.design_fab_show_motion_spec);
        }
        return (p2.h) h0.h.f(this.f4546l);
    }

    private ViewTreeObserver.OnPreDrawListener q() {
        if (this.D == null) {
            this.D = new d();
        }
        return this.D;
    }

    void A() {
        throw null;
    }

    void B() {
        ViewTreeObserver viewTreeObserver = this.f4558x.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.D;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.D = null;
        }
    }

    void C(int[] iArr) {
        throw null;
    }

    void D(float f7, float f8, float f9) {
        throw null;
    }

    void E(Rect rect) {
        h0.h.g(this.f4538d, "Didn't initialize content background");
        if (!V()) {
            this.f4559y.c(this.f4538d);
        } else {
            this.f4559y.c(new InsetDrawable(this.f4538d, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    void F() {
        float rotation = this.f4558x.getRotation();
        if (this.f4551q != rotation) {
            this.f4551q = rotation;
            Z();
        }
    }

    void G() {
        ArrayList<h> arrayList = this.f4557w;
        if (arrayList != null) {
            Iterator<h> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    void H() {
        ArrayList<h> arrayList = this.f4557w;
        if (arrayList != null) {
            Iterator<h> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    boolean I() {
        throw null;
    }

    void J(ColorStateList colorStateList) {
        e3.g gVar = this.f4536b;
        if (gVar != null) {
            gVar.setTintList(colorStateList);
        }
    }

    void K(PorterDuff.Mode mode) {
        e3.g gVar = this.f4536b;
        if (gVar != null) {
            gVar.setTintMode(mode);
        }
    }

    final void L(float f7) {
        if (this.f4541g != f7) {
            this.f4541g = f7;
            D(f7, this.f4542h, this.f4543i);
        }
    }

    void M(boolean z6) {
        this.f4539e = z6;
    }

    final void N(p2.h hVar) {
        this.f4550p = hVar;
    }

    final void O(float f7) {
        if (this.f4542h != f7) {
            this.f4542h = f7;
            D(this.f4541g, f7, this.f4543i);
        }
    }

    final void P(float f7) {
        this.f4552r = f7;
        Matrix matrix = this.C;
        g(f7, matrix);
        this.f4558x.setImageMatrix(matrix);
    }

    final void Q(float f7) {
        if (this.f4543i != f7) {
            this.f4543i = f7;
            D(this.f4541g, this.f4542h, f7);
        }
    }

    void R(ColorStateList colorStateList) {
        Drawable drawable = this.f4537c;
        if (drawable != null) {
            a0.a.i(drawable, c3.b.d(colorStateList));
        }
    }

    void S(boolean z6) {
        this.f4540f = z6;
        b0();
    }

    final void T(e3.k kVar) {
        this.f4535a = kVar;
        e3.g gVar = this.f4536b;
        if (gVar != null) {
            gVar.setShapeAppearanceModel(kVar);
        }
        Object obj = this.f4537c;
        if (obj instanceof n) {
            ((n) obj).setShapeAppearanceModel(kVar);
        }
    }

    final void U(p2.h hVar) {
        this.f4549o = hVar;
    }

    boolean V() {
        throw null;
    }

    final boolean X() {
        return !this.f4539e || this.f4558x.getSizeDimension() >= this.f4544j;
    }

    void Y(i iVar, boolean z6) {
        if (x()) {
            return;
        }
        Animator animator = this.f4548n;
        if (animator != null) {
            animator.cancel();
        }
        if (!W()) {
            this.f4558x.b(0, z6);
            this.f4558x.setAlpha(1.0f);
            this.f4558x.setScaleY(1.0f);
            this.f4558x.setScaleX(1.0f);
            P(1.0f);
            if (iVar != null) {
                iVar.a();
                return;
            }
            return;
        }
        if (this.f4558x.getVisibility() != 0) {
            this.f4558x.setAlpha(0.0f);
            this.f4558x.setScaleY(0.0f);
            this.f4558x.setScaleX(0.0f);
            P(0.0f);
        }
        p2.h hVarL = this.f4549o;
        if (hVarL == null) {
            hVarL = l();
        }
        AnimatorSet animatorSetH = h(hVarL, 1.0f, 1.0f, 1.0f);
        animatorSetH.addListener(new b(z6, iVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.f4555u;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    void Z() {
        throw null;
    }

    final void a0() {
        P(this.f4552r);
    }

    final void b0() {
        Rect rect = this.f4560z;
        r(rect);
        E(rect);
        this.f4559y.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    void c0(float f7) {
        e3.g gVar = this.f4536b;
        if (gVar != null) {
            gVar.W(f7);
        }
    }

    public void d(Animator.AnimatorListener animatorListener) {
        if (this.f4556v == null) {
            this.f4556v = new ArrayList<>();
        }
        this.f4556v.add(animatorListener);
    }

    void e(Animator.AnimatorListener animatorListener) {
        if (this.f4555u == null) {
            this.f4555u = new ArrayList<>();
        }
        this.f4555u.add(animatorListener);
    }

    void f(h hVar) {
        if (this.f4557w == null) {
            this.f4557w = new ArrayList<>();
        }
        this.f4557w.add(hVar);
    }

    final Drawable j() {
        return this.f4538d;
    }

    float m() {
        throw null;
    }

    boolean n() {
        return this.f4539e;
    }

    final p2.h o() {
        return this.f4550p;
    }

    float p() {
        return this.f4542h;
    }

    void r(Rect rect) {
        int sizeDimension = this.f4539e ? (this.f4544j - this.f4558x.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.f4540f ? m() + this.f4543i : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    float s() {
        return this.f4543i;
    }

    final e3.k t() {
        return this.f4535a;
    }

    final p2.h u() {
        return this.f4549o;
    }

    void v(i iVar, boolean z6) {
        if (w()) {
            return;
        }
        Animator animator = this.f4548n;
        if (animator != null) {
            animator.cancel();
        }
        if (!W()) {
            this.f4558x.b(z6 ? 8 : 4, z6);
            if (iVar != null) {
                iVar.b();
                return;
            }
            return;
        }
        p2.h hVarK = this.f4550p;
        if (hVarK == null) {
            hVarK = k();
        }
        AnimatorSet animatorSetH = h(hVarK, 0.0f, 0.0f, 0.0f);
        animatorSetH.addListener(new C0049a(z6, iVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.f4556v;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    boolean w() {
        return this.f4558x.getVisibility() == 0 ? this.f4554t == 1 : this.f4554t != 2;
    }

    boolean x() {
        return this.f4558x.getVisibility() != 0 ? this.f4554t == 2 : this.f4554t != 1;
    }

    void y() {
        throw null;
    }

    void z() {
        e3.g gVar = this.f4536b;
        if (gVar != null) {
            e3.h.f(this.f4558x, gVar);
        }
        if (I()) {
            this.f4558x.getViewTreeObserver().addOnPreDrawListener(q());
        }
    }
}
