package com.byd.sing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import com.byd.sing.c;
import com.byd.sing.h;

/* JADX INFO: loaded from: classes.dex */
public class LineTextView extends TextView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3294b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3295c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3296d;
    private int e;
    private int f;
    private int g;
    private Paint h;
    private String i;
    private String[] j;
    private int k;
    private int l;

    public LineTextView(Context context) {
        this(context, null);
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void b(Canvas canvas) {
        int i = this.g;
        if (i == 0) {
            return;
        }
        int i2 = this.f3295c / i;
        int height = (int) ((getHeight() / 2) - ((this.h.descent() + this.h.ascent()) / 2.0f));
        int i3 = 0;
        while (true) {
            int i4 = this.g;
            if (i3 >= i4) {
                return;
            }
            if (i3 == i4 - 1) {
                canvas.drawText(this.j[i3], (this.f3295c - this.e) - a(this.f3294b, 2.0f), height, this.h);
            } else if (i3 == 0 || i3 == i4 - 1) {
                canvas.drawText(this.j[i3], (i3 * i2) + a(this.f3294b, 2.0f), height, this.h);
            } else {
                canvas.drawText(this.j[i3], ((i3 * i2) + (i2 / 2)) - (this.e / 2), height, this.h);
            }
            i3++;
        }
    }

    private void c() {
        setPadding(0, 0, 0, 0);
        this.f3295c = getWidth();
        this.f3296d = getHeight();
    }

    private void d(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, h.i0);
        this.i = typedArrayObtainStyledAttributes.getString(1);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, (int) c.k(16.0f));
        this.l = typedArrayObtainStyledAttributes.getColor(0, -16777216);
        typedArrayObtainStyledAttributes.recycle();
    }

    private float getBaseY() {
        Paint.FontMetrics fontMetrics = this.h.getFontMetrics();
        float f = fontMetrics.bottom;
        return ((f - fontMetrics.top) / 2.0f) - f;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c();
        b(canvas);
    }

    public void setTestSize(int i) {
        this.k = i;
        String str = this.i;
        if (str != null) {
            setText(str);
        }
    }

    public void setText(String str) {
        if (str != null) {
            this.i = str;
            this.g = str.length();
            Rect rect = new Rect();
            int i = 0;
            this.h.getTextBounds(str, 0, 1, rect);
            this.e = rect.width();
            this.f = (rect.bottom + rect.top) / 2;
            String[] strArr = new String[this.g];
            while (i < this.g) {
                int i2 = i + 1;
                strArr[i] = str.substring(i, i2);
                i = i2;
            }
            this.j = strArr;
        }
    }

    public LineTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3294b = context;
        d(context, attributeSet);
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(this.l);
        this.h.setTextSize(this.k);
        this.h.setAntiAlias(true);
        c();
    }
}
