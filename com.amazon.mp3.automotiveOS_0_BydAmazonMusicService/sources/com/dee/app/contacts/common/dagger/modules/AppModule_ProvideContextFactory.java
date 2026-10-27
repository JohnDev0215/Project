package com.dee.app.contacts.common.dagger.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class AppModule_ProvideContextFactory implements Factory<Context> {
    private final AppModule module;

    public AppModule_ProvideContextFactory(AppModule appModule) {
        this.module = appModule;
    }

    @Override // javax.inject.Provider
    public Context get() {
        return (Context) Preconditions.checkNotNull(this.module.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Context> create(AppModule appModule) {
        return new AppModule_ProvideContextFactory(appModule);
    }
}
