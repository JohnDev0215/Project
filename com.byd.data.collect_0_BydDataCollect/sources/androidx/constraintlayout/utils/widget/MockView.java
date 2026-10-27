package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.f;

/* JADX INFO: loaded from: classes.dex */
public class MockView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Paint f984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f986d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f987e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f988f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected String f989g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Rect f990h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f991i;
    private int j;
    private int k;
    private int l;

    public MockView(Context context) {
        super(context);
        this.f984b = new Paint();
        this.f985c = new Paint();
        this.f986d = new Paint();
        this.f987e = true;
        this.f988f = true;
        this.f989g = null;
        this.f990h = new Rect();
        this.f991i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.MockView_mock_label) {
                    this.f989g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == f.MockView_mock_showDiagonals) {
                    this.f987e = typedArrayObtainStyledAttributes.getBoolean(index, this.f987e);
                } else if (index == f.MockView_mock_diagonalsColor) {
                    this.f991i = typedArrayObtainStyledAttributes.getColor(index, this.f991i);
                } else if (index == f.MockView_mock_labelBackgroundColor) {
                    this.k = typedArrayObtainStyledAttributes.getColor(index, this.k);
                } else if (index == f.MockView_mock_labelColor) {
                    this.j = typedArrayObtainStyledAttributes.getColor(index, this.j);
                } else if (index == f.MockView_mock_showLabel) {
                    this.f988f = typedArrayObtainStyledAttributes.getBoolean(index, this.f988f);
                }
            }
        }
        if (this.f989g == null) {
            try {
                this.f989g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f984b.setColor(this.f991i);
        this.f984b.setAntiAlias(true);
        this.f985c.setColor(this.j);
        this.f985c.setAntiAlias(true);
        this.f986d.setColor(this.k);
        this.l = Math.round(this.l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f987e) {
            width--;
            height--;
            float f2 = width;
            float f3 = height;
            canvas.drawLine(0.0f, 0.0f, f2, f3, this.f984b);
            canvas.drawLine(0.0f, f3, f2, 0.0f, this.f984b);
            canvas.drawLine(0.0f, 0.0f, f2, 0.0f, this.f984b);
            canvas.drawLine(f2, 0.0f, f2, f3, this.f984b);
            canvas.drawLine(f2, f3, 0.0f, f3, this.f984b);
            canvas.drawLine(0.0f, f3, 0.0f, 0.0f, this.f984b);
        }
        String str = this.f989g;
        if (str == null || !this.f988f) {
            return;
        }
        this.f985c.getTextBounds(str, 0, str.length(), this.f990h);
        float fWidth = (width - this.f990h.width()) / 2.0f;
        float fHeight = ((height - this.f990h.height()) / 2.0f) + this.f990h.height();
        this.f990h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.f990h;
        int i2 = rect.left;
        int i3 = this.l;
        rect.set(i2 - i3, rect.top - i3, rect.right + i3, rect.bottom + i3);
        canvas.drawRect(this.f990h, this.f986d);
        canvas.drawText(this.f989g, fWidth, fHeight, this.f985c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f984b = new Paint();
        this.f985c = new Paint();
        this.f986d = new Paint();
        this.f987e = true;
        this.f988f = true;
        this.f989g = null;
        this.f990h = new Rect();
        this.f991i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f984b = new Paint();
        this.f985c = new Paint();
        this.f986d = new Paint();
        this.f987e = true;
        this.f988f = true;
        this.f989g = null;
        this.f990h = new Rect();
        this.f991i = Color.argb(255, 0, 0, 0);
        this.j = Color.argb(255, 200, 200, 200);
        this.k = Color.argb(255, 50, 50, 50);
        this.l = 4;
        a(context, attributeSet);
    }
}
