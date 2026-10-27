package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: renamed from: oN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1271oN extends Drawable {
    public final Paint a = new Paint();
    public final Rect b = new Rect();
    public final Integer c = null;

    public C1271oN(Integer num) {
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Integer num = this.c;
        if (num != null) {
            canvas.drawColor(num.intValue());
        }
        canvas.drawRect(this.b, this.a);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.b.set(0, 0, rect.width(), this.b.height());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
