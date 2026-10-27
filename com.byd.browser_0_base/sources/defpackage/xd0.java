package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import defpackage.td;

/* JADX INFO: compiled from: DrawingDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class xd0<S extends td> {
    public final S a;
    public vd0 b;

    public xd0(S s) {
        this.a = s;
    }

    public abstract void a(Canvas canvas, float f);

    public abstract void b(Canvas canvas, Paint paint, float f, float f2, int i);

    public abstract void c(Canvas canvas, Paint paint);

    public abstract int d();

    public abstract int e();
}
