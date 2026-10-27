package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.widget.e;

/* JADX INFO: loaded from: classes.dex */
public class MotionEffect extends MotionHelper {
    private float o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t;
    private int u;
    private int v;

    public MotionEffect(Context context) {
        super(context);
        this.o = 0.1f;
        this.p = 49;
        this.q = 50;
        this.r = 0;
        this.s = 0;
        this.t = true;
        this.u = -1;
        this.v = -1;
    }

    private void F(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.s9);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.w9) {
                    int i2 = typedArrayObtainStyledAttributes.getInt(index, this.p);
                    this.p = i2;
                    this.p = Math.max(Math.min(i2, 99), 0);
                } else if (index == e.u9) {
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, this.q);
                    this.q = i3;
                    this.q = Math.max(Math.min(i3, 99), 0);
                } else if (index == e.y9) {
                    this.r = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.r);
                } else if (index == e.z9) {
                    this.s = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.s);
                } else if (index == e.t9) {
                    this.o = typedArrayObtainStyledAttributes.getFloat(index, this.o);
                } else if (index == e.v9) {
                    this.v = typedArrayObtainStyledAttributes.getInt(index, this.v);
                } else if (index == e.x9) {
                    this.t = typedArrayObtainStyledAttributes.getBoolean(index, this.t);
                } else if (index == e.A9) {
                    this.u = typedArrayObtainStyledAttributes.getResourceId(index, this.u);
                }
            }
            int i4 = this.p;
            int i5 = this.q;
            if (i4 == i5) {
                if (i4 > 0) {
                    this.p = i4 - 1;
                } else {
                    this.q = i5 + 1;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0188, code lost:
    
        if (r14 == 0.0f) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x019c, code lost:
    
        if (r14 == 0.0f) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01ac, code lost:
    
        if (r15 == 0.0f) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0164  */
    @Override // androidx.constraintlayout.motion.widget.MotionHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void D(androidx.constraintlayout.motion.widget.MotionLayout r22, java.util.HashMap<android.view.View, androidx.constraintlayout.motion.widget.n> r23) {
        /*
            Method dump skipped, instruction units count: 502
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.helper.widget.MotionEffect.D(androidx.constraintlayout.motion.widget.MotionLayout, java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper
    public boolean x() {
        return true;
    }

    public MotionEffect(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = 0.1f;
        this.p = 49;
        this.q = 50;
        this.r = 0;
        this.s = 0;
        this.t = true;
        this.u = -1;
        this.v = -1;
        F(context, attributeSet);
    }

    public MotionEffect(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = 0.1f;
        this.p = 49;
        this.q = 50;
        this.r = 0;
        this.s = 0;
        this.t = true;
        this.u = -1;
        this.v = -1;
        F(context, attributeSet);
    }
}
