package defpackage;

import android.content.Intent;
import android.os.IBinder;
import android.util.SparseIntArray;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: compiled from: MediaCaptureNotificationServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class yc1 extends SplitCompatService.a {
    public NotificationManagerProxyImpl b;
    public SharedPreferencesManager c;
    public final SparseIntArray d = new SparseIntArray();

    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void i(android.content.Context r5, int r6, org.chromium.content_public.browser.WebContents r7, org.chromium.url.GURL r8) {
        /*
            r0 = 1
            r1 = 0
            if (r7 != 0) goto L5
            goto L23
        L5:
            boolean r2 = J.N.MDk3$bjp(r7)
            if (r2 == 0) goto Ld
            r7 = 4
            goto L24
        Ld:
            boolean r2 = J.N.MybJWOXK(r7)
            boolean r7 = J.N.MKIWbnaU(r7)
            if (r2 == 0) goto L1b
            if (r7 == 0) goto L1b
            r7 = r0
            goto L24
        L1b:
            if (r2 == 0) goto L1f
            r7 = 3
            goto L24
        L1f:
            if (r7 == 0) goto L23
            r7 = 2
            goto L24
        L23:
            r7 = r1
        L24:
            if (r7 == 0) goto L27
            goto L46
        L27:
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r2 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            java.lang.String r3 = "WebRTCNotificationIds"
            r4 = 0
            java.util.Set r2 = r2.i(r3, r4)
            if (r2 == 0) goto L45
            boolean r3 = r2.isEmpty()
            if (r3 != 0) goto L45
            java.lang.String r3 = java.lang.String.valueOf(r6)
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L45
            goto L46
        L45:
            r0 = r1
        L46:
            if (r0 != 0) goto L49
            return
        L49:
            android.content.Intent r0 = new android.content.Intent
            java.lang.Class<org.chromium.chrome.browser.media.MediaCaptureNotificationService> r1 = org.chromium.chrome.browser.media.MediaCaptureNotificationService.class
            r0.<init>(r5, r1)
            java.lang.String r1 = "org.chromium.chrome.browser.media.SCREEN_CAPTURE_UPDATE"
            r0.setAction(r1)
            java.lang.String r1 = "NotificationId"
            r0.putExtra(r1, r6)
            java.lang.String r1 = "NotificationMediaUrl"
            java.lang.String r8 = r8.j()
            r0.putExtra(r1, r8)
            java.lang.String r8 = "NotificationMediaType"
            r0.putExtra(r8, r7)
            n53 r7 = defpackage.oj2.r()
            o53 r7 = (defpackage.o53) r7
            org.chromium.chrome.browser.tab.Tab r7 = r7.e(r6)
            if (r7 == 0) goto L87
            n53 r7 = defpackage.oj2.r()
            o53 r7 = (defpackage.o53) r7
            org.chromium.chrome.browser.tab.Tab r6 = r7.e(r6)
            boolean r6 = r6.isIncognito()
            java.lang.String r7 = "NotificationIsIncognito"
            r0.putExtra(r7, r6)
        L87:
            r5.startService(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yc1.i(android.content.Context, int, org.chromium.content_public.browser.WebContents, org.chromium.url.GURL):void");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        return null;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void b() {
        this.b = new NotificationManagerProxyImpl(xz.a);
        this.c = SharedPreferencesManager.getInstance();
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void c() {
        h();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d3  */
    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int e(android.content.Intent r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yc1.e(android.content.Intent, int, int):int");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final boolean g(Intent intent) {
        h();
        return super.g(intent);
    }

    public final void h() {
        Set<String> setI = this.c.i("WebRTCNotificationIds", null);
        if (setI == null) {
            return;
        }
        Iterator<String> it = setI.iterator();
        while (it.hasNext()) {
            this.b.d(Integer.parseInt(it.next()), "MediaCaptureNotificationService");
        }
        this.c.removeKey("WebRTCNotificationIds");
    }

    public final void j(int i, boolean z) {
        HashSet hashSet = new HashSet(this.c.i("WebRTCNotificationIds", new HashSet()));
        if (z && !hashSet.isEmpty() && hashSet.contains(String.valueOf(i))) {
            hashSet.remove(String.valueOf(i));
        } else if (!z) {
            hashSet.add(String.valueOf(i));
        }
        this.c.p("WebRTCNotificationIds", hashSet);
    }
}
