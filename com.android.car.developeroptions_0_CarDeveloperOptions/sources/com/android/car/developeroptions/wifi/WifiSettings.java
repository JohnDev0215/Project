package com.android.car.developeroptions.wifi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkTemplate;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.LinkifyUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedSettingsFragment;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.datausage.DataUsagePreference;
import com.android.car.developeroptions.datausage.DataUsageUtils;
import com.android.car.developeroptions.location.ScanningSettings;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SummaryUpdater;
import com.android.car.developeroptions.widget.SwitchBarController;
import com.android.car.developeroptions.wifi.ConnectedAccessPointPreference;
import com.android.car.developeroptions.wifi.WifiDialog;
import com.android.car.developeroptions.wifi.details.WifiNetworkDetailsFragment;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiSettings extends RestrictedSettingsFragment implements WifiTracker.WifiListener, AccessPoint.AccessPointListener, WifiDialog.WifiDialogListener, DialogInterface.OnDismissListener {
    static final int ADD_NETWORK_REQUEST = 2;
    static final String PREF_KEY_DATA_USAGE = "wifi_data_usage";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.wifi.WifiSettings.4
    };
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.wifi.WifiSettings.5
        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
        public SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
            return new SummaryProvider(activity, summaryLoader);
        }
    };
    private Bundle mAccessPointSavedState;
    private PreferenceCategory mAccessPointsPreferenceCategory;
    AddWifiNetworkPreference mAddWifiNetworkPreference;
    private CaptivePortalNetworkCallback mCaptivePortalNetworkCallback;
    private boolean mClickedConnect;
    Preference mConfigureWifiSettingsPreference;
    private WifiManager.ActionListener mConnectListener;
    private PreferenceCategory mConnectedAccessPointPreferenceCategory;
    private ConnectivityManager mConnectivityManager;
    DataUsagePreference mDataUsagePreference;
    private WifiDialog mDialog;
    private int mDialogMode;
    private AccessPoint mDlgAccessPoint;
    private boolean mEnableNextOnConnection;
    private WifiManager.ActionListener mForgetListener;
    private final Runnable mHideProgressBarRunnable;
    private boolean mIsRestricted;
    private String mOpenSsid;
    private View mProgressHeader;
    private WifiManager.ActionListener mSaveListener;
    Preference mSavedNetworksPreference;
    private AccessPoint mSelectedAccessPoint;
    private LinkablePreference mStatusMessagePreference;
    private final Runnable mUpdateAccessPointsRunnable;
    private AccessPointPreference.UserBadgeCache mUserBadgeCache;
    private WifiEnabler mWifiEnabler;
    protected WifiManager mWifiManager;
    WifiTracker mWifiTracker;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i != 1 ? 0 : 603;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_wifi;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 103;
    }

    private static boolean isVerboseLoggingEnabled() {
        return WifiTracker.sVerboseLogging || Log.isLoggable("WifiSettings", 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$WifiSettings() {
        setProgressBarVisible(false);
    }

    public WifiSettings() {
        super("no_config_wifi");
        this.mUpdateAccessPointsRunnable = new Runnable() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSettings$mE0QTYeUqZ4gRmUHZW-NMDPSwMo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$WifiSettings();
            }
        };
        this.mHideProgressBarRunnable = new Runnable() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSettings$qhQ9NfSi16QrGocOeq8WPQr3LsA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1$WifiSettings();
            }
        };
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mProgressHeader = setPinnedHeaderView(R.layout.progress_header).findViewById(R.id.progress_bar_animation);
            setProgressBarVisible(false);
        }
        ((SettingsActivity) activity).getSwitchBar().setSwitchBarText(R.string.wifi_settings_master_switch_title, R.string.wifi_settings_master_switch_title);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setAnimationAllowed(false);
        addPreferences();
        this.mIsRestricted = isUiRestricted();
    }

    private void addPreferences() {
        addPreferencesFromResource(R.xml.wifi_settings);
        this.mConnectedAccessPointPreferenceCategory = (PreferenceCategory) findPreference("connected_access_point");
        this.mAccessPointsPreferenceCategory = (PreferenceCategory) findPreference("access_points");
        this.mConfigureWifiSettingsPreference = findPreference("configure_settings");
        this.mSavedNetworksPreference = findPreference("saved_networks");
        this.mAddWifiNetworkPreference = new AddWifiNetworkPreference(getPrefContext());
        this.mStatusMessagePreference = (LinkablePreference) findPreference("wifi_status_message");
        this.mUserBadgeCache = new AccessPointPreference.UserBadgeCache(getPackageManager());
        DataUsagePreference dataUsagePreference = (DataUsagePreference) findPreference(PREF_KEY_DATA_USAGE);
        this.mDataUsagePreference = dataUsagePreference;
        dataUsagePreference.setVisible(DataUsageUtils.hasWifiRadio(getContext()));
        this.mDataUsagePreference.setTemplate(NetworkTemplate.buildTemplateWifiWildcard(), 0, null);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        ConnectivityManager connectivityManager;
        super.onActivityCreated(bundle);
        WifiTracker wifiTrackerCreate = WifiTrackerFactory.create(getActivity(), this, getSettingsLifecycle(), true, true);
        this.mWifiTracker = wifiTrackerCreate;
        this.mWifiManager = wifiTrackerCreate.getManager();
        if (getActivity() != null) {
            this.mConnectivityManager = (ConnectivityManager) getActivity().getSystemService(ConnectivityManager.class);
        }
        this.mConnectListener = new WifiConnectListener(getActivity());
        this.mSaveListener = new WifiManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.WifiSettings.1
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, R.string.wifi_failed_save_message, 0).show();
                }
            }
        };
        this.mForgetListener = new WifiManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.WifiSettings.2
            public void onSuccess() {
            }

            public void onFailure(int i) {
                FragmentActivity activity = WifiSettings.this.getActivity();
                if (activity != null) {
                    Toast.makeText(activity, R.string.wifi_failed_forget_message, 0).show();
                }
            }
        };
        if (bundle != null) {
            this.mDialogMode = bundle.getInt("dialog_mode");
            if (bundle.containsKey("wifi_ap_state")) {
                this.mAccessPointSavedState = bundle.getBundle("wifi_ap_state");
            }
        }
        Intent intent = getActivity().getIntent();
        boolean booleanExtra = intent.getBooleanExtra("wifi_enable_next_on_connect", false);
        this.mEnableNextOnConnection = booleanExtra;
        if (booleanExtra && hasNextButton() && (connectivityManager = (ConnectivityManager) getActivity().getSystemService("connectivity")) != null) {
            changeNextButtonState(connectivityManager.getNetworkInfo(1).isConnected());
        }
        registerForContextMenu(getListView());
        setHasOptionsMenu(true);
        if (intent.hasExtra("wifi_start_connect_ssid")) {
            this.mOpenSsid = intent.getStringExtra("wifi_start_connect_ssid");
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.teardownSwitchController();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mWifiEnabler = createWifiEnabler();
        if (this.mIsRestricted) {
            restrictUi();
        } else {
            onWifiStateChanged(this.mWifiManager.getWifiState());
        }
    }

    private void restrictUi() {
        if (!isUiRestrictedByOnlyAdmin()) {
            getEmptyTextView().setText(R.string.wifi_empty_list_user_restricted);
        }
        getPreferenceScreen().removeAll();
    }

    private WifiEnabler createWifiEnabler() {
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        return new WifiEnabler(settingsActivity, new SwitchBarController(settingsActivity.getSwitchBar()), this.mMetricsFeatureProvider);
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        FragmentActivity activity = getActivity();
        super.onResume();
        boolean z = this.mIsRestricted;
        boolean zIsUiRestricted = isUiRestricted();
        this.mIsRestricted = zIsUiRestricted;
        if (!z && zIsUiRestricted) {
            restrictUi();
        }
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.resume(activity);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.pause();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        getView().removeCallbacks(this.mUpdateAccessPointsRunnable);
        getView().removeCallbacks(this.mHideProgressBarRunnable);
        unregisterCaptivePortalNetworkCallback();
        super.onStop();
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2) {
            handleAddNetworkRequest(i2, intent);
            return;
        }
        if (i == 0) {
            if (i2 == -1) {
                WifiDialog wifiDialog = this.mDialog;
                if (wifiDialog != null) {
                    wifiDialog.dismiss();
                }
                this.mWifiTracker.resumeScanning();
                return;
            }
            return;
        }
        boolean z = this.mIsRestricted;
        boolean zIsUiRestricted = isUiRestricted();
        this.mIsRestricted = zIsUiRestricted;
        if (z && !zIsUiRestricted && getPreferenceScreen().getPreferenceCount() == 0) {
            addPreferences();
        }
    }

    @Override // com.android.car.developeroptions.RestrictedSettingsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mDialog != null) {
            bundle.putInt("dialog_mode", this.mDialogMode);
            if (this.mDlgAccessPoint != null) {
                Bundle bundle2 = new Bundle();
                this.mAccessPointSavedState = bundle2;
                this.mDlgAccessPoint.saveWifiState(bundle2);
                bundle.putBundle("wifi_ap_state", this.mAccessPointSavedState);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Preference preference = (Preference) view.getTag();
        if (preference instanceof LongPressAccessPointPreference) {
            AccessPoint accessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            this.mSelectedAccessPoint = accessPoint;
            contextMenu.setHeaderTitle(accessPoint.getTitle());
            if (this.mSelectedAccessPoint.isConnectable()) {
                contextMenu.add(0, 7, 0, R.string.wifi_menu_connect);
            }
            if (WifiUtils.isNetworkLockedDown(getActivity(), this.mSelectedAccessPoint.getConfig())) {
                return;
            }
            if (this.mSelectedAccessPoint.isSaved() || this.mSelectedAccessPoint.isEphemeral()) {
                contextMenu.add(0, 8, 0, R.string.wifi_menu_forget);
            }
            if (this.mSelectedAccessPoint.isSaved()) {
                contextMenu.add(0, 9, 0, R.string.wifi_menu_modify);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mSelectedAccessPoint == null) {
            return super.onContextItemSelected(menuItem);
        }
        int itemId = menuItem.getItemId();
        if (itemId != 7) {
            if (itemId == 8) {
                forget();
                return true;
            }
            if (itemId == 9) {
                showDialog(this.mSelectedAccessPoint, 2);
                return true;
            }
            return super.onContextItemSelected(menuItem);
        }
        boolean zIsSaved = this.mSelectedAccessPoint.isSaved();
        if (zIsSaved) {
            connect(this.mSelectedAccessPoint.getConfig(), zIsSaved);
        } else if (this.mSelectedAccessPoint.getSecurity() == 0 || this.mSelectedAccessPoint.getSecurity() == 4) {
            this.mSelectedAccessPoint.generateOpenNetworkConfig();
            connect(this.mSelectedAccessPoint.getConfig(), zIsSaved);
        } else {
            showDialog(this.mSelectedAccessPoint, 1);
        }
        return true;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getFragment() != null) {
            preference.setOnPreferenceClickListener(null);
            return super.onPreferenceTreeClick(preference);
        }
        if (preference instanceof LongPressAccessPointPreference) {
            AccessPoint accessPoint = ((LongPressAccessPointPreference) preference).getAccessPoint();
            this.mSelectedAccessPoint = accessPoint;
            if (accessPoint == null) {
                return false;
            }
            if (accessPoint.isActive()) {
                return super.onPreferenceTreeClick(preference);
            }
            int connectingType = WifiUtils.getConnectingType(this.mSelectedAccessPoint);
            if (connectingType == 1) {
                this.mSelectedAccessPoint.generateOpenNetworkConfig();
                connect(this.mSelectedAccessPoint.getConfig(), this.mSelectedAccessPoint.isSaved());
            } else if (connectingType == 2) {
                connect(this.mSelectedAccessPoint.getConfig(), true);
            } else if (connectingType == 3) {
                this.mSelectedAccessPoint.startOsuProvisioning(this.mConnectListener);
                this.mClickedConnect = true;
            } else {
                showDialog(this.mSelectedAccessPoint, 1);
            }
        } else if (preference == this.mAddWifiNetworkPreference) {
            onAddNetworkPressed();
        } else {
            return super.onPreferenceTreeClick(preference);
        }
        return true;
    }

    private void showDialog(AccessPoint accessPoint, int i) {
        if (accessPoint != null) {
            if (WifiUtils.isNetworkLockedDown(getActivity(), accessPoint.getConfig()) && accessPoint.isActive()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getActivity(), RestrictedLockUtilsInternal.getDeviceOwner(getActivity()));
                return;
            }
        }
        if (this.mDialog != null) {
            removeDialog(1);
            this.mDialog = null;
        }
        this.mDlgAccessPoint = accessPoint;
        this.mDialogMode = i;
        showDialog(1);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            if (this.mDlgAccessPoint == null && this.mAccessPointSavedState != null) {
                this.mDlgAccessPoint = new AccessPoint(getActivity(), this.mAccessPointSavedState);
                this.mAccessPointSavedState = null;
            }
            WifiDialog wifiDialogCreateModal = WifiDialog.createModal(getActivity(), this, this.mDlgAccessPoint, this.mDialogMode);
            this.mDialog = wifiDialogCreateModal;
            this.mSelectedAccessPoint = this.mDlgAccessPoint;
            return wifiDialogCreateModal;
        }
        return super.onCreateDialog(i);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment
    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        this.mDialog = null;
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onAccessPointsChanged() {
        Log.d("WifiSettings", "onAccessPointsChanged (WifiTracker) callback initiated");
        updateAccessPointsDelayed();
    }

    private void updateAccessPointsDelayed() {
        if (getActivity() == null || this.mIsRestricted || !this.mWifiManager.isWifiEnabled()) {
            return;
        }
        View view = getView();
        Handler handler = view.getHandler();
        if (handler == null || !handler.hasCallbacks(this.mUpdateAccessPointsRunnable)) {
            setProgressBarVisible(true);
            view.postDelayed(this.mUpdateAccessPointsRunnable, 300L);
        }
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onWifiStateChanged(int i) {
        if (this.mIsRestricted) {
            return;
        }
        int wifiState = this.mWifiManager.getWifiState();
        if (wifiState == 0) {
            removeConnectedAccessPointPreference();
            removeAccessPointPreference();
            addMessagePreference(R.string.wifi_stopping);
        } else if (wifiState == 1) {
            setOffMessage();
            setAdditionalSettingsSummaries();
            setProgressBarVisible(false);
        } else if (wifiState != 2) {
            if (wifiState != 3) {
                return;
            }
            lambda$new$0();
        } else {
            removeConnectedAccessPointPreference();
            removeAccessPointPreference();
            addMessagePreference(R.string.wifi_starting);
            setProgressBarVisible(true);
        }
    }

    @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
    public void onConnectedChanged() {
        changeNextButtonState(this.mWifiTracker.isConnected());
    }

    private static boolean isDisabledByWrongPassword(AccessPoint accessPoint) {
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus;
        WifiConfiguration config = accessPoint.getConfig();
        return (config == null || (networkSelectionStatus = config.getNetworkSelectionStatus()) == null || networkSelectionStatus.getNetworkSelectionStatus() == 0 || 8 != networkSelectionStatus.getNetworkSelectionDisableReason()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX INFO: renamed from: updateAccessPointPreferences, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$WifiSettings() {
        if (this.mWifiManager.isWifiEnabled()) {
            List<AccessPoint> accessPoints = this.mWifiTracker.getAccessPoints();
            if (isVerboseLoggingEnabled()) {
                Log.i("WifiSettings", "updateAccessPoints called for: " + accessPoints);
            }
            this.mStatusMessagePreference.setVisible(false);
            this.mConnectedAccessPointPreferenceCategory.setVisible(true);
            this.mAccessPointsPreferenceCategory.setVisible(true);
            cacheRemoveAllPrefs(this.mAccessPointsPreferenceCategory);
            boolean zConfigureConnectedAccessPointPreferenceCategory = configureConnectedAccessPointPreferenceCategory(accessPoints);
            int size = accessPoints.size();
            boolean z = false;
            int i = zConfigureConnectedAccessPointPreferenceCategory;
            while (i < size) {
                AccessPoint accessPoint = accessPoints.get(i);
                if (accessPoint.isReachable()) {
                    String key = accessPoint.getKey();
                    LongPressAccessPointPreference longPressAccessPointPreference = (LongPressAccessPointPreference) getCachedPreference(key);
                    if (longPressAccessPointPreference != null) {
                        longPressAccessPointPreference.setOrder(i);
                    } else {
                        LongPressAccessPointPreference longPressAccessPointPreferenceCreateLongPressAccessPointPreference = createLongPressAccessPointPreference(accessPoint);
                        longPressAccessPointPreferenceCreateLongPressAccessPointPreference.setKey(key);
                        longPressAccessPointPreferenceCreateLongPressAccessPointPreference.setOrder(i);
                        String str = this.mOpenSsid;
                        if (str != null && str.equals(accessPoint.getSsidStr()) && accessPoint.getSecurity() != 0 && accessPoint.getSecurity() != 4 && (!accessPoint.isSaved() || isDisabledByWrongPassword(accessPoint))) {
                            onPreferenceTreeClick(longPressAccessPointPreferenceCreateLongPressAccessPointPreference);
                            this.mOpenSsid = null;
                        }
                        this.mAccessPointsPreferenceCategory.addPreference(longPressAccessPointPreferenceCreateLongPressAccessPointPreference);
                        accessPoint.setListener(this);
                        longPressAccessPointPreferenceCreateLongPressAccessPointPreference.refresh();
                    }
                    z = true;
                }
                i++;
            }
            removeCachedPrefs(this.mAccessPointsPreferenceCategory);
            this.mAddWifiNetworkPreference.setOrder(i);
            this.mAccessPointsPreferenceCategory.addPreference(this.mAddWifiNetworkPreference);
            setAdditionalSettingsSummaries();
            if (!z) {
                setProgressBarVisible(true);
                Preference preference = new Preference(getPrefContext());
                preference.setSelectable(false);
                preference.setSummary(R.string.wifi_empty_list_wifi_on);
                preference.setOrder(i);
                preference.setKey("wifi_empty_list");
                this.mAccessPointsPreferenceCategory.addPreference(preference);
                return;
            }
            getView().postDelayed(this.mHideProgressBarRunnable, 1700L);
        }
    }

    private LongPressAccessPointPreference createLongPressAccessPointPreference(AccessPoint accessPoint) {
        return new LongPressAccessPointPreference(accessPoint, getPrefContext(), this.mUserBadgeCache, false, R.drawable.ic_wifi_signal_0, this);
    }

    private ConnectedAccessPointPreference createConnectedAccessPointPreference(AccessPoint accessPoint) {
        return new ConnectedAccessPointPreference(accessPoint, getPrefContext(), this.mUserBadgeCache, R.drawable.ic_wifi_signal_0, false);
    }

    private boolean configureConnectedAccessPointPreferenceCategory(List<AccessPoint> list) {
        if (list.size() == 0) {
            removeConnectedAccessPointPreference();
            return false;
        }
        AccessPoint accessPoint = list.get(0);
        if (!accessPoint.isActive()) {
            removeConnectedAccessPointPreference();
            return false;
        }
        if (this.mConnectedAccessPointPreferenceCategory.getPreferenceCount() == 0) {
            addConnectedAccessPointPreference(accessPoint);
            return true;
        }
        ConnectedAccessPointPreference connectedAccessPointPreference = (ConnectedAccessPointPreference) this.mConnectedAccessPointPreferenceCategory.getPreference(0);
        if (connectedAccessPointPreference.getAccessPoint() != accessPoint) {
            removeConnectedAccessPointPreference();
            addConnectedAccessPointPreference(accessPoint);
            return true;
        }
        connectedAccessPointPreference.refresh();
        registerCaptivePortalNetworkCallback(getCurrentWifiNetwork(), connectedAccessPointPreference);
        return true;
    }

    private void addConnectedAccessPointPreference(AccessPoint accessPoint) {
        final ConnectedAccessPointPreference connectedAccessPointPreferenceCreateConnectedAccessPointPreference = createConnectedAccessPointPreference(accessPoint);
        registerCaptivePortalNetworkCallback(getCurrentWifiNetwork(), connectedAccessPointPreferenceCreateConnectedAccessPointPreference);
        connectedAccessPointPreferenceCreateConnectedAccessPointPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSettings$ot5RKdjOl0m22wRttlkIfhGrNNk
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$addConnectedAccessPointPreference$2$WifiSettings(connectedAccessPointPreferenceCreateConnectedAccessPointPreference, preference);
            }
        });
        connectedAccessPointPreferenceCreateConnectedAccessPointPreference.setOnGearClickListener(new ConnectedAccessPointPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSettings$pHI82j8CDmgFroR6IR6PraJyWhY
            @Override // com.android.car.developeroptions.wifi.ConnectedAccessPointPreference.OnGearClickListener
            public final void onGearClick(ConnectedAccessPointPreference connectedAccessPointPreference) {
                this.f$0.lambda$addConnectedAccessPointPreference$3$WifiSettings(connectedAccessPointPreferenceCreateConnectedAccessPointPreference, connectedAccessPointPreference);
            }
        });
        connectedAccessPointPreferenceCreateConnectedAccessPointPreference.refresh();
        this.mConnectedAccessPointPreferenceCategory.addPreference(connectedAccessPointPreferenceCreateConnectedAccessPointPreference);
        this.mConnectedAccessPointPreferenceCategory.setVisible(true);
        if (this.mClickedConnect) {
            this.mClickedConnect = false;
            scrollToPreference(this.mConnectedAccessPointPreferenceCategory);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$addConnectedAccessPointPreference$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$addConnectedAccessPointPreference$2$WifiSettings(ConnectedAccessPointPreference connectedAccessPointPreference, Preference preference) {
        connectedAccessPointPreference.getAccessPoint().saveWifiState(connectedAccessPointPreference.getExtras());
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback != null && captivePortalNetworkCallback.isCaptivePortal()) {
            this.mConnectivityManager.startCaptivePortalApp(this.mCaptivePortalNetworkCallback.getNetwork());
            return true;
        }
        launchNetworkDetailsFragment(connectedAccessPointPreference);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$addConnectedAccessPointPreference$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$addConnectedAccessPointPreference$3$WifiSettings(ConnectedAccessPointPreference connectedAccessPointPreference, ConnectedAccessPointPreference connectedAccessPointPreference2) {
        connectedAccessPointPreference.getAccessPoint().saveWifiState(connectedAccessPointPreference.getExtras());
        launchNetworkDetailsFragment(connectedAccessPointPreference);
    }

    private void registerCaptivePortalNetworkCallback(Network network, ConnectedAccessPointPreference connectedAccessPointPreference) {
        if (network == null || connectedAccessPointPreference == null) {
            Log.w("WifiSettings", "Network or Preference were null when registering callback.");
            return;
        }
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback == null || !captivePortalNetworkCallback.isSameNetworkAndPreference(network, connectedAccessPointPreference)) {
            unregisterCaptivePortalNetworkCallback();
            this.mCaptivePortalNetworkCallback = new CaptivePortalNetworkCallback(network, connectedAccessPointPreference);
            this.mConnectivityManager.registerNetworkCallback(new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build(), this.mCaptivePortalNetworkCallback, new Handler(Looper.getMainLooper()));
        }
    }

    private void unregisterCaptivePortalNetworkCallback() {
        CaptivePortalNetworkCallback captivePortalNetworkCallback = this.mCaptivePortalNetworkCallback;
        if (captivePortalNetworkCallback != null) {
            try {
                this.mConnectivityManager.unregisterNetworkCallback(captivePortalNetworkCallback);
            } catch (RuntimeException e) {
                Log.e("WifiSettings", "Unregistering CaptivePortalNetworkCallback failed.", e);
            }
            this.mCaptivePortalNetworkCallback = null;
        }
    }

    private void launchAddNetworkFragment() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setTitleRes(R.string.wifi_add_network);
        subSettingLauncher.setDestination(AddNetworkFragment.class.getName());
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.setResultListener(this, 2);
        subSettingLauncher.launch();
    }

    private void launchNetworkDetailsFragment(ConnectedAccessPointPreference connectedAccessPointPreference) {
        CharSequence text;
        AccessPoint accessPoint = connectedAccessPointPreference.getAccessPoint();
        Context context = getContext();
        if (FeatureFlagUtils.isEnabled(context, "settings_wifi_details_datausage_header")) {
            text = accessPoint.getTitle();
        } else {
            text = context.getText(R.string.pref_title_network_details);
        }
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setTitleText(text);
        subSettingLauncher.setDestination(WifiNetworkDetailsFragment.class.getName());
        subSettingLauncher.setArguments(connectedAccessPointPreference.getExtras());
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }

    private Network getCurrentWifiNetwork() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            return wifiManager.getCurrentNetwork();
        }
        return null;
    }

    private void removeConnectedAccessPointPreference() {
        this.mConnectedAccessPointPreferenceCategory.removeAll();
        this.mConnectedAccessPointPreferenceCategory.setVisible(false);
        unregisterCaptivePortalNetworkCallback();
    }

    private void removeAccessPointPreference() {
        this.mAccessPointsPreferenceCategory.removeAll();
        this.mAccessPointsPreferenceCategory.setVisible(false);
    }

    void setAdditionalSettingsSummaries() {
        this.mConfigureWifiSettingsPreference.setSummary(getString(isWifiWakeupEnabled() ? R.string.wifi_configure_settings_preference_summary_wakeup_on : R.string.wifi_configure_settings_preference_summary_wakeup_off));
        int numSavedNetworks = this.mWifiTracker.getNumSavedNetworks();
        this.mSavedNetworksPreference.setVisible(numSavedNetworks > 0);
        this.mSavedNetworksPreference.setSummary(getResources().getQuantityString(R.plurals.wifi_saved_access_points_summary, numSavedNetworks, Integer.valueOf(numSavedNetworks)));
    }

    private boolean isWifiWakeupEnabled() {
        Context context = getContext();
        return this.mWifiManager.isAutoWakeupEnabled() && this.mWifiManager.isScanAlwaysAvailable() && Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0 && !((PowerManager) context.getSystemService(PowerManager.class)).isPowerSaveMode();
    }

    private void setOffMessage() {
        CharSequence text;
        CharSequence text2 = getText(R.string.wifi_empty_list_wifi_off);
        if (this.mWifiManager.isScanAlwaysAvailable()) {
            text = getText(R.string.wifi_scan_notify_text);
        } else {
            text = getText(R.string.wifi_scan_notify_text_scanning_off);
        }
        this.mStatusMessagePreference.setText(text2, text, new LinkifyUtils.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$WifiSettings$GL8UN0JOVIOa8ckdlS5yTM1xXqg
            @Override // com.android.car.developeroptions.LinkifyUtils.OnClickListener
            public final void onClick() {
                this.f$0.lambda$setOffMessage$4$WifiSettings();
            }
        });
        removeConnectedAccessPointPreference();
        removeAccessPointPreference();
        this.mStatusMessagePreference.setVisible(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setOffMessage$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setOffMessage$4$WifiSettings() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(ScanningSettings.class.getName());
        subSettingLauncher.setTitleRes(R.string.location_scanning_screen_title);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }

    private void addMessagePreference(int i) {
        this.mStatusMessagePreference.setTitle(i);
        this.mStatusMessagePreference.setVisible(true);
    }

    protected void setProgressBarVisible(boolean z) {
        View view = this.mProgressHeader;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void changeNextButtonState(boolean z) {
        if (this.mEnableNextOnConnection && hasNextButton()) {
            getNextButton().setEnabled(z);
        }
    }

    @Override // com.android.car.developeroptions.wifi.WifiDialog.WifiDialogListener
    public void onForget(WifiDialog wifiDialog) {
        forget();
    }

    @Override // com.android.car.developeroptions.wifi.WifiDialog.WifiDialogListener
    public void onSubmit(WifiDialog wifiDialog) {
        WifiDialog wifiDialog2 = this.mDialog;
        if (wifiDialog2 != null) {
            submit(wifiDialog2.getController());
        }
    }

    @Override // com.android.car.developeroptions.wifi.WifiDialog.WifiDialogListener
    public void onScan(WifiDialog wifiDialog, String str) {
        startActivityForResult(WifiDppUtils.getEnrolleeQrCodeScannerIntent(str), 0);
    }

    void submit(WifiConfigController wifiConfigController) {
        WifiConfiguration config = wifiConfigController.getConfig();
        if (config == null) {
            AccessPoint accessPoint = this.mSelectedAccessPoint;
            if (accessPoint != null && accessPoint.isSaved()) {
                connect(this.mSelectedAccessPoint.getConfig(), true);
            }
        } else if (wifiConfigController.getMode() == 2) {
            this.mWifiManager.save(config, this.mSaveListener);
        } else {
            this.mWifiManager.save(config, this.mSaveListener);
            if (this.mSelectedAccessPoint != null) {
                connect(config, false);
            }
        }
        this.mWifiTracker.resumeScanning();
    }

    void forget() {
        this.mMetricsFeatureProvider.action(getActivity(), 137, new Pair[0]);
        if (!this.mSelectedAccessPoint.isSaved()) {
            if (this.mSelectedAccessPoint.getNetworkInfo() != null && this.mSelectedAccessPoint.getNetworkInfo().getState() != NetworkInfo.State.DISCONNECTED) {
                this.mWifiManager.disableEphemeralNetwork(AccessPoint.convertToQuotedString(this.mSelectedAccessPoint.getSsidStr()));
            } else {
                Log.e("WifiSettings", "Failed to forget invalid network " + this.mSelectedAccessPoint.getConfig());
                return;
            }
        } else if (this.mSelectedAccessPoint.getConfig().isPasspoint()) {
            try {
                this.mWifiManager.removePasspointConfiguration(this.mSelectedAccessPoint.getConfig().FQDN);
            } catch (IllegalArgumentException e) {
                Log.e("WifiSettings", "Failed to remove Passpoint configuration with error: " + e);
                return;
            }
        } else {
            this.mWifiManager.forget(this.mSelectedAccessPoint.getConfig().networkId, this.mForgetListener);
        }
        this.mWifiTracker.resumeScanning();
        changeNextButtonState(false);
    }

    protected void connect(WifiConfiguration wifiConfiguration, boolean z) {
        this.mMetricsFeatureProvider.action(getContext(), 135, z);
        this.mWifiManager.connect(wifiConfiguration, this.mConnectListener);
        this.mClickedConnect = true;
    }

    void handleAddNetworkRequest(int i, Intent intent) {
        if (i == -1) {
            handleAddNetworkSubmitEvent(intent);
        }
        this.mWifiTracker.resumeScanning();
    }

    private void handleAddNetworkSubmitEvent(Intent intent) {
        WifiConfiguration wifiConfiguration = (WifiConfiguration) intent.getParcelableExtra("wifi_config_key");
        if (wifiConfiguration != null) {
            this.mWifiManager.save(wifiConfiguration, this.mSaveListener);
        }
    }

    private void onAddNetworkPressed() {
        this.mSelectedAccessPoint = null;
        launchAddNetworkFragment();
    }

    @Override // com.android.settingslib.wifi.AccessPoint.AccessPointListener
    public void onAccessPointChanged(final AccessPoint accessPoint) {
        Log.d("WifiSettings", "onAccessPointChanged (singular) callback initiated");
        View view = getView();
        if (view != null) {
            view.post(new Runnable(this) { // from class: com.android.car.developeroptions.wifi.WifiSettings.3
                @Override // java.lang.Runnable
                public void run() {
                    Object tag = accessPoint.getTag();
                    if (tag != null) {
                        ((AccessPointPreference) tag).refresh();
                    }
                }
            });
        }
    }

    @Override // com.android.settingslib.wifi.AccessPoint.AccessPointListener
    public void onLevelChanged(AccessPoint accessPoint) {
        ((AccessPointPreference) accessPoint.getTag()).onLevelChanged();
    }

    private static class SummaryProvider implements SummaryLoader.SummaryProvider, SummaryUpdater.OnSummaryChangeListener {
        private final Context mContext;
        WifiSummaryUpdater mSummaryHelper;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
            this.mSummaryHelper = new WifiSummaryUpdater(this.mContext, this);
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            this.mSummaryHelper.register(z);
        }

        @Override // com.android.car.developeroptions.widget.SummaryUpdater.OnSummaryChangeListener
        public void onSummaryChanged(String str) {
            this.mSummaryLoader.setSummary(this, str);
        }
    }
}
