package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.Intent;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.biometrics.face.FaceProfileStatusPreferenceController;
import com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintProfileStatusPreferenceController;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.enterprise.EnterprisePrivacyPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.PreferenceCategoryController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SecuritySettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.security.SecuritySettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.security_dashboard_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_security;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "SecuritySettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 87;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.security_dashboard_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (((LockUnificationPreferenceController) use(LockUnificationPreferenceController.class)).handleActivityResult(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    void startUnification() {
        ((LockUnificationPreferenceController) use(LockUnificationPreferenceController.class)).startUnification();
    }

    void updateUnificationPreference() {
        ((LockUnificationPreferenceController) use(LockUnificationPreferenceController.class)).updateState(null);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, SecuritySettings securitySettings) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EnterprisePrivacyPreferenceController(context));
        arrayList.add(new ScreenPinningPreferenceController(context));
        arrayList.add(new SimLockPreferenceController(context));
        arrayList.add(new EncryptionStatusPreferenceController(context, "encryption_and_credential"));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new FaceStatusPreferenceController(context));
        arrayList2.add(new FingerprintStatusPreferenceController(context));
        arrayList2.add(new ChangeScreenLockPreferenceController(context, securitySettings));
        arrayList.add(new PreferenceCategoryController(context, "security_category").setChildren(arrayList2));
        arrayList.addAll(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new ChangeProfileScreenLockPreferenceController(context, securitySettings));
        arrayList3.add(new LockUnificationPreferenceController(context, securitySettings));
        arrayList3.add(new VisiblePatternProfilePreferenceController(context, lifecycle));
        arrayList3.add(new FaceProfileStatusPreferenceController(context));
        arrayList3.add(new FingerprintProfileStatusPreferenceController(context));
        arrayList.add(new PreferenceCategoryController(context, "security_category_profile").setChildren(arrayList3));
        arrayList.addAll(arrayList3);
        return arrayList;
    }
}
