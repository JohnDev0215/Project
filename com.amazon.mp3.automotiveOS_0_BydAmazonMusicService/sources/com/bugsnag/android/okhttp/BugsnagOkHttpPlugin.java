package com.bugsnag.android.okhttp;

import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.bugsnag.android.BreadcrumbHooksKt;
import com.bugsnag.android.BreadcrumbType;
import com.bugsnag.android.Client;
import com.bugsnag.android.Plugin;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: BugsnagOkHttpPlugin.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0007\u0012\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00130-¢\u0006\u0004\b2\u00103J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u001e\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0011\u001a\u00020\u0005H\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J?\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t*\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u0013H\u0001¢\u0006\u0004\b$\u0010%R&\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001f0'8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010,R \u0010.\u001a\b\u0012\u0004\u0012\u00020\u00130-8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101¨\u00064"}, d2 = {"Lcom/bugsnag/android/okhttp/BugsnagOkHttpPlugin;", "Lcom/bugsnag/android/Plugin;", "Lokhttp3/EventListener;", "Lokhttp3/Call;", NotificationCompat.CATEGORY_CALL, "", "captureNetworkBreadcrumb", "Lokhttp3/Request;", "request", "", "", "", "buildQueryParams", "sanitizeUrl", "Lcom/bugsnag/android/Client;", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "load", "unload", "callStart", "", "byteCount", "requestBodyEnd", "responseBodyEnd", "Lokhttp3/Response;", "response", "responseHeadersEnd", "callEnd", "Ljava/io/IOException;", "ioe", "callFailed", "canceled", "Lcom/bugsnag/android/okhttp/NetworkRequestMetadata;", "info", "Lcom/bugsnag/android/okhttp/RequestResult;", "result", "nowMs", "collateMetadata$bugsnag_plugin_android_okhttp_release", "(Lcom/bugsnag/android/Client;Lokhttp3/Call;Lcom/bugsnag/android/okhttp/NetworkRequestMetadata;Lcom/bugsnag/android/okhttp/RequestResult;J)Ljava/util/Map;", "collateMetadata", "Ljava/util/concurrent/ConcurrentHashMap;", "requestMap", "Ljava/util/concurrent/ConcurrentHashMap;", "getRequestMap$bugsnag_plugin_android_okhttp_release", "()Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/bugsnag/android/Client;", "Lkotlin/Function0;", "timeProvider", "Lkotlin/jvm/functions/Function0;", "getTimeProvider$bugsnag_plugin_android_okhttp_release", "()Lkotlin/jvm/functions/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "bugsnag-plugin-android-okhttp_release"}, k = 1, mv = {1, 4, 2})
public final class BugsnagOkHttpPlugin extends EventListener implements Plugin {
    private Client client;
    private final ConcurrentHashMap<Call, NetworkRequestMetadata> requestMap;
    private final Function0<Long> timeProvider;

