package com.android.server.telecom.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.telecom.Log;
import android.util.LocalLog;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.BluetoothAdapterProxy;
import com.android.server.telecom.BluetoothHeadsetProxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceManager {
    private BluetoothAdapterProxy mBluetoothAdapterProxy;
    private BluetoothHeadsetProxy mBluetoothHeadsetService;
    private BluetoothDevice mBluetoothHearingAidActiveDeviceCache;
    private BluetoothHearingAid mBluetoothHearingAidService;
    private BluetoothRouteManager mBluetoothRouteManager;
    private final BluetoothProfile.ServiceListener mBluetoothProfileServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.server.telecom.bluetooth.BluetoothDeviceManager.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            String str;
            Log.startSession("BMSL.oSC");
            try {
                synchronized (BluetoothDeviceManager.this.mLock) {
                    try {
                        if (i == 1) {
                            BluetoothDeviceManager.this.mBluetoothHeadsetService = new BluetoothHeadsetProxy((BluetoothHeadset) bluetoothProfile);
                            str = "Got BluetoothHeadset: " + BluetoothDeviceManager.this.mBluetoothHeadsetService;
                        } else if (i == 21) {
                            BluetoothDeviceManager.this.mBluetoothHearingAidService = (BluetoothHearingAid) bluetoothProfile;
                            str = "Got BluetoothHearingAid: " + BluetoothDeviceManager.this.mBluetoothHearingAidService;
                        } else {
                            str = "Connected to non-requested bluetooth service. Not changing bluetooth headset.";
                        }
                        Log.i(BluetoothDeviceManager.this, str, new Object[0]);
                        BluetoothDeviceManager.this.mLocalLog.log(str);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            String str;
            LinkedHashMap linkedHashMap;
            Log.startSession("BMSL.oSD");
            try {
                synchronized (BluetoothDeviceManager.this.mLock) {
                    try {
                        if (i == 1) {
                            BluetoothDeviceManager.this.mBluetoothHeadsetService = null;
                            linkedHashMap = BluetoothDeviceManager.this.mHfpDevicesByAddress;
                            BluetoothDeviceManager.this.mBluetoothRouteManager.onActiveDeviceChanged(null, false);
                            str = "Lost BluetoothHeadset service. Removing all tracked devices";
                        } else {
                            if (i != 21) {
                                return;
                            }
                            BluetoothDeviceManager.this.mBluetoothHearingAidService = null;
                            LinkedHashMap linkedHashMap2 = BluetoothDeviceManager.this.mHearingAidDevicesByAddress;
                            BluetoothDeviceManager.this.mBluetoothRouteManager.onActiveDeviceChanged(null, true);
                            str = "Lost BluetoothHearingAid service. Removing all tracked devices.";
                            linkedHashMap = linkedHashMap2;
                        }
                        Log.i(BluetoothDeviceManager.this, str, new Object[0]);
                        BluetoothDeviceManager.this.mLocalLog.log(str);
                        LinkedList linkedList = new LinkedList(linkedHashMap.values());
                        linkedHashMap.clear();
                        Iterator it = linkedList.iterator();
                        while (it.hasNext()) {
                            BluetoothDeviceManager.this.mBluetoothRouteManager.onDeviceLost(((BluetoothDevice) it.next()).getAddress());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private final LinkedHashMap<String, BluetoothDevice> mHfpDevicesByAddress = new LinkedHashMap<>();
    private final LinkedHashMap<String, BluetoothDevice> mHearingAidDevicesByAddress = new LinkedHashMap<>();
    private final LinkedHashMap<BluetoothDevice, Long> mHearingAidDeviceSyncIds = new LinkedHashMap<>();
    private final LocalLog mLocalLog = new LocalLog(20);
    private final Object mLock = new Object();

    public BluetoothDeviceManager(Context context, BluetoothAdapterProxy bluetoothAdapterProxy) {
        if (bluetoothAdapterProxy != null) {
            this.mBluetoothAdapterProxy = bluetoothAdapterProxy;
            bluetoothAdapterProxy.getProfileProxy(context, this.mBluetoothProfileServiceListener, 1);
            bluetoothAdapterProxy.getProfileProxy(context, this.mBluetoothProfileServiceListener, 21);
        }
    }

    public void setBluetoothRouteManager(BluetoothRouteManager bluetoothRouteManager) {
        this.mBluetoothRouteManager = bluetoothRouteManager;
    }

    public int getNumConnectedDevices() {
        int size;
        synchronized (this.mLock) {
            size = this.mHfpDevicesByAddress.size() + this.mHearingAidDevicesByAddress.size();
        }
        return size;
    }

    public Collection<BluetoothDevice> getConnectedDevices() {
        Collection<BluetoothDevice> collectionUnmodifiableCollection;
        synchronized (this.mLock) {
            ArrayList arrayList = new ArrayList(this.mHfpDevicesByAddress.values());
            arrayList.addAll(this.mHearingAidDevicesByAddress.values());
            collectionUnmodifiableCollection = Collections.unmodifiableCollection(arrayList);
        }
        return collectionUnmodifiableCollection;
    }

    public Collection<BluetoothDevice> getUniqueConnectedDevices() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mHfpDevicesByAddress.values());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        BluetoothHearingAid bluetoothHearingAid = this.mBluetoothHearingAidService;
        if (bluetoothHearingAid != null) {
            Iterator it = bluetoothHearingAid.getActiveDevices().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) it.next();
                if (bluetoothDevice != null) {
                    arrayList.add(bluetoothDevice);
                    linkedHashSet.add(this.mHearingAidDeviceSyncIds.getOrDefault(bluetoothDevice, -1L));
                    break;
                }
            }
        }
        synchronized (this.mLock) {
            for (BluetoothDevice bluetoothDevice2 : this.mHearingAidDevicesByAddress.values()) {
                long jLongValue = this.mHearingAidDeviceSyncIds.getOrDefault(bluetoothDevice2, -1L).longValue();
                if (!linkedHashSet.contains(Long.valueOf(jLongValue))) {
                    arrayList.add(bluetoothDevice2);
                    linkedHashSet.add(Long.valueOf(jLongValue));
                }
            }
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public BluetoothHeadsetProxy getHeadsetService() {
        return this.mBluetoothHeadsetService;
    }

    public BluetoothHearingAid getHearingAidService() {
        return this.mBluetoothHearingAidService;
    }

    void onDeviceConnected(BluetoothDevice bluetoothDevice, boolean z) {
        LinkedHashMap<String, BluetoothDevice> linkedHashMap;
        this.mLocalLog.log("Device connected -- address: " + bluetoothDevice.getAddress() + " isHeadingAid: " + z);
        synchronized (this.mLock) {
            if (z) {
                if (this.mBluetoothHearingAidService == null) {
                    Log.w(this, "Hearing aid service null when receiving device added broadcast", new Object[0]);
                    return;
                } else {
                    this.mHearingAidDeviceSyncIds.put(bluetoothDevice, Long.valueOf(this.mBluetoothHearingAidService.getHiSyncId(bluetoothDevice)));
                    linkedHashMap = this.mHearingAidDevicesByAddress;
                }
            } else {
                if (this.mBluetoothHeadsetService == null) {
                    Log.w(this, "Headset service null when receiving device added broadcast", new Object[0]);
                    return;
                }
                linkedHashMap = this.mHfpDevicesByAddress;
            }
            if (!linkedHashMap.containsKey(bluetoothDevice.getAddress())) {
                linkedHashMap.put(bluetoothDevice.getAddress(), bluetoothDevice);
                this.mBluetoothRouteManager.onDeviceAdded(bluetoothDevice.getAddress());
            }
        }
    }

    void onDeviceDisconnected(BluetoothDevice bluetoothDevice, boolean z) {
        LinkedHashMap<String, BluetoothDevice> linkedHashMap;
        this.mLocalLog.log("Device disconnected -- address: " + bluetoothDevice.getAddress() + " isHeadingAid: " + z);
        synchronized (this.mLock) {
            if (z) {
                this.mHearingAidDeviceSyncIds.remove(bluetoothDevice);
                linkedHashMap = this.mHearingAidDevicesByAddress;
            } else {
                linkedHashMap = this.mHfpDevicesByAddress;
            }
            if (linkedHashMap.containsKey(bluetoothDevice.getAddress())) {
                linkedHashMap.remove(bluetoothDevice.getAddress());
                this.mBluetoothRouteManager.onDeviceLost(bluetoothDevice.getAddress());
            }
        }
    }

    public void disconnectAudio() {
        BluetoothHearingAid bluetoothHearingAid = this.mBluetoothHearingAidService;
        if (bluetoothHearingAid == null) {
            Log.w(this, "Trying to disconnect audio but no hearing aid service exists", new Object[0]);
        } else {
            Iterator it = bluetoothHearingAid.getActiveDevices().iterator();
            while (it.hasNext()) {
                if (((BluetoothDevice) it.next()) != null) {
                    this.mBluetoothAdapterProxy.setActiveDevice(null, 2);
                }
            }
        }
        disconnectSco();
    }

    public void disconnectSco() {
        BluetoothHeadsetProxy bluetoothHeadsetProxy = this.mBluetoothHeadsetService;
        if (bluetoothHeadsetProxy == null) {
            Log.w(this, "Trying to disconnect audio but no headset service exists.", new Object[0]);
        } else {
            bluetoothHeadsetProxy.disconnectAudio();
        }
    }

    public boolean connectAudio(String str) {
        if (this.mHearingAidDevicesByAddress.containsKey(str)) {
            if (this.mBluetoothHearingAidService == null) {
                Log.w(this, "Attempting to turn on audio when the hearing aid service is null", new Object[0]);
                return false;
            }
            return this.mBluetoothAdapterProxy.setActiveDevice(this.mHearingAidDevicesByAddress.get(str), 2);
        }
        if (this.mHfpDevicesByAddress.containsKey(str)) {
            BluetoothDevice bluetoothDevice = this.mHfpDevicesByAddress.get(str);
            if (this.mBluetoothHeadsetService == null) {
                Log.w(this, "Attempting to turn on audio when the headset service is null", new Object[0]);
                return false;
            }
            if (!this.mBluetoothAdapterProxy.setActiveDevice(bluetoothDevice, 1)) {
                Log.w(this, "Couldn't set active device to %s", new Object[]{str});
                return false;
            }
            if (this.mBluetoothHeadsetService.isAudioOn()) {
                return true;
            }
            return this.mBluetoothHeadsetService.connectAudio();
        }
        Log.w(this, "Attempting to turn on audio for a disconnected device", new Object[0]);
        return false;
    }

    public void cacheHearingAidDevice() {
        BluetoothHearingAid bluetoothHearingAid = this.mBluetoothHearingAidService;
        if (bluetoothHearingAid != null) {
            for (BluetoothDevice bluetoothDevice : bluetoothHearingAid.getActiveDevices()) {
                if (bluetoothDevice != null) {
                    this.mBluetoothHearingAidActiveDeviceCache = bluetoothDevice;
                }
            }
        }
    }

    public void restoreHearingAidDevice() {
        BluetoothDevice bluetoothDevice = this.mBluetoothHearingAidActiveDeviceCache;
        if (bluetoothDevice == null || this.mBluetoothHearingAidService == null) {
            return;
        }
        this.mBluetoothAdapterProxy.setActiveDevice(bluetoothDevice, 2);
        this.mBluetoothHearingAidActiveDeviceCache = null;
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        this.mLocalLog.dump(indentingPrintWriter);
    }
}
