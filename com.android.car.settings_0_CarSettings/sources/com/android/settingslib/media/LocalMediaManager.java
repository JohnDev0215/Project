package com.android.settingslib.media;

import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.media.RoutingSessionInfo;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.media.MediaManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes6.dex */
public class LocalMediaManager implements BluetoothCallback {
    private static final Comparator<MediaDevice> COMPARATOR = Comparator.naturalOrder();
    private static final int MAX_DISCONNECTED_DEVICE_NUM = 5;
    private static final String TAG = "LocalMediaManager";
    private Context mContext;
    MediaDevice mCurrentConnectedDevice;
    private InfoMediaManager mInfoMediaManager;
    private LocalBluetoothManager mLocalBluetoothManager;
    private MediaDevice mOnTransferBluetoothDevice;
    private String mPackageName;
    MediaDevice mPhoneDevice;
    private final Collection<DeviceCallback> mCallbacks = new CopyOnWriteArrayList();
    private final Object mMediaDevicesLock = new Object();
    final MediaDeviceCallback mMediaDeviceCallback = new MediaDeviceCallback();
    List<MediaDevice> mMediaDevices = new CopyOnWriteArrayList();
    List<MediaDevice> mDisconnectedMediaDevices = new CopyOnWriteArrayList();
    DeviceAttributeChangeCallback mDeviceAttributeChangeCallback = new DeviceAttributeChangeCallback();
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaDeviceState {
        public static final int STATE_CONNECTED = 0;
        public static final int STATE_CONNECTING = 1;
        public static final int STATE_CONNECTING_FAILED = 3;
        public static final int STATE_DISCONNECTED = 2;
    }

    public void registerCallback(DeviceCallback callback) {
        this.mCallbacks.add(callback);
    }

    public void unregisterCallback(DeviceCallback callback) {
        this.mCallbacks.remove(callback);
    }

    public LocalMediaManager(Context context, String packageName, Notification notification) {
        this.mContext = context;
        this.mPackageName = packageName;
        this.mLocalBluetoothManager = LocalBluetoothManager.getInstance(context, null);
        if (this.mLocalBluetoothManager == null) {
            Log.e(TAG, "Bluetooth is not supported on this device");
        } else {
            this.mInfoMediaManager = new InfoMediaManager(context, packageName, notification, this.mLocalBluetoothManager);
        }
    }

    public LocalMediaManager(Context context, LocalBluetoothManager localBluetoothManager, InfoMediaManager infoMediaManager, String packageName) {
        this.mContext = context;
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mInfoMediaManager = infoMediaManager;
        this.mPackageName = packageName;
    }

    public boolean connectDevice(MediaDevice connectDevice) {
        MediaDevice device;
        synchronized (this.mMediaDevicesLock) {
            device = getMediaDeviceById(this.mMediaDevices, connectDevice.getId());
        }
        if (device == null) {
            Log.w(TAG, "connectDevice() connectDevice not in the list!");
            return false;
        }
        if (device instanceof BluetoothMediaDevice) {
            CachedBluetoothDevice cachedDevice = ((BluetoothMediaDevice) device).getCachedDevice();
            if (!cachedDevice.isConnected() && !cachedDevice.isBusy()) {
                this.mOnTransferBluetoothDevice = connectDevice;
                device.setState(1);
                cachedDevice.connect();
                return true;
            }
        }
        MediaDevice mediaDevice = this.mCurrentConnectedDevice;
        if (device == mediaDevice) {
            Log.d(TAG, "connectDevice() this device all ready connected! : " + device.getName());
            return false;
        }
        if (mediaDevice != null) {
            mediaDevice.disconnect();
        }
        device.setState(1);
        if (TextUtils.isEmpty(this.mPackageName)) {
            this.mInfoMediaManager.connectDeviceWithoutPackageName(device);
        } else {
            device.connect();
        }
        return true;
    }

    void dispatchSelectedDeviceStateChanged(MediaDevice device, int state) {
        for (DeviceCallback callback : getCallbacks()) {
            callback.onSelectedDeviceStateChanged(device, state);
        }
    }

    public void startScan() {
        synchronized (this.mMediaDevicesLock) {
            this.mMediaDevices.clear();
        }
        this.mInfoMediaManager.registerCallback(this.mMediaDeviceCallback);
        this.mInfoMediaManager.startScan();
    }

