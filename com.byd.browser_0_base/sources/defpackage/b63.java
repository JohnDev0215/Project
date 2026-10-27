package defpackage;

import J.N;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabbedPaintPreview.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b63 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ a63 f;

    public b63(a63 a63Var, boolean z) {
        this.f = a63Var;
        this.e = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        a63 a63Var = this.f;
        Tab tab = a63Var.e;
        if (tab != null) {
            tab.o().c(a63Var.g);
        }
        if (a63Var.i != null) {
            a63Var.i = null;
        }
        if (this.e) {
            Tab tab2 = a63Var.e;
            WebContents webContentsC = tab2 != null ? tab2.c() : null;
            if (webContentsC != null && !webContentsC.isDestroyed()) {
                WindowAndroid windowAndroidV = webContentsC.V();
                Activity activity = windowAndroidV != null ? (Activity) windowAndroidV.k().get() : null;
                View currentFocus = activity != null ? activity.getCurrentFocus() : null;
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
                WebContentsAccessibilityImpl webContentsAccessibilityImplB = kq3.b(webContentsC);
                if (webContentsAccessibilityImplB != null) {
                    long j = webContentsAccessibilityImplB.j;
                    if (j != 0) {
                        N.MPQKLw45(j, webContentsAccessibilityImplB.s, -1);
                        webContentsAccessibilityImplB.s = -1;
                        webContentsAccessibilityImplB.A(webContentsAccessibilityImplB.l, 256);
                        webContentsAccessibilityImplB.l = -1;
                    }
                }
            }
        }
        a63Var.m = false;
        a63Var.n = false;
    }
}
