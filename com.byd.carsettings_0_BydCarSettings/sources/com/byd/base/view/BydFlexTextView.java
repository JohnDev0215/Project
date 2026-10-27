package com.byd.base.view;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class BydFlexTextView extends TextView {
    public BydFlexTextView(Context context) {
        this(context, null);
    }

    public final void a(int i, int i2) {
        int iMax;
        int maxWidth = getMaxWidth();
        if (i < maxWidth) {
            return;
        }
        String strTrim = getText().toString().trim();
        TextPaint paint = getPaint();
        if (!strTrim.contains(" ")) {
            Rect rect = new Rect();
            paint.getTextBounds(strTrim, 0, strTrim.length(), rect);
            int iWidth = rect.width();
            if (iWidth > maxWidth) {
                setMeasuredDimension(iWidth / ((iWidth / maxWidth) + 1), i2);
                return;
            }
        }
        String[] strArrSplit = strTrim.split(" ");
        if (strArrSplit == null || strArrSplit.length <= 0) {
            iMax = 0;
        } else {
            iMax = 0;
            for (String str : strArrSplit) {
                iMax = Math.max(iMax, (int) paint.measureText(str));
            }
        }
        while (true) {
            int iMeasureText = ((int) paint.measureText(strTrim)) + getPaddingLeft() + getPaddingRight();
            if (iMeasureText <= maxWidth) {
                setMeasuredDimension(Math.max(iMeasureText, iMax), i2);
                return;
            }
            int iLastIndexOf = strTrim.lastIndexOf(" ");
            if (iLastIndexOf == -1) {
                setMeasuredDimension(Math.min(Math.max(iMeasureText, iMax), maxWidth), i2);
                return;
            }
            strTrim = strTrim.substring(0, iLastIndexOf);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        a(getMeasuredWidth(), getMeasuredHeight());
    }

    public BydFlexTextView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydFlexTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
