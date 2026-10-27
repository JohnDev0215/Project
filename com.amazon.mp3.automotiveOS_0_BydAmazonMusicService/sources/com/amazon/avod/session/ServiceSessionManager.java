package com.amazon.avod.session;

import android.content.Context;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.session.perf.SessionTags;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.ProfiledRunnable;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ServiceSessionManager implements SessionIdFetchingCallback {
    private volatile SessionConfig mSessionConfig;
    private volatile SessionRetriever mSessionRetriever;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private volatile String mSessionId = null;
    private final ExecutorService mExecutorService = ExecutorBuilder.newBuilderFor(ServiceSessionManager.class, "SessionIdRetrievalExecutor").withFixedThreadPoolSize(1).build();

    private static class SingletonHolder {
        private static volatile ServiceSessionManager sInstance = new ServiceSessionManager(new NoopSessionRetriever(), SessionConfig.getInstance());

        private SingletonHolder() {
        }
    }

    public static ServiceSessionManager getInstance() {
        return SingletonHolder.sInstance;
    }

    ServiceSessionManager(@Nonnull SessionRetriever sessionRetriever, @Nonnull SessionConfig sessionConfig) {
        this.mSessionRetriever = (SessionRetriever) Preconditions.checkNotNull(sessionRetriever, "sessionRetriever");
        this.mSessionConfig = (SessionConfig) Preconditions.checkNotNull(sessionConfig, "sessionConfig");
    }

    public void initialize(@Nonnull Context context) {
        this.mInitializationLatch.start(30L, TimeUnit.SECONDS);
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.CRITICAL, SessionTags.TRACE_INITIALIZATION);
        this.mSessionRetriever.initialize(context);
        Profiler.endTrace(traceKeyBeginTrace);
        this.mInitializationLatch.complete();
    }

    public void waitOnInitialized() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public void retrieveSessionId() {
        this.mInitializationLatch.checkInitialized();
        this.mExecutorService.submit(new ProfiledRunnable(new Runnable() { // from class: com.amazon.avod.session.ServiceSessionManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$retrieveSessionId$0();
            }
        }, Profiler.TraceLevel.INFO, "ServiceSessionManager:retrieveSessionId", new Object[0]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: retrieveSessionIdInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$retrieveSessionId$0() {
        this.mInitializationLatch.checkInitialized();
        Profiler.reportCounterWithNameParameters(SessionIdMetrics.SESSION_ID_RETRIEVAL_STARTED, ImmutableList.of(this.mSessionRetriever.type()));
        DLog.logf("SessionId: retrieving latest sessionId.");
        this.mSessionId = this.mSessionConfig.getCachedSessionId();
        Profiler.trigger(SessionTags.RETRIEVAL_START);
        SessionRetriever sessionRetriever = this.mSessionRetriever;
        try {
            sessionRetriever.retrieveSessionId(this);
        } catch (InterruptedException e) {
            DLog.exceptionf(e, "Interrupted while retrieving from %s. Keeping current sessionId.", sessionRetriever);
            Profiler.trigger(SessionTags.RETRIEVAL_FAILURE);
            Thread.currentThread().interrupt();
        } catch (ExecutionException e2) {
            DLog.exceptionf(e2, "Failed to retrieve from %s. Keeping current sessionId.", sessionRetriever);
            Profiler.trigger(SessionTags.RETRIEVAL_FAILURE);
        }
    }

    @Nullable
    public String getSessionId() {
        this.mInitializationLatch.checkInitialized();
        return this.mSessionId;
    }

    @Override // com.amazon.avod.session.SessionIdFetchingCallback
    public void onSessionIdFetched(@Nullable String str) {
        DLog.devf("Successfully retrieved sessionId \"%s\" from %s.", str, this.mSessionRetriever);
        reportMetricsAndUpdateSessionIdIfNecessary(str, SessionIdMetrics.SESSION_ID_NOT_CHANGED, SessionIdMetrics.SESSION_ID_CHANGED, true);
        Profiler.trigger(SessionTags.RETRIEVAL_SUCCESS);
        Profiler.reportCounterWithNameParameters(SessionIdMetrics.SESSION_ID_RETRIEVAL_SUCCESS, ImmutableList.of(this.mSessionRetriever.type()));
    }

    public void reportMetricsAndUpdateSessionIdIfNecessary(@Nullable String str, @Nonnull SessionIdMetrics sessionIdMetrics, @Nonnull SessionIdMetrics sessionIdMetrics2, boolean z) {
        if ((this.mSessionId == null && str == null) || (this.mSessionId != null && this.mSessionId.equals(str))) {
            Profiler.reportCounterWithNameParameters(sessionIdMetrics, ImmutableList.of(this.mSessionRetriever.type()));
        } else {
            Profiler.reportCounterWithNameParameters(sessionIdMetrics2, ImmutableList.of(this.mSessionRetriever.type()));
        }
        if (z) {
            this.mSessionConfig.setCachedSessionId(str);
            this.mSessionId = str;
        }
    }
}
