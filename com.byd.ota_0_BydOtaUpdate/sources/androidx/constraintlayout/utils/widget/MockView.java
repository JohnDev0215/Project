package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {
    public String E3;
    public Rect F3;
    public int G3;
    public int H3;
    public int I3;
    public int J3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f382c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f383d;
    public Paint s;
    public boolean x;
    public boolean y;

    public MockView(Context context) {
        super(context);
        this.f382c = new Paint();
        this.f383d = new Paint();
        this.s = new Paint();
        this.x = true;
        this.y = true;
        this.E3 = null;
        this.F3 = new Rect();
        this.G3 = Color.argb(255, 0, 0, 0);
        this.H3 = Color.argb(255, 200, 200, 200);
        this.I3 = Color.argb(255, 50, 50, 50);
        this.J3 = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.q6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.s6) {
                    this.E3 = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.v6) {
                    this.x = typedArrayObtainStyledAttributes.getBoolean(index, this.x);
                } else if (index == e.r6) {
                    this.G3 = typedArrayObtainStyledAttributes.getColor(index, this.G3);
                } else if (index == e.t6) {
                    this.I3 = typedArrayObtainStyledAttributes.getColor(index, this.I3);
                } else if (index == e.u6) {
                    this.H3 = typedArrayObtainStyledAttributes.getColor(index, this.H3);
                } else if (index == e.w6) {
                    this.y = typedArrayObtainStyledAttributes.getBoolean(index, this.y);
                }
            }
        }
        if (this.E3 == null) {
            try {
                this.E3 = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f382c.setColor(this.G3);
        this.f382c.setAntiAlias(true);
        this.f383d.setColor(this.H3);
        this.f383d.setAntiAlias(true);
        this.s.setColor(this.I3);
        this.J3 = Math.round(this.J3 * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.x) {
            width--;
            height--;
            float f2 = width;
            float f3 = height;
            canvas.drawLine(0.0f, 0.0f, f2, f3, this.f382c);
            canvas.drawLine(0.0f, f3, f2, 0.0f, this.f382c);
            canvas.drawLine(0.0f, 0.0f, f2, 0.0f, this.f382c);
            canvas.drawLine(f2, 0.0f, f2, f3, this.f382c);
            canvas.drawLine(f2, f3, 0.0f, f3, this.f382c);
            canvas.drawLine(0.0f, f3, 0.0f, 0.0f, this.f382c);
        }
        String str = this.E3;
        if (str == null || !this.y) {
            return;
        }
        this.f383d.getTextBounds(str, 0, str.length(), this.F3);
        float fWidth = (width - this.F3.width()) / 2.0f;
        float fHeight = ((height - this.F3.height()) / 2.0f) + this.F3.height();
        this.F3.offset((int) fWidth, (int) fHeight);
        Rect rect = this.F3;
        int i2 = rect.left;
        int i3 = this.J3;
        rect.set(i2 - i3, rect.top - i3, rect.right + i3, rect.bottom + i3);
        canvas.drawRect(this.F3, this.s);
        canvas.drawText(this.E3, fWidth, fHeight, this.f383d);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f382c = new Paint();
        this.f383d = new Paint();
        this.s = new Paint();
        this.x = true;
        this.y = true;
        this.E3 = null;
        this.F3 = new Rect();
        this.G3 = Color.argb(255, 0, 0, 0);
        this.H3 = Color.argb(255, 200, 200, 200);
        this.I3 = Color.argb(255, 50, 50, 50);
        this.J3 = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f382c = new Paint();
        this.f383d = new Paint();
        this.s = new Paint();
        this.x = true;
        this.y = true;
        this.E3 = null;
        this.F3 = new Rect();
        this.G3 = Color.argb(255, 0, 0, 0);
        this.H3 = Color.argb(255, 200, 200, 200);
        this.I3 = Color.argb(255, 50, 50, 50);
        this.J3 = 4;
        a(context, attributeSet);
    }
}
