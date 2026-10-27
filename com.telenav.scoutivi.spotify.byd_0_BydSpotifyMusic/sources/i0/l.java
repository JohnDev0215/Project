package i0;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: compiled from: NestedScrollingChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewParent f6874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewParent f6875b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final View f6876c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f6877d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int[] f6878e;

    public l(View view) {
        this.f6876c = view;
    }

    private boolean g(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        ViewParent viewParentH;
        int i12;
        int i13;
        int[] iArr3;
        if (!l() || (viewParentH = h(i11)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0 && i9 == 0 && i10 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f6876c.getLocationInWindow(iArr);
            i12 = iArr[0];
            i13 = iArr[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr2 == null) {
            int[] iArrI = i();
            iArrI[0] = 0;
            iArrI[1] = 0;
            iArr3 = iArrI;
        } else {
            iArr3 = iArr2;
        }
        x.d(viewParentH, this.f6876c, i7, i8, i9, i10, i11, iArr3);
        if (iArr != null) {
            this.f6876c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i12;
            iArr[1] = iArr[1] - i13;
        }
        return true;
    }

    private ViewParent h(int i7) {
        if (i7 == 0) {
            return this.f6874a;
        }
        if (i7 != 1) {
            return null;
        }
        return this.f6875b;
    }

    private int[] i() {
        if (this.f6878e == null) {
            this.f6878e = new int[2];
        }
        return this.f6878e;
    }

    private void n(int i7, ViewParent viewParent) {
        if (i7 == 0) {
            this.f6874a = viewParent;
        } else {
            if (i7 != 1) {
                return;
            }
            this.f6875b = viewParent;
        }
    }

    public boolean a(float f7, float f8, boolean z6) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return x.a(viewParentH, this.f6876c, f7, f8, z6);
    }

    public boolean b(float f7, float f8) {
        ViewParent viewParentH;
        if (!l() || (viewParentH = h(0)) == null) {
            return false;
        }
        return x.b(viewParentH, this.f6876c, f7, f8);
    }

    public boolean c(int i7, int i8, int[] iArr, int[] iArr2) {
        return d(i7, i8, iArr, iArr2, 0);
    }

    public boolean d(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        ViewParent viewParentH;
        int i10;
        int i11;
        if (!l() || (viewParentH = h(i9)) == null) {
            return false;
        }
        if (i7 == 0 && i8 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f6876c.getLocationInWindow(iArr2);
            i10 = iArr2[0];
            i11 = iArr2[1];
        } else {
            i10 = 0;
            i11 = 0;
        }
        if (iArr == null) {
            iArr = i();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        x.c(viewParentH, this.f6876c, i7, i8, iArr, i9);
        if (iArr2 != null) {
            this.f6876c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i10;
            iArr2[1] = iArr2[1] - i11;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void e(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        g(i7, i8, i9, i10, iArr, i11, iArr2);
    }

    public boolean f(int i7, int i8, int i9, int i10, int[] iArr) {
        return g(i7, i8, i9, i10, iArr, 0, null);
    }

    public boolean j() {
        return k(0);
    }

    public boolean k(int i7) {
        return h(i7) != null;
    }

    public boolean l() {
        return this.f6877d;
    }

    public void m(boolean z6) {
        if (this.f6877d) {
            u.C0(this.f6876c);
        }
        this.f6877d = z6;
    }

    public boolean o(int i7) {
        return p(i7, 0);
    }

    public boolean p(int i7, int i8) {
        if (k(i8)) {
            return true;
        }
        if (!l()) {
            return false;
        }
        View view = this.f6876c;
        for (ViewParent parent = this.f6876c.getParent(); parent != null; parent = parent.getParent()) {
            if (x.f(parent, view, this.f6876c, i7, i8)) {
                n(i8, parent);
                x.e(parent, view, this.f6876c, i7, i8);
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

    public void r(int i7) {
        ViewParent viewParentH = h(i7);
        if (viewParentH != null) {
            x.g(viewParentH, this.f6876c, i7);
            n(i7, null);
        }
    }
}
