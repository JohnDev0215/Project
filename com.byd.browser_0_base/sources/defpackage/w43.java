package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import java.util.Locale;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;

/* JADX INFO: compiled from: TabSwitcherDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w43 extends za3 {
    public final float b;
    public final float c;
    public final Rect d;
    public final TextPaint e;
    public int f;
    public boolean g;

    public w43(Context context, int i, Bitmap bitmap) {
        super(context, bitmap);
        this.d = new Rect();
        c(n3.y0(context));
        this.b = context.getResources().getDimension(R$dimen.toolbar_tab_count_text_size_1_digit);
        this.c = context.getResources().getDimension(R$dimen.toolbar_tab_count_text_size_2_digit);
        TextPaint textPaint = new TextPaint();
        this.e = textPaint;
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-condensed", 1));
        textPaint.setColor(this.a.getColorForState(getState(), 0));
    }

    public static w43 d(Context context, int i) {
        return new w43(context, i, BitmapFactory.decodeResource(context.getResources(), R$drawable.btn_tabswitcher_modern));
    }

    @Override // defpackage.za3
    public final void c(ColorStateList colorStateList) {
        super.c(colorStateList);
        TextPaint textPaint = this.e;
        if (textPaint != null) {
            textPaint.setColor(this.a.getColorForState(getState(), 0));
        }
    }

    @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int i = this.f;
        String str = i <= 0 ? "" : i > 99 ? this.g ? ";)" : ":D" : String.format(Locale.getDefault(), "%d", Integer.valueOf(this.f));
        if (str.isEmpty()) {
            return;
        }
        TextPaint textPaint = this.e;
        int length = str.length();
        Rect rect = this.d;
        textPaint.getTextBounds(str, 0, length, rect);
        Rect bounds = getBounds();
        int iWidth = (int) (((double) bounds.width()) / 1.76d);
        double dHeight = ((double) bounds.height()) / 1.8d;
        int i2 = rect.bottom;
        canvas.drawText(str, iWidth, (int) ((dHeight + ((double) ((i2 - rect.top) / 2))) - ((double) i2)), textPaint);
    }

    public final void e(int i, boolean z) {
        if (i == this.f && z == this.g) {
            return;
        }
        this.f = i;
        this.g = z;
        this.e.setTextSize(i > 9 ? this.c : this.b);
        invalidateSelf();
    }

    @Override // defpackage.za3, android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        if (zOnStateChange) {
            this.e.setColor(this.a.getColorForState(getState(), 0));
        }
        return zOnStateChange;
    }
}
