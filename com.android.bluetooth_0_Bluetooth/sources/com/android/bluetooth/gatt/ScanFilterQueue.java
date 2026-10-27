package com.android.bluetooth.gatt;

import android.bluetooth.BluetoothUuid;
import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
class ScanFilterQueue {
    private static final byte DEVICE_TYPE_ALL = 2;
    private static final int MAX_LEN_PER_FIELD = 26;
    public static final int TYPE_DEVICE_ADDRESS = 0;
    public static final int TYPE_LOCAL_NAME = 4;
    public static final int TYPE_MANUFACTURER_DATA = 5;
    public static final int TYPE_SERVICE_DATA = 6;
    public static final int TYPE_SERVICE_DATA_CHANGED = 1;
    public static final int TYPE_SERVICE_UUID = 2;
    public static final int TYPE_SOLICIT_UUID = 3;
    private Set<Entry> mEntries = new HashSet();

    ScanFilterQueue() {
    }

    class Entry {
        public byte addr_type;
        public String address;
        public int company;
        public int company_mask;
        public byte[] data;
        public byte[] data_mask;
        public String name;
        public byte type;
        public UUID uuid;
        public UUID uuid_mask;

        Entry() {
        }
    }

    void addDeviceAddress(String address, byte type) {
        Entry entry = new Entry();
        entry.type = (byte) 0;
        entry.address = address;
        entry.addr_type = type;
        this.mEntries.add(entry);
    }

    void addServiceChanged() {
        Entry entry = new Entry();
        entry.type = (byte) 1;
        this.mEntries.add(entry);
    }

    void addUuid(UUID uuid) {
        Entry entry = new Entry();
        entry.type = (byte) 2;
        entry.uuid = uuid;
        entry.uuid_mask = new UUID(0L, 0L);
        this.mEntries.add(entry);
    }

    void addUuid(UUID uuid, UUID uuidMask) {
        Entry entry = new Entry();
        entry.type = (byte) 2;
        entry.uuid = uuid;
        entry.uuid_mask = uuidMask;
        this.mEntries.add(entry);
    }

    void addSolicitUuid(UUID uuid) {
        Entry entry = new Entry();
        entry.type = (byte) 3;
        entry.uuid = uuid;
        entry.uuid_mask = new UUID(0L, 0L);
        this.mEntries.add(entry);
    }

    void addSolicitUuid(UUID uuid, UUID uuidMask) {
        Entry entry = new Entry();
        entry.type = (byte) 3;
        entry.uuid = uuid;
        entry.uuid_mask = uuidMask;
        this.mEntries.add(entry);
    }

    void addName(String name) {
        Entry entry = new Entry();
        entry.type = (byte) 4;
        entry.name = name;
        this.mEntries.add(entry);
    }

    void addManufacturerData(int company, byte[] data) {
        Entry entry = new Entry();
        entry.type = (byte) 5;
        entry.company = company;
        entry.company_mask = 65535;
        entry.data = data;
        entry.data_mask = new byte[data.length];
        Arrays.fill(entry.data_mask, (byte) -1);
        this.mEntries.add(entry);
    }

    void addManufacturerData(int company, int companyMask, byte[] data, byte[] dataMask) {
        Entry entry = new Entry();
        entry.type = (byte) 5;
        entry.company = company;
        entry.company_mask = companyMask;
        entry.data = data;
        entry.data_mask = dataMask;
        this.mEntries.add(entry);
    }

    void addServiceData(byte[] data, byte[] dataMask) {
        Entry entry = new Entry();
        entry.type = (byte) 6;
        entry.data = data;
        entry.data_mask = dataMask;
        this.mEntries.add(entry);
    }

    Entry pop() {
        if (this.mEntries.isEmpty()) {
            return null;
        }
        Iterator<Entry> iterator = this.mEntries.iterator();
        Entry entry = iterator.next();
        iterator.remove();
        return entry;
    }

    int getFeatureSelection() {
        int selc = 0;
        for (Entry entry : this.mEntries) {
            selc |= 1 << entry.type;
        }
        return selc;
    }

    Entry[] toArray() {
        Set<Entry> set = this.mEntries;
        return (Entry[]) set.toArray(new Entry[set.size()]);
    }

    void addScanFilter(ScanFilter filter) {
        if (filter == null) {
            return;
        }
        if (filter.getDeviceName() != null) {
            addName(filter.getDeviceName());
        }
        if (filter.getDeviceAddress() != null) {
            addDeviceAddress(filter.getDeviceAddress(), (byte) 2);
        }
        if (filter.getServiceUuid() != null) {
            if (filter.getServiceUuidMask() == null) {
                addUuid(filter.getServiceUuid().getUuid());
            } else {
                addUuid(filter.getServiceUuid().getUuid(), filter.getServiceUuidMask().getUuid());
            }
        }
        if (filter.getServiceSolicitationUuid() != null) {
            if (filter.getServiceSolicitationUuidMask() == null) {
                addSolicitUuid(filter.getServiceSolicitationUuid().getUuid());
            } else {
                addSolicitUuid(filter.getServiceSolicitationUuid().getUuid(), filter.getServiceSolicitationUuidMask().getUuid());
            }
        }
        if (filter.getManufacturerData() != null) {
            if (filter.getManufacturerDataMask() == null) {
                addManufacturerData(filter.getManufacturerId(), filter.getManufacturerData());
            } else {
                addManufacturerData(filter.getManufacturerId(), 65535, filter.getManufacturerData(), filter.getManufacturerDataMask());
            }
        }
        if (filter.getServiceDataUuid() != null && filter.getServiceData() != null) {
            ParcelUuid serviceDataUuid = filter.getServiceDataUuid();
            byte[] serviceData = filter.getServiceData();
            byte[] serviceDataMask = filter.getServiceDataMask();
            if (serviceDataMask == null) {
                serviceDataMask = new byte[serviceData.length];
                Arrays.fill(serviceDataMask, (byte) -1);
            }
            byte[] serviceData2 = concate(serviceDataUuid, serviceData);
            byte[] serviceDataMask2 = concate(serviceDataUuid, serviceDataMask);
            if (serviceData2 != null && serviceDataMask2 != null) {
                addServiceData(serviceData2, serviceDataMask2);
            }
        }
    }

    private byte[] concate(ParcelUuid serviceDataUuid, byte[] serviceData) {
        byte[] uuid = BluetoothUuid.uuidToBytes(serviceDataUuid);
        int dataLen = uuid.length + (serviceData == null ? 0 : serviceData.length);
        if (dataLen > 26) {
            return null;
        }
        byte[] concated = new byte[dataLen];
        System.arraycopy(uuid, 0, concated, 0, uuid.length);
        if (serviceData != null) {
            System.arraycopy(serviceData, 0, concated, uuid.length, serviceData.length);
        }
        return concated;
    }
}
