package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import b0.b;

/* JADX INFO: loaded from: classes.dex */
public class Constraints extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f1143d;

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
        if (this.f1143d == null) {
            this.f1143d = new b();
        }
        this.f1143d.j(this);
        return this.f1143d;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
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

    public Constraints(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        c(attributeSet);
        super.setVisibility(8);
    }

    public static class a extends ConstraintLayout.b {
        public float A0;
        public float B0;

        /* JADX INFO: renamed from: p0, reason: collision with root package name */
        public float f1144p0;

        /* JADX INFO: renamed from: q0, reason: collision with root package name */
        public boolean f1145q0;

        /* JADX INFO: renamed from: r0, reason: collision with root package name */
        public float f1146r0;

        /* JADX INFO: renamed from: s0, reason: collision with root package name */
        public float f1147s0;

        /* JADX INFO: renamed from: t0, reason: collision with root package name */
        public float f1148t0;

        /* JADX INFO: renamed from: u0, reason: collision with root package name */
        public float f1149u0;

        /* JADX INFO: renamed from: v0, reason: collision with root package name */
        public float f1150v0;

        /* JADX INFO: renamed from: w0, reason: collision with root package name */
        public float f1151w0;

        /* JADX INFO: renamed from: x0, reason: collision with root package name */
        public float f1152x0;

        /* JADX INFO: renamed from: y0, reason: collision with root package name */
        public float f1153y0;

        /* JADX INFO: renamed from: z0, reason: collision with root package name */
        public float f1154z0;

        public a(int i8, int i9) {
            super(i8, i9);
            this.f1144p0 = 1.0f;
            this.f1145q0 = false;
            this.f1146r0 = 0.0f;
            this.f1147s0 = 0.0f;
            this.f1148t0 = 0.0f;
            this.f1149u0 = 0.0f;
            this.f1150v0 = 1.0f;
            this.f1151w0 = 1.0f;
            this.f1152x0 = 0.0f;
            this.f1153y0 = 0.0f;
            this.f1154z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1144p0 = 1.0f;
            this.f1145q0 = false;
            this.f1146r0 = 0.0f;
            this.f1147s0 = 0.0f;
            this.f1148t0 = 0.0f;
            this.f1149u0 = 0.0f;
            this.f1150v0 = 1.0f;
            this.f1151w0 = 1.0f;
            this.f1152x0 = 0.0f;
            this.f1153y0 = 0.0f;
            this.f1154z0 = 0.0f;
            this.A0 = 0.0f;
            this.B0 = 0.0f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintSet);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.ConstraintSet_android_alpha) {
                    this.f1144p0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1144p0);
                } else if (index == R$styleable.ConstraintSet_android_elevation) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        this.f1146r0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1146r0);
                        this.f1145q0 = true;
                    }
                } else if (index == R$styleable.ConstraintSet_android_rotationX) {
                    this.f1148t0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1148t0);
                } else if (index == R$styleable.ConstraintSet_android_rotationY) {
                    this.f1149u0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1149u0);
                } else if (index == R$styleable.ConstraintSet_android_rotation) {
                    this.f1147s0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1147s0);
                } else if (index == R$styleable.ConstraintSet_android_scaleX) {
                    this.f1150v0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1150v0);
                } else if (index == R$styleable.ConstraintSet_android_scaleY) {
                    this.f1151w0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1151w0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotX) {
                    this.f1152x0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1152x0);
                } else if (index == R$styleable.ConstraintSet_android_transformPivotY) {
                    this.f1153y0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1153y0);
                } else if (index == R$styleable.ConstraintSet_android_translationX) {
                    this.f1154z0 = typedArrayObtainStyledAttributes.getFloat(index, this.f1154z0);
                } else if (index == R$styleable.ConstraintSet_android_translationY) {
                    this.A0 = typedArrayObtainStyledAttributes.getFloat(index, this.A0);
                } else if (index == R$styleable.ConstraintSet_android_translationZ && Build.VERSION.SDK_INT >= 21) {
                    this.B0 = typedArrayObtainStyledAttributes.getFloat(index, this.B0);
                }
            }
        }
    }
}
