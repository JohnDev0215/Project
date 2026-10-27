package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.f;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {
    private Paint m;
    MotionLayout n;
    float[] o;
    Matrix p;
    int q;
    int r;
    float s;

    public MotionTelltales(Context context) {
        super(context);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == f.MotionTelltales_telltales_tailColor) {
                    this.r = typedArrayObtainStyledAttributes.getColor(index, this.r);
                } else if (index == f.MotionTelltales_telltales_velocityMode) {
                    this.q = typedArrayObtainStyledAttributes.getInt(index, this.q);
                } else if (index == f.MotionTelltales_telltales_tailScale) {
                    this.s = typedArrayObtainStyledAttributes.getFloat(index, this.s);
                }
            }
        }
        this.m.setColor(this.r);
        this.m.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.p);
        if (this.n == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.n = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i2 = 0; i2 < 5; i2++) {
            float f2 = fArr[i2];
            for (int i3 = 0; i3 < 5; i3++) {
                float f3 = fArr[i3];
                this.n.f0(this, f3, f2, this.o, this.q);
                this.p.mapVectors(this.o);
                float f4 = width * f3;
                float f5 = height * f2;
                float[] fArr2 = this.o;
                float f6 = fArr2[0];
                float f7 = this.s;
                float f8 = f5 - (fArr2[1] * f7);
                this.p.mapVectors(fArr2);
                canvas.drawLine(f4, f5, f4 - (f6 * f7), f8, this.m);
            }
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.f989g = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m = new Paint();
        this.o = new float[2];
        this.p = new Matrix();
        this.q = 0;
        this.r = -65281;
        this.s = 0.25f;
        a(context, attributeSet);
    }
}
