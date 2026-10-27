package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {
    public Paint K3;
    public MotionLayout L3;
    public float[] M3;
    public Matrix N3;
    public int O3;
    public int P3;
    public float Q3;

    public MotionTelltales(Context context) {
        super(context);
        this.K3 = new Paint();
        this.M3 = new float[2];
        this.N3 = new Matrix();
        this.O3 = 0;
        this.P3 = -65281;
        this.Q3 = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.R6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.S6) {
                    this.P3 = typedArrayObtainStyledAttributes.getColor(index, this.P3);
                } else if (index == e.U6) {
                    this.O3 = typedArrayObtainStyledAttributes.getInt(index, this.O3);
                } else if (index == e.T6) {
                    this.Q3 = typedArrayObtainStyledAttributes.getFloat(index, this.Q3);
                }
            }
        }
        this.K3.setColor(this.P3);
        this.K3.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.N3);
        if (this.L3 == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.L3 = (MotionLayout) parent;
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
                this.L3.f0(this, f3, f2, this.M3, this.O3);
                this.N3.mapVectors(this.M3);
                float f4 = width * f3;
                float f5 = height * f2;
                float[] fArr2 = this.M3;
                float f6 = fArr2[0];
                float f7 = this.Q3;
                float f8 = f5 - (fArr2[1] * f7);
                this.N3.mapVectors(fArr2);
                canvas.drawLine(f4, f5, f4 - (f6 * f7), f8, this.K3);
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.E3 = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.K3 = new Paint();
        this.M3 = new float[2];
        this.N3 = new Matrix();
        this.O3 = 0;
        this.P3 = -65281;
        this.Q3 = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.K3 = new Paint();
        this.M3 = new float[2];
        this.N3 = new Matrix();
        this.O3 = 0;
        this.P3 = -65281;
        this.Q3 = 0.25f;
        a(context, attributeSet);
    }
}
