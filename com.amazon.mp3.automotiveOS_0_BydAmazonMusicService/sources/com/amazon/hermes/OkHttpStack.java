package com.amazon.hermes;

import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* JADX INFO: loaded from: classes2.dex */
public class OkHttpStack implements HttpStack {
    private static final String BODY_TYPE_KEY = "Content-Type";
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String DEFAULT_BODY_TYPE = "application/json; charset=UTF-8";
    private static final String HTTP_PROTOCOL = "HTTP";
    private static final String SPDY_PROTOCOL = "SPDY";
    private final OkHttpClient mOkHttpClient;

    public OkHttpStack() {
        this(UnifiedOkHttpClient.newClient());
    }

    public OkHttpStack(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.mOkHttpClient = okHttpClient;
    }

    @Override // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError {
        if (request instanceof CoralCall) {
            CoralCall coralCall = (CoralCall) request;
            RequestFacade requestFacade = coralCall.getRequestFacade();
            List<RequestInterceptor> requestInterceptors = coralCall.getRequestInterceptors();
            if (requestInterceptors != null) {
                try {
                    ObjectMapper objectMapper = coralCall.getObjectMapper();
                    Iterator<RequestInterceptor> it = requestInterceptors.iterator();
                    while (it.hasNext()) {
                        it.next().intercept(requestFacade, objectMapper);
                    }
                } catch (Exception unused) {
                    throw new AuthFailureError();
                }
            }
        }
        OkHttpClient.Builder builderNewBuilder = this.mOkHttpClient.newBuilder();
        long timeoutMs = request.getTimeoutMs();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        builderNewBuilder.connectTimeout(timeoutMs, timeUnit);
        builderNewBuilder.readTimeout(timeoutMs, timeUnit);
        builderNewBuilder.writeTimeout(timeoutMs, timeUnit);
        Request.Builder builder = new Request.Builder();
        builder.url(request.getUrl());
        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : map.entrySet()) {
            builder.addHeader(entry2.getKey(), entry2.getValue());
        }
        setConnectionParametersForRequest(builder, request);
        Response responseExecute = FirebasePerfOkHttpClient.execute(builderNewBuilder.build().newCall(builder.build()));
        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(parseProtocol(responseExecute.protocol()), responseExecute.code(), responseExecute.message()));
        basicHttpResponse.setEntity(entityFromOkHttpResponse(responseExecute));
        Headers headers = responseExecute.headers();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            String strName = headers.name(i);
            String strValue = headers.value(i);
            if (strName != null) {
                basicHttpResponse.addHeader(new BasicHeader(strName, strValue));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity entityFromOkHttpResponse(Response response) throws IOException {
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody != null) {
            basicHttpEntity.setContent(responseBodyBody.byteStream());
            basicHttpEntity.setContentLength(responseBodyBody.getContentLength());
            basicHttpEntity.setContentEncoding(response.header(CONTENT_ENCODING));
            MediaType mediaType = responseBodyBody.get$contentType();
            if (mediaType != null) {
                basicHttpEntity.setContentType(mediaType.type());
            }
        }
        return basicHttpEntity;
    }

    private static void setConnectionParametersForRequest(Request.Builder builder, com.android.volley.Request<?> request) throws IOException, AuthFailureError {
        Map<String, String> headers = request.getHeaders();
        String str = headers.containsKey("Content-Type") ? headers.get("Content-Type") : DEFAULT_BODY_TYPE;
        switch (request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    builder.post(RequestBody.create(MediaType.parse(str), postBody));
                    return;
                }
                return;
            case 0:
                builder.get();
                return;
            case 1:
                builder.post(createRequestBody(request, str));
                return;
            case 2:
                builder.put(createRequestBody(request, str));
                return;
            case 3:
                builder.delete();
                return;
            case 4:
                builder.head();
                return;
            case 5:
                builder.method(FirebasePerformance.HttpMethod.OPTIONS, null);
                return;
            case 6:
                builder.method(FirebasePerformance.HttpMethod.TRACE, null);
                return;
            case 7:
                builder.patch(createRequestBody(request, str));
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    /* JADX INFO: renamed from: com.amazon.hermes.OkHttpStack$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$okhttp3$Protocol;

        static {
            int[] iArr = new int[Protocol.values().length];
            $SwitchMap$okhttp3$Protocol = iArr;
            try {
                iArr[Protocol.HTTP_1_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$okhttp3$Protocol[Protocol.HTTP_1_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$okhttp3$Protocol[Protocol.SPDY_3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$okhttp3$Protocol[Protocol.HTTP_2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static ProtocolVersion parseProtocol(Protocol protocol) {
        int i = AnonymousClass1.$SwitchMap$okhttp3$Protocol[protocol.ordinal()];
        if (i == 1) {
            return new ProtocolVersion(HTTP_PROTOCOL, 1, 0);
        }
        if (i == 2) {
            return new ProtocolVersion(HTTP_PROTOCOL, 1, 1);
        }
        if (i == 3) {
            return new ProtocolVersion(SPDY_PROTOCOL, 3, 1);
        }
        if (i == 4) {
            return new ProtocolVersion(SPDY_PROTOCOL, 2, 0);
        }
        throw new IllegalAccessError("Unkwown protocol");
    }

    private static RequestBody createRequestBody(com.android.volley.Request request, String str) throws AuthFailureError {
        byte[] body = request.getBody();
        if (body == null) {
            return null;
        }
        return RequestBody.create(MediaType.parse(str), body);
    }
}
