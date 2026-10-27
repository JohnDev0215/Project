package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import b.e.c.e;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements MotionLayout.i {
    public boolean H3;
    public boolean I3;
    public float J3;
    public View[] K3;

    public MotionHelper(Context context) {
        super(context);
        this.H3 = false;
        this.I3 = false;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void a(MotionLayout motionLayout, int i2, int i3) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void b(MotionLayout motionLayout, int i2, boolean z, float f2) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void c(MotionLayout motionLayout, int i2, int i3, float f2) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void d(MotionLayout motionLayout, int i2) {
    }

    public float getProgress() {
        return this.J3;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.E6);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i2);
                if (index == e.G6) {
                    this.H3 = typedArrayObtainStyledAttributes.getBoolean(index, this.H3);
                } else if (index == e.F6) {
                    this.I3 = typedArrayObtainStyledAttributes.getBoolean(index, this.I3);
                }
            }
        }
    }

    public void setProgress(float f2) {
        this.J3 = f2;
        int i2 = 0;
        if (this.f394d > 0) {
            this.K3 = l((ConstraintLayout) getParent());
            while (i2 < this.f394d) {
                x(this.K3[i2], f2);
                i2++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i2 < childCount) {
            View childAt = viewGroup.getChildAt(i2);
            if (!(childAt instanceof MotionHelper)) {
                x(childAt, f2);
            }
            i2++;
        }
    }

    public boolean v() {
        return this.I3;
    }

    public boolean w() {
        return this.H3;
    }

    public void x(View view, float f2) {
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H3 = false;
        this.I3 = false;
        m(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.H3 = false;
        this.I3 = false;
        m(attributeSet);
    }
}
