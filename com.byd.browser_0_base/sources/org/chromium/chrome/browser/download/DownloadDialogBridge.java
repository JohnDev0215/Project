package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import defpackage.am1;
import defpackage.bi1;
import defpackage.nc0;
import defpackage.oc0;
import defpackage.oj2;
import defpackage.v62;
import defpackage.zl1;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.download.b;
import org.chromium.chrome.browser.download.dialogs.DownloadLocationCustomView;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes.dex */
public class DownloadDialogBridge implements nc0 {
    public long a;
    public final oc0 b;
    public WindowAndroid c;
    public int d;
    public String e;

    public DownloadDialogBridge(long j, oc0 oc0Var) {
        this.a = j;
        this.b = oc0Var;
    }

    @CalledByNative
    public static DownloadDialogBridge create(long j) {
        oc0 oc0Var = new oc0();
        DownloadDialogBridge downloadDialogBridge = new DownloadDialogBridge(j, oc0Var);
        oc0Var.e = downloadDialogBridge;
        return downloadDialogBridge;
    }

    public final void a(String str) {
        this.e = str;
        if (this.d == 6) {
            oj2.O("MobileDownload.Location.Dialog.SuggestionSelected", !str.equals(N.M4fixBWD()));
        }
        long j = this.a;
        if (j == 0) {
            return;
        }
        N.Molx_ess(j, this, this.e);
    }

    @CalledByNative
    public void destroy() {
        this.a = 0L;
        oc0 oc0Var = this.b;
        bi1 bi1Var = oc0Var.j;
        if (bi1Var != null) {
            bi1Var.b(4, oc0Var.f);
        }
        j<PropertyModel, DownloadLocationCustomView, v62> jVar = oc0Var.h;
        if (jVar != null) {
            jVar.c();
        }
    }

    @CalledByNative
    public final void showDialog(WindowAndroid windowAndroid, final long j, final int i, final int i2, final String str, final boolean z) {
        this.c = windowAndroid;
        final Activity activity = (Activity) windowAndroid.k().get();
        if (activity != null) {
            b.e.a.a(new Callback(activity, i2, j, i, str, z) { // from class: yb0
                public final /* synthetic */ Activity f;
                public final /* synthetic */ int g;
                public final /* synthetic */ long h;
                public final /* synthetic */ String i;
                public final /* synthetic */ boolean j;

                {
                    this.i = str;
                    this.j = z;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
                @Override // org.chromium.base.Callback
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onResult(java.lang.Object r14) {
                    /*
                        r13 = this;
                        java.util.ArrayList r14 = (java.util.ArrayList) r14
                        org.chromium.chrome.browser.download.DownloadDialogBridge r0 = r13.e
                        r0.getClass()
                        android.app.Activity r1 = r13.f
                        r2 = r1
                        ci1 r2 = (defpackage.ci1) r2
                        bi1 r2 = r2.P0()
                        sk r3 = defpackage.bq.a
                        java.lang.String r3 = "SmartSuggestionForLargeDownloads"
                        boolean r3 = J.N.M09VlOh_(r3)
                        long r4 = r13.h
                        if (r3 == 0) goto L61
                        java.lang.String r3 = J.N.M4fixBWD()
                        r6 = 0
                        int r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                        r7 = 0
                        if (r6 > 0) goto L28
                        goto L53
                    L28:
                        java.util.Iterator r14 = r14.iterator()
                        r6 = r7
                    L2d:
                        boolean r8 = r14.hasNext()
                        if (r8 == 0) goto L57
                        java.lang.Object r8 = r14.next()
                        ma0 r8 = (defpackage.ma0) r8
                        long r9 = r8.c
                        long r9 = r9 - r4
                        double r9 = (double) r9
                        long r11 = r8.d
                        double r11 = (double) r11
                        double r9 = r9 / r11
                        r11 = 4587366580439587226(0x3fa999999999999a, double:0.05)
                        int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                        if (r9 >= 0) goto L4b
                        goto L2d
                    L4b:
                        java.lang.String r6 = r8.b
                        boolean r6 = r3.equals(r6)
                        if (r6 == 0) goto L55
                    L53:
                        r6 = r7
                        goto L57
                    L55:
                        r6 = 1
                        goto L2d
                    L57:
                        if (r6 == 0) goto L61
                        r14 = 2
                        java.lang.String r3 = "MobileDownload.Location.Dialog.Suggestion.Events"
                        defpackage.oj2.U(r7, r14, r3)
                        r14 = 6
                        goto L63
                    L61:
                        int r14 = r13.g
                    L63:
                        org.chromium.chrome.browser.profiles.Profile r3 = org.chromium.chrome.browser.profiles.Profile.d()
                        defpackage.oj2.k(r3)
                        r0.d = r14
                        java.lang.String r3 = r13.i
                        r0.e = r3
                        oc0 r0 = r0.b
                        r0.getClass()
                        if (r1 == 0) goto L9b
                        if (r2 != 0) goto L7a
                        goto L9b
                    L7a:
                        r0.n = r1
                        r0.j = r2
                        r0.k = r4
                        r0.l = r14
                        r0.m = r3
                        boolean r14 = J.N.MGOzH4qx()
                        r0.q = r14
                        boolean r13 = r13.j
                        r0.p = r13
                        org.chromium.chrome.browser.download.b r13 = org.chromium.chrome.browser.download.b.e.a
                        so r14 = new so
                        r1 = 24
                        r14.<init>(r1, r0)
                        r13.a(r14)
                        goto La0
                    L9b:
                        r13 = 8
                        r0.b(r13)
                    La0:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: defpackage.yb0.onResult(java.lang.Object):void");
                }
            });
            return;
        }
        long j2 = this.a;
        if (j2 == 0) {
            return;
        }
        N.M9BtabC7(j2, this);
        WindowAndroid windowAndroid2 = this.c;
        if (windowAndroid2 != null) {
            zl1 zl1Var = (zl1) am1.a.e(windowAndroid2.r);
            if (zl1Var != null) {
                zl1Var.destroy();
                zl1Var.f.finish();
            }
            this.c = null;
        }
    }
}
