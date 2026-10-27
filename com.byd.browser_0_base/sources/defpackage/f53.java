package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabUma.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f53 extends nh0 implements lk3 {
    public static long i;
    public int e;
    public long f = -1;
    public long g = -1;
    public int h = 0;

    public f53(int i2, TabImpl tabImpl) {
        System.currentTimeMillis();
        this.e = i2;
        if (i2 == 0) {
            y1(1);
        } else if (i2 == 1 || i2 == 2 || i2 == 3) {
            y1(2);
        }
        tabImpl.x(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        y1(4);
        tab.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void H0() {
        if (this.h == 1) {
            this.e = 0;
        } else {
            this.e = 1;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        this.g = -1L;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i2, Tab tab) {
        long j = this.g;
        if (j != -1 && this.f >= j && i2 == 0) {
            oj2.R((int) (-1), "Tab.PerceivedRestoreTime");
        }
        this.g = -1L;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i2, Tab tab) {
        if (i2 != 1) {
            y1(2);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void U0() {
        this.g = SystemClock.elapsedRealtime();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        long j = this.g;
        if (j != -1 && this.f >= j) {
            oj2.R((int) (SystemClock.elapsedRealtime() - this.f), "Tab.PerceivedRestoreTime");
        }
        this.g = -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0057  */
    @Override // defpackage.nh0, defpackage.n23
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l0(int r14, org.chromium.chrome.browser.tab.Tab r15) {
        /*
            r13 = this;
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r15 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r15)
            long r0 = r15.r
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r4 = r13.f
            r6 = -1
            int r15 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r8 = 3
            if (r15 == 0) goto L1d
            if (r14 != r8) goto L1d
            long r4 = r2 - r4
            java.lang.String r15 = "Tab.SwitchedToForegroundAge"
            int r4 = (int) r4
            defpackage.oj2.R(r4, r15)
        L1d:
            long r4 = defpackage.f53.i
            r9 = 1
            long r4 = r4 + r9
            defpackage.f53.i = r4
            int r15 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            r4 = 0
            r5 = 1
            if (r15 != 0) goto L2c
            r15 = r5
            goto L2d
        L2c:
            r15 = r4
        L2d:
            int r9 = r13.e
            if (r9 != r8) goto L39
            long r10 = r13.f
            int r10 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r10 != 0) goto L39
            r10 = r5
            goto L3a
        L39:
            r10 = r4
        L3a:
            long r11 = r13.g
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 != 0) goto L43
            if (r10 != 0) goto L43
            goto L58
        L43:
            long r10 = r13.f
            int r4 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r4 != 0) goto L57
            if (r15 == 0) goto L4d
            r4 = 6
            goto L58
        L4d:
            r4 = 2
            if (r9 != r4) goto L52
            r4 = 7
            goto L58
        L52:
            if (r9 != r8) goto L57
            r4 = 8
            goto L58
        L57:
            r4 = r5
        L58:
            if (r14 != r8) goto L61
            r9 = 9
            java.lang.String r10 = "Tab.StatusWhenSwitchedBackToForeground"
            defpackage.oj2.U(r4, r9, r10)
        L61:
            long r9 = r13.f
            int r4 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r4 != 0) goto L88
            r6 = 0
            int r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r4 <= 0) goto L88
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r0
            r0 = 60000(0xea60, double:2.9644E-319)
            if (r15 == 0) goto L7f
            long r6 = r6 / r0
            int r14 = (int) r6
            java.lang.String r15 = "Tabs.ForegroundTabAgeAtStartup"
            defpackage.oj2.R(r14, r15)
            goto L88
        L7f:
            if (r14 != r8) goto L88
            long r6 = r6 / r0
            int r14 = (int) r6
            java.lang.String r15 = "Tab.AgeUponRestoreFromColdStart"
            defpackage.oj2.R(r14, r15)
        L88:
            r13.f = r2
            r13.y1(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f53.l0(int, org.chromium.chrome.browser.tab.Tab):void");
    }

    public final void y1(int i2) {
        if (this.h == i2) {
            return;
        }
        System.currentTimeMillis();
        this.h = i2;
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
    }
}
