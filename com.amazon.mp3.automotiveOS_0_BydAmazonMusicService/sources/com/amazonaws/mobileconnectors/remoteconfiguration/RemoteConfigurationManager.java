package com.amazonaws.mobileconnectors.remoteconfiguration;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.MalformedAppConfigIdException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ArcusThrottler;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.Arn;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.AttributesImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationDb;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.ConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.LocalConfigInstanceIdGenerationHelper;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.AndroidRemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RemoteConfigurationFetcher;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.net.RequestThrottledException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class RemoteConfigurationManager {
    private static final String DEFAULT_ENDPOINT = "https://arcus-uswest.amazon.com";
    private static final String KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID = "localConfigurationInstanceId";
    private static final String REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX = "configuration.prefs";
    private static final String TAG = "RemoteConfigurationManager";
    private final String mAppConfigId;
    private final Attributes mAttributes;
    private final ConfigurationDb mConfigurationDb;
    private int mLastSuccessfulSyncAttributeHash;
    private final RemoteConfigurationFetcher mRemoteConfigurationFetcher;
    private final SharedPreferences mSharedPreferences;
    private final ArcusThrottler mThrottler;

    public static class Builder {
        protected static final ConcurrentHashMap<String, RemoteConfigurationManager> MANAGERS = new ConcurrentHashMap<>();
        protected final String mAppConfigId;
        protected final Context mContext;
        private boolean mCustomContext;
        protected JSONObject mDefaultConfiguration;

        public Builder(Context context, String str) {
            this.mDefaultConfiguration = new JSONObject();
            this.mCustomContext = false;
            if (context == null) {
                throw new NullPointerException("The Context may not be null");
            }
            if (str != null) {
                RemoteConfigurationManager.verifyAppConfigId(str);
                this.mContext = context;
                this.mAppConfigId = str;
                return;
            }
            throw new NullPointerException("The App Configuration ID may not be null");
        }

        public Builder(Context context, String str, boolean z) {
            this(context, str);
            this.mCustomContext = z;
        }

        public Builder withDefaultConfiguration(JSONObject jSONObject) {
            if (jSONObject == null) {
                throw new NullPointerException("The default configuration may not be null");
            }
            this.mDefaultConfiguration = jSONObject;
            return this;
        }

        public RemoteConfigurationManager createOrGet() {
            String str = this.mAppConfigId;
            if (str == null) {
                throw new IllegalStateException("An App Configuration ID must be specified");
            }
            ConcurrentHashMap<String, RemoteConfigurationManager> concurrentHashMap = MANAGERS;
            if (!concurrentHashMap.containsKey(str)) {
                ensureBuilderReady();
                concurrentHashMap.putIfAbsent(this.mAppConfigId, new RemoteConfigurationManager(this));
            }
            return concurrentHashMap.get(this.mAppConfigId);
        }

        protected void ensureBuilderReady() {
            if (this.mContext == null) {
                throw new IllegalStateException("The Context may not be null");
            }
            if (this.mAppConfigId == null) {
                throw new IllegalStateException("The App Configuration ID may not be null");
            }
            if (this.mDefaultConfiguration == null) {
                throw new IllegalStateException("The default configuration may not be null");
            }
        }
    }

    private RemoteConfigurationManager(Builder builder) {
        this(builder.mContext, builder.mAppConfigId, builder.mDefaultConfiguration, builder.mCustomContext);
    }

    RemoteConfigurationManager(Context context, String str, JSONObject jSONObject) {
        this(context, str, jSONObject, ConfigurationDb.getOrCreateInstance(context, str), DEFAULT_ENDPOINT, false);
    }

    RemoteConfigurationManager(Context context, String str, JSONObject jSONObject, boolean z) {
        this(context, str, jSONObject, ConfigurationDb.getOrCreateInstance(context, str), DEFAULT_ENDPOINT, z);
    }

    RemoteConfigurationManager(Context context, String str, JSONObject jSONObject, ConfigurationDb configurationDb, String str2, boolean z) {
        this.mThrottler = new ArcusThrottler();
        Checks.checkNotNull(context, "appContext cannot be null");
        Checks.checkNotNull(str, "appConfigId cannot be null");
        verifyAppConfigId(str);
        context = z ? context : context.getApplicationContext();
        try {
            URL url = new URL(str2);
            this.mAppConfigId = str;
            this.mSharedPreferences = context.getSharedPreferences(getPreferencesFileNameForAppConfig(), 0);
            AttributesImpl attributesImpl = new AttributesImpl(context);
            this.mAttributes = attributesImpl;
            this.mLastSuccessfulSyncAttributeHash = attributesImpl.hashCode();
            this.mConfigurationDb = configurationDb;
            this.mRemoteConfigurationFetcher = new AndroidRemoteConfigurationFetcher(context, url);
            if (jSONObject != null) {
                RemoteConfiguration remoteConfiguration = configurationDb.readRemoteConfiguration(str);
                if (remoteConfiguration == null || remoteConfiguration.getOrigin() == 1) {
                    Log.d(TAG, "Saving default configuration");
                    configurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), str, 1, null, false));
                } else {
                    Log.d(TAG, "Skipping default configuration saving");
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid endpoint", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void verifyAppConfigId(String str) {
        try {
            Arn.fromArn(str);
        } catch (IllegalArgumentException e) {
            throw new MalformedAppConfigIdException("Invalid appConfigId ARN.", e);
        }
    }

    public static Builder forAppId(Context context, String str) {
        return new Builder(context, str);
    }

    public static Builder forAppId(Context context, String str, boolean z) {
        return new Builder(context, str, z);
    }

    public static RemoteConfigurationManager getInstance(String str) {
        Checks.checkNotNull(str, "The App Configuration ID may not be null");
        return Builder.MANAGERS.get(str);
    }

    public Configuration openConfiguration() {
        return this.mConfigurationDb.readConfiguration();
    }

    public void sync(ConfigurationSyncCallback configurationSyncCallback) {
        Checks.checkNotNull(configurationSyncCallback, "ConfigurationSyncCallback cannot be null");
        validateAndSyncOnNewThread(configurationSyncCallback);
    }

    private void validateAndSyncOnNewThread(final ConfigurationSyncCallback configurationSyncCallback) {
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        executorServiceNewSingleThreadExecutor.submit(new Runnable() { // from class: com.amazonaws.mobileconnectors.remoteconfiguration.RemoteConfigurationManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$validateAndSyncOnNewThread$0(configurationSyncCallback);
            }
        });
        executorServiceNewSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$validateAndSyncOnNewThread$0(ConfigurationSyncCallback configurationSyncCallback) {
        syncOnCurrentThread(getAndValidateConfiguration(), configurationSyncCallback);
    }

    private RemoteConfiguration getAndValidateConfiguration() {
        ConfigurationDb configurationDb = this.mConfigurationDb;
        if (configurationDb == null) {
            return null;
        }
        try {
            RemoteConfiguration remoteConfiguration = configurationDb.readRemoteConfiguration(this.mAppConfigId);
            if (remoteConfiguration == null) {
                return null;
            }
            new JSONObject(remoteConfiguration.getConfiguration().getAsJsonString());
            return remoteConfiguration;
        } catch (ConfigurationNotFoundException unused) {
            Log.d(TAG, "Configuration was not found in local database.");
            return null;
        } catch (JSONException unused2) {
            Log.w(TAG, "Removing corrupted configuration from local database.");
            this.mConfigurationDb.deleteConfiguration();
            return null;
        }
    }

    private synchronized void syncOnCurrentThread(RemoteConfiguration remoteConfiguration, ConfigurationSyncCallback configurationSyncCallback) {
        Attributes attributesM2501clone = this.mAttributes.m2501clone();
        if (!this.mThrottler.isSyncAllowedRightNow() && (this.mThrottler.getCause() != 10 || this.mLastSuccessfulSyncAttributeHash == attributesM2501clone.hashCode())) {
            configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
            return;
        }
        try {
            RemoteConfiguration remoteConfigurationFetch = this.mRemoteConfigurationFetcher.fetch(this.mAppConfigId, attributesM2501clone, remoteConfiguration != null ? remoteConfiguration.getEntityTag() : null, getOrGenerateLocalConfigurationInstanceId());
            this.mLastSuccessfulSyncAttributeHash = attributesM2501clone.hashCode();
            this.mThrottler.updateSyncTimeAfterSuccess();
            if (remoteConfigurationFetch.isUpdate()) {
                this.mConfigurationDb.saveConfiguration(remoteConfigurationFetch);
                configurationSyncCallback.onConfigurationModified(remoteConfigurationFetch.getConfiguration());
            } else {
                RemoteConfigurationImpl remoteConfigurationImpl = new RemoteConfigurationImpl(new ConfigurationImpl(remoteConfiguration.getConfiguration().getAsJsonString(), new Date()), remoteConfiguration.getAppConfigurationId(), remoteConfiguration.getOrigin(), remoteConfiguration.getEntityTag(), false);
                this.mConfigurationDb.saveConfiguration(remoteConfigurationImpl);
                configurationSyncCallback.onConfigurationUnmodified(remoteConfigurationImpl.getConfiguration());
            }
        } catch (RequestThrottledException unused) {
            this.mThrottler.updateSyncTimeAfterThrottle(0L);
            configurationSyncCallback.onThrottle(this.mThrottler.getTimeToNextSyncInMS());
        } catch (Exception e) {
            this.mThrottler.updateSyncTimeAfterFailure();
            configurationSyncCallback.onFailure(e);
        }
    }

    public void overwriteConfiguration(JSONObject jSONObject) {
        Checks.checkNotNull(jSONObject, "The Configuration cannot be null");
        this.mConfigurationDb.saveConfiguration(new RemoteConfigurationImpl(new ConfigurationImpl(jSONObject.toString(), new Date()), this.mAppConfigId, 3, null, false));
    }

    private String getOrGenerateLocalConfigurationInstanceId() {
        String string = this.mSharedPreferences.getString(KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID, null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String strGenerateLocalConfigInstanceId = LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(UUID.randomUUID().toString());
        setLocalConfigurationInstanceId(strGenerateLocalConfigInstanceId);
        return strGenerateLocalConfigInstanceId;
    }

    private String getPreferencesFileNameForAppConfig() {
        return this.mAppConfigId + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + REMOTE_CONFIG_SHARED_PREF_FILE_SUFFIX;
    }

    public Attributes openAttributes() {
        return this.mAttributes;
    }

    private void setLocalConfigurationInstanceId(String str) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString(KEY_SHARED_PREF_LOCAL_CONFIG_INSTANCE_ID, str);
        editorEdit.apply();
    }

    public void setRampingKey(String str) {
        if (str == null) {
            throw new NullPointerException("The ramping key cannot be null.");
        }
        if (str.isEmpty() || str.length() > 64) {
            throw new IllegalArgumentException("The ramping key cannot be empty and its length is limited to 64 characters.");
        }
        setLocalConfigurationInstanceId(LocalConfigInstanceIdGenerationHelper.generateLocalConfigInstanceId(str));
    }
}
