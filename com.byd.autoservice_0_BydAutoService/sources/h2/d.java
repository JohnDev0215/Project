package h2;

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
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;
import l0.t;
import n2.m;
import n2.p;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f3090a = s1.a.f4903c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f3091b = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f3092c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3093d = {R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f3094e = {R.attr.state_hovered, R.attr.state_enabled};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f3095f = {R.attr.state_enabled};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f3096g = new int[0];
    public int A;
    public ArrayList<Animator.AnimatorListener> C;
    public ArrayList<Animator.AnimatorListener> D;
    public ArrayList<i> E;
    public final FloatingActionButton F;
    public final m2.b G;
    public ViewTreeObserver.OnPreDrawListener L;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f3097h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public n2.h f3098i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f3099j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h2.c f3100k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f3101l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3102m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f3104o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f3105p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3106q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f3107r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final i2.h f3108s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public s1.h f3109t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public s1.h f3110u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Animator f3111v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public s1.h f3112w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public s1.h f3113x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f3114y;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3103n = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f3115z = 1.0f;
    public int B = 0;
    public final Rect H = new Rect();
    public final RectF I = new RectF();
    public final RectF J = new RectF();
    public final Matrix K = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f3117b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ j f3118c;

        public a(boolean z3, j jVar) {
            this.f3117b = z3;
            this.f3118c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3116a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.f3111v = null;
            if (this.f3116a) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.F;
            boolean z3 = this.f3117b;
            floatingActionButton.b(z3 ? 8 : 4, z3);
            j jVar = this.f3118c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f3117b);
            d.this.B = 1;
            d.this.f3111v = animator;
            this.f3116a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3120a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j f3121b;

        public b(boolean z3, j jVar) {
            this.f3120a = z3;
            this.f3121b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.f3111v = null;
            j jVar = this.f3121b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f3120a);
            d.this.B = 2;
            d.this.f3111v = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c extends s1.g {
        public c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f3, Matrix matrix, Matrix matrix2) {
            d.this.f3115z = f3;
            return super.evaluate(f3, matrix, matrix2);
        }
    }

    /* JADX INFO: renamed from: h2.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class C0016d implements TypeEvaluator<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FloatEvaluator f3124a = new FloatEvaluator();

        public C0016d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f3, Float f4, Float f5) {
            float fFloatValue = this.f3124a.evaluate(f3, (Number) f4, (Number) f5).floatValue();
            if (fFloatValue < 0.1f) {
                fFloatValue = 0.0f;
            }
            return Float.valueOf(fFloatValue);
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class e implements ViewTreeObserver.OnPreDrawListener {
        public e() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            d.this.H();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class f extends l {
        public f() {
            super(d.this, null);
        }

        @Override // h2.d.l
        public float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class g extends l {
        public g() {
            super(d.this, null);
        }

        @Override // h2.d.l
        public float a() {
            d dVar = d.this;
            return dVar.f3104o + dVar.f3105p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends l {
        public h() {
            super(d.this, null);
        }

        @Override // h2.d.l
        public float a() {
            d dVar = d.this;
            return dVar.f3104o + dVar.f3106q;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public interface i {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public interface j {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class k extends l {
        public k() {
            super(d.this, null);
        }

        @Override // h2.d.l
        public float a() {
            return d.this.f3104o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3131a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3132b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3133c;

        public l() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.g0((int) this.f3133c);
            this.f3131a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f3131a) {
                n2.h hVar = d.this.f3098i;
                this.f3132b = hVar == null ? 0.0f : hVar.w();
                this.f3133c = a();
                this.f3131a = true;
            }
            d dVar = d.this;
            float f3 = this.f3132b;
            dVar.g0((int) (f3 + ((this.f3133c - f3) * valueAnimator.getAnimatedFraction())));
        }

        public /* synthetic */ l(d dVar, a aVar) {
            this();
        }
    }

    public d(FloatingActionButton floatingActionButton, m2.b bVar) {
        this.F = floatingActionButton;
        this.G = bVar;
        i2.h hVar = new i2.h();
        this.f3108s = hVar;
        hVar.a(f3091b, i(new h()));
        hVar.a(f3092c, i(new g()));
        hVar.a(f3093d, i(new g()));
        hVar.a(f3094e, i(new g()));
        hVar.a(f3095f, i(new k()));
        hVar.a(f3096g, i(new f()));
        this.f3114y = floatingActionButton.getRotation();
    }

    public void A() {
        this.f3108s.c();
    }

    public void B() {
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            n2.i.f(this.F, hVar);
        }
        if (K()) {
            this.F.getViewTreeObserver().addOnPreDrawListener(r());
        }
    }

    public void C() {
    }

    public void D() {
        ViewTreeObserver viewTreeObserver = this.F.getViewTreeObserver();
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.L;
        if (onPreDrawListener != null) {
            viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            this.L = null;
        }
    }

    public void E(int[] iArr) {
        this.f3108s.d(iArr);
    }

    public void F(float f3, float f4, float f5) {
        f0();
        g0(f3);
    }

    public void G(Rect rect) {
        k0.h.d(this.f3101l, "Didn't initialize content background");
        if (!Z()) {
            this.G.c(this.f3101l);
        } else {
            this.G.c(new InsetDrawable(this.f3101l, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    public void H() {
        float rotation = this.F.getRotation();
        if (this.f3114y != rotation) {
            this.f3114y = rotation;
            d0();
        }
    }

    public void I() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void J() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public boolean K() {
        return true;
    }

    public void L(ColorStateList colorStateList) {
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            hVar.setTintList(colorStateList);
        }
        h2.c cVar = this.f3100k;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    public void M(PorterDuff.Mode mode) {
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            hVar.setTintMode(mode);
        }
    }

    public final void N(float f3) {
        if (this.f3104o != f3) {
            this.f3104o = f3;
            F(f3, this.f3105p, this.f3106q);
        }
    }

    public void O(boolean z3) {
        this.f3102m = z3;
    }

    public final void P(s1.h hVar) {
        this.f3113x = hVar;
    }

    public final void Q(float f3) {
        if (this.f3105p != f3) {
            this.f3105p = f3;
            F(this.f3104o, f3, this.f3106q);
        }
    }

    public final void R(float f3) {
        this.f3115z = f3;
        Matrix matrix = this.K;
        g(f3, matrix);
        this.F.setImageMatrix(matrix);
    }

    public final void S(int i3) {
        if (this.A != i3) {
            this.A = i3;
            e0();
        }
    }

    public void T(int i3) {
        this.f3107r = i3;
    }

    public final void U(float f3) {
        if (this.f3106q != f3) {
            this.f3106q = f3;
            F(this.f3104o, this.f3105p, f3);
        }
    }

    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.f3099j;
        if (drawable != null) {
            e0.a.o(drawable, l2.b.d(colorStateList));
        }
    }

    public void W(boolean z3) {
        this.f3103n = z3;
        f0();
    }

    public final void X(m mVar) {
        this.f3097h = mVar;
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        Object obj = this.f3099j;
        if (obj instanceof p) {
            ((p) obj).setShapeAppearanceModel(mVar);
        }
        h2.c cVar = this.f3100k;
        if (cVar != null) {
            cVar.f(mVar);
        }
    }

    public final void Y(s1.h hVar) {
        this.f3112w = hVar;
    }

    public boolean Z() {
        return true;
    }

    public final boolean a0() {
        return t.P(this.F) && !this.F.isInEditMode();
    }

    public final boolean b0() {
        return !this.f3102m || this.F.getSizeDimension() >= this.f3107r;
    }

    public void c0(j jVar, boolean z3) {
        if (z()) {
            return;
        }
        Animator animator = this.f3111v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(0, z3);
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
        s1.h hVarM = this.f3112w;
        if (hVarM == null) {
            hVarM = m();
        }
        AnimatorSet animatorSetH = h(hVarM, 1.0f, 1.0f, 1.0f);
        animatorSetH.addListener(new b(z3, jVar));
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

    public void d0() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.f3114y % 90.0f != 0.0f) {
                if (this.F.getLayerType() != 1) {
                    this.F.setLayerType(1, null);
                }
            } else if (this.F.getLayerType() != 0) {
                this.F.setLayerType(0, null);
            }
        }
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            hVar.e0((int) this.f3114y);
        }
    }

    public void e(Animator.AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    public final void e0() {
        R(this.f3115z);
    }

    public void f(i iVar) {
        if (this.E == null) {
            this.E = new ArrayList<>();
        }
        this.E.add(iVar);
    }

    public final void f0() {
        Rect rect = this.H;
        s(rect);
        G(rect);
        this.G.a(rect.left, rect.top, rect.right, rect.bottom);
    }

    public final void g(float f3, Matrix matrix) {
        matrix.reset();
        if (this.F.getDrawable() == null || this.A == 0) {
            return;
        }
        RectF rectF = this.I;
        RectF rectF2 = this.J;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i3 = this.A;
        rectF2.set(0.0f, 0.0f, i3, i3);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i4 = this.A;
        matrix.postScale(f3, f3, i4 / 2.0f, i4 / 2.0f);
    }

    public void g0(float f3) {
        n2.h hVar = this.f3098i;
        if (hVar != null) {
            hVar.W(f3);
        }
    }

    public final AnimatorSet h(s1.h hVar, float f3, float f4, float f5) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.ALPHA, f3);
        hVar.h("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_X, f4);
        hVar.h("scale").a(objectAnimatorOfFloat2);
        h0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_Y, f4);
        hVar.h("scale").a(objectAnimatorOfFloat3);
        h0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f5, this.K);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new s1.f(), new c(), new Matrix(this.K));
        hVar.h("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        s1.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final void h0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new C0016d());
    }

    public final ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f3090a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public n2.h j() {
        return new n2.h((m) k0.h.c(this.f3097h));
    }

    public final Drawable k() {
        return this.f3101l;
    }

    public final s1.h l() {
        if (this.f3110u == null) {
            this.f3110u = s1.h.d(this.F.getContext(), R$animator.design_fab_hide_motion_spec);
        }
        return (s1.h) k0.h.c(this.f3110u);
    }

    public final s1.h m() {
        if (this.f3109t == null) {
            this.f3109t = s1.h.d(this.F.getContext(), R$animator.design_fab_show_motion_spec);
        }
        return (s1.h) k0.h.c(this.f3109t);
    }

    public float n() {
        return this.f3104o;
    }

    public boolean o() {
        return this.f3102m;
    }

    public final s1.h p() {
        return this.f3113x;
    }

    public float q() {
        return this.f3105p;
    }

    public final ViewTreeObserver.OnPreDrawListener r() {
        if (this.L == null) {
            this.L = new e();
        }
        return this.L;
    }

    public void s(Rect rect) {
        int sizeDimension = this.f3102m ? (this.f3107r - this.F.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.f3103n ? n() + this.f3106q : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    public float t() {
        return this.f3106q;
    }

    public final m u() {
        return this.f3097h;
    }

    public final s1.h v() {
        return this.f3112w;
    }

    public void w(j jVar, boolean z3) {
        if (y()) {
            return;
        }
        Animator animator = this.f3111v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(z3 ? 8 : 4, z3);
            if (jVar != null) {
                jVar.b();
                return;
            }
            return;
        }
        s1.h hVarL = this.f3113x;
        if (hVarL == null) {
            hVarL = l();
        }
        AnimatorSet animatorSetH = h(hVarL, 0.0f, 0.0f, 0.0f);
        animatorSetH.addListener(new a(z3, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.D;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i3) {
        n2.h hVarJ = j();
        this.f3098i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f3098i.setTintMode(mode);
        }
        this.f3098i.d0(-12303292);
        this.f3098i.N(this.F.getContext());
        l2.a aVar = new l2.a(this.f3098i.D());
        aVar.setTintList(l2.b.d(colorStateList2));
        this.f3099j = aVar;
        this.f3101l = new LayerDrawable(new Drawable[]{(Drawable) k0.h.c(this.f3098i), aVar});
    }

    public boolean y() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    public boolean z() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }
}
