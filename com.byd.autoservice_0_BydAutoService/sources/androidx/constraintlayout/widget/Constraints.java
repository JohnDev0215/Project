package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import y.b;

/* JADX INFO: loaded from: classes.dex */
public class Constraints extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b f1052b;

    public Constraints(Context context) {
        super(context);
        super.setVisibility(8);
    }

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

    public final void c(AttributeSet attributeSet) {
        Log.v("Constraints", " ################# init");
    }

    public b getConstraintSet() {
        if (this.f1052b == null) {
            this.f1052b = new b();
        }
        this.f1052b.j(this);
        return this.f1052b;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ConstraintLayout.b(layoutParams);
    }

    public Constraints(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(attributeSet);
        super.setVisibility(8);
    }

    public Constraints(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        c(attributeSet);
        super.setVisibility(8);
    }

    public static class a extends ConstraintLayout.b {
        public float A0;
        public float B0;

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        public float f1053p0;

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        public boolean f1054q0;

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        public float f1055r0;

        /* JADX INFO: renamed from: s0, reason: collision with root package name */
        public float f1056s0;

        /* JADX INFO: renamed from: t0, reason: collision with root package name */
        public float f1057t0;

        /* JADX INFO: renamed from: u0, reason: collision with root package name */
        public float f1058u0;

        /* JADX INFO: renamed from: v0, reason: collision with root package name */
        public float f1059v0;

        /* JADX INFO: renamed from: w0, reason: collision with root package name */
        public float f1060w0;

        /* JADX INFO: renamed from: x0, reason: collision with root package name */
        public float f1061x0;

        /* JADX INFO: renamed from: y0, reason: collision with root package name */
        public float f1062y0;

        /* JADX INFO: renamed from: z0, reason: collision with root package name */
        public float f1063z0;

        public a(int i3, int i4) {
            super(i3, i4);
            this.f1053p0 = 1.0f;
            this.f1054q0 = false;
            this.f1055r0 = 0.0f;
            this.f1056s0 = 0.0f;
            this.f1057t0 = 0.0f;
            this.f1058u0 = 0.0f;
            this.f1059v0 = 1.0f;
            this.f1060w0 = 1.0f;
            this.f1061x0 = 0.0f;
            this.f1062y0 = 0.0f;
            this.f1063z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1053p0 = 1.0f;
            this.f1054q0 = false;
            this.f1055r0 = 0.0f;
            this.f1056s0 = 0.0f;
            this.f1057t0 = 0.0f;
            this.f1058u0 = 0.0f;
            this.f1059v0 = 1.0f;
            this.f1060w0 = 1.0f;
            this.f1061x0 = 0.0f;
            this.f1062y0 = 0.0f;
            this.f1063z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.ConstraintSet_android_alpha) {
                    this.f1053p0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1053p0);
                } else if (index == R$styleable.ConstraintSet_android_elevation) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.f1055r0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1055r0);
                        this.f1054q0 = true;
                    }
                } else if (index == R$styleable.ConstraintSet_android_rotationX) {
                    this.f1057t0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1057t0);
                } else if (index == R$styleable.ConstraintSet_android_rotationY) {
                    this.f1058u0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1058u0);
                } else if (index == R$styleable.ConstraintSet_android_rotation) {
                    this.f1056s0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1056s0);
                } else if (index == R$styleable.ConstraintSet_android_scaleX) {
                    this.f1059v0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1059v0);
                } else if (index == R$styleable.ConstraintSet_android_scaleY) {
                    this.f1060w0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1060w0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotX) {
                    this.f1061x0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1061x0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotY) {
                    this.f1062y0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1062y0);
                } else if (index == R$styleable.ConstraintSet_android_translationX) {
                    this.f1063z0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1063z0);
                } else if (index == R$styleable.ConstraintSet_android_translationY) {
                    this.A0 = typedArrayObtainStyledAttributes.getFloat(index, this.A0);
                } else if (index == R$styleable.ConstraintSet_android_translationZ && Build.VERSION.SDK_INT >= 21) {
                    this.B0 = typedArrayObtainStyledAttributes.getFloat(index, this.B0);
                }
            }
        }
    }
}
