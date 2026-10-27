package com.airbnb.lottie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f2459b = f.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f2460c = new Matrix();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.airbnb.lottie.d f2461d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.u.e f2462e;
    private float f;
    private boolean g;
    private boolean h;
    private final Set<?> i;
    private final ArrayList<o> j;
    private final ValueAnimator.AnimatorUpdateListener k;
    private ImageView.ScaleType l;
    private com.airbnb.lottie.s.b m;
    private String n;
    private com.airbnb.lottie.b o;
    private com.airbnb.lottie.s.a p;
    com.airbnb.lottie.a q;
    q r;
    private boolean s;
    private com.airbnb.lottie.model.layer.b t;
    private int u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;

    /* JADX INFO: compiled from: LottieDrawable.java */
    class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2463a;

        a(String str) {
            this.f2463a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.U(this.f2463a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2465a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2466b;

        b(int i, int i2) {
            this.f2465a = i;
            this.f2466b = i2;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.T(this.f2465a, this.f2466b);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class c implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2468a;

        c(int i) {
            this.f2468a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.N(this.f2468a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class d implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f2470a;

        d(float f) {
            this.f2470a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.Z(this.f2470a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class e implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.model.d f2472a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f2473b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.v.c f2474c;

        e(com.airbnb.lottie.model.d dVar, Object obj, com.airbnb.lottie.v.c cVar) {
            this.f2472a = dVar;
            this.f2473b = obj;
            this.f2474c = cVar;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.c(this.f2472a, this.f2473b, this.f2474c);
        }
    }

    /* JADX INFO: renamed from: com.airbnb.lottie.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LottieDrawable.java */
    class C0055f implements ValueAnimator.AnimatorUpdateListener {
        C0055f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (f.this.t != null) {
                f.this.t.I(f.this.f2462e.g());
            }
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class g implements o {
        g() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.H();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class h implements o {
        h() {
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.J();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class i implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2479a;

        i(int i) {
            this.f2479a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.V(this.f2479a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class j implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f2481a;

        j(float f) {
            this.f2481a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.X(this.f2481a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class k implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f2483a;

        k(int i) {
            this.f2483a = i;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.Q(this.f2483a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class l implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ float f2485a;

        l(float f) {
            this.f2485a = f;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.S(this.f2485a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class m implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2487a;

        m(String str) {
            this.f2487a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.W(this.f2487a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    class n implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f2489a;

        n(String str) {
            this.f2489a = str;
        }

        @Override // com.airbnb.lottie.f.o
        public void a(com.airbnb.lottie.d dVar) {
            f.this.R(this.f2489a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    private interface o {
        void a(com.airbnb.lottie.d dVar);
    }

    public f() {
        com.airbnb.lottie.u.e eVar = new com.airbnb.lottie.u.e();
        this.f2462e = eVar;
        this.f = 1.0f;
        this.g = true;
        this.h = false;
        this.i = new HashSet();
        this.j = new ArrayList<>();
        C0055f c0055f = new C0055f();
        this.k = c0055f;
        this.u = 255;
        this.x = true;
        this.y = false;
        eVar.addUpdateListener(c0055f);
    }

    private void d() {
        this.t = new com.airbnb.lottie.model.layer.b(this, s.a(this.f2461d), this.f2461d.j(), this.f2461d);
    }

    private void g(Canvas canvas) {
        if (ImageView.ScaleType.FIT_XY == this.l) {
            h(canvas);
        } else {
            i(canvas);
        }
    }

    private void h(Canvas canvas) {
        float f;
        if (this.t == null) {
            return;
        }
        int iSave = -1;
        Rect bounds = getBounds();
        float fWidth = bounds.width() / this.f2461d.b().width();
        float fHeight = bounds.height() / this.f2461d.b().height();
        if (this.x) {
            float fMin = Math.min(fWidth, fHeight);
            if (fMin < 1.0f) {
                f = 1.0f / fMin;
                fWidth /= f;
                fHeight /= f;
            } else {
                f = 1.0f;
            }
            if (f > 1.0f) {
                iSave = canvas.save();
                float fWidth2 = bounds.width() / 2.0f;
                float fHeight2 = bounds.height() / 2.0f;
                float f2 = fWidth2 * fMin;
                float f3 = fMin * fHeight2;
                canvas.translate(fWidth2 - f2, fHeight2 - f3);
                canvas.scale(f, f, f2, f3);
            }
        }
        this.f2460c.reset();
        this.f2460c.preScale(fWidth, fHeight);
        this.t.g(canvas, this.f2460c, this.u);
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    private void i(Canvas canvas) {
        float f;
        if (this.t == null) {
            return;
        }
        float f2 = this.f;
        float fU = u(canvas);
        if (f2 > fU) {
            f = this.f / fU;
        } else {
            fU = f2;
            f = 1.0f;
        }
        int iSave = -1;
        if (f > 1.0f) {
            iSave = canvas.save();
            float fWidth = this.f2461d.b().width() / 2.0f;
            float fHeight = this.f2461d.b().height() / 2.0f;
            float f3 = fWidth * fU;
            float f4 = fHeight * fU;
            canvas.translate((A() * fWidth) - f3, (A() * fHeight) - f4);
            canvas.scale(f, f, f3, f4);
        }
        this.f2460c.reset();
        this.f2460c.preScale(fU, fU);
        this.t.g(canvas, this.f2460c, this.u);
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    private void i0() {
        if (this.f2461d == null) {
            return;
        }
        float fA = A();
        setBounds(0, 0, (int) (this.f2461d.b().width() * fA), (int) (this.f2461d.b().height() * fA));
    }

    private Context n() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    private com.airbnb.lottie.s.a o() {
        if (getCallback() == null) {
            return null;
        }
        if (this.p == null) {
            this.p = new com.airbnb.lottie.s.a(getCallback(), this.q);
        }
        return this.p;
    }

    private com.airbnb.lottie.s.b r() {
        if (getCallback() == null) {
            return null;
        }
        com.airbnb.lottie.s.b bVar = this.m;
        if (bVar != null && !bVar.b(n())) {
            this.m = null;
        }
        if (this.m == null) {
            this.m = new com.airbnb.lottie.s.b(getCallback(), this.n, this.o, this.f2461d.i());
        }
        return this.m;
    }

    private float u(Canvas canvas) {
        return Math.min(canvas.getWidth() / this.f2461d.b().width(), canvas.getHeight() / this.f2461d.b().height());
    }

    public float A() {
        return this.f;
    }

    public float B() {
        return this.f2462e.l();
    }

    public q C() {
        return this.r;
    }

    public Typeface D(String str, String str2) {
        com.airbnb.lottie.s.a aVarO = o();
        if (aVarO != null) {
            return aVarO.b(str, str2);
        }
        return null;
    }

    public boolean E() {
        com.airbnb.lottie.u.e eVar = this.f2462e;
        if (eVar == null) {
            return false;
        }
        return eVar.isRunning();
    }

    public boolean F() {
        return this.w;
    }

    public void G() {
        this.j.clear();
        this.f2462e.n();
    }

    public void H() {
        if (this.t == null) {
            this.j.add(new g());
            return;
        }
        if (this.g || y() == 0) {
            this.f2462e.o();
        }
        if (this.g) {
            return;
        }
        N((int) (B() < 0.0f ? v() : t()));
        this.f2462e.endAnimation();
    }

    public List<com.airbnb.lottie.model.d> I(com.airbnb.lottie.model.d dVar) {
        if (this.t == null) {
            com.airbnb.lottie.u.d.c("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.t.e(dVar, 0, arrayList, new com.airbnb.lottie.model.d(new String[0]));
        return arrayList;
    }

    public void J() {
        if (this.t == null) {
            this.j.add(new h());
            return;
        }
        if (this.g || y() == 0) {
            this.f2462e.s();
        }
        if (this.g) {
            return;
        }
        N((int) (B() < 0.0f ? v() : t()));
        this.f2462e.endAnimation();
    }

    public void K(boolean z) {
        this.w = z;
    }

    public boolean L(com.airbnb.lottie.d dVar) {
        if (this.f2461d == dVar) {
            return false;
        }
        this.y = false;
        f();
        this.f2461d = dVar;
        d();
        this.f2462e.u(dVar);
        Z(this.f2462e.getAnimatedFraction());
        d0(this.f);
        i0();
        Iterator it = new ArrayList(this.j).iterator();
        while (it.hasNext()) {
            ((o) it.next()).a(dVar);
            it.remove();
        }
        this.j.clear();
        dVar.u(this.v);
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof ImageView)) {
            return true;
        }
        ImageView imageView = (ImageView) callback;
        imageView.setImageDrawable(null);
        imageView.setImageDrawable(this);
        return true;
    }

    public void M(com.airbnb.lottie.a aVar) {
        com.airbnb.lottie.s.a aVar2 = this.p;
        if (aVar2 != null) {
            aVar2.c(aVar);
        }
    }

    public void N(int i2) {
        if (this.f2461d == null) {
            this.j.add(new c(i2));
        } else {
            this.f2462e.v(i2);
        }
    }

    public void O(com.airbnb.lottie.b bVar) {
        this.o = bVar;
        com.airbnb.lottie.s.b bVar2 = this.m;
        if (bVar2 != null) {
            bVar2.d(bVar);
        }
    }

    public void P(String str) {
        this.n = str;
    }

    public void Q(int i2) {
        if (this.f2461d == null) {
            this.j.add(new k(i2));
        } else {
            this.f2462e.w(i2 + 0.99f);
        }
    }

    public void R(String str) {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar == null) {
            this.j.add(new n(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            Q((int) (gVarK.f2607c + gVarK.f2608d));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void S(float f) {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar == null) {
            this.j.add(new l(f));
        } else {
            Q((int) com.airbnb.lottie.u.g.j(dVar.o(), this.f2461d.f(), f));
        }
    }

    public void T(int i2, int i3) {
        if (this.f2461d == null) {
            this.j.add(new b(i2, i3));
        } else {
            this.f2462e.x(i2, i3 + 0.99f);
        }
    }

    public void U(String str) {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar == null) {
            this.j.add(new a(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            int i2 = (int) gVarK.f2607c;
            T(i2, ((int) gVarK.f2608d) + i2);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public void V(int i2) {
        if (this.f2461d == null) {
            this.j.add(new i(i2));
        } else {
            this.f2462e.y(i2);
        }
    }

    public void W(String str) {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar == null) {
            this.j.add(new m(str));
            return;
        }
        com.airbnb.lottie.model.g gVarK = dVar.k(str);
        if (gVarK != null) {
            V((int) gVarK.f2607c);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void X(float f) {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar == null) {
            this.j.add(new j(f));
        } else {
            V((int) com.airbnb.lottie.u.g.j(dVar.o(), this.f2461d.f(), f));
        }
    }

    public void Y(boolean z) {
        this.v = z;
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar != null) {
            dVar.u(z);
        }
    }

    public void Z(float f) {
        if (this.f2461d == null) {
            this.j.add(new d(f));
            return;
        }
        com.airbnb.lottie.c.a("Drawable#setProgress");
        this.f2462e.v(com.airbnb.lottie.u.g.j(this.f2461d.o(), this.f2461d.f(), f));
        com.airbnb.lottie.c.b("Drawable#setProgress");
    }

    public void a0(int i2) {
        this.f2462e.setRepeatCount(i2);
    }

    public void b0(int i2) {
        this.f2462e.setRepeatMode(i2);
    }

    public <T> void c(com.airbnb.lottie.model.d dVar, T t, com.airbnb.lottie.v.c<T> cVar) {
        if (this.t == null) {
            this.j.add(new e(dVar, t, cVar));
            return;
        }
        boolean zIsEmpty = true;
        if (dVar.d() != null) {
            dVar.d().f(t, cVar);
        } else {
            List<com.airbnb.lottie.model.d> listI = I(dVar);
            for (int i2 = 0; i2 < listI.size(); i2++) {
                listI.get(i2).d().f(t, cVar);
            }
            zIsEmpty = true ^ listI.isEmpty();
        }
        if (zIsEmpty) {
            invalidateSelf();
            if (t == com.airbnb.lottie.k.A) {
                Z(x());
            }
        }
    }

    public void c0(boolean z) {
        this.h = z;
    }

    public void d0(float f) {
        this.f = f;
        i0();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.y = false;
        com.airbnb.lottie.c.a("Drawable#draw");
        if (this.h) {
            try {
                g(canvas);
            } catch (Throwable th) {
                com.airbnb.lottie.u.d.b("Lottie crashed in draw!", th);
            }
        } else {
            g(canvas);
        }
        com.airbnb.lottie.c.b("Drawable#draw");
    }

    public void e() {
        this.j.clear();
        this.f2462e.cancel();
    }

    void e0(ImageView.ScaleType scaleType) {
        this.l = scaleType;
    }

    public void f() {
        if (this.f2462e.isRunning()) {
            this.f2462e.cancel();
        }
        this.f2461d = null;
        this.t = null;
        this.m = null;
        this.f2462e.f();
        invalidateSelf();
    }

    public void f0(float f) {
        this.f2462e.z(f);
    }

    void g0(Boolean bool) {
        this.g = bool.booleanValue();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.u;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f2461d == null) {
            return -1;
        }
        return (int) (r0.b().height() * A());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f2461d == null) {
            return -1;
        }
        return (int) (r0.b().width() * A());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void h0(q qVar) {
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
        if (this.y) {
            return;
        }
        this.y = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return E();
    }

    public void j(boolean z) {
        if (this.s == z) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            com.airbnb.lottie.u.d.c("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.s = z;
        if (this.f2461d != null) {
            d();
        }
    }

    public boolean j0() {
        return this.r == null && this.f2461d.c().l() > 0;
    }

    public boolean k() {
        return this.s;
    }

    public void l() {
        this.j.clear();
        this.f2462e.endAnimation();
    }

    public com.airbnb.lottie.d m() {
        return this.f2461d;
    }

    public int p() {
        return (int) this.f2462e.h();
    }

    public Bitmap q(String str) {
        com.airbnb.lottie.s.b bVarR = r();
        if (bVarR != null) {
            return bVarR.a(str);
        }
        return null;
    }

    public String s() {
        return this.n;
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
        this.u = i2;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        com.airbnb.lottie.u.d.c("Use addColorFilter instead.");
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        H();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        l();
    }

    public float t() {
        return this.f2462e.j();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.unscheduleDrawable(this, runnable);
    }

    public float v() {
        return this.f2462e.k();
    }

    public com.airbnb.lottie.n w() {
        com.airbnb.lottie.d dVar = this.f2461d;
        if (dVar != null) {
            return dVar.m();
        }
        return null;
    }

    public float x() {
        return this.f2462e.g();
    }

    public int y() {
        return this.f2462e.getRepeatCount();
    }

    public int z() {
        return this.f2462e.getRepeatMode();
    }
}
