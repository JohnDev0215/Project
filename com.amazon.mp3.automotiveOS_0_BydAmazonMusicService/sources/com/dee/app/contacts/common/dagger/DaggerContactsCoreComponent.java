package com.dee.app.contacts.common.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import com.dee.app.contacts.common.dagger.modules.AppModule;
import com.dee.app.contacts.common.dagger.modules.AppModule_ProvideActivityLifecycleUtilFactory;
import com.dee.app.contacts.common.dagger.modules.AppModule_ProvideContextFactory;
import com.dee.app.contacts.common.dagger.modules.AppModule_ProvideSharedPreferencesFactory;
import com.dee.app.contacts.common.dagger.modules.CommonModule;
import com.dee.app.contacts.common.dagger.modules.CommonModule_ProvideObjectMapperFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideAlexaCommsCoreIdentityServiceFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideAlexaCommsCoreRemoteConfigurationServiceFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideAuthenticationProviderFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideCommsNetworkingClientFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideFeatureFlagModuleFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideLoggerModuleFactory;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule_ProvideMetricsModuleFactory;
import com.dee.app.contacts.common.dagger.modules.NetworkModule;
import com.dee.app.contacts.common.dagger.modules.NetworkModule_ProvideACMSHostFactory;
import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import com.dee.app.contacts.interfaces.IContactsLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public final class DaggerContactsCoreComponent implements ContactsCoreComponent {
    private AppModule appModule;
    private Provider<String> provideACMSHostProvider;
    private Provider<ActivityLifecycleUtil> provideActivityLifecycleUtilProvider;
    private Provider<AlexaCommsCoreIdentityService> provideAlexaCommsCoreIdentityServiceProvider;
    private Provider<AlexaCommsCoreRemoteConfigurationService> provideAlexaCommsCoreRemoteConfigurationServiceProvider;
    private Provider<AuthenticationProvider> provideAuthenticationProvider;
    private Provider<INetworkingClient> provideCommsNetworkingClientProvider;
    private Provider<Context> provideContextProvider;
    private Provider<AlexaCommsCoreFeatureService> provideFeatureFlagModuleProvider;
    private Provider<IContactsLogger> provideLoggerModuleProvider;
    private Provider<AlexaCommsCoreMetricsService> provideMetricsModuleProvider;
    private Provider<ObjectMapper> provideObjectMapperProvider;
    private Provider<SharedPreferences> provideSharedPreferencesProvider;

    private DaggerContactsCoreComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private void initialize(Builder builder) {
        this.provideContextProvider = DoubleCheck.provider(AppModule_ProvideContextFactory.create(builder.appModule));
        this.provideSharedPreferencesProvider = DoubleCheck.provider(AppModule_ProvideSharedPreferencesFactory.create(builder.appModule, this.provideContextProvider));
        this.provideObjectMapperProvider = DoubleCheck.provider(CommonModule_ProvideObjectMapperFactory.create(builder.commonModule));
        this.provideAlexaCommsCoreIdentityServiceProvider = DoubleCheck.provider(CommsCoreModule_ProvideAlexaCommsCoreIdentityServiceFactory.create(builder.commsCoreModule));
        this.provideAlexaCommsCoreRemoteConfigurationServiceProvider = DoubleCheck.provider(CommsCoreModule_ProvideAlexaCommsCoreRemoteConfigurationServiceFactory.create(builder.commsCoreModule));
        this.provideMetricsModuleProvider = DoubleCheck.provider(CommsCoreModule_ProvideMetricsModuleFactory.create(builder.commsCoreModule));
        this.provideAuthenticationProvider = DoubleCheck.provider(CommsCoreModule_ProvideAuthenticationProviderFactory.create(builder.commsCoreModule, this.provideAlexaCommsCoreIdentityServiceProvider, this.provideAlexaCommsCoreRemoteConfigurationServiceProvider, this.provideMetricsModuleProvider));
        this.provideACMSHostProvider = NetworkModule_ProvideACMSHostFactory.create(builder.networkModule, this.provideAlexaCommsCoreRemoteConfigurationServiceProvider, this.provideAlexaCommsCoreIdentityServiceProvider);
        this.provideCommsNetworkingClientProvider = DoubleCheck.provider(CommsCoreModule_ProvideCommsNetworkingClientFactory.create(builder.commsCoreModule, this.provideAuthenticationProvider, this.provideACMSHostProvider));
        this.provideLoggerModuleProvider = DoubleCheck.provider(CommsCoreModule_ProvideLoggerModuleFactory.create(builder.commsCoreModule));
        this.provideFeatureFlagModuleProvider = DoubleCheck.provider(CommsCoreModule_ProvideFeatureFlagModuleFactory.create(builder.commsCoreModule));
        this.appModule = builder.appModule;
        this.provideActivityLifecycleUtilProvider = DoubleCheck.provider(AppModule_ProvideActivityLifecycleUtilFactory.create(builder.appModule));
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public SharedPreferences getSharedPreferences() {
        return this.provideSharedPreferencesProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public ObjectMapper getObjectMapper() {
        return this.provideObjectMapperProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public INetworkingClient getNetworkClient() {
        return this.provideCommsNetworkingClientProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public AlexaCommsCoreMetricsService getMetricsService() {
        return this.provideMetricsModuleProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public AlexaCommsCoreIdentityService getIdentityService() {
        return this.provideAlexaCommsCoreIdentityServiceProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public String getDeviceId() {
        return (String) Preconditions.checkNotNull(this.appModule.provideDeviceId(this.provideContextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public String getApplicationName() {
        return (String) Preconditions.checkNotNull(this.appModule.provideApplicationName(this.provideContextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public ActivityLifecycleUtil getActivityLifecycleUtil() {
        return this.provideActivityLifecycleUtilProvider.get();
    }

    @Override // com.dee.app.contacts.common.dagger.ContactsCoreComponent
    public Context getContext() {
        return this.provideContextProvider.get();
    }

    public static final class Builder {
        private AppModule appModule;
        private CommonModule commonModule;
        private CommsCoreModule commsCoreModule;
        private NetworkModule networkModule;

        private Builder() {
        }

        public ContactsCoreComponent build() {
            if (this.appModule == null) {
                throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
            }
            if (this.commonModule == null) {
                this.commonModule = new CommonModule();
            }
            if (this.commsCoreModule == null) {
                this.commsCoreModule = new CommsCoreModule();
            }
            if (this.networkModule == null) {
                this.networkModule = new NetworkModule();
            }
            return new DaggerContactsCoreComponent(this);
        }

        public Builder commsCoreModule(CommsCoreModule commsCoreModule) {
            this.commsCoreModule = (CommsCoreModule) Preconditions.checkNotNull(commsCoreModule);
            return this;
        }

        public Builder appModule(AppModule appModule) {
            this.appModule = (AppModule) Preconditions.checkNotNull(appModule);
            return this;
        }
    }
}
