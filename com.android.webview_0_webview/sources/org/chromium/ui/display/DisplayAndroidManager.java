package org.chromium.ui.display;

import J.N;
import android.hardware.display.DisplayManager;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import defpackage.AbstractC0827gO;
import defpackage.C0939iO;
import defpackage.C0994jO;
import defpackage.Is;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DisplayAndroidManager {
    public static DisplayAndroidManager e;
    public long a;
    public int b;
    public final SparseArray c = new SparseArray();
    public C0939iO d = new C0939iO(this, null);

    public static DisplayAndroidManager a() {
        ThreadUtils.b();
        if (e == null) {
            DisplayAndroidManager displayAndroidManager = new DisplayAndroidManager();
            e = displayAndroidManager;
            Objects.requireNonNull(displayAndroidManager);
            Display display = ((DisplayManager) Is.a.getSystemService("display")).getDisplay(0);
            if (display == null) {
                display = ((WindowManager) Is.a.getSystemService("window")).getDefaultDisplay();
            }
            displayAndroidManager.b = display.getDisplayId();
            int displayId = display.getDisplayId();
            C0994jO c0994jO = new C0994jO(display);
            displayAndroidManager.c.put(displayId, c0994jO);
            c0994jO.f(display);
            C0939iO c0939iO = displayAndroidManager.d;
            Objects.requireNonNull(c0939iO);
            ((DisplayManager) Is.a.getSystemService("display")).registerDisplayListener(c0939iO, null);
        }
        return e;
    }

    public static void onNativeSideCreated(long j) {
        DisplayAndroidManager displayAndroidManagerA = a();
        displayAndroidManagerA.a = j;
        N.MdOwtyr6(j, displayAndroidManagerA, displayAndroidManagerA.b);
        for (int i = 0; i < displayAndroidManagerA.c.size(); i++) {
            displayAndroidManagerA.b((AbstractC0827gO) displayAndroidManagerA.c.valueAt(i));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(defpackage.AbstractC0827gO r16) {
        /*
            r15 = this;
            r11 = r15
            r0 = r16
            long r1 = r11.a
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto Lc
            return
        Lc:
            long r1 = r11.a
            int r3 = r0.b
            android.graphics.Point r4 = r0.c
            int r5 = r4.x
            int r6 = r4.y
            float r7 = r0.d
            int r4 = r0.g
            r8 = 1
            r9 = 0
            if (r4 == 0) goto L36
            if (r4 == r8) goto L31
            r10 = 2
            if (r4 == r10) goto L2c
            r10 = 3
            if (r4 == r10) goto L27
            goto L36
        L27:
            r4 = 270(0x10e, float:3.78E-43)
            r10 = 270(0x10e, float:3.78E-43)
            goto L37
        L2c:
            r4 = 180(0xb4, float:2.52E-43)
            r10 = 180(0xb4, float:2.52E-43)
            goto L37
        L31:
            r4 = 90
            r10 = 90
            goto L37
        L36:
            r10 = 0
        L37:
            int r12 = r0.e
            int r13 = r0.f
            boolean r4 = r0.k
            if (r4 == 0) goto L45
            boolean r0 = r0.l
            if (r0 == 0) goto L45
            r14 = 1
            goto L46
        L45:
            r14 = 0
        L46:
            r0 = r1
            r2 = r15
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r10
            r8 = r12
            r9 = r13
            r10 = r14
            J.N.M2$ANfTC(r0, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.display.DisplayAndroidManager.b(gO):void");
    }
}
