package defpackage;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HN extends AN implements View.OnLayoutChangeListener {
    public boolean c;
    public WeakReference d;

    public HN(WeakReference weakReference) {
        this.d = weakReference;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    @Override // android.view.View.OnLayoutChangeListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayoutChange(android.view.View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        /*
            r0 = this;
            java.lang.ref.WeakReference r2 = r0.d
            java.lang.Object r2 = r2.get()
            android.app.Activity r2 = (android.app.Activity) r2
            if (r2 != 0) goto Lb
            return
        Lb:
            int r3 = android.os.Build.VERSION.SDK_INT
            android.view.View r1 = r1.getRootView()
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L6a
            android.graphics.Rect r6 = new android.graphics.Rect
            r6.<init>()
            r1.getWindowVisibleDisplayFrame(r6)
            int r7 = r6.top
            int r8 = r1.getHeight()
            int r6 = r6.height()
            int r6 = r6 + r7
            int r8 = r8 - r6
            r6 = 23
            if (r8 > 0) goto L2f
            r8 = 0
            goto L3c
        L2f:
            if (r3 < r6) goto L3c
            android.view.WindowInsets r7 = r1.getRootWindowInsets()
            if (r7 == 0) goto L3c
            int r7 = r7.getStableInsetBottom()
            int r8 = r8 - r7
        L3c:
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            r1.getWindowVisibleDisplayFrame(r7)
            int r7 = r7.width()
            int r1 = r1.getWidth()
            if (r7 == r1) goto L50
            r1 = 1
            goto L51
        L50:
            r1 = 0
        L51:
            if (r1 == 0) goto L54
            goto L56
        L54:
            if (r3 < r6) goto L58
        L56:
            r1 = 0
            goto L67
        L58:
            android.content.res.Resources r1 = r2.getResources()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.density
            r2 = 1120403456(0x42c80000, float:100.0)
            float r1 = r1 * r2
            int r1 = (int) r1
        L67:
            if (r8 <= r1) goto L6a
            goto L6b
        L6a:
            r4 = 0
        L6b:
            boolean r1 = r0.c
            if (r1 != r4) goto L70
            return
        L70:
            r0.c = r4
            Ys r1 = r0.a
            java.util.Iterator r1 = r1.iterator()
        L78:
            r2 = r1
            Ws r2 = (defpackage.Ws) r2
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L8b
            java.lang.Object r2 = r2.next()
            zN r2 = (defpackage.InterfaceC1875zN) r2
            r2.a(r4)
            goto L78
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.HN.onLayoutChange(android.view.View, int, int, int, int, int, int, int, int):void");
    }
}
