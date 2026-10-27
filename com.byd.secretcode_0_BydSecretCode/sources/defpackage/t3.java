package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import defpackage.z3;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class t3 extends v3 {

    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class a implements z3.a {
        public a() {
        }

        @Override // z3.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    @Override // defpackage.v3, defpackage.x3
    public void o() {
        z3.b = new a();
    }
}
