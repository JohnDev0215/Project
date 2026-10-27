package b.h.m;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f2633a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f2634b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f2635c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2636d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f2637e;

    public k(View view) {
        this.f2635c = view;
    }

    public boolean a(float f2, float f3, boolean z) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return w.a(viewParentH, this.f2635c, f2, f3, z);
    }

    public boolean b(float f2, float f3) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return w.b(viewParentH, this.f2635c, f2, f3);
    }

    public boolean c(int i2, int i3, int[] iArr, int[] iArr2) {
        return d(i2, i3, iArr, iArr2, 0);
    }

    public boolean d(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ViewParent viewParentH;
        int i5;
        int i6;
        if (!l() || (viewParentH = h(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f2635c.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = i();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        w.c(viewParentH, this.f2635c, i2, i3, iArr, i4);
        if (iArr2 != null) {
            this.f2635c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        g(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public boolean f(int i2, int i3, int i4, int i5, int[] iArr) {
        return g(i2, i3, i4, i5, iArr, 0, null);
    }

    public final boolean g(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        ViewParent viewParentH;
        int i7;
        int i8;
        int[] iArr3;
        if (!l() || (viewParentH = h(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f2635c.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            int[] iArrI = i();
            iArrI[0] = 0;
            iArrI[1] = 0;
            iArr3 = iArrI;
        } else {
            iArr3 = iArr2;
        }
        w.d(viewParentH, this.f2635c, i2, i3, i4, i5, i6, iArr3);
        if (iArr != null) {
            this.f2635c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    public final ViewParent h(int i2) {
        if (i2 == 0) {
            return this.f2633a;
        }
        if (i2 != 1) {
            return null;
        }
        return this.f2634b;
    }

    public final int[] i() {
        if (this.f2637e == null) {
            this.f2637e = new int[2];
        }
        return this.f2637e;
    }

    public boolean j() {
        return k(0);
    }

    public boolean k(int i2) {
        return h(i2) != null;
    }

    public boolean l() {
        return this.f2636d;
    }

    public void m(boolean z) {
        if (this.f2636d) {
            t.C0(this.f2635c);
        }
        this.f2636d = z;
    }

    public final void n(int i2, ViewParent viewParent) {
        if (i2 == 0) {
            this.f2633a = viewParent;
        } else {
            if (i2 != 1) {
                return;
            }
            this.f2634b = viewParent;
        }
    }

    public boolean o(int i2) {
        return p(i2, 0);
    }

    public boolean p(int i2, int i3) {
        if (k(i3)) {
            return true;
        }
        if (!l()) {
            return false;
        }
        View view = this.f2635c;
        for (ViewParent parent = this.f2635c.getParent(); parent != null; parent = parent.getParent()) {
            if (w.f(parent, view, this.f2635c, i2, i3)) {
                n(i3, parent);
                w.e(parent, view, this.f2635c, i2, i3);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void q() {
        r(0);
    }

    public void r(int i2) {
        ViewParent viewParentH = h(i2);
        if (viewParentH != null) {
            w.g(viewParentH, this.f2635c, i2);
            n(i2, null);
        }
    }
}
