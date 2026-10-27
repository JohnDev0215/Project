package com.amazon.mp3.net.stratus;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class StratusRequestDispatcher extends BaseRequestDispatcher {
    private static final String TAG = "StratusRequestDispatcher";

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return "StratusServiceExternal";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return "com.amazon.stratus";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi version) {
        EndPointURL endPointURL = Environment.STRATUS.get();
        if (version != null) {
            endPointURL.appendPath(version.getPath());
        }
        return endPointURL;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getTag() {
        return TAG;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected JsonHttpClient getClient() {
        return new StratusHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject requestBody) {
        return createCoralRpcRequest(requestType, requestBody);
    }
}
