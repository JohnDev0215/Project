package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import java.util.HashMap;
import o1.a;
import r1.AbstractC2270b;
import r1.p;

/* JADX INFO: loaded from: classes.dex */
public class Barrier extends AbstractC2270b {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f11698s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f11699t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a f11700u;

    public Barrier(Context context) {
        super(context);
        this.l = new int[32];
        this.f21587r = new HashMap();
        this.f21584n = context;
        f(null);
        super.setVisibility(8);
    }

    public final void f(AttributeSet attributeSet) {
        int[] iArr = p.f21764b;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, iArr);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == 35) {
                    String string = typedArrayObtainStyledAttributes.getString(index);
                    this.f21585p = string;
                    setIds(string);
                } else if (index == 36) {
                    String string2 = typedArrayObtainStyledAttributes.getString(index);
                    this.f21586q = string2;
                    setReferenceTags(string2);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f11700u = new a();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(attributeSet, iArr);
            int indexCount2 = typedArrayObtainStyledAttributes2.getIndexCount();
            for (int i10 = 0; i10 < indexCount2; i10++) {
                int index2 = typedArrayObtainStyledAttributes2.getIndex(i10);
                if (index2 == 26) {
                    setType(typedArrayObtainStyledAttributes2.getInt(index2, 0));
                } else if (index2 == 25) {
                    this.f11700u.f20234u0 = typedArrayObtainStyledAttributes2.getBoolean(index2, true);
                } else if (index2 == 27) {
                    this.f11700u.f20235v0 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(index2, 0);
                }
            }
            typedArrayObtainStyledAttributes2.recycle();
        }
        this.o = this.f11700u;
        e();
    }

    public boolean getAllowsGoneWidget() {
        return this.f11700u.f20234u0;
    }

    public int getMargin() {
        return this.f11700u.f20235v0;
    }

    public int getType() {
        return this.f11698s;
    }

    public void setAllowsGoneWidget(boolean z6) {
        this.f11700u.f20234u0 = z6;
    }

    public void setDpMargin(int i7) {
        this.f11700u.f20235v0 = (int) ((i7 * getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setMargin(int i7) {
        this.f11700u.f20235v0 = i7;
    }

    public void setType(int i7) {
        this.f11698s = i7;
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new int[32];
        this.f21587r = new HashMap();
        this.f21584n = context;
        f(attributeSet);
        super.setVisibility(8);
    }
}
