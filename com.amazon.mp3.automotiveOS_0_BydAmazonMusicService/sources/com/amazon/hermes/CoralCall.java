package com.amazon.hermes;

import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.RequestFuture;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.umd.cs.findbugs.annotations.Nullable;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CoralCall<B, R> extends Request<R> {
    private static final long TIMEOUT_SECS = 30;
    private AuthMethod authMethod;
    private byte[] bodySerialized;
    private Callback<R> callback;
    private final boolean compressedBody;
    private RequestFuture<R> future;
    private Map<String, String> headers;
    private ObjectMapper mapper;
    private RequestFacade requestFacade;
    private List<RequestInterceptor> requestInterceptors;
    private final RequestQueue requestQueue;
    private ResponseMetadata responseMetadata;
    private long timeout;
    private final Version version;

    private enum Version {
        V11,
        V12
    }

    protected abstract ApiInfo getApiInfo();

    protected abstract Class<R> getResponseType();

    @Deprecated
    public CoralCall(AuthMethod authMethod, URL url, B b) {
        this(authMethod, url, (Object) b, false);
    }

    @Deprecated
    public CoralCall(AuthMethod authMethod, URL url, B b, boolean z) {
        super(1, url.toString(), null);
        this.timeout = TIMEOUT_SECS;
        this.mapper = Hermes.get().getObjectMapper();
        this.requestQueue = Hermes.get().getRequestQueue();
        this.requestFacade = buildRequestFacade(url, b, z);
        this.requestInterceptors = new ArrayList();
        this.authMethod = authMethod;
        this.compressedBody = z;
        this.version = Version.V11;
    }

    public CoralCall(URL url, B b, List<RequestInterceptor> list) {
        this(url, (Object) b, list, false);
    }

    public CoralCall(URL url, B b, List<RequestInterceptor> list, boolean z) {
        super(1, url.toString(), null);
        this.timeout = TIMEOUT_SECS;
        this.mapper = Hermes.get().getObjectMapper();
        this.requestQueue = Hermes.get().getRequestQueue();
        this.requestFacade = buildRequestFacade(url, b, z);
        if (list != null) {
            this.requestInterceptors = list;
        } else {
            this.requestInterceptors = new ArrayList();
        }
        this.compressedBody = z;
        this.version = Version.V12;
    }

    public CoralCall(URL url, B b) {
        this(url, (Object) b, false);
    }

    public CoralCall(URL url, B b, boolean z) {
        this(url, b, new ArrayList(), z);
    }

    public CoralCall(URL url, B b, RequestInterceptor requestInterceptor) {
        this(url, (Object) b, requestInterceptor, false);
    }

    public CoralCall(URL url, B b, RequestInterceptor requestInterceptor, boolean z) {
        List listAsList;
        if (requestInterceptor == null) {
            listAsList = new ArrayList();
        } else {
            listAsList = Arrays.asList(requestInterceptor);
        }
        this(url, b, (List<RequestInterceptor>) listAsList, z);
    }

    List<RequestInterceptor> getRequestInterceptors() {
        return this.requestInterceptors;
    }

    protected RequestFacade getRequestFacade() {
        return this.requestFacade;
    }

    protected ObjectMapper getObjectMapper() {
        return this.mapper;
    }

    @Deprecated
    public R execute() throws VolleyError {
        return execute(this.mapper);
    }

    public R execute(ObjectMapper objectMapper) throws VolleyError {
        Validate.notNull(objectMapper, "ObjectMapper can't be null", new Object[0]);
        this.mapper = objectMapper;
        RequestFuture<R> requestFutureNewFuture = RequestFuture.newFuture();
        this.future = requestFutureNewFuture;
        requestFutureNewFuture.setRequest(this.requestQueue.add(this));
        try {
            return this.future.get(this.timeout, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            this.future.cancel(true);
            return null;
        } catch (ExecutionException e) {
            sendSearchFailureFlexEvent(this.future, e);
            throw ((VolleyError) e.getCause());
        } catch (TimeoutException unused2) {
            throw new TimeoutError();
        }
    }

    @Deprecated
    public void execute(Callback<R> callback) {
        execute(callback, this.mapper);
    }

    private void sendSearchFailureFlexEvent(RequestFuture<R> requestFuture, ExecutionException executionException) {
        String str;
        if (getApiInfo() == null || getApiInfo().getServiceInfo() == null) {
            return;
        }
        if ("search".equalsIgnoreCase(getApiInfo().getApiName()) || "suggest".equalsIgnoreCase(getApiInfo().getApiName())) {
            float f = 0.0f;
            try {
                String serviceName = getApiInfo().getServiceInfo().getServiceName();
                String apiName = getApiInfo().getApiName();
                String strSubstring = (executionException == null || executionException.getCause() == null || executionException.getCause().getCause() == null || executionException.getCause().getCause().getMessage() == null) ? null : executionException.getCause().getCause().getMessage().substring(0, 128);
                String str2 = getHeaders() != null ? getHeaders().get("x-amzn-RequestId") : null;
                if (getResponseMetadata() != null && getResponseMetadata().getHeaders() != null && (str = getResponseMetadata().getHeaders().get("OkHttp-Response-Source")) != null && str.split(" ").length >= 2) {
                    f = Float.parseFloat(getResponseMetadata().getHeaders().get("OkHttp-Response-Source").split(" ")[1]);
                }
                MetricsHolder.getManager().handleEvent(FlexEvent.builder("clientAPIFailure").withFlexStr1(serviceName).withFlexStr2(apiName).withFlexStr3(strSubstring).withFlexStr4(str2).withFlexNum1(Float.valueOf(f)).build());
            } catch (AuthFailureError | Exception unused) {
            }
        }
    }

    public void execute(Callback<R> callback, ObjectMapper objectMapper) {
        Validate.notNull(objectMapper, "ObjectMapper can't be null", new Object[0]);
        this.callback = callback;
        this.mapper = objectMapper;
        this.requestQueue.add(this);
    }

    private byte[] getSerializedBody() {
        if (this.bodySerialized == null) {
            try {
                this.bodySerialized = this.mapper.writeValueAsBytes(this.requestFacade.getBody());
            } catch (IOException e) {
                deliverError(new VolleyError(e));
            }
        }
        return this.bodySerialized;
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
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        this.responseMetadata = new ResponseMetadata(volleyError.networkResponse);
        if (volleyError instanceof ServerError) {
            byte[] bArr = volleyError.networkResponse.data;
            String str = null;
            try {
                str = new String(bArr, ParserUtil.UTF_8);
            } catch (Exception unused) {
            }
            try {
                return new CoralError(str, ((CoralExceptionDeserializer) this.mapper.readValue(bArr, CoralExceptionDeserializer.class)).get());
            } catch (IOException e) {
                return str != null ? new UnhandledError(str) : new VolleyError(e);
            } catch (Exception e2) {
                return new UnhandledError(e2.getMessage(), e2);
            }
        }
        return super.parseNetworkError(volleyError);
    }

    @Override // com.android.volley.Request
    protected Response<R> parseNetworkResponse(NetworkResponse networkResponse) {
        this.responseMetadata = new ResponseMetadata(networkResponse);
        try {
            Class<R> responseType = getResponseType();
            return Response.success((responseType != null && responseType.isAssignableFrom(Void.class) && networkResponse.data.length == 0) ? null : this.mapper.readValue(networkResponse.data, responseType), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        CoralHeader coralHeader;
        if (this.headers == null) {
            this.headers = new HashMap();
            if (Version.V11 == this.version) {
                coralHeader = new CoralHeader(getApiInfo(), this.authMethod, this.compressedBody);
            } else {
                coralHeader = new CoralHeader(getApiInfo(), this.compressedBody);
            }
            this.headers.putAll(coralHeader.getHeaders());
            this.headers.putAll(this.requestFacade.getHeaders());
        }
        return this.headers;
    }

    @Override // com.android.volley.Request
    @SuppressWarnings(justification = "Exposed by Volley", value = {"EI_EXPOSE_REP"})
    public byte[] getBody() throws AuthFailureError {
        if (this.compressedBody) {
            return (byte[]) this.requestFacade.getBody();
        }
        return getSerializedBody();
    }

    @Override // com.android.volley.Request
    public String getCacheKey() {
        return super.getCacheKey() + ":" + Arrays.hashCode(getSerializedBody());
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Validate.notNull(objectMapper, "ObjectMapper can't be null", new Object[0]);
        this.mapper = objectMapper;
    }

    public void setTimeout(long j) {
        this.timeout = j;
    }

    @Nullable
    public ResponseMetadata getResponseMetadata() {
        return this.responseMetadata;
    }

    private RequestFacade buildRequestFacade(URL url, B b, boolean z) {
        RequestFacade requestFacade;
        try {
            if (z) {
                requestFacade = new RequestFacade(url, gzip(this.mapper.writeValueAsBytes(b)));
            } else {
                requestFacade = new RequestFacade(url, b);
            }
            return requestFacade;
        } catch (IOException e) {
            deliverError(new VolleyError(e));
            return null;
        }
    }

    private byte[] gzip(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        try {
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            gZIPOutputStream.close();
            throw th;
        }
    }
}
