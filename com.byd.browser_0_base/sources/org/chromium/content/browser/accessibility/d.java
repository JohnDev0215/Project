package org.chromium.content.browser.accessibility;

import J.N;
import android.view.View;
import android.view.ViewStructure;
import defpackage.lq3;
import defpackage.yp1;
import defpackage.za2;
import org.chromium.content.browser.accessibility.a;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: WebContentsAccessibilityDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements org.chromium.content.browser.accessibility.a {
    public final WebContentsImpl a;
    public final a b = new a();

    /* JADX INFO: compiled from: WebContentsAccessibilityDelegate.java */
    public class a implements a.InterfaceC0148a {
        public a() {
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float a() {
            return g().d;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float b() {
            return g().a;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final int c() {
            za2 za2VarG = g();
            return (int) Math.ceil(za2VarG.a(za2VarG.f));
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float d() {
            return g().k;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float e(float f) {
            return g().a(f);
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float f() {
            return g().c;
        }

        public final za2 g() {
            return d.this.a.l;
        }

        @Override // org.chromium.content.browser.accessibility.a.InterfaceC0148a
        public final float getScrollY() {
            return g().b;
        }
    }

    public d(WebContents webContents) {
        this.a = (WebContentsImpl) webContents;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final String a() {
        return this.a.n;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final WebContents c() {
        return this.a;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final View e() {
        return this.a.u().getContainerView();
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final void h(ViewStructure viewStructure, lq3 lq3Var) {
        WebContentsImpl webContentsImpl = this.a;
        webContentsImpl.R();
        N.M16eLpU9(webContentsImpl.f, viewStructure, new yp1(webContentsImpl.l), lq3Var);
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final a.InterfaceC0148a i() {
        return this.b;
    }

    @Override // org.chromium.content.browser.accessibility.a
    public final boolean isIncognito() {
        return this.a.isIncognito();
    }
}
