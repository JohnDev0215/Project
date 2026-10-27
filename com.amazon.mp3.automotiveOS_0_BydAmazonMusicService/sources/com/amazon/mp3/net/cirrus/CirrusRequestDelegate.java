package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusRequestDelegate implements CirrusRequest {
    public static final String TAG = "CirrusRequestDelegate";
    private CirrusApi mApiVersion;
    private CirrusRequestDispatcher mDispatcher;
    private String mName;

    CirrusRequestDelegate(final String name, final CirrusApi apiVersion, CirrusRequestDispatcher dispatcher) {
        this.mName = name;
        this.mApiVersion = apiVersion;
        this.mDispatcher = dispatcher;
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest, com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public CirrusApi getApiVersion() {
        return this.mApiVersion;
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest
    public CirrusRequestDispatcher getDispatcher() {
        return this.mDispatcher;
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest
    public JSONObject execute() throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        return execute(new JSONObject());
    }

    @Override // com.amazon.mp3.net.cirrus.CirrusRequest, com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(final JSONObject requestParams) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        try {
            return getDispatcher().dispatch(this, requestParams);
        } catch (CirrusExceptions.CirrusException e) {
            Log.error(TAG, "Cirrus Exception while dispatching " + getRequestName(), e);
            throw e;
        } catch (ServiceException e2) {
            Log.error(TAG, "Service Exception while dispatching " + getRequestName(), e2);
            throw new CirrusExceptions.CirrusException("Got a non-Cirrus Exception, throwing anyway");
        }
    }
}
