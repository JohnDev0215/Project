package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: compiled from: LayoutResource.java */
/* JADX INFO: loaded from: classes3.dex */
public final class k61 {
    public final RectF a;
    public final RectF b;

    public k61(float f, pb2 pb2Var) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        l50 l50VarE = pb2Var.e();
        if (l50VarE != null) {
            rect = (Rect) l50VarE.a;
            rect2 = (Rect) l50VarE.d;
        }
        Rect rectD = pb2Var.d();
        this.a = new RectF(rect.left * f, rect.top * f, rect.right * f, rect.bottom * f);
        this.b = new RectF(rectD.left * f, rectD.top * f, rectD.right * f, rectD.bottom * f);
        new RectF(rect2.left * f, rect2.top * f, rect2.right * f, rect2.bottom * f);
    }
}
