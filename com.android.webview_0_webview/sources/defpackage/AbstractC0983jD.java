package defpackage;

import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: jD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0983jD {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: iD
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            SelectionPopupControllerImpl selectionPopupControllerImpl = new SelectionPopupControllerImpl(webContents, null, true);
            selectionPopupControllerImpl.x = BD.s;
            return selectionPopupControllerImpl;
        }
    };
}
