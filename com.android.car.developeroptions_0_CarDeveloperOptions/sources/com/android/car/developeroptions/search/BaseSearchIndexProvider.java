package com.android.car.developeroptions.search;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.Log;
import com.android.car.developeroptions.core.PreferenceXmlParserUtils;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class BaseSearchIndexProvider implements Indexable$SearchIndexProvider {
    @Override // com.android.settingslib.search.Indexable$SearchIndexProvider
    public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
        return null;
    }

    public List<String> getNonIndexableKeysFromXml(Context context, int i, boolean z) {
        return getKeysFromXml(context, i, z);
    }

    private List<String> getKeysFromXml(Context context, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            for (Bundle bundle : PreferenceXmlParserUtils.extractMetadata(context, i, 515)) {
                if (z || !bundle.getBoolean("searchable", true)) {
                    arrayList.add(bundle.getString("key"));
                }
            }
        } catch (IOException | XmlPullParserException unused) {
            Log.w("BaseSearchIndex", "Error parsing non-indexable from xml " + i);
        }
        return arrayList;
    }
}
