package com.amazon.music.skyfire.ui.fragment;

import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class SelectionFragment_MembersInjector implements MembersInjector<SelectionFragment> {
    private final Provider<ElementBindingProvider> elementBindingProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;

    public SelectionFragment_MembersInjector(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2) {
        this.styleSheetProvider = provider;
        this.elementBindingProvider = provider2;
    }

    public static MembersInjector<SelectionFragment> create(Provider<StyleSheetProvider> provider, Provider<ElementBindingProvider> provider2) {
        return new SelectionFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(SelectionFragment selectionFragment) {
        injectStyleSheetProvider(selectionFragment, this.styleSheetProvider.get());
        injectElementBindingProvider(selectionFragment, this.elementBindingProvider.get());
    }

    public static void injectStyleSheetProvider(SelectionFragment selectionFragment, StyleSheetProvider styleSheetProvider) {
        selectionFragment.styleSheetProvider = styleSheetProvider;
    }

    public static void injectElementBindingProvider(SelectionFragment selectionFragment, ElementBindingProvider elementBindingProvider) {
        selectionFragment.elementBindingProvider = elementBindingProvider;
    }
}
