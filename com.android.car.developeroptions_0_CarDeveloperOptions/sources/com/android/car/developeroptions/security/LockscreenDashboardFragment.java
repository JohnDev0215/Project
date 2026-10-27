package com.android.car.developeroptions.security;

import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.display.AmbientDisplayAlwaysOnPreferenceController;
import com.android.car.developeroptions.display.AmbientDisplayNotificationsPreferenceController;
import com.android.car.developeroptions.gestures.DoubleTapScreenPreferenceController;
import com.android.car.developeroptions.gestures.PickupGesturePreferenceController;
import com.android.car.developeroptions.notification.LockScreenNotificationPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.security.OwnerInfoPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LockscreenDashboardFragment extends DashboardFragment implements OwnerInfoPreferenceController.OwnerInfoCallback {
    static final String KEY_ADD_USER_FROM_LOCK_SCREEN = "security_lockscreen_add_users_when_locked";
    static final String KEY_LOCK_SCREEN_NOTIFICATON = "security_setting_lock_screen_notif";
    static final String KEY_LOCK_SCREEN_NOTIFICATON_WORK_PROFILE = "security_setting_lock_screen_notif_work";
    static final String KEY_LOCK_SCREEN_NOTIFICATON_WORK_PROFILE_HEADER = "security_setting_lock_screen_notif_work_header";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.security.LockscreenDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.security_lockscreen_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private AmbientDisplayConfiguration mConfig;
    private OwnerInfoPreferenceController mOwnerInfoPreferenceController;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_lockscreen;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "LockscreenDashboardFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 882;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.security_lockscreen_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((AmbientDisplayAlwaysOnPreferenceController) use(AmbientDisplayAlwaysOnPreferenceController.class)).setConfig(getConfig(context)).setCallback(new AmbientDisplayAlwaysOnPreferenceController.OnPreferenceChangedCallback() { // from class: com.android.car.developeroptions.security.-$$Lambda$LockscreenDashboardFragment$SQnrEsMIGOrZd51Fmcnv8PELxFc
            @Override // com.android.car.developeroptions.display.AmbientDisplayAlwaysOnPreferenceController.OnPreferenceChangedCallback
            public final void onPreferenceChanged() {
                this.f$0.updatePreferenceStates();
            }
        });
        ((AmbientDisplayNotificationsPreferenceController) use(AmbientDisplayNotificationsPreferenceController.class)).setConfig(getConfig(context));
        ((DoubleTapScreenPreferenceController) use(DoubleTapScreenPreferenceController.class)).setConfig(getConfig(context));
        ((PickupGesturePreferenceController) use(PickupGesturePreferenceController.class)).setConfig(getConfig(context));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        Lifecycle settingsLifecycle = getSettingsLifecycle();
        LockScreenNotificationPreferenceController lockScreenNotificationPreferenceController = new LockScreenNotificationPreferenceController(context, KEY_LOCK_SCREEN_NOTIFICATON, KEY_LOCK_SCREEN_NOTIFICATON_WORK_PROFILE_HEADER, KEY_LOCK_SCREEN_NOTIFICATON_WORK_PROFILE);
        settingsLifecycle.addObserver(lockScreenNotificationPreferenceController);
        arrayList.add(lockScreenNotificationPreferenceController);
        OwnerInfoPreferenceController ownerInfoPreferenceController = new OwnerInfoPreferenceController(context, this, settingsLifecycle);
        this.mOwnerInfoPreferenceController = ownerInfoPreferenceController;
        arrayList.add(ownerInfoPreferenceController);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.security.OwnerInfoPreferenceController.OwnerInfoCallback
    public void onOwnerInfoUpdated() {
        OwnerInfoPreferenceController ownerInfoPreferenceController = this.mOwnerInfoPreferenceController;
        if (ownerInfoPreferenceController != null) {
            ownerInfoPreferenceController.updateSummary();
        }
    }

    private AmbientDisplayConfiguration getConfig(Context context) {
        if (this.mConfig == null) {
            this.mConfig = new AmbientDisplayConfiguration(context);
        }
        return this.mConfig;
    }
}
