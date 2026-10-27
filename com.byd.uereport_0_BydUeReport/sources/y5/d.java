package y5;

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
import e6.m;
import e6.p;
import java.util.ArrayList;
import java.util.Iterator;
import o0.t;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f9346a = j5.a.f5580c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f9347b = {R.attr.state_pressed, R.attr.state_enabled};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f9348c = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f9349d = {R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f9350e = {R.attr.state_hovered, R.attr.state_enabled};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f9351f = {R.attr.state_enabled};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f9352g = new int[0];
    public int A;
    public ArrayList<Animator.AnimatorListener> C;
    public ArrayList<Animator.AnimatorListener> D;
    public ArrayList<i> E;
    public final FloatingActionButton F;
    public final d6.b G;
    public ViewTreeObserver.OnPreDrawListener L;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m f9353h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e6.h f9354i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f9355j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y5.c f9356k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f9357l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9358m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f9360o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f9361p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f9362q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9363r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final z5.h f9364s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public j5.h f9365t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public j5.h f9366u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Animator f9367v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public j5.h f9368w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public j5.h f9369x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f9370y;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9359n = true;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f9371z = 1.0f;
    public int B = 0;
    public final Rect H = new Rect();
    public final RectF I = new RectF();
    public final RectF J = new RectF();
    public final Matrix K = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f9372d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f9373e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ j f9374f;

        public a(boolean z7, j jVar) {
            this.f9373e = z7;
            this.f9374f = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f9372d = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.f9367v = null;
            if (this.f9372d) {
                return;
            }
            FloatingActionButton floatingActionButton = d.this.F;
            boolean z7 = this.f9373e;
            floatingActionButton.b(z7 ? 8 : 4, z7);
            j jVar = this.f9374f;
            if (jVar != null) {
                jVar.b();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f9373e);
            d.this.B = 1;
            d.this.f9367v = animator;
            this.f9372d = false;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ boolean f9376d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ j f9377e;

        public b(boolean z7, j jVar) {
            this.f9376d = z7;
            this.f9377e = jVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.B = 0;
            d.this.f9367v = null;
            j jVar = this.f9377e;
            if (jVar != null) {
                jVar.a();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            d.this.F.b(0, this.f9376d);
            d.this.B = 2;
            d.this.f9367v = animator;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c extends j5.g {
        public c() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f8, Matrix matrix, Matrix matrix2) {
            d.this.f9371z = f8;
            return super.evaluate(f8, matrix, matrix2);
        }
    }

    /* JADX INFO: renamed from: y5.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class C0149d implements TypeEvaluator<Float> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public FloatEvaluator f9380a = new FloatEvaluator();

        public C0149d() {
        }

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float evaluate(float f8, Float f9, Float f10) {
            float fFloatValue = this.f9380a.evaluate(f8, (Number) f9, (Number) f10).floatValue();
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

        @Override // y5.d.l
        public float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class g extends l {
        public g() {
            super(d.this, null);
        }

        @Override // y5.d.l
        public float a() {
            d dVar = d.this;
            return dVar.f9360o + dVar.f9361p;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends l {
        public h() {
            super(d.this, null);
        }

        @Override // y5.d.l
        public float a() {
            d dVar = d.this;
            return dVar.f9360o + dVar.f9362q;
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

        @Override // y5.d.l
        public float a() {
            return d.this.f9360o;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class l extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f9387d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f9388e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public float f9389f;

        public l() {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            d.this.g0((int) this.f9389f);
            this.f9387d = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f9387d) {
                e6.h hVar = d.this.f9354i;
                this.f9388e = hVar == null ? 0.0f : hVar.w();
                this.f9389f = a();
                this.f9387d = true;
            }
            d dVar = d.this;
            float f8 = this.f9388e;
            dVar.g0((int) (f8 + ((this.f9389f - f8) * valueAnimator.getAnimatedFraction())));
        }

        public /* synthetic */ l(d dVar, a aVar) {
            this();
        }
    }

    public d(FloatingActionButton floatingActionButton, d6.b bVar) {
        this.F = floatingActionButton;
        this.G = bVar;
        z5.h hVar = new z5.h();
        this.f9364s = hVar;
        hVar.a(f9347b, i(new h()));
        hVar.a(f9348c, i(new g()));
        hVar.a(f9349d, i(new g()));
        hVar.a(f9350e, i(new g()));
        hVar.a(f9351f, i(new k()));
        hVar.a(f9352g, i(new f()));
        this.f9370y = floatingActionButton.getRotation();
    }

    public void A() {
        this.f9364s.c();
    }

    public void B() {
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            e6.i.f(this.F, hVar);
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
        this.f9364s.d(iArr);
    }

    public void F(float f8, float f9, float f10) {
        f0();
        g0(f8);
    }

    public void G(Rect rect) {
        n0.h.f(this.f9357l, "Didn't initialize content background");
        if (!Z()) {
            this.G.b(this.f9357l);
        } else {
            this.G.b(new InsetDrawable(this.f9357l, rect.left, rect.top, rect.right, rect.bottom));
        }
    }

    public void H() {
        float rotation = this.F.getRotation();
        if (this.f9370y != rotation) {
            this.f9370y = rotation;
            d0();
        }
    }

    public void I() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public void J() {
        ArrayList<i> arrayList = this.E;
        if (arrayList != null) {
            Iterator<i> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public boolean K() {
        return true;
    }

    public void L(ColorStateList colorStateList) {
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            hVar.setTintList(colorStateList);
        }
        y5.c cVar = this.f9356k;
        if (cVar != null) {
            cVar.c(colorStateList);
        }
    }

    public void M(PorterDuff.Mode mode) {
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            hVar.setTintMode(mode);
        }
    }

    public final void N(float f8) {
        if (this.f9360o != f8) {
            this.f9360o = f8;
            F(f8, this.f9361p, this.f9362q);
        }
    }

    public void O(boolean z7) {
        this.f9358m = z7;
    }

    public final void P(j5.h hVar) {
        this.f9369x = hVar;
    }

    public final void Q(float f8) {
        if (this.f9361p != f8) {
            this.f9361p = f8;
            F(this.f9360o, f8, this.f9362q);
        }
    }

    public final void R(float f8) {
        this.f9371z = f8;
        Matrix matrix = this.K;
        g(f8, matrix);
        this.F.setImageMatrix(matrix);
    }

    public final void S(int i8) {
        if (this.A != i8) {
            this.A = i8;
            e0();
        }
    }

    public void T(int i8) {
        this.f9363r = i8;
    }

    public final void U(float f8) {
        if (this.f9362q != f8) {
            this.f9362q = f8;
            F(this.f9360o, this.f9361p, f8);
        }
    }

    public void V(ColorStateList colorStateList) {
        Drawable drawable = this.f9355j;
        if (drawable != null) {
            h0.a.o(drawable, c6.b.d(colorStateList));
        }
    }

    public void W(boolean z7) {
        this.f9359n = z7;
        f0();
    }

    public final void X(m mVar) {
        this.f9353h = mVar;
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        Object obj = this.f9355j;
        if (obj instanceof p) {
            ((p) obj).setShapeAppearanceModel(mVar);
        }
        y5.c cVar = this.f9356k;
        if (cVar != null) {
            cVar.f(mVar);
        }
    }

    public final void Y(j5.h hVar) {
        this.f9368w = hVar;
    }

    public boolean Z() {
        return true;
    }

    public final boolean a0() {
        return t.Q(this.F) && !this.F.isInEditMode();
    }

    public final boolean b0() {
        return !this.f9358m || this.F.getSizeDimension() >= this.f9363r;
    }

    public void c0(j jVar, boolean z7) {
        if (z()) {
            return;
        }
        Animator animator = this.f9367v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(0, z7);
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
        j5.h hVarM = this.f9368w;
        if (hVarM == null) {
            hVarM = m();
        }
        AnimatorSet animatorSetH = h(hVarM, 1.0f, 1.0f, 1.0f);
        animatorSetH.addListener(new b(z7, jVar));
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
            if (this.f9370y % 90.0f != 0.0f) {
                if (this.F.getLayerType() != 1) {
                    this.F.setLayerType(1, null);
                }
            } else if (this.F.getLayerType() != 0) {
                this.F.setLayerType(0, null);
            }
        }
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            hVar.e0((int) this.f9370y);
        }
    }

    public void e(Animator.AnimatorListener animatorListener) {
        if (this.C == null) {
            this.C = new ArrayList<>();
        }
        this.C.add(animatorListener);
    }

    public final void e0() {
        R(this.f9371z);
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

    public final void g(float f8, Matrix matrix) {
        matrix.reset();
        if (this.F.getDrawable() == null || this.A == 0) {
            return;
        }
        RectF rectF = this.I;
        RectF rectF2 = this.J;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i8 = this.A;
        rectF2.set(0.0f, 0.0f, i8, i8);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i9 = this.A;
        matrix.postScale(f8, f8, i9 / 2.0f, i9 / 2.0f);
    }

    public void g0(float f8) {
        e6.h hVar = this.f9354i;
        if (hVar != null) {
            hVar.W(f8);
        }
    }

    public final AnimatorSet h(j5.h hVar, float f8, float f9, float f10) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.ALPHA, f8);
        hVar.h("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_X, f9);
        hVar.h("scale").a(objectAnimatorOfFloat2);
        h0(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.F, (Property<FloatingActionButton, Float>) View.SCALE_Y, f9);
        hVar.h("scale").a(objectAnimatorOfFloat3);
        h0(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        g(f10, this.K);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.F, new j5.f(), new c(), new Matrix(this.K));
        hVar.h("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        j5.b.a(animatorSet, arrayList);
        return animatorSet;
    }

    public final void h0(ObjectAnimator objectAnimator) {
        if (Build.VERSION.SDK_INT != 26) {
            return;
        }
        objectAnimator.setEvaluator(new C0149d());
    }

    public final ValueAnimator i(l lVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(f9346a);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(lVar);
        valueAnimator.addUpdateListener(lVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public e6.h j() {
        return new e6.h((m) n0.h.e(this.f9353h));
    }

    public final Drawable k() {
        return this.f9357l;
    }

    public final j5.h l() {
        if (this.f9366u == null) {
            this.f9366u = j5.h.d(this.F.getContext(), R$animator.design_fab_hide_motion_spec);
        }
        return (j5.h) n0.h.e(this.f9366u);
    }

    public final j5.h m() {
        if (this.f9365t == null) {
            this.f9365t = j5.h.d(this.F.getContext(), R$animator.design_fab_show_motion_spec);
        }
        return (j5.h) n0.h.e(this.f9365t);
    }

    public float n() {
        return this.f9360o;
    }

    public boolean o() {
        return this.f9358m;
    }

    public final j5.h p() {
        return this.f9369x;
    }

    public float q() {
        return this.f9361p;
    }

    public final ViewTreeObserver.OnPreDrawListener r() {
        if (this.L == null) {
            this.L = new e();
        }
        return this.L;
    }

    public void s(Rect rect) {
        int sizeDimension = this.f9358m ? (this.f9363r - this.F.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.f9359n ? n() + this.f9362q : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    public float t() {
        return this.f9362q;
    }

    public final m u() {
        return this.f9353h;
    }

    public final j5.h v() {
        return this.f9368w;
    }

    public void w(j jVar, boolean z7) {
        if (y()) {
            return;
        }
        Animator animator = this.f9367v;
        if (animator != null) {
            animator.cancel();
        }
        if (!a0()) {
            this.F.b(z7 ? 8 : 4, z7);
            if (jVar != null) {
                jVar.b();
                return;
            }
            return;
        }
        j5.h hVarL = this.f9369x;
        if (hVarL == null) {
            hVarL = l();
        }
        AnimatorSet animatorSetH = h(hVarL, 0.0f, 0.0f, 0.0f);
        animatorSetH.addListener(new a(z7, jVar));
        ArrayList<Animator.AnimatorListener> arrayList = this.D;
        if (arrayList != null) {
            Iterator<Animator.AnimatorListener> it = arrayList.iterator();
            while (it.hasNext()) {
                animatorSetH.addListener(it.next());
            }
        }
        animatorSetH.start();
    }

    public void x(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i8) {
        e6.h hVarJ = j();
        this.f9354i = hVarJ;
        hVarJ.setTintList(colorStateList);
        if (mode != null) {
            this.f9354i.setTintMode(mode);
        }
        this.f9354i.d0(-12303292);
        this.f9354i.N(this.F.getContext());
        c6.a aVar = new c6.a(this.f9354i.D());
        aVar.setTintList(c6.b.d(colorStateList2));
        this.f9355j = aVar;
        this.f9357l = new LayerDrawable(new Drawable[]{(Drawable) n0.h.e(this.f9354i), aVar});
    }

    public boolean y() {
        return this.F.getVisibility() == 0 ? this.B == 1 : this.B != 2;
    }

    public boolean z() {
        return this.F.getVisibility() != 0 ? this.B == 2 : this.B != 1;
    }
}
