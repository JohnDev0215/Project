package com.core.system.service.utils;

import android.os.Process;
import android.os.SystemProperties;
import android.os.UserHandle;
import com.ts.car.input.ConfigParser;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class OneShotEventUtil {
    private static final String SYSTEM_PROP_CORE_SERVER_ONCE_BOOT = "sys.core.systemserver.onceboot.";
    private static final String SYSTEM_PROP_CORE_SERVER_ONE_SHOT = "persist.sys.core.systemserver.oneshot.";
    private static volatile OneShotEventUtil mInstance = new OneShotEventUtil();
    private HashMap<String, Boolean> mOneShotPropertiesCache = new HashMap<>();
    private HashMap<String, Boolean> mOnceBootPropertiesCache = new HashMap<>();

    private OneShotEventUtil() {
    }

    public static OneShotEventUtil getInstance() {
        return mInstance;
    }

    public synchronized boolean isShoted(String name) {
        if (this.mOneShotPropertiesCache.getOrDefault(combineOneShotPropertyKey(name), false).booleanValue()) {
            return true;
        }
        boolean result = SystemProperties.getBoolean(combineOneShotPropertyKey(name), false);
        this.mOneShotPropertiesCache.put(combineOneShotPropertyKey(name), Boolean.valueOf(result));
        return result;
    }

    public synchronized void markShoted(String name) {
        SystemProperties.set(combineOneShotPropertyKey(name), "true");
        this.mOneShotPropertiesCache.put(combineOneShotPropertyKey(name), true);
    }

    public synchronized void markShoted(String name, boolean res) {
        SystemProperties.set(combineOneShotPropertyKey(name), res + ConfigParser.EMPTY);
        this.mOneShotPropertiesCache.put(combineOneShotPropertyKey(name), Boolean.valueOf(res));
    }

    private String combineOneShotPropertyKey(String name) {
        return SYSTEM_PROP_CORE_SERVER_ONE_SHOT + name + ".user" + UserHandle.getUserId(Process.myUid());
    }

    public synchronized boolean isServiceBootedAfterDeviceStarted(String name) {
        if (this.mOnceBootPropertiesCache.getOrDefault(combineOncebootPropertyKey(name), false).booleanValue()) {
            return true;
        }
        boolean result = SystemProperties.getBoolean(combineOncebootPropertyKey(name), false);
        this.mOnceBootPropertiesCache.put(combineOncebootPropertyKey(name), Boolean.valueOf(result));
        return result;
    }

    public synchronized void markServiceBootedAfterDeviceStarted(String name) {
        SystemProperties.set(combineOncebootPropertyKey(name), "true");
        this.mOnceBootPropertiesCache.put(combineOncebootPropertyKey(name), true);
    }

    public synchronized void markServiceBootedAfterDeviceStarted(String name, boolean value) {
        SystemProperties.set(combineOncebootPropertyKey(name), value + ConfigParser.EMPTY);
        this.mOnceBootPropertiesCache.put(combineOncebootPropertyKey(name), Boolean.valueOf(value));
    }

    private String combineOncebootPropertyKey(String name) {
        return SYSTEM_PROP_CORE_SERVER_ONCE_BOOT + name + ".user" + UserHandle.getUserId(Process.myUid());
    }
}
