package com.android.car.developeroptions.inputmethod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserDictionaryList extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.inputmethod.UserDictionaryList.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.user_dictionary_list_fragment;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "UserDictionaryList";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 61;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.user_dictionary_list_fragment;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Intent intent = getActivity().getIntent();
        String str = null;
        String stringExtra = intent == null ? null : intent.getStringExtra("locale");
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("locale");
        if (string != null) {
            str = string;
        } else if (stringExtra != null) {
            str = stringExtra;
        }
        ((UserDictionaryListPreferenceController) use(UserDictionaryListPreferenceController.class)).setLocale(str);
    }
}
