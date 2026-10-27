package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.CommsNetworkingClient;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import com.amazon.commsnetworking.auth.MAPAuthenticationProvider;
import com.dee.app.contacts.common.constants.coremodules.Constants;
import com.dee.app.contacts.common.utils.ContactsLogger;
import com.dee.app.contacts.interfaces.IContactsLogger;

/* JADX INFO: loaded from: classes5.dex */
public class CommsCoreModule {
    private AuthenticationProvider clientAuthenticationProvider;
    private AlexaCommsCoreFeatureService clientFeatureFlagService;
    private AlexaCommsCoreIdentityService clientIdentityService;
    private AlexaCommsCoreMetricsService clientMetricsService;
    private INetworkingClient clientNetworkingClient;
    private AlexaCommsCoreRemoteConfigurationService clientRemoteConfigService;

    public CommsCoreModule(INetworkingClient iNetworkingClient, AuthenticationProvider authenticationProvider, AlexaCommsCoreFeatureService alexaCommsCoreFeatureService, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.clientNetworkingClient = iNetworkingClient;
        this.clientFeatureFlagService = alexaCommsCoreFeatureService;
        this.clientIdentityService = alexaCommsCoreIdentityService;
        this.clientMetricsService = alexaCommsCoreMetricsService;
        this.clientRemoteConfigService = alexaCommsCoreRemoteConfigurationService;
        this.clientAuthenticationProvider = authenticationProvider;
    }

    public CommsCoreModule() {
    }

    public AlexaCommsCoreMetricsService provideMetricsModule() {
        return this.clientMetricsService;
    }

    public IContactsLogger provideLoggerModule() {
        return new ContactsLogger();
    }

    public AlexaCommsCoreFeatureService provideFeatureFlagModule() {
        return this.clientFeatureFlagService;
    }

    public AlexaCommsCoreIdentityService provideAlexaCommsCoreIdentityService() {
        return this.clientIdentityService;
    }

    public AlexaCommsCoreRemoteConfigurationService provideAlexaCommsCoreRemoteConfigurationService() {
        return this.clientRemoteConfigService;
    }

    public AuthenticationProvider provideAuthenticationProvider(AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        AuthenticationProvider authenticationProvider = this.clientAuthenticationProvider;
        return authenticationProvider != null ? authenticationProvider : new MAPAuthenticationProvider("amazon-contacts", alexaCommsCoreIdentityService, alexaCommsCoreRemoteConfigurationService, alexaCommsCoreMetricsService);
    }

    public INetworkingClient provideCommsNetworkingClient(AuthenticationProvider authenticationProvider, String str) {
        INetworkingClient iNetworkingClient = this.clientNetworkingClient;
        return iNetworkingClient != null ? iNetworkingClient : new CommsNetworkingClient.Builder().withSource("amazon-contacts").withReadTimeout(Constants.READ_TIMEOUT.intValue()).withWriteTimeout(Constants.WRITE_TIMEOUT.intValue()).withAuthProvider(authenticationProvider).withHost(str).build();
    }
}
