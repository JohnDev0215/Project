package org.chromium.content.browser.accessibility;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewStructure;
import defpackage.es3;
import defpackage.lq3;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: AccessibilityDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public interface a {

    /* JADX INFO: renamed from: org.chromium.content.browser.accessibility.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityDelegate.java */
    public interface InterfaceC0148a {
        float a();

        float b();

        int c();

        float d();

        float e(float f);

        float f();

        float getScrollY();
    }

    String a();

    WebContents c();

    View e();

    default long g() {
        return 0L;
    }

    void h(ViewStructure viewStructure, lq3 lq3Var);

    InterfaceC0148a i();

    boolean isIncognito();

    default void b(Rect rect) {
    }

    default void d(Rect rect) {
    }

    default void f(es3 es3Var) {
    }
}
