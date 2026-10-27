package com.amazon.mp3.net;

import com.amazon.mp3.util.Log;
import java.net.HttpURLConnection;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class RequestIdInjectingHttpClient extends JsonHttpClient {
    private final String mTag = getClass().getSimpleName();

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void setConnectionOptions(HttpURLConnection connection, HttpRequestBuilder builder) {
        super.setConnectionOptions(connection, builder);
        String string = UUID.randomUUID().toString();
        Log.debug(this.mTag, "Adding new request Id: %s", string);
        connection.setRequestProperty("x-amzn-RequestId", string);
    }
}
