package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.h;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.r.c.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: BaseLayer.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements com.airbnb.lottie.r.b.e, a.b, com.airbnb.lottie.model.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2631a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix f2632b = new Matrix();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Paint f2633c = new com.airbnb.lottie.r.a(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Paint f2634d = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_IN);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f2635e = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_OUT);
    private final Paint f;
    private final Paint g;
    private final RectF h;
    private final RectF i;
    private final RectF j;
    private final RectF k;
    private final String l;
    final Matrix m;
    final com.airbnb.lottie.f n;
    final Layer o;
    private com.airbnb.lottie.r.c.g p;
    private com.airbnb.lottie.r.c.c q;
    private a r;
    private a s;
    private List<a> t;
    private final List<com.airbnb.lottie.r.c.a<?, ?>> u;
    final o v;
    private boolean w;

    /* JADX INFO: renamed from: com.airbnb.lottie.model.layer.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseLayer.java */
    class C0056a implements a.b {
        C0056a() {
        }

        @Override // com.airbnb.lottie.r.c.a.b
        public void b() {
            a aVar = a.this;
            aVar.J(aVar.q.o() == 1.0f);
        }
    }

    /* JADX INFO: compiled from: BaseLayer.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2637a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f2638b;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            f2638b = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2638b[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2638b[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2638b[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            f2637a = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2637a[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2637a[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2637a[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2637a[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2637a[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2637a[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    a(com.airbnb.lottie.f fVar, Layer layer) {
        com.airbnb.lottie.r.a aVar = new com.airbnb.lottie.r.a(1);
        this.f = aVar;
        this.g = new com.airbnb.lottie.r.a(PorterDuff.Mode.CLEAR);
        this.h = new RectF();
        this.i = new RectF();
        this.j = new RectF();
        this.k = new RectF();
        this.m = new Matrix();
        this.u = new ArrayList();
        this.w = true;
        this.n = fVar;
        this.o = layer;
        this.l = layer.g() + "#draw";
        if (layer.f() == Layer.MatteType.INVERT) {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        o oVarB = layer.u().b();
        this.v = oVarB;
        oVarB.b(this);
        if (layer.e() != null && !layer.e().isEmpty()) {
            com.airbnb.lottie.r.c.g gVar = new com.airbnb.lottie.r.c.g(layer.e());
            this.p = gVar;
            Iterator<com.airbnb.lottie.r.c.a<h, Path>> it = gVar.a().iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
            for (com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 : this.p.c()) {
                k(aVar2);
                aVar2.a(this);
            }
        }
        K();
    }

    private void A(RectF rectF, Matrix matrix) {
        this.i.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (y()) {
            int size = this.p.b().size();
            for (int i = 0; i < size; i++) {
                Mask mask = this.p.b().get(i);
                this.f2631a.set(this.p.a().get(i).h());
                this.f2631a.transform(matrix);
                int i2 = b.f2638b[mask.a().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    return;
                }
                if ((i2 == 3 || i2 == 4) && mask.d()) {
                    return;
                }
                this.f2631a.computeBounds(this.k, false);
                if (i == 0) {
                    this.i.set(this.k);
                } else {
                    RectF rectF2 = this.i;
                    rectF2.set(Math.min(rectF2.left, this.k.left), Math.min(this.i.top, this.k.top), Math.max(this.i.right, this.k.right), Math.max(this.i.bottom, this.k.bottom));
                }
            }
            if (rectF.intersect(this.i)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void B(RectF rectF, Matrix matrix) {
        if (z() && this.o.f() != Layer.MatteType.INVERT) {
            this.j.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.r.a(this.j, matrix, true);
            if (rectF.intersect(this.j)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private void C() {
        this.n.invalidateSelf();
    }

    private void D(float f) {
        this.n.m().m().a(this.o.g(), f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J(boolean z) {
        if (z != this.w) {
            this.w = z;
            C();
        }
    }

    private void K() {
        if (this.o.c().isEmpty()) {
            J(true);
            return;
        }
        com.airbnb.lottie.r.c.c cVar = new com.airbnb.lottie.r.c.c(this.o.c());
        this.q = cVar;
        cVar.k();
        this.q.a(new C0056a());
        J(this.q.h().floatValue() == 1.0f);
        k(this.q);
    }

    private void l(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        this.f2633c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f2631a, this.f2633c);
    }

    private void m(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f2634d);
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        this.f2633c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f2631a, this.f2633c);
        canvas.restore();
    }

    private void n(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f2633c);
        canvas.drawRect(this.h, this.f2633c);
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        this.f2633c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f2631a, this.f2635e);
        canvas.restore();
    }

    private void o(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f2634d);
        canvas.drawRect(this.h, this.f2633c);
        this.f2635e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        canvas.drawPath(this.f2631a, this.f2635e);
        canvas.restore();
    }

    private void p(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f2635e);
        canvas.drawRect(this.h, this.f2633c);
        this.f2635e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        canvas.drawPath(this.f2631a, this.f2635e);
        canvas.restore();
    }

    private void q(Canvas canvas, Matrix matrix) {
        com.airbnb.lottie.c.a("Layer#saveLayer");
        com.airbnb.lottie.u.h.n(canvas, this.h, this.f2634d, 19);
        if (Build.VERSION.SDK_INT < 28) {
            u(canvas);
        }
        com.airbnb.lottie.c.b("Layer#saveLayer");
        for (int i = 0; i < this.p.b().size(); i++) {
            Mask mask = this.p.b().get(i);
            com.airbnb.lottie.r.c.a<h, Path> aVar = this.p.a().get(i);
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.p.c().get(i);
            int i2 = b.f2638b[mask.a().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (i == 0) {
                        this.f2633c.setColor(-16777216);
                        this.f2633c.setAlpha(255);
                        canvas.drawRect(this.h, this.f2633c);
                    }
                    if (mask.d()) {
                        p(canvas, matrix, mask, aVar, aVar2);
                    } else {
                        r(canvas, matrix, mask, aVar, aVar2);
                    }
                } else if (i2 != 3) {
                    if (i2 == 4) {
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
                this.f2633c.setAlpha(255);
                canvas.drawRect(this.h, this.f2633c);
            }
        }
        com.airbnb.lottie.c.a("Layer#restoreLayer");
        canvas.restore();
        com.airbnb.lottie.c.b("Layer#restoreLayer");
    }

    private void r(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f2631a.set(aVar.h());
        this.f2631a.transform(matrix);
        canvas.drawPath(this.f2631a, this.f2635e);
    }

    private boolean s() {
        if (this.p.a().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.p.b().size(); i++) {
            if (this.p.b().get(i).a() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void t() {
        if (this.t != null) {
            return;
        }
        if (this.s == null) {
            this.t = Collections.emptyList();
            return;
        }
        this.t = new ArrayList();
        for (a aVar = this.s; aVar != null; aVar = aVar.s) {
            this.t.add(aVar);
        }
    }

    private void u(Canvas canvas) {
        com.airbnb.lottie.c.a("Layer#clearLayer");
        RectF rectF = this.h;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.g);
        com.airbnb.lottie.c.b("Layer#clearLayer");
    }

    static a w(Layer layer, com.airbnb.lottie.f fVar, com.airbnb.lottie.d dVar) {
        switch (b.f2637a[layer.d().ordinal()]) {
            case 1:
                return new e(fVar, layer);
            case 2:
                return new com.airbnb.lottie.model.layer.b(fVar, layer, dVar.n(layer.k()), dVar);
            case 3:
                return new f(fVar, layer);
            case 4:
                return new c(fVar, layer);
            case 5:
                return new d(fVar, layer);
            case 6:
                return new g(fVar, layer);
            default:
                com.airbnb.lottie.u.d.c("Unknown layer type " + layer.d());
                return null;
        }
    }

    public void E(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.u.remove(aVar);
    }

    void F(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
    }

    void G(a aVar) {
        this.r = aVar;
    }

    void H(a aVar) {
        this.s = aVar;
    }

    void I(float f) {
        this.v.j(f);
        if (this.p != null) {
            for (int i = 0; i < this.p.a().size(); i++) {
                this.p.a().get(i).l(f);
            }
        }
        if (this.o.t() != 0.0f) {
            f /= this.o.t();
        }
        com.airbnb.lottie.r.c.c cVar = this.q;
        if (cVar != null) {
            cVar.l(f / this.o.t());
        }
        a aVar = this.r;
        if (aVar != null) {
            this.r.I(aVar.o.t() * f);
        }
        for (int i2 = 0; i2 < this.u.size(); i2++) {
            this.u.get(i2).l(f);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        t();
        this.m.set(matrix);
        if (z) {
            List<a> list = this.t;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.m.preConcat(this.t.get(size).v.f());
                }
            } else {
                a aVar = this.s;
                if (aVar != null) {
                    this.m.preConcat(aVar.v.f());
                }
            }
        }
        this.m.preConcat(this.v.f());
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        C();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<com.airbnb.lottie.r.b.c> list, List<com.airbnb.lottie.r.b.c> list2) {
    }

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        if (dVar.g(i(), i)) {
            if (!"__container".equals(i())) {
                dVar2 = dVar2.a(i());
                if (dVar.c(i(), i)) {
                    list.add(dVar2.i(this));
                }
            }
            if (dVar.h(i(), i)) {
                F(dVar, i + dVar.e(i(), i), list, dVar2);
            }
        }
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        this.v.c(t, cVar);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a(this.l);
        if (!this.w || this.o.v()) {
            com.airbnb.lottie.c.b(this.l);
            return;
        }
        t();
        com.airbnb.lottie.c.a("Layer#parentMatrix");
        this.f2632b.reset();
        this.f2632b.set(matrix);
        for (int size = this.t.size() - 1; size >= 0; size--) {
            this.f2632b.preConcat(this.t.get(size).v.f());
        }
        com.airbnb.lottie.c.b("Layer#parentMatrix");
        int iIntValue = (int) ((((i / 255.0f) * (this.v.h() == null ? 100 : this.v.h().h().intValue())) / 100.0f) * 255.0f);
        if (!z() && !y()) {
            this.f2632b.preConcat(this.v.f());
            com.airbnb.lottie.c.a("Layer#drawLayer");
            v(canvas, this.f2632b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            D(com.airbnb.lottie.c.b(this.l));
            return;
        }
        com.airbnb.lottie.c.a("Layer#computeBounds");
        a(this.h, this.f2632b, false);
        B(this.h, matrix);
        this.f2632b.preConcat(this.v.f());
        A(this.h, this.f2632b);
        if (!this.h.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
            this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        com.airbnb.lottie.c.b("Layer#computeBounds");
        if (!this.h.isEmpty()) {
            com.airbnb.lottie.c.a("Layer#saveLayer");
            this.f2633c.setAlpha(255);
            com.airbnb.lottie.u.h.m(canvas, this.h, this.f2633c);
            com.airbnb.lottie.c.b("Layer#saveLayer");
            u(canvas);
            com.airbnb.lottie.c.a("Layer#drawLayer");
            v(canvas, this.f2632b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            if (y()) {
                q(canvas, this.f2632b);
            }
            if (z()) {
                com.airbnb.lottie.c.a("Layer#drawMatte");
                com.airbnb.lottie.c.a("Layer#saveLayer");
                com.airbnb.lottie.u.h.n(canvas, this.h, this.f, 19);
                com.airbnb.lottie.c.b("Layer#saveLayer");
                u(canvas);
                this.r.g(canvas, matrix, iIntValue);
                com.airbnb.lottie.c.a("Layer#restoreLayer");
                canvas.restore();
                com.airbnb.lottie.c.b("Layer#restoreLayer");
                com.airbnb.lottie.c.b("Layer#drawMatte");
            }
            com.airbnb.lottie.c.a("Layer#restoreLayer");
            canvas.restore();
            com.airbnb.lottie.c.b("Layer#restoreLayer");
        }
        D(com.airbnb.lottie.c.b(this.l));
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.o.g();
    }

    public void k(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        if (aVar == null) {
            return;
        }
        this.u.add(aVar);
    }

    abstract void v(Canvas canvas, Matrix matrix, int i);

    Layer x() {
        return this.o;
    }

    boolean y() {
        com.airbnb.lottie.r.c.g gVar = this.p;
        return (gVar == null || gVar.a().isEmpty()) ? false : true;
    }

    boolean z() {
        return this.r != null;
    }
}
