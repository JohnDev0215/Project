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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f1032d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f1033e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f1034f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1035g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1036h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1037i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Rect f1038j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1039k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1040l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1041m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1042n;

    public MockView(Context context) {
        super(context);
        this.f1032d = new Paint();
        this.f1033e = new Paint();
        this.f1034f = new Paint();
        this.f1035g = true;
        this.f1036h = true;
        this.f1037i = null;
        this.f1038j = new Rect();
        this.f1039k = Color.argb(255, 0, 0, 0);
        this.f1040l = Color.argb(255, 200, 200, 200);
        this.f1041m = Color.argb(255, 50, 50, 50);
        this.f1042n = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.MockView_mock_label) {
                    this.f1037i = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.MockView_mock_showDiagonals) {
                    this.f1035g = typedArrayObtainStyledAttributes.getBoolean(index, this.f1035g);
                } else if (index == R$styleable.MockView_mock_diagonalsColor) {
                    this.f1039k = typedArrayObtainStyledAttributes.getColor(index, this.f1039k);
                } else if (index == R$styleable.MockView_mock_labelBackgroundColor) {
                    this.f1041m = typedArrayObtainStyledAttributes.getColor(index, this.f1041m);
                } else if (index == R$styleable.MockView_mock_labelColor) {
                    this.f1040l = typedArrayObtainStyledAttributes.getColor(index, this.f1040l);
                } else if (index == R$styleable.MockView_mock_showLabel) {
                    this.f1036h = typedArrayObtainStyledAttributes.getBoolean(index, this.f1036h);
                }
            }
        }
        if (this.f1037i == null) {
            try {
                this.f1037i = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f1032d.setColor(this.f1039k);
        this.f1032d.setAntiAlias(true);
        this.f1033e.setColor(this.f1040l);
        this.f1033e.setAntiAlias(true);
        this.f1034f.setColor(this.f1041m);
        this.f1042n = Math.round(this.f1042n * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f1035g) {
            width--;
            height--;
            float f8 = width;
            float f9 = height;
            canvas.drawLine(0.0f, 0.0f, f8, f9, this.f1032d);
            canvas.drawLine(0.0f, f9, f8, 0.0f, this.f1032d);
            canvas.drawLine(0.0f, 0.0f, f8, 0.0f, this.f1032d);
            canvas.drawLine(f8, 0.0f, f8, f9, this.f1032d);
            canvas.drawLine(f8, f9, 0.0f, f9, this.f1032d);
            canvas.drawLine(0.0f, f9, 0.0f, 0.0f, this.f1032d);
        }
        String str = this.f1037i;
        if (str == null || !this.f1036h) {
            return;
        }
        this.f1033e.getTextBounds(str, 0, str.length(), this.f1038j);
        float fWidth = (width - this.f1038j.width()) / 2.0f;
        float fHeight = ((height - this.f1038j.height()) / 2.0f) + this.f1038j.height();
        this.f1038j.offset((int) fWidth, (int) fHeight);
        Rect rect = this.f1038j;
        int i8 = rect.left;
        int i9 = this.f1042n;
        rect.set(i8 - i9, rect.top - i9, rect.right + i9, rect.bottom + i9);
        canvas.drawRect(this.f1038j, this.f1034f);
        canvas.drawText(this.f1037i, fWidth, fHeight, this.f1033e);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1032d = new Paint();
        this.f1033e = new Paint();
        this.f1034f = new Paint();
        this.f1035g = true;
        this.f1036h = true;
        this.f1037i = null;
        this.f1038j = new Rect();
        this.f1039k = Color.argb(255, 0, 0, 0);
        this.f1040l = Color.argb(255, 200, 200, 200);
        this.f1041m = Color.argb(255, 50, 50, 50);
        this.f1042n = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1032d = new Paint();
        this.f1033e = new Paint();
        this.f1034f = new Paint();
        this.f1035g = true;
        this.f1036h = true;
        this.f1037i = null;
        this.f1038j = new Rect();
        this.f1039k = Color.argb(255, 0, 0, 0);
        this.f1040l = Color.argb(255, 200, 200, 200);
        this.f1041m = Color.argb(255, 50, 50, 50);
        this.f1042n = 4;
        a(context, attributeSet);
    }
}
