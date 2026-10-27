package com.ts.dm.lib.devicemanager;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ts.car.core.FoundationProvider;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.config.DeviceManagerConfig;
import com.ts.dm.lib.util.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import ts.car.dm.fsdk.interfaces.IDeviceCapabilityCheckCallback;
import ts.car.dm.fsdk.interfaces.IProjectionAndroidAutoManager;
import ts.car.dm.fsdk.interfaces.IProjectionCarPlayManager;
import ts.car.dm.fsdk.interfaces.IProjectionManagerListener;
import ts.car.dm.fsdk.interfaces.ProjectionDevice;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionDeviceManager {
    private static final String TAG = "ProjectionDeviceManager";
    private IProjectionManagerListenerImpl mAndroidAutoListener;
    private IProjectionManagerListenerImpl mCarPlayListener;
    private Context mContext;
    private IProjectionAndroidAutoManager mIProjectionAndroidAutoManager;
    private IProjectionCarPlayManager mIProjectionCarPlayManager;
    private OnProjectionDeviceStatusChangedListener mOnProjectionDeviceStatusChangedListener;
    private SparseArray<List<ProjectionDevice>> mProjectionDeviceSet = new SparseArray<>(2);
    private volatile boolean isAndroidAutoReady = false;
    private volatile boolean isCarPlayReady = false;

    public interface OnProjectionDeviceStatusChangedListener {
        void onProjectionDeviceDetachedOverUsb(ProjectionDevice projectionDevice, int i);

        void onProjectionDeviceMerged(String str, String str2, ProjectionDevice projectionDevice, int i);

        void onProjectionDeviceProjectionRequested(int i, ProjectionDevice projectionDevice, int i2);

        void onProjectionDeviceStatusChanged(ProjectionDevice projectionDevice, int i);

        void onServiceReady(int i);

        void oniPhoneAttachedOverUsb(boolean z, ProjectionDevice projectionDevice, int i);
    }

    public ProjectionDeviceManager(Context context) {
        this.mContext = context;
        initProjectionManager();
    }

    private void initProjectionManager() {
        if (DeviceManagerConfig.isAndroidAutoEnable()) {
            IProjectionManagerListenerImpl iProjectionManagerListenerImpl = new IProjectionManagerListenerImpl(2);
            this.mAndroidAutoListener = iProjectionManagerListenerImpl;
            this.mIProjectionAndroidAutoManager = (IProjectionAndroidAutoManager) FoundationProvider.create(IProjectionAndroidAutoManager.class, this.mContext, iProjectionManagerListenerImpl);
        }
        if (DeviceManagerConfig.isCarPlayEnable()) {
            IProjectionManagerListenerImpl iProjectionManagerListenerImpl2 = new IProjectionManagerListenerImpl(1);
            this.mCarPlayListener = iProjectionManagerListenerImpl2;
            this.mIProjectionCarPlayManager = (IProjectionCarPlayManager) FoundationProvider.create(IProjectionCarPlayManager.class, this.mContext, iProjectionManagerListenerImpl2);
        }
    }

    public void setOnProjectionDeviceStatusChangedListener(OnProjectionDeviceStatusChangedListener listener) {
        this.mOnProjectionDeviceStatusChangedListener = listener;
    }

    public void checkWirelessCarPlayAvailability(String mac, IDeviceCapabilityCheckCallback callback) {
        if (this.mIProjectionCarPlayManager == null || !this.isCarPlayReady) {
            callback.onCheckWirelessCarPlayCapability(false);
        } else {
            this.mIProjectionCarPlayManager.checkWirelessCarPlayAvailability(mac, callback);
        }
    }

    public synchronized void saveProjectionDevice(ProjectionDevice device, int type) {
        if (device == null || type == 0) {
            return;
        }
        List<ProjectionDevice> list = this.mProjectionDeviceSet.get(type);
        if (list == null) {
            list = new ArrayList();
            this.mProjectionDeviceSet.put(type, list);
        }
        Iterator<ProjectionDevice> it = list.iterator();
        while (it.hasNext()) {
            ProjectionDevice projectionDevice = it.next();
            if (Utils.safeEquals(projectionDevice.getMacAddress(), device.getMacAddress()) || Utils.safeEquals(projectionDevice.getUsbSerialNumber(), device.getUsbSerialNumber())) {
                it.remove();
            }
        }
        list.add(device);
    }

    public synchronized ProjectionDevice getProjectionDevice(DmDevice device, int type) {
        if (device == null) {
            return null;
        }
        List<ProjectionDevice> list = this.mProjectionDeviceSet.get(type);
        if (list != null && !list.isEmpty()) {
            for (ProjectionDevice p : list) {
                if (isSameDevice(p, device)) {
                    return p;
                }
            }
        }
        if (type == 2) {
            return checkAndroidAutoProjectionStatus(device);
        }
        if (type != 1) {
            return null;
        }
        return checkCarPlayProjectionStatus(device);
    }

    private boolean isSameDevice(ProjectionDevice projectionDevice, DmDevice dmDevice) {
        return Utils.safeEquals(projectionDevice.getMacAddress(), dmDevice.getMacAddress()) || Utils.safeEquals(projectionDevice.getUsbSerialNumber(), dmDevice.getUsbSerialNumber()) || Utils.safeEquals(projectionDevice.getMacAddress(), dmDevice.getDeviceUuid()) || Utils.safeEquals(projectionDevice.getUsbSerialNumber(), dmDevice.getDeviceUuid());
    }

    private ProjectionDevice checkAndroidAutoProjectionStatus(DmDevice device) {
        if (this.isAndroidAutoReady) {
            ProjectionDevice androidAutoDevice = this.mIProjectionAndroidAutoManager.getProjectionDevice(device.getMacAddress());
            if (androidAutoDevice != null) {
                saveProjectionDevice(androidAutoDevice, 2);
                device.setAndroidAutoProjectionState(Utils.convertProjectionDeviceStatus(androidAutoDevice.getDeviceStatus()));
            }
            return androidAutoDevice;
        }
        return null;
    }

    private ProjectionDevice checkCarPlayProjectionStatus(DmDevice device) {
        if (this.isCarPlayReady) {
            ProjectionDevice carplayDevice = this.mIProjectionCarPlayManager.getProjectionDevice(device.getMacAddress());
            if (carplayDevice != null) {
                saveProjectionDevice(carplayDevice, 1);
                device.setCarPlayProjectionState(Utils.convertProjectionDeviceStatus(carplayDevice.getDeviceStatus()));
            }
            return carplayDevice;
        }
        return null;
    }

    public synchronized void deleteProjectionDevice(DmDevice dmDevice) {
        if (dmDevice == null) {
            return;
        }
        int size = this.mProjectionDeviceSet.size();
        for (int i = 0; i < size; i++) {
            List<ProjectionDevice> list = this.mProjectionDeviceSet.valueAt(i);
            if (list != null) {
                Iterator<ProjectionDevice> it = list.iterator();
                while (it.hasNext()) {
                    ProjectionDevice device = it.next();
                    if (isSameDevice(device, dmDevice)) {
                        it.remove();
                    }
                }
            }
        }
    }

    private synchronized void deleteProjectionDeviceByMac(String mac, int type) {
        if (mac == null || type == 0) {
            return;
        }
        List<ProjectionDevice> list = this.mProjectionDeviceSet.get(type);
        if (list == null) {
            return;
        }
        Iterator<ProjectionDevice> it = list.iterator();
        while (it.hasNext()) {
            ProjectionDevice device = it.next();
            if (Utils.safeEquals(device.getMacAddress(), mac)) {
                it.remove();
            }
        }
    }

    private synchronized void deleteProjectionDeviceByUsb(String usb, int type) {
        if (usb == null || type == 0) {
            return;
        }
        List<ProjectionDevice> list = this.mProjectionDeviceSet.get(type);
        if (list == null) {
            return;
        }
        Iterator<ProjectionDevice> it = list.iterator();
        while (it.hasNext()) {
            ProjectionDevice device = it.next();
            if (Utils.safeEquals(device.getUsbSerialNumber(), usb)) {
                it.remove();
            }
        }
    }

    public void deleteProjectionDeviceFromDb(String deviceId) {
        if (this.isAndroidAutoReady) {
            this.mIProjectionAndroidAutoManager.deleteProjection(deviceId);
        }
        if (this.isCarPlayReady) {
            this.mIProjectionCarPlayManager.deleteProjection(deviceId);
        }
    }

    public boolean cancelWirelessCarPlay(String mac) {
        if (this.isCarPlayReady && !TextUtils.isEmpty(mac)) {
            return this.mIProjectionCarPlayManager.cancelWirelessCarPlay(mac);
        }
        return false;
    }

    public void mergeProjectionDevice(String usb, String mac, ProjectionDevice mergedDevice, int projectionType) {
        deleteProjectionDeviceByUsb(usb, projectionType);
        deleteProjectionDeviceByMac(mac, projectionType);
        saveProjectionDevice(mergedDevice, projectionType);
    }

    public void checkProjectionDevice(Set<DmDevice> devices, int projectionType) {
        if (projectionType == 1) {
            checkCarPlayDevice(devices);
        } else if (projectionType == 2) {
            checkAndroidAutoDevice(devices);
        }
    }

    private void checkAndroidAutoDevice(Set<DmDevice> devices) {
        devices.forEach(new Consumer() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$ProjectionDeviceManager$2TI93_pq2R33zI99ppRXWX0exO8
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$checkAndroidAutoDevice$0$ProjectionDeviceManager((DmDevice) obj);
            }
        });
        List<ProjectionDevice> usbList = this.mIProjectionAndroidAutoManager.getUsbDeviceList();
        if (usbList != null) {
            usbList.forEach(new Consumer() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$ProjectionDeviceManager$Rh71sxPP4lnTa1ovxbR9JIiTXg4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$checkAndroidAutoDevice$1$ProjectionDeviceManager((ProjectionDevice) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$checkAndroidAutoDevice$0$ProjectionDeviceManager(DmDevice device) {
        ProjectionDevice projectionDevice = checkAndroidAutoProjectionStatus(device);
        if (projectionDevice != null) {
            this.mAndroidAutoListener.onDeviceStatusChanged(projectionDevice);
        }
    }

    public /* synthetic */ void lambda$checkAndroidAutoDevice$1$ProjectionDeviceManager(ProjectionDevice device) {
        if (device != null) {
            this.mAndroidAutoListener.onDeviceStatusChanged(device);
            this.mAndroidAutoListener.onDeviceProjectionRequested(1, device);
        }
    }

    private void checkCarPlayDevice(Set<DmDevice> devices) {
        devices.forEach(new Consumer() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$ProjectionDeviceManager$L88u-PoHVeqADUriuLdXO3QPa2Q
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$checkCarPlayDevice$2$ProjectionDeviceManager((DmDevice) obj);
            }
        });
        List<ProjectionDevice> usbList = this.mIProjectionCarPlayManager.getUsbDeviceList();
        if (usbList != null) {
            usbList.forEach(new Consumer() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$ProjectionDeviceManager$aghoDJ9GIs7y7QjOVQFMHrjPXB8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$checkCarPlayDevice$3$ProjectionDeviceManager((ProjectionDevice) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$checkCarPlayDevice$2$ProjectionDeviceManager(DmDevice device) {
        ProjectionDevice projectionDevice = checkCarPlayProjectionStatus(device);
        if (projectionDevice != null) {
            this.mCarPlayListener.onDeviceStatusChanged(projectionDevice);
        }
    }

    public /* synthetic */ void lambda$checkCarPlayDevice$3$ProjectionDeviceManager(ProjectionDevice device) {
        if (device != null) {
            this.mCarPlayListener.onDeviceStatusChanged(device);
            this.mCarPlayListener.onDeviceProjectionRequested(1, device);
        }
    }

    public List<ProjectionDevice> getUsbDeviceList() {
        ArrayList<ProjectionDevice> result = new ArrayList<>();
        List<ProjectionDevice> androidAutoUsbList = this.mIProjectionAndroidAutoManager.getUsbDeviceList();
        if (androidAutoUsbList != null && androidAutoUsbList.size() > 0) {
            result.addAll(androidAutoUsbList);
        }
        List<ProjectionDevice> carPlayUsbList = this.mIProjectionCarPlayManager.getUsbDeviceList();
        if (carPlayUsbList != null && carPlayUsbList.size() > 0) {
            result.addAll(carPlayUsbList);
        }
        return result;
    }

    private class IProjectionManagerListenerImpl implements IProjectionManagerListener {
        private int mProjectionType;

        IProjectionManagerListenerImpl(int projectionType) {
            this.mProjectionType = 0;
            this.mProjectionType = projectionType;
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onServiceConnected() {
            int i = this.mProjectionType;
            if (i == 1) {
                if (!ProjectionDeviceManager.this.isCarPlayReady) {
                    updateDeviceCarPlayCapability();
                }
            } else if (i == 2 && !ProjectionDeviceManager.this.isAndroidAutoReady) {
                updateDeviceAndroidAutoCapability();
            }
        }

        private synchronized void updateDeviceCarPlayCapability() {
            ProjectionDeviceManager.this.isCarPlayReady = true;
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onServiceReady(1);
            }
        }

        private synchronized void updateDeviceAndroidAutoCapability() {
            ProjectionDeviceManager.this.isAndroidAutoReady = true;
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onServiceReady(2);
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onServiceDisconnected() {
            int i = this.mProjectionType;
            if (i == 1) {
                ProjectionDeviceManager.this.isCarPlayReady = false;
            } else if (i == 2) {
                ProjectionDeviceManager.this.isAndroidAutoReady = false;
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onDeviceStatusChanged(ProjectionDevice device) {
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onProjectionDeviceStatusChanged(device, this.mProjectionType);
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onDeviceMerged(String usb, String mac, ProjectionDevice mergedDevice) {
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onProjectionDeviceMerged(usb, mac, mergedDevice, this.mProjectionType);
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onDeviceProjectionRequested(int type, ProjectionDevice device) {
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onProjectionDeviceProjectionRequested(type, device, this.mProjectionType);
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void oniPhoneAttachedOverUsb(boolean supported, ProjectionDevice device) {
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.oniPhoneAttachedOverUsb(supported, device, this.mProjectionType);
            }
        }

        @Override // ts.car.dm.fsdk.interfaces.IProjectionManagerListener
        public void onDeviceDetachedOverUsb(ProjectionDevice device) {
            if (ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener != null) {
                ProjectionDeviceManager.this.mOnProjectionDeviceStatusChangedListener.onProjectionDeviceDetachedOverUsb(device, this.mProjectionType);
            }
        }
    }
}
