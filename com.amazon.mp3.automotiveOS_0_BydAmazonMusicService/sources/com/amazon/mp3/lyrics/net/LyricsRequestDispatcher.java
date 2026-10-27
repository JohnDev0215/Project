package com.amazon.mp3.lyrics.net;

import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.net.HttpRequestBuilder;
import com.amazon.mp3.net.RequestAuthorizer;
import com.amazon.mp3.net.RequestIdInjectingHttpClient;
import com.amazon.mp3.util.Log;
import com.amazonaws.http.HttpHeader;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsRequestDispatcher {
    private static final String TAG = "LyricsRequestDispatcher";

    public static JSONObject dispatch(Request requestType, JSONObject request) throws Exception {
        Log.debug(TAG, "Lyrics Request: %s", request);
        return new RequestIdInjectingHttpClient().execute(createMusicContentRequest(requestType, request.toString())).getResult();
    }

    private static HttpRequestBuilder createMusicContentRequest(Request requestType, String body) {
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder(getLyricsHost(), 443, "/");
        httpRequestBuilder.setUseHttps(true);
        httpRequestBuilder.setMethod(1);
        httpRequestBuilder.setBody(body);
        HttpRequestBuilder httpRequestBuilderSignRequest = new RequestAuthorizer().signRequest(httpRequestBuilder);
        httpRequestBuilderSignRequest.addHeader(HttpHeader.CONTENT_TYPE, "application/json; charset=UTF-8").addHeader(HttpHeader.ACCEPT, "application/json").addHeader("Content-Encoding", "amz-1.0").addHeader("X-Amz-Target", requestType.getServiceName() + '.' + requestType.getRequestName());
        return httpRequestBuilderSignRequest;
    }

    private static String getLyricsHost() {
        return EndpointsProvider.get().getLyricsEndpoint();
    }
}
