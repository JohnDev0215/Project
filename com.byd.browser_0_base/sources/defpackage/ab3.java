package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.text.TextPaint;
import org.chromium.chrome.R$bool;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: TitleBitmapFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class ab3 {
    public final int a;
    public final TextPaint b;
    public final int c;
    public final int d;
    public final float e;
    public final float f;

    public ab3(Context context, boolean z) {
        Resources resources = context.getResources();
        int defaultColor = bz.b(context, z ? R$color.compositor_tab_title_bar_text_incognito : R$color.compositor_tab_title_bar_text).getDefaultColor();
        float dimension = resources.getDimension(R$dimen.compositor_tab_title_text_size);
        boolean z2 = resources.getBoolean(R$bool.compositor_tab_title_fake_bold_text);
        TextPaint textPaint = new TextPaint(1);
        this.b = textPaint;
        textPaint.setColor(defaultColor);
        textPaint.setTextSize(dimension);
        textPaint.setFakeBoldText(z2);
        textPaint.density = resources.getDisplayMetrics().density;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fCeil = (float) Math.ceil(fontMetrics.bottom - fontMetrics.top);
        this.e = fCeil;
        this.f = -fontMetrics.top;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.compositor_tab_title_favicon_size);
        this.c = dimensionPixelSize;
        this.d = (int) Math.max(dimensionPixelSize, fCeil);
        int iMax = (int) (Math.max(resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().heightPixels) * 1.0f);
        this.a = iMax;
        this.c = Math.min(iMax, dimensionPixelSize);
    }
}
