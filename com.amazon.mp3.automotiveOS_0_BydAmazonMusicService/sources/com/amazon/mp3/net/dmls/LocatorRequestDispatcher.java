package com.amazon.mp3.net.dmls;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LocatorRequestDispatcher extends BaseRequestDispatcher {
    private static final String TAG = "LocatorRequestDispatcher";

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return "DigitalMusicLocatorServiceExternal";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return "com.amazon.digitalmusiclocator";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi version) {
        EndPointURL endPointURL = Environment.DMLS.get();
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
        return new DMLSHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject request) {
        return createCoralRpcRequest(requestType, request, false);
    }
}
