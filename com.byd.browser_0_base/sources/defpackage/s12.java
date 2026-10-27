package defpackage;

import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import android.view.ViewStructure;
import org.chromium.content.browser.accessibility.a;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PlayerAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class s12 implements org.chromium.content.browser.accessibility.a {
    public final w12 a;
    public final long b;
    public final a c;

    public s12(w12 w12Var, long j, Size size) {
        this.a = w12Var;
        this.b = j;
        y12 y12Var = w12Var.a;
        this.c = new a(y12Var == null ? null : y12Var.i, y12Var.b, size);
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final String a() {
        return null;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final void b(Rect rect) {
        this.a.a.c(rect.centerX(), rect.centerY(), true);
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final WebContents c() {
        return null;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final void d(Rect rect) {
        Runnable runnable;
        c22 c22Var = this.a.c;
        if (rect == null) {
            c22Var.getClass();
            return;
        }
        e22 e22Var = c22Var.f;
        float fB = e22Var.b();
        Rect rect2 = new Rect((int) (rect.left * fB), (int) (rect.top * fB), (int) (rect.right * fB), (int) (rect.bottom * fB));
        Rect rectA = e22Var.a();
        if (rectA.contains(rect2)) {
            return;
        }
        float fHeight = rect2.top < rectA.top ? r0 - r1 : (rect2.height() + r0) - rectA.bottom;
        float fWidth = rect2.left < rectA.left ? r1 - r3 : (rect2.width() + r1) - rectA.right;
        c22Var.d.forceFinished(true);
        if (!c22Var.b(fWidth, fHeight) || (runnable = c22Var.i) == null) {
            return;
        }
        runnable.run();
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final View e() {
        return this.a.d;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final void f(es3 es3Var) {
        this.a.c.l = es3Var;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final long g() {
        return this.b;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final a.InterfaceC0148a i() {
        return this.c;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final boolean isIncognito() {
        return false;
    }

    /* JADX INFO: compiled from: PlayerAccessibilityDelegate.java */
    public static class a implements a.InterfaceC0148a {
        public final e22 a;
        public final Size b;
        public final Size c;

        public a(e22 e22Var, Size size, Size size2) {
            this.a = e22Var;
            this.b = size;
            this.c = size2;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float a() {
            return this.b.getHeight();
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float b() {
            int i = this.a.a().left;
            return i + (this.c == null ? 0 : r1.getWidth());
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final int c() {
            return this.a.a.getHeight();
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float d() {
            return 0.0f;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float f() {
            return this.b.getWidth();
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float getScrollY() {
            int i = this.a.a().top;
            return i + (this.c == null ? 0 : r1.getHeight());
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float e(float f) {
            return f;
        }
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final void h(ViewStructure viewStructure, lq3 lq3Var) {
    }
}
