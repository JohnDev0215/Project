package com.android.settingslib.search;

import com.android.settingslib.search.Indexable;

/* JADX INFO: loaded from: classes6.dex */
public class SearchIndexableData {
    private final Indexable.SearchIndexProvider mSearchIndexProvider;
    private final Class mTargetClass;

    public SearchIndexableData(Class targetClass, Indexable.SearchIndexProvider provider) {
        this.mTargetClass = targetClass;
        this.mSearchIndexProvider = provider;
    }

    public Class getTargetClass() {
        return this.mTargetClass;
    }

    public Indexable.SearchIndexProvider getSearchIndexProvider() {
        return this.mSearchIndexProvider;
    }
}
