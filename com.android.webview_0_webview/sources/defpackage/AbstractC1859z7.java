package defpackage;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: z7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1859z7 extends Z6 {
    public static final String[] b0 = {"android:visibility:visibility", "android:visibility:parent"};
    public int a0 = 3;

    public final void H(C0977j7 c0977j7) {
        c0977j7.a.put("android:visibility:visibility", Integer.valueOf(c0977j7.b.getVisibility()));
        c0977j7.a.put("android:visibility:parent", c0977j7.b.getParent());
        int[] iArr = new int[2];
        c0977j7.b.getLocationOnScreen(iArr);
        c0977j7.a.put("android:visibility:screenLocation", iArr);
    }

    public final C1804y7 I(C0977j7 c0977j7, C0977j7 c0977j72) {
        C1804y7 c1804y7 = new C1804y7();
        c1804y7.a = false;
        c1804y7.b = false;
        if (c0977j7 == null || !c0977j7.a.containsKey("android:visibility:visibility")) {
            c1804y7.c = -1;
            c1804y7.e = null;
        } else {
            c1804y7.c = ((Integer) c0977j7.a.get("android:visibility:visibility")).intValue();
            c1804y7.e = (ViewGroup) c0977j7.a.get("android:visibility:parent");
        }
        if (c0977j72 == null || !c0977j72.a.containsKey("android:visibility:visibility")) {
            c1804y7.d = -1;
            c1804y7.f = null;
        } else {
            c1804y7.d = ((Integer) c0977j72.a.get("android:visibility:visibility")).intValue();
            c1804y7.f = (ViewGroup) c0977j72.a.get("android:visibility:parent");
        }
        if (c0977j7 == null || c0977j72 == null) {
            if (c0977j7 == null && c1804y7.d == 0) {
                c1804y7.b = true;
                c1804y7.a = true;
            } else if (c0977j72 == null && c1804y7.c == 0) {
                c1804y7.b = false;
                c1804y7.a = true;
            }
        } else {
            if (c1804y7.c == c1804y7.d && c1804y7.e == c1804y7.f) {
                return c1804y7;
            }
            int i = c1804y7.c;
            int i2 = c1804y7.d;
            if (i != i2) {
                if (i == 0) {
                    c1804y7.b = false;
                    c1804y7.a = true;
                } else if (i2 == 0) {
                    c1804y7.b = true;
                    c1804y7.a = true;
                }
            } else if (c1804y7.f == null) {
                c1804y7.b = false;
                c1804y7.a = true;
            } else if (c1804y7.e == null) {
                c1804y7.b = true;
                c1804y7.a = true;
            }
        }
        return c1804y7;
    }

    public abstract Animator J(ViewGroup viewGroup, View view, C0977j7 c0977j7, C0977j7 c0977j72);

    @Override // defpackage.Z6
    public void d(C0977j7 c0977j7) {
        H(c0977j7);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x00da A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0126  */
    @Override // defpackage.Z6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.Animator k(android.view.ViewGroup r10, defpackage.C0977j7 r11, defpackage.C0977j7 r12) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1859z7.k(android.view.ViewGroup, j7, j7):android.animation.Animator");
    }

    @Override // defpackage.Z6
    public String[] p() {
        return b0;
    }

    @Override // defpackage.Z6
    public boolean r(C0977j7 c0977j7, C0977j7 c0977j72) {
        if (c0977j7 == null && c0977j72 == null) {
            return false;
        }
        if (c0977j7 != null && c0977j72 != null && c0977j72.a.containsKey("android:visibility:visibility") != c0977j7.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        C1804y7 c1804y7I = I(c0977j7, c0977j72);
        if (c1804y7I.a) {
            return c1804y7I.c == 0 || c1804y7I.d == 0;
        }
        return false;
    }
}
