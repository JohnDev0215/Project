package com.android.car.developeroptions.applications.manageapplications;

import android.app.Activity;
import android.app.usage.IUsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.preference.PreferenceFrameLayout;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$ChangeWifiStateActivity;
import com.android.car.developeroptions.Settings$GamesStorageActivity;
import com.android.car.developeroptions.Settings$HighPowerApplicationsActivity;
import com.android.car.developeroptions.Settings$ManageExternalSourcesActivity;
import com.android.car.developeroptions.Settings$MoviesStorageActivity;
import com.android.car.developeroptions.Settings$NotificationAppListActivity;
import com.android.car.developeroptions.Settings$OverlaySettingsActivity;
import com.android.car.developeroptions.Settings$PhotosStorageActivity;
import com.android.car.developeroptions.Settings$StorageUseActivity;
import com.android.car.developeroptions.Settings$UsageAccessSettingsActivity;
import com.android.car.developeroptions.Settings$WriteSettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.AppInfoBase;
import com.android.car.developeroptions.applications.AppStateAppOpsBridge;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.car.developeroptions.applications.AppStateInstallAppsBridge;
import com.android.car.developeroptions.applications.AppStateNotificationBridge;
import com.android.car.developeroptions.applications.AppStateOverlayBridge;
import com.android.car.developeroptions.applications.AppStatePowerBridge;
import com.android.car.developeroptions.applications.AppStateUsageBridge;
import com.android.car.developeroptions.applications.AppStateWriteSettingsBridge;
import com.android.car.developeroptions.applications.AppStorageSettings;
import com.android.car.developeroptions.applications.InstalledAppCounter;
import com.android.car.developeroptions.applications.UsageAccessDetails;
import com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment;
import com.android.car.developeroptions.applications.appinfo.DrawOverlayDetails;
import com.android.car.developeroptions.applications.appinfo.ExternalSourcesDetails;
import com.android.car.developeroptions.applications.appinfo.WriteSettingsDetails;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.fuelgauge.HighPowerDetail;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.notification.AppNotificationSettings;
import com.android.car.developeroptions.notification.NotificationBackend;
import com.android.car.developeroptions.widget.LoadingViewController;
import com.android.car.developeroptions.wifi.AppStateChangeWifiStateBridge;
import com.android.car.developeroptions.wifi.ChangeWifiStateDetails;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.applications.StorageStatsSource;
import com.android.settingslib.fuelgauge.PowerWhitelistBackend;
import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.widget.settingsspinner.SettingsSpinnerAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ManageApplications extends InstrumentedFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {
    public static final Set<Integer> LIST_TYPES_WITH_INSTANT = new ArraySet(Arrays.asList(0, 3));
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.applications.manageapplications.ManageApplications.1
        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private ApplicationsAdapter mApplications;
    private ApplicationsState mApplicationsState;
    private String mCurrentPkgName;
    private int mCurrentUid;
    private View mEmptyView;
    private AppFilterItem mFilter;
    FilterSpinnerAdapter mFilterAdapter;
    private Spinner mFilterSpinner;
    private int mFilterType;
    CharSequence mInvalidSizeStr;
    private boolean mIsWorkOnly;
    private View mListContainer;
    public int mListType;
    private View mLoadingContainer;
    private NotificationBackend mNotificationBackend;
    private Menu mOptionsMenu;
    RecyclerView mRecyclerView;
    private ResetAppsHelper mResetAppsHelper;
    private View mRootView;
    private SearchView mSearchView;
    private boolean mShowSystem;
    int mSortOrder = R.id.sort_order_alpha;
    View mSpinnerHeader;
    private int mStorageType;
    private IUsageStatsManager mUsageStatsManager;
    private UserManager mUserManager;
    private String mVolumeUuid;
    private int mWorkUserId;

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        return false;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        FragmentActivity activity = getActivity();
        this.mApplicationsState = ApplicationsState.getInstance(activity.getApplication());
        Intent intent = activity.getIntent();
        Bundle arguments = getArguments();
        int i = R.string.application_info_label;
        int intExtra = intent.getIntExtra(":settings:show_fragment_title_resid", R.string.application_info_label);
        String string = arguments != null ? arguments.getString("classname") : null;
        if (string == null) {
            string = intent.getComponent().getClassName();
        }
        if (string.equals(Settings$StorageUseActivity.class.getName())) {
            if (arguments != null && arguments.containsKey("volumeUuid")) {
                this.mVolumeUuid = arguments.getString("volumeUuid");
                this.mStorageType = arguments.getInt("storageType", 0);
                this.mListType = 3;
            } else {
                this.mListType = 0;
            }
            this.mSortOrder = R.id.sort_order_size;
        } else if (string.equals(Settings$UsageAccessSettingsActivity.class.getName())) {
            this.mListType = 4;
            intExtra = R.string.usage_access;
        } else if (string.equals(Settings$HighPowerApplicationsActivity.class.getName())) {
            this.mListType = 5;
            this.mShowSystem = true;
            intExtra = R.string.high_power_apps;
        } else if (string.equals(Settings$OverlaySettingsActivity.class.getName())) {
            this.mListType = 6;
            intExtra = R.string.system_alert_window_settings;
        } else if (string.equals(Settings$WriteSettingsActivity.class.getName())) {
            this.mListType = 7;
            intExtra = R.string.write_settings;
        } else if (string.equals(Settings$ManageExternalSourcesActivity.class.getName())) {
            this.mListType = 8;
            intExtra = R.string.install_other_apps;
        } else if (string.equals(Settings$GamesStorageActivity.class.getName())) {
            this.mListType = 9;
            this.mSortOrder = R.id.sort_order_size;
        } else if (string.equals(Settings$MoviesStorageActivity.class.getName())) {
            this.mListType = 10;
            this.mSortOrder = R.id.sort_order_size;
        } else if (string.equals(Settings$PhotosStorageActivity.class.getName())) {
            this.mListType = 11;
            this.mSortOrder = R.id.sort_order_size;
            this.mStorageType = arguments.getInt("storageType", 0);
        } else if (string.equals(Settings$ChangeWifiStateActivity.class.getName())) {
            this.mListType = 13;
            intExtra = R.string.change_wifi_state_title;
        } else if (string.equals(Settings$NotificationAppListActivity.class.getName())) {
            this.mListType = 1;
            this.mUsageStatsManager = IUsageStatsManager.Stub.asInterface(ServiceManager.getService("usagestats"));
            this.mUserManager = UserManager.get(getContext());
            this.mNotificationBackend = new NotificationBackend();
            this.mSortOrder = R.id.sort_order_recent_notification;
            intExtra = R.string.app_notifications_title;
        } else {
            if (intExtra != -1) {
                i = intExtra;
            }
            this.mListType = 0;
            intExtra = i;
        }
        AppFilterRegistry appFilterRegistry = AppFilterRegistry.getInstance();
        this.mFilter = appFilterRegistry.get(appFilterRegistry.getDefaultFilterType(this.mListType));
        this.mIsWorkOnly = arguments != null ? arguments.getBoolean("workProfileOnly") : false;
        this.mWorkUserId = arguments != null ? arguments.getInt("workId") : -1;
        if (bundle != null) {
            this.mSortOrder = bundle.getInt("sortOrder", this.mSortOrder);
            this.mShowSystem = bundle.getBoolean("showSystem", this.mShowSystem);
            this.mFilterType = bundle.getInt("filterType", 2);
        }
        this.mInvalidSizeStr = activity.getText(R.string.invalid_size_value);
        this.mResetAppsHelper = new ResetAppsHelper(activity);
        if (intExtra > 0) {
            activity.setTitle(intExtra);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mListType == 6 && !Utils.isSystemAlertWindowEnabled(getContext())) {
            this.mRootView = layoutInflater.inflate(R.layout.manage_applications_apps_unsupported, (ViewGroup) null);
            setHasOptionsMenu(false);
            return this.mRootView;
        }
        View viewInflate = layoutInflater.inflate(R.layout.manage_applications_apps, (ViewGroup) null);
        this.mRootView = viewInflate;
        this.mLoadingContainer = viewInflate.findViewById(R.id.loading_container);
        View viewFindViewById = this.mRootView.findViewById(R.id.list_container);
        this.mListContainer = viewFindViewById;
        if (viewFindViewById != null) {
            this.mEmptyView = viewFindViewById.findViewById(android.R.id.empty);
            ApplicationsAdapter applicationsAdapter = new ApplicationsAdapter(this.mApplicationsState, this, this.mFilter, bundle);
            this.mApplications = applicationsAdapter;
            if (bundle != null) {
                applicationsAdapter.mHasReceivedLoadEntries = bundle.getBoolean("hasEntries", false);
                this.mApplications.mHasReceivedBridgeCallback = bundle.getBoolean("hasBridge", false);
            }
            int userId = this.mIsWorkOnly ? this.mWorkUserId : UserHandle.getUserId(this.mCurrentUid);
            int i = this.mStorageType;
            if (i == 1) {
                Context context = getContext();
                this.mApplications.setExtraViewController(new MusicViewHolderController(context, new StorageStatsSource(context), this.mVolumeUuid, UserHandle.of(userId)));
            } else if (i == 3) {
                Context context2 = getContext();
                this.mApplications.setExtraViewController(new PhotosViewHolderController(context2, new StorageStatsSource(context2), this.mVolumeUuid, UserHandle.of(userId)));
            }
            RecyclerView recyclerView = (RecyclerView) this.mListContainer.findViewById(R.id.apps_list);
            this.mRecyclerView = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
            this.mRecyclerView.setAdapter(this.mApplications);
        }
        if (viewGroup instanceof PreferenceFrameLayout) {
            this.mRootView.getLayoutParams().removeBorders = true;
        }
        createHeader();
        this.mResetAppsHelper.onRestoreInstanceState(bundle);
        return this.mRootView;
    }

    void createHeader() {
        FragmentActivity activity = getActivity();
        FrameLayout frameLayout = (FrameLayout) this.mRootView.findViewById(R.id.pinned_header);
        View viewInflate = activity.getLayoutInflater().inflate(R.layout.apps_filter_spinner, (ViewGroup) frameLayout, false);
        this.mSpinnerHeader = viewInflate;
        this.mFilterSpinner = (Spinner) viewInflate.findViewById(R.id.filter_spinner);
        FilterSpinnerAdapter filterSpinnerAdapter = new FilterSpinnerAdapter(this);
        this.mFilterAdapter = filterSpinnerAdapter;
        this.mFilterSpinner.setAdapter((SpinnerAdapter) filterSpinnerAdapter);
        this.mFilterSpinner.setOnItemSelectedListener(this);
        frameLayout.addView(this.mSpinnerHeader, 0);
        AppFilterRegistry appFilterRegistry = AppFilterRegistry.getInstance();
        this.mFilterAdapter.enableFilter(appFilterRegistry.getDefaultFilterType(this.mListType));
        if (this.mListType == 0 && UserManager.get(getActivity()).getUserProfiles().size() > 1) {
            this.mFilterAdapter.enableFilter(8);
            this.mFilterAdapter.enableFilter(9);
        }
        if (this.mListType == 1) {
            this.mFilterAdapter.enableFilter(6);
            this.mFilterAdapter.enableFilter(7);
            this.mFilterAdapter.enableFilter(16);
            this.mFilterAdapter.disableFilter(2);
        }
        if (this.mListType == 5) {
            this.mFilterAdapter.enableFilter(1);
        }
        ApplicationsState.AppFilter compositeFilter = getCompositeFilter(this.mListType, this.mStorageType, this.mVolumeUuid);
        if (this.mIsWorkOnly) {
            compositeFilter = new ApplicationsState.CompoundFilter(compositeFilter, appFilterRegistry.get(9).getFilter());
        }
        if (compositeFilter != null) {
            this.mApplications.setCompositeFilter(compositeFilter);
        }
    }

    static ApplicationsState.AppFilter getCompositeFilter(int i, int i2, String str) {
        ApplicationsState.CompoundFilter compoundFilter;
        ApplicationsState.VolumeFilter volumeFilter = new ApplicationsState.VolumeFilter(str);
        if (i == 3) {
            if (i2 == 1) {
                compoundFilter = new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_AUDIO, volumeFilter);
            } else {
                if (i2 != 0) {
                    return volumeFilter;
                }
                compoundFilter = new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_OTHER_APPS, volumeFilter);
            }
            return compoundFilter;
        }
        if (i == 9) {
            return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_GAMES, volumeFilter);
        }
        if (i == 10) {
            return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_MOVIES, volumeFilter);
        }
        if (i == 11) {
            return new ApplicationsState.CompoundFilter(ApplicationsState.FILTER_PHOTOS, volumeFilter);
        }
        return null;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        switch (this.mListType) {
            case 0:
                return 65;
            case 1:
                return 133;
            case 2:
            case 12:
            default:
                return 0;
            case 3:
                return this.mStorageType == 1 ? 839 : 182;
            case 4:
                return 95;
            case 5:
                return 184;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
            case 7:
                return 221;
            case 8:
                return 808;
            case 9:
                return 838;
            case 10:
                return 935;
            case 11:
                return 1092;
            case 13:
                return 338;
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateView();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.resume(this.mSortOrder);
            this.mApplications.updateLoading();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mResetAppsHelper.onSaveInstanceState(bundle);
        bundle.putInt("sortOrder", this.mSortOrder);
        bundle.putBoolean("showSystem", this.mShowSystem);
        bundle.putBoolean("hasEntries", this.mApplications.mHasReceivedLoadEntries);
        bundle.putBoolean("hasBridge", this.mApplications.mHasReceivedBridgeCallback);
        bundle.putInt("filterType", this.mFilter.getFilterType());
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.onSaveInstanceState(bundle);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.pause();
        }
        this.mResetAppsHelper.stop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        ApplicationsAdapter applicationsAdapter = this.mApplications;
        if (applicationsAdapter != null) {
            applicationsAdapter.release();
        }
        this.mRootView = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        String str;
        if (i != 1 || (str = this.mCurrentPkgName) == null) {
            return;
        }
        int i3 = this.mListType;
        if (i3 == 1) {
            this.mApplications.mExtraInfoBridge.forceUpdate(this.mCurrentPkgName, this.mCurrentUid);
        } else if (i3 != 5 && i3 != 6 && i3 != 7) {
            this.mApplicationsState.requestSize(str, UserHandle.getUserId(this.mCurrentUid));
        } else {
            this.mApplications.mExtraInfoBridge.forceUpdate(this.mCurrentPkgName, this.mCurrentUid);
        }
    }

    private void startApplicationDetailsActivity() {
        switch (this.mListType) {
            case 1:
                startAppInfoFragment(AppNotificationSettings.class, R.string.notifications_title);
                break;
            case 2:
            case 12:
            default:
                startAppInfoFragment(AppInfoDashboardFragment.class, R.string.application_info_label);
                break;
            case 3:
                startAppInfoFragment(AppStorageSettings.class, R.string.storage_settings);
                break;
            case 4:
                startAppInfoFragment(UsageAccessDetails.class, R.string.usage_access);
                break;
            case 5:
                HighPowerDetail.show(this, this.mCurrentUid, this.mCurrentPkgName, 1);
                break;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                startAppInfoFragment(DrawOverlayDetails.class, R.string.overlay_settings);
                break;
            case 7:
                startAppInfoFragment(WriteSettingsDetails.class, R.string.write_system_settings);
                break;
            case 8:
                startAppInfoFragment(ExternalSourcesDetails.class, R.string.install_other_apps);
                break;
            case 9:
                startAppInfoFragment(AppStorageSettings.class, R.string.game_storage_settings);
                break;
            case 10:
                startAppInfoFragment(AppStorageSettings.class, R.string.storage_movies_tv);
                break;
            case 11:
                startAppInfoFragment(AppStorageSettings.class, R.string.storage_photos_videos);
                break;
            case 13:
                startAppInfoFragment(ChangeWifiStateDetails.class, R.string.change_wifi_state_title);
                break;
        }
    }

    private void startAppInfoFragment(Class<?> cls, int i) {
        AppInfoBase.startAppInfoFragment(cls, i, this.mCurrentPkgName, this.mCurrentUid, this, 1, getMetricsCategory());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        HelpUtils.prepareHelpMenuItem(activity, menu, getHelpResource(), ManageApplications.class.getName());
        this.mOptionsMenu = menu;
        menuInflater.inflate(R.menu.manage_apps, menu);
        MenuItem menuItemFindItem = menu.findItem(R.id.search_app_list_menu);
        if (menuItemFindItem != null) {
            SearchView searchView = (SearchView) menuItemFindItem.getActionView();
            this.mSearchView = searchView;
            searchView.setQueryHint(getText(R.string.search_settings));
            this.mSearchView.setOnQueryTextListener(this);
        }
        updateOptionsMenu();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(Menu menu) {
        updateOptionsMenu();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyOptionsMenu() {
        this.mOptionsMenu = null;
    }

    int getHelpResource() {
        switch (this.mListType) {
        }
        return R.string.help_uri_apps_overlay;
    }

    void updateOptionsMenu() {
        Menu menu = this.mOptionsMenu;
        if (menu == null) {
            return;
        }
        menu.findItem(R.id.advanced).setVisible(false);
        this.mOptionsMenu.findItem(R.id.sort_order_alpha).setVisible(this.mListType == 3 && this.mSortOrder != R.id.sort_order_alpha);
        this.mOptionsMenu.findItem(R.id.sort_order_size).setVisible(this.mListType == 3 && this.mSortOrder != R.id.sort_order_size);
        this.mOptionsMenu.findItem(R.id.show_system).setVisible((this.mShowSystem || this.mListType == 5) ? false : true);
        this.mOptionsMenu.findItem(R.id.hide_system).setVisible(this.mShowSystem && this.mListType != 5);
        this.mOptionsMenu.findItem(R.id.reset_app_preferences).setVisible(this.mListType == 0);
        this.mOptionsMenu.findItem(R.id.sort_order_recent_notification).setVisible(false);
        this.mOptionsMenu.findItem(R.id.sort_order_frequent_notification).setVisible(false);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (menuItem.getItemId()) {
            case R.id.advanced /* 2131361897 */:
                if (this.mListType != 1) {
                    startActivityForResult(new Intent("android.settings.MANAGE_DEFAULT_APPS_SETTINGS"), 2);
                }
                return true;
            case R.id.hide_system /* 2131362296 */:
            case R.id.show_system /* 2131362757 */:
                this.mShowSystem = !this.mShowSystem;
                this.mApplications.rebuild();
                break;
            case R.id.reset_app_preferences /* 2131362663 */:
                this.mResetAppsHelper.buildResetDialog();
                return true;
            case R.id.sort_order_alpha /* 2131362785 */:
            case R.id.sort_order_size /* 2131362788 */:
                ApplicationsAdapter applicationsAdapter = this.mApplications;
                if (applicationsAdapter != null) {
                    applicationsAdapter.rebuild(itemId);
                }
                break;
            default:
                return false;
        }
        updateOptionsMenu();
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.mApplications == null) {
            return;
        }
        int childAdapterPosition = this.mRecyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1) {
            Log.w("ManageApplications", "Cannot find position for child, skipping onClick handling");
            return;
        }
        if (this.mApplications.getApplicationCount() > childAdapterPosition) {
            ApplicationInfo applicationInfo = this.mApplications.getAppEntry(childAdapterPosition).info;
            this.mCurrentPkgName = applicationInfo.packageName;
            this.mCurrentUid = applicationInfo.uid;
            startApplicationDetailsActivity();
            return;
        }
        this.mApplications.mExtraViewController.onClick(this);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        AppFilterItem filter = this.mFilterAdapter.getFilter(i);
        this.mFilter = filter;
        this.mApplications.setFilter(filter);
        Log.d("ManageApplications", "Selecting filter " + ((Object) getContext().getText(this.mFilter.getTitle())));
    }

    @Override // android.widget.SearchView.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        this.mApplications.filterSearch(str);
        return false;
    }

    public void updateView() {
        updateOptionsMenu();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }

    public void setHasDisabled(boolean z) {
        if (this.mListType != 0) {
            return;
        }
        this.mFilterAdapter.setFilterEnabled(3, z);
        this.mFilterAdapter.setFilterEnabled(5, z);
    }

    public void setHasInstant(boolean z) {
        if (LIST_TYPES_WITH_INSTANT.contains(Integer.valueOf(this.mListType))) {
            this.mFilterAdapter.setFilterEnabled(4, z);
        }
    }

    static class FilterSpinnerAdapter extends SettingsSpinnerAdapter<CharSequence> {
        private final Context mContext;
        private final ArrayList<AppFilterItem> mFilterOptions;
        private final ManageApplications mManageApplications;

        public FilterSpinnerAdapter(ManageApplications manageApplications) {
            super(manageApplications.getContext());
            this.mFilterOptions = new ArrayList<>();
            this.mContext = manageApplications.getContext();
            this.mManageApplications = manageApplications;
        }

        public AppFilterItem getFilter(int i) {
            return this.mFilterOptions.get(i);
        }

        public void setFilterEnabled(int i, boolean z) {
            if (z) {
                enableFilter(i);
            } else {
                disableFilter(i);
            }
        }

        public void enableFilter(int i) {
            AppFilterItem appFilterItem = AppFilterRegistry.getInstance().get(i);
            if (this.mFilterOptions.contains(appFilterItem)) {
                return;
            }
            Log.d("ManageApplications", "Enabling filter " + ((Object) this.mContext.getText(appFilterItem.getTitle())));
            this.mFilterOptions.add(appFilterItem);
            Collections.sort(this.mFilterOptions);
            updateFilterView(this.mFilterOptions.size() > 1);
            notifyDataSetChanged();
            if (this.mFilterOptions.size() == 1) {
                Log.d("ManageApplications", "Auto selecting filter " + appFilterItem + " " + ((Object) this.mContext.getText(appFilterItem.getTitle())));
                this.mManageApplications.mFilterSpinner.setSelection(0);
                this.mManageApplications.onItemSelected(null, null, 0, 0L);
            }
            if (this.mFilterOptions.size() > 1) {
                int iIndexOf = this.mFilterOptions.indexOf(AppFilterRegistry.getInstance().get(this.mManageApplications.mFilterType));
                if (iIndexOf != -1) {
                    this.mManageApplications.mFilterSpinner.setSelection(iIndexOf);
                    this.mManageApplications.onItemSelected(null, null, iIndexOf, 0L);
                }
            }
        }

        public void disableFilter(int i) {
            AppFilterItem appFilterItem = AppFilterRegistry.getInstance().get(i);
            if (this.mFilterOptions.remove(appFilterItem)) {
                Log.d("ManageApplications", "Disabling filter " + appFilterItem + " " + ((Object) this.mContext.getText(appFilterItem.getTitle())));
                Collections.sort(this.mFilterOptions);
                updateFilterView(this.mFilterOptions.size() > 1);
                notifyDataSetChanged();
                if (this.mManageApplications.mFilter != appFilterItem || this.mFilterOptions.size() <= 0) {
                    return;
                }
                Log.d("ManageApplications", "Auto selecting filter " + this.mFilterOptions.get(0) + ((Object) this.mContext.getText(this.mFilterOptions.get(0).getTitle())));
                this.mManageApplications.mFilterSpinner.setSelection(0);
                this.mManageApplications.onItemSelected(null, null, 0, 0L);
            }
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.mFilterOptions.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public CharSequence getItem(int i) {
            return this.mContext.getText(this.mFilterOptions.get(i).getTitle());
        }

        void updateFilterView(boolean z) {
            if (z) {
                this.mManageApplications.mSpinnerHeader.setVisibility(0);
                this.mManageApplications.mRecyclerView.setPadding(0, this.mContext.getResources().getDimensionPixelSize(R.dimen.app_bar_height), 0, 0);
            } else {
                this.mManageApplications.mSpinnerHeader.setVisibility(8);
                this.mManageApplications.mRecyclerView.setPadding(0, 0, 0, 0);
            }
        }
    }

    static class ApplicationsAdapter extends RecyclerView.Adapter<ApplicationViewHolder> implements ApplicationsState.Callbacks, AppStateBaseBridge.Callback {
        private AppFilterItem mAppFilter;
        private PowerWhitelistBackend mBackend;
        private ApplicationsState.AppFilter mCompositeFilter;
        private final Context mContext;
        private ArrayList<ApplicationsState.AppEntry> mEntries;
        private final AppStateBaseBridge mExtraInfoBridge;
        private FileViewHolderController mExtraViewController;
        private boolean mHasReceivedBridgeCallback;
        private boolean mHasReceivedLoadEntries;
        private final IconDrawableFactory mIconDrawableFactory;
        private int mLastIndex;
        private final LoadingViewController mLoadingViewController;
        private final ManageApplications mManageApplications;
        OnScrollListener mOnScrollListener;
        private ArrayList<ApplicationsState.AppEntry> mOriginalEntries;
        private RecyclerView mRecyclerView;
        private boolean mResumed;
        private SearchFilter mSearchFilter;
        private final ApplicationsState.Session mSession;
        private final ApplicationsState mState;
        private int mLastSortMode = -1;
        private int mWhichSize = 0;

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageIconChanged() {
        }

        public ApplicationsAdapter(ApplicationsState applicationsState, ManageApplications manageApplications, AppFilterItem appFilterItem, Bundle bundle) {
            this.mLastIndex = -1;
            setHasStableIds(true);
            this.mState = applicationsState;
            this.mSession = applicationsState.newSession(this);
            this.mManageApplications = manageApplications;
            this.mLoadingViewController = new LoadingViewController(manageApplications.mLoadingContainer, this.mManageApplications.mListContainer);
            FragmentActivity activity = manageApplications.getActivity();
            this.mContext = activity;
            this.mIconDrawableFactory = IconDrawableFactory.newInstance(activity);
            this.mAppFilter = appFilterItem;
            this.mBackend = PowerWhitelistBackend.getInstance(this.mContext);
            int i = this.mManageApplications.mListType;
            if (i == 1) {
                this.mExtraInfoBridge = new AppStateNotificationBridge(this.mContext, this.mState, this, manageApplications.mUsageStatsManager, manageApplications.mUserManager, manageApplications.mNotificationBackend);
            } else if (i == 4) {
                this.mExtraInfoBridge = new AppStateUsageBridge(this.mContext, this.mState, this);
            } else if (i == 5) {
                this.mExtraInfoBridge = new AppStatePowerBridge(this.mContext, this.mState, this);
            } else if (i == 6) {
                this.mExtraInfoBridge = new AppStateOverlayBridge(this.mContext, this.mState, this);
            } else if (i == 7) {
                this.mExtraInfoBridge = new AppStateWriteSettingsBridge(this.mContext, this.mState, this);
            } else if (i == 8) {
                this.mExtraInfoBridge = new AppStateInstallAppsBridge(this.mContext, this.mState, this);
            } else if (i == 13) {
                this.mExtraInfoBridge = new AppStateChangeWifiStateBridge(this.mContext, this.mState, this);
            } else {
                this.mExtraInfoBridge = null;
            }
            if (bundle != null) {
                this.mLastIndex = bundle.getInt("state_last_scroll_index");
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            this.mRecyclerView = recyclerView;
            OnScrollListener onScrollListener = new OnScrollListener(this);
            this.mOnScrollListener = onScrollListener;
            this.mRecyclerView.addOnScrollListener(onScrollListener);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
            this.mOnScrollListener = null;
            this.mRecyclerView = null;
        }

        public void setCompositeFilter(ApplicationsState.AppFilter appFilter) {
            this.mCompositeFilter = appFilter;
            rebuild();
        }

        public void setFilter(AppFilterItem appFilterItem) {
            this.mAppFilter = appFilterItem;
            if (7 == appFilterItem.getFilterType()) {
                rebuild(R.id.sort_order_frequent_notification);
                return;
            }
            if (6 == appFilterItem.getFilterType()) {
                rebuild(R.id.sort_order_recent_notification);
            } else if (16 == appFilterItem.getFilterType()) {
                rebuild(R.id.sort_order_alpha);
            } else {
                rebuild();
            }
        }

        public void setExtraViewController(FileViewHolderController fileViewHolderController) {
            this.mExtraViewController = fileViewHolderController;
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.applications.manageapplications.-$$Lambda$ManageApplications$ApplicationsAdapter$UlRwRvnIF-4T50JL-95-0WPovAo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setExtraViewController$1$ManageApplications$ApplicationsAdapter();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$setExtraViewController$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$setExtraViewController$1$ManageApplications$ApplicationsAdapter() {
            this.mExtraViewController.queryStats();
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.applications.manageapplications.-$$Lambda$ManageApplications$ApplicationsAdapter$dLz1ZVNR2pKj69pcJ4OCb87JY08
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setExtraViewController$0$ManageApplications$ApplicationsAdapter();
                }
            });
        }

        public void resume(int i) {
            Log.i("ManageApplications", "Resume!  mResumed=" + this.mResumed);
            if (!this.mResumed) {
                this.mResumed = true;
                this.mSession.onResume();
                this.mLastSortMode = i;
                AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
                if (appStateBaseBridge != null) {
                    appStateBaseBridge.resume();
                }
                rebuild();
                return;
            }
            rebuild(i);
        }

        public void pause() {
            if (this.mResumed) {
                this.mResumed = false;
                this.mSession.onPause();
                AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
                if (appStateBaseBridge != null) {
                    appStateBaseBridge.pause();
                }
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            bundle.putInt("state_last_scroll_index", ((LinearLayoutManager) this.mManageApplications.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition());
        }

        public void release() {
            this.mSession.onDestroy();
            AppStateBaseBridge appStateBaseBridge = this.mExtraInfoBridge;
            if (appStateBaseBridge != null) {
                appStateBaseBridge.release();
            }
        }

        public void rebuild(int i) {
            if (i == this.mLastSortMode) {
                return;
            }
            this.mManageApplications.mSortOrder = i;
            this.mLastSortMode = i;
            rebuild();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ApplicationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View viewNewView;
            if (this.mManageApplications.mListType == 1) {
                viewNewView = ApplicationViewHolder.newView(viewGroup, true);
            } else {
                viewNewView = ApplicationViewHolder.newView(viewGroup, false);
            }
            return new ApplicationViewHolder(viewNewView);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return (hasExtraView() && (getItemCount() - 1 == i)) ? 1 : 0;
        }

        public void rebuild() {
            final Comparator<ApplicationsState.AppEntry> comparator;
            ApplicationsState.CompoundFilter compoundFilter;
            if (!this.mHasReceivedLoadEntries || (this.mExtraInfoBridge != null && !this.mHasReceivedBridgeCallback)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Not rebuilding until all the app entries loaded. !mHasReceivedLoadEntries=");
                sb.append(!this.mHasReceivedLoadEntries);
                sb.append(" !mExtraInfoBridgeNull=");
                sb.append(this.mExtraInfoBridge != null);
                sb.append(" !mHasReceivedBridgeCallback=");
                sb.append(!this.mHasReceivedBridgeCallback);
                Log.d("ManageApplications", sb.toString());
                return;
            }
            if (Environment.isExternalStorageEmulated()) {
                this.mWhichSize = 0;
            } else {
                this.mWhichSize = 1;
            }
            ApplicationsState.AppFilter filter = this.mAppFilter.getFilter();
            ApplicationsState.AppFilter appFilter = this.mCompositeFilter;
            if (appFilter != null) {
                filter = new ApplicationsState.CompoundFilter(filter, appFilter);
            }
            if (!this.mManageApplications.mShowSystem) {
                if (ManageApplications.LIST_TYPES_WITH_INSTANT.contains(Integer.valueOf(this.mManageApplications.mListType))) {
                    compoundFilter = new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER_AND_INSTANT);
                } else {
                    compoundFilter = new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER);
                }
                filter = compoundFilter;
            }
            switch (this.mLastSortMode) {
                case R.id.sort_order_frequent_notification /* 2131362786 */:
                    comparator = AppStateNotificationBridge.FREQUENCY_NOTIFICATION_COMPARATOR;
                    break;
                case R.id.sort_order_recent_notification /* 2131362787 */:
                    comparator = AppStateNotificationBridge.RECENT_NOTIFICATION_COMPARATOR;
                    break;
                case R.id.sort_order_size /* 2131362788 */:
                    int i = this.mWhichSize;
                    if (i == 1) {
                        comparator = ApplicationsState.INTERNAL_SIZE_COMPARATOR;
                    } else if (i == 2) {
                        comparator = ApplicationsState.EXTERNAL_SIZE_COMPARATOR;
                    } else {
                        comparator = ApplicationsState.SIZE_COMPARATOR;
                    }
                    break;
                default:
                    comparator = ApplicationsState.ALPHA_COMPARATOR;
                    break;
            }
            final ApplicationsState.CompoundFilter compoundFilter2 = new ApplicationsState.CompoundFilter(filter, ApplicationsState.FILTER_NOT_HIDE);
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.applications.manageapplications.-$$Lambda$ManageApplications$ApplicationsAdapter$90_FwNGIl12euOE7utj_O6oZ-KM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$rebuild$3$ManageApplications$ApplicationsAdapter(compoundFilter2, comparator);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$rebuild$3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$rebuild$3$ManageApplications$ApplicationsAdapter(ApplicationsState.AppFilter appFilter, Comparator comparator) {
            final ArrayList<ApplicationsState.AppEntry> arrayListRebuild = this.mSession.rebuild(appFilter, comparator, false);
            if (arrayListRebuild != null) {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.car.developeroptions.applications.manageapplications.-$$Lambda$ManageApplications$ApplicationsAdapter$Bkhtc8l9211TDTb-l0gAdv9vlxg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$rebuild$2$ManageApplications$ApplicationsAdapter(arrayListRebuild);
                    }
                });
            }
        }

        public void filterSearch(String str) {
            if (this.mSearchFilter == null) {
                this.mSearchFilter = new SearchFilter();
            }
            this.mSearchFilter.filter(str);
        }

        private static boolean packageNameEquals(PackageItemInfo packageItemInfo, PackageItemInfo packageItemInfo2) {
            String str;
            String str2;
            if (packageItemInfo == null || packageItemInfo2 == null || (str = packageItemInfo.packageName) == null || (str2 = packageItemInfo2.packageName) == null) {
                return false;
            }
            return str.equals(str2);
        }

        private ArrayList<ApplicationsState.AppEntry> removeDuplicateIgnoringUser(ArrayList<ApplicationsState.AppEntry> arrayList) {
            int size = arrayList.size();
            ArrayList<ApplicationsState.AppEntry> arrayList2 = new ArrayList<>(size);
            ApplicationInfo applicationInfo = null;
            int i = 0;
            while (i < size) {
                ApplicationsState.AppEntry appEntry = arrayList.get(i);
                ApplicationInfo applicationInfo2 = appEntry.info;
                if (!packageNameEquals(applicationInfo, applicationInfo2)) {
                    arrayList2.add(appEntry);
                }
                i++;
                applicationInfo = applicationInfo2;
            }
            arrayList2.trimToSize();
            return arrayList2;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        /* JADX INFO: renamed from: onRebuildComplete, reason: merged with bridge method [inline-methods] */
        public void lambda$rebuild$2$ManageApplications$ApplicationsAdapter(ArrayList<ApplicationsState.AppEntry> arrayList) {
            Log.d("ManageApplications", "onRebuildComplete");
            int filterType = this.mAppFilter.getFilterType();
            if (filterType == 0 || filterType == 1) {
                arrayList = removeDuplicateIgnoringUser(arrayList);
            }
            this.mEntries = arrayList;
            this.mOriginalEntries = arrayList;
            notifyDataSetChanged();
            if (getItemCount() != 0) {
                this.mManageApplications.mEmptyView.setVisibility(8);
                this.mManageApplications.mRecyclerView.setVisibility(0);
                if (this.mManageApplications.mSearchView != null && this.mManageApplications.mSearchView.isVisibleToUser()) {
                    CharSequence query = this.mManageApplications.mSearchView.getQuery();
                    if (!TextUtils.isEmpty(query)) {
                        filterSearch(query.toString());
                    }
                }
            } else {
                this.mManageApplications.mRecyclerView.setVisibility(8);
                this.mManageApplications.mEmptyView.setVisibility(0);
            }
            if (this.mLastIndex != -1 && getItemCount() > this.mLastIndex) {
                this.mManageApplications.mRecyclerView.getLayoutManager().scrollToPosition(this.mLastIndex);
                this.mLastIndex = -1;
            }
            if (this.mSession.getAllApps().size() != 0 && this.mManageApplications.mListContainer.getVisibility() != 0) {
                this.mLoadingViewController.showContent(true);
            }
            ManageApplications manageApplications = this.mManageApplications;
            if (manageApplications.mListType == 4) {
                return;
            }
            manageApplications.setHasDisabled(this.mState.haveDisabledApps());
            this.mManageApplications.setHasInstant(this.mState.haveInstantApps());
        }

        void updateLoading() {
            if (this.mHasReceivedLoadEntries && this.mSession.getAllApps().size() != 0) {
                this.mLoadingViewController.showContent(false);
            } else {
                this.mLoadingViewController.showLoadingViewDelayed();
            }
        }

        @Override // com.android.car.developeroptions.applications.AppStateBaseBridge.Callback
        public void onExtraInfoUpdated() {
            this.mHasReceivedBridgeCallback = true;
            rebuild();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onRunningStateChanged(boolean z) {
            this.mManageApplications.getActivity().setProgressBarIndeterminateVisibility(z);
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageListChanged() {
            rebuild();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onLoadEntriesCompleted() {
            this.mHasReceivedLoadEntries = true;
            rebuild();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onPackageSizeChanged(String str) {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList == null) {
                return;
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ApplicationInfo applicationInfo = this.mEntries.get(i).info;
                if (applicationInfo != null || TextUtils.equals(str, applicationInfo.packageName)) {
                    if (TextUtils.equals(this.mManageApplications.mCurrentPkgName, applicationInfo.packageName)) {
                        rebuild();
                        return;
                    }
                    this.mOnScrollListener.postNotifyItemChange(i);
                }
            }
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onLauncherInfoChanged() {
            if (this.mManageApplications.mShowSystem) {
                return;
            }
            rebuild();
        }

        @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
        public void onAllSizesComputed() {
            if (this.mLastSortMode == R.id.sort_order_size) {
                rebuild();
            }
        }

        /* JADX INFO: renamed from: onExtraViewCompleted, reason: merged with bridge method [inline-methods] */
        public void lambda$setExtraViewController$0$ManageApplications$ApplicationsAdapter() {
            if (hasExtraView()) {
                notifyItemChanged(getItemCount() - 1);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList == null) {
                return 0;
            }
            return arrayList.size() + (hasExtraView() ? 1 : 0);
        }

        public int getApplicationCount() {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public ApplicationsState.AppEntry getAppEntry(int i) {
            return this.mEntries.get(i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int i) {
            if (i == this.mEntries.size()) {
                return -1L;
            }
            return this.mEntries.get(i).id;
        }

        public boolean isEnabled(int i) {
            if (getItemViewType(i) == 1 || this.mManageApplications.mListType != 5) {
                return true;
            }
            ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
            return (this.mBackend.isSysWhitelisted(appEntry.info.packageName) || this.mBackend.isDefaultActiveApp(appEntry.info.packageName)) ? false : true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ApplicationViewHolder applicationViewHolder, int i) {
            ArrayList<ApplicationsState.AppEntry> arrayList = this.mEntries;
            if (arrayList != null && this.mExtraViewController != null && i == arrayList.size()) {
                this.mExtraViewController.setupView(applicationViewHolder);
            } else {
                ApplicationsState.AppEntry appEntry = this.mEntries.get(i);
                synchronized (appEntry) {
                    applicationViewHolder.setTitle(appEntry.label);
                    applicationViewHolder.setIcon(this.mIconDrawableFactory.getBadgedIcon(appEntry.info));
                    updateSummary(applicationViewHolder, appEntry);
                    updateSwitch(applicationViewHolder, appEntry);
                    applicationViewHolder.updateDisableView(appEntry.info);
                }
                applicationViewHolder.setEnabled(isEnabled(i));
            }
            applicationViewHolder.itemView.setOnClickListener(this.mManageApplications);
        }

        private void updateSummary(ApplicationViewHolder applicationViewHolder, ApplicationsState.AppEntry appEntry) {
            ManageApplications manageApplications = this.mManageApplications;
            int i = manageApplications.mListType;
            if (i == 1) {
                Object obj = appEntry.extraInfo;
                if (obj != null && (obj instanceof AppStateNotificationBridge.NotificationsSentState)) {
                    applicationViewHolder.setSummary(AppStateNotificationBridge.getSummary(this.mContext, (AppStateNotificationBridge.NotificationsSentState) obj, this.mLastSortMode));
                    return;
                } else {
                    applicationViewHolder.setSummary((CharSequence) null);
                    return;
                }
            }
            if (i != 13) {
                switch (i) {
                    case 4:
                        Object obj2 = appEntry.extraInfo;
                        if (obj2 != null) {
                            applicationViewHolder.setSummary(new AppStateUsageBridge.UsageState((AppStateAppOpsBridge.PermissionState) obj2).isPermissible() ? R.string.app_permission_summary_allowed : R.string.app_permission_summary_not_allowed);
                        } else {
                            applicationViewHolder.setSummary((CharSequence) null);
                        }
                        break;
                    case 5:
                        applicationViewHolder.setSummary(HighPowerDetail.getSummary(this.mContext, appEntry));
                        break;
                    case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                        applicationViewHolder.setSummary(DrawOverlayDetails.getSummary(this.mContext, appEntry));
                        break;
                    case 7:
                        applicationViewHolder.setSummary(WriteSettingsDetails.getSummary(this.mContext, appEntry));
                        break;
                    case 8:
                        applicationViewHolder.setSummary(ExternalSourcesDetails.getPreferenceSummary(this.mContext, appEntry));
                        break;
                    default:
                        applicationViewHolder.updateSizeText(appEntry, manageApplications.mInvalidSizeStr, this.mWhichSize);
                        break;
                }
                return;
            }
            applicationViewHolder.setSummary(ChangeWifiStateDetails.getSummary(this.mContext, appEntry));
        }

        private void updateSwitch(ApplicationViewHolder applicationViewHolder, ApplicationsState.AppEntry appEntry) {
            if (this.mManageApplications.mListType != 1) {
                return;
            }
            applicationViewHolder.updateSwitch(((AppStateNotificationBridge) this.mExtraInfoBridge).getSwitchOnClickListener(appEntry), AppStateNotificationBridge.enableSwitch(appEntry), AppStateNotificationBridge.checkSwitch(appEntry));
            Object obj = appEntry.extraInfo;
            if (obj != null && (obj instanceof AppStateNotificationBridge.NotificationsSentState)) {
                applicationViewHolder.setSummary(AppStateNotificationBridge.getSummary(this.mContext, (AppStateNotificationBridge.NotificationsSentState) obj, this.mLastSortMode));
            } else {
                applicationViewHolder.setSummary((CharSequence) null);
            }
        }

        private boolean hasExtraView() {
            FileViewHolderController fileViewHolderController = this.mExtraViewController;
            return fileViewHolderController != null && fileViewHolderController.shouldShow();
        }

        public static class OnScrollListener extends RecyclerView.OnScrollListener {
            private ApplicationsAdapter mAdapter;
            private boolean mDelayNotifyDataChange;
            private int mScrollState = 0;

            public OnScrollListener(ApplicationsAdapter applicationsAdapter) {
                this.mAdapter = applicationsAdapter;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                this.mScrollState = i;
                if (i == 0 && this.mDelayNotifyDataChange) {
                    this.mDelayNotifyDataChange = false;
                    this.mAdapter.notifyDataSetChanged();
                }
            }

            public void postNotifyItemChange(int i) {
                if (this.mScrollState == 0) {
                    this.mAdapter.notifyItemChanged(i);
                } else {
                    this.mDelayNotifyDataChange = true;
                }
            }
        }

        private class SearchFilter extends Filter {
            private SearchFilter() {
            }

            @Override // android.widget.Filter
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                ArrayList arrayList;
                if (TextUtils.isEmpty(charSequence)) {
                    arrayList = ApplicationsAdapter.this.mOriginalEntries;
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (ApplicationsState.AppEntry appEntry : ApplicationsAdapter.this.mOriginalEntries) {
                        if (appEntry.label.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            arrayList2.add(appEntry);
                        }
                    }
                    arrayList = arrayList2;
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = arrayList;
                filterResults.count = arrayList.size();
                return filterResults;
            }

            @Override // android.widget.Filter
            protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                ApplicationsAdapter.this.mEntries = (ArrayList) filterResults.values;
                ApplicationsAdapter.this.notifyDataSetChanged();
            }
        }
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mLoader;

        private SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mLoader = summaryLoader;
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            if (z) {
                Context context = this.mContext;
                new InstalledAppCounter(context, -1, context.getPackageManager()) { // from class: com.android.car.developeroptions.applications.manageapplications.ManageApplications.SummaryProvider.1
                    @Override // com.android.car.developeroptions.applications.AppCounter
                    protected void onCountComplete(int i) {
                        SummaryLoader summaryLoader = SummaryProvider.this.mLoader;
                        SummaryProvider summaryProvider = SummaryProvider.this;
                        summaryLoader.setSummary(summaryProvider, summaryProvider.mContext.getString(R.string.apps_summary, Integer.valueOf(i)));
                    }
                }.execute(new Void[0]);
            }
        }
    }
}
