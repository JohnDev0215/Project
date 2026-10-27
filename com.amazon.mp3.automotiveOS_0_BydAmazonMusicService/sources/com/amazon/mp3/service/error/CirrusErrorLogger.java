package com.amazon.mp3.service.error;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.cast.MediaError;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class CirrusErrorLogger {
    private static final String TAG = "CirrusErrorLogger";
    private static CirrusErrorLogger sInstance;
    private final Context mContext = AmazonApplication.getContext();
    private Handler mHandler;
    private HandlerThread mThread;

    private CirrusErrorLogger() {
    }

    public static synchronized CirrusErrorLogger getInstance() {
        if (sInstance == null) {
            sInstance = new CirrusErrorLogger();
        }
        return sInstance;
    }

    public void log(final CirrusError error, final String description) {
        new Thread(new Runnable() { // from class: com.amazon.mp3.service.error.CirrusErrorLogger.1
            @Override // java.lang.Runnable
            public void run() {
                CirrusErrorLogger.this.logInternal(error, description);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void logInternal(CirrusError error, String description) {
        Log.error(TAG, "CirrusErrorLogger: %s: %s", error, description);
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            CirrusErrorRequest cirrusErrorRequest = new CirrusErrorRequest(error, description);
            startHandlerThreadIfStopped();
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1, cirrusErrorRequest));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void killHandlerThread() {
        HandlerThread handlerThread = this.mThread;
        if (handlerThread == null) {
            return;
        }
        Looper looper = handlerThread.getLooper();
        if (looper != null) {
            looper.quit();
        }
        this.mThread.interrupt();
        this.mHandler = null;
        this.mThread = null;
    }

    private synchronized void startHandlerThreadIfStopped() {
        if (this.mThread == null || this.mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("CirrusErrorLoggingThread", 10);
            this.mThread = handlerThread;
            handlerThread.start();
            this.mHandler = new ErrorLoggerHandler(this.mThread.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void getAndSubmitQueuedRequest(CirrusErrorRequest request) {
        if (!submitReportEventRequest(request)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(3, request));
        } else {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(4));
        }
    }

    private boolean submitReportEventRequest(CirrusErrorRequest request) {
        if (AccountCredentialUtil.get(this.mContext).isSignedOut()) {
            return false;
        }
        try {
            CirrusV3Request.ReportEvent.execute(buildCirrusErrorReportRequest(request));
            return true;
        } catch (AbstractHttpClient.HttpClientException e) {
            Log.error(TAG, "Network Error when call reportEvent: %s", e.getMessage());
            return false;
        } catch (CirrusExceptions.CirrusException e2) {
            Log.error(TAG, "Cirrus Error when call reportEvent: %s", e2.getMessage());
            BlueMoonExceptionsUtil.disableBlueMoonException();
            return false;
        } catch (ServiceException e3) {
            Log.error(TAG, "Service Exception when call reportEvent: $s", e3.getMessage());
            return false;
        }
    }

    private JSONObject buildCirrusErrorReportRequest(CirrusErrorRequest request) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("customerInfo", AccountCredentialUtil.get().buildCirrusCustomerInfo());
            jSONObject.put("eventName", request.mError.getCirrusErrorKey());
            jSONObject.put("eventDescription", request.mDescription);
            jSONObject.put("eventSeverity", MediaError.ERROR_TYPE_ERROR);
            jSONObject.put("eventCount", 1);
            jSONObject.put("clientVersion", Build.MODEL + JsonPointer.SEPARATOR + ApplicationAttributes.getInstance(this.mContext).getVersion());
        } catch (JSONException unused) {
            Log.error(TAG, "Cannot create cirrus error report request");
        }
        return jSONObject;
    }

    private static class CirrusErrorRequest {
        public String mDescription;
        public CirrusError mError;
        public int mTimeToLive = 10;

        public CirrusErrorRequest(CirrusError error, String description) {
            this.mError = error;
            this.mDescription = description;
        }
    }

    private class ErrorLoggerHandler extends Handler {
        public ErrorLoggerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CirrusErrorRequest cirrusErrorRequest;
            int i;
            removeMessages(2);
            int i2 = msg.what;
            if (i2 == 1) {
                CirrusErrorLogger.this.getAndSubmitQueuedRequest((CirrusErrorRequest) msg.obj);
                sendDelayedStopMessage();
                return;
            }
            if (i2 == 2) {
                CirrusErrorLogger.this.killHandlerThread();
                return;
            }
            if (i2 != 3) {
                if (i2 != 4) {
                    return;
                }
                sendDelayedStopMessage();
            } else {
                Object obj = msg.obj;
                if ((obj instanceof CirrusErrorRequest) && (i = (cirrusErrorRequest = (CirrusErrorRequest) obj).mTimeToLive) > 0) {
                    cirrusErrorRequest.mTimeToLive = i - 1;
                    sendMessageDelayed(obtainMessage(1, obj), 8000L);
                }
                sendDelayedStopMessage();
            }
        }

        private void sendDelayedStopMessage() {
            sendMessageDelayed(obtainMessage(2), 100000L);
        }
    }
}
