package com.amazon.avod.ads.http;

import com.amazon.avod.ads.api.AdNetworkException;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.service.ResponseRetryPolicy;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.HttpResponse;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.RequestStrategy;
import com.amazon.bolthttp.Response;
import com.amazon.bolthttp.policy.impl.ConstantBackoffPolicy;
import com.amazonaws.http.HttpHeader;
import java.net.URL;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BoltHttpAdClient extends AdHttpClient {
    private final ServiceClient mServiceClient;

    public BoltHttpAdClient(@Nonnull HttpParameters httpParameters) {
        this(httpParameters, new NoopCookieStore(), ServiceClient.getInstance());
    }

    BoltHttpAdClient(@Nonnull HttpParameters httpParameters, HttpCookieStore httpCookieStore, ServiceClient serviceClient) {
        super(httpParameters, httpCookieStore);
        this.mServiceClient = serviceClient;
    }

    @Override // com.amazon.avod.ads.http.AdHttpClient
    protected void pingHttpServer(URL url, int i, int i2) throws AdNetworkException {
        getResponse(url, i, i2);
    }

    @Override // com.amazon.avod.ads.http.AdHttpClient
    protected String processHttpRequest(URL url, int i, int i2) throws AdNetworkException {
        return (String) getResponse(url, i, i2).getValue();
    }

    private <T> Response<T> getResponse(URL url, int i, int i2) throws AdNetworkException {
        Response<T> responseExecuteSync = this.mServiceClient.executeSync(getBoltRequest(url, i, i2));
        if (!responseExecuteSync.hasException()) {
            return responseExecuteSync;
        }
        DLog.exceptionf(responseExecuteSync.getException(), "Encountered exception while contacting http server", new Object[0]);
        throw new AdNetworkException("Encountered exception while contacting http server", responseExecuteSync.getException());
    }

    private <T> Request<T> getBoltRequest(URL url, int i, int i2) {
        long j = i2;
        return Request.builder().setUrl(url).setRequestStrategy(RequestStrategy.builder().setRetryPolicy(new ResponseRetryPolicy(i, j)).setBackoffPolicy(new ConstantBackoffPolicy(j)).build()).setHeader(HttpHeader.USER_AGENT, getHttpParameters().getUserAgent()).setResponseHandler(new HttpResponse.Handler<T>() { // from class: com.amazon.avod.ads.http.BoltHttpAdClient.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.amazon.bolthttp.HttpResponse.Handler
            @Nullable
            public T process(@Nonnull HttpResponse<T> httpResponse) throws Exception {
                return (T) httpResponse.getBody();
            }
        }).build();
    }
}
