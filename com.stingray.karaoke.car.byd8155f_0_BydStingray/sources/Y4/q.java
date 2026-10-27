package Y4;

import android.graphics.Matrix;
import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public final class q extends r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f10774b;
    public float c;

    @Override // Y4.r
    public final void a(Matrix matrix, Path path) {
        Matrix matrix2 = this.f10775a;
        matrix.invert(matrix2);
        path.transform(matrix2);
        path.lineTo(this.f10774b, this.c);
        path.transform(matrix);
    }
}
