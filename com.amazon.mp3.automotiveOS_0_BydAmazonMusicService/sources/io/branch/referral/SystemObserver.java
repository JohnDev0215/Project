package io.branch.referral;

import android.app.UiModeManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes7.dex */
abstract class SystemObserver {
    private String AIDInitializationSessionID_;
    private String GAIDString_ = null;
    private int LATVal_ = 0;

    interface AdsParamsFetchEvents {
        void onAdsParamsFetchFinished();
    }

    SystemObserver() {
    }

    static UniqueId getUniqueID(Context context, boolean z) {
        return new UniqueId(context, z);
    }

    static String getPackageName(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                PrefHelper.LogException("Error obtaining PackageName", e);
            }
        }
        return "";
    }

    static String getAppVersion(Context context) {
        String str;
        if (context != null) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                PrefHelper.LogException("Error obtaining AppVersion", e);
                str = "";
            }
        } else {
            str = "";
        }
        return TextUtils.isEmpty(str) ? "bnc_no_value" : str;
    }

    static long getFirstInstallTime(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            } catch (Exception e) {
                PrefHelper.LogException("Error obtaining FirstInstallTime", e);
            }
        }
        return 0L;
    }

    static boolean isPackageInstalled(Context context) {
        List<ResolveInfo> listQueryIntentActivities;
        if (context == null) {
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null || (listQueryIntentActivities = packageManager.queryIntentActivities(launchIntentForPackage, 65536)) == null) {
                return false;
            }
            return listQueryIntentActivities.size() > 0;
        } catch (Exception e) {
            PrefHelper.LogException("Error obtaining PackageInfo", e);
            return false;
        }
    }

    static long getLastUpdateTime(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            } catch (Exception e) {
                PrefHelper.LogException("Error obtaining LastUpdateTime", e);
            }
        }
        return 0L;
    }

    static String getPhoneBrand() {
        return Build.MANUFACTURER;
    }

    static String getPhoneModel() {
        return Build.MODEL;
    }

    static String getISO2CountryCode() {
        return Locale.getDefault() != null ? Locale.getDefault().getCountry() : "";
    }

    static String getISO2LanguageCode() {
        return Locale.getDefault() != null ? Locale.getDefault().getLanguage() : "";
    }

    private static boolean isFireOSDevice() {
        return getPhoneBrand().equalsIgnoreCase("amazon");
    }

    static String getOS(Context context) {
        return isFireOSDevice() ? context == null ? getPhoneModel().contains("AFT") ? "AMAZON_FIRE_TV" : "AMAZON_FIRE" : context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv") ? "AMAZON_FIRE_TV" : "AMAZON_FIRE" : "Android";
    }

    static int getOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    static DisplayMetrics getScreenDisplay(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context != null && (windowManager = (WindowManager) context.getSystemService("window")) != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }

    static boolean getWifiConnected(Context context) {
        if (context == null || context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager != null ? connectivityManager.getNetworkInfo(1) : null;
        return networkInfo != null && networkInfo.isConnected();
    }

    boolean prefetchAdsParams(Context context, AdsParamsFetchEvents adsParamsFetchEvents) {
        this.AIDInitializationSessionID_ = PrefHelper.getInstance(context).getSessionID();
        if (!isFireOSDevice()) {
            new GAdsPrefetchTask(context, adsParamsFetchEvents).executeTask(new Void[0]);
            return true;
        }
        if (context == null) {
            return false;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            this.LATVal_ = Settings.Secure.getInt(contentResolver, "limit_ad_tracking");
            String string = Settings.Secure.getString(contentResolver, "advertising_id");
            this.GAIDString_ = string;
            if (TextUtils.isEmpty(string) || this.GAIDString_.equals("00000000-0000-0000-0000-000000000000") || this.LATVal_ == 1) {
                this.GAIDString_ = null;
            }
            if (adsParamsFetchEvents != null) {
                adsParamsFetchEvents.onAdsParamsFetchFinished();
            }
        } catch (Settings.SettingNotFoundException unused) {
        }
        return false;
    }

    private class GAdsPrefetchTask extends BranchAsyncTask<Void, Void, Void> {
        private final AdsParamsFetchEvents callback_;
        private WeakReference<Context> contextRef_;

        public GAdsPrefetchTask(Context context, AdsParamsFetchEvents adsParamsFetchEvents) {
            this.contextRef_ = new WeakReference<>(context);
            this.callback_ = adsParamsFetchEvents;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() { // from class: io.branch.referral.SystemObserver.GAdsPrefetchTask.1
                @Override // java.lang.Runnable
                public void run() {
                    Context context = (Context) GAdsPrefetchTask.this.contextRef_.get();
                    if (context != null) {
                        Process.setThreadPriority(-19);
                        Object adInfoObject = GAdsPrefetchTask.this.getAdInfoObject(context);
                        GAdsPrefetchTask.this.setGoogleLATValue(adInfoObject);
                        if (SystemObserver.this.LATVal_ == 1) {
                            SystemObserver.this.GAIDString_ = null;
                        } else {
                            GAdsPrefetchTask.this.setGAID(adInfoObject);
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
            try {
                countDownLatch.await(1500L, TimeUnit.MILLISECONDS);
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            AdsParamsFetchEvents adsParamsFetchEvents = this.callback_;
            if (adsParamsFetchEvents != null) {
                adsParamsFetchEvents.onAdsParamsFetchFinished();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Object getAdInfoObject(Context context) {
            if (context == null) {
                return null;
            }
            try {
                return AdvertisingIdClient.class.getMethod("getAdvertisingIdInfo", Context.class).invoke(null, context);
            } catch (Throwable unused) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGAID(Object obj) {
            try {
                Method method = obj.getClass().getMethod("getId", new Class[0]);
                SystemObserver.this.GAIDString_ = (String) method.invoke(obj, new Object[0]);
            } catch (Exception unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGoogleLATValue(Object obj) {
            try {
                Method method = obj.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
                SystemObserver.this.LATVal_ = ((Boolean) method.invoke(obj, new Object[0])).booleanValue() ? 1 : 0;
            } catch (Exception unused) {
            }
        }
    }

    static String getLocalIPAddress() {
        String str = "";
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                Iterator it2 = Collections.list(((NetworkInterface) it.next()).getInetAddresses()).iterator();
                while (true) {
                    if (it2.hasNext()) {
                        InetAddress inetAddress = (InetAddress) it2.next();
                        if (!inetAddress.isLoopbackAddress()) {
                            String hostAddress = inetAddress.getHostAddress();
                            if (hostAddress.indexOf(58) < 0) {
                                str = hostAddress;
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    static String getUIMode(Context context) {
        UiModeManager uiModeManager;
        String str;
        if (context != null) {
            try {
                uiModeManager = (UiModeManager) context.getSystemService("uimode");
            } catch (Exception unused) {
                return "UI_MODE_TYPE_UNDEFINED";
            }
        } else {
            uiModeManager = null;
        }
        if (uiModeManager == null) {
            return "UI_MODE_TYPE_UNDEFINED";
        }
        switch (uiModeManager.getCurrentModeType()) {
            case 1:
                str = "UI_MODE_TYPE_NORMAL";
                break;
            case 2:
                str = "UI_MODE_TYPE_DESK";
                break;
            case 3:
                str = "UI_MODE_TYPE_CAR";
                break;
            case 4:
                str = "UI_MODE_TYPE_TELEVISION";
                break;
            case 5:
                str = "UI_MODE_TYPE_APPLIANCE";
                break;
            case 6:
                str = "UI_MODE_TYPE_WATCH";
                break;
            default:
                return "UI_MODE_TYPE_UNDEFINED";
        }
        return str;
    }

    static class UniqueId {
        private boolean isRealId;
        private String uniqueId;

        UniqueId(Context context, boolean z) {
            this.isRealId = !z;
            this.uniqueId = "bnc_no_value";
            String string = null;
            if (context != null && !z && !Branch.isSimulatingInstalls()) {
                string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            }
            if (string == null) {
                string = UUID.randomUUID().toString();
                this.isRealId = false;
            }
            this.uniqueId = string;
        }

        String getId() {
            return this.uniqueId;
        }

        boolean isReal() {
            return this.isRealId;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            UniqueId uniqueId = (UniqueId) obj;
            return this.uniqueId.equals(uniqueId.uniqueId) && this.isRealId == uniqueId.isRealId;
        }

        public int hashCode() {
            int i = ((this.isRealId ? 1 : 0) + 1) * 31;
            String str = this.uniqueId;
            return i + (str == null ? 0 : str.hashCode());
        }
    }

    String getAID() {
        return this.GAIDString_;
    }

    int getLATVal() {
        return this.LATVal_;
    }

    static String getImei(Context context) {
        String secondaryRequestMetaData = PrefHelper.getInstance(context).getSecondaryRequestMetaData(Defines$ModuleNameKeys.imei.getKey());
        if (TextUtils.isEmpty(secondaryRequestMetaData)) {
            return null;
        }
        return secondaryRequestMetaData;
    }

    String getAIDInitializationSessionID() {
        return this.AIDInitializationSessionID_;
    }
}
