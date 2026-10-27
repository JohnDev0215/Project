package b.d.a.a.y;

import a.b.k.l;
import a.h.k.q;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.Property;
import android.view.View;
import android.view.ViewTreeObserver;
import b.d.a.a.e0.j;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static final TimeInterpolator A = b.d.a.a.l.a.f1968c;
    public static final int[] B = {R.attr.state_pressed, R.attr.state_enabled};
    public static final int[] C = {R.attr.state_hovered, R.attr.state_focused, R.attr.state_enabled};
    public static final int[] D = {R.attr.state_focused, R.attr.state_enabled};
    public static final int[] E = {R.attr.state_hovered, R.attr.state_enabled};
    public static final int[] F = {R.attr.state_enabled};
    public static final int[] G = new int[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public j f2145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.d.a.a.e0.g f2146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2147c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f2149e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f2150f;
    public float g;
    public final b.d.a.a.z.g h;
    public b.d.a.a.l.g i;
    public b.d.a.a.l.g j;
    public Animator k;
    public b.d.a.a.l.g l;
    public b.d.a.a.l.g m;
    public float n;
    public ArrayList<Animator.AnimatorListener> q;
    public ArrayList<Animator.AnimatorListener> r;
    public ArrayList<InterfaceC0045e> s;
    public final FloatingActionButton t;
    public final b.d.a.a.d0.b u;
    public ViewTreeObserver.OnPreDrawListener z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2148d = true;
    public float o = 1.0f;
    public int p = 0;
    public final Rect v = new Rect();
    public final RectF w = new RectF();
    public final RectF x = new RectF();
    public final Matrix y = new Matrix();

    public class a extends b.d.a.a.l.f {
        public a() {
        }

        @Override // android.animation.TypeEvaluator
        public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
            e.this.o = f2;
            matrix.getValues(this.f1976a);
            matrix2.getValues(this.f1977b);
            for (int i = 0; i < 9; i++) {
                float[] fArr = this.f1977b;
                float f3 = fArr[i];
                float[] fArr2 = this.f1976a;
                fArr[i] = ((f3 - fArr2[i]) * f2) + fArr2[i];
            }
            this.f1978c.setValues(this.f1977b);
            return this.f1978c;
        }
    }

    public class b extends h {
        public b(e eVar) {
            super(null);
        }

        @Override // b.d.a.a.y.e.h
        public float a() {
            return 0.0f;
        }
    }

    public class c extends h {
        public c() {
            super(null);
        }

        @Override // b.d.a.a.y.e.h
        public float a() {
            e eVar = e.this;
            return eVar.f2149e + eVar.f2150f;
        }
    }

    public class d extends h {
        public d() {
            super(null);
        }

        @Override // b.d.a.a.y.e.h
        public float a() {
            e eVar = e.this;
            return eVar.f2149e + eVar.g;
        }
    }

    /* JADX INFO: renamed from: b.d.a.a.y.e$e, reason: collision with other inner class name */
    public interface InterfaceC0045e {
        void a();

        void b();
    }

    public interface f {
    }

    public class g extends h {
        public g() {
            super(null);
        }

        @Override // b.d.a.a.y.e.h
        public float a() {
            return e.this.f2149e;
        }
    }

    public abstract class h extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2155a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2156b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2157c;

        public h(b.d.a.a.y.c cVar) {
        }

        public abstract float a();

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (e.this == null) {
                throw null;
            }
            this.f2155a = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (!this.f2155a) {
                b.d.a.a.e0.g gVar = e.this.f2146b;
                this.f2156b = gVar == null ? 0.0f : gVar.f1828a.o;
                this.f2157c = a();
                this.f2155a = true;
            }
            e eVar = e.this;
            valueAnimator.getAnimatedFraction();
            if (eVar == null) {
                throw null;
            }
        }
    }

    public e(FloatingActionButton floatingActionButton, b.d.a.a.d0.b bVar) {
        this.t = floatingActionButton;
        this.u = bVar;
        b.d.a.a.z.g gVar = new b.d.a.a.z.g();
        this.h = gVar;
        gVar.a(B, b(new d()));
        this.h.a(C, b(new c()));
        this.h.a(D, b(new c()));
        this.h.a(E, b(new c()));
        this.h.a(F, b(new g()));
        this.h.a(G, b(new b(this)));
        this.n = this.t.getRotation();
    }

    public final AnimatorSet a(b.d.a.a.l.g gVar, float f2, float f3, float f4) {
        ArrayList arrayList = new ArrayList();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.t, (Property<FloatingActionButton, Float>) View.ALPHA, f2);
        gVar.d("opacity").a(objectAnimatorOfFloat);
        arrayList.add(objectAnimatorOfFloat);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.t, (Property<FloatingActionButton, Float>) View.SCALE_X, f3);
        gVar.d("scale").a(objectAnimatorOfFloat2);
        arrayList.add(objectAnimatorOfFloat2);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(this.t, (Property<FloatingActionButton, Float>) View.SCALE_Y, f3);
        gVar.d("scale").a(objectAnimatorOfFloat3);
        arrayList.add(objectAnimatorOfFloat3);
        this.y.reset();
        this.t.getDrawable();
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(this.t, new b.d.a.a.l.e(), new a(), new Matrix(this.y));
        gVar.d("iconScale").a(objectAnimatorOfObject);
        arrayList.add(objectAnimatorOfObject);
        AnimatorSet animatorSet = new AnimatorSet();
        l.i.f0(animatorSet, arrayList);
        return animatorSet;
    }

    public final ValueAnimator b(h hVar) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(A);
        valueAnimator.setDuration(100L);
        valueAnimator.addListener(hVar);
        valueAnimator.addUpdateListener(hVar);
        valueAnimator.setFloatValues(0.0f, 1.0f);
        return valueAnimator;
    }

    public abstract float c();

    public void d(Rect rect) {
        int sizeDimension = this.f2147c ? (0 - this.t.getSizeDimension()) / 2 : 0;
        int iMax = Math.max(sizeDimension, (int) Math.ceil(this.f2148d ? c() + this.g : 0.0f));
        int iMax2 = Math.max(sizeDimension, (int) Math.ceil(r0 * 1.5f));
        rect.set(iMax, iMax2, iMax, iMax2);
    }

    public boolean e() {
        int visibility = this.t.getVisibility();
        int i = this.p;
        return visibility == 0 ? i == 1 : i != 2;
    }

    public boolean f() {
        int visibility = this.t.getVisibility();
        int i = this.p;
        return visibility != 0 ? i == 2 : i != 1;
    }

    public abstract void g();

    public abstract void h();

    public abstract void i(int[] iArr);

    public abstract void j(float f2, float f3, float f4);

    public void k() {
        ArrayList<InterfaceC0045e> arrayList = this.s;
        if (arrayList != null) {
            Iterator<InterfaceC0045e> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    public void l() {
        ArrayList<InterfaceC0045e> arrayList = this.s;
        if (arrayList != null) {
            Iterator<InterfaceC0045e> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }
    }

    public abstract boolean m();

    public final void n(float f2) {
        this.o = f2;
        Matrix matrix = this.y;
        matrix.reset();
        this.t.getDrawable();
        this.t.setImageMatrix(matrix);
    }

    public abstract void o(ColorStateList colorStateList);

    public abstract boolean p();

    public final boolean q() {
        return q.C(this.t) && !this.t.isInEditMode();
    }

    public final boolean r() {
        return !this.f2147c || this.t.getSizeDimension() >= 0;
    }

    public abstract void s();

    public final void t() {
        Rect rect = this.v;
        d(rect);
        l.i.i(null, "Didn't initialize content background");
        if (p()) {
            InsetDrawable insetDrawable = new InsetDrawable((Drawable) null, rect.left, rect.top, rect.right, rect.bottom);
            FloatingActionButton.b bVar = (FloatingActionButton.b) this.u;
            if (bVar == null) {
                throw null;
            }
            super/*android.widget.ImageButton*/.setBackgroundDrawable(insetDrawable);
        } else if (((FloatingActionButton.b) this.u) == null) {
            throw null;
        }
        b.d.a.a.d0.b bVar2 = this.u;
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        FloatingActionButton.b bVar3 = (FloatingActionButton.b) bVar2;
        FloatingActionButton.this.k.set(i, i2, i3, i4);
        FloatingActionButton floatingActionButton = FloatingActionButton.this;
        int i5 = floatingActionButton.i;
        floatingActionButton.setPadding(i + i5, i2 + i5, i3 + i5, i4 + i5);
    }
}
