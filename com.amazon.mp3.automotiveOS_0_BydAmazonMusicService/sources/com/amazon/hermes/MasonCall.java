package com.amazon.hermes;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.RequestFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MasonCall<B, R> extends Request<R> {
    private Callback<R> callback;
    private RequestFuture<R> future;
    private final ObjectMapper mapper;
    private final RequestQueue requestQueue;

    protected abstract Class<R> getResponseType();

    protected MasonCall(int i, URL url) {
        super(i, url.toString(), null);
        this.mapper = Hermes.get().getObjectMapper();
        this.requestQueue = Hermes.get().getRequestQueue();
    }

    public R execute() throws VolleyError {
        RequestFuture<R> requestFutureNewFuture = RequestFuture.newFuture();
        this.future = requestFutureNewFuture;
        requestFutureNewFuture.setRequest(this.requestQueue.add(this));
        try {
            return this.future.get(getTimeoutMs(), TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            this.future.cancel(true);
            return null;
        } catch (ExecutionException e) {
            throw ((VolleyError) e.getCause());
        } catch (TimeoutException unused2) {
            throw new TimeoutError();
        }
    }

    public void execute(Callback<R> callback) {
        this.callback = callback;
        this.requestQueue.add(this);
    }

    @Override // com.android.volley.Request
    protected void deliverResponse(R r) {
        Callback<R> callback = this.callback;
        if (callback != null) {
            callback.onSuccess(r);
        }
        RequestFuture<R> requestFuture = this.future;
        if (requestFuture != null) {
            requestFuture.onResponse(r);
        }
    }

    @Override // com.android.volley.Request
    public void deliverError(VolleyError volleyError) {
        Callback<R> callback = this.callback;
        if (callback != null) {
            callback.onError(volleyError);
        }
        RequestFuture<R> requestFuture = this.future;
        if (requestFuture != null) {
            requestFuture.onErrorResponse(volleyError);
        }
    }

    @Override // com.android.volley.Request
    protected Response<R> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            return Response.success(this.mapper.readValue(networkResponse.data, getResponseType()), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }
}
