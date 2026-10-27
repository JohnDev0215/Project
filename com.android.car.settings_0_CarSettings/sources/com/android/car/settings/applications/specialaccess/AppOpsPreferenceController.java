package com.android.car.settings.applications.specialaccess;

import android.app.AppOpsManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.applications.specialaccess.AppEntryListManager;
import com.android.car.settings.applications.specialaccess.AppStateAppOpsBridge;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiSwitchPreference;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AppOpsPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final ApplicationsState.AppFilter FILTER_HAS_INFO = new ApplicationsState.AppFilter() { // from class: com.android.car.settings.applications.specialaccess.AppOpsPreferenceController.1
        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationsState.AppEntry info) {
            return info.extraInfo != null;
        }
    };
    AppEntryListManager mAppEntryListManager;
    private final AppOpsManager mAppOpsManager;
    private int mAppOpsOpCode;
    private final AppEntryListManager.Callback mCallback;
    private List<ApplicationsState.AppEntry> mEntries;
    private int mNegativeOpMode;
    private final Preference.OnPreferenceChangeListener mOnPreferenceChangeListener;
    private String mPermission;
    private boolean mShowSystem;

    public AppOpsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mOnPreferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.applications.specialaccess.AppOpsPreferenceController.2
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                AppOpPreference appOpPreference = (AppOpPreference) preference;
                ApplicationsState.AppEntry entry = appOpPreference.mEntry;
                AppStateAppOpsBridge.PermissionState extraInfo = (AppStateAppOpsBridge.PermissionState) entry.extraInfo;
                boolean allowOp = ((Boolean) newValue).booleanValue();
                if (allowOp == extraInfo.isPermissible()) {
                    return false;
                }
                AppOpsPreferenceController.this.mAppOpsManager.setMode(AppOpsPreferenceController.this.mAppOpsOpCode, entry.info.uid, entry.info.packageName, allowOp ? 0 : AppOpsPreferenceController.this.mNegativeOpMode);
                AppOpsPreferenceController.this.mAppEntryListManager.forceUpdate(entry);
                return true;
            }
        };
        this.mCallback = new AppEntryListManager.Callback() { // from class: com.android.car.settings.applications.specialaccess.AppOpsPreferenceController.3
            @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.Callback
            public void onAppEntryListChanged(List<ApplicationsState.AppEntry> entries) {
                AppOpsPreferenceController.this.mEntries = entries;
                AppOpsPreferenceController.this.refreshUi();
            }
        };
        this.mAppOpsOpCode = -1;
        this.mNegativeOpMode = -1;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mAppEntryListManager = new AppEntryListManager(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    public void init(int appOpsOpCode, String permission, int negativeOpMode) {
        this.mAppOpsOpCode = appOpsOpCode;
        this.mPermission = permission;
        this.mNegativeOpMode = negativeOpMode;
    }

    public void setShowSystem(boolean showSystem) {
        if (this.mShowSystem != showSystem) {
            this.mShowSystem = showSystem;
            this.mAppEntryListManager.forceUpdate();
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAppOpsOpCode == -1) {
            throw new IllegalStateException("App operation code must be initialized");
        }
        if (this.mPermission == null) {
            throw new IllegalStateException("Manifest permission must be initialized");
        }
        if (this.mNegativeOpMode == -1) {
            throw new IllegalStateException("Negative case app operation mode must be initialized");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        AppStateAppOpsBridge extraInfoBridge = new AppStateAppOpsBridge(getContext(), this.mAppOpsOpCode, this.mPermission);
        this.mAppEntryListManager.init(extraInfoBridge, new AppEntryListManager.AppFilterProvider() { // from class: com.android.car.settings.applications.specialaccess.-$$Lambda$1xy5mHoQYLWCOUHo9VoEInJUSBs
            @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.AppFilterProvider
            public final ApplicationsState.AppFilter getAppFilter() {
                return this.f$0.getAppFilter();
            }
        }, this.mCallback);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mAppEntryListManager.start();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mAppEntryListManager.stop();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mAppEntryListManager.destroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        if (this.mEntries == null) {
            return;
        }
        preference.removeAll();
        for (ApplicationsState.AppEntry entry : this.mEntries) {
            Preference appOpPreference = new AppOpPreference(getContext(), entry);
            appOpPreference.setOnPreferenceChangeListener(this.mOnPreferenceChangeListener);
            preference.addPreference(appOpPreference);
        }
    }

    protected ApplicationsState.AppFilter getAppFilter() {
        ApplicationsState.AppFilter filterObj = new ApplicationsState.CompoundFilter(FILTER_HAS_INFO, ApplicationsState.FILTER_NOT_HIDE);
        if (!this.mShowSystem) {
            return new ApplicationsState.CompoundFilter(filterObj, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER);
        }
        return filterObj;
    }

    private static class AppOpPreference extends CarUiSwitchPreference {
        private final ApplicationsState.AppEntry mEntry;

        AppOpPreference(Context context, ApplicationsState.AppEntry entry) {
            super(context);
            String key = entry.info.packageName + "|" + entry.info.uid;
            setKey(key);
            setTitle(entry.label);
            setIcon(entry.icon);
            setSummary(getAppStateText(entry.info));
            setPersistent(false);
            AppStateAppOpsBridge.PermissionState extraInfo = (AppStateAppOpsBridge.PermissionState) entry.extraInfo;
            setChecked(extraInfo.isPermissible());
            this.mEntry = entry;
        }

        private String getAppStateText(ApplicationInfo info) {
            if ((info.flags & 8388608) == 0) {
                return getContext().getString(R.string.not_installed);
            }
            if (!info.enabled || info.enabledSetting == 4) {
                return getContext().getString(R.string.disabled);
            }
            return null;
        }
    }
}
