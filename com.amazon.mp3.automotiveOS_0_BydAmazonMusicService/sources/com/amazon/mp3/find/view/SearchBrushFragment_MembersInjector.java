package com.amazon.mp3.find.view;

import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class SearchBrushFragment_MembersInjector implements MembersInjector<SearchBrushFragment> {
    public static void injectSearchViewModelFactory(SearchBrushFragment instance, SearchViewModelFactory searchViewModelFactory) {
        instance.searchViewModelFactory = searchViewModelFactory;
    }
}
