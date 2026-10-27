package org.chromium.android_webview;

import defpackage.InterfaceC1894zo;
import defpackage.Ws;
import defpackage.Ys;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwContentsLifecycleNotifier {
    public static final Ys a = new Ys();

    public static void onFirstWebViewCreated() {
        ThreadUtils.b();
        Iterator it = a.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((InterfaceC1894zo) ws.next()).b();
            }
        }
    }

    public static void onLastWebViewDestroyed() {
        ThreadUtils.b();
        Iterator it = a.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((InterfaceC1894zo) ws.next()).a();
            }
        }
    }
}
