package a.h.k;

import a.b.k.l;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f814a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f815b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f816c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f817d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f818e;

    public g(View view) {
        this.f816c = view;
    }

    public boolean a(float f2, float f3, boolean z) {
        ViewParent viewParentG;
        if (!this.f817d || (viewParentG = g(0)) == null) {
            return false;
        }
        return l.i.a0(viewParentG, this.f816c, f2, f3, z);
    }

    public boolean b(float f2, float f3) {
        ViewParent viewParentG;
        if (!this.f817d || (viewParentG = g(0)) == null) {
            return false;
        }
        return l.i.b0(viewParentG, this.f816c, f2, f3);
    }

    public boolean c(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent viewParentG;
        int i4;
        int i5;
        if (!this.f817d || (viewParentG = g(i3)) == null) {
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
            this.f816c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            if (this.f818e == null) {
                this.f818e = new int[2];
            }
            iArr = this.f818e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        l.i.c0(viewParentG, this.f816c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f816c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void d(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        f(i, i2, i3, i4, null, i5, iArr2);
    }

    public boolean e(int i, int i2, int i3, int i4, int[] iArr) {
        return f(i, i2, i3, i4, iArr, 0, null);
    }

    public final boolean f(int i, int i2, int i3, int i4, int[] iArr, int i5, int[] iArr2) {
        ViewParent viewParentG;
        int i6;
        int i7;
        int[] iArr3;
        if (!this.f817d || (viewParentG = g(i5)) == null) {
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
            this.f816c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        if (iArr2 == null) {
            if (this.f818e == null) {
                this.f818e = new int[2];
            }
            int[] iArr4 = this.f818e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        View view = this.f816c;
        if (viewParentG instanceof i) {
            ((i) viewParentG).k(view, i, i2, i3, i4, i5, iArr3);
        } else {
            iArr3[0] = iArr3[0] + i3;
            iArr3[1] = iArr3[1] + i4;
            if (viewParentG instanceof h) {
                ((h) viewParentG).l(view, i, i2, i3, i4, i5);
            } else if (i5 == 0) {
                try {
                    viewParentG.onNestedScroll(view, i, i2, i3, i4);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentG + " does not implement interface method onNestedScroll", e2);
                }
            }
        }
        if (iArr != null) {
            this.f816c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public final ViewParent g(int i) {
        if (i == 0) {
            return this.f814a;
        }
        if (i != 1) {
            return null;
        }
        return this.f815b;
    }

    public boolean h(int i) {
        return g(i) != null;
    }

    public boolean i(int i, int i2) {
        boolean zOnStartNestedScroll;
        if (g(i2) != null) {
            return true;
        }
        if (this.f817d) {
            View view = this.f816c;
            for (ViewParent parent = this.f816c.getParent(); parent != null; parent = parent.getParent()) {
                View view2 = this.f816c;
                boolean z = parent instanceof h;
                if (z) {
                    zOnStartNestedScroll = ((h) parent).o(view, view2, i, i2);
                } else if (i2 == 0) {
                    try {
                        zOnStartNestedScroll = parent.onStartNestedScroll(view, view2, i);
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e2);
                        zOnStartNestedScroll = false;
                    }
                } else {
                    zOnStartNestedScroll = false;
                }
                if (zOnStartNestedScroll) {
                    if (i2 == 0) {
                        this.f814a = parent;
                    } else if (i2 == 1) {
                        this.f815b = parent;
                    }
                    View view3 = this.f816c;
                    if (z) {
                        ((h) parent).i(view, view3, i, i2);
                    } else if (i2 == 0) {
                        try {
                            parent.onNestedScrollAccepted(view, view3, i);
                        } catch (AbstractMethodError e3) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e3);
                        }
                    }
                    return true;
                }
                if (parent instanceof View) {
                    view = (View) parent;
                }
            }
        }
        return false;
    }

    public void j(int i) {
        ViewParent viewParentG = g(i);
        if (viewParentG != null) {
            View view = this.f816c;
            if (viewParentG instanceof h) {
                ((h) viewParentG).m(view, i);
            } else if (i == 0) {
                try {
                    viewParentG.onStopNestedScroll(view);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentG + " does not implement interface method onStopNestedScroll", e2);
                }
            }
            if (i == 0) {
                this.f814a = null;
            } else {
                if (i != 1) {
                    return;
                }
                this.f815b = null;
            }
        }
    }
}
