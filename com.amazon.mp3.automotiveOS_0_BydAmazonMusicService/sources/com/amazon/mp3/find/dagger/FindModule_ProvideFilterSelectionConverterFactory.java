package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideFilterSelectionConverterFactory implements Factory<PageGridViewModelConverter<SearchFilterSelections>> {
    public static PageGridViewModelConverter<SearchFilterSelections> provideFilterSelectionConverter(FindModule instance, Context context, SearchFeaturesProvider searchFeaturesProvider) {
        return (PageGridViewModelConverter) Preconditions.checkNotNullFromProvides(instance.provideFilterSelectionConverter(context, searchFeaturesProvider));
    }
}
