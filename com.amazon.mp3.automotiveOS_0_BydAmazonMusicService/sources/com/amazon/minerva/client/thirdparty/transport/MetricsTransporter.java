package com.amazon.minerva.client.thirdparty.transport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes3.dex */
public class MetricsTransporter {
    private static final String TAG = "MetricsTransporter";
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private final PowerManager mPowerManager;
    private ServiceKPIReporter mServiceKPIReporter;
    private final WifiManager mWifiManager;

    public MetricsTransporter(Context context, MetricsConfigurationHelper metricsConfigurationHelper, ServiceKPIReporter serviceKPIReporter) {
        this.mContext = context;
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mWifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mServiceKPIReporter = serviceKPIReporter;
    }

    public UploadResult transmit(SerializedBatch serializedBatch) throws Throwable {
        WifiManager.WifiLock wifiLockCreateWifiLock;
        PowerManager.WakeLock wakeLockCreateWakeLock;
        if (serializedBatch == null || serializedBatch.getBatchContent() == null || serializedBatch.getBatchContent().length == 0) {
            Log.e(TAG, "Transmitted metric batch is invalid.");
            return new UploadResult("UNEXPECTED_ERROR", "Invalid Batch");
        }
        if (!isDeviceOnline()) {
            Log.d(TAG, "Abort transmission due to no usable connection.");
            return new UploadResult("NO_CONNECTION", "No Connection");
        }
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                wifiLockCreateWifiLock = createWifiLock();
                try {
                    acquireWifiLock(wifiLockCreateWifiLock);
                    wakeLockCreateWakeLock = createWakeLock();
                    try {
                        acquireWakeLock(wakeLockCreateWakeLock, this.mMetricsConfigurationHelper.getUploadConfiguration().getWakeLockTimeoutMillis());
                        String urlEndpoint = this.mMetricsConfigurationHelper.getUploadConfiguration().getUrlEndpoint();
                        String hashedDeviceType = CustomDeviceUtil.getInstance().getHashedDeviceType();
                        String str = TAG;
                        Log.i(str, "get hashed deviceType from customDeviceUtil: " + hashedDeviceType);
                        String strReplace = urlEndpoint.replace("{deviceType}", hashedDeviceType).replace("{region}", serializedBatch.getRegion());
                        Log.i(str, "minerva upload url:" + strReplace);
                        HttpURLConnection httpURLConnection2 = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(strReplace).openConnection()));
                        try {
                            String oAuthToken = getOAuthToken();
                            if (oAuthToken != null && !oAuthToken.isEmpty()) {
                                httpURLConnection2.addRequestProperty("x-amz-access-token", oAuthToken);
                                Log.i(str, "Add OAuth token to connection");
                            }
                            httpURLConnection2.setConnectTimeout(this.mMetricsConfigurationHelper.getUploadConfiguration().getConnectTimeoutMillis());
                            httpURLConnection2.setReadTimeout(this.mMetricsConfigurationHelper.getUploadConfiguration().getReadTimeoutMillis());
                            httpURLConnection2.setFixedLengthStreamingMode(serializedBatch.getBatchContent().length);
                            httpURLConnection2.setDoOutput(true);
                            httpURLConnection2.setRequestMethod(FirebasePerformance.HttpMethod.POST);
                            Log.i(str, "connection: " + httpURLConnection2);
                            addHeaders(httpURLConnection2);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                            bufferedOutputStream.write(serializedBatch.getBatchContent());
                            bufferedOutputStream.flush();
                            UploadResult uploadResultHandleResponse = handleResponse(httpURLConnection2);
                            httpURLConnection2.disconnect();
                            Log.i(str, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.getMetricName(), "aminerva", 1L);
                            return uploadResultHandleResponse;
                        } catch (MalformedURLException e) {
                            e = e;
                            httpURLConnection = httpURLConnection2;
                            String str2 = TAG;
                            Log.w(str2, "MalformedURLException thrown when creating endpointURL", e);
                            UploadResult uploadResult = new UploadResult("UNEXPECTED_ERROR", "Invalid EndpointURL");
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            Log.i(str2, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.getMetricName(), "aminerva", 1L);
                            return uploadResult;
                        } catch (IOException e2) {
                            e = e2;
                            httpURLConnection = httpURLConnection2;
                            String str3 = TAG;
                            Log.w(str3, "IOException thrown when uploading metrics", e);
                            UploadResult uploadResult2 = new UploadResult("UNEXPECTED_ERROR", "IOException");
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            Log.i(str3, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.getMetricName(), "aminerva", 1L);
                            return uploadResult2;
                        } catch (Exception e3) {
                            e = e3;
                            httpURLConnection = httpURLConnection2;
                            String str4 = TAG;
                            Log.i(str4, "Exception .... " + e);
                            UploadResult uploadResult3 = new UploadResult("UNEXPECTED_ERROR", "Unknown exception");
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            Log.i(str4, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.getMetricName(), "aminerva", 1L);
                            return uploadResult3;
                        } catch (Throwable th) {
                            th = th;
                            httpURLConnection = httpURLConnection2;
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            Log.i(TAG, "Releasing wake and wifi locks.");
                            releaseWakeLock(wakeLockCreateWakeLock);
                            releaseWifiLock(wifiLockCreateWifiLock);
                            this.mServiceKPIReporter.report(KPIMetric.BATCH_UPLOADED.getMetricName(), "aminerva", 1L);
                            throw th;
                        }
                    } catch (MalformedURLException e4) {
                        e = e4;
                    } catch (IOException e5) {
                        e = e5;
                    } catch (Exception e6) {
                        e = e6;
                    }
                } catch (MalformedURLException e7) {
                    e = e7;
                    wakeLockCreateWakeLock = null;
                } catch (IOException e8) {
                    e = e8;
                    wakeLockCreateWakeLock = null;
                } catch (Exception e9) {
                    e = e9;
                    wakeLockCreateWakeLock = null;
                } catch (Throwable th2) {
                    th = th2;
                    wakeLockCreateWakeLock = null;
                }
            } catch (MalformedURLException e10) {
                e = e10;
                wifiLockCreateWifiLock = null;
                wakeLockCreateWakeLock = null;
            } catch (IOException e11) {
                e = e11;
                wifiLockCreateWifiLock = null;
                wakeLockCreateWakeLock = null;
            } catch (Exception e12) {
                e = e12;
                wifiLockCreateWifiLock = null;
                wakeLockCreateWakeLock = null;
            } catch (Throwable th3) {
                th = th3;
                wifiLockCreateWifiLock = null;
                wakeLockCreateWakeLock = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private String getOAuthToken() {
        try {
            String accessToken = CustomDeviceUtil.getInstance().getOAuthProvider().getAccessToken();
            Log.i(TAG, "Got OAuthToken");
            return accessToken;
        } catch (Exception e) {
            Log.e(TAG, "Exception happens when getting the OAuto Token: " + e);
            return null;
        }
    }

    private boolean isDeviceOnline() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private PowerManager.WakeLock createWakeLock() {
        String str = TAG;
        Log.i(str, "Acquiring wakelock.");
        return this.mPowerManager.newWakeLock(1, str + ":HTTPMetricsTransportWakeLock");
    }

    private void acquireWakeLock(PowerManager.WakeLock wakeLock, long j) {
        if (wakeLock != null) {
            wakeLock.acquire(j);
        }
    }

    private void releaseWakeLock(PowerManager.WakeLock wakeLock) {
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        wakeLock.release();
    }

    private WifiManager.WifiLock createWifiLock() {
        String str = TAG;
        Log.i(str, "Acquiring wifi lock.");
        return this.mWifiManager.createWifiLock(3, str + ":HTTPMetricsTransportWifiLock");
    }

    private void acquireWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock != null) {
            wifiLock.acquire();
        }
    }

    private void releaseWifiLock(WifiManager.WifiLock wifiLock) {
        if (wifiLock == null || !wifiLock.isHeld()) {
            return;
        }
        wifiLock.release();
    }

    private void addHeaders(HttpURLConnection httpURLConnection) {
        String ionFormat = this.mMetricsConfigurationHelper.getUploadConfiguration().getIonFormat();
        String deviceType = CustomDeviceUtil.getInstance().getDeviceType();
        Log.i(TAG, "get deviceType from customDeviceUtil: " + deviceType);
        httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, ionFormat);
        httpURLConnection.setRequestProperty("x-amz-device-type", deviceType);
        httpURLConnection.setRequestProperty(HttpHeader.ACCEPT, ionFormat);
    }

    private UploadResult handleResponse(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseCode == 200) {
            Log.i(TAG, String.format("Successfully uploaded metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult("SUCCESS", responseMessage, consumeResponsePayload(httpURLConnection.getInputStream()));
        }
        if (responseCode >= 500) {
            Log.w(TAG, String.format("Server error when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult("SERVER_ERROR", responseMessage);
        }
        if (responseCode >= 400) {
            Log.e(TAG, String.format("Client error when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
            return new UploadResult("CLIENT_ERROR", responseMessage);
        }
        Log.w(TAG, String.format("Unexpected response code when uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
        return new UploadResult("UNEXPECTED_ERROR", responseMessage);
    }

    private byte[] consumeResponsePayload(InputStream inputStream) throws IOException {
        Log.i(TAG, "Consuming response payload.");
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
