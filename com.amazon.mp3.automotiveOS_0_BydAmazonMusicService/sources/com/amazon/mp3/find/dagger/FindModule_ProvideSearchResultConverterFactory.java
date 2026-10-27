package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchResultConverterFactory implements Factory<BrushV2Converter<SearchResponse>> {
    public static BrushV2Converter<SearchResponse> provideSearchResultConverter(FindModule instance, Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, SearchMetricsService searchMetricsService) {
        return (BrushV2Converter) Preconditions.checkNotNullFromProvides(instance.provideSearchResultConverter(context, cacheImageProvider, searchFeaturesProvider, searchMetricsService));
    }
}
