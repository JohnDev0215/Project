package q1;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: RectangleContent.java */
/* JADX INFO: loaded from: classes.dex */
public class o implements a.b, k, m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f7338c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f7339d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o1.g f7340e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r1.a<?, PointF> f7341f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r1.a<?, PointF> f7342g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r1.a<?, Float> f7343h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f7345j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7336a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RectF f7337b = new RectF();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public b f7344i = new b();

    public o(o1.g gVar, w1.a aVar, v1.f fVar) {
        this.f7338c = fVar.c();
        this.f7339d = fVar.f();
        this.f7340e = gVar;
        r1.a<PointF, PointF> aVarA = fVar.d().a();
        this.f7341f = aVarA;
        r1.a<PointF, PointF> aVarA2 = fVar.e().a();
        this.f7342g = aVarA2;
        r1.a<Float, Float> aVarA3 = fVar.b().a();
        this.f7343h = aVarA3;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVar.k(aVarA3);
        aVarA.a(this);
        aVarA2.a(this);
        aVarA3.a(this);
    }

    @Override // q1.m
    public Path a() {
        if (this.f7345j) {
            return this.f7336a;
        }
        this.f7336a.reset();
        if (this.f7339d) {
            this.f7345j = true;
            return this.f7336a;
        }
        PointF pointFH = this.f7342g.h();
        float f8 = pointFH.x / 2.0f;
        float f9 = pointFH.y / 2.0f;
        r1.a<?, Float> aVar = this.f7343h;
        float fO = aVar == null ? 0.0f : ((r1.c) aVar).o();
        float fMin = Math.min(f8, f9);
        if (fO > fMin) {
            fO = fMin;
        }
        PointF pointFH2 = this.f7341f.h();
        this.f7336a.moveTo(pointFH2.x + f8, (pointFH2.y - f9) + fO);
        this.f7336a.lineTo(pointFH2.x + f8, (pointFH2.y + f9) - fO);
        if (fO > 0.0f) {
            RectF rectF = this.f7337b;
            float f10 = pointFH2.x;
            float f11 = fO * 2.0f;
            float f12 = pointFH2.y;
            rectF.set((f10 + f8) - f11, (f12 + f9) - f11, f10 + f8, f12 + f9);
            this.f7336a.arcTo(this.f7337b, 0.0f, 90.0f, false);
        }
        this.f7336a.lineTo((pointFH2.x - f8) + fO, pointFH2.y + f9);
        if (fO > 0.0f) {
            RectF rectF2 = this.f7337b;
            float f13 = pointFH2.x;
            float f14 = pointFH2.y;
            float f15 = fO * 2.0f;
            rectF2.set(f13 - f8, (f14 + f9) - f15, (f13 - f8) + f15, f14 + f9);
            this.f7336a.arcTo(this.f7337b, 90.0f, 90.0f, false);
        }
        this.f7336a.lineTo(pointFH2.x - f8, (pointFH2.y - f9) + fO);
        if (fO > 0.0f) {
            RectF rectF3 = this.f7337b;
            float f16 = pointFH2.x;
            float f17 = pointFH2.y;
            float f18 = fO * 2.0f;
            rectF3.set(f16 - f8, f17 - f9, (f16 - f8) + f18, (f17 - f9) + f18);
            this.f7336a.arcTo(this.f7337b, 180.0f, 90.0f, false);
        }
        this.f7336a.lineTo((pointFH2.x + f8) - fO, pointFH2.y - f9);
        if (fO > 0.0f) {
            RectF rectF4 = this.f7337b;
            float f19 = pointFH2.x;
            float f20 = fO * 2.0f;
            float f21 = pointFH2.y;
            rectF4.set((f19 + f8) - f20, f21 - f9, f19 + f8, (f21 - f9) + f20);
            this.f7336a.arcTo(this.f7337b, 270.0f, 90.0f, false);
        }
        this.f7336a.close();
        this.f7344i.b(this.f7336a);
        this.f7345j = true;
        return this.f7336a;
    }

    @Override // r1.a.b
    public void c() {
        e();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        for (int i8 = 0; i8 < list.size(); i8++) {
            c cVar = list.get(i8);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.f7344i.a(sVar);
                    sVar.e(this);
                }
            }
        }
    }

    public final void e() {
        this.f7345j = false;
        this.f7340e.invalidateSelf();
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.c
    public String h() {
        return this.f7338c;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (t7 == o1.l.f6835h) {
            this.f7342g.m(cVar);
        } else if (t7 == o1.l.f6837j) {
            this.f7341f.m(cVar);
        } else if (t7 == o1.l.f6836i) {
            this.f7343h.m(cVar);
        }
    }
}
