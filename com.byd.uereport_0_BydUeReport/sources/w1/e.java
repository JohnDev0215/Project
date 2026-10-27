package w1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.model.layer.Layer;
import java.util.Collections;
import java.util.List;
import v1.j;

/* JADX INFO: compiled from: ShapeLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends a {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final q1.d f8703x;

    public e(o1.g gVar, Layer layer) {
        super(gVar, layer);
        q1.d dVar = new q1.d(gVar, this, new j("__container", layer.l(), false));
        this.f8703x = dVar;
        dVar.d(Collections.emptyList(), Collections.emptyList());
    }

    @Override // w1.a
    public void F(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        this.f8703x.f(dVar, i8, list, dVar2);
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        this.f8703x.b(rectF, this.f8682m, z7);
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
        this.f8703x.g(canvas, matrix, i8);
    }
}
