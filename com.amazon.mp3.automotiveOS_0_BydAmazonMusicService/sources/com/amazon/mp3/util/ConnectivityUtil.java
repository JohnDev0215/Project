package com.amazon.mp3.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.annotation.RequiresApi;
import androidx.core.os.EnvironmentCompat;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.store.dialog.NoConnectionDialog;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ConnectivityUtil {
    private static final String LOGTAG = "ConnectivityUtil";
    private static Context sContext;

    public static synchronized void initialize(final Context context) {
        sContext = context.getApplicationContext();
    }

    private static synchronized Context getContext() {
        Context context = sContext;
        if (context != null) {
            return context;
        }
        return AmazonApplication.getContext();
    }

    @Deprecated
    public static boolean isWifiConnected() {
        return isWifiConnected(getContext());
    }

    public static boolean isWifiConnected(final Context context) {
        if (context == null) {
            return false;
        }
        int connectionType = getConnectionType(context);
        return connectionType == 1 || connectionType == 2;
    }

    @Deprecated
    public static boolean isWifiOnlyDevice() {
        return isWifiOnlyDevice(getContext());
    }

    public static boolean isWifiOnlyDevice(final Context context) {
        if (context == null) {
            return false;
        }
        return determineIsWifiOnlyDevice(context);
    }

    public static boolean isWifiRequiredForDownloads(Context context) {
        return MusicDownloader.getInstance(context).isDownloadOnWifiOnly().booleanValue();
    }

    public static boolean isConnectionHighSpeed(final Context context) {
        if (context == null) {
            return false;
        }
        int connectionType = getConnectionType(context);
        return connectionType == 1 || connectionType == 2 || connectionType == 4;
    }

    @Deprecated
    public static boolean hasValidInternetConnection() {
        return hasValidInternetConnection(getContext());
    }

    public static boolean hasValidInternetConnection(final Context context) {
        if (context == null) {
            return false;
        }
        if (isWifiConnected(context)) {
            return true;
        }
        if (OtaUtil.isEnabled()) {
            return !isDataRoaming(context);
        }
        return false;
    }

    @Deprecated
    public static boolean isAvailable() {
        return isAvailable(getContext());
    }

    public static boolean isAvailable(final Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null) {
            return false;
        }
        if (useNetworkApis()) {
            ConnectivityManager connectivityManager = getConnectivityManager(context);
            return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? false : true;
        }
        return hasDataConnectionUsingNetworkCapabilities(context);
    }

    public static boolean isDataRoaming(final Context context) {
        ConnectivityManager connectivityManager;
        int connectionType = getConnectionType(context);
        if (connectionType == 1 || connectionType == 2) {
            return false;
        }
        if (context == null || (connectivityManager = getConnectivityManager(context)) == null) {
            return true;
        }
        if (useNetworkApis()) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isRoaming();
        }
        NetworkCapabilities currentNetworkCapabilities = getCurrentNetworkCapabilities(connectivityManager);
        return (currentNetworkCapabilities == null || currentNetworkCapabilities.hasCapability(18)) ? false : true;
    }

    @Deprecated
    public static boolean allRadiosOff() {
        return allRadiosOff(getContext());
    }

    public static boolean allRadiosOff(final Context context) {
        if (context == null) {
            return false;
        }
        return isAirplaneModeOn(context) || isWifiOnlyAndRadioOff(context);
    }

    public static boolean isAirplaneModeOn(final Context context) {
        return (context == null || Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 0) ? false : true;
    }

    public static boolean isWifiOnlyAndRadioOff(final Context context) {
        WifiManager wifiManager = getWifiManager(context);
        return (wifiManager == null || !isWifiOnlyDevice(context) || wifiManager.isWifiEnabled()) ? false : true;
    }

    public static boolean hasDataConnection(final Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = getConnectivityManager(context)) == null) {
            return false;
        }
        if (useNetworkApis()) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return (activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected() && activeNetworkInfo.isAvailable()) || activeNetworkInfo.getSubtype() == 6;
        }
        return hasDataConnectionUsingNetworkCapabilities(connectivityManager);
    }

    static int getConnectionTypeInteger(final int telephonyManagerType) {
        switch (telephonyManagerType) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 3;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 15:
                return 4;
            default:
                Log.error(LOGTAG, "Unknown cellular type: " + telephonyManagerType);
                return 0;
        }
    }

    @Deprecated
    public static int getConnectionType() {
        return getConnectionType(getContext());
    }

    public static int getConnectionType(final Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = getConnectivityManager(context)) == null) {
            return 0;
        }
        if (useNetworkApis()) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                int type = activeNetworkInfo.getType();
                if (type == 0) {
                    return getConnectionTypeInteger(activeNetworkInfo.getSubtype());
                }
                if (type == 1) {
                    return 1;
                }
                if (type == 9) {
                    return 2;
                }
            }
        } else {
            NetworkCapabilities currentNetworkCapabilities = getCurrentNetworkCapabilities(connectivityManager);
            if (currentNetworkCapabilities != null) {
                if (currentNetworkCapabilities.hasTransport(1)) {
                    return 1;
                }
                if (currentNetworkCapabilities.hasTransport(0)) {
                    return networkSpeedUsingNetworkCapabilites(connectivityManager);
                }
                if (currentNetworkCapabilities.hasTransport(3)) {
                    return 2;
                }
            }
        }
        return 0;
    }

    public static String getIpAddress(final Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        return Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
    }

    @Deprecated
    public static boolean hasAnyInternetConnection() {
        return hasAnyInternetConnection(getContext());
    }

    public static boolean hasAnyInternetConnection(final Context context) {
        if (context == null) {
            return false;
        }
        boolean z = hasDataConnection(context) || isConnectionHighSpeed(context);
        if (!z) {
            logConnectivityDebugInfo(context);
        }
        return z;
    }

    @Deprecated
    public static boolean isBackgroundDataEnabled() {
        return isBackgroundDataEnabled(getContext());
    }

    public static boolean isBackgroundDataEnabled(final Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = getConnectivityManager(context)) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getDetailedState() == NetworkInfo.DetailedState.BLOCKED) ? false : true;
    }

    @Deprecated
    public static String getCarrierName() {
        return getCarrierName(getContext());
    }

    @Deprecated
    public static String getCarrierName(final Context context) {
        Log.error(LOGTAG, "Fetching carrier name has been deprecated");
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    public static WifiManager.WifiLock createAndAcquireWifiLock(final Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager != null ? wifiManager.createWifiLock(3, LOGTAG) : null;
        if (wifiLockCreateWifiLock != null) {
            wifiLockCreateWifiLock.acquire();
        }
        return wifiLockCreateWifiLock;
    }

    public static void releaseWifiLock(WifiManager.WifiLock lock) {
        if (lock != null) {
            lock.release();
        }
    }

    private static boolean determineIsWifiOnlyDevice(final Context context) {
        ConnectivityManager connectivityManager;
        if (context == null || (connectivityManager = getConnectivityManager(context)) == null || !useNetworkApis()) {
            return false;
        }
        for (NetworkInfo networkInfo : connectivityManager.getAllNetworkInfo()) {
            int type = networkInfo.getType();
            if (type != 1 && type != 9 && type != 7 && type != 17 && type != 13) {
                return false;
            }
        }
        return true;
    }

    public static final boolean checkConnectivityAndShowDialog(final FragmentActivity activity) {
        if (hasAnyInternetConnection(activity)) {
            return true;
        }
        if (activity != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.util.ConnectivityUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    NoConnectionDialog.newInstance(false).show(activity.getSupportFragmentManager(), "NoConnectionDialog");
                }
            });
            return false;
        }
        Log.error(LOGTAG, "Null activity passed to checkConnectivityAndShowDialog, can't show dialog!");
        return false;
    }

    public static final boolean checkConnectivityAndShowDialog(final Context context) {
        if (context == null) {
            Log.error(LOGTAG, "Null context passed to checkConnectivityAndShowDialog, can't show dialog!");
            return false;
        }
        if (hasAnyInternetConnection(context)) {
            return true;
        }
        buildNoConnectionNoRetryDialog(context).show();
        return false;
    }

    private static Dialog buildNoConnectionNoRetryDialog(final Context context) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(context);
        bauhausDialogBuilder.setTitle(R.string.dmusic_no_connection_title);
        bauhausDialogBuilder.setMessage(ResourceUtil.getNoConnectionStringId());
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, (DialogInterface.OnClickListener) null);
        return bauhausDialogBuilder.create();
    }

    @Deprecated
    public static void logConnectivityDebugInfo() {
        logConnectivityDebugInfo(getContext());
    }

    public static void logConnectivityDebugInfo(final Context context) {
        ConnectivityManager connectivityManager;
        String str;
        String string;
        if (context == null || (connectivityManager = getConnectivityManager(context)) == null) {
            return;
        }
        if (useNetworkApis()) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                String typeName = activeNetworkInfo.getTypeName();
                if (!TextUtils.isEmpty(activeNetworkInfo.getSubtypeName())) {
                    typeName = typeName + " (" + activeNetworkInfo.getSubtypeName() + ')';
                }
                StringBuilder sb = new StringBuilder();
                sb.append(typeName);
                sb.append(" is ");
                sb.append(activeNetworkInfo.isConnected() ? "Connected" : "Not connected");
                string = sb.toString();
                String reason = activeNetworkInfo.getReason();
                if (!TextUtils.isEmpty(reason)) {
                    string = string + " because " + reason;
                }
            } else {
                string = "No active network";
            }
        } else {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
            if (activeNetwork != null) {
                str = "\nNetwork: " + activeNetwork.toString() + ",\n";
            } else {
                str = "{";
            }
            if (networkCapabilities != null) {
                str = str + "Capabilities: " + networkCapabilities.toString() + "\n";
            }
            string = str + "}";
        }
        Log.info(LOGTAG, string);
    }

    private static ConnectivityManager getConnectivityManager(final Context context) {
        if (context != null) {
            return (ConnectivityManager) context.getSystemService("connectivity");
        }
        return null;
    }

    private static WifiManager getWifiManager(final Context context) {
        if (context != null) {
            return (WifiManager) context.getSystemService("wifi");
        }
        return null;
    }

    @RequiresApi(api = 23)
    private static NetworkCapabilities getCurrentNetworkCapabilities(final ConnectivityManager connectivityManager) {
        return connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
    }

    private static boolean hasDataConnectionUsingNetworkCapabilities(final Context context) {
        return hasDataConnectionUsingNetworkCapabilities(getConnectivityManager(context));
    }

    private static boolean hasDataConnectionUsingNetworkCapabilities(final ConnectivityManager connectivityManager) {
        NetworkCapabilities currentNetworkCapabilities = getCurrentNetworkCapabilities(connectivityManager);
        return currentNetworkCapabilities != null && currentNetworkCapabilities.hasCapability(12) && currentNetworkCapabilities.hasCapability(14) && currentNetworkCapabilities.hasCapability(13);
    }

    private static int networkSpeedUsingNetworkCapabilites(ConnectivityManager connectivityManager) {
        NetworkCapabilities currentNetworkCapabilities = getCurrentNetworkCapabilities(connectivityManager);
        if (currentNetworkCapabilities == null) {
            return 0;
        }
        return currentNetworkCapabilities.getLinkDownstreamBandwidthKbps() < 110 ? 3 : 4;
    }

    private static boolean useNetworkApis() {
        return Build.VERSION.SDK_INT <= 30;
    }
}
