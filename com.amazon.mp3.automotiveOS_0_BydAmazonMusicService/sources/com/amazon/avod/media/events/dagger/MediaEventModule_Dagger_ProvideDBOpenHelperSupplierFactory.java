package com.amazon.avod.media.events.dagger;

import android.content.Context;
import com.amazon.avod.db.DBOpenHelperSupplier;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideDBOpenHelperSupplierFactory implements Factory<DBOpenHelperSupplier> {
    private final Provider<Context> contextProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideDBOpenHelperSupplierFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<Context> provider) {
        this.module = mediaEventModule_Dagger;
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public DBOpenHelperSupplier get() {
        return (DBOpenHelperSupplier) Preconditions.checkNotNull(this.module.provideDBOpenHelperSupplier(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DBOpenHelperSupplier> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<Context> provider) {
        return new MediaEventModule_Dagger_ProvideDBOpenHelperSupplierFactory(mediaEventModule_Dagger, provider);
    }
}
