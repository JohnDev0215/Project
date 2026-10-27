package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchHistoryConverterFactory implements Factory<BrushV2Converter<SearchHistoryItem>> {
    public static BrushV2Converter<SearchHistoryItem> provideSearchHistoryConverter(FindModule instance, Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        return (BrushV2Converter) Preconditions.checkNotNullFromProvides(instance.provideSearchHistoryConverter(context, cacheImageProvider, searchFeaturesProvider));
    }
}
