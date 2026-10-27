package defpackage;

import J.N;
import android.content.res.Configuration;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.ContentUiEventHandler;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Wz extends AbstractC1018jt implements InterfaceC0650dE, InterfaceC1074kt {
    public final WebContentsImpl s;
    public Boolean t;
    public boolean u;
    public Boolean v;
    public boolean w;

    public Wz(WebContents webContents) {
        this.s = (WebContentsImpl) webContents;
    }

    public static Wz a(WebContents webContents) {
        return (Wz) ((WebContentsImpl) webContents).S(Wz.class, Vz.a);
    }

    public void b() {
        C0590cA c0590cAP = C0590cA.P(this.s);
        c0590cAP.v = true;
        c0590cAP.O();
        Iterator it = c0590cAP.s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).onAttachedToWindow();
            }
        }
    }

    public void c(Configuration configuration) {
        try {
            TraceEvent.x("ViewEventSink.onConfigurationChanged", null);
            Iterator it = C0590cA.P(this.s).s.iterator();
            while (true) {
                Ws ws = (Ws) it;
                if (!ws.hasNext()) {
                    break;
                } else {
                    ((Yz) ws.next()).onConfigurationChanged(configuration);
                }
            }
            ViewAndroidDelegate viewAndroidDelegateD = this.s.D();
            if (viewAndroidDelegateD != null) {
                viewAndroidDelegateD.getContainerView().requestLayout();
            }
        } finally {
            TraceEvent.A("ViewEventSink.onConfigurationChanged");
        }
    }

    public void d() {
        C0590cA c0590cAP = C0590cA.P(this.s);
        c0590cAP.Q();
        c0590cAP.v = false;
        Iterator it = c0590cAP.s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).onDetachedFromWindow();
            }
        }
    }

    public final void e() {
        Boolean bool = this.t;
        if (bool == null) {
            return;
        }
        boolean z = bool.booleanValue() && !this.u;
        Boolean bool2 = this.v;
        if (bool2 == null || bool2.booleanValue() != z) {
            this.v = Boolean.valueOf(z);
            WebContentsImpl webContentsImpl = this.s;
            if (webContentsImpl == null) {
                return;
            }
            C0590cA c0590cAP = C0590cA.P(webContentsImpl);
            boolean zBooleanValue = this.v.booleanValue();
            boolean z2 = this.w;
            Iterator it = c0590cAP.s.iterator();
            while (true) {
                Ws ws = (Ws) it;
                if (!ws.hasNext()) {
                    break;
                } else {
                    ((Yz) ws.next()).b(zBooleanValue, z2);
                }
            }
            WebContentsImpl webContentsImpl2 = this.s;
            boolean zBooleanValue2 = this.v.booleanValue();
            long j = webContentsImpl2.t;
            if (j == 0) {
                return;
            }
            N.M9QxNoTJ(j, webContentsImpl2, zBooleanValue2);
        }
    }

    public void f(boolean z) {
        Boolean bool = this.t;
        if (bool == null || bool.booleanValue() != z) {
            this.t = Boolean.valueOf(z);
            e();
        }
    }

    public void g(boolean z) {
        Iterator it = C0590cA.P(this.s).s.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((Yz) ws.next()).onWindowFocusChanged(z);
            }
        }
    }

    public void h(InterfaceC0594cE interfaceC0594cE) {
        GestureListenerManagerImpl.O(this.s).w = interfaceC0594cE;
        ((ContentUiEventHandler) this.s.S(ContentUiEventHandler.class, AbstractC0689dz.a)).t = interfaceC0594cE;
    }
}
