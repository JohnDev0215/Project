package com.amazon.avod.media.framework.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import com.amazon.avod.connectivity.MobileNetworkClass;
import com.amazon.avod.util.DLog;
import com.google.common.base.Strings;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MultiNetworkHistoryManager implements NetworkHistoryManager {
    private final ConnectivityManager mConnectivityManager;
    private final NetworkHistoryPersistence mPersistence;
    private final TelephonyManager mTelephonyManager;
    private final WifiManager mWifiManager;

    public MultiNetworkHistoryManager(Context context) {
        this(new NetworkHistoryPersistence(context), (ConnectivityManager) context.getSystemService("connectivity"), (WifiManager) context.getApplicationContext().getSystemService("wifi"), (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE));
    }

    MultiNetworkHistoryManager(NetworkHistoryPersistence networkHistoryPersistence, ConnectivityManager connectivityManager, WifiManager wifiManager, TelephonyManager telephonyManager) {
        this.mPersistence = networkHistoryPersistence;
        this.mConnectivityManager = connectivityManager;
        this.mWifiManager = wifiManager;
        this.mTelephonyManager = telephonyManager;
    }

    @Override // com.amazon.avod.media.framework.network.NetworkHistoryManager
    public NetworkHistory getCurrentNetworkHistory() {
        String currentNetworkName = getCurrentNetworkName();
        if (currentNetworkName != null) {
            return this.mPersistence.getNetworkHistory(currentNetworkName);
        }
        DLog.logf("There is currently no active network, so NetworkHistory cannot be loaded.");
        return null;
    }

    @Override // com.amazon.avod.media.framework.network.NetworkHistoryManager
    public void setNetworkInfo(int i, @Nullable ByteBuffer byteBuffer, long j, long j2) {
        String currentNetworkName = getCurrentNetworkName();
        if (!Strings.isNullOrEmpty(currentNetworkName)) {
            this.mPersistence.putNetworkHistory(new NetworkHistory(currentNetworkName, i, byteBuffer, j, j2));
        } else {
            DLog.warnf("There is currently no active network, so NetworkHistory can not be saved.");
        }
    }

    @Override // com.amazon.avod.media.framework.network.NetworkHistoryManager
    public String getCurrentNetworkName() {
        NetworkInfo activeNetworkInfo;
        WifiInfo connectionInfo;
        ConnectivityManager connectivityManager = this.mConnectivityManager;
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return null;
        }
        int type = activeNetworkInfo.getType();
        if (type == 9) {
            return "ethernet";
        }
        if (type == 1) {
            WifiManager wifiManager = this.mWifiManager;
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return null;
            }
            return String.format(Locale.US, "%s#%s", connectionInfo.getSSID(), connectionInfo.getBSSID());
        }
        if (type == 0 || type == 3 || type == 5 || type == 4 || type == 2) {
            return String.format(Locale.US, "%s#%s", MobileNetworkClass.getClassByTelephonyManager(this.mTelephonyManager).getShortName(), this.mTelephonyManager.getNetworkOperatorName());
        }
        return null;
    }
}
