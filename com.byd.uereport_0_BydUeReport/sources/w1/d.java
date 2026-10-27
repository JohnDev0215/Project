package w1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.model.layer.Layer;

/* JADX INFO: compiled from: NullLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends a {
    public d(o1.g gVar, Layer layer) {
        super(gVar, layer);
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
    }
}
