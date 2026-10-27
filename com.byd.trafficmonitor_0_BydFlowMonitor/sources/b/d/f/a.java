package b.d.f;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import b.d.f.g;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: b.d.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class C0023a implements g.a {
        public C0023a() {
        }

        @Override // b.d.f.g.a
        public void a(Canvas canvas, RectF rectF, float f2, Paint paint) {
            canvas.drawRoundRect(rectF, f2, f2, paint);
        }
    }

    @Override // b.d.f.c, b.d.f.e
    public void o() {
        g.f1920b = new C0023a();
    }
}
