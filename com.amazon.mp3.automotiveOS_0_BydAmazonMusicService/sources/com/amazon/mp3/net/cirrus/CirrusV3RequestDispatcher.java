package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.music.inappmessaging.internal.model.Splash;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusV3RequestDispatcher extends BaseRequestDispatcher {
    private static final String TAG = "CirrusV3RequestDispatcher";

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return "CirrusLibraryServiceExternalV3";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return "com.amazon.cirrus.libraryservice.v3";
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi version) {
        EndPointURL endPointURL = Environment.CIRRUS_V3.get();
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
        return new CirrusHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject requestBody) {
        return createCoralRpcRequest(requestType, requestBody);
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected void setUseHttpsOverride(HttpRequestBuilder builder) {
        builder.setUseHttps(true);
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected void addCustomerInfo(JSONObject object) throws JSONException {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get();
        object.put(Splash.PARAMS_DEVICE_TYPE, accountCredentialStorage.getDeviceType());
        object.put(Splash.PARAMS_DEVICE_ID, accountCredentialStorage.getDeviceId());
    }
}
