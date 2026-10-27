package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.DeadSystemException;
import android.os.PowerManager;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.CodecType;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.NetworkType;
import com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.device.utils.thirdparty.NetworkManager;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractHTTPMetricsTransport implements MetricsTransport, TransportStateNotifier {
    protected final String TAG = getClass().getSimpleName();
    protected final Context mContext;
    protected final DeviceUtil mDeviceUtil;
    protected final MetricsTransport mFallbackTransport;
    protected final MetricsConfiguration mMetricsConfiguration;
    protected final URL mURL;

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void close() {
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier
    public void listenForTransportWarmed(TransportStateNotifier.TransportWarmedListener transportWarmedListener) {
    }

    protected abstract HttpURLConnection openConnection(URL url) throws IOException;

    protected abstract boolean signRequest(HttpURLConnection httpURLConnection);

    public AbstractHTTPMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        validate(context, metricsConfiguration, deviceUtil);
        this.mContext = context;
        this.mMetricsConfiguration = metricsConfiguration;
        this.mDeviceUtil = deviceUtil;
        this.mURL = resolveEndpoint();
        this.mFallbackTransport = metricsTransport;
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public UploadResult transmit(byte[] bArr) throws Exception {
        UploadResult uploadResultAttemptTransmit = attemptTransmit(bArr);
        if (this.mFallbackTransport == null || uploadResultAttemptTransmit.getUploadStatus() != 5) {
            return uploadResultAttemptTransmit;
        }
        Log.i(this.TAG, "Attempting transmission using fallback transport");
        return this.mFallbackTransport.transmit(bArr);
    }

    protected UploadResult attemptTransmit(byte[] bArr) throws Exception {
        PowerManager.WakeLock wakeLockAcquireWakeLock;
        WifiManager.WifiLock wifiLock;
        WifiManager.WifiLock wifiLockAcquireWifiLock;
        if (bArr == null || bArr.length == 0) {
            Log.e(this.TAG, "Transmitted metricBatch cannot be null or empty");
            return new UploadResult(8);
        }
        try {
            if (!isWifiAvailable() && !isEthernetAvailable() && !isWANAvailable()) {
                Log.w(this.TAG, "Aborting metrics transmission because there is no usable connection");
                return new UploadResult(7);
            }
            HttpConfiguration httpConfiguration = this.mMetricsConfiguration.getHttpConfiguration();
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    wakeLockAcquireWakeLock = acquireWakeLock();
                    try {
                        wifiLockAcquireWifiLock = acquireWifiLock();
                    } catch (AuthenticatedURLConnection.NoCredentialsException e) {
                        e = e;
                        wifiLockAcquireWifiLock = null;
                    } catch (IllegalStateException e2) {
                        e = e2;
                        wifiLockAcquireWifiLock = null;
                    } catch (SocketTimeoutException e3) {
                        e = e3;
                        wifiLockAcquireWifiLock = null;
                    } catch (UnknownHostException e4) {
                        e = e4;
                        wifiLockAcquireWifiLock = null;
                    } catch (IOException e5) {
                        e = e5;
                        wifiLockAcquireWifiLock = null;
                    } catch (Exception e6) {
                        e = e6;
                        wifiLockAcquireWifiLock = null;
                    } catch (Throwable th) {
                        th = th;
                        wifiLock = null;
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        throw th;
                    }
                    try {
                        HttpURLConnection httpURLConnectionOpenConnection = openConnection(this.mURL);
                        httpURLConnectionOpenConnection.setConnectTimeout(httpConfiguration.getConnectTimeout());
                        httpURLConnectionOpenConnection.setReadTimeout(httpConfiguration.getConnectTimeout());
                        httpURLConnectionOpenConnection.setFixedLengthStreamingMode(bArr.length);
                        httpURLConnectionOpenConnection.setDoOutput(true);
                        httpURLConnectionOpenConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
                        addProtobufHeaders(httpURLConnectionOpenConnection);
                        if (!signRequest(httpURLConnectionOpenConnection)) {
                            UploadResult uploadResult = new UploadResult(5);
                            httpURLConnectionOpenConnection.disconnect();
                            Log.i(this.TAG, "Manually releasing wifi lock");
                            if (wifiLockAcquireWifiLock != null) {
                                NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                            }
                            Log.i(this.TAG, "Manually releasing wake lock");
                            if (wakeLockAcquireWakeLock != null) {
                                NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                            }
                            return uploadResult;
                        }
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnectionOpenConnection.getOutputStream());
                        bufferedOutputStream.write(bArr);
                        bufferedOutputStream.flush();
                        UploadResult uploadResultHandleResponse = handleResponse(httpURLConnectionOpenConnection);
                        httpURLConnectionOpenConnection.disconnect();
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResultHandleResponse;
                    } catch (AuthenticatedURLConnection.NoCredentialsException e7) {
                        e = e7;
                        Log.w(this.TAG, "NoCredentialsException thrown while uploading", e);
                        UploadResult uploadResult2 = new UploadResult(5, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult2;
                    } catch (IllegalStateException e8) {
                        e = e8;
                        Log.w(this.TAG, "IllegalStateException thrown while uploading", e);
                        UploadResult uploadResult3 = new UploadResult(9, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult3;
                    } catch (SocketTimeoutException e9) {
                        e = e9;
                        Log.w(this.TAG, "SocketTimeoutException thrown while uploading", e);
                        UploadResult uploadResult4 = new UploadResult(2, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult4;
                    } catch (UnknownHostException e10) {
                        e = e10;
                        Log.w(this.TAG, "UnknownHostException thrown while uploading", e);
                        UploadResult uploadResult5 = new UploadResult(7, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult5;
                    } catch (IOException e11) {
                        e = e11;
                        Log.w(this.TAG, "IOException thrown while uploading", e);
                        if (!isConnectionRefusedError(e)) {
                            UploadResult uploadResult6 = new UploadResult(10, e);
                            if (0 != 0) {
                                httpURLConnection.disconnect();
                            }
                            Log.i(this.TAG, "Manually releasing wifi lock");
                            if (wifiLockAcquireWifiLock != null) {
                                NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                            }
                            Log.i(this.TAG, "Manually releasing wake lock");
                            if (wakeLockAcquireWakeLock != null) {
                                NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                            }
                            return uploadResult6;
                        }
                        Log.w(this.TAG, "IOException is an ECONNREFUSED. Reporting unusable connection.");
                        UploadResult uploadResult7 = new UploadResult(7, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult7;
                    } catch (Exception e12) {
                        e = e12;
                        if (!(e instanceof DeadSystemException)) {
                            throw e;
                        }
                        Log.w(this.TAG, "DeadSystemException thrown while uploading", e);
                        UploadResult uploadResult8 = new UploadResult(6, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLockAcquireWifiLock != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLockAcquireWifiLock);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLockAcquireWakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLockAcquireWakeLock);
                        }
                        return uploadResult8;
                    }
                } catch (AuthenticatedURLConnection.NoCredentialsException e13) {
                    e = e13;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (IllegalStateException e14) {
                    e = e14;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (SocketTimeoutException e15) {
                    e = e15;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (UnknownHostException e16) {
                    e = e16;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (IOException e17) {
                    e = e17;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (Exception e18) {
                    e = e18;
                    wakeLockAcquireWakeLock = null;
                    wifiLockAcquireWifiLock = null;
                } catch (Throwable th2) {
                    th = th2;
                    wakeLockAcquireWakeLock = null;
                    wifiLock = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e19) {
            if (!(e19 instanceof DeadSystemException)) {
                throw e19;
            }
            Log.w(this.TAG, "DeadSystemException thrown while checking network available", e19);
            return new UploadResult(6, e19);
        }
    }

    protected boolean isConnectionRefusedError(IOException iOException) {
        if (!(iOException instanceof ConnectException)) {
            return false;
        }
        Throwable cause = iOException.getCause();
        if ((cause instanceof ErrnoException) && OsConstants.ECONNREFUSED == ((ErrnoException) cause).errno) {
            return true;
        }
        String message = iOException.getMessage();
        return message != null && message.contains("ECONNREFUSED");
    }

    protected URL resolveEndpoint() {
        try {
            return new URL(this.mMetricsConfiguration.getHttpConfiguration().getUrlEndpoint() + "/metricsBatch");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL provided: " + this.mMetricsConfiguration.getHttpConfiguration().getUrlEndpoint(), e);
        }
    }

    protected boolean isWifiAvailable() throws IllegalStateException {
        return this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.WIFI) && NetworkManager.instance(this.mContext).isWifiConnected();
    }

    protected boolean isEthernetAvailable() throws IllegalStateException {
        return this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.ETHERNET) && NetworkManager.instance(this.mContext).isEthernetConnected();
    }

    protected boolean isWANAvailable() throws IllegalStateException {
        boolean zContains = this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.WAN);
        String strFetchWANSupportedDeviceMode = this.mDeviceUtil.fetchWANSupportedDeviceMode();
        if (strFetchWANSupportedDeviceMode != null) {
            zContains = zContains || strFetchWANSupportedDeviceMode.equals(this.mDeviceUtil.fetchDeviceMode());
        }
        return zContains && NetworkManager.instance(this.mContext).isWanConnected();
    }

    private void validate(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil) {
        if (context == null) {
            throw new IllegalArgumentException("Context may not be null");
        }
        if (metricsConfiguration == null) {
            throw new IllegalArgumentException("MetricsConfiguration may not be null");
        }
        if (deviceUtil == null) {
            throw new IllegalArgumentException("DeviceUtil may not be null");
        }
    }

    private UploadResult handleResponse(HttpURLConnection httpURLConnection) throws Throwable {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        consumeInputStream(httpURLConnection);
        if (responseCode != 400 && responseCode != 401) {
            switch (responseCode) {
                case 200:
                case 201:
                case 202:
                    Log.i(this.TAG, String.format("Successfully uploaded metrics data; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                    return new UploadResult(1, responseCode);
                default:
                    switch (responseCode) {
                        case 403:
                        case 404:
                        case 405:
                            break;
                        default:
                            switch (responseCode) {
                                case 500:
                                case 501:
                                case 502:
                                case 503:
                                case 504:
                                    Log.e(this.TAG, String.format("Server error while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                                    return new UploadResult(4, responseCode);
                                default:
                                    Log.i(this.TAG, String.format("Unexpected response code while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                                    return new UploadResult(6, responseCode);
                            }
                    }
                    break;
            }
        }
        Log.e(this.TAG, String.format("Client error while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
        return new UploadResult(3, responseCode);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void consumeInputStream(java.net.HttpURLConnection r9) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.String r0 = "IOException closing response InputStream"
            r1 = 0
            java.io.InputStream r9 = r9.getInputStream()     // Catch: java.lang.Throwable -> L8 java.io.IOException -> Lb java.io.FileNotFoundException -> L18
            goto L23
        L8:
            r9 = move-exception
            goto L7f
        Lb:
            r2 = move-exception
            java.lang.String r3 = r8.TAG     // Catch: java.lang.Throwable -> L8
            java.lang.String r4 = "IOException opening response InputStream"
            android.util.Log.w(r3, r4, r2)     // Catch: java.lang.Throwable -> L8
            java.io.InputStream r9 = r9.getErrorStream()     // Catch: java.lang.Throwable -> L8
            goto L23
        L18:
            java.lang.String r2 = r8.TAG     // Catch: java.lang.Throwable -> L8
            java.lang.String r3 = "No response from connection InputStream"
            android.util.Log.d(r2, r3)     // Catch: java.lang.Throwable -> L8
            java.io.InputStream r9 = r9.getErrorStream()     // Catch: java.lang.Throwable -> L8
        L23:
            if (r9 == 0) goto L72
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L8
            r1 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r1]     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            r3.<init>()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.String r5 = "UTF-8"
            r4.<init>(r9, r5)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            r9 = 0
            r5 = r9
        L3c:
            int r6 = r4.read(r1)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            if (r6 < 0) goto L4b
            r7 = 4
            if (r5 >= r7) goto L4b
            r3.append(r1, r9, r6)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            int r5 = r5 + 1
            goto L3c
        L4b:
            java.lang.String r9 = r8.TAG     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            r1.<init>()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.String r4 = "Response body: "
            r1.append(r4)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            r1.append(r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            android.util.Log.i(r9, r1)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L69
            goto L71
        L66:
            r9 = move-exception
            r1 = r2
            goto L7f
        L69:
            r9 = move-exception
            java.lang.String r1 = r8.TAG     // Catch: java.lang.Throwable -> L66
            java.lang.String r3 = "IOException reading response InputStream"
            android.util.Log.e(r1, r3, r9)     // Catch: java.lang.Throwable -> L66
        L71:
            r1 = r2
        L72:
            if (r1 == 0) goto L7e
            r1.close()     // Catch: java.io.IOException -> L78
            goto L7e
        L78:
            r9 = move-exception
            java.lang.String r1 = r8.TAG
            android.util.Log.e(r1, r0, r9)
        L7e:
            return
        L7f:
            if (r1 == 0) goto L8b
            r1.close()     // Catch: java.io.IOException -> L85
            goto L8b
        L85:
            r1 = move-exception
            java.lang.String r2 = r8.TAG
            android.util.Log.e(r2, r0, r1)
        L8b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport.consumeInputStream(java.net.HttpURLConnection):void");
    }

    private void addProtobufHeaders(HttpURLConnection httpURLConnection) {
        CodecConfiguration codecConfiguration = this.mMetricsConfiguration.getCodecConfiguration();
        if (codecConfiguration.getCodecType() == CodecType.PROTOCOL_BUFFERS) {
            httpURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/octet-stream");
            httpURLConnection.setRequestProperty("x-codec-format", codecConfiguration.getCodecType().getName());
            httpURLConnection.setRequestProperty("x-codec-version", codecConfiguration.getCodecVersion());
        }
    }

    private PowerManager.WakeLock acquireWakeLock() {
        long wakeLockTimeout = this.mMetricsConfiguration.getHttpConfiguration().getWakeLockTimeout();
        Log.i(this.TAG, "Acquiring wake lock with timeout: " + wakeLockTimeout + "ms");
        PowerManager.WakeLock wakeLockCreateWakeLock = NetworkManager.instance(this.mContext).createWakeLock("HTTPMetricsTransportWakeLock");
        if (wakeLockCreateWakeLock != null) {
            wakeLockCreateWakeLock.setReferenceCounted(false);
            NetworkManager.instance(this.mContext).acquireWakeLock(wakeLockCreateWakeLock, wakeLockTimeout);
        }
        return wakeLockCreateWakeLock;
    }

    private WifiManager.WifiLock acquireWifiLock() {
        Log.i(this.TAG, "Acquiring wifi lock");
        WifiManager.WifiLock wifiLockCreateWifiLock = NetworkManager.instance(this.mContext).createWifiLock("HTTPMetricsTransport");
        NetworkManager.instance(this.mContext).acquireWifiLock(wifiLockCreateWifiLock);
        return wifiLockCreateWifiLock;
    }
}
