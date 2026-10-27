package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: RoundedIconGenerator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class uc2 {
    public final int a;
    public final int b;
    public final int c;
    public final RectF d;
    public final Paint e;
    public final TextPaint f;
    public final float g;

    public uc2(Resources resources, int i, int i2, int i3, int i4, int i5) {
        this((int) (resources.getDisplayMetrics().density * i), (int) (resources.getDisplayMetrics().density * i2), (int) (resources.getDisplayMetrics().density * i3), i4, resources.getDisplayMetrics().density * i5);
    }

    public final Bitmap a(String str) {
        int i = this.b;
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        int i2 = this.a;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i2, i, config);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        RectF rectF = this.d;
        int i3 = this.c;
        canvas.drawRoundRect(rectF, i3, i3, this.e);
        canvas.drawText(str.substring(0, Math.min(1, str.length())).toUpperCase(Locale.getDefault()), i2 / 2.0f, this.g, this.f);
        return bitmapCreateBitmap;
    }

    @Deprecated
    public final Bitmap b(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strB = bk3.b(str, z);
        if (!TextUtils.isEmpty(strB)) {
            str = strB;
        } else if (str.startsWith("chrome://") || str.startsWith("chrome-native://")) {
            str = "chrome";
        } else {
            try {
                ph3 ph3Var = new ph3(str);
                if (!TextUtils.isEmpty(ph3Var.e())) {
                    str = ph3Var.e();
                }
            } catch (Exception unused) {
                Log.w("cr_RoundedIconGenerator", "Unable to parse the URL for generating an icon: ".concat(str));
            }
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return a(str);
    }

    public final Bitmap c(GURL gurl) {
        return b(gurl.j(), false);
    }

    public uc2(int i, int i2, int i3, int i4, float f) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = new RectF(0.0f, 0.0f, i, i2);
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setColor(i4);
        TextPaint textPaint = new TextPaint(1);
        this.f = textPaint;
        textPaint.setColor(-1);
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        this.g = Math.max((i2 - (fontMetrics.bottom - fontMetrics.top)) / 2.0f, 0.0f) - fontMetrics.top;
    }
}
