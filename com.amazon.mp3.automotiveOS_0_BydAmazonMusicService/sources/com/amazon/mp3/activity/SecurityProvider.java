package com.amazon.mp3.activity;

import android.content.Context;
import android.os.Build;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;

/* JADX INFO: loaded from: classes3.dex */
public class SecurityProvider {
    private static final String TAG = "SecurityProvider";

    public static void upgrade(final Context context) {
        if (PlatformUtil.isFireOS()) {
            Log.debug(TAG, "Skipping attempt to install the latest security provider since this is a Fire Tablet device.");
            return;
        }
        try {
            ProviderInstaller.installIfNeeded(context);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e) {
            Log.warning(TAG, "Some of the network calls may not work because of the failure in installing the latest security provider " + e);
            sendUpgradeSecurityProviderFailedEvent(e);
        }
    }

    private static void sendUpgradeSecurityProviderFailedEvent(final Exception e) {
        FlexEvent.Builder builder = FlexEvent.builder("securityProviderUpgradeFailed");
        if (e != null) {
            builder.withFlexStr1(e.getMessage());
        }
        builder.withFlexStr2(Integer.toString(Build.VERSION.SDK_INT));
        MetricsLogger.sendEvent(builder.build());
    }
}
