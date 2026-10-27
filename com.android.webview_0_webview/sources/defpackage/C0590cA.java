package defpackage;

import java.util.Iterator;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: cA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0590cA extends AbstractC0715eO implements InterfaceC0771fO, InterfaceC1074kt {
    public final Ys s = new Ys();
    public WindowAndroid t;
    public Wz u;
    public boolean v;
    public int w;
    public float x;

    public C0590cA(WebContents webContents, Zz zz) {
        this.u = Wz.a(webContents);
        WindowAndroid windowAndroidH = webContents.H();
        if (windowAndroidH != null) {
            u(windowAndroidH);
        }
        this.s.f((WebContentsImpl) webContents);
    }

    public static C0590cA P(WebContents webContents) {
        return (C0590cA) ((WebContentsImpl) webContents).S(C0590cA.class, AbstractC0534bA.a);
    }

    public final void O() {
        WindowAndroid windowAndroid;
        WindowAndroid windowAndroid2;
        if (this.v && (windowAndroid2 = this.t) != null) {
            AbstractC0827gO abstractC0827gO = windowAndroid2.t;
            abstractC0827gO.a.put(this, null);
            x(abstractC0827gO.g);
            e(abstractC0827gO.d);
        }
        if (!this.v || (windowAndroid = this.t) == null) {
            return;
        }
        windowAndroid.E.f(this.u);
        if (this.t.R() == 3) {
            Wz wz = this.u;
            if (wz.u) {
                wz.u = false;
                wz.e();
            }
        }
    }

    public final void Q() {
        WindowAndroid windowAndroid;
        WindowAndroid windowAndroid2 = this.t;
        if (windowAndroid2 != null) {
            windowAndroid2.t.a.remove(this);
        }
        if (!this.v || (windowAndroid = this.t) == null) {
            return;
        }
        windowAndroid.E.g(this.u);
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void e(float f) {
        if (this.x == f) {
            return;
        }
        this.x = f;
        Iterator it = this.s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).e(f);
            }
        }
    }

    public void u(WindowAndroid windowAndroid) {
        if (windowAndroid == this.t) {
            return;
        }
        Q();
        this.t = windowAndroid;
        O();
        Iterator it = this.s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).u(windowAndroid);
            }
        }
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void x(int i) {
        if (this.w == i) {
            return;
        }
        this.w = i;
        Iterator it = this.s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).x(i);
            }
        }
    }
}
