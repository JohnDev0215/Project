package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.util.HashMap;
import p.al;
import p.ay;
import p.sz1;
import p.ty;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends ay {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f14p;
    public int q;
    public al r;

    public Barrier(Context context) {
        super(context);
        this.c = new int[32];
        this.o = new HashMap();
        this.k = context;
        f(null);
        super.setVisibility(8);
    }

    public final void f(AttributeSet attributeSet) {
        int[] iArr = sz1.b;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, iArr);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == 35) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.m = string;
                    setIds(string);
                } else if (index == 36) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.n = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        al alVar = new al();
        alVar.p0 = new ty[4];
        alVar.q0 = 0;
        alVar.r0 = 0;
        alVar.s0 = true;
        alVar.t0 = 0;
        alVar.u0 = false;
        this.r = alVar;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, iArr);
            int indexCount2 = typedArrayObtainStyledAttributes2.getIndexCount();
            for (int i2 = 0; i2 < indexCount2; i2++) {
                int index2 = typedArrayObtainStyledAttributes2.getIndex(i2);
                if (index2 == 26) {
                    setType(typedArrayObtainStyledAttributes2.getInt(index2, 0));
                } else if (index2 == 25) {
                    this.r.s0 = typedArrayObtainStyledAttributes2.getBoolean(index2, true);
                } else if (index2 == 27) {
                    this.r.t0 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                }
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        this.l = this.r;
        e();
    }

    public boolean getAllowsGoneWidget() {
        return this.r.s0;
    }

    public int getMargin() {
        return this.r.t0;
    }

    public int getType() {
        return this.f14p;
    }

    public void setAllowsGoneWidget(boolean z) {
        this.r.s0 = z;
    }

    public void setDpMargin(int i) {
        this.r.t0 = (int) ((i * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i) {
        this.r.t0 = i;
    }

    public void setType(int i) {
        this.f14p = i;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new int[32];
        this.o = new HashMap();
        this.k = context;
        f(attributeSet);
        super.setVisibility(8);
    }
}
