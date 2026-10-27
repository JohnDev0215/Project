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

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint f952m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public MotionLayout f953n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float[] f954o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Matrix f955p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f956q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f957r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f958s;

    public MotionTelltales(Context context) {
        super(context);
        this.f952m = new Paint();
        this.f954o = new float[2];
        this.f955p = new Matrix();
        this.f956q = 0;
        this.f957r = -65281;
        this.f958s = 0.25f;
        a(context, null);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionTelltales);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.MotionTelltales_telltales_tailColor) {
                    this.f957r = typedArrayObtainStyledAttributes.getColor(index, this.f957r);
                } else if (index == R$styleable.MotionTelltales_telltales_velocityMode) {
                    this.f956q = typedArrayObtainStyledAttributes.getInt(index, this.f956q);
                } else if (index == R$styleable.MotionTelltales_telltales_tailScale) {
                    this.f958s = typedArrayObtainStyledAttributes.getFloat(index, this.f958s);
                }
            }
        }
        this.f952m.setColor(this.f957r);
        this.f952m.setStrokeWidth(5.0f);
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // androidx.constraintlayout.utils.widget.MockView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getMatrix().invert(this.f955p);
        if (this.f953n == null) {
            ViewParent parent = getParent();
            if (parent instanceof MotionLayout) {
                this.f953n = (MotionLayout) parent;
                return;
            }
            return;
        }
        int width = getWidth();
        int height = getHeight();
        float[] fArr = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
        for (int i3 = 0; i3 < 5; i3++) {
            float f3 = fArr[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                float f4 = fArr[i4];
                this.f953n.f0(this, f4, f3, this.f954o, this.f956q);
                this.f955p.mapVectors(this.f954o);
                float f5 = width * f4;
                float f6 = height * f3;
                float[] fArr2 = this.f954o;
                float f7 = fArr2[0];
                float f8 = this.f958s;
                float f9 = f6 - (fArr2[1] * f8);
                this.f955p.mapVectors(fArr2);
                canvas.drawLine(f5, f6, f5 - (f7 * f8), f9, this.f952m);
            }
        }
    }

    @Override // android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        postInvalidate();
    }

    public void setText(CharSequence charSequence) {
        this.f946g = charSequence.toString();
        requestLayout();
    }

    public MotionTelltales(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f952m = new Paint();
        this.f954o = new float[2];
        this.f955p = new Matrix();
        this.f956q = 0;
        this.f957r = -65281;
        this.f958s = 0.25f;
        a(context, attributeSet);
    }

    public MotionTelltales(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f952m = new Paint();
        this.f954o = new float[2];
        this.f955p = new Matrix();
        this.f956q = 0;
        this.f957r = -65281;
        this.f958s = 0.25f;
        a(context, attributeSet);
    }
}
