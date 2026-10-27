package org.chromium.chrome.browser.profiles;

import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileManager {
    public static final org.chromium.base.b<a> a = new org.chromium.base.b<>();
    public static boolean b;

    public interface a {
        void b(Profile profile);

        void e(Profile profile);
    }

    public static void a(a aVar) {
        a.a(aVar);
    }

    public static void b(a aVar) {
        a.c(aVar);
    }

    @CalledByNative
    public static void onProfileAdded(Profile profile) {
        b = true;
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b(profile);
            }
        }
    }
}
