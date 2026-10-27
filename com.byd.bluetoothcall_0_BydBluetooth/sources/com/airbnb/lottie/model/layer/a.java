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
public abstract class a implements com.airbnb.lottie.r.b.e, a.InterfaceC0077a, com.airbnb.lottie.model.e {

    /* JADX INFO: renamed from: a */
    private final Path f3015a = new Path();

    /* JADX INFO: renamed from: b */
    private final Matrix f3016b = new Matrix();

    /* JADX INFO: renamed from: c */
    private final Paint f3017c = new com.airbnb.lottie.r.a(1);

    /* JADX INFO: renamed from: d */
    private final Paint f3018d = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_IN);

    /* JADX INFO: renamed from: e */
    private final Paint f3019e = new com.airbnb.lottie.r.a(1, PorterDuff.Mode.DST_OUT);
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
    private a q;
    private a r;
    private List<a> s;
    private final List<com.airbnb.lottie.r.c.a<?, ?>> t;
    final o u;
    private boolean v;

    /* JADX INFO: renamed from: com.airbnb.lottie.model.layer.a$a */
    /* JADX INFO: compiled from: BaseLayer.java */
    class C0075a implements a.InterfaceC0077a {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ com.airbnb.lottie.r.c.c f3020a;

        C0075a(com.airbnb.lottie.r.c.c cVar) {
            this.f3020a = cVar;
        }

        @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
        public void b() {
            a.this.H(this.f3020a.n() == 1.0f);
        }
    }

    /* JADX INFO: compiled from: BaseLayer.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a */
        static final /* synthetic */ int[] f3022a;

        /* JADX INFO: renamed from: b */
        static final /* synthetic */ int[] f3023b;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            f3023b = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3023b[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3023b[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            f3022a = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3022a[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3022a[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3022a[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3022a[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3022a[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3022a[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
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
        this.t = new ArrayList();
        this.v = true;
        this.n = fVar;
        this.o = layer;
        this.l = layer.g() + "#draw";
        if (layer.f() == Layer.MatteType.INVERT) {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            aVar.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        o oVarB = layer.u().b();
        this.u = oVarB;
        oVarB.b(this);
        if (layer.e() != null && !layer.e().isEmpty()) {
            com.airbnb.lottie.r.c.g gVar = new com.airbnb.lottie.r.c.g(layer.e());
            this.p = gVar;
            Iterator<com.airbnb.lottie.r.c.a<h, Path>> it = gVar.a().iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
            for (com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 : this.p.c()) {
                j(aVar2);
                aVar2.a(this);
            }
        }
        I();
    }

    private void A() {
        this.n.invalidateSelf();
    }

    private void B(float f) {
        this.n.j().m().a(this.o.g(), f);
    }

    public void H(boolean z) {
        if (z != this.v) {
            this.v = z;
            A();
        }
    }

    private void I() {
        if (this.o.c().isEmpty()) {
            H(true);
            return;
        }
        com.airbnb.lottie.r.c.c cVar = new com.airbnb.lottie.r.c.c(this.o.c());
        cVar.k();
        cVar.a(new C0075a(cVar));
        H(cVar.h().floatValue() == 1.0f);
        j(cVar);
    }

    private void k(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        this.f3017c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f3015a, this.f3017c);
    }

    private void l(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f3018d);
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        this.f3017c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f3015a, this.f3017c);
        canvas.restore();
    }

    private void m(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f3017c);
        canvas.drawRect(this.h, this.f3017c);
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        this.f3017c.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        canvas.drawPath(this.f3015a, this.f3019e);
        canvas.restore();
    }

    private void n(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f3018d);
        canvas.drawRect(this.h, this.f3017c);
        this.f3019e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        canvas.drawPath(this.f3015a, this.f3019e);
        canvas.restore();
    }

    private void o(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        com.airbnb.lottie.u.h.m(canvas, this.h, this.f3019e);
        canvas.drawRect(this.h, this.f3017c);
        this.f3019e.setAlpha((int) (aVar2.h().intValue() * 2.55f));
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        canvas.drawPath(this.f3015a, this.f3019e);
        canvas.restore();
    }

    private void p(Canvas canvas, Matrix matrix) {
        com.airbnb.lottie.c.a("Layer#saveLayer");
        com.airbnb.lottie.u.h.n(canvas, this.h, this.f3018d, 19);
        if (Build.VERSION.SDK_INT < 28) {
            canvas.drawColor(0);
        }
        com.airbnb.lottie.c.b("Layer#saveLayer");
        for (int i = 0; i < this.p.b().size(); i++) {
            Mask mask = this.p.b().get(i);
            com.airbnb.lottie.r.c.a<h, Path> aVar = this.p.a().get(i);
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.p.c().get(i);
            int i2 = b.f3023b[mask.a().ordinal()];
            if (i2 == 1) {
                if (i == 0) {
                    this.f3017c.setColor(-16777216);
                    this.f3017c.setAlpha(255);
                    canvas.drawRect(this.h, this.f3017c);
                }
                if (mask.d()) {
                    o(canvas, matrix, mask, aVar, aVar2);
                } else {
                    q(canvas, matrix, mask, aVar, aVar2);
                }
            } else if (i2 != 2) {
                if (i2 == 3) {
                    if (mask.d()) {
                        m(canvas, matrix, mask, aVar, aVar2);
                    } else {
                        k(canvas, matrix, mask, aVar, aVar2);
                    }
                }
            } else if (mask.d()) {
                n(canvas, matrix, mask, aVar, aVar2);
            } else {
                l(canvas, matrix, mask, aVar, aVar2);
            }
        }
        com.airbnb.lottie.c.a("Layer#restoreLayer");
        canvas.restore();
        com.airbnb.lottie.c.b("Layer#restoreLayer");
    }

    private void q(Canvas canvas, Matrix matrix, Mask mask, com.airbnb.lottie.r.c.a<h, Path> aVar, com.airbnb.lottie.r.c.a<Integer, Integer> aVar2) {
        this.f3015a.set(aVar.h());
        this.f3015a.transform(matrix);
        canvas.drawPath(this.f3015a, this.f3019e);
    }

    private void r() {
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

    private void s(Canvas canvas) {
        com.airbnb.lottie.c.a("Layer#clearLayer");
        RectF rectF = this.h;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.g);
        com.airbnb.lottie.c.b("Layer#clearLayer");
    }

    static a u(Layer layer, com.airbnb.lottie.f fVar, com.airbnb.lottie.d dVar) {
        switch (b.f3022a[layer.d().ordinal()]) {
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
                com.airbnb.lottie.u.d.b("Unknown layer type " + layer.d());
                return null;
        }
    }

    private void y(RectF rectF, Matrix matrix) {
        this.i.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (w()) {
            int size = this.p.b().size();
            for (int i = 0; i < size; i++) {
                Mask mask = this.p.b().get(i);
                this.f3015a.set(this.p.a().get(i).h());
                this.f3015a.transform(matrix);
                int i2 = b.f3023b[mask.a().ordinal()];
                if (i2 == 1) {
                    return;
                }
                if ((i2 == 2 || i2 == 3) && mask.d()) {
                    return;
                }
                this.f3015a.computeBounds(this.k, false);
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

    private void z(RectF rectF, Matrix matrix) {
        if (x() && this.o.f() != Layer.MatteType.INVERT) {
            this.j.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.q.a(this.j, matrix, true);
            if (rectF.intersect(this.j)) {
                return;
            }
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    public void C(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        this.t.remove(aVar);
    }

    void D(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
    }

    void E(a aVar) {
        this.q = aVar;
    }

    void F(a aVar) {
        this.r = aVar;
    }

    void G(float f) {
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
            this.q.G(aVar.o.t() * f);
        }
        for (int i2 = 0; i2 < this.t.size(); i2++) {
            this.t.get(i2).l(f);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        r();
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

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
    public void b() {
        A();
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
                D(dVar, i + dVar.e(i(), i), list, dVar2);
            }
        }
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        this.u.c(t, cVar);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a(this.l);
        if (!this.v || this.o.v()) {
            com.airbnb.lottie.c.b(this.l);
            return;
        }
        r();
        com.airbnb.lottie.c.a("Layer#parentMatrix");
        this.f3016b.reset();
        this.f3016b.set(matrix);
        for (int size = this.s.size() - 1; size >= 0; size--) {
            this.f3016b.preConcat(this.s.get(size).u.f());
        }
        com.airbnb.lottie.c.b("Layer#parentMatrix");
        int iIntValue = (int) ((((i / 255.0f) * (this.u.h() == null ? 100 : this.u.h().h().intValue())) / 100.0f) * 255.0f);
        if (!x() && !w()) {
            this.f3016b.preConcat(this.u.f());
            com.airbnb.lottie.c.a("Layer#drawLayer");
            t(canvas, this.f3016b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            B(com.airbnb.lottie.c.b(this.l));
            return;
        }
        com.airbnb.lottie.c.a("Layer#computeBounds");
        a(this.h, this.f3016b, false);
        z(this.h, matrix);
        this.f3016b.preConcat(this.u.f());
        y(this.h, this.f3016b);
        if (!this.h.intersect(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight())) {
            this.h.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
        com.airbnb.lottie.c.b("Layer#computeBounds");
        if (!this.h.isEmpty()) {
            com.airbnb.lottie.c.a("Layer#saveLayer");
            com.airbnb.lottie.u.h.m(canvas, this.h, this.f3017c);
            com.airbnb.lottie.c.b("Layer#saveLayer");
            s(canvas);
            com.airbnb.lottie.c.a("Layer#drawLayer");
            t(canvas, this.f3016b, iIntValue);
            com.airbnb.lottie.c.b("Layer#drawLayer");
            if (w()) {
                p(canvas, this.f3016b);
            }
            if (x()) {
                com.airbnb.lottie.c.a("Layer#drawMatte");
                com.airbnb.lottie.c.a("Layer#saveLayer");
                com.airbnb.lottie.u.h.n(canvas, this.h, this.f, 19);
                com.airbnb.lottie.c.b("Layer#saveLayer");
                s(canvas);
                this.q.g(canvas, matrix, iIntValue);
                com.airbnb.lottie.c.a("Layer#restoreLayer");
                canvas.restore();
                com.airbnb.lottie.c.b("Layer#restoreLayer");
                com.airbnb.lottie.c.b("Layer#drawMatte");
            }
            com.airbnb.lottie.c.a("Layer#restoreLayer");
            canvas.restore();
            com.airbnb.lottie.c.b("Layer#restoreLayer");
        }
        B(com.airbnb.lottie.c.b(this.l));
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.o.g();
    }

    public void j(com.airbnb.lottie.r.c.a<?, ?> aVar) {
        if (aVar == null) {
            return;
        }
        this.t.add(aVar);
    }

    abstract void t(Canvas canvas, Matrix matrix, int i);

    Layer v() {
        return this.o;
    }

    boolean w() {
        com.airbnb.lottie.r.c.g gVar = this.p;
        return (gVar == null || gVar.a().isEmpty()) ? false : true;
    }

    boolean x() {
        return this.q != null;
    }
}
