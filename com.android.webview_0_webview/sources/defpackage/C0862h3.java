package defpackage;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: h3, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0862h3 {
    public ViewParent a;
    public ViewParent b;
    public final View c;
    public boolean d;
    public int[] e;

    public C0862h3(View view) {
        this.c = view;
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent viewParentE;
        if (!this.d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedFling(this.c, f, f2, z);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedFling", e);
            return false;
        }
    }

    public boolean b(float f, float f2) {
        ViewParent viewParentE;
        if (!this.d || (viewParentE = e(0)) == null) {
            return false;
        }
        try {
            return viewParentE.onNestedPreFling(this.c, f, f2);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreFling", e);
            return false;
        }
    }

    public boolean c(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent viewParentE;
        int i4;
        int i5;
        int[] iArr3;
        if (!this.d || (viewParentE = e(i3)) == null) {
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
            this.c.getLocationInWindow(iArr2);
            i4 = iArr2[0];
            i5 = iArr2[1];
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (iArr == null) {
            if (this.e == null) {
                this.e = new int[2];
            }
            iArr3 = this.e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        View view = this.c;
        if (viewParentE instanceof InterfaceC0918i3) {
            ((InterfaceC0918i3) viewParentE).c(view, i, i2, iArr3, i3);
        } else if (i3 == 0) {
            try {
                viewParentE.onNestedPreScroll(view, i, i2, iArr3);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedPreScroll", e);
            }
        }
        if (iArr2 != null) {
            this.c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i4;
            iArr2[1] = iArr2[1] - i5;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    public boolean d(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        ViewParent viewParentE;
        int i6;
        int i7;
        if (!this.d || (viewParentE = e(i5)) == null) {
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
            this.c.getLocationInWindow(iArr);
            i6 = iArr[0];
            i7 = iArr[1];
        } else {
            i6 = 0;
            i7 = 0;
        }
        View view = this.c;
        if (viewParentE instanceof InterfaceC0918i3) {
            ((InterfaceC0918i3) viewParentE).d(view, i, i2, i3, i4, i5);
        } else if (i5 == 0) {
            try {
                viewParentE.onNestedScroll(view, i, i2, i3, i4);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onNestedScroll", e);
            }
        }
        if (iArr != null) {
            this.c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i6;
            iArr[1] = iArr[1] - i7;
        }
        return true;
    }

    public final ViewParent e(int i) {
        if (i == 0) {
            return this.a;
        }
        if (i != 1) {
            return null;
        }
        return this.b;
    }

    public boolean f(int i) {
        return e(i) != null;
    }

    public boolean g(int i, int i2) {
        boolean zOnStartNestedScroll;
        if (e(i2) != null) {
            return true;
        }
        if (this.d) {
            View view = this.c;
            for (ViewParent parent = this.c.getParent(); parent != null; parent = parent.getParent()) {
                View view2 = this.c;
                boolean z = parent instanceof InterfaceC0918i3;
                if (z) {
                    zOnStartNestedScroll = ((InterfaceC0918i3) parent).e(view, view2, i, i2);
                } else if (i2 == 0) {
                    try {
                        zOnStartNestedScroll = parent.onStartNestedScroll(view, view2, i);
                    } catch (AbstractMethodError e) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e);
                        zOnStartNestedScroll = false;
                    }
                } else {
                    zOnStartNestedScroll = false;
                }
                if (zOnStartNestedScroll) {
                    if (i2 == 0) {
                        this.a = parent;
                    } else if (i2 == 1) {
                        this.b = parent;
                    }
                    View view3 = this.c;
                    if (z) {
                        ((InterfaceC0918i3) parent).a(view, view3, i, i2);
                    } else if (i2 == 0) {
                        try {
                            parent.onNestedScrollAccepted(view, view3, i);
                        } catch (AbstractMethodError e2) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e2);
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

    public void h(int i) {
        ViewParent viewParentE = e(i);
        if (viewParentE != null) {
            View view = this.c;
            if (viewParentE instanceof InterfaceC0918i3) {
                ((InterfaceC0918i3) viewParentE).b(view, i);
            } else if (i == 0) {
                try {
                    viewParentE.onStopNestedScroll(view);
                } catch (AbstractMethodError e) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentE + " does not implement interface method onStopNestedScroll", e);
                }
            }
            if (i == 0) {
                this.a = null;
            } else {
                if (i != 1) {
                    return;
                }
                this.b = null;
            }
        }
    }
}
