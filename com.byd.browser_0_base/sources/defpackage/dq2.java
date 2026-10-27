package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import defpackage.pn3;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: SoftwareDraw.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dq2 implements pn3.a {
    public Bitmap a;

    @Override // pn3.a
    public final boolean a() {
        return false;
    }

    @Override // pn3.a
    public final void b() {
        this.a = null;
    }

    @Override // pn3.a
    public final boolean d(View view, Rect rect, float f, fm fmVar, l51 l51Var) {
        TraceEvent traceEventH0 = TraceEvent.H0("SoftwareDraw:syncCaptureBitmap", null);
        try {
            int width = (int) (view.getWidth() * f);
            int height = (int) (view.getHeight() * f);
            boolean z = width == 0 || height == 0;
            if (z) {
                width = 1;
                height = 1;
            }
            Bitmap bitmap = this.a;
            if (bitmap != null && (bitmap.getWidth() != width || this.a.getHeight() != height)) {
                this.a.recycle();
                this.a = null;
            }
            if (this.a == null) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                bitmapCreateBitmap.setHasAlpha(true);
                this.a = bitmapCreateBitmap;
            }
            if (z) {
                this.a.setPixel(0, 0, 0);
            } else {
                hm.a(new Canvas(this.a), view, rect, f, true, fmVar);
            }
            l51Var.onResult(this.a);
            boolean z2 = !z;
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return z2;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // pn3.a
    public final void c(View view, float f) {
    }
}
