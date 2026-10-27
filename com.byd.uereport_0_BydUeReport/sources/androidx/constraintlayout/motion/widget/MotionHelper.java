package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements MotionLayout.i {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f825l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f826m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f827n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View[] f828o;

    public MotionHelper(Context context) {
        super(context);
        this.f825l = false;
        this.f826m = false;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void a(MotionLayout motionLayout, int i8, int i9, float f8) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void b(MotionLayout motionLayout, int i8, int i9) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void c(MotionLayout motionLayout, int i8, boolean z7, float f8) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void d(MotionLayout motionLayout, int i8) {
    }

    public float getProgress() {
        return this.f827n;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i8 = 0; i8 < indexCount; i8++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i8);
                if (index == R$styleable.MotionHelper_onShow) {
                    this.f825l = typedArrayObtainStyledAttributes.getBoolean(index, this.f825l);
                } else if (index == R$styleable.MotionHelper_onHide) {
                    this.f826m = typedArrayObtainStyledAttributes.getBoolean(index, this.f826m);
                }
            }
        }
    }

    public void setProgress(float f8) {
        this.f827n = f8;
        int i8 = 0;
        if (this.f1062e > 0) {
            this.f828o = l((ConstraintLayout) getParent());
            while (i8 < this.f1062e) {
                x(this.f828o[i8], f8);
                i8++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i8 < childCount) {
            View childAt = viewGroup.getChildAt(i8);
            if (!(childAt instanceof MotionHelper)) {
                x(childAt, f8);
            }
            i8++;
        }
    }

    public boolean v() {
        return this.f826m;
    }

    public boolean w() {
        return this.f825l;
    }

    public void x(View view, float f8) {
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f825l = false;
        this.f826m = false;
        m(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f825l = false;
        this.f826m = false;
        m(attributeSet);
    }
}
