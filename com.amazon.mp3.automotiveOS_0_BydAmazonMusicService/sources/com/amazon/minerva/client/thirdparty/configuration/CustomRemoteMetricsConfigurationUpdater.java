package com.amazon.minerva.client.thirdparty.configuration;

import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import org.json.JSONException;

/* JADX INFO: loaded from: classes3.dex */
public class CustomRemoteMetricsConfigurationUpdater implements ConfigurationSyncCallback {
    private static final String TAG = "CustomRemoteMetricsConfigurationUpdater";
    private AssetFileParser mAssetFileParser;
    private MetricsConfigurationHelper mMetricsConfigurationHelper;

    public CustomRemoteMetricsConfigurationUpdater(MetricsConfigurationHelper metricsConfigurationHelper, AssetFileParser assetFileParser) {
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mAssetFileParser = assetFileParser;
    }

    private void setCustomConfigurations(Configuration configuration) {
        try {
            this.mMetricsConfigurationHelper.setCustomMetricsConfiguration(this.mAssetFileParser.getDomainMetricsConfiguration(configuration.getAsJsonObject()));
        } catch (JSONException e) {
            Log.e(TAG, "An error occurs when retrieving domain configuration.", e);
        }
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationModified(Configuration configuration) {
        setCustomConfigurations(configuration);
        Log.i(TAG, "Customer's Remote configuration is changed, and update local metric configurations.");
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationUnmodified(Configuration configuration) {
        setCustomConfigurations(configuration);
        Log.i(TAG, "Customer's  Remote configuration is unchanged, but still update local metric configurations to ensure the match.");
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onFailure(Exception exc) {
        Log.e(TAG, "Customer's  Remote configuration sync failed, and wait for next cycle.", exc);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onThrottle(long j) {
        Log.w(TAG, String.format("Customer's  Remote configuration sync was throttled, and can retry in %d minutes.", Long.valueOf((j / 1000) / 60)));
    }
}
