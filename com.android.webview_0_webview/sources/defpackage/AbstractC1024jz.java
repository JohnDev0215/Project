package defpackage;

import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: jz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1024jz {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: iz
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            return new GestureListenerManagerImpl(webContents);
        }
    };
}
