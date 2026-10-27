package o0;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f6688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f6689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f6690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f6692e;

    public k(View view) {
        this.f6690c = view;
    }

    public boolean a(float f8, float f9, boolean z7) {
        ViewParent viewParentI;
        if (!m() || (viewParentI = i(0)) == null) {
            return false;
        }
        return w.a(viewParentI, this.f6690c, f8, f9, z7);
    }

    public boolean b(float f8, float f9) {
        ViewParent viewParentI;
        if (!m() || (viewParentI = i(0)) == null) {
            return false;
        }
        return w.b(viewParentI, this.f6690c, f8, f9);
    }

    public boolean c(int i8, int i9, int[] iArr, int[] iArr2) {
        return d(i8, i9, iArr, iArr2, 0);
    }

    public boolean d(int i8, int i9, int[] iArr, int[] iArr2, int i10) {
        ViewParent viewParentI;
        int i11;
        int i12;
        if (!m() || (viewParentI = i(i10)) == null) {
            return false;
        }
        if (i8 == 0 && i9 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f6690c.getLocationInWindow(iArr2);
            i11 = iArr2[0];
            i12 = iArr2[1];
        } else {
            i11 = 0;
            i12 = 0;
        }
        if (iArr == null) {
            iArr = j();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        w.c(viewParentI, this.f6690c, i8, i9, iArr, i10);
        if (iArr2 != null) {
            this.f6690c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i11;
            iArr2[1] = iArr2[1] - i12;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        h(i8, i9, i10, i11, iArr, i12, iArr2);
    }

    public boolean f(int i8, int i9, int i10, int i11, int[] iArr) {
        return h(i8, i9, i10, i11, iArr, 0, null);
    }

    public boolean g(int i8, int i9, int i10, int i11, int[] iArr, int i12) {
        return h(i8, i9, i10, i11, iArr, i12, null);
    }

    public final boolean h(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        ViewParent viewParentI;
        int i13;
        int i14;
        int[] iArr3;
        if (!m() || (viewParentI = i(i12)) == null) {
            return false;
        }
        if (i8 == 0 && i9 == 0 && i10 == 0 && i11 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f6690c.getLocationInWindow(iArr);
            i13 = iArr[0];
            i14 = iArr[1];
        } else {
            i13 = 0;
            i14 = 0;
        }
        if (iArr2 == null) {
            int[] iArrJ = j();
            iArrJ[0] = 0;
            iArrJ[1] = 0;
            iArr3 = iArrJ;
        } else {
            iArr3 = iArr2;
        }
        w.d(viewParentI, this.f6690c, i8, i9, i10, i11, i12, iArr3);
        if (iArr != null) {
            this.f6690c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i13;
            iArr[1] = iArr[1] - i14;
        }
        return true;
    }

    public final ViewParent i(int i8) {
        if (i8 == 0) {
            return this.f6688a;
        }
        if (i8 != 1) {
            return null;
        }
        return this.f6689b;
    }

    public final int[] j() {
        if (this.f6692e == null) {
            this.f6692e = new int[2];
        }
        return this.f6692e;
    }

    public boolean k() {
        return l(0);
    }

    public boolean l(int i8) {
        return i(i8) != null;
    }

    public boolean m() {
        return this.f6691d;
    }

    public void n(boolean z7) {
        if (this.f6691d) {
            t.D0(this.f6690c);
        }
        this.f6691d = z7;
    }

    public final void o(int i8, ViewParent viewParent) {
        if (i8 == 0) {
            this.f6688a = viewParent;
        } else {
            if (i8 != 1) {
                return;
            }
            this.f6689b = viewParent;
        }
    }

    public boolean p(int i8) {
        return q(i8, 0);
    }

    public boolean q(int i8, int i9) {
        if (l(i9)) {
            return true;
        }
        if (!m()) {
            return false;
        }
        View view = this.f6690c;
        for (ViewParent parent = this.f6690c.getParent(); parent != null; parent = parent.getParent()) {
            if (w.f(parent, view, this.f6690c, i8, i9)) {
                o(i9, parent);
                w.e(parent, view, this.f6690c, i8, i9);
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

    public void s(int i8) {
        ViewParent viewParentI = i(i8);
        if (viewParentI != null) {
            w.g(viewParentI, this.f6690c, i8);
            o(i8, null);
        }
    }
}
