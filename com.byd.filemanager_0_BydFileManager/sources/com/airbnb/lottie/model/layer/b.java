package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import com.airbnb.lottie.k;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.r.c.p;
import com.airbnb.lottie.u.h;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: CompositionLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends com.airbnb.lottie.model.layer.a {
    private final RectF A;
    private Paint B;
    private com.airbnb.lottie.r.c.a<Float, Float> x;
    private final List<com.airbnb.lottie.model.layer.a> y;
    private final RectF z;

    /* JADX INFO: compiled from: CompositionLayer.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2639a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f2639a = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2639a[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public b(com.airbnb.lottie.f fVar, Layer layer, List<Layer> list, com.airbnb.lottie.d dVar) {
        int i;
        com.airbnb.lottie.model.layer.a aVar;
        super(fVar, layer);
        this.y = new ArrayList();
        this.z = new RectF();
        this.A = new RectF();
        this.B = new Paint();
        com.airbnb.lottie.model.i.b bVarS = layer.s();
        if (bVarS != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA = bVarS.a();
            this.x = aVarA;
            k(aVarA);
            this.x.a(this);
        } else {
            this.x = null;
        }
        b.d.d dVar2 = new b.d.d(dVar.j().size());
        int size = list.size() - 1;
        com.airbnb.lottie.model.layer.a aVar2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = list.get(size);
            com.airbnb.lottie.model.layer.a aVarW = com.airbnb.lottie.model.layer.a.w(layer2, fVar, dVar);
            if (aVarW != null) {
                dVar2.j(aVarW.x().b(), aVarW);
                if (aVar2 != null) {
                    aVar2.G(aVarW);
                    aVar2 = null;
                } else {
                    this.y.add(0, aVarW);
                    int i2 = a.f2639a[layer2.f().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        aVar2 = aVarW;
                    }
                }
            }
            size--;
        }
        for (i = 0; i < dVar2.m(); i++) {
            com.airbnb.lottie.model.layer.a aVar3 = (com.airbnb.lottie.model.layer.a) dVar2.f(dVar2.i(i));
            if (aVar3 != null && (aVar = (com.airbnb.lottie.model.layer.a) dVar2.f(aVar3.x().h())) != null) {
                aVar3.H(aVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    protected void F(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        for (int i2 = 0; i2 < this.y.size(); i2++) {
            this.y.get(i2).e(dVar, i, list, dVar2);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    public void I(float f) {
        super.I(f);
        if (this.x != null) {
            f = ((this.x.h().floatValue() * this.o.a().h()) - this.o.a().o()) / (this.n.m().e() + 0.01f);
        }
        if (this.x == null) {
            f -= this.o.p();
        }
        if (this.o.t() != 0.0f) {
            f /= this.o.t();
        }
        for (int size = this.y.size() - 1; size >= 0; size--) {
            this.y.get(size).I(f);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        for (int size = this.y.size() - 1; size >= 0; size--) {
            this.z.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.y.get(size).a(this.z, this.m, true);
            rectF.union(this.z);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == k.A) {
            if (cVar == null) {
                com.airbnb.lottie.r.c.a<Float, Float> aVar = this.x;
                if (aVar != null) {
                    aVar.m(null);
                    return;
                }
                return;
            }
            p pVar = new p(cVar);
            this.x = pVar;
            pVar.a(this);
            k(this.x);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    void v(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a("CompositionLayer#draw");
        this.A.set(0.0f, 0.0f, this.o.j(), this.o.i());
        matrix.mapRect(this.A);
        boolean z = this.n.F() && this.y.size() > 1 && i != 255;
        if (z) {
            this.B.setAlpha(i);
            h.m(canvas, this.A, this.B);
        } else {
            canvas.save();
        }
        if (z) {
            i = 255;
        }
        for (int size = this.y.size() - 1; size >= 0; size--) {
            if (!this.A.isEmpty() ? canvas.clipRect(this.A) : true) {
                this.y.get(size).g(canvas, matrix, i);
            }
        }
        canvas.restore();
        com.airbnb.lottie.c.b("CompositionLayer#draw");
    }
}
