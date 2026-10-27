package com.amazon.music.arcus;

import android.content.Context;
import android.os.Handler;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ConfigurationManager implements ConfigRefreshCallback {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class.getSimpleName());
    private String appConfigId;
    private ConfigAttributesManager attributesManager;
    private volatile Configuration currentConfiguration;
    private Handler handler;
    private final CountDownLatch latch;
    private RemoteConfigurationManagerFactory managerFactory;
    private ConfigRefreshGateResolver refreshGateResolver;
    private ConfigThresholdManager thresholdManager;
    private ConfigThrottleManager throttleManager;

    public ConfigurationManager(Context context, Map<String, Object> map, String str) {
        this(context, map, str, ThreadUtils.getBackgroundHandler(), new RemoteConfigurationManagerFactory(), new ConfigAttributesManager(), new ConfigThrottleManager(), new ConfigThresholdManager(context, str));
    }

    ConfigurationManager(Context context, Map<String, Object> map, String str, Handler handler, RemoteConfigurationManagerFactory remoteConfigurationManagerFactory, ConfigAttributesManager configAttributesManager, ConfigThrottleManager configThrottleManager, ConfigThresholdManager configThresholdManager) {
        this(context, map, str, handler, remoteConfigurationManagerFactory, configAttributesManager, configThrottleManager, configThresholdManager, new ConfigRefreshGateResolver(new HashSet(Arrays.asList(configAttributesManager, configThrottleManager, configThresholdManager))));
    }

    ConfigurationManager(Context context, Map<String, Object> map, String str, Handler handler, RemoteConfigurationManagerFactory remoteConfigurationManagerFactory, ConfigAttributesManager configAttributesManager, ConfigThrottleManager configThrottleManager, ConfigThresholdManager configThresholdManager, ConfigRefreshGateResolver configRefreshGateResolver) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.latch = countDownLatch;
        this.appConfigId = (String) Validate.notNull(str);
        this.handler = (Handler) Validate.notNull(handler);
        this.attributesManager = (ConfigAttributesManager) Validate.notNull(configAttributesManager);
        this.throttleManager = (ConfigThrottleManager) Validate.notNull(configThrottleManager);
        this.thresholdManager = (ConfigThresholdManager) Validate.notNull(configThresholdManager);
        this.refreshGateResolver = (ConfigRefreshGateResolver) Validate.notNull(configRefreshGateResolver);
        RemoteConfigurationManagerFactory remoteConfigurationManagerFactory2 = (RemoteConfigurationManagerFactory) Validate.notNull(remoteConfigurationManagerFactory);
        this.managerFactory = remoteConfigurationManagerFactory2;
        RemoteConfigurationManager remoteConfigurationManagerCreateAndGet = remoteConfigurationManagerFactory2.createAndGet(context, str);
        updateAttributes(remoteConfigurationManagerCreateAndGet, map);
        ConfigurationSync configurationSync = new ConfigurationSync(remoteConfigurationManagerCreateAndGet, configAttributesManager, configThrottleManager, configThresholdManager, configRefreshGateResolver);
        configurationSync.setConfigRefreshCallback(this);
        configurationSync.refreshConfig();
        try {
            if (!countDownLatch.await(5L, TimeUnit.SECONDS)) {
                LOG.debug("The CountDownLatch timed out. The data returned from Arcus might be stale");
                this.currentConfiguration = remoteConfigurationManagerCreateAndGet.openConfiguration();
            }
        } catch (InterruptedException e) {
            LOG.debug("The CountDownLatch has been interrupted. The data returned from Arcus might be stale.", (Throwable) e);
            this.currentConfiguration = remoteConfigurationManagerCreateAndGet.openConfiguration();
        }
        LOG.debug("Initialization of RemoteConfigurationManager succeeded.");
    }

    public <T> T getConfiguration(Map<String, Object> map, String str, Class<T> cls) throws ArcusConfigurationUnavailableException {
        Validate.notNull(map);
        Validate.notBlank(str);
        Validate.notNull(cls);
        RemoteConfigurationManager remoteConfigurationManager = this.managerFactory.get(this.appConfigId);
        updateAttributes(remoteConfigurationManager, map);
        if (this.refreshGateResolver.shouldConfigBeRefreshed()) {
            LOG.debug("Configuration needs to be refreshed.");
            ConfigurationSync configurationSync = new ConfigurationSync(remoteConfigurationManager, this.attributesManager, this.throttleManager, this.thresholdManager, this.refreshGateResolver);
            configurationSync.setConfigRefreshCallback(this);
            this.handler.post(new ConfigurationSyncRunnable(configurationSync));
        }
        Configuration currentConfiguration = getCurrentConfiguration();
        if (currentConfiguration != null) {
            return (T) get(currentConfiguration.getAsJsonObject(), cls, str);
        }
        LOG.error("Locally cached configuration was null.");
        throw new ArcusConfigurationUnavailableException("Locally cached configuration was null.");
    }

    void setCurrentConfiguration(Configuration configuration) {
        this.currentConfiguration = configuration;
    }

    Configuration getCurrentConfiguration() {
        return this.currentConfiguration;
    }

    private void updateAttributes(RemoteConfigurationManager remoteConfigurationManager, Map<String, Object> map) {
        Validate.notNull(remoteConfigurationManager);
        Validate.notNull(map);
        this.attributesManager.updateManagerAttributes(remoteConfigurationManager, map);
    }

    @Override // com.amazon.music.arcus.ConfigRefreshCallback
    public void onConfigurationUpdated(Configuration configuration, boolean z) {
        if (z || getCurrentConfiguration() == null) {
            setCurrentConfiguration(configuration);
            LOG.debug("Modified configuration from sync stored locally.");
        }
        this.latch.countDown();
    }

    @Override // com.amazon.music.arcus.ConfigRefreshCallback
    public void onConfigurationUpdateFailed(Exception exc, Configuration configuration) {
        if (getCurrentConfiguration() == null && configuration != null) {
            setCurrentConfiguration(configuration);
            LOG.debug("Cached configuration stored locally because local configuration was null.");
        }
        this.latch.countDown();
    }

    private <T> T get(JSONObject jSONObject, Class<T> cls, String str) throws ArcusConfigurationUnavailableException {
        Object jSONArray = null;
        try {
            if (cls == String.class) {
                jSONArray = jSONObject.getString(str);
            } else if (cls == Integer.class) {
                jSONArray = Integer.valueOf(jSONObject.getInt(str));
            } else if (cls == Long.class) {
                jSONArray = Long.valueOf(jSONObject.getLong(str));
            } else if (cls == Double.class) {
                jSONArray = Double.valueOf(jSONObject.getDouble(str));
            } else if (cls == Boolean.class) {
                jSONArray = Boolean.valueOf(jSONObject.getBoolean(str));
            } else if (cls == JSONObject.class) {
                jSONArray = jSONObject.getJSONObject(str);
            } else if (cls == JSONArray.class) {
                jSONArray = jSONObject.getJSONArray(str);
            }
            if (jSONArray == null) {
                String str2 = String.format("Retrieved value for key %s was null", str);
                LOG.error(str2);
                throw new ArcusConfigurationUnavailableException(str2);
            }
            if (!cls.isAssignableFrom(jSONArray.getClass())) {
                String str3 = String.format("Retrieved value %s cannot be casted as type %s", jSONArray, cls);
                LOG.error(str3);
                throw new ArcusConfigurationUnavailableException(str3);
            }
            return cls.cast(jSONArray);
        } catch (JSONException e) {
            String str4 = String.format("Error retrieving key %s in the JSON object", str);
            LOG.debug(str4, (Throwable) e);
            throw new ArcusConfigurationUnavailableException(str4, e);
        }
    }
}
