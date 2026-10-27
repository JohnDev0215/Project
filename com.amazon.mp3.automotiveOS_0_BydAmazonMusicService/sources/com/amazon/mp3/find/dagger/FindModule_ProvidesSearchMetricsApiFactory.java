package com.amazon.mp3.find.dagger;

import com.amazon.music.find.metrics.SearchMetricsApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvidesSearchMetricsApiFactory implements Factory<SearchMetricsApi> {
    public static SearchMetricsApi providesSearchMetricsApi(FindModule instance) {
        return (SearchMetricsApi) Preconditions.checkNotNullFromProvides(instance.providesSearchMetricsApi());
    }
}
