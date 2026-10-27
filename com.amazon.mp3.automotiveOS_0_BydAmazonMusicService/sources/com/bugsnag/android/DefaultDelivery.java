package com.bugsnag.android;

import android.net.TrafficStats;
import androidx.core.app.NotificationCompat;
import com.bugsnag.android.internal.JsonHelper;
import com.bugsnag.android.internal.TrimMetrics;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: DefaultDelivery.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J,\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0016J\u0015\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0019J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J \u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\fH\u0002J.\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0016H\u0002J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/bugsnag/android/DefaultDelivery;", "Lcom/bugsnag/android/Delivery;", "connectivity", "Lcom/bugsnag/android/Connectivity;", "apiKey", "", "maxStringValueLength", "", "logger", "Lcom/bugsnag/android/Logger;", "(Lcom/bugsnag/android/Connectivity;Ljava/lang/String;ILcom/bugsnag/android/Logger;)V", "deliver", "Lcom/bugsnag/android/DeliveryStatus;", "payload", "Lcom/bugsnag/android/EventPayload;", "deliveryParams", "Lcom/bugsnag/android/DeliveryParams;", "Lcom/bugsnag/android/Session;", "urlString", "json", "", "headers", "", "getDeliveryStatus", "responseCode", "getDeliveryStatus$bugsnag_android_core_release", "isUnrecoverableStatusCode", "", "logRequestInfo", "", "code", "conn", "Ljava/net/HttpURLConnection;", NotificationCompat.CATEGORY_STATUS, "makeRequest", ImagesContract.URL, "Ljava/net/URL;", "serializePayload", "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class DefaultDelivery implements Delivery {
    private final String apiKey;
    private final Connectivity connectivity;
    private final Logger logger;
    private final int maxStringValueLength;

    private final boolean isUnrecoverableStatusCode(int responseCode) {
        return 400 <= responseCode && 499 >= responseCode && responseCode != 408 && responseCode != 429;
    }

    public DefaultDelivery(Connectivity connectivity, String str, int i, Logger logger) {
        this.connectivity = connectivity;
        this.apiKey = str;
        this.maxStringValueLength = i;
        this.logger = logger;
    }

    @Override // com.bugsnag.android.Delivery
    public DeliveryStatus deliver(Session payload, DeliveryParams deliveryParams) {
        DeliveryStatus deliveryStatusDeliver = deliver(deliveryParams.getEndpoint(), JsonHelper.INSTANCE.serialize(payload), deliveryParams.getHeaders());
        this.logger.i("Session API request finished with status " + deliveryStatusDeliver);
        return deliveryStatusDeliver;
    }

    private final byte[] serializePayload(EventPayload payload) {
        JsonHelper jsonHelper = JsonHelper.INSTANCE;
        byte[] bArrSerialize = jsonHelper.serialize(payload);
        if (bArrSerialize.length <= 999700) {
            return bArrSerialize;
        }
        Event event = payload.getEvent();
        if (event == null) {
            File eventFile = payload.getEventFile();
            if (eventFile == null) {
                kotlin.jvm.internal.Intrinsics.throwNpe();
            }
            event = new MarshalledEventSource(eventFile, this.apiKey, this.logger).invoke();
            payload.setEvent$bugsnag_android_core_release(event);
            payload.setApiKey(this.apiKey);
        }
        TrimMetrics trimMetricsTrimMetadataStringsTo = event.getImpl().trimMetadataStringsTo(this.maxStringValueLength);
        event.getImpl().getInternalMetrics().setMetadataTrimMetrics(trimMetricsTrimMetadataStringsTo.getItemsTrimmed(), trimMetricsTrimMetadataStringsTo.getDataTrimmed());
        byte[] bArrSerialize2 = jsonHelper.serialize(payload);
        if (bArrSerialize2.length <= 999700) {
            return bArrSerialize2;
        }
        TrimMetrics trimMetricsTrimBreadcrumbsBy = event.getImpl().trimBreadcrumbsBy(bArrSerialize2.length - 999700);
        event.getImpl().getInternalMetrics().setBreadcrumbTrimMetrics(trimMetricsTrimBreadcrumbsBy.getItemsTrimmed(), trimMetricsTrimBreadcrumbsBy.getDataTrimmed());
        return jsonHelper.serialize(payload);
    }

    @Override // com.bugsnag.android.Delivery
    public DeliveryStatus deliver(EventPayload payload, DeliveryParams deliveryParams) {
        DeliveryStatus deliveryStatusDeliver = deliver(deliveryParams.getEndpoint(), serializePayload(payload), deliveryParams.getHeaders());
        this.logger.i("Error API request finished with status " + deliveryStatusDeliver);
        return deliveryStatusDeliver;
    }

    public final DeliveryStatus deliver(String urlString, byte[] json, Map<String, String> headers) {
        TrafficStats.setThreadStatsTag(1);
        Connectivity connectivity = this.connectivity;
        if (connectivity != null && !connectivity.hasNetworkConnection()) {
            return DeliveryStatus.UNDELIVERED;
        }
        HttpURLConnection httpURLConnectionMakeRequest = null;
        try {
            try {
                try {
                    httpURLConnectionMakeRequest = makeRequest(new URL(urlString), json, headers);
                    int responseCode = httpURLConnectionMakeRequest.getResponseCode();
                    DeliveryStatus deliveryStatus$bugsnag_android_core_release = getDeliveryStatus$bugsnag_android_core_release(responseCode);
                    logRequestInfo(responseCode, httpURLConnectionMakeRequest, deliveryStatus$bugsnag_android_core_release);
                    httpURLConnectionMakeRequest.disconnect();
                    return deliveryStatus$bugsnag_android_core_release;
                } catch (Exception e) {
                    this.logger.w("Unexpected error delivering payload", e);
                    DeliveryStatus deliveryStatus = DeliveryStatus.FAILURE;
                    if (httpURLConnectionMakeRequest != null) {
                        httpURLConnectionMakeRequest.disconnect();
                    }
                    return deliveryStatus;
                } catch (OutOfMemoryError e2) {
                    this.logger.w("Encountered OOM delivering payload, falling back to persist on disk", e2);
                    DeliveryStatus deliveryStatus2 = DeliveryStatus.UNDELIVERED;
                    if (httpURLConnectionMakeRequest != null) {
                        httpURLConnectionMakeRequest.disconnect();
                    }
                    return deliveryStatus2;
                }
            } catch (IOException e3) {
                this.logger.w("IOException encountered in request", e3);
                DeliveryStatus deliveryStatus3 = DeliveryStatus.UNDELIVERED;
                if (httpURLConnectionMakeRequest != null) {
                    httpURLConnectionMakeRequest.disconnect();
                }
                return deliveryStatus3;
            }
        } catch (Throwable th) {
            if (httpURLConnectionMakeRequest != null) {
                httpURLConnectionMakeRequest.disconnect();
            }
            throw th;
        }
    }

    private final HttpURLConnection makeRequest(URL url, byte[] json, Map<String, String> headers) throws IOException {
        URLConnection uRLConnection = (URLConnection) FirebasePerfUrlConnection.instrument(url.openConnection());
        if (uRLConnection == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setFixedLengthStreamingMode(json.length);
        String strComputeSha1Digest = DeliveryHeadersKt.computeSha1Digest(json);
        if (strComputeSha1Digest != null) {
            httpURLConnection.addRequestProperty("Bugsnag-Integrity", strComputeSha1Digest);
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null) {
                httpURLConnection.addRequestProperty(key, value);
            }
        }
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(json);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, null);
            return httpURLConnection;
        } finally {
        }
    }

    private final void logRequestInfo(int code, HttpURLConnection conn, DeliveryStatus status) {
        BufferedReader bufferedReader;
        try {
            Result.Companion companion = Result.INSTANCE;
            this.logger.i("Request completed with code " + code + ", message: " + conn.getResponseMessage() + ", headers: " + conn.getHeaderFields());
            Result.m2810constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th));
        }
        try {
            InputStream inputStream = conn.getInputStream();
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(inputStream, "conn.inputStream");
            Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
            bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                this.logger.d("Received request response: " + TextStreamsKt.readText(bufferedReader));
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedReader, null);
                Result.m2810constructorimpl(Unit.INSTANCE);
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } catch (Throwable th2) {
            Result.Companion companion3 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            if (status != DeliveryStatus.DELIVERED) {
                InputStream errorStream = conn.getErrorStream();
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(errorStream, "conn.errorStream");
                Reader inputStreamReader2 = new InputStreamReader(errorStream, Charsets.UTF_8);
                bufferedReader = inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192);
                try {
                    this.logger.w("Request error details: " + TextStreamsKt.readText(bufferedReader));
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(bufferedReader, null);
                } finally {
                }
            }
            Result.m2810constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m2810constructorimpl(ResultKt.createFailure(th3));
        }
    }

    public final DeliveryStatus getDeliveryStatus$bugsnag_android_core_release(int responseCode) {
        if (200 <= responseCode && 299 >= responseCode) {
            return DeliveryStatus.DELIVERED;
        }
        return isUnrecoverableStatusCode(responseCode) ? DeliveryStatus.FAILURE : DeliveryStatus.UNDELIVERED;
    }
}
