package u;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import u.g;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: u.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class C0126a implements g.a {
        public C0126a() {
        }

        @Override // u.g.a
        public void a(Canvas canvas, RectF rectF, float f8, Paint paint) {
            canvas.drawRoundRect(rectF, f8, f8, paint);
        }
    }

    @Override // u.c, u.e
    public void g() {
        g.f8102b = new C0126a();
    }
}
