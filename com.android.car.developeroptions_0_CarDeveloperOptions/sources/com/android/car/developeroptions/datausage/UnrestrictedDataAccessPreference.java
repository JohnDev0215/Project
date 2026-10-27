package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.datausage.AppStateDataUsageBridge;
import com.android.car.developeroptions.datausage.DataSaverBackend;
import com.android.car.developeroptions.widget.AppSwitchPreference;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreferenceHelper;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class UnrestrictedDataAccessPreference extends AppSwitchPreference implements DataSaverBackend.Listener {
    private final ApplicationsState mApplicationsState;
    private final DataSaverBackend mDataSaverBackend;
    private final AppStateDataUsageBridge.DataUsageState mDataUsageState;
    private final ApplicationsState.AppEntry mEntry;
    private final RestrictedPreferenceHelper mHelper;
    private final DashboardFragment mParentFragment;

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onDataSaverChanged(boolean z) {
    }

    public UnrestrictedDataAccessPreference(Context context, ApplicationsState.AppEntry appEntry, ApplicationsState applicationsState, DataSaverBackend dataSaverBackend, DashboardFragment dashboardFragment) {
        super(context);
        setWidgetLayoutResource(R.layout.restricted_switch_widget);
        this.mHelper = new RestrictedPreferenceHelper(context, this, null);
        this.mEntry = appEntry;
        this.mDataUsageState = (AppStateDataUsageBridge.DataUsageState) appEntry.extraInfo;
        appEntry.ensureLabel(context);
        this.mApplicationsState = applicationsState;
        this.mDataSaverBackend = dataSaverBackend;
        this.mParentFragment = dashboardFragment;
        ApplicationInfo applicationInfo = appEntry.info;
        setDisabledByAdmin(RestrictedLockUtilsInternal.checkIfMeteredDataRestricted(context, applicationInfo.packageName, UserHandle.getUserId(applicationInfo.uid)));
        updateState();
        setKey(generateKey(this.mEntry));
        Drawable drawable = this.mEntry.icon;
        if (drawable != null) {
            setIcon(drawable);
        }
    }

    static String generateKey(ApplicationsState.AppEntry appEntry) {
        return appEntry.info.packageName + "|" + appEntry.info.uid;
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mDataSaverBackend.addListener(this);
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        this.mDataSaverBackend.remListener(this);
        super.onDetached();
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    protected void onClick() {
        if (this.mDataUsageState.isDataSaverBlacklisted) {
            AppInfoDashboardFragment.startAppInfoFragment(AppDataUsage.class, R.string.data_usage_app_summary_title, null, this.mParentFragment, this.mEntry);
        } else {
            super.onClick();
        }
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        if (this.mHelper.performClick()) {
            return;
        }
        super.performClick();
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        if (this.mEntry.icon == null) {
            preferenceViewHolder.itemView.post(new Runnable() { // from class: com.android.car.developeroptions.datausage.UnrestrictedDataAccessPreference.1
                @Override // java.lang.Runnable
                public void run() {
                    UnrestrictedDataAccessPreference.this.mApplicationsState.ensureIcon(UnrestrictedDataAccessPreference.this.mEntry);
                    UnrestrictedDataAccessPreference unrestrictedDataAccessPreference = UnrestrictedDataAccessPreference.this;
                    unrestrictedDataAccessPreference.setIcon(unrestrictedDataAccessPreference.mEntry.icon);
                }
            });
        }
        boolean zIsDisabledByAdmin = isDisabledByAdmin();
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        if (zIsDisabledByAdmin) {
            viewFindViewById.setVisibility(0);
        } else {
            AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
            viewFindViewById.setVisibility((dataUsageState == null || !dataUsageState.isDataSaverBlacklisted) ? 0 : 4);
        }
        super.onBindViewHolder(preferenceViewHolder);
        this.mHelper.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.findViewById(R.id.restricted_icon).setVisibility(zIsDisabledByAdmin ? 0 : 8);
        preferenceViewHolder.findViewById(android.R.id.switch_widget).setVisibility(zIsDisabledByAdmin ? 8 : 0);
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onWhitelistStatusChanged(int i, boolean z) {
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState == null || this.mEntry.info.uid != i) {
            return;
        }
        dataUsageState.isDataSaverWhitelisted = z;
        updateState();
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onBlacklistStatusChanged(int i, boolean z) {
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState == null || this.mEntry.info.uid != i) {
            return;
        }
        dataUsageState.isDataSaverBlacklisted = z;
        updateState();
    }

    public AppStateDataUsageBridge.DataUsageState getDataUsageState() {
        return this.mDataUsageState;
    }

    public ApplicationsState.AppEntry getEntry() {
        return this.mEntry;
    }

    public boolean isDisabledByAdmin() {
        return this.mHelper.isDisabledByAdmin();
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mHelper.setDisabledByAdmin(enforcedAdmin);
    }

    public void updateState() {
        setTitle(this.mEntry.label);
        AppStateDataUsageBridge.DataUsageState dataUsageState = this.mDataUsageState;
        if (dataUsageState != null) {
            setChecked(dataUsageState.isDataSaverWhitelisted);
            if (isDisabledByAdmin()) {
                setSummary(R.string.disabled_by_admin);
            } else if (this.mDataUsageState.isDataSaverBlacklisted) {
                setSummary(R.string.restrict_background_blacklisted);
            } else {
                setSummary("");
            }
        }
        notifyChanged();
    }
}
