package I4;

import A7.m;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.google.accompanist.drawablepainter.DrawablePainter;
import h6.j;
import m0.f;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Drawable.Callback {
    public final /* synthetic */ DrawablePainter l;

    public a(DrawablePainter drawablePainter) {
        this.l = drawablePainter;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        m.f(drawable, "d");
        DrawablePainter drawablePainter = this.l;
        drawablePainter.f12735r.setValue(Integer.valueOf(((Number) drawablePainter.f12735r.getValue()).intValue() + 1));
        Object obj = c.f2942a;
        Drawable drawable2 = drawablePainter.f12734q;
        drawablePainter.f12736s.setValue(new f((drawable2.getIntrinsicWidth() < 0 || drawable2.getIntrinsicHeight() < 0) ? 9205357640488583168L : j.f(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight())));
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, m7.f] */
    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        m.f(drawable, "d");
        m.f(runnable, "what");
        ((Handler) c.f2942a.getValue()).postAtTime(runnable, j3);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, m7.f] */
    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        m.f(drawable, "d");
        m.f(runnable, "what");
        ((Handler) c.f2942a.getValue()).removeCallbacks(runnable);
    }
}
