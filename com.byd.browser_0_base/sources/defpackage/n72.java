package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import defpackage.p72;

/* JADX INFO: compiled from: PulseDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n72 implements p72.e {
    public final /* synthetic */ p72.c a;

    public n72(p72.c cVar) {
        this.a = cVar;
    }

    @Override // p72.e
    public final void a(p72 p72Var, float f) {
        p72Var.invalidateSelf();
    }

    @Override // p72.e
    public final void b(p72 p72Var, Paint paint, Canvas canvas, float f) {
        Rect bounds = p72Var.getBounds();
        p72.c cVar = this.a;
        float fA = cVar.a(bounds);
        canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), C0173a.f(cVar.b(bounds), fA, 1.0f - f, fA), paint);
    }
}
