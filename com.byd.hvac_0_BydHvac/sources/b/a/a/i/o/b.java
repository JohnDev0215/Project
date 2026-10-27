package b.a.a.i.o;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RectF f933b;
    public int e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f934c = ViewCompat.MEASURED_STATE_MASK;
    public int d = 0;
    public int f = 1;

    public b(Context context) {
        this.e = a(context, 2.0f);
        Paint paint = new Paint();
        this.f932a = paint;
        paint.setColor(this.f934c);
        this.f932a.setAntiAlias(true);
    }

    public int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void b(int i) {
        this.f932a.setColor(i);
    }

    public void c(int i, int i2) {
        this.e = i;
        this.d = i2;
        invalidateSelf();
    }

    public void d(int i) {
        if (i != 0 && i != 1 && i != 2) {
            throw new IllegalArgumentException("Gravity must be one of 0(DividerDrawable.TOP)、1(DividerDrawable.CENTER) and 2(DividerDrawable.BOTTOM)");
        }
        this.f = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawColor(0);
        int i = this.d;
        if (i != 0) {
            float f = i;
            RectF rectF = this.f933b;
            float f2 = rectF.bottom;
            if (f < f2) {
                int i2 = (int) ((f2 - i) / 2.0f);
                int i3 = this.f;
                if (i3 == 0) {
                    canvas.drawRect(rectF.left, rectF.top, rectF.right, f2 - (i2 * 2), this.f932a);
                    return;
                }
                if (i3 == 1) {
                    float f3 = i2;
                    canvas.drawRect(rectF.left, rectF.top + f3, rectF.right, f2 - f3, this.f932a);
                    return;
                } else {
                    if (i3 != 2) {
                        return;
                    }
                    canvas.drawRect(rectF.left, rectF.top + (i2 * 2), rectF.right, f2, this.f932a);
                    return;
                }
            }
        }
        canvas.drawRect(this.f933b, this.f932a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.f932a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.f933b = new RectF(i, i2, i3, i4);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f932a.setColorFilter(colorFilter);
    }
}
