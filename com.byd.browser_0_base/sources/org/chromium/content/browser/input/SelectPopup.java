package org.chromium.content.browser.input;

import J.N;
import android.view.View;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.lk3;
import defpackage.qh1;
import defpackage.v22;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class SelectPopup implements v22.a, jt3, lk3 {
    public final WebContentsImpl e;
    public final View f;
    public a g;
    public long h;
    public long i;

    public interface a {
        void a();

        void b(boolean z);
    }

    public static final class b {
        public static final qh1 a = new qh1(20);
    }

    public SelectPopup(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl;
        ViewAndroidDelegate viewAndroidDelegateU = webContentsImpl.u();
        this.f = viewAndroidDelegateU.getContainerView();
        viewAndroidDelegateU.d.a(this);
        ((v22) webContentsImpl.n0(v22.class, v22.b.a)).e.add(this);
        kt3.e(webContentsImpl).b(this);
    }

    @CalledByNative
    public static SelectPopup create(WebContents webContents, long j) {
        SelectPopup selectPopup = (SelectPopup) ((WebContentsImpl) webContents).n0(SelectPopup.class, b.a);
        selectPopup.h = j;
        return selectPopup;
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        this.g = null;
    }

    @Override // v22.a
    public final void b() {
        a aVar = this.g;
        if (aVar != null) {
            aVar.b(true);
        }
    }

    public final void d(int[] iArr) {
        long j = this.h;
        if (j != 0) {
            N.ME0LgXse(j, this, this.i, iArr);
        }
        this.i = 0L;
        this.g = null;
    }

    @CalledByNative
    public void hideWithoutCancel() {
        a aVar = this.g;
        if (aVar == null) {
            return;
        }
        aVar.b(false);
        this.g = null;
        this.i = 0L;
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.h = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0088  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void show(android.view.View r15, long r16, java.lang.String[] r18, int[] r19, boolean r20, int[] r21, boolean r22) {
        /*
            r14 = this;
            r0 = r14
            r1 = r16
            r3 = r18
            android.view.View r4 = r0.f
            android.view.ViewParent r5 = r4.getParent()
            r6 = 0
            if (r5 == 0) goto La4
            int r4 = r4.getVisibility()
            if (r4 == 0) goto L16
            goto La4
        L16:
            org.chromium.content.browser.webcontents.WebContentsImpl r4 = r0.e
            if (r4 != 0) goto L1b
            goto L3c
        L1b:
            boolean r5 = org.chromium.content.browser.selection.SelectionPopupControllerImpl.M
            java.lang.Class<org.chromium.content.browser.selection.SelectionPopupControllerImpl> r5 = org.chromium.content.browser.selection.SelectionPopupControllerImpl.class
            lk3 r5 = r4.n0(r5, r6)
            org.chromium.content.browser.selection.SelectionPopupControllerImpl r5 = (org.chromium.content.browser.selection.SelectionPopupControllerImpl) r5
            if (r5 == 0) goto L2d
            r6 = 1
            r5.y = r6
            r5.i()
        L2d:
            wd0 r5 = v22.b.a
            java.lang.Class<v22> r6 = defpackage.v22.class
            lk3 r5 = r4.n0(r6, r5)
            v22 r5 = (defpackage.v22) r5
            if (r5 == 0) goto L3c
            r5.a()
        L3c:
            android.content.Context r7 = r4.j0()
            if (r7 != 0) goto L43
            return
        L43:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
        L49:
            int r6 = r3.length
            if (r5 >= r6) goto L5b
            zh2 r6 = new zh2
            r8 = r3[r5]
            r9 = r19[r5]
            r6.<init>(r8, r9)
            r4.add(r6)
            int r5 = r5 + 1
            goto L49
        L5b:
            boolean r3 = org.chromium.ui.base.DeviceFormFactor.isTablet()
            if (r3 == 0) goto L88
            if (r20 != 0) goto L88
            boolean r3 = org.chromium.ui.accessibility.AccessibilityState.f
            if (r3 != 0) goto L6a
            org.chromium.ui.accessibility.AccessibilityState.a()
        L6a:
            org.chromium.ui.accessibility.AccessibilityState$c r3 = org.chromium.ui.accessibility.AccessibilityState.e
            boolean r3 = r3.b
            if (r3 != 0) goto L88
            org.chromium.content.browser.input.e r3 = new org.chromium.content.browser.input.e
            se3 r8 = new se3
            r5 = 22
            r8.<init>(r5, r14)
            org.chromium.content.browser.webcontents.WebContentsImpl r13 = r0.e
            r6 = r3
            r9 = r15
            r10 = r4
            r11 = r21
            r12 = r22
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            r0.g = r3
            goto L9c
        L88:
            org.chromium.content.browser.input.d r3 = new org.chromium.content.browser.input.d
            aq3 r8 = new aq3
            r5 = 10
            r8.<init>(r5, r14)
            r6 = r3
            r9 = r4
            r10 = r20
            r11 = r21
            r6.<init>(r7, r8, r9, r10, r11)
            r0.g = r3
        L9c:
            r0.i = r1
            org.chromium.content.browser.input.SelectPopup$a r0 = r0.g
            r0.a()
            return
        La4:
            r0.i = r1
            r14.d(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.input.SelectPopup.show(android.view.View, long, java.lang.String[], int[], boolean, int[], boolean):void");
    }
}
