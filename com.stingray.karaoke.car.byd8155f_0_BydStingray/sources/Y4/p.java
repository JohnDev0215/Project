package Y4;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public final class p extends r {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final RectF f10769h = new RectF();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f10770b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f10771e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f10772f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f10773g;

    public p(float f10, float f11, float f12, float f13) {
        this.f10770b = f10;
        this.c = f11;
        this.d = f12;
        this.f10771e = f13;
    }

    @Override // Y4.r
    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f10775a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        RectF rectF = f10769h;
        rectF.set(this.f10770b, this.c, this.d, this.f10771e);
        path.arcTo(rectF, this.f10772f, this.f10773g, false);
        path.transform(matrix);
    }
}
