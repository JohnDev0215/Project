package p;

import android.animation.ObjectAnimator;
import android.view.View;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class xl0 extends wn2 {
    public static final String[] G = {"android:visibility:visibility", "android:visibility:parent"};
    public int F;

    public xl0(int i) {
        this.F = 3;
        if ((i & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.F = i;
    }

    public static void N(do2 do2Var) {
        int visibility = do2Var.b.getVisibility();
        HashMap map = do2Var.a;
        map.put("android:visibility:visibility", Integer.valueOf(visibility));
        View view = do2Var.b;
        map.put("android:visibility:parent", view.getParent());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        map.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.nv2 P(p.do2 r8, p.do2 r9) {
        /*
            p.nv2 r0 = new p.nv2
            r0.<init>()
            r1 = 0
            r0.a = r1
            r0.b = r1
            r2 = 0
            r3 = -1
            java.lang.String r4 = "android:visibility:parent"
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L2f
            java.util.HashMap r6 = r8.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L2f
            java.lang.Object r7 = r6.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r0.c = r7
            java.lang.Object r6 = r6.get(r4)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.e = r6
            goto L33
        L2f:
            r0.c = r3
            r0.e = r2
        L33:
            if (r9 == 0) goto L52
            java.util.HashMap r6 = r9.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L52
            java.lang.Object r2 = r6.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.d = r2
            java.lang.Object r2 = r6.get(r4)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f = r2
            goto L56
        L52:
            r0.d = r3
            r0.f = r2
        L56:
            r2 = 1
            if (r8 == 0) goto L8a
            if (r9 == 0) goto L8a
            int r8 = r0.c
            int r9 = r0.d
            if (r8 != r9) goto L68
            android.view.ViewGroup r3 = r0.e
            android.view.ViewGroup r4 = r0.f
            if (r3 != r4) goto L68
            goto L9f
        L68:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L71
            r0.b = r1
            r0.a = r2
            goto L9f
        L71:
            if (r9 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            goto L9f
        L78:
            android.view.ViewGroup r8 = r0.f
            if (r8 != 0) goto L81
            r0.b = r1
            r0.a = r2
            goto L9f
        L81:
            android.view.ViewGroup r8 = r0.e
            if (r8 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            goto L9f
        L8a:
            if (r8 != 0) goto L95
            int r8 = r0.d
            if (r8 != 0) goto L95
            r0.b = r2
            r0.a = r2
            goto L9f
        L95:
            if (r9 != 0) goto L9f
            int r8 = r0.c
            if (r8 != 0) goto L9f
            r0.b = r1
            r0.a = r2
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.xl0.P(p.do2, p.do2):p.nv2");
    }

    public final ObjectAnimator O(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        dv2.a.M(view, f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, dv2.b, f2);
        objectAnimatorOfFloat.addListener(new em0(view));
        a(new wl0(this, 0, view));
        return objectAnimatorOfFloat;
    }

    @Override // p.wn2
    public final void f(do2 do2Var) {
        N(do2Var);
    }

    @Override // p.wn2
    public final void j(do2 do2Var) {
        N(do2Var);
        do2Var.a.put("android:fade:transitionAlpha", Float.valueOf(dv2.a.L(do2Var.b)));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x022a  */
    @Override // p.wn2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator n(android.view.ViewGroup r23, p.do2 r24, p.do2 r25) {
        /*
            Method dump skipped, instruction units count: 752
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.xl0.n(android.view.ViewGroup, p.do2, p.do2):android.animation.Animator");
    }

    @Override // p.wn2
    public final /* bridge */ /* synthetic */ String[] t() {
        return G;
    }

    @Override // p.wn2
    public final boolean v(do2 do2Var, do2 do2Var2) {
        if (do2Var == null && do2Var2 == null) {
            return false;
        }
        if (do2Var != null && do2Var2 != null && do2Var2.a.containsKey("android:visibility:visibility") != do2Var.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        nv2 nv2VarP = P(do2Var, do2Var2);
        if (nv2VarP.a) {
            return nv2VarP.c == 0 || nv2VarP.d == 0;
        }
        return false;
    }
}
