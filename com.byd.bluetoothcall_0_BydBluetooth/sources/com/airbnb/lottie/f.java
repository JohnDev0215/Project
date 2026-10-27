package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import com.airbnb.lottie.t.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: LottieDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends Drawable implements Drawable.Callback, Animatable {

    /* JADX INFO: renamed from: b */
    private static final String f2843b = f.class.getSimpleName();

    /* JADX INFO: renamed from: c */
    private final Matrix f2844c = new Matrix();

    /* JADX INFO: renamed from: d */
    private com.airbnb.lottie.d f2845d;

    /* JADX INFO: renamed from: e */
    private final com.airbnb.lottie.u.e f2846e;
    private float f;
    private boolean g;
    private final Set<?> h;
    private final ArrayList<o> i;
    private final ValueAnimator.AnimatorUpdateListener j;
    private com.airbnb.lottie.s.b k;
    private String l;
    private com.airbnb.lottie.b m;
    private com.airbnb.lottie.s.a n;
    com.airbnb.lottie.a o;
    q p;
    private boolean q;
    private com.airbnb.lottie.model.layer.b r;
    private int s;
    private boolean t;
    private boolean u;
    private boolean v;

    /* JADX INFO: compiled from: LottieDrawable.java */
    class a implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f2847a;

        a(String str) {
            this.f2847a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.R(this.f2847a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class b implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ int f2849a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ int f2850b;

        b(int i, int i2) {
            this.f2849a = i;
            this.f2850b = i2;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.Q(this.f2849a, this.f2850b);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class c implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ int f2852a;

        c(int i) {
            this.f2852a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.K(this.f2852a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class d implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ float f2854a;

        d(float f) {
            this.f2854a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.W(this.f2854a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class e implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ com.airbnb.lottie.model.d f2856a;

        /* JADX INFO: renamed from: b */
        final /* synthetic */ Object f2857b;

        /* JADX INFO: renamed from: c */
        final /* synthetic */ com.airbnb.lottie.v.c f2858c;

        e(com.airbnb.lottie.model.d dVar, Object obj, com.airbnb.lottie.v.c cVar) {
            this.f2856a = dVar;
            this.f2857b = obj;
            this.f2858c = cVar;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.c(this.f2856a, this.f2857b, this.f2858c);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.f$f */
    /* JADX INFO: compiled from: LottieDrawable.java */
    class C0074f implements ValueAnimator.AnimatorUpdateListener {
        C0074f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (f.this.r != null) {
                f.this.r.G(f.this.f2846e.g());
            }
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class g implements o {
        g() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.E();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class h implements o {
        h() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.G();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class i implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ int f2863a;

        i(int i) {
            this.f2863a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.S(this.f2863a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class j implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ float f2865a;

        j(float f) {
            this.f2865a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.U(this.f2865a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class k implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ int f2867a;

        k(int i) {
            this.f2867a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.N(this.f2867a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class l implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ float f2869a;

        l(float f) {
            this.f2869a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.P(this.f2869a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class m implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f2871a;

        m(String str) {
            this.f2871a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.T(this.f2871a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class n implements o {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ String f2873a;

        n(String str) {
            this.f2873a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.O(this.f2873a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    private interface o {
        void a(com.airbnb.lottie.d dVar);
    }

    public f() {
        com.airbnb.lottie.u.e eVar = new com.airbnb.lottie.u.e();
        this.f2846e = eVar;
        this.f = 1.0f;
        this.g = true;
        this.h = new HashSet();
        this.i = new ArrayList<>();
        C0074f c0074f = new C0074f();
        this.j = c0074f;
        this.s = 255;
        this.v = false;
        eVar.addUpdateListener(c0074f);
    }

    private void d() {
        this.r = new com.airbnb.lottie.model.layer.b(this, s.a(this.f2845d), this.f2845d.j(), this.f2845d);
    }

    private void d0() {
        if (this.f2845d == null) {
            return;
        }
        float fX = x();
        setBounds(0, 0, (int) (this.f2845d.b().width() * fX), (int) (this.f2845d.b().height() * fX));
    }

    private Context k() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    private com.airbnb.lottie.s.a l() {
        if (getCallback() == null) {
            return null;
        }
        if (this.n == null) {
            this.n = new com.airbnb.lottie.s.a(getCallback(), this.o);
        }
        return this.n;
    }

    private com.airbnb.lottie.s.b o() {
        if (getCallback() == null) {
            return null;
        }
        com.airbnb.lottie.s.b bVar = this.k;
        if (bVar != null && !bVar.b(k())) {
            this.k = null;
        }
        if (this.k == null) {
            this.k = new com.airbnb.lottie.s.b(getCallback(), this.l, this.m, this.f2845d.i());
        }
        return this.k;
    }

    private float r(Canvas canvas) {
        return Math.min(canvas.getWidth() / this.f2845d.b().width(), canvas.getHeight() / this.f2845d.b().height());
    }

    public Typeface A(String str, String str2) {
        com.airbnb.lottie.s.a aVarL = l();
        if (aVarL != null) {
            return aVarL.b(str, str2);
        }
        return null;
    }

    public boolean B() {
        return this.f2846e.isRunning();
    }

    public boolean C() {
        return this.u;
    }

    public void D() {
        this.i.clear();
        this.f2846e.n();
    }

    public void E() {
        if (this.r == null) {
            this.i.add(new g());
            return;
        }
        if (this.g || v() == 0) {
            this.f2846e.o();
        }
        if (this.g) {
            return;
        }
        K((int) (y() < 0.0f ? s() : q()));
    }

    public List<com.airbnb.lottie.model.d> F(com.airbnb.lottie.model.d dVar) {
        if (this.r == null) {
            com.airbnb.lottie.u.d.b("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.r.e(dVar, 0, arrayList, new com.airbnb.lottie.model.d(new String[0]));
        return arrayList;
    }

    public void G() {
        if (this.r == null) {
            this.i.add(new h());
        } else if (this.g) {
            this.f2846e.s();
        }
    }

    public void H(boolean z) {
        this.u = z;
    }

    public boolean I(com.airbnb.lottie.d dVar) {
        if (this.f2845d == dVar) {
            return false;
        }
        this.v = false;
        f();
        this.f2845d = dVar;
        d();
        this.f2846e.u(dVar);
        W(this.f2846e.getAnimatedFraction());
        Z(this.f);
        d0();
        Iterator it = new ArrayList(this.i).iterator();
        while (it.hasNext()) {
            ((o) it.next()).a(dVar);
            it.remove();
        }
        this.i.clear();
        dVar.u(this.t);
        return true;
    }

    public void J(com.airbnb.lottie.a aVar) {
        com.airbnb.lottie.s.a aVar2 = this.n;
        if (aVar2 != null) {
            aVar2.c(aVar);
        }
    }

    public void K(int i2) {
        if (this.f2845d == null) {
            this.i.add(new c(i2));
        } else {
            this.f2846e.v(i2);
        }
    }

    public void L(com.airbnb.lottie.b bVar) {
        this.m = bVar;
        com.airbnb.lottie.s.b bVar2 = this.k;
        if (bVar2 != null) {
            bVar2.d(bVar);
        }
    }

    public void M(String str) {
        this.l = str;
    }

    public void N(int i2) {
        if (this.f2845d == null) {
            this.i.add(new k(i2));
        } else {
            this.f2846e.w(i2 + 0.99f);
        }
    }

    public void O(String str) {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar == null) {
            this.i.add(new n(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            N((int) (gVarK.f2991c + gVarK.f2992d));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void P(float f) {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar == null) {
            this.i.add(new l(f));
        } else {
            N((int) com.airbnb.lottie.u.g.j(dVar.o(), this.f2845d.f(), f));
        }
    }

    public void Q(int i2, int i3) {
        if (this.f2845d == null) {
            this.i.add(new b(i2, i3));
        } else {
            this.f2846e.x(i2, i3 + 0.99f);
        }
    }

    public void R(String str) {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar == null) {
            this.i.add(new a(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            int i2 = (int) gVarK.f2991c;
            Q(i2, ((int) gVarK.f2992d) + i2);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public void S(int i2) {
        if (this.f2845d == null) {
            this.i.add(new i(i2));
        } else {
            this.f2846e.y(i2);
        }
    }

    public void T(String str) {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar == null) {
            this.i.add(new m(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            S((int) gVarK.f2991c);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void U(float f) {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar == null) {
            this.i.add(new j(f));
        } else {
            S((int) com.airbnb.lottie.u.g.j(dVar.o(), this.f2845d.f(), f));
        }
    }

    public void V(boolean z) {
        this.t = z;
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar != null) {
            dVar.u(z);
        }
    }

    public void W(float f) {
        if (this.f2845d == null) {
            this.i.add(new d(f));
            return;
        }
        com.airbnb.lottie.c.a("Drawable#setProgress");
        this.f2846e.v(com.airbnb.lottie.u.g.j(this.f2845d.o(), this.f2845d.f(), f));
        com.airbnb.lottie.c.b("Drawable#setProgress");
    }

    public void X(int i2) {
        this.f2846e.setRepeatCount(i2);
    }

    public void Y(int i2) {
        this.f2846e.setRepeatMode(i2);
    }

    public void Z(float f) {
        this.f = f;
        d0();
    }

    public void a0(float f) {
        this.f2846e.z(f);
    }

    void b0(Boolean bool) {
        this.g = bool.booleanValue();
    }

    public <T> void c(com.airbnb.lottie.model.d dVar, T t, com.airbnb.lottie.v.c<T> cVar) {
        if (this.r == null) {
            this.i.add(new e(dVar, t, cVar));
            return;
        }
        boolean zIsEmpty = true;
        if (dVar.d() != null) {
            dVar.d().f(t, cVar);
        } else {
            List<com.airbnb.lottie.model.d> listF = F(dVar);
            for (int i2 = 0; i2 < listF.size(); i2++) {
                listF.get(i2).d().f(t, cVar);
            }
            zIsEmpty = true ^ listF.isEmpty();
        }
        if (zIsEmpty) {
            invalidateSelf();
            if (t == com.airbnb.lottie.k.A) {
                W(u());
            }
        }
    }

    public void c0(q qVar) {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f;
        this.v = false;
        com.airbnb.lottie.c.a("Drawable#draw");
        if (this.r == null) {
            return;
        }
        float f2 = this.f;
        float fR = r(canvas);
        if (f2 > fR) {
            f = this.f / fR;
        } else {
            fR = f2;
            f = 1.0f;
        }
        int iSave = -1;
        if (f > 1.0f) {
            iSave = canvas.save();
            float fWidth = this.f2845d.b().width() / 2.0f;
            float fHeight = this.f2845d.b().height() / 2.0f;
            float f3 = fWidth * fR;
            float f4 = fHeight * fR;
            canvas.translate((x() * fWidth) - f3, (x() * fHeight) - f4);
            canvas.scale(f, f, f3, f4);
        }
        this.f2844c.reset();
        this.f2844c.preScale(fR, fR);
        this.r.g(canvas, this.f2844c, this.s);
        com.airbnb.lottie.c.b("Drawable#draw");
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    public void e() {
        this.i.clear();
        this.f2846e.cancel();
    }

    public boolean e0() {
        return this.p == null && this.f2845d.c().l() > 0;
    }

    public void f() {
        if (this.f2846e.isRunning()) {
            this.f2846e.cancel();
        }
        this.f2845d = null;
        this.r = null;
        this.k = null;
        this.f2846e.f();
        invalidateSelf();
    }

    public void g(boolean z) {
        if (this.q == z) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            com.airbnb.lottie.u.d.b("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.q = z;
        if (this.f2845d != null) {
            d();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.s;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f2845d == null) {
            return -1;
        }
        return (int) (r0.b().height() * x());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f2845d == null) {
            return -1;
        }
        return (int) (r0.b().width() * x());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean h() {
        return this.q;
    }

    public void i() {
        this.i.clear();
        this.f2846e.endAnimation();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.v) {
            return;
        }
        this.v = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return B();
    }

    public com.airbnb.lottie.d j() {
        return this.f2845d;
    }

    public int m() {
        return (int) this.f2846e.h();
    }

    public Bitmap n(String str) {
        com.airbnb.lottie.s.b bVarO = o();
        if (bVarO != null) {
            return bVarO.a(str);
        }
        return null;
    }

    public String p() {
        return this.l;
    }

    public float q() {
        return this.f2846e.j();
    }

    public float s() {
        return this.f2846e.k();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.s = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        com.airbnb.lottie.u.d.b("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        E();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        i();
    }

    public com.airbnb.lottie.n t() {
        com.airbnb.lottie.d dVar = this.f2845d;
        if (dVar != null) {
            return dVar.m();
        }
        return null;
    }

    public float u() {
        return this.f2846e.g();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    public int v() {
        return this.f2846e.getRepeatCount();
    }

    public int w() {
        return this.f2846e.getRepeatMode();
    }

    public float x() {
        return this.f;
    }

    public float y() {
        return this.f2846e.l();
    }

    public q z() {
        return this.p;
    }
}
