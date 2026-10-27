package com.amazon.mp3.net.soprano;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.CoralHttpClient;
import com.amazon.mp3.util.Log;
import java.io.InputStream;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SopranoHttpClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/net/soprano/SopranoHttpClient;", "Lcom/amazon/mp3/net/CoralHttpClient;", "()V", "onProcessRawResponse", "", "httpConnection", "Ljava/net/HttpURLConnection;", "responseStream", "Ljava/io/InputStream;", "validateHttpStatusCode", NotificationCompat.CATEGORY_STATUS, "", "validateResponse", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SopranoHttpClient extends CoralHttpClient {
    @Override // com.amazon.mp3.net.JsonHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected boolean validateHttpStatusCode(int status) {
        if (status != 200) {
            return 400 <= status && status < 501;
        }
        return true;
    }

    @Override // com.amazon.mp3.net.CoralHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected void validateResponse() {
        SopranoExceptions.INSTANCE.validateCoralResponse(this);
    }

    @Override // com.amazon.mp3.net.JsonHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected boolean onProcessRawResponse(HttpURLConnection httpConnection, InputStream responseStream) {
        try {
            return super.onProcessRawResponse(httpConnection, responseStream);
        } catch (AbstractHttpClient.EmptyResponseException e) {
            Log.warning("SopranoHttpClient", Intrinsics.stringPlus("Service returned empty response: ", e));
            return true;
        }
    }
}
