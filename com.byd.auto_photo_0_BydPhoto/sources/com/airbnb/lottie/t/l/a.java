package com.airbnb.lottie.t.l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.r.c.o;
import com.airbnb.lottie.t.k.g;
import com.airbnb.lottie.t.k.l;
import com.airbnb.lottie.t.l.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements com.airbnb.lottie.r.b.e, a.InterfaceC0025a, com.airbnb.lottie.t.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f880a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix f881b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Paint f882c = new com.airbnb.lottie.r.a(1);
    private final Paint d = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_IN);
    private final Paint e = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_OUT);
    private final Paint f;
    private final Paint g;
    private final RectF h;
    private final RectF i;
    private final RectF j;
    private final RectF k;
    private final String l;
    final Matrix m;
    final com.airbnb.lottie.f n;
    final d o;

    @Nullable
    private com.airbnb.lottie.r.c.g p;

    @Nullable
    private a q;

    @Nullable
    private a r;
    private List<a> s;
    private final List<com.airbnb.lottie.r.c.a<?, ?>> t;
    final o u;
    private boolean v;

    /* JADX INFO: renamed from: com.airbnb.lottie.t.l.a$a, reason: collision with other inner class name */
    class C0026a implements a.InterfaceC0025a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.airbnb.lottie.r.c.c f883a;

        C0026a(com.airbnb.lottie.r.c.c cVar) {
            this.f883a = cVar;
        }

        @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
        public void b() {
            a.this.F(this.f883a.n() == 1.0f);
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f885a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f886b;

        static {
            int[] iArr = new int[g.a.values().length];
            f886b = iArr;
            try {
                iArr[g.a.MASK_MODE_SUBTRACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f886b[g.a.MASK_MODE_INTERSECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f886b[g.a.MASK_MODE_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[d.a.values().length];
            f885a = iArr2;
            try {
                iArr2[d.a.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f885a[d.a.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f885a[d.a.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f885a[d.a.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f885a[d.a.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f885a[d.a.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f885a[d.a.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    a(com.airbnb.lottie.f fVar, d dVar) {
        com.airbnb.lottie.r.a aVar = new com.airbnb.lottie.r.a(1);
        this.f = aVar;
        this.g = new com.airbnb.lottie.r.a(PorterDuff.Mode.CLEAR);
        this.h = new RectF();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new RectF();
        this.m = new Matrix();
        this.t = new ArrayList();
        this.v = true;
        this.n = fVar;
        this.o = dVar;
        this.l = dVar.g() + "#draw";
        aVar.setXfermode(dVar.f() == d.b.INVERT ? new PorterDuffXfermode(PorterDuff.Mode.DST_OUT) : new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        o oVarB = dVar.u().b();
        this.u = oVarB;
        oVarB.b(this);
        if (dVar.e() != null && !dVar.e().isEmpty()) {
            com.airbnb.lottie.r.c.g gVar = new com.airbnb.lottie.r.c.g(dVar.e());
            this.p = gVar;
            Iterator<com.airbnb.lottie.r.c.a<l, Path>> it = gVar.a().iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
            for (com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 : this.p.c()) {
                h(aVar2);
                aVar2.a(this);
            }
        }
        G();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(boolean z) {
        if (z != this.v) {
            this.v = z;
            y();
        }
    }

    private void G() {
        if (this.o.c().isEmpty()) {
            F(true);
            return;
        }
        com.airbnb.lottie.r.c.c cVar = new com.airbnb.lottie.r.c.c(this.o.c());
        cVar.k();
        cVar.a(new C0026a(cVar));
        F(cVar.h().floatValue() == 1.0f);
        h(cVar);
    }

    private void i(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        this.f882c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f880a, this.f882c);
    }

    private void j(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.w.h.m(canvas, this.h, this.d);
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        this.f882c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f880a, this.f882c);
        canvas.restore();
    }

    private void k(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.w.h.m(canvas, this.h, this.f882c);
        canvas.drawRect(this.h, this.f882c);
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        this.f882c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f880a, this.e);
        canvas.restore();
    }

    private void l(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.w.h.m(canvas, this.h, this.d);
        canvas.drawRect(this.h, this.f882c);
        this.e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        canvas.drawPath(this.f880a, this.e);
        canvas.restore();
    }

    private void m(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.w.h.m(canvas, this.h, this.e);
        canvas.drawRect(this.h, this.f882c);
        this.e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        canvas.drawPath(this.f880a, this.e);
        canvas.restore();
    }

    private void n(Canvas canvas, Matrix matrix) {
        com.airbnb.lottie.c.a("Layer#saveLayer");
        com.airbnb.lottie.w.h.n(canvas, this.h, this.d, 19);
        if (Build.VERSION.SDK_INT < 28) {
            canvas.drawColor(0);
        }
        com.airbnb.lottie.c.b("Layer#saveLayer");
        for (int i = 0; i < this.p.b().size(); i++) {
            com.airbnb.lottie.t.k.g gVar = this.p.b().get(i);
            com.airbnb.lottie.r.c.a<l, Path> aVar = this.p.a().get(i);
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.p.c().get(i);
            int i2 = b.f886b[gVar.a().ordinal()];
            if (i2 == 1) {
                if (i == 0) {
                    this.f882c.setColor(ViewCompat.MEASURED_STATE_MASK);
                    this.f882c.setAlpha(255);
                    canvas.drawRect(this.h, this.f882c);
                }
                if (gVar.d()) {
                    m(canvas, matrix, gVar, aVar, aVar2);
                } else {
                    o(canvas, matrix, gVar, aVar, aVar2);
                }
            } else if (i2 != 2) {
                if (i2 == 3) {
                    if (gVar.d()) {
                        k(canvas, matrix, gVar, aVar, aVar2);
                    } else {
                        i(canvas, matrix, gVar, aVar, aVar2);
                    }
                }
            } else if (gVar.d()) {
                l(canvas, matrix, gVar, aVar, aVar2);
            } else {
                j(canvas, matrix, gVar, aVar, aVar2);
            }
        }
        com.airbnb.lottie.c.a("Layer#restoreLayer");
        canvas.restore();
        com.airbnb.lottie.c.b("Layer#restoreLayer");
    }

    private void o(Canvas canvas, Matrix matrix, com.airbnb.lottie.t.k.g gVar, com.airbnb.lottie.r.c.a<l, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f880a.set(aVar.h());
        this.f880a.transform(matrix);
        canvas.drawPath(this.f880a, this.e);
    }

    private void p() {
        if (this.s != null) {
            return;
        }
        if (this.r == null) {
            this.s = Collections.emptyList();
            return;
        }
        this.s = new ArrayList();
        for (a aVar = this.r; aVar != null; aVar = aVar.r) {
            this.s.add(aVar);
        }
    }

    private void q(Canvas canvas) {
        com.airbnb.lottie.c.a("Layer#clearLayer");
        RectF rectF = this.h;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.g);
        com.airbnb.lottie.c.b("Layer#clearLayer");
    }

    @Nullable
    static a s(d dVar, com.airbnb.lottie.f fVar, com.airbnb.lottie.d dVar2) {
        switch (b.f885a[dVar.d().ordinal()]) {
            case 1:
                return new f(fVar, dVar);
            case 2:
                return new com.airbnb.lottie.t.l.b(fVar, dVar, dVar2.n(dVar.k()), dVar2);
            case 3:
                return new g(fVar, dVar);
            case 4:
                return new c(fVar, dVar);
            case 5:
                return new e(fVar, dVar);
            case 6:
                return new h(fVar, dVar);
            default:
                com.airbnb.lottie.w.d.b("Unknown layer type " + dVar.d());
                return null;
        }
    }

    private void w(RectF rectF, Matrix matrix) {
        this.i.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (u()) {
            int size = this.p.b().size();
            for (int i = 0; i < size; i++) {
                com.airbnb.lottie.t.k.g gVar = this.p.b().get(i);
                this.f880a.set(this.p.a().get(i).h());
                this.f880a.transform(matrix);
                int i2 = b.f886b[gVar.a().ordinal()];
                if (i2 == 1) {
                    return;
                }
                if ((i2 == 2 || i2 == 3) && gVar.d()) {
                    return;
                }
                this.f880a.computeBounds(this.k, false);
                RectF rectF2 = this.i;
                if (i == 0) {
                    rectF2.set(this.k);
                } else {
                    rectF2.set(Math.min(rectF2.left, this.k.left), Math.min(this.i.top, this.k.top), Math.max(this.i.right, this.k.right), Math.max(this.i.bottom, this.k.bottom));
                }
            }
            if (rectF.intersect(this.i)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void x(RectF rectF, Matrix matrix) {
        if (v() && this.o.f() != d.b.INVERT) {
            this.j.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.q.a(this.j, matrix, true);
            if (rectF.intersect(this.j)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void y() {
        this.n.invalidateSelf();
    }

    private void z(float f) {
        this.n.j().m().a(this.o.g(), f);
    }

    public void A(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.t.remove(aVar);
    }

    void B(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
    }

    void C(@Nullable a aVar) {
        this.q = aVar;
    }

    void D(@Nullable a aVar) {
        this.r = aVar;
    }

    void E(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.u.j(f);
        if (this.p != null) {
            for (int i = 0; i < this.p.a().size(); i++) {
                this.p.a().get(i).l(f);
            }
        }
        if (this.o.t() != 0.0f) {
            f /= this.o.t();
        }
        a aVar = this.q;
        if (aVar != null) {
            this.q.E(aVar.o.t() * f);
        }
        for (int i2 = 0; i2 < this.t.size(); i2++) {
            this.t.get(i2).l(f);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    @CallSuper
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        p();
        this.m.set(matrix);
        if (z) {
            List<a> list = this.s;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.m.preConcat(this.s.get(size).u.f());
                }
            } else {
                a aVar = this.r;
                if (aVar != null) {
                    this.m.preConcat(aVar.u.f());
                }
            }
        }
        this.m.preConcat(this.u.f());
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        y();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<com.airbnb.lottie.r.b.c> list, List<com.airbnb.lottie.r.b.c> list2) {
    }

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        if (eVar.g(getName(), i)) {
            if (!"__container".equals(getName())) {
                eVar2 = eVar2.a(getName());
                if (eVar.c(getName(), i)) {
                    list.add(eVar2.i(this));
                }
            }
            if (eVar.h(getName(), i)) {
                B(eVar, i + eVar.e(getName(), i), list, eVar2);
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a(this.l);
        if (!this.v || this.o.v()) {
            com.airbnb.lottie.c.b(this.l);
            return;
        }
        p();
        com.airbnb.lottie.c.a("Layer#parentMatrix");
        this.f881b.reset();
        this.f881b.set(matrix);
        for (int size = this.s.size() - 1; size >= 0; size--) {
            this.f881b.preConcat(this.s.get(size).u.f());
        }
        com.airbnb.lottie.c.b("Layer#parentMatrix");
        int iIntValue = (int) ((((i / 255.0f) * (this.u.h() == null ? 100 : this.u.h().h().intValue())) / 100.0f) * 255.0f);
        if (!v() && !u()) {
            this.f881b.preConcat(this.u.f());
            com.airbnb.lottie.c.a("Layer#drawLayer");
            r(canvas, this.f881b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            z(com.airbnb.lottie.c.b(this.l));
            return;
        }
        com.airbnb.lottie.c.a("Layer#computeBounds");
        a(this.h, this.f881b, false);
        x(this.h, matrix);
        this.f881b.preConcat(this.u.f());
        w(this.h, this.f881b);
        if (!this.h.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
            this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        com.airbnb.lottie.c.b("Layer#computeBounds");
        if (!this.h.isEmpty()) {
            com.airbnb.lottie.c.a("Layer#saveLayer");
            com.airbnb.lottie.w.h.m(canvas, this.h, this.f882c);
            com.airbnb.lottie.c.b("Layer#saveLayer");
            q(canvas);
            com.airbnb.lottie.c.a("Layer#drawLayer");
            r(canvas, this.f881b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            if (u()) {
                n(canvas, this.f881b);
            }
            if (v()) {
                com.airbnb.lottie.c.a("Layer#drawMatte");
                com.airbnb.lottie.c.a("Layer#saveLayer");
                com.airbnb.lottie.w.h.n(canvas, this.h, this.f, 19);
                com.airbnb.lottie.c.b("Layer#saveLayer");
                q(canvas);
                this.q.f(canvas, matrix, iIntValue);
                com.airbnb.lottie.c.a("Layer#restoreLayer");
                canvas.restore();
                com.airbnb.lottie.c.b("Layer#restoreLayer");
                com.airbnb.lottie.c.b("Layer#drawMatte");
            }
            com.airbnb.lottie.c.a("Layer#restoreLayer");
            canvas.restore();
            com.airbnb.lottie.c.b("Layer#restoreLayer");
        }
        z(com.airbnb.lottie.c.b(this.l));
    }

    @Override // com.airbnb.lottie.t.f
    @CallSuper
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        this.u.c(t, cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.o.g();
    }

    public void h(@Nullable com.airbnb.lottie.r.c.a<?, ?> aVar) {
        if (aVar == null) {
            return;
        }
        this.t.add(aVar);
    }

    abstract void r(Canvas canvas, Matrix matrix, int i);

    d t() {
        return this.o;
    }

    boolean u() {
        com.airbnb.lottie.r.c.g gVar = this.p;
        return (gVar == null || gVar.a().isEmpty()) ? false : true;
    }

    boolean v() {
        return this.q != null;
    }
}
