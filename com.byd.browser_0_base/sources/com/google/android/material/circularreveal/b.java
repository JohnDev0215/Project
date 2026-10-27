package com.google.android.material.circularreveal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.circularreveal.c;
import defpackage.n3;

/* JADX INFO: compiled from: CircularRevealHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final a a;
    public final View b;
    public final Paint c;
    public c.d d;
    public Drawable e;

    /* JADX INFO: compiled from: CircularRevealHelper.java */
    public interface a {
        void c(Canvas canvas);

        boolean d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(a aVar) {
        this.a = aVar;
        View view = (View) aVar;
        this.b = view;
        view.setWillNotDraw(false);
        new Path();
        new Paint(7);
        Paint paint = new Paint(1);
        this.c = paint;
        paint.setColor(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(android.graphics.Canvas r10) {
        /*
            r9 = this;
            com.google.android.material.circularreveal.c$d r0 = r9.d
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L17
            float r0 = r0.c
            r3 = 2139095039(0x7f7fffff, float:3.4028235E38)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 != 0) goto L11
            r0 = r2
            goto L12
        L11:
            r0 = r1
        L12:
            if (r0 == 0) goto L15
            goto L17
        L15:
            r0 = r1
            goto L18
        L17:
            r0 = r2
        L18:
            r0 = r0 ^ r2
            android.graphics.Paint r8 = r9.c
            com.google.android.material.circularreveal.b$a r3 = r9.a
            android.view.View r4 = r9.b
            if (r0 == 0) goto L45
            r3.c(r10)
            int r0 = r8.getColor()
            int r0 = android.graphics.Color.alpha(r0)
            if (r0 == 0) goto L30
            r0 = r2
            goto L31
        L30:
            r0 = r1
        L31:
            if (r0 == 0) goto L68
            r0 = 0
            r5 = 0
            int r3 = r4.getWidth()
            float r6 = (float) r3
            int r3 = r4.getHeight()
            float r7 = (float) r3
            r3 = r10
            r4 = r0
            r3.drawRect(r4, r5, r6, r7, r8)
            goto L68
        L45:
            r3.c(r10)
            int r0 = r8.getColor()
            int r0 = android.graphics.Color.alpha(r0)
            if (r0 == 0) goto L54
            r0 = r2
            goto L55
        L54:
            r0 = r1
        L55:
            if (r0 == 0) goto L68
            r0 = 0
            r5 = 0
            int r3 = r4.getWidth()
            float r6 = (float) r3
            int r3 = r4.getHeight()
            float r7 = (float) r3
            r3 = r10
            r4 = r0
            r3.drawRect(r4, r5, r6, r7, r8)
        L68:
            android.graphics.drawable.Drawable r0 = r9.e
            if (r0 == 0) goto L71
            com.google.android.material.circularreveal.c$d r3 = r9.d
            if (r3 == 0) goto L71
            r1 = r2
        L71:
            if (r1 == 0) goto L9c
            android.graphics.Rect r0 = r0.getBounds()
            com.google.android.material.circularreveal.c$d r1 = r9.d
            float r1 = r1.a
            int r2 = r0.width()
            float r2 = (float) r2
            r3 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 / r3
            float r1 = r1 - r2
            com.google.android.material.circularreveal.c$d r2 = r9.d
            float r2 = r2.b
            int r0 = r0.height()
            float r0 = (float) r0
            float r0 = r0 / r3
            float r2 = r2 - r0
            r10.translate(r1, r2)
            android.graphics.drawable.Drawable r9 = r9.e
            r9.draw(r10)
            float r9 = -r1
            float r0 = -r2
            r10.translate(r9, r0)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.circularreveal.b.a(android.graphics.Canvas):void");
    }

    public final int b() {
        return this.c.getColor();
    }

    public final c.d c() {
        c.d dVar = this.d;
        if (dVar == null) {
            return null;
        }
        c.d dVar2 = new c.d(dVar);
        if (dVar2.c == Float.MAX_VALUE) {
            float f = dVar2.a;
            float f2 = dVar2.b;
            View view = this.b;
            dVar2.c = n3.I(f, f2, view.getWidth(), view.getHeight());
        }
        return dVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d() {
        /*
            r3 = this;
            com.google.android.material.circularreveal.b$a r0 = r3.a
            boolean r0 = r0.d()
            r1 = 0
            if (r0 == 0) goto L24
            com.google.android.material.circularreveal.c$d r3 = r3.d
            r0 = 1
            if (r3 == 0) goto L1f
            float r3 = r3.c
            r2 = 2139095039(0x7f7fffff, float:3.4028235E38)
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 != 0) goto L19
            r3 = r0
            goto L1a
        L19:
            r3 = r1
        L1a:
            if (r3 == 0) goto L1d
            goto L1f
        L1d:
            r3 = r1
            goto L20
        L1f:
            r3 = r0
        L20:
            r3 = r3 ^ r0
            if (r3 != 0) goto L24
            r1 = r0
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.circularreveal.b.d():boolean");
    }

    public final void e(Drawable drawable) {
        this.e = drawable;
        this.b.invalidate();
    }

    public final void f(int i) {
        this.c.setColor(i);
        this.b.invalidate();
    }

    public final void g(c.d dVar) {
        View view = this.b;
        if (dVar == null) {
            this.d = null;
        } else {
            c.d dVar2 = this.d;
            if (dVar2 == null) {
                this.d = new c.d(dVar);
            } else {
                float f = dVar.a;
                float f2 = dVar.b;
                float f3 = dVar.c;
                dVar2.a = f;
                dVar2.b = f2;
                dVar2.c = f3;
            }
            if (dVar.c + 1.0E-4f >= n3.I(dVar.a, dVar.b, (float) view.getWidth(), (float) view.getHeight())) {
                this.d.c = Float.MAX_VALUE;
            }
        }
        view.invalidate();
    }
}
