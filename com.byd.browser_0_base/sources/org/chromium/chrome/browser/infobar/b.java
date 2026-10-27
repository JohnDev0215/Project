package org.chromium.chrome.browser.infobar;

import android.view.View;
import android.widget.PopupWindow;
import defpackage.tz0;
import defpackage.u73;
import org.chromium.chrome.browser.infobar.InfoBarContainer;
import org.chromium.components.infobars.InfoBar;

/* JADX INFO: compiled from: IPHInfoBarSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements PopupWindow.OnDismissListener, tz0, InfoBarContainer.f {
    public final a e;
    public C0087b f;

    /* JADX INFO: compiled from: IPHInfoBarSupport.java */
    public interface a {
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.infobar.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IPHInfoBarSupport.java */
    public static class C0087b {
        public View a;
        public u73 b;
        public String c;
    }

    /* JADX INFO: compiled from: IPHInfoBarSupport.java */
    public static class c {
        public final int a;
        public final int b;

        public c(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public b(org.chromium.chrome.browser.infobar.a aVar) {
        this.e = aVar;
    }

    @Override // org.chromium.chrome.browser.infobar.InfoBarContainer.f
    public final void d(InfoBar infoBar) {
        C0087b c0087b = this.f;
        if (c0087b == null || infoBar.j != c0087b.a) {
            return;
        }
        c0087b.b.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
    @Override // defpackage.tz0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(defpackage.xz0 r11) {
        /*
            r10 = this;
            r0 = 0
            if (r11 != 0) goto L5
            r4 = r0
            goto La
        L5:
            android.view.View r1 = r11.getView()
            r4 = r1
        La:
            org.chromium.chrome.browser.infobar.b$b r1 = r10.f
            if (r1 == 0) goto L17
            android.view.View r2 = r1.a
            if (r2 == r4) goto L17
            u73 r1 = r1.b
            r1.b()
        L17:
            if (r11 == 0) goto L9c
            if (r4 == 0) goto L9c
            java.util.WeakHashMap<android.view.View, in3> r1 = defpackage.om3.a
            boolean r1 = om3.g.b(r4)
            if (r1 != 0) goto L25
            goto L9c
        L25:
            int r11 = r11.n()
            org.chromium.chrome.browser.infobar.b$a r1 = r10.e
            org.chromium.chrome.browser.infobar.a r1 = (org.chromium.chrome.browser.infobar.a) r1
            r2 = 70
            if (r11 == r2) goto L32
            goto L51
        L32:
            org.chromium.chrome.browser.tab.Tab r11 = r1.c
            org.chromium.content_public.browser.WebContents r11 = r11.c()
            org.chromium.chrome.browser.profiles.Profile r2 = org.chromium.chrome.browser.profiles.Profile.d()
            boolean r11 = J.N.MYyPZdVK(r2, r11)
            if (r11 == 0) goto L51
            org.chromium.chrome.browser.profiles.Profile r11 = org.chromium.chrome.browser.profiles.Profile.d()
            J.N.Mwtey02Q(r11)
            org.chromium.chrome.browser.infobar.b$c r11 = new org.chromium.chrome.browser.infobar.b$c
            int r2 = org.chromium.chrome.R$string.notifications_iph
            r11.<init>(r2, r2)
            goto L52
        L51:
            r11 = r0
        L52:
            if (r11 == 0) goto L88
            xe3 r2 = r1.b
            java.lang.String r3 = "IPH_QuietNotificationPrompts"
            boolean r2 = r2.shouldTriggerHelpUI(r3)
            if (r2 != 0) goto L5f
            goto L8b
        L5f:
            org.chromium.chrome.browser.infobar.b$b r0 = new org.chromium.chrome.browser.infobar.b$b
            r0.<init>()
            r0.a = r4
            r0.c = r3
            u73 r9 = new u73
            android.content.Context r3 = r1.a
            int r5 = r11.a
            int r6 = r11.b
            qo r11 = defpackage.qo.f()
            boolean r8 = r11.c()
            nn3 r7 = new nn3
            r7.<init>(r4)
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0.b = r9
            r11 = 1
            r9.e(r11)
            goto L8b
        L88:
            r1.getClass()
        L8b:
            r10.f = r0
            if (r0 != 0) goto L90
            return
        L90:
            u73 r11 = r0.b
            r11.addOnDismissListener(r10)
            org.chromium.chrome.browser.infobar.b$b r10 = r10.f
            u73 r10 = r10.b
            r10.f()
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.infobar.b.f(xz0):void");
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        C0087b c0087b = this.f;
        if (c0087b == null) {
            return;
        }
        ((org.chromium.chrome.browser.infobar.a) this.e).b.dismissed(c0087b.c);
        this.f = null;
    }

    @Override // org.chromium.chrome.browser.infobar.InfoBarContainer.f
    public final void b() {
    }

    @Override // org.chromium.chrome.browser.infobar.InfoBarContainer.f
    public final void c() {
    }

    @Override // org.chromium.chrome.browser.infobar.InfoBarContainer.f
    public final void e() {
    }

    @Override // defpackage.tz0
    public final void a(int i) {
    }
}
