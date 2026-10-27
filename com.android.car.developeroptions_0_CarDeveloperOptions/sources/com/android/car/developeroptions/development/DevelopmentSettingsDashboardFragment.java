package com.android.car.developeroptions.development;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.RestrictedDashboardFragment;
import com.android.car.developeroptions.development.BluetoothA2dpHwOffloadRebootDialog;
import com.android.car.developeroptions.development.autofill.AutofillLoggingLevelPreferenceController;
import com.android.car.developeroptions.development.autofill.AutofillResetOptionsPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import com.android.settingslib.development.SystemPropPoker;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DevelopmentSettingsDashboardFragment extends RestrictedDashboardFragment implements SwitchBar.OnSwitchChangeListener, OemUnlockDialogHost, AdbDialogHost, AdbClearKeysDialogHost, LogPersistDialogHost, BluetoothA2dpHwOffloadRebootDialog.OnA2dpHwDialogConfirmedListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment.4
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.development_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private BluetoothA2dp mBluetoothA2dp;
    private final BluetoothA2dpConfigStore mBluetoothA2dpConfigStore;
    private final BroadcastReceiver mBluetoothA2dpReceiver;
    private final BluetoothProfile.ServiceListener mBluetoothA2dpServiceListener;
    private final BroadcastReceiver mEnableAdbReceiver;
    private boolean mIsAvailable;
    private List<AbstractPreferenceController> mPreferenceControllers;
    private SwitchBar mSwitchBar;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DevSettingsDashboard";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 39;
    }

    public DevelopmentSettingsDashboardFragment() {
        super("no_debugging_features");
        this.mBluetoothA2dpConfigStore = new BluetoothA2dpConfigStore();
        this.mIsAvailable = true;
        this.mPreferenceControllers = new ArrayList();
        this.mEnableAdbReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                for (Object obj : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                    if (obj instanceof AdbOnChangeListener) {
                        ((AdbOnChangeListener) obj).onAdbSettingChanged();
                    }
                }
            }
        };
        this.mBluetoothA2dpReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Log.d("DevSettingsDashboard", "mBluetoothA2dpReceiver.onReceive intent=" + intent);
                if ("android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED".equals(intent.getAction())) {
                    Log.d("DevSettingsDashboard", "Received BluetoothCodecStatus=" + ((BluetoothCodecStatus) intent.getParcelableExtra("android.bluetooth.extra.CODEC_STATUS")));
                    for (Object obj : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                        if (obj instanceof BluetoothServiceConnectionListener) {
                            ((BluetoothServiceConnectionListener) obj).onBluetoothCodecUpdated();
                        }
                    }
                }
            }
        };
        this.mBluetoothA2dpServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment.3
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                synchronized (DevelopmentSettingsDashboardFragment.this.mBluetoothA2dpConfigStore) {
                    DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
                }
                for (Object obj : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                    if (obj instanceof BluetoothServiceConnectionListener) {
                        ((BluetoothServiceConnectionListener) obj).onBluetoothServiceConnected(DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp);
                    }
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                synchronized (DevelopmentSettingsDashboardFragment.this.mBluetoothA2dpConfigStore) {
                    DevelopmentSettingsDashboardFragment.this.mBluetoothA2dp = null;
                }
                for (Object obj : DevelopmentSettingsDashboardFragment.this.mPreferenceControllers) {
                    if (obj instanceof BluetoothServiceConnectionListener) {
                        ((BluetoothServiceConnectionListener) obj).onBluetoothServiceDisconnected();
                    }
                }
            }
        };
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Utils.isMonkeyRunning()) {
            getActivity().finish();
        }
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setIfOnlyAvailableForAdmins(true);
        if (isUiRestricted() || !Utils.isDeviceProvisioned(getActivity())) {
            this.mIsAvailable = false;
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(R.string.development_settings_not_available);
            }
            getPreferenceScreen().removeAll();
            return;
        }
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        new DevelopmentSwitchBarController(this, switchBar, this.mIsAvailable, getSettingsLifecycle());
        this.mSwitchBar.show();
        if (DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(getContext())) {
            enableDeveloperOptions();
        } else {
            disableDeveloperOptions();
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        registerReceivers();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(getActivity(), this.mBluetoothA2dpServiceListener, 2);
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        unregisterReceivers();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.closeProfileProxy(2, this.mBluetoothA2dp);
            this.mBluetoothA2dp = null;
        }
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r2, boolean z) {
        if (r2 == this.mSwitchBar.getSwitch() && z != DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(getContext())) {
            if (z) {
                EnableDevelopmentSettingWarningDialog.show(this);
                return;
            }
            BluetoothA2dpHwOffloadPreferenceController bluetoothA2dpHwOffloadPreferenceController = (BluetoothA2dpHwOffloadPreferenceController) getDevelopmentOptionsController(BluetoothA2dpHwOffloadPreferenceController.class);
            if (bluetoothA2dpHwOffloadPreferenceController == null || bluetoothA2dpHwOffloadPreferenceController.isDefaultValue()) {
                disableDeveloperOptions();
            } else {
                DisableDevSettingsDialogFragment.show(this);
            }
        }
    }

    @Override // com.android.car.developeroptions.development.OemUnlockDialogHost
    public void onOemUnlockDialogConfirmed() {
        ((OemUnlockPreferenceController) getDevelopmentOptionsController(OemUnlockPreferenceController.class)).onOemUnlockConfirmed();
    }

    @Override // com.android.car.developeroptions.development.OemUnlockDialogHost
    public void onOemUnlockDialogDismissed() {
        ((OemUnlockPreferenceController) getDevelopmentOptionsController(OemUnlockPreferenceController.class)).onOemUnlockDismissed();
    }

    @Override // com.android.car.developeroptions.development.AdbDialogHost
    public void onEnableAdbDialogConfirmed() {
        ((AdbPreferenceController) getDevelopmentOptionsController(AdbPreferenceController.class)).onAdbDialogConfirmed();
    }

    @Override // com.android.car.developeroptions.development.AdbDialogHost
    public void onEnableAdbDialogDismissed() {
        ((AdbPreferenceController) getDevelopmentOptionsController(AdbPreferenceController.class)).onAdbDialogDismissed();
    }

    @Override // com.android.car.developeroptions.development.AdbClearKeysDialogHost
    public void onAdbClearKeysDialogConfirmed() {
        ((ClearAdbKeysPreferenceController) getDevelopmentOptionsController(ClearAdbKeysPreferenceController.class)).onClearAdbKeysConfirmed();
    }

    @Override // com.android.car.developeroptions.development.LogPersistDialogHost
    public void onDisableLogPersistDialogConfirmed() {
        ((LogPersistPreferenceController) getDevelopmentOptionsController(LogPersistPreferenceController.class)).onDisableLogPersistDialogConfirmed();
    }

    @Override // com.android.car.developeroptions.development.LogPersistDialogHost
    public void onDisableLogPersistDialogRejected() {
        ((LogPersistPreferenceController) getDevelopmentOptionsController(LogPersistPreferenceController.class)).onDisableLogPersistDialogRejected();
    }

    @Override // com.android.car.developeroptions.development.BluetoothA2dpHwOffloadRebootDialog.OnA2dpHwDialogConfirmedListener
    public void onA2dpHwDialogConfirmed() {
        ((BluetoothA2dpHwOffloadPreferenceController) getDevelopmentOptionsController(BluetoothA2dpHwOffloadPreferenceController.class)).onA2dpHwDialogConfirmed();
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean zOnActivityResult = false;
        for (Object obj : this.mPreferenceControllers) {
            if (obj instanceof OnActivityResultListener) {
                zOnActivityResult |= ((OnActivityResultListener) obj).onActivityResult(i, i2, intent);
            }
        }
        if (zOnActivityResult) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return Utils.isMonkeyRunning() ? R.xml.placeholder_prefs : R.xml.development_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        if (Utils.isMonkeyRunning()) {
            this.mPreferenceControllers = new ArrayList();
            return null;
        }
        List<AbstractPreferenceController> listBuildPreferenceControllers = buildPreferenceControllers(context, getActivity(), getSettingsLifecycle(), this, new BluetoothA2dpConfigStore());
        this.mPreferenceControllers = listBuildPreferenceControllers;
        return listBuildPreferenceControllers;
    }

    private void registerReceivers() {
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mEnableAdbReceiver, new IntentFilter("com.android.settingslib.development.AbstractEnableAdbController.ENABLE_ADB_STATE_CHANGED"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED");
        getActivity().registerReceiver(this.mBluetoothA2dpReceiver, intentFilter);
    }

    private void unregisterReceivers() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mEnableAdbReceiver);
        getActivity().unregisterReceiver(this.mBluetoothA2dpReceiver);
    }

    private void enableDeveloperOptions() {
        if (Utils.isMonkeyRunning()) {
            return;
        }
        DevelopmentSettingsEnabler.setDevelopmentSettingsEnabled(getContext(), true);
        for (AbstractPreferenceController abstractPreferenceController : this.mPreferenceControllers) {
            if (abstractPreferenceController instanceof DeveloperOptionsPreferenceController) {
                ((DeveloperOptionsPreferenceController) abstractPreferenceController).onDeveloperOptionsEnabled();
            }
        }
    }

    private void disableDeveloperOptions() {
        if (Utils.isMonkeyRunning()) {
            return;
        }
        DevelopmentSettingsEnabler.setDevelopmentSettingsEnabled(getContext(), false);
        SystemPropPoker systemPropPoker = SystemPropPoker.getInstance();
        systemPropPoker.blockPokes();
        for (AbstractPreferenceController abstractPreferenceController : this.mPreferenceControllers) {
            if (abstractPreferenceController instanceof DeveloperOptionsPreferenceController) {
                ((DeveloperOptionsPreferenceController) abstractPreferenceController).onDeveloperOptionsDisabled();
            }
        }
        systemPropPoker.unblockPokes();
        systemPropPoker.poke();
    }

    void onEnableDevelopmentOptionsConfirmed() {
        enableDeveloperOptions();
    }

    void onEnableDevelopmentOptionsRejected() {
        this.mSwitchBar.setChecked(false);
    }

    void onDisableDevelopmentOptionsConfirmed() {
        disableDeveloperOptions();
    }

    void onDisableDevelopmentOptionsRejected() {
        this.mSwitchBar.setChecked(true);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Activity activity, Lifecycle lifecycle, DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MemoryUsagePreferenceController(context));
        arrayList.add(new SystemServerHeapDumpPreferenceController(context));
        arrayList.add(new LocalBackupPasswordPreferenceController(context));
        arrayList.add(new StayAwakePreferenceController(context, lifecycle));
        arrayList.add(new HdcpCheckingPreferenceController(context));
        arrayList.add(new BluetoothSnoopLogPreferenceController(context));
        arrayList.add(new OemUnlockPreferenceController(context, activity, developmentSettingsDashboardFragment));
        arrayList.add(new FileEncryptionPreferenceController(context));
        arrayList.add(new PictureColorModePreferenceController(context, lifecycle));
        arrayList.add(new WebViewAppPreferenceController(context));
        arrayList.add(new CoolColorTemperaturePreferenceController(context));
        arrayList.add(new DisableAutomaticUpdatesPreferenceController(context));
        arrayList.add(new ClearAdbKeysPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new LocalTerminalPreferenceController(context));
        arrayList.add(new AutomaticSystemServerHeapDumpPreferenceController(context));
        arrayList.add(new MockLocationAppPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new DebugViewAttributesPreferenceController(context));
        arrayList.add(new SelectDebugAppPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new WaitForDebuggerPreferenceController(context));
        arrayList.add(new EnableGpuDebugLayersPreferenceController(context));
        arrayList.add(new VerifyAppsOverUsbPreferenceController(context));
        arrayList.add(new ArtVerifierPreferenceController(context));
        arrayList.add(new LogdSizePreferenceController(context));
        arrayList.add(new LogPersistPreferenceController(context, developmentSettingsDashboardFragment, lifecycle));
        arrayList.add(new CameraLaserSensorPreferenceController(context));
        arrayList.add(new WifiDisplayCertificationPreferenceController(context));
        arrayList.add(new WifiVerboseLoggingPreferenceController(context));
        arrayList.add(new MobileDataAlwaysOnPreferenceController(context));
        arrayList.add(new TetheringHardwareAccelPreferenceController(context));
        arrayList.add(new BluetoothDeviceNoNamePreferenceController(context));
        arrayList.add(new BluetoothAbsoluteVolumePreferenceController(context));
        arrayList.add(new BluetoothAvrcpVersionPreferenceController(context));
        arrayList.add(new BluetoothA2dpHwOffloadPreferenceController(context, developmentSettingsDashboardFragment));
        arrayList.add(new BluetoothAudioCodecPreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioSampleRatePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioBitsPerSamplePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioChannelModePreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothAudioQualityPreferenceController(context, lifecycle, bluetoothA2dpConfigStore));
        arrayList.add(new BluetoothMaxConnectedAudioDevicesPreferenceController(context));
        arrayList.add(new ShowTapsPreferenceController(context));
        arrayList.add(new PointerLocationPreferenceController(context));
        arrayList.add(new ShowSurfaceUpdatesPreferenceController(context));
        arrayList.add(new ShowLayoutBoundsPreferenceController(context));
        arrayList.add(new RtlLayoutPreferenceController(context));
        arrayList.add(new WindowAnimationScalePreferenceController(context));
        arrayList.add(new EmulateDisplayCutoutPreferenceController(context));
        arrayList.add(new TransitionAnimationScalePreferenceController(context));
        arrayList.add(new AnimatorDurationScalePreferenceController(context));
        arrayList.add(new SecondaryDisplayPreferenceController(context));
        arrayList.add(new GpuViewUpdatesPreferenceController(context));
        arrayList.add(new HardwareLayersUpdatesPreferenceController(context));
        arrayList.add(new DebugGpuOverdrawPreferenceController(context));
        arrayList.add(new DebugNonRectClipOperationsPreferenceController(context));
        arrayList.add(new ForceDarkPreferenceController(context));
        arrayList.add(new ForceMSAAPreferenceController(context));
        arrayList.add(new HardwareOverlaysPreferenceController(context));
        arrayList.add(new SimulateColorSpacePreferenceController(context));
        arrayList.add(new UsbAudioRoutingPreferenceController(context));
        arrayList.add(new StrictModePreferenceController(context));
        arrayList.add(new ProfileGpuRenderingPreferenceController(context));
        arrayList.add(new KeepActivitiesPreferenceController(context));
        arrayList.add(new BackgroundProcessLimitPreferenceController(context));
        arrayList.add(new ShowFirstCrashDialogPreferenceController(context));
        arrayList.add(new AppsNotRespondingPreferenceController(context));
        arrayList.add(new NotificationChannelWarningsPreferenceController(context));
        arrayList.add(new AllowAppsOnExternalPreferenceController(context));
        arrayList.add(new ResizableActivityPreferenceController(context));
        arrayList.add(new FreeformWindowsPreferenceController(context));
        arrayList.add(new DesktopModePreferenceController(context));
        arrayList.add(new ShortcutManagerThrottlingPreferenceController(context));
        arrayList.add(new EnableGnssRawMeasFullTrackingPreferenceController(context));
        arrayList.add(new EnableUserSwitchMessageController(context));
        arrayList.add(new DefaultLaunchPreferenceController(context, "running_apps"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "demo_mode"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "quick_settings_tiles"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "feature_flags_dashboard"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "default_usb_configuration"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "density"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "background_check"));
        arrayList.add(new DefaultLaunchPreferenceController(context, "inactive_apps"));
        arrayList.add(new AutofillLoggingLevelPreferenceController(context, lifecycle));
        arrayList.add(new AutofillResetOptionsPreferenceController(context));
        arrayList.add(new OverlayCategoryPreferenceController(context, "android.theme.customization.accent_color"));
        arrayList.add(new OverlayCategoryPreferenceController(context, "android.theme.customization.font"));
        arrayList.add(new OverlayCategoryPreferenceController(context, "android.theme.customization.adaptive_icon_shape"));
        arrayList.add(new TrustAgentsExtendUnlockPreferenceController(context));
        arrayList.add(new TrustLostLocksScreenPreferenceController(context));
        return arrayList;
    }

    <T extends AbstractPreferenceController> T getDevelopmentOptionsController(Class<T> cls) {
        return (T) use(cls);
    }
}
