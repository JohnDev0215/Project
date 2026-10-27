package l0;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f3640a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f3641b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f3642c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f3644e;

    public k(View view) {
        this.f3642c = view;
    }

    public boolean a(float f3, float f4, boolean z3) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return w.a(viewParentH, this.f3642c, f3, f4, z3);
    }

    public boolean b(float f3, float f4) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return w.b(viewParentH, this.f3642c, f3, f4);
    }

    public boolean c(int i3, int i4, int[] iArr, int[] iArr2) {
        return d(i3, i4, iArr, iArr2, 0);
    }

    public boolean d(int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        ViewParent viewParentH;
        int i6;
        int i7;
        if (!l() || (viewParentH = h(i5)) == null) {
            return false;
        }
        if (i3 == 0 && i4 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f3642c.getLocationInWindow(iArr2);
            i6 = iArr2[0];
            i7 = iArr2[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr == null) {
            iArr = i();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        w.c(viewParentH, this.f3642c, i3, i4, iArr, i5);
        if (iArr2 != null) {
            this.f3642c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i6;
            iArr2[1] = iArr2[1] - i7;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i3, int i4, int i5, int i6, int[] iArr, int i7, int[] iArr2) {
        g(i3, i4, i5, i6, iArr, i7, iArr2);
    }

    public boolean f(int i3, int i4, int i5, int i6, int[] iArr) {
        return g(i3, i4, i5, i6, iArr, 0, null);
    }

    public final boolean g(int i3, int i4, int i5, int i6, int[] iArr, int i7, int[] iArr2) {
        ViewParent viewParentH;
        int i8;
        int i9;
        int[] iArr3;
        if (!l() || (viewParentH = h(i7)) == null) {
            return false;
        }
        if (i3 == 0 && i4 == 0 && i5 == 0 && i6 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f3642c.getLocationInWindow(iArr);
            i8 = iArr[0];
            i9 = iArr[1];
        } else {
            i8 = 0;
            i9 = 0;
        }
        if (iArr2 == null) {
            int[] iArrI = i();
            iArrI[0] = 0;
            iArrI[1] = 0;
            iArr3 = iArrI;
        } else {
            iArr3 = iArr2;
        }
        w.d(viewParentH, this.f3642c, i3, i4, i5, i6, i7, iArr3);
        if (iArr != null) {
            this.f3642c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i8;
            iArr[1] = iArr[1] - i9;
        }
        return true;
    }

    public final ViewParent h(int i3) {
        if (i3 == 0) {
            return this.f3640a;
        }
        if (i3 != 1) {
            return null;
        }
        return this.f3641b;
    }

    public final int[] i() {
        if (this.f3644e == null) {
            this.f3644e = new int[2];
        }
        return this.f3644e;
    }

    public boolean j() {
        return k(0);
    }

    public boolean k(int i3) {
        return h(i3) != null;
    }

    public boolean l() {
        return this.f3643d;
    }

    public void m(boolean z3) {
        if (this.f3643d) {
            t.A0(this.f3642c);
        }
        this.f3643d = z3;
    }

    public final void n(int i3, ViewParent viewParent) {
        if (i3 == 0) {
            this.f3640a = viewParent;
        } else {
            if (i3 != 1) {
                return;
            }
            this.f3641b = viewParent;
        }
    }

    public boolean o(int i3) {
        return p(i3, 0);
    }

    public boolean p(int i3, int i4) {
        if (k(i4)) {
            return true;
        }
        if (!l()) {
            return false;
        }
        View view = this.f3642c;
        for (ViewParent parent = this.f3642c.getParent(); parent != null; parent = parent.getParent()) {
            if (w.f(parent, view, this.f3642c, i3, i4)) {
                n(i4, parent);
                w.e(parent, view, this.f3642c, i3, i4);
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

    public void r(int i3) {
        ViewParent viewParentH = h(i3);
        if (viewParentH != null) {
            w.g(viewParentH, this.f3642c, i3);
            n(i3, null);
        }
    }
}
