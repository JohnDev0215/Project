package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.widget.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    public float r;
    public int s;
    public int t;
    public int u;
    public int v;
    public boolean w;
    public int x;
    public int y;

    public MotionEffect(Context context) {
        super(context);
        this.r = 0.1f;
        this.s = 49;
        this.t = 50;
        this.u = 0;
        this.v = 0;
        this.w = true;
        this.x = -1;
        this.y = -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0186, code lost:
    
        if (r14 == 0.0f) goto L67;
     */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01b7  */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(androidx.constraintlayout.motion.widget.MotionLayout r21, java.util.HashMap<android.view.View, defpackage.bj1> r22) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.MotionEffect.u(androidx.constraintlayout.motion.widget.MotionLayout, java.util.HashMap):void");
    }

    public final void v(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MotionEffect);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionEffect_motionEffect_start) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(index, this.s);
                    this.s = i2;
                    this.s = Math.max(Math.min(i2, 99), 0);
                } else if (index == R$styleable.MotionEffect_motionEffect_end) {
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, this.t);
                    this.t = i3;
                    this.t = Math.max(Math.min(i3, 99), 0);
                } else if (index == R$styleable.MotionEffect_motionEffect_translationX) {
                    this.u = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.u);
                } else if (index == R$styleable.MotionEffect_motionEffect_translationY) {
                    this.v = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.v);
                } else if (index == R$styleable.MotionEffect_motionEffect_alpha) {
                    this.r = typedArrayObtainStyledAttributes.getFloat(index, this.r);
                } else if (index == R$styleable.MotionEffect_motionEffect_move) {
                    this.y = typedArrayObtainStyledAttributes.getInt(index, this.y);
                } else if (index == R$styleable.MotionEffect_motionEffect_strict) {
                    this.w = typedArrayObtainStyledAttributes.getBoolean(index, this.w);
                } else if (index == R$styleable.MotionEffect_motionEffect_viewTransition) {
                    this.x = typedArrayObtainStyledAttributes.getResourceId(index, this.x);
                }
            }
            int i4 = this.s;
            int i5 = this.t;
            if (i4 == i5) {
                if (i4 > 0) {
                    this.s = i4 - 1;
                } else {
                    this.t = i5 + 1;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = 0.1f;
        this.s = 49;
        this.t = 50;
        this.u = 0;
        this.v = 0;
        this.w = true;
        this.x = -1;
        this.y = -1;
        v(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.r = 0.1f;
        this.s = 49;
        this.t = 50;
        this.u = 0;
        this.v = 0;
        this.w = true;
        this.x = -1;
        this.y = -1;
        v(context, attributeSet);
    }
}
