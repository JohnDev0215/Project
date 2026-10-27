package defpackage;

import org.chromium.content.browser.input.SelectPopup;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: cB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0591cB {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: bB
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            return new SelectPopup(webContents);
        }
    };
}
