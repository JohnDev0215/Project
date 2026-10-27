package com.amazon.mp3.config;

import android.content.Context;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.util.Log;
import com.amazon.music.arcus.RemoteConfigRequest;
import com.amazon.music.arcus.config.android.RemoteSystemConfigProvider;
import com.amazon.music.arcus.config.android.SystemConfigProvider;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SystemConfigRetriever {
    private static final String TAG = "SystemConfigRetriever";
    private static SystemConfigProvider sConfigProvider;
    private static SystemConfigProvider sNoOpConfigProvider;

    public static synchronized void init(final Context context) {
        if (sConfigProvider == null) {
            RemoteConfigRequest.Builder builder = new RemoteConfigRequest.Builder();
            if (SettingsActivity.shouldUseArcusTestConfig(context)) {
                builder.withTestEnvironment();
            }
            sConfigProvider = new RemoteSystemConfigProvider(context, builder.build());
        }
    }

    public static synchronized SystemConfigProvider getSystemConfigProvider() {
        SystemConfigProvider systemConfigProvider = sConfigProvider;
        if (systemConfigProvider != null) {
            return systemConfigProvider;
        }
        return getNoOpProvider();
    }

    public static synchronized SystemConfigProvider getNoOpProvider() {
        if (sNoOpConfigProvider == null) {
            sNoOpConfigProvider = new NoOpSystemConfigProvider();
        }
        return sNoOpConfigProvider;
    }

    public static String getSystemLogLevel() {
        try {
            return getSystemConfigProvider().getLogLevel();
        } catch (Exception e) {
            Log.error(TAG, "Unable to retrieve system log level: " + e);
            return null;
        }
    }

    public static Map getLogLevelTagMap() {
        try {
            return getSystemConfigProvider().getLogLevelTagMap();
        } catch (Exception e) {
            Log.error(TAG, "Unable to retrieve log level map: " + e);
            return null;
        }
    }

    public static Date getUploadLogRequestDate(final String deviceId) {
        try {
            return getSystemConfigProvider().getUploadLogRequestDate(deviceId);
        } catch (Exception e) {
            Log.error(TAG, "*** Unable to retrieve upload log request date: " + e);
            return null;
        }
    }
}
