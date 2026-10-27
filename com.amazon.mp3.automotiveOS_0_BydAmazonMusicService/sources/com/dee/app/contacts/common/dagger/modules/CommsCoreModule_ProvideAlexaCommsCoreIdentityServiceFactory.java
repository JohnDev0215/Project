package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideAlexaCommsCoreIdentityServiceFactory implements Factory<AlexaCommsCoreIdentityService> {
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideAlexaCommsCoreIdentityServiceFactory(CommsCoreModule commsCoreModule) {
        this.module = commsCoreModule;
    }

    @Override // javax.inject.Provider
    public AlexaCommsCoreIdentityService get() {
        return this.module.provideAlexaCommsCoreIdentityService();
    }

    public static Factory<AlexaCommsCoreIdentityService> create(CommsCoreModule commsCoreModule) {
        return new CommsCoreModule_ProvideAlexaCommsCoreIdentityServiceFactory(commsCoreModule);
    }
}
