package org.chromium.content.browser.input;

import J.N;
import android.content.Context;
import defpackage.AbstractC1588uB;
import defpackage.C0590cA;
import defpackage.C1259oB;
import defpackage.C1643vB;
import defpackage.C1905zz;
import defpackage.InterfaceC1074kt;
import defpackage.InterfaceC1740wz;
import defpackage.Xz;
import defpackage.Yz;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TextSuggestionHost extends Xz implements Yz, InterfaceC1740wz, InterfaceC1074kt {
    public long s;
    public final WebContentsImpl t;
    public final Context u;
    public final ViewAndroidDelegate v;
    public boolean w;
    public WindowAndroid x;
    public C1259oB y;
    public C1643vB z;

    public TextSuggestionHost(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.t = webContentsImpl;
        this.u = webContentsImpl.R();
        this.x = this.t.H();
        this.v = this.t.D();
        WebContentsImpl webContentsImpl2 = this.t;
        if (webContentsImpl2 != null) {
            C1905zz.a(webContentsImpl2).s.add(this);
        }
        C0590cA c0590cAP = C0590cA.P(this.t);
        c0590cAP.s.f(this);
        if (c0590cAP.v) {
            this.w = true;
        }
    }

    public static TextSuggestionHost create(WebContents webContents, long j) {
        TextSuggestionHost textSuggestionHost = (TextSuggestionHost) ((WebContentsImpl) webContents).S(TextSuggestionHost.class, AbstractC1588uB.a);
        textSuggestionHost.s = j;
        return textSuggestionHost;
    }

    private void onNativeDestroyed() {
        hidePopups();
        this.s = 0L;
    }

    private void showSpellCheckSuggestionMenu(double d, double d2, String str, String[] strArr) {
        if (!this.w) {
            O(false);
            return;
        }
        hidePopups();
        C1259oB c1259oB = new C1259oB(this.u, this, this.x, this.v.getContainerView());
        this.y = c1259oB;
        c1259oB.f(d, d2 + ((double) this.t.y.k), str, strArr);
    }

    private void showTextSuggestionMenu(double d, double d2, String str, SuggestionInfo[] suggestionInfoArr) {
        if (!this.w) {
            O(false);
            return;
        }
        hidePopups();
        C1643vB c1643vB = new C1643vB(this.u, this, this.x, this.v.getContainerView());
        this.z = c1643vB;
        c1643vB.f(d, d2 + ((double) this.t.y.k), str, suggestionInfoArr);
    }

    public void O(boolean z) {
        if (!z) {
            N.MnvYa0QF(this.s, this);
        }
        this.y = null;
        this.z = null;
    }

    @Override // defpackage.InterfaceC1740wz
    public void a() {
        hidePopups();
    }

    public void hidePopups() {
        C1643vB c1643vB = this.z;
        if (c1643vB != null && c1643vB.y.isShowing()) {
            this.z.y.dismiss();
            this.z = null;
        }
        C1259oB c1259oB = this.y;
        if (c1259oB == null || !c1259oB.y.isShowing()) {
            return;
        }
        this.y.y.dismiss();
        this.y = null;
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onAttachedToWindow() {
        this.w = true;
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onDetachedFromWindow() {
        this.w = false;
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void u(WindowAndroid windowAndroid) {
        this.x = windowAndroid;
        C1259oB c1259oB = this.y;
        if (c1259oB != null) {
            c1259oB.v = windowAndroid;
        }
        C1643vB c1643vB = this.z;
        if (c1643vB != null) {
            c1643vB.v = this.x;
        }
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void x(int i) {
        hidePopups();
    }
}
