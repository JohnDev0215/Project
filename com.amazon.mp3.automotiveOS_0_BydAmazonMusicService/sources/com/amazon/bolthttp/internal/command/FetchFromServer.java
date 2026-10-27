package com.amazon.bolthttp.internal.command;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.CancelledException;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.HttpInterceptor;
import com.amazon.bolthttp.HttpResponse;
import com.amazon.bolthttp.NetworkStrategy;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.RequestStrategy;
import com.amazon.bolthttp.internal.Affinity$NetworkAffinity;
import com.amazon.bolthttp.internal.Command;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.NetworkSharedState;
import com.amazon.bolthttp.internal.command.NetworkEventListener;
import com.amazon.bolthttp.internal.net.WifiLockHolder;
import com.amazon.bolthttp.internal.util.StrategyHelper;
import com.amazon.bolthttp.internal.util.TimeUtils;
import com.amazon.bolthttp.policy.NetworkContext;
import com.amazon.bolthttp.policy.RequestContext;
import com.google.common.base.Preconditions;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;
import okhttp3.logging.HttpLoggingInterceptor;

/* JADX INFO: loaded from: classes2.dex */
final class FetchFromServer<T> extends Command<Affinity$NetworkAffinity, NetworkSharedState> {

    @Nonnull
    private final DownloadStatistics.DownloadStatisticsBuilder mDownloadStatisticsBuilder;
    private Logger mLogger;
    private NetworkContext mNetworkContext;
    private NetworkStrategy mNetworkStrategy;
    private final Request<T> mRequest;
    private RequestContext mRequestContext;
    private RequestStrategy mRequestStrategy;
    private StrategyHelper<T> mStrategyHelper;

    FetchFromServer(@Nonnull Request<T> request, @Nullable StrategyHelper<T> strategyHelper) {
        super(Affinity$NetworkAffinity.class);
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        this.mStrategyHelper = strategyHelper;
        this.mDownloadStatisticsBuilder = new DownloadStatistics.DownloadStatisticsBuilder();
    }

    @Override // com.amazon.bolthttp.internal.Command
    protected void execute() {
        this.mLogger = getLogger();
        WifiLockHolder wifiLockHolder = getSharedState().getWifiLockHolder();
        try {
            try {
                wifiLockHolder.acquireLock();
                initializeStrategyAndContext();
                if (this.mRequestContext.getAttemptCount() == 0 && !this.mRequestStrategy.getRetryPolicy().shouldRetry(this.mRequestContext)) {
                    Exception returnedException = this.mRequestStrategy.getRetryPolicy().getReturnedException(this.mRequestContext);
                    this.mLogger.log(Logger.Type.WARN, "FetchFromServer: (%sms, #%s) Aborting request due to %s for %s", Long.valueOf(this.mNetworkContext.getTotalElapsedTime()), Integer.valueOf(this.mNetworkContext.getAttemptCount()), returnedException, this.mRequest);
                    dispatch(InvokeCallback.onFailure(this.mRequest, BoltException.Component.RESPONSE_HANDLER, returnedException, this.mDownloadStatisticsBuilder.build()));
                    return;
                }
                TimeUtils.sleep(this.mRequestStrategy.getBackoffPolicy().getBackoffMillis(this.mRequestContext));
                this.mRequestContext.onStartNextAttempt();
                reportStartFetchEvent();
                while (this.mNetworkStrategy.getRetryPolicy().shouldRetry(this.mNetworkContext)) {
                    TimeUtils.sleep(this.mNetworkStrategy.getBackoffPolicy().getBackoffMillis(this.mNetworkContext));
                    this.mNetworkContext.onStartNextAttempt();
                    HttpResponse<T> httpResponseExecuteAttempt = executeAttempt();
                    if (httpResponseExecuteAttempt != null) {
                        dispatch(new ProcessResponse(this.mRequest, this.mStrategyHelper, httpResponseExecuteAttempt, this.mDownloadStatisticsBuilder.build()));
                        return;
                    }
                }
                Exception returnedException2 = this.mNetworkStrategy.getRetryPolicy().getReturnedException(this.mNetworkContext);
                Preconditions.checkNotNull(returnedException2, "RetryPolicy.getReturnedException() == null");
                this.mLogger.log(Logger.Type.WARN, "FetchFromServer: (%sms, #%s) Aborting network retry due to %s for %s", Long.valueOf(this.mNetworkContext.getTotalElapsedTime()), Integer.valueOf(this.mNetworkContext.getAttemptCount()), returnedException2, this.mRequest);
                dispatch(InvokeCallback.onFailure(this.mRequest, BoltException.Component.NETWORK, returnedException2, this.mDownloadStatisticsBuilder.build()));
            } catch (CancelledException e) {
                this.mLogger.log(Logger.Type.INFO, "FetchFromServer: (%sms, #%s) Aborting network request because it was cancelled: %s", Long.valueOf(this.mNetworkContext.getTotalElapsedTime()), Integer.valueOf(this.mNetworkContext.getAttemptCount()), this.mRequest);
                dispatch(InvokeCallback.onFailure(this.mRequest, BoltException.Component.RESPONSE_HANDLER, e, this.mDownloadStatisticsBuilder.build()));
            } catch (InterruptedException e2) {
                this.mLogger.log(Logger.Type.WARN, "FetchFromServer: (%sms, #%s) Aborting network attempt due to %s for %s", Long.valueOf(this.mNetworkContext.getTotalElapsedTime()), Integer.valueOf(this.mNetworkContext.getAttemptCount()), e2, this.mRequest);
                dispatch(InvokeCallback.onFailure(this.mRequest, BoltException.Component.NETWORK, e2, this.mDownloadStatisticsBuilder.build()));
            }
        } finally {
            wifiLockHolder.releaseLock();
        }
    }

