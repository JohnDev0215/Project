package com.android.bluetooth.btservice;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothActivityEnergyInfo;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetooth;
import android.bluetooth.IBluetoothCallback;
import android.bluetooth.IBluetoothMetadataListener;
import android.bluetooth.IBluetoothSocketManager;
import android.bluetooth.OobData;
import android.bluetooth.UidTraffic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.INetd;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.EventLog;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.a2dpsink.A2dpSinkService;
import com.android.bluetooth.auto.Constant;
import com.android.bluetooth.auto.DockEventReceiver;
import com.android.bluetooth.btservice.RemoteDevices;
import com.android.bluetooth.btservice.bluetoothkeystore.BluetoothKeystoreService;
import com.android.bluetooth.btservice.storage.DatabaseManager;
import com.android.bluetooth.btservice.storage.MetadataDatabase;
import com.android.bluetooth.gatt.GattService;
import com.android.bluetooth.hearingaid.HearingAidService;
import com.android.bluetooth.hfp.HeadsetService;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import com.android.bluetooth.hid.HidDeviceService;
import com.android.bluetooth.hid.HidHostService;
import com.android.bluetooth.map.BluetoothMapService;
import com.android.bluetooth.mapclient.MapClientService;
import com.android.bluetooth.pan.PanService;
import com.android.bluetooth.pbap.BluetoothPbapService;
import com.android.bluetooth.pbapclient.PbapClientService;
import com.android.bluetooth.sap.SapService;
import com.android.bluetooth.sdp.SdpManager;
import com.android.internal.app.IBatteryStats;
import com.android.internal.util.ArrayUtils;
import com.google.common.primitives.Ints;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ts.car.core.ServiceProvider;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.carplay.ICarPlayAppManager;

