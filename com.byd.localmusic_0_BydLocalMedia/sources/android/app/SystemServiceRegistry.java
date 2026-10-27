package android.app;

import android.accounts.AccountManager;
import android.accounts.IAccountManager;
import android.annotation.SystemApi;
import android.app.IAlarmManager;
import android.app.IWallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.admin.IDevicePolicyManager;
import android.app.blob.BlobStoreManagerFrameworkInitializer;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.IContentSuggestionsManager;
import android.app.job.JobSchedulerFrameworkInitializer;
import android.app.prediction.AppPredictionManager;
import android.app.role.RoleControllerManager;
import android.app.role.RoleManager;
import android.app.slice.SliceManager;
import android.app.timedetector.TimeDetector;
import android.app.timedetector.TimeDetectorImpl;
import android.app.timezone.RulesManager;
import android.app.timezonedetector.TimeZoneDetector;
import android.app.timezonedetector.TimeZoneDetectorImpl;
import android.app.trust.TrustManager;
import android.app.usage.IStorageStatsManager;
import android.app.usage.IUsageStatsManager;
import android.app.usage.NetworkStatsManager;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.companion.CompanionDeviceManager;
import android.companion.ICompanionDeviceManager;
import android.content.ClipboardManager;
import android.content.ContentCaptureOptions;
import android.content.Context;
import android.content.IRestrictionsManager;
import android.content.RestrictionsManager;
import android.content.integrity.AppIntegrityManager;
import android.content.integrity.IAppIntegrityManager;
import android.content.om.IOverlayManager;
import android.content.om.OverlayManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.CrossProfileApps;
import android.content.pm.DataLoaderManager;
import android.content.pm.ICrossProfileApps;
import android.content.pm.IDataLoaderManager;
import android.content.pm.IShortcutService;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.content.rollback.IRollbackManager;
import android.content.rollback.RollbackManager;
import android.debug.AdbManager;
import android.debug.IAdbManager;
import android.hardware.ConsumerIrManager;
import android.hardware.ISerialManager;
import android.hardware.SensorManager;
import android.hardware.SensorPrivacyManager;
import android.hardware.SerialManager;
import android.hardware.SystemSensorManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.IAuthService;
import android.hardware.camera2.CameraManager;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.DisplayManager;
import android.hardware.face.FaceManager;
import android.hardware.face.IFaceService;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.IFingerprintService;
import android.hardware.hdmi.HdmiControlManager;
import android.hardware.hdmi.IHdmiControlService;
import android.hardware.input.InputManager;
import android.hardware.iris.IIrisService;
import android.hardware.iris.IrisManager;
import android.hardware.lights.LightsManager;
import android.hardware.location.ContextHubManager;
import android.hardware.radio.RadioManager;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.UsbManager;
import android.location.CountryDetector;
import android.location.ICountryDetector;
import android.location.ILocationManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.midi.IMidiManager;
import android.media.midi.MidiManager;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.soundtrigger.SoundTriggerManager;
import android.media.tv.ITvInputManager;
import android.media.tv.TvInputManager;
import android.media.tv.tunerresourcemanager.ITunerResourceManager;
import android.media.tv.tunerresourcemanager.TunerResourceManager;
import android.net.ConnectivityDiagnosticsManager;
import android.net.ConnectivityManager;
import android.net.ConnectivityThread;
import android.net.EthernetManager;
import android.net.IConnectivityManager;
import android.net.IEthernetManager;
import android.net.IIpSecService;
import android.net.INetworkPolicyManager;
import android.net.ITestNetworkManager;
import android.net.IpSecManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkScoreManager;
import android.net.NetworkWatchlistManager;
import android.net.TestNetworkManager;
import android.net.TetheringManager;
import android.net.VpnManager;
import android.net.lowpan.ILowpanManager;
import android.net.lowpan.LowpanManager;
import android.net.nsd.INsdManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiFrameworkInitializer;
import android.net.wifi.nl80211.WifiNl80211Manager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.BatteryStatsManager;
import android.os.BugreportManager;
import android.os.CarTwilightManager;
import android.os.DropBoxManager;
import android.os.HardwarePropertiesManager;
import android.os.IBatteryPropertiesRegistrar;
import android.os.IBinder;
import android.os.ICarTwilightManager;
import android.os.IDumpstate;
import android.os.IHardwarePropertiesManager;
import android.os.ILauncherManager;
import android.os.IPowerManager;
import android.os.IRecoverySystem;
import android.os.ISystemUpdateManager;
import android.os.IThermalService;
import android.os.IUserManager;
import android.os.IncidentManager;
import android.os.LauncherManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.RecoverySystem;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StatsFrameworkInitializer;
import android.os.SystemConfigManager;
import android.os.SystemUpdateManager;
import android.os.SystemVibrator;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.health.SystemHealthManager;
import android.os.image.DynamicSystemManager;
import android.os.image.IDynamicSystemService;
import android.os.incremental.IIncrementalService;
import android.os.incremental.IncrementalManager;
import android.os.storage.StorageManager;
import android.permission.PermissionControllerManager;
import android.permission.PermissionManager;
import android.print.IPrintManager;
import android.print.PrintManager;
import android.security.FileIntegrityManager;
import android.security.IFileIntegrityService;
import android.service.oemlock.IOemLockService;
import android.service.oemlock.OemLockManager;
import android.service.persistentdata.IPersistentDataBlockService;
import android.service.persistentdata.PersistentDataBlockManager;
import android.service.vr.IVrManager;
import android.telecom.TelecomManager;
import android.telephony.MmsManager;
import android.telephony.TelephonyFrameworkInitializer;
import android.telephony.TelephonyRegistryManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.ContextThemeWrapper;
import android.view.GestureManager;
import android.view.IGestureManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.WindowManagerImpl;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.autofill.AutofillManager;
import android.view.autofill.IAutoFillManager;
import android.view.contentcapture.ContentCaptureManager;
import android.view.contentcapture.IContentCaptureManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import android.view.textservice.TextServicesManager;
import com.android.internal.R;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.IBatteryStats;
import com.android.internal.app.ISoundTriggerService;
import com.android.internal.appwidget.IAppWidgetService;
import com.android.internal.net.INetworkWatchlistManager;
import com.android.internal.os.IDropBoxManagerService;
import com.android.internal.policy.PhoneLayoutInflater;
import com.android.internal.util.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class SystemServiceRegistry {
    private static final String TAG = "SystemServiceRegistry";
    public static boolean sEnableServiceNotFoundWtf = false;
    private static volatile boolean sInitializing;
    private static int sServiceCacheSize;
    private static final Map<Class<?>, String> SYSTEM_SERVICE_NAMES = new ArrayMap();
    private static final Map<String, ServiceFetcher<?>> SYSTEM_SERVICE_FETCHERS = new ArrayMap();
    private static final Map<String, String> SYSTEM_SERVICE_CLASS_NAMES = new ArrayMap();

    @SystemApi
    public interface ContextAwareServiceProducerWithBinder<TServiceClass> {
        TServiceClass createService(Context context, IBinder iBinder);
    }

    @SystemApi
    public interface ContextAwareServiceProducerWithoutBinder<TServiceClass> {
        TServiceClass createService(Context context);
    }

    interface ServiceFetcher<T> {
        T getService(ContextImpl contextImpl);
    }

    @SystemApi
    public interface StaticServiceProducerWithBinder<TServiceClass> {
        TServiceClass createService(IBinder iBinder);
    }

    @SystemApi
    public interface StaticServiceProducerWithoutBinder<TServiceClass> {
        TServiceClass createService();
    }

    static /* synthetic */ int access$008() {
        int i = sServiceCacheSize;
        sServiceCacheSize = i + 1;
        return i;
    }

    static {
        registerService(Context.ACCESSIBILITY_SERVICE, AccessibilityManager.class, new CachedServiceFetcher<AccessibilityManager>() { // from class: android.app.SystemServiceRegistry.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AccessibilityManager createService(ContextImpl contextImpl) {
                return AccessibilityManager.getInstance(contextImpl);
            }
        });
        registerService(Context.CAPTIONING_SERVICE, CaptioningManager.class, new CachedServiceFetcher<CaptioningManager>() { // from class: android.app.SystemServiceRegistry.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public CaptioningManager createService(ContextImpl contextImpl) {
                return new CaptioningManager(contextImpl);
            }
        });
        registerService("account", AccountManager.class, new CachedServiceFetcher<AccountManager>() { // from class: android.app.SystemServiceRegistry.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AccountManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AccountManager(contextImpl, IAccountManager.Stub.asInterface(ServiceManager.getServiceOrThrow("account")));
            }
        });
        registerService(Context.ACTIVITY_SERVICE, ActivityManager.class, new CachedServiceFetcher<ActivityManager>() { // from class: android.app.SystemServiceRegistry.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ActivityManager createService(ContextImpl contextImpl) {
                return new ActivityManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService(Context.ACTIVITY_TASK_SERVICE, ActivityTaskManager.class, new CachedServiceFetcher<ActivityTaskManager>() { // from class: android.app.SystemServiceRegistry.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ActivityTaskManager createService(ContextImpl contextImpl) {
                return new ActivityTaskManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService(Context.URI_GRANTS_SERVICE, UriGrantsManager.class, new CachedServiceFetcher<UriGrantsManager>() { // from class: android.app.SystemServiceRegistry.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public UriGrantsManager createService(ContextImpl contextImpl) {
                return new UriGrantsManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService("alarm", AlarmManager.class, new CachedServiceFetcher<AlarmManager>() { // from class: android.app.SystemServiceRegistry.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AlarmManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AlarmManager(IAlarmManager.Stub.asInterface(ServiceManager.getServiceOrThrow("alarm")), contextImpl);
            }
        });
        registerService("audio", AudioManager.class, new CachedServiceFetcher<AudioManager>() { // from class: android.app.SystemServiceRegistry.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AudioManager createService(ContextImpl contextImpl) {
                return new AudioManager(contextImpl);
            }
        });
        registerService(Context.MEDIA_ROUTER_SERVICE, MediaRouter.class, new CachedServiceFetcher<MediaRouter>() { // from class: android.app.SystemServiceRegistry.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public MediaRouter createService(ContextImpl contextImpl) {
                return new MediaRouter(contextImpl);
            }
        });
        registerService("bluetooth", BluetoothManager.class, new CachedServiceFetcher<BluetoothManager>() { // from class: android.app.SystemServiceRegistry.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public BluetoothManager createService(ContextImpl contextImpl) {
                return new BluetoothManager(contextImpl);
            }
        });
        registerService(Context.HDMI_CONTROL_SERVICE, HdmiControlManager.class, new StaticServiceFetcher<HdmiControlManager>() { // from class: android.app.SystemServiceRegistry.11
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public HdmiControlManager createService() throws ServiceManager.ServiceNotFoundException {
                return new HdmiControlManager(IHdmiControlService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.HDMI_CONTROL_SERVICE)));
            }
        });
        registerService(Context.TEXT_CLASSIFICATION_SERVICE, TextClassificationManager.class, new CachedServiceFetcher<TextClassificationManager>() { // from class: android.app.SystemServiceRegistry.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TextClassificationManager createService(ContextImpl contextImpl) {
                return new TextClassificationManager(contextImpl);
            }
        });
        registerService(Context.CLIPBOARD_SERVICE, ClipboardManager.class, new CachedServiceFetcher<ClipboardManager>() { // from class: android.app.SystemServiceRegistry.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ClipboardManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new ClipboardManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        SYSTEM_SERVICE_NAMES.put(android.text.ClipboardManager.class, Context.CLIPBOARD_SERVICE);
        registerService("connectivity", ConnectivityManager.class, new StaticApplicationContextServiceFetcher<ConnectivityManager>() { // from class: android.app.SystemServiceRegistry.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticApplicationContextServiceFetcher
            public ConnectivityManager createService(Context context) throws ServiceManager.ServiceNotFoundException {
                return new ConnectivityManager(context, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
            }
        });
        registerService(Context.NETD_SERVICE, IBinder.class, new StaticServiceFetcher<IBinder>() { // from class: android.app.SystemServiceRegistry.15
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public IBinder createService() throws ServiceManager.ServiceNotFoundException {
                return ServiceManager.getServiceOrThrow(Context.NETD_SERVICE);
            }
        });
        registerService(Context.TETHERING_SERVICE, TetheringManager.class, new AnonymousClass16());
        registerService(Context.IPSEC_SERVICE, IpSecManager.class, new CachedServiceFetcher<IpSecManager>() { // from class: android.app.SystemServiceRegistry.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public IpSecManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new IpSecManager(contextImpl, IIpSecService.Stub.asInterface(ServiceManager.getService(Context.IPSEC_SERVICE)));
            }
        });
        registerService(Context.VPN_MANAGEMENT_SERVICE, VpnManager.class, new CachedServiceFetcher<VpnManager>() { // from class: android.app.SystemServiceRegistry.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public VpnManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new VpnManager(contextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getService("connectivity")));
            }
        });
        registerService(Context.CONNECTIVITY_DIAGNOSTICS_SERVICE, ConnectivityDiagnosticsManager.class, new CachedServiceFetcher<ConnectivityDiagnosticsManager>() { // from class: android.app.SystemServiceRegistry.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ConnectivityDiagnosticsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new ConnectivityDiagnosticsManager(contextImpl, IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")));
            }
        });
        registerService(Context.TEST_NETWORK_SERVICE, TestNetworkManager.class, new StaticApplicationContextServiceFetcher<TestNetworkManager>() { // from class: android.app.SystemServiceRegistry.20
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticApplicationContextServiceFetcher
            public TestNetworkManager createService(Context context) throws ServiceManager.ServiceNotFoundException {
                try {
                    return new TestNetworkManager(ITestNetworkManager.Stub.asInterface(IConnectivityManager.Stub.asInterface(ServiceManager.getServiceOrThrow("connectivity")).startOrGetTestNetworkService()));
                } catch (RemoteException unused) {
                    throw new ServiceManager.ServiceNotFoundException(Context.TEST_NETWORK_SERVICE);
                }
            }
        });
        registerService(Context.COUNTRY_DETECTOR, CountryDetector.class, new StaticServiceFetcher<CountryDetector>() { // from class: android.app.SystemServiceRegistry.21
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public CountryDetector createService() throws ServiceManager.ServiceNotFoundException {
                return new CountryDetector(ICountryDetector.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.COUNTRY_DETECTOR)));
            }
        });
        registerService(Context.DEVICE_POLICY_SERVICE, DevicePolicyManager.class, new CachedServiceFetcher<DevicePolicyManager>() { // from class: android.app.SystemServiceRegistry.22
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DevicePolicyManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new DevicePolicyManager(contextImpl, IDevicePolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.DEVICE_POLICY_SERVICE)));
            }
        });
        registerService(Context.DOWNLOAD_SERVICE, DownloadManager.class, new CachedServiceFetcher<DownloadManager>() { // from class: android.app.SystemServiceRegistry.23
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DownloadManager createService(ContextImpl contextImpl) {
                return new DownloadManager(contextImpl);
            }
        });
        registerService(Context.BATTERY_SERVICE, BatteryManager.class, new CachedServiceFetcher<BatteryManager>() { // from class: android.app.SystemServiceRegistry.24
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public BatteryManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new BatteryManager(contextImpl, IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")), IBatteryPropertiesRegistrar.Stub.asInterface(ServiceManager.getServiceOrThrow("batteryproperties")));
            }
        });
        registerService("nfc", NfcManager.class, new CachedServiceFetcher<NfcManager>() { // from class: android.app.SystemServiceRegistry.25
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NfcManager createService(ContextImpl contextImpl) {
                return new NfcManager(contextImpl);
            }
        });
        registerService(Context.DROPBOX_SERVICE, DropBoxManager.class, new CachedServiceFetcher<DropBoxManager>() { // from class: android.app.SystemServiceRegistry.26
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DropBoxManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new DropBoxManager(contextImpl, IDropBoxManagerService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.DROPBOX_SERVICE)));
            }
        });
        registerService("input", InputManager.class, new StaticServiceFetcher<InputManager>() { // from class: android.app.SystemServiceRegistry.27
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public InputManager createService() {
                return InputManager.getInstance();
            }
        });
        registerService(Context.DISPLAY_SERVICE, DisplayManager.class, new CachedServiceFetcher<DisplayManager>() { // from class: android.app.SystemServiceRegistry.28
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DisplayManager createService(ContextImpl contextImpl) {
                return new DisplayManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.COLOR_DISPLAY_SERVICE, ColorDisplayManager.class, new CachedServiceFetcher<ColorDisplayManager>() { // from class: android.app.SystemServiceRegistry.29
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ColorDisplayManager createService(ContextImpl contextImpl) {
                return new ColorDisplayManager();
            }
        });
        registerService(Context.INPUT_METHOD_SERVICE, InputMethodManager.class, new ServiceFetcher<InputMethodManager>() { // from class: android.app.SystemServiceRegistry.30
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.ServiceFetcher
            public InputMethodManager getService(ContextImpl contextImpl) {
                return InputMethodManager.forContext(contextImpl.getOuterContext());
            }
        });
        registerService(Context.TEXT_SERVICES_MANAGER_SERVICE, TextServicesManager.class, new CachedServiceFetcher<TextServicesManager>() { // from class: android.app.SystemServiceRegistry.31
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TextServicesManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return TextServicesManager.createInstance(contextImpl);
            }
        });
        registerService(Context.KEYGUARD_SERVICE, KeyguardManager.class, new CachedServiceFetcher<KeyguardManager>() { // from class: android.app.SystemServiceRegistry.32
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public KeyguardManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new KeyguardManager(contextImpl);
            }
        });
        registerService(Context.LAYOUT_INFLATER_SERVICE, LayoutInflater.class, new CachedServiceFetcher<LayoutInflater>() { // from class: android.app.SystemServiceRegistry.33
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LayoutInflater createService(ContextImpl contextImpl) {
                return new PhoneLayoutInflater(contextImpl.getOuterContext());
            }
        });
        registerService("location", LocationManager.class, new CachedServiceFetcher<LocationManager>() { // from class: android.app.SystemServiceRegistry.34
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LocationManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new LocationManager(contextImpl, ILocationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("location")));
            }
        });
        registerService(Context.NETWORK_POLICY_SERVICE, NetworkPolicyManager.class, new CachedServiceFetcher<NetworkPolicyManager>() { // from class: android.app.SystemServiceRegistry.35
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NetworkPolicyManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new NetworkPolicyManager(contextImpl, INetworkPolicyManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.NETWORK_POLICY_SERVICE)));
            }
        });
        registerService("notification", NotificationManager.class, new CachedServiceFetcher<NotificationManager>() { // from class: android.app.SystemServiceRegistry.36
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NotificationManager createService(ContextImpl contextImpl) {
                Context outerContext = contextImpl.getOuterContext();
                return new NotificationManager(new ContextThemeWrapper(outerContext, Resources.selectSystemTheme(0, outerContext.getApplicationInfo().targetSdkVersion, 16973835, 16973935, 16974126, 16974130)), contextImpl.mMainThread.getHandler());
            }
        });
        registerService(Context.NSD_SERVICE, NsdManager.class, new CachedServiceFetcher<NsdManager>() { // from class: android.app.SystemServiceRegistry.37
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NsdManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new NsdManager(contextImpl.getOuterContext(), INsdManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.NSD_SERVICE)));
            }
        });
        registerService("power", PowerManager.class, new CachedServiceFetcher<PowerManager>() { // from class: android.app.SystemServiceRegistry.38
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public PowerManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new PowerManager(contextImpl.getOuterContext(), IPowerManager.Stub.asInterface(ServiceManager.getServiceOrThrow("power")), IThermalService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.THERMAL_SERVICE)), contextImpl.mMainThread.getHandler());
            }
        });
        registerService("recovery", RecoverySystem.class, new CachedServiceFetcher<RecoverySystem>() { // from class: android.app.SystemServiceRegistry.39
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RecoverySystem createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RecoverySystem(IRecoverySystem.Stub.asInterface(ServiceManager.getServiceOrThrow("recovery")));
            }
        });
        registerService("search", SearchManager.class, new CachedServiceFetcher<SearchManager>() { // from class: android.app.SystemServiceRegistry.40
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SearchManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SearchManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService("sensor", SensorManager.class, new CachedServiceFetcher<SensorManager>() { // from class: android.app.SystemServiceRegistry.41
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SensorManager createService(ContextImpl contextImpl) {
                return new SystemSensorManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler().getLooper());
            }
        });
        registerService(Context.SENSOR_PRIVACY_SERVICE, SensorPrivacyManager.class, new CachedServiceFetcher<SensorPrivacyManager>() { // from class: android.app.SystemServiceRegistry.42
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SensorPrivacyManager createService(ContextImpl contextImpl) {
                return SensorPrivacyManager.getInstance(contextImpl);
            }
        });
        registerService(Context.STATUS_BAR_SERVICE, StatusBarManager.class, new CachedServiceFetcher<StatusBarManager>() { // from class: android.app.SystemServiceRegistry.43
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public StatusBarManager createService(ContextImpl contextImpl) {
                return new StatusBarManager(contextImpl.getOuterContext());
            }
        });
        registerService("storage", StorageManager.class, new CachedServiceFetcher<StorageManager>() { // from class: android.app.SystemServiceRegistry.44
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public StorageManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new StorageManager(contextImpl, contextImpl.mMainThread.getHandler().getLooper());
            }
        });
        registerService(Context.STORAGE_STATS_SERVICE, StorageStatsManager.class, new CachedServiceFetcher<StorageStatsManager>() { // from class: android.app.SystemServiceRegistry.45
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public StorageStatsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new StorageStatsManager(contextImpl, IStorageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.STORAGE_STATS_SERVICE)));
            }
        });
        registerService(Context.SYSTEM_UPDATE_SERVICE, SystemUpdateManager.class, new CachedServiceFetcher<SystemUpdateManager>() { // from class: android.app.SystemServiceRegistry.46
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SystemUpdateManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SystemUpdateManager(ISystemUpdateManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.SYSTEM_UPDATE_SERVICE)));
            }
        });
        registerService(Context.SYSTEM_CONFIG_SERVICE, SystemConfigManager.class, new CachedServiceFetcher<SystemConfigManager>() { // from class: android.app.SystemServiceRegistry.47
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SystemConfigManager createService(ContextImpl contextImpl) {
                return new SystemConfigManager();
            }
        });
        registerService(Context.TELEPHONY_REGISTRY_SERVICE, TelephonyRegistryManager.class, new CachedServiceFetcher<TelephonyRegistryManager>() { // from class: android.app.SystemServiceRegistry.48
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TelephonyRegistryManager createService(ContextImpl contextImpl) {
                return new TelephonyRegistryManager(contextImpl);
            }
        });
        registerService(Context.TELECOM_SERVICE, TelecomManager.class, new CachedServiceFetcher<TelecomManager>() { // from class: android.app.SystemServiceRegistry.49
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TelecomManager createService(ContextImpl contextImpl) {
                return new TelecomManager(contextImpl.getOuterContext());
            }
        });
        registerService("mms", MmsManager.class, new CachedServiceFetcher<MmsManager>() { // from class: android.app.SystemServiceRegistry.50
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public MmsManager createService(ContextImpl contextImpl) {
                return new MmsManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.UI_MODE_SERVICE, UiModeManager.class, new CachedServiceFetcher<UiModeManager>() { // from class: android.app.SystemServiceRegistry.51
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public UiModeManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new UiModeManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.USB_SERVICE, UsbManager.class, new CachedServiceFetcher<UsbManager>() { // from class: android.app.SystemServiceRegistry.52
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public UsbManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new UsbManager(contextImpl, IUsbManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.USB_SERVICE)));
            }
        });
        registerService("adb", AdbManager.class, new CachedServiceFetcher<AdbManager>() { // from class: android.app.SystemServiceRegistry.53
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AdbManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AdbManager(contextImpl, IAdbManager.Stub.asInterface(ServiceManager.getServiceOrThrow("adb")));
            }
        });
        registerService(Context.SERIAL_SERVICE, SerialManager.class, new CachedServiceFetcher<SerialManager>() { // from class: android.app.SystemServiceRegistry.54
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SerialManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SerialManager(contextImpl, ISerialManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.SERIAL_SERVICE)));
            }
        });
        registerService(Context.VIBRATOR_SERVICE, Vibrator.class, new CachedServiceFetcher<Vibrator>() { // from class: android.app.SystemServiceRegistry.55
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public Vibrator createService(ContextImpl contextImpl) {
                return new SystemVibrator(contextImpl);
            }
        });
        registerService(Context.WALLPAPER_SERVICE, WallpaperManager.class, new CachedServiceFetcher<WallpaperManager>() { // from class: android.app.SystemServiceRegistry.56
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public WallpaperManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                IBinder service = ServiceManager.getService(Context.WALLPAPER_SERVICE);
                if (service == null) {
                    ApplicationInfo applicationInfo = contextImpl.getApplicationInfo();
                    if (applicationInfo.targetSdkVersion >= 28 && applicationInfo.isInstantApp()) {
                        throw new ServiceManager.ServiceNotFoundException(Context.WALLPAPER_SERVICE);
                    }
                    if (!Resources.getSystem().getBoolean(R.bool.config_enableWallpaperService)) {
                        return DisabledWallpaperManager.getInstance();
                    }
                    Log.e(SystemServiceRegistry.TAG, "No wallpaper service");
                }
                return new WallpaperManager(IWallpaperManager.Stub.asInterface(service), contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService("lowpan", LowpanManager.class, new CachedServiceFetcher<LowpanManager>() { // from class: android.app.SystemServiceRegistry.57
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LowpanManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new LowpanManager(contextImpl.getOuterContext(), ILowpanManager.Stub.asInterface(ServiceManager.getServiceOrThrow("lowpan")), ConnectivityThread.getInstanceLooper());
            }
        });
        registerService(Context.ETHERNET_SERVICE, EthernetManager.class, new CachedServiceFetcher<EthernetManager>() { // from class: android.app.SystemServiceRegistry.58
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public EthernetManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new EthernetManager(contextImpl.getOuterContext(), IEthernetManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.ETHERNET_SERVICE)));
            }
        });
        registerService(Context.WIFI_NL80211_SERVICE, WifiNl80211Manager.class, new CachedServiceFetcher<WifiNl80211Manager>() { // from class: android.app.SystemServiceRegistry.59
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public WifiNl80211Manager createService(ContextImpl contextImpl) {
                return new WifiNl80211Manager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.WINDOW_SERVICE, WindowManager.class, new CachedServiceFetcher<WindowManager>() { // from class: android.app.SystemServiceRegistry.60
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public WindowManager createService(ContextImpl contextImpl) {
                return new WindowManagerImpl(contextImpl);
            }
        });
        registerService(Context.GESTURE_SERVICE, GestureManager.class, new CachedServiceFetcher<GestureManager>() { // from class: android.app.SystemServiceRegistry.61
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public GestureManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new GestureManager(contextImpl.getOuterContext(), IGestureManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.GESTURE_SERVICE)));
            }
        });
        registerService(Context.LAUNCHER_SERVICE, LauncherManager.class, new CachedServiceFetcher<LauncherManager>() { // from class: android.app.SystemServiceRegistry.62
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LauncherManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new LauncherManager(contextImpl.getOuterContext(), ILauncherManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.LAUNCHER_SERVICE)));
            }
        });
        registerService("twilight", CarTwilightManager.class, new CachedServiceFetcher<CarTwilightManager>() { // from class: android.app.SystemServiceRegistry.63
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public CarTwilightManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new CarTwilightManager(contextImpl.getOuterContext(), ICarTwilightManager.Stub.asInterface(ServiceManager.getServiceOrThrow("twilight")));
            }
        });
        registerService("user", UserManager.class, new CachedServiceFetcher<UserManager>() { // from class: android.app.SystemServiceRegistry.64
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public UserManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new UserManager(contextImpl, IUserManager.Stub.asInterface(ServiceManager.getServiceOrThrow("user")));
            }
        });
        registerService(Context.APP_OPS_SERVICE, AppOpsManager.class, new CachedServiceFetcher<AppOpsManager>() { // from class: android.app.SystemServiceRegistry.65
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AppOpsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AppOpsManager(contextImpl, IAppOpsService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.APP_OPS_SERVICE)));
            }
        });
        registerService(Context.CAMERA_SERVICE, CameraManager.class, new CachedServiceFetcher<CameraManager>() { // from class: android.app.SystemServiceRegistry.66
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public CameraManager createService(ContextImpl contextImpl) {
                return new CameraManager(contextImpl);
            }
        });
        registerService(Context.LAUNCHER_APPS_SERVICE, LauncherApps.class, new CachedServiceFetcher<LauncherApps>() { // from class: android.app.SystemServiceRegistry.67
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LauncherApps createService(ContextImpl contextImpl) {
                return new LauncherApps(contextImpl);
            }
        });
        registerService(Context.RESTRICTIONS_SERVICE, RestrictionsManager.class, new CachedServiceFetcher<RestrictionsManager>() { // from class: android.app.SystemServiceRegistry.68
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RestrictionsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RestrictionsManager(contextImpl, IRestrictionsManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.RESTRICTIONS_SERVICE)));
            }
        });
        registerService(Context.PRINT_SERVICE, PrintManager.class, new CachedServiceFetcher<PrintManager>() { // from class: android.app.SystemServiceRegistry.69
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public PrintManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new PrintManager(contextImpl.getOuterContext(), contextImpl.getPackageManager().hasSystemFeature(PackageManager.FEATURE_PRINTING) ? IPrintManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.PRINT_SERVICE)) : null, contextImpl.getUserId(), UserHandle.getAppId(contextImpl.getApplicationInfo().uid));
            }
        });
        registerService(Context.COMPANION_DEVICE_SERVICE, CompanionDeviceManager.class, new CachedServiceFetcher<CompanionDeviceManager>() { // from class: android.app.SystemServiceRegistry.70
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public CompanionDeviceManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new CompanionDeviceManager(contextImpl.getPackageManager().hasSystemFeature(PackageManager.FEATURE_COMPANION_DEVICE_SETUP) ? ICompanionDeviceManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.COMPANION_DEVICE_SERVICE)) : null, contextImpl.getOuterContext());
            }
        });
        registerService(Context.CONSUMER_IR_SERVICE, ConsumerIrManager.class, new CachedServiceFetcher<ConsumerIrManager>() { // from class: android.app.SystemServiceRegistry.71
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ConsumerIrManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new ConsumerIrManager(contextImpl);
            }
        });
        registerService(Context.MEDIA_SESSION_SERVICE, MediaSessionManager.class, new CachedServiceFetcher<MediaSessionManager>() { // from class: android.app.SystemServiceRegistry.72
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public MediaSessionManager createService(ContextImpl contextImpl) {
                return new MediaSessionManager(contextImpl);
            }
        });
        registerService(Context.TRUST_SERVICE, TrustManager.class, new StaticServiceFetcher<TrustManager>() { // from class: android.app.SystemServiceRegistry.73
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public TrustManager createService() throws ServiceManager.ServiceNotFoundException {
                return new TrustManager(ServiceManager.getServiceOrThrow(Context.TRUST_SERVICE));
            }
        });
        registerService(Context.FINGERPRINT_SERVICE, FingerprintManager.class, new CachedServiceFetcher<FingerprintManager>() { // from class: android.app.SystemServiceRegistry.74
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public FingerprintManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                IBinder service;
                if (contextImpl.getApplicationInfo().targetSdkVersion >= 26) {
                    service = ServiceManager.getServiceOrThrow(Context.FINGERPRINT_SERVICE);
                } else {
                    service = ServiceManager.getService(Context.FINGERPRINT_SERVICE);
                }
                return new FingerprintManager(contextImpl.getOuterContext(), IFingerprintService.Stub.asInterface(service));
            }
        });
        registerService(Context.FACE_SERVICE, FaceManager.class, new CachedServiceFetcher<FaceManager>() { // from class: android.app.SystemServiceRegistry.75
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public FaceManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                IBinder service;
                if (contextImpl.getApplicationInfo().targetSdkVersion >= 26) {
                    service = ServiceManager.getServiceOrThrow(Context.FACE_SERVICE);
                } else {
                    service = ServiceManager.getService(Context.FACE_SERVICE);
                }
                return new FaceManager(contextImpl.getOuterContext(), IFaceService.Stub.asInterface(service));
            }
        });
        registerService(Context.IRIS_SERVICE, IrisManager.class, new CachedServiceFetcher<IrisManager>() { // from class: android.app.SystemServiceRegistry.76
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public IrisManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new IrisManager(contextImpl.getOuterContext(), IIrisService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.IRIS_SERVICE)));
            }
        });
        registerService(Context.BIOMETRIC_SERVICE, BiometricManager.class, new CachedServiceFetcher<BiometricManager>() { // from class: android.app.SystemServiceRegistry.77
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public BiometricManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new BiometricManager(contextImpl.getOuterContext(), IAuthService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.AUTH_SERVICE)));
            }
        });
        registerService(Context.TV_INPUT_SERVICE, TvInputManager.class, new CachedServiceFetcher<TvInputManager>() { // from class: android.app.SystemServiceRegistry.78
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TvInputManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new TvInputManager(ITvInputManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TV_INPUT_SERVICE)), contextImpl.getUserId());
            }
        });
        registerService(Context.TV_TUNER_RESOURCE_MGR_SERVICE, TunerResourceManager.class, new CachedServiceFetcher<TunerResourceManager>() { // from class: android.app.SystemServiceRegistry.79
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TunerResourceManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new TunerResourceManager(ITunerResourceManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TV_TUNER_RESOURCE_MGR_SERVICE)), contextImpl.getUserId());
            }
        });
        registerService(Context.NETWORK_SCORE_SERVICE, NetworkScoreManager.class, new CachedServiceFetcher<NetworkScoreManager>() { // from class: android.app.SystemServiceRegistry.80
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NetworkScoreManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new NetworkScoreManager(contextImpl);
            }
        });
        registerService(Context.USAGE_STATS_SERVICE, UsageStatsManager.class, new CachedServiceFetcher<UsageStatsManager>() { // from class: android.app.SystemServiceRegistry.81
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public UsageStatsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new UsageStatsManager(contextImpl.getOuterContext(), IUsageStatsManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.USAGE_STATS_SERVICE)));
            }
        });
        registerService(Context.NETWORK_STATS_SERVICE, NetworkStatsManager.class, new CachedServiceFetcher<NetworkStatsManager>() { // from class: android.app.SystemServiceRegistry.82
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NetworkStatsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new NetworkStatsManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.PERSISTENT_DATA_BLOCK_SERVICE, PersistentDataBlockManager.class, new StaticServiceFetcher<PersistentDataBlockManager>() { // from class: android.app.SystemServiceRegistry.83
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public PersistentDataBlockManager createService() throws ServiceManager.ServiceNotFoundException {
                IPersistentDataBlockService iPersistentDataBlockServiceAsInterface = IPersistentDataBlockService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.PERSISTENT_DATA_BLOCK_SERVICE));
                if (iPersistentDataBlockServiceAsInterface != null) {
                    return new PersistentDataBlockManager(iPersistentDataBlockServiceAsInterface);
                }
                return null;
            }
        });
        registerService(Context.OEM_LOCK_SERVICE, OemLockManager.class, new StaticServiceFetcher<OemLockManager>() { // from class: android.app.SystemServiceRegistry.84
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public OemLockManager createService() throws ServiceManager.ServiceNotFoundException {
                IOemLockService iOemLockServiceAsInterface = IOemLockService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.OEM_LOCK_SERVICE));
                if (iOemLockServiceAsInterface != null) {
                    return new OemLockManager(iOemLockServiceAsInterface);
                }
                return null;
            }
        });
        registerService(Context.MEDIA_PROJECTION_SERVICE, MediaProjectionManager.class, new CachedServiceFetcher<MediaProjectionManager>() { // from class: android.app.SystemServiceRegistry.85
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public MediaProjectionManager createService(ContextImpl contextImpl) {
                return new MediaProjectionManager(contextImpl);
            }
        });
        registerService(Context.APPWIDGET_SERVICE, AppWidgetManager.class, new CachedServiceFetcher<AppWidgetManager>() { // from class: android.app.SystemServiceRegistry.86
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AppWidgetManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AppWidgetManager(contextImpl, IAppWidgetService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.APPWIDGET_SERVICE)));
            }
        });
        registerService("midi", MidiManager.class, new CachedServiceFetcher<MidiManager>() { // from class: android.app.SystemServiceRegistry.87
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public MidiManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new MidiManager(IMidiManager.Stub.asInterface(ServiceManager.getServiceOrThrow("midi")));
            }
        });
        registerService(Context.RADIO_SERVICE, RadioManager.class, new CachedServiceFetcher<RadioManager>() { // from class: android.app.SystemServiceRegistry.88
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RadioManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RadioManager(contextImpl);
            }
        });
        registerService(Context.HARDWARE_PROPERTIES_SERVICE, HardwarePropertiesManager.class, new CachedServiceFetcher<HardwarePropertiesManager>() { // from class: android.app.SystemServiceRegistry.89
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public HardwarePropertiesManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new HardwarePropertiesManager(contextImpl, IHardwarePropertiesManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.HARDWARE_PROPERTIES_SERVICE)));
            }
        });
        registerService(Context.SOUND_TRIGGER_SERVICE, SoundTriggerManager.class, new CachedServiceFetcher<SoundTriggerManager>() { // from class: android.app.SystemServiceRegistry.90
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SoundTriggerManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SoundTriggerManager(contextImpl, contextImpl.getPackageManager().hasSystemFeature(PackageManager.FEATURE_PRINTING) ? ISoundTriggerService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.SOUND_TRIGGER_SERVICE)) : null);
            }
        });
        registerService("shortcut", ShortcutManager.class, new CachedServiceFetcher<ShortcutManager>() { // from class: android.app.SystemServiceRegistry.91
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ShortcutManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new ShortcutManager(contextImpl, IShortcutService.Stub.asInterface(ServiceManager.getServiceOrThrow("shortcut")));
            }
        });
        registerService("overlay", OverlayManager.class, new CachedServiceFetcher<OverlayManager>() { // from class: android.app.SystemServiceRegistry.92
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public OverlayManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new OverlayManager(contextImpl, IOverlayManager.Stub.asInterface(ServiceManager.getServiceOrThrow("overlay")));
            }
        });
        registerService(Context.NETWORK_WATCHLIST_SERVICE, NetworkWatchlistManager.class, new CachedServiceFetcher<NetworkWatchlistManager>() { // from class: android.app.SystemServiceRegistry.93
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public NetworkWatchlistManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new NetworkWatchlistManager(contextImpl, INetworkWatchlistManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.NETWORK_WATCHLIST_SERVICE)));
            }
        });
        registerService(Context.SYSTEM_HEALTH_SERVICE, SystemHealthManager.class, new CachedServiceFetcher<SystemHealthManager>() { // from class: android.app.SystemServiceRegistry.94
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SystemHealthManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SystemHealthManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
            }
        });
        registerService(Context.CONTEXTHUB_SERVICE, ContextHubManager.class, new CachedServiceFetcher<ContextHubManager>() { // from class: android.app.SystemServiceRegistry.95
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ContextHubManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new ContextHubManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler().getLooper());
            }
        });
        registerService(Context.INCIDENT_SERVICE, IncidentManager.class, new CachedServiceFetcher<IncidentManager>() { // from class: android.app.SystemServiceRegistry.96
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public IncidentManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new IncidentManager(contextImpl);
            }
        });
        registerService(Context.BUGREPORT_SERVICE, BugreportManager.class, new CachedServiceFetcher<BugreportManager>() { // from class: android.app.SystemServiceRegistry.97
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public BugreportManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new BugreportManager(contextImpl.getOuterContext(), IDumpstate.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.BUGREPORT_SERVICE)));
            }
        });
        registerService("autofill", AutofillManager.class, new CachedServiceFetcher<AutofillManager>() { // from class: android.app.SystemServiceRegistry.98
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AutofillManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AutofillManager(contextImpl.getOuterContext(), IAutoFillManager.Stub.asInterface(ServiceManager.getService("autofill")));
            }
        });
        registerService("content_capture", ContentCaptureManager.class, new CachedServiceFetcher<ContentCaptureManager>() { // from class: android.app.SystemServiceRegistry.99
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ContentCaptureManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                IContentCaptureManager iContentCaptureManagerAsInterface;
                Context outerContext = contextImpl.getOuterContext();
                ContentCaptureOptions contentCaptureOptions = outerContext.getContentCaptureOptions();
                if (contentCaptureOptions == null) {
                    return null;
                }
                if ((contentCaptureOptions.lite || contentCaptureOptions.isWhitelisted(outerContext)) && (iContentCaptureManagerAsInterface = IContentCaptureManager.Stub.asInterface(ServiceManager.getService("content_capture"))) != null) {
                    return new ContentCaptureManager(outerContext, iContentCaptureManagerAsInterface, contentCaptureOptions);
                }
                return null;
            }
        });
        registerService(Context.APP_PREDICTION_SERVICE, AppPredictionManager.class, new CachedServiceFetcher<AppPredictionManager>() { // from class: android.app.SystemServiceRegistry.100
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AppPredictionManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                if (ServiceManager.getService(Context.APP_PREDICTION_SERVICE) == null) {
                    return null;
                }
                return new AppPredictionManager(contextImpl);
            }
        });
        registerService(Context.CONTENT_SUGGESTIONS_SERVICE, ContentSuggestionsManager.class, new CachedServiceFetcher<ContentSuggestionsManager>() { // from class: android.app.SystemServiceRegistry.101
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public ContentSuggestionsManager createService(ContextImpl contextImpl) {
                return new ContentSuggestionsManager(contextImpl.getUserId(), IContentSuggestionsManager.Stub.asInterface(ServiceManager.getService(Context.CONTENT_SUGGESTIONS_SERVICE)));
            }
        });
        registerService(Context.VR_SERVICE, VrManager.class, new CachedServiceFetcher<VrManager>() { // from class: android.app.SystemServiceRegistry.102
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public VrManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new VrManager(IVrManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.VR_SERVICE)));
            }
        });
        registerService(Context.TIME_ZONE_RULES_MANAGER_SERVICE, RulesManager.class, new CachedServiceFetcher<RulesManager>() { // from class: android.app.SystemServiceRegistry.103
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RulesManager createService(ContextImpl contextImpl) {
                return new RulesManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.CROSS_PROFILE_APPS_SERVICE, CrossProfileApps.class, new CachedServiceFetcher<CrossProfileApps>() { // from class: android.app.SystemServiceRegistry.104
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public CrossProfileApps createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new CrossProfileApps(contextImpl.getOuterContext(), ICrossProfileApps.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.CROSS_PROFILE_APPS_SERVICE)));
            }
        });
        registerService("slice", SliceManager.class, new CachedServiceFetcher<SliceManager>() { // from class: android.app.SystemServiceRegistry.105
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public SliceManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new SliceManager(contextImpl.getOuterContext(), contextImpl.mMainThread.getHandler());
            }
        });
        registerService(Context.TIME_DETECTOR_SERVICE, TimeDetector.class, new CachedServiceFetcher<TimeDetector>() { // from class: android.app.SystemServiceRegistry.106
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TimeDetector createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new TimeDetectorImpl();
            }
        });
        registerService(Context.TIME_ZONE_DETECTOR_SERVICE, TimeZoneDetector.class, new CachedServiceFetcher<TimeZoneDetector>() { // from class: android.app.SystemServiceRegistry.107
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TimeZoneDetector createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new TimeZoneDetectorImpl();
            }
        });
        registerService("permission", PermissionManager.class, new CachedServiceFetcher<PermissionManager>() { // from class: android.app.SystemServiceRegistry.108
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public PermissionManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new PermissionManager(contextImpl.getOuterContext(), AppGlobals.getPackageManager());
            }
        });
        registerService(Context.PERMISSION_CONTROLLER_SERVICE, PermissionControllerManager.class, new CachedServiceFetcher<PermissionControllerManager>() { // from class: android.app.SystemServiceRegistry.109
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public PermissionControllerManager createService(ContextImpl contextImpl) {
                return new PermissionControllerManager(contextImpl.getOuterContext(), contextImpl.getMainThreadHandler());
            }
        });
        registerService(Context.ROLE_SERVICE, RoleManager.class, new CachedServiceFetcher<RoleManager>() { // from class: android.app.SystemServiceRegistry.110
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RoleManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RoleManager(contextImpl.getOuterContext());
            }
        });
        registerService(Context.ROLE_CONTROLLER_SERVICE, RoleControllerManager.class, new CachedServiceFetcher<RoleControllerManager>() { // from class: android.app.SystemServiceRegistry.111
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RoleControllerManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RoleControllerManager(contextImpl.getOuterContext());
            }
        });
        registerService("rollback", RollbackManager.class, new CachedServiceFetcher<RollbackManager>() { // from class: android.app.SystemServiceRegistry.112
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public RollbackManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new RollbackManager(contextImpl.getOuterContext(), IRollbackManager.Stub.asInterface(ServiceManager.getServiceOrThrow("rollback")));
            }
        });
        registerService(Context.DYNAMIC_SYSTEM_SERVICE, DynamicSystemManager.class, new CachedServiceFetcher<DynamicSystemManager>() { // from class: android.app.SystemServiceRegistry.113
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DynamicSystemManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new DynamicSystemManager(IDynamicSystemService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.DYNAMIC_SYSTEM_SERVICE)));
            }
        });
        registerService("batterystats", BatteryStatsManager.class, new CachedServiceFetcher<BatteryStatsManager>() { // from class: android.app.SystemServiceRegistry.114
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public BatteryStatsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new BatteryStatsManager(IBatteryStats.Stub.asInterface(ServiceManager.getServiceOrThrow("batterystats")));
            }
        });
        registerService(Context.DATA_LOADER_MANAGER_SERVICE, DataLoaderManager.class, new CachedServiceFetcher<DataLoaderManager>() { // from class: android.app.SystemServiceRegistry.115
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DataLoaderManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new DataLoaderManager(IDataLoaderManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.DATA_LOADER_MANAGER_SERVICE)));
            }
        });
        registerService(Context.LIGHTS_SERVICE, LightsManager.class, new CachedServiceFetcher<LightsManager>() { // from class: android.app.SystemServiceRegistry.116
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public LightsManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new LightsManager(contextImpl);
            }
        });
        registerService(Context.INCREMENTAL_SERVICE, IncrementalManager.class, new CachedServiceFetcher<IncrementalManager>() { // from class: android.app.SystemServiceRegistry.117
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public IncrementalManager createService(ContextImpl contextImpl) {
                IBinder service = ServiceManager.getService(Context.INCREMENTAL_SERVICE);
                if (service == null) {
                    return null;
                }
                return new IncrementalManager(IIncrementalService.Stub.asInterface(service));
            }
        });
        registerService(Context.FILE_INTEGRITY_SERVICE, FileIntegrityManager.class, new CachedServiceFetcher<FileIntegrityManager>() { // from class: android.app.SystemServiceRegistry.118
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public FileIntegrityManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new FileIntegrityManager(contextImpl.getOuterContext(), IFileIntegrityService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.FILE_INTEGRITY_SERVICE)));
            }
        });
        registerService(Context.APP_INTEGRITY_SERVICE, AppIntegrityManager.class, new CachedServiceFetcher<AppIntegrityManager>() { // from class: android.app.SystemServiceRegistry.119
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public AppIntegrityManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new AppIntegrityManager(IAppIntegrityManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.APP_INTEGRITY_SERVICE)));
            }
        });
        registerService(Context.DREAM_SERVICE, DreamManager.class, new CachedServiceFetcher<DreamManager>() { // from class: android.app.SystemServiceRegistry.120
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public DreamManager createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return new DreamManager(contextImpl);
            }
        });
        sInitializing = true;
        try {
            JobSchedulerFrameworkInitializer.registerServiceWrappers();
            BlobStoreManagerFrameworkInitializer.initialize();
            TelephonyFrameworkInitializer.registerServiceWrappers();
            WifiFrameworkInitializer.registerServiceWrappers();
            StatsFrameworkInitializer.registerServiceWrappers();
        } finally {
            sInitializing = false;
        }
    }

    private SystemServiceRegistry() {
    }

    /* JADX INFO: renamed from: android.app.SystemServiceRegistry$16, reason: invalid class name */
    class AnonymousClass16 extends CachedServiceFetcher<TetheringManager> {
        AnonymousClass16() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
        public TetheringManager createService(ContextImpl contextImpl) {
            return new TetheringManager(contextImpl, new Supplier() { // from class: android.app.-$$Lambda$SystemServiceRegistry$16$s6mZ42tuGUunhKa_5iwjLY5FGdM
                @Override // java.util.function.Supplier
                public final Object get() {
                    return ServiceManager.getService(Context.TETHERING_SERVICE);
                }
            });
        }
    }

    private static void ensureInitializing(String str) {
        Preconditions.checkState(sInitializing, "Internal error: " + str + " can only be called during class initialization.");
    }

    public static Object[] createServiceCache() {
        return new Object[sServiceCacheSize];
    }

    public static Object getSystemService(ContextImpl contextImpl, String str) {
        if (str == null) {
            return null;
        }
        ServiceFetcher<?> serviceFetcher = SYSTEM_SERVICE_FETCHERS.get(str);
        if (serviceFetcher == null) {
            if (sEnableServiceNotFoundWtf) {
                Slog.wtf(TAG, "Unknown manager requested: " + str);
            }
            return null;
        }
        Object service = serviceFetcher.getService(contextImpl);
        if (!sEnableServiceNotFoundWtf || service != null) {
            return service;
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -769002131) {
            if (iHashCode != 974854528) {
                if (iHashCode == 1085372378 && str.equals(Context.INCREMENTAL_SERVICE)) {
                    b = 2;
                }
            } else if (str.equals("content_capture")) {
                b = 0;
            }
        } else if (str.equals(Context.APP_PREDICTION_SERVICE)) {
            b = 1;
        }
        if (b != 0 && b != 1 && b != 2) {
            Slog.wtf(TAG, "Manager wrapper not available: " + str);
        }
        return null;
    }

    public static String getSystemServiceName(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        String str = SYSTEM_SERVICE_NAMES.get(cls);
        if (sEnableServiceNotFoundWtf && str == null) {
            Slog.wtf(TAG, "Unknown manager requested: " + cls.getCanonicalName());
        }
        return str;
    }

    private static <T> void registerService(String str, Class<T> cls, ServiceFetcher<T> serviceFetcher) {
        SYSTEM_SERVICE_NAMES.put(cls, str);
        SYSTEM_SERVICE_FETCHERS.put(str, serviceFetcher);
        SYSTEM_SERVICE_CLASS_NAMES.put(str, cls.getSimpleName());
    }

    public static String getSystemServiceClassName(String str) {
        return SYSTEM_SERVICE_CLASS_NAMES.get(str);
    }

    @SystemApi
    public static <TServiceClass> void registerStaticService(final String str, Class<TServiceClass> cls, final StaticServiceProducerWithBinder<TServiceClass> staticServiceProducerWithBinder) {
        ensureInitializing("registerStaticService");
        Preconditions.checkStringNotEmpty(str);
        Objects.requireNonNull(cls);
        Objects.requireNonNull(staticServiceProducerWithBinder);
        registerService(str, cls, new StaticServiceFetcher<TServiceClass>() { // from class: android.app.SystemServiceRegistry.121
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public TServiceClass createService() throws ServiceManager.ServiceNotFoundException {
                return (TServiceClass) staticServiceProducerWithBinder.createService(ServiceManager.getServiceOrThrow(str));
            }
        });
    }

    @SystemApi
    public static <TServiceClass> void registerStaticService(String str, Class<TServiceClass> cls, final StaticServiceProducerWithoutBinder<TServiceClass> staticServiceProducerWithoutBinder) {
        ensureInitializing("registerStaticService");
        Preconditions.checkStringNotEmpty(str);
        Objects.requireNonNull(cls);
        Objects.requireNonNull(staticServiceProducerWithoutBinder);
        registerService(str, cls, new StaticServiceFetcher<TServiceClass>() { // from class: android.app.SystemServiceRegistry.122
            @Override // android.app.SystemServiceRegistry.StaticServiceFetcher
            public TServiceClass createService() {
                return (TServiceClass) staticServiceProducerWithoutBinder.createService();
            }
        });
    }

    @SystemApi
    public static <TServiceClass> void registerContextAwareService(final String str, Class<TServiceClass> cls, final ContextAwareServiceProducerWithBinder<TServiceClass> contextAwareServiceProducerWithBinder) {
        ensureInitializing("registerContextAwareService");
        Preconditions.checkStringNotEmpty(str);
        Objects.requireNonNull(cls);
        Objects.requireNonNull(contextAwareServiceProducerWithBinder);
        registerService(str, cls, new CachedServiceFetcher<TServiceClass>() { // from class: android.app.SystemServiceRegistry.123
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TServiceClass createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException {
                return (TServiceClass) contextAwareServiceProducerWithBinder.createService(contextImpl.getOuterContext(), ServiceManager.getServiceOrThrow(str));
            }
        });
    }

    @SystemApi
    public static <TServiceClass> void registerContextAwareService(String str, Class<TServiceClass> cls, final ContextAwareServiceProducerWithoutBinder<TServiceClass> contextAwareServiceProducerWithoutBinder) {
        ensureInitializing("registerContextAwareService");
        Preconditions.checkStringNotEmpty(str);
        Objects.requireNonNull(cls);
        Objects.requireNonNull(contextAwareServiceProducerWithoutBinder);
        registerService(str, cls, new CachedServiceFetcher<TServiceClass>() { // from class: android.app.SystemServiceRegistry.124
            @Override // android.app.SystemServiceRegistry.CachedServiceFetcher
            public TServiceClass createService(ContextImpl contextImpl) {
                return (TServiceClass) contextAwareServiceProducerWithoutBinder.createService(contextImpl.getOuterContext());
            }
        });
    }

    static abstract class CachedServiceFetcher<T> implements ServiceFetcher<T> {
        private final int mCacheIndex = SystemServiceRegistry.access$008();

        public abstract T createService(ContextImpl contextImpl) throws ServiceManager.ServiceNotFoundException;

        CachedServiceFetcher() {
        }

        @Override // android.app.SystemServiceRegistry.ServiceFetcher
        public final T getService(ContextImpl contextImpl) {
            T t;
            boolean z;
            Object[] objArr = contextImpl.mServiceCache;
            int[] iArr = contextImpl.mServiceInitializationStateArray;
            boolean zInterrupted = false;
            while (true) {
                synchronized (objArr) {
                    t = (T) objArr[this.mCacheIndex];
                    if (t != null || iArr[this.mCacheIndex] == 3) {
                        break;
                    }
                    if (iArr[this.mCacheIndex] == 2) {
                        iArr[this.mCacheIndex] = 0;
                    }
                    if (iArr[this.mCacheIndex] == 0) {
                        iArr[this.mCacheIndex] = 1;
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        try {
                            try {
                                T tCreateService = createService(contextImpl);
                                synchronized (objArr) {
                                    objArr[this.mCacheIndex] = tCreateService;
                                    iArr[this.mCacheIndex] = 2;
                                    objArr.notifyAll();
                                }
                                t = tCreateService;
                                break;
                            } catch (ServiceManager.ServiceNotFoundException e) {
                                SystemServiceRegistry.onServiceNotFound(e);
                                synchronized (objArr) {
                                    objArr[this.mCacheIndex] = null;
                                    iArr[this.mCacheIndex] = 3;
                                    objArr.notifyAll();
                                    t = null;
                                }
                            }
                        } catch (Throwable th) {
                            synchronized (objArr) {
                                objArr[this.mCacheIndex] = null;
                                iArr[this.mCacheIndex] = 3;
                                objArr.notifyAll();
                                throw th;
                            }
                        }
                    } else {
                        synchronized (objArr) {
                            while (iArr[this.mCacheIndex] < 2) {
                                try {
                                    zInterrupted |= Thread.interrupted();
                                    objArr.wait();
                                } catch (InterruptedException unused) {
                                    Slog.w(SystemServiceRegistry.TAG, "getService() interrupted");
                                    zInterrupted = true;
                                }
                            }
                        }
                    }
                }
            }
            if (zInterrupted) {
                Thread.currentThread().interrupt();
            }
            return t;
        }
    }

    static abstract class StaticServiceFetcher<T> implements ServiceFetcher<T> {
        private T mCachedInstance;

        public abstract T createService() throws ServiceManager.ServiceNotFoundException;

        StaticServiceFetcher() {
        }

        @Override // android.app.SystemServiceRegistry.ServiceFetcher
        public final T getService(ContextImpl contextImpl) {
            T t;
            synchronized (this) {
                if (this.mCachedInstance == null) {
                    try {
                        this.mCachedInstance = createService();
                    } catch (ServiceManager.ServiceNotFoundException e) {
                        SystemServiceRegistry.onServiceNotFound(e);
                    }
                    t = this.mCachedInstance;
                } else {
                    t = this.mCachedInstance;
                }
            }
            return t;
        }
    }

    static abstract class StaticApplicationContextServiceFetcher<T> implements ServiceFetcher<T> {
        private T mCachedInstance;

        public abstract T createService(Context context) throws ServiceManager.ServiceNotFoundException;

        StaticApplicationContextServiceFetcher() {
        }

        @Override // android.app.SystemServiceRegistry.ServiceFetcher
        public final T getService(ContextImpl contextImpl) {
            T t;
            synchronized (this) {
                if (this.mCachedInstance == null) {
                    Context applicationContext = contextImpl.getApplicationContext();
                    if (applicationContext != null) {
                        contextImpl = applicationContext;
                    }
                    try {
                        this.mCachedInstance = createService(contextImpl);
                    } catch (ServiceManager.ServiceNotFoundException e) {
                        SystemServiceRegistry.onServiceNotFound(e);
                    }
                    t = this.mCachedInstance;
                } else {
                    t = this.mCachedInstance;
                }
            }
            return t;
        }
    }

    public static void onServiceNotFound(ServiceManager.ServiceNotFoundException serviceNotFoundException) {
        if (Process.myUid() < 10000) {
            Log.wtf(TAG, serviceNotFoundException.getMessage(), serviceNotFoundException);
        } else {
            Log.w(TAG, serviceNotFoundException.getMessage());
        }
    }
}
