package com.amazon.mp3.find.view;

import com.amazon.music.find.viewmodels.FilterViewModelFactory;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class FindFilterDialogFragment_MembersInjector implements MembersInjector<FindFilterDialogFragment> {
    public static void injectFilterViewModelFactory(FindFilterDialogFragment instance, FilterViewModelFactory filterViewModelFactory) {
        instance.filterViewModelFactory = filterViewModelFactory;
    }

    public static void injectSearchViewModelFactory(FindFilterDialogFragment instance, SearchViewModelFactory searchViewModelFactory) {
        instance.searchViewModelFactory = searchViewModelFactory;
    }
}
