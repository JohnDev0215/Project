package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.UIConfigurationService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSearchSuggestionConverterFactory implements Factory<BrushV2Converter<SearchSuggestion>> {
    public static BrushV2Converter<SearchSuggestion> provideSearchSuggestionConverter(FindModule instance, Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        return (BrushV2Converter) Preconditions.checkNotNullFromProvides(instance.provideSearchSuggestionConverter(context, cacheImageProvider, searchFeaturesProvider, uiConfigurationService));
    }
}
