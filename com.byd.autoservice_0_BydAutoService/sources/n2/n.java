package n2;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o[] f4177a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f4178b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f4179c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f4180d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f4181e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f4182f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f4183g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f4184h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f4185i = new float[2];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4186j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i3);

        void b(o oVar, Matrix matrix, int i3);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f4187a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f4188b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f4189c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f4190d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4191e;

        public b(m mVar, float f3, RectF rectF, a aVar, Path path) {
            this.f4190d = aVar;
            this.f4187a = mVar;
            this.f4191e = f3;
            this.f4189c = rectF;
            this.f4188b = path;
        }
    }

    public n() {
        for (int i3 = 0; i3 < 4; i3++) {
            this.f4177a[i3] = new o();
            this.f4178b[i3] = new Matrix();
            this.f4179c[i3] = new Matrix();
        }
    }

    public final float a(int i3) {
        return (i3 + 1) * 90;
    }

    public final void b(b bVar, int i3) {
        this.f4184h[0] = this.f4177a[i3].k();
        this.f4184h[1] = this.f4177a[i3].l();
        this.f4178b[i3].mapPoints(this.f4184h);
        if (i3 == 0) {
            Path path = bVar.f4188b;
            float[] fArr = this.f4184h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f4188b;
            float[] fArr2 = this.f4184h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f4177a[i3].d(this.f4178b[i3], bVar.f4188b);
        a aVar = bVar.f4190d;
        if (aVar != null) {
            aVar.b(this.f4177a[i3], this.f4178b[i3], i3);
        }
    }

    public final void c(b bVar, int i3) {
        int i4 = (i3 + 1) % 4;
        this.f4184h[0] = this.f4177a[i3].i();
        this.f4184h[1] = this.f4177a[i3].j();
        this.f4178b[i3].mapPoints(this.f4184h);
        this.f4185i[0] = this.f4177a[i4].k();
        this.f4185i[1] = this.f4177a[i4].l();
        this.f4178b[i4].mapPoints(this.f4185i);
        float f3 = this.f4184h[0];
        float[] fArr = this.f4185i;
        float fMax = Math.max(((float) Math.hypot(f3 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f4189c, i3);
        this.f4183g.n(0.0f, 0.0f);
        f fVarJ = j(i3, bVar.f4187a);
        fVarJ.b(fMax, fI, bVar.f4191e, this.f4183g);
        Path path = new Path();
        this.f4183g.d(this.f4179c[i3], path);
        if (this.f4186j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i3) || k(path, i4))) {
            path.op(path, this.f4182f, Path.Op.DIFFERENCE);
            this.f4184h[0] = this.f4183g.k();
            this.f4184h[1] = this.f4183g.l();
            this.f4179c[i3].mapPoints(this.f4184h);
            Path path2 = this.f4181e;
            float[] fArr2 = this.f4184h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f4183g.d(this.f4179c[i3], this.f4181e);
        } else {
            this.f4183g.d(this.f4179c[i3], bVar.f4188b);
        }
        a aVar = bVar.f4190d;
        if (aVar != null) {
            aVar.a(this.f4183g, this.f4179c[i3], i3);
        }
    }

    public void d(m mVar, float f3, RectF rectF, Path path) {
        e(mVar, f3, rectF, null, path);
    }

    public void e(m mVar, float f3, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.f4181e.rewind();
        this.f4182f.rewind();
        this.f4182f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mVar, f3, rectF, aVar, path);
        for (int i3 = 0; i3 < 4; i3++) {
            l(bVar, i3);
            m(i3);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            b(bVar, i4);
            c(bVar, i4);
        }
        path.close();
        this.f4181e.close();
        if (Build.VERSION.SDK_INT < 19 || this.f4181e.isEmpty()) {
            return;
        }
        path.op(this.f4181e, Path.Op.UNION);
    }

    public final void f(int i3, RectF rectF, PointF pointF) {
        if (i3 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i3 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i3 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    public final c g(int i3, m mVar) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    public final d h(int i3, m mVar) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    public final float i(RectF rectF, int i3) {
        float[] fArr = this.f4184h;
        o[] oVarArr = this.f4177a;
        fArr[0] = oVarArr[i3].f4194c;
        fArr[1] = oVarArr[i3].f4195d;
        this.f4178b[i3].mapPoints(fArr);
        return (i3 == 1 || i3 == 3) ? Math.abs(rectF.centerX() - this.f4184h[0]) : Math.abs(rectF.centerY() - this.f4184h[1]);
    }

    public final f j(int i3, m mVar) {
        return i3 != 1 ? i3 != 2 ? i3 != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    public final boolean k(Path path, int i3) {
        Path path2 = new Path();
        this.f4177a[i3].d(this.f4178b[i3], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    public final void l(b bVar, int i3) {
        h(i3, bVar.f4187a).b(this.f4177a[i3], 90.0f, bVar.f4191e, bVar.f4189c, g(i3, bVar.f4187a));
        float fA = a(i3);
        this.f4178b[i3].reset();
        f(i3, bVar.f4189c, this.f4180d);
        Matrix matrix = this.f4178b[i3];
        PointF pointF = this.f4180d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f4178b[i3].preRotate(fA);
    }

    public final void m(int i3) {
        this.f4184h[0] = this.f4177a[i3].i();
        this.f4184h[1] = this.f4177a[i3].j();
        this.f4178b[i3].mapPoints(this.f4184h);
        float fA = a(i3);
        this.f4179c[i3].reset();
        Matrix matrix = this.f4179c[i3];
        float[] fArr = this.f4184h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f4179c[i3].preRotate(fA);
    }
}
