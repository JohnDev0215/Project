package com.amazon.minerva.client.thirdparty.configuration;

import android.content.Context;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class ConfigurationManager {
    private static final String TAG = "ConfigurationManager";
    private static int syncUpConfigurationSeconds = 86400;
    private AssetFileParser mAssetFileParser;
    private Context mContext;
    private RemoteConfigurationManager mCustomRemoteConfigurationManager;
    private CustomRemoteMetricsConfigurationUpdater mCustomRemoteMetricsConfigurationUpdater;
    private RemoteConfigurationManager mDefaultRemoteConfigurationManager;
    private DefaultRemoteMetricsConfigurationUpdater mDefaultRemoteMetricsConfigurationUpdater;
    private MetricsConfigurationHelper mMetricsConfigurationHelper;
    private ScheduledExecutorService mScheduler;

    public ConfigurationManager(Context context) {
        if (context == null) {
            Log.e(TAG, "Context cannot be null.");
            return;
        }
        this.mContext = context;
        this.mAssetFileParser = new AssetFileParser(context);
        this.mMetricsConfigurationHelper = new MetricsConfigurationHelper(context, this.mAssetFileParser);
        this.mDefaultRemoteMetricsConfigurationUpdater = new DefaultRemoteMetricsConfigurationUpdater(this.mMetricsConfigurationHelper, this.mAssetFileParser);
        this.mCustomRemoteMetricsConfigurationUpdater = new CustomRemoteMetricsConfigurationUpdater(this.mMetricsConfigurationHelper, this.mAssetFileParser);
    }

    public void initializeWithArcus() {
        if (CustomDeviceUtil.getInstance().getAppConfigId() != null) {
            Log.i(TAG, "initialize custom Arcus and schedule periodic sync. ");
            initializeWithCustomArcus();
        }
        Log.i(TAG, "initialize default Arcus and schedule periodic sync ");
        initializeWithDefaultArcus();
    }

    private void initializeWithCustomArcus() {
        this.mCustomRemoteConfigurationManager = RemoteConfigurationManager.forAppId(this.mContext, CustomDeviceUtil.getInstance().getAppConfigId(), true).createOrGet();
        Runnable runnable = new Runnable() { // from class: com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager.1
            @Override // java.lang.Runnable
            public void run() {
                Log.i(ConfigurationManager.TAG, "periodically sync Custom Arcus App config");
                ConfigurationManager.this.mCustomRemoteConfigurationManager.sync(ConfigurationManager.this.mCustomRemoteMetricsConfigurationUpdater);
            }
        };
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(runnable, 0L, syncUpConfigurationSeconds, TimeUnit.SECONDS);
    }

    private void initializeWithDefaultArcus() {
        this.mDefaultRemoteConfigurationManager = RemoteConfigurationManager.forAppId(this.mContext, "arn:aws:remote-config:us-west-2:455205533140:appConfig:apiz0o87", true).createOrGet();
        Runnable runnable = new Runnable() { // from class: com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager.2
            @Override // java.lang.Runnable
            public void run() {
                Log.i(ConfigurationManager.TAG, "periodically sync default Arcus App config");
                ConfigurationManager.this.mDefaultRemoteConfigurationManager.sync(ConfigurationManager.this.mDefaultRemoteMetricsConfigurationUpdater);
            }
        };
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.mScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(runnable, 0L, syncUpConfigurationSeconds, TimeUnit.SECONDS);
    }

    public MetricsConfigurationHelper getMetricsConfigurationHelper() {
        return this.mMetricsConfigurationHelper;
    }
}
