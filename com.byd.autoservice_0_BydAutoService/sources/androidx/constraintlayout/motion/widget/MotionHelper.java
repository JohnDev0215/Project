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

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f732j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f733k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f734l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View[] f735m;

    public MotionHelper(Context context) {
        super(context);
        this.f732j = false;
        this.f733k = false;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void a(MotionLayout motionLayout, int i3, int i4) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void b(MotionLayout motionLayout, int i3, boolean z3, float f3) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void c(MotionLayout motionLayout, int i3, int i4, float f3) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.i
    public void d(MotionLayout motionLayout, int i3) {
    }

    public float getProgress() {
        return this.f734l;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void m(AttributeSet attributeSet) {
        super.m(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                if (index == R$styleable.MotionHelper_onShow) {
                    this.f732j = typedArrayObtainStyledAttributes.getBoolean(index, this.f732j);
                } else if (index == R$styleable.MotionHelper_onHide) {
                    this.f733k = typedArrayObtainStyledAttributes.getBoolean(index, this.f733k);
                }
            }
        }
    }

    public void setProgress(float f3) {
        this.f734l = f3;
        int i3 = 0;
        if (this.f971c > 0) {
            this.f735m = l((ConstraintLayout) getParent());
            while (i3 < this.f971c) {
                x(this.f735m[i3], f3);
                i3++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i3 < childCount) {
            View childAt = viewGroup.getChildAt(i3);
            if (!(childAt instanceof MotionHelper)) {
                x(childAt, f3);
            }
            i3++;
        }
    }

    public boolean v() {
        return this.f733k;
    }

    public boolean w() {
        return this.f732j;
    }

    public void x(View view, float f3) {
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f732j = false;
        this.f733k = false;
        m(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f732j = false;
        this.f733k = false;
        m(attributeSet);
    }
}
