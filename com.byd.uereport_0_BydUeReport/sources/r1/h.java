package r1;

import android.graphics.Path;
import android.graphics.PointF;

/* JADX INFO: compiled from: PathKeyframe.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends b2.a<PointF> {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Path f7759o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b2.a<PointF> f7760p;

    public h(o1.e eVar, b2.a<PointF> aVar) {
        super(eVar, aVar.f2045b, aVar.f2046c, aVar.f2047d, aVar.f2048e, aVar.f2049f);
        this.f7760p = aVar;
        i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i() {
        T t7;
        T t8 = this.f2046c;
        boolean z7 = (t8 == 0 || (t7 = this.f2045b) == 0 || !((PointF) t7).equals(((PointF) t8).x, ((PointF) t8).y)) ? false : true;
        T t9 = this.f2046c;
        if (t9 == 0 || z7) {
            return;
        }
        b2.a<PointF> aVar = this.f7760p;
        this.f7759o = a2.h.d((PointF) this.f2045b, (PointF) t9, aVar.f2056m, aVar.f2057n);
    }

    public Path j() {
        return this.f7759o;
    }
}
