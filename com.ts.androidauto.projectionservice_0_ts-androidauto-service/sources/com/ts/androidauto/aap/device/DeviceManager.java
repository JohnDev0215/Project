package com.ts.androidauto.aap.device;

import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.ts.androidauto.aap.common.DeviceStatus;
import com.ts.androidauto.aap.common.StatusConst;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManager {
    private static final String TAG = "AAPDeviceManager";
    private static DeviceManager mDeviceManagerInstance;
    private ConcurrentHashMap<AndroidAutoDevice, DeviceStatus> mDeviceStatusList;

    public static synchronized DeviceManager getInstance() {
        if (mDeviceManagerInstance == null) {
            mDeviceManagerInstance = new DeviceManager();
        }
        return mDeviceManagerInstance;
    }

    private DeviceManager() {
    }

    public void init() {
        this.mDeviceStatusList = new ConcurrentHashMap<>();
    }

    public synchronized boolean addDevice(AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "addDevice linkDevice:" + linkDevice);
        if (linkDevice == null) {
            LogUtil.info(TAG, "addDevice failed for null linkDevice.");
            return false;
        }
        if (this.mDeviceStatusList != null && this.mDeviceStatusList.size() > 0) {
            for (AndroidAutoDevice tmpDevice : this.mDeviceStatusList.keySet()) {
                if (linkDevice == tmpDevice) {
                    LogUtil.info(TAG, "addDevice failed for same linkDevice.");
                    return false;
                }
            }
        }
        boolean isAdded = false;
        AndroidAutoDevice targetDevice = findDevice(linkDevice);
        if (targetDevice == null) {
            if (this.mDeviceStatusList != null) {
                isAdded = true;
                LogUtil.info(TAG, "addDevice success.");
                DeviceStatus deviceStatus = new DeviceStatus();
                deviceStatus.setLinkStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_INIT);
                deviceStatus.setAoaStatus(StatusConst.AoaStatus.AOA_STATUS_INIT);
                this.mDeviceStatusList.put(linkDevice, deviceStatus);
            } else {
                LogUtil.info(TAG, "addDevice failed for mDeviceList is null.");
            }
        } else {
            LogUtil.info(TAG, "addDevice failed for same linkDevice. update property");
            linkDevice.setBluetoothDevice(targetDevice.getBluetoothDevice());
            if (targetDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber() && linkDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber()) {
                linkDevice.setCurrentMode(targetDevice.getCurrentMode());
            }
            if (targetDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                linkDevice.setUsbSerialNumber(targetDevice.getUsbSerialNumber());
            }
            if (linkDevice.getDeviceId() == null) {
                linkDevice.setDeviceId(targetDevice.getDeviceId());
            }
            linkDevice.setErrorCode(targetDevice.getErrorCode());
            targetDevice.copy(linkDevice);
        }
        LogUtil.info(TAG, "addDevice finish. isAdded:" + isAdded);
        return isAdded;
    }

    private AndroidAutoDevice findDevice(AndroidAutoDevice inDevice) {
        if (inDevice == null) {
            LogUtil.info(TAG, "findDevice failed for null Device.");
            return null;
        }
        ConcurrentHashMap<AndroidAutoDevice, DeviceStatus> concurrentHashMap = this.mDeviceStatusList;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
            return null;
        }
        for (AndroidAutoDevice tmpDevice : this.mDeviceStatusList.keySet()) {
            if (inDevice == tmpDevice) {
                LogUtil.info(TAG, "findDevice, found same Device.");
                return tmpDevice;
            }
        }
        for (AndroidAutoDevice tmpDevice2 : this.mDeviceStatusList.keySet()) {
            if (tmpDevice2 != null && inDevice.getLinkType() == tmpDevice2.getLinkType()) {
                if (inDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
                    if (tmpDevice2.getDeviceId() != null && tmpDevice2.getDeviceId().equals(inDevice.getDeviceId())) {
                        LogUtil.info(TAG, "findDevice, found wire Device.");
                        return tmpDevice2;
                    }
                } else {
                    if (inDevice.getLinkType() != AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
                        return null;
                    }
                    if (tmpDevice2.getBtAddr() != null && tmpDevice2.getBtAddr().equals(inDevice.getBtAddr())) {
                        LogUtil.info(TAG, "findDevice, found wireless Device.");
                        return tmpDevice2;
                    }
                }
            }
        }
        return null;
    }

    public synchronized AndroidAutoDevice getTargetDevice(AndroidAutoDevice linkDevice) {
        LogUtil.info(TAG, "getTargetDevice linkDevice:" + linkDevice);
        if (linkDevice == null) {
            LogUtil.info(TAG, "getTargetDevice failed. input param is null.");
            return null;
        }
        return findDevice(linkDevice);
    }

    private DeviceStatus getDeviceStatus(AndroidAutoDevice linkDevice) {
        AndroidAutoDevice targetDevice = getTargetDevice(linkDevice);
        if (targetDevice == null) {
            return null;
        }
        DeviceStatus devicestatus = this.mDeviceStatusList.get(targetDevice);
        return devicestatus;
    }

    public synchronized AndroidAutoConst.LinkStatus getDeviceMirrorStatus(AndroidAutoDevice device) {
        AndroidAutoConst.LinkStatus status;
        DeviceStatus deviceStatus = getDeviceStatus(device);
        status = AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED;
        if (deviceStatus != null) {
            status = deviceStatus.getLinkStatus();
        }
        LogUtil.info(TAG, "getDeviceMirrorStatus status:" + status);
        return status;
    }

    public synchronized void updateBluetoothDevice(AndroidAutoDevice linkDevice, BluetoothDevice btDev) {
        LogUtil.info(TAG, "updateBluetoothDevice " + linkDevice);
        AndroidAutoDevice targetDevice = findDevice(linkDevice);
        if (targetDevice != null) {
            targetDevice.setBluetoothDevice(btDev);
            if (btDev == null && targetDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber()) {
                removeDevice(targetDevice);
            }
        }
    }

    private void removeDevice(AndroidAutoDevice linkDevice) {
        AndroidAutoDevice targetDevice = findDevice(linkDevice);
        if (targetDevice != null && this.mDeviceStatusList != null) {
            LogUtil.info(TAG, "removeDevice " + targetDevice);
            this.mDeviceStatusList.remove(targetDevice);
        }
    }

    private void updateDeviceStatus(AndroidAutoDevice linkDevice, DeviceStatus deviceStatus) {
        LogUtil.info(TAG, "updateDeviceStatus " + linkDevice);
        if (this.mDeviceStatusList != null && linkDevice != null) {
            AndroidAutoDevice targetDevice = findDevice(linkDevice);
            LogUtil.info(TAG, "currtarget:" + targetDevice);
            if (targetDevice != null) {
                DeviceStatus currStatus = this.mDeviceStatusList.get(targetDevice);
                AndroidAutoConst.LinkStatus linkStatus = null;
                StatusConst.AoaStatus aoaStatus = null;
                if (deviceStatus != null) {
                    linkStatus = deviceStatus.getLinkStatus();
                    aoaStatus = deviceStatus.getAoaStatus();
                }
                LogUtil.info(TAG, "updateDeviceStatus linkStatus:" + linkStatus);
                setDeviceStatus(currStatus, deviceStatus);
                if (aoaStatus != null) {
                    currStatus.setAoaStatus(aoaStatus);
                }
                targetDevice.setStatus(linkDevice.getStatus());
                if (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED) {
                    if (targetDevice.getLinkType() == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal() && targetDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber() && targetDevice.getBluetoothDevice() != null) {
                        targetDevice.setCurrentMode(AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber());
                        return;
                    } else {
                        if (targetDevice.getLinkType() != AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal() || targetDevice.getCurrentMode() != AndroidAutoConst.LinkMode.LINK_MODE_RFCOMM.toNumber() || targetDevice.getBluetoothDevice() == null) {
                            removeDevice(targetDevice);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            LogUtil.info(TAG, "mDeviceStatusList not found deivce:" + linkDevice);
        }
    }

    public synchronized void updateDeviceStatus(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, StatusConst.AoaStatus aoaStatus) {
        LogUtil.info(TAG, "updateDeviceStatus 3 paramters------------");
        if (linkDevice != null) {
            DeviceStatus deviceStatus = new DeviceStatus();
            if (linkStatus != null) {
                deviceStatus.setLinkStatus(linkStatus);
            } else {
                AndroidAutoDevice targetDevice = getTargetDevice(linkDevice);
                LogUtil.info(TAG, "updateDeviceStatus targetDevice:" + targetDevice);
                if (targetDevice != null) {
                    deviceStatus.setLinkStatus(AndroidAutoConst.LinkStatus.values()[targetDevice.getStatus()]);
                }
            }
            if (aoaStatus != null) {
                deviceStatus.setAoaStatus(aoaStatus);
            }
            updateDeviceStatus(linkDevice, deviceStatus);
        }
    }

    public boolean isLegalStatus(AndroidAutoConst.LinkStatus currLinkStatus, AndroidAutoConst.LinkStatus newLinkStatus) {
        boolean isLegalStatus = false;
        if (currLinkStatus == newLinkStatus) {
            isLegalStatus = true;
        } else {
            switch (AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[currLinkStatus.ordinal()]) {
                case 1:
                    isLegalStatus = isLegalStateAtInit(newLinkStatus);
                    break;
                case 2:
                    isLegalStatus = isLegalStateAtAvailable(newLinkStatus);
                    break;
                case 3:
                    isLegalStatus = isLegalStateAtActivating(newLinkStatus);
                    break;
                case 4:
                    isLegalStatus = isLegalStateAtActivated(newLinkStatus);
                    break;
                case 5:
                    isLegalStatus = isLegalStateAtDeactivating(newLinkStatus);
                    break;
                case 6:
                case 7:
                case 8:
                    isLegalStatus = isLegalStateAtDeactivated(newLinkStatus);
                    break;
                case 9:
                    isLegalStatus = isLegalStateAtFrx(newLinkStatus);
                    break;
                default:
                    LogUtil.info(TAG, "isLegalStatus unexpected current status=" + currLinkStatus);
                    break;
            }
        }
        LogUtil.debug(TAG, "isLegalStatus current status=" + currLinkStatus + " for newStatus=" + newLinkStatus + " result=" + isLegalStatus);
        return isLegalStatus;
    }

    private boolean isLegalStateAtInit(AndroidAutoConst.LinkStatus newLinkStatus) {
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[newLinkStatus.ordinal()];
        if (i == 2 || i == 3) {
            return true;
        }
        return false;
    }

    private boolean isLegalStateAtAvailable(AndroidAutoConst.LinkStatus newLinkStatus) {
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[newLinkStatus.ordinal()];
        return i == 1 || i == 3;
    }

    private boolean isLegalStateAtActivating(AndroidAutoConst.LinkStatus newLinkStatus) {
        switch (newLinkStatus) {
            case LINK_STATUS_ACTIVATED:
            case LINK_STATUS_DEACTIVATING:
            case LINK_STATUS_DEACTIVATED:
            case LINK_STATUS_CONNECT_FAILED:
            case LINK_STATUS_CONNECT_ERROR:
            case LINK_STATUS_AAP_FRX:
                return true;
            default:
                return false;
        }
    }

    private boolean isLegalStateAtActivated(AndroidAutoConst.LinkStatus newLinkStatus) {
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[newLinkStatus.ordinal()];
        if (i == 5 || i == 6 || i == 7 || i == 8) {
            return true;
        }
        return false;
    }

    private boolean isLegalStateAtDeactivating(AndroidAutoConst.LinkStatus newLinkStatus) {
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[newLinkStatus.ordinal()];
        if (i == 6 || i == 7 || i == 8) {
            return true;
        }
        return false;
    }

    private boolean isLegalStateAtDeactivated(AndroidAutoConst.LinkStatus newLinkStatus) {
        int i = AnonymousClass1.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[newLinkStatus.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }

    private boolean isLegalStateAtFrx(AndroidAutoConst.LinkStatus newLinkStatus) {
        switch (newLinkStatus) {
            case LINK_STATUS_ACTIVATED:
            case LINK_STATUS_DEACTIVATING:
            case LINK_STATUS_DEACTIVATED:
            case LINK_STATUS_CONNECT_FAILED:
            case LINK_STATUS_CONNECT_ERROR:
                return true;
            default:
                return false;
        }
    }

    private void setDeviceStatus(DeviceStatus currStatus, DeviceStatus newStatus) {
        if (currStatus != null && newStatus != null) {
            AndroidAutoConst.LinkStatus currLinkStatus = currStatus.getLinkStatus();
            AndroidAutoConst.LinkStatus newLinkStatus = newStatus.getLinkStatus();
            LogUtil.info(TAG, "setDeviceStatus currLinkStatus:" + currLinkStatus + " newLinkStatus:" + newLinkStatus);
            if (currLinkStatus == newLinkStatus) {
                return;
            }
            boolean isLegalStatus = isLegalStatus(currLinkStatus, newLinkStatus);
            LogUtil.info(TAG, "setDeviceStatus isLegalStatus:" + isLegalStatus);
            if (isLegalStatus) {
                currStatus.setLinkStatus(newLinkStatus);
            }
        }
    }

    private synchronized List<AndroidAutoDevice> getDeviceListByStatus(AndroidAutoConst.LinkStatus sourceLinkStatus) {
        List<AndroidAutoDevice> deviceList;
        DeviceStatus deviceStatus;
        AndroidAutoConst.LinkStatus linkStatus;
        AndroidAutoDevice currLinkDevice;
        deviceList = null;
        StringBuilder sb = new StringBuilder();
        sb.append("getDeviceListByStatus status: ");
        sb.append(sourceLinkStatus);
        sb.append(" list: ");
        sb.append(this.mDeviceStatusList != null ? Integer.valueOf(this.mDeviceStatusList.size()) : "null");
        LogUtil.debug(TAG, sb.toString());
        if (this.mDeviceStatusList != null && this.mDeviceStatusList.size() > 0) {
            deviceList = new ArrayList<>();
            for (Map.Entry<AndroidAutoDevice, DeviceStatus> map : this.mDeviceStatusList.entrySet()) {
                if (map != null && (deviceStatus = map.getValue()) != null && (linkStatus = deviceStatus.getLinkStatus()) == sourceLinkStatus && (currLinkDevice = map.getKey()) != null) {
                    int keyStatus = currLinkDevice.getStatus();
                    if (keyStatus != linkStatus.ordinal()) {
                        currLinkDevice.setStatus(linkStatus.ordinal());
                    }
                    deviceList.add(currLinkDevice);
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getDeviceListByStatus return: ");
        sb2.append(deviceList != null ? Integer.valueOf(deviceList.size()) : "null");
        LogUtil.debug(TAG, sb2.toString());
        return deviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrConnectingLinkDevice() {
        List<AndroidAutoDevice> connectingDeviceList;
        connectingDeviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING);
        return connectingDeviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrConnectedLinkDevice() {
        List<AndroidAutoDevice> deviceList;
        deviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED);
        List<AndroidAutoDevice> frxDeviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX);
        if (frxDeviceList != null && !frxDeviceList.isEmpty()) {
            deviceList.addAll(frxDeviceList);
        }
        return deviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrActivitedLinkDevice() {
        List<AndroidAutoDevice> deviceList;
        deviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED);
        return deviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrFrxLinkDevice() {
        List<AndroidAutoDevice> deviceList;
        deviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX);
        return deviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrDisConnectingLinkDevice() {
        List<AndroidAutoDevice> disConnectingDeviceList;
        disConnectingDeviceList = getDeviceListByStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING);
        return disConnectingDeviceList;
    }

    public synchronized List<AndroidAutoDevice> getCurrDeviceList() {
        List<AndroidAutoDevice> currDeviceList;
        currDeviceList = new ArrayList<>();
        List<AndroidAutoDevice> connectingDeviceList = getCurrConnectingLinkDevice();
        List<AndroidAutoDevice> connectedDeviceList = getCurrConnectedLinkDevice();
        List<AndroidAutoDevice> disConnectingDeviceList = getCurrDisConnectingLinkDevice();
        if (connectingDeviceList != null && !connectingDeviceList.isEmpty()) {
            currDeviceList.addAll(connectingDeviceList);
        }
        if (connectedDeviceList != null && !connectedDeviceList.isEmpty()) {
            currDeviceList.addAll(connectedDeviceList);
        }
        if (disConnectingDeviceList != null && !disConnectingDeviceList.isEmpty()) {
            currDeviceList.addAll(disConnectingDeviceList);
        }
        return currDeviceList;
    }

    public synchronized boolean isInAoaSwitchingMode(AndroidAutoDevice linkDevice) {
        boolean isInAoaSwitching;
        isInAoaSwitching = false;
        DeviceStatus deviceStatus = getDeviceStatus(linkDevice);
        StringBuilder sb = new StringBuilder();
        sb.append(" deviceStatus:");
        sb.append(deviceStatus == null);
        LogUtil.info(TAG, sb.toString());
        if (deviceStatus != null && deviceStatus.getAoaStatus() != null) {
            LogUtil.info(TAG, " status:" + deviceStatus.getAoaStatus());
            if (deviceStatus.getAoaStatus() == StatusConst.AoaStatus.AOA_STATUS_SWITCHING) {
                isInAoaSwitching = true;
            }
        }
        return isInAoaSwitching;
    }

    public synchronized AndroidAutoDevice getDeviceByBluetoothDevice(BluetoothDevice btDevice) {
        AndroidAutoDevice device;
        BluetoothDevice itemBtDevice;
        device = null;
        if (btDevice != null) {
            String btAddress = btDevice.getAddress();
            if (this.mDeviceStatusList != null && !this.mDeviceStatusList.isEmpty()) {
                Iterator<AndroidAutoDevice> iterator = this.mDeviceStatusList.keySet().iterator();
                while (true) {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    AndroidAutoDevice itemDevice = iterator.next();
                    if (itemDevice != null && (itemBtDevice = itemDevice.getBluetoothDevice()) != null) {
                        String itemBtAddress = itemBtDevice.getAddress();
                        if (!TextUtils.isEmpty(itemBtAddress) && itemBtAddress.equals(btAddress) && itemDevice.isWireLessLink()) {
                            device = itemDevice;
                            break;
                        }
                    }
                }
            }
        }
        return device;
    }

    public synchronized AndroidAutoDevice getDeviceByDeviceId(String deviceId, int linkType) {
        if (deviceId == null) {
            LogUtil.info(TAG, "getDeviceByDeviceId failed for null deviceId.");
            return null;
        }
        AndroidAutoDevice targetDevice = null;
        if (linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal() || linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
            if (this.mDeviceStatusList != null && this.mDeviceStatusList.size() > 0) {
                Iterator<AndroidAutoDevice> it = this.mDeviceStatusList.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AndroidAutoDevice tmpDevice = it.next();
                    if (tmpDevice != null && linkType == tmpDevice.getLinkType() && deviceId.equals(tmpDevice.getDeviceId())) {
                        LogUtil.info(TAG, "getDeviceByDeviceId, found Device.");
                        targetDevice = tmpDevice;
                        break;
                    }
                }
            }
        } else {
            LogUtil.info(TAG, "getDeviceByDeviceId failed for invalid linkType.");
        }
        return targetDevice;
    }

    public synchronized void dumpDeviceList() {
        LogUtil.info(TAG, "dumpDeviceList START");
        if (this.mDeviceStatusList != null && this.mDeviceStatusList.size() > 0) {
            for (AndroidAutoDevice tmpDevice : this.mDeviceStatusList.keySet()) {
                if (tmpDevice != null) {
                    LogUtil.info(TAG, "dumpDeviceList " + tmpDevice.toString());
                }
            }
        }
        LogUtil.info(TAG, "dumpDeviceList END");
    }
}
