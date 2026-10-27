package org.chromium.chrome.browser.ntp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.DrawableWrapper;
import android.text.TextPaint;
import defpackage.bz;
import defpackage.uh3;
import java.util.HashMap;
import java.util.Locale;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;

/* JADX INFO: loaded from: classes2.dex */
public class RecentTabCountDrawable extends DrawableWrapper {
    public final Rect e;
    public final TextPaint f;
    public ColorStateList g;
    public int h;

    /* JADX WARN: Illegal instructions before constructor call */
    public RecentTabCountDrawable(Context context) {
        int i = R$drawable.ic_recent_tabs_bulk_20dp;
        int i2 = R$color.default_icon_color_tint_list;
        HashMap map = uh3.a;
        super(uh3.f(context, i, bz.b(context, i2)));
        this.e = new Rect();
        TextPaint textPaint = new TextPaint();
        this.f = textPaint;
        b(bz.b(context, i2));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-condensed", 1));
        textPaint.setColor(a());
        textPaint.setTextSize(context.getResources().getDimension(R$dimen.recent_tabs_count_text_size));
    }

    public final int a() {
        return this.g.getColorForState(getState(), 0);
    }

    public final void b(ColorStateList colorStateList) {
        if (this.g == colorStateList) {
            return;
        }
        this.g = colorStateList;
        if (colorStateList != null) {
            setColorFilter(a(), PorterDuff.Mode.SRC_IN);
        }
        super.setTint(a());
        TextPaint textPaint = this.f;
        if (textPaint != null) {
            textPaint.setColor(a());
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        int i = this.h;
        String str = i <= 0 ? "" : i > 99 ? ":D" : String.format(Locale.getDefault(), "%d", Integer.valueOf(this.h));
        if (str.isEmpty()) {
            return;
        }
        TextPaint textPaint = this.f;
        int length = str.length();
        Rect rect = this.e;
        textPaint.getTextBounds(str, 0, length, rect);
        Rect bounds = super.getBounds();
        int iRound = Math.round(bounds.width() * 0.583f) + bounds.left;
        int iRound2 = Math.round(bounds.height() * 0.5833333f) + bounds.top;
        int i2 = rect.bottom;
        canvas.drawText(str, iRound, (((i2 - rect.top) / 2) + iRound2) - i2, textPaint);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        if (this.g == null) {
            z = false;
        } else {
            setColorFilter(a(), PorterDuff.Mode.SRC_IN);
            z = true;
        }
        if (z) {
            this.f.setColor(a());
        }
        return z || super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        super.setAlpha(i);
        this.f.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i) {
        b(ColorStateList.valueOf(i));
    }
}
