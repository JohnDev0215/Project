package com.amazon.mp3.find.view;

import com.amazon.music.find.viewmodels.SearchHostViewModelFactory;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class SearchHostFragment_MembersInjector implements MembersInjector<SearchHostFragment> {
    public static void injectSearchViewModelFactory(SearchHostFragment instance, SearchHostViewModelFactory searchViewModelFactory) {
        instance.searchViewModelFactory = searchViewModelFactory;
    }
}
