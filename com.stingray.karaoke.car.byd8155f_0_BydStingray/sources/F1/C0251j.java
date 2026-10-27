package F1;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/* JADX INFO: renamed from: F1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0251j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f2339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f2340b;
    public final ViewGroup c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f2341e;

    public C0251j(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public final boolean a(float f10, float f11, boolean z6) {
        ViewParent viewParentE;
        if (!this.d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedFling(this.c, f10, f11, z6);
        } catch (AbstractMethodError e10) {
            h6.n.i("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedFling", e10);
            return false;
        }
    }

    public final boolean b(float f10, float f11) {
        ViewParent viewParentE;
        if (!this.d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedPreFling(this.c, f10, f11);
        } catch (AbstractMethodError e10) {
            h6.n.i("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreFling", e10);
            return false;
        }
    }

    public final boolean c(int i7, int i10, int i11, int[] iArr, int[] iArr2) {
        ViewParent viewParentE;
        int i12;
        int i13;
        int[] iArr3;
        if (!this.d || (viewParentE = e(i11)) == null) {
            return false;
        }
        if (i7 == 0 && i10 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        ViewGroup viewGroup = this.c;
        if (iArr2 != null) {
            viewGroup.getLocationInWindow(iArr2);
            int i14 = iArr2[0];
            i13 = iArr2[1];
            i12 = i14;
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr == null) {
            if (this.f2341e == null) {
                this.f2341e = new int[2];
            }
            iArr3 = this.f2341e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        if (viewParentE instanceof InterfaceC0252k) {
            ((InterfaceC0252k) viewParentE).c(viewGroup, i7, i10, iArr3, i11);
        } else if (i11 == 0) {
            try {
                viewParentE.onNestedPreScroll(viewGroup, i7, i10, iArr3);
            } catch (AbstractMethodError e10) {
                h6.n.i("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreScroll", e10);
            }
        }
        if (iArr2 != null) {
            viewGroup.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i12;
            iArr2[1] = iArr2[1] - i13;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    public final boolean d(int i7, int i10, int i11, int i12, int[] iArr, int i13, int[] iArr2) {
        ViewParent viewParentE;
        int i14;
        int i15;
        int[] iArr3;
        if (!this.d || (viewParentE = e(i13)) == null) {
            return false;
        }
        if (i7 == 0 && i10 == 0 && i11 == 0 && i12 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        ViewGroup viewGroup = this.c;
        if (iArr != null) {
            viewGroup.getLocationInWindow(iArr);
            i14 = iArr[0];
            i15 = iArr[1];
        } else {
            i14 = 0;
            i15 = 0;
        }
        if (iArr2 == null) {
            if (this.f2341e == null) {
                this.f2341e = new int[2];
            }
            int[] iArr4 = this.f2341e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        if (viewParentE instanceof InterfaceC0253l) {
            ((InterfaceC0253l) viewParentE).g(viewGroup, i7, i10, i11, i12, i13, iArr3);
        } else {
            iArr3[0] = iArr3[0] + i11;
            iArr3[1] = iArr3[1] + i12;
            if (viewParentE instanceof InterfaceC0252k) {
                ((InterfaceC0252k) viewParentE).h(viewGroup, i7, i10, i11, i12, i13);
            } else if (i13 == 0) {
                try {
                    viewParentE.onNestedScroll(viewGroup, i7, i10, i11, i12);
                } catch (AbstractMethodError e10) {
                    h6.n.i("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedScroll", e10);
                }
            }
        }
        if (iArr != null) {
            viewGroup.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i14;
            iArr[1] = iArr[1] - i15;
        }
        return true;
    }

    public final ViewParent e(int i7) {
        if (i7 == 0) {
            return this.f2339a;
        }
        if (i7 != 1) {
            return null;
        }
        return this.f2340b;
    }

    public final boolean f(int i7) {
        return e(i7) != null;
    }

    public final boolean g(int i7, int i10) {
        boolean zOnStartNestedScroll;
        if (f(i10)) {
            return true;
        }
        if (this.d) {
            View view = this.c;
            View view2 = view;
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                boolean z6 = parent instanceof InterfaceC0252k;
                if (z6) {
                    zOnStartNestedScroll = ((InterfaceC0252k) parent).i(view2, view, i7, i10);
                } else if (i10 == 0) {
                    try {
                        zOnStartNestedScroll = parent.onStartNestedScroll(view2, view, i7);
                    } catch (AbstractMethodError e10) {
                        h6.n.i("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e10);
                        zOnStartNestedScroll = false;
                    }
                } else {
                    zOnStartNestedScroll = false;
                }
                if (zOnStartNestedScroll) {
                    if (i10 == 0) {
                        this.f2339a = parent;
                    } else if (i10 == 1) {
                        this.f2340b = parent;
                    }
                    if (z6) {
                        ((InterfaceC0252k) parent).a(view2, view, i7, i10);
                    } else if (i10 == 0) {
                        try {
                            parent.onNestedScrollAccepted(view2, view, i7);
                        } catch (AbstractMethodError e11) {
                            h6.n.i("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e11);
                        }
                    }
                    return true;
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
        }
        return false;
    }

    public final void h(int i7) {
        ViewParent viewParentE = e(i7);
        if (viewParentE != null) {
            boolean z6 = viewParentE instanceof InterfaceC0252k;
            ViewGroup viewGroup = this.c;
            if (z6) {
                ((InterfaceC0252k) viewParentE).b(viewGroup, i7);
            } else if (i7 == 0) {
                try {
                    viewParentE.onStopNestedScroll(viewGroup);
                } catch (AbstractMethodError e10) {
                    h6.n.i("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onStopNestedScroll", e10);
                }
            }
            if (i7 == 0) {
                this.f2339a = null;
            } else {
                if (i7 != 1) {
                    return;
                }
                this.f2340b = null;
            }
        }
    }
}
