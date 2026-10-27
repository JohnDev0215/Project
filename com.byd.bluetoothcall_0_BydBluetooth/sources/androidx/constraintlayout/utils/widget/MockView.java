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
    private Paint f1007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f1008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f1009d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1010e;
    private boolean f;
    protected String g;
    private Rect h;
    private int i;
    private int j;
    private int k;
    private int l;

    public MockView(Context context) {
        super(context);
        this.f1007b = new Paint();
        this.f1008c = new Paint();
        this.f1009d = new Paint();
        this.f1010e = true;
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
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.a9);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.c9) {
                    this.g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.f9) {
                    this.f1010e = typedArrayObtainStyledAttributes.getBoolean(index, this.f1010e);
                } else if (index == e.b9) {
                    this.i = typedArrayObtainStyledAttributes.getColor(index, this.i);
                } else if (index == e.d9) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == e.e9) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == e.g9) {
                    this.f = typedArrayObtainStyledAttributes.getBoolean(index, this.f);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.g == null) {
            try {
                this.g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f1007b.setColor(this.i);
        this.f1007b.setAntiAlias(true);
        this.f1008c.setColor(this.j);
        this.f1008c.setAntiAlias(true);
        this.f1009d.setColor(this.k);
        this.l = Math.round(this.l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f1010e) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.f1007b);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.f1007b);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.f1007b);
            canvas.drawLine(f, 0.0f, f, f2, this.f1007b);
            canvas.drawLine(f, f2, 0.0f, f2, this.f1007b);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.f1007b);
        }
        String str = this.g;
        if (str == null || !this.f) {
            return;
        }
        this.f1008c.getTextBounds(str, 0, str.length(), this.h);
        float fWidth = (width - this.h.width()) / 2.0f;
        float fHeight = ((height - this.h.height()) / 2.0f) + this.h.height();
        this.h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.h;
        int i = rect.left;
        int i2 = this.l;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.h, this.f1009d);
        canvas.drawText(this.g, fWidth, fHeight, this.f1008c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1007b = new Paint();
        this.f1008c = new Paint();
        this.f1009d = new Paint();
        this.f1010e = true;
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
        this.f1007b = new Paint();
        this.f1008c = new Paint();
        this.f1009d = new Paint();
        this.f1010e = true;
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
