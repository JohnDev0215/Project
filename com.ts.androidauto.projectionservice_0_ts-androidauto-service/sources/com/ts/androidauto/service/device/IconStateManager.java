package com.ts.androidauto.service.device;

import android.content.Context;
import android.os.Handler;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.service.common.DeviceConnectionManagerModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public class IconStateManager {
    private static final int ANDROID_AUTO_CAPABILITY = 1152;
    private static final String TAG = "AAPIconStateManager";
    private static IconStateManager mIconStateManagerInstance;
    private DeviceConnectionManagerModel.Listener mDcmListener;
    private List<Listener> mListenerList = new Vector();
    private LinkedHashMap<String, Integer> mAvailableDevices = new LinkedHashMap<>();
    private Handler mHandler = new Handler();

    public interface Listener {
        default void onLastAvailableDeviceChanged(String deviceId, int capability) {
        }
    }

    public static synchronized IconStateManager getInstance() {
        if (mIconStateManagerInstance == null) {
            mIconStateManagerInstance = new IconStateManager();
        }
        return mIconStateManagerInstance;
    }

    private IconStateManager() {
    }

    public void init(Context context) {
        if (context != null) {
            DeviceConnectionManagerModel.getInstance().init(context);
            this.mDcmListener = new AnonymousClass1();
            DeviceConnectionManagerModel.getInstance().registerListener(this.mDcmListener);
            HashMap<String, ConnectionDevice> maps = DeviceConnectionManagerModel.getInstance().getDeviceList();
            updateAvailableDeviceList(maps);
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.service.device.IconStateManager$1, reason: invalid class name */
    class AnonymousClass1 implements DeviceConnectionManagerModel.Listener {
        AnonymousClass1() {
        }

        @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
        public void onDeviceStatusChanged(final ConnectionDevice device) {
            LogUtil.info(IconStateManager.TAG, "[onDeviceStatusChanged] device : " + device);
            IconStateManager.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.device.-$$Lambda$IconStateManager$1$SOIIz6stT9PS5kAhCyfZOQf-8Ao
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeviceStatusChanged$0$IconStateManager$1(device);
                }
            });
        }

        public /* synthetic */ void lambda$onDeviceStatusChanged$0$IconStateManager$1(ConnectionDevice device) {
            IconStateManager.this.updateAvailableDevices(device);
        }

        @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
        public void onDevicesMerged(final String oneDevId, String anotherDevId, ConnectionDevice device) {
            LogUtil.debug(IconStateManager.TAG, "[onDevicesMerged] oneDevId : " + LogEncrypt.MacAddressEncrypt(oneDevId) + " anotherDevId: " + LogEncrypt.MacAddressEncrypt(anotherDevId) + " device:" + device);
            IconStateManager.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.device.-$$Lambda$IconStateManager$1$pVoIXMF2VP5Zn_58KW9nxGx_3wY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDevicesMerged$1$IconStateManager$1(oneDevId);
                }
            });
        }

        public /* synthetic */ void lambda$onDevicesMerged$1$IconStateManager$1(String oneDevId) {
            IconStateManager.this.deleteOldMergedDevice(oneDevId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAvailableDevices(ConnectionDevice device) {
        LinkedHashMap<String, Integer> linkedHashMap;
        if (device != null && (linkedHashMap = this.mAvailableDevices) != null) {
            if (linkedHashMap.containsKey(device.getDeviceId())) {
                if (!device.isAvailableCapability(1024) && !device.isAvailableCapability(128)) {
                    this.mAvailableDevices.remove(device.getDeviceId());
                    onLastAvailableDeviceChanged();
                    return;
                } else {
                    if ((device.getAvailableCapabilitys() & ANDROID_AUTO_CAPABILITY) != (this.mAvailableDevices.get(device.getDeviceId()).intValue() & ANDROID_AUTO_CAPABILITY)) {
                        this.mAvailableDevices.remove(device.getDeviceId());
                        this.mAvailableDevices.put(device.getDeviceId(), Integer.valueOf(device.getAvailableCapabilitys()));
                        onLastAvailableDeviceChanged();
                        return;
                    }
                    return;
                }
            }
            if (device.isAvailableCapability(1024) || device.isAvailableCapability(128)) {
                this.mAvailableDevices.put(device.getDeviceId(), Integer.valueOf(device.getAvailableCapabilitys()));
                onLastAvailableDeviceChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOldMergedDevice(String deviceId) {
        LinkedHashMap<String, Integer> linkedHashMap = this.mAvailableDevices;
        if (linkedHashMap != null && deviceId != null && linkedHashMap.containsKey(deviceId)) {
            this.mAvailableDevices.remove(deviceId);
            onLastAvailableDeviceChanged();
        }
    }

    private void updateAvailableDeviceList(HashMap<String, ConnectionDevice> maps) {
        if (maps != null && this.mAvailableDevices != null) {
            for (ConnectionDevice device : maps.values()) {
                if (device != null && (device.isAvailableCapability(1024) || device.isAvailableCapability(128))) {
                    this.mAvailableDevices.put(device.getDeviceId(), Integer.valueOf(device.getAvailableCapabilitys()));
                }
            }
            onLastAvailableDeviceChanged();
        }
    }

    private <K, V> Map.Entry<K, V> getLastAvailableDevice(LinkedHashMap<K, V> map) {
        Map.Entry<K, V> lastAvailableDevice = null;
        for (Map.Entry<K, V> lastAvailableDevice2 : map.entrySet()) {
            lastAvailableDevice = lastAvailableDevice2;
        }
        return lastAvailableDevice;
    }

    public String getLastAvailableDevice() {
        LinkedHashMap<String, Integer> linkedHashMap = this.mAvailableDevices;
        if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
            return (String) getLastAvailableDevice(this.mAvailableDevices).getKey();
        }
        return null;
    }

    public int getLastAvailableCapability(String deviceId) {
        LogUtil.info(TAG, "getLastAvailableCapability deviceId: " + deviceId);
        int capability = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_NULL.ordinal();
        LinkedHashMap<String, Integer> linkedHashMap = this.mAvailableDevices;
        if (linkedHashMap != null && !linkedHashMap.isEmpty() && deviceId.equals(getLastAvailableDevice(this.mAvailableDevices).getKey())) {
            int dcmCapability = ((Integer) getLastAvailableDevice(this.mAvailableDevices).getValue()).intValue();
            return convertDcmCapability(dcmCapability);
        }
        return capability;
    }

    public ArrayList<String> getAvailableDevices() {
        ArrayList<String> availableDeviceList = new ArrayList<>();
        LinkedHashMap<String, Integer> linkedHashMap = this.mAvailableDevices;
        if (linkedHashMap != null && !linkedHashMap.isEmpty()) {
            Iterator<Map.Entry<String, Integer>> iterator = this.mAvailableDevices.entrySet().iterator();
            while (iterator.hasNext()) {
                availableDeviceList.add(iterator.next().getKey());
            }
        }
        return availableDeviceList;
    }

    private void onLastAvailableDeviceChanged() {
        synchronized (this.mListenerList) {
            if (this.mListenerList != null) {
                for (Listener listener : this.mListenerList) {
                    if (this.mAvailableDevices != null && !this.mAvailableDevices.isEmpty()) {
                        int dcmCapability = ((Integer) getLastAvailableDevice(this.mAvailableDevices).getValue()).intValue();
                        int capability = convertDcmCapability(dcmCapability);
                        listener.onLastAvailableDeviceChanged((String) getLastAvailableDevice(this.mAvailableDevices).getKey(), capability);
                    } else {
                        listener.onLastAvailableDeviceChanged(null, 0);
                    }
                }
            }
        }
    }

    public void registerListener(Listener listener) {
        synchronized (this.mListenerList) {
            if (this.mListenerList != null && listener != null && !this.mListenerList.contains(listener)) {
                this.mListenerList.add(listener);
            }
        }
    }

    public int convertDcmCapability(int dcmCapability) {
        int capability = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_NULL.ordinal();
        LogUtil.info(TAG, "convertDcmCapability dcmCapability: " + dcmCapability);
        if ((dcmCapability & 128) > 0) {
            capability |= AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
        }
        if ((dcmCapability & 1024) > 0) {
            capability |= AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal();
        }
        LogUtil.info(TAG, "convertDcmCapability capability: " + capability);
        return capability;
    }
}
