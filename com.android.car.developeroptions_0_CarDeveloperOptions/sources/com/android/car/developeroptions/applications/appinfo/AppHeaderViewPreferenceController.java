package com.android.car.developeroptions.applications.appinfo;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class AppHeaderViewPreferenceController extends BasePreferenceController implements AppInfoDashboardFragment.Callback, LifecycleObserver, OnStart {
    private static final String KEY_HEADER = "header_view";
    private EntityHeaderController mEntityHeaderController;
    private LayoutPreference mHeader;
    private final Lifecycle mLifecycle;
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppHeaderViewPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_HEADER);
        this.mParent = appInfoDashboardFragment;
        this.mPackageName = str;
        this.mLifecycle = lifecycle;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mHeader = (LayoutPreference) preferenceScreen.findPreference(KEY_HEADER);
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(this.mParent.getActivity(), this.mParent, this.mHeader.findViewById(R.id.entity_header));
        entityHeaderControllerNewInstance.setPackageName(this.mPackageName);
        entityHeaderControllerNewInstance.setButtonActions(0, 0);
        entityHeaderControllerNewInstance.bindHeaderButtons();
        this.mEntityHeaderController = entityHeaderControllerNewInstance;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        EntityHeaderController entityHeaderController = this.mEntityHeaderController;
        entityHeaderController.setRecyclerView(this.mParent.getListView(), this.mLifecycle);
        entityHeaderController.styleActionBar(this.mParent.getActivity());
    }

    @Override // com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment.Callback
    public void refreshUi() {
        setAppLabelAndIcon(this.mParent.getPackageInfo(), this.mParent.getAppEntry());
    }

    private void setAppLabelAndIcon(PackageInfo packageInfo, ApplicationsState.AppEntry appEntry) {
        FragmentActivity activity = this.mParent.getActivity();
        boolean zIsInstant = AppUtils.isInstant(packageInfo.applicationInfo);
        EntityHeaderController entityHeaderController = this.mEntityHeaderController;
        entityHeaderController.setLabel(appEntry);
        entityHeaderController.setIcon(appEntry);
        entityHeaderController.setIsInstantApp(zIsInstant);
        entityHeaderController.done((Activity) activity, false);
    }
}