/* JADX INFO: loaded from: classes.dex */
public class AdapterService extends Service {
    private static final String ACTION_ALARM_WAKEUP = "com.android.bluetooth.btservice.action.ALARM_WAKEUP";
    public static final String ACTION_LOAD_ADAPTER_PROPERTIES = "com.android.bluetooth.btservice.action.LOAD_ADAPTER_PROPERTIES";
    public static final String ACTION_SERVICE_STATE_CHANGED = "com.android.bluetooth.btservice.action.STATE_CHANGED";
    public static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    static final String BLUETOOTH_BTSNOOP_DEFAULT_MODE_PROPERTY = "persist.bluetooth.btsnoopdefaultmode";
    static final String BLUETOOTH_BTSNOOP_LOG_MODE_PROPERTY = "persist.bluetooth.btsnooplogmode";
    public static final String BLUETOOTH_NAME_SET_KEY = "persist.bluetooth.btsname";
    static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    public static final String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";
    private static final int CONTROLLER_ENERGY_UPDATE_TIMEOUT_MILLIS = 30;
    private static final boolean DBG = true;
    public static final String EXTRA_ACTION = "action";
    private static final int INIT_DELAY_TIME = 7000;
    private static final String IS_BLE_SUPPORTED_PROPERTY = "persist.vendor.bt.is_ble_supported";
    static final String LOCAL_MAC_ADDRESS_PERM = "android.permission.LOCAL_MAC_ADDRESS";
    private static final String MESSAGE_ACCESS_PERMISSION_PREFERENCE_FILE = "message_access_permission";
    private static final int MESSAGE_PROFILE_SERVICE_REGISTERED = 2;
    private static final int MESSAGE_PROFILE_SERVICE_STATE_CHANGED = 1;
    private static final int MESSAGE_PROFILE_SERVICE_UNREGISTERED = 3;
    private static final int MIN_ADVT_INSTANCES_FOR_MA = 5;
    private static final int MIN_OFFLOADED_FILTERS = 10;
    private static final int MIN_OFFLOADED_SCAN_STORAGE_BYTES = 1024;
    private static final String PHONEBOOK_ACCESS_PERMISSION_PREFERENCE_FILE = "phonebook_access_permission";
    public static final int PROFILE_CONN_REJECTED = 2;
    static final String RECEIVE_MAP_PERM = "android.permission.RECEIVE_BLUETOOTH_MAP";
    private static final String SIM_ACCESS_PERMISSION_PREFERENCE_FILE = "sim_access_permission";
    private static final String TAG = "BluetoothAdapterService";
    private static final boolean VERBOSE = false;
    private static AdapterService sAdapterService;
    public static final BroadcastReceiver sUserSwitchedReceiver;
    private A2dpService mA2dpService;
    private A2dpSinkService mA2dpSinkService;
    private ActiveDeviceManager mActiveDeviceManager;
    private AdapterProperties mAdapterProperties;
    private AdapterState mAdapterStateMachine;
    private AlarmManager mAlarmManager;
    private AppOpsManager mAppOps;
    private IBatteryStats mBatteryStats;
    private AdapterServiceBinder mBinder;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothKeystoreService mBluetoothKeystoreService;
    private BluetoothSocketManagerBinder mBluetoothSocketManagerBinder;
    private BondStateMachine mBondStateMachine;
    private RemoteCallbackList<IBluetoothCallback> mCallbacks;
    private boolean mCleaningUp;
    private int mCurrentRequestId;
    private DatabaseManager mDatabaseManager;
    private DockEventReceiver mDockEventReceiver;
    private long mEnergyUsedTotalVoltAmpSecMicro;
    private HandlerThread mHandlerThread;
    private HeadsetClientService mHeadsetClientService;
    private HeadsetService mHeadsetService;
    private HearingAidService mHearingAidService;
    private HidDeviceService mHidDeviceService;
    private HidHostService mHidHostService;
    private long mIdleTimeTotalMs;
    private boolean mIsBleSupported;
    private JniCallbacks mJniCallbacks;
    private MapClientService mMapClientService;
    private BluetoothMapService mMapService;
    private boolean mNativeAvailable;
    private PanService mPanService;
    private PbapClientService mPbapClientService;
    private BluetoothPbapService mPbapService;
    private PendingIntent mPendingAlarm;
    private PhonePolicy mPhonePolicy;
    private PowerManager mPowerManager;
    private RemoteDevices mRemoteDevices;
    private long mRxTimeTotalMs;
    private SapService mSapService;
    private SilenceDeviceManager mSilenceDeviceManager;
    private int mStackReportedState;
    private long mTxTimeTotalMs;
    private UserManager mUserManager;
    private PowerManager.WakeLock mWakeLock;
    private String mWakeLockName;
    private WifiManager mWifiManager;
    private final Object mEnergyInfoLock = new Object();
    private final SparseArray<UidTraffic> mUidTraffic = new SparseArray<>();
    private final ArrayList<ProfileService> mRegisteredProfiles = new ArrayList<>();
    private final ArrayList<ProfileService> mRunningProfiles = new ArrayList<>();
    private String mSnoopLogSettingAtEnable = "empty";
    private String mDefaultSnoopLogSettingAtEnable = "empty";
    private final ArrayList<DiscoveringPackage> mDiscoveringPackages = new ArrayList<>();
    private ICarPlayAppManager mCarPlayAppManager = null;
    private IAndroidAutoAppManager mAndroidAutoAppManager = null;
    private boolean isAndroidAutoConnected = false;
    private boolean isCarPlayConnected = false;
    private SdpManager mSdpManager = null;
    private final HashMap<BluetoothDevice, ArrayList<IBluetoothMetadataListener>> mMetadataListeners = new HashMap<>();
    private final HashMap<String, Integer> mProfileServicesState = new HashMap<>();
    private boolean mQuietmode = false;
    private final AdapterServiceHandler mHandler = new AdapterServiceHandler();
    private final BroadcastReceiver mAlarmBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.AdapterService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (AdapterService.this) {
                AdapterService.this.mPendingAlarm = null;
                AdapterService.this.alarmFiredNative();
            }
        }
    };
    private final IServiceConnectionListener mAndroidAutoServiceConnectionListener = new IServiceConnectionListener() { // from class: com.android.bluetooth.btservice.AdapterService.4
        public void onServiceConnected(IPlatformService service) {
            Log.d(AdapterService.TAG, "AA onServiceConnected");
            AdapterService.this.isAndroidAutoConnected = true;
        }

        public void onServiceDisconnected(IPlatformService service) {
            Log.d(AdapterService.TAG, "AA onServiceDisconnected");
            AdapterService.this.isAndroidAutoConnected = false;
        }
    };
    private final IServiceConnectionListener mCarPlayServiceConnectionListener = new IServiceConnectionListener() { // from class: com.android.bluetooth.btservice.AdapterService.5
        public void onServiceConnected(IPlatformService service) {
            AdapterService.this.isCarPlayConnected = true;
        }

        public void onServiceDisconnected(IPlatformService service) {
            AdapterService.this.isCarPlayConnected = false;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public native void alarmFiredNative();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean cancelDiscoveryNative();

    static native void classInitNative();

    private native byte[] dumpMetricsNative();

    private native void dumpNative(FileDescriptor fileDescriptor, String[] strArr);

    private native void getLinkKeyNative(byte[] bArr);

    private native int getMetricIdNative(byte[] bArr);

    private native void interopDatabaseAddNative(int i, byte[] bArr, int i2);

    private native void interopDatabaseClearNative();

    private native byte[] obfuscateAddressNative(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean pinReplyNative(byte[] bArr, boolean z, int i, byte[] bArr2);

    private native int readEnergyInfo();

    private native boolean readLocalOobDataNative();

    private native boolean removeAclNative(byte[] bArr, int i);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean sspReplyNative(byte[] bArr, int i, boolean z, int i2);

    private native boolean startDiscoveryNative();

    native boolean addOutOfBandBondDeviceNative(byte[] bArr, byte[] bArr2, int i, int i2);

    native boolean cancelBondNative(byte[] bArr);

    native void cleanupNative();

    native int connectSocketNative(byte[] bArr, int i, byte[] bArr2, int i2, int i3, int i4);

    native boolean createBondNative(byte[] bArr, int i);

    native boolean createBondOutOfBandNative(byte[] bArr, int i, OobData oobData);

    native int createSocketChannelNative(int i, String str, byte[] bArr, int i2, int i3, int i4);

    native boolean disableNative();

    native boolean enableNative();

    native boolean factoryResetNative();

    native boolean getAdapterPropertiesNative();

    native boolean getAdapterPropertyNative(int i);

    native int getConnectionStateNative(byte[] bArr);

    native boolean getDevicePropertyNative(byte[] bArr, int i);

    native boolean getRemoteMasInstancesNative(byte[] bArr);

    native boolean getRemoteServicesNative(byte[] bArr);

    native boolean initNative(boolean z, boolean z2, int i, boolean z3);

    native boolean removeBondNative(byte[] bArr);

    native void requestMaximumTxDataLengthNative(byte[] bArr);

    native boolean sdpSearchNative(byte[] bArr, byte[] bArr2);

    native boolean setAdapterPropertyNative(int i);

    native boolean setAdapterPropertyNative(int i, byte[] bArr);

    native boolean setDevicePropertyNative(byte[] bArr, int i, byte[] bArr2);

    static {
        classInitNative();
        sUserSwitchedReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.AdapterService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if ("android.intent.action.USER_SWITCHED".equals(intent.getAction())) {
                    int fuid = intent.getIntExtra("android.intent.extra.user_handle", 0);
                    Utils.setForegroundUserId(fuid);
                }
            }
        };
    }

    public static synchronized AdapterService getAdapterService() {
        Log.d(TAG, "getAdapterService() - returning " + sAdapterService);
        return sAdapterService;
    }

    private static synchronized void setAdapterService(AdapterService instance) {
        Log.d(TAG, "setAdapterService() - trying to set service to " + instance);
        if (instance == null) {
            return;
        }
        sAdapterService = instance;
    }

    private static synchronized void clearAdapterService(AdapterService current) {
        if (sAdapterService == current) {
            sAdapterService = null;
        }
    }

    public void addProfile(ProfileService profile) {
        this.mHandler.obtainMessage(2, profile).sendToTarget();
    }

    public void removeProfile(ProfileService profile) {
        this.mHandler.obtainMessage(3, profile).sendToTarget();
    }

    public void onProfileServiceStateChanged(ProfileService profile, int state) {
        if (state != 12 && state != 10) {
            throw new IllegalArgumentException(BluetoothAdapter.nameForState(state));
        }
        Message m = this.mHandler.obtainMessage(1);
        m.obj = profile;
        m.arg1 = state;
        this.mHandler.sendMessage(m);
    }

    class AdapterServiceHandler extends Handler {
        AdapterServiceHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AdapterService.this.verboseLog("handleMessage() - Message: " + msg.what);
            int i = msg.what;
            if (i == 1) {
                AdapterService.this.verboseLog("handleMessage() - MESSAGE_PROFILE_SERVICE_STATE_CHANGED");
                processProfileServiceStateChanged((ProfileService) msg.obj, msg.arg1);
            } else if (i == 2) {
                AdapterService.this.verboseLog("handleMessage() - MESSAGE_PROFILE_SERVICE_REGISTERED");
                registerProfileService((ProfileService) msg.obj);
            } else if (i == 3) {
                AdapterService.this.verboseLog("handleMessage() - MESSAGE_PROFILE_SERVICE_UNREGISTERED");
                unregisterProfileService((ProfileService) msg.obj);
            }
        }

        private void registerProfileService(ProfileService profile) {
            if (!AdapterService.this.mRegisteredProfiles.contains(profile)) {
                AdapterService.this.mRegisteredProfiles.add(profile);
                return;
            }
            Log.e(AdapterService.TAG, profile.getName() + " already registered.");
        }

        private void unregisterProfileService(ProfileService profile) {
            if (AdapterService.this.mRegisteredProfiles.contains(profile)) {
                AdapterService.this.mRegisteredProfiles.remove(profile);
                return;
            }
            Log.e(AdapterService.TAG, profile.getName() + " not registered (UNREGISTER).");
        }

        private void processProfileServiceStateChanged(ProfileService profile, int state) {
            if (state == 10) {
                if (AdapterService.this.mRegisteredProfiles.contains(profile)) {
                    if (AdapterService.this.mRunningProfiles.contains(profile)) {
                        AdapterService.this.mRunningProfiles.remove(profile);
                        if (AdapterService.this.mRunningProfiles.size() != 1 || !GattService.class.getSimpleName().equals(((ProfileService) AdapterService.this.mRunningProfiles.get(0)).getName())) {
                            if (AdapterService.this.mRunningProfiles.size() == 0) {
                                AdapterService.this.disableNative();
                                return;
                            }
                            return;
                        }
                        AdapterService.this.mAdapterStateMachine.sendMessage(6);
                        return;
                    }
                    Log.e(AdapterService.TAG, profile.getName() + " not running.");
                    return;
                }
                Log.e(AdapterService.TAG, profile.getName() + " not registered (STATE_OFF).");
                return;
            }
            if (state == 12) {
                if (AdapterService.this.mRegisteredProfiles.contains(profile)) {
                    if (!AdapterService.this.mRunningProfiles.contains(profile)) {
                        AdapterService.this.mRunningProfiles.add(profile);
                        if (!GattService.class.getSimpleName().equals(profile.getName())) {
                            if (AdapterService.this.mRegisteredProfiles.size() == Config.getSupportedProfiles().length && AdapterService.this.mRegisteredProfiles.size() == AdapterService.this.mRunningProfiles.size()) {
                                AdapterService.this.mAdapterProperties.onBluetoothReady();
                                AdapterService.this.updateUuids();
                                AdapterService.this.setBluetoothClassFromConfig();
                                AdapterService.this.initProfileServices();
                                AdapterService.this.getAdapterPropertyNative(14);
                                if (AdapterService.this.isBleSupported()) {
                                    AdapterService.this.getAdapterPropertyNative(15);
                                }
                                AdapterService.this.mAdapterStateMachine.sendMessage(5);
                                return;
                            }
                            return;
                        }
                        AdapterService.this.enableNative();
                        return;
                    }
                    Log.e(AdapterService.TAG, profile.getName() + " already running.");
                    return;
                }
                Log.e(AdapterService.TAG, profile.getName() + " not registered (STATE_ON).");
                return;
            }
            Log.e(AdapterService.TAG, "Unhandled profile state: " + state);
        }
    }

    private void updateInteropDatabase() {
        char c;
        interopDatabaseClearNative();
        String interopString = Settings.Global.getString(getContentResolver(), "bluetooth_interoperability_list");
        if (interopString == null) {
            return;
        }
        Log.d(TAG, "updateInteropDatabase: [" + interopString + "]");
        String[] entries = interopString.split(";");
        int length = entries.length;
        char c2 = 0;
        int i = 0;
        while (i < length) {
            String entry = entries[i];
            String[] tokens = entry.split(",");
            if (tokens.length != 2) {
                c = c2;
            } else {
                try {
                    int feature = Integer.parseInt(tokens[1]);
                    int length2 = (tokens[c2].length() + 1) / 3;
                    if (length2 < 1 || length2 > 6) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("updateInteropDatabase: Malformed address string '");
                        c = 0;
                        sb.append(tokens[0]);
                        sb.append("'");
                        Log.e(TAG, sb.toString());
                    } else {
                        byte[] addr = new byte[6];
                        int offset = 0;
                        int i2 = 0;
                        while (i2 < tokens[c2].length()) {
                            if (tokens[c2].charAt(i2) == ':') {
                                i2++;
                            } else {
                                int offset2 = offset + 1;
                                try {
                                    addr[offset] = (byte) Integer.parseInt(tokens[c2].substring(i2, i2 + 2), 16);
                                    i2 += 2;
                                    offset = offset2;
                                    c2 = 0;
                                } catch (NumberFormatException e) {
                                    offset = 0;
                                }
                            }
                        }
                        if (offset == 0) {
                            c = 0;
                        } else {
                            interopDatabaseAddNative(feature, addr, length2);
                            c = 0;
                        }
                    }
                } catch (NumberFormatException e2) {
                    c = c2;
                    Log.e(TAG, "updateInteropDatabase: Invalid feature '" + tokens[1] + "'");
                }
            }
            i++;
            c2 = c;
        }
    }

    /* JADX WARN: Type inference failed for: r2v26, types: [com.android.bluetooth.btservice.AdapterService$1] */
    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        debugLog("onCreate()");
        RemoteDevices remoteDevices = new RemoteDevices(this, Looper.getMainLooper());
        this.mRemoteDevices = remoteDevices;
        remoteDevices.init();
        clearDiscoveringPackages();
        this.mBinder = new AdapterServiceBinder(this);
        this.mAdapterProperties = new AdapterProperties(this);
        this.mAdapterStateMachine = AdapterState.make(this);
        this.mJniCallbacks = new JniCallbacks(this, this.mAdapterProperties);
        BluetoothKeystoreService bluetoothKeystoreService = new BluetoothKeystoreService(isNiapMode());
        this.mBluetoothKeystoreService = bluetoothKeystoreService;
        bluetoothKeystoreService.start();
        int configCompareResult = this.mBluetoothKeystoreService.getCompareResult();
        boolean isAtvDevice = getApplicationContext().getPackageManager().hasSystemFeature("android.software.leanback_only");
        initNative(isGuest(), isNiapMode(), configCompareResult, isAtvDevice);
        this.mNativeAvailable = true;
        this.mIsBleSupported = SystemProperties.getBoolean(IS_BLE_SUPPORTED_PROPERTY, true);
        debugLog("mIsBleSupported is " + this.mIsBleSupported);
        this.mCallbacks = new RemoteCallbackList<>();
        this.mAppOps = (AppOpsManager) getSystemService(AppOpsManager.class);
        getAdapterPropertyNative(2);
        getAdapterPropertyNative(1);
        getAdapterPropertyNative(4);
        this.mAlarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.mPowerManager = (PowerManager) getSystemService("power");
        this.mUserManager = (UserManager) getSystemService("user");
        this.mBatteryStats = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
        this.mBluetoothKeystoreService.initJni();
        this.mSdpManager = SdpManager.init(this);
        registerReceiver(this.mAlarmBroadcastReceiver, new IntentFilter(ACTION_ALARM_WAKEUP));
        if (getResources().getBoolean(R.bool.enable_phone_policy)) {
            Log.i(TAG, "Phone policy enabled");
            PhonePolicy phonePolicy = new PhonePolicy(this, new ServiceFactory());
            this.mPhonePolicy = phonePolicy;
            phonePolicy.start();
        } else {
            Log.i(TAG, "Phone policy disabled");
        }
        ActiveDeviceManager activeDeviceManager = new ActiveDeviceManager(this, new ServiceFactory());
        this.mActiveDeviceManager = activeDeviceManager;
        activeDeviceManager.start();
        DatabaseManager databaseManager = new DatabaseManager(this);
        this.mDatabaseManager = databaseManager;
        databaseManager.start(MetadataDatabase.createDatabase(this));
        SilenceDeviceManager silenceDeviceManager = new SilenceDeviceManager(this, new ServiceFactory(), Looper.getMainLooper());
        this.mSilenceDeviceManager = silenceDeviceManager;
        silenceDeviceManager.start();
        this.mBluetoothSocketManagerBinder = new BluetoothSocketManagerBinder(this);
        setAdapterService(this);
        invalidateBluetoothCaches();
        new AsyncTask<Void, Void, Void>() { // from class: com.android.bluetooth.btservice.AdapterService.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... params) {
                AdapterService.this.getSharedPreferences(AdapterService.PHONEBOOK_ACCESS_PERMISSION_PREFERENCE_FILE, 0);
                AdapterService.this.getSharedPreferences(AdapterService.MESSAGE_ACCESS_PERMISSION_PREFERENCE_FILE, 0);
                AdapterService.this.getSharedPreferences(AdapterService.SIM_ACCESS_PERMISSION_PREFERENCE_FILE, 0);
                return null;
            }
        }.execute(new Void[0]);
        try {
            int systemUiUid = getApplicationContext().getPackageManager().getPackageUid("com.android.systemui", 1048576);
            Utils.setSystemUiUid(systemUiUid);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Unable to resolve SystemUI's UID.", e);
        }
        IntentFilter filter = new IntentFilter("android.intent.action.USER_SWITCHED");
        getApplicationContext().registerReceiverForAllUsers(sUserSwitchedReceiver, filter, null, null);
        int fuid = ActivityManager.getCurrentUser();
        Utils.setForegroundUserId(fuid);
        IntentFilter filterAuto = new IntentFilter();
        filterAuto.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filterAuto.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        filterAuto.addAction(Constant.LINK_LOSS_DETECTED);
        filterAuto.addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        filterAuto.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        DockEventReceiver dockEventReceiver = new DockEventReceiver();
        this.mDockEventReceiver = dockEventReceiver;
        registerReceiver(dockEventReceiver, filterAuto);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        initCarPlayAndAndroidAuto();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        debugLog("onBind()");
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        debugLog("onUnbind() - calling cleanup");
        cleanup();
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        debugLog("onDestroy()");
        if (!isMock()) {
            Log.i(TAG, "Force exit to cleanup internal state in Bluetooth stack");
            System.exit(0);
        }
    }

    void bringUpBle() {
        debugLog("bleOnProcessStart()");
        if (getResources().getBoolean(android.R.bool.config_appCompatUserAppAspectRatioSettingsIsEnabled)) {
            Config.init(getApplicationContext());
        }
        this.mRemoteDevices.reset();
        this.mAdapterProperties.init(this.mRemoteDevices);
        debugLog("bleOnProcessStart() - Make Bond State Machine");
        BondStateMachine bondStateMachineMake = BondStateMachine.make(this, this.mAdapterProperties, this.mRemoteDevices);
        this.mBondStateMachine = bondStateMachineMake;
        this.mJniCallbacks.init(bondStateMachineMake, this.mRemoteDevices);
        if (isBleSupported()) {
            try {
                this.mBatteryStats.noteResetBleScan();
            } catch (RemoteException e) {
                Log.w(TAG, "RemoteException trying to send a reset to BatteryStats");
            }
            BluetoothStatsLog.write_non_chained(2, -1, null, 2, false, false, false);
            setProfileServiceState(GattService.class, 12);
        }
    }

    void bringDownBle() {
        stopGattProfileService();
    }

    void stateChangeCallback(int status) {
        if (status == 0) {
            debugLog("stateChangeCallback: disableNative() completed");
            if (isBleSupported()) {
                debugLog("stateChangeCallback: disableNative() completed");
                this.mAdapterStateMachine.sendMessage(8);
                return;
            } else {
                debugLog("send BREDR_STOPPED due to BLE disabled");
                this.mAdapterStateMachine.sendMessage(6);
                return;
            }
        }
        if (status == 1) {
            if (isBleSupported()) {
                this.mAdapterStateMachine.sendMessage(7);
                return;
            } else {
                this.mAdapterStateMachine.sendMessage(13);
                return;
            }
        }
        Log.e(TAG, "Incorrect status " + status + " in stateChangeCallback");
    }

    void setBluetoothClassFromConfig() {
        int bluetoothClassConfig = retrieveBluetoothClassConfig();
        if (bluetoothClassConfig != 0) {
            this.mAdapterProperties.setBluetoothClass(new BluetoothClass(bluetoothClassConfig));
        }
    }

    private int retrieveBluetoothClassConfig() {
        return Settings.Global.getInt(getContentResolver(), "bluetooth_class_of_device", 0);
    }

    void startProfileServices() {
        debugLog("startCoreServices()");
        Class[] supportedProfileServices = Config.getSupportedProfiles();
        if (supportedProfileServices.length == 1 && GattService.class.getSimpleName().equals(supportedProfileServices[0].getSimpleName())) {
            this.mAdapterProperties.onBluetoothReady();
            updateUuids();
            setBluetoothClassFromConfig();
            this.mAdapterStateMachine.sendMessage(5);
            return;
        }
        setAllProfileServiceStates(supportedProfileServices, 12);
    }

    void stopProfileServices() {
        cancelDiscoveryNative();
        this.mAdapterProperties.setScanMode(0);
        Class[] supportedProfileServices = Config.getSupportedProfiles();
        if (supportedProfileServices.length == 1 && this.mRunningProfiles.size() == 1 && GattService.class.getSimpleName().equals(this.mRunningProfiles.get(0).getName())) {
            debugLog("stopProfileServices() - No profiles services to stop or already stopped.");
            this.mAdapterStateMachine.sendMessage(6);
        } else {
            setAllProfileServiceStates(supportedProfileServices, 10);
        }
    }

    private void stopGattProfileService() {
        this.mAdapterProperties.onBleDisable();
        if (this.mRunningProfiles.size() == 0) {
            debugLog("stopGattProfileService() - No profiles services to stop.");
            this.mAdapterStateMachine.sendMessage(8);
        }
        setProfileServiceState(GattService.class, 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateBluetoothGetStateCache() {
        BluetoothAdapter.invalidateBluetoothGetStateCache();
    }

    void updateAdapterState(int prevState, int newState) {
        this.mAdapterProperties.setState(newState);
        invalidateBluetoothGetStateCache();
        RemoteCallbackList<IBluetoothCallback> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int n = remoteCallbackList.beginBroadcast();
            debugLog("updateAdapterState() - Broadcasting state " + BluetoothAdapter.nameForState(newState) + " to " + n + " receivers.");
            for (int i = 0; i < n; i++) {
                try {
                    this.mCallbacks.getBroadcastItem(i).onBluetoothStateChange(prevState, newState);
                } catch (RemoteException e) {
                    debugLog("updateAdapterState() - Callback #" + i + " failed (" + e + ")");
                }
            }
            this.mCallbacks.finishBroadcast();
        }
        if (newState == 14) {
            this.mSnoopLogSettingAtEnable = SystemProperties.get(BLUETOOTH_BTSNOOP_LOG_MODE_PROPERTY, "empty");
            String string = Settings.Global.getString(getContentResolver(), "bluetooth_btsnoop_default_mode");
            this.mDefaultSnoopLogSettingAtEnable = string;
            SystemProperties.set(BLUETOOTH_BTSNOOP_DEFAULT_MODE_PROPERTY, string);
            return;
        }
        if (newState == 15 && prevState != 10) {
            String snoopLogSetting = SystemProperties.get(BLUETOOTH_BTSNOOP_LOG_MODE_PROPERTY, "empty");
            String snoopDefaultModeSetting = Settings.Global.getString(getContentResolver(), "bluetooth_btsnoop_default_mode");
            if (!TextUtils.equals(this.mSnoopLogSettingAtEnable, snoopLogSetting) || !TextUtils.equals(this.mDefaultSnoopLogSettingAtEnable, snoopDefaultModeSetting)) {
                this.mAdapterStateMachine.sendMessage(4);
            }
        }
    }

    void cleanup() {
        debugLog("cleanup()");
        if (this.mCleaningUp) {
            errorLog("cleanup() - Service already starting to cleanup, ignoring request...");
            return;
        }
        clearAdapterService(this);
        this.mCleaningUp = true;
        invalidateBluetoothCaches();
        unregisterReceiver(this.mAlarmBroadcastReceiver);
        unregisterReceiver(this.mDockEventReceiver);
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        PendingIntent pendingIntent = this.mPendingAlarm;
        if (pendingIntent != null) {
            this.mAlarmManager.cancel(pendingIntent);
            this.mPendingAlarm = null;
        }
        synchronized (this) {
            if (this.mWakeLock != null) {
                if (this.mWakeLock.isHeld()) {
                    this.mWakeLock.release();
                }
                this.mWakeLock = null;
            }
        }
        DatabaseManager databaseManager = this.mDatabaseManager;
        if (databaseManager != null) {
            databaseManager.cleanup();
        }
        AdapterState adapterState = this.mAdapterStateMachine;
        if (adapterState != null) {
            adapterState.doQuit();
        }
        BondStateMachine bondStateMachine = this.mBondStateMachine;
        if (bondStateMachine != null) {
            bondStateMachine.doQuit();
        }
        RemoteDevices remoteDevices = this.mRemoteDevices;
        if (remoteDevices != null) {
            remoteDevices.cleanup();
        }
        SdpManager sdpManager = this.mSdpManager;
        if (sdpManager != null) {
            sdpManager.cleanup();
            this.mSdpManager = null;
        }
        BluetoothKeystoreService bluetoothKeystoreService = this.mBluetoothKeystoreService;
        if (bluetoothKeystoreService != null) {
            bluetoothKeystoreService.cleanup();
        }
        if (this.mNativeAvailable) {
            debugLog("cleanup() - Cleaning up adapter native");
            cleanupNative();
            this.mNativeAvailable = false;
        }
        AdapterProperties adapterProperties = this.mAdapterProperties;
        if (adapterProperties != null) {
            adapterProperties.cleanup();
        }
        JniCallbacks jniCallbacks = this.mJniCallbacks;
        if (jniCallbacks != null) {
            jniCallbacks.cleanup();
        }
        PhonePolicy phonePolicy = this.mPhonePolicy;
        if (phonePolicy != null) {
            phonePolicy.cleanup();
        }
        SilenceDeviceManager silenceDeviceManager = this.mSilenceDeviceManager;
        if (silenceDeviceManager != null) {
            silenceDeviceManager.cleanup();
        }
        ActiveDeviceManager activeDeviceManager = this.mActiveDeviceManager;
        if (activeDeviceManager != null) {
            activeDeviceManager.cleanup();
        }
        HashMap<String, Integer> map = this.mProfileServicesState;
        if (map != null) {
            map.clear();
        }
        BluetoothSocketManagerBinder bluetoothSocketManagerBinder = this.mBluetoothSocketManagerBinder;
        if (bluetoothSocketManagerBinder != null) {
            bluetoothSocketManagerBinder.cleanUp();
            this.mBluetoothSocketManagerBinder = null;
        }
        AdapterServiceBinder adapterServiceBinder = this.mBinder;
        if (adapterServiceBinder != null) {
            adapterServiceBinder.cleanup();
            this.mBinder = null;
        }
        RemoteCallbackList<IBluetoothCallback> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            remoteCallbackList.kill();
        }
        debugLog("cleanup() exit");
    }

    private void invalidateBluetoothCaches() {
        BluetoothAdapter.invalidateGetProfileConnectionStateCache();
        BluetoothAdapter.invalidateIsOffloadedFilteringSupportedCache();
        BluetoothDevice.invalidateBluetoothGetBondStateCache();
        BluetoothAdapter.invalidateBluetoothGetStateCache();
    }

    private void setProfileServiceState(Class service, int state) {
        Intent intent = new Intent(this, (Class<?>) service);
        intent.putExtra(EXTRA_ACTION, ACTION_SERVICE_STATE_CHANGED);
        intent.putExtra("android.bluetooth.adapter.extra.STATE", state);
        startService(intent);
    }

    private void setAllProfileServiceStates(Class[] services, int state) {
        for (Class service : services) {
            if (!GattService.class.getSimpleName().equals(service.getSimpleName())) {
                setProfileServiceState(service, state);
            }
        }
    }

    private boolean isSupported(ParcelUuid[] localDeviceUuids, ParcelUuid[] remoteDeviceUuids, int profile, BluetoothDevice device) {
        if (remoteDeviceUuids == null || remoteDeviceUuids.length == 0) {
            Log.e(TAG, "isSupported: Remote Device Uuids Empty");
        }
        if (profile == 1) {
            return (ArrayUtils.contains(localDeviceUuids, BluetoothUuid.HSP_AG) && ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HSP)) || (ArrayUtils.contains(localDeviceUuids, BluetoothUuid.HFP_AG) && ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HFP));
        }
        if (profile == 16) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HFP_AG) && ArrayUtils.contains(localDeviceUuids, BluetoothUuid.HFP);
        }
        if (profile == 2) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.ADV_AUDIO_DIST) || ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.A2DP_SINK);
        }
        if (profile == 11) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.ADV_AUDIO_DIST) || ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.A2DP_SOURCE);
        }
        if (profile == 20) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.OBEX_OBJECT_PUSH);
        }
        if (profile == 4) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HID) || ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HOGP);
        }
        if (profile == 19) {
            return this.mHidDeviceService.getConnectionState(device) == 0;
        }
        if (profile == 5) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.NAP);
        }
        if (profile == 9) {
            return this.mMapService.getConnectionState(device) == 2;
        }
        if (profile == 6) {
            return this.mPbapService.getConnectionState(device) == 2;
        }
        if (profile == 18) {
            return true;
        }
        if (profile == 17) {
            return ArrayUtils.contains(localDeviceUuids, BluetoothUuid.PBAP_PCE) && ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.PBAP_PSE);
        }
        if (profile == 21) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.HEARING_AID);
        }
        if (profile == 10) {
            return ArrayUtils.contains(remoteDeviceUuids, BluetoothUuid.SAP);
        }
        Log.e(TAG, "isSupported: Unexpected profile passed in to function: " + profile);
        return false;
    }

    private boolean isAnyProfileEnabled(BluetoothDevice device) {
        A2dpService a2dpService = this.mA2dpService;
        if (a2dpService != null && a2dpService.getConnectionPolicy(device) > 0) {
            return true;
        }
        A2dpSinkService a2dpSinkService = this.mA2dpSinkService;
        if (a2dpSinkService != null && a2dpSinkService.getConnectionPolicy(device) > 0) {
            return true;
        }
        HeadsetService headsetService = this.mHeadsetService;
        if (headsetService != null && headsetService.getConnectionPolicy(device) > 0) {
            return true;
        }
        HeadsetClientService headsetClientService = this.mHeadsetClientService;
        if (headsetClientService != null && headsetClientService.getConnectionPolicy(device) > 0) {
            return true;
        }
        MapClientService mapClientService = this.mMapClientService;
        if (mapClientService != null && mapClientService.getConnectionPolicy(device) > 0) {
            return true;
        }
        HidHostService hidHostService = this.mHidHostService;
        if (hidHostService != null && hidHostService.getConnectionPolicy(device) > 0) {
            return true;
        }
        PanService panService = this.mPanService;
        if (panService != null && panService.getConnectionPolicy(device) > 0) {
            return true;
        }
        PbapClientService pbapClientService = this.mPbapClientService;
        if (pbapClientService != null && pbapClientService.getConnectionPolicy(device) > 0) {
            return true;
        }
        HearingAidService hearingAidService = this.mHearingAidService;
        return hearingAidService != null && hearingAidService.getConnectionPolicy(device) > 0;
    }

    private boolean connectEnabledProfiles(BluetoothDevice device) {
        ParcelUuid[] remoteDeviceUuids = getRemoteUuids(device);
        ParcelUuid[] localDeviceUuids = this.mAdapterProperties.getUuids();
        if (this.mA2dpService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 2, device) && this.mA2dpService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting A2dp");
            this.mA2dpService.connect(device);
        }
        if (this.mA2dpSinkService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 11, device) && this.mA2dpSinkService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting A2dp Sink");
            this.mA2dpSinkService.connect(device);
        }
        if (this.mHeadsetService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 1, device) && this.mHeadsetService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting Headset Profile");
            this.mHeadsetService.connect(device);
        }
        if (this.mHeadsetClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 16, device) && this.mHeadsetClientService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting HFP");
            this.mHeadsetClientService.connect(device);
        }
        if (this.mMapClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 18, device) && this.mMapClientService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting MAP");
            this.mMapClientService.connect(device);
        }
        if (this.mHidHostService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 4, device) && this.mHidHostService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting Hid Host Profile");
            this.mHidHostService.connect(device);
        }
        if (this.mPanService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 5, device) && this.mPanService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting Pan Profile");
            this.mPanService.connect(device);
        }
        if (this.mPbapClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 17, device) && this.mPbapClientService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting Pbap");
            this.mPbapClientService.connect(device);
        }
        if (this.mHearingAidService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 21, device) && this.mHearingAidService.getConnectionPolicy(device) > 0) {
            Log.i(TAG, "connectEnabledProfiles: Connecting Hearing Aid Profile");
            this.mHearingAidService.connect(device);
        }
        return true;
    }

    private boolean profileServicesRunning() {
        if (this.mRegisteredProfiles.size() == Config.getSupportedProfiles().length && this.mRegisteredProfiles.size() == this.mRunningProfiles.size()) {
            return true;
        }
        Log.e(TAG, "profileServicesRunning: One or more supported services not running");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initProfileServices() {
        Log.i(TAG, "initProfileServices: Initializing all bluetooth profile services");
        this.mA2dpService = A2dpService.getA2dpService();
        this.mA2dpSinkService = A2dpSinkService.getA2dpSinkService();
        this.mHeadsetService = HeadsetService.getHeadsetService();
        this.mHeadsetClientService = HeadsetClientService.getHeadsetClientService();
        this.mMapService = BluetoothMapService.getBluetoothMapService();
        this.mMapClientService = MapClientService.getMapClientService();
        this.mHidDeviceService = HidDeviceService.getHidDeviceService();
        this.mHidHostService = HidHostService.getHidHostService();
        this.mPanService = PanService.getPanService();
        this.mPbapService = BluetoothPbapService.getBluetoothPbapService();
        this.mPbapClientService = PbapClientService.getPbapClientService();
        this.mHearingAidService = HearingAidService.getHearingAidService();
        this.mSapService = SapService.getSapService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAvailable() {
        return !this.mCleaningUp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidLinkKey(String linkKey, int keyType, int pinLen) {
        return !linkKey.isEmpty() && keyType >= 0 && pinLen >= 0;
    }

    public static class AdapterServiceBinder extends IBluetooth.Stub {
        private AdapterService mService;

        AdapterServiceBinder(AdapterService svc) {
            this.mService = svc;
            svc.invalidateBluetoothGetStateCache();
            BluetoothAdapter.getDefaultAdapter().disableBluetoothGetStateCache();
        }

        public void cleanup() {
            this.mService = null;
        }

        public AdapterService getService() {
            AdapterService adapterService = this.mService;
            if (adapterService != null && adapterService.isAvailable()) {
                return this.mService;
            }
            return null;
        }

        public int getState() {
            AdapterService service = getService();
            if (service == null) {
                return 10;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getState();
        }

        public boolean enable(boolean quietMode) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "enable")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.enable(quietMode);
        }

        public boolean disable() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "disable")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.disable();
        }

        public String getAddress() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getAddress")) {
                return null;
            }
            Utils.enforceBluetoothPermission(service);
            Utils.enforceLocalMacAddressPermission(service);
            return Utils.getAddressStringFromByte(service.mAdapterProperties.getAddress());
        }

        public ParcelUuid[] getUuids() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getUuids")) {
                return new ParcelUuid[0];
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.getUuids();
        }

        public String getName() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getName")) {
                return null;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getName();
        }

        public boolean setName(String name) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setName")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            SystemProperties.set(AdapterService.BLUETOOTH_NAME_SET_KEY, name);
            return service.mAdapterProperties.setName(name);
        }

        public BluetoothClass getBluetoothClass() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getBluetoothClass")) {
                return null;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.mAdapterProperties.getBluetoothClass();
        }

        public boolean setBluetoothClass(BluetoothClass bluetoothClass) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setBluetoothClass")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (!service.mAdapterProperties.setBluetoothClass(bluetoothClass)) {
                return false;
            }
            return Settings.Global.putInt(service.getContentResolver(), "bluetooth_class_of_device", bluetoothClass.getClassOfDevice());
        }

        public int getIoCapability() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getIoCapability")) {
                return 255;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.mAdapterProperties.getIoCapability();
        }

        public boolean setIoCapability(int capability) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setIoCapability")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (AdapterService.isValidIoCapability(capability)) {
                return service.mAdapterProperties.setIoCapability(capability);
            }
            return false;
        }

        public int getLeIoCapability() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getLeIoCapability")) {
                return 255;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.mAdapterProperties.getLeIoCapability();
        }

        public boolean setLeIoCapability(int capability) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setLeIoCapability")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (AdapterService.isValidIoCapability(capability)) {
                return service.mAdapterProperties.setLeIoCapability(capability);
            }
            return false;
        }

        public int getScanMode() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getScanMode")) {
                return 20;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.getScanMode();
        }

        public boolean setScanMode(int mode, int duration) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setScanMode")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            service.mAdapterProperties.setDiscoverableTimeout(duration);
            return service.mAdapterProperties.setScanMode(AdapterService.convertScanModeToHal(mode));
        }

        public int getDiscoverableTimeout() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getDiscoverableTimeout")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.getDiscoverableTimeout();
        }

        public boolean setDiscoverableTimeout(int timeout) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setDiscoverableTimeout")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.setDiscoverableTimeout(timeout);
        }

        public boolean readLocalOobData() {
            if (!Utils.checkCaller()) {
                Log.w(AdapterService.TAG, "readLocalOobData() - Not allowed for non-active user");
                return false;
            }
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            return service.readLocalOobData();
        }

        public boolean startDiscovery(String callingPackage, String callingFeatureId) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "startDiscovery")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.startDiscovery(callingPackage, callingFeatureId);
        }

        public boolean cancelDiscovery() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "cancelDiscovery")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            service.debugLog("cancelDiscovery");
            return service.cancelDiscoveryNative();
        }

        public boolean isDiscovering() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "isDiscovering")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.isDiscovering();
        }

        public long getDiscoveryEndMillis() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getDiscoveryEndMillis")) {
                return -1L;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.mAdapterProperties.discoveryEndMillis();
        }

        public List<BluetoothDevice> getMostRecentlyConnectedDevices() {
            AdapterService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.mDatabaseManager.getMostRecentlyConnectedDevices();
        }

        public BluetoothDevice[] getBondedDevices() {
            AdapterService service = getService();
            if (service == null) {
                return new BluetoothDevice[0];
            }
            Utils.enforceBluetoothPermission(service);
            return service.getBondedDevices();
        }

        public int getAdapterConnectionState() {
            AdapterService service = getService();
            if (service == null) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.getConnectionState();
        }

        public int getProfileConnectionState(int profile) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getProfileConnectionState")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.mAdapterProperties.getProfileConnectionState(profile);
        }

        public boolean createBond(BluetoothDevice device, int transport, OobData oobData) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "createBond")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.createBond(device, transport, oobData);
        }

        public boolean addOutOfBandBondDevice(BluetoothDevice device, String linkKey, int linkKeyType, int pinLen) {
            if (!Utils.checkCallerAllowManagedProfiles(this.mService)) {
                Log.w(AdapterService.TAG, "addOutOfBandBondDevice() - Not allowed for non-active user");
                return false;
            }
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            if (!service.isValidLinkKey(linkKey, linkKeyType, pinLen)) {
                Log.w(AdapterService.TAG, "Invaild linkkey");
                return false;
            }
            return service.addOutOfBandBondDevice(device, linkKey, linkKeyType, pinLen);
        }

        public void getLinkKey(BluetoothDevice device) {
            if (!Utils.checkCallerAllowManagedProfiles(this.mService)) {
                Log.w(AdapterService.TAG, "getLinkKey() - Not allowed for non-active user");
                return;
            }
            AdapterService service = getService();
            if (service == null) {
                return;
            }
            service.getLinkKey(device);
        }

        public boolean cancelBondProcess(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "cancelBondProcess")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp != null) {
                deviceProp.setBondingInitiatedLocally(false);
            }
            return service.cancelBondNative(Utils.addressToBytes(device.getAddress()));
        }

        public boolean removeBond(BluetoothDevice device) {
            AdapterService service = getService();
            StringBuilder sb = new StringBuilder();
            sb.append("removeBond service: ");
            sb.append(service);
            sb.append(" callerIsSystemOrActiveUser:");
            sb.append(!Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "removeBond"));
            Log.d(AdapterService.TAG, sb.toString());
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "removeBond")) {
                Log.d(AdapterService.TAG, "removeBond service is null or !callerIsSystemOrActiveUser");
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            Log.d(AdapterService.TAG, "removeBond deviceProp: " + deviceProp);
            if (deviceProp != null) {
                Log.d(AdapterService.TAG, "removeBond deviceProp.getBondState(): " + deviceProp.getBondState());
            }
            if (deviceProp == null || deviceProp.getBondState() != 12) {
                return false;
            }
            deviceProp.setBondingInitiatedLocally(false);
            Message msg = service.mBondStateMachine.obtainMessage(3);
            msg.obj = device;
            service.mBondStateMachine.sendMessage(msg);
            return true;
        }

        public int getBondState(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null) {
                return 10;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getBondState(device);
        }

        public boolean isBondingInitiatedLocally(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            return deviceProp != null && deviceProp.isBondingInitiatedLocally();
        }

        public long getSupportedProfiles() {
            AdapterService service = getService();
            if (service == null) {
                return 0L;
            }
            return Config.getSupportedProfilesBitMask();
        }

        public int getConnectionState(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getConnectionState(device);
        }

        public boolean removeAcl(BluetoothDevice device, int transport) {
            Log.d(AdapterService.TAG, "removeAcl() Remove ACL called in AdapterService ");
            if (!Utils.checkCaller()) {
                Log.w(AdapterService.TAG, "removeAcl(): not allowed for non-active user");
                return false;
            }
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            return service.removeAcl(device, transport);
        }

        public boolean removeActiveDevice(int profiles) {
            if (!Utils.checkCaller()) {
                Log.w(AdapterService.TAG, "removeActiveDevice() - Not allowed for non-active user");
                return false;
            }
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            return service.setActiveDevice(null, profiles);
        }

        public boolean setActiveDevice(BluetoothDevice device, int profiles) {
            if (!Utils.checkCaller()) {
                Log.w(AdapterService.TAG, "setActiveDevice() - Not allowed for non-active user");
                return false;
            }
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            return service.setActiveDevice(device, profiles);
        }

        public boolean connectAllEnabledProfiles(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "connectAllEnabledProfiles")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.connectAllEnabledProfiles(device);
        }

        public boolean disconnectAllEnabledProfiles(BluetoothDevice device) {
            AdapterService service = getService();
            if ((true ^ Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "disconnectAllEnabledProfiles")) || (service == null)) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.disconnectAllEnabledProfiles(device);
        }

        public String getRemoteName(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getRemoteName")) {
                return null;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getRemoteName(device);
        }

        public int getRemoteType(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getRemoteType")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp != null) {
                return deviceProp.getDeviceType();
            }
            return 0;
        }

        public String getRemoteAlias(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getRemoteAlias")) {
                return null;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp != null) {
                return deviceProp.getAlias();
            }
            return null;
        }

        public boolean setRemoteAlias(BluetoothDevice device, String name) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setRemoteAlias")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp == null) {
                return false;
            }
            deviceProp.setAlias(device, name);
            return true;
        }

        public int getRemoteClass(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getRemoteClass")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp != null) {
                return deviceProp.getBluetoothClass();
            }
            return 0;
        }

        public ParcelUuid[] getRemoteUuids(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "getRemoteUuids")) {
                return new ParcelUuid[0];
            }
            Utils.enforceBluetoothPermission(service);
            return service.getRemoteUuids(device);
        }

        public boolean fetchRemoteUuids(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveOrManagedUser(service, AdapterService.TAG, "fetchRemoteUuids")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            service.mRemoteDevices.fetchUuids(device);
            return true;
        }

        public boolean setPin(BluetoothDevice device, boolean accept, int len, byte[] pinCode) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setPin")) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp == null || !deviceProp.isBondingOrBonded()) {
                return false;
            }
            if (pinCode.length != len) {
                EventLog.writeEvent(1397638484, "139287605", -1, "PIN code length mismatch");
                return false;
            }
            service.logUserBondResponse(device, accept, 3);
            return service.pinReplyNative(Utils.addressToBytes(device.getAddress()), accept, len, pinCode);
        }

        public boolean setPasskey(BluetoothDevice device, boolean accept, int len, byte[] passkey) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setPasskey")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp == null || !deviceProp.isBonding()) {
                return false;
            }
            if (passkey.length != len) {
                EventLog.writeEvent(1397638484, "139287605", -1, "Passkey length mismatch");
                return false;
            }
            service.logUserBondResponse(device, accept, 5);
            return service.sspReplyNative(Utils.addressToBytes(device.getAddress()), 1, accept, Utils.byteArrayToInt(passkey));
        }

        public boolean setPairingConfirmation(BluetoothDevice device, boolean accept) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setPairingConfirmation")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp == null || !deviceProp.isBonding()) {
                return false;
            }
            service.logUserBondResponse(device, accept, 5);
            return service.sspReplyNative(Utils.addressToBytes(device.getAddress()), 0, accept, 0);
        }

        public boolean getSilenceMode(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getSilenceMode")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.mSilenceDeviceManager.getSilenceMode(device);
        }

        public boolean setSilenceMode(BluetoothDevice device, boolean silence) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setSilenceMode")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.mSilenceDeviceManager.setSilenceMode(device, silence);
            return true;
        }

        public int getPhonebookAccessPermission(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getPhonebookAccessPermission")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getDeviceAccessFromPrefs(device, AdapterService.PHONEBOOK_ACCESS_PERMISSION_PREFERENCE_FILE);
        }

        public boolean setPhonebookAccessPermission(BluetoothDevice device, int value) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setPhonebookAccessPermission")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.setPhonebookAccessPermission(device, value);
            return true;
        }

        public int getMessageAccessPermission(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getMessageAccessPermission")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getDeviceAccessFromPrefs(device, AdapterService.MESSAGE_ACCESS_PERMISSION_PREFERENCE_FILE);
        }

        public boolean setMessageAccessPermission(BluetoothDevice device, int value) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setMessageAccessPermission")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.setMessageAccessPermission(device, value);
            return true;
        }

        public int getSimAccessPermission(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getSimAccessPermission")) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getDeviceAccessFromPrefs(device, AdapterService.SIM_ACCESS_PERMISSION_PREFERENCE_FILE);
        }

        public boolean setSimAccessPermission(BluetoothDevice device, int value) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setSimAccessPermission")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.setSimAccessPermission(device, value);
            return true;
        }

        public IBluetoothSocketManager getSocketManager() {
            AdapterService service = getService();
            if (service != null) {
                return IBluetoothSocketManager.Stub.asInterface(service.mBluetoothSocketManagerBinder);
            }
            return null;
        }

        public boolean sdpSearch(BluetoothDevice device, ParcelUuid uuid) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "sdpSearch")) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            if (service.mSdpManager != null) {
                service.mSdpManager.sdpSearch(device, uuid);
                return true;
            }
            return false;
        }

        public int getBatteryLevel(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getBatteryLevel")) {
                return -1;
            }
            Utils.enforceBluetoothPermission(service);
            RemoteDevices.DeviceProperties deviceProp = service.mRemoteDevices.getDeviceProperties(device);
            if (deviceProp == null) {
                return -1;
            }
            return deviceProp.getBatteryLevel();
        }

        public int getMaxConnectedAudioDevices() {
            AdapterService service = getService();
            if (service == null) {
                return 1;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getMaxConnectedAudioDevices();
        }

        public boolean isA2dpOffloadEnabled() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.isA2dpOffloadEnabled();
        }

        public boolean factoryReset() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (service.mDatabaseManager != null) {
                service.mDatabaseManager.factoryReset();
            }
            if (service.mBluetoothKeystoreService != null) {
                service.mBluetoothKeystoreService.factoryReset();
            }
            return service.factoryResetNative();
        }

        public void registerCallback(IBluetoothCallback callback) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "registerCallback")) {
                return;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.mCallbacks.register(callback);
        }

        public void unregisterCallback(IBluetoothCallback callback) {
            AdapterService service = getService();
            if (service == null || service.mCallbacks == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "unregisterCallback")) {
                return;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.mCallbacks.unregister(callback);
        }

        public boolean isMultiAdvertisementSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            int val = service.mAdapterProperties.getNumOfAdvertisementInstancesSupported();
            return val >= 5;
        }

        public boolean isOffloadedFilteringSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            int val = service.getNumOfOffloadedScanFilterSupported();
            return val >= 10;
        }

        public boolean isOffloadedScanBatchingSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            int val = service.getOffloadedScanResultStorage();
            return val >= 1024;
        }

        public boolean isLe2MPhySupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.isLe2MPhySupported();
        }

        public boolean isLeCodedPhySupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.isLeCodedPhySupported();
        }

        public boolean isLeExtendedAdvertisingSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.isLeExtendedAdvertisingSupported();
        }

        public boolean isLePeriodicAdvertisingSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPermission(service);
            return service.isLePeriodicAdvertisingSupported();
        }

        public int getLeMaximumAdvertisingDataLength() {
            AdapterService service = getService();
            if (service == null) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getLeMaximumAdvertisingDataLength();
        }

        public boolean isActivityAndEnergyReportingSupported() {
            AdapterService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.mAdapterProperties.isActivityAndEnergyReportingSupported();
        }

        public BluetoothActivityEnergyInfo reportActivityInfo() {
            AdapterService service = getService();
            if (service == null) {
                return null;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.reportActivityInfo();
        }

        public boolean registerMetadataListener(IBluetoothMetadataListener listener, BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "registerMetadataListener")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (service.mMetadataListeners != null) {
                ArrayList<IBluetoothMetadataListener> list = (ArrayList) service.mMetadataListeners.get(device);
                if (list == null) {
                    list = new ArrayList<>();
                } else if (list.contains(listener)) {
                    return true;
                }
                list.add(listener);
                service.mMetadataListeners.put(device, list);
                return true;
            }
            return false;
        }

        public boolean unregisterMetadataListener(BluetoothDevice device) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "unregisterMetadataListener")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (service.mMetadataListeners != null) {
                if (service.mMetadataListeners.containsKey(device)) {
                    service.mMetadataListeners.remove(device);
                    return true;
                }
                return true;
            }
            return false;
        }

        public boolean setMetadata(BluetoothDevice device, int key, byte[] value) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "setMetadata")) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            if (value.length <= 2048) {
                return service.mDatabaseManager.setCustomMeta(device, key, value);
            }
            return false;
        }

        public byte[] getMetadata(BluetoothDevice device, int key) {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "getMetadata")) {
                return null;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.mDatabaseManager.getCustomMeta(device, key);
        }

        public void requestActivityInfo(ResultReceiver result) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("controller_activity", reportActivityInfo());
            result.send(0, bundle);
        }

        public void onLeServiceUp() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "onLeServiceUp")) {
                return;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.mAdapterStateMachine.sendMessage(1);
        }

        public void onBrEdrDown() {
            AdapterService service = getService();
            if (service == null || !Utils.callerIsSystemOrActiveUser(AdapterService.TAG, "onBrEdrDown")) {
                return;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            service.mAdapterStateMachine.sendMessage(4);
        }

        public void dump(FileDescriptor fd, String[] args) {
            PrintWriter writer = new PrintWriter(new FileOutputStream(fd));
            AdapterService service = getService();
            if (service == null) {
                return;
            }
            Utils.enforceDumpPermission(service);
            service.dump(fd, writer, args);
            writer.close();
        }
    }

    public boolean isEnabled() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
        return this.mAdapterProperties.getState() == 12;
    }

    public int getState() {
        AdapterProperties adapterProperties = this.mAdapterProperties;
        if (adapterProperties != null) {
            return adapterProperties.getState();
        }
        return 10;
    }

    public synchronized boolean enable(boolean quietMode) {
        if (this.mUserManager.hasUserRestriction("no_bluetooth", UserHandle.SYSTEM)) {
            debugLog("enable() called when Bluetooth was disallowed");
            return false;
        }
        debugLog("enable() - Enable called with quiet mode status =  " + quietMode);
        this.mQuietmode = quietMode;
        if (isBleSupported()) {
            this.mAdapterStateMachine.sendMessage(3);
        } else {
            debugLog("enable() - send USER_TURN_ON due to BLE disabled");
            this.mAdapterStateMachine.sendMessage(1);
        }
        return true;
    }

    boolean disable() {
        debugLog("disable() called with mRunningProfiles.size() = " + this.mRunningProfiles.size());
        this.mAdapterStateMachine.sendMessage(2);
        return true;
    }

    public String getName() {
        return this.mAdapterProperties.getName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidIoCapability(int capability) {
        if (capability < 0 || capability >= 5) {
            Log.e(TAG, "Invalid IO capability value - " + capability);
            return false;
        }
        return true;
    }

    ArrayList<DiscoveringPackage> getDiscoveringPackages() {
        return this.mDiscoveringPackages;
    }

    boolean readLocalOobData() {
        debugLog("readLocalOobData");
        if (!isEnabled()) {
            errorLog("read fail due to BT isn't enabled");
            return false;
        }
        return readLocalOobDataNative();
    }

    void clearDiscoveringPackages() {
        synchronized (this.mDiscoveringPackages) {
            this.mDiscoveringPackages.clear();
        }
    }

    boolean startDiscovery(String callingPackage, String callingFeatureId) {
        String permission;
        UserHandle callingUser = UserHandle.of(UserHandle.getCallingUserId());
        debugLog("startDiscovery");
        this.mAppOps.checkPackage(Binder.getCallingUid(), callingPackage);
        boolean isQApp = Utils.isQApp(this, callingPackage);
        if (Utils.checkCallerHasNetworkSettingsPermission(this)) {
            permission = "android.permission.NETWORK_SETTINGS";
        } else if (Utils.checkCallerHasNetworkSetupWizardPermission(this)) {
            permission = "android.permission.NETWORK_SETUP_WIZARD";
        } else if (isQApp) {
            if (!Utils.checkCallerHasFineLocation(this, this.mAppOps, callingPackage, callingFeatureId, callingUser)) {
                return false;
            }
            permission = "android.permission.ACCESS_FINE_LOCATION";
        } else {
            if (!Utils.checkCallerHasCoarseLocation(this, this.mAppOps, callingPackage, callingFeatureId, callingUser)) {
                return false;
            }
            permission = "android.permission.ACCESS_COARSE_LOCATION";
        }
        synchronized (this.mDiscoveringPackages) {
            this.mDiscoveringPackages.add(new DiscoveringPackage(callingPackage, permission));
        }
        return startDiscoveryNative();
    }

    public BluetoothDevice[] getBondedDevices() {
        return this.mAdapterProperties.getBondedDevices();
    }

    public DatabaseManager getDatabase() {
        return this.mDatabaseManager;
    }

    boolean createBond(BluetoothDevice device, int transport, OobData oobData) {
        RemoteDevices.DeviceProperties deviceProp = this.mRemoteDevices.getDeviceProperties(device);
        if (deviceProp != null && deviceProp.getBondState() != 10) {
            return false;
        }
        this.mRemoteDevices.setBondingInitiatedLocally(Utils.getByteAddress(device));
        cancelDiscoveryNative();
        Message msg = this.mBondStateMachine.obtainMessage(1);
        msg.obj = device;
        msg.arg1 = transport;
        if (oobData != null) {
            Bundle oobDataBundle = new Bundle();
            oobDataBundle.putParcelable(BondStateMachine.OOBDATA, oobData);
            msg.setData(oobDataBundle);
        }
        this.mBondStateMachine.sendMessage(msg);
        return true;
    }

    boolean addOutOfBandBondDevice(BluetoothDevice device, String linkKey, int linkKeyType, int pinLen) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        RemoteDevices.DeviceProperties deviceProp = this.mRemoteDevices.getDeviceProperties(device);
        if (deviceProp != null && deviceProp.getBondState() != 10) {
            return false;
        }
        this.mRemoteDevices.setBondingInitiatedLocally(Utils.getByteAddress(device));
        Log.d(TAG, "addOutOfBandBondDevice, send ADD_OOB_BOND_DEVICE");
        Message msg = this.mBondStateMachine.obtainMessage(240);
        msg.obj = device;
        msg.arg1 = linkKeyType;
        msg.arg2 = pinLen;
        Bundle b = new Bundle();
        b.putString("linkKey", linkKey);
        msg.setData(b);
        this.mBondStateMachine.sendMessage(msg);
        return true;
    }

    void getLinkKey(BluetoothDevice device) {
        byte[] addr = Utils.getBytesFromAddress(device.getAddress());
        getLinkKeyNative(addr);
    }

    void sendGetLinkKeyIntent(String linkKey, String address, boolean keyFound, int keyType) {
        Intent intent = new Intent("android.bluetooth.adapter.action.CUSTOM_ACTION_RESULT");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", address);
        if (keyFound) {
            Log.d(TAG, "found linkkey " + keyFound);
            intent.putExtra("link_key", linkKey);
            intent.putExtra("link_key_type", keyType);
        } else {
            Log.e(TAG, "Can not find linkkey");
            intent.putExtra("link_key", INetd.NEXTHOP_NONE);
            intent.putExtra("link_key_type", -1);
        }
        sendBroadcast(intent, "android.permission.BLUETOOTH");
    }

    void onGetLinkKey(String linkKey, byte[] remoteAddr, boolean keyFound, int keyType) {
        String address = Utils.getAddressStringFromByte(remoteAddr);
        sendGetLinkKeyIntent(linkKey, address, keyFound, keyType);
    }

    void sendLocalOobDataIntent(int status, OobData oobData) {
        debugLog("sendLocalOobDataIntent, status is " + status);
        Intent intent = new Intent("android.bluetooth.adapter.action.LOCAL_OOB_DATA");
        intent.putExtra("android.bluetooth.adapter.extra.EXTRA_OOB_RESULT", status);
        intent.putExtra("android.bluetooth.adapter.extra.LOCAL_OOB_DATA", (Parcelable) oobData);
        sendBroadcast(intent, "android.permission.BLUETOOTH");
    }

    void readLocalOobDataCallback(int status, byte[] c192, byte[] r192, byte[] c256, byte[] r256) {
        debugLog("readLocalOobDataCallback");
        OobData oobData = new OobData();
        if (status == 0) {
            debugLog("Succeed to get local OOB data");
            oobData.setC192(c192);
            oobData.setR192(r192);
            oobData.setC256(c256);
            oobData.setR256(r256);
        } else {
            Log.e(TAG, "Fail to get local OOB data");
        }
        sendLocalOobDataIntent(status, oobData);
    }

    public boolean isQuietModeEnabled() {
        debugLog("isQuetModeEnabled() - Enabled = " + this.mQuietmode);
        return this.mQuietmode;
    }

    public void updateUuids() {
        debugLog("updateUuids() - Updating UUIDs for bonded devices");
        BluetoothDevice[] bondedDevices = getBondedDevices();
        if (bondedDevices == null) {
            return;
        }
        for (BluetoothDevice device : bondedDevices) {
            this.mRemoteDevices.updateUuids(device);
        }
    }

    public void deviceUuidUpdated(BluetoothDevice device) {
        Message msg = this.mBondStateMachine.obtainMessage(10);
        msg.obj = device;
        this.mBondStateMachine.sendMessage(msg);
    }

    public int getBondState(BluetoothDevice device) {
        RemoteDevices.DeviceProperties deviceProp = this.mRemoteDevices.getDeviceProperties(device);
        if (deviceProp == null) {
            return 10;
        }
        return deviceProp.getBondState();
    }

    int getConnectionState(BluetoothDevice device) {
        return getConnectionStateNative(Utils.addressToBytes(device.getAddress()));
    }

    public boolean removeAcl(BluetoothDevice device, int transport) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
        RemoteDevices.DeviceProperties deviceProp = this.mRemoteDevices.getDeviceProperties(device);
        if (deviceProp == null) {
            return false;
        }
        byte[] address = Utils.getBytesFromAddress(device.getAddress());
        return removeAclNative(address, transport);
    }

    public boolean setActiveDevice(BluetoothDevice device, int profiles) {
        HeadsetService headsetService;
        A2dpService a2dpService;
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        boolean setA2dp = false;
        boolean setHeadset = false;
        if (profiles != 0) {
            if (profiles == 1) {
                setHeadset = true;
            } else if (profiles == 2) {
                setA2dp = true;
                setHeadset = true;
            } else {
                return false;
            }
        } else {
            setA2dp = true;
        }
        if (setA2dp && (a2dpService = this.mA2dpService) != null && (device == null || a2dpService.getConnectionPolicy(device) == 100)) {
            Log.i(TAG, "setActiveDevice: Setting active A2dp device " + device);
            this.mA2dpService.setActiveDevice(device);
        }
        HearingAidService hearingAidService = this.mHearingAidService;
        if (hearingAidService != null && (device == null || hearingAidService.getConnectionPolicy(device) == 100)) {
            Log.i(TAG, "setActiveDevice: Setting active Hearing Aid " + device);
            this.mHearingAidService.setActiveDevice(device);
        }
        if (setHeadset && (headsetService = this.mHeadsetService) != null && (device == null || headsetService.getConnectionPolicy(device) == 100)) {
            Log.i(TAG, "setActiveDevice: Setting active Headset " + device);
            this.mHeadsetService.setActiveDevice(device);
        }
        return true;
    }

    public boolean connectAllEnabledProfiles(BluetoothDevice device) {
        if (!profileServicesRunning()) {
            Log.e(TAG, "connectAllEnabledProfiles: Not all profile services running");
            return false;
        }
        if (isAnyProfileEnabled(device)) {
            return connectEnabledProfiles(device);
        }
        int numProfilesConnected = 0;
        ParcelUuid[] remoteDeviceUuids = getRemoteUuids(device);
        ParcelUuid[] localDeviceUuids = this.mAdapterProperties.getUuids();
        if (this.mA2dpService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 2, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting A2dp");
            this.mA2dpService.setConnectionPolicy(device, 100);
            numProfilesConnected = 0 + 1;
        }
        if (this.mA2dpSinkService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 11, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting A2dp Sink");
            this.mA2dpSinkService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mHeadsetService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 1, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting Headset Profile");
            this.mHeadsetService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mHeadsetClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 16, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting HFP");
            this.mHeadsetClientService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mMapClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 18, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting MAP");
            this.mMapClientService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mHidHostService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 4, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting Hid Host Profile");
            this.mHidHostService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mPanService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 5, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting Pan Profile");
            this.mPanService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mPbapClientService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 17, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting Pbap");
            this.mPbapClientService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        if (this.mHearingAidService != null && isSupported(localDeviceUuids, remoteDeviceUuids, 21, device)) {
            Log.i(TAG, "connectAllEnabledProfiles: Connecting Hearing Aid Profile");
            this.mHearingAidService.setConnectionPolicy(device, 100);
            numProfilesConnected++;
        }
        Log.i(TAG, "connectAllEnabledProfiles: Number of Profiles Connected: " + numProfilesConnected);
        return true;
    }

    public boolean disconnectAllEnabledProfiles(BluetoothDevice device) {
        if (!profileServicesRunning()) {
            Log.e(TAG, "disconnectAllEnabledProfiles: Not all profile services bound");
            return false;
        }
        A2dpService a2dpService = this.mA2dpService;
        if (a2dpService != null && a2dpService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting A2dp");
            this.mA2dpService.disconnect(device);
        }
        A2dpSinkService a2dpSinkService = this.mA2dpSinkService;
        if (a2dpSinkService != null && a2dpSinkService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting A2dp Sink");
            this.mA2dpSinkService.disconnect(device);
        }
        HeadsetService headsetService = this.mHeadsetService;
        if (headsetService != null && headsetService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Headset Profile");
            this.mHeadsetService.disconnect(device);
        }
        HeadsetClientService headsetClientService = this.mHeadsetClientService;
        if (headsetClientService != null && headsetClientService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting HFP");
            this.mHeadsetClientService.disconnect(device);
        }
        MapClientService mapClientService = this.mMapClientService;
        if (mapClientService != null && mapClientService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting MAP Client");
            this.mMapClientService.disconnect(device);
        }
        BluetoothMapService bluetoothMapService = this.mMapService;
        if (bluetoothMapService != null && bluetoothMapService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting MAP");
            this.mMapService.disconnect(device);
        }
        HidDeviceService hidDeviceService = this.mHidDeviceService;
        if (hidDeviceService != null && hidDeviceService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Hid Device Profile");
            this.mHidDeviceService.disconnect(device);
        }
        HidHostService hidHostService = this.mHidHostService;
        if (hidHostService != null && hidHostService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Hid Host Profile");
            this.mHidHostService.disconnect(device);
        }
        PanService panService = this.mPanService;
        if (panService != null && panService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Pan Profile");
            this.mPanService.disconnect(device);
        }
        PbapClientService pbapClientService = this.mPbapClientService;
        if (pbapClientService != null && pbapClientService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Pbap Client");
            this.mPbapClientService.disconnect(device);
        }
        BluetoothPbapService bluetoothPbapService = this.mPbapService;
        if (bluetoothPbapService != null && bluetoothPbapService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Pbap Server");
            this.mPbapService.disconnect(device);
        }
        HearingAidService hearingAidService = this.mHearingAidService;
        if (hearingAidService != null && hearingAidService.getConnectionState(device) == 2) {
            Log.i(TAG, "disconnectAllEnabledProfiles: Disconnecting Hearing Aid Profile");
            this.mHearingAidService.disconnect(device);
            return true;
        }
        return true;
    }

    public String getRemoteName(BluetoothDevice device) {
        RemoteDevices.DeviceProperties deviceProp;
        RemoteDevices remoteDevices = this.mRemoteDevices;
        if (remoteDevices == null || (deviceProp = remoteDevices.getDeviceProperties(device)) == null) {
            return null;
        }
        return deviceProp.getName();
    }

    public ParcelUuid[] getRemoteUuids(BluetoothDevice device) {
        RemoteDevices.DeviceProperties deviceProp = this.mRemoteDevices.getDeviceProperties(device);
        if (deviceProp == null) {
            return null;
        }
        return deviceProp.getUuids();
    }

    void logUserBondResponse(BluetoothDevice device, boolean accepted, int event) {
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_BOND_STATE_CHANGED, obfuscateAddress(device), 0L, device.getType(), 11, event, accepted ? 0 : 2);
    }

    int getDeviceAccessFromPrefs(BluetoothDevice device, String prefFile) {
        SharedPreferences prefs = getSharedPreferences(prefFile, 0);
        if (!prefs.contains(device.getAddress())) {
            return 0;
        }
        if (prefs.getBoolean(device.getAddress(), false)) {
            return 1;
        }
        return 2;
    }

    void setDeviceAccessFromPrefs(BluetoothDevice device, int value, String prefFile) {
        SharedPreferences pref = getSharedPreferences(prefFile, 0);
        SharedPreferences.Editor editor = pref.edit();
        if (value == 0) {
            editor.remove(device.getAddress());
        } else {
            editor.putBoolean(device.getAddress(), value == 1);
        }
        editor.apply();
    }

    void setPhonebookAccessPermission(BluetoothDevice device, int value) {
        setDeviceAccessFromPrefs(device, value, PHONEBOOK_ACCESS_PERMISSION_PREFERENCE_FILE);
    }

    void setMessageAccessPermission(BluetoothDevice device, int value) {
        setDeviceAccessFromPrefs(device, value, MESSAGE_ACCESS_PERMISSION_PREFERENCE_FILE);
    }

    void setSimAccessPermission(BluetoothDevice device, int value) {
        setDeviceAccessFromPrefs(device, value, SIM_ACCESS_PERMISSION_PREFERENCE_FILE);
    }

    public boolean isRpaOffloadSupported() {
        Utils.enforceBluetoothPermission(this);
        return this.mAdapterProperties.isRpaOffloadSupported();
    }

    public int getNumOfOffloadedIrkSupported() {
        Utils.enforceBluetoothPermission(this);
        return this.mAdapterProperties.getNumOfOffloadedIrkSupported();
    }

    public int getNumOfOffloadedScanFilterSupported() {
        return this.mAdapterProperties.getNumOfOffloadedScanFilterSupported();
    }

    public int getOffloadedScanResultStorage() {
        return this.mAdapterProperties.getOffloadedScanResultStorage();
    }

    public boolean isLe2MPhySupported() {
        return this.mAdapterProperties.isLe2MPhySupported();
    }

    public boolean isLeCodedPhySupported() {
        return this.mAdapterProperties.isLeCodedPhySupported();
    }

    public boolean isLeExtendedAdvertisingSupported() {
        return this.mAdapterProperties.isLeExtendedAdvertisingSupported();
    }

    public boolean isLePeriodicAdvertisingSupported() {
        return this.mAdapterProperties.isLePeriodicAdvertisingSupported();
    }

    public int getLeMaximumAdvertisingDataLength() {
        return this.mAdapterProperties.getLeMaximumAdvertisingDataLength();
    }

    public int getMaxConnectedAudioDevices() {
        return this.mAdapterProperties.getMaxConnectedAudioDevices();
    }

    public boolean isA2dpOffloadEnabled() {
        return this.mAdapterProperties.isA2dpOffloadEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothActivityEnergyInfo reportActivityInfo() {
        BluetoothActivityEnergyInfo info;
        if (this.mAdapterProperties.getState() != 12 || !this.mAdapterProperties.isActivityAndEnergyReportingSupported()) {
            return null;
        }
        readEnergyInfo();
        synchronized (this.mEnergyInfoLock) {
            try {
                this.mEnergyInfoLock.wait(30L);
            } catch (InterruptedException e) {
            }
            info = new BluetoothActivityEnergyInfo(SystemClock.elapsedRealtime(), this.mStackReportedState, this.mTxTimeTotalMs, this.mRxTimeTotalMs, this.mIdleTimeTotalMs, this.mEnergyUsedTotalVoltAmpSecMicro);
            int arrayLen = 0;
            for (int i = 0; i < this.mUidTraffic.size(); i++) {
                UidTraffic traffic = this.mUidTraffic.valueAt(i);
                if (traffic.getTxBytes() != 0 || traffic.getRxBytes() != 0) {
                    arrayLen++;
                }
            }
            UidTraffic[] result = arrayLen > 0 ? new UidTraffic[arrayLen] : null;
            int putIdx = 0;
            for (int i2 = 0; i2 < this.mUidTraffic.size(); i2++) {
                UidTraffic traffic2 = this.mUidTraffic.valueAt(i2);
                if (traffic2.getTxBytes() != 0 || traffic2.getRxBytes() != 0) {
                    result[putIdx] = traffic2.clone();
                    putIdx++;
                }
            }
            info.setUidTraffic(result);
        }
        return info;
    }

    public int getTotalNumOfTrackableAdvertisements() {
        Utils.enforceBluetoothPermission(this);
        return this.mAdapterProperties.getTotalNumOfTrackableAdvertisements();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertScanModeToHal(int mode) {
        if (mode == 20) {
            return 0;
        }
        if (mode == 21) {
            return 1;
        }
        if (mode == 23) {
            return 2;
        }
        return -1;
    }

    static int convertScanModeFromHal(int mode) {
        if (mode == 0) {
            return 20;
        }
        if (mode == 1) {
            return 21;
        }
        if (mode == 2) {
            return 23;
        }
        return -1;
    }

    private boolean setWakeAlarm(long delayMillis, boolean shouldWake) {
        synchronized (this) {
            if (this.mPendingAlarm != null) {
                this.mAlarmManager.cancel(this.mPendingAlarm);
            }
            long wakeupTime = SystemClock.elapsedRealtime() + delayMillis;
            int type = shouldWake ? 2 : 3;
            Intent intent = new Intent(ACTION_ALARM_WAKEUP);
            PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, Ints.MAX_POWER_OF_TWO);
            this.mPendingAlarm = broadcast;
            this.mAlarmManager.setExact(type, wakeupTime, broadcast);
        }
        return true;
    }

    private boolean acquireWakeLock(String lockName) {
        synchronized (this) {
            if (this.mWakeLock == null) {
                this.mWakeLockName = lockName;
                this.mWakeLock = this.mPowerManager.newWakeLock(1, lockName);
            }
            if (!this.mWakeLock.isHeld()) {
                this.mWakeLock.acquire();
            }
        }
        return true;
    }

    private boolean releaseWakeLock(String lockName) {
        synchronized (this) {
            if (this.mWakeLock == null) {
                errorLog("Repeated wake lock release; aborting release: " + lockName);
                return false;
            }
            if (this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void energyInfoCallback(int r21, int r22, long r23, long r25, long r27, long r29, android.bluetooth.UidTraffic[] r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.btservice.AdapterService.energyInfoCallback(int, int, long, long, long, long, android.bluetooth.UidTraffic[]):void");
    }

    public void metadataChanged(String address, int key, byte[] value) {
        BluetoothDevice device = this.mRemoteDevices.getDevice(Utils.getBytesFromAddress(address));
        if (this.mMetadataListeners.containsKey(device)) {
            ArrayList<IBluetoothMetadataListener> list = this.mMetadataListeners.get(device);
            for (IBluetoothMetadataListener listener : list) {
                try {
                    listener.onMetadataChanged(device, key, value);
                } catch (RemoteException e) {
                    Log.w(TAG, "RemoteException when onMetadataChanged");
                }
            }
        }
    }

    private int getIdleCurrentMa() {
        return getResources().getInteger(android.R.integer.config_attentionMaximumExtension);
    }

    private int getTxCurrentMa() {
        return getResources().getInteger(android.R.integer.config_audio_ring_vol_default);
    }

    private int getRxCurrentMa() {
        return getResources().getInteger(android.R.integer.config_audio_notif_vol_steps);
    }

    private double getOperatingVolt() {
        return ((double) getResources().getInteger(android.R.integer.config_audio_notif_vol_default)) / 1000.0d;
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        if (args.length == 0) {
            writer.println("Skipping dump in APP SERVICES, see bluetooth_manager section.");
            writer.println("Use --print argument for dumpsys direct from AdapterService.");
            return;
        }
        verboseLog("dumpsys arguments, check for protobuf output: " + TextUtils.join(" ", args));
        if (args[0].equals("--proto-bin")) {
            dumpMetrics(fd);
            return;
        }
        writer.println();
        this.mAdapterProperties.dump(fd, writer, args);
        writer.println("mSnoopLogSettingAtEnable = " + this.mSnoopLogSettingAtEnable);
        writer.println("mDefaultSnoopLogSettingAtEnable = " + this.mDefaultSnoopLogSettingAtEnable);
        writer.println();
        this.mAdapterStateMachine.dump(fd, writer, args);
        StringBuilder sb = new StringBuilder();
        for (ProfileService profile : this.mRegisteredProfiles) {
            profile.dump(sb);
        }
        this.mSilenceDeviceManager.dump(fd, writer, args);
        this.mDatabaseManager.dump(writer);
        writer.write(sb.toString());
        writer.flush();
        dumpNative(fd, args);
    }

    private void dumpMetrics(FileDescriptor fd) {
        BluetoothMetricsProto.BluetoothLog.Builder metricsBuilder = BluetoothMetricsProto.BluetoothLog.newBuilder();
        byte[] nativeMetricsBytes = dumpMetricsNative();
        debugLog("dumpMetrics: native metrics size is " + nativeMetricsBytes.length);
        if (nativeMetricsBytes.length > 0) {
            try {
                metricsBuilder.mergeFrom(nativeMetricsBytes);
            } catch (InvalidProtocolBufferException ex) {
                Log.w(TAG, "dumpMetrics: problem parsing metrics protobuf, " + ex.getMessage());
                return;
            }
        }
        metricsBuilder.setNumBondedDevices(getBondedDevices().length);
        MetricsLogger.dumpProto(metricsBuilder);
        for (ProfileService profile : this.mRegisteredProfiles) {
            profile.dumpProto(metricsBuilder);
        }
        byte[] metricsBytes = Base64.encode(metricsBuilder.build().toByteArray(), 0);
        debugLog("dumpMetrics: combined metrics size is " + metricsBytes.length);
        try {
            FileOutputStream protoOut = new FileOutputStream(fd);
            try {
                protoOut.write(metricsBytes);
                protoOut.close();
            } finally {
            }
        } catch (IOException e) {
            errorLog("dumpMetrics: error writing combined protobuf to fd, " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void verboseLog(String msg) {
    }

    private void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    private boolean isGuest() {
        return UserManager.get(this).isGuestUser();
    }

    private boolean isNiapMode() {
        return ((DevicePolicyManager) getSystemService("device_policy")).isCommonCriteriaModeEnabled(null);
    }

    public byte[] obfuscateAddress(BluetoothDevice device) {
        if (device == null) {
            return new byte[0];
        }
        return obfuscateAddressNative(Utils.getByteAddress(device));
    }

    public int getMetricId(BluetoothDevice device) {
        if (device == null) {
            return 0;
        }
        return getMetricIdNative(Utils.getByteAddress(device));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAndroidAuto() {
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, this);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            iAndroidAutoAppManager.registerConnectionListener(this.mAndroidAutoServiceConnectionListener);
        }
    }

    public boolean isAndroidAutoConnect() {
        IAndroidAutoAppManager iAndroidAutoAppManager;
        int connectionStatus;
        if (!this.isAndroidAutoConnected || (iAndroidAutoAppManager = this.mAndroidAutoAppManager) == null) {
            return false;
        }
        try {
            connectionStatus = iAndroidAutoAppManager.getAndroidAutoSessionStatus();
            Log.d(TAG, "isAndroidAutoConnect  AA connectionStatus" + connectionStatus);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connectionStatus == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCarPlay() {
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, this);
        this.mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager != null) {
            iCarPlayAppManager.registerConnectionListener(this.mCarPlayServiceConnectionListener);
        }
    }

    public int getApConfigurationBand() {
        if (this.mWifiManager == null) {
            this.mWifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
        }
        SoftApConfiguration apConfiguration = this.mWifiManager.getSoftApConfiguration();
        if (apConfiguration == null) {
            return -1;
        }
        return apConfiguration.getBand();
    }

    public boolean disconnectCurrentBluetooth(String address) {
        if (address.isEmpty()) {
            return true;
        }
        Set<BluetoothDevice> bondedDevices = this.mBluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            return true;
        }
        boolean isRemoveOperation = false;
        for (BluetoothDevice device : bondedDevices) {
            if (device.isConnected() && !device.getAddress().equals(address)) {
                isRemoveOperation = true;
                removeAcl(device, 1);
            }
        }
        return isRemoveOperation;
    }

    public boolean isHasConnected(String address) {
        Set<BluetoothDevice> bondedDevices = this.mBluetoothAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            return false;
        }
        for (BluetoothDevice device : bondedDevices) {
            String ad = device.getAddress();
            if (!ad.equals(address) && device.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCarPlayConnect() {
        ICarPlayAppManager iCarPlayAppManager;
        int connectionStatus;
        if (!this.isCarPlayConnected || (iCarPlayAppManager = this.mCarPlayAppManager) == null) {
            return false;
        }
        try {
            connectionStatus = iCarPlayAppManager.getCarPlaySessionStatus();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return connectionStatus == 2;
    }

    private void initCarPlayAndAndroidAuto() {
        if (this.mHandlerThread == null) {
            this.mHandlerThread = new HandlerThread("Carplay.init");
        }
        this.mHandlerThread.start();
        Handler handler = new Handler(this.mHandlerThread.getLooper());
        handler.postDelayed(new Runnable() { // from class: com.android.bluetooth.btservice.AdapterService.6
            @Override // java.lang.Runnable
            public void run() {
                AdapterService.this.initCarPlay();
                AdapterService.this.initAndroidAuto();
            }
        }, 7000L);
    }

    public boolean isMock() {
        return false;
    }

    public boolean isBleSupported() {
        return this.mIsBleSupported;
    }
}
