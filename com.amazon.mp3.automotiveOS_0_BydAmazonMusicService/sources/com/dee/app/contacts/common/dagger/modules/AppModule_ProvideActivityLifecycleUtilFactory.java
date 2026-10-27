package com.dee.app.contacts.common.dagger.modules;

import com.dee.app.contacts.common.utils.ActivityLifecycleUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public final class AppModule_ProvideActivityLifecycleUtilFactory implements Factory<ActivityLifecycleUtil> {
    private final AppModule module;

    public AppModule_ProvideActivityLifecycleUtilFactory(AppModule appModule) {
        this.module = appModule;
    }

    @Override // javax.inject.Provider
    public ActivityLifecycleUtil get() {
        return (ActivityLifecycleUtil) Preconditions.checkNotNull(this.module.provideActivityLifecycleUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ActivityLifecycleUtil> create(AppModule appModule) {
        return new AppModule_ProvideActivityLifecycleUtilFactory(appModule);
    }
}
