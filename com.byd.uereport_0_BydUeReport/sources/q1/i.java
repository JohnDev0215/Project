package q1;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import com.airbnb.lottie.model.content.GradientType;

/* JADX INFO: compiled from: GradientStrokeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends a {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final String f7303o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f7304p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final v.d<LinearGradient> f7305q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final v.d<RadialGradient> f7306r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final RectF f7307s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final GradientType f7308t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f7309u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final r1.a<v1.c, v1.c> f7310v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final r1.a<PointF, PointF> f7311w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final r1.a<PointF, PointF> f7312x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public r1.p f7313y;

    public i(o1.g gVar, w1.a aVar, v1.e eVar) {
        super(gVar, aVar, eVar.b().toPaintCap(), eVar.g().toPaintJoin(), eVar.i(), eVar.k(), eVar.m(), eVar.h(), eVar.c());
        this.f7305q = new v.d<>();
        this.f7306r = new v.d<>();
        this.f7307s = new RectF();
        this.f7303o = eVar.j();
        this.f7308t = eVar.f();
        this.f7304p = eVar.n();
        this.f7309u = (int) (gVar.m().d() / 32.0f);
        r1.a<v1.c, v1.c> aVarA = eVar.e().a();
        this.f7310v = aVarA;
        aVarA.a(this);
        aVar.k(aVarA);
        r1.a<PointF, PointF> aVarA2 = eVar.l().a();
        this.f7311w = aVarA2;
        aVarA2.a(this);
        aVar.k(aVarA2);
        r1.a<PointF, PointF> aVarA3 = eVar.d().a();
        this.f7312x = aVarA3;
        aVarA3.a(this);
        aVar.k(aVarA3);
    }

    @Override // q1.a, q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        if (this.f7304p) {
            return;
        }
        b(this.f7307s, matrix, false);
        Shader shaderM = this.f7308t == GradientType.LINEAR ? m() : n();
        shaderM.setLocalMatrix(matrix);
        this.f7247i.setShader(shaderM);
        super.g(canvas, matrix, i8);
    }

    @Override // q1.c
    public String h() {
        return this.f7303o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // q1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == o1.l.D) {
            r1.p pVar = this.f7313y;
            if (pVar != null) {
                this.f7244f.E(pVar);
            }
            if (cVar == null) {
                this.f7313y = null;
                return;
            }
            r1.p pVar2 = new r1.p(cVar);
            this.f7313y = pVar2;
            pVar2.a(this);
            this.f7244f.k(this.f7313y);
        }
    }

    public final int[] k(int[] iArr) {
        r1.p pVar = this.f7313y;
        if (pVar != null) {
            Integer[] numArr = (Integer[]) pVar.h();
            int i8 = 0;
            if (iArr.length == numArr.length) {
                while (i8 < iArr.length) {
                    iArr[i8] = numArr[i8].intValue();
                    i8++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i8 < numArr.length) {
                    iArr[i8] = numArr[i8].intValue();
                    i8++;
                }
            }
        }
        return iArr;
    }

    public final int l() {
        int iRound = Math.round(this.f7311w.f() * this.f7309u);
        int iRound2 = Math.round(this.f7312x.f() * this.f7309u);
        int iRound3 = Math.round(this.f7310v.f() * this.f7309u);
        int i8 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i8 = i8 * 31 * iRound2;
        }
        return iRound3 != 0 ? i8 * 31 * iRound3 : i8;
    }

    public final LinearGradient m() {
        long jL = l();
        LinearGradient linearGradientF = this.f7305q.f(jL);
        if (linearGradientF != null) {
            return linearGradientF;
        }
        PointF pointFH = this.f7311w.h();
        PointF pointFH2 = this.f7312x.h();
        v1.c cVarH = this.f7310v.h();
        LinearGradient linearGradient = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, k(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.f7305q.k(jL, linearGradient);
        return linearGradient;
    }

    public final RadialGradient n() {
        long jL = l();
        RadialGradient radialGradientF = this.f7306r.f(jL);
        if (radialGradientF != null) {
            return radialGradientF;
        }
        PointF pointFH = this.f7311w.h();
        PointF pointFH2 = this.f7312x.h();
        v1.c cVarH = this.f7310v.h();
        int[] iArrK = k(cVarH.a());
        float[] fArrB = cVarH.b();
        RadialGradient radialGradient = new RadialGradient(pointFH.x, pointFH.y, (float) Math.hypot(pointFH2.x - r7, pointFH2.y - r8), iArrK, fArrB, Shader.TileMode.CLAMP);
        this.f7306r.k(jL, radialGradient);
        return radialGradient;
    }
}
