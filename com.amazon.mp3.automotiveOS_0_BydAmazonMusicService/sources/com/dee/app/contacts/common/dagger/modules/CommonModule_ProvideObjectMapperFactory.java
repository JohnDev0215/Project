package com.dee.app.contacts.common.dagger.modules;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class CommonModule_ProvideObjectMapperFactory implements Factory<ObjectMapper> {
    private final CommonModule module;

    public CommonModule_ProvideObjectMapperFactory(CommonModule commonModule) {
        this.module = commonModule;
    }

    @Override // javax.inject.Provider
    public ObjectMapper get() {
        return (ObjectMapper) Preconditions.checkNotNull(this.module.provideObjectMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ObjectMapper> create(CommonModule commonModule) {
        return new CommonModule_ProvideObjectMapperFactory(commonModule);
    }
}
