package defpackage;

import org.chromium.content.browser.ContentUiEventHandler;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: dz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0689dz {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: cz
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            return new ContentUiEventHandler(webContents);
        }
    };
}
