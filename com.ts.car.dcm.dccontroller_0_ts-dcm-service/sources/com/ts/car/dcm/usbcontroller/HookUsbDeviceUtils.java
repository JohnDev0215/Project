package com.ts.car.dcm.usbcontroller;

import android.hardware.usb.IUsbSerialReader;
import android.hardware.usb.UsbDevice;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes.dex */
public class HookUsbDeviceUtils {
    private static final String TAG = "HookUsbDeviceUtils";

    public static synchronized void hookFilterUsbSerialNumber(UsbDevice usbDevice) {
        final Object serialNumberReader = getFieldValue(usbDevice, "mSerialNumberReader");
        if (serialNumberReader != null) {
            IUsbSerialReader proxyInstance = (IUsbSerialReader) Proxy.newProxyInstance(serialNumberReader.getClass().getClassLoader(), new Class[]{IUsbSerialReader.class}, new InvocationHandler() { // from class: com.ts.car.dcm.usbcontroller.-$$Lambda$HookUsbDeviceUtils$BNH6y0H6hgW2oWfl3akEpufvv1Y
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method, Object[] objArr) {
                    return HookUsbDeviceUtils.lambda$hookFilterUsbSerialNumber$0(serialNumberReader, obj, method, objArr);
                }
            });
            setFieldValue(usbDevice, "mSerialNumberReader", proxyInstance);
        }
    }

    static /* synthetic */ Object lambda$hookFilterUsbSerialNumber$0(Object serialNumberReader, Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("getSerial")) {
            String serialNumber = (String) method.invoke(serialNumberReader, args);
            return filter(serialNumber);
        }
        return method.invoke(serialNumberReader, args);
    }

    private static void setFieldValue(Object targetObject, String fieldName, Object fieldValue) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(targetObject, fieldValue);
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
        }
    }

    private static Object getFieldValue(Object targetObject, String fieldName) {
        try {
            Field field = targetObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object obj = field.get(targetObject);
            return obj;
        } catch (IllegalAccessException | NoSuchFieldException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String filter(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Log.d(TAG, "filter usb serial number before:" + str);
        String str2 = str.replaceAll("[^\\p{Print}\\t\\n]", "");
        Log.d(TAG, "filter usb serial number after:" + str2);
        return str2;
    }
}