    private void initializeStrategyAndContext() {
        if (this.mStrategyHelper == null) {
            StrategyHelper<T> strategyHelper = new StrategyHelper<>(this.mRequest, getSharedState().getConfig());
            this.mStrategyHelper = strategyHelper;
            strategyHelper.initializeRequestContextAndPolicies();
        }
        this.mRequestContext = this.mStrategyHelper.getRequestContext();
        this.mRequestStrategy = this.mStrategyHelper.getRequestStrategy();
        this.mNetworkContext = this.mStrategyHelper.initializeNetworkContextAndPolicies();
        this.mNetworkStrategy = this.mStrategyHelper.getNetworkStrategy();
    }

    /* JADX WARN: Not initialized variable reg: 14, insn: 0x015d: MOVE (r12 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:57:0x015d */
    /* JADX WARN: Not initialized variable reg: 15, insn: 0x015e: MOVE (r4 I:??[long, double]) = (r15 I:??[long, double]), block:B:57:0x015d */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e1 A[Catch: all -> 0x015c, TryCatch #5 {all -> 0x015c, blocks: (B:15:0x0090, B:40:0x00dd, B:42:0x00e1, B:44:0x00f1), top: B:71:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.bolthttp.HttpResponse<T> executeAttempt() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.bolthttp.internal.command.FetchFromServer.executeAttempt():com.amazon.bolthttp.HttpResponse");
    }

    private void waitForNetwork() throws InterruptedException, IOException {
        getSharedState().getConnectivityChecker().waitUntilConnected(this.mNetworkStrategy.getTimeoutPolicy().getConnectivityTimeoutMillis(this.mNetworkContext));
    }

