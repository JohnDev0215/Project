package com.android.bluetooth.gatt;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class HandleMap {
    private static final boolean DBG = false;
    private static final String TAG = "BtGatt.HandleMap";
    public static final int TYPE_CHARACTERISTIC = 2;
    public static final int TYPE_DESCRIPTOR = 3;
    public static final int TYPE_SERVICE = 1;
    public static final int TYPE_UNDEFINED = 0;
    List<Entry> mEntries;
    int mLastCharacteristic = 0;
    Map<Integer, Integer> mRequestMap;

    class Entry {
        public boolean advertisePreferred;
        public int charHandle;
        public int handle;
        public int instance;
        public int serverIf;
        public int serviceHandle;
        public int serviceType;
        public boolean started;
        public int type;
        public UUID uuid;

        Entry(int serverIf, int handle, UUID uuid, int serviceType, int instance) {
            this.serverIf = 0;
            this.type = 0;
            this.handle = 0;
            this.uuid = null;
            this.instance = 0;
            this.serviceType = 0;
            this.serviceHandle = 0;
            this.charHandle = 0;
            this.started = false;
            this.advertisePreferred = false;
            this.serverIf = serverIf;
            this.type = 1;
            this.handle = handle;
            this.uuid = uuid;
            this.instance = instance;
            this.serviceType = serviceType;
        }

        Entry(int serverIf, int handle, UUID uuid, int serviceType, int instance, boolean advertisePreferred) {
            this.serverIf = 0;
            this.type = 0;
            this.handle = 0;
            this.uuid = null;
            this.instance = 0;
            this.serviceType = 0;
            this.serviceHandle = 0;
            this.charHandle = 0;
            this.started = false;
            this.advertisePreferred = false;
            this.serverIf = serverIf;
            this.type = 1;
            this.handle = handle;
            this.uuid = uuid;
            this.instance = instance;
            this.serviceType = serviceType;
            this.advertisePreferred = advertisePreferred;
        }

        Entry(int serverIf, int type, int handle, UUID uuid, int serviceHandle) {
            this.serverIf = 0;
            this.type = 0;
            this.handle = 0;
            this.uuid = null;
            this.instance = 0;
            this.serviceType = 0;
            this.serviceHandle = 0;
            this.charHandle = 0;
            this.started = false;
            this.advertisePreferred = false;
            this.serverIf = serverIf;
            this.type = type;
            this.handle = handle;
            this.uuid = uuid;
            this.serviceHandle = serviceHandle;
        }

        Entry(int serverIf, int type, int handle, UUID uuid, int serviceHandle, int charHandle) {
            this.serverIf = 0;
            this.type = 0;
            this.handle = 0;
            this.uuid = null;
            this.instance = 0;
            this.serviceType = 0;
            this.serviceHandle = 0;
            this.charHandle = 0;
            this.started = false;
            this.advertisePreferred = false;
            this.serverIf = serverIf;
            this.type = type;
            this.handle = handle;
            this.uuid = uuid;
            this.serviceHandle = serviceHandle;
            this.charHandle = charHandle;
        }
    }

    HandleMap() {
        this.mEntries = null;
        this.mRequestMap = null;
        this.mEntries = new ArrayList();
        this.mRequestMap = new HashMap();
    }

    void clear() {
        this.mEntries.clear();
        this.mRequestMap.clear();
    }

    void addService(int serverIf, int handle, UUID uuid, int serviceType, int instance, boolean advertisePreferred) {
        this.mEntries.add(new Entry(serverIf, handle, uuid, serviceType, instance, advertisePreferred));
    }

    void addCharacteristic(int serverIf, int handle, UUID uuid, int serviceHandle) {
        this.mLastCharacteristic = handle;
        this.mEntries.add(new Entry(serverIf, 2, handle, uuid, serviceHandle));
    }

    void addDescriptor(int serverIf, int handle, UUID uuid, int serviceHandle) {
        this.mEntries.add(new Entry(serverIf, 3, handle, uuid, serviceHandle, this.mLastCharacteristic));
    }

    void setStarted(int serverIf, int handle, boolean started) {
        for (Entry entry : this.mEntries) {
            if (entry.type == 1 && entry.serverIf == serverIf && entry.handle == handle) {
                entry.started = started;
                return;
            }
        }
    }

    Entry getByHandle(int handle) {
        for (Entry entry : this.mEntries) {
            if (entry.handle == handle) {
                return entry;
            }
        }
        Log.e(TAG, "getByHandle() - Handle " + handle + " not found!");
        return null;
    }

    boolean checkServiceExists(UUID uuid, int handle) {
        for (Entry entry : this.mEntries) {
            if (entry.type == 1 && entry.handle == handle && entry.uuid.equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    void deleteService(int serverIf, int serviceHandle) {
        Iterator<Entry> it = this.mEntries.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            if (entry.serverIf == serverIf && (entry.handle == serviceHandle || entry.serviceHandle == serviceHandle)) {
                it.remove();
            }
        }
    }

    List<Entry> getEntries() {
        return this.mEntries;
    }

    void addRequest(int requestId, int handle) {
        this.mRequestMap.put(Integer.valueOf(requestId), Integer.valueOf(handle));
    }

    void deleteRequest(int requestId) {
        this.mRequestMap.remove(Integer.valueOf(requestId));
    }

    Entry getByRequestId(int requestId) {
        Integer handle = this.mRequestMap.get(Integer.valueOf(requestId));
        if (handle == null) {
            Log.e(TAG, "getByRequestId() - Request ID " + requestId + " not found!");
            return null;
        }
        return getByHandle(handle.intValue());
    }

    void dump(StringBuilder sb) {
        sb.append("  Entries: " + this.mEntries.size() + "\n");
        sb.append("  Requests: " + this.mRequestMap.size() + "\n");
        for (Entry entry : this.mEntries) {
            sb.append("  " + entry.serverIf + ": [" + entry.handle + "] ");
            int i = entry.type;
            if (i == 1) {
                sb.append("Service " + entry.uuid);
                sb.append(", started " + entry.started);
            } else if (i == 2) {
                sb.append("  Characteristic " + entry.uuid);
            } else if (i == 3) {
                sb.append("    Descriptor " + entry.uuid);
            }
            sb.append("\n");
        }
    }
}
