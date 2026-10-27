package defpackage;

import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class TA {
    public static final InterfaceC1480sD a = new InterfaceC1480sD() { // from class: SA
        @Override // defpackage.InterfaceC1480sD
        public Object a(WebContents webContents) {
            return new ImeAdapterImpl(webContents);
        }
    };
}
