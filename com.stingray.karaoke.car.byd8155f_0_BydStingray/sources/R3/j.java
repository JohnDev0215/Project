package R3;

import a4.AbstractC0927h;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import b4.C0995a;

/* JADX INFO: loaded from: classes.dex */
public final class j extends C0995a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Path f7402q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0995a f7403r;

    public j(O3.a aVar, C0995a c0995a) {
        super(aVar, (PointF) c0995a.f12212b, (PointF) c0995a.c, c0995a.d, c0995a.f12213e, c0995a.f12214f, c0995a.f12215g, c0995a.f12216h);
        this.f7403r = c0995a;
        d();
    }

    public final void d() {
        Object obj;
        Object obj2 = this.c;
        Object obj3 = this.f12212b;
        boolean z6 = (obj2 == null || obj3 == null || !((PointF) obj3).equals(((PointF) obj2).x, ((PointF) obj2).y)) ? false : true;
        if (obj3 == null || (obj = this.c) == null || z6) {
            return;
        }
        PointF pointF = (PointF) obj3;
        PointF pointF2 = (PointF) obj;
        C0995a c0995a = this.f7403r;
        PointF pointF3 = c0995a.o;
        PointF pointF4 = c0995a.f12222p;
        Matrix matrix = AbstractC0927h.f11132a;
        Path path = new Path();
        path.moveTo(pointF.x, pointF.y);
        if (pointF3 == null || pointF4 == null || (pointF3.length() == 0.0f && pointF4.length() == 0.0f)) {
            path.lineTo(pointF2.x, pointF2.y);
        } else {
            float f10 = pointF3.x + pointF.x;
            float f11 = pointF.y + pointF3.y;
            float f12 = pointF2.x;
            float f13 = f12 + pointF4.x;
            float f14 = pointF2.y;
            path.cubicTo(f10, f11, f13, f14 + pointF4.y, f12, f14);
        }
        this.f7402q = path;
    }
}
