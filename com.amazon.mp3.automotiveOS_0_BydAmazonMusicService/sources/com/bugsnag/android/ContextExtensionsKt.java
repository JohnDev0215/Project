package com.bugsnag.android;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.jvm.JvmName;

/* JADX INFO: compiled from: ContextExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\b\u001a\u0004\u0018\u00010\u0007*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0000\u001a\"\u0010\n\u001a\u00020\t*\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0000\u001a\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000b*\u00020\u0000H\u0001¢\u0006\u0004\b\f\u0010\r\u001a\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u00020\u0000H\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u0013*\u00020\u0000H\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u000e\u0010\u0018\u001a\u0004\u0018\u00010\u0017*\u00020\u0000H\u0001¨\u0006\u0019"}, d2 = {"Landroid/content/Context;", "Landroid/content/BroadcastReceiver;", "receiver", "Landroid/content/IntentFilter;", PageUriUtils.FILTER_QUERY_PARAM, "Lcom/bugsnag/android/Logger;", "logger", "Landroid/content/Intent;", "registerReceiverSafe", "", "unregisterReceiverSafe", "Landroid/app/ActivityManager;", "getActivityManagerFrom", "(Landroid/content/Context;)Landroid/app/ActivityManager;", "getActivityManager", "Landroid/net/ConnectivityManager;", "getConnectivityManagerFrom", "(Landroid/content/Context;)Landroid/net/ConnectivityManager;", "getConnectivityManager", "Landroid/os/storage/StorageManager;", "getStorageManagerFrom", "(Landroid/content/Context;)Landroid/os/storage/StorageManager;", "getStorageManager", "Landroid/location/LocationManager;", "getLocationManager", "bugsnag-android-core_release"}, k = 2, mv = {1, 4, 2})
public final class ContextExtensionsKt {
    public static final Intent registerReceiverSafe(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Logger logger) {
        try {
            return context.registerReceiver(broadcastReceiver, intentFilter);
        } catch (RemoteException e) {
            if (logger == null) {
                return null;
            }
            logger.w("Failed to register receiver", e);
            return null;
        } catch (IllegalArgumentException e2) {
            if (logger == null) {
                return null;
            }
            logger.w("Failed to register receiver", e2);
            return null;
        } catch (SecurityException e3) {
            if (logger == null) {
                return null;
            }
            logger.w("Failed to register receiver", e3);
            return null;
        }
    }

    public static final void unregisterReceiverSafe(Context context, BroadcastReceiver broadcastReceiver, Logger logger) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (RemoteException e) {
            if (logger != null) {
                logger.w("Failed to register receiver", e);
            }
        } catch (IllegalArgumentException e2) {
            if (logger != null) {
                logger.w("Failed to register receiver", e2);
            }
        } catch (SecurityException e3) {
            if (logger != null) {
                logger.w("Failed to register receiver", e3);
            }
        }
    }

    @JvmName(name = "getActivityManagerFrom")
    public static final ActivityManager getActivityManagerFrom(Context context) {
        try {
            Object systemService = context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            if (!(systemService instanceof ActivityManager)) {
                systemService = null;
            }
            return (ActivityManager) systemService;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @JvmName(name = "getConnectivityManagerFrom")
    public static final ConnectivityManager getConnectivityManagerFrom(Context context) {
        try {
            Object systemService = context.getSystemService("connectivity");
            if (!(systemService instanceof ConnectivityManager)) {
                systemService = null;
            }
            return (ConnectivityManager) systemService;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @JvmName(name = "getLocationManager")
    public static final LocationManager getLocationManager(Context context) {
        try {
            Object systemService = context.getSystemService("location");
            if (!(systemService instanceof LocationManager)) {
                systemService = null;
            }
            return (LocationManager) systemService;
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @JvmName(name = "getStorageManagerFrom")
    public static final StorageManager getStorageManagerFrom(Context context) {
        try {
            Object systemService = context.getSystemService("storage");
            if (!(systemService instanceof StorageManager)) {
                systemService = null;
            }
            return (StorageManager) systemService;
        } catch (RuntimeException unused) {
            return null;
        }
    }
}
