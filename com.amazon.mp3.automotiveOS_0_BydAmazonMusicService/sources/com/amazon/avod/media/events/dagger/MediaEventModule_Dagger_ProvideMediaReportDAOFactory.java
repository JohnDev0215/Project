package com.amazon.avod.media.events.dagger;

import android.content.Context;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.media.events.dao.MediaReportDAO;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaReportDAOFactory implements Factory<MediaReportDAO> {
    private final Provider<Context> contextProvider;
    private final Provider<DBOpenHelperSupplier> dbOpenHelperSupplierProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideMediaReportDAOFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<DBOpenHelperSupplier> provider, Provider<Context> provider2) {
        this.module = mediaEventModule_Dagger;
        this.dbOpenHelperSupplierProvider = provider;
        this.contextProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MediaReportDAO get() {
        return (MediaReportDAO) Preconditions.checkNotNull(this.module.provideMediaReportDAO(this.dbOpenHelperSupplierProvider.get(), this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaReportDAO> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<DBOpenHelperSupplier> provider, Provider<Context> provider2) {
        return new MediaEventModule_Dagger_ProvideMediaReportDAOFactory(mediaEventModule_Dagger, provider, provider2);
    }
}
