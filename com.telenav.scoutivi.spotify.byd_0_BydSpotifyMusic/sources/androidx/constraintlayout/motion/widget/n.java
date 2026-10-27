package androidx.constraintlayout.motion.widget;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashMap;

/* JADX INFO: compiled from: MotionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends androidx.constraintlayout.widget.b implements MotionLayout.j {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1498o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1499p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private float f1500q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    protected View[] f1501r;

    public void A(MotionLayout motionLayout) {
    }

    public void B(Canvas canvas) {
    }

    public void C(Canvas canvas) {
    }

    public void D(MotionLayout motionLayout, HashMap<View, m> map) {
    }

    public void E(View view, float f7) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void a(MotionLayout motionLayout, int i7, int i8) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void b(MotionLayout motionLayout, int i7, boolean z6, float f7) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void c(MotionLayout motionLayout, int i7, int i8, float f7) {
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout.j
    public void d(MotionLayout motionLayout, int i7) {
    }

    public float getProgress() {
        return this.f1500q;
    }

    @Override // androidx.constraintlayout.widget.b
    protected void o(AttributeSet attributeSet) {
        super.o(attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, androidx.constraintlayout.widget.i.MotionHelper);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == androidx.constraintlayout.widget.i.MotionHelper_onShow) {
                    this.f1498o = typedArrayObtainStyledAttributes.getBoolean(index, this.f1498o);
                } else if (index == androidx.constraintlayout.widget.i.MotionHelper_onHide) {
                    this.f1499p = typedArrayObtainStyledAttributes.getBoolean(index, this.f1499p);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public void setProgress(float f7) {
        this.f1500q = f7;
        int i7 = 0;
        if (this.f1741g > 0) {
            this.f1501r = n((ConstraintLayout) getParent());
            while (i7 < this.f1741g) {
                E(this.f1501r[i7], f7);
                i7++;
            }
            return;
        }
        ViewGroup viewGroup = (ViewGroup) getParent();
        int childCount = viewGroup.getChildCount();
        while (i7 < childCount) {
            View childAt = viewGroup.getChildAt(i7);
            if (!(childAt instanceof n)) {
                E(childAt, f7);
            }
            i7++;
        }
    }

    public boolean x() {
        return false;
    }

    public boolean y() {
        return this.f1499p;
    }

    public boolean z() {
        return this.f1498o;
    }
}
