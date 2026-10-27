package com.dee.app.contacts.common.dagger.modules;

import com.dee.app.contacts.interfaces.IContactsLogger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class CommsCoreModule_ProvideLoggerModuleFactory implements Factory<IContactsLogger> {
    private final CommsCoreModule module;

    public CommsCoreModule_ProvideLoggerModuleFactory(CommsCoreModule commsCoreModule) {
        this.module = commsCoreModule;
    }

    @Override // javax.inject.Provider
    public IContactsLogger get() {
        return (IContactsLogger) Preconditions.checkNotNull(this.module.provideLoggerModule(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<IContactsLogger> create(CommsCoreModule commsCoreModule) {
        return new CommsCoreModule_ProvideLoggerModuleFactory(commsCoreModule);
    }
}
