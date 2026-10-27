package com.android.settingslib.search;

import java.util.Collection;

/* JADX INFO: loaded from: classes6.dex */
public interface SearchIndexableResources {
    void addIndex(SearchIndexableData searchIndexableData);

    Collection<SearchIndexableData> getProviderValues();
}
