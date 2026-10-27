package com.android.bluetooth.avrcp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioDeviceCallback;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.net.INetd;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class AvrcpVolumeManager extends AudioDeviceCallback {
    private static final int AVRCP_MAX_VOL = 127;
    public static final boolean DEBUG = true;
    private static final int STREAM_MUSIC = 3;
    public static final String TAG = "AvrcpVolumeManager";
    private static final String VOLUME_BLACKLIST = "absolute_volume_blacklist";
    private static final int VOLUME_CHANGE_LOGGER_SIZE = 30;
    private static final String VOLUME_CHANGE_LOG_TITLE = "Volume Events";
    private static final String VOLUME_MAP = "bluetooth_volume_map";
    private static int sDeviceMaxVolume = 0;
    private static int sNewDeviceVolume = 0;
    AudioManager mAudioManager;
    Context mContext;
    AvrcpNativeInterface mNativeInterface;
    private final AvrcpEventLogger mVolumeEventLogger = new AvrcpEventLogger(30, VOLUME_CHANGE_LOG_TITLE);
    HashMap<BluetoothDevice, Boolean> mDeviceMap = new HashMap<>();
    HashMap<BluetoothDevice, Integer> mVolumeMap = new HashMap<>();
    BluetoothDevice mCurrentDevice = null;
    boolean mAbsoluteVolumeSupported = false;

    static int avrcpToSystemVolume(int avrcpVolume) {
        return (int) Math.floor((((double) avrcpVolume) * ((double) sDeviceMaxVolume)) / 127.0d);
    }

    static int systemToAvrcpVolume(int deviceVolume) {
        int avrcpVolume = (int) Math.floor((((double) deviceVolume) * 127.0d) / ((double) sDeviceMaxVolume));
        if (avrcpVolume > 127) {
            return 127;
        }
        return avrcpVolume;
    }

    private SharedPreferences getVolumeMap() {
        return this.mContext.getSharedPreferences(VOLUME_MAP, 0);
    }

    private void switchVolumeDevice(BluetoothDevice device) {
        d("switchVolumeDevice: Set Absolute volume support to " + this.mDeviceMap.get(device));
        this.mAudioManager.avrcpSupportsAbsoluteVolume(device.getAddress(), this.mDeviceMap.get(device).booleanValue());
        int savedVolume = getVolume(device, sNewDeviceVolume);
        d("switchVolumeDevice: savedVolume=" + savedVolume);
        if (this.mDeviceMap.get(device).booleanValue()) {
            int avrcpVolume = systemToAvrcpVolume(savedVolume);
            this.mVolumeEventLogger.logd(TAG, "switchVolumeDevice: Updating device volume: avrcpVolume=" + avrcpVolume);
            this.mNativeInterface.sendVolumeChanged(device.getAddress(), avrcpVolume);
        }
    }

    AvrcpVolumeManager(Context context, AudioManager audioManager, AvrcpNativeInterface nativeInterface) {
        this.mContext = context;
        this.mAudioManager = audioManager;
        this.mNativeInterface = nativeInterface;
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        sDeviceMaxVolume = streamMaxVolume;
        sNewDeviceVolume = streamMaxVolume / 2;
        this.mAudioManager.registerAudioDeviceCallback(this, null);
        Map<String, ?> allKeys = getVolumeMap().getAll();
        SharedPreferences.Editor volumeMapEditor = getVolumeMap().edit();
        for (Map.Entry<String, ?> entry : allKeys.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(key);
            if ((value instanceof Integer) && d.getBondState() == 12) {
                this.mVolumeMap.put(d, (Integer) value);
            } else {
                d("Removing " + key + " from the volume map");
                volumeMapEditor.remove(key);
            }
        }
        volumeMapEditor.apply();
    }

    synchronized void storeVolumeForDevice(BluetoothDevice device, int storeVolume) {
        if (device.getBondState() != 12) {
            return;
        }
        SharedPreferences.Editor pref = getVolumeMap().edit();
        this.mVolumeEventLogger.logd(TAG, "storeVolume: Storing stream volume level for device " + device.getAddressOnlyforLog() + " : " + storeVolume);
        this.mVolumeMap.put(device, Integer.valueOf(storeVolume));
        pref.putInt(device.getAddress(), storeVolume);
        pref.apply();
    }

    synchronized void storeVolumeForDevice(BluetoothDevice device) {
        int storeVolume = this.mAudioManager.getStreamVolume(3);
        storeVolumeForDevice(device, storeVolume);
    }

    synchronized void removeStoredVolumeForDevice(BluetoothDevice device) {
        if (device.getBondState() != 10) {
            return;
        }
        SharedPreferences.Editor pref = getVolumeMap().edit();
        this.mVolumeEventLogger.logd(TAG, "RemoveStoredVolume: Remove stored stream volume level for device " + device.getAddressOnlyforLog());
        this.mVolumeMap.remove(device);
        pref.remove(device.getAddress());
        pref.apply();
    }

    synchronized int getVolume(BluetoothDevice device, int defaultValue) {
        if (!this.mVolumeMap.containsKey(device)) {
            Log.w(TAG, "getVolume: Couldn't find volume preference for device: " + device.getAddressOnlyforLog());
            return defaultValue;
        }
        d("getVolume: Returning volume " + this.mVolumeMap.get(device));
        return this.mVolumeMap.get(device).intValue();
    }

    public int getNewDeviceVolume() {
        return sNewDeviceVolume;
    }

    void setVolume(BluetoothDevice device, int avrcpVolume) {
        int deviceVolume = (int) Math.floor((((double) avrcpVolume) * ((double) sDeviceMaxVolume)) / 127.0d);
        this.mVolumeEventLogger.logd(true, TAG, "setVolume: device=" + device.getAddressOnlyforLog() + " avrcpVolume=" + avrcpVolume + " deviceVolume=" + deviceVolume + " sDeviceMaxVolume=" + sDeviceMaxVolume);
        this.mAudioManager.setStreamVolume(3, deviceVolume, (deviceVolume == getVolume(device, -1) ? 0 : 1) | 64);
        storeVolumeForDevice(device);
    }

    void sendVolumeChanged(BluetoothDevice device, int deviceVolume) {
        if (deviceVolume == getVolume(device, -1)) {
            d("sendVolumeChanged: Skipping update volume to same as current.");
            return;
        }
        int avrcpVolume = (int) Math.floor((((double) deviceVolume) * 127.0d) / ((double) sDeviceMaxVolume));
        if (avrcpVolume > 127) {
            avrcpVolume = 127;
        }
        this.mVolumeEventLogger.logd(true, TAG, "sendVolumeChanged: device=" + device.getAddressOnlyforLog() + " avrcpVolume=" + avrcpVolume + " deviceVolume=" + deviceVolume + " sDeviceMaxVolume=" + sDeviceMaxVolume);
        this.mNativeInterface.sendVolumeChanged(device.getAddress(), avrcpVolume);
        storeVolumeForDevice(device);
    }

    boolean getAbsoluteVolumeSupported(BluetoothDevice device) {
        if (this.mDeviceMap.containsKey(device)) {
            return this.mDeviceMap.get(device).booleanValue();
        }
        return false;
    }

    @Override // android.media.AudioDeviceCallback
    public synchronized void onAudioDevicesAdded(AudioDeviceInfo[] addedDevices) {
        if (this.mCurrentDevice == null) {
            d("onAudioDevicesAdded: Not expecting device changed");
            return;
        }
        boolean foundDevice = false;
        d("onAudioDevicesAdded: size: " + addedDevices.length);
        int i = 0;
        while (true) {
            if (i >= addedDevices.length) {
                break;
            }
            d("onAudioDevicesAdded: address=" + addedDevices[i].getAddress());
            if (addedDevices[i].getType() != 8 || !Objects.equals(addedDevices[i].getAddress(), this.mCurrentDevice.getAddress())) {
                i++;
            } else {
                foundDevice = true;
                break;
            }
        }
        if (!foundDevice) {
            d("Didn't find deferred device in list: device=" + this.mCurrentDevice);
            return;
        }
        if (!this.mDeviceMap.containsKey(this.mCurrentDevice)) {
            Log.w(TAG, "volumeDeviceSwitched: Device isn't connected: " + this.mCurrentDevice);
            return;
        }
        switchVolumeDevice(this.mCurrentDevice);
    }

    synchronized void deviceConnected(BluetoothDevice device, boolean absoluteVolume) {
        d("deviceConnected: device=" + device.getAddressOnlyforLog() + " absoluteVolume=" + absoluteVolume);
        this.mDeviceMap.put(device, Boolean.valueOf(absoluteVolume));
        if (device.equals(this.mCurrentDevice)) {
            switchVolumeDevice(device);
        }
    }

    synchronized void volumeDeviceSwitched(BluetoothDevice device) {
        d("volumeDeviceSwitched: mCurrentDevice=" + this.mCurrentDevice + " device=" + device.getAddressOnlyforLog());
        if (Objects.equals(device, this.mCurrentDevice)) {
            return;
        }
        this.mCurrentDevice = device;
    }

    synchronized void deviceDisconnected(BluetoothDevice device) {
        d("deviceDisconnected: device=" + device.getAddressOnlyforLog());
        this.mDeviceMap.remove(device);
    }

    public void dump(StringBuilder sb) {
        sb.append("AvrcpVolumeManager:\n");
        sb.append("  mCurrentDevice: " + this.mCurrentDevice + "\n");
        sb.append("  Current System Volume: " + this.mAudioManager.getStreamVolume(3) + "\n");
        sb.append("  Device Volume Memory Map:\n");
        int i = 4;
        sb.append(String.format("    %-17s : %-14s : %3s : %s\n", "Device Address", "Device Name", "Vol", "AbsVol"));
        Map<String, ?> allKeys = getVolumeMap().getAll();
        for (Map.Entry<String, ?> entry : allKeys.entrySet()) {
            Object value = entry.getValue();
            BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(entry.getKey());
            String deviceName = d.getName();
            if (deviceName == null) {
                deviceName = INetd.NEXTHOP_NONE;
            } else if (deviceName.length() > 14) {
                deviceName = deviceName.substring(0, 11).concat("...");
            }
            String absoluteVolume = "NotConnected";
            if (this.mDeviceMap.containsKey(d)) {
                absoluteVolume = this.mDeviceMap.get(d).toString();
            }
            if (value instanceof Integer) {
                Object[] objArr = new Object[i];
                objArr[0] = d.getAddress();
                objArr[1] = deviceName;
                objArr[2] = (Integer) value;
                objArr[3] = absoluteVolume;
                sb.append(String.format("    %-17s : %-14s : %3d : %s\n", objArr));
            }
            i = 4;
        }
        StringBuilder tempBuilder = new StringBuilder();
        this.mVolumeEventLogger.dump(tempBuilder);
        sb.append(tempBuilder.toString().replaceAll("(?m)^", "  "));
        tempBuilder.append("\n");
    }

    static void d(String msg) {
        Log.d(TAG, msg);
    }
}
