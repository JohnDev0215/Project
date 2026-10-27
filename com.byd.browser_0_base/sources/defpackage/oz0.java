package defpackage;

import android.animation.ObjectAnimator;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import defpackage.td;

/* JADX INFO: compiled from: IndeterminateDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class oz0<S extends td> extends vd0 {
    public xd0<S> p;
    public nz0<ObjectAnimator> q;

    public oz0(Context context, td tdVar, xd0<S> xd0Var, nz0<ObjectAnimator> nz0Var) {
        super(context, tdVar);
        this.p = xd0Var;
        xd0Var.b = this;
        this.q = nz0Var;
        nz0Var.a = this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect rect = new Rect();
        if (getBounds().isEmpty() || !isVisible() || !canvas.getClipBounds(rect)) {
            return;
        }
        canvas.save();
        xd0<S> xd0Var = this.p;
        float fC = c();
        xd0Var.a.a();
        xd0Var.a(canvas, fC);
        xd0<S> xd0Var2 = this.p;
        Paint paint = this.m;
        xd0Var2.c(canvas, paint);
        int i = 0;
        while (true) {
            nz0<ObjectAnimator> nz0Var = this.q;
            int[] iArr = nz0Var.c;
            if (i >= iArr.length) {
                canvas.restore();
                return;
            }
            xd0<S> xd0Var3 = this.p;
            int i2 = i * 2;
            float[] fArr = nz0Var.b;
            xd0Var3.b(canvas, paint, fArr[i2], fArr[i2 + 1], iArr[i]);
            i++;
        }
    }

    @Override // defpackage.vd0
    public final boolean g(boolean z, boolean z2, boolean z3) {
        boolean zG = super.g(z, z2, z3);
        if (!isRunning()) {
            this.q.a();
        }
        w5 w5Var = this.g;
        ContentResolver contentResolver = this.e.getContentResolver();
        w5Var.getClass();
        Settings.Global.getFloat(contentResolver, "animator_duration_scale", 1.0f);
        if (z && z3) {
            this.q.e();
        }
        return zG;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.p.d();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.p.e();
    }
}