    private Response executeCall(@Nonnegative long j) throws IOException {
        HttpInterceptor.Params params = new HttpInterceptor.Params(this.mRequest.getUrl(), this.mRequest.getHeaders());
        HttpInterceptor httpInterceptor = this.mRequest.getHttpInterceptor();
        if (httpInterceptor != null) {
            httpInterceptor.beforeExecute(params, this.mNetworkContext);
        }
        OkHttpClient okHttpClientNewHttpClient = newHttpClient(j);
        Request.Builder builder = new Request.Builder();
        builder.url(params.getUrl());
        boolean z = HttpMethod.requiresRequestBody(this.mRequest.getMethod().name()) && this.mRequest.getBody() == null;
        boolean z2 = (HttpMethod.permitsRequestBody(this.mRequest.getMethod().name()) || this.mRequest.getBody() == null) ? false : true;
        if (z) {
            builder.method(this.mRequest.getMethod().name(), RequestBody.create((MediaType) null, new byte[0]));
        } else if (z2) {
            this.mLogger.log(Logger.Type.WARN, "FetchFromServer: (%sms, #%s) Request contains body which is being dropped due to incompatible method (%s) for %s", Long.valueOf(this.mNetworkContext.getTotalElapsedTime()), Integer.valueOf(this.mNetworkContext.getAttemptCount()), this.mRequest.getMethod().name(), this.mRequest);
            builder.method(this.mRequest.getMethod().name(), null);
        } else if (this.mRequest.getBody() == null) {
            builder.method(this.mRequest.getMethod().name(), null);
        } else {
            builder.method(this.mRequest.getMethod().name(), this.mRequest.getBody().toRequestBody());
        }
        Headers headers = params.getHeaders();
        for (int i = 0; i < headers.size(); i++) {
            builder.header(headers.name(i), headers.value(i));
        }
        return FirebasePerfOkHttpClient.execute(okHttpClientNewHttpClient.newCall(builder.build()));
    }

    private OkHttpClient newHttpClient(@Nonnegative long j) {
        OkHttpClient.Builder builderNewOkHttpClientBuilder = getSharedState().newOkHttpClientBuilder();
        long connectTimeoutMillis = this.mNetworkStrategy.getTimeoutPolicy().getConnectTimeoutMillis(this.mNetworkContext);
        if (connectTimeoutMillis != -1) {
            if (connectTimeoutMillis > 2147483647L) {
                throw new IllegalStateException("Connect timeout too large: " + connectTimeoutMillis);
            }
            builderNewOkHttpClientBuilder.connectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS);
        }
        long readTimeoutMillis = this.mNetworkStrategy.getTimeoutPolicy().getReadTimeoutMillis(this.mNetworkContext);
        if (readTimeoutMillis != -1) {
            if (readTimeoutMillis > 2147483647L) {
                throw new IllegalStateException("Read timeout too large: " + readTimeoutMillis);
            }
            builderNewOkHttpClientBuilder.readTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS);
        }
        if (this.mRequest.getHttpLogger() != null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(this.mRequest.getHttpLogger());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builderNewOkHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }
        builderNewOkHttpClientBuilder.eventListenerFactory(new NetworkEventListener.Factory(this.mRequest, j));
        return builderNewOkHttpClientBuilder.build();
    }

    @Nonnull
    private HttpResponse<T> processResponse(@Nonnull Response response) throws IOException, CancelledException {
        Preconditions.checkNotNull(response, "response");
        HttpResponse.Builder builder = HttpResponse.builder();
        builder.setRequest(this.mRequest);
        if (this.mRequest.isCancelled()) {
            throw new CancelledException("Request cancelled");
        }
        builder.setResponseCode(response.code());
        builder.setResponseMessage(response.message());
        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    builder.addHeader(key, it.next());
                }
            }
        }
        builder.setBody(response.body().bytes());
        return builder.build();
    }

    private void reportStartFetchEvent() {
        Iterator<EventListener> it = this.mRequest.getEventListeners().iterator();
        while (it.hasNext()) {
            it.next().onExecutionStart(this.mRequest);
        }
    }

    private void reportNetworkEvent(@Nonnull EventListener.NetworkEvent.State state, int i, long j, @Nullable Exception exc) {
        if (this.mRequest.getEventListeners().isEmpty()) {
            return;
        }
        EventListener.NetworkEvent networkEvent = new EventListener.NetworkEvent(state, i, j, exc);
        Iterator<EventListener> it = this.mRequest.getEventListeners().iterator();
        while (it.hasNext()) {
            it.next().onNetworkEvent(networkEvent, this.mRequest);
        }
    }

    @Override // com.amazon.bolthttp.internal.Command
    public String getTraceName() {
        return String.format("%s:%s", FetchFromServer.class.getSimpleName(), this.mRequest.getUrl().getPath());
    }

    public String toString() {
        return String.format("Command[class=%s, %s]", FetchFromServer.class.getSimpleName(), this.mRequest.toSimpleString());
    }
}
