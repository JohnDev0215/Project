package com.amazon.avod.media.events.dagger;

import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.media.events.dao.MediaEventDAO;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaEventDAOFactory implements Factory<MediaEventDAO> {
    private final Provider<DBOpenHelperSupplier> dbOpenHelperSupplierProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideMediaEventDAOFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<DBOpenHelperSupplier> provider) {
        this.module = mediaEventModule_Dagger;
        this.dbOpenHelperSupplierProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaEventDAO get() {
        return (MediaEventDAO) Preconditions.checkNotNull(this.module.provideMediaEventDAO(this.dbOpenHelperSupplierProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaEventDAO> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<DBOpenHelperSupplier> provider) {
        return new MediaEventModule_Dagger_ProvideMediaEventDAOFactory(mediaEventModule_Dagger, provider);
    }
}
