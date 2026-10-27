package defpackage;

import android.widget.AdapterView;

/* JADX INFO: renamed from: qr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1403qr implements AdapterView.OnItemSelectedListener {
    public C0124Gq a;
    public final /* synthetic */ C1567tr b;

    public C1403qr(C1567tr c1567tr, C0124Gq c0124Gq) {
        this.b = c1567tr;
        this.a = c0124Gq;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    @Override // android.widget.AdapterView.OnItemSelectedListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onItemSelected(android.widget.AdapterView r6, android.view.View r7, int r8, long r9) {
        /*
            r5 = this;
            Gq r7 = r5.a
            java.lang.String r7 = r7.a
            tr r9 = r5.b
            java.util.Map r9 = r9.p0
            java.lang.Object r9 = r9.get(r7)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            r10 = 0
            r0 = 2
            r1 = 1
            if (r9 != 0) goto L15
            r9 = 0
            goto L1e
        L15:
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L1d
            r9 = 1
            goto L1e
        L1d:
            r9 = 2
        L1e:
            java.lang.String[] r2 = defpackage.C1567tr.s0
            r2 = r2[r8]
            int r3 = r2.hashCode()
            r4 = -1085510111(0xffffffffbf4c6e21, float:-0.79855543)
            if (r3 == r4) goto L4a
            r10 = 55059233(0x3482321, float:5.8815044E-37)
            if (r3 == r10) goto L40
            r10 = 335584924(0x14009e9c, float:6.493629E-27)
            if (r3 == r10) goto L36
            goto L53
        L36:
            java.lang.String r10 = "Disabled"
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L53
            r10 = 2
            goto L54
        L40:
            java.lang.String r10 = "Enabled"
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L53
            r10 = 1
            goto L54
        L4a:
            java.lang.String r3 = "Default"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L53
            goto L54
        L53:
            r10 = -1
        L54:
            if (r10 == 0) goto L6f
            if (r10 == r1) goto L65
            if (r10 == r0) goto L5b
            goto L76
        L5b:
            tr r10 = r5.b
            java.util.Map r10 = r10.p0
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r10.put(r7, r0)
            goto L76
        L65:
            tr r10 = r5.b
            java.util.Map r10 = r10.p0
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r10.put(r7, r0)
            goto L76
        L6f:
            tr r10 = r5.b
            java.util.Map r10 = r10.p0
            r10.remove(r7)
        L76:
            if (r9 == r8) goto L8c
            tr r7 = r5.b
            r7.h0()
            android.view.ViewParent r6 = r6.getParent()
            boolean r7 = r6 instanceof android.view.View
            if (r7 == 0) goto L8c
            tr r7 = r5.b
            android.view.View r6 = (android.view.View) r6
            defpackage.C1567tr.g0(r7, r6, r8)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1403qr.onItemSelected(android.widget.AdapterView, android.view.View, int, long):void");
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
