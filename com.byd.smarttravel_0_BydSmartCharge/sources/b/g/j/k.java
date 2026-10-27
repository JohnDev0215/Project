package b.g.j;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f1133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f1134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f1135c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1136d;
    public int[] e;

    public k(View view) {
        this.f1135c = view;
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent viewParentI;
        if (!m() || (viewParentI = i(0)) == null) {
            return false;
        }
        return w.a(viewParentI, this.f1135c, f, f2, z);
    }

    public boolean b(float f, float f2) {
        ViewParent viewParentI;
        if (!m() || (viewParentI = i(0)) == null) {
            return false;
        }
        return w.b(viewParentI, this.f1135c, f, f2);
    }

    public boolean c(int i, int i2, int[] iArr, int[] iArr2) {
        return d(i, i2, iArr, iArr2, 0);
    }

    public boolean d(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent viewParentI;
        int i4;
        int i5;
        if (!m() || (viewParentI = i(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f1135c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            iArr = j();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        w.c(viewParentI, this.f1135c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f1135c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        h(i, i2, i3, i4, iArr, i5, iArr2);
    }

    public boolean f(int i, int i2, int i3, int i4, int[] iArr) {
        return h(i, i2, i3, i4, iArr, 0, null);
    }

    public boolean g(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return h(i, i2, i3, i4, iArr, i5, null);
    }

    public final boolean h(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent viewParentI;
        int i6;
        int i7;
        int[] iArr3;
        if (!m() || (viewParentI = i(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f1135c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            int[] iArrJ = j();
            iArrJ[0] = 0;
            iArrJ[1] = 0;
            iArr3 = iArrJ;
        } else {
            iArr3 = iArr2;
        }
        w.d(viewParentI, this.f1135c, i, i2, i3, i4, i5, iArr3);
        if (iArr != null) {
            this.f1135c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public final ViewParent i(int i) {
        if (i == 0) {
            return this.f1133a;
        }
        if (i != 1) {
            return null;
        }
        return this.f1134b;
    }

    public final int[] j() {
        if (this.e == null) {
            this.e = new int[2];
        }
        return this.e;
    }

    public boolean k() {
        return l(0);
    }

    public boolean l(int i) {
        return i(i) != null;
    }

    public boolean m() {
        return this.f1136d;
    }

    public void n(boolean z) {
        if (this.f1136d) {
            t.A0(this.f1135c);
        }
        this.f1136d = z;
    }

    public final void o(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f1133a = viewParent;
        } else {
            if (i != 1) {
                return;
            }
            this.f1134b = viewParent;
        }
    }

    public boolean p(int i) {
        return q(i, 0);
    }

    public boolean q(int i, int i2) {
        if (l(i2)) {
            return true;
        }
        if (!m()) {
            return false;
        }
        View view = this.f1135c;
        for (ViewParent parent = this.f1135c.getParent(); parent != null; parent = parent.getParent()) {
            if (w.f(parent, view, this.f1135c, i, i2)) {
                o(i2, parent);
                w.e(parent, view, this.f1135c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void r() {
        s(0);
    }

    public void s(int i) {
        ViewParent viewParentI = i(i);
        if (viewParentI != null) {
            w.g(viewParentI, this.f1135c, i);
            o(i, null);
        }
    }
}