    void dispatchDeviceListUpdate() {
        List<MediaDevice> mediaDevices = new ArrayList<>(this.mMediaDevices);
        for (DeviceCallback callback : getCallbacks()) {
            callback.onDeviceListUpdate(mediaDevices);
        }
    }

    void dispatchDeviceAttributesChanged() {
        for (DeviceCallback callback : getCallbacks()) {
            callback.onDeviceAttributesChanged();
        }
    }

    void dispatchOnRequestFailed(int reason) {
        for (DeviceCallback callback : getCallbacks()) {
            callback.onRequestFailed(reason);
        }
    }

    public void stopScan() {
        this.mInfoMediaManager.unregisterCallback(this.mMediaDeviceCallback);
        this.mInfoMediaManager.stopScan();
        unRegisterDeviceAttributeChangeCallback();
    }

    public MediaDevice getMediaDeviceById(List<MediaDevice> devices, String id) {
        for (MediaDevice mediaDevice : devices) {
            if (TextUtils.equals(mediaDevice.getId(), id)) {
                return mediaDevice;
            }
        }
        Log.i(TAG, "getMediaDeviceById() can't found device");
        return null;
    }

    public MediaDevice getMediaDeviceById(String id) {
        synchronized (this.mMediaDevicesLock) {
            for (MediaDevice mediaDevice : this.mMediaDevices) {
                if (TextUtils.equals(mediaDevice.getId(), id)) {
                    return mediaDevice;
                }
            }
            Log.i(TAG, "Unable to find device " + id);
            return null;
        }
    }

    public MediaDevice getCurrentConnectedDevice() {
        return this.mCurrentConnectedDevice;
    }

    public boolean addDeviceToPlayMedia(MediaDevice device) {
        return this.mInfoMediaManager.addDeviceToPlayMedia(device);
    }

    public boolean removeDeviceFromPlayMedia(MediaDevice device) {
        return this.mInfoMediaManager.removeDeviceFromPlayMedia(device);
    }

    public List<MediaDevice> getSelectableMediaDevice() {
        return this.mInfoMediaManager.getSelectableMediaDevice();
    }

    public List<MediaDevice> getDeselectableMediaDevice() {
        return this.mInfoMediaManager.getDeselectableMediaDevice();
    }

    public boolean releaseSession() {
        return this.mInfoMediaManager.releaseSession();
    }

    public List<MediaDevice> getSelectedMediaDevice() {
        return this.mInfoMediaManager.getSelectedMediaDevice();
    }

    public void adjustSessionVolume(String sessionId, int volume) {
        List<RoutingSessionInfo> infos = getActiveMediaSession();
        for (RoutingSessionInfo info : infos) {
            if (TextUtils.equals(sessionId, info.getId())) {
                this.mInfoMediaManager.adjustSessionVolume(info, volume);
                return;
            }
        }
        Log.w(TAG, "adjustSessionVolume: Unable to find session: " + sessionId);
    }

    public void adjustSessionVolume(int volume) {
        this.mInfoMediaManager.adjustSessionVolume(volume);
    }

    public int getSessionVolumeMax() {
        return this.mInfoMediaManager.getSessionVolumeMax();
    }

    public int getSessionVolume() {
        return this.mInfoMediaManager.getSessionVolume();
    }

    public CharSequence getSessionName() {
        return this.mInfoMediaManager.getSessionName();
    }

