package com.byd.auto_emanual.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.byd.auto_emanual.NetStatusCallback;

/* JADX INFO: loaded from: classes.dex */
public class NetUtil {
    private static final String TAG = "NetUtil";

    public static boolean isNetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        String str = TAG;
        MLog.i(str, "isNetAvailable isAvailable = " + z);
        if (!z) {
            return z;
        }
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        boolean zHasCapability = networkCapabilities != null ? networkCapabilities.hasCapability(16) : false;
        MLog.i(str, "isNetAvailable hasCapability = " + zHasCapability);
        if (!zHasCapability && !NetStatusCallback.oldNetAvailable) {
            NetStatusCallback.getInstance().pingNet();
        }
        return zHasCapability;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        MLog.i(TAG, "connectivity is " + activeNetworkInfo.getType());
        return activeNetworkInfo.getType() == 1;
    }

    public static boolean pingSuccess() {
        String domainName = HttpUtil.getDomainName();
        if (TextUtils.isEmpty(domainName)) {
            return false;
        }
        try {
            int iWaitFor = Runtime.getRuntime().exec("ping -c 1 -w 1 " + domainName).waitFor();
            MLog.i(TAG, "pingSuccess " + iWaitFor);
            return iWaitFor == 0;
        } catch (Exception e) {
            MLog.e(TAG, "pingSuccess exception.", e);
            return false;
        }
    }
}
