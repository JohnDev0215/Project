package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class a extends c {
    private int j;
    private int k;
    private a.e.b.k.a l;

    public a(Context context) {
        super(context);
        super.setVisibility(8);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void o(a.e.b.k.e r6, int r7, boolean r8) {
        /*
            r5 = this;
            r5.k = r7
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 1
            r1 = 0
            r2 = 6
            r3 = 5
            r4 = 17
            if (r7 >= r4) goto L16
            int r7 = r5.j
            if (r7 != r3) goto L13
        L10:
            r5.k = r1
            goto L28
        L13:
            if (r7 != r2) goto L28
        L15:
            goto L1c
        L16:
            int r7 = r5.j
            if (r8 == 0) goto L22
            if (r7 != r3) goto L1f
        L1c:
            r5.k = r0
            goto L28
        L1f:
            if (r7 != r2) goto L28
            goto L10
        L22:
            if (r7 != r3) goto L25
            goto L10
        L25:
            if (r7 != r2) goto L28
            goto L15
        L28:
            boolean r7 = r6 instanceof a.e.b.k.a
            if (r7 == 0) goto L33
            a.e.b.k.a r6 = (a.e.b.k.a) r6
            int r7 = r5.k
            r6.M0(r7)
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.a.o(a.e.b.k.e, int, boolean):void");
    }

    @Override // androidx.constraintlayout.widget.c
    protected void g(AttributeSet attributeSet) {
        super.g(attributeSet);
        this.l = new a.e.b.k.a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, j.a1);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.j1) {
                    setType(typedArrayObtainStyledAttributes.getInt(index, 0));
                } else if (index == j.i1) {
                    this.l.L0(typedArrayObtainStyledAttributes.getBoolean(index, true));
                } else if (index == j.k1) {
                    this.l.N0(typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
        }
        this.e = this.l;
        m();
    }

    public int getMargin() {
        return this.l.J0();
    }

    public int getType() {
        return this.j;
    }

    @Override // androidx.constraintlayout.widget.c
    public void h(a.e.b.k.e eVar, boolean z) {
        o(eVar, this.j, z);
    }

    public boolean n() {
        return this.l.H0();
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.L0(z);
    }

    public void setDpMargin(int i) {
        this.l.N0((int) ((i * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i) {
        this.l.N0(i);
    }

    public void setType(int i) {
        this.j = i;
    }
}
