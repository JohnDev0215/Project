package com.amazon.mp3.find.dagger;

import com.amazon.music.find.providers.SearchEntityFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesSearchEntityFactoryFactory implements Factory<SearchEntityFactory> {
    public static SearchEntityFactory providesSearchEntityFactory(FindModule instance) {
        return (SearchEntityFactory) Preconditions.checkNotNullFromProvides(instance.providesSearchEntityFactory());
    }
}
