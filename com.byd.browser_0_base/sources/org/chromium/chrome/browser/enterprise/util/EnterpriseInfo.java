package org.chromium.chrome.browser.enterprise.util;

import defpackage.ps;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public abstract class EnterpriseInfo {
    public static org.chromium.chrome.browser.enterprise.util.a a;

    public static class a {
        public final boolean a;
        public final boolean b;

        public a(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && this.b == aVar.b;
        }
    }

    public static EnterpriseInfo b() {
        Object obj = ThreadUtils.a;
        if (a == null) {
            a = new org.chromium.chrome.browser.enterprise.util.a();
        }
        return a;
    }

    @CalledByNative
    public static void getManagedStateForNative() {
        b().a(new ps(5));
    }

    public abstract void a(Callback<a> callback);

    public abstract void c();
}
