package org.chromium.chrome.browser.safe_browsing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import defpackage.q11;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class SafeBrowsingReferringAppBridge {

    public static class ReferringAppInfo {
        public final int a;
        public final String b;

        public ReferringAppInfo(int i, String str) {
            this.a = i;
            this.b = str;
        }

        @CalledByNative
        public String getName() {
            return this.b;
        }

        @CalledByNative
        public int getSource() {
            return this.a;
        }
    }

    @CalledByNative
    public static ReferringAppInfo getReferringAppInfo(WindowAndroid windowAndroid) {
        Activity activity = (Activity) windowAndroid.k().get();
        String str = "";
        if (activity == null) {
            return new ReferringAppInfo(0, "");
        }
        Intent intent = activity.getIntent();
        if (intent == null) {
            return new ReferringAppInfo(0, "");
        }
        int iC = b.c(intent);
        if (iC == 0) {
            String strR = q11.r(intent, "com.android.browser.application_id");
            if (strR != null) {
                return new ReferringAppInfo(2, strR);
            }
            String strR2 = q11.r(intent, "org.chromium.chrome.browser.activity_referrer");
            if (strR2 != null) {
                return new ReferringAppInfo(3, strR2);
            }
            Uri referrer = activity.getReferrer();
            return referrer != null ? new ReferringAppInfo(3, referrer.toString()) : new ReferringAppInfo(0, "");
        }
        switch (iC) {
            case 0:
                str = "other";
                break;
            case 1:
                str = "gmail";
                break;
            case 2:
                str = "facebook";
                break;
            case 3:
                str = "plus";
                break;
            case 4:
                str = "twitter";
                break;
            case 5:
                str = "chrome";
                break;
            case 6:
                str = "google.hangouts";
                break;
            case 7:
                str = "android.messages";
                break;
            case 8:
                str = "google.news";
                break;
            case 9:
                str = "line";
                break;
            case 10:
                str = "whatsapp";
                break;
            case 11:
                str = "google.search.app";
                break;
            case 12:
                str = "webapk";
                break;
            case 13:
                str = "yahoo.mail";
                break;
            case 14:
                str = "viber";
                break;
            case 15:
                str = "youtube";
                break;
        }
        return new ReferringAppInfo(1, str);
    }
}
