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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f942c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f943d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f944e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f945f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f946g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Rect f947h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f948i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f949j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f950k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f951l;

    public MockView(Context context) {
        super(context);
        this.f941b = new Paint();
        this.f942c = new Paint();
        this.f943d = new Paint();
        this.f944e = true;
        this.f945f = true;
        this.f946g = null;
        this.f947h = new Rect();
        this.f948i = Color.argb(255, 0, 0, 0);
        this.f949j = Color.argb(255, 200, 200, 200);
        this.f950k = Color.argb(255, 50, 50, 50);
        this.f951l = 4;
        a(context, null);
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MockView);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.MockView_mock_label) {
                    this.f946g = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.MockView_mock_showDiagonals) {
                    this.f944e = typedArrayObtainStyledAttributes.getBoolean(index, this.f944e);
                } else if (index == R$styleable.MockView_mock_diagonalsColor) {
                    this.f948i = typedArrayObtainStyledAttributes.getColor(index, this.f948i);
                } else if (index == R$styleable.MockView_mock_labelBackgroundColor) {
                    this.f950k = typedArrayObtainStyledAttributes.getColor(index, this.f950k);
                } else if (index == R$styleable.MockView_mock_labelColor) {
                    this.f949j = typedArrayObtainStyledAttributes.getColor(index, this.f949j);
                } else if (index == R$styleable.MockView_mock_showLabel) {
                    this.f945f = typedArrayObtainStyledAttributes.getBoolean(index, this.f945f);
                }
            }
        }
        if (this.f946g == null) {
            try {
                this.f946g = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.f941b.setColor(this.f948i);
        this.f941b.setAntiAlias(true);
        this.f942c.setColor(this.f949j);
        this.f942c.setAntiAlias(true);
        this.f943d.setColor(this.f950k);
        this.f951l = Math.round(this.f951l * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f944e) {
            width--;
            height--;
            float f3 = width;
            float f4 = height;
            canvas.drawLine(0.0f, 0.0f, f3, f4, this.f941b);
            canvas.drawLine(0.0f, f4, f3, 0.0f, this.f941b);
            canvas.drawLine(0.0f, 0.0f, f3, 0.0f, this.f941b);
            canvas.drawLine(f3, 0.0f, f3, f4, this.f941b);
            canvas.drawLine(f3, f4, 0.0f, f4, this.f941b);
            canvas.drawLine(0.0f, f4, 0.0f, 0.0f, this.f941b);
        }
        String str = this.f946g;
        if (str == null || !this.f945f) {
            return;
        }
        this.f942c.getTextBounds(str, 0, str.length(), this.f947h);
        float fWidth = (width - this.f947h.width()) / 2.0f;
        float fHeight = ((height - this.f947h.height()) / 2.0f) + this.f947h.height();
        this.f947h.offset((int) fWidth, (int) fHeight);
        Rect rect = this.f947h;
        int i3 = rect.left;
        int i4 = this.f951l;
        rect.set(i3 - i4, rect.top - i4, rect.right + i4, rect.bottom + i4);
        canvas.drawRect(this.f947h, this.f943d);
        canvas.drawText(this.f946g, fWidth, fHeight, this.f942c);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f941b = new Paint();
        this.f942c = new Paint();
        this.f943d = new Paint();
        this.f944e = true;
        this.f945f = true;
        this.f946g = null;
        this.f947h = new Rect();
        this.f948i = Color.argb(255, 0, 0, 0);
        this.f949j = Color.argb(255, 200, 200, 200);
        this.f950k = Color.argb(255, 50, 50, 50);
        this.f951l = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f941b = new Paint();
        this.f942c = new Paint();
        this.f943d = new Paint();
        this.f944e = true;
        this.f945f = true;
        this.f946g = null;
        this.f947h = new Rect();
        this.f948i = Color.argb(255, 0, 0, 0);
        this.f949j = Color.argb(255, 200, 200, 200);
        this.f950k = Color.argb(255, 50, 50, 50);
        this.f951l = 4;
        a(context, attributeSet);
    }
}
