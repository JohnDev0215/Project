package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpBody;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.exception.ApolloNetworkException;
import com.apollographql.apollo3.network.OkHttpExtensionsKt;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

/* JADX INFO: compiled from: OkHttpEngine.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\n\u0010\u000eB\u0013\b\u0016\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\n\u0010\u0011B\u0019\b\u0016\u0012\u0006\u0010\u0012\u001a\u00020\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u000f¢\u0006\u0004\b\n\u0010\u0014J\u001b\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/network/http/DefaultHttpEngine;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "request", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "execute", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lokhttp3/Call$Factory;", "httpCallFactory", "Lokhttp3/Call$Factory;", "<init>", "(Lokhttp3/Call$Factory;)V", "Lokhttp3/OkHttpClient;", "okHttpClient", "(Lokhttp3/OkHttpClient;)V", "", "timeoutMillis", "(J)V", "connectTimeout", "readTimeout", "(JJ)V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class DefaultHttpEngine implements HttpEngine {
    private final Call.Factory httpCallFactory;

    public DefaultHttpEngine(Call.Factory httpCallFactory) {
        Intrinsics.checkNotNullParameter(httpCallFactory, "httpCallFactory");
        this.httpCallFactory = httpCallFactory;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DefaultHttpEngine(OkHttpClient okHttpClient) {
        this((Call.Factory) okHttpClient);
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
    }

    public DefaultHttpEngine(long j) {
        this(j, j);
    }

    public /* synthetic */ DefaultHttpEngine(long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 60000L : j);
    }

    public DefaultHttpEngine(long j, long j2) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        this(builder.connectTimeout(j, timeUnit).readTimeout(j2, timeUnit).build());
    }

    @Override // com.apollographql.apollo3.network.http.HttpEngine
    public Object execute(HttpRequest httpRequest, Continuation<? super HttpResponse> continuation) throws IOException {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Request.Builder builderHeaders = new Request.Builder().url(httpRequest.getUrl()).headers(OkHttpExtensionsKt.toOkHttpHeaders(httpRequest.getHeaders()));
        if (httpRequest.getMethod() == HttpMethod.Get) {
            builderHeaders.get();
        } else {
            final HttpBody body = httpRequest.getBody();
            if (!(body != null)) {
                throw new IllegalStateException("HTTP POST requires a request body".toString());
            }
            builderHeaders.post(new RequestBody() { // from class: com.apollographql.apollo3.network.http.DefaultHttpEngine$execute$2$httpRequest$1$2
                @Override // okhttp3.RequestBody
                /* JADX INFO: renamed from: contentType */
                public MediaType getContentType() {
                    return MediaType.INSTANCE.get(body.getContentType());
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return body.getContentLength();
                }

                @Override // okhttp3.RequestBody
                public void writeTo(BufferedSink sink) {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    body.writeTo(sink);
                }
            });
        }
        final Call callNewCall = this.httpCallFactory.newCall(builderHeaders.build());
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.apollographql.apollo3.network.http.DefaultHttpEngine$execute$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callNewCall.cancel();
            }
        });
        Response responseExecute = null;
        try {
            responseExecute = FirebasePerfOkHttpClient.execute(callNewCall);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        if (e != null) {
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new ApolloNetworkException("Failed to execute GraphQL http network request", e))));
        } else {
            Result.Companion companion2 = Result.INSTANCE;
            Intrinsics.checkNotNull(responseExecute);
            HttpResponse.Builder builder = new HttpResponse.Builder(responseExecute.code());
            ResponseBody responseBodyBody = responseExecute.body();
            Intrinsics.checkNotNull(responseBodyBody);
            HttpResponse.Builder builderBody = builder.body(responseBodyBody.getSource());
            Headers headers = responseExecute.headers();
            IntRange intRangeUntil = RangesKt___RangesKt.until(0, headers.size());
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(intRangeUntil, 10));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                int iNextInt = ((IntIterator) it).nextInt();
                arrayList.add(new HttpHeader(headers.name(iNextInt), headers.value(iNextInt)));
            }
            Object objM2810constructorimpl = Result.m2810constructorimpl(builderBody.addHeaders(arrayList).build());
            ResultKt.throwOnFailure(objM2810constructorimpl);
            cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(objM2810constructorimpl));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
