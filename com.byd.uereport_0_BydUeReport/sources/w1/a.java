package w1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.layer.Layer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import r1.a;
import r1.o;
import v1.h;

/* JADX INFO: compiled from: BaseLayer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements q1.e, a.b, t1.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f8670a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f8671b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Paint f8672c = new p1.a(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Paint f8673d = new p1.a(1, PorterDuff.Mode.DST_IN);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f8674e = new p1.a(1, PorterDuff.Mode.DST_OUT);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f8675f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f8676g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f8677h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f8678i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final RectF f8679j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final RectF f8680k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f8681l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Matrix f8682m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final o1.g f8683n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Layer f8684o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r1.g f8685p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public r1.c f8686q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public a f8687r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a f8688s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List<a> f8689t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final List<r1.a<?, ?>> f8690u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final o f8691v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f8692w;

    /* JADX INFO: renamed from: w1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseLayer.java */
    public class C0136a implements a.b {
        public C0136a() {
        }

        @Override // r1.a.b
        public void c() {
            a aVar = a.this;
            aVar.J(aVar.f8686q.o() == 1.0f);
        }
    }

    /* JADX INFO: compiled from: BaseLayer.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8694a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f8695b;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            f8695b = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8695b[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8695b[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8695b[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            f8694a = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8694a[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8694a[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8694a[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8694a[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8694a[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8694a[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    public a(o1.g gVar, Layer layer) {
        p1.a aVar = new p1.a(1);
        this.f8675f = aVar;
        this.f8676g = new p1.a(PorterDuff.Mode.CLEAR);
        this.f8677h = new RectF();
        this.f8678i = new RectF();
        this.f8679j = new RectF();
        this.f8680k = new RectF();
        this.f8682m = new Matrix();
        this.f8690u = new ArrayList();
        this.f8692w = true;
        this.f8683n = gVar;
        this.f8684o = layer;
        this.f8681l = layer.g() + "#draw";
        if (layer.f() == Layer.MatteType.INVERT) {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        o oVarB = layer.u().b();
        this.f8691v = oVarB;
        oVarB.b(this);
        if (layer.e() != null && !layer.e().isEmpty()) {
            r1.g gVar2 = new r1.g(layer.e());
            this.f8685p = gVar2;
            Iterator<r1.a<h, Path>> it = gVar2.a().iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
            for (r1.a<Integer, Integer> aVar2 : this.f8685p.c()) {
                k(aVar2);
                aVar2.a(this);
            }
        }
        K();
    }

    public static a w(Layer layer, o1.g gVar, o1.e eVar) {
        switch (b.f8694a[layer.d().ordinal()]) {
            case 1:
                return new e(gVar, layer);
            case 2:
                return new w1.b(gVar, layer, eVar.n(layer.k()), eVar);
            case 3:
                return new f(gVar, layer);
            case 4:
                return new c(gVar, layer);
            case 5:
                return new d(gVar, layer);
            case 6:
                return new g(gVar, layer);
            default:
                a2.d.c("Unknown layer type " + layer.d());
                return null;
        }
    }

    public final void A(RectF rectF, Matrix matrix) {
        this.f8678i.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (y()) {
            int size = this.f8685p.b().size();
            for (int i8 = 0; i8 < size; i8++) {
                Mask mask = this.f8685p.b().get(i8);
                this.f8670a.set(this.f8685p.a().get(i8).h());
                this.f8670a.transform(matrix);
                int i9 = b.f8695b[mask.a().ordinal()];
                if (i9 == 1 || i9 == 2) {
                    return;
                }
                if ((i9 == 3 || i9 == 4) && mask.d()) {
                    return;
                }
                this.f8670a.computeBounds(this.f8680k, false);
                if (i8 == 0) {
                    this.f8678i.set(this.f8680k);
                } else {
                    RectF rectF2 = this.f8678i;
                    rectF2.set(Math.min(rectF2.left, this.f8680k.left), Math.min(this.f8678i.top, this.f8680k.top), Math.max(this.f8678i.right, this.f8680k.right), Math.max(this.f8678i.bottom, this.f8680k.bottom));
                }
            }
            if (rectF.intersect(this.f8678i)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void B(RectF rectF, Matrix matrix) {
        if (z() && this.f8684o.f() != Layer.MatteType.INVERT) {
            this.f8679j.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.f8687r.b(this.f8679j, matrix, true);
            if (rectF.intersect(this.f8679j)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public final void C() {
        this.f8683n.invalidateSelf();
    }

    public final void D(float f8) {
        this.f8683n.m().m().a(this.f8684o.g(), f8);
    }

    public void E(r1.a<?, ?> aVar) {
        this.f8690u.remove(aVar);
    }

    public void F(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
    }

    public void G(a aVar) {
        this.f8687r = aVar;
    }

    public void H(a aVar) {
        this.f8688s = aVar;
    }

    public void I(float f8) {
        this.f8691v.j(f8);
        if (this.f8685p != null) {
            for (int i8 = 0; i8 < this.f8685p.a().size(); i8++) {
                this.f8685p.a().get(i8).l(f8);
            }
        }
        if (this.f8684o.t() != 0.0f) {
            f8 /= this.f8684o.t();
        }
        r1.c cVar = this.f8686q;
        if (cVar != null) {
            cVar.l(f8 / this.f8684o.t());
        }
        a aVar = this.f8687r;
        if (aVar != null) {
            this.f8687r.I(aVar.f8684o.t() * f8);
        }
        for (int i9 = 0; i9 < this.f8690u.size(); i9++) {
            this.f8690u.get(i9).l(f8);
        }
    }

    public final void J(boolean z7) {
        if (z7 != this.f8692w) {
            this.f8692w = z7;
            C();
        }
    }

    public final void K() {
        if (this.f8684o.c().isEmpty()) {
            J(true);
            return;
        }
        r1.c cVar = new r1.c(this.f8684o.c());
        this.f8686q = cVar;
        cVar.k();
        this.f8686q.a(new C0136a());
        J(this.f8686q.h().floatValue() == 1.0f);
        k(this.f8686q);
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        this.f8677h.set(0.0f, 0.0f, 0.0f, 0.0f);
        t();
        this.f8682m.set(matrix);
        if (z7) {
            List<a> list = this.f8689t;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.f8682m.preConcat(this.f8689t.get(size).f8691v.f());
                }
            } else {
                a aVar = this.f8688s;
                if (aVar != null) {
                    this.f8682m.preConcat(aVar.f8691v.f());
                }
            }
        }
        this.f8682m.preConcat(this.f8691v.f());
    }

    @Override // r1.a.b
    public void c() {
        C();
    }

    @Override // q1.c
    public void d(List<q1.c> list, List<q1.c> list2) {
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        if (dVar.g(h(), i8)) {
            if (!"__container".equals(h())) {
                dVar2 = dVar2.a(h());
                if (dVar.c(h(), i8)) {
                    list.add(dVar2.i(this));
                }
            }
            if (dVar.h(h(), i8)) {
                F(dVar, i8 + dVar.e(h(), i8), list, dVar2);
            }
        }
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        o1.d.a(this.f8681l);
        if (!this.f8692w || this.f8684o.v()) {
            o1.d.b(this.f8681l);
            return;
        }
        t();
        o1.d.a("Layer#parentMatrix");
        this.f8671b.reset();
        this.f8671b.set(matrix);
        for (int size = this.f8689t.size() - 1; size >= 0; size--) {
            this.f8671b.preConcat(this.f8689t.get(size).f8691v.f());
        }
        o1.d.b("Layer#parentMatrix");
        int iIntValue = (int) ((((i8 / 255.0f) * (this.f8691v.h() == null ? 100 : this.f8691v.h().h().intValue())) / 100.0f) * 255.0f);
        if (!z() && !y()) {
            this.f8671b.preConcat(this.f8691v.f());
            o1.d.a("Layer#drawLayer");
            v(canvas, this.f8671b, iIntValue);
            o1.d.b("Layer#drawLayer");
            D(o1.d.b(this.f8681l));
            return;
        }
        o1.d.a("Layer#computeBounds");
        b(this.f8677h, this.f8671b, false);
        B(this.f8677h, matrix);
        this.f8671b.preConcat(this.f8691v.f());
        A(this.f8677h, this.f8671b);
        if (!this.f8677h.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
            this.f8677h.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        o1.d.b("Layer#computeBounds");
        if (!this.f8677h.isEmpty()) {
            o1.d.a("Layer#saveLayer");
            this.f8672c.setAlpha(255);
            a2.h.m(canvas, this.f8677h, this.f8672c);
            o1.d.b("Layer#saveLayer");
            u(canvas);
            o1.d.a("Layer#drawLayer");
            v(canvas, this.f8671b, iIntValue);
            o1.d.b("Layer#drawLayer");
            if (y()) {
                q(canvas, this.f8671b);
            }
            if (z()) {
                o1.d.a("Layer#drawMatte");
                o1.d.a("Layer#saveLayer");
                a2.h.n(canvas, this.f8677h, this.f8675f, 19);
                o1.d.b("Layer#saveLayer");
                u(canvas);
                this.f8687r.g(canvas, matrix, iIntValue);
                o1.d.a("Layer#restoreLayer");
                canvas.restore();
                o1.d.b("Layer#restoreLayer");
                o1.d.b("Layer#drawMatte");
            }
            o1.d.a("Layer#restoreLayer");
            canvas.restore();
            o1.d.b("Layer#restoreLayer");
        }
        D(o1.d.b(this.f8681l));
    }

    @Override // q1.c
    public String h() {
        return this.f8684o.g();
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        this.f8691v.c(t7, cVar);
    }

    public void k(r1.a<?, ?> aVar) {
        if (aVar == null) {
            return;
        }
        this.f8690u.add(aVar);
    }

    public final void l(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        this.f8672c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f8670a, this.f8672c);
    }

    public final void m(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        a2.h.m(canvas, this.f8677h, this.f8673d);
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        this.f8672c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f8670a, this.f8672c);
        canvas.restore();
    }

    public final void n(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        a2.h.m(canvas, this.f8677h, this.f8672c);
        canvas.drawRect(this.f8677h, this.f8672c);
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        this.f8672c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f8670a, this.f8674e);
        canvas.restore();
    }

    public final void o(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        a2.h.m(canvas, this.f8677h, this.f8673d);
        canvas.drawRect(this.f8677h, this.f8672c);
        this.f8674e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        canvas.drawPath(this.f8670a, this.f8674e);
        canvas.restore();
    }

    public final void p(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        a2.h.m(canvas, this.f8677h, this.f8674e);
        canvas.drawRect(this.f8677h, this.f8672c);
        this.f8674e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        canvas.drawPath(this.f8670a, this.f8674e);
        canvas.restore();
    }

    public final void q(Canvas canvas, Matrix matrix) {
        o1.d.a("Layer#saveLayer");
        a2.h.n(canvas, this.f8677h, this.f8673d, 19);
        if (Build.VERSION.SDK_INT < 28) {
            u(canvas);
        }
        o1.d.b("Layer#saveLayer");
        for (int i8 = 0; i8 < this.f8685p.b().size(); i8++) {
            Mask mask = this.f8685p.b().get(i8);
            r1.a<h, Path> aVar = this.f8685p.a().get(i8);
            r1.a<Integer, Integer> aVar2 = this.f8685p.c().get(i8);
            int i9 = b.f8695b[mask.a().ordinal()];
            if (i9 != 1) {
                if (i9 == 2) {
                    if (i8 == 0) {
                        this.f8672c.setColor(-16777216);
                        this.f8672c.setAlpha(255);
                        canvas.drawRect(this.f8677h, this.f8672c);
                    }
                    if (mask.d()) {
                        p(canvas, matrix, mask, aVar, aVar2);
                    } else {
                        r(canvas, matrix, mask, aVar, aVar2);
                    }
                } else if (i9 != 3) {
                    if (i9 == 4) {
                        if (mask.d()) {
                            n(canvas, matrix, mask, aVar, aVar2);
                        } else {
                            l(canvas, matrix, mask, aVar, aVar2);
                        }
                    }
                } else if (mask.d()) {
                    o(canvas, matrix, mask, aVar, aVar2);
                } else {
                    m(canvas, matrix, mask, aVar, aVar2);
                }
            } else if (s()) {
                this.f8672c.setAlpha(255);
                canvas.drawRect(this.f8677h, this.f8672c);
            }
        }
        o1.d.a("Layer#restoreLayer");
        canvas.restore();
        o1.d.b("Layer#restoreLayer");
    }

    public final void r(Canvas canvas, Matrix matrix, Mask mask, r1.a<h, Path> aVar, r1.a<Integer, Integer> aVar2) {
        this.f8670a.set(aVar.h());
        this.f8670a.transform(matrix);
        canvas.drawPath(this.f8670a, this.f8674e);
    }

    public final boolean s() {
        if (this.f8685p.a().isEmpty()) {
            return false;
        }
        for (int i8 = 0; i8 < this.f8685p.b().size(); i8++) {
            if (this.f8685p.b().get(i8).a() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    public final void t() {
        if (this.f8689t != null) {
            return;
        }
        if (this.f8688s == null) {
            this.f8689t = Collections.emptyList();
            return;
        }
        this.f8689t = new ArrayList();
        for (a aVar = this.f8688s; aVar != null; aVar = aVar.f8688s) {
            this.f8689t.add(aVar);
        }
    }

    public final void u(Canvas canvas) {
        o1.d.a("Layer#clearLayer");
        RectF rectF = this.f8677h;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.f8676g);
        o1.d.b("Layer#clearLayer");
    }

    public abstract void v(Canvas canvas, Matrix matrix, int i8);

    public Layer x() {
        return this.f8684o;
    }

    public boolean y() {
        r1.g gVar = this.f8685p;
        return (gVar == null || gVar.a().isEmpty()) ? false : true;
    }

    public boolean z() {
        return this.f8687r != null;
    }
}
