package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum CirrusV3Request implements ServiceRequest {
    Sync(BaseJavaModule.METHOD_TYPE_SYNC),
    InsertTracksByAsin("insertTracksByAsin"),
    GetTracksById("getTracksById"),
    DeleteTracks("deleteTracks"),
    ReportEvent("reportEvent"),
    GetLibraryCounts("getLibraryCounts");

    private static final String TAG = CirrusV3Request.class.getSimpleName();
    private final String mRequestName;
    private CirrusV3RequestDispatcher sDispatcher;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public ServiceApi getApiVersion() {
        return null;
    }

    CirrusV3Request(String requestName) {
        this.mRequestName = requestName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mRequestName;
    }

    public BaseRequestDispatcher getDispatcher() {
        if (this.sDispatcher == null) {
            this.sDispatcher = new CirrusV3RequestDispatcher();
        }
        return this.sDispatcher;
    }

    public JSONObject execute() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(JSONObject requestParams) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObjectDispatch = getDispatcher().dispatch(this, requestParams);
        Log.debugBuildOnly(TAG, "CirrusV3Request request %s return in %dms \n with parameters %s", getRequestName(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), requestParams);
        return jSONObjectDispatch;
    }
}
