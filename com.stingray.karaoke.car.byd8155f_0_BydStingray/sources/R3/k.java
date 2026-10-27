package R3;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import b4.C0995a;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class k extends i {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final PointF f7404h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f7405i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f7406j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final PathMeasure f7407k;
    public j l;

    public k(ArrayList arrayList) {
        super(arrayList);
        this.f7404h = new PointF();
        this.f7405i = new float[2];
        this.f7406j = new float[2];
        this.f7407k = new PathMeasure();
    }

    @Override // R3.e
    public final Object e(C0995a c0995a, float f10) {
        j jVar = (j) c0995a;
        Path path = jVar.f7402q;
        if (path == null) {
            return (PointF) c0995a.f12212b;
        }
        j jVar2 = this.l;
        PathMeasure pathMeasure = this.f7407k;
        if (jVar2 != jVar) {
            pathMeasure.setPath(path, false);
            this.l = jVar;
        }
        float length = pathMeasure.getLength();
        float f11 = f10 * length;
        float[] fArr = this.f7405i;
        float[] fArr2 = this.f7406j;
        pathMeasure.getPosTan(f11, fArr, fArr2);
        PointF pointF = this.f7404h;
        pointF.set(fArr[0], fArr[1]);
        if (f11 < 0.0f) {
            pointF.offset(fArr2[0] * f11, fArr2[1] * f11);
            return pointF;
        }
        if (f11 <= length) {
            return pointF;
        }
        float f12 = f11 - length;
        pointF.offset(fArr2[0] * f12, fArr2[1] * f12);
        return pointF;
    }
}
