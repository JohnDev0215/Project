package com.android.bluetooth.btservice.storage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetd;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.google.common.collect.EvictingQueue;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class DatabaseManager {
    private static final String LEGACY_A2DP_OPTIONAL_CODECS_ENABLED_PREFIX = "bluetooth_a2dp_optional_codecs_enabled_";
    private static final String LEGACY_A2DP_SINK_PRIORITY_PREFIX = "bluetooth_a2dp_sink_priority_";
    private static final String LEGACY_A2DP_SRC_PRIORITY_PREFIX = "bluetooth_a2dp_src_priority_";
    private static final String LEGACY_A2DP_SUPPORTS_OPTIONAL_CODECS_PREFIX = "bluetooth_a2dp_supports_optional_codecs_";
    private static final String LEGACY_BTSNOOP_DEFAULT_MODE = "bluetooth_btsnoop_default_mode";
    private static final String LEGACY_HEADSET_PRIORITY_PREFIX = "bluetooth_headset_priority_";
    private static final String LEGACY_HEARING_AID_PRIORITY_PREFIX = "bluetooth_hearing_aid_priority_";
    private static final String LEGACY_INPUT_DEVICE_PRIORITY_PREFIX = "bluetooth_input_device_priority_";
    private static final String LEGACY_MAP_CLIENT_PRIORITY_PREFIX = "bluetooth_map_client_priority_";
    private static final String LEGACY_MAP_PRIORITY_PREFIX = "bluetooth_map_priority_";
    private static final String LEGACY_PAN_PRIORITY_PREFIX = "bluetooth_pan_priority_";
    private static final String LEGACY_PBAP_CLIENT_PRIORITY_PREFIX = "bluetooth_pbap_client_priority_";
    private static final String LEGACY_SAP_PRIORITY_PREFIX = "bluetooth_sap_priority_";
    private static final int LOAD_DATABASE_TIMEOUT = 500;
    private static final String LOCAL_STORAGE = "LocalStorage";
    private static final int METADATA_CHANGED_LOG_MAX_SIZE = 20;
    private static final int MSG_CLEAR_DATABASE = 100;
    private static final int MSG_DELETE_DATABASE = 2;
    private static final int MSG_LOAD_DATABASE = 0;
    private static final int MSG_UPDATE_DATABASE = 1;
    private static final String TAG = "BluetoothDatabase";
    private AdapterService mAdapterService;
    private HandlerThread mHandlerThread = null;
    private Handler mHandler = null;
    private MetadataDatabase mDatabase = null;
    private boolean mMigratedFromSettingsGlobal = false;
    final Map<String, Metadata> mMetadataCache = new HashMap();
    private final Semaphore mSemaphore = new Semaphore(1);
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.storage.DatabaseManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.e(DatabaseManager.TAG, "Received intent with null action");
                return;
            }
            byte b = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != -1530327060) {
                if (iHashCode == 2116862345 && action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                    b = 0;
                }
            } else if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                b = 1;
            }
            if (b == 0) {
                int state = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                Objects.requireNonNull(device, "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
                DatabaseManager.this.bondStateChanged(device, state);
                return;
            }
            if (b == 1) {
                int state2 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                if (!DatabaseManager.this.mMigratedFromSettingsGlobal && state2 == 11) {
                    DatabaseManager.this.migrateSettingsGlobal();
                }
            }
        }
    };
    private final EvictingQueue<String> mMetadataChangedLog = EvictingQueue.create(20);

    public DatabaseManager(AdapterService service) {
        this.mAdapterService = null;
        this.mAdapterService = service;
    }

    class DatabaseHandler extends Handler {
        DatabaseHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            List<Metadata> list;
            int i = msg.what;
            if (i == 0) {
                synchronized (DatabaseManager.this.mDatabase) {
                    try {
                        list = DatabaseManager.this.mDatabase.load();
                    } catch (IllegalStateException e) {
                        Log.e(DatabaseManager.TAG, "Unable to open database: " + e);
                        DatabaseManager.this.mDatabase = MetadataDatabase.createDatabaseWithoutMigration(DatabaseManager.this.mAdapterService);
                        list = DatabaseManager.this.mDatabase.load();
                    }
                    DatabaseManager.this.compactLastConnectionTime(list);
                    DatabaseManager.this.cacheMetadata(list);
                }
                return;
            }
            if (i == 1) {
                Metadata data = (Metadata) msg.obj;
                synchronized (DatabaseManager.this.mDatabase) {
                    DatabaseManager.this.mDatabase.insert(data);
                }
                return;
            }
            if (i == 2) {
                String address = (String) msg.obj;
                synchronized (DatabaseManager.this.mDatabase) {
                    DatabaseManager.this.mDatabase.delete(address);
                }
                return;
            }
            if (i == 100) {
                synchronized (DatabaseManager.this.mDatabase) {
                    DatabaseManager.this.mDatabase.deleteAll();
                }
            }
        }
    }

    void bondStateChanged(BluetoothDevice device, int state) {
        synchronized (this.mMetadataCache) {
            String address = device.getAddress();
            if (state != 10) {
                if (this.mMetadataCache.containsKey(address)) {
                } else {
                    createMetadata(address, false);
                }
            } else {
                Metadata metadata = this.mMetadataCache.get(address);
                if (metadata != null) {
                    this.mMetadataCache.remove(address);
                    deleteDatabase(metadata);
                }
            }
        }
    }

    boolean isValidMetaKey(int key) {
        switch (key) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return true;
            default:
                Log.w(TAG, "Invalid metadata key " + key);
                return false;
        }
    }

    public boolean setCustomMeta(BluetoothDevice device, int key, byte[] newValue) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "setCustomMeta: device is null");
                return false;
            }
            if (!isValidMetaKey(key)) {
                Log.e(TAG, "setCustomMeta: meta key invalid " + key);
                return false;
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                createMetadata(address, false);
            }
            Metadata data = this.mMetadataCache.get(address);
            byte[] oldValue = data.getCustomizedMeta(key);
            if (oldValue != null && Arrays.equals(oldValue, newValue)) {
                Log.v(TAG, "setCustomMeta: metadata not changed.");
                return true;
            }
            logManufacturerInfo(device, key, newValue);
            logMetadataChange(address, "setCustomMeta key=" + key);
            data.setCustomizedMeta(key, newValue);
            updateDatabase(data);
            this.mAdapterService.metadataChanged(address, key, newValue);
            return true;
        }
    }

    public byte[] getCustomMeta(BluetoothDevice device, int key) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "getCustomMeta: device is null");
                return null;
            }
            if (!isValidMetaKey(key)) {
                Log.e(TAG, "getCustomMeta: meta key invalid " + key);
                return null;
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                Log.d(TAG, "getCustomMeta: device " + address + " is not in cache");
                return null;
            }
            Metadata data = this.mMetadataCache.get(address);
            return data.getCustomizedMeta(key);
        }
    }

    public boolean setProfileConnectionPolicy(BluetoothDevice device, int profile, int newConnectionPolicy) {
        synchronized (this.mMetadataCache) {
            try {
                if (device == null) {
                    Log.e(TAG, "setProfileConnectionPolicy: device is null");
                    return false;
                }
                if (newConnectionPolicy != -1 && newConnectionPolicy != 0 && newConnectionPolicy != 100) {
                    Log.e(TAG, "setProfileConnectionPolicy: invalid connection policy " + newConnectionPolicy);
                    return false;
                }
                String address = device.getAddress();
                if (!this.mMetadataCache.containsKey(address)) {
                    if (newConnectionPolicy == -1) {
                        return true;
                    }
                    createMetadata(address, false);
                }
                Metadata data = this.mMetadataCache.get(address);
                int oldConnectionPolicy = data.getProfileConnectionPolicy(profile);
                if (oldConnectionPolicy == newConnectionPolicy) {
                    Log.v(TAG, "setProfileConnectionPolicy connection policy not changed.");
                    return true;
                }
                String profileStr = BluetoothProfile.getProfileName(profile);
                logMetadataChange(address, profileStr + " connection policy changed: : " + oldConnectionPolicy + " -> " + newConnectionPolicy);
                data.setProfileConnectionPolicy(profile, newConnectionPolicy);
                updateDatabase(data);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getProfileConnectionPolicy(BluetoothDevice device, int profile) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "getProfileConnectionPolicy: device is null");
                return -1;
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                Log.d(TAG, "getProfileConnectionPolicy: device " + address + " is not in cache");
                return -1;
            }
            Metadata data = this.mMetadataCache.get(address);
            int connectionPolicy = data.getProfileConnectionPolicy(profile);
            Log.v(TAG, "getProfileConnectionPolicy: " + address + ", profile=" + profile + ", connectionPolicy = " + connectionPolicy);
            return connectionPolicy;
        }
    }

    public void setA2dpSupportsOptionalCodecs(BluetoothDevice device, int newValue) {
        synchronized (this.mMetadataCache) {
            try {
                if (device == null) {
                    Log.e(TAG, "setA2dpOptionalCodec: device is null");
                    return;
                }
                if (newValue != -1 && newValue != 0 && newValue != 1) {
                    Log.e(TAG, "setA2dpSupportsOptionalCodecs: invalid value " + newValue);
                    return;
                }
                String address = device.getAddress();
                if (this.mMetadataCache.containsKey(address)) {
                    Metadata data = this.mMetadataCache.get(address);
                    int oldValue = data.a2dpSupportsOptionalCodecs;
                    if (oldValue == newValue) {
                        return;
                    }
                    logMetadataChange(address, "Supports optional codec changed: " + oldValue + " -> " + newValue);
                    data.a2dpSupportsOptionalCodecs = newValue;
                    updateDatabase(data);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getA2dpSupportsOptionalCodecs(BluetoothDevice device) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "setA2dpOptionalCodec: device is null");
                return -1;
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                Log.d(TAG, "getA2dpOptionalCodec: device " + address + " is not in cache");
                return -1;
            }
            Metadata data = this.mMetadataCache.get(address);
            return data.a2dpSupportsOptionalCodecs;
        }
    }

    public void setA2dpOptionalCodecsEnabled(BluetoothDevice device, int newValue) {
        synchronized (this.mMetadataCache) {
            try {
                if (device == null) {
                    Log.e(TAG, "setA2dpOptionalCodecEnabled: device is null");
                    return;
                }
                if (newValue != -1 && newValue != 0 && newValue != 1) {
                    Log.e(TAG, "setA2dpOptionalCodecsEnabled: invalid value " + newValue);
                    return;
                }
                String address = device.getAddress();
                if (this.mMetadataCache.containsKey(address)) {
                    Metadata data = this.mMetadataCache.get(address);
                    int oldValue = data.a2dpOptionalCodecsEnabled;
                    if (oldValue == newValue) {
                        return;
                    }
                    logMetadataChange(address, "Enable optional codec changed: " + oldValue + " -> " + newValue);
                    data.a2dpOptionalCodecsEnabled = newValue;
                    updateDatabase(data);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int getA2dpOptionalCodecsEnabled(BluetoothDevice device) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "getA2dpOptionalCodecEnabled: device is null");
                return -1;
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                Log.d(TAG, "getA2dpOptionalCodecEnabled: device " + address + " is not in cache");
                return -1;
            }
            Metadata data = this.mMetadataCache.get(address);
            return data.a2dpOptionalCodecsEnabled;
        }
    }

    public void setConnection(BluetoothDevice device, boolean isA2dpDevice) {
        synchronized (this.mMetadataCache) {
            Log.d(TAG, "setConnection: device=" + device + " and isA2dpDevice=" + isA2dpDevice);
            if (device == null) {
                Log.e(TAG, "setConnection: device is null");
                return;
            }
            if (isA2dpDevice) {
                resetActiveA2dpDevice();
            }
            String address = device.getAddress();
            if (!this.mMetadataCache.containsKey(address)) {
                Log.d(TAG, "setConnection: Creating new metadata entry for device: " + device.getAddressOnlyforLog());
                createMetadata(address, isA2dpDevice);
                return;
            }
            Metadata metadata = this.mMetadataCache.get(address);
            int i = MetadataDatabase.sCurrentConnectionNumber;
            MetadataDatabase.sCurrentConnectionNumber = i + 1;
            metadata.last_active_time = i;
            if (isA2dpDevice) {
                metadata.is_active_a2dp_device = true;
            }
            Log.d(TAG, "Updating last connected time for device: " + device.getAddressOnlyforLog() + " to " + metadata.last_active_time);
            updateDatabase(metadata);
        }
    }

    public void setDisconnection(BluetoothDevice device) {
        synchronized (this.mMetadataCache) {
            if (device == null) {
                Log.e(TAG, "setDisconnection: device is null");
                return;
            }
            String address = device.getAddress();
            if (this.mMetadataCache.containsKey(address)) {
                Metadata metadata = this.mMetadataCache.get(address);
                if (metadata.is_active_a2dp_device) {
                    metadata.is_active_a2dp_device = false;
                    Log.d(TAG, "setDisconnection: Updating is_active_device to false for device: " + device.getAddressOnlyforLog());
                    updateDatabase(metadata);
                }
            }
        }
    }

    private void resetActiveA2dpDevice() {
        synchronized (this.mMetadataCache) {
            Log.d(TAG, "resetActiveA2dpDevice()");
            for (Map.Entry<String, Metadata> entry : this.mMetadataCache.entrySet()) {
                Metadata metadata = entry.getValue();
                if (metadata.is_active_a2dp_device) {
                    Log.d(TAG, "resetActiveA2dpDevice");
                    metadata.is_active_a2dp_device = false;
                    updateDatabase(metadata);
                }
            }
        }
    }

    public List<BluetoothDevice> getMostRecentlyConnectedDevices() {
        List<BluetoothDevice> mostRecentlyConnectedDevices = new ArrayList<>();
        synchronized (this.mMetadataCache) {
            List<Metadata> sortedMetadata = new ArrayList<>(this.mMetadataCache.values());
            sortedMetadata.sort(new Comparator() { // from class: com.android.bluetooth.btservice.storage.-$$Lambda$DatabaseManager$r_7GExCVut1JQUvZHgpYBa0J7oc
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Long.compare(((Metadata) obj2).last_active_time, ((Metadata) obj).last_active_time);
                }
            });
            for (Metadata metadata : sortedMetadata) {
                try {
                    mostRecentlyConnectedDevices.add(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(metadata.getAddress()));
                } catch (IllegalArgumentException e) {
                    Log.d(TAG, "getBondedDevicesOrdered: Invalid address for device " + metadata.getAddress());
                }
            }
        }
        return mostRecentlyConnectedDevices;
    }

    public BluetoothDevice getMostRecentlyConnectedA2dpDevice() {
        synchronized (this.mMetadataCache) {
            Iterator<Map.Entry<String, Metadata>> it = this.mMetadataCache.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Metadata> entry = it.next();
                Metadata metadata = entry.getValue();
                if (metadata.is_active_a2dp_device) {
                    try {
                        return BluetoothAdapter.getDefaultAdapter().getRemoteDevice(metadata.getAddress());
                    } catch (IllegalArgumentException e) {
                        Log.d(TAG, "getMostRecentlyConnectedA2dpDevice: Invalid address for device " + metadata.getAddress());
                    }
                }
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compactLastConnectionTime(List<Metadata> metadataList) {
        Log.d(TAG, "compactLastConnectionTime: Compacting metadata after load");
        MetadataDatabase.sCurrentConnectionNumber = 0;
        for (int index = metadataList.size() - 1; index >= 0; index--) {
            Metadata metadata = metadataList.get(index);
            if (metadata.last_active_time != MetadataDatabase.sCurrentConnectionNumber) {
                Log.d(TAG, "compactLastConnectionTime: Setting last_active_item for device: " + metadata.getAddress() + " from " + metadata.last_active_time + " to " + MetadataDatabase.sCurrentConnectionNumber);
                metadata.last_active_time = (long) MetadataDatabase.sCurrentConnectionNumber;
                updateDatabase(metadata);
                MetadataDatabase.sCurrentConnectionNumber = MetadataDatabase.sCurrentConnectionNumber + 1;
            }
        }
    }

    public Looper getHandlerLooper() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread == null) {
            return null;
        }
        return handlerThread.getLooper();
    }

    public void start(MetadataDatabase database) {
        Log.d(TAG, "start()");
        if (this.mAdapterService == null) {
            Log.e(TAG, "stat failed, mAdapterService is null.");
            return;
        }
        if (database == null) {
            Log.e(TAG, "stat failed, database is null.");
            return;
        }
        this.mDatabase = database;
        HandlerThread handlerThread = new HandlerThread("BluetoothDatabaseManager");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new DatabaseHandler(this.mHandlerThread.getLooper());
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mAdapterService.registerReceiver(this.mReceiver, filter);
        loadDatabase();
    }

    String getDatabaseAbsolutePath() {
        return this.mAdapterService.getDatabasePath(MetadataDatabase.DATABASE_NAME).getAbsolutePath();
    }

    public void factoryReset() {
        Log.w(TAG, "factoryReset");
        Message message = this.mHandler.obtainMessage(100);
        this.mHandler.sendMessage(message);
    }

    public void cleanup() {
        removeUnusedMetadata();
        this.mAdapterService.unregisterReceiver(this.mReceiver);
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        this.mMetadataCache.clear();
    }

    void createMetadata(String address, boolean isActiveA2dpDevice) {
        Metadata data = new Metadata(address);
        data.is_active_a2dp_device = isActiveA2dpDevice;
        this.mMetadataCache.put(address, data);
        updateDatabase(data);
        logMetadataChange(address, "Metadata created");
    }

    void removeUnusedMetadata() {
        final BluetoothDevice[] bondedDevices = this.mAdapterService.getBondedDevices();
        synchronized (this.mMetadataCache) {
            this.mMetadataCache.forEach(new BiConsumer() { // from class: com.android.bluetooth.btservice.storage.-$$Lambda$DatabaseManager$UpK2wxpBJBHcTVRdyEOLDS1f2-g
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.f$0.lambda$removeUnusedMetadata$2$DatabaseManager(bondedDevices, (String) obj, (Metadata) obj2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$removeUnusedMetadata$2$DatabaseManager(BluetoothDevice[] bondedDevices, final String address, Metadata metadata) {
        if (!address.equals(LOCAL_STORAGE) && !Arrays.asList(bondedDevices).stream().anyMatch(new Predicate() { // from class: com.android.bluetooth.btservice.storage.-$$Lambda$DatabaseManager$8ZM9KhytIScv7B0DkWUR4N7efHg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return address.equals(((BluetoothDevice) obj).getAddress());
            }
        })) {
            List<Integer> list = metadata.getChangedCustomizedMeta();
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int key = it.next().intValue();
                this.mAdapterService.metadataChanged(address, key, null);
            }
            Log.i(TAG, "remove unpaired device from database " + address);
            deleteDatabase(this.mMetadataCache.get(address));
        }
    }

    void cacheMetadata(List<Metadata> list) {
        synchronized (this.mMetadataCache) {
            Log.i(TAG, "cacheMetadata");
            this.mSemaphore.release();
            if (!isMigrated(list)) {
                this.mMigratedFromSettingsGlobal = false;
                return;
            }
            this.mMigratedFromSettingsGlobal = true;
            for (Metadata data : list) {
                String address = data.getAddress();
                Log.v(TAG, "cacheMetadata: found device " + address);
                this.mMetadataCache.put(address, data);
            }
            Log.i(TAG, "cacheMetadata: Database is ready");
        }
    }

    boolean isMigrated(List<Metadata> list) {
        for (Metadata data : list) {
            String address = data.getAddress();
            if (address.equals(LOCAL_STORAGE) && data.migrated) {
                return true;
            }
        }
        return false;
    }

    void migrateSettingsGlobal() {
        DatabaseManager databaseManager = this;
        Log.i(TAG, "migrateSettingGlobal");
        BluetoothDevice[] bondedDevices = databaseManager.mAdapterService.getBondedDevices();
        ContentResolver contentResolver = databaseManager.mAdapterService.getContentResolver();
        int length = bondedDevices.length;
        int sapConnectionPolicy = 0;
        while (sapConnectionPolicy < length) {
            BluetoothDevice device = bondedDevices[sapConnectionPolicy];
            int a2dpConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyA2dpSinkPriorityKey(device.getAddress()), -1);
            int a2dpSinkConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyA2dpSrcPriorityKey(device.getAddress()), -1);
            int hearingaidConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyHearingAidPriorityKey(device.getAddress()), -1);
            int headsetConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyHeadsetPriorityKey(device.getAddress()), -1);
            int headsetClientConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyHeadsetPriorityKey(device.getAddress()), -1);
            int hidHostConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyHidHostPriorityKey(device.getAddress()), -1);
            int mapConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyMapPriorityKey(device.getAddress()), -1);
            int mapClientConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyMapClientPriorityKey(device.getAddress()), -1);
            int panConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyPanPriorityKey(device.getAddress()), -1);
            BluetoothDevice[] bondedDevices2 = bondedDevices;
            int pbapConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyPbapClientPriorityKey(device.getAddress()), -1);
            int i = length;
            int pbapClientConnectionPolicy = Settings.Global.getInt(contentResolver, getLegacyPbapClientPriorityKey(device.getAddress()), -1);
            int i2 = sapConnectionPolicy;
            int sapConnectionPolicy2 = Settings.Global.getInt(contentResolver, getLegacySapPriorityKey(device.getAddress()), -1);
            int a2dpSupportsOptionalCodec = Settings.Global.getInt(contentResolver, getLegacyA2dpSupportsOptionalCodecsKey(device.getAddress()), -1);
            int a2dpOptionalCodecEnabled = Settings.Global.getInt(contentResolver, getLegacyA2dpOptionalCodecsEnabledKey(device.getAddress()), -1);
            String address = device.getAddress();
            ContentResolver contentResolver2 = contentResolver;
            Metadata data = new Metadata(address);
            data.setProfileConnectionPolicy(2, a2dpConnectionPolicy);
            data.setProfileConnectionPolicy(11, a2dpSinkConnectionPolicy);
            data.setProfileConnectionPolicy(1, headsetConnectionPolicy);
            data.setProfileConnectionPolicy(16, headsetClientConnectionPolicy);
            data.setProfileConnectionPolicy(4, hidHostConnectionPolicy);
            data.setProfileConnectionPolicy(5, panConnectionPolicy);
            data.setProfileConnectionPolicy(6, pbapConnectionPolicy);
            data.setProfileConnectionPolicy(17, pbapClientConnectionPolicy);
            data.setProfileConnectionPolicy(9, mapConnectionPolicy);
            data.setProfileConnectionPolicy(18, mapClientConnectionPolicy);
            data.setProfileConnectionPolicy(10, sapConnectionPolicy2);
            data.setProfileConnectionPolicy(21, hearingaidConnectionPolicy);
            data.a2dpSupportsOptionalCodecs = a2dpSupportsOptionalCodec;
            data.a2dpOptionalCodecsEnabled = a2dpOptionalCodecEnabled;
            databaseManager = this;
            databaseManager.mMetadataCache.put(address, data);
            databaseManager.updateDatabase(data);
            sapConnectionPolicy = i2 + 1;
            contentResolver = contentResolver2;
            bondedDevices = bondedDevices2;
            length = i;
        }
        Metadata localData = new Metadata(LOCAL_STORAGE);
        localData.migrated = true;
        databaseManager.mMetadataCache.put(LOCAL_STORAGE, localData);
        databaseManager.updateDatabase(localData);
        loadDatabase();
    }

    private static String getLegacyHeadsetPriorityKey(String address) {
        return LEGACY_HEADSET_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyA2dpSinkPriorityKey(String address) {
        return LEGACY_A2DP_SINK_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyA2dpSrcPriorityKey(String address) {
        return LEGACY_A2DP_SRC_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyA2dpSupportsOptionalCodecsKey(String address) {
        return LEGACY_A2DP_SUPPORTS_OPTIONAL_CODECS_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyA2dpOptionalCodecsEnabledKey(String address) {
        return LEGACY_A2DP_OPTIONAL_CODECS_ENABLED_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyHidHostPriorityKey(String address) {
        return LEGACY_INPUT_DEVICE_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyPanPriorityKey(String address) {
        return LEGACY_PAN_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyHearingAidPriorityKey(String address) {
        return LEGACY_HEARING_AID_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyMapPriorityKey(String address) {
        return LEGACY_MAP_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyMapClientPriorityKey(String address) {
        return LEGACY_MAP_CLIENT_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacyPbapClientPriorityKey(String address) {
        return LEGACY_PBAP_CLIENT_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private static String getLegacySapPriorityKey(String address) {
        return LEGACY_SAP_PRIORITY_PREFIX + address.toUpperCase(Locale.ROOT);
    }

    private void loadDatabase() {
        Log.d(TAG, "Load Database");
        Message message = this.mHandler.obtainMessage(0);
        this.mHandler.sendMessage(message);
        try {
            this.mSemaphore.tryAcquire(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.e(TAG, "loadDatabase: semaphore acquire failed");
        }
    }

    private void updateDatabase(Metadata data) {
        if (data.getAddress() == null) {
            Log.e(TAG, "updateDatabase: address is null");
            return;
        }
        Log.d(TAG, "updateDatabase " + data.getAddress());
        Message message = this.mHandler.obtainMessage(1);
        message.obj = data;
        this.mHandler.sendMessage(message);
    }

    void deleteDatabase(Metadata data) {
        String address = data.getAddress();
        if (address == null) {
            Log.e(TAG, "deleteDatabase: address is null");
            return;
        }
        logMetadataChange(address, "Metadata deleted");
        Message message = this.mHandler.obtainMessage(2);
        message.obj = data.getAddress();
        this.mHandler.sendMessage(message);
    }

    private void logManufacturerInfo(BluetoothDevice device, int key, byte[] bytesValue) {
        String modelName;
        String hardwareVersion;
        String softwareVersion;
        String callingApp = this.mAdapterService.getPackageManager().getNameForUid(Binder.getCallingUid());
        String manufacturerName = INetd.NEXTHOP_NONE;
        String value = Utils.byteArrayToUtf8String(bytesValue);
        if (key == 0) {
            manufacturerName = value;
            modelName = INetd.NEXTHOP_NONE;
            hardwareVersion = INetd.NEXTHOP_NONE;
            softwareVersion = INetd.NEXTHOP_NONE;
        } else if (key == 1) {
            modelName = value;
            hardwareVersion = INetd.NEXTHOP_NONE;
            softwareVersion = INetd.NEXTHOP_NONE;
        } else if (key == 2) {
            modelName = INetd.NEXTHOP_NONE;
            hardwareVersion = INetd.NEXTHOP_NONE;
            softwareVersion = value;
        } else if (key == 3) {
            modelName = INetd.NEXTHOP_NONE;
            hardwareVersion = value;
            softwareVersion = INetd.NEXTHOP_NONE;
        } else {
            return;
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_DEVICE_INFO_REPORTED, this.mAdapterService.obfuscateAddress(device), 2, callingApp, manufacturerName, modelName, hardwareVersion, softwareVersion, this.mAdapterService.getMetricId(device));
    }

    private void logMetadataChange(String address, String log) {
        String time = Utils.getLocalTimeString();
        String uidPid = Utils.getUidPidString();
        this.mMetadataChangedLog.add(time + " (" + uidPid + ") " + address + " " + log);
    }

    public void dump(PrintWriter writer) {
        writer.println("\nBluetoothDatabase:");
        writer.println("  Metadata Changes:");
        for (String log : this.mMetadataChangedLog) {
            writer.println("    " + log);
        }
        writer.println("\nMetadata:");
        for (Map.Entry<String, Metadata> entry : this.mMetadataCache.entrySet()) {
            if (!entry.getKey().equals(LOCAL_STORAGE)) {
                writer.println("    " + entry.getValue());
            }
        }
    }
}
