package com.dee.app.contacts.core;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.api.INetworkingClient;
import com.amazon.commsnetworking.auth.AuthenticationProvider;
import com.dee.app.contacts.common.dagger.ContactsCoreComponent;
import com.dee.app.contacts.common.dagger.ContactsCoreDaggerWrapper;
import com.dee.app.contacts.common.dagger.modules.CommsCoreModule;
import com.dee.app.contacts.common.exceptions.InvalidInputException;
import dagger.internal.Preconditions;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class ContactsDaggerWrapper {
    private static ContactsCoreComponent contactsLibrary;

    private ContactsDaggerWrapper() {
    }

    public static ContactsCoreComponent initialise(@NonNull Context context, INetworkingClient iNetworkingClient, AuthenticationProvider authenticationProvider, AlexaCommsCoreMetricsService alexaCommsCoreMetricsService, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, AlexaCommsCoreFeatureService alexaCommsCoreFeatureService, AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService) throws InvalidInputException {
        Log.i("TAG", "Initializing core components");
        if (authenticationProvider == null) {
            validateIdentityService(alexaCommsCoreIdentityService);
            Preconditions.checkNotNull(alexaCommsCoreRemoteConfigurationService);
        }
        ContactsCoreComponent contactsCoreComponent = ContactsCoreDaggerWrapper.getContactsCoreComponent(new CommsCoreModule(iNetworkingClient, authenticationProvider, alexaCommsCoreFeatureService, alexaCommsCoreIdentityService, alexaCommsCoreRemoteConfigurationService, alexaCommsCoreMetricsService), context);
        contactsLibrary = contactsCoreComponent;
        return contactsCoreComponent;
    }

    public static ContactsCoreComponent initialise(@NonNull Context context, INetworkingClient iNetworkingClient, AuthenticationProvider authenticationProvider, AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) throws InvalidInputException {
        if (authenticationProvider == null) {
            throw new InvalidInputException("IdentityService and RemoteConfigService cannot be null when AuthenticationProvider is null");
        }
        return initialise(context, iNetworkingClient, authenticationProvider, alexaCommsCoreMetricsService, null, null, null);
    }

    private static void validateIdentityService(AlexaCommsCoreIdentityService alexaCommsCoreIdentityService) throws InvalidInputException {
        if (alexaCommsCoreIdentityService == null || alexaCommsCoreIdentityService.getIdentity() == null || StringUtils.isBlank(alexaCommsCoreIdentityService.getIdentity().getHomegroupId()) || StringUtils.isBlank(alexaCommsCoreIdentityService.getIdentity().getCommsId()) || StringUtils.isBlank(alexaCommsCoreIdentityService.getIdentity().getDirectedId())) {
            throw new InvalidInputException("Error while fetching AlexaCommsCoreIdentityService; commsId, homegroupId and directedId must be populated in CommsCoreIdentity");
        }
    }
}
