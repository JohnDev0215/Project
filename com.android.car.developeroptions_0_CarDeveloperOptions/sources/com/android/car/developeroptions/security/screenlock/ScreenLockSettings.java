package com.android.car.developeroptions.security.screenlock;

import android.content.Context;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.security.OwnerInfoPreferenceController;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ScreenLockSettings extends DashboardFragment implements OwnerInfoPreferenceController.OwnerInfoCallback {
    private static final int MY_USER_ID = UserHandle.myUserId();
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.security.screenlock.ScreenLockSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.screen_lock_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private LockPatternUtils mLockPatternUtils;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ScreenLockSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1265;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.screen_lock_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mLockPatternUtils = new LockPatternUtils(context);
        return buildPreferenceControllers(context, this, getSettingsLifecycle(), this.mLockPatternUtils);
    }

    @Override // com.android.car.developeroptions.security.OwnerInfoPreferenceController.OwnerInfoCallback
    public void onOwnerInfoUpdated() {
        ((OwnerInfoPreferenceController) use(OwnerInfoPreferenceController.class)).updateSummary();
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Fragment fragment, Lifecycle lifecycle, LockPatternUtils lockPatternUtils) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PatternVisiblePreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new PowerButtonInstantLockPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new LockAfterTimeoutPreferenceController(context, MY_USER_ID, lockPatternUtils));
        arrayList.add(new OwnerInfoPreferenceController(context, fragment, lifecycle));
        return arrayList;
    }
}
