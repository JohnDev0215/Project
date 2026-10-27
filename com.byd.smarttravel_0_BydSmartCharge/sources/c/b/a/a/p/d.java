package c.b.a.a.p;

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
import b.g.j.t;
import c.b.a.a.v.m;
import c.b.a.a.v.p;
import com.google.android.material.R$animator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f1754a = c.b.a.a.a.a.f1650c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1755b = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1756c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1757d = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] e = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] f = {R.attr.state_enabled};
    public static final int[] g = new int[0];
    public int A;
    public ArrayList<Animator.AnimatorListener> C;
    public ArrayList<Animator.AnimatorListener> D;
    public ArrayList<i> E;
    public final FloatingActionButton F;
    public final c.b.a.a.u.b G;
    public ViewTreeObserver.OnPreDrawListener L;
    public m h;
    public c.b.a.a.v.h i;
    public Drawable j;
    public c.b.a.a.p.c k;
    public Drawable l;
    public boolean m;
    public float o;
    public float p;
    public float q;
    public int r;
    public final c.b.a.a.q.h s;
    public c.b.a.a.a.h t;
    public c.b.a.a.a.h u;
    public Animator v;
    public c.b.a.a.a.h w;
    public c.b.a.a.a.h x;
    public float y;
    public boolean n = true;
    public float z = 1.0f;
    public int B = 0;
    public final Rect H = new Rect();
    public final RectF I = new RectF();
    public final RectF J = new RectF();
    public final Matrix K = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1758a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f1759b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ j f1760c;

        public a(boolean z, j jVar) {
            this.f1759b = z;
            this.f1760c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1758a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.v = null;
            if (this.f1758a) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.F;
            boolean z = this.f1759b;
            floatingActionButton.b(z ? 8 : 4, z);
            j jVar = this.f1760c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f1759b);
            d.this.B = 1;
            d.this.v = animator;
            this.f1758a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f1762a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ j f1763b;

        public b(boolean z, j jVar) {
            this.f1762a = z;
            this.f1763b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.v = null;
            j jVar = this.f1763b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f1762a);
            d.this.B = 2;
            d.this.v = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c extends c.b.a.a.a.g {
        public c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            d.this.z = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    /* JADX INFO: renamed from: c.b.a.a.p.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class C0054d implements TypeEvaluator<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FloatEvaluator f1766a = new FloatEvaluator();

        public C0054d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f, Float f2, Float f3) {
            float fFloatValue = this.f1766a.evaluate(f, (Number) f2, (Number) f3).floatValue();
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

        @Override // c.b.a.a.p.d.l
        public float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class g extends l {
        public g() {
            super(d.this, null);
        }

        @Override // c.b.a.a.p.d.l
        public float a() {
            d dVar = d.this;
            return dVar.o + dVar.p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends l {
        public h() {
            super(d.this, null);
        }

        @Override // c.b.a.a.p.d.l
        public float a() {
            d dVar = d.this;
            return dVar.o + dVar.q;
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

        @Override // c.b.a.a.p.d.l
        public float a() {
            return d.this.o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1769a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1770b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1771c;

        public l() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.g0((int) this.f1771c);
            this.f1769a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f1769a) {
                c.b.a.a.v.h hVar = d.this.i;
                this.f1770b = hVar == null ? 0.0f : hVar.w();
                this.f1771c = a();
                this.f1769a = true;
            }
            d dVar = d.this;
            float f = this.f1770b;
            dVar.g0((int) (f + ((this.f1771c - f) * valueAnimator.getAnimatedFraction())));
        }

        public /* synthetic */ l(d dVar, a aVar) {
            this();
        }
    }

    public d(FloatingActionButton floatingActionButton, c.b.a.a.u.b bVar) {
        this.F = floatingActionButton;
        this.G = bVar;
        c.b.a.a.q.h hVar = new c.b.a.a.q.h();
        this.s = hVar;
        hVar.a(f1755b, i(new h()));
        hVar.a(f1756c, i(new g()));
        hVar.a(f1757d, i(new g()));
        hVar.a(e, i(new g()));
        hVar.a(f, i(new k()));
        hVar.a(g, i(new f()));
        this.y = floatingActionButton.getRotation();
    }

    public void A() {
        this.s.c();
    }

    public void B() {
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            c.b.a.a.v.i.f(this.F, hVar);
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
        this.s.d(iArr);
    }

    public void F(float f2, float f3, float f4) {
        f0();
        g0(f2);
    }

    public void G(Rect rect) {
        b.g.i.h.d(this.l, "Didn't initialize content background");
        if (!Z()) {
            this.G.c(this.l);
        } else {
            this.G.c(new InsetDrawable(this.l, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    public void H() {
        float rotation = this.F.getRotation();
        if (this.y != rotation) {
            this.y = rotation;
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
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            hVar.setTintList(colorStateList);
        }
        c.b.a.a.p.c cVar = this.k;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    public void M(PorterDuff.Mode mode) {
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            hVar.setTintMode(mode);
        }
    }

    public final void N(float f2) {
        if (this.o != f2) {
            this.o = f2;
            F(f2, this.p, this.q);
        }
    }

    public void O(boolean z) {
        this.m = z;
    }

    public final void P(c.b.a.a.a.h hVar) {
        this.x = hVar;
    }

    public final void Q(float f2) {
        if (this.p != f2) {
            this.p = f2;
            F(this.o, f2, this.q);
        }
    }

    public final void R(float f2) {
        this.z = f2;
        Matrix matrix = this.K;
        g(f2, matrix);
        this.F.setImageMatrix(matrix);
    }

    public final void S(int i2) {
        if (this.A != i2) {
            this.A = i2;
            e0();
        }
    }

    public void T(int i2) {
        this.r = i2;
    }

    public final void U(float f2) {
        if (this.q != f2) {
            this.q = f2;
            F(this.o, this.p, f2);
        }
    }

    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.j;
        if (drawable != null) {
            b.g.c.l.a.o(drawable, c.b.a.a.t.b.d(colorStateList));
        }
    }

    public void W(boolean z) {
        this.n = z;
        f0();
    }

    public final void X(m mVar) {
        this.h = mVar;
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        Object obj = this.j;
        if (obj instanceof p) {
            ((p) obj).setShapeAppearanceModel(mVar);
        }
        c.b.a.a.p.c cVar = this.k;
        if (cVar != null) {
            cVar.f(mVar);
        }
    }

    public final void Y(c.b.a.a.a.h hVar) {
        this.w = hVar;
    }

    public boolean Z() {
        return true;
    }

    public final boolean a0() {
        return t.P(this.F) && !this.F.isInEditMode();
    }

    public final boolean b0() {
        return !this.m || this.F.getSizeDimension() >= this.r;
    }

    public void c0(j jVar, boolean z) {
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
        c.b.a.a.a.h hVarM = this.w;
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

    public void d0() {
        if (Build.VERSION.SDK_INT == 19) {
            if (this.y % 90.0f != 0.0f) {
                if (this.F.getLayerType() != 1) {
                    this.F.setLayerType(1, null);
                }
            } else if (this.F.getLayerType() != 0) {
                this.F.setLayerType(0, null);
            }
        }
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            hVar.e0((int) this.y);
        }
    }

    public void e(Animator.AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    public final void e0() {
        R(this.z);
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

    public final void g(float f2, Matrix matrix) {
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

    public void g0(float f2) {
        c.b.a.a.v.h hVar = this.i;
        if (hVar != null) {
            hVar.W(f2);
        }
    }

    public final AnimatorSet h(c.b.a.a.a.h hVar, float f2, float f3, float f4) {
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
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new c.b.a.a.a.f(), new c(), new Matrix(this.K));
        hVar.h("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        c.b.a.a.a.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final void h0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new C0054d());
    }

    public final ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f1754a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public c.b.a.a.v.h j() {
        return new c.b.a.a.v.h((m) b.g.i.h.c(this.h));
    }

    public final Drawable k() {
        return this.l;
    }

    public final c.b.a.a.a.h l() {
        if (this.u == null) {
            this.u = c.b.a.a.a.h.d(this.F.getContext(), R$animator.design_fab_hide_motion_spec);
        }
        return (c.b.a.a.a.h) b.g.i.h.c(this.u);
    }

    public final c.b.a.a.a.h m() {
        if (this.t == null) {
            this.t = c.b.a.a.a.h.d(this.F.getContext(), R$animator.design_fab_show_motion_spec);
        }
        return (c.b.a.a.a.h) b.g.i.h.c(this.t);
    }

    public float n() {
        return this.o;
    }

    public boolean o() {
        return this.m;
    }

    public final c.b.a.a.a.h p() {
        return this.x;
    }

    public float q() {
        return this.p;
    }

    public final ViewTreeObserver.OnPreDrawListener r() {
        if (this.L == null) {
            this.L = new e();
        }
        return this.L;
    }

    public void s(Rect rect) {
        int sizeDimension = this.m ? (this.r - this.F.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.n ? n() + this.q : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    public float t() {
        return this.q;
    }

    public final m u() {
        return this.h;
    }

    public final c.b.a.a.a.h v() {
        return this.w;
    }

    public void w(j jVar, boolean z) {
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
        c.b.a.a.a.h hVarL = this.x;
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

    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        c.b.a.a.v.h hVarJ = j();
        this.i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.i.setTintMode(mode);
        }
        this.i.d0(-12303292);
        this.i.N(this.F.getContext());
        c.b.a.a.t.a aVar = new c.b.a.a.t.a(this.i.D());
        aVar.setTintList(c.b.a.a.t.b.d(colorStateList2));
        this.j = aVar;
        this.l = new LayerDrawable(new Drawable[]{(Drawable) b.g.i.h.c(this.i), aVar});
    }

    public boolean y() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    public boolean z() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }
}
