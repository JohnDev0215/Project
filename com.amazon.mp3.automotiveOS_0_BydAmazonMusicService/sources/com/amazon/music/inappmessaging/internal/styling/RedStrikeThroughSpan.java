package com.amazon.music.inappmessaging.internal.styling;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class RedStrikeThroughSpan extends ReplacementSpan {
    private static final String TAG = RedStrikeThroughSpan.class.getSimpleName();
    String fontColor;
    final StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();

    public RedStrikeThroughSpan(String fontColor) {
        this.fontColor = fontColor;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        return Math.round(paint.measureText(text, start, end));
    }

    public int parseColor(String fontColor) {
        try {
            if (StringUtils.isNotEmpty(fontColor) && fontColor.startsWith("#") && fontColor.length() == 4) {
                StringBuilder sb = new StringBuilder();
                for (char c : fontColor.toCharArray()) {
                    if (c == '#') {
                        sb.append(c);
                    } else {
                        sb.append(c);
                        sb.append(c);
                    }
                }
                return Color.parseColor(sb.toString());
            }
            if (StringUtils.isNotEmpty(fontColor)) {
                return Color.parseColor(fontColor);
            }
            return -1;
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "Unknown color provided in strike-through html tag:" + fontColor);
            return -1;
        }
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        paint.setTypeface(this.styleSheet.getFontStyle(FontStyleKey.PRIMARY_HEADLINE_3_MEDIUM_SHARPGROTESK).getTypeface());
        paint.setColor(parseColor(this.fontColor));
        canvas.drawText(text, start, end, x, y, paint);
        Rect rect = new Rect();
        paint.getTextBounds(text.toString(), start, end, rect);
        float f = ((rect.top + rect.bottom) / 2) + y;
        RectF rectF = new RectF(x, top, paint.measureText(text, start, end) + x, bottom);
        Paint paint2 = new Paint();
        paint2.setColor(SupportMenu.CATEGORY_MASK);
        paint2.setStrokeWidth((rect.bottom - rect.top) / 10);
        canvas.drawLine(rectF.left, f, rectF.right, f, paint2);
    }
}
