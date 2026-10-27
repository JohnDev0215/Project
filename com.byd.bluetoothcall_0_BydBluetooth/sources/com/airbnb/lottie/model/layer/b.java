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
    private Paint A;
    private com.airbnb.lottie.r.c.a<Float, Float> w;
    private final List<com.airbnb.lottie.model.layer.a> x;
    private final RectF y;
    private final RectF z;

    /* JADX INFO: compiled from: CompositionLayer.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3024a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f3024a = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3024a[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public b(com.airbnb.lottie.f fVar, Layer layer, List<Layer> list, com.airbnb.lottie.d dVar) {
        int i;
        com.airbnb.lottie.model.layer.a aVar;
        super(fVar, layer);
        this.x = new ArrayList();
        this.y = new RectF();
        this.z = new RectF();
        this.A = new Paint();
        com.airbnb.lottie.model.i.b bVarS = layer.s();
        if (bVarS != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA = bVarS.a();
            this.w = aVarA;
            j(aVarA);
            this.w.a(this);
        } else {
            this.w = null;
        }
        b.d.d dVar2 = new b.d.d(dVar.j().size());
        int size = list.size() - 1;
        com.airbnb.lottie.model.layer.a aVar2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = list.get(size);
            com.airbnb.lottie.model.layer.a aVarU = com.airbnb.lottie.model.layer.a.u(layer2, fVar, dVar);
            if (aVarU != null) {
                dVar2.j(aVarU.v().b(), aVarU);
                if (aVar2 != null) {
                    aVar2.E(aVarU);
                    aVar2 = null;
                } else {
                    this.x.add(0, aVarU);
                    int i2 = a.f3024a[layer2.f().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        aVar2 = aVarU;
                    }
                }
            }
            size--;
        }
        for (i = 0; i < dVar2.m(); i++) {
            com.airbnb.lottie.model.layer.a aVar3 = (com.airbnb.lottie.model.layer.a) dVar2.f(dVar2.i(i));
            if (aVar3 != null && (aVar = (com.airbnb.lottie.model.layer.a) dVar2.f(aVar3.v().h())) != null) {
                aVar3.F(aVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    protected void D(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            this.x.get(i2).e(dVar, i, list, dVar2);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    public void G(float f) {
        super.G(f);
        if (this.w != null) {
            f = ((this.w.h().floatValue() * this.o.a().h()) - this.o.a().o()) / (this.n.j().e() + 0.01f);
        }
        if (this.o.t() != 0.0f) {
            f /= this.o.t();
        }
        if (this.w == null) {
            f -= this.o.p();
        }
        for (int size = this.x.size() - 1; size >= 0; size--) {
            this.x.get(size).G(f);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        for (int size = this.x.size() - 1; size >= 0; size--) {
            this.y.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.x.get(size).a(this.y, this.m, true);
            rectF.union(this.y);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == k.A) {
            if (cVar == null) {
                this.w = null;
                return;
            }
            p pVar = new p(cVar);
            this.w = pVar;
            j(pVar);
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    void t(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a("CompositionLayer#draw");
        this.z.set(0.0f, 0.0f, this.o.j(), this.o.i());
        matrix.mapRect(this.z);
        boolean z = this.n.C() && this.x.size() > 1 && i != 255;
        if (z) {
            this.A.setAlpha(i);
            h.m(canvas, this.z, this.A);
        } else {
            canvas.save();
        }
        if (z) {
            i = 255;
        }
        for (int size = this.x.size() - 1; size >= 0; size--) {
            if (!this.z.isEmpty() ? canvas.clipRect(this.z) : true) {
                this.x.get(size).g(canvas, matrix, i);
            }
        }
        canvas.restore();
        com.airbnb.lottie.c.b("CompositionLayer#draw");
    }
}
