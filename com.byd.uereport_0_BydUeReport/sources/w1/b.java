package w1;

import a2.h;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import com.airbnb.lottie.model.layer.Layer;
import java.util.ArrayList;
import java.util.List;
import o1.l;
import r1.p;

/* JADX INFO: compiled from: CompositionLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends w1.a {
    public final RectF A;
    public Paint B;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public r1.a<Float, Float> f8696x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final List<w1.a> f8697y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final RectF f8698z;

    /* JADX INFO: compiled from: CompositionLayer.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8699a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f8699a = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8699a[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public b(o1.g gVar, Layer layer, List<Layer> list, o1.e eVar) {
        int i8;
        w1.a aVar;
        super(gVar, layer);
        this.f8697y = new ArrayList();
        this.f8698z = new RectF();
        this.A = new RectF();
        this.B = new Paint();
        u1.b bVarS = layer.s();
        if (bVarS != null) {
            r1.a<Float, Float> aVarA = bVarS.a();
            this.f8696x = aVarA;
            k(aVarA);
            this.f8696x.a(this);
        } else {
            this.f8696x = null;
        }
        v.d dVar = new v.d(eVar.j().size());
        int size = list.size() - 1;
        w1.a aVar2 = null;
        while (true) {
            if (size < 0) {
                break;
            }
            Layer layer2 = list.get(size);
            w1.a aVarW = w1.a.w(layer2, gVar, eVar);
            if (aVarW != null) {
                dVar.k(aVarW.x().b(), aVarW);
                if (aVar2 != null) {
                    aVar2.G(aVarW);
                    aVar2 = null;
                } else {
                    this.f8697y.add(0, aVarW);
                    int i9 = a.f8699a[layer2.f().ordinal()];
                    if (i9 == 1 || i9 == 2) {
                        aVar2 = aVarW;
                    }
                }
            }
            size--;
        }
        for (i8 = 0; i8 < dVar.n(); i8++) {
            w1.a aVar3 = (w1.a) dVar.f(dVar.j(i8));
            if (aVar3 != null && (aVar = (w1.a) dVar.f(aVar3.x().h())) != null) {
                aVar3.H(aVar);
            }
        }
    }

    @Override // w1.a
    public void F(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        for (int i9 = 0; i9 < this.f8697y.size(); i9++) {
            this.f8697y.get(i9).f(dVar, i8, list, dVar2);
        }
    }

    @Override // w1.a
    public void I(float f8) {
        super.I(f8);
        if (this.f8696x != null) {
            f8 = ((this.f8696x.h().floatValue() * this.f8684o.a().h()) - this.f8684o.a().o()) / (this.f8683n.m().e() + 0.01f);
        }
        if (this.f8696x == null) {
            f8 -= this.f8684o.p();
        }
        if (this.f8684o.t() != 0.0f) {
            f8 /= this.f8684o.t();
        }
        for (int size = this.f8697y.size() - 1; size >= 0; size--) {
            this.f8697y.get(size).I(f8);
        }
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        for (int size = this.f8697y.size() - 1; size >= 0; size--) {
            this.f8698z.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.f8697y.get(size).b(this.f8698z, this.f8682m, true);
            rectF.union(this.f8698z);
        }
    }

    @Override // w1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == l.A) {
            if (cVar == null) {
                r1.a<Float, Float> aVar = this.f8696x;
                if (aVar != null) {
                    aVar.m(null);
                    return;
                }
                return;
            }
            p pVar = new p(cVar);
            this.f8696x = pVar;
            pVar.a(this);
            k(this.f8696x);
        }
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
        o1.d.a("CompositionLayer#draw");
        this.A.set(0.0f, 0.0f, this.f8684o.j(), this.f8684o.i());
        matrix.mapRect(this.A);
        boolean z7 = this.f8683n.F() && this.f8697y.size() > 1 && i8 != 255;
        if (z7) {
            this.B.setAlpha(i8);
            h.m(canvas, this.A, this.B);
        } else {
            canvas.save();
        }
        if (z7) {
            i8 = 255;
        }
        for (int size = this.f8697y.size() - 1; size >= 0; size--) {
            if (!this.A.isEmpty() ? canvas.clipRect(this.A) : true) {
                this.f8697y.get(size).g(canvas, matrix, i8);
            }
        }
        canvas.restore();
        o1.d.b("CompositionLayer#draw");
    }
}
