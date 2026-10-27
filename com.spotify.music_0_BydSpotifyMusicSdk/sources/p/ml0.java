package p;

import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class ml0 extends oh1 {
    public final /* synthetic */ nl0 k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ml0(nl0 nl0Var) {
        super(7);
        this.k = nl0Var;
    }

    @Override // p.oh1
    public final c3 t(int i) {
        return new c3(AccessibilityNodeInfo.obtain(this.k.n(i).a));
    }

    @Override // p.oh1
    public final c3 u(int i) {
        nl0 nl0Var = this.k;
        int i2 = i == 2 ? nl0Var.k : nl0Var.l;
        if (i2 == Integer.MIN_VALUE) {
            return null;
        }
        return t(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    @Override // p.oh1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w(int r7, int r8, android.os.Bundle r9) {
        /*
            r6 = this;
            p.nl0 r0 = r6.k
            android.view.View r1 = r0.i
            r2 = -1
            if (r7 == r2) goto L86
            r9 = 1
            if (r8 == r9) goto L81
            r2 = 2
            if (r8 == r2) goto L7c
            r2 = 64
            r3 = 65536(0x10000, float:9.1835E-41)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 0
            if (r8 == r2) goto L51
            r2 = 128(0x80, float:1.8E-43)
            if (r8 == r2) goto L41
            p.ms r0 = (p.ms) r0
            r1 = 16
            if (r8 != r1) goto L8c
            com.google.android.material.chip.Chip r8 = r0.q
            if (r7 != 0) goto L2a
            boolean r5 = r8.performClick()
            goto L8c
        L2a:
            if (r7 != r9) goto L8c
            r8.playSoundEffect(r5)
            android.view.View$OnClickListener r7 = r8.f32p
            if (r7 == 0) goto L37
            r7.onClick(r8)
            r5 = r9
        L37:
            boolean r7 = r8.z
            if (r7 == 0) goto L8c
            p.ms r7 = r8.y
            r7.q(r9, r9)
            goto L8c
        L41:
            int r8 = r0.k
            if (r8 != r7) goto L4e
            r0.k = r4
            r1.invalidate()
            r0.q(r7, r3)
            goto L4f
        L4e:
            r9 = r5
        L4f:
            r5 = r9
            goto L8c
        L51:
            android.view.accessibility.AccessibilityManager r8 = r0.h
            boolean r2 = r8.isEnabled()
            if (r2 == 0) goto L4e
            boolean r8 = r8.isTouchExplorationEnabled()
            if (r8 != 0) goto L60
            goto L4e
        L60:
            int r8 = r0.k
            if (r8 == r7) goto L4e
            if (r8 == r4) goto L70
            r0.k = r4
            android.view.View r2 = r0.i
            r2.invalidate()
            r0.q(r8, r3)
        L70:
            r0.k = r7
            r1.invalidate()
            r8 = 32768(0x8000, float:4.5918E-41)
            r0.q(r7, r8)
            goto L4f
        L7c:
            boolean r5 = r0.j(r7)
            goto L8c
        L81:
            boolean r5 = r0.p(r7)
            goto L8c
        L86:
            java.util.WeakHashMap r7 = p.zt2.a
            boolean r5 = p.it2.j(r1, r8, r9)
        L8c:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ml0.w(int, int, android.os.Bundle):boolean");
    }
}
