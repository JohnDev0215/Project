package defpackage;

import J.N;
import android.content.Context;
import android.graphics.Rect;
import android.os.SystemClock;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.concurrent.Callable;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.customtabs.features.TabInteractionRecorder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: CustomTabObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class b40 extends nh0 {
    public final CustomTabsConnection e;
    public final CustomTabsSessionToken f;
    public final boolean g;
    public final bl1 h = new bl1(new wb(17, this));
    public final int i;
    public final int j;
    public long k;
    public long l;
    public long m;
    public int n;

    public b40(Context context, oj ojVar, CustomTabsConnection customTabsConnection) {
        boolean zN = ojVar.N();
        this.g = zN;
        customTabsConnection = zN ? null : customTabsConnection;
        this.e = customTabsConnection;
        CustomTabsSessionToken customTabsSessionTokenZ = ojVar.z();
        this.f = customTabsSessionTokenZ;
        if (!zN && customTabsConnection.t(customTabsSessionTokenZ)) {
            float dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.custom_tabs_screenshot_width);
            float dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.custom_tabs_screenshot_height);
            Rect rectA = TabUtils.a(context);
            if (rectA.width() == 0 || rectA.height() == 0) {
                this.i = Math.round(dimensionPixelSize);
                this.j = Math.round(dimensionPixelSize2);
            } else {
                float fMin = Math.min(dimensionPixelSize / rectA.width(), dimensionPixelSize2 / rectA.height());
                this.i = Math.round(rectA.width() * fMin);
                this.j = Math.round(rectA.height() * fMin);
            }
        }
        this.n = 0;
        this.k = -1L;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void F0(Tab tab) {
        TabInteractionRecorder tabInteractionRecorder = (TabInteractionRecorder) N.MJqYRCCN(tab);
        if (tabInteractionRecorder != null) {
            tabInteractionRecorder.a();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void J0(int i, Tab tab) {
        this.n = 0;
        this.k = -1L;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        this.h.a(tab, new al1(), 1000L);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.n == 2) {
            long j = this.k;
            if (j > 0) {
                String str = this.g ? "ChromeGeneratedCustomTab" : "CustomTabs";
                long j2 = jElapsedRealtime - j;
                long j3 = this.l;
                if (j3 > 0) {
                    long j4 = j3 - j;
                    oj2.T(str.concat(".IntentToFirstNavigationStartTime.ZoomedOut"), j4, 50L, 600000L, 50);
                    oj2.T(str.concat(".IntentToFirstNavigationStartTime.ZoomedIn"), j4, 200L, 1000L, 100);
                }
                oj2.T(str.concat(".IntentToPageLoadedTime"), j2, 10L, 600000L, 100);
                if (this.l != 0) {
                    long j5 = this.m - this.k;
                    oj2.T("CustomTabs.IntentToFirstCommitNavigationTime3.ZoomedIn", j5, 200L, 1000L, 100);
                    oj2.T("CustomTabs.IntentToFirstCommitNavigationTime3.ZoomedOut", j5, 50L, 600000L, 50);
                }
            }
        }
        this.n = 0;
        this.k = -1L;
        final bl1 bl1Var = this.h;
        bl1Var.d = true;
        bl1Var.a(tab, new Callable() { // from class: zk1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Boolean.valueOf(bl1Var.e);
            }
        }, 1000L);
        bl1Var.a(tab, new al1(), 15000L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0093 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0095 A[Catch: all -> 0x00ba, TRY_ENTER, TryCatch #2 {, blocks: (B:6:0x000f, B:10:0x001d, B:21:0x0030, B:23:0x0040, B:25:0x0061, B:43:0x008c, B:44:0x008d, B:48:0x0095, B:52:0x009b, B:56:0x00a1, B:60:0x00b5, B:61:0x00b6, B:14:0x0023, B:20:0x002f, B:63:0x00b8, B:64:0x00b9, B:26:0x0062, B:31:0x0071, B:35:0x007e, B:7:0x0010, B:12:0x001f, B:16:0x0026), top: B:73:0x000f, inners: #0, #1 }] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    @Override // defpackage.nh0, defpackage.n23
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void g0(int r21, org.chromium.chrome.browser.tab.Tab r22, org.chromium.content_public.browser.LoadUrlParams r23) {
        /*
            r20 = this;
            r0 = r20
            org.chromium.chrome.browser.customtabs.CustomTabsConnection r1 = r0.e
            if (r1 == 0) goto Lbd
            androidx.browser.customtabs.CustomTabsSessionToken r0 = r0.f
            r2 = r23
            java.lang.String r2 = r2.a
            org.chromium.chrome.browser.customtabs.a r1 = r1.c
            monitor-enter(r1)
            monitor-enter(r1)     // Catch: java.lang.Throwable -> Lba
            java.util.HashMap r3 = r1.c     // Catch: java.lang.Throwable -> Lb7
            java.lang.Object r3 = r3.get(r0)     // Catch: java.lang.Throwable -> Lb7
            org.chromium.chrome.browser.customtabs.a$e r3 = (org.chromium.chrome.browser.customtabs.a.e) r3     // Catch: java.lang.Throwable -> Lb7
            r4 = 2
            r5 = 1
            r6 = 0
            if (r3 != 0) goto L1f
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
            goto L24
        L1f:
            java.lang.String r3 = r3.m     // Catch: java.lang.Throwable -> Lb7
            if (r3 != 0) goto L26
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
        L24:
            r2 = r6
            goto L30
        L26:
            boolean r2 = android.text.TextUtils.equals(r3, r2)     // Catch: java.lang.Throwable -> Lb7
            if (r2 == 0) goto L2e
            r2 = r5
            goto L2f
        L2e:
            r2 = r4
        L2f:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
        L30:
            java.lang.String r3 = "CustomTabs.PredictionStatus"
            r7 = 3
            defpackage.oj2.U(r2, r7, r3)     // Catch: java.lang.Throwable -> Lba
            java.util.HashMap r3 = r1.c     // Catch: java.lang.Throwable -> Lba
            java.lang.Object r3 = r3.get(r0)     // Catch: java.lang.Throwable -> Lba
            org.chromium.chrome.browser.customtabs.a$e r3 = (org.chromium.chrome.browser.customtabs.a.e) r3     // Catch: java.lang.Throwable -> Lba
            if (r2 != r5) goto L5f
            long r8 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> Lba
            long r10 = r3.n     // Catch: java.lang.Throwable -> Lba
            long r13 = r8 - r10
            int r2 = r3.a     // Catch: java.lang.Throwable -> Lba
            jb2 r2 = defpackage.jb2.a(r2)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r8 = r3.m     // Catch: java.lang.Throwable -> Lba
            r2.c(r8)     // Catch: java.lang.Throwable -> Lba
            java.lang.String r12 = "CustomTabs.PredictionToLaunch"
            r15 = 1
            r17 = 180000(0x2bf20, double:8.8932E-319)
            r19 = 100
            defpackage.oj2.T(r12, r13, r15, r17, r19)     // Catch: java.lang.Throwable -> Lba
        L5f:
            java.lang.String r2 = "CustomTabs.WarmupStateOnLaunch"
            monitor-enter(r1)     // Catch: java.lang.Throwable -> Lba
            java.util.HashMap r8 = r1.c     // Catch: java.lang.Throwable -> Lb4
            java.lang.Object r0 = r8.get(r0)     // Catch: java.lang.Throwable -> Lb4
            org.chromium.chrome.browser.customtabs.a$e r0 = (org.chromium.chrome.browser.customtabs.a.e) r0     // Catch: java.lang.Throwable -> Lb4
            if (r0 == 0) goto L6e
            r8 = r5
            goto L6f
        L6e:
            r8 = r6
        L6f:
            if (r8 == 0) goto L7d
            android.util.SparseBooleanArray r9 = r1.d     // Catch: java.lang.Throwable -> Lb4
            int r0 = r0.a     // Catch: java.lang.Throwable -> Lb4
            boolean r0 = r9.get(r0)     // Catch: java.lang.Throwable -> Lb4
            if (r0 == 0) goto L7d
            r0 = r5
            goto L7e
        L7d:
            r0 = r6
        L7e:
            boolean r9 = r1.e     // Catch: java.lang.Throwable -> Lb4
            r10 = 4
            if (r8 == 0) goto L8b
            if (r0 == 0) goto L87
            r7 = r10
            goto L8c
        L87:
            if (r9 == 0) goto L8c
            r7 = r4
            goto L8c
        L8b:
            r7 = r9
        L8c:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
            r0 = 5
            defpackage.oj2.U(r7, r0, r2)     // Catch: java.lang.Throwable -> Lba
            if (r3 != 0) goto L95
            monitor-exit(r1)
            goto Lbd
        L95:
            boolean r0 = r3.h     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto L9a
            goto L9b
        L9a:
            r5 = r6
        L9b:
            boolean r0 = r3.i     // Catch: java.lang.Throwable -> Lba
            if (r0 == 0) goto La0
            goto La1
        La0:
            r4 = r6
        La1:
            int r5 = r5 + r4
            java.lang.String r0 = "CustomTabs.MayLaunchUrlType"
            defpackage.oj2.U(r5, r10, r0)     // Catch: java.lang.Throwable -> Lba
            r0 = 0
            r3.m = r0     // Catch: java.lang.Throwable -> Lba
            r4 = 0
            r3.n = r4     // Catch: java.lang.Throwable -> Lba
            r3.i = r6     // Catch: java.lang.Throwable -> Lba
            r3.h = r6     // Catch: java.lang.Throwable -> Lba
            monitor-exit(r1)
            goto Lbd
        Lb4:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
            throw r0     // Catch: java.lang.Throwable -> Lba
        Lb7:
            r0 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> Lba
            throw r0     // Catch: java.lang.Throwable -> Lba
        Lba:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        Lbd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b40.g0(int, org.chromium.chrome.browser.tab.Tab, org.chromium.content_public.browser.LoadUrlParams):void");
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        int i = this.n;
        if (i == 1) {
            this.l = SystemClock.elapsedRealtime();
            this.n = 2;
        } else if (i == 2) {
            if (this.e != null) {
                tab.getUrl().j();
                tab.getTitle();
            }
            this.l = SystemClock.elapsedRealtime();
        }
        CustomTabsConnection customTabsConnection = this.e;
        if (customTabsConnection != null) {
            CustomTabsSessionToken customTabsSessionToken = this.f;
            a aVar = customTabsConnection.c;
            synchronized (aVar) {
                a.e eVar = (a.e) aVar.c.get(customTabsSessionToken);
                if (eVar != null) {
                    eVar.k = false;
                }
            }
            bl1 bl1Var = this.h;
            bl1Var.d = false;
            bl1Var.e = false;
            bl1Var.f = false;
            bl1Var.b();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (((this.m > 0L ? 1 : (this.m == 0L ? 0 : -1)) == 0) && navigationHandle.g && !navigationHandle.i && !navigationHandle.c) {
            this.m = SystemClock.elapsedRealtime();
        }
    }
}
