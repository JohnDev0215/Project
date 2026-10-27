package com.amazon.mp3.find.dagger;

import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.service.ConverterService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideCarModeConverterServiceFactory implements Factory<ConverterService> {
    public static ConverterService provideCarModeConverterService(FindModule instance, BrushV2Converter<SearchResponse> carModeSearchResultConverterBrushV2) {
        return (ConverterService) Preconditions.checkNotNullFromProvides(instance.provideCarModeConverterService(carModeSearchResultConverterBrushV2));
    }
}
