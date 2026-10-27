package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements MotionLayout.j {
    private boolean k;
    private boolean l;
    private float m;
    protected View[] n;

    public MotionHelper(Context context) {
        super(context);
        this.k = false;
        this.l = false;
    }

    public void A(MotionLayout motionLayout) {
    }

    public void B(Canvas canvas) {
    }

    public void C(Canvas canvas) {
    }

    public void D(MotionLayout motionLayout, HashMap<View, n> map) {
    }

    public void E(View view, float f) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void a(MotionLayout motionLayout, int i, int i2) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void b(MotionLayout motionLayout, int i, boolean z, float f) {
    }

    public void c(MotionLayout motionLayout, int i, int i2, float f) {
    }

    public void d(MotionLayout motionLayout, int i) {
    }

    public float getProgress() {
        return this.m;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.e.B9);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == androidx.constraintlayout.widget.e.D9) {
                    this.k = typedArrayObtainStyledAttributes.getBoolean(index, this.k);
                } else if (index == androidx.constraintlayout.widget.e.C9) {
                    this.l = typedArrayObtainStyledAttributes.getBoolean(index, this.l);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void setProgress(float f) {
        this.m = f;
        int i = 0;
        if (this.f1029c > 0) {
            this.n = n((ConstraintLayout) getParent());
            while (i < this.f1029c) {
                E(this.n[i], f);
                i++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i < childCount) {
            View childAt = viewGroup.getChildAt(i);
            if (!(childAt instanceof MotionHelper)) {
                E(childAt, f);
            }
            i++;
        }
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return this.l;
    }

    public boolean z() {
        return this.k;
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = false;
        this.l = false;
        o(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.k = false;
        this.l = false;
        o(attributeSet);
    }
}
