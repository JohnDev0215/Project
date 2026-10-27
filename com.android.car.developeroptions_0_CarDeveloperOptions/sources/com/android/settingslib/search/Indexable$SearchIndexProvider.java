package com.android.settingslib.search;

import android.content.Context;
import android.provider.SearchIndexableResource;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Indexable$SearchIndexProvider {
    List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z);
}
