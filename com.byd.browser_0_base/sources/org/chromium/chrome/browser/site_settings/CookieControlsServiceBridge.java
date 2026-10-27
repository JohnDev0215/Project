package org.chromium.chrome.browser.site_settings;

import J.N;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.ntp.a;

/* JADX INFO: loaded from: classes2.dex */
public class CookieControlsServiceBridge {
    public final long a = N.MDQjbYOx(this);
    public final a b;

    public interface a {
    }

    public CookieControlsServiceBridge(a aVar) {
        this.b = aVar;
    }

    @CalledByNative
    public final void sendCookieControlsUIChanges(boolean z, int i) {
        org.chromium.chrome.browser.ntp.a aVar = (org.chromium.chrome.browser.ntp.a) this.b;
        aVar.h = z;
        aVar.i = i;
        Iterator<a.InterfaceC0096a> it = aVar.f.iterator();
        while (true) {
            b.a aVar2 = (b.a) it;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0096a) aVar2.next()).a(i, z);
            }
        }
    }
}
