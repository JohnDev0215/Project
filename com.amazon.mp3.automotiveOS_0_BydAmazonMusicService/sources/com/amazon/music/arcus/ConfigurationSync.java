package com.amazon.music.arcus;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigurationSync implements ConfigurationSyncCallback {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationSync.class.getSimpleName());
    private final ConfigAttributesManager attributesManager;
    private ConfigRefreshCallback configRefreshCallback;
    private final ConfigRefreshGateResolver refreshGateResolver;
    private final RemoteConfigurationManager remoteConfigurationManager;
    private final ConfigThresholdManager thresholdManager;
    private final ConfigThrottleManager throttleManager;

    public ConfigurationSync(RemoteConfigurationManager remoteConfigurationManager, ConfigAttributesManager configAttributesManager, ConfigThrottleManager configThrottleManager, ConfigThresholdManager configThresholdManager, ConfigRefreshGateResolver configRefreshGateResolver) {
        this.remoteConfigurationManager = (RemoteConfigurationManager) Validate.notNull(remoteConfigurationManager);
        this.attributesManager = (ConfigAttributesManager) Validate.notNull(configAttributesManager);
        this.throttleManager = (ConfigThrottleManager) Validate.notNull(configThrottleManager);
        this.thresholdManager = (ConfigThresholdManager) Validate.notNull(configThresholdManager);
        this.refreshGateResolver = (ConfigRefreshGateResolver) Validate.notNull(configRefreshGateResolver);
    }

    public void setConfigRefreshCallback(ConfigRefreshCallback configRefreshCallback) {
        this.configRefreshCallback = configRefreshCallback;
    }

    public void refreshConfig() {
        if (this.refreshGateResolver.isCurrentlyRefreshing()) {
            return;
        }
        this.refreshGateResolver.setCurrentlyRefreshing(true);
        this.remoteConfigurationManager.sync(this);
        LOG.debug("Begin attempt to sync RemoteConfigurationManager.");
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onFailure(Exception exc) {
        LOG.error("Attempt to sync RemoteConfigurationManager failed.", (Throwable) exc);
        if (this.configRefreshCallback != null) {
            Configuration configurationOpenConfiguration = null;
            try {
                configurationOpenConfiguration = this.remoteConfigurationManager.openConfiguration();
            } catch (Exception e) {
                LOG.warn("Unable to fetch cached configuration from disk, likely because it doesn't exist yet.", (Throwable) e);
            }
            this.configRefreshCallback.onConfigurationUpdateFailed(exc, configurationOpenConfiguration);
        }
        this.refreshGateResolver.setCurrentlyRefreshing(false);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onThrottle(long j) {
        if (this.refreshGateResolver.shouldConfigBeRefreshed()) {
            LOG.info("Attempt to sync RemoteConfigurationManager throttled; refresh is needed so waiting to try again.");
            this.throttleManager.setIsThrottled(true);
            try {
                try {
                    Thread.sleep(j);
                    refreshConfig();
                } catch (InterruptedException e) {
                    LOG.error("Attempt to sync RemoteConfigurationManager was interrupted.", (Throwable) e);
                }
            } finally {
                this.throttleManager.setIsThrottled(false);
            }
        } else {
            LOG.debug("Attempt to sync RemoteConfigurationManager throttled; refresh not needed so additional actions were not taken.");
        }
        this.refreshGateResolver.setCurrentlyRefreshing(false);
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationModified(Configuration configuration) {
        ConfigRefreshCallback configRefreshCallback = this.configRefreshCallback;
        if (configRefreshCallback != null) {
            configRefreshCallback.onConfigurationUpdated(configuration, true);
        }
        this.thresholdManager.onConfigRefreshed(configuration, true);
        this.attributesManager.onConfigRefreshed();
        this.refreshGateResolver.setCurrentlyRefreshing(false);
        LOG.debug("Attempt to sync RemoteConfigurationManager was successful; configuration was modified.");
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.ConfigurationSyncCallback
    public void onConfigurationUnmodified(Configuration configuration) {
        ConfigRefreshCallback configRefreshCallback = this.configRefreshCallback;
        if (configRefreshCallback != null) {
            configRefreshCallback.onConfigurationUpdated(configuration, false);
        }
        this.thresholdManager.onConfigRefreshed(configuration, false);
        this.attributesManager.onConfigRefreshed();
        this.refreshGateResolver.setCurrentlyRefreshing(false);
        LOG.debug("Attempt to sync RemoteConfigurationManager was successful; configuration was unmodified.");
    }
}
