package com.amazon.mp3.find.dagger;

import com.amazon.music.find.service.SearchResultTransformerService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideCarModeTransformerServiceFactory implements Factory<SearchResultTransformerService> {
    public static SearchResultTransformerService provideCarModeTransformerService(FindModule instance) {
        return (SearchResultTransformerService) Preconditions.checkNotNullFromProvides(instance.provideCarModeTransformerService());
    }
}
