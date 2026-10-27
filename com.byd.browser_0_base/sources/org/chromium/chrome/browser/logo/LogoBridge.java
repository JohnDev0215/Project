package org.chromium.chrome.browser.logo;

import J.N;
import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class LogoBridge {
    public long a;

    public static class Logo {
        public final Bitmap a;
        public final String b;
        public final String c;
        public final String d;

        public Logo(Bitmap bitmap, String str, String str2, String str3) {
            this.a = bitmap;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }
    }

    public interface LogoObserver {
        @CalledByNative
        void onCachedLogoRevalidated();

        @CalledByNative
        void onLogoAvailable(Logo logo, boolean z);
    }

    public LogoBridge(Profile profile) {
        this.a = N.MiF49PQd(this, profile);
    }

    @CalledByNative
    public static Logo createLogo(Bitmap bitmap, String str, String str2, String str3) {
        return new Logo(bitmap, str, str2, str3);
    }
}
