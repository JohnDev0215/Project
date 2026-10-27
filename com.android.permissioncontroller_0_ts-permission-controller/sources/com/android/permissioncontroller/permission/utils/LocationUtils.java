package com.android.permissioncontroller.permission.utils;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.Log;
import com.android.permissioncontroller.PermissionControllerApplication;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class LocationUtils {
    public static final String LOCATION_PERMISSION = "android.permission-group.LOCATION";
    private static final long LOCATION_UPDATE_DELAY_MS = 1000;
    private static final String TAG = LocationUtils.class.getSimpleName();
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static final ArrayList<LocationListener> sLocationListeners = new ArrayList<>();
    private static BroadcastReceiver sLocationBroadcastReceiver = new AnonymousClass2();

    public interface LocationListener {
        void onLocationStateChange(boolean z);
    }

    public static void showLocationDialog(final Context context, CharSequence label) {
        new AlertDialog.Builder(context).setIcon(R.drawable.ic_dialog_alert_material).setTitle(android.R.string.dialog_alert_title).setMessage(context.getString(R.string.location_warning, label)).setNegativeButton(R.string.ok, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.location_settings, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.utils.LocationUtils.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        }).show();
    }

    public static void startLocationControllerExtraPackageSettings(Context context, UserHandle user) {
        try {
            context.startActivityAsUser(new Intent("android.settings.LOCATION_CONTROLLER_EXTRA_PACKAGE_SETTINGS"), user);
        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "No activity to handle android.settings.LOCATION_CONTROLLER_EXTRA_PACKAGE_SETTINGS");
        }
    }

    public static boolean isLocationEnabled(Context context) {
        return ((LocationManager) context.getSystemService(LocationManager.class)).isLocationEnabled();
    }

    public static boolean isLocationGroupAndProvider(Context context, String groupName, String packageName) {
        return LOCATION_PERMISSION.equals(groupName) && ((LocationManager) context.getSystemService(LocationManager.class)).isProviderPackage(packageName);
    }

    public static boolean isLocationGroupAndControllerExtraPackage(Context context, String groupName, String packageName) {
        return LOCATION_PERMISSION.equals(groupName) && packageName.equals(((LocationManager) context.getSystemService(LocationManager.class)).getExtraLocationControllerPackage());
    }

    public static boolean isExtraLocationControllerPackageEnabled(Context context) {
        try {
            return ((LocationManager) context.getSystemService(LocationManager.class)).isExtraLocationControllerPackageEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.utils.LocationUtils$2, reason: invalid class name */
    class AnonymousClass2 extends BroadcastReceiver {
        AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            final boolean isEnabled = intent.getBooleanExtra("android.location.extra.LOCATION_ENABLED", true);
            LocationUtils.sMainHandler.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.utils.-$$Lambda$LocationUtils$2$iXHiL4UF0Z_L6OH24fZI21vEGBw
                @Override // java.lang.Runnable
                public final void run() {
                    LocationUtils.AnonymousClass2.lambda$onReceive$0(isEnabled);
                }
            }, LocationUtils.LOCATION_UPDATE_DELAY_MS);
        }

        static /* synthetic */ void lambda$onReceive$0(boolean isEnabled) {
            synchronized (LocationUtils.sLocationListeners) {
                for (LocationListener l : LocationUtils.sLocationListeners) {
                    l.onLocationStateChange(isEnabled);
                }
            }
        }
    }

    public static void addLocationListener(LocationListener listener) {
        synchronized (sLocationListeners) {
            boolean wasEmpty = sLocationListeners.isEmpty();
            sLocationListeners.add(listener);
            if (wasEmpty) {
                IntentFilter intentFilter = new IntentFilter("android.location.MODE_CHANGED");
                PermissionControllerApplication.get().getApplicationContext().registerReceiverForAllUsers(sLocationBroadcastReceiver, intentFilter, null, null);
            }
        }
    }

    public static boolean removeLocationListener(LocationListener listener) {
        boolean success;
        synchronized (sLocationListeners) {
            success = sLocationListeners.remove(listener);
            if (success && sLocationListeners.isEmpty()) {
                PermissionControllerApplication.get().getApplicationContext().unregisterReceiver(sLocationBroadcastReceiver);
            }
        }
        return success;
    }
}
