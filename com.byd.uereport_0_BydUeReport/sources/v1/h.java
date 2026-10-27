package v1;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ShapeData.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<t1.a> f8552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public PointF f8553b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f8554c;

    public h(PointF pointF, boolean z7, List<t1.a> list) {
        this.f8553b = pointF;
        this.f8554c = z7;
        this.f8552a = new ArrayList(list);
    }

    public List<t1.a> a() {
        return this.f8552a;
    }

    public PointF b() {
        return this.f8553b;
    }

    public void c(h hVar, h hVar2, float f8) {
        if (this.f8553b == null) {
            this.f8553b = new PointF();
        }
        this.f8554c = hVar.d() || hVar2.d();
        if (hVar.a().size() != hVar2.a().size()) {
            a2.d.c("Curves must have the same number of control points. Shape 1: " + hVar.a().size() + "\tShape 2: " + hVar2.a().size());
        }
        int iMin = Math.min(hVar.a().size(), hVar2.a().size());
        if (this.f8552a.size() < iMin) {
            for (int size = this.f8552a.size(); size < iMin; size++) {
                this.f8552a.add(new t1.a());
            }
        } else if (this.f8552a.size() > iMin) {
            for (int size2 = this.f8552a.size() - 1; size2 >= iMin; size2--) {
                List<t1.a> list = this.f8552a;
                list.remove(list.size() - 1);
            }
        }
        PointF pointFB = hVar.b();
        PointF pointFB2 = hVar2.b();
        e(a2.g.j(pointFB.x, pointFB2.x, f8), a2.g.j(pointFB.y, pointFB2.y, f8));
        for (int size3 = this.f8552a.size() - 1; size3 >= 0; size3--) {
            t1.a aVar = hVar.a().get(size3);
            t1.a aVar2 = hVar2.a().get(size3);
            PointF pointFA = aVar.a();
            PointF pointFB3 = aVar.b();
            PointF pointFC = aVar.c();
            PointF pointFA2 = aVar2.a();
            PointF pointFB4 = aVar2.b();
            PointF pointFC2 = aVar2.c();
            this.f8552a.get(size3).d(a2.g.j(pointFA.x, pointFA2.x, f8), a2.g.j(pointFA.y, pointFA2.y, f8));
            this.f8552a.get(size3).e(a2.g.j(pointFB3.x, pointFB4.x, f8), a2.g.j(pointFB3.y, pointFB4.y, f8));
            this.f8552a.get(size3).f(a2.g.j(pointFC.x, pointFC2.x, f8), a2.g.j(pointFC.y, pointFC2.y, f8));
        }
    }

    public boolean d() {
        return this.f8554c;
    }

    public final void e(float f8, float f9) {
        if (this.f8553b == null) {
            this.f8553b = new PointF();
        }
        this.f8553b.set(f8, f9);
    }

    public String toString() {
        return "ShapeData{numCurves=" + this.f8552a.size() + "closed=" + this.f8554c + '}';
    }

    public h() {
        this.f8552a = new ArrayList();
    }
}
