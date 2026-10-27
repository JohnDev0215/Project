package com.ts.car.sdk.dcm;

import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.service.dcm.data.AbnormalDevice;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public class Utils {
    private static final String TAG = "PlatformDeviceConnectionManager";

    public static <X, Y> boolean copy(X src, Y target, Class<?> srcClass, Class<?> targetClass) {
        if (src == null || target == null) {
            Log.e(TAG, "src or target is null src: " + src + ", target: " + target);
            return false;
        }
        Field[] srcFields = srcClass.getDeclaredFields();
        Field[] targetFields = targetClass.getDeclaredFields();
        for (Field field : targetFields) {
            Field tmp = findFieldByField(field, srcFields, srcClass);
            if (tmp == null) {
                Log.e(TAG, "src can't match all field");
                return false;
            }
            tmp.setAccessible(true);
            field.setAccessible(true);
            try {
                field.set(target, tmp.get(src));
            } catch (IllegalAccessException | IllegalArgumentException exp) {
                exp.printStackTrace();
            }
        }
        if (targetClass.getSuperclass() != Object.class) {
            copy(src, target, srcClass, targetClass.getSuperclass());
        }
        return true;
    }

    private static Field findFieldByField(Field field, Field[] fields, Class<?> srcClass) {
        String name = field.getName();
        for (Field tmp : fields) {
            if ((name.equals("mDevId") && ("mDeviceId".equals(tmp.getName()) || "mDeviceUuid".equals(tmp.getName()) || "mDevId".equals(tmp.getName()))) || name.equals(tmp.getName())) {
                return tmp;
            }
        }
        Class<?> srcSuperClass = srcClass.getSuperclass();
        while (srcSuperClass != null) {
            Field[] superFields = srcSuperClass.getDeclaredFields();
            for (Field tmp2 : superFields) {
                if (name.equals(tmp2.getName())) {
                    return tmp2;
                }
            }
            srcSuperClass = srcClass.getSuperclass();
        }
        return null;
    }

    public static ConnectionDevice covertAidlToConnectionDevice(ConnDevice connDevice) {
        if (connDevice == null) {
            Log.e(TAG, "covertAidlToConnectionDevice connDevice is null");
            return null;
        }
        ConnectionDevice device = new ConnectionDevice(connDevice.getDeviceUuid());
        device.setFriendlyName(connDevice.getFriendlyName());
        device.setBtAddr(connDevice.getBtAddr());
        device.setUsbSerialNumber(connDevice.getUsbSerialNumber());
        device.setWifiAddr(connDevice.getWifiAddr());
        device.setWifiIpAddress(connDevice.getWifiIpAddress());
        device.setAccepted(connDevice.getAccepted());
        device.setLastUptime(connDevice.getLastUptime());
        device.setLastService(connDevice.getLastService());
        device.setLast(connDevice.isLast());
        device.setFavorite(connDevice.isFavorite());
        device.setDeviceConnectedState(connDevice.getDeviceConnectedState());
        device.setCapability(connDevice.getCapability());
        device.setUsbHidNode(connDevice.getUsbHidNode());
        device.setIapNode(connDevice.getIapNode());
        device.setUsbNcmNode(connDevice.getUsbNcmNode());
        device.setDeviceType(connDevice.getDeviceType());
        device.setInUsbDeviceMode(connDevice.isInUsbDeviceMode());
        device.setAvailableCapability(connDevice.getAvailableCapabilitys());
        device.setActiveCapability(connDevice.getActiveCapability());
        device.setAlwaysCapability(connDevice.getAlwaysCapability());
        device.setIsPaired(connDevice.isPaired());
        device.setBtDevicePairedTime(connDevice.getAccepted());
        device.setBtDevicePairedTime(connDevice.getBtDevicePairedTime());
        device.setWirelessCarplayConnectedTime(connDevice.getWirelessCarplayConnectedTime());
        device.setUsbCarplayConnectedTime(connDevice.getUsbCarplayConnectedTime());
        device.setUsbOtgFlag(connDevice.isUsbOtgPort());
        device.setConnectedTypeState(connDevice.getConnectedTypeState());
        device.setActiveCapaState(connDevice.getActiveCapabilityState());
        device.setBtProfileState(connDevice.getBtProfileState());
        device.setWirelessAndroidAutoConnectedTime(connDevice.getWirelessAndroidAutoConnectedTime());
        device.setUsbAndroidAutoConnectedTime(connDevice.getUsbAndroidAutoConnectedTime());
        device.setIsCarplayFirstConn(connDevice.isCarplayFirstConn());
        device.setIsAndroidAutoFirstConn(connDevice.isAndroidAutoFirstConn());
        device.setMobileDeviceId(connDevice.getMobileDeviceId());
        return device;
    }

    public static List<ConnectionDevice> covertAidlToConnectionDevice(List<ConnDevice> list) {
        if (list == null) {
            Log.e(TAG, "covertAidlToConnectionDevice list is null");
            return null;
        }
        List<ConnectionDevice> connectedDevices = new ArrayList<>();
        for (ConnDevice connDevice : list) {
            ConnectionDevice device = null;
            if (connDevice != null) {
                device = covertAidlToConnectionDevice(connDevice);
            }
            connectedDevices.add(device);
        }
        return connectedDevices;
    }

    public static AbnormalDevice covertAidlToAbnormalDevice(ts.car.dcm.common.data.AbnormalDevice abnormalDevice) {
        if (abnormalDevice == null) {
            Log.e(TAG, "covertAidlToAbnormalDevice abnormalDevice is null");
            return null;
        }
        AbnormalDevice device = new AbnormalDevice(abnormalDevice.getDeviceId());
        device.setConnType(abnormalDevice.getConnType());
        device.setAbnormalType(abnormalDevice.getAbnormalType());
        return device;
    }

    public static List<AbnormalDevice> covertAidlToAbnormalDevice(List<ts.car.dcm.common.data.AbnormalDevice> list) {
        if (list == null) {
            Log.e(TAG, "covertAidlToAbnormalDevice list is null");
            return null;
        }
        List<AbnormalDevice> abnormalDevices = new ArrayList<>();
        for (ts.car.dcm.common.data.AbnormalDevice abnormalDevice : list) {
            AbnormalDevice device = null;
            if (abnormalDevice != null) {
                device = covertAidlToAbnormalDevice(abnormalDevice);
            }
            abnormalDevices.add(device);
        }
        return abnormalDevices;
    }

    public static ProjectionLinkParameter covertProjectionLinkParameterToAidl(ts.car.service.dcm.data.ProjectionLinkParameter projectionLinkParameter) {
        if (projectionLinkParameter == null) {
            Log.e(TAG, "covertProjectionLinkParameterToAidl projectionLinkParameter is null");
            return null;
        }
        ProjectionLinkParameter parameter = new ProjectionLinkParameter(projectionLinkParameter.getDevId(), projectionLinkParameter.getCapability());
        parameter.setFromUser(projectionLinkParameter.getFromUser());
        parameter.setAutoConnectDevice(projectionLinkParameter.getAutoConnectDevice());
        parameter.setAlways(projectionLinkParameter.getAlways());
        parameter.setFirst(projectionLinkParameter.getFirst());
        parameter.setCapabilityAlreadyChecked(projectionLinkParameter.isCapabilityAlreadyChecked());
        parameter.setForceProjection(projectionLinkParameter.getForceProjection());
        return parameter;
    }
}
