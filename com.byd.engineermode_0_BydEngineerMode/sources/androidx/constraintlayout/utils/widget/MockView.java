package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.e;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Paint f701b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f702c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f703d;
    private boolean e;
    private boolean f;
    protected String g;
    private Rect h;
    private int i;
    private int j;
    private int k;
    private int l;

    public MockView(Context context) {
        super(context);
        this.f701b = new Paint();
        this.f702c = new Paint();
        this.f703d = new Paint();
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

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.q6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.s6) {
                    this.g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.v6) {
                    this.e = typedArrayObtainStyledAttributes.getBoolean(index, this.e);
                } else if (index == e.r6) {
                    this.i = typedArrayObtainStyledAttributes.getColor(index, this.i);
                } else if (index == e.t6) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == e.u6) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == e.w6) {
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
        this.f701b.setColor(this.i);
        this.f701b.setAntiAlias(true);
        this.f702c.setColor(this.j);
        this.f702c.setAntiAlias(true);
        this.f703d.setColor(this.k);
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
            canvas.drawLine(0.0f, 0.0f, f, f2, this.f701b);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.f701b);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.f701b);
            canvas.drawLine(f, 0.0f, f, f2, this.f701b);
            canvas.drawLine(f, f2, 0.0f, f2, this.f701b);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.f701b);
        }
        String str = this.g;
        if (str == null || !this.f) {
            return;
        }
        this.f702c.getTextBounds(str, 0, str.length(), this.h);
        float fWidth = (width - this.h.width()) / 2.0f;
        float fHeight = ((height - this.h.height()) / 2.0f) + this.h.height();
        this.h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.h;
        int i = rect.left;
        int i2 = this.l;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.h, this.f703d);
        canvas.drawText(this.g, fWidth, fHeight, this.f702c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f701b = new Paint();
        this.f702c = new Paint();
        this.f703d = new Paint();
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
        this.f701b = new Paint();
        this.f702c = new Paint();
        this.f703d = new Paint();
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
