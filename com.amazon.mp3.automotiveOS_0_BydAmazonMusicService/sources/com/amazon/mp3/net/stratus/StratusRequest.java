package com.amazon.mp3.net.stratus;

import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.util.Log;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public enum StratusRequest implements ServiceRequest {
    RetrieveDevice("retrieveDevice"),
    AuthorizeDevice("authorizeDevice"),
    IsFeatureLaunched("isFeatureLaunched"),
    RetrievePrivacyCompliancePreferences("retrievePrivacyCompliancePreferences"),
    UpdatePrivacyCompliancePreferences("updatePrivacyCompliancePreferences");

    public static final String TAG = StratusRequest.class.getSimpleName();
    private final String mRequestName;
    private BaseRequestDispatcher sDispatcher;

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public ServiceApi getApiVersion() {
        return null;
    }

    StratusRequest(final String requestName) {
        this.mRequestName = requestName;
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public String getRequestName() {
        return this.mRequestName;
    }

    public BaseRequestDispatcher getDispatcher() {
        if (this.sDispatcher == null) {
            this.sDispatcher = new StratusRequestDispatcher();
        }
        return this.sDispatcher;
    }

    public JSONObject execute() throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        return execute(new JSONObject());
    }

    @Override // com.amazon.mp3.net.service.ServiceRequest
    public JSONObject execute(final JSONObject requestParams) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONObject jSONObjectDispatch = getDispatcher().dispatch(this, requestParams);
        Log.debugBuildOnly(TAG, "StratusRequest request %s return in %dms \n with parameters %s \n Response %s", getRequestName(), Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), requestParams, jSONObjectDispatch);
        return jSONObjectDispatch;
    }
}
