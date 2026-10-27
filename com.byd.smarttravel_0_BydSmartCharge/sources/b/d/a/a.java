package b.d.a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import b.d.a.g;

/* JADX INFO: compiled from: CardViewApi17Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: b.d.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CardViewApi17Impl.java */
    public class C0009a implements g.a {
        public C0009a() {
        }

        @Override // b.d.a.g.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    @Override // b.d.a.c, b.d.a.e
    public void o() {
        g.f875b = new C0009a();
    }
}
