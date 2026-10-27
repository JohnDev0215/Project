package Q3;

import A7.D;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f implements l, R3.a, c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final O3.j f6146b;
    public final R3.h c;
    public final R3.e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final W3.a f6147e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6149g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6145a = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final D f6148f = new D(1, false);

    public f(O3.j jVar, X3.b bVar, W3.a aVar) {
        aVar.getClass();
        this.f6146b = jVar;
        R3.e eVarT0 = aVar.f9759b.t0();
        this.c = (R3.h) eVarT0;
        R3.e eVarT02 = aVar.f9758a.t0();
        this.d = eVarT02;
        this.f6147e = aVar;
        bVar.d(eVarT0);
        bVar.d(eVarT02);
        eVarT0.a(this);
        eVarT02.a(this);
    }

    @Override // R3.a
    public final void b() {
        this.f6149g = false;
        this.f6146b.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = (ArrayList) list;
            if (i7 >= arrayList.size()) {
                return;
            }
            c cVar = (c) arrayList.get(i7);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.c == 1) {
                    this.f6148f.l.add(sVar);
                    sVar.d(this);
                }
            }
            i7++;
        }
    }

    @Override // Q3.l
    public final Path e() {
        boolean z6 = this.f6149g;
        Path path = this.f6145a;
        if (z6) {
            return path;
        }
        path.reset();
        W3.a aVar = this.f6147e;
        if (aVar.d) {
            this.f6149g = true;
            return path;
        }
        PointF pointF = (PointF) this.c.d();
        float f10 = pointF.x / 2.0f;
        float f11 = pointF.y / 2.0f;
        float f12 = f10 * 0.55228f;
        float f13 = f11 * 0.55228f;
        path.reset();
        if (aVar.c) {
            float f14 = -f11;
            path.moveTo(0.0f, f14);
            float f15 = 0.0f - f12;
            float f16 = -f10;
            float f17 = 0.0f - f13;
            path.cubicTo(f15, f14, f16, f17, f16, 0.0f);
            float f18 = f13 + 0.0f;
            path.cubicTo(f16, f18, f15, f11, 0.0f, f11);
            float f19 = f12 + 0.0f;
            path.cubicTo(f19, f11, f10, f18, f10, 0.0f);
            path.cubicTo(f10, f17, f19, f14, 0.0f, f14);
        } else {
            float f20 = -f11;
            path.moveTo(0.0f, f20);
            float f21 = f12 + 0.0f;
            float f22 = 0.0f - f13;
            path.cubicTo(f21, f20, f10, f22, f10, 0.0f);
            float f23 = f13 + 0.0f;
            path.cubicTo(f10, f23, f21, f11, 0.0f, f11);
            float f24 = 0.0f - f12;
            float f25 = -f10;
            path.cubicTo(f24, f11, f25, f23, f25, 0.0f);
            path.cubicTo(f25, f22, f24, f20, 0.0f, f20);
        }
        PointF pointF2 = (PointF) this.d.d();
        path.offset(pointF2.x, pointF2.y);
        path.close();
        this.f6148f.l(path);
        this.f6149g = true;
        return path;
    }
}
