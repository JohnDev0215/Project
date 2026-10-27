package y6;

import A7.m;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class g extends View {
    public x6.c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint f24172m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final RectF f24173n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f24174p;

    public g(Context context) {
        super(context, null);
        this.f24172m = new Paint();
        this.f24173n = new RectF();
        setWillNotDraw(false);
    }

    public final float getBackgroundOpacity() {
        return this.o;
    }

    public final float getOpacity() {
        return this.f24174p;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        m.f(canvas, "canvas");
        super.onDraw(canvas);
        if (this.l == null) {
            m.j("lyricsConfigs");
            throw null;
        }
        float f10 = (this.o * 0.3f) + 0.3f;
        Paint paint = this.f24172m;
        paint.setAlpha((int) (255 * f10 * this.f24174p));
        canvas.drawRoundRect(this.f24173n, 20.0f, 20.0f, paint);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        this.f24173n.set(0.0f, 0.0f, i7, i10);
    }

    public final void setBackgroundOpacity(float f10) {
        if (this.o != f10) {
            invalidate();
        }
        this.o = f10;
    }

    public final void setOpacity(float f10) {
        if (this.f24174p != f10) {
            invalidate();
        }
        this.f24174p = f10;
    }
}
