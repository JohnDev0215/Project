package defpackage;

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

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class wi {
    public static final TimeInterpolator a = bh.c;
    public static final int[] b = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    public static final int[] d = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] e = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] f = {R.attr.state_enabled};
    public static final int[] g = new int[0];
    public int A;
    public ArrayList<Animator.AnimatorListener> C;
    public ArrayList<Animator.AnimatorListener> D;
    public ArrayList<i> E;
    public final FloatingActionButton F;
    public final zj G;
    public ViewTreeObserver.OnPreDrawListener L;
    public mk h;
    public hk i;
    public Drawable j;
    public vi k;
    public Drawable l;
    public boolean m;
    public float o;
    public float p;
    public float q;
    public int r;
    public final gj s;
    public ih t;
    public ih u;
    public Animator v;
    public ih w;
    public ih x;
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
        public boolean a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ j c;

        public a(boolean z, j jVar) {
            this.b = z;
            this.c = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            wi.this.B = 0;
            wi.this.v = null;
            if (this.a) {
                return;
            }
            FloatingActionButton floatingActionButton = wi.this.F;
            boolean z = this.b;
            floatingActionButton.b(z ? 8 : 4, z);
            j jVar = this.c;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            wi.this.F.b(0, this.b);
            wi.this.B = 1;
            wi.this.v = animator;
            this.a = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b extends AnimatorListenerAdapter {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ j b;

        public b(boolean z, j jVar) {
            this.a = z;
            this.b = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            wi.this.B = 0;
            wi.this.v = null;
            j jVar = this.b;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            wi.this.F.b(0, this.a);
            wi.this.B = 2;
            wi.this.v = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c extends hh {
        public c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            wi.this.z = f;
            return super.evaluate(f, matrix, matrix2);
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class d implements TypeEvaluator<Float> {
        public FloatEvaluator a = new FloatEvaluator();

        public d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f, Float f2, Float f3) {
            float fFloatValue = this.a.evaluate(f, (Number) f2, (Number) f3).floatValue();
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
            wi.this.H();
            return true;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class f extends l {
        public f() {
            super(wi.this, null);
        }

        @Override // wi.l
        public float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class g extends l {
        public g() {
            super(wi.this, null);
        }

        @Override // wi.l
        public float a() {
            wi wiVar = wi.this;
            return wiVar.o + wiVar.p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends l {
        public h() {
            super(wi.this, null);
        }

        @Override // wi.l
        public float a() {
            wi wiVar = wi.this;
            return wiVar.o + wiVar.q;
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
            super(wi.this, null);
        }

        @Override // wi.l
        public float a() {
            return wi.this.o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public boolean a;
        public float b;
        public float c;

        public l() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            wi.this.g0((int) this.c);
            this.a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.a) {
                hk hkVar = wi.this.i;
                this.b = hkVar == null ? 0.0f : hkVar.w();
                this.c = a();
                this.a = true;
            }
            wi wiVar = wi.this;
            float f = this.b;
            wiVar.g0((int) (f + ((this.c - f) * valueAnimator.getAnimatedFraction())));
        }

        public /* synthetic */ l(wi wiVar, a aVar) {
            this();
        }
    }

    public wi(FloatingActionButton floatingActionButton, zj zjVar) {
        this.F = floatingActionButton;
        this.G = zjVar;
        gj gjVar = new gj();
        this.s = gjVar;
        gjVar.a(b, i(new h()));
        gjVar.a(c, i(new g()));
        gjVar.a(d, i(new g()));
        gjVar.a(e, i(new g()));
        gjVar.a(f, i(new k()));
        gjVar.a(g, i(new f()));
        this.y = floatingActionButton.getRotation();
    }

    public void A() {
        this.s.c();
    }

    public void B() {
        hk hkVar = this.i;
        if (hkVar != null) {
            ik.f(this.F, hkVar);
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
        z8.d(this.l, "Didn't initialize content background");
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
        hk hkVar = this.i;
        if (hkVar != null) {
            hkVar.setTintList(colorStateList);
        }
        vi viVar = this.k;
        if (viVar != null) {
            viVar.c(colorStateList);
        }
    }

    public void M(PorterDuff.Mode mode) {
        hk hkVar = this.i;
        if (hkVar != null) {
            hkVar.setTintMode(mode);
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

    public final void P(ih ihVar) {
        this.x = ihVar;
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
            y7.o(drawable, xj.d(colorStateList));
        }
    }

    public void W(boolean z) {
        this.n = z;
        f0();
    }

    public final void X(mk mkVar) {
        this.h = mkVar;
        hk hkVar = this.i;
        if (hkVar != null) {
            hkVar.setShapeAppearanceModel(mkVar);
        }
        Object obj = this.j;
        if (obj instanceof pk) {
            ((pk) obj).setShapeAppearanceModel(mkVar);
        }
        vi viVar = this.k;
        if (viVar != null) {
            viVar.f(mkVar);
        }
    }

    public final void Y(ih ihVar) {
        this.w = ihVar;
    }

    public boolean Z() {
        return true;
    }

    public final boolean a0() {
        return t9.P(this.F) && !this.F.isInEditMode();
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
        ih ihVarM = this.w;
        if (ihVarM == null) {
            ihVarM = m();
        }
        AnimatorSet animatorSetH = h(ihVarM, 1.0f, 1.0f, 1.0f);
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
        hk hkVar = this.i;
        if (hkVar != null) {
            hkVar.e0((int) this.y);
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
        hk hkVar = this.i;
        if (hkVar != null) {
            hkVar.W(f2);
        }
    }

    public final AnimatorSet h(ih ihVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        ihVar.h("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        ihVar.h("scale").a(objectAnimatorOfFloat2);
        h0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        ihVar.h("scale").a(objectAnimatorOfFloat3);
        h0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f4, this.K);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new gh(), new c(), new Matrix(this.K));
        ihVar.h("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        ch.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final void h0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new d());
    }

    public final ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public hk j() {
        return new hk((mk) z8.c(this.h));
    }

    public final Drawable k() {
        return this.l;
    }

    public final ih l() {
        if (this.u == null) {
            this.u = ih.d(this.F.getContext(), R$animator.design_fab_hide_motion_spec);
        }
        return (ih) z8.c(this.u);
    }

    public final ih m() {
        if (this.t == null) {
            this.t = ih.d(this.F.getContext(), R$animator.design_fab_show_motion_spec);
        }
        return (ih) z8.c(this.t);
    }

    public float n() {
        return this.o;
    }

    public boolean o() {
        return this.m;
    }

    public final ih p() {
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

    public final mk u() {
        return this.h;
    }

    public final ih v() {
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
        ih ihVarL = this.x;
        if (ihVarL == null) {
            ihVarL = l();
        }
        AnimatorSet animatorSetH = h(ihVarL, 0.0f, 0.0f, 0.0f);
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
        hk hkVarJ = j();
        this.i = hkVarJ;
        hkVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.i.setTintMode(mode);
        }
        this.i.d0(-12303292);
        this.i.N(this.F.getContext());
        wj wjVar = new wj(this.i.D());
        wjVar.setTintList(xj.d(colorStateList2));
        this.j = wjVar;
        this.l = new LayerDrawable(new Drawable[]{(Drawable) z8.c(this.i), wjVar});
    }

    public boolean y() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    public boolean z() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }
}
