package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.ConfigStorageClient;
import com.google.firebase.remoteconfig.internal.Personalization;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public class RemoteConfigComponent {
    private static final Clock DEFAULT_CLOCK = DefaultClock.getInstance();
    private static final Random DEFAULT_RANDOM = new Random();

    @Nullable
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final String appId;
    private final Context context;

    @GuardedBy("this")
    private Map<String, String> customHeaders;
    private final Executor executor;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;

    @GuardedBy("this")
    private final Map<String, FirebaseRemoteConfig> frcNamespaceInstances;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ AnalyticsConnector lambda$getFetchHandler$0() {
        return null;
    }

    RemoteConfigComponent(Context context, @Blocking Executor executor, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider) {
        this(context, executor, firebaseApp, firebaseInstallationsApi, firebaseABTesting, provider, true);
    }

    @VisibleForTesting
    protected RemoteConfigComponent(Context context, Executor executor, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider, boolean z) {
        this.frcNamespaceInstances = new HashMap();
        this.customHeaders = new HashMap();
        this.context = context;
        this.executor = executor;
        this.firebaseApp = firebaseApp;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.firebaseAbt = firebaseABTesting;
        this.analyticsConnector = provider;
        this.appId = firebaseApp.getOptions().getApplicationId();
        if (z) {
            Tasks.call(executor, new Callable() { // from class: com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda1
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.getDefault();
                }
            });
        }
    }

    FirebaseRemoteConfig getDefault() {
        return get("firebase");
    }

    @KeepForSdk
    @VisibleForTesting
    public synchronized FirebaseRemoteConfig get(String str) {
        ConfigCacheClient cacheClient;
        ConfigCacheClient cacheClient2;
        ConfigCacheClient cacheClient3;
        ConfigMetadataClient metadataClient;
        ConfigGetParameterHandler getHandler;
        cacheClient = getCacheClient(str, "fetch");
        cacheClient2 = getCacheClient(str, "activate");
        cacheClient3 = getCacheClient(str, "defaults");
        metadataClient = getMetadataClient(this.context, this.appId, str);
        getHandler = getGetHandler(cacheClient2, cacheClient3);
        final Personalization personalization = getPersonalization(this.firebaseApp, str, this.analyticsConnector);
        if (personalization != null) {
            getHandler.addListener(new BiConsumer() { // from class: com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.common.util.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    personalization.logArmActive((String) obj, (ConfigContainer) obj2);
                }
            });
        }
        return get(this.firebaseApp, str, this.firebaseInstallations, this.firebaseAbt, this.executor, cacheClient, cacheClient2, cacheClient3, getFetchHandler(str, cacheClient, metadataClient), getHandler, metadataClient);
    }

    @VisibleForTesting
    synchronized FirebaseRemoteConfig get(FirebaseApp firebaseApp, String str, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        if (!this.frcNamespaceInstances.containsKey(str)) {
            FirebaseRemoteConfig firebaseRemoteConfig = new FirebaseRemoteConfig(this.context, firebaseApp, firebaseInstallationsApi, isAbtSupported(firebaseApp, str) ? firebaseABTesting : null, executor, configCacheClient, configCacheClient2, configCacheClient3, configFetchHandler, configGetParameterHandler, configMetadataClient);
            firebaseRemoteConfig.startLoadingConfigsFromDisk();
            this.frcNamespaceInstances.put(str, firebaseRemoteConfig);
        }
        return this.frcNamespaceInstances.get(str);
    }

    private ConfigCacheClient getCacheClient(String str, String str2) {
        return ConfigCacheClient.getInstance(this.executor, ConfigStorageClient.getInstance(this.context, String.format("%s_%s_%s_%s.json", "frc", this.appId, str, str2)));
    }

    @VisibleForTesting
    ConfigFetchHttpClient getFrcBackendApiClient(String str, String str2, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHttpClient(this.context, this.firebaseApp.getOptions().getApplicationId(), str, str2, configMetadataClient.getFetchTimeoutInSeconds(), configMetadataClient.getFetchTimeoutInSeconds());
    }

    @VisibleForTesting
    synchronized ConfigFetchHandler getFetchHandler(String str, ConfigCacheClient configCacheClient, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHandler(this.firebaseInstallations, isPrimaryApp(this.firebaseApp) ? this.analyticsConnector : new Provider() { // from class: com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda2
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return RemoteConfigComponent.lambda$getFetchHandler$0();
            }
        }, this.executor, DEFAULT_CLOCK, DEFAULT_RANDOM, configCacheClient, getFrcBackendApiClient(this.firebaseApp.getOptions().getApiKey(), str, configMetadataClient), configMetadataClient, this.customHeaders);
    }

    private ConfigGetParameterHandler getGetHandler(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        return new ConfigGetParameterHandler(this.executor, configCacheClient, configCacheClient2);
    }

    @VisibleForTesting
    static ConfigMetadataClient getMetadataClient(Context context, String str, String str2) {
        return new ConfigMetadataClient(context.getSharedPreferences(String.format("%s_%s_%s_%s", "frc", str, str2, "settings"), 0));
    }

    @Nullable
    private static Personalization getPersonalization(FirebaseApp firebaseApp, String str, Provider<AnalyticsConnector> provider) {
        if (isPrimaryApp(firebaseApp) && str.equals("firebase")) {
            return new Personalization(provider);
        }
        return null;
    }

    private static boolean isAbtSupported(FirebaseApp firebaseApp, String str) {
        return str.equals("firebase") && isPrimaryApp(firebaseApp);
    }

    private static boolean isPrimaryApp(FirebaseApp firebaseApp) {
        return firebaseApp.getName().equals("[DEFAULT]");
    }
}
