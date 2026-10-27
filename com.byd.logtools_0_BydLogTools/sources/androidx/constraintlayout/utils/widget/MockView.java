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
    private Paint f1064b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f1065c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f1066d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1067e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1068f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f1069g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Rect f1070h;
    private int i;
    private int j;
    private int k;
    private int l;

    public MockView(Context context) {
        super(context);
        this.f1064b = new Paint();
        this.f1065c = new Paint();
        this.f1066d = new Paint();
        this.f1067e = true;
        this.f1068f = true;
        this.f1069g = null;
        this.f1070h = new Rect();
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
                    this.f1069g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.v6) {
                    this.f1067e = typedArrayObtainStyledAttributes.getBoolean(index, this.f1067e);
                } else if (index == e.r6) {
                    this.i = typedArrayObtainStyledAttributes.getColor(index, this.i);
                } else if (index == e.t6) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == e.u6) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == e.w6) {
                    this.f1068f = typedArrayObtainStyledAttributes.getBoolean(index, this.f1068f);
                }
            }
        }
        if (this.f1069g == null) {
            try {
                this.f1069g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f1064b.setColor(this.i);
        this.f1064b.setAntiAlias(true);
        this.f1065c.setColor(this.j);
        this.f1065c.setAntiAlias(true);
        this.f1066d.setColor(this.k);
        this.l = Math.round(this.l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f1067e) {
            width--;
            height--;
            float f2 = width;
            float f3 = height;
            canvas.drawLine(0.0f, 0.0f, f2, f3, this.f1064b);
            canvas.drawLine(0.0f, f3, f2, 0.0f, this.f1064b);
            canvas.drawLine(0.0f, 0.0f, f2, 0.0f, this.f1064b);
            canvas.drawLine(f2, 0.0f, f2, f3, this.f1064b);
            canvas.drawLine(f2, f3, 0.0f, f3, this.f1064b);
            canvas.drawLine(0.0f, f3, 0.0f, 0.0f, this.f1064b);
        }
        String str = this.f1069g;
        if (str == null || !this.f1068f) {
            return;
        }
        this.f1065c.getTextBounds(str, 0, str.length(), this.f1070h);
        float fWidth = (width - this.f1070h.width()) / 2.0f;
        float fHeight = ((height - this.f1070h.height()) / 2.0f) + this.f1070h.height();
        this.f1070h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.f1070h;
        int i = rect.left;
        int i2 = this.l;
        rect.set(i - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
        canvas.drawRect(this.f1070h, this.f1066d);
        canvas.drawText(this.f1069g, fWidth, fHeight, this.f1065c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1064b = new Paint();
        this.f1065c = new Paint();
        this.f1066d = new Paint();
        this.f1067e = true;
        this.f1068f = true;
        this.f1069g = null;
        this.f1070h = new Rect();
        this.i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1064b = new Paint();
        this.f1065c = new Paint();
        this.f1066d = new Paint();
        this.f1067e = true;
        this.f1068f = true;
        this.f1069g = null;
        this.f1070h = new Rect();
        this.i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }
}
