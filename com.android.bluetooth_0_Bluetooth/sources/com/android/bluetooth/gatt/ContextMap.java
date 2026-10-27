package com.android.bluetooth.gatt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.WorkSource;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class ContextMap<C, T> {
    private static final String TAG = "BtGatt.ContextMap";
    private List<ContextMap<C, T>.App> mApps = new ArrayList();
    HashMap<Integer, AppScanStats> mAppScanStats = new HashMap<>();
    Set<ContextMap<C, T>.Connection> mConnections = new HashSet();

    ContextMap() {
    }

    class Connection {
        public String address;
        public int appId;
        public int connId;
        public long startTime = SystemClock.elapsedRealtime();

        Connection(int connId, String address, int appId) {
            this.connId = connId;
            this.address = address;
            this.appId = appId;
        }
    }

    class App {
        public AppScanStats appScanStats;
        public C callback;
        boolean hasBluetoothPrivilegedPermission;
        boolean hasLocationPermission;
        public int id;
        public T info;
        public List<String> mAssociatedDevices;
        private IBinder.DeathRecipient mDeathRecipient;
        boolean mEligibleForSanitizedExposureNotification;
        boolean mHasNetworkSettingsPermission;
        boolean mHasNetworkSetupWizardPermission;
        boolean mHasScanWithoutLocationPermission;
        boolean mIsQApp;
        UserHandle mUserHandle;
        public String name;
        public UUID uuid;
        public Boolean isCongested = false;
        private List<CallbackInfo> mCongestionQueue = new ArrayList();

        App(UUID uuid, C callback, T info, String name, AppScanStats appScanStats) {
            this.uuid = uuid;
            this.callback = callback;
            this.info = info;
            this.name = name;
            this.appScanStats = appScanStats;
        }

        void linkToDeath(IBinder.DeathRecipient deathRecipient) {
            C c = this.callback;
            if (c == null) {
                return;
            }
            try {
                IBinder binder = ((IInterface) c).asBinder();
                binder.linkToDeath(deathRecipient, 0);
                this.mDeathRecipient = deathRecipient;
            } catch (RemoteException e) {
                Log.e(ContextMap.TAG, "Unable to link deathRecipient for app id " + this.id);
            }
        }

        void unlinkToDeath() {
            if (this.mDeathRecipient != null) {
                try {
                    IBinder binder = ((IInterface) this.callback).asBinder();
                    binder.unlinkToDeath(this.mDeathRecipient, 0);
                } catch (NoSuchElementException e) {
                    Log.e(ContextMap.TAG, "Unable to unlink deathRecipient for app id " + this.id);
                }
            }
        }

        void queueCallback(CallbackInfo callbackInfo) {
            this.mCongestionQueue.add(callbackInfo);
        }

        CallbackInfo popQueuedCallback() {
            if (this.mCongestionQueue.size() == 0) {
                return null;
            }
            return this.mCongestionQueue.remove(0);
        }
    }

    ContextMap<C, T>.App add(UUID uuid, WorkSource workSource, C callback, T info, GattService service) throws Throwable {
        String appName;
        int appUid = Binder.getCallingUid();
        String appName2 = service.getPackageManager().getNameForUid(appUid);
        if (appName2 != null) {
            appName = appName2;
        } else {
            appName = "Unknown App (UID: " + appUid + ")";
        }
        synchronized (this.mApps) {
            try {
                try {
                    AppScanStats appScanStats = this.mAppScanStats.get(Integer.valueOf(appUid));
                    if (appScanStats == null) {
                        appScanStats = new AppScanStats(appName, workSource, this, service);
                        this.mAppScanStats.put(Integer.valueOf(appUid), appScanStats);
                    }
                    ContextMap<C, T>.App app = new App(uuid, callback, info, appName, appScanStats);
                    this.mApps.add(app);
                    appScanStats.isRegistered = true;
                    return app;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    void remove(UUID uuid) {
        synchronized (this.mApps) {
            Iterator<ContextMap<C, T>.App> i = this.mApps.iterator();
            while (true) {
                if (!i.hasNext()) {
                    break;
                }
                ContextMap<C, T>.App entry = i.next();
                if (entry.uuid.equals(uuid)) {
                    entry.unlinkToDeath();
                    entry.appScanStats.isRegistered = false;
                    i.remove();
                    break;
                }
            }
        }
    }

    void remove(int id) {
        synchronized (this.mApps) {
            Iterator<ContextMap<C, T>.App> i = this.mApps.iterator();
            while (true) {
                if (!i.hasNext()) {
                    break;
                }
                ContextMap<C, T>.App entry = i.next();
                if (entry.id == id) {
                    removeConnectionsByAppId(id);
                    entry.unlinkToDeath();
                    entry.appScanStats.isRegistered = false;
                    i.remove();
                    break;
                }
            }
        }
    }

    List<Integer> getAllAppsIds() {
        List<Integer> appIds = new ArrayList<>();
        synchronized (this.mApps) {
            for (ContextMap<C, T>.App entry : this.mApps) {
                appIds.add(Integer.valueOf(entry.id));
            }
        }
        return appIds;
    }

    void addConnection(int id, int connId, String address) {
        synchronized (this.mConnections) {
            ContextMap<C, T>.App entry = getById(id);
            if (entry != null) {
                this.mConnections.add(new Connection(connId, address, id));
            }
        }
    }

    void removeConnection(int id, int connId) {
        synchronized (this.mConnections) {
            Iterator<ContextMap<C, T>.Connection> i = this.mConnections.iterator();
            while (true) {
                if (!i.hasNext()) {
                    break;
                }
                ContextMap<C, T>.Connection connection = i.next();
                if (connection.connId == connId) {
                    i.remove();
                    break;
                }
            }
        }
    }

    void removeConnectionsByAppId(int appId) {
        Iterator<ContextMap<C, T>.Connection> i = this.mConnections.iterator();
        while (i.hasNext()) {
            ContextMap<C, T>.Connection connection = i.next();
            if (connection.appId == appId) {
                i.remove();
            }
        }
    }

    ContextMap<C, T>.App getById(int id) {
        synchronized (this.mApps) {
            for (ContextMap<C, T>.App entry : this.mApps) {
                if (entry.id == id) {
                    return entry;
                }
            }
            Log.e(TAG, "Context not found for ID " + id);
            return null;
        }
    }

    ContextMap<C, T>.App getByUuid(UUID uuid) {
        synchronized (this.mApps) {
            for (ContextMap<C, T>.App entry : this.mApps) {
                if (entry.uuid.equals(uuid)) {
                    return entry;
                }
            }
            Log.e(TAG, "Context not found for UUID " + uuid);
            return null;
        }
    }

    ContextMap<C, T>.App getByName(String name) {
        synchronized (this.mApps) {
            for (ContextMap<C, T>.App entry : this.mApps) {
                if (entry.name.equals(name)) {
                    return entry;
                }
            }
            Log.e(TAG, "Context not found for name " + name);
            return null;
        }
    }

    ContextMap<C, T>.App getByContextInfo(T contextInfo) {
        synchronized (this.mApps) {
            for (ContextMap<C, T>.App entry : this.mApps) {
                if (entry.info != null && entry.info.equals(contextInfo)) {
                    return entry;
                }
            }
            Log.e(TAG, "Context not found for info " + contextInfo);
            return null;
        }
    }

    AppScanStats getAppScanStatsById(int id) {
        ContextMap<C, T>.App temp = getById(id);
        if (temp != null) {
            return temp.appScanStats;
        }
        return null;
    }

    AppScanStats getAppScanStatsByUid(int uid) {
        return this.mAppScanStats.get(Integer.valueOf(uid));
    }

    Set<String> getConnectedDevices() {
        Set<String> addresses = new HashSet<>();
        for (ContextMap<C, T>.Connection connection : this.mConnections) {
            addresses.add(connection.address);
        }
        return addresses;
    }

    ContextMap<C, T>.App getByConnId(int connId) {
        for (ContextMap<C, T>.Connection connection : this.mConnections) {
            if (connection.connId == connId) {
                return getById(connection.appId);
            }
        }
        return null;
    }

    Integer connIdByAddress(int id, String address) {
        ContextMap<C, T>.App entry = getById(id);
        if (entry == null) {
            return null;
        }
        for (ContextMap<C, T>.Connection connection : this.mConnections) {
            if (connection.address.equalsIgnoreCase(address) && connection.appId == id) {
                return Integer.valueOf(connection.connId);
            }
        }
        return null;
    }

    String addressByConnId(int connId) {
        for (ContextMap<C, T>.Connection connection : this.mConnections) {
            if (connection.connId == connId) {
                return connection.address;
            }
        }
        return null;
    }

    List<ContextMap<C, T>.Connection> getConnectionByApp(int appId) {
        List<ContextMap<C, T>.Connection> currentConnections = new ArrayList<>();
        for (ContextMap<C, T>.Connection connection : this.mConnections) {
            if (connection.appId == appId) {
                currentConnections.add(connection);
            }
        }
        return currentConnections;
    }

    void clear() {
        synchronized (this.mApps) {
            Iterator<ContextMap<C, T>.App> i = this.mApps.iterator();
            while (i.hasNext()) {
                ContextMap<C, T>.App entry = i.next();
                entry.unlinkToDeath();
                entry.appScanStats.isRegistered = false;
                i.remove();
            }
        }
        synchronized (this.mConnections) {
            this.mConnections.clear();
        }
    }

    Map<Integer, String> getConnectedMap() {
        Map<Integer, String> connectedmap = new HashMap<>();
        for (ContextMap<C, T>.Connection conn : this.mConnections) {
            connectedmap.put(Integer.valueOf(conn.appId), conn.address);
        }
        return connectedmap;
    }

    void dump(StringBuilder sb) {
        sb.append("  Entries: " + this.mAppScanStats.size() + "\n\n");
        for (Map.Entry<Integer, AppScanStats> entry : this.mAppScanStats.entrySet()) {
            AppScanStats appScanStats = entry.getValue();
            appScanStats.dumpToString(sb);
        }
    }
}
