package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class MotionTelltales extends MockView {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Paint f1043o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MotionLayout f1044p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float[] f1045q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Matrix f1046r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1047s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1048t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f1049u;

    public MotionTelltales(Context context) {
        super(context);
        this.f1043o = new Paint();
        this.f1045q = new float[2];
        this.f1046r = new Matrix();
        this.f1047s = 0;
        this.f1048t = -65281;
        this.f1049u = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.MotionTelltales_telltales_tailColor) {
                    this.f1048t = typedArrayObtainStyledAttributes.getColor(index, this.f1048t);
                } else if (index == R$styleable.MotionTelltales_telltales_velocityMode) {
                    this.f1047s = typedArrayObtainStyledAttributes.getInt(index, this.f1047s);
                } else if (index == R$styleable.MotionTelltales_telltales_tailScale) {
                    this.f1049u = typedArrayObtainStyledAttributes.getFloat(index, this.f1049u);
                }
            }
        }
        this.f1043o.setColor(this.f1048t);
        this.f1043o.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.f1046r);
        if (this.f1044p == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.f1044p = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i8 = 0; i8 < 5; i8++) {
            float f8 = fArr[i8];
            for (int i9 = 0; i9 < 5; i9++) {
                float f9 = fArr[i9];
                this.f1044p.f0(this, f9, f8, this.f1045q, this.f1047s);
                this.f1046r.mapVectors(this.f1045q);
                float f10 = width * f9;
                float f11 = height * f8;
                float[] fArr2 = this.f1045q;
                float f12 = fArr2[0];
                float f13 = this.f1049u;
                float f14 = f11 - (fArr2[1] * f13);
                this.f1046r.mapVectors(fArr2);
                canvas.drawLine(f10, f11, f10 - (f12 * f13), f14, this.f1043o);
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.f1037i = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1043o = new Paint();
        this.f1045q = new float[2];
        this.f1046r = new Matrix();
        this.f1047s = 0;
        this.f1048t = -65281;
        this.f1049u = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1043o = new Paint();
        this.f1045q = new float[2];
        this.f1046r = new Matrix();
        this.f1047s = 0;
        this.f1048t = -65281;
        this.f1049u = 0.25f;
        a(context, attributeSet);
    }
}
