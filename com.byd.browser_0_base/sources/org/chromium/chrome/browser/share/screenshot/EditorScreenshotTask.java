package org.chromium.chrome.browser.share.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import defpackage.cg0;
import defpackage.ji;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public final class EditorScreenshotTask implements cg0 {
    public final Activity e;
    public final ji f;
    public Bitmap g;
    public Runnable h;

    public EditorScreenshotTask(Activity activity, ji jiVar) {
        this.e = activity;
        this.f = jiVar;
    }

    public final void a(Bitmap bitmap) {
        this.g = bitmap;
        Runnable runnable = this.h;
        if (runnable != null) {
            runnable.run();
        }
        this.h = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005f  */
    @Override // defpackage.cg0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(defpackage.eu1 r6) {
        /*
            r5 = this;
            r5.h = r6
            r6 = 1
            r0 = 0
            android.app.Activity r1 = r5.e
            if (r1 == 0) goto L5f
            boolean r2 = r1 instanceof org.chromium.chrome.browser.app.ChromeActivity
            if (r2 != 0) goto Ld
            goto L35
        Ld:
            r2 = r1
            org.chromium.chrome.browser.app.ChromeActivity r2 = (org.chromium.chrome.browser.app.ChromeActivity) r2
            org.chromium.chrome.browser.tab.Tab r2 = r2.v2()
            ji r3 = r5.f
            boolean r3 = r3.m()
            if (r3 == 0) goto L1d
            goto L35
        L1d:
            if (r2 != 0) goto L20
            goto L33
        L20:
            boolean r3 = r2.isUserInteractable()
            if (r3 != 0) goto L27
            goto L33
        L27:
            jk1 r3 = r2.u()
            if (r3 != 0) goto L35
            boolean r2 = r2.s()
            if (r2 != 0) goto L35
        L33:
            r2 = r6
            goto L36
        L35:
            r2 = r0
        L36:
            if (r2 != 0) goto L39
            goto L5f
        L39:
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            android.view.Window r3 = r1.getWindow()
            android.view.View r3 = r3.getDecorView()
            android.view.View r3 = r3.getRootView()
            r3.getWindowVisibleDisplayFrame(r2)
            r3 = r1
            org.chromium.chrome.browser.app.ChromeActivity r3 = (org.chromium.chrome.browser.app.ChromeActivity) r3
            g3 r3 = r3.O
            int r4 = r2.width()
            int r2 = r2.height()
            J.N.MRY3Qp1V(r5, r3, r4, r2)
            r2 = r6
            goto L60
        L5f:
            r2 = r0
        L60:
            if (r2 == 0) goto L63
            return
        L63:
            if (r1 != 0) goto L67
            r6 = r0
            goto L71
        L67:
            d73 r0 = defpackage.rh3.a
            eg0 r2 = new eg0
            r2.<init>(r5, r1)
            org.chromium.base.task.PostTask.c(r0, r2)
        L71:
            if (r6 == 0) goto L74
            return
        L74:
            d73 r6 = defpackage.rh3.a
            dg0 r0 = new dg0
            r0.<init>(r5)
            org.chromium.base.task.PostTask.c(r6, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.share.screenshot.EditorScreenshotTask.b(eu1):void");
    }

    @Override // defpackage.cg0
    public final Bitmap c() {
        return this.g;
    }

    @CalledByNative
    public final void onBytesReceived(byte[] bArr) {
        a(bArr != null ? BitmapFactory.decodeByteArray(bArr, 0, bArr.length) : null);
    }
}
