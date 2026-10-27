package q1;

import android.graphics.Path;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: ShapeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements m, a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7357b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f7358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o1.g f7359d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final r1.a<?, Path> f7360e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7361f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7356a = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f7362g = new b();

    public q(o1.g gVar, w1.a aVar, v1.k kVar) {
        this.f7357b = kVar.b();
        this.f7358c = kVar.d();
        this.f7359d = gVar;
        r1.a<v1.h, Path> aVarA = kVar.c().a();
        this.f7360e = aVarA;
        aVar.k(aVarA);
        aVarA.a(this);
    }

    @Override // q1.m
    public Path a() {
        if (this.f7361f) {
            return this.f7356a;
        }
        this.f7356a.reset();
        if (this.f7358c) {
            this.f7361f = true;
            return this.f7356a;
        }
        this.f7356a.set(this.f7360e.h());
        this.f7356a.setFillType(Path.FillType.EVEN_ODD);
        this.f7362g.b(this.f7356a);
        this.f7361f = true;
        return this.f7356a;
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
                    this.f7362g.a(sVar);
                    sVar.e(this);
                }
            }
        }
    }

    public final void e() {
        this.f7361f = false;
        this.f7359d.invalidateSelf();
    }
}
