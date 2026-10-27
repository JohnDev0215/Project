package org.chromium.components.security_interstitials;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class DateAndTimeSettingsHelper {
    public static void openDateAndTimeSettings() {
        Intent intent = new Intent("android.settings.DATE_SETTINGS");
        try {
            intent.addFlags(268435456);
            Is.a.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
