package com.android.bluetooth.gatt;

import android.bluetooth.BluetoothUuid;
import android.bluetooth.le.AdvertiseData;
import android.os.ParcelUuid;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class AdvertiseHelper {
    private static final int COMPLETE_LIST_128_BIT_SERVICE_UUIDS = 7;
    private static final int COMPLETE_LIST_16_BIT_SERVICE_UUIDS = 3;
    private static final int COMPLETE_LIST_32_BIT_SERVICE_UUIDS = 5;
    private static final int COMPLETE_LOCAL_NAME = 9;
    private static final int DEVICE_NAME_MAX = 26;
    private static final int MANUFACTURER_SPECIFIC_DATA = 255;
    private static final int SERVICE_DATA_128_BIT_UUID = 33;
    private static final int SERVICE_DATA_16_BIT_UUID = 22;
    private static final int SERVICE_DATA_32_BIT_UUID = 32;
    private static final int SHORTENED_LOCAL_NAME = 8;
    private static final String TAG = "AdvertiseHelper";
    private static final int TX_POWER_LEVEL = 10;

    AdvertiseHelper() {
    }

    public static byte[] advertiseDataToBytes(AdvertiseData data, String name) {
        byte type;
        if (data == null) {
            return new byte[0];
        }
        ByteArrayOutputStream ret = new ByteArrayOutputStream();
        if (data.getIncludeDeviceName()) {
            try {
                byte[] nameBytes = name.getBytes("UTF-8");
                int nameLength = nameBytes.length;
                if (nameLength > 26) {
                    nameLength = 26;
                    type = 8;
                } else {
                    type = 9;
                }
                ret.write(nameLength + 1);
                ret.write(type);
                ret.write(nameBytes, 0, nameLength);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Can't include name - encoding error!", e);
            }
        }
        for (int i = 0; i < data.getManufacturerSpecificData().size(); i++) {
            int manufacturerId = data.getManufacturerSpecificData().keyAt(i);
            byte[] manufacturerData = data.getManufacturerSpecificData().get(manufacturerId);
            int dataLen = (manufacturerData == null ? 0 : manufacturerData.length) + 2;
            byte[] concated = new byte[dataLen];
            concated[0] = (byte) (manufacturerId & 255);
            concated[1] = (byte) ((manufacturerId >> 8) & 255);
            if (manufacturerData != null) {
                System.arraycopy(manufacturerData, 0, concated, 2, manufacturerData.length);
            }
            ret.write(concated.length + 1);
            ret.write(255);
            ret.write(concated, 0, concated.length);
        }
        if (data.getIncludeTxPowerLevel()) {
            ret.write(2);
            ret.write(10);
            ret.write(0);
        }
        if (data.getServiceUuids() != null) {
            ByteArrayOutputStream serviceUuids16 = new ByteArrayOutputStream();
            ByteArrayOutputStream serviceUuids32 = new ByteArrayOutputStream();
            ByteArrayOutputStream serviceUuids128 = new ByteArrayOutputStream();
            Iterator<ParcelUuid> it = data.getServiceUuids().iterator();
            while (it.hasNext()) {
                byte[] uuid = BluetoothUuid.uuidToBytes(it.next());
                if (uuid.length == 2) {
                    serviceUuids16.write(uuid, 0, uuid.length);
                } else if (uuid.length == 4) {
                    serviceUuids32.write(uuid, 0, uuid.length);
                } else {
                    serviceUuids128.write(uuid, 0, uuid.length);
                }
            }
            if (serviceUuids16.size() != 0) {
                ret.write(serviceUuids16.size() + 1);
                ret.write(3);
                ret.write(serviceUuids16.toByteArray(), 0, serviceUuids16.size());
            }
            if (serviceUuids32.size() != 0) {
                ret.write(serviceUuids32.size() + 1);
                ret.write(5);
                ret.write(serviceUuids32.toByteArray(), 0, serviceUuids32.size());
            }
            if (serviceUuids128.size() != 0) {
                ret.write(serviceUuids128.size() + 1);
                ret.write(7);
                ret.write(serviceUuids128.toByteArray(), 0, serviceUuids128.size());
            }
        }
        if (!data.getServiceData().isEmpty()) {
            for (ParcelUuid parcelUuid : data.getServiceData().keySet()) {
                byte[] serviceData = data.getServiceData().get(parcelUuid);
                byte[] uuid2 = BluetoothUuid.uuidToBytes(parcelUuid);
                int uuidLen = uuid2.length;
                int dataLen2 = (serviceData == null ? 0 : serviceData.length) + uuidLen;
                byte[] concated2 = new byte[dataLen2];
                System.arraycopy(uuid2, 0, concated2, 0, uuidLen);
                if (serviceData != null) {
                    System.arraycopy(serviceData, 0, concated2, uuidLen, serviceData.length);
                }
                if (uuid2.length == 2) {
                    ret.write(concated2.length + 1);
                    ret.write(22);
                    ret.write(concated2, 0, concated2.length);
                } else if (uuid2.length == 4) {
                    ret.write(concated2.length + 1);
                    ret.write(32);
                    ret.write(concated2, 0, concated2.length);
                } else {
                    ret.write(concated2.length + 1);
                    ret.write(33);
                    ret.write(concated2, 0, concated2.length);
                }
            }
        }
        return ret.toByteArray();
    }
}
