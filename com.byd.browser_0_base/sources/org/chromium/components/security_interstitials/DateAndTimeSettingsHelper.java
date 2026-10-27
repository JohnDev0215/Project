package org.chromium.components.security_interstitials;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DateAndTimeSettingsHelper {
    @CalledByNative
    public static void openDateAndTimeSettings() {
        Intent intent = new Intent("android.settings.DATE_SETTINGS");
        try {
            intent.addFlags(268435456);
            xz.a.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
