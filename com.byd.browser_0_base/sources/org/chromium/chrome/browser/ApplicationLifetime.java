package org.chromium.chrome.browser;

import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationLifetime {
    public static final org.chromium.base.b<a> a = new org.chromium.base.b<>();

    public interface a {
        void a(boolean z);
    }

    @CalledByNative
    public static void terminate(boolean z) {
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(z);
            }
        }
    }
}
