package androidx.constraintlayout.widget;

import a.f.a.i.a;
import a.f.b.b;
import a.f.b.i;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends b {
    public int j;
    public int k;
    public a l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    @Override // a.f.b.b
    public void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.l = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, i.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == i.ConstraintLayout_Layout_barrierDirection) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == i.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.y0 = typedArrayObtainStyledAttributes.getBoolean(index, true);
                } else if (index == i.ConstraintLayout_Layout_barrierMargin) {
                    this.l.z0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f632d = this.l;
        l();
    }

    public boolean getAllowsGoneWidget() {
        return this.l.y0;
    }

    public int getMargin() {
        return this.l.z0;
    }

    public int getType() {
        return this.j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0014, code lost:
    
        if (r0 == 6) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000b, code lost:
    
        if (r0 == 6) goto L9;
     */
    @Override // a.f.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(a.f.a.i.d r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.j
            r3.k = r0
            r1 = 6
            r2 = 5
            if (r5 == 0) goto Le
            if (r0 != r2) goto Lb
            goto L16
        Lb:
            if (r0 != r1) goto L18
            goto L10
        Le:
            if (r0 != r2) goto L14
        L10:
            r5 = 0
        L11:
            r3.k = r5
            goto L18
        L14:
            if (r0 != r1) goto L18
        L16:
            r5 = 1
            goto L11
        L18:
            boolean r5 = r4 instanceof a.f.a.i.a
            if (r5 == 0) goto L22
            a.f.a.i.a r4 = (a.f.a.i.a) r4
            int r3 = r3.k
            r4.x0 = r3
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.Barrier.h(a.f.a.i.d, boolean):void");
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.y0 = z;
    }

    public void setDpMargin(int i) {
        this.l.z0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.l.z0 = i;
    }

    public void setType(int i) {
        this.j = i;
    }
}
