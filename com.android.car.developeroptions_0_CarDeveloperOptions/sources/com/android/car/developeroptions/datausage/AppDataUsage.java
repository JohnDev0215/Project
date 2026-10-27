package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.UserHandle;
import android.telephony.SubscriptionManager;
import android.util.ArraySet;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.datausage.DataSaverBackend;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.AppItem;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedSwitchPreference;
import com.android.settingslib.net.NetworkCycleDataForUid;
import com.android.settingslib.net.NetworkCycleDataForUidLoader;
import com.android.settingslib.net.UidDetail;
import com.android.settingslib.net.UidDetailProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppDataUsage extends DataUsageBaseFragment implements Preference.OnPreferenceChangeListener, DataSaverBackend.Listener {
    private AppItem mAppItem;
    private PreferenceCategory mAppList;
    private Preference mAppSettings;
    private Intent mAppSettingsIntent;
    private Preference mBackgroundUsage;
    private Context mContext;
    private SpinnerPreference mCycle;
    private CycleAdapter mCycleAdapter;
    private ArrayList<Long> mCycles;
    private DataSaverBackend mDataSaverBackend;
    private Preference mForegroundUsage;
    private Drawable mIcon;
    CharSequence mLabel;
    private PackageManager mPackageManager;
    String mPackageName;
    private RestrictedSwitchPreference mRestrictBackground;
    private long mSelectedCycle;
    NetworkTemplate mTemplate;
    private Preference mTotalUsage;
    private RestrictedSwitchPreference mUnrestrictedData;
    private List<NetworkCycleDataForUid> mUsageData;
    private final ArraySet<String> mPackages = new ArraySet<>();
    private AdapterView.OnItemSelectedListener mCycleListener = new AdapterView.OnItemSelectedListener() { // from class: com.android.car.developeroptions.datausage.AppDataUsage.1
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            AppDataUsage.this.bindData(i);
        }
    };
    final LoaderManager.LoaderCallbacks<List<NetworkCycleDataForUid>> mUidDataCallbacks = new LoaderManager.LoaderCallbacks<List<NetworkCycleDataForUid>>() { // from class: com.android.car.developeroptions.datausage.AppDataUsage.2
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<NetworkCycleDataForUid>> loader) {
        }

        /* JADX WARN: Type inference failed for: r1v1, types: [androidx.loader.content.Loader<java.util.List<com.android.settingslib.net.NetworkCycleDataForUid>>, com.android.settingslib.net.NetworkCycleDataLoader] */
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<NetworkCycleDataForUid>> onCreateLoader(int i, Bundle bundle) {
            NetworkCycleDataForUidLoader.Builder<?> builder = NetworkCycleDataForUidLoader.builder(AppDataUsage.this.mContext);
            builder.setRetrieveDetail(true);
            builder.setNetworkTemplate(AppDataUsage.this.mTemplate);
            if (AppDataUsage.this.mAppItem.category == 0) {
                for (int i2 = 0; i2 < AppDataUsage.this.mAppItem.uids.size(); i2++) {
                    builder.addUid(AppDataUsage.this.mAppItem.uids.keyAt(i2));
                }
            } else {
                builder.addUid(AppDataUsage.this.mAppItem.key);
            }
            if (AppDataUsage.this.mCycles != null) {
                builder.setCycles(AppDataUsage.this.mCycles);
            }
            return builder.build();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<NetworkCycleDataForUid>> loader, List<NetworkCycleDataForUid> list) {
            AppDataUsage.this.mUsageData = list;
            AppDataUsage.this.mCycleAdapter.updateCycleList(list);
            int i = 0;
            if (AppDataUsage.this.mSelectedCycle > 0) {
                int size = list.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (list.get(i2).getEndTime() == AppDataUsage.this.mSelectedCycle) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
                if (i > 0) {
                    AppDataUsage.this.mCycle.setSelection(i);
                }
                AppDataUsage.this.bindData(i);
                return;
            }
            AppDataUsage.this.bindData(0);
        }
    };
    private final LoaderManager.LoaderCallbacks<ArraySet<Preference>> mAppPrefCallbacks = new LoaderManager.LoaderCallbacks<ArraySet<Preference>>() { // from class: com.android.car.developeroptions.datausage.AppDataUsage.3
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<ArraySet<Preference>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<ArraySet<Preference>> onCreateLoader(int i, Bundle bundle) {
            return new AppPrefLoader(AppDataUsage.this.getPrefContext(), AppDataUsage.this.mPackages, AppDataUsage.this.getPackageManager());
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<ArraySet<Preference>> loader, ArraySet<Preference> arraySet) {
            if (arraySet == null || AppDataUsage.this.mAppList == null) {
                return;
            }
            Iterator<Preference> it = arraySet.iterator();
            while (it.hasNext()) {
                AppDataUsage.this.mAppList.addPreference(it.next());
            }
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AppDataUsage";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 343;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_data_usage;
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onDataSaverChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        int intExtra;
        super.onCreate(bundle);
        this.mContext = getContext();
        this.mPackageManager = getPackageManager();
        Bundle arguments = getArguments();
        this.mAppItem = arguments != null ? (AppItem) arguments.getParcelable("app_item") : null;
        this.mTemplate = arguments != null ? (NetworkTemplate) arguments.getParcelable("network_template") : null;
        this.mCycles = arguments != null ? (ArrayList) arguments.getSerializable("network_cycles") : null;
        this.mSelectedCycle = arguments != null ? arguments.getLong("selected_cycle") : 0L;
        if (this.mTemplate == null) {
            this.mTemplate = DataUsageUtils.getDefaultTemplate(this.mContext, SubscriptionManager.getDefaultDataSubscriptionId());
        }
        boolean z = false;
        if (this.mAppItem == null) {
            if (arguments != null) {
                intExtra = arguments.getInt("uid", -1);
            } else {
                intExtra = getActivity().getIntent().getIntExtra("uid", -1);
            }
            if (intExtra == -1) {
                getActivity().finish();
            } else {
                addUid(intExtra);
                AppItem appItem = new AppItem(intExtra);
                this.mAppItem = appItem;
                appItem.addUid(intExtra);
            }
        } else {
            for (int i = 0; i < this.mAppItem.uids.size(); i++) {
                addUid(this.mAppItem.uids.keyAt(i));
            }
        }
        this.mTotalUsage = findPreference("total_usage");
        this.mForegroundUsage = findPreference("foreground_usage");
        this.mBackgroundUsage = findPreference("background_usage");
        this.mCycle = (SpinnerPreference) findPreference("cycle");
        this.mCycleAdapter = new CycleAdapter(this.mContext, this.mCycle, this.mCycleListener);
        UidDetailProvider uidDetailProvider = getUidDetailProvider();
        int i2 = this.mAppItem.key;
        if (i2 > 0) {
            if (!UserHandle.isApp(i2)) {
                UidDetail uidDetail = uidDetailProvider.getUidDetail(this.mAppItem.key, true);
                this.mIcon = uidDetail.icon;
                this.mLabel = uidDetail.label;
                removePreference("unrestricted_data_saver");
                removePreference("restrict_background");
            } else {
                if (this.mPackages.size() != 0) {
                    try {
                        ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(this.mPackages.valueAt(0), 0, UserHandle.getUserId(this.mAppItem.key));
                        this.mIcon = IconDrawableFactory.newInstance(getActivity()).getBadgedIcon(applicationInfoAsUser);
                        this.mLabel = applicationInfoAsUser.loadLabel(this.mPackageManager);
                        this.mPackageName = applicationInfoAsUser.packageName;
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                RestrictedSwitchPreference restrictedSwitchPreference = (RestrictedSwitchPreference) findPreference("restrict_background");
                this.mRestrictBackground = restrictedSwitchPreference;
                restrictedSwitchPreference.setOnPreferenceChangeListener(this);
                RestrictedSwitchPreference restrictedSwitchPreference2 = (RestrictedSwitchPreference) findPreference("unrestricted_data_saver");
                this.mUnrestrictedData = restrictedSwitchPreference2;
                restrictedSwitchPreference2.setOnPreferenceChangeListener(this);
            }
            this.mDataSaverBackend = new DataSaverBackend(this.mContext);
            this.mAppSettings = findPreference("app_settings");
            Intent intent = new Intent("android.intent.action.MANAGE_NETWORK_USAGE");
            this.mAppSettingsIntent = intent;
            intent.addCategory("android.intent.category.DEFAULT");
            PackageManager packageManager = getPackageManager();
            Iterator<String> it = this.mPackages.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                this.mAppSettingsIntent.setPackage(it.next());
                if (packageManager.resolveActivity(this.mAppSettingsIntent, 0) != null) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                removePreference("app_settings");
                this.mAppSettings = null;
            }
            if (this.mPackages.size() > 1) {
                this.mAppList = (PreferenceCategory) findPreference("app_list");
                LoaderManager.getInstance(this).restartLoader(3, Bundle.EMPTY, this.mAppPrefCallbacks);
                return;
            } else {
                removePreference("app_list");
                return;
            }
        }
        FragmentActivity activity = getActivity();
        UidDetail uidDetail2 = uidDetailProvider.getUidDetail(this.mAppItem.key, true);
        this.mIcon = uidDetail2.icon;
        this.mLabel = uidDetail2.label;
        this.mPackageName = activity.getPackageName();
        removePreference("unrestricted_data_saver");
        removePreference("app_settings");
        removePreference("restrict_background");
        removePreference("app_list");
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        DataSaverBackend dataSaverBackend = this.mDataSaverBackend;
        if (dataSaverBackend != null) {
            dataSaverBackend.addListener(this);
        }
        LoaderManager.getInstance(this).restartLoader(2, null, this.mUidDataCallbacks);
        updatePrefs();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        DataSaverBackend dataSaverBackend = this.mDataSaverBackend;
        if (dataSaverBackend != null) {
            dataSaverBackend.remListener(this);
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (preference == this.mRestrictBackground) {
            this.mDataSaverBackend.setIsBlacklisted(this.mAppItem.key, this.mPackageName, !((Boolean) obj).booleanValue());
            updatePrefs();
            return true;
        }
        if (preference != this.mUnrestrictedData) {
            return false;
        }
        this.mDataSaverBackend.setIsWhitelisted(this.mAppItem.key, this.mPackageName, ((Boolean) obj).booleanValue());
        return true;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference == this.mAppSettings) {
            getActivity().startActivityAsUser(this.mAppSettingsIntent, new UserHandle(UserHandle.getUserId(this.mAppItem.key)));
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    void updatePrefs() {
        updatePrefs(getAppRestrictBackground(), getUnrestrictData());
    }

    UidDetailProvider getUidDetailProvider() {
        return new UidDetailProvider(this.mContext);
    }

    private void updatePrefs(boolean z, boolean z2) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfMeteredDataRestricted = RestrictedLockUtilsInternal.checkIfMeteredDataRestricted(this.mContext, this.mPackageName, UserHandle.getUserId(this.mAppItem.key));
        RestrictedSwitchPreference restrictedSwitchPreference = this.mRestrictBackground;
        if (restrictedSwitchPreference != null) {
            restrictedSwitchPreference.setChecked(!z);
            this.mRestrictBackground.setDisabledByAdmin(enforcedAdminCheckIfMeteredDataRestricted);
        }
        RestrictedSwitchPreference restrictedSwitchPreference2 = this.mUnrestrictedData;
        if (restrictedSwitchPreference2 != null) {
            if (z) {
                restrictedSwitchPreference2.setVisible(false);
                return;
            }
            restrictedSwitchPreference2.setVisible(true);
            this.mUnrestrictedData.setChecked(z2);
            this.mUnrestrictedData.setDisabledByAdmin(enforcedAdminCheckIfMeteredDataRestricted);
        }
    }

    private void addUid(int i) {
        String[] packagesForUid = this.mPackageManager.getPackagesForUid(i);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                this.mPackages.add(str);
            }
        }
    }

    void bindData(int i) {
        long backgroudUsage;
        long foregroudUsage;
        List<NetworkCycleDataForUid> list = this.mUsageData;
        if (list == null || i >= list.size()) {
            backgroudUsage = 0;
            this.mCycle.setVisible(false);
            foregroudUsage = 0;
        } else {
            this.mCycle.setVisible(true);
            NetworkCycleDataForUid networkCycleDataForUid = this.mUsageData.get(i);
            backgroudUsage = networkCycleDataForUid.getBackgroudUsage();
            foregroudUsage = networkCycleDataForUid.getForegroudUsage();
        }
        this.mTotalUsage.setSummary(DataUsageUtils.formatDataUsage(this.mContext, backgroudUsage + foregroudUsage));
        this.mForegroundUsage.setSummary(DataUsageUtils.formatDataUsage(this.mContext, foregroudUsage));
        this.mBackgroundUsage.setSummary(DataUsageUtils.formatDataUsage(this.mContext, backgroudUsage));
    }

    private boolean getAppRestrictBackground() {
        return (this.services.mPolicyManager.getUidPolicy(this.mAppItem.key) & 1) != 0;
    }

    private boolean getUnrestrictData() {
        DataSaverBackend dataSaverBackend = this.mDataSaverBackend;
        if (dataSaverBackend != null) {
            return dataSaverBackend.isWhitelisted(this.mAppItem.key);
        }
        return false;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        int packageUidAsUser;
        super.onViewCreated(view, bundle);
        String strValueAt = this.mPackages.size() != 0 ? this.mPackages.valueAt(0) : null;
        if (strValueAt != null) {
            try {
                packageUidAsUser = this.mPackageManager.getPackageUidAsUser(strValueAt, UserHandle.getUserId(this.mAppItem.key));
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("AppDataUsage", "Skipping UID because cannot find package " + strValueAt);
                packageUidAsUser = 0;
            }
        } else {
            packageUidAsUser = 0;
        }
        boolean z = this.mAppItem.key > 0;
        FragmentActivity activity = getActivity();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, null);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        entityHeaderControllerNewInstance.setUid(packageUidAsUser);
        entityHeaderControllerNewInstance.setHasAppInfoLink(z);
        entityHeaderControllerNewInstance.setButtonActions(0, 0);
        entityHeaderControllerNewInstance.setIcon(this.mIcon);
        entityHeaderControllerNewInstance.setLabel(this.mLabel);
        entityHeaderControllerNewInstance.setPackageName(strValueAt);
        getPreferenceScreen().addPreference(entityHeaderControllerNewInstance.done(activity, getPrefContext()));
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onWhitelistStatusChanged(int i, boolean z) {
        if (this.mAppItem.uids.get(i, false)) {
            updatePrefs(getAppRestrictBackground(), z);
        }
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onBlacklistStatusChanged(int i, boolean z) {
        if (this.mAppItem.uids.get(i, false)) {
            updatePrefs(z, getUnrestrictData());
        }
    }
}
