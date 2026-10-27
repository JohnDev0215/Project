package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.dmls.DMLSExceptions;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum LocatorRequest implements ServiceRequest {
    GetStreamingUrls("getStreamingURLs"),
    GetLicensesForPlayback("getLicensesForPlayback"),
    UpdateStreamingStatus("updateStreamingStatus"),
    TerminateAndUpdateStreamingStatus("terminateAndUpdateStreamingStatus"),
    GetStreamingURLsWithFirstChunkV2("getStreamingURLsWithFirstChunkV2");

    private static final int MAX_RETRY = 2;
    private static final String TAG = LocatorRequest.class.getSimpleName();
    private final String mRequestName;
    private LocatorRequestDispatcher sDispatcher;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public ServiceApi getApiVersion() {
        return null;
    }

    LocatorRequest(String requestName) {
        this.mRequestName = requestName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mRequestName;
    }

    public BaseRequestDispatcher getDispatcher() {
        if (this.sDispatcher == null) {
            this.sDispatcher = new LocatorRequestDispatcher();
        }
        return this.sDispatcher;
    }

    public JSONObject execute() throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, DMLSExceptions.DMLSException {
        return execute(new JSONObject());
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public synchronized JSONObject execute(JSONObject requestParams) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, DMLSExceptions.DMLSException {
        JSONObject jSONObjectDispatch;
        System.currentTimeMillis();
        jSONObjectDispatch = null;
        int i = 0;
        while (true) {
            try {
                try {
                    jSONObjectDispatch = getDispatcher().dispatch(this, requestParams);
                } catch (ServiceException e) {
                    Log.error(TAG, "DMLS Logging: Service Exception while dispatching " + getRequestName(), e);
                    throw new DMLSExceptions.DMLSException("Got a non-DMLS Exception, throwing anyway");
                }
            } catch (DMLSExceptions.StatusCodeException e2) {
                try {
                    if (e2.getStatus() == StatusCode.RETRY) {
                        Log.warning(TAG, "DMLS Logging: Service asking for a retry attempt, %s", e2.getMessage());
                    } else {
                        Log.warning(TAG, "DMLS Logging: Not a retry request: %s", e2.getMessage());
                        throw e2;
                    }
                } catch (DMLSExceptions.StatusCodeException e3) {
                    Log.error(TAG, "DMLS Logging: StatusCode Exception while dispatching " + getRequestName(), e3);
                    throw e3;
                }
            }
            if (jSONObjectDispatch != null) {
                break;
            }
            int i2 = i + 1;
            if (i >= 2) {
                break;
            }
            i = i2;
        }
        if (jSONObjectDispatch == null) {
            Log.warning(TAG, "DMLS Logging: Max retry reached");
            throw new DMLSExceptions.MaxRetryException();
        }
        return jSONObjectDispatch;
    }
}
