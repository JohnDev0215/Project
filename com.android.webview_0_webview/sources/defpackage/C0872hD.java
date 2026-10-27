package defpackage;

import J.N;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: renamed from: hD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0872hD implements ZD {
    public final /* synthetic */ SelectionPopupControllerImpl a;

    public C0872hD(SelectionPopupControllerImpl selectionPopupControllerImpl, RunnableC0649dD runnableC0649dD) {
        this.a = selectionPopupControllerImpl;
    }

    public void a(YD yd) {
        int i;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.a;
        if (!selectionPopupControllerImpl.P) {
            selectionPopupControllerImpl.V = null;
            return;
        }
        int i2 = yd.a;
        if (i2 > 0 || (i = yd.b) < 0) {
            SelectionPopupControllerImpl selectionPopupControllerImpl2 = this.a;
            selectionPopupControllerImpl2.V = null;
            selectionPopupControllerImpl2.K();
            return;
        }
        selectionPopupControllerImpl.V = yd;
        if (i2 != 0 || i != 0) {
            WebContentsImpl webContentsImpl = this.a.w;
            N.MjgOFo_o(webContentsImpl.t, webContentsImpl, yd.a, yd.b, true);
        } else {
            C1039kD c1039kD = selectionPopupControllerImpl.T;
            if (c1039kD != null) {
                c1039kD.f(selectionPopupControllerImpl.M, selectionPopupControllerImpl.N, yd);
            }
            this.a.K();
        }
    }
}
