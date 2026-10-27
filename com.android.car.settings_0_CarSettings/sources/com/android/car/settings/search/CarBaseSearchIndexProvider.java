package com.android.car.settings.search;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceXmlParser;
import com.android.settingslib.search.Indexable;
import com.android.settingslib.search.SearchIndexableRaw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class CarBaseSearchIndexProvider implements Indexable.SearchIndexProvider {
    private static final Logger LOG = new Logger((Class<?>) CarBaseSearchIndexProvider.class);
    private final String mIntentAction;
    private final String mIntentClass;
    private final int mXmlRes;

    public CarBaseSearchIndexProvider(int xmlRes, String intentAction) {
        this.mXmlRes = xmlRes;
        this.mIntentAction = intentAction;
        this.mIntentClass = null;
    }

    public CarBaseSearchIndexProvider(int xmlRes, Class intentClass) {
        this.mXmlRes = xmlRes;
        this.mIntentAction = null;
        this.mIntentClass = intentClass.getName();
    }

    @Override // com.android.settingslib.search.Indexable.SearchIndexProvider
    public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean enabled) {
        SearchIndexableResource sir = new SearchIndexableResource(context);
        sir.xmlResId = this.mXmlRes;
        sir.intentAction = this.mIntentAction;
        sir.intentTargetPackage = context.getPackageName();
        sir.intentTargetClass = this.mIntentClass;
        return Collections.singletonList(sir);
    }

    @Override // com.android.settingslib.search.Indexable.SearchIndexProvider
    public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean enabled) {
        return null;
    }

    @Override // com.android.settingslib.search.Indexable.SearchIndexProvider
    public List<SearchIndexableRaw> getDynamicRawDataToIndex(Context context, boolean enabled) {
        return null;
    }

    @Override // com.android.settingslib.search.Indexable.SearchIndexProvider
    public List<String> getNonIndexableKeys(Context context) {
        boolean searchEnabled = isPageSearchEnabled(context);
        List<String> keys = new ArrayList<>();
        try {
            List<Bundle> metadata = PreferenceXmlParser.extractMetadata(context, this.mXmlRes, 513);
            for (Bundle bundle : metadata) {
                if (!searchEnabled || !bundle.getBoolean(PreferenceXmlParser.METADATA_SEARCHABLE, true)) {
                    keys.add(bundle.getString(PreferenceXmlParser.METADATA_KEY));
                }
            }
        } catch (IOException | XmlPullParserException e) {
            LOG.w("Error parsing non-indexable XML - " + this.mXmlRes);
        }
        return keys;
    }

    protected boolean isPageSearchEnabled(Context context) {
        return true;
    }

    protected SearchIndexableRaw createRawDataEntry(Context context, String key, String title, String screenTitle) {
        SearchIndexableRaw raw = new SearchIndexableRaw(context);
        raw.key = key;
        raw.title = title;
        raw.screenTitle = screenTitle;
        raw.intentAction = this.mIntentAction;
        raw.intentTargetPackage = context.getPackageName();
        raw.intentTargetClass = this.mIntentClass;
        return raw;
    }
}
