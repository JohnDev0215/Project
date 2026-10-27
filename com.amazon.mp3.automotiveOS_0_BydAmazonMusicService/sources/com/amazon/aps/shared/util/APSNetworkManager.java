package com.amazon.aps.shared.util;

import android.content.Context;
import android.util.Log;
import com.amazon.aps.shared.APSAnalytics;
import com.amazon.aps.shared.analytics.APSEvent;
import com.amazon.aps.shared.analytics.APSEventSeverity;
import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes2.dex */
public class APSNetworkManager {
    private static final String TAG = "APSNetworkManager";
    private static APSNetworkManager apsNetworkManager;
    private Context context;
    private final ExecutorService executor = Executors.newFixedThreadPool(1);
    private boolean shutdownInProgress = false;

    private APSNetworkManager(Context context) {
        this.context = context;
        Runtime.getRuntime().addShutdownHook(new Thread() { // from class: com.amazon.aps.shared.util.APSNetworkManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    APSNetworkManager.this.shutdownInProgress = true;
                    Log.d(APSNetworkManager.TAG, "App is shutting down, terminating the thread executor");
                    APSNetworkManager.this.executor.shutdown();
                } catch (RuntimeException e) {
                    Log.e(APSNetworkManager.TAG, "Error in stopping the executor", e);
                }
            }
        });
    }

    public static APSNetworkManager getInstance(Context context) {
        if (apsNetworkManager == null) {
            apsNetworkManager = new APSNetworkManager(context);
        }
        return apsNetworkManager;
    }

    public void uploadEvent(APSEvent aPSEvent) {
        if (aPSEvent.getEventSeverity() == APSEventSeverity.FATAL) {
            uploadFatalEvent(aPSEvent);
        }
    }

    private void uploadFatalEvent(final APSEvent aPSEvent) {
        execute(new Runnable() { // from class: com.amazon.aps.shared.util.APSNetworkManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.lambda$uploadFatalEvent$0(aPSEvent);
            }
        });
    }

    private synchronized void execute(Runnable runnable) {
        try {
        } catch (InternalError e) {
            Log.e(TAG, "Internal error in executing the thread", e);
            handleError(e);
        } catch (RuntimeException e2) {
            Log.e(TAG, "Error running the thread", e2);
        }
        if (!this.shutdownInProgress) {
            this.executor.execute(runnable);
        }
    }

    private void handleError(InternalError internalError) {
        if (internalError.getLocalizedMessage().contains("shutdown")) {
            Log.e(TAG, "Got the shutdown signal", internalError);
        }
    }

    private boolean setConfigsInConnection(HttpsURLConnection httpsURLConnection) {
        try {
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(10000);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setChunkedStreamingMode(0);
            httpsURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
            httpsURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/json");
            httpsURLConnection.setRequestProperty("x-api-key", APSAnalytics.getApiKey());
            httpsURLConnection.connect();
            return true;
        } catch (IOException e) {
            Log.e(TAG, "Error in setting the connection parameter:", e);
            return false;
        }
    }

    private void closeStreamsAndConnection(InputStream inputStream, OutputStream outputStream, HttpsURLConnection httpsURLConnection) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException | RuntimeException e) {
                Log.e(TAG, "Unable to close the out stream", e);
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException | RuntimeException e2) {
                Log.e(TAG, "Unable to close the in stream", e2);
            }
        }
        try {
            httpsURLConnection.disconnect();
        } catch (RuntimeException e3) {
            Log.e(TAG, "Unable to close the url connection", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendData, reason: merged with bridge method [inline-methods] */
    public boolean lambda$uploadFatalEvent$0(APSEvent aPSEvent) throws Throwable {
        HttpsURLConnection httpsURLConnection;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            try {
                httpsURLConnection = (HttpsURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(APSAnalytics.getHttpUrl()).openConnection()));
            } catch (IOException e) {
                e = e;
                httpsURLConnection = null;
                bufferedOutputStream = null;
                Log.e(TAG, "Error in sendData: ", e);
                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                return false;
            } catch (RuntimeException e2) {
                e = e2;
                httpsURLConnection = null;
                bufferedOutputStream = null;
                Log.e(TAG, "Error in sendData: ", e);
                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                return false;
            } catch (Throwable th) {
                th = th;
                httpsURLConnection = null;
                bufferedOutputStream = null;
            }
            try {
                if (setConfigsInConnection(httpsURLConnection)) {
                    bufferedOutputStream = new BufferedOutputStream(httpsURLConnection.getOutputStream());
                    try {
                        String jsonPayload = aPSEvent.toJsonPayload();
                        bufferedOutputStream.write(jsonPayload.getBytes());
                        bufferedOutputStream.flush();
                        String str = TAG;
                        Log.d(str, "Sending the event data: " + jsonPayload);
                        int responseCode = httpsURLConnection.getResponseCode();
                        Log.d(str, "Response code received : " + responseCode);
                        if (responseCode == 200) {
                            byte[] bArr = new byte[1024];
                            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpsURLConnection.getInputStream());
                            try {
                                bufferedInputStream2.read(bArr);
                                Log.d(str, "Response received: " + new String(bArr));
                                closeStreamsAndConnection(bufferedInputStream2, bufferedOutputStream, httpsURLConnection);
                                return true;
                            } catch (IOException e3) {
                                e = e3;
                                bufferedInputStream = bufferedInputStream2;
                                Log.e(TAG, "Error in sendData: ", e);
                                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                                return false;
                            } catch (RuntimeException e4) {
                                e = e4;
                                bufferedInputStream = bufferedInputStream2;
                                Log.e(TAG, "Error in sendData: ", e);
                                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedInputStream = bufferedInputStream2;
                                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                                throw th;
                            }
                        }
                    } catch (IOException e5) {
                        e = e5;
                    } catch (RuntimeException e6) {
                        e = e6;
                    }
                } else {
                    bufferedOutputStream = null;
                }
                closeStreamsAndConnection(null, bufferedOutputStream, httpsURLConnection);
                return false;
            } catch (IOException e7) {
                e = e7;
                bufferedOutputStream = null;
                Log.e(TAG, "Error in sendData: ", e);
                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                return false;
            } catch (RuntimeException e8) {
                e = e8;
                bufferedOutputStream = null;
                Log.e(TAG, "Error in sendData: ", e);
                closeStreamsAndConnection(bufferedInputStream, bufferedOutputStream, httpsURLConnection);
                return false;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
