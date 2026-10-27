package org.chromium.components.safe_browsing;

import defpackage.Ts;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class SafeBrowsingApiBridge {
    public static SafeBrowsingApiHandler create() {
        try {
            throw null;
        } catch (NullPointerException e) {
            StringBuilder sbF = Z7.f("Failed to init handler: ");
            sbF.append(e.getMessage());
            Ts.a("SBApiBridge", sbF.toString(), new Object[0]);
            return null;
        }
    }

    public static String getSafetyNetId(SafeBrowsingApiHandler safeBrowsingApiHandler) {
        return safeBrowsingApiHandler.c();
    }

    public static boolean startAllowlistLookup(SafeBrowsingApiHandler safeBrowsingApiHandler, String str, int i) {
        return safeBrowsingApiHandler.a(str, i);
    }

    public static void startUriLookup(SafeBrowsingApiHandler safeBrowsingApiHandler, long j, String str, int[] iArr) {
        safeBrowsingApiHandler.b(j, str, iArr);
    }
}
