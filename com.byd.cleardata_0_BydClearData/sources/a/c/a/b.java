package a.c.a;

import a.c.a.h;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
class b extends d {

    class a implements h.a {
        a() {
        }

        @Override // a.c.a.h.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    b() {
    }

    @Override // a.c.a.d, a.c.a.f
    public void m() {
        h.f128b = new a();
    }
}
