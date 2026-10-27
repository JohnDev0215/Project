package com.ts.dm.lib.devicemanager;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.config.DeviceManagerConfig;
import com.ts.dm.lib.data.DeviceRecord;
import com.ts.dm.lib.devicemanager.ProjectionDeviceManager;
import com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager;
import com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter;
import com.ts.dm.lib.storemanager.IDataBaseOperate;
import com.ts.dm.lib.storemanager.Sqlite3DataBaseOperate;
import com.ts.dm.lib.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import ts.car.dm.fsdk.interfaces.BluetoothDevice;
import ts.car.dm.fsdk.interfaces.IDeviceCapabilityCheckCallback;
import ts.car.dm.fsdk.interfaces.ProjectionDevice;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager implements IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener, ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener, ILastProfileTimeCounter.OnLastProfileTimeListenr {
    private static final int MAX_INDEX_SAME_DEVICE_NAME = 10;
    private static final String TAG = "DeviceManager";
    private Context mContext;
    private Handler mHandler;
    private IBluetoothDeviceManager mIBluetoothDeviceManager;
    public IDataBaseOperate mIDataBaseOperate;
    private ILastProfileTimeCounter mILastProfileTimeCounter;
    private ProjectionDeviceManager mProjectionDeviceManager;
    private CopyOnWriteArraySet<DmDevice> mDeviceSet = new CopyOnWriteArraySet<>();
    private HashSet<OnDeviceChangedListener> mOnDeviceChangedListeners = new HashSet<>();

    public DeviceManager(Context context, int dbVersion) {
        this.mContext = context;
        this.mIDataBaseOperate = new Sqlite3DataBaseOperate(context, dbVersion);
        initHandler();
        ILastProfileTimeCounter iLastProfileTimeCounterNewInstance = ILastProfileTimeCounter.newInstance(context);
        this.mILastProfileTimeCounter = iLastProfileTimeCounterNewInstance;
        iLastProfileTimeCounterNewInstance.addOnLastProfileTimeListener(this);
        IBluetoothDeviceManager iBluetoothDeviceManagerCreateDefaultInstance = IBluetoothDeviceManager.createDefaultInstance(context, this.mHandler.getLooper());
        this.mIBluetoothDeviceManager = iBluetoothDeviceManagerCreateDefaultInstance;
        iBluetoothDeviceManagerCreateDefaultInstance.registerOnBluetoothDeviceStatusChangedListener(this);
        initList();
        ProjectionDeviceManager projectionDeviceManager = new ProjectionDeviceManager(context);
        this.mProjectionDeviceManager = projectionDeviceManager;
        projectionDeviceManager.setOnProjectionDeviceStatusChangedListener(this);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    private void initList() {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$Os6ukOco9sk8aqRHgK91dHwd-jQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initList$0$DeviceManager();
            }
        });
    }

    public /* synthetic */ void lambda$initList$0$DeviceManager() {
        initDeviceList();
        syncBluetoothBondedList();
    }

    private void initDeviceList() {
        List<DeviceRecord> localList = this.mIDataBaseOperate.queryAllDevices();
        if (localList == null || localList.isEmpty()) {
            return;
        }
        for (DeviceRecord record : localList) {
            DmDevice dmDevice = getDeviceByMac(record.getMacAddress());
            if (dmDevice == null) {
                DmDevice dmDevice2 = Utils.createDmDevice(record);
                this.mDeviceSet.add(dmDevice2);
            }
        }
    }

    private void syncBluetoothBondedList() {
        if (!isBluetoothOpen()) {
            return;
        }
        List<BluetoothDevice> bondedList = getBondedDevices();
        ArrayList<BluetoothDevice> list = new ArrayList<>();
        if (bondedList != null) {
            list.addAll(bondedList);
        }
        Iterator<DmDevice> it = new HashSet(this.mDeviceSet).iterator();
        while (true) {
            int i = 2;
            if (!it.hasNext()) {
                break;
            }
            DmDevice dmDevice = it.next();
            BluetoothDevice bluetoothDevice = findBluetoothDevice(list, dmDevice);
            if (bluetoothDevice == null) {
                i = 0;
            }
            updateDmDeviceBondedStatus(dmDevice, bluetoothDevice, i);
        }
        if (!list.isEmpty()) {
            for (BluetoothDevice device : list) {
                if (getDeviceByMac(device.getMacAddress()) == null) {
                    createDmDeviceByBluetooth(device, 2);
                }
            }
        }
    }

    public boolean isBluetoothOpen() {
        return BluetoothAdapter.getDefaultAdapter().getState() == 12;
    }

    private BluetoothDevice findBluetoothDevice(ArrayList<BluetoothDevice> list, DmDevice dmDevice) {
        Iterator<BluetoothDevice> it = list.iterator();
        while (it.hasNext()) {
            BluetoothDevice device = it.next();
            if (device.getMacAddress().equals(dmDevice.getMacAddress())) {
                it.remove();
                return device;
            }
        }
        return null;
    }

    public synchronized void addOnDeviceChangedListener(OnDeviceChangedListener listener) {
        this.mOnDeviceChangedListeners.add(listener);
    }

    public synchronized DmDevice getDeviceById(String deviceId) {
        if (deviceId == null) {
            return null;
        }
        for (DmDevice device : this.mDeviceSet) {
            if (Utils.safeEquals(device.getDeviceUuid(), deviceId)) {
                return device;
            }
            if (Utils.safeEquals(device.getMacAddress(), deviceId)) {
                return device;
            }
            if (Utils.safeEquals(device.getUsbSerialNumber(), deviceId)) {
                return device;
            }
        }
        return null;
    }

    public synchronized DmDevice getDeviceByMac(String mac) {
        if (mac == null) {
            return null;
        }
        for (DmDevice device : this.mDeviceSet) {
            if (Utils.safeEquals(device.getMacAddress(), mac)) {
                return device;
            }
        }
        return null;
    }

    public synchronized DmDevice getDeviceByUsb(String usb) {
        if (usb == null) {
            return null;
        }
        for (DmDevice device : this.mDeviceSet) {
            if (Utils.safeEquals(device.getUsbSerialNumber(), usb)) {
                return device;
            }
        }
        return null;
    }

    public synchronized DmDevice getDevice(ProjectionDevice device) {
        if (device == null) {
            return null;
        }
        for (DmDevice d : this.mDeviceSet) {
            if (Utils.isSameDevice(device, d)) {
                return d;
            }
        }
        return null;
    }

    private synchronized DmDevice getOldestDevice(HashSet<DmDevice> deviceSet) {
        if (deviceSet.isEmpty()) {
            return null;
        }
        DmDevice result = null;
        for (DmDevice device : deviceSet) {
            if (!device.isUsbDevice() && (result == null || result.getLastProfileTimeMillis() > device.getLastProfileTimeMillis())) {
                result = device;
            }
        }
        return result;
    }

    public ProjectionDevice getProjectionDevice(DmDevice device, int type) {
        return this.mProjectionDeviceManager.getProjectionDevice(device, type);
    }

    public ProjectionDevice getProjectionDevice(String deviceId, int type) {
        if (deviceId == null) {
            return null;
        }
        return getProjectionDevice(getDeviceById(deviceId), type);
    }

    private synchronized void saveDevice(DmDevice dmDevice) {
        if (dmDevice == null) {
            return;
        }
        if (DeviceManagerConfig.isEnableIndexOfSameDeviceName()) {
            setIndexOfSameDeviceName(dmDevice);
        }
        this.mDeviceSet.remove(dmDevice);
        this.mDeviceSet.add(dmDevice);
        if (dmDevice.getMacAddress() != null) {
            this.mIDataBaseOperate.saveOrUpdateDeviceRecord(dmDevice);
        }
    }

    private void setIndexOfSameDeviceName(DmDevice dmDevice) {
        if (dmDevice.getFriendlyName() == null || dmDevice.getIndexOfSameDeviceName() != -1) {
            return;
        }
        int index = -1;
        int[] indexNum = new int[10];
        List<DmDevice> deviceList = getDeviceList();
        for (DmDevice device : deviceList) {
            int indexOfSameDeviceName = device.getIndexOfSameDeviceName();
            if (!device.equals(dmDevice) && device.getFriendlyName().equals(dmDevice.getFriendlyName())) {
                int lastIndex = indexOfSameDeviceName - 1;
                if (lastIndex >= 0 && lastIndex < indexNum.length) {
                    indexNum[lastIndex] = 1;
                }
                if (indexOfSameDeviceName > index) {
                    index = indexOfSameDeviceName;
                }
            }
        }
        int index2 = index + 1;
        if (index2 > 10) {
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                }
                if (indexNum[i] != 0) {
                    i++;
                } else {
                    index2 = i + 1;
                    break;
                }
            }
        }
        dmDevice.setIndexOfSameDeviceName(index2);
    }

    public List<DmDevice> getDeviceList() {
        return new ArrayList(this.mDeviceSet);
    }

    public List<DmDevice> getOnlineDeviceList() {
        if (this.mDeviceSet.isEmpty()) {
            return null;
        }
        List<DmDevice> list = new ArrayList<>();
        for (DmDevice device : this.mDeviceSet) {
            if (device != null && device.isProjectionConnected()) {
                list.add(device);
            }
        }
        return list;
    }

    public boolean deleteDevice(DmDevice device) {
        if (device != null && this.mDeviceSet.contains(device)) {
            return deleteDeviceDirectly(device) ? true : true;
        }
        return false;
    }

    private boolean deleteDeviceDirectly(DmDevice device) {
        return checkDmDeviceWillBeRemoved(device);
    }

    private synchronized void deleteDeviceInner(DmDevice dmDevice) {
        if (dmDevice == null) {
            return;
        }
        if (this.mProjectionDeviceManager != null) {
            if (dmDevice.isDeleted()) {
                this.mProjectionDeviceManager.deleteProjectionDeviceFromDb(dmDevice.getDeviceUuid());
            }
            this.mProjectionDeviceManager.deleteProjectionDevice(dmDevice);
            this.mIDataBaseOperate.deleteDevice(dmDevice.getMacAddress());
            dmDevice.setOptAction(2);
            if (this.mDeviceSet.contains(dmDevice)) {
                this.mProjectionDeviceManager.cancelWirelessCarPlay(dmDevice.getMacAddress());
                this.mDeviceSet.remove(dmDevice);
                broadOnDeviceChangedListener(dmDevice);
            }
        }
    }

    public synchronized List<DmDevice> trimToSize(int size) {
        if (this.mDeviceSet.size() < size) {
            return null;
        }
        HashSet<DmDevice> deviceSet = new HashSet<>(this.mDeviceSet);
        List<DmDevice> usbList = findUsbDeviceList(deviceSet);
        if (deviceSet.size() == size) {
            if (usbList.size() > 0) {
                return null;
            }
            return trimToSizeInner(deviceSet, size);
        }
        return trimToSizeInner(deviceSet, usbList.size() > 0 ? usbList.size() + size : size);
    }

    private synchronized List<DmDevice> trimToSizeInner(HashSet<DmDevice> deviceSet, int size) {
        Iterator<DmDevice> iterator = deviceSet.iterator();
        while (iterator.hasNext()) {
            DmDevice device = iterator.next();
            if (device.isDeleted() || device.getOptAction() == 2) {
                iterator.remove();
                if (deviceSet.size() <= size) {
                    return null;
                }
            }
        }
        List<DmDevice> result = new ArrayList<>();
        while (deviceSet.size() > size) {
            DmDevice device2 = getOldestDevice(deviceSet);
            result.add(device2);
            deviceSet.remove(device2);
        }
        return result;
    }

    private List<DmDevice> findUsbDeviceList(HashSet<DmDevice> deviceSet) {
        List<DmDevice> result = new ArrayList<>();
        for (DmDevice device : deviceSet) {
            if (device.getProjectionConnectType() == 1 || device.isUsbDevice()) {
                if (!device.isPaired()) {
                    result.add(device);
                }
            }
        }
        return result;
    }

    public List<BluetoothDevice> getBondedDevices() {
        return this.mIBluetoothDeviceManager.getBondedDevices();
    }

    public int connectProfile(String mac, int type) {
        return this.mIBluetoothDeviceManager.connectProfile(getDeviceByMac(mac), type);
    }

    public int disconnectProfile(String mac, int type) {
        return this.mIBluetoothDeviceManager.disconnectProfile(getDeviceByMac(mac), type);
    }

    public void checkCarplayWirelessCapability(String mac, IDeviceCapabilityCheckCallback callback) {
        this.mProjectionDeviceManager.checkWirelessCarPlayAvailability(mac, callback);
    }

    public boolean cancelWirelessCarPlay(String mac) {
        return this.mProjectionDeviceManager.cancelWirelessCarPlay(mac);
    }

    private boolean checkDmDeviceWillBeRemoved(DmDevice device) {
        if (device == null) {
            return false;
        }
        if (!device.isPaired() && device.isProjectionInvalid()) {
            deleteDeviceInner(device);
            return true;
        }
        if (device.isDeleted() && !device.isDeviceInProjection() && device.isPaired()) {
            removeBond(device);
            return false;
        }
        if (!device.isDeleted() || device.isPaired() || device.isDeviceInProjection()) {
            return false;
        }
        removeBond(device);
        this.mProjectionDeviceManager.deleteProjectionDeviceFromDb(device.getDeviceUuid());
        return false;
    }

    private boolean removeBond(DmDevice device) {
        if (!TextUtils.isEmpty(device.getMacAddress())) {
            return this.mIBluetoothDeviceManager.removeBond(device.getMacAddress());
        }
        return false;
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener
    public void onBluetoothStateChanged(final int state) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$BFrSAw08dp3GvUEL2skmf82D09I
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onBluetoothStateChanged$1$DeviceManager(state);
            }
        });
    }

    public /* synthetic */ void lambda$onBluetoothStateChanged$1$DeviceManager(int state) {
        if (state == 12) {
            syncBluetoothBondedList();
        } else if (state == 10) {
            disableBluetoothDevices();
        }
    }

    private void disableBluetoothDevices() {
        this.mHandler.removeCallbacksAndMessages(null);
        for (DmDevice device : this.mDeviceSet) {
            if (device.isPaired()) {
                device.setHfpState(2);
                device.setA2dpState(2);
                device.setOptAction(0);
                broadOnDeviceChangedListener(device);
            }
        }
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener
    public void onDeviceBondStatusChanged(final BluetoothDevice device, final int bondStatus) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$E_EyA9h1q4X-LfZDugnTrO2AV_c
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceBondStatusChanged$2$DeviceManager(device, bondStatus);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceBondStatusChanged$2$DeviceManager(BluetoothDevice device, int bondStatus) {
        DmDevice dmDevice = getDeviceByMac(device.getMacAddress());
        if (bondStatus == 2) {
            if (dmDevice == null || dmDevice.getOptAction() == 2) {
                createDmDeviceByBluetooth(device, bondStatus);
                return;
            } else {
                updateDmDeviceBondedStatus(dmDevice, device, bondStatus);
                return;
            }
        }
        if (dmDevice != null && bondStatus == 0) {
            updateDmDeviceBondedStatus(dmDevice, device, bondStatus);
        }
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener
    public void onDeviceProfileStatusChanged(final BluetoothDevice device, final int profile) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$4mQIeZ2u5kDFQyvy4Nmx4Q_F5B0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceProfileStatusChanged$3$DeviceManager(device, profile);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceProfileStatusChanged$3$DeviceManager(BluetoothDevice device, int profile) {
        DmDevice dmDevice = getDeviceByMac(device.getMacAddress());
        if (dmDevice != null) {
            updateBluetoothProfileStatus(dmDevice, device);
            broadOnDeviceProfileStatusChanged(device, profile);
        }
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener
    public void onDeviceNameChanged(final BluetoothDevice device) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$pV-sJTOaHgmfaaZioztvTyqu3wM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDeviceNameChanged$4$DeviceManager(device);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceNameChanged$4$DeviceManager(BluetoothDevice device) {
        DmDevice dmDevice = getDeviceByMac(device.getMacAddress());
        if (dmDevice != null) {
            updateBluetoothProfileStatus(dmDevice, device);
        }
    }

    private void createDmDeviceByBluetooth(BluetoothDevice bluetoothDevice, int bondStatus) {
        DmDevice dmDevice = Utils.createDmDevice(bluetoothDevice, this.mILastProfileTimeCounter);
        dmDevice.setOptAction(1);
        saveDevice(dmDevice);
        broadOnDeviceChangedListener(dmDevice);
    }

    private void updateDmDeviceBondedStatus(DmDevice dmDevice, BluetoothDevice bluetoothDevice, int bondStatus) {
        boolean hasChanged = Utils.updateDmDeviceBondStatus(dmDevice, bluetoothDevice, bondStatus, this.mILastProfileTimeCounter);
        if (hasChanged && !checkDmDeviceWillBeRemoved(dmDevice)) {
            dmDevice.setOptAction(0);
            saveDevice(dmDevice);
            broadOnDeviceChangedListener(dmDevice);
        }
    }

    private void updateBluetoothProfileStatus(DmDevice dmDevice, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null && dmDevice != null) {
            boolean hasChanged = Utils.updateBluetoothProfileStatus(dmDevice, bluetoothDevice, this.mILastProfileTimeCounter);
            if (hasChanged && !checkDmDeviceWillBeRemoved(dmDevice)) {
                dmDevice.setOptAction(0);
                saveDevice(dmDevice);
                broadOnDeviceChangedListener(dmDevice);
            }
        }
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void onServiceReady(int projectionType) {
        this.mProjectionDeviceManager.checkProjectionDevice(this.mDeviceSet, projectionType);
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void onProjectionDeviceStatusChanged(ProjectionDevice projectionDevice, int projectionType) {
        checkDeviceStatusChanged(projectionDevice, projectionType);
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void onProjectionDeviceMerged(final String usb, final String mac, final ProjectionDevice mergedDevice, final int projectionType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$lRBHpQ5BOfUw-2fMjz98s4UORgI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProjectionDeviceMerged$5$DeviceManager(usb, mac, mergedDevice, projectionType);
            }
        });
    }

    public /* synthetic */ void lambda$onProjectionDeviceMerged$5$DeviceManager(String usb, String mac, ProjectionDevice mergedDevice, int projectionType) {
        mergeDmDevice(usb, mac, mergedDevice, projectionType);
        this.mProjectionDeviceManager.mergeProjectionDevice(usb, mac, mergedDevice, projectionType);
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void onProjectionDeviceProjectionRequested(final int type, final ProjectionDevice device, final int projectionType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$lDA2uAuD2FXkMFfXVOPk5FvMrJY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProjectionDeviceProjectionRequested$6$DeviceManager(device, type, projectionType);
            }
        });
    }

    public /* synthetic */ void lambda$onProjectionDeviceProjectionRequested$6$DeviceManager(ProjectionDevice device, int type, int projectionType) {
        if (device.getDeviceStatus() == -1 || device.getDeviceStatus() == 0) {
            return;
        }
        DmDevice dmDevice = getDevice(device);
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setConnectType(type);
        connectRequest.setCapabilityType(projectionType);
        connectRequest.setDevice(dmDevice);
        broadOnDeviceProjectionRequested(true, connectRequest);
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void oniPhoneAttachedOverUsb(final boolean supported, final ProjectionDevice device, final int projectionType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$j--OvXcGfwsbxJHUs4YguKTcTyk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$oniPhoneAttachedOverUsb$7$DeviceManager(projectionType, device, supported);
            }
        });
    }

    public /* synthetic */ void lambda$oniPhoneAttachedOverUsb$7$DeviceManager(int projectionType, ProjectionDevice device, boolean supported) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setConnectType(1);
        connectRequest.setCapabilityType(projectionType);
        DmDevice dmDevice = getDevice(device);
        if (dmDevice == null) {
            dmDevice = Utils.createDmDevice(device, projectionType, this.mILastProfileTimeCounter);
        }
        connectRequest.setDevice(dmDevice);
        broadOnDeviceProjectionRequested(supported, connectRequest);
    }

    @Override // com.ts.dm.lib.devicemanager.ProjectionDeviceManager.OnProjectionDeviceStatusChangedListener
    public void onProjectionDeviceDetachedOverUsb(final ProjectionDevice device, final int projectionType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$HwsKUbWLC1ti5EtD6TCeae5AeMg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onProjectionDeviceDetachedOverUsb$8$DeviceManager(device, projectionType);
            }
        });
    }

    public /* synthetic */ void lambda$onProjectionDeviceDetachedOverUsb$8$DeviceManager(ProjectionDevice device, int projectionType) {
        broadOnDeviceDetachedOverUsb(Utils.createDmDevice(device, projectionType, this.mILastProfileTimeCounter));
    }

    @Override // com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter.OnLastProfileTimeListenr
    public void onLastProfileTimeMax() {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$Xn7Bog4wFZllG8f__ajBSrXkpgI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onLastProfileTimeMax$9$DeviceManager();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reorderLastProfileTime, reason: merged with bridge method [inline-methods] */
    public void lambda$onLastProfileTimeMax$9$DeviceManager() {
        ArrayList<DmDevice> list = new ArrayList<>(this.mDeviceSet);
        Collections.sort(list, new Comparator<DmDevice>() { // from class: com.ts.dm.lib.devicemanager.DeviceManager.1
            @Override // java.util.Comparator
            public int compare(DmDevice o1, DmDevice o2) {
                return Long.compare(o1.getLastProfileTimeMillis(), o2.getLastProfileTimeMillis());
            }
        });
        this.mILastProfileTimeCounter.updateLastProfileMax(0);
        for (DmDevice dmDevice : list) {
            dmDevice.setOptAction(0);
            int newLastProfileTime = this.mILastProfileTimeCounter.generateLastProfileTime();
            if (newLastProfileTime != dmDevice.getLastProfileTimeMillis()) {
                dmDevice.setLastProfileTimeMillis(newLastProfileTime);
                saveDevice(dmDevice);
                broadOnDeviceChangedListener(dmDevice);
            }
        }
    }

    public void checkDeviceStatusChanged(final ProjectionDevice device, final int projectionType) {
        this.mHandler.post(new Runnable() { // from class: com.ts.dm.lib.devicemanager.-$$Lambda$DeviceManager$PRsqRIdyqx8JBtE_DD-1vl3AADA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkDeviceStatusChanged$10$DeviceManager(device, projectionType);
            }
        });
    }

    public /* synthetic */ void lambda$checkDeviceStatusChanged$10$DeviceManager(ProjectionDevice device, int projectionType) {
        if (!isDeviceHasChanged(device, projectionType)) {
            return;
        }
        updateDmDeviceByProjection(device, projectionType);
    }

    private boolean isDeviceHasChanged(ProjectionDevice device, int projectionType) {
        if (device == null || projectionType == 0) {
            return false;
        }
        DmDevice dmDevice = getDevice(device);
        if (dmDevice == null) {
            return true;
        }
        if (!TextUtils.isEmpty(device.getDeviceName()) && !device.getDeviceName().equals(dmDevice.getFriendlyName())) {
            return true;
        }
        int newState = Utils.convertProjectionDeviceStatus(device.getDeviceStatus());
        if (dmDevice.checkCapabilityState(projectionType, newState) && device.isUsbDevice() == dmDevice.isUsbDevice() && device.getProjectionConnectType() == dmDevice.getProjectionConnectType()) {
            return false;
        }
        return true;
    }

    private void mergeDmDevice(String usb, String mac, ProjectionDevice mergedDevice, int projectionType) {
        DmDevice usbDevice;
        DmDevice macDevice;
        if (TextUtils.isEmpty(usb) || TextUtils.isEmpty(mac) || (usbDevice = getDeviceByUsb(usb)) == (macDevice = getDeviceByMac(mac))) {
            return;
        }
        if (usbDevice != null && macDevice == null) {
            usbDevice.setMacAddress(mac);
            updateDmDeviceByProjection(mergedDevice, projectionType);
            return;
        }
        if (macDevice != null && usbDevice == null) {
            macDevice.setUsbSerialNumber(usb);
            updateDmDeviceByProjection(mergedDevice, projectionType);
        } else if (usbDevice != null && macDevice != null) {
            if (usbDevice.isDeviceInProjection()) {
                mergeDevices(usbDevice, macDevice, mergedDevice, projectionType);
            } else {
                mergeDevices(macDevice, usbDevice, mergedDevice, projectionType);
            }
        }
    }

    private void mergeDevices(DmDevice mergeDevice, DmDevice removeDevice, ProjectionDevice mergedDevice, int projectionType) {
        removeDeviceInner(removeDevice);
        mergeDevice.setHfpState(Math.max(mergeDevice.getHfpState(), removeDevice.getHfpState()));
        mergeDevice.setA2dpState(Math.max(mergeDevice.getA2dpState(), removeDevice.getA2dpState()));
        mergeDevice.setPaired(mergeDevice.isPaired() || removeDevice.isPaired());
        mergeDevice.setMacAddress(mergedDevice.getMacAddress());
        mergeDevice.setUsbSerialNumber(mergedDevice.getUsbSerialNumber());
        updateDmDeviceByProjection(mergedDevice, projectionType);
    }

    private void removeDeviceInner(DmDevice removeDevice) {
        removeDevice.setOptAction(2);
        this.mDeviceSet.remove(removeDevice);
        this.mProjectionDeviceManager.deleteProjectionDevice(removeDevice);
        broadOnDeviceChangedListener(removeDevice);
    }

    private synchronized DmDevice updateDmDeviceByProjection(ProjectionDevice device, int type) {
        if (device == null || type == 0) {
            return null;
        }
        DmDevice dmDevice = getDevice(device);
        if (dmDevice == null || dmDevice.getOptAction() == 2) {
            dmDevice = Utils.createDmDevice(device, type, this.mILastProfileTimeCounter);
            dmDevice.setOptAction(1);
        } else {
            dmDevice.setOptAction(0);
        }
        dmDevice.setUsbDevice(device.isUsbDevice());
        dmDevice.setProjectionConnectType(device.getProjectionConnectType());
        if (!TextUtils.isEmpty(device.getDeviceName()) && !device.getDeviceName().equals(dmDevice.getFriendlyName())) {
            dmDevice.setFriendlyName(device.getDeviceName());
        }
        int newState = Utils.convertProjectionDeviceStatus(device.getDeviceStatus());
        if (newState == 4) {
            dmDevice.setLastProfileTimeMillis(this.mILastProfileTimeCounter.generateLastProfileTime());
        }
        dmDevice.setCapabilityState(type, newState);
        if (!checkDmDeviceWillBeRemoved(dmDevice)) {
            this.mProjectionDeviceManager.saveProjectionDevice(device, type);
            saveDevice(dmDevice);
            broadOnDeviceChangedListener(dmDevice);
        }
        return dmDevice;
    }

    private synchronized void broadOnDeviceChangedListener(DmDevice device) {
        if (device != null) {
            if (this.mOnDeviceChangedListeners != null) {
                this.mIBluetoothDeviceManager.checkProfileConnectStatus(device);
                for (OnDeviceChangedListener listener : this.mOnDeviceChangedListeners) {
                    listener.onDeviceStatusChanged(device);
                }
            }
        }
    }

    private synchronized void broadOnDeviceProjectionRequested(boolean supported, ConnectRequest request) {
        if (request != null) {
            if (this.mOnDeviceChangedListeners != null) {
                for (OnDeviceChangedListener listener : this.mOnDeviceChangedListeners) {
                    listener.onDeviceProjectionRequested(supported, request);
                }
            }
        }
    }

    private synchronized void broadOnDeviceDetachedOverUsb(DmDevice device) {
        if (device != null) {
            if (this.mOnDeviceChangedListeners != null) {
                for (OnDeviceChangedListener listener : this.mOnDeviceChangedListeners) {
                    listener.onDeviceDetachedOverUsb(device);
                }
            }
        }
    }

    private synchronized void broadOnDeviceProfileStatusChanged(BluetoothDevice device, int profile) {
        if (device != null) {
            if (this.mOnDeviceChangedListeners != null) {
                for (OnDeviceChangedListener listener : this.mOnDeviceChangedListeners) {
                    listener.onDeviceProfileStatusChanged(device, profile);
                }
            }
        }
    }

    public interface OnDeviceChangedListener {
        void onDeviceStatusChanged(DmDevice dmDevice);

        default void onDeviceProjectionRequested(boolean supported, ConnectRequest request) {
        }

        default void onDeviceDetachedOverUsb(DmDevice device) {
        }

        default void onDeviceProfileStatusChanged(BluetoothDevice device, int profile) {
        }
    }
}
