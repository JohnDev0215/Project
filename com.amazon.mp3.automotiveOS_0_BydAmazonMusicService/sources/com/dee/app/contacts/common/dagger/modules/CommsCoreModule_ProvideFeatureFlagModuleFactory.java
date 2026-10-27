package com.dee.app.contacts.common.dagger.modules;

import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideFeatureFlagModuleFactory implements Factory<AlexaCommsCoreFeatureService> {
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideFeatureFlagModuleFactory(CommsCoreModule commsCoreModule) {
        this.module = commsCoreModule;
    }

    @Override // javax.inject.Provider
    public AlexaCommsCoreFeatureService get() {
        return this.module.provideFeatureFlagModule();
    }

    public static Factory<AlexaCommsCoreFeatureService> create(CommsCoreModule commsCoreModule) {
        return new CommsCoreModule_ProvideFeatureFlagModuleFactory(commsCoreModule);
    }
}
