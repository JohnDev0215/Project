package com.airbnb.lottie.t.l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.k;
import com.airbnb.lottie.r.c.p;
import com.airbnb.lottie.t.l.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.airbnb.lottie.t.l.a {
    private Paint A;

    @Nullable
    private com.airbnb.lottie.r.c.a<Float, Float> w;
    private final List<com.airbnb.lottie.t.l.a> x;
    private final RectF y;
    private final RectF z;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f887a;

        static {
            int[] iArr = new int[d.b.values().length];
            f887a = iArr;
            try {
                iArr[d.b.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f887a[d.b.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public b(com.airbnb.lottie.f fVar, d dVar, List<d> list, com.airbnb.lottie.d dVar2) {
        int i;
        com.airbnb.lottie.t.l.a aVar;
        super(fVar, dVar);
        this.x = new ArrayList();
        this.y = new RectF();
        this.z = new RectF();
        this.A = new Paint();
        com.airbnb.lottie.t.j.b bVarS = dVar.s();
        if (bVarS != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA = bVarS.a();
            this.w = aVarA;
            h(aVarA);
            this.w.a(this);
        } else {
            this.w = null;
        }
        LongSparseArray longSparseArray = new LongSparseArray(dVar2.j().size());
        int size = list.size() - 1;
        com.airbnb.lottie.t.l.a aVar2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            d dVar3 = list.get(size);
            com.airbnb.lottie.t.l.a aVarS = com.airbnb.lottie.t.l.a.s(dVar3, fVar, dVar2);
            if (aVarS != null) {
                longSparseArray.put(aVarS.t().b(), aVarS);
                if (aVar2 != null) {
                    aVar2.C(aVarS);
                    aVar2 = null;
                } else {
                    this.x.add(0, aVarS);
                    int i2 = a.f887a[dVar3.f().ordinal()];
                    if (i2 == 1 || i2 == 2) {
                        aVar2 = aVarS;
                    }
                }
            }
            size--;
        }
        for (i = 0; i < longSparseArray.size(); i++) {
            com.airbnb.lottie.t.l.a aVar3 = (com.airbnb.lottie.t.l.a) longSparseArray.get(longSparseArray.keyAt(i));
            if (aVar3 != null && (aVar = (com.airbnb.lottie.t.l.a) longSparseArray.get(aVar3.t().h())) != null) {
                aVar3.D(aVar);
            }
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    protected void B(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            this.x.get(i2).e(eVar, i, list, eVar2);
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    public void E(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        super.E(f);
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
            this.x.get(size).E(f);
        }
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        for (int size = this.x.size() - 1; size >= 0; size--) {
            this.y.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.x.get(size).a(this.y, this.m, true);
            rectF.union(this.y);
        }
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        super.g(t, cVar);
        if (t == k.A) {
            if (cVar == null) {
                this.w = null;
                return;
            }
            p pVar = new p(cVar);
            this.w = pVar;
            h(pVar);
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    void r(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a("CompositionLayer#draw");
        this.z.set(0.0f, 0.0f, this.o.j(), this.o.i());
        matrix.mapRect(this.z);
        boolean z = this.n.C() && this.x.size() > 1 && i != 255;
        if (z) {
            this.A.setAlpha(i);
            com.airbnb.lottie.w.h.m(canvas, this.z, this.A);
        } else {
            canvas.save();
        }
        if (z) {
            i = 255;
        }
        for (int size = this.x.size() - 1; size >= 0; size--) {
            if (!this.z.isEmpty() ? canvas.clipRect(this.z) : true) {
                this.x.get(size).f(canvas, matrix, i);
            }
        }
        canvas.restore();
        com.airbnb.lottie.c.b("CompositionLayer#draw");
    }
}
