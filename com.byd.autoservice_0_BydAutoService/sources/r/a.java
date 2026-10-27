package r;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import r.g;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: r.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class C0056a implements g.a {
        public C0056a() {
        }

        @Override // r.g.a
        public void a(Canvas canvas, RectF rectF, float f3, Paint paint) {
            canvas.drawRoundRect(rectF, f3, f3, paint);
        }
    }

    @Override // r.c, r.e
    public void o() {
        g.f4657b = new C0056a();
    }
}
