package com.android.settingslib.search;

import android.content.Context;
import android.provider.SearchIndexableResource;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public interface Indexable {

    public interface SearchIndexProvider {
        List<SearchIndexableRaw> getDynamicRawDataToIndex(Context context, boolean z);

        List<String> getNonIndexableKeys(Context context);

        List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean z);

        List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z);
    }
}
