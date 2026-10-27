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
import defpackage.bj1;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class MotionHelper extends ConstraintHelper implements MotionLayout.h {
    public boolean n;
    public boolean o;
    public float p;
    public View[] q;

    public MotionHelper(Context context) {
        super(context);
        this.n = false;
        this.o = false;
    }

    public float getProgress() {
        return this.p;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionHelper_onShow) {
                    this.n = typedArrayObtainStyledAttributes.getBoolean(index, this.n);
                } else if (index == R$styleable.MotionHelper_onHide) {
                    this.o = typedArrayObtainStyledAttributes.getBoolean(index, this.o);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void setProgress(float f) {
        this.p = f;
        int i = 0;
        if (this.f > 0) {
            this.q = m((ConstraintLayout) getParent());
            while (i < this.f) {
                View view = this.q[i];
                i++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i < childCount) {
            boolean z = viewGroup.getChildAt(i) instanceof MotionHelper;
            i++;
        }
    }

    public MotionHelper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = false;
        this.o = false;
        n(attributeSet);
    }

    public MotionHelper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = false;
        this.o = false;
        n(attributeSet);
    }

    public void b() {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.h
    public final void c() {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.h
    public final void d() {
    }

    public void a(int i) {
    }

    public void u(MotionLayout motionLayout, HashMap<View, bj1> map) {
    }
}
