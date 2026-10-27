package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideSeeMoreResultConverterFactory implements Factory<BrushV2Converter<SeeMoreResponse>> {
    public static BrushV2Converter<SeeMoreResponse> provideSeeMoreResultConverter(FindModule instance, Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        return (BrushV2Converter) Preconditions.checkNotNullFromProvides(instance.provideSeeMoreResultConverter(context, cacheImageProvider, searchFeaturesProvider));
    }
}
