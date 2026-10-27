package F0;

import android.view.View;
import android.view.translation.ViewTranslationCallback;
import androidx.compose.ui.platform.AndroidComposeView;
import i0.ViewOnAttachStateChangeListenerC1400d;
import java.util.LinkedHashMap;
import u.C2476p;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class O implements ViewTranslationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final O f2098a = new O();

    /* JADX WARN: Removed duplicated region for block: B:26:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onClearTranslation(android.view.View r14) {
        /*
            r13 = this;
            java.lang.String r13 = "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView"
            A7.m.d(r14, r13)
            androidx.compose.ui.platform.AndroidComposeView r14 = (androidx.compose.ui.platform.AndroidComposeView) r14
            i0.d r13 = r14.getContentCaptureManager()
            r14 = 1
            r13.f15657r = r14
            u.p r13 = r13.c()
            java.lang.Object[] r0 = r13.c
            long[] r13 = r13.f22615a
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L80
            r2 = 0
            r3 = r2
        L1d:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L7b
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L37:
            if (r8 >= r6) goto L79
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.3E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L75
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            F0.Q0 r9 = (F0.Q0) r9
            L0.o r9 = r9.f2107a
            L0.j r9 = r9.d
            L0.u r10 = L0.r.f3959w
            java.util.LinkedHashMap r9 = r9.l
            java.lang.Object r10 = r9.get(r10)
            r11 = 0
            if (r10 != 0) goto L59
            r10 = r11
        L59:
            if (r10 == 0) goto L75
            L0.u r10 = L0.i.l
            java.lang.Object r9 = r9.get(r10)
            if (r9 != 0) goto L64
            goto L65
        L64:
            r11 = r9
        L65:
            L0.a r11 = (L0.a) r11
            if (r11 == 0) goto L75
            m7.c r9 = r11.f3876b
            z7.a r9 = (z7.InterfaceC2772a) r9
            if (r9 == 0) goto L75
            java.lang.Object r9 = r9.a()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
        L75:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L37
        L79:
            if (r6 != r7) goto L80
        L7b:
            if (r3 == r1) goto L80
            int r3 = r3 + 1
            goto L1d
        L80:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.O.onClearTranslation(android.view.View):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onHideTranslation(android.view.View r14) {
        /*
            r13 = this;
            java.lang.String r13 = "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView"
            A7.m.d(r14, r13)
            androidx.compose.ui.platform.AndroidComposeView r14 = (androidx.compose.ui.platform.AndroidComposeView) r14
            i0.d r13 = r14.getContentCaptureManager()
            r14 = 1
            r13.f15657r = r14
            u.p r13 = r13.c()
            java.lang.Object[] r0 = r13.c
            long[] r13 = r13.f22615a
            int r1 = r13.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L88
            r2 = 0
            r3 = r2
        L1d:
            r4 = r13[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L83
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L37:
            if (r8 >= r6) goto L81
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.3E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L7d
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            F0.Q0 r9 = (F0.Q0) r9
            L0.o r9 = r9.f2107a
            L0.j r9 = r9.d
            L0.u r10 = L0.r.f3959w
            java.util.LinkedHashMap r9 = r9.l
            java.lang.Object r10 = r9.get(r10)
            r11 = 0
            if (r10 != 0) goto L59
            r10 = r11
        L59:
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
            boolean r10 = A7.m.a(r10, r12)
            if (r10 == 0) goto L7d
            L0.u r10 = L0.i.f3895k
            java.lang.Object r9 = r9.get(r10)
            if (r9 != 0) goto L6a
            goto L6b
        L6a:
            r11 = r9
        L6b:
            L0.a r11 = (L0.a) r11
            if (r11 == 0) goto L7d
            m7.c r9 = r11.f3876b
            z7.k r9 = (z7.InterfaceC2782k) r9
            if (r9 == 0) goto L7d
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            java.lang.Object r9 = r9.b(r10)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
        L7d:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L37
        L81:
            if (r6 != r7) goto L88
        L83:
            if (r3 == r1) goto L88
            int r3 = r3 + 1
            goto L1d
        L88:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.O.onHideTranslation(android.view.View):boolean");
    }

    public final boolean onShowTranslation(View view) {
        InterfaceC2782k interfaceC2782k;
        A7.m.d(view, "null cannot be cast to non-null type androidx.compose.ui.platform.AndroidComposeView");
        ViewOnAttachStateChangeListenerC1400d contentCaptureManager = ((AndroidComposeView) view).getContentCaptureManager();
        contentCaptureManager.f15657r = 2;
        C2476p c2476pC = contentCaptureManager.c();
        Object[] objArr = c2476pC.c;
        long[] jArr = c2476pC.f22615a;
        int length = jArr.length - 2;
        if (length < 0) {
            return true;
        }
        int i7 = 0;
        while (true) {
            long j3 = jArr[i7];
            if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i10 = 8 - ((~(i7 - length)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((255 & j3) < 128) {
                        L0.j jVar = ((Q0) objArr[(i7 << 3) + i11]).f2107a.d;
                        L0.u uVar = L0.r.f3959w;
                        LinkedHashMap linkedHashMap = jVar.l;
                        Object obj = linkedHashMap.get(uVar);
                        if (obj == null) {
                            obj = null;
                        }
                        if (A7.m.a(obj, Boolean.FALSE)) {
                            Object obj2 = linkedHashMap.get(L0.i.f3895k);
                            L0.a aVar = (L0.a) (obj2 != null ? obj2 : null);
                            if (aVar != null && (interfaceC2782k = (InterfaceC2782k) aVar.f3876b) != null) {
                            }
                        }
                    }
                    j3 >>= 8;
                }
                if (i10 != 8) {
                    return true;
                }
            }
            if (i7 == length) {
                return true;
            }
            i7++;
        }
    }
}
