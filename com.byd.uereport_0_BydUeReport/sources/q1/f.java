package q1;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: EllipseContent.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements m, a.b, k {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o1.g f7269c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r1.a<?, PointF> f7270d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r1.a<?, PointF> f7271e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final v1.a f7272f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7274h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7267a = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f7273g = new b();

    public f(o1.g gVar, w1.a aVar, v1.a aVar2) {
        this.f7268b = aVar2.b();
        this.f7269c = gVar;
        r1.a<PointF, PointF> aVarA = aVar2.d().a();
        this.f7270d = aVarA;
        r1.a<PointF, PointF> aVarA2 = aVar2.c().a();
        this.f7271e = aVarA2;
        this.f7272f = aVar2;
        aVar.k(aVarA);
        aVar.k(aVarA2);
        aVarA.a(this);
        aVarA2.a(this);
    }

    @Override // q1.m
    public Path a() {
        if (this.f7274h) {
            return this.f7267a;
        }
        this.f7267a.reset();
        if (this.f7272f.e()) {
            this.f7274h = true;
            return this.f7267a;
        }
        PointF pointFH = this.f7270d.h();
        float f8 = pointFH.x / 2.0f;
        float f9 = pointFH.y / 2.0f;
        float f10 = f8 * 0.55228f;
        float f11 = 0.55228f * f9;
        this.f7267a.reset();
        if (this.f7272f.f()) {
            float f12 = -f9;
            this.f7267a.moveTo(0.0f, f12);
            float f13 = 0.0f - f10;
            float f14 = -f8;
            float f15 = 0.0f - f11;
            this.f7267a.cubicTo(f13, f12, f14, f15, f14, 0.0f);
            float f16 = f11 + 0.0f;
            this.f7267a.cubicTo(f14, f16, f13, f9, 0.0f, f9);
            float f17 = f10 + 0.0f;
            this.f7267a.cubicTo(f17, f9, f8, f16, f8, 0.0f);
            this.f7267a.cubicTo(f8, f15, f17, f12, 0.0f, f12);
        } else {
            float f18 = -f9;
            this.f7267a.moveTo(0.0f, f18);
            float f19 = f10 + 0.0f;
            float f20 = 0.0f - f11;
            this.f7267a.cubicTo(f19, f18, f8, f20, f8, 0.0f);
            float f21 = f11 + 0.0f;
            this.f7267a.cubicTo(f8, f21, f19, f9, 0.0f, f9);
            float f22 = 0.0f - f10;
            float f23 = -f8;
            this.f7267a.cubicTo(f22, f9, f23, f21, f23, 0.0f);
            this.f7267a.cubicTo(f23, f20, f22, f18, 0.0f, f18);
        }
        PointF pointFH2 = this.f7271e.h();
        this.f7267a.offset(pointFH2.x, pointFH2.y);
        this.f7267a.close();
        this.f7273g.b(this.f7267a);
        this.f7274h = true;
        return this.f7267a;
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
                    this.f7273g.a(sVar);
                    sVar.e(this);
                }
            }
        }
    }

    public final void e() {
        this.f7274h = false;
        this.f7269c.invalidateSelf();
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.c
    public String h() {
        return this.f7268b;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (t7 == o1.l.f6834g) {
            this.f7270d.m(cVar);
        } else if (t7 == o1.l.f6837j) {
            this.f7271e.m(cVar);
        }
    }
}
