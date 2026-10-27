package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes6.dex */
public class CachedBluetoothDeviceManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "CachedBluetoothDeviceManager";
    private final LocalBluetoothManager mBtManager;
    final List<CachedBluetoothDevice> mCachedDevices;
    private Context mContext;
    HearingAidDeviceManager mHearingAidDeviceManager;

    CachedBluetoothDeviceManager(Context context, LocalBluetoothManager localBtManager) {
        ArrayList arrayList = new ArrayList();
        this.mCachedDevices = arrayList;
        this.mContext = context;
        this.mBtManager = localBtManager;
        this.mHearingAidDeviceManager = new HearingAidDeviceManager(localBtManager, arrayList);
    }

    public synchronized Collection<CachedBluetoothDevice> getCachedDevicesCopy() {
        return new ArrayList(this.mCachedDevices);
    }

    public static boolean onDeviceDisappeared(CachedBluetoothDevice cachedDevice) {
        cachedDevice.setJustDiscovered(false);
        return cachedDevice.getBondState() == 10;
    }

    public void onDeviceNameUpdated(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null) {
            cachedDevice.refreshName();
        }
    }

    public synchronized CachedBluetoothDevice findDevice(BluetoothDevice device) {
        for (CachedBluetoothDevice cachedDevice : this.mCachedDevices) {
            if (cachedDevice.getDevice().equals(device)) {
                return cachedDevice;
            }
            CachedBluetoothDevice subDevice = cachedDevice.getSubDevice();
            if (subDevice != null && subDevice.getDevice().equals(device)) {
                return subDevice;
            }
        }
        return null;
    }

    public CachedBluetoothDevice addDevice(BluetoothDevice device) {
        CachedBluetoothDevice newDevice;
        LocalBluetoothProfileManager profileManager = this.mBtManager.getProfileManager();
        synchronized (this) {
            newDevice = findDevice(device);
            if (newDevice == null) {
                newDevice = new CachedBluetoothDevice(this.mContext, profileManager, device);
                this.mHearingAidDeviceManager.initHearingAidDeviceIfNeeded(newDevice);
                if (!this.mHearingAidDeviceManager.setSubDeviceIfNeeded(newDevice)) {
                    this.mCachedDevices.add(newDevice);
                    this.mBtManager.getEventManager().dispatchDeviceAdded(newDevice);
                }
            }
        }
        return newDevice;
    }

    public synchronized String getSubDeviceSummary(CachedBluetoothDevice device) {
        CachedBluetoothDevice subDevice = device.getSubDevice();
        if (subDevice != null && subDevice.isConnected()) {
            return subDevice.getConnectionSummary();
        }
        return null;
    }

    public synchronized boolean isSubDevice(BluetoothDevice device) {
        CachedBluetoothDevice subDevice;
        for (CachedBluetoothDevice cachedDevice : this.mCachedDevices) {
            if (!cachedDevice.getDevice().equals(device) && (subDevice = cachedDevice.getSubDevice()) != null && subDevice.getDevice().equals(device)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void updateHearingAidsDevices() {
        this.mHearingAidDeviceManager.updateHearingAidsDevices();
    }

    public String getName(BluetoothDevice device) {
        CachedBluetoothDevice cachedDevice = findDevice(device);
        if (cachedDevice != null && cachedDevice.getName() != null) {
            return cachedDevice.getName();
        }
        String name = device.getAlias();
        if (name != null) {
            return name;
        }
        return device.getAddress();
    }

    public synchronized void clearNonBondedDevices() {
        clearNonBondedSubDevices();
        this.mCachedDevices.removeIf(new Predicate() { // from class: com.android.settingslib.bluetooth.-$$Lambda$CachedBluetoothDeviceManager$1n6G0RUX5KnCwfoBdpyaC68q3xA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CachedBluetoothDeviceManager.lambda$clearNonBondedDevices$0((CachedBluetoothDevice) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$clearNonBondedDevices$0(CachedBluetoothDevice cachedDevice) {
        return cachedDevice.getBondState() == 10;
    }

    private void clearNonBondedSubDevices() {
        for (int i = this.mCachedDevices.size() - 1; i >= 0; i--) {
            CachedBluetoothDevice cachedDevice = this.mCachedDevices.get(i);
            CachedBluetoothDevice subDevice = cachedDevice.getSubDevice();
            if (subDevice != null && subDevice.getDevice().getBondState() == 10) {
                cachedDevice.setSubDevice(null);
            }
        }
    }

    public synchronized void onScanningStateChanged(boolean started) {
        if (started) {
            for (int i = this.mCachedDevices.size() - 1; i >= 0; i--) {
                CachedBluetoothDevice cachedDevice = this.mCachedDevices.get(i);
                cachedDevice.setJustDiscovered(false);
                CachedBluetoothDevice subDevice = cachedDevice.getSubDevice();
                if (subDevice != null) {
                    subDevice.setJustDiscovered(false);
                }
            }
        }
    }

    public synchronized void onBluetoothStateChanged(int bluetoothState) {
        if (bluetoothState == 13) {
            for (int i = this.mCachedDevices.size() - 1; i >= 0; i--) {
                CachedBluetoothDevice cachedDevice = this.mCachedDevices.get(i);
                CachedBluetoothDevice subDevice = cachedDevice.getSubDevice();
                if (subDevice != null && subDevice.getBondState() != 12) {
                    cachedDevice.setSubDevice(null);
                }
                if (cachedDevice.getBondState() != 12) {
                    cachedDevice.setJustDiscovered(false);
                    this.mCachedDevices.remove(i);
                }
            }
        }
    }

    public synchronized boolean onProfileConnectionStateChangedIfProcessed(CachedBluetoothDevice cachedDevice, int state) {
        return this.mHearingAidDeviceManager.onProfileConnectionStateChangedIfProcessed(cachedDevice, state);
    }

    public synchronized void onDeviceUnpaired(CachedBluetoothDevice device) {
        CachedBluetoothDevice mainDevice = this.mHearingAidDeviceManager.findMainDevice(device);
        CachedBluetoothDevice subDevice = device.getSubDevice();
        if (subDevice != null) {
            subDevice.unpair();
            device.setSubDevice(null);
        } else if (mainDevice != null) {
            mainDevice.unpair();
            mainDevice.setSubDevice(null);
        }
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }
}
