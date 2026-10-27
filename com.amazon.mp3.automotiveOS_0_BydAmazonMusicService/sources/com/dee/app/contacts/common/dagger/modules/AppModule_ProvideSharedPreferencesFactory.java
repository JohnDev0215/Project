package com.dee.app.contacts.common.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes5.dex */
public final class AppModule_ProvideSharedPreferencesFactory implements Factory<SharedPreferences> {
    private final Provider<Context> contextProvider;
    private final AppModule module;

    public AppModule_ProvideSharedPreferencesFactory(AppModule appModule, Provider<Context> provider) {
        this.module = appModule;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public SharedPreferences get() {
        return (SharedPreferences) Preconditions.checkNotNull(this.module.provideSharedPreferences(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SharedPreferences> create(AppModule appModule, Provider<Context> provider) {
        return new AppModule_ProvideSharedPreferencesFactory(appModule, provider);
    }
}
