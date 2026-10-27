package org.chromium.content.browser.input;

import J.N;
import android.content.Context;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.l83;
import defpackage.lk3;
import defpackage.rq2;
import defpackage.v22;
import defpackage.wd0;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class TextSuggestionHost implements jt3, v22.a, lk3 {
    public long e;
    public final WebContentsImpl f;
    public final Context g;
    public final ViewAndroidDelegate h;
    public boolean i;
    public WindowAndroid j;
    public rq2 k;
    public l83 l;

    public static final class a {
        public static final wd0 a = new wd0(26);
    }

    public TextSuggestionHost(WebContentsImpl webContentsImpl) {
        this.f = webContentsImpl;
        this.g = webContentsImpl.j0();
        this.j = webContentsImpl.V();
        this.h = webContentsImpl.u();
        ((v22) webContentsImpl.n0(v22.class, v22.b.a)).e.add(this);
        kt3.e(webContentsImpl).b(this);
    }

    @CalledByNative
    public static TextSuggestionHost create(WebContents webContents, long j) {
        TextSuggestionHost textSuggestionHost = (TextSuggestionHost) ((WebContentsImpl) webContents).n0(TextSuggestionHost.class, a.a);
        textSuggestionHost.e = j;
        return textSuggestionHost;
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        this.j = windowAndroid;
        rq2 rq2Var = this.k;
        if (rq2Var != null) {
            rq2Var.h = windowAndroid;
        }
        l83 l83Var = this.l;
        if (l83Var != null) {
            l83Var.h = windowAndroid;
        }
    }

    @Override // v22.a
    public final void b() {
        hidePopups();
    }

    @CalledByNative
    public void hidePopups() {
        l83 l83Var = this.l;
        if (l83Var != null && l83Var.k.isShowing()) {
            this.l.k.dismiss();
            this.l = null;
        }
        rq2 rq2Var = this.k;
        if (rq2Var == null || !rq2Var.k.isShowing()) {
            return;
        }
        this.k.k.dismiss();
        this.k = null;
    }

    @Override // defpackage.jt3
    public final void onAttachedToWindow() {
        this.i = true;
    }

    @Override // defpackage.jt3
    public final void onDetachedFromWindow() {
        this.i = false;
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        hidePopups();
        this.e = 0L;
    }

    @CalledByNative
    public final void showSpellCheckSuggestionMenu(double d, double d2, String str, String[] strArr) {
        if (!this.i) {
            N.MnvYa0QF(this.e, this);
            this.k = null;
            this.l = null;
            return;
        }
        hidePopups();
        rq2 rq2Var = new rq2(this.g, this, this.j, this.h.getContainerView());
        this.k = rq2Var;
        double d3 = d2 + ((double) this.f.l.k);
        rq2Var.v = (String[]) strArr.clone();
        rq2Var.o.setVisibility(0);
        rq2Var.e(d, d3, str);
    }

    @CalledByNative
    public final void showTextSuggestionMenu(double d, double d2, String str, SuggestionInfo[] suggestionInfoArr) {
        if (!this.i) {
            N.MnvYa0QF(this.e, this);
            this.k = null;
            this.l = null;
            return;
        }
        hidePopups();
        l83 l83Var = new l83(this.g, this, this.j, this.h.getContainerView());
        this.l = l83Var;
        double d3 = d2 + ((double) this.f.l.k);
        l83Var.v = (SuggestionInfo[]) suggestionInfoArr.clone();
        l83Var.o.setVisibility(8);
        l83Var.e(d, d3, str);
    }

    @Override // ya0.a
    public final void w(int i) {
        hidePopups();
    }
}
