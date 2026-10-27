package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.dee.app.contacts.common.constants.coremodules.RemoteConfig;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkModule {
    String provideClientId() {
        return "amazon-contacts";
    }

    String provideACMSHost(AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, AlexaCommsCoreIdentityService alexaCommsCoreIdentityService) {
        String pfmCode;
        try {
            pfmCode = alexaCommsCoreIdentityService.getIdentity().getMarketplaceInfo().getPfmCode();
        } catch (Exception unused) {
            pfmCode = "Default";
        }
        RemoteConfig remoteConfig = RemoteConfig.ACMS_HOST;
        String str = String.format("%s.%s", remoteConfig.getKey(), pfmCode);
        String defaultValue = remoteConfig.getDefaultValue();
        try {
            return alexaCommsCoreRemoteConfigurationService.getRemoteConfiguration(str, defaultValue).toString();
        } catch (Exception unused2) {
            return defaultValue;
        }
    }
}
