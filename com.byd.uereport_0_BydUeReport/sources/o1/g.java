package o1;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import y1.s;

/* JADX INFO: compiled from: LottieDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable implements Drawable.Callback, Animatable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f6771d = g.class.getSimpleName();
    public boolean A;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Matrix f6772e = new Matrix();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public o1.e f6773f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a2.e f6774g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f6775h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6776i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6777j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Set<?> f6778k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList<o> f6779l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ValueAnimator.AnimatorUpdateListener f6780m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ImageView.ScaleType f6781n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public s1.b f6782o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f6783p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public o1.c f6784q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public s1.a f6785r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public o1.b f6786s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public r f6787t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6788u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public w1.b f6789v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f6790w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f6791x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f6792y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f6793z;

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6794a;

        public a(String str) {
            this.f6794a = str;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.U(this.f6794a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6796a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f6797b;

        public b(int i8, int i9) {
            this.f6796a = i8;
            this.f6797b = i9;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.T(this.f6796a, this.f6797b);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class c implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6799a;

        public c(int i8) {
            this.f6799a = i8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.N(this.f6799a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class d implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f6801a;

        public d(float f8) {
            this.f6801a = f8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.Z(this.f6801a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class e implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ t1.d f6803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f6804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b2.c f6805c;

        public e(t1.d dVar, Object obj, b2.c cVar) {
            this.f6803a = dVar;
            this.f6804b = obj;
            this.f6805c = cVar;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.c(this.f6803a, this.f6804b, this.f6805c);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class f implements ValueAnimator.AnimatorUpdateListener {
        public f() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (g.this.f6789v != null) {
                g.this.f6789v.I(g.this.f6774g.g());
            }
        }
    }

    /* JADX INFO: renamed from: o1.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LottieDrawable.java */
    public class C0100g implements o {
        public C0100g() {
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.H();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class h implements o {
        public h() {
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.J();
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class i implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6810a;

        public i(int i8) {
            this.f6810a = i8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.V(this.f6810a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class j implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f6812a;

        public j(float f8) {
            this.f6812a = f8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.X(this.f6812a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class k implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f6814a;

        public k(int i8) {
            this.f6814a = i8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.Q(this.f6814a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class l implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ float f6816a;

        public l(float f8) {
            this.f6816a = f8;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.S(this.f6816a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class m implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6818a;

        public m(String str) {
            this.f6818a = str;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.W(this.f6818a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public class n implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f6820a;

        public n(String str) {
            this.f6820a = str;
        }

        @Override // o1.g.o
        public void a(o1.e eVar) {
            g.this.R(this.f6820a);
        }
    }

    /* JADX INFO: compiled from: LottieDrawable.java */
    public interface o {
        void a(o1.e eVar);
    }

    public g() {
        a2.e eVar = new a2.e();
        this.f6774g = eVar;
        this.f6775h = 1.0f;
        this.f6776i = true;
        this.f6777j = false;
        this.f6778k = new HashSet();
        this.f6779l = new ArrayList<>();
        f fVar = new f();
        this.f6780m = fVar;
        this.f6790w = 255;
        this.f6793z = true;
        this.A = false;
        eVar.addUpdateListener(fVar);
    }

    public float A() {
        return this.f6775h;
    }

    public float B() {
        return this.f6774g.l();
    }

    public r C() {
        return this.f6787t;
    }

    public Typeface D(String str, String str2) {
        s1.a aVarO = o();
        if (aVarO != null) {
            return aVarO.b(str, str2);
        }
        return null;
    }

    public boolean E() {
        a2.e eVar = this.f6774g;
        if (eVar == null) {
            return false;
        }
        return eVar.isRunning();
    }

    public boolean F() {
        return this.f6792y;
    }

    public void G() {
        this.f6779l.clear();
        this.f6774g.n();
    }

    public void H() {
        if (this.f6789v == null) {
            this.f6779l.add(new C0100g());
            return;
        }
        if (this.f6776i || y() == 0) {
            this.f6774g.o();
        }
        if (this.f6776i) {
            return;
        }
        N((int) (B() < 0.0f ? v() : t()));
        this.f6774g.endAnimation();
    }

    public List<t1.d> I(t1.d dVar) {
        if (this.f6789v == null) {
            a2.d.c("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.f6789v.f(dVar, 0, arrayList, new t1.d(new String[0]));
        return arrayList;
    }

    public void J() {
        if (this.f6789v == null) {
            this.f6779l.add(new h());
            return;
        }
        if (this.f6776i || y() == 0) {
            this.f6774g.s();
        }
        if (this.f6776i) {
            return;
        }
        N((int) (B() < 0.0f ? v() : t()));
        this.f6774g.endAnimation();
    }

    public void K(boolean z7) {
        this.f6792y = z7;
    }

    public boolean L(o1.e eVar) {
        if (this.f6773f == eVar) {
            return false;
        }
        this.A = false;
        f();
        this.f6773f = eVar;
        d();
        this.f6774g.u(eVar);
        Z(this.f6774g.getAnimatedFraction());
        d0(this.f6775h);
        i0();
        Iterator it = new ArrayList(this.f6779l).iterator();
        while (it.hasNext()) {
            ((o) it.next()).a(eVar);
            it.remove();
        }
        this.f6779l.clear();
        eVar.u(this.f6791x);
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof ImageView)) {
            return true;
        }
        ImageView imageView = (ImageView) callback;
        imageView.setImageDrawable(null);
        imageView.setImageDrawable(this);
        return true;
    }

    public void M(o1.b bVar) {
        s1.a aVar = this.f6785r;
        if (aVar != null) {
            aVar.c(bVar);
        }
    }

    public void N(int i8) {
        if (this.f6773f == null) {
            this.f6779l.add(new c(i8));
        } else {
            this.f6774g.v(i8);
        }
    }

    public void O(o1.c cVar) {
        this.f6784q = cVar;
        s1.b bVar = this.f6782o;
        if (bVar != null) {
            bVar.d(cVar);
        }
    }

    public void P(String str) {
        this.f6783p = str;
    }

    public void Q(int i8) {
        if (this.f6773f == null) {
            this.f6779l.add(new k(i8));
        } else {
            this.f6774g.w(i8 + 0.99f);
        }
    }

    public void R(String str) {
        o1.e eVar = this.f6773f;
        if (eVar == null) {
            this.f6779l.add(new n(str));
            return;
        }
        t1.g gVarK = eVar.k(str);
        if (gVarK != null) {
            Q((int) (gVarK.f8014c + gVarK.f8015d));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void S(float f8) {
        o1.e eVar = this.f6773f;
        if (eVar == null) {
            this.f6779l.add(new l(f8));
        } else {
            Q((int) a2.g.j(eVar.o(), this.f6773f.f(), f8));
        }
    }

    public void T(int i8, int i9) {
        if (this.f6773f == null) {
            this.f6779l.add(new b(i8, i9));
        } else {
            this.f6774g.x(i8, i9 + 0.99f);
        }
    }

    public void U(String str) {
        o1.e eVar = this.f6773f;
        if (eVar == null) {
            this.f6779l.add(new a(str));
            return;
        }
        t1.g gVarK = eVar.k(str);
        if (gVarK != null) {
            int i8 = (int) gVarK.f8014c;
            T(i8, ((int) gVarK.f8015d) + i8);
        } else {
            throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
        }
    }

    public void V(int i8) {
        if (this.f6773f == null) {
            this.f6779l.add(new i(i8));
        } else {
            this.f6774g.y(i8);
        }
    }

    public void W(String str) {
        o1.e eVar = this.f6773f;
        if (eVar == null) {
            this.f6779l.add(new m(str));
            return;
        }
        t1.g gVarK = eVar.k(str);
        if (gVarK != null) {
            V((int) gVarK.f8014c);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + str + ".");
    }

    public void X(float f8) {
        o1.e eVar = this.f6773f;
        if (eVar == null) {
            this.f6779l.add(new j(f8));
        } else {
            V((int) a2.g.j(eVar.o(), this.f6773f.f(), f8));
        }
    }

    public void Y(boolean z7) {
        this.f6791x = z7;
        o1.e eVar = this.f6773f;
        if (eVar != null) {
            eVar.u(z7);
        }
    }

    public void Z(float f8) {
        if (this.f6773f == null) {
            this.f6779l.add(new d(f8));
            return;
        }
        o1.d.a("Drawable#setProgress");
        this.f6774g.v(a2.g.j(this.f6773f.o(), this.f6773f.f(), f8));
        o1.d.b("Drawable#setProgress");
    }

    public void a0(int i8) {
        this.f6774g.setRepeatCount(i8);
    }

    public void b0(int i8) {
        this.f6774g.setRepeatMode(i8);
    }

    public <T> void c(t1.d dVar, T t7, b2.c<T> cVar) {
        if (this.f6789v == null) {
            this.f6779l.add(new e(dVar, t7, cVar));
            return;
        }
        boolean zIsEmpty = true;
        if (dVar.d() != null) {
            dVar.d().i(t7, cVar);
        } else {
            List<t1.d> listI = I(dVar);
            for (int i8 = 0; i8 < listI.size(); i8++) {
                listI.get(i8).d().i(t7, cVar);
            }
            zIsEmpty = true ^ listI.isEmpty();
        }
        if (zIsEmpty) {
            invalidateSelf();
            if (t7 == o1.l.A) {
                Z(x());
            }
        }
    }

    public void c0(boolean z7) {
        this.f6777j = z7;
    }

    public final void d() {
        this.f6789v = new w1.b(this, s.a(this.f6773f), this.f6773f.j(), this.f6773f);
    }

    public void d0(float f8) {
        this.f6775h = f8;
        i0();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        this.A = false;
        o1.d.a("Drawable#draw");
        if (this.f6777j) {
            try {
                g(canvas);
            } catch (Throwable th) {
                a2.d.b("Lottie crashed in draw!", th);
            }
        } else {
            g(canvas);
        }
        o1.d.b("Drawable#draw");
    }

    public void e() {
        this.f6779l.clear();
        this.f6774g.cancel();
    }

    public void e0(ImageView.ScaleType scaleType) {
        this.f6781n = scaleType;
    }

    public void f() {
        if (this.f6774g.isRunning()) {
            this.f6774g.cancel();
        }
        this.f6773f = null;
        this.f6789v = null;
        this.f6782o = null;
        this.f6774g.f();
        invalidateSelf();
    }

    public void f0(float f8) {
        this.f6774g.z(f8);
    }

    public final void g(Canvas canvas) {
        if (ImageView.ScaleType.FIT_XY == this.f6781n) {
            h(canvas);
        } else {
            i(canvas);
        }
    }

    public void g0(Boolean bool) {
        this.f6776i = bool.booleanValue();
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f6790w;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f6773f == null) {
            return -1;
        }
        return (int) (r0.b().height() * A());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f6773f == null) {
            return -1;
        }
        return (int) (r0.b().width() * A());
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final void h(Canvas canvas) {
        float f8;
        if (this.f6789v == null) {
            return;
        }
        int iSave = -1;
        Rect bounds = getBounds();
        float fWidth = bounds.width() / this.f6773f.b().width();
        float fHeight = bounds.height() / this.f6773f.b().height();
        if (this.f6793z) {
            float fMin = Math.min(fWidth, fHeight);
            if (fMin < 1.0f) {
                f8 = 1.0f / fMin;
                fWidth /= f8;
                fHeight /= f8;
            } else {
                f8 = 1.0f;
            }
            if (f8 > 1.0f) {
                iSave = canvas.save();
                float fWidth2 = bounds.width() / 2.0f;
                float fHeight2 = bounds.height() / 2.0f;
                float f9 = fWidth2 * fMin;
                float f10 = fMin * fHeight2;
                canvas.translate(fWidth2 - f9, fHeight2 - f10);
                canvas.scale(f8, f8, f9, f10);
            }
        }
        this.f6772e.reset();
        this.f6772e.preScale(fWidth, fHeight);
        this.f6789v.g(canvas, this.f6772e, this.f6790w);
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    public void h0(r rVar) {
    }

    public final void i(Canvas canvas) {
        float f8;
        if (this.f6789v == null) {
            return;
        }
        float f9 = this.f6775h;
        float fU = u(canvas);
        if (f9 > fU) {
            f8 = this.f6775h / fU;
        } else {
            fU = f9;
            f8 = 1.0f;
        }
        int iSave = -1;
        if (f8 > 1.0f) {
            iSave = canvas.save();
            float fWidth = this.f6773f.b().width() / 2.0f;
            float fHeight = this.f6773f.b().height() / 2.0f;
            float f10 = fWidth * fU;
            float f11 = fHeight * fU;
            canvas.translate((A() * fWidth) - f10, (A() * fHeight) - f11);
            canvas.scale(f8, f8, f10, f11);
        }
        this.f6772e.reset();
        this.f6772e.preScale(fU, fU);
        this.f6789v.g(canvas, this.f6772e, this.f6790w);
        if (iSave > 0) {
            canvas.restoreToCount(iSave);
        }
    }

    public final void i0() {
        if (this.f6773f == null) {
            return;
        }
        float fA = A();
        setBounds(0, 0, (int) (this.f6773f.b().width() * fA), (int) (this.f6773f.b().height() * fA));
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
        if (this.A) {
            return;
        }
        this.A = true;
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return E();
    }

    public void j(boolean z7) {
        if (this.f6788u == z7) {
            return;
        }
        if (Build.VERSION.SDK_INT < 19) {
            a2.d.c("Merge paths are not supported pre-Kit Kat.");
            return;
        }
        this.f6788u = z7;
        if (this.f6773f != null) {
            d();
        }
    }

    public boolean j0() {
        return this.f6787t == null && this.f6773f.c().l() > 0;
    }

    public boolean k() {
        return this.f6788u;
    }

    public void l() {
        this.f6779l.clear();
        this.f6774g.endAnimation();
    }

    public o1.e m() {
        return this.f6773f;
    }

    public final Context n() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public final s1.a o() {
        if (getCallback() == null) {
            return null;
        }
        if (this.f6785r == null) {
            this.f6785r = new s1.a(getCallback(), this.f6786s);
        }
        return this.f6785r;
    }

    public int p() {
        return (int) this.f6774g.h();
    }

    public Bitmap q(String str) {
        s1.b bVarR = r();
        if (bVarR != null) {
            return bVarR.a(str);
        }
        return null;
    }

    public final s1.b r() {
        if (getCallback() == null) {
            return null;
        }
        s1.b bVar = this.f6782o;
        if (bVar != null && !bVar.b(n())) {
            this.f6782o = null;
        }
        if (this.f6782o == null) {
            this.f6782o = new s1.b(getCallback(), this.f6783p, this.f6784q, this.f6773f.i());
        }
        return this.f6782o;
    }

    public String s() {
        return this.f6783p;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
        Drawable.Callback callback = getCallback();
        if (callback == null) {
            return;
        }
        callback.scheduleDrawable(this, runnable, j8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f6790w = i8;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        a2.d.c("Use addColorFilter instead.");
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
        return this.f6774g.j();
    }

    public final float u(Canvas canvas) {
        return Math.min(canvas.getWidth() / this.f6773f.b().width(), canvas.getHeight() / this.f6773f.b().height());
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
        return this.f6774g.k();
    }

    public p w() {
        o1.e eVar = this.f6773f;
        if (eVar != null) {
            return eVar.m();
        }
        return null;
    }

    public float x() {
        return this.f6774g.g();
    }

    public int y() {
        return this.f6774g.getRepeatCount();
    }

    public int z() {
        return this.f6774g.getRepeatMode();
    }
}
