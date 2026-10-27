package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: zz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1905zz extends AbstractC1018jt implements InterfaceC1074kt {
    public final List s = new ArrayList();

    public C1905zz(WebContents webContents) {
    }

    public static C1905zz a(WebContents webContents) {
        return (C1905zz) ((WebContentsImpl) webContents).S(C1905zz.class, AbstractC1850yz.a);
    }

    public static void c(WebContents webContents) {
        if (webContents == null) {
            return;
        }
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(webContents);
        if (selectionPopupControllerImplT != null) {
            selectionPopupControllerImplT.L = true;
            selectionPopupControllerImplT.s();
        }
        C1905zz c1905zzA = a(webContents);
        if (c1905zzA != null) {
            c1905zzA.b();
        }
    }

    public void b() {
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            ((InterfaceC1740wz) it.next()).a();
        }
    }
}
