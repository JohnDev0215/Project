package com.android.car.developeroptions.security;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EncryptionAndCredential extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new SecuritySearchIndexProvider();

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_encryption;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "EncryptionAndCredential";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 846;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.encryption_and_credential;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        EncryptionStatusPreferenceController encryptionStatusPreferenceController = new EncryptionStatusPreferenceController(context, "encryption_and_credentials_encryption_status");
        arrayList.add(encryptionStatusPreferenceController);
        arrayList.add(new PreferenceCategoryController(context, "encryption_and_credentials_status_category").setChildren(Arrays.asList(encryptionStatusPreferenceController)));
        arrayList.add(new CredentialStoragePreferenceController(context));
        arrayList.add(new UserCredentialsPreferenceController(context));
        arrayList.add(new ResetCredentialsPreferenceController(context, lifecycle));
        arrayList.add(new InstallCredentialsPreferenceController(context));
        return arrayList;
    }

    private static class SecuritySearchIndexProvider extends BaseSearchIndexProvider {
        private SecuritySearchIndexProvider() {
        }

        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.encryption_and_credential;
            return Arrays.asList(searchIndexableResource);
        }
    }
}
