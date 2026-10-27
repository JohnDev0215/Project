package org.chromium.components.webapps;

import defpackage.zt1;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AppBannerManager {
    public static final a a = new a(R$string.menu_install_webapp, R$string.app_banner_install);
    public static final a b = new a(R$string.menu_add_to_homescreen, R$string.add);
    public static Boolean c;

    public static class a {
        public final int a;
        public final int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    @CalledByNative
    public static AppBannerManager create(long j) {
        return new AppBannerManager();
    }

    @CalledByNative
    public static boolean isRelatedNonWebAppInstalled(String str) {
        return zt1.e(str);
    }

    @CalledByNative
    public static boolean isSupported() {
        if (c == null) {
            c = Boolean.valueOf(WebappsUtils.a());
        }
        return c.booleanValue();
    }

    @CalledByNative
    public final void destroy() {
    }

    @CalledByNative
    public final void fetchAppDetails(String str, String str2, String str3, int i) {
    }
}
