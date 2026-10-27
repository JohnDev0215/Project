package com.byd.vrassistant.utils;

import android.content.Context;
import android.os.storage.StorageManager;
import com.blankj.utilcode.util.LogUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class UsbUtils {
    private static final String MOUNTS_FILE = "/proc/mounts";
    private static final String TAG = "UsbUtils";
    private StorageManager mStorageManager;

    public static boolean isExistUSB(Context context) {
        String[] storagePath = getStoragePath(context);
        if (storagePath != null) {
            LogUtils.i(TAG, " path[0] = " + storagePath[0]);
            if (storagePath[0].toString().equalsIgnoreCase("")) {
                LogUtils.i(TAG, "优盘不存在");
            } else {
                LogUtils.i(TAG, "优盘存在");
                return true;
            }
        } else {
            LogUtils.i(TAG, "path=null");
        }
        return false;
    }

    public static String[] getStoragePath(Context context) {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.StorageVolume");
            Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
            Method method2 = cls.getMethod("getPath", new Class[0]);
            Method method3 = cls.getMethod("isRemovable", new Class[0]);
            Object objInvoke = method.invoke(storageManager, new Object[0]);
            int length = Array.getLength(objInvoke);
            for (int i = 0; i < length; i++) {
                Object obj = Array.get(objInvoke, i);
                String str = (String) method2.invoke(obj, new Object[0]);
                if (((Boolean) method3.invoke(obj, new Object[0])).booleanValue()) {
                    arrayList.add(str);
                }
            }
        } catch (ClassNotFoundException e) {
            LogUtils.e(TAG, "getUsbPath ClassNotFoundException", e);
        } catch (IllegalAccessException e2) {
            LogUtils.e(TAG, "getUsbPath IllegalAccessException", e2);
        } catch (NoSuchMethodException e3) {
            LogUtils.e(TAG, "getUsbPath NoSuchMethodException", e3);
        } catch (InvocationTargetException e4) {
            LogUtils.e(TAG, "getUsbPath InvocationTargetException", e4);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public static String defaultUsbPath(Context context) {
        String str;
        String[] storagePath = getStoragePath(context);
        return (storagePath == null || (str = storagePath[0]) == null) ? "" : str;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0020 -> B:32:0x0035). Please report as a decompilation issue!!! */
    public static boolean isMounted(String str) throws Throwable {
        boolean z = false;
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(MOUNTS_FILE));
                    while (true) {
                        try {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            if (line.contains(str)) {
                                z = true;
                                break;
                            }
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return z;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return z;
    }
}
