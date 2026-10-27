package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: loaded from: classes.dex */
public class f extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    e f1141b;

    public static class a extends ConstraintLayout.b {
        public float A0;
        public float B0;
        public float p0;
        public boolean q0;
        public float r0;
        public float s0;
        public float t0;
        public float u0;
        public float v0;
        public float w0;
        public float x0;
        public float y0;
        public float z0;

        public a(int i, int i2) {
            super(i, i2);
            this.p0 = 1.0f;
            this.q0 = false;
            this.r0 = 0.0f;
            this.s0 = 0.0f;
            this.t0 = 0.0f;
            this.u0 = 0.0f;
            this.v0 = 1.0f;
            this.w0 = 1.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.p0 = 1.0f;
            this.q0 = false;
            this.r0 = 0.0f;
            this.s0 = 0.0f;
            this.t0 = 0.0f;
            this.u0 = 0.0f;
            this.v0 = 1.0f;
            this.w0 = 1.0f;
            this.x0 = 0.0f;
            this.y0 = 0.0f;
            this.z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.o2);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.p2) {
                    this.p0 = typedArrayObtainStyledAttributes.getFloat(index, this.p0);
                } else if (index == j.A2) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.r0 = typedArrayObtainStyledAttributes.getFloat(index, this.r0);
                        this.q0 = true;
                    }
                } else if (index == j.x2) {
                    this.t0 = typedArrayObtainStyledAttributes.getFloat(index, this.t0);
                } else if (index == j.y2) {
                    this.u0 = typedArrayObtainStyledAttributes.getFloat(index, this.u0);
                } else if (index == j.w2) {
                    this.s0 = typedArrayObtainStyledAttributes.getFloat(index, this.s0);
                } else if (index == j.u2) {
                    this.v0 = typedArrayObtainStyledAttributes.getFloat(index, this.v0);
                } else if (index == j.v2) {
                    this.w0 = typedArrayObtainStyledAttributes.getFloat(index, this.w0);
                } else if (index == j.q2) {
                    this.x0 = typedArrayObtainStyledAttributes.getFloat(index, this.x0);
                } else if (index == j.r2) {
                    this.y0 = typedArrayObtainStyledAttributes.getFloat(index, this.y0);
                } else if (index == j.s2) {
                    this.z0 = typedArrayObtainStyledAttributes.getFloat(index, this.z0);
                } else if (index == j.t2) {
                    this.A0 = typedArrayObtainStyledAttributes.getFloat(index, this.A0);
                } else if (index == j.z2 && Build.VERSION.SDK_INT >= 21) {
                    this.B0 = typedArrayObtainStyledAttributes.getFloat(index, this.B0);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.b(layoutParams);
    }

    public e getConstraintSet() {
        if (this.f1141b == null) {
            this.f1141b = new e();
        }
        this.f1141b.g(this);
        return this.f1141b;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