    public List<RoutingSessionInfo> getActiveMediaSession() {
        return this.mInfoMediaManager.getActiveMediaSession();
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    MediaDevice updateCurrentConnectedDevice() {
        MediaDevice connectedDevice = null;
        synchronized (this.mMediaDevicesLock) {
            for (MediaDevice device : this.mMediaDevices) {
                if (device instanceof BluetoothMediaDevice) {
                    if (isActiveDevice(((BluetoothMediaDevice) device).getCachedDevice()) && device.isConnected()) {
                        return device;
                    }
                } else if (device instanceof PhoneMediaDevice) {
                    connectedDevice = device;
                }
            }
            return connectedDevice;
        }
    }

    private boolean isActiveDevice(CachedBluetoothDevice device) {
        HearingAidProfile hearingAidProfile;
        boolean isActiveDeviceA2dp = false;
        boolean isActiveDeviceHearingAid = false;
        A2dpProfile a2dpProfile = this.mLocalBluetoothManager.getProfileManager().getA2dpProfile();
        if (a2dpProfile != null) {
            isActiveDeviceA2dp = device.getDevice().equals(a2dpProfile.getActiveDevice());
        }
        if (!isActiveDeviceA2dp && (hearingAidProfile = this.mLocalBluetoothManager.getProfileManager().getHearingAidProfile()) != null) {
            isActiveDeviceHearingAid = hearingAidProfile.getActiveDevices().contains(device.getDevice());
        }
        return isActiveDeviceA2dp || isActiveDeviceHearingAid;
    }

    private Collection<DeviceCallback> getCallbacks() {
        return new CopyOnWriteArrayList(this.mCallbacks);
    }

    class MediaDeviceCallback implements MediaManager.MediaDeviceCallback {
        MediaDeviceCallback() {
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onDeviceAdded(MediaDevice device) {
            boolean isAdded = false;
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                if (!LocalMediaManager.this.mMediaDevices.contains(device)) {
                    LocalMediaManager.this.mMediaDevices.add(device);
                    isAdded = true;
                }
            }
            if (isAdded) {
                LocalMediaManager.this.dispatchDeviceListUpdate();
            }
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onDeviceListAdded(List<MediaDevice> devices) {
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                LocalMediaManager.this.mMediaDevices.clear();
                LocalMediaManager.this.mMediaDevices.addAll(devices);
                Collections.sort(devices, LocalMediaManager.COMPARATOR);
                for (MediaDevice device : devices) {
                    int type = device.getDeviceType();
                    if (type != 1 && type != 2 && type != 7) {
                    }
                    LocalMediaManager.this.mMediaDevices.addAll(buildDisconnectedBluetoothDevice());
                }
            }
            MediaDevice infoMediaDevice = LocalMediaManager.this.mInfoMediaManager.getCurrentConnectedDevice();
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            localMediaManager.mCurrentConnectedDevice = infoMediaDevice != null ? infoMediaDevice : localMediaManager.updateCurrentConnectedDevice();
            LocalMediaManager.this.dispatchDeviceListUpdate();
            if (LocalMediaManager.this.mOnTransferBluetoothDevice != null && LocalMediaManager.this.mOnTransferBluetoothDevice.isConnected()) {
                LocalMediaManager localMediaManager2 = LocalMediaManager.this;
                localMediaManager2.connectDevice(localMediaManager2.mOnTransferBluetoothDevice);
                LocalMediaManager.this.mOnTransferBluetoothDevice.setState(0);
                LocalMediaManager localMediaManager3 = LocalMediaManager.this;
                localMediaManager3.dispatchSelectedDeviceStateChanged(localMediaManager3.mOnTransferBluetoothDevice, 0);
                LocalMediaManager.this.mOnTransferBluetoothDevice = null;
            }
        }

        private List<MediaDevice> buildDisconnectedBluetoothDevice() {
            if (LocalMediaManager.this.mBluetoothAdapter == null) {
                Log.w(LocalMediaManager.TAG, "buildDisconnectedBluetoothDevice() BluetoothAdapter is null");
                return new ArrayList();
            }
            List<BluetoothDevice> bluetoothDevices = LocalMediaManager.this.mBluetoothAdapter.getMostRecentlyConnectedDevices();
            CachedBluetoothDeviceManager cachedDeviceManager = LocalMediaManager.this.mLocalBluetoothManager.getCachedDeviceManager();
            List<CachedBluetoothDevice> cachedBluetoothDeviceList = new ArrayList<>();
            int deviceCount = 0;
            for (BluetoothDevice device : bluetoothDevices) {
                CachedBluetoothDevice cachedDevice = cachedDeviceManager.findDevice(device);
                if (cachedDevice != null && cachedDevice.getBondState() == 12 && !cachedDevice.isConnected() && isA2dpOrHearingAidDevice(cachedDevice)) {
                    deviceCount++;
                    cachedBluetoothDeviceList.add(cachedDevice);
                    if (deviceCount >= 5) {
                        break;
                    }
                }
            }
            LocalMediaManager.this.unRegisterDeviceAttributeChangeCallback();
            LocalMediaManager.this.mDisconnectedMediaDevices.clear();
            for (CachedBluetoothDevice cachedDevice2 : cachedBluetoothDeviceList) {
                MediaDevice mediaDevice = new BluetoothMediaDevice(LocalMediaManager.this.mContext, cachedDevice2, null, null, LocalMediaManager.this.mPackageName);
                if (!LocalMediaManager.this.mMediaDevices.contains(mediaDevice)) {
                    cachedDevice2.registerCallback(LocalMediaManager.this.mDeviceAttributeChangeCallback);
                    LocalMediaManager.this.mDisconnectedMediaDevices.add(mediaDevice);
                }
            }
            return new ArrayList(LocalMediaManager.this.mDisconnectedMediaDevices);
        }

        private boolean isA2dpOrHearingAidDevice(CachedBluetoothDevice device) {
            for (LocalBluetoothProfile profile : device.getConnectableProfiles()) {
                if ((profile instanceof A2dpProfile) || (profile instanceof HearingAidProfile)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onDeviceRemoved(MediaDevice device) {
            boolean isRemoved = false;
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                if (LocalMediaManager.this.mMediaDevices.contains(device)) {
                    LocalMediaManager.this.mMediaDevices.remove(device);
                    isRemoved = true;
                }
            }
            if (isRemoved) {
                LocalMediaManager.this.dispatchDeviceListUpdate();
            }
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onDeviceListRemoved(List<MediaDevice> devices) {
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                LocalMediaManager.this.mMediaDevices.removeAll(devices);
            }
            LocalMediaManager.this.dispatchDeviceListUpdate();
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onConnectedDeviceChanged(String id) {
            MediaDevice connectDevice;
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                connectDevice = LocalMediaManager.this.getMediaDeviceById(LocalMediaManager.this.mMediaDevices, id);
            }
            MediaDevice connectDevice2 = connectDevice != null ? connectDevice : LocalMediaManager.this.updateCurrentConnectedDevice();
            LocalMediaManager.this.mCurrentConnectedDevice = connectDevice2;
            if (connectDevice2 != null) {
                connectDevice2.setState(0);
                LocalMediaManager localMediaManager = LocalMediaManager.this;
                localMediaManager.dispatchSelectedDeviceStateChanged(localMediaManager.mCurrentConnectedDevice, 0);
            }
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onDeviceAttributesChanged() {
            LocalMediaManager.this.dispatchDeviceAttributesChanged();
        }

        @Override // com.android.settingslib.media.MediaManager.MediaDeviceCallback
        public void onRequestFailed(int reason) {
            synchronized (LocalMediaManager.this.mMediaDevicesLock) {
                for (MediaDevice device : LocalMediaManager.this.mMediaDevices) {
                    if (device.getState() == 1) {
                        device.setState(3);
                    }
                }
            }
            LocalMediaManager.this.dispatchOnRequestFailed(reason);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unRegisterDeviceAttributeChangeCallback() {
        for (MediaDevice device : this.mDisconnectedMediaDevices) {
            ((BluetoothMediaDevice) device).getCachedDevice().unregisterCallback(this.mDeviceAttributeChangeCallback);
        }
    }

    public interface DeviceCallback {
        default void onDeviceListUpdate(List<MediaDevice> devices) {
        }

        default void onSelectedDeviceStateChanged(MediaDevice device, int state) {
        }

        default void onDeviceAttributesChanged() {
        }

        default void onRequestFailed(int reason) {
        }
    }

    class DeviceAttributeChangeCallback implements CachedBluetoothDevice.Callback {
        DeviceAttributeChangeCallback() {
        }

        @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
        public void onDeviceAttributesChanged() {
            if (LocalMediaManager.this.mOnTransferBluetoothDevice != null && !((BluetoothMediaDevice) LocalMediaManager.this.mOnTransferBluetoothDevice).getCachedDevice().isBusy() && !LocalMediaManager.this.mOnTransferBluetoothDevice.isConnected()) {
                LocalMediaManager.this.mOnTransferBluetoothDevice.setState(3);
                LocalMediaManager.this.mOnTransferBluetoothDevice = null;
                LocalMediaManager.this.dispatchOnRequestFailed(0);
            }
            LocalMediaManager.this.dispatchDeviceAttributesChanged();
        }
    }
}