    @JvmOverloads
    public BugsnagOkHttpPlugin() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ BugsnagOkHttpPlugin(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function0<Long>() { // from class: com.bugsnag.android.okhttp.BugsnagOkHttpPlugin.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Long invoke() {
                return Long.valueOf(invoke2());
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final long invoke2() {
                return System.currentTimeMillis();
            }
        } : function0);
    }

    @JvmOverloads
    public BugsnagOkHttpPlugin(Function0<Long> function0) {
        this.timeProvider = function0;
        this.requestMap = new ConcurrentHashMap<>();
    }

    @Override // com.bugsnag.android.Plugin
    public void load(Client client) {
        this.client = client;
    }

    @Override // com.bugsnag.android.Plugin
    public void unload() {
        this.client = null;
    }

    @Override // okhttp3.EventListener
    public void callStart(Call call) {
        this.requestMap.put(call, new NetworkRequestMetadata(this.timeProvider.invoke().longValue()));
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(Call call, long byteCount) {
        NetworkRequestMetadata networkRequestMetadata = this.requestMap.get(call);
        if (networkRequestMetadata != null) {
            networkRequestMetadata.requestBodyCount = byteCount;
        }
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(Call call, long byteCount) {
        NetworkRequestMetadata networkRequestMetadata = this.requestMap.get(call);
        if (networkRequestMetadata != null) {
            networkRequestMetadata.responseBodyCount = byteCount;
        }
    }

    @Override // okhttp3.EventListener
    public void responseHeadersEnd(Call call, Response response) {
        NetworkRequestMetadata networkRequestMetadata = this.requestMap.get(call);
        if (networkRequestMetadata != null) {
            networkRequestMetadata.status = response.code();
        }
    }

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
        captureNetworkBreadcrumb(call);
    }

    @Override // okhttp3.EventListener
    public void callFailed(Call call, IOException ioe) {
        captureNetworkBreadcrumb(call);
    }

    @Override // okhttp3.EventListener
    public void canceled(Call call) {
        captureNetworkBreadcrumb(call);
    }

    private final void captureNetworkBreadcrumb(Call call) {
        NetworkRequestMetadata requestInfo;
        Client client = this.client;
        if (client == null || (requestInfo = this.requestMap.remove(call)) == null || BreadcrumbHooksKt.shouldDiscardNetworkBreadcrumb(client)) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(requestInfo, "requestInfo");
        RequestResult requestResult = BugsnagOkHttpPluginKt.getRequestResult(requestInfo);
        client.leaveBreadcrumb(requestResult.getMessage(), collateMetadata$bugsnag_plugin_android_okhttp_release(client, call, requestInfo, requestResult, this.timeProvider.invoke().longValue()), BreadcrumbType.REQUEST);
    }

    @VisibleForTesting
    public final Map<String, Object> collateMetadata$bugsnag_plugin_android_okhttp_release(Client client, Call call, NetworkRequestMetadata networkRequestMetadata, RequestResult requestResult, long j) {
        Request request = call.request();
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("method", request.method()), TuplesKt.to(ImagesContract.URL, sanitizeUrl(request)), TuplesKt.to("duration", Long.valueOf(j - networkRequestMetadata.startTime)), TuplesKt.to("requestContentLength", Long.valueOf(networkRequestMetadata.requestBodyCount)));
        Map<String, Object> mapBuildQueryParams = buildQueryParams(request);
        if (!mapBuildQueryParams.isEmpty()) {
            mapMutableMapOf.put("urlParams", mapBuildQueryParams);
        }
        if (requestResult != RequestResult.ERROR) {
            mapMutableMapOf.put("responseContentLength", Long.valueOf(networkRequestMetadata.responseBodyCount));
            mapMutableMapOf.put(NotificationCompat.CATEGORY_STATUS, Integer.valueOf(networkRequestMetadata.status));
        }
        return MapsKt__MapsKt.toMap(mapMutableMapOf);
    }

    private final Map<String, Object> buildQueryParams(Request request) {
        HttpUrl httpUrlUrl = request.url();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : httpUrlUrl.queryParameterNames()) {
            List<String> listQueryParameterValues = httpUrlUrl.queryParameterValues(str);
            if (listQueryParameterValues.size() == 1) {
                linkedHashMap.put(str, CollectionsKt___CollectionsKt.first((List) listQueryParameterValues));
            } else {
                linkedHashMap.put(str, httpUrlUrl.queryParameterValues(str));
            }
        }
        return linkedHashMap;
    }

    private final String sanitizeUrl(Request request) {
        HttpUrl httpUrlUrl = request.url();
        HttpUrl.Builder builderNewBuilder = httpUrlUrl.newBuilder();
        Iterator<T> it = httpUrlUrl.queryParameterNames().iterator();
        while (it.hasNext()) {
            builderNewBuilder.removeAllQueryParameters((String) it.next());
        }
        return builderNewBuilder.build().getUrl();
    }
}
