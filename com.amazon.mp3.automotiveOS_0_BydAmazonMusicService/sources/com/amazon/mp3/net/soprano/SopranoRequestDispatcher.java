package com.amazon.mp3.net.soprano;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.BaseRequestDispatcher;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.service.ServiceApi;
import com.amazon.mp3.net.service.ServiceRequest;
import kotlin.Metadata;
import org.json.JSONObject;

/* JADX INFO: compiled from: SopranoRequestDispatcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0014J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0014¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoRequestDispatcher;", "Lcom/amazon/mp3/net/BaseRequestDispatcher;", "()V", "createRequestBuilder", "Lcom/amazon/mp3/net/HttpRequestBuilder;", "requestType", "Lcom/amazon/mp3/net/service/ServiceRequest;", "requestBody", "Lorg/json/JSONObject;", "getClient", "Lcom/amazon/mp3/net/JsonHttpClient;", "getEndPointURL", "Lcom/amazon/mp3/environment/url/EndPointURL;", "version", "Lcom/amazon/mp3/net/service/ServiceApi;", "getServiceName", "", "getServicePackage", "getTag", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SopranoRequestDispatcher extends BaseRequestDispatcher {
    private static final String SERVICE_PACKAGE = "com.amazon.soprano";
    private static final String TAG = SopranoRequestDispatcher.class.getSimpleName();
    private static final String SERVICE_NAME = "SopranoServiceExternal";

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected EndPointURL getEndPointURL(ServiceApi version) {
        EndPointURL endPointURL = Environment.SOPRANOS_URL.get();
        if (version != null && endPointURL != null) {
            endPointURL.appendPath(version.getPath());
        }
        return endPointURL;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServicePackage() {
        return SERVICE_PACKAGE;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected String getTag() {
        return TAG;
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected JsonHttpClient getClient() {
        return new SopranoHttpClient();
    }

    @Override // com.amazon.mp3.net.BaseRequestDispatcher
    protected HttpRequestBuilder createRequestBuilder(ServiceRequest requestType, JSONObject requestBody) {
        return createCoralRpcRequest(requestType, requestBody);
    }
}
