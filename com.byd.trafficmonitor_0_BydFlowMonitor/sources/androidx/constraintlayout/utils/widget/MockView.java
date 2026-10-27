package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import b.f.c.e;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f683b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f684c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f685d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f686e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f687f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Rect f689h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f690i;
    public int j;
    public int k;
    public int l;

    public MockView(Context context) {
        super(context);
        this.f683b = new Paint();
        this.f684c = new Paint();
        this.f685d = new Paint();
        this.f686e = true;
        this.f687f = true;
        this.f688g = null;
        this.f689h = new Rect();
        this.f690i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.MockView_mock_label) {
                    this.f688g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.MockView_mock_showDiagonals) {
                    this.f686e = typedArrayObtainStyledAttributes.getBoolean(index, this.f686e);
                } else if (index == e.MockView_mock_diagonalsColor) {
                    this.f690i = typedArrayObtainStyledAttributes.getColor(index, this.f690i);
                } else if (index == e.MockView_mock_labelBackgroundColor) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == e.MockView_mock_labelColor) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == e.MockView_mock_showLabel) {
                    this.f687f = typedArrayObtainStyledAttributes.getBoolean(index, this.f687f);
                }
            }
        }
        if (this.f688g == null) {
            try {
                this.f688g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f683b.setColor(this.f690i);
        this.f683b.setAntiAlias(true);
        this.f684c.setColor(this.j);
        this.f684c.setAntiAlias(true);
        this.f685d.setColor(this.k);
        this.l = Math.round(this.l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f686e) {
            width--;
            height--;
            float f2 = width;
            float f3 = height;
            canvas.drawLine(0.0f, 0.0f, f2, f3, this.f683b);
            canvas.drawLine(0.0f, f3, f2, 0.0f, this.f683b);
            canvas.drawLine(0.0f, 0.0f, f2, 0.0f, this.f683b);
            canvas.drawLine(f2, 0.0f, f2, f3, this.f683b);
            canvas.drawLine(f2, f3, 0.0f, f3, this.f683b);
            canvas.drawLine(0.0f, f3, 0.0f, 0.0f, this.f683b);
        }
        String str = this.f688g;
        if (str == null || !this.f687f) {
            return;
        }
        this.f684c.getTextBounds(str, 0, str.length(), this.f689h);
        float fWidth = (width - this.f689h.width()) / 2.0f;
        float fHeight = ((height - this.f689h.height()) / 2.0f) + this.f689h.height();
        this.f689h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.f689h;
        int i2 = rect.left;
        int i3 = this.l;
        rect.set(i2 - i3, rect.top - i3, rect.right + i3, rect.bottom + i3);
        canvas.drawRect(this.f689h, this.f685d);
        canvas.drawText(this.f688g, fWidth, fHeight, this.f684c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f683b = new Paint();
        this.f684c = new Paint();
        this.f685d = new Paint();
        this.f686e = true;
        this.f687f = true;
        this.f688g = null;
        this.f689h = new Rect();
        this.f690i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f683b = new Paint();
        this.f684c = new Paint();
        this.f685d = new Paint();
        this.f686e = true;
        this.f687f = true;
        this.f688g = null;
        this.f689h = new Rect();
        this.f690i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }
}
