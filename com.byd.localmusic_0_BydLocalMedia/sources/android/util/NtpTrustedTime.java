package android.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.SntpClient;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public class NtpTrustedTime implements TrustedTime {
    private static final boolean LOGD = true;
    private static final String TAG = "NtpTrustedTime";
    private static NtpTrustedTime sSingleton;
    private static HashMap<String, NtpTrustedTime> sSingletonMap;
    private final Context mContext;
    private volatile TimeResult mTimeResult;
    private final Supplier<ConnectivityManager> mConnectivityManagerSupplier = new Supplier<ConnectivityManager>() { // from class: android.util.NtpTrustedTime.1
        private ConnectivityManager mConnectivityManager;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.function.Supplier
        public synchronized ConnectivityManager get() {
            if (this.mConnectivityManager == null) {
                this.mConnectivityManager = (ConnectivityManager) NtpTrustedTime.this.mContext.getSystemService(ConnectivityManager.class);
            }
            return this.mConnectivityManager;
        }
    };
    private boolean isFirstTimeRefreshNtpTime = true;
    private int ntpServerIndex = -1;

    public static class TimeResult {
        private final long mCertaintyMillis;
        private final long mElapsedRealtimeMillis;
        private final long mTimeMillis;

        public TimeResult(long j, long j2, long j3) {
            this.mTimeMillis = j;
            this.mElapsedRealtimeMillis = j2;
            this.mCertaintyMillis = j3;
        }

        public long getTimeMillis() {
            return this.mTimeMillis;
        }

        public long getElapsedRealtimeMillis() {
            return this.mElapsedRealtimeMillis;
        }

        public long getCertaintyMillis() {
            return this.mCertaintyMillis;
        }

        public long currentTimeMillis() {
            return this.mTimeMillis + getAgeMillis();
        }

        public long getAgeMillis() {
            return SystemClock.elapsedRealtime() - this.mElapsedRealtimeMillis;
        }

        public String toString() {
            return "TimeResult{mTimeMillis=" + this.mTimeMillis + ", mElapsedRealtimeMillis=" + this.mElapsedRealtimeMillis + ", mCertaintyMillis=" + this.mCertaintyMillis + '}';
        }
    }

    private NtpTrustedTime(Context context) {
        this.mContext = (Context) Objects.requireNonNull(context);
    }

    public static synchronized NtpTrustedTime getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new NtpTrustedTime(context.getApplicationContext());
        }
        return sSingleton;
    }

    public static synchronized NtpTrustedTime getInstance(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        if (sSingletonMap == null) {
            sSingletonMap = new HashMap<>();
            NtpTrustedTime ntpTrustedTime = new NtpTrustedTime(applicationContext);
            sSingletonMap.put(str, ntpTrustedTime);
            return ntpTrustedTime;
        }
        NtpTrustedTime ntpTrustedTime2 = sSingletonMap.get(str);
        if (ntpTrustedTime2 != null) {
            return ntpTrustedTime2;
        }
        NtpTrustedTime ntpTrustedTime3 = new NtpTrustedTime(applicationContext);
        sSingletonMap.put(str, ntpTrustedTime3);
        return ntpTrustedTime3;
    }

    private boolean forceRefreshInternal(Network network) {
        synchronized (this) {
            ArrayList<NtpConnectionInfo> ntpConnectionInfoList = getNtpConnectionInfoList();
            if (ntpConnectionInfoList != null) {
                return refreshTimeWithNtpServerList(ntpConnectionInfoList, network);
            }
            NtpConnectionInfo ntpConnectionInfo = getNtpConnectionInfo();
            if (ntpConnectionInfo == null) {
                Log.d(TAG, "forceRefreshByApn3: invalid server config");
                return false;
            }
            ConnectivityManager connectivityManager = this.mConnectivityManagerSupplier.get();
            if (connectivityManager == null) {
                Log.d(TAG, "forceRefreshByApn3: no ConnectivityManager");
                return false;
            }
            if (network == null) {
                network = connectivityManager.getActiveNetwork();
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo != null && networkInfo.isConnected()) {
                Log.d(TAG, "forceRefreshByApn3() from cache miss");
                SntpClient sntpClient = new SntpClient();
                if (!sntpClient.requestTime(ntpConnectionInfo.getServer(), ntpConnectionInfo.getTimeoutMillis(), network)) {
                    return false;
                }
                this.mTimeResult = new TimeResult(sntpClient.getNtpTime(), sntpClient.getNtpTimeReference(), sntpClient.getRoundTripTime() / 2);
                return true;
            }
            Log.d(TAG, "forceRefreshByApn3: no connectivity");
            return false;
        }
    }

    @Override // android.util.TrustedTime
    public boolean forceRefresh() {
        return forceRefreshInternal(null);
    }

    public boolean forceRefresh(Network network) {
        return forceRefreshInternal(network);
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public boolean hasCache() {
        return this.mTimeResult != null;
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public long getCacheAge() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult != null) {
            return SystemClock.elapsedRealtime() - timeResult.getElapsedRealtimeMillis();
        }
        return Long.MAX_VALUE;
    }

    @Override // android.util.TrustedTime
    @Deprecated
    public long currentTimeMillis() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult == null) {
            throw new IllegalStateException("Missing authoritative time source");
        }
        Log.d(TAG, "currentTimeMillis() cache hit");
        return timeResult.currentTimeMillis();
    }

    @Deprecated
    public long getCachedNtpTime() {
        Log.d(TAG, "getCachedNtpTime() cache hit");
        TimeResult timeResult = this.mTimeResult;
        if (timeResult == null) {
            return 0L;
        }
        return timeResult.getTimeMillis();
    }

    @Deprecated
    public long getCachedNtpTimeReference() {
        TimeResult timeResult = this.mTimeResult;
        if (timeResult == null) {
            return 0L;
        }
        return timeResult.getElapsedRealtimeMillis();
    }

    public TimeResult getCachedTimeResult() {
        return this.mTimeResult;
    }

    private static class NtpConnectionInfo {
        private final String mServer;
        private final int mTimeoutMillis;

        NtpConnectionInfo(String str, int i) {
            this.mServer = (String) Objects.requireNonNull(str);
            this.mTimeoutMillis = i;
        }

        public String getServer() {
            return this.mServer;
        }

        int getTimeoutMillis() {
            return this.mTimeoutMillis;
        }
    }

    private NtpConnectionInfo getNtpConnectionInfo() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Resources resources = this.mContext.getResources();
        String string = resources.getString(R.string.config_ntpServer);
        int integer = resources.getInteger(R.integer.config_ntpTimeout);
        String string2 = Settings.Global.getString(contentResolver, Settings.Global.NTP_SERVER);
        int i = Settings.Global.getInt(contentResolver, Settings.Global.NTP_TIMEOUT, integer);
        if (string2 != null) {
            string = string2;
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new NtpConnectionInfo(string, i);
    }

    private ArrayList<NtpConnectionInfo> getNtpConnectionInfoList() {
        Resources resources = this.mContext.getResources();
        String[] stringArray = resources.getStringArray(R.array.config_ntpServers);
        if (stringArray == null || stringArray.length == 0) {
            return null;
        }
        int i = Settings.Global.getInt(this.mContext.getContentResolver(), Settings.Global.NTP_TIMEOUT, resources.getInteger(R.integer.config_ntpTimeout));
        ArrayList<NtpConnectionInfo> arrayList = new ArrayList<>();
        for (String str : stringArray) {
            arrayList.add(new NtpConnectionInfo(str, i));
        }
        return arrayList;
    }

    private boolean refreshTimeWithNtpServerList(ArrayList<NtpConnectionInfo> arrayList, Network network) {
        ConnectivityManager connectivityManager = this.mConnectivityManagerSupplier.get();
        if (connectivityManager == null) {
            Log.d(TAG, "forceRefresh: no ConnectivityManager");
            return false;
        }
        if (network == null) {
            network = connectivityManager.getActiveNetwork();
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
        if (networkInfo == null || !networkInfo.isConnected()) {
            Log.d(TAG, "forceRefresh: no connectivity");
            return false;
        }
        if (this.isFirstTimeRefreshNtpTime) {
            this.isFirstTimeRefreshNtpTime = false;
            Log.d(TAG, "refreshTimeWithAllNtpServer.");
            return refreshTimeWithAllNtpServer(arrayList, network);
        }
        int i = this.ntpServerIndex + 1;
        this.ntpServerIndex = i;
        if (i < 0 || i >= arrayList.size()) {
            this.ntpServerIndex = 0;
        }
        SntpClient sntpClient = new SntpClient();
        String server = arrayList.get(this.ntpServerIndex).getServer();
        int timeoutMillis = arrayList.get(this.ntpServerIndex).getTimeoutMillis();
        Log.d(TAG, "forceRefresh time with server:" + server + ", timeout:" + timeoutMillis + ", index:" + this.ntpServerIndex);
        if (!sntpClient.requestTime(server, timeoutMillis, network)) {
            return false;
        }
        this.mTimeResult = new TimeResult(sntpClient.getNtpTime(), sntpClient.getNtpTimeReference(), sntpClient.getRoundTripTime() / 2);
        return true;
    }

    private boolean refreshTimeWithAllNtpServer(ArrayList<NtpConnectionInfo> arrayList, Network network) {
        SntpClient sntpClient = new SntpClient();
        for (NtpConnectionInfo ntpConnectionInfo : arrayList) {
            String server = ntpConnectionInfo.getServer();
            int timeoutMillis = ntpConnectionInfo.getTimeoutMillis();
            Log.d(TAG, "forceRefresh with server:" + server + ", timeout:" + timeoutMillis);
            if (sntpClient.requestTime(server, timeoutMillis, network)) {
                this.mTimeResult = new TimeResult(sntpClient.getNtpTime(), sntpClient.getNtpTimeReference(), sntpClient.getRoundTripTime() / 2);
                return true;
            }
        }
        return false;
    }
}
