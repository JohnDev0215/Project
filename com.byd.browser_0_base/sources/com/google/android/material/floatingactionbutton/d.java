package com.google.android.material.floatingactionbutton;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Property;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$integer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import defpackage.C0173a;
import defpackage.a8;
import defpackage.dc2;
import defpackage.ex0;
import defpackage.fj1;
import defpackage.ij1;
import defpackage.ik0;
import defpackage.n3;
import defpackage.ns2;
import defpackage.sc1;
import defpackage.th;
import defpackage.tj2;
import defpackage.tn0;
import defpackage.u5;
import defpackage.uj2;
import defpackage.un0;
import defpackage.vc1;
import defpackage.yj2;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static final ik0 C = u5.c;
    public static final int D = R$attr.motionDurationLong2;
    public static final int E = R$attr.motionEasingEmphasizedInterpolator;
    public static final int F = R$attr.motionDurationMedium1;
    public static final int G = R$attr.motionEasingEmphasizedAccelerateInterpolator;
    public static final int[] H = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] I = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final int[] f31J = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] K = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] L = {R.attr.state_enabled};
    public static final int[] M = new int[0];
    public tn0 B;
    public uj2 a;
    public sc1 b;
    public Drawable c;
    public th d;
    public LayerDrawable e;
    public boolean f;
    public float h;
    public float i;
    public float j;
    public int k;
    public Animator l;
    public fj1 m;
    public fj1 n;
    public float o;
    public int q;
    public ArrayList<Animator.AnimatorListener> s;
    public ArrayList<Animator.AnimatorListener> t;
    public ArrayList<f> u;
    public final FloatingActionButton v;
    public final tj2 w;
    public boolean g = true;
    public float p = 1.0f;
    public int r = 0;
    public final Rect x = new Rect();
    public final RectF y = new RectF();
    public final RectF z = new RectF();
    public final Matrix A = new Matrix();

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class a extends vc1 {
        public a() {
        }

        @Override // android.animation.TypeEvaluator
        public final Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            d.this.p = f;
            float[] fArr = this.a;
            matrix.getValues(fArr);
            float[] fArr2 = this.b;
            matrix2.getValues(fArr2);
            for (int i = 0; i < 9; i++) {
                float f2 = fArr2[i];
                float f3 = fArr[i];
                fArr2[i] = C0173a.f(f2, f3, f, f3);
            }
            Matrix matrix3 = this.c;
            matrix3.setValues(fArr2);
            return matrix3;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        public final /* synthetic */ float e;
        public final /* synthetic */ float f;
        public final /* synthetic */ float g;
        public final /* synthetic */ float h;
        public final /* synthetic */ float i;
        public final /* synthetic */ float j;
        public final /* synthetic */ float k;
        public final /* synthetic */ Matrix l;

        public b(float f, float f2, float f3, float f4, float f5, float f6, float f7, Matrix matrix) {
            this.e = f;
            this.f = f2;
            this.g = f3;
            this.h = f4;
            this.i = f5;
            this.j = f6;
            this.k = f7;
            this.l = matrix;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            d dVar = d.this;
            dVar.v.setAlpha(u5.a(this.e, this.f, 0.0f, 0.2f, fFloatValue));
            FloatingActionButton floatingActionButton = dVar.v;
            float f = this.h;
            float f2 = this.g;
            floatingActionButton.setScaleX(((f - f2) * fFloatValue) + f2);
            FloatingActionButton floatingActionButton2 = dVar.v;
            float f3 = this.i;
            floatingActionButton2.setScaleY(((f - f3) * fFloatValue) + f3);
            float f4 = this.k;
            float f5 = this.j;
            dVar.p = C0173a.f(f4, f5, fFloatValue, f5);
            float f6 = C0173a.f(f4, f5, fFloatValue, f5);
            Matrix matrix = this.l;
            dVar.a(f6, matrix);
            dVar.v.setImageMatrix(matrix);
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class c extends i {
        public c(un0 un0Var) {
            super(un0Var);
        }

        @Override // com.google.android.material.floatingactionbutton.d.i
        public final float a() {
            return 0.0f;
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.floatingactionbutton.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class C0036d extends i {
        public final /* synthetic */ d i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0036d(un0 un0Var) {
            super(un0Var);
            this.i = un0Var;
        }

        @Override // com.google.android.material.floatingactionbutton.d.i
        public final float a() {
            d dVar = this.i;
            return dVar.h + dVar.i;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class e extends i {
        public final /* synthetic */ d i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(un0 un0Var) {
            super(un0Var);
            this.i = un0Var;
        }

        @Override // com.google.android.material.floatingactionbutton.d.i
        public final float a() {
            d dVar = this.i;
            return dVar.h + dVar.j;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public interface f {
        void a();

        void b();
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public interface g {
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public class h extends i {
        public final /* synthetic */ d i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(un0 un0Var) {
            super(un0Var);
            this.i = un0Var;
        }

        @Override // com.google.android.material.floatingactionbutton.d.i
        public final float a() {
            return this.i.h;
        }
    }

    /* JADX INFO: compiled from: FloatingActionButtonImpl.java */
    public abstract class i extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        public boolean e;
        public float f;
        public float g;
        public final /* synthetic */ d h;

        public i(un0 un0Var) {
            this.h = un0Var;
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            float f = (int) this.g;
            sc1 sc1Var = this.h.b;
            if (sc1Var != null) {
                sc1Var.m(f);
            }
            this.e = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            boolean z = this.e;
            d dVar = this.h;
            if (!z) {
                sc1 sc1Var = dVar.b;
                this.f = sc1Var == null ? 0.0f : sc1Var.e.n;
                this.g = a();
                this.e = true;
            }
            float f = this.f;
            float animatedFraction = (int) ((valueAnimator.getAnimatedFraction() * (this.g - f)) + f);
            sc1 sc1Var2 = dVar.b;
            if (sc1Var2 != null) {
                sc1Var2.m(animatedFraction);
            }
        }
    }

    public d(FloatingActionButton floatingActionButton, FloatingActionButton.b bVar) {
        this.v = floatingActionButton;
        this.w = bVar;
        ns2 ns2Var = new ns2();
        un0 un0Var = (un0) this;
        ns2Var.a(H, d(new e(un0Var)));
        ns2Var.a(I, d(new C0036d(un0Var)));
        ns2Var.a(f31J, d(new C0036d(un0Var)));
        ns2Var.a(K, d(new C0036d(un0Var)));
        ns2Var.a(L, d(new h(un0Var)));
        ns2Var.a(M, d(new c(un0Var)));
        this.o = floatingActionButton.getRotation();
    }

    public static ValueAnimator d(i iVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(C);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(iVar);
        valueAnimator.addUpdateListener(iVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public final void a(float f2, Matrix matrix) {
        matrix.reset();
        if (this.v.getDrawable() == null || this.q == 0) {
            return;
        }
        RectF rectF = this.y;
        RectF rectF2 = this.z;
        rectF.set(0.0f, 0.0f, r0.getIntrinsicWidth(), r0.getIntrinsicHeight());
        int i2 = this.q;
        rectF2.set(0.0f, 0.0f, i2, i2);
        matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.CENTER);
        int i3 = this.q;
        matrix.postScale(f2, f2, i3 / 2.0f, i3 / 2.0f);
    }

    public final AnimatorSet b(fj1 fj1Var, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        Property property = View.ALPHA;
        float[] fArr = {f2};
        FloatingActionButton floatingActionButton = this.v;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) property, fArr);
        fj1Var.f("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        fj1Var.f("scale").a(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(floatingActionButton, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        fj1Var.f("scale").a(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        Matrix matrix = this.A;
        a(f4, matrix);
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(floatingActionButton, new ex0(), new a(), new Matrix(matrix));
        fj1Var.f("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        a8.a0(animatorSet, arrayList);
        return animatorSet;
    }

    public final AnimatorSet c(float f2, float f3, float f4, int i2, int i3) {
        AnimatorSet animatorSet = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        FloatingActionButton floatingActionButton = this.v;
        valueAnimatorOfFloat.addUpdateListener(new b(floatingActionButton.getAlpha(), f2, floatingActionButton.getScaleX(), f3, floatingActionButton.getScaleY(), this.p, f4, new Matrix(this.A)));
        arrayList.add(valueAnimatorOfFloat);
        a8.a0(animatorSet, arrayList);
        animatorSet.setDuration(ij1.c(floatingActionButton.getContext(), i2, floatingActionButton.getContext().getResources().getInteger(R$integer.material_motion_duration_long_1)));
        animatorSet.setInterpolator(ij1.d(floatingActionButton.getContext(), i3, u5.b));
        return animatorSet;
    }

    public float e() {
        throw null;
    }

    public void f(Rect rect) {
        int sizeDimension = this.f ? (this.k - this.v.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.g ? e() + this.j : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r1 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    public void g(ColorStateList colorStateList, PorterDuff.Mode mode, ColorStateList colorStateList2, int i2) {
        throw null;
    }

    public void h() {
        throw null;
    }

    public void i() {
        throw null;
    }

    public void j(int[] iArr) {
        throw null;
    }

    public void k(float f2, float f3, float f4) {
        throw null;
    }

    public final void l() {
        ArrayList<f> arrayList = this.u;
        if (arrayList != null) {
            Iterator<f> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void m(ColorStateList colorStateList) {
        Drawable drawable = this.c;
        if (drawable != null) {
            drawable.setTintList(dc2.c(colorStateList));
        }
    }

    public final void n(uj2 uj2Var) {
        this.a = uj2Var;
        sc1 sc1Var = this.b;
        if (sc1Var != null) {
            sc1Var.setShapeAppearanceModel(uj2Var);
        }
        Object obj = this.c;
        if (obj instanceof yj2) {
            ((yj2) obj).setShapeAppearanceModel(uj2Var);
        }
        th thVar = this.d;
        if (thVar != null) {
            thVar.o = uj2Var;
            thVar.invalidateSelf();
        }
    }

    public boolean o() {
        throw null;
    }

    public void p() {
        throw null;
    }

    public final void q() {
        Rect rect = this.x;
        f(rect);
        n3.v(this.e, "Didn't initialize content background");
        boolean zO = o();
        tj2 tj2Var = this.w;
        if (zO) {
            super/*android.view.View*/.setBackgroundDrawable(new InsetDrawable((Drawable) this.e, rect.left, rect.top, rect.right, rect.bottom));
        } else {
            LayerDrawable layerDrawable = this.e;
            FloatingActionButton.b bVar = (FloatingActionButton.b) tj2Var;
            if (layerDrawable != null) {
                super/*android.view.View*/.setBackgroundDrawable(layerDrawable);
            } else {
                bVar.getClass();
            }
        }
        int i2 = rect.left;
        int i3 = rect.top;
        int i4 = rect.right;
        int i5 = rect.bottom;
        FloatingActionButton floatingActionButton = FloatingActionButton.this;
        floatingActionButton.p.set(i2, i3, i4, i5);
        int i6 = floatingActionButton.m;
        floatingActionButton.setPadding(i2 + i6, i3 + i6, i4 + i6, i5 + i6);
    }
}
