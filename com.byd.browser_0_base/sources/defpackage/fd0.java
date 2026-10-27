package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import defpackage.ac0;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.download.f;
import org.chromium.chrome.browser.download.g;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: DownloadNotificationService.java */
/* JADX INFO: loaded from: classes.dex */
public final class fd0 {
    public Bitmap c;
    public final ArrayList a = new ArrayList();
    public final NotificationManagerProxyImpl b = new NotificationManagerProxyImpl(xz.a);
    public final g d = g.a.a;
    public final ac0 e = new ac0();

    /* JADX INFO: compiled from: DownloadNotificationService.java */
    public static class a {
        public static final fd0 a = new fd0();
    }

    public final void a(int i, ny nyVar) {
        boolean z;
        NotificationManagerProxyImpl notificationManagerProxyImpl = this.b;
        notificationManagerProxyImpl.getClass();
        TraceEvent traceEventH0 = TraceEvent.H0("NotificationManagerProxyImpl.cancel(id)", null);
        try {
            notificationManagerProxyImpl.a.b.cancel(null, i);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            g gVar = this.d;
            Iterator it = gVar.a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (((f) it.next()).f.equals(nyVar)) {
                    it.remove();
                    z = true;
                    break;
                }
            }
            if (z) {
                gVar.b(false);
            }
            this.a.remove(nyVar);
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r8 = this;
            org.chromium.content.browser.BrowserStartupControllerImpl r0 = defpackage.qj.a()
            boolean r0 = r0.g()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1a
            org.chromium.chrome.browser.profiles.Profile r0 = org.chromium.chrome.browser.profiles.Profile.d()
            long r3 = r0.b
            boolean r0 = J.N.MrGvO7pv(r3, r0)
            if (r0 == 0) goto L1a
            r0 = r1
            goto L1b
        L1a:
            r0 = r2
        L1b:
            org.chromium.chrome.browser.download.g r3 = r8.d
            java.util.ArrayList r4 = r3.a
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r4)
            java.util.Iterator r4 = r5.iterator()
        L28:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L72
            java.lang.Object r5 = r4.next()
            org.chromium.chrome.browser.download.f r5 = (org.chromium.chrome.browser.download.f) r5
            org.chromium.chrome.browser.profiles.OTRProfileID r6 = r5.b
            org.chromium.chrome.browser.profiles.OTRProfileID r7 = org.chromium.chrome.browser.profiles.OTRProfileID.b
            if (r6 == 0) goto L3c
            r6 = r1
            goto L3d
        L3c:
            r6 = r2
        L3d:
            if (r6 != 0) goto L40
            goto L28
        L40:
            ny r6 = r5.f
            org.chromium.chrome.browser.download.f r7 = r3.a(r6)
            if (r7 != 0) goto L49
            goto L4e
        L49:
            int r7 = r7.a
            r8.d(r6, r7)
        L4e:
            if (r0 == 0) goto L28
            boolean r7 = defpackage.n3.N0(r6)
            if (r7 == 0) goto L65
            sk r7 = defpackage.bq.a
            java.lang.String r7 = "UseDownloadOfflineContentProvider"
            boolean r7 = J.N.M09VlOh_(r7)
            if (r7 != 0) goto L65
            org.chromium.chrome.browser.download.DownloadManagerService r7 = org.chromium.chrome.browser.download.DownloadManagerService.g()
            goto L69
        L65:
            gq1 r7 = defpackage.z7.L()
        L69:
            org.chromium.chrome.browser.profiles.OTRProfileID r5 = r5.b
            r7.c(r6, r5)
            r7.f()
            goto L28
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fd0.b():void");
    }

    public final int c(ny nyVar) {
        f fVarA = this.d.a(nyVar);
        if (fVarA != null) {
            return fVarA.a;
        }
        int iD = SharedPreferencesManager.getInstance().d(1000000, "NextDownloadNotificationId");
        SharedPreferencesManager.getInstance().m(iD != Integer.MAX_VALUE ? iD + 1 : 1000000, "NextDownloadNotificationId");
        return iD;
    }

    public final void d(ny nyVar, int i) {
        Context context = xz.a;
        ac0 ac0Var = this.e;
        ac0Var.getClass();
        Log.w("cr_DownloadFg", "updateDownloadStatus status: 3, id: " + i);
        ac0Var.h.put(Integer.valueOf(i), new ac0.c(i, context));
        ac0Var.b(false);
        a(i, nyVar);
    }

    public final void e(ny nyVar, String str, boolean z, boolean z2, boolean z3, boolean z4, int i) {
        g gVar = this.d;
        f fVarA = gVar.a(nyVar);
        if (!z) {
            if (TextUtils.isEmpty(str) && gVar.a(nyVar) == null) {
                return;
            }
            c(nyVar);
            return;
        }
        if (fVarA == null || fVarA.e || z4) {
            if (z2 || i != 0) {
                c(nyVar);
                this.a.remove(nyVar);
            } else if (fVarA == null) {
                c(nyVar);
            }
        }
    }
}
