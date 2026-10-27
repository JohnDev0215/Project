package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.car.Car;
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
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.v.s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class f extends Drawable implements Drawable.Callback, Animatable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f676a = f.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix f677b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.airbnb.lottie.d f678c;
    private final com.airbnb.lottie.w.e d;
    private float e;
    private boolean f;
    private final Set<?> g;
    private final ArrayList<o> h;
    private final ValueAnimator.AnimatorUpdateListener i;

    @Nullable
    private com.airbnb.lottie.s.b j;

    @Nullable
    private String k;

    @Nullable
    private com.airbnb.lottie.b l;

    @Nullable
    private com.airbnb.lottie.s.a m;

    @Nullable
    com.airbnb.lottie.a n;

    @Nullable
    q o;
    private boolean p;

    @Nullable
    private com.airbnb.lottie.t.l.b q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;

    class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f679a;

        a(String str) {
            this.f679a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.R(this.f679a);
        }
    }

    class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f681a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f682b;

        b(int i, int i2) {
            this.f681a = i;
            this.f682b = i2;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.Q(this.f681a, this.f682b);
        }
    }

    class c implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f684a;

        c(int i) {
            this.f684a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.K(this.f684a);
        }
    }

    class d implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f686a;

        d(float f) {
            this.f686a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.W(this.f686a);
        }
    }

    class e implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.t.e f688a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f689b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.x.c f690c;

        e(com.airbnb.lottie.t.e eVar, Object obj, com.airbnb.lottie.x.c cVar) {
            this.f688a = eVar;
            this.f689b = obj;
            this.f690c = cVar;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.c(this.f688a, this.f689b, this.f690c);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.f$f, reason: collision with other inner class name */
    class C0023f implements ValueAnimator.AnimatorUpdateListener {
        C0023f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (f.this.q != null) {
                f.this.q.E(f.this.d.g());
            }
        }
    }

    class g implements o {
        g() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.E();
        }
    }

    class h implements o {
        h() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.G();
        }
    }

    class i implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f694a;

        i(int i) {
            this.f694a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.S(this.f694a);
        }
    }

    class j implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f696a;

        j(float f) {
            this.f696a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.U(this.f696a);
        }
    }

    class k implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f698a;

        k(int i) {
            this.f698a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.N(this.f698a);
        }
    }

    class l implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f700a;

        l(float f) {
            this.f700a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.P(this.f700a);
        }
    }

    class m implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f702a;

        m(String str) {
            this.f702a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.T(this.f702a);
        }
    }

    class n implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f704a;

        n(String str) {
            this.f704a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.O(this.f704a);
        }
    }

    private interface o {
        void a(com.airbnb.lottie.d dVar);
    }

    public f() {
        com.airbnb.lottie.w.e eVar = new com.airbnb.lottie.w.e();
        this.d = eVar;
        this.e = 1.0f;
        this.f = true;
        this.g = new HashSet();
        this.h = new ArrayList<>();
        C0023f c0023f = new C0023f();
        this.i = c0023f;
        this.r = 255;
        this.u = false;
        eVar.addUpdateListener(c0023f);
    }

    private void d() {
        this.q = new com.airbnb.lottie.t.l.b(this, s.a(this.f678c), this.f678c.j(), this.f678c);
    }

    private void d0() {
        if (this.f678c == null) {
            return;
        }
        float fX = x();
        setBounds(0, 0, (int) (this.f678c.b().width() * fX), (int) (this.f678c.b().height() * fX));
    }

    @Nullable
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
        if (this.m == null) {
            this.m = new com.airbnb.lottie.s.a(getCallback(), this.n);
        }
        return this.m;
    }

    private com.airbnb.lottie.s.b o() {
        if (getCallback() == null) {
            return null;
        }
        com.airbnb.lottie.s.b bVar = this.j;
        if (bVar != null && !bVar.b(k())) {
            this.j = null;
        }
        if (this.j == null) {
            this.j = new com.airbnb.lottie.s.b(getCallback(), this.k, this.l, this.f678c.i());
        }
        return this.j;
    }

    private float r(@NonNull Canvas canvas) {
        return Math.min(canvas.getWidth() / this.f678c.b().width(), canvas.getHeight() / this.f678c.b().height());
    }

    @Nullable
    public Typeface A(String str, String str2) {
        com.airbnb.lottie.s.a aVarL = l();
        if (aVarL != null) {
            return aVarL.b(str, str2);
        }
        return null;
    }

    public boolean B() {
        return this.d.isRunning();
    }

    public boolean C() {
        return this.t;
    }

    public void D() {
        this.h.clear();
        this.d.n();
    }

    @MainThread
    public void E() {
        if (this.q == null) {
            this.h.add(new g());
            return;
        }
        if (this.f || v() == 0) {
            this.d.o();
        }
        if (this.f) {
            return;
        }
        K((int) (y() < 0.0f ? s() : q()));
    }

    public List<com.airbnb.lottie.t.e> F(com.airbnb.lottie.t.e eVar) {
        if (this.q == null) {
            com.airbnb.lottie.w.d.b("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.q.e(eVar, 0, arrayList, new com.airbnb.lottie.t.e(new String[0]));
        return arrayList;
    }

    @MainThread
    public void G() {
        if (this.q == null) {
            this.h.add(new h());
        } else if (this.f) {
            this.d.s();
        }
    }

    public void H(boolean z) {
        this.t = z;
    }

    public boolean I(com.airbnb.lottie.d dVar) {
        if (this.f678c == dVar) {
            return false;
        }
        this.u = false;
        f();
        this.f678c = dVar;
        d();
        this.d.u(dVar);
        W(this.d.getAnimatedFraction());
        Z(this.e);
        d0();
        Iterator it = new ArrayList(this.h).iterator();
        while (it.hasNext()) {
            ((o) it.next()).a(dVar);
            it.remove();
        }
        this.h.clear();
        dVar.u(this.s);
        return true;
    }

    public void J(com.airbnb.lottie.a aVar) {
        com.airbnb.lottie.s.a aVar2 = this.m;
        if (aVar2 != null) {
            aVar2.c(aVar);
        }
    }

    public void K(int i2) {
        if (this.f678c == null) {
            this.h.add(new c(i2));
        } else {
            this.d.v(i2);
        }
    }

    public void L(com.airbnb.lottie.b bVar) {
        this.l = bVar;
        com.airbnb.lottie.s.b bVar2 = this.j;
        if (bVar2 != null) {
            bVar2.d(bVar);
        }
    }

    public void M(@Nullable String str) {
        this.k = str;
    }

    public void N(int i2) {
        if (this.f678c == null) {
            this.h.add(new k(i2));
        } else {
            this.d.w(i2 + 0.99f);
        }
    }

    public void O(String str) {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar == null) {
            this.h.add(new n(str));
            return;
        }
        com.airbnb.lottie.t.h hVarK = dVar.k(str);
        if (hVarK != null) {
            N((int) (hVarK.f800c + hVarK.d));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void P(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar == null) {
            this.h.add(new l(f));
        } else {
            N((int) com.airbnb.lottie.w.g.j(dVar.o(), this.f678c.f(), f));
        }
    }

    public void Q(int i2, int i3) {
        if (this.f678c == null) {
            this.h.add(new b(i2, i3));
        } else {
            this.d.x(i2, i3 + 0.99f);
        }
    }

    public void R(String str) {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar == null) {
            this.h.add(new a(str));
            return;
        }
        com.airbnb.lottie.t.h hVarK = dVar.k(str);
        if (hVarK != null) {
            int i2 = (int) hVarK.f800c;
            Q(i2, ((int) hVarK.d) + i2);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public void S(int i2) {
        if (this.f678c == null) {
            this.h.add(new i(i2));
        } else {
            this.d.y(i2);
        }
    }

    public void T(String str) {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar == null) {
            this.h.add(new m(str));
            return;
        }
        com.airbnb.lottie.t.h hVarK = dVar.k(str);
        if (hVarK != null) {
            S((int) hVarK.f800c);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void U(float f) {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar == null) {
            this.h.add(new j(f));
        } else {
            S((int) com.airbnb.lottie.w.g.j(dVar.o(), this.f678c.f(), f));
        }
    }

    public void V(boolean z) {
        this.s = z;
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar != null) {
            dVar.u(z);
        }
    }

    public void W(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.f678c == null) {
            this.h.add(new d(f));
            return;
        }
        com.airbnb.lottie.c.a("Drawable#setProgress");
        this.d.v(com.airbnb.lottie.w.g.j(this.f678c.o(), this.f678c.f(), f));
        com.airbnb.lottie.c.b("Drawable#setProgress");
    }

    public void X(int i2) {
        this.d.setRepeatCount(i2);
    }

    public void Y(int i2) {
        this.d.setRepeatMode(i2);
    }

    public void Z(float f) {
        this.e = f;
        d0();
    }

    public void a0(float f) {
        this.d.z(f);
    }

    void b0(Boolean bool) {
        this.f = bool.booleanValue();
    }

    public <T> void c(com.airbnb.lottie.t.e eVar, T t, com.airbnb.lottie.x.c<T> cVar) {
        if (this.q == null) {
            this.h.add(new e(eVar, t, cVar));
            return;
        }
        boolean zIsEmpty = true;
        if (eVar.d() != null) {
            eVar.d().g(t, cVar);
        } else {
            List<com.airbnb.lottie.t.e> listF = F(eVar);
            for (int i2 = 0; i2 < listF.size(); i2++) {
                listF.get(i2).d().g(t, cVar);
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
    public void draw(@NonNull Canvas canvas) {
        float f;
        this.u = false;
        com.airbnb.lottie.c.a("Drawable#draw");
        if (this.q == null) {
            return;
        }
        float f2 = this.e;
        float fR = r(canvas);
        if (f2 > fR) {
            f = this.e / fR;
        } else {
            fR = f2;
            f = 1.0f;
        }
        int iSave = -1;
        if (f > 1.0f) {
            iSave = canvas.save();
            float fWidth = this.f678c.b().width() / 2.0f;
            float fHeight = this.f678c.b().height() / 2.0f;
            float f3 = fWidth * fR;
            float f4 = fHeight * fR;
            canvas.translate((x() * fWidth) - f3, (x() * fHeight) - f4);
            canvas.scale(f, f, f3, f4);
        }
        this.f677b.reset();
        this.f677b.preScale(fR, fR);
        this.q.f(canvas, this.f677b, this.r);
        com.airbnb.lottie.c.b("Drawable#draw");
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    public void e() {
        this.h.clear();
        this.d.cancel();
    }

    public boolean e0() {
        return this.o == null && this.f678c.c().size() > 0;
    }

    public void f() {
        if (this.d.isRunning()) {
            this.d.cancel();
        }
        this.f678c = null;
        this.q = null;
        this.j = null;
        this.d.f();
        invalidateSelf();
    }

    public void g(boolean z) {
        if (this.p == z) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            com.airbnb.lottie.w.d.b("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.p = z;
        if (this.f678c != null) {
            d();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.r;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f678c == null) {
            return -1;
        }
        return (int) (r0.b().height() * x());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f678c == null) {
            return -1;
        }
        return (int) (r0.b().width() * x());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public boolean h() {
        return this.p;
    }

    @MainThread
    public void i() {
        this.h.clear();
        this.d.endAnimation();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        if (this.u) {
            return;
        }
        this.u = true;
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
        return this.f678c;
    }

    public int m() {
        return (int) this.d.h();
    }

    @Nullable
    public Bitmap n(String str) {
        com.airbnb.lottie.s.b bVarO = o();
        if (bVarO != null) {
            return bVarO.a(str);
        }
        return null;
    }

    @Nullable
    public String p() {
        return this.k;
    }

    public float q() {
        return this.d.j();
    }

    public float s() {
        return this.d.k();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(@IntRange(from = Car.CAR_WAIT_TIMEOUT_DO_NOT_WAIT, to = 255) int i2) {
        this.r = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        com.airbnb.lottie.w.d.b("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Animatable
    @MainThread
    public void start() {
        E();
    }

    @Override // android.graphics.drawable.Animatable
    @MainThread
    public void stop() {
        i();
    }

    @Nullable
    public com.airbnb.lottie.n t() {
        com.airbnb.lottie.d dVar = this.f678c;
        if (dVar != null) {
            return dVar.m();
        }
        return null;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float u() {
        return this.d.g();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    public int v() {
        return this.d.getRepeatCount();
    }

    public int w() {
        return this.d.getRepeatMode();
    }

    public float x() {
        return this.e;
    }

    public float y() {
        return this.d.l();
    }

    @Nullable
    public q z() {
        return this.o;
    }
}
