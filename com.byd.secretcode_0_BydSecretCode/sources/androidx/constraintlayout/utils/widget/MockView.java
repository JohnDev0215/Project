package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {
    public Paint b;
    public Paint c;
    public Paint d;
    public boolean e;
    public boolean f;
    public String g;
    public Rect h;
    public int i;
    public int j;
    public int k;
    public int l;

    public MockView(Context context) {
        super(context);
        this.b = new Paint();
        this.c = new Paint();
        this.d = new Paint();
        this.e = true;
        this.f = true;
        this.g = null;
        this.h = new Rect();
        this.i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MockView_mock_label) {
                    this.g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.MockView_mock_showDiagonals) {
                    this.e = typedArrayObtainStyledAttributes.getBoolean(index, this.e);
                } else if (index == R$styleable.MockView_mock_diagonalsColor) {
                    this.i = typedArrayObtainStyledAttributes.getColor(index, this.i);
                } else if (index == R$styleable.MockView_mock_labelBackgroundColor) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == R$styleable.MockView_mock_labelColor) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == R$styleable.MockView_mock_showLabel) {
                    this.f = typedArrayObtainStyledAttributes.getBoolean(index, this.f);
                }
            }
        }
        if (this.g == null) {
            try {
                this.g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.b.setColor(this.i);
        this.b.setAntiAlias(true);
        this.c.setColor(this.j);
        this.c.setAntiAlias(true);
        this.d.setColor(this.k);
        this.l = Math.round(this.l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.e) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.b);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.b);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.b);
            canvas.drawLine(f, 0.0f, f, f2, this.b);
            canvas.drawLine(f, f2, 0.0f, f2, this.b);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.b);
        }
        String str = this.g;
        if (str == null || !this.f) {
            return;
        }
        this.c.getTextBounds(str, 0, str.length(), this.h);
        float fWidth = (width - this.h.width()) / 2.0f;
        float fHeight = ((height - this.h.height()) / 2.0f) + this.h.height();
        this.h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.h;
        int i = rect.left;
        int i2 = this.l;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.h, this.d);
        canvas.drawText(this.g, fWidth, fHeight, this.c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Paint();
        this.c = new Paint();
        this.d = new Paint();
        this.e = true;
        this.f = true;
        this.g = null;
        this.h = new Rect();
        this.i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new Paint();
        this.c = new Paint();
        this.d = new Paint();
        this.e = true;
        this.f = true;
        this.g = null;
        this.h = new Rect();
        this.i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }
}
