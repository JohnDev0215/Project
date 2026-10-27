package okhttp3.internal.cache;

import com.amazonaws.http.HttpHeader;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;

/* JADX INFO: compiled from: CacheStrategy.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000b2\u00020\u0001:\u0002\u000b\fB\u001b\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy;", "", "networkRequest", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(Lokhttp3/Request;Lokhttp3/Response;)V", "getCacheResponse", AsmString.METHOD_DESC_OKHTTP300_CALL_EXECUTE, "getNetworkRequest", "()Lokhttp3/Request;", "Companion", "Factory", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CacheStrategy {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Response cacheResponse;
    private final Request networkRequest;

    public CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    public final Request getNetworkRequest() {
        return this.networkRequest;
    }

    public final Response getCacheResponse() {
        return this.cacheResponse;
    }

    /* JADX INFO: compiled from: CacheStrategy.kt */
    @Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b$\u0010%J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\b\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\u0006\u0010\f\u001a\u00020\u0004R\u0014\u0010\r\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u001a\u0010\n\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u0017R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u0017R\u0016\u0010\u001e\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u000eR\u0016\u0010\u001f\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u000eR\u0018\u0010 \u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010\u001aR\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Factory;", "", "", "isFreshnessLifetimeHeuristic", "Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "", "computeFreshnessLifetime", "cacheResponseAge", "Lokhttp3/Request;", "request", "hasConditions", "compute", "nowMillis", "J", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "Lokhttp3/Response;", "cacheResponse", "Lokhttp3/Response;", "Ljava/util/Date;", "servedDate", "Ljava/util/Date;", "", "servedDateString", "Ljava/lang/String;", "lastModified", "lastModifiedString", "expires", "sentRequestMillis", "receivedResponseMillis", "etag", "", "ageSeconds", "I", "<init>", "(JLokhttp3/Request;Lokhttp3/Response;)V", "okhttp"}, k = 1, mv = {1, 6, 0})
    public static final class Factory {
        private int ageSeconds;
        private final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private final long nowMillis;
        private long receivedResponseMillis;
        private final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long j, Request request, Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.nowMillis = j;
            this.request = request;
            this.cacheResponse = response;
            this.ageSeconds = -1;
            if (response != null) {
                this.sentRequestMillis = response.sentRequestAtMillis();
                this.receivedResponseMillis = response.receivedResponseAtMillis();
                Headers headers = response.headers();
                int i = 0;
                int size = headers.size();
                while (i < size) {
                    int i2 = i + 1;
                    String strName = headers.name(i);
                    String strValue = headers.value(i);
                    if (StringsKt__StringsJVMKt.equals(strName, HttpHeader.DATE, true)) {
                        this.servedDate = DatesKt.toHttpDateOrNull(strValue);
                        this.servedDateString = strValue;
                    } else if (StringsKt__StringsJVMKt.equals(strName, "Expires", true)) {
                        this.expires = DatesKt.toHttpDateOrNull(strValue);
                    } else if (StringsKt__StringsJVMKt.equals(strName, "Last-Modified", true)) {
                        this.lastModified = DatesKt.toHttpDateOrNull(strValue);
                        this.lastModifiedString = strValue;
                    } else if (StringsKt__StringsJVMKt.equals(strName, "ETag", true)) {
                        this.etag = strValue;
                    } else if (StringsKt__StringsJVMKt.equals(strName, "Age", true)) {
                        this.ageSeconds = Util.toNonNegativeInt(strValue, -1);
                    }
                    i = i2;
                }
            }
        }

        private final boolean isFreshnessLifetimeHeuristic() {
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            return response.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        public final CacheStrategy compute() {
            CacheStrategy cacheStrategyComputeCandidate = computeCandidate();
            return (cacheStrategyComputeCandidate.getNetworkRequest() == null || !this.request.cacheControl().onlyIfCached()) ? cacheStrategyComputeCandidate : new CacheStrategy(null, null);
        }

        private final CacheStrategy computeCandidate() {
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null);
            }
            if (!CacheStrategy.INSTANCE.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl = this.request.cacheControl();
            if (cacheControl.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl2 = this.cacheResponse.cacheControl();
            long jCacheResponseAge = cacheResponseAge();
            long jComputeFreshnessLifetime = computeFreshnessLifetime();
            if (cacheControl.maxAgeSeconds() != -1) {
                jComputeFreshnessLifetime = Math.min(jComputeFreshnessLifetime, TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds()));
            }
            long millis = 0;
            long millis2 = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis(cacheControl.minFreshSeconds()) : 0L;
            if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                millis = TimeUnit.SECONDS.toMillis(cacheControl.maxStaleSeconds());
            }
            if (!cacheControl2.noCache()) {
                long j = millis2 + jCacheResponseAge;
                if (j < millis + jComputeFreshnessLifetime) {
                    Response.Builder builderNewBuilder = this.cacheResponse.newBuilder();
                    if (j >= jComputeFreshnessLifetime) {
                        builderNewBuilder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jCacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                        builderNewBuilder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new CacheStrategy(null, builderNewBuilder.build());
                }
            }
            String str = this.etag;
            String str2 = "If-Modified-Since";
            if (str != null) {
                str2 = "If-None-Match";
            } else if (this.lastModified != null) {
                str = this.lastModifiedString;
            } else if (this.servedDate != null) {
                str = this.servedDateString;
            } else {
                return new CacheStrategy(this.request, null);
            }
            Headers.Builder builderNewBuilder2 = this.request.headers().newBuilder();
            Intrinsics.checkNotNull(str);
            builderNewBuilder2.addLenient$okhttp(str2, str);
            return new CacheStrategy(this.request.newBuilder().headers(builderNewBuilder2.build()).build(), this.cacheResponse);
        }

        private final long computeFreshnessLifetime() {
            Long lValueOf;
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            if (response.cacheControl().maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.maxAgeSeconds());
            }
            Date date = this.expires;
            if (date != null) {
                Date date2 = this.servedDate;
                lValueOf = date2 != null ? Long.valueOf(date2.getTime()) : null;
                long time = date.getTime() - (lValueOf == null ? this.receivedResponseMillis : lValueOf.longValue());
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
                return 0L;
            }
            Date date3 = this.servedDate;
            lValueOf = date3 != null ? Long.valueOf(date3.getTime()) : null;
            long jLongValue = lValueOf == null ? this.sentRequestMillis : lValueOf.longValue();
            Date date4 = this.lastModified;
            Intrinsics.checkNotNull(date4);
            long time2 = jLongValue - date4.getTime();
            if (time2 > 0) {
                return time2 / ((long) 10);
            }
            return 0L;
        }

        private final long cacheResponseAge() {
            Date date = this.servedDate;
            long jMax = date != null ? Math.max(0L, this.receivedResponseMillis - date.getTime()) : 0L;
            int i = this.ageSeconds;
            if (i != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.receivedResponseMillis;
            return jMax + (j - this.sentRequestMillis) + (this.nowMillis - j);
        }

        private final boolean hasConditions(Request request) {
            return (request.header("If-Modified-Since") == null && request.header("If-None-Match") == null) ? false : true;
        }
    }

    /* JADX INFO: compiled from: CacheStrategy.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Companion;", "", "()V", "isCacheable", "", "response", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean isCacheable(okhttp3.Response r5, okhttp3.Request r6) {
            /*
                r4 = this;
                java.lang.String r0 = "response"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.String r0 = "request"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                int r0 = r5.code()
                r1 = 200(0xc8, float:2.8E-43)
                r2 = 0
                if (r0 == r1) goto L65
                r1 = 410(0x19a, float:5.75E-43)
                if (r0 == r1) goto L65
                r1 = 414(0x19e, float:5.8E-43)
                if (r0 == r1) goto L65
                r1 = 501(0x1f5, float:7.02E-43)
                if (r0 == r1) goto L65
                r1 = 203(0xcb, float:2.84E-43)
                if (r0 == r1) goto L65
                r1 = 204(0xcc, float:2.86E-43)
                if (r0 == r1) goto L65
                r1 = 307(0x133, float:4.3E-43)
                if (r0 == r1) goto L3b
                r1 = 308(0x134, float:4.32E-43)
                if (r0 == r1) goto L65
                r1 = 404(0x194, float:5.66E-43)
                if (r0 == r1) goto L65
                r1 = 405(0x195, float:5.68E-43)
                if (r0 == r1) goto L65
                switch(r0) {
                    case 300: goto L65;
                    case 301: goto L65;
                    case 302: goto L3b;
                    default: goto L3a;
                }
            L3a:
                return r2
            L3b:
                r0 = 2
                java.lang.String r1 = "Expires"
                r3 = 0
                java.lang.String r0 = okhttp3.Response.header$default(r5, r1, r3, r0, r3)
                if (r0 != 0) goto L65
                okhttp3.CacheControl r0 = r5.cacheControl()
                int r0 = r0.maxAgeSeconds()
                r1 = -1
                if (r0 != r1) goto L65
                okhttp3.CacheControl r0 = r5.cacheControl()
                boolean r0 = r0.getIsPublic()
                if (r0 != 0) goto L65
                okhttp3.CacheControl r0 = r5.cacheControl()
                boolean r0 = r0.getIsPrivate()
                if (r0 != 0) goto L65
                return r2
            L65:
                okhttp3.CacheControl r5 = r5.cacheControl()
                boolean r5 = r5.noStore()
                if (r5 != 0) goto L7a
                okhttp3.CacheControl r5 = r6.cacheControl()
                boolean r5 = r5.noStore()
                if (r5 != 0) goto L7a
                r2 = 1
            L7a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.CacheStrategy.Companion.isCacheable(okhttp3.Response, okhttp3.Request):boolean");
        }
    }
}
