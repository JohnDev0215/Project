package defpackage;

import org.chromium.content.browser.JavascriptInjectorImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: oz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1301oz {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: nz
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            return new JavascriptInjectorImpl(webContents);
        }
    };
}
