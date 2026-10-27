package com.amazon.mp3.find.dagger;

import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideConverterServiceFactory implements Factory<ConverterService> {
    public static ConverterService provideConverterService(FindModule instance, BrushV2Converter<SearchHistoryItem> searchHistoryConverterBrushV2, BrushV2Converter<SearchSuggestion> searchSuggestionConverterBrushV2, BrushV2Converter<SearchResponse> searchResultConverterBrushV2, BrushV2Converter<SeeMoreResponse> seeMoreResultConverterBrushV2) {
        return (ConverterService) Preconditions.checkNotNullFromProvides(instance.provideConverterService(searchHistoryConverterBrushV2, searchSuggestionConverterBrushV2, searchResultConverterBrushV2, seeMoreResultConverterBrushV2));
    }
}
