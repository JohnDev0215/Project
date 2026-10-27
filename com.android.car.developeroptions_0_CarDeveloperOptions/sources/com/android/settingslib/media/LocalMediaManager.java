package com.android.settingslib.media;

import android.bluetooth.BluetoothAdapter;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class LocalMediaManager implements BluetoothCallback {

    @VisibleForTesting
    BluetoothAdapter mBluetoothAdapter;
    private final Collection<DeviceCallback> mCallbacks;

    @VisibleForTesting
    MediaDevice mCurrentConnectedDevice;

    @VisibleForTesting
    DeviceAttributeChangeCallback mDeviceAttributeChangeCallback;

    @VisibleForTesting
    List<MediaDevice> mDisconnectedMediaDevices;
    private InfoMediaManager mInfoMediaManager;
    private LocalBluetoothManager mLocalBluetoothManager;

    @VisibleForTesting
    final MediaDeviceCallback mMediaDeviceCallback;

    @VisibleForTesting
    List<MediaDevice> mMediaDevices;
    private final Object mMediaDevicesLock;
    private MediaDevice mOnTransferBluetoothDevice;
    private String mPackageName;

    @VisibleForTesting
    MediaDevice mPhoneDevice;

    public interface DeviceCallback {
        default void onDeviceAttributesChanged() {
        }

        default void onRequestFailed(int i) {
        }
    }

    class MediaDeviceCallback {
    }

    static {
        Comparator.naturalOrder();
    }

    public boolean connectDevice(MediaDevice mediaDevice) {
        MediaDevice mediaDeviceById;
        synchronized (this.mMediaDevicesLock) {
            mediaDeviceById = getMediaDeviceById(this.mMediaDevices, mediaDevice.getId());
        }
        if (mediaDeviceById == null) {
            Log.w("LocalMediaManager", "connectDevice() connectDevice not in the list!");
            return false;
        }
        if (mediaDeviceById instanceof BluetoothMediaDevice) {
            CachedBluetoothDevice cachedDevice = ((BluetoothMediaDevice) mediaDeviceById).getCachedDevice();
            if (!cachedDevice.isConnected() && !cachedDevice.isBusy()) {
                this.mOnTransferBluetoothDevice = mediaDevice;
                mediaDeviceById.setState(1);
                cachedDevice.connect();
                return true;
            }
        }
        MediaDevice mediaDevice2 = this.mCurrentConnectedDevice;
        if (mediaDeviceById == mediaDevice2) {
            Log.d("LocalMediaManager", "connectDevice() this device all ready connected! : " + mediaDeviceById.getName());
            return false;
        }
        if (mediaDevice2 != null) {
            mediaDevice2.disconnect();
        }
        mediaDeviceById.setState(1);
        if (TextUtils.isEmpty(this.mPackageName)) {
            this.mInfoMediaManager.connectDeviceWithoutPackageName(mediaDeviceById);
        } else {
            mediaDeviceById.connect();
        }
        return true;
    }

    void dispatchDeviceAttributesChanged() {
        Iterator<DeviceCallback> it = getCallbacks().iterator();
        while (it.hasNext()) {
            it.next().onDeviceAttributesChanged();
        }
    }

    void dispatchOnRequestFailed(int i) {
        Iterator<DeviceCallback> it = getCallbacks().iterator();
        while (it.hasNext()) {
            it.next().onRequestFailed(i);
        }
    }

    public MediaDevice getMediaDeviceById(List<MediaDevice> list, String str) {
        for (MediaDevice mediaDevice : list) {
            if (TextUtils.equals(mediaDevice.getId(), str)) {
                return mediaDevice;
            }
        }
        Log.i("LocalMediaManager", "getMediaDeviceById() can't found device");
        return null;
    }

    @VisibleForTesting
    MediaDevice updateCurrentConnectedDevice() {
        synchronized (this.mMediaDevicesLock) {
            MediaDevice mediaDevice = null;
            for (MediaDevice mediaDevice2 : this.mMediaDevices) {
                if (mediaDevice2 instanceof BluetoothMediaDevice) {
                    if (isActiveDevice(((BluetoothMediaDevice) mediaDevice2).getCachedDevice()) && mediaDevice2.isConnected()) {
                        return mediaDevice2;
                    }
                } else if (mediaDevice2 instanceof PhoneMediaDevice) {
                    mediaDevice = mediaDevice2;
                }
            }
            return mediaDevice;
        }
    }

    private boolean isActiveDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        HearingAidProfile hearingAidProfile;
        A2dpProfile a2dpProfile = this.mLocalBluetoothManager.getProfileManager().getA2dpProfile();
        boolean zEquals = a2dpProfile != null ? cachedBluetoothDevice.getDevice().equals(a2dpProfile.getActiveDevice()) : false;
        return zEquals || ((zEquals || (hearingAidProfile = this.mLocalBluetoothManager.getProfileManager().getHearingAidProfile()) == null) ? false : hearingAidProfile.getActiveDevices().contains(cachedBluetoothDevice.getDevice()));
    }

    private Collection<DeviceCallback> getCallbacks() {
        return new CopyOnWriteArrayList(this.mCallbacks);
    }

    @VisibleForTesting
    class DeviceAttributeChangeCallback implements CachedBluetoothDevice.Callback {
        final /* synthetic */ LocalMediaManager this$0;

        @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
        public void onDeviceAttributesChanged() {
            if (this.this$0.mOnTransferBluetoothDevice != null && !((BluetoothMediaDevice) this.this$0.mOnTransferBluetoothDevice).getCachedDevice().isBusy() && !this.this$0.mOnTransferBluetoothDevice.isConnected()) {
                this.this$0.mOnTransferBluetoothDevice.setState(3);
                this.this$0.mOnTransferBluetoothDevice = null;
                this.this$0.dispatchOnRequestFailed(0);
            }
            this.this$0.dispatchDeviceAttributesChanged();
        }
    }
}
